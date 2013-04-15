package net.grainier.appointments.util;

import java.util.ArrayList;

import net.grainier.appointments.models.Appointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHandler {
	// Initialize static variables
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "_name";
	public static final String KEY_TIME = "_time";
	public static final String KEY_DETAILS = "_details";
	private static final String DATABASE_NAME = "appointmentsDB";
	private static final String DATABASE_TABLE = "appointmentsTable";
	private static final int DATABASE_VERSION = 1; // DB version
	private DbHelper dbHelper;
	private final Context context;
	private SQLiteDatabase sqLiteDatabase;

	private class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// This method will only use when the first time we create database
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
					+ " TEXT NOT NULL, " + KEY_TIME + " INTEGER, "
					+ KEY_DETAILS + " TEXT NOT NULL);");
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

	public SQLHandler open() throws SQLiteException {
		dbHelper = new DbHelper(context);
		sqLiteDatabase = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long addAppointment(Appointment a) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, a.getTitle());
		values.put(KEY_TIME, a.getTime());
		values.put(KEY_DETAILS, a.getDetails());
		return sqLiteDatabase.insert(DATABASE_TABLE, null, values);
	}

	public ArrayList<Appointment> searchByTitle(String title) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		Appointment result = new Appointment();
		String[] columns = new String[] { KEY_ROWID, KEY_TITLE, KEY_TIME,
				KEY_DETAILS }; // column list
		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, KEY_TITLE + " LIKE ?", new String[]{title+"%"}, null, null, null); // query

		if (c != null) {
			int index_id = c.getColumnIndex(KEY_ROWID);
			int index_title = c.getColumnIndex(KEY_TITLE);
			int index_time = c.getColumnIndex(KEY_TIME);
			int index_details = c.getColumnIndex(KEY_DETAILS);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result.setId(c.getInt(index_id));
				result.setTitle(c.getString(index_title));
				result.setTime(c.getLong(index_time));
				result.setDetails(c.getString(index_details));
				appointments.add(result);
			}
			return appointments;
		} else
			return null;
	}

	public ArrayList<Appointment> searchByDate(String date) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		String[] columns = new String[] { KEY_ROWID, KEY_TITLE, KEY_TIME,
				KEY_DETAILS }; // column list
		Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null); // query

		if (c != null) {
			int index_id = c.getColumnIndex(KEY_ROWID);
			int index_title = c.getColumnIndex(KEY_TITLE);
			int index_time = c.getColumnIndex(KEY_TIME);
			int index_details = c.getColumnIndex(KEY_DETAILS);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				Appointment result = new Appointment();
				result.setId(c.getInt(index_id));
				result.setTitle(c.getString(index_title));
				result.setTime(c.getLong(index_time));
				result.setDetails(c.getString(index_details));
				appointments.add(result);
			}
			return appointments;
		} else
			return null;
	}
}
