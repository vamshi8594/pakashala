package com.vamshi.pakashala.entity;

public class Material {
	private String mId;
	private String name;
	private Float quantity;
	private String units;
	private Float defaultValue;
	private String isEnabled;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public Float getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Float defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	} 
}
