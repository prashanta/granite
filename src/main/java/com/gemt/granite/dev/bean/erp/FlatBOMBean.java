package com.gemt.granite.dev.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FlatBOMBean {

	String partNum;
	String Description;
	String partClass;
	int qtyPer;
	String partType;

	private String invUM;

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPartClass() {
		return partClass;
	}

	public void setPartClass(String partClass) {
		this.partClass = partClass;
	}

	public int getQtyPer() {
		return qtyPer;
	}

	public void setQtyPer(int qtyPer) {
		this.qtyPer = qtyPer;
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

	public String toString() {
		return " partnum:" + partNum + "   Part Type " + partType +  "   Description: " + Description
				+ "  qty: " + qtyPer + "   Part Class " + partClass ;
	}

}
