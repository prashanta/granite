package com.gemt.granite.bean.mfg;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.gemt.granite.utility.JsonDateSerializer;


@XmlRootElement
public class JobOperationBean {
	
	String jobNum;
	int oprSeq;
	int assemblySeq;
	String wcCode;
	String opCode;
	boolean jobComplete;
	boolean prodComplete;
	int runQty;
	int qtyComplete;
	Date startDate;
	Date dueDate;
	
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public int getOprSeq() {
		return oprSeq;
	}
	public void setOprSeq(int oprSeq) {
		this.oprSeq = oprSeq;
	}	
	public int getAssemblySeq() {
		return assemblySeq;
	}
	public void setAssemblySeq(int assemblySeq) {
		this.assemblySeq = assemblySeq;
	}
	public String getWcCode() {
		return wcCode;
	}
	public void setWcCode(String wcCode) {
		this.wcCode = wcCode;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public boolean isJobComplete() {
		return jobComplete;
	}
	public void setJobComplete(boolean jobComplete) {
		this.jobComplete = jobComplete;
	}
	public boolean isProdComplete() {
		return prodComplete;
	}
	public void setProdComplete(boolean prodComplete) {
		this.prodComplete = prodComplete;
	}
	public int getRunQty() {
		return runQty;
	}
	public void setRunQty(int runQty) {
		this.runQty = runQty;
	}
	public int getQtyComplete() {
		return qtyComplete;
	}
	public void setQtyComplete(int qtyComplete) {
		this.qtyComplete = qtyComplete;
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
}