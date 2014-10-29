package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartPlantBean {
	
	String partNum;
	int leadTime;
	boolean processMRP;
	int daysOfSupply;
	
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	public boolean isProcessMRP() {
		return processMRP;
	}
	public void setProcessMRP(boolean processMRP) {
		this.processMRP = processMRP;
	}
	public int getDaysOfSupply() {
		return daysOfSupply;
	}
	public void setDaysOfSupply(int daysOfSupply) {
		this.daysOfSupply = daysOfSupply;
	}
	
}