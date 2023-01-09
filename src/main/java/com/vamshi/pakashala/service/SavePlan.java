package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.ItemDetails;
import com.vamshi.pakashala.entity.Material;
import com.vamshi.pakashala.entity.Plans;

public class SavePlan {
	Connection c = null;
	PreparedStatement stmt = null;
	public long getmaxPlanId() {
		
		long max=0;
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select max(cast(plan_id as integer))  mai from plan;");
	    	    
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next())
		    	  max=rs.getInt("mai");
		} catch (Exception e) {
			e.printStackTrace();
		}
	     if(max==0)
	    	 max=2020202020;
		return max; 
	}
	
	public long getmaxMaterialId() {
		
		long max=0;
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select max(m_id)  mai from material;");
	    	    
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next())
		    	  max=rs.getLong("mai");
		} catch (Exception e) {
			e.printStackTrace();
		}
	     if(max==0)
	    	 max=7777777777l;
		return max; 
	}

	public boolean savePlanDetails(long itid, Plans pdt) {
		 PgConnectDB p=new PgConnectDB();
   	  c=p.getConnection();
   	 try {
		stmt = c.prepareStatement("insert into plan values(?,?,?,?,?,?,?);");
		stmt.setString(1, String.valueOf(itid));
		stmt.setString(2,pdt.getName());
		stmt.setInt(3,pdt.getNmeals());
		stmt.setFloat(4,pdt.getPrice());
		stmt.setString(5,pdt.getDesc()); 
		stmt.setInt(6,pdt.getValidity()); 
		stmt.setString(7,pdt.getEnable());  
		stmt.execute(); 
	} catch (SQLException e) { 
		e.printStackTrace();
		return false;
	}
		return true;
	}
	
	public boolean saveMaterials(long mid, Material mi) {
		 PgConnectDB p=new PgConnectDB();
  	  c=p.getConnection();
  	 try {
		stmt = c.prepareStatement("insert into material(m_id, name, quantity, units, default_value, is_enabled) values(?,?,?,?,?,?);");
		stmt.setString(1, String.valueOf(mid));
		stmt.setString(2,mi.getName());
		stmt.setFloat(3,mi.getQuantity());
		stmt.setString(4,mi.getUnits());
		stmt.setFloat(5,mi.getDefaultValue()); 
		stmt.setString(6,mi.getIsEnabled());  
		stmt.execute(); 
	} catch (SQLException e) { 
		e.printStackTrace();
		return false;
	}
		return true;
	}
	
	public boolean updateMaterials(long mid, Material mi) {
		 PgConnectDB p=new PgConnectDB();
 	  c=p.getConnection();
 	 try {
 		 
		stmt = c.prepareStatement("UPDATE material SET name=?, quantity=?, units=?, default_value=?, is_enabled=? where m_id=?;");
		stmt.setString(6, String.valueOf(mid));
		stmt.setString(1,mi.getName());
		stmt.setFloat(2,mi.getQuantity());
		stmt.setString(3,mi.getUnits());
		stmt.setFloat(4,mi.getDefaultValue()); 
		stmt.setString(5,mi.getIsEnabled());  
		stmt.execute(); 
	} catch (SQLException e) { 
		e.printStackTrace();
		return false;
	}
		return true;
	}

	public boolean updatePlanDetails(long itid, Plans pdt) {
		 PgConnectDB p=new PgConnectDB();
	   	  c=p.getConnection();
	   	 try {
			stmt = c.prepareStatement("update plan set  plan_name=?, meals=?, price=?, p_desc=?, validity=?, is_enabled=? where plan_id=?;");
			stmt.setString(7, String.valueOf(itid));
			stmt.setString(1,pdt.getName());
			stmt.setInt(2,pdt.getNmeals());
			stmt.setFloat(3,pdt.getPrice());
			stmt.setString(4,pdt.getDesc()); 
			stmt.setInt(5,pdt.getValidity()); 
			stmt.setString(6,pdt.getEnable());  
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
