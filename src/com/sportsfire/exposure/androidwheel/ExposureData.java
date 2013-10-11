package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.sportsfire.db.SeasonTable;
import com.sportsfire.exposure.db.PlayerSessionsTable;
import com.sportsfire.exposure.db.SquadSessionsTable;
import com.sportsfire.exposure.db.UpdatesTable;
import com.sportsfire.unique.Provider;

public class ExposureData {

	private String seasonID;
	private ContentResolver content;
	private Cursor cursor;

	public ExposureData(Context context, String seasonID) {
		content = context.getContentResolver();
		this.seasonID = seasonID;
	}

	public void closeCursor() {
		try {
			cursor.close();
		} catch (Exception e) {
		}
	}

	public String getSeasonStart() {
		String[] projection = { SeasonTable.KEY_START_DATE };
		final String where = SeasonTable.KEY_SEASON_ID + " = '" + seasonID + "'";
		cursor = content.query(Provider.CONTENT_URI_SEASONS, projection, where, null, null);
		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			cursor.moveToFirst();
			String start = cursor.getString(0);
			cursor.close();
			return start;
		}
	}

	public void logAll2(String playerID) {
		ArrayList<ArrayList<String>> exposure = new ArrayList<ArrayList<String>>();
		final String where = PlayerSessionsTable.KEY_PLAYER_ID + " = '" + playerID + "' and "
				+ PlayerSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "'";

		String[] projection = { "*" };
		cursor = content.query(Provider.CONTENT_URI_PLAYER_SESSIONS, projection, where, null,
				PlayerSessionsTable.KEY_NUMBER);
		if (cursor.getCount() == 0) {
			cursor.close();
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				try {
					ArrayList<String> data = new ArrayList<String>();
					for (int i = 0; i < cursor.getColumnCount(); i++) {
						data.add(cursor.getString(i));
					}
					exposure.add(data);
				} catch (Exception e) {
				}

			}

		}
		Log.i("SHOWALL", exposure.toString());
		cursor.close();
	}
	
	public void logAll3() {
		ArrayList<ArrayList<String>> exposure = new ArrayList<ArrayList<String>>();

		String[] projection = { "*" };
		cursor = content.query(Provider.CONTENT_URI_EXPOSURE_UPDATES, projection, null, null,
				null);
		if (cursor.getCount() == 0) {
			cursor.close();
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				try {
					ArrayList<String> data = new ArrayList<String>();
					for (int i = 0; i < cursor.getColumnCount(); i++) {
						data.add(cursor.getString(i));
					}
					exposure.add(data);
				} catch (Exception e) {
				}

			}

		}
		Log.i("SHOWALL", exposure.toString());
		cursor.close();
	}
	public boolean setPlayerExposure(String playerID, String type, int number, String duration, int weekNum,
			int dayNum, String value, String preTraining, String postTraining) {
		if (value == "")
			return false;
		String date = getDateInSeason(weekNum, dayNum);
		return setPlayerExposure(playerID, type, number, duration, date, value, preTraining, postTraining);
	}
	
	public boolean setPlayerExposure(String playerID, String type, int number, String duration, String date, String value, String preTraining, String postTraining) {
		if (value == "")
			return false;
		final String where = PlayerSessionsTable.KEY_PLAYER_ID + " = '" + playerID + "' and "
				+ PlayerSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' and " + PlayerSessionsTable.KEY_NUMBER
				+ " = '" + number + "' and " + PlayerSessionsTable.KEY_DATE + " = '" + date + "'";

		String[] projection = { PlayerSessionsTable.KEY_ID };
		cursor = content.query(Provider.CONTENT_URI_PLAYER_SESSIONS, projection, where, null, null);

		ContentValues values = new ContentValues();

		if (cursor.getCount() == 0) {
			values.put(PlayerSessionsTable.KEY_TYPE, type);
			values.put(PlayerSessionsTable.KEY_PLAYER_ID, playerID);
			values.put(PlayerSessionsTable.KEY_SEASON_ID, seasonID);
			values.put(PlayerSessionsTable.KEY_NUMBER, number);
			values.put(PlayerSessionsTable.KEY_DURATION, duration);
			values.put(PlayerSessionsTable.KEY_SESSION, value);
			values.put(PlayerSessionsTable.KEY_POST_TRAINING, preTraining);
			values.put(PlayerSessionsTable.KEY_PRE_TRAINING, postTraining);
			values.put(PlayerSessionsTable.KEY_DATE, date);
			Uri uri = content.insert(Provider.CONTENT_URI_PLAYER_SESSIONS, values);
			String insertID = uri.getLastPathSegment();

			values.clear();
			values.put(UpdatesTable.KEY_VALUE_ID, Integer.parseInt(insertID));

			content.insert(Provider.CONTENT_URI_EXPOSURE_UPDATES, values);

		} else {
			values.put(PlayerSessionsTable.KEY_SESSION, value);
			values.put(PlayerSessionsTable.KEY_TYPE, type);
			values.put(PlayerSessionsTable.KEY_DURATION, duration);
			values.put(PlayerSessionsTable.KEY_POST_TRAINING, preTraining);
			values.put(PlayerSessionsTable.KEY_PRE_TRAINING, postTraining);
			cursor.moveToFirst();
			content.update(Provider.CONTENT_URI_PLAYER_SESSIONS, values, where, null);
			values.clear();
			values.put(UpdatesTable.KEY_VALUE_ID, cursor.getInt(0));
			values.put(UpdatesTable.KEY_TABLE_NAME, PlayerSessionsTable.TABLE_NAME);
			content.insert(Provider.CONTENT_URI_EXPOSURE_UPDATES, values);

		}
		cursor.close();
		return true;
	}
	
	public void deletePlayerExposure(String playerID, int number, int weekNum, int dayNum) {
		String date = getDateInSeason(weekNum, dayNum);
		final String where = PlayerSessionsTable.KEY_PLAYER_ID + " = '" + playerID + "' and "
				+ PlayerSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' and " 
				+ PlayerSessionsTable.KEY_NUMBER + " = '" + number + "' and "
				+ PlayerSessionsTable.KEY_DATE + " = '" + date + "'";

		String[] projection = { PlayerSessionsTable.KEY_ID };

		cursor = content.query(Provider.CONTENT_URI_PLAYER_SESSIONS, projection, where, null, null);

		if (cursor.getCount() == 0) {
			cursor.close();
		} else {
			cursor.moveToFirst();
			Log.i("DELETE", playerID);
			final String where2 = UpdatesTable.KEY_TABLE_NAME + " = '" + PlayerSessionsTable.TABLE_NAME + "' and "
					+ UpdatesTable.KEY_VALUE_ID + " = '" + cursor.getInt(0) + "'";
			content.delete(Provider.CONTENT_URI_EXPOSURE_UPDATES, where2, null);
			content.delete(Provider.CONTENT_URI_PLAYER_SESSIONS, where, null);
			cursor.close();
		}
	}
	
	
	public ArrayList<ArrayList<String>> getPlayerExposure(String playerID, int weekNum, int dayNum) {
		ArrayList<ArrayList<String>> exposure = new ArrayList<ArrayList<String>>();
		String date = getDateInSeason(weekNum, dayNum);
		final String where = PlayerSessionsTable.KEY_PLAYER_ID + " = '" + playerID + "' and "
				+ PlayerSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' and " + PlayerSessionsTable.KEY_DATE
				+ " = '" + date + "'";

		String[] projection = {PlayerSessionsTable.KEY_PRE_TRAINING, PlayerSessionsTable.KEY_SESSION,
				PlayerSessionsTable.KEY_POST_TRAINING, PlayerSessionsTable.KEY_DURATION, PlayerSessionsTable.KEY_TYPE };

		cursor = content.query(Provider.CONTENT_URI_PLAYER_SESSIONS, projection, where, null,
				PlayerSessionsTable.KEY_NUMBER);

		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				try {
					ArrayList<String> data = new ArrayList<String>();
					data.add(cursor.getString(0));
					data.add(cursor.getString(1));
					data.add(cursor.getString(2));
					data.add(cursor.getString(3));
					data.add(cursor.getString(4));
					exposure.add(data);
				} catch (Exception e) {
				}

			}

		}
		cursor.close();
		return exposure;
	}

	public String getDateInSeason(Integer weekNum, Integer dayNum) {
		Integer count = ((7 * weekNum) + dayNum);
		String[] projection = { "date('" + getSeasonStart() + "','+" + count + " days')" };
		cursor = content.query(Provider.CONTENT_URI_SQUADS, projection, null, null, null);
		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			cursor.moveToFirst();
			String date = cursor.getString(0);
			cursor.close();
			return date;
		}
	}

	public ArrayList<String> getDaysOfWeek(Integer weekNumber) {
		String start = getSeasonStart();
		ArrayList<String> data = new ArrayList<String>();
		Integer pos = (7 * weekNumber);
		String[] projection = new String[7];
		for (int day = 0; day < 7; day++) {
			projection[day] = "strftime('%d','" + start + "','+" + (pos + day) + " days')";
		}
		cursor = content.query(Provider.CONTENT_URI_SQUADS, projection, null, null, null);
		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				data.add(cursor.getString(i));
			}
		}
		cursor.close();
		return data;
	}

	public void logAll(String squadID) {
		ArrayList<ArrayList<String>> exposure = new ArrayList<ArrayList<String>>();
		final String where = SquadSessionsTable.KEY_SQUAD_ID + " = '" + squadID + "' and "
				+ SquadSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "'";
		String[] projection = { "*" };

		cursor = content.query(Provider.CONTENT_URI_SQUAD_SESSIONS, projection, where, null,
				SquadSessionsTable.KEY_DATE);

		if (cursor.getCount() == 0) {
			cursor.close();
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				try {
					ArrayList<String> data = new ArrayList<String>();
					for (int i = 0; i < cursor.getColumnCount(); i++) {
						data.add(cursor.getString(i));
					}
					exposure.add(data);
				} catch (Exception e) {
				}

			}

		}
		Log.i("SHOWALL", exposure.toString());
		cursor.close();
	}


	public boolean setSquadExposure(String squadID, String type, int number, String duration, int weekNum, int dayNum,
			String value) {
		if (value == "")
			return false;
		String date = getDateInSeason(weekNum, dayNum);
		return setSquadExposure(squadID, type, number, duration, date, value);
	}

	public boolean setSquadExposure(String squadID, String type, int number, String duration, String date,
			String value) {
		if (value == "")
			return false;
		final String where = SquadSessionsTable.KEY_SQUAD_ID + " = '" + squadID + "' and "
				+ SquadSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' and " + SquadSessionsTable.KEY_NUMBER
				+ " = '" + number + "' and " + SquadSessionsTable.KEY_DATE + " = '" + date + "'";

		String[] projection = { SquadSessionsTable.KEY_ID };
		cursor = content.query(Provider.CONTENT_URI_SQUAD_SESSIONS, projection, where, null, null);

		ContentValues values = new ContentValues();
		if (cursor.getCount() == 0) {
			values.put(SquadSessionsTable.KEY_TYPE, type);
			values.put(SquadSessionsTable.KEY_SQUAD_ID, squadID);
			values.put(SquadSessionsTable.KEY_SEASON_ID, seasonID);
			values.put(SquadSessionsTable.KEY_DURATION, duration);
			values.put(SquadSessionsTable.KEY_NUMBER, number);
			values.put(SquadSessionsTable.KEY_SESSION, value);
			values.put(SquadSessionsTable.KEY_DATE, date);
			Log.i("NEW", values.toString());
			Uri uri = content.insert(Provider.CONTENT_URI_SQUAD_SESSIONS, values);
			String insertID = uri.getLastPathSegment();
			values.clear();
			values.put(UpdatesTable.KEY_VALUE_ID, Integer.parseInt(insertID));
			values.put(UpdatesTable.KEY_TABLE_NAME, SquadSessionsTable.TABLE_NAME);
			content.insert(Provider.CONTENT_URI_EXPOSURE_UPDATES, values);
		} else {
			values.put(SquadSessionsTable.KEY_TYPE, type);
			values.put(SquadSessionsTable.KEY_DURATION, duration);
			values.put(SquadSessionsTable.KEY_SESSION, value);
			Log.i("UPDATE", values.toString());
			cursor.moveToFirst();
			content.update(Provider.CONTENT_URI_SQUAD_SESSIONS, values, where, null);
			values.clear();
			values.put(UpdatesTable.KEY_VALUE_ID, cursor.getInt(0));
			values.put(UpdatesTable.KEY_TABLE_NAME, SquadSessionsTable.TABLE_NAME);
			content.insert(Provider.CONTENT_URI_EXPOSURE_UPDATES, values);
		}
		cursor.close();
		return true;
	}
	
	public void deleteSquadExposure(String squadID, int number, int weekNum, int dayNum) {
		String date = getDateInSeason(weekNum, dayNum);
		final String where = SquadSessionsTable.KEY_SQUAD_ID + " = '" + squadID + "' and "
				+ SquadSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' and " + SquadSessionsTable.KEY_NUMBER
				+ " = '" + number + "' and " + SquadSessionsTable.KEY_DATE + " = '" + date + "'";

		String[] projection = { SquadSessionsTable.KEY_ID };

		cursor = content.query(Provider.CONTENT_URI_SQUAD_SESSIONS, projection, where, null, null);

		if (cursor.getCount() == 0) {
			cursor.close();
		} else {
			cursor.moveToFirst();
			Log.i("DELETE", squadID);
			final String where2 = UpdatesTable.KEY_TABLE_NAME + " = '" + SquadSessionsTable.TABLE_NAME + "' and "
					+ UpdatesTable.KEY_VALUE_ID + " = '" + cursor.getInt(0) + "'";
			content.delete(Provider.CONTENT_URI_EXPOSURE_UPDATES, where2, null);
			content.delete(Provider.CONTENT_URI_SQUAD_SESSIONS, where, null);
			cursor.close();
		}
	}

	public ArrayList<ArrayList<String>> getSquadExposure(String squadID, int weekNum, int dayNum) {
		ArrayList<ArrayList<String>> exposure = new ArrayList<ArrayList<String>>();
		String date = getDateInSeason(weekNum, dayNum);
		final String where = SquadSessionsTable.KEY_SQUAD_ID + " = '" + squadID + "' AND "
				+ SquadSessionsTable.KEY_SEASON_ID + " = '" + seasonID + "' AND " + SquadSessionsTable.KEY_DATE
				+ " = '" + date + "'";

		String[] projection = { SquadSessionsTable.KEY_SESSION, SquadSessionsTable.KEY_DURATION,
				SquadSessionsTable.KEY_TYPE };

		cursor = content.query(Provider.CONTENT_URI_SQUAD_SESSIONS, projection, where, null,
				SquadSessionsTable.KEY_NUMBER);

		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				try {
					ArrayList<String> data = new ArrayList<String>();
					data.add(cursor.getString(0));
					data.add(cursor.getString(1));
					data.add(cursor.getString(2));
					exposure.add(data);
				} catch (Exception e) {
				}
			}
		}
		cursor.close();
		return exposure;
	}

}
