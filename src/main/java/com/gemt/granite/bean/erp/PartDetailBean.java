package com.gemt.granite.bean.erp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartDetailBean {
	PartBean part;
	
	List<PartRevBean> revList;
	
	List<PartBinBean> binList;
	
	public PartBean getPart() {
		return part;
	}
	public void setPart(PartBean part) {
		this.part = part;
	}
	public List<PartRevBean> getRevList() {
		return revList;
	}
	public void setRevList(List<PartRevBean> revList) {
		this.revList = revList;
	}
	public List<PartBinBean> getBinList() {
		return binList;
	}
	public void setBinList(List<PartBinBean> binList) {
		this.binList = binList;
	}
}
