package com.vamshi.pakashala.entity;

import java.sql.Date;
import java.util.ArrayList;

import com.vamshi.pakashala.service.GetItems;

public class Orders {
	private String order_id;
	private String item_id; 
	private Date date;
	private String notes;
	private String location;
	private String price; 
	private String paidStatus;
	private String username;
	private String orderType;
	private String totalPrice;
	private ArrayList<ItemDetails> idl;
	public ArrayList<ItemDetails> getIdl() {
		
		return idl;
	}
	public void setIdl() { 
		ArrayList<ItemDetails> litem = new ArrayList<>();
		GetItems gi=new GetItems();
		litem=gi.getItemsinOrder(item_id); 
		
		
		this.idl = litem;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	} 
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getItem_id() {
		return item_id;
	} 
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", item_id=" + item_id + ", date=" + date + ", notes=" + notes
				+ ", location=" + location + ", price=" + price + ", paidStatus=" + paidStatus + ", username="
				+ username + ", orderType=" + orderType + ", totalPrice=" + totalPrice + ", idl=" + idl + "]";
	}
	

}
