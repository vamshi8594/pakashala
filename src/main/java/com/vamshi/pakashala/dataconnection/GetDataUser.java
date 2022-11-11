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
			} 
		     
			return l;
		}
}
