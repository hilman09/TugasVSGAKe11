package com.example.latihandatabase.helper;



import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;

import java.util.HashMap;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbhilman.db";
    private static final String TABLE_SQLITE = "sqlite";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE "+ TABLE_SQLITE + " (" +
                KEY_ID + "INTEGER PRIMARY KEY autoincrement," + KEY_NAME + " TEXT NOT NULL, "
                + KEY_ADDRESS + " TEXT NOT NULL" + " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLITE);
        onCreate(db);
    }

//    public ArrayList<HashMap <String, String>> getAllData(){}
//        ArrayList<HashMap <String, String>> wordList;
//        wordList =  ArrayList<HashMap <String, String>>();
//        String query = "SELECT * FROM " + TABLE_SQLITE;
//        SQLiteDatabase database = this.getWritableDatabase();

        public ArrayList<HashMap<String, String>> getAllData() {
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_SQLITE;

            SQLiteDatabase db = this.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    HashMap <String, String> map = new HashMap<>();
                    map.put(KEY_ID, cursor.getString(0));
                    map.put(KEY_NAME,cursor.getString(1));
                    map.put(KEY_ADDRESS,cursor.getString(2));
                    // Adding contact to list
                   wordList.add(map);
                } while (cursor.moveToNext());
            }
            Log.e("select sqlite: ", "" + wordList );
            db.close();
            return wordList;
        }

    public void addData( String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = " INSERT INTO " + TABLE_SQLITE + " (name , address)" + " VALUES ('"+ name +"', '" + address + "')";
        Log.e("addDatae: ", addQuery );
        db.execSQL(addQuery);
        db.close();
    }

    public void update(int id , String name , String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        String upQuery = " UPDATE " + TABLE_SQLITE + " SET" + KEY_NAME + "=''" + name + " , " + KEY_ADDRESS + "=''"
                + address + "'" + " WHERE " + KEY_ID + "=''" + "'" + id + "'";
        Log.e("update: ", upQuery );
        db.execSQL(upQuery);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delquery = " DELETE FROM " + TABLE_SQLITE + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("delete: ", delquery );
        db.execSQL(delquery);
        db.close();
    }



}
