package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Prashanta.S
 */
@XmlRootElement
public class PartBean {
	
	String partNum;
	String partDescription;
	String partClass;
	String invUM;
	String partType;
	boolean nonStock;
	boolean inactive;
	
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
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
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
		
	
}