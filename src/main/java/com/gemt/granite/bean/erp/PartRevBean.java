package com.gemt.granite.bean.erp;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gemt.granite.utility.JsonDateSerializer;

@XmlRootElement
public class PartRevBean {
	String revisionNum;
	boolean approved;
	Date approvedDate;
	String approvedBy;
	Date effectiveDate;
	
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}		
}
