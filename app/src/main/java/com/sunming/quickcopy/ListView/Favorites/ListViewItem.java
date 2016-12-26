package com.sunming.quickcopy.ListView.Favorites;

/**
 * Created by minkyujo on 2016. 12. 10..
 */

public class ListViewItem {
    private String uuid;
    private String title ;
    private String contents;
    private int isFavorites;

    public ListViewItem(String uuid, String title, String contents, int isFavorites){
        this.uuid = uuid;
        this.title = title;
        this.contents = contents;
        this.isFavorites = isFavorites;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public String getTitle() {
        return this.title ;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public String getContents(){
        return this.contents;
    }

    public void setUUID(String uuid){
        this.uuid = uuid;
    }

    public String getUUID(){
        return this.uuid;
    }

    public void setIsFavorites(int isFavorites){ this.isFavorites = isFavorites; }

    public int getIsFavorites(){
        return this.isFavorites;
    }
}
