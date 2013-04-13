package net.grainier.appointments;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AppointmentsListActivity extends Activity {

	ListView appointmentsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointments_list);
		
		appointmentsList = (ListView) findViewById(R.id.lvAppointmentsList);
		
		SQLHandler SQLite = new SQLHandler(this);
		
		try{
		SQLite.open();
		ArrayAdapter<Appointment> listAdapter = new ArrayAdapter<Appointment>(this,android.R.layout.simple_list_item_1, SQLite.searchByDate("3333"));
		appointmentsList.setAdapter(listAdapter);
		SQLite.close();
		} catch (SQLiteException e){
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointments_list, menu);
		return true;
	}

}
