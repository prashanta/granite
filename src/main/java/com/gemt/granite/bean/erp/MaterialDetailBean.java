package com.gemt.granite.bean.erp;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gemt.granite.utility.JsonDateSerializer;

@XmlRootElement
public class MaterialDetailBean{
	
	String partNum;
	int mtlSeq;
	int qtyPer;
	String mtlPartNum;
	String bubbleNum;
	boolean pullAsAsm;
	boolean viewAsAsm;
	
	String partDescription;
	String partClass;
	String invUM;
	String partType;
	boolean nonStock;
	String manufacturer;
	String manufacturerNumber;
	String project;
	float estimatedCost;
	String mfgComment;
	String purComment;
		
	int leadTime;
	boolean processMRP;
	int daysOfSupply;
	boolean genPOSuggestion;
	
	String revisionNum;
	Date revisionEffectiveDate;
	
	String approvedVendor;
	
	String primaryBin;
	
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
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
	public String getBubbleNum() {
		return bubbleNum;
	}
	public void setBubbleNum(String bubbleNum) {
		this.bubbleNum = bubbleNum;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getManufacturerNumber() {
		return manufacturerNumber;
	}
	public void setManufacturerNumber(String manufacturerNumber) {
		this.manufacturerNumber = manufacturerNumber;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public float getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(float estimatedCost) {
		this.estimatedCost = estimatedCost;
	}	
	public String getMfgComment() {
		return mfgComment;
	}
	public void setMfgComment(String mfgComment) {
		this.mfgComment = mfgComment;
	}
	public String getPurComment() {
		return purComment;
	}
	public void setPurComment(String purComment) {
		this.purComment = purComment;
	}
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}	
	public boolean isGenPOSuggestion() {
		return genPOSuggestion;
	}
	public void setGenPOSuggestion(boolean genPOSuggestion) {
		this.genPOSuggestion = genPOSuggestion;
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
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getRevisionEffectiveDate() {
		return revisionEffectiveDate;
	}
	public void setRevisionEffectiveDate(Date revisionEffectiveDate) {
		this.revisionEffectiveDate = revisionEffectiveDate;
	}
	public String getApprovedVendor() {
		return approvedVendor;
	}
	public void setApprovedVendor(String approvedVendor) {
		this.approvedVendor = approvedVendor;
	}
	public String getPrimaryBin() {
		return primaryBin;
	}
	public void setPrimaryBin(String primaryBin) {
		this.primaryBin = primaryBin;
	}	
}
