package net.grainier.appointments;

import java.util.Calendar;
import java.util.Date;

import com.squareup.timessquare.CalendarPickerView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends Activity {

	ImageButton create, view, delete, move, search, translate;
	CalendarPickerView calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		create = (ImageButton) findViewById(R.id.btnMenuCreate);
		view = (ImageButton) findViewById(R.id.btnMenuView);
		
		Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);

		calendar = (CalendarPickerView) findViewById(R.id.cpvCalendar);
		calendar.init(new Date(), new Date(), nextYear.getTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public void onClick(View v) {
		long date = calendar.getSelectedDate().getTime();
		
		switch (v.getId()) {
		case R.id.btnMenuCreate:
			Intent createActivity = new Intent(this, CreateActivity.class);
			createActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(createActivity);
			break;
		case R.id.btnMenuView:
			Intent viewActivity = new Intent(this, AppointmentsListActivity.class);
			viewActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(viewActivity);
			break;
		case R.id.btnMenuDelete:
			Intent deleteActivity = new Intent(this, AppointmentsListActivity.class);
			deleteActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(deleteActivity);
			break;
		case R.id.btnMenuMove:
			Intent moveActivity = new Intent(this, AppointmentsListActivity.class);
			moveActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(moveActivity);
			break;
		case R.id.btnMenuSearch:
			Intent searchActivity = new Intent(this, AppointmentsListActivity.class);
			searchActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(searchActivity);
			break;
		case R.id.btnMenuTranstale:
			Intent translateActivity = new Intent(this, AppointmentsListActivity.class);
			translateActivity.putExtra(CustomDateFormat.SELECTED_DATE, date);
			startActivity(translateActivity);
			break;
		default:
			break;
		}
	}

}
