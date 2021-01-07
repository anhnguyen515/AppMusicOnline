package com.example.appmusiconline.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlayListDBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "playlist.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SONGS           = "playlist";
    public static final String COLUMN_IMAGE          = "imageSong";
    public static final String COLUMN_NAME           = "nameSong";
    public static final String COLUMN_TIME           = "timeSong";
    public static final String COLUMN_ARTIST         = "artistSong";
    public static final String COLUMN_LINK           = "linkSong";
    public static final String COLUMN_ID             = "idSong";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_SONGS + " (" + COLUMN_ID
            + " INTEGER, " + COLUMN_NAME + " TEXT, " + COLUMN_ARTIST
            + " TEXT, " + COLUMN_IMAGE + " TEXT, " + COLUMN_TIME
            + " TEXT, " + COLUMN_LINK + " TEXT PRIMARY KEY " + ")";

    public PlayListDBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL(TABLE_CREATE);
    }
}
