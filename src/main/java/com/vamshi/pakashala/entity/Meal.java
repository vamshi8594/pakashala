package com.vamshi.pakashala.entity;

import java.util.Date;

public class Meal {
	private String mealId;
	private String username;
	private Date mDate;
	private String tod;
	private int mcount;
	private String address;
	private String notes;
	public String getMealId() {
		return mealId;
	}
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public String getTod() {
		return tod;
	}
	public void setTod(String tod) {
		this.tod = tod;
	}
	public int getMcount() {
		return mcount;
	}
	public void setMcount(int mcount) {
		this.mcount = mcount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
