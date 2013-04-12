package net.grainier.appointments;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHandler {

	// We never going to change these
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "_name";
	public static final String KEY_DATE = "_date";
	public static final String KEY_TIME = "_time";
	public static final String KEY_DETAILS = "_details";
	private static final String DATABASE_NAME = "appointmentsDB";
	private static final String DATABASE_TABLE = "appointmentsTable";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	private DbHelper dbHelper;
	private final Context context;
	private SQLiteDatabase sqLiteDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			/*
			 * This method will only use when the first time we create database
			 */
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
					+ " TEXT NOT NULL, " + KEY_DATE + " INTEGER, " + KEY_TIME
					+ " TEXT NOT NULL, " + KEY_DETAILS + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public SQLHandler(Context c) {
		this.context = c;
	}

	public SQLHandler open() throws SQLException {
		dbHelper = new DbHelper(context);

		sqLiteDatabase = dbHelper.getWritableDatabase();

		// dbHelper.onUpgrade(sqLiteDatabase, DATABASE_VERSION,
		// DATABASE_VERSION);

		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long createEntry(String name, int age, String gender, String type,
			float rating) {

		ContentValues values = new ContentValues();

		values.put(KEY_TITLE, name);
		values.put(KEY_DATE, age);
		values.put(KEY_TIME, gender);
		values.put(KEY_DETAILS, type);

		return sqLiteDatabase.insert(DATABASE_TABLE, null, values);

	}

	public ArrayList<String> getUserList() {
		// column list
		String[] columns = new String[] { KEY_ROWID, KEY_TITLE };

		// userList to be returned
		ArrayList<String> userList = new ArrayList<String>();

		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);

		// User result = new User();

		// int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_TITLE);
		// int iAge = c.getColumnIndex(KEY_DATE);
		// int iGender = c.getColumnIndex(KEY_TIME);
		// int iType = c.getColumnIndex(KEY_DETAILS);
		// int iRating = c.getColumnIndex(KEY_RATING);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			userList.add(c.getString(iName));
		}

		return userList;
	}

	public Appointment selectByName(String name) {
		
		Appointment result = new Appointment();
		
		String[] columns = new String[] { KEY_ROWID, KEY_TITLE, KEY_DATE, KEY_TIME, KEY_DETAILS }; // column list

		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, KEY_TITLE + "='" + name + "'", null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			int index_id = c.getColumnIndex(KEY_ROWID);			
			int index_title = c.getColumnIndex(KEY_TITLE);
			int index_date = c.getColumnIndex(KEY_DATE);
			int index_time = c.getColumnIndex(KEY_TIME);
			int index_details = c.getColumnIndex(KEY_DETAILS);
			
			result.setTitle(c.getString(index_title));
			result.setTime(c.getString(index_time));
			result.setDetails(c.getString(index_details));
			return result;
		}
		return null;
	}
}
