package net.grainier.appointments;

public class Appointment {
	
	private int id;
	private String title;
	private long time;	
	private String details;
	
	@Override
	public String toString() {
		return "Title : " + title + "\nTime : " + String.valueOf(time);
	}
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
