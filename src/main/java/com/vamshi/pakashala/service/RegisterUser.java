package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.UserDetail;

public class RegisterUser {
	public void register(UserDetail ud) {
		Connection c = null;
		PreparedStatement stmt = null; 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("insert into users(firstname,lastname,mobile,gender,username) values(?,?,?,?,?)");
	    	  stmt.setString(1,ud.getFname());
	    	  stmt.setString(2,ud.getLname()); 
	    	  long i=Long.parseLong(ud.getPnum()); 
	    	  stmt.setLong(3,i);
	    	  stmt.setString(4,ud.getGender());
	    	  stmt.setString(5,ud.getUname()); 
		      stmt.execute(); 
		      stmt = c.prepareStatement("insert into login values(?,?)"); 
	    	  stmt.setString(1,ud.getUname());
	    	  stmt.setString(2,ud.getPassword());
		      stmt.execute(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public String getUserFirstName(String uname) {
		Connection c = null;
		PreparedStatement stmt = null;
		String fname=null;
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select firstname from users where username=?");
	    	  stmt.setString(1,uname);  
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next())
		    	  fname=rs.getString("firstname");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fname; 
	}
	public UserDetail getUser(String uname) {
		Connection c = null;
		PreparedStatement stmt = null;
		UserDetail ud=new UserDetail();
		String fname=null;
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from users where username=?");
	    	  stmt.setString(1,uname);  
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next())
		      {
		    	  ud.setUname(rs.getString("username"));
		    	  ud.setFname(rs.getString("firstname"));
		    	  ud.setCurrentSubs(rs.getString("current_subs"));
		    	  ud.setLname(rs.getString("lastname"));
		      }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ud; 
	}
}
