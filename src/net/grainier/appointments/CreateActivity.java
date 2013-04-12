package net.grainier.appointments;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
		}
	}
}
