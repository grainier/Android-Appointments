package net.grainier.appointments;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {

	private EditText title, time, details;
	private Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_appointment);

		title = (EditText) findViewById(R.id.etCreateTitle);
		time = (EditText) findViewById(R.id.etCreateTime);
		details = (EditText) findViewById(R.id.etCreateDetails);
		save = (Button) findViewById(R.id.btnCreateSave);
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnCreateSave:
			try {
				Appointment a = new Appointment();
				
				a.setTitle(title.getText().toString());
				a.setTime(Long.valueOf(time.getText().toString()));
				a.setDetails(details.getText().toString());
					
				/* DB Stuff */
				SQLHandler addToDB = new SQLHandler(CreateActivity.this);
				
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
			
			default: break;
		}
	}
}
