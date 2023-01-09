package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.Meal;
import com.vamshi.pakashala.entity.Orders; 

public class GetOrders {
	Connection c = null;
	PreparedStatement stmt = null;
	public ArrayList<Orders> getOrderHistory(String uname) { 
		ArrayList<Orders> olist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from orders where username=?;"); 
	    	  stmt.setString(1, uname);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  Orders oi=new Orders();
	    		  oi.setOrder_id(rs.getString("order_id"));
	    		  oi.setItem_id(rs.getString("item_id"));
	    		  oi.setIdl();
	    		  oi.setDate(rs.getDate("date"));
	    		  oi.setLocation(rs.getString("location"));
	    		  oi.setNotes(rs.getString("notes"));
	    		  oi.setPaidStatus(rs.getString("paid_status"));
	    		  oi.setPrice(rs.getString("price"));
	    		  oi.setUsername(rs.getString("username"));
	    		  oi.setTotalPrice(rs.getString("total_price"));
	    		  oi.setOrderType(rs.getString("order_type"));
	    		  olist.add(oi);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist; 
	}
	
	public Orders getSingleOrder(String oid) { 
		Orders oi=new Orders();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from orders where order_id=?;"); 
	    	  stmt.setString(1, oid);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  
	    		  oi.setOrder_id(rs.getString("order_id"));
	    		  oi.setItem_id(rs.getString("item_id"));
	    		  oi.setIdl();
	    		  oi.setDate(rs.getDate("date"));
	    		  oi.setLocation(rs.getString("location"));
	    		  oi.setNotes(rs.getString("notes"));
	    		  oi.setPaidStatus(rs.getString("paid_status"));
	    		  oi.setPrice(rs.getString("price"));
	    		  oi.setUsername(rs.getString("username"));
	    		  oi.setTotalPrice(rs.getString("total_price"));
	    		  oi.setOrderType(rs.getString("order_type")); 
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oi; 
	}
	
	public void addOrder(Orders or) {  
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("INSERT INTO orders(order_id, item_id, date, notes, location, price, paid_status, username, order_type, total_price) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"); 
	    	  stmt.setString(1, or.getOrder_id());
	    	  stmt.setString(2, or.getItem_id());
	    	  stmt.setDate(3,   or.getDate());
	    	  stmt.setString(4, or.getNotes());
	    	  stmt.setString(5, or.getLocation());
	    	  stmt.setFloat(6,Float.parseFloat(or.getPrice()));
	    	  stmt.setString(7, or.getPaidStatus());
	    	  stmt.setString(8, or.getUsername());
	    	  stmt.setString(9, or.getOrderType());
	    	  stmt.setDouble(10, Double.parseDouble(or.getTotalPrice()));
	    	  stmt.execute();  
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void confirmOrder(String oid) {  
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("update orders set paid_status='confirmed' where order_id= ?;"); 
	    	  stmt.setString(1, oid); 
	    	  stmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public long getmaxOrderId() {
			
			long max=0;
		      try{
		    	  PgConnectDB p=new PgConnectDB();
		    	  c=p.getConnection();
		    	  stmt = c.prepareStatement("select max(order_id)  mai from orders;");
		    	    
			      ResultSet rs=stmt.executeQuery();
			      while(rs.next())
			    	  max=rs.getLong("mai");
			} catch (Exception e) {
				e.printStackTrace();
			}
		     if(max==0)
		    	 max= 6060606060L;
			return max; 
		}

	public ArrayList<Meal> getMealist(String uname) { 
		ArrayList<Meal> mlist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from meals where username=? and mdate>current_date;"); 
	    	  stmt.setString(1, uname);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  Meal mi=new Meal();
	    		  mi.setMealId(rs.getString("meal_id"));
	    		  mi.setUsername(rs.getString("username"));
	    		  mi.setMcount(rs.getInt("mealcount"));
	    		  mi.setTod(rs.getString("tod"));
	    		  mi.setAddress(rs.getString("address"));
	    		  mi.setNotes(rs.getString("notes"));
	    		  mi.setmDate(rs.getDate("mdate"));
	    		  mlist.add(mi);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mlist; 
	}

	public ArrayList<Orders> pendingOrders() {
		ArrayList<Orders> olist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from orders where paid_status='pending'  order by date desc;");  
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  Orders oi=new Orders();
	    		  oi.setOrder_id(rs.getString("order_id"));
	    		  oi.setItem_id(rs.getString("item_id"));
	    		  oi.setIdl();
	    		  oi.setDate(rs.getDate("date"));
	    		  oi.setLocation(rs.getString("location"));
	    		  oi.setNotes(rs.getString("notes"));
	    		  oi.setPaidStatus(rs.getString("paid_status"));
	    		  oi.setPrice(rs.getString("price"));
	    		  oi.setTotalPrice(rs.getString("total_price"));
	    		  oi.setUsername(rs.getString("username"));
	    		  oi.setOrderType(rs.getString("order_type"));
	    		  olist.add(oi);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist; 
	}
	public ArrayList<Orders> pendingOrders(String uname) {
		ArrayList<Orders> olist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from orders where username=? and paid_status='pending' order by date desc;"); 
	    	  stmt.setString(1, uname);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  Orders oi=new Orders();
	    		  oi.setOrder_id(rs.getString("order_id"));
	    		  oi.setItem_id(rs.getString("item_id"));
	    		  oi.setIdl();
	    		  oi.setDate(rs.getDate("date"));
	    		  oi.setLocation(rs.getString("location"));
	    		  oi.setNotes(rs.getString("notes"));
	    		  oi.setPaidStatus(rs.getString("paid_status"));
	    		  oi.setPrice(rs.getString("price"));
	    		  oi.setTotalPrice(rs.getString("total_price"));
	    		  oi.setUsername(rs.getString("username"));
	    		  oi.setOrderType(rs.getString("order_type"));
	    		  olist.add(oi);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist; 
	}
	public ArrayList<Orders> nonPendingOrders(String uname) {
		ArrayList<Orders> olist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from orders where username=? and paid_status<>'pending'  order by date desc;"); 
	    	  stmt.setString(1, uname);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  Orders oi=new Orders();
	    		  oi.setOrder_id(rs.getString("order_id"));
	    		  oi.setItem_id(rs.getString("item_id"));
	    		  oi.setIdl();
	    		  oi.setDate(rs.getDate("date"));
	    		  oi.setLocation(rs.getString("location"));
	    		  oi.setNotes(rs.getString("notes"));
	    		  oi.setPaidStatus(rs.getString("paid_status"));
	    		  oi.setPrice(rs.getString("price"));
	    		  oi.setTotalPrice(rs.getString("total_price"));
	    		  oi.setUsername(rs.getString("username"));
	    		  oi.setOrderType(rs.getString("order_type"));
	    		  olist.add(oi);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist; 
	}
	public void cancelOrder(String orderid) { 
		try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("update orders set paid_status='cancelled' where order_id= ?;"); 
	    	  stmt.setString(1, orderid); 
	    	  stmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

}
