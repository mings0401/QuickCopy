package com.sunming.quickcopy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sunming.quickcopy.Database.MySQLiteHandler;
import com.sunming.quickcopy.ListView.Favorites.ListViewAdapter;
import com.sunming.quickcopy.ListView.Favorites.ListViewItem;
import com.sunming.quickcopy.Util.MyText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

@SuppressLint("ValidFragment")
public class FavoritesTab extends Fragment {
	Context mContext;
	private AdView mAdView;
	MySQLiteHandler ms;
	private ArrayList<ListViewItem> myTextItem;
	private ListViewAdapter myTextAdapter;
	private ListView myFavoritesList;
	private EditText searchFavoritesMyTextEditTxt;
	private Button addMyTestBtn;
	public Context _thisContext;

	public FavoritesTab(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_favoritestab, null);
		_thisContext = getActivity().getApplicationContext();
		ms = new MySQLiteHandler(_thisContext);
		settingAd(view);
		myFavoritesList = (ListView) view.findViewById(R.id.myFavoritesList);

		setCurrentFavoritesMyTextList();

		//listview filter event
		searchFavoritesMyTextEditTxt = (EditText) view.findViewById(R.id.searchFavoritesMyTextEditTxt);
		searchFavoritesMyTextEditTxt.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = searchFavoritesMyTextEditTxt.getText().toString().toLowerCase(Locale.getDefault());
				myTextAdapter.filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
										  int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
									  int arg3) {
				// TODO Auto-generated method stub
			}
		});

		return view;
	}

	/**
	 * 광고 셋팅해주는 함수
	 */
	public void settingAd(View view){
		//상단 배너 광고
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView = (AdView) view.findViewById(R.id.favoritesAdView);
		mAdView.loadAd(adRequest);
	}

	/**
	 * List에 넣을 목록들 가져와서 뿌려주는 함수
	 */
	public void setCurrentFavoritesMyTextList(){
		HashMap map = ms.getFavoritesMyText();
		myTextItem = new ArrayList<ListViewItem>();

		Iterator<String> keys = map.keySet().iterator();
		while ( keys.hasNext() ) {
			String key = keys.next();
			MyText mytext = (MyText) map.get(key);
			String uuid = mytext.getUuid();
			String title = mytext.getTitle();
			String contents = mytext.getContents();
			int isFavorites = mytext.getIsFavorites();

			myTextItem.add(new ListViewItem(uuid, title, contents, isFavorites));
		}

		// 북마크 Adapter 작성
		myTextAdapter = new ListViewAdapter(_thisContext, R.layout.listview, myTextItem);
		myFavoritesList.setAdapter(myTextAdapter);

	}

}