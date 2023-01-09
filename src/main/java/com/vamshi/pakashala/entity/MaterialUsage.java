package com.vamshi.pakashala.entity;

public class MaterialUsage {
	private String itemId;
	private String mId; 
	private Float portion; 
	private String units; 
	private String isEnabled;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Float getPortion() {
		return portion;
	}
	public void setPortion(Float portion) {
		this.portion = portion;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	} 
}
