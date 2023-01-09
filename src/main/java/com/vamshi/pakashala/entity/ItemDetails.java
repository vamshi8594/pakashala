package com.vamshi.pakashala.entity;

import java.io.File;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class ItemDetails {
	private MultipartFile fi; 
	private String name;
	private String category;
	private String price;
	private String keywords;
	private String enable;
	private String filepath;
	private String itemid;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public MultipartFile getFi() {
		return fi;
	}
	public void setFi(MultipartFile fi) {
		this.fi = fi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	} 
	@Override
	public String toString() {
		return "ItemDetails [fi=" + fi + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", keywords=" + keywords + ", enable=" + enable + ", filepath=" + filepath + ", itemid=" + itemid
				+ "]";
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDetails other = (ItemDetails) obj;
		return Objects.equals(itemid, other.itemid);
	} 
	
}
