package com.gemt.granite.bean.mfg;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gemt.granite.utility.JsonDateSerializer;


@XmlRootElement
public class MTLXCUTOperBean {	
	String jobNum;
	Date startDate;
	Date dueDate;		
	int productionQty;	
	float estProdHours;
	
	int operSeq;
	String nextWorkCenter;
	
	String subAsmPartNum;
	String subAsmMtlPartNum;
	float subAsmMtlQty;
	String subAsmMtlUOM;
	
	String mtlPartNum;
	float mtlQty;
	String mtlUOM;
	
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum){
		this.jobNum = jobNum;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getProductionQty() {
		return productionQty;
	}
	public void setProductionQty(int productionQty) {
		this.productionQty = productionQty;
	}	
	public float getEstProdHours() {
		return estProdHours;
	}
	public void setEstProdHours(float estProdHours) {
		this.estProdHours = estProdHours;
	}
	public int getOperSeq() {
		return operSeq;
	}
	public void setOperSeq(int operSeq) {
		this.operSeq = operSeq;
	}	
	public String getNextWorkCenter() {
		return nextWorkCenter;
	}
	public void setNextWorkCenter(String nextWorkCenter) {
		this.nextWorkCenter = nextWorkCenter;
	}
	public String getSubAsmPartNum() {
		return subAsmPartNum;
	}
	public void setSubAsmPartNum(String subAsmPartNum) {
		this.subAsmPartNum = subAsmPartNum;
	}
	public String getSubAsmMtlPartNum() {
		return subAsmMtlPartNum;
	}
	public void setSubAsmMtlPartNum(String subAsmMtlPartNum) {
		this.subAsmMtlPartNum = subAsmMtlPartNum;
	}
	public float getSubAsmMtlQty() {
		return subAsmMtlQty;
	}
	public void setSubAsmMtlQty(Object subAsmMtlQty) {
		if(subAsmMtlQty != null)
			this.subAsmMtlQty = (float)subAsmMtlQty;
		else
			this.subAsmMtlQty = -1;
	}
	public String getSubAsmMtlUOM() {
		return subAsmMtlUOM;
	}
	public void setSubAsmMtlUOM(String subAsmMtlUOM) {
		this.subAsmMtlUOM = subAsmMtlUOM;
	}
	public String getMtlPartNum() {
		return mtlPartNum;
	}
	public void setMtlPartNum(String mtlPartNum) {
		this.mtlPartNum = mtlPartNum;
	}
	public float getMtlQty() {
		return mtlQty;
	}
	public void setMtlQty(Object mtlQty) {
		if(mtlQty != null)
			this.mtlQty = (float)mtlQty;
		else
			this.mtlQty = -1;
	}
	public String getMtlUOM() {
		return mtlUOM;
	}
	public void setMtlUOM(String mtlUOM) {
		this.mtlUOM = mtlUOM;
	}
}