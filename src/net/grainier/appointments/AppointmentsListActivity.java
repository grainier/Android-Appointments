package net.grainier.appointments;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AppointmentsListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointments_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointments_list, menu);
		return true;
	}

}
