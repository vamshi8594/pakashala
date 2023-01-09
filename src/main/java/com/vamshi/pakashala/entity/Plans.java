package com.vamshi.pakashala.entity;
 

public class Plans { 
	private String planid;
	private String name;
	private int nmeals;
	private Float price;
	private String desc;
	private int validity;
	private String enable;
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNmeals() {
		return nmeals;
	}
	public void setNmeals(int nmeals) {
		this.nmeals = nmeals;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "plans [planid=" + planid + ", name=" + name + ", nmeals=" + nmeals + ", price=" + price + ", desc="
				+ desc + ", validity=" + validity + ", enable=" + enable + "]";
	} 
}
