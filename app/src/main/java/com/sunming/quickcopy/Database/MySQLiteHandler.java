package com.sunming.quickcopy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.sunming.quickcopy.Util.MyText;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by minkyujo on 2016. 12. 10..
 */

public class MySQLiteHandler {
    //DB 관련 변수
    private MySQLiteOpenHelper helper;
    String dbName = "quickcopy.db";
    int dbVersion = 1; // 데이터베이스 버전
    private SQLiteDatabase db;

   public MySQLiteHandler(Context context){
       // sqLite3 : 모바일 용으로 제작된 경량화 DB
       helper = new MySQLiteOpenHelper(context, dbName, null, dbVersion);

       try {
           db = helper.getWritableDatabase(); // 읽고 쓸수 있는 DB
           //db = helper.getReadableDatabase(); // 읽기 전용 DB select문
       } catch (SQLiteException e) {
           e.printStackTrace();
       }
   }

    public boolean updateMyText(String uuid, String title, String contents, int isFavorites){
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("contents", contents);
        values.put("isFavorites", isFavorites);

        return db.update("myText", values, "uuid=?", new String[]{uuid}) > 0;
    }

    /**
     * DB에 네 text집어넣기
     */
    public boolean insertMyText (String title, String contents) {
        UUID uuid = UUID.randomUUID();
        String s_uuid = uuid.toString(); //uuid 랜덤 생성
        ContentValues values = new ContentValues();
        values.put("uuid", s_uuid);
        values.put("title", title);
        values.put("contents", contents);
        values.put("isFavorites", 1);
        return db.insert("myText", null, values) > 0;
    }

    /**
     * DB에서 목록 가져오기
     */
    public HashMap getMyText() {
        Cursor c = db.rawQuery("select * from myText;", null);
        int _id = 0; String title="", contents="";
        HashMap<String, MyText> map = new HashMap<String, MyText>();

        while(c.moveToNext()) {
            MyText myText = new MyText(c.getString(0), c.getString(1), c.getString(2), c.getInt(3));

            map.put(c.getString(0), myText);
        }
        return map;
    }

    /**
     * DB에서 즐겨찾기 목록 가져오기
     */
    public HashMap getFavoritesMyText() {
        Cursor c = db.rawQuery("select * from myText where isFavorites = 1;", null);
        int _id = 0; String title="", contents="";
        HashMap<String, MyText> map = new HashMap<String, MyText>();

        while(c.moveToNext()) {
            MyText myText = new MyText(c.getString(0), c.getString(1), c.getString(2), c.getInt(3));

            map.put(c.getString(0), myText);
        }
        return map;
    }

    /**
     *
     * @param uuid
     */
    public boolean delete(String uuid) {
        // 입력한 항목과 일치하는 행 삭제
        return db.delete("myText", "uuid=?", new String[]{uuid}) > 0;
    }


}

