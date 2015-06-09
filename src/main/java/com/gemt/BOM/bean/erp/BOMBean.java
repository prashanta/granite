package com.gemt.BOM.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.rest.dev.BOM;

@XmlRootElement
public class BOMBean {
	
	//String partNum;
//	String Description;
//	int Class;
	int qtyPer;
//	String mtlPartNum;
//	String UOM;
	//boolean pullAsAsm;
//	boolean viewAsAsm;
	
	/*public String getPartNum() {
		return partNum;
	}*/
	public String getDescription(MaterialDetailBean partNum) {
		return partNum.getPartDescription();
	}
	
	public void getQtyPer(BOM partInfo) {
	//	partInfo.qtyCount(partInfoList);
	}
	
	
	public String getMtlPartNum(MaterialDetailBean partNum) {
		return partNum.getMtlPartNum();
	}
	
	public String getClass(MaterialDetailBean partNum) {
		return partNum.getPartClass();
	}
	
	public String getTypeCode(MaterialDetailBean partNum) {
		return partNum.getPartType();
	}
	
	
}		

