package com.vamshi.pakashala.entity;

public class Item {
		private String itemId;
		private String name;
		private String category;
		private String price;
		private String isEnabled;
		private String imagePath;
		private String keywords;
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
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
		public String getIsEnabled() {
			return isEnabled;
		}
		public void setIsEnabled(String isEnabled) {
			this.isEnabled = isEnabled;
		}
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		@Override
		public String toString() {
			return "Item [itemId=" + itemId + ", name=" + name + ", category=" + category + ", price=" + price
					+ ", isEnabled=" + isEnabled + ", imagePath=" + imagePath + ", keywords=" + keywords + "]";
		}
		
}
