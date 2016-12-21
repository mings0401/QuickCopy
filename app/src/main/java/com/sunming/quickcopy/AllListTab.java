package com.sunming.quickcopy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sunming.quickcopy.Database.MySQLiteHandler;
import com.sunming.quickcopy.ListView.ListViewAdapter;
import com.sunming.quickcopy.ListView.ListViewItem;
import com.sunming.quickcopy.Util.MyText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SuppressLint("ValidFragment")
public class AllListTab extends Fragment {
	Context mContext;
	private AdView mAdView;
	MySQLiteHandler ms;
	private List<ListViewItem> myTextItem;
	private ListViewAdapter myTextAdapter;
	private ListView myTextListVeiw;
	public Context _thisContext;
	public AllListTab(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		_thisContext = getActivity().getApplicationContext();
		View view = inflater.inflate(R.layout.activity_alllisttab, null);
		ms = new MySQLiteHandler(_thisContext);
		settingAd(view);
		ms.insertMyText("asd","asd");
		myTextListVeiw = (ListView) view.findViewById(R.id.myCopyList);
		setCurrentMyTextList();

		return view;
	}

	/**
	 * 광고 셋팅해주는 함수
	 */
	public void settingAd(View view){
		//상단 배너 광고
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView = (AdView) view.findViewById(R.id.allListAdView);
		mAdView.loadAd(adRequest);
	}

	/**
	 * List에 넣을 목록들 가져와서 뿌려주는 함수
	 */
	public void setCurrentMyTextList(){
		HashMap map = ms.getMyText();
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
		myTextListVeiw.setAdapter(myTextAdapter);

	}

}