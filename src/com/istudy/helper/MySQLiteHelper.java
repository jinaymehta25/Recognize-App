package com.istudy.helper;

import com.istudy.dataset.DataSet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{
	public static final String TABLE_GAMEPLAY = "gameplay";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PLAYED = "played";
	public static final String COLUMN_HIGHSCORE = "highscore";

	private static final String DATABASE_NAME = "recognize.db";
	private static final int DATABASE_VERSION = 3;
	
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_GAMEPLAY + "(" + COLUMN_ID + " integer primary key, " + COLUMN_PLAYED+ " integer, " 
		  + COLUMN_HIGHSCORE + " integer);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("MySQLiteHelper", "onCreate");
		db.execSQL(DATABASE_CREATE);
		for(int i=0;i<DataSet.themeIdArray.length;i++)
			addData(i,db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMEPLAY);
		    onCreate(db);		    
	}
	
	private void addData(int id, SQLiteDatabase db) {
	    

	    ContentValues values = new ContentValues();
	    values.put(COLUMN_ID, id); // StatesModel Name
	    values.put(COLUMN_PLAYED, 0); // StatesModel Phone
	    values.put(COLUMN_HIGHSCORE, 0); // StatesModel Phone

	    // Inserting Row
	    db.insert(TABLE_GAMEPLAY, null, values);
	    
	   }

}
