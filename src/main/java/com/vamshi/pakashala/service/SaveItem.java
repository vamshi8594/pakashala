package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.ItemDetails;

public class SaveItem {
	Connection c = null;
	PreparedStatement stmt = null;
	public long getmaxItemId() {
		
		long max=0;
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select max(cast(item_id as integer))  mai from item;");
	    	    
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next())
		    	  max=rs.getInt("mai");
		} catch (Exception e) {
			e.printStackTrace();
		}
	     if(max==0)
	    	 max=1010101010;
		return max; 
	}

	public boolean saveItemDetails(String imgdt, long itid, ItemDetails itd) {
		 PgConnectDB p=new PgConnectDB();
   	  c=p.getConnection();
   	 try {
		stmt = c.prepareStatement("insert into item values(?,?,?,?,?,?,?);");
		stmt.setString(1, String.valueOf(itid));
		stmt.setString(2,itd.getName());
		stmt.setString(3,itd.getCategory());
		stmt.setFloat(4,Float.parseFloat(itd.getPrice()));
		stmt.setString(5,itd.getEnable());
		stmt.setString(6,imgdt);
		stmt.setString(7,itd.getKeywords());
		stmt.execute(); 
	} catch (SQLException e) { 
		e.printStackTrace();
		return false;
	}
		return true;
	}

	public boolean updateItemDetails(String imgdt, long itid, ItemDetails itd) {
		 PgConnectDB p=new PgConnectDB();
	   	  c=p.getConnection();
	   	 try {
			stmt = c.prepareStatement("update item  set  name=?, category=?, price=?, is_enabled=?, image_path=?, keywords=? where item_id=?;");
			stmt.setString(7, String.valueOf(itid));
			stmt.setString(1,itd.getName());
			stmt.setString(2,itd.getCategory());
			stmt.setFloat(3,Float.parseFloat(itd.getPrice()));
			stmt.setString(4,itd.getEnable());
			stmt.setString(5,imgdt);
			stmt.setString(6,itd.getKeywords());
			System.out.println("inside update details");
			System.out.println(stmt.toString());
			stmt.executeUpdate();  
		} catch (SQLException e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
