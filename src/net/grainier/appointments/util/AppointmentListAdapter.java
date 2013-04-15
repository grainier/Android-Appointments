package net.grainier.appointments.util;

import java.util.ArrayList;

import net.grainier.appointments.models.Appointment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
	private int resource;
	private LayoutInflater inflater;
	private Context context;

	public AppointmentListAdapter(Context ctx, int resourceId,
			ArrayList<Appointment> appointments) {
		super(ctx, resourceId, appointments);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		context = ctx;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Appointment appointment = getItem(position);
		AppointmentListCache viewCache;

		if (convertView == null) {
			convertView = (LinearLayout) inflater.inflate(resource, null);
			viewCache = new AppointmentListCache(convertView);
			convertView.setTag(viewCache);
		} else {
			convertView = (LinearLayout) convertView;
			viewCache = (AppointmentListCache) convertView.getTag();
		}

		TextView txtTitle = viewCache.getTextTitle(resource);
		txtTitle.setText(appointment.getTitle());
		
		TextView txtDetails = viewCache.getTextDetails(resource);
		txtDetails.setText(appointment.getDetails());

		TextView txtTime = viewCache.getTextTime(resource);
		txtTime.setText(appointment.getCalendarTime().getTime().toString());

		return convertView;
	}
}
