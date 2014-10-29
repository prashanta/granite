package com.gemt.granite.bean.erp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartBinBean {
	String binNum;
	int onhandQty;
	
	public String getBinNum() {
		return binNum;
	}
	public void setBinNum(String binNum) {
		this.binNum = binNum;
	}
	public int getOnhandQty() {
		return onhandQty;
	}
	public void setOnhandQty(int onhandQty) {
		this.onhandQty = onhandQty;
	}
}
