package com.sunming.quickcopy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

@SuppressLint("ValidFragment")
public class FavoritesTab extends Fragment {
		Context mContext;
		private AdView mAdView;

		public FavoritesTab(Context context) {
			mContext = context;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, 
				ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.activity_favoritestab, null);

			settingAd(view);

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

}