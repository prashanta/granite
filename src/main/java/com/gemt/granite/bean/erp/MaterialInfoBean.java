package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialInfoBean {

	String partNum;
	String mtlPartNum;
	boolean pullAsAsm;
	boolean viewAsAsm;
	String revisionNum;
	float qtyPer;
	String partDescription;
	String partClass;
	String partType;
	String invUM;

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
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

	public String getRevisionNum() {
		return revisionNum;
	}

	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}

	public float getQtyPer() {
		return qtyPer;
	}

	public void setQtyPer(float qtyPer) {
		this.qtyPer = qtyPer;
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

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getInvUM() {
		return invUM;
	}

	public void setInvUM(String invUM) {
		this.invUM = invUM;
	}
}
