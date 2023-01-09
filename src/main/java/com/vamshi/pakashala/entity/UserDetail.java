package com.vamshi.pakashala.entity;

public class UserDetail {
	private String fname;
	private String lname;
	private String pnum; 
	private String gender;
	private String uname;
	private String password;
	private String currentSubs;
	public String getCurrentSubs() {
		return currentSubs;
	}
	public void setCurrentSubs(String currentSubs) {
		this.currentSubs = currentSubs;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDetail [fname=" + fname + ", lname=" + lname + ", pnum=" + pnum + ", gender=" + gender + ", uname="
				+ uname + ", password=" + password + "]";
	}

}
