package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vamshi.pakashala.dataconnection.PgConnectDB; 
import com.vamshi.pakashala.entity.Plans;

public class GetPlans {
	Connection c = null;
	PreparedStatement stmt = null;
	public ArrayList<Plans> getPlanList() { 
		ArrayList<Plans> plist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from plan where is_enabled='enable';"); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  Plans planitem=new Plans();
		    	  planitem.setPlanid(rs.getString("plan_id"));
		    	  planitem.setName(rs.getString("plan_name"));
		    	  planitem.setNmeals(rs.getInt("meals")); 
		    	  planitem.setPrice(rs.getFloat("price"));
		    	  planitem.setEnable(rs.getString("is_enabled"));
		    	  planitem.setDesc(rs.getString("p_desc"));
		    	  planitem.setValidity(rs.getInt("validity"));
		    	  plist.add(planitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist; 
	}
	
	public Plans getCurrentPlan(String userID) { 
		Plans planitem = new Plans();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select p.* from subscriptions s, users u,plan p where p.plan_id=s.plan_id and u.current_subs=s.sub_id and u.username=? and s.end_date>current_date;"); 
		      stmt.setString(1, userID);
	    	  ResultSet rs=stmt.executeQuery();
		      while(rs.next()) { 
		    	  planitem.setPlanid(rs.getString("plan_id"));
		    	  planitem.setName(rs.getString("plan_name"));
		    	  planitem.setNmeals(rs.getInt("meals")); 
		    	  planitem.setPrice(rs.getFloat("price"));
		    	  planitem.setEnable(rs.getString("is_enabled"));
		    	  planitem.setDesc(rs.getString("p_desc"));
		    	  planitem.setValidity(rs.getInt("validity")); 
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planitem; 
	}
	public Plans getPlan(String planID) { 
		Plans planitem = new Plans();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from plan where plan_id=?;"); 
		      stmt.setString(1, planID);
	    	  ResultSet rs=stmt.executeQuery();
		      while(rs.next()) { 
		    	  planitem.setPlanid(rs.getString("plan_id"));
		    	  planitem.setName(rs.getString("plan_name"));
		    	  planitem.setNmeals(rs.getInt("meals")); 
		    	  planitem.setPrice(rs.getFloat("price"));
		    	  planitem.setEnable(rs.getString("is_enabled"));
		    	  planitem.setDesc(rs.getString("p_desc"));
		    	  planitem.setValidity(rs.getInt("validity")); 
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planitem; 
	}

}
