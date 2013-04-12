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
	public static final String KEY_NAME = "user_name";
	public static final String KEY_AGE = "user_age";
	public static final String KEY_GENDER = "user_gender";
	public static final String KEY_TYPE = "user_type";
	public static final String KEY_RATING = "user_rating";

	// Database Name
	private static final String DATABASE_NAME = "UserDB";

	// Table Name
	private static final String DATABASE_TABLE = "userTable";

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
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_AGE + " INTEGER, " + KEY_GENDER
					+ " TEXT NOT NULL, " + KEY_TYPE + " TEXT NOT NULL, "
					+ KEY_RATING + " REAL);");

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

		values.put(KEY_NAME, name);
		values.put(KEY_AGE, age);
		values.put(KEY_GENDER, gender);
		values.put(KEY_TYPE, type);
		values.put(KEY_RATING, rating);

		return sqLiteDatabase.insert(DATABASE_TABLE, null, values);

	}

	public ArrayList<String> getUserList() {
		// column list
		String[] columns = new String[] { KEY_ROWID, KEY_NAME };

		// userList to be returned
		ArrayList<String> userList = new ArrayList<String>();

		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);

		// User result = new User();

		// int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		// int iAge = c.getColumnIndex(KEY_AGE);
		// int iGender = c.getColumnIndex(KEY_GENDER);
		// int iType = c.getColumnIndex(KEY_TYPE);
		// int iRating = c.getColumnIndex(KEY_RATING);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			userList.add(c.getString(iName));
		}

		return userList;
	}

	public Appointment selectByName(String name) {
		
		Appointment result = new Appointment();

		// column list
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_AGE,
				KEY_GENDER, KEY_TYPE, KEY_RATING };

		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, KEY_NAME + "='" + name + "'", null, null, null, null);

		if (c != null) {
			c.moveToFirst();

			int iName = c.getColumnIndex(KEY_NAME);
			int iAge = c.getColumnIndex(KEY_AGE);
			int iGender = c.getColumnIndex(KEY_GENDER);
			int iType = c.getColumnIndex(KEY_TYPE);
			int iRating = c.getColumnIndex(KEY_RATING);
						
			result.setTitle(c.getString(iName));
			result.setTime(c.getString(iAge));
			result.setDetails(c.getString(iGender));
			return result;
		}
		return null;
	}
}
