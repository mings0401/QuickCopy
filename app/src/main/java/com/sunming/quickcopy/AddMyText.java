package com.sunming.quickcopy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sunming.quickcopy.Database.MySQLiteHandler;

public class AddMyText extends Activity {
    private EditText inputTitleEditTxt, inputContentsEditTxt;
    private MySQLiteHandler ms;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_text);

        ms = new MySQLiteHandler(this);

        settingAd();

        ActionBar actionBar = getActionBar();
        actionBar = getActionBar();
        actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);			//액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);		//액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);			//홈 아이콘을 숨김처리합니다.

        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.layout_actionbar, null);
        actionBar.setCustomView(mCustomView);

        inputTitleEditTxt = (EditText) findViewById(R.id.inputTitleEditTxt);
        inputContentsEditTxt = (EditText) findViewById(R.id.inputContentsEditTxt);

        findViewById(R.id.saveMyTestBtn).setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if(ms.insertMyText(inputTitleEditTxt.getText().toString(), inputContentsEditTxt.getText().toString())){
               finish();
            }


        }
    };

    /**
     * 광고 셋팅해주는 함수
     */
    public void settingAd(){
        //상단 배너 광고
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView = (AdView) findViewById(R.id.addMyTextAdview);
        mAdView.loadAd(adRequest);
    }

}
