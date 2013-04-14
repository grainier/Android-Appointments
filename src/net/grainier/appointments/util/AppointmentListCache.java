package net.grainier.appointments.util;

import net.grainier.appointments.R;
import net.grainier.appointments.R.id;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AppointmentListCache {

	private View baseView;
	private TextView textTitle;
	private TextView textTime;
	private ImageView imageIcon;

	public AppointmentListCache(View baseView) {
		this.baseView = baseView;
	}

	public View getViewBase() {
		return baseView;
	}

	public TextView getTextTitle(int resource) {
		if (textTitle == null) {
			textTitle = (TextView) baseView.findViewById(R.id.tvItemTitle);
		}
		return textTitle;
	}

	public TextView getTextTime(int resource) {
		if (textTime == null) {
			textTime = (TextView) baseView.findViewById(R.id.tvItemTime);
		}
		return textTime;
	}

	public ImageView getImageView(int resource) {
		if (imageIcon == null) {
			imageIcon = (ImageView) baseView.findViewById(R.id.ivAppointmentIcon);
		}
		return imageIcon;
	}
}