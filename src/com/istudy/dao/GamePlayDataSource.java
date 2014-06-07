package com.istudy.dao;


import java.util.LinkedList;

import com.istudy.bean.Albums;
import com.istudy.helper.MySQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class GamePlayDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
		      MySQLiteHelper.COLUMN_PLAYED, MySQLiteHelper.COLUMN_HIGHSCORE };
	
	public GamePlayDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLiteException	 {
	    database = dbHelper.getWritableDatabase();
	}

	public void close() {
	    dbHelper.close();
	}
	
	public Albums getGamePlayRecord(int id){
		Albums album = new Albums();		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMEPLAY,
		        allColumns, MySQLiteHelper.COLUMN_ID + "=?",new String[] {Integer.toString(id)}, null, null, null, null);
		
		if(cursor !=null)
			cursor.moveToFirst();
		
		
		album.setId(Integer.parseInt(cursor.getString(0)));
		album.setPlayed(Integer.parseInt(cursor.getString(1)));
		album.setHighscore(Integer.parseInt(cursor.getString(2)));
		cursor.close();
		return album;
	}
	
	public int updateGamePlayRecord(Albums album){
		ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_PLAYED, album.isPlayed());
	    values.put(MySQLiteHelper.COLUMN_HIGHSCORE, album.getHighscore());
	 
	    // updating row
	    return database.update(MySQLiteHelper.TABLE_GAMEPLAY, values, MySQLiteHelper.COLUMN_ID + " = ?",
	            new String[] { String.valueOf(album.getId()) });
	}
	
	public LinkedList<Integer> getAvailableThemes(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMEPLAY,
		        allColumns, MySQLiteHelper.COLUMN_PLAYED + "=?",new String[] {Integer.toString(0)}, null, null, null, null);
		
		if(cursor !=null)
			cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
		      list.add(Integer.parseInt(cursor.getString(0)));
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    
		return list;
	}
}
