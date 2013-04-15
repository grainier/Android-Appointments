package net.grainier.appointments;

import net.grainier.appointments.models.Appointment;
import net.grainier.appointments.util.AppointmentListAdapter;
import net.grainier.appointments.util.SQLHandler;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AppointmentsListActivity extends ListActivity {

	private ListView appointmentsList;
	private Context ctx;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_appointments_list);		
		ctx=this;
		
		appointmentsList = (ListView) findViewById(android.R.id.list);
		
		SQLHandler SQLite = new SQLHandler(this);
		
		try{
		SQLite.open();
		appointmentsList.setAdapter(new AppointmentListAdapter(ctx, R.layout.list_appointment, SQLite.searchByDate("3333")));
		SQLite.close();
		} catch (SQLiteException e){
			
		}		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Appointment selectedAppointment = (Appointment) l.getAdapter().getItem(position);
		Toast.makeText(this, selectedAppointment.toString(), Toast.LENGTH_SHORT).show(); 
	}
}
