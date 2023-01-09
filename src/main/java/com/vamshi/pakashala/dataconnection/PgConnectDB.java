package com.vamshi.pakashala.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager; 

public class PgConnectDB {
	 public Connection getConnection() {
	      Connection c = null; 
	      String urls="jdbc:postgresql://localhost:5432/pakashala";
			String uname="postgres";
			String psswd="vamshi";
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection(urls,
	            uname, psswd);
	         
	         System.out.println("Opened database successfully");

	        
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      } 
	      return c;
	   } 
}
