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
		calendar = (CalendarPickerView) findViewById(R.id.cpvCalendar);
		
		initCalendar(calendar);
	}

	public void initCalendar(CalendarPickerView cpv) {
		Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);		
		Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.MONTH, -1);
		cpv.init(new Date(), lastYear.getTime(), nextYear.getTime());
	}

	public void onClick(View v) {
		long date = calendar.getSelectedDate().getTime();
		
		switch (v.getId()) {
		case R.id.btnMenuCreate:
			Intent createActivity = new Intent(this, AppointmentActivity.class);
			createActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			createActivity.putExtra(AppointmentActivity.SELECTED_TYPE, 1);
			startActivity(createActivity);
			break;
		case R.id.btnMenuView:
			Intent viewActivity = new Intent(this, AppointmentsListActivity.class);
			viewActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			viewActivity.putExtra(AppointmentActivity.SELECTED_TYPE, 2);
			startActivity(viewActivity);
			break;
		case R.id.btnMenuMove:
			Intent moveActivity = new Intent(this, AppointmentsListActivity.class);
			moveActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			moveActivity.putExtra(AppointmentActivity.SELECTED_TYPE, 3);
			startActivity(moveActivity);
			break;
		case R.id.btnMenuDelete:
			Intent deleteActivity = new Intent(this, AppointmentsListActivity.class);
			deleteActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			startActivity(deleteActivity);
			break;
		case R.id.btnMenuSearch:
			Intent searchActivity = new Intent(this, SearchActivity.class);
			searchActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			startActivity(searchActivity);
			break;
		case R.id.btnMenuTranstale:
			Intent translateActivity = new Intent(this, AppointmentsListActivity.class);
			translateActivity.putExtra(AppointmentActivity.SELECTED_DATE, date);
			startActivity(translateActivity);
			break;
		default:
			break;
		}
	}

}
