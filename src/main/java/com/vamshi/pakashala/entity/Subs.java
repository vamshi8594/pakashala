package com.vamshi.pakashala.entity;

import java.util.Date;

public class Subs {
		private String subId;
		private String planId;
		private Date sDate;
		private Date eDate;
		private int nRemain;
		public String getSubId() {
			return subId;
		}
		public void setSubId(String subId) {
			this.subId = subId;
		}
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public Date getsDate() {
			return sDate;
		}
		public void setsDate(Date sDate) {
			this.sDate = sDate;
		}
		public Date geteDate() {
			return eDate;
		}
		public void seteDate(Date eDate) {
			this.eDate = eDate;
		}
		public int getnRemain() {
			return nRemain;
		}
		public void setnRemain(int nRemain) {
			this.nRemain = nRemain;
		}
		
}
