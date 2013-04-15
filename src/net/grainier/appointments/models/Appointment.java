package net.grainier.appointments.models;

import java.util.Calendar;

import net.grainier.appointments.util.CustomDateFormat;

public class Appointment {
	
	private int id;
	private String title;
	private long time;	
	private String details;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getTime() {
		return time;
	}
	
	public Calendar getCalendarTime() {
		return CustomDateFormat.longAsCalendar(time);
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}	
}
