package net.grainier.appointments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	public static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    
    private Date(){} // make this un-initiable
    
    public static long dateAsLong(Calendar cal){
    	return Long.parseLong(dateFormat.format(cal.getTime()));
    }
    
    public static Calendar longAsCalendar(long l){
    	try {
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(String.valueOf(l)));
			return c;			
		} catch (ParseException e) {
			return null;
		}
    }
    
    public static long cuttentTimeAsLong(){
    	return dateAsLong(Calendar.getInstance());
    }
}
