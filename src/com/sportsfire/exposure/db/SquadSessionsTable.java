package com.sportsfire.exposure.db;

import com.sportsfire.db.PlayerTable;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SquadSessionsTable {
	// Table name
	public static final String TABLE_NAME = "squadsessions";
	// Players Table Keys
	public static final String KEY_ID = "_id"; // Primary key
	public static final String KEY_NUMBER = "number";
	public static final String KEY_DATE = "date";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_TYPE = "type";
	public static final String KEY_SESSION = "session";
	public static final String KEY_SQUAD_ID = "squadid";
	public static final String KEY_SEASON_ID = "seasonid";

	public static void onCreate(SQLiteDatabase db) {
		String createValuesTable = "CREATE TABLE " 
				+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_NUMBER + " INTEGER,"
				+ KEY_DATE + " TEXT NOT NULL,"
				+ KEY_DURATION + " INTEGER," 
				+ KEY_TYPE + " TEXT,"
				+ KEY_SESSION + " TEXT," 
				+ KEY_SQUAD_ID
				+ " INTEGER NOT NULL," + KEY_SEASON_ID + " INTEGER NOT NULL" + ")";

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