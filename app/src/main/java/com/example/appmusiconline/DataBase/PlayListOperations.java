package com.example.appmusiconline.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appmusiconline.Model.PersonalSong;

import java.util.ArrayList;

public class PlayListOperations {
    public static final String TAG = "Favorites Database";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            PlayListDBHandler.COLUMN_ID,
            PlayListDBHandler.COLUMN_IMAGE,
            PlayListDBHandler.COLUMN_NAME,
            PlayListDBHandler.COLUMN_TIME,
            PlayListDBHandler.COLUMN_ARTIST,
            PlayListDBHandler.COLUMN_LINK
    };

    public PlayListOperations(Context context)  {
        dbHandler = new PlayListDBHandler(context);
    }

    public void open() {
        Log.i(TAG, " Database Opened");
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database Closed");
        dbHandler.close();
    }

    public void addSong(PersonalSong songsList) {
        open();
        ContentValues values = new ContentValues();
        values.put(PlayListDBHandler.COLUMN_NAME, songsList.getNameSong());
        values.put(PlayListDBHandler.COLUMN_ARTIST, songsList.getArtistSong());
        values.put(PlayListDBHandler.COLUMN_LINK, songsList.getLinkSong());
        values.put(PlayListDBHandler.COLUMN_IMAGE, songsList.getImageSong());
        values.put(PlayListDBHandler.COLUMN_TIME, songsList.getTimeSong());

        database.insertWithOnConflict(PlayListDBHandler.TABLE_SONGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();
    }

    public ArrayList<PersonalSong> getAllPlayList() {
        open();
        Cursor cursor = database.query(PlayListDBHandler.TABLE_SONGS, allColumns,
                null, null, null, null, null);
        ArrayList<PersonalSong> playList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                PersonalSong songsList = new PersonalSong(cursor.getString(cursor.getColumnIndex(PlayListDBHandler.COLUMN_NAME))
                        , cursor.getString(cursor.getColumnIndex(PlayListDBHandler.COLUMN_ARTIST))
                        , cursor.getString(cursor.getColumnIndex(PlayListDBHandler.COLUMN_IMAGE))
                        , cursor.getString(cursor.getColumnIndex(PlayListDBHandler.COLUMN_TIME))
                        , cursor.getString(cursor.getColumnIndex(PlayListDBHandler.COLUMN_LINK)));
                playList.add(songsList);
            }
        }
        close();
        return playList;
    }

    public void removeSong(String songPath) {
        open();
        String whereClause =
                PlayListDBHandler.COLUMN_LINK + "=?";
        String[] whereArgs = new String[]{songPath};

        database.delete(PlayListDBHandler.TABLE_SONGS, whereClause, whereArgs);
        close();
    }

}
