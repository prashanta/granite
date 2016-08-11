package com.gemt.granite.bean.erp;

import java.sql.Date;

public class MRPRunInfo {
	Date mrpLastRunDate;
	int mrpLastRunTime;
	
	public Date getMrpLastRunDate() {
		return mrpLastRunDate;
	}
	public void setMrpLastRunDate(Date mrpLastRunDate) {
		this.mrpLastRunDate = mrpLastRunDate;
	}
	public int getMrpLastRunTime() {
		return mrpLastRunTime;
	}
	public void setMrpLastRunTime(int mrpLastRunTime) {
		this.mrpLastRunTime = mrpLastRunTime;
	}	
}
