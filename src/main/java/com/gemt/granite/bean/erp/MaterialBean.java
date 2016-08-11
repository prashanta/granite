package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * Bean to store basic Part Material information
 * 
 * @author Prashanta.S
 */
@XmlRootElement
public class MaterialBean {
	
	/** Parent part number */
	String partNum;
	
	/** Parent part revision number */
	String revisionNum;
	
	/** Material part number*/
	String mtlPartNum;
	
	/** Part Material sequence number */
	int mtlSeq;
	
	/** Quantity of part material */
	float qtyPer;
	
	/** Pulls as Assembly {true: Pull as Assembly, false: Pull as Material} */
	boolean pullAsAsm;
	
	/** View as Assembly  {true: View as Assembly, false: View as Material} */
	boolean viewAsAsm;
	
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
	public float getQtyPer() {
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
}
