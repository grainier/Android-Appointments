package net.grainier.appointments;

import net.grainier.appointments.models.Appointment;
import net.grainier.appointments.util.CustomDateFormat;
import net.grainier.appointments.util.SQLHandler;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AppointmentActivity extends Activity {

	public static final String SELECTED_DATE = "SELECTED_DATE";
	public static final String SELECTED_TYPE = "SELECTED_TYPE";
	
	private long selectedDate = 1365877800000L;
	private int selectedType = 1;
	private EditText title, time, details;
	private ImageButton save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_appointment);
		
		title = (EditText) findViewById(R.id.etCreateTitle);
		time = (EditText) findViewById(R.id.etCreateTime);
		details = (EditText) findViewById(R.id.etCreateDetails);
		save = (ImageButton) findViewById(R.id.btnAppointmentSave);
		
		// get selected date from the previous activity
		selectedDate = getIntent().getLongExtra(SELECTED_DATE, selectedDate);
		selectedType = getIntent().getIntExtra(SELECTED_TYPE, selectedType);
		
		time.setText(String.valueOf(CustomDateFormat.millisecToReadableLong(selectedDate)));
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnAppointmentSave:
			try {
				Appointment a = new Appointment();
				
				a.setTitle(title.getText().toString());
				a.setTime(Long.valueOf(time.getText().toString()));
				a.setDetails(details.getText().toString());
					
				/* DB Stuff */
				SQLHandler addToDB = new SQLHandler(AppointmentActivity.this);
				
				addToDB.open();
				addToDB.addAppointment(a);
				addToDB.close();
				
				Toast.makeText(getApplicationContext(), "Appoint ment added", Toast.LENGTH_SHORT).show();
			} catch (SQLiteException e) {
				Toast.makeText(getApplicationContext(), "SQLite Error occured", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Other Error occured", Toast.LENGTH_SHORT).show();
			}
			break;
			
		case R.id.btnAppointmentCancel:
			
			break;
			
			default: break;
		}
	}
}
