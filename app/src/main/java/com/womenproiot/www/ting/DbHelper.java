package com.womenproiot.www.ting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String name = "link.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;

    public DbHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEETUP (SEQ TEXT NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, TITLE TEXT, AGE INTEGER, GENDER TEXT, REG_DATE TEXT, MODI_DATE TEXT);");
        db.execSQL("CREATE TABLE ATTENDEE (SEQ TEXT NOT NULL UNIQUE, NAME TEXT, LATITUDE INTEGER,LONGITUDE INTEGER,REG_DATE TEXT, MODI_DATE TEXT,FR_CODE INTEGER NOT NULL, ADDRESS TEXT NOT NULL);");
       }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE MEETUP ;");
        db.execSQL("DROP TABLE ATTENDEE ;");
        onCreate(db);
//        Toast.makeText(this.,"onUpgrade", Toast.LENGTH_LONG).show();
    }


}