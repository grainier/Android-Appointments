package net.grainier.appointments.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDateFormat {

	public static final String DATE_FORMAT = "yyyyMMddHHmm";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			DATE_FORMAT);

	private CustomDateFormat() {
	} // make this un-initiable

	public static long dateAsLong(Calendar cal) {
		return Long.parseLong(dateFormat.format(cal.getTime()));
	}

	public static Calendar longAsCalendar(long l) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(String.valueOf(l)));
			return c;
		} catch (ParseException e) {
			return null;
		}
	}

	public static long cuttentTimeAsLong() {
		return dateAsLong(Calendar.getInstance());
	}

	public static long millisecToReadableLong(long millisec) {
		Date date = new Date(millisec);
		return Long.valueOf(dateFormat.format(date));
	}
}
