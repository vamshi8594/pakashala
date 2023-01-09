package com.vamshi.pakashala.dataconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vamshi.pakashala.entity.Login;
 

public class GetDataUser {
		public Login getLoginData(String uname, String password) {
			Login l=new Login();
			Connection c = null;
			PreparedStatement stmt = null; 
		      try{
		    	  PgConnectDB p=new PgConnectDB();
		    	  c=p.getConnection();
		    	  stmt = c.prepareStatement("SELECT * FROM Login where username=?");
		    	  stmt.setString(1,uname);
			         ResultSet rs = stmt.executeQuery();
			         while ( rs.next() ) { 
			            l.setUname(rs.getString("username"));
			            l.setPassword(rs.getString("password")); 
			         }
			         if(l.getPassword().equals(password)) {
			        	 return l;
			         } 
			         l.setUname("failed");
			         l.setPassword("failed");
				
			} catch (Exception e) {
				e.printStackTrace();
				l.setUname("failed");
		         l.setPassword("failed");
			} 
		     
			return l;
		}
		public boolean checkUser(String uname) {
			Connection c = null;
			PreparedStatement stmt = null; 
		      try{
		    	  PgConnectDB p=new PgConnectDB();
		    	  c=p.getConnection();
		    	  stmt = c.prepareStatement("SELECT * FROM users where username=?");
		    	  stmt.setString(1,uname);
			         ResultSet rs = stmt.executeQuery();
			         while ( rs.next() ) { 
			            if(rs.getString("username").equals(uname)){
			            	return true;
			            }
			         } 
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return false; 
		}
		public Login getmLoginData(String uname, String password) {
			Login l=new Login();
			Connection c = null;
			PreparedStatement stmt = null; 
		      try{
		    	  PgConnectDB p=new PgConnectDB();
		    	  c=p.getConnection();
		    	  stmt = c.prepareStatement("SELECT * FROM managers where username=?");
		    	  stmt.setString(1,uname);
			         ResultSet rs = stmt.executeQuery();
			         while ( rs.next() ) { 
			            l.setUname(rs.getString("username"));
			            l.setPassword(rs.getString("password")); 
			         }
			         if(l.getPassword().equals(password)) {
			        	 return l;
			         } 
			         l.setUname("failed");
			         l.setPassword("failed");
				
			} catch (Exception e) { 
				e.printStackTrace();
				l.setUname("failed");
		         l.setPassword("failed");
		         return l;
			} 
		     
			return l;
		}
}
