package com.vamshi.pakashala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.vamshi.pakashala.dataconnection.PgConnectDB;
import com.vamshi.pakashala.entity.ItemDetails;
import com.vamshi.pakashala.entity.Material;
import com.vamshi.pakashala.entity.MaterialUsage;
import com.vamshi.pakashala.entity.Plans;

public class GetItems {
	Connection c = null;
	PreparedStatement stmt = null;
	public ArrayList<ItemDetails> getItemsList() { 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from item order by item_id desc;"); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  ItemDetails sitem=new ItemDetails();
		    	  sitem.setItemid(rs.getString("item_id"));
		    	  sitem.setName(rs.getString("name"));
		    	  sitem.setCategory(rs.getString("category"));
		    	  sitem.setPrice(String.valueOf(rs.getFloat("price")));
		    	  sitem.setEnable(rs.getString("is_enabled"));
		    	  sitem.setKeywords(rs.getString("keywords"));
		    	  sitem.setFilepath(rs.getString("image_path"));
		    	  itlist.add(sitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itlist; 
	}
	
	public ArrayList<ItemDetails> getItemsinOrder(String str) { 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		String[] arrOfStr = null; 
		String fstr="'xna'"; 
		arrOfStr = str.split(","); 
    	for (String a : arrOfStr)
    	{     
	    	fstr+=",'"+a+"'";
    	}
    	
    	
    	Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
    	GetItems gi=new GetItems();
    	if(str!=null)
		{	arrOfStr = str.split(","); 
        	for (String a : arrOfStr)
        	{
        		
        	System.out.println(a);
        	Integer j = hm.get(gi.getSingleItem(a));
            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
        	}
        }
    	
    	
    	
    	System.out.println(fstr);
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from item where item_id in ("+fstr+");");  
	    	  System.out.println(stmt.toString());
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  ItemDetails sitem=new ItemDetails();
		    	  sitem.setItemid(rs.getString("item_id"));
		    	  sitem.setName(rs.getString("name"));
		    	  sitem.setCategory(rs.getString("category"));
		    	  sitem.setPrice(String.valueOf(rs.getFloat("price")));
		    	  sitem.setEnable(rs.getString("is_enabled"));
		    	  sitem.setKeywords(rs.getString("keywords"));
		    	  sitem.setFilepath(rs.getString("image_path"));
		    	  System.out.println(sitem.toString());
		    	  int i=hm.get(sitem);
		    	  sitem.setCount(i);
		    	  System.out.println(i);
		    	  itlist.add(sitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itlist; 
	}
	
	public ArrayList<ItemDetails> getActiveItems() { 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from item where is_enabled='enable';"); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  ItemDetails sitem=new ItemDetails();
		    	  sitem.setItemid(rs.getString("item_id"));
		    	  sitem.setName(rs.getString("name"));
		    	  sitem.setCategory(rs.getString("category"));
		    	  sitem.setPrice(String.valueOf(rs.getFloat("price")));
		    	  sitem.setEnable(rs.getString("is_enabled"));
		    	  sitem.setKeywords(rs.getString("keywords"));
		    	  sitem.setFilepath(rs.getString("image_path"));
		    	  itlist.add(sitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itlist; 
	}
	
	public ArrayList<ItemDetails> getSearchRes(String searchkey) { 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from item where lower(name) like ? or lower(keywords) like ?  ;"); 
	    	  stmt.setString(1, "%" + searchkey.toLowerCase() + "%");
	    	  stmt.setString(2, "%" + searchkey.toLowerCase() + "%");
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  ItemDetails sitem=new ItemDetails();
		    	  sitem.setItemid(rs.getString("item_id"));
		    	  sitem.setName(rs.getString("name"));
		    	  sitem.setCategory(rs.getString("category"));
		    	  sitem.setPrice(String.valueOf(rs.getFloat("price")));
		    	  sitem.setEnable(rs.getString("is_enabled"));
		    	  sitem.setKeywords(rs.getString("keywords"));
		    	  sitem.setFilepath(rs.getString("image_path"));
		    	  itlist.add(sitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itlist; 
	}
	
	public ItemDetails getSingleItem(String ItemId) { 
		ItemDetails sitem=new ItemDetails();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from item where item_id=?;"); 
	    	  stmt.setString(1, ItemId);
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  
		    	  sitem.setItemid(rs.getString("item_id"));
		    	  sitem.setName(rs.getString("name"));
		    	  sitem.setCategory(rs.getString("category"));
		    	  sitem.setPrice(String.valueOf(rs.getFloat("price")));
		    	  sitem.setEnable(rs.getString("is_enabled"));
		    	  sitem.setKeywords(rs.getString("keywords"));
		    	  sitem.setFilepath(rs.getString("image_path"));
		    	   
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sitem; 
	}
	public ArrayList<Plans> getPlansList() {
		ArrayList<Plans> plist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from plan order by plan_id desc;"); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  Plans rplans=new Plans();
		    	  rplans.setPlanid(rs.getString("plan_id"));
		    	  rplans.setName(rs.getString("plan_name"));
		    	  rplans.setPrice(rs.getFloat("price"));
		    	  rplans.setNmeals(rs.getInt("meals"));
		    	  rplans.setEnable(rs.getString("is_enabled"));
		    	  rplans.setDesc(rs.getString("p_desc"));
		    	  rplans.setValidity(rs.getInt("validity"));
		    	  plist.add(rplans);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist; 
	}
	public ArrayList<Material> getMaterialList() { 
		ArrayList<Material> itlist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from Material order by m_id desc;"); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) {
		    	  Material mitem=new Material();
		    	  mitem.setmId(rs.getString("m_id"));
		    	  mitem.setName(rs.getString("name"));
		    	  mitem.setQuantity(rs.getFloat("quantity"));
		    	  mitem.setUnits(rs.getString("units"));
		    	  mitem.setDefaultValue(rs.getFloat("default_value"));
		    	  mitem.setIsEnabled(rs.getString("is_enabled")); 
		    	  itlist.add(mitem);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itlist; 
	}
	public Material getMaterial(String mid) { 
		Material mitem=new Material();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from Material where m_id=?;"); 
	    	  stmt.setString(1, mid); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) { 
		    	  mitem.setmId(rs.getString("m_id"));
		    	  mitem.setName(rs.getString("name"));
		    	  mitem.setQuantity(rs.getFloat("quantity"));
		    	  mitem.setUnits(rs.getString("units"));
		    	  mitem.setDefaultValue(rs.getFloat("default_value"));
		    	  mitem.setIsEnabled(rs.getString("is_enabled"));  
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mitem; 
	}
	public ArrayList<MaterialUsage> getMaterialUsage(String itemId) { 
		ArrayList<MaterialUsage> mulist = new ArrayList<>();
		 
	      try{
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  stmt = c.prepareStatement("select * from material_usage where item_id=? order by m_id desc;"); 
	    	  stmt.setString(1, itemId); 
		      ResultSet rs=stmt.executeQuery();
		      while(rs.next()) { 
		    	  MaterialUsage mu=new MaterialUsage();
		    	  mu.setmId(rs.getString("m_id")); 
		    	  mu.setItemId(rs.getString("item_id"));
		    	  mu.setPortion(rs.getFloat("portion_required"));
		    	  mu.setUnits(rs.getString("units")); 
		    	  mu.setIsEnabled(rs.getString("is_enabled")); 
		    	  mulist.add(mu);
		      }
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mulist; 
	}

	public boolean updateMaterial(MaterialUsage mu) { 
	    	  PgConnectDB p=new PgConnectDB();
	    	  c=p.getConnection();
	    	  try {
	  			stmt = c.prepareStatement("update Material  set  quantity=(quantity-?) where m_id=?;"); 
	  			stmt.setFloat(1,mu.getPortion());
	  			stmt.setString(2,mu.getmId()); 
	  			stmt.executeUpdate();
	  			
	  			stmt = c.prepareStatement("select quantity from  Material  where  m_id=?;");
	  			stmt.setString(1,mu.getmId()); 
	  			ResultSet rs=stmt.executeQuery();
			      while(rs.next()) { 
			    	 Float qu=rs.getFloat("quantity");
			    	 if(qu<0)
			    		 return false;
			      }
	  			return true;
	  		} catch (SQLException e) { 
	  			e.printStackTrace();
	  			return false;
	  		}
	}
	
	public boolean insertMaterialUsage(MaterialUsage mu) { 
  	  PgConnectDB p=new PgConnectDB();
  	  c=p.getConnection();
  	  try {
			stmt = c.prepareStatement("INSERT INTO material_usage(item_id, m_id, units, is_enabled, portion_required) VALUES (?, ?, ?, ?, ?);"); 
			
			stmt.setString(1, mu.getItemId());
			stmt.setString(2, mu.getmId());
			stmt.setString(3, mu.getUnits());
			stmt.setFloat(5,mu.getPortion());
			stmt.setString(4,mu.getIsEnabled()); 
			stmt.execute();  
			return true;
		} catch (SQLException e) { 
			e.printStackTrace();
			return false;
		}
	}

	public boolean rollbackUpdateMaterial(MaterialUsage mu) {
		 PgConnectDB p=new PgConnectDB();
   	  c=p.getConnection();
   	  try {
 			stmt = c.prepareStatement("update Material  set  quantity=(quantity+?) where m_id=?;"); 
 			stmt.setFloat(1,mu.getPortion());
 			stmt.setString(2,mu.getmId()); 
 			stmt.executeUpdate();
 			return true;
 		} catch (SQLException e) { 
 			e.printStackTrace();
 			return false;
 		} 
	}
}
