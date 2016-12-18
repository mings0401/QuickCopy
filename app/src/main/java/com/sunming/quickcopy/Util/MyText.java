package com.sunming.quickcopy.Util;

import java.util.UUID;

/**
 * Created by minkyujo on 2016. 12. 10..
 */

public class MyText {
    private String uuid = "";
    private String title = "";
    private String contents = "";
    private int isFavorites = 0; // 0 = false, 1 = true

    public MyText(String uuid, String title, String contents, int isFavorites){
        //사용자가 입력한 값으로 저장
        this.uuid = uuid;
        this.title = title;
        this.contents = contents;
        this.isFavorites = isFavorites;
    }

    public String getId(){
        return this.uuid;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContents(){
        return this.contents;
    }

    public int getIsFavorites() { return this.isFavorites; }
}
