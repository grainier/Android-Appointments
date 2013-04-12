package net.grainier.appointments;

public class Appointment {
	
	private int id;
	private String title;
	private String date;
	private String time;	
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}	
}
