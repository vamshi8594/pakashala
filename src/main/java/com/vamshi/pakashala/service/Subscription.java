package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.Meal;
import com.vamshi.pakashala.entity.Plans;
import com.vamshi.pakashala.entity.Subs;

public class Subscription {
	Connection c = null;
	PreparedStatement stmt = null;
	public boolean createSubscription(String userID,String planid, long subId) {  
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	    GetPlans gp=new GetPlans();
		      Plans pl;
		      pl=gp.getPlan(planid);
	    	  Statement s = c.createStatement();
	    	  Calendar cal = Calendar.getInstance();
	    	  	Date currentTime=cal.getTime(); 
	        	cal.add(Calendar.DAY_OF_MONTH, pl.getValidity()); 
	        	Date endTime=cal.getTime();
	        	// Step 4: Create a statement / create table
	            String sql1
	                = "insert into subscriptions values('"+subId+"','"+planid+"','"+currentTime+"','"+endTime+"',"+pl.getNmeals()+")";
	 
	            // Step 5: Process a query
	            // Insert records in the table
	            String sql2
	                = "update users set prev_subs=current_subs where username='"+userID+"'";
	            String sql3
	                = "update users set current_subs='"+subId+"' where username='"+userID+"'"; 
	            s.addBatch(sql1);
	            s.addBatch(sql2);
	            s.addBatch(sql3);  
	            s.executeBatch(); 
	            s.close();
		      return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public String getMaxSubId() { 
		String subId="7777777778";
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select max(sub_id) as sub_id from subscriptions;");  
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next())
	    		  	subId=rs.getString("sub_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subId; 
	}
	
	public String getMaxMealId() { 
		String mId="66666666669";
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select max(meal_id) as meal_id from meals;");  
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next())
	    		  	mId=rs.getString("meal_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mId; 
	}
	
	public Subs getUserSubscription(String uname) { 
		Subs sb=new Subs();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select s.* from subscriptions s, users u where u.current_subs=s.sub_id and u.username=?;");  
	    	  stmt.setString(1, uname);
	    	  ResultSet rs=stmt.executeQuery(); 
	    	  while(rs.next()) {
	    		  	sb.setSubId(rs.getString("sub_id"));
	    	  		sb.setPlanId(rs.getString("plan_id"));
	    	  		sb.setsDate(rs.getDate("start_date"));
	    	  		sb.seteDate(rs.getDate("end_date"));
	    	  		sb.setnRemain(rs.getInt("meals_remaining"));
	    	  		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb; 
	} 

	public boolean addMeal(String uname, long mid, Meal ml) {
		try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  
	    	  boolean b=this.updateAddMeals(uname, ml.getMcount());
	    	  if(b)
	    	  {
	    		  stmt = c.prepareStatement("insert into meals values(?,?,?,?,?,?,?);");  
	    		  stmt.setString(1, String.valueOf(mid));
		    	  stmt.setString(2, uname);
		    	  stmt.setDate(3, (java.sql.Date) ml.getmDate());
		    	  stmt.setString(4, ml.getTod());
		    	  stmt.setInt(5, ml.getMcount());
		    	  stmt.setString(6, ml.getAddress());
		    	  stmt.setString(7, ml.getNotes());
		    	  stmt.execute(); 
		    	  return true;
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateAddMeals(String uname, int mc) {
		try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  Subs sb; 
	    	  sb=this.getUserSubscription(uname);
	    	  stmt = c.prepareStatement("update subscriptions set meals_remaining=? where sub_id=?;"); 
	    	  int ac=sb.getnRemain()-mc;
	    	   if(ac<0)
	    		   return false;
	    	   else {
	    	  stmt.setInt(1, ac);
	    	  stmt.setString(2, sb.getSubId()); 
	    	  stmt.execute(); 
	    	  stmt.close();
	    	  return true;
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
