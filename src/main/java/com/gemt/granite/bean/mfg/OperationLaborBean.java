package com.gemt.granite.bean.mfg;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.gemt.granite.utility.JsonDateSerializer;
import com.gemt.granite.utility.JsonTimeSerializer;


@XmlRootElement
public class OperationLaborBean {
	
	String jobNum;
	String wcCode;
	String opCode;
	String employeeName;
	float clockInTime;
	Date clockInDate;
	float clockOutTime;
	Date clockOutDate;
	boolean opComplete;
	
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@JsonSerialize(using=JsonTimeSerializer.class)
	public float getClockInTime() {
		return clockInTime;
	}
	public void setClockInTime(float clockInTime) {
		this.clockInTime = clockInTime;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getClockInDate() {
		return clockInDate;
	}
	public void setClockInDate(Date clockInDate) {
		this.clockInDate = clockInDate;
	}
	@JsonSerialize(using=JsonTimeSerializer.class)
	public float getClockOutTime() {
		return clockOutTime;
	}
	public void setClockOutTime(float clockOutTime) {
		this.clockOutTime = clockOutTime;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getClockOutDate() {
		return clockOutDate;
	}	
	public void setClockOutDate(Date clockOutDate) {
		this.clockOutDate = clockOutDate;
	}
	public boolean isOpComplete() {
		return opComplete;
	}
	public void setOpComplete(boolean opComplete) {
		this.opComplete = opComplete;
	}				
}