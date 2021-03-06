package com.sportsfire.exposure.db;

import com.sportsfire.db.PlayerTable;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UpdatesTable {

	// Table name
	public static final String TABLE_NAME = "exposureupdates";

	// Players Table Keys
	public static final String KEY_ID = "_id"; // Primary key
	public static final String KEY_VALUE_ID = "valueid";
	public static final String KEY_TABLE_NAME = "tablename";

	public static void onCreate(SQLiteDatabase db) {
		String createValuesTable = "CREATE TABLE " + TABLE_NAME + "(" 
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_VALUE_ID + " INTEGER,"
				+ KEY_TABLE_NAME + " TEXT" + ")";

		db.execSQL(createValuesTable);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PlayerTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		// Drop older table if it exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}
}