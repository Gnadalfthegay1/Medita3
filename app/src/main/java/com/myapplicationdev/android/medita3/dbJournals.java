package com.myapplicationdev.android.medita3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class dbJournals extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "journalDB.db";
    public static final String TABLE_NAME = "Journal";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "date";
    public static final String COLUMN_NAME_2 = "journal";
    //initialize the database

    public dbJournals(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY ," + COLUMN_NAME + " TEXT,"
                + COLUMN_NAME_2 + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public ArrayList<HashMap<String, String>> loadHandler() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();

            user.put("date",cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            user.put("entry",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_2)));
            userList.add(user);
        }
        return  userList;
    }
    public void addHandler(Journals journal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbJournals.COLUMN_NAME, journal.getDate());
        values.put(dbJournals.COLUMN_NAME_2, journal.getJournal());
        if(db!=null)
            db.insert(TABLE_NAME, null, values);
        else
            Log.d("AppName","db is null");
    }
    public Journals findHandler(String date) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + date + "'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Journals journal = new Journals();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            journal.setDate(cursor.getString(1));
            journal.setJournal(cursor.getString(2));
            cursor.close();
        } else {
            journal = null;
        }
        db.close();
        return journal;
    }
    public boolean deleteHandler(String date) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + date + "'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Journals journal = new Journals();
        if (cursor.moveToFirst()) {
            db.delete(TABLE_NAME, COLUMN_NAME + "=?",
                    new String[] {
                String.valueOf(date)
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(String date, String entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME_2, entry);
        return db.update(TABLE_NAME, args, COLUMN_NAME + " = '" + date+"'", null) > 0;
    }
    public ArrayList<String> loadDate() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            userList.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        }
        return  userList;
    }
}
