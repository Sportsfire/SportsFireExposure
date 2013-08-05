package com.sportsfire.exposure.sync;

import com.sportsfire.db.*;
import com.sportsfire.exposure.db.*;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class ExposureProvider extends ContentProvider {
	private DBHelper db;
	
	public static String AUTHORITY = "com.sportsfire.exposure.sync.Provider";
	public static final int SQUAD_SESSIONS = 300;
	public static final int PLAYER_SESSIONS = 330;
	public static final int EXPOSURE_UPDATES = 360;
	
	private static final String BASEPATH = "content://" + AUTHORITY + "/"; 

	private static final String PLAYER_SESSIONS_BASE_PATH = "playersessions";
	private static final String SQUAD_SESSIONS_BASE_PATH = "squadsessions";
	private static final String EXPOSURE_UPDATES_BASE_PATH = "exposureupdates";
	public static final Uri CONTENT_URI_SQUAD_SESSIONS = Uri.parse(BASEPATH + SQUAD_SESSIONS_BASE_PATH);
	public static final Uri CONTENT_URI_PLAYER_SESSIONS = Uri.parse(BASEPATH + PLAYER_SESSIONS_BASE_PATH);
	public static final Uri CONTENT_URI_EXPOSURE_UPDATES = Uri.parse(BASEPATH + EXPOSURE_UPDATES_BASE_PATH);

	public static final String CONTENT_TYPE_SQUAD_SESSIONS = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/type-squad-sessions";
	public static final String CONTENT_TYPE_PLAYER_SESSIONS = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/type-player-sessions";
	public static final String CONTENT_TYPE_EXPOSURE_UPDATES = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/type-exposure-updates";
	public static final String CONTENT_TYPE_SCREENING_VALUES = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/type-screening-values";
	public static final String CONTENT_TYPE_SCREENING_UPDATES = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/type-screening-updates";	

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		sURIMatcher.addURI(AUTHORITY, SQUAD_SESSIONS_BASE_PATH, SQUAD_SESSIONS );
		sURIMatcher.addURI(AUTHORITY, PLAYER_SESSIONS_BASE_PATH, PLAYER_SESSIONS);
		sURIMatcher.addURI(AUTHORITY, EXPOSURE_UPDATES_BASE_PATH, EXPOSURE_UPDATES);
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int rowsAffected = 0;
		SQLiteDatabase sqldb = db.getWritableDatabase();
		int uriType = sURIMatcher.match(uri);
		String id;
		switch(uriType){
		case SQUAD_SESSIONS:
			rowsAffected = sqldb.delete(SquadSessionsTable.TABLE_NAME, selection, selectionArgs);
			break;
		case PLAYER_SESSIONS:
			rowsAffected = sqldb.delete(PlayerSessionsTable.TABLE_NAME, selection, selectionArgs);
			break;
		case EXPOSURE_UPDATES:
			rowsAffected = sqldb.delete(UpdatesTable.TABLE_NAME, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkown URI");
		}
		
		getContext().getContentResolver().notifyChange(uri,null);
		return rowsAffected;
	}

	@Override
	public String getType(Uri uri) {
		int uriType = sURIMatcher.match(uri);
		switch(uriType){
		case SQUAD_SESSIONS:
			return CONTENT_TYPE_SQUAD_SESSIONS;
		case PLAYER_SESSIONS:
			return CONTENT_TYPE_PLAYER_SESSIONS;
		case EXPOSURE_UPDATES:
			return CONTENT_TYPE_EXPOSURE_UPDATES;
		default:
			return null;
				
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqldb = db.getWritableDatabase();
        long newID = 0;
        if(uriType == SQUAD_SESSIONS){
        	newID = sqldb.insert(SquadSessionsTable.TABLE_NAME, null, values);
        } else if(uriType == PLAYER_SESSIONS){
        	newID = sqldb.insert(PlayerSessionsTable.TABLE_NAME, null, values);
        } else if(uriType == EXPOSURE_UPDATES){
        	newID = sqldb.insert(UpdatesTable.TABLE_NAME, null, values);
        }
        if (newID > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, newID);
            getContext().getContentResolver().notifyChange(uri, null);
            return newUri;
        } else {
            throw new SQLException("Failed to insert row into " + uri);
        }
	}

	@Override
	public boolean onCreate() {
		db = new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, 
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		int uriType = sURIMatcher.match(uri);
		switch(uriType){
		case SQUAD_SESSIONS:
			queryBuilder.setTables(SquadSessionsTable.TABLE_NAME);
			// no filter
			break;
		case PLAYER_SESSIONS:
			queryBuilder.setTables(PlayerSessionsTable.TABLE_NAME);
			// no filter
			break;
		case EXPOSURE_UPDATES:
			queryBuilder.setTables(UpdatesTable.TABLE_NAME);
			// no filter
			break;
		default:
			throw new IllegalArgumentException("Unkown URI");
		}
		
		Cursor cursor = queryBuilder.query(db.getReadableDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqldb = db.getWritableDatabase();

        int rowsAffected;
        String tableid = "";
        String tablename = "";
        switch(uriType){
        case PLAYER_SESSIONS:
        	tableid = PlayerSessionsTable.KEY_ID;
        	tablename = PlayerSessionsTable.TABLE_NAME;
        	break;
        case SQUAD_SESSIONS:
        	tableid = SquadSessionsTable.KEY_ID;
        	tablename = SquadSessionsTable.TABLE_NAME;
        	break;
        case EXPOSURE_UPDATES:
        	tableid = UpdatesTable.KEY_ID;
        	tablename = UpdatesTable.TABLE_NAME;
        	break;
        default:
            throw new IllegalArgumentException("Unknown URI");
        }

        switch (uriType) {
        case SQUAD_SESSIONS:
        case PLAYER_SESSIONS:
        case EXPOSURE_UPDATES:
            rowsAffected = sqldb.update(tablename,
                    values, selection, selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
	}

}
