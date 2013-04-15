package net.grainier.appointments;

import net.grainier.appointments.util.AppointmentListAdapter;
import net.grainier.appointments.util.SQLHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity {

	private ListView resultAppointments;
	private EditText searchKey;
	private Context ctx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ctx = this;
		resultAppointments = (ListView) findViewById(android.R.id.list);
		searchKey = (EditText) findViewById(R.id.etSearchKey);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSearchOk:
			SQLHandler SQLite = new SQLHandler(this);
			try {
				SQLite.open();
				resultAppointments.setAdapter(new AppointmentListAdapter(ctx,
						R.layout.appointment_row, SQLite.searchByKeyWord(searchKey.getText().toString())));
				SQLite.close();
			} catch (SQLiteException e) {

			}
			break;

		default:
			break;
		}

	}

}
