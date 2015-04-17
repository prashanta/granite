package com.gemt.granite.bean.mfg;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MTLXCUTActiveOperationBean {	
	OperationLaborBean labor;
	MTLXCUTOperBean oper;
	
	public OperationLaborBean getLabor() {
		return labor;
	}
	public void setLabor(OperationLaborBean labor) {
		this.labor = labor;
	}
	public MTLXCUTOperBean getOper() {
		return oper;
	}
	public void setOper(MTLXCUTOperBean oper) {
		this.oper = oper;
	}	
}