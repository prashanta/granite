package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialDetailBean {
	
	String partNum;
	String revisionNum;
	int mtlSeq;
	int qtyPer;
	String mtlPartNum;
	boolean pullAsAsm;
	boolean viewAsAsm;
	
	String partDescription;
	String partClass;
	String invUM;
	String partType;
	boolean nonStock;
	
	int leadTime;
	boolean processMRP;
	int daysOfSupply;
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public int getMtlSeq() {
		return mtlSeq;
	}
	public void setMtlSeq(int mtlSeq) {
		this.mtlSeq = mtlSeq;
	}
	public int getQtyPer() {
		return qtyPer;
	}
	public void setQtyPer(int qtyPer) {
		this.qtyPer = qtyPer;
	}
	public String getMtlPartNum() {
		return mtlPartNum;
	}
	public void setMtlPartNum(String mtlPartNum) {
		this.mtlPartNum = mtlPartNum;
	}
	public boolean isPullAsAsm() {
		return pullAsAsm;
	}
	public void setPullAsAsm(boolean pullAsAsm) {
		this.pullAsAsm = pullAsAsm;
	}
	public boolean isViewAsAsm() {
		return viewAsAsm;
	}
	public void setViewAsAsm(boolean viewAsAsm) {
		this.viewAsAsm = viewAsAsm;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public String getPartClass() {
		return partClass;
	}
	public void setPartClass(String partClass) {
		this.partClass = partClass;
	}
	public String getInvUM() {
		return invUM;
	}
	public void setInvUM(String invUM) {
		this.invUM = invUM;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public boolean isNonStock() {
		return nonStock;
	}
	public void setNonStock(boolean nonStock) {
		this.nonStock = nonStock;
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
