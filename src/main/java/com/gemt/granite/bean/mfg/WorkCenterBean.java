package com.gemt.granite.bean.mfg;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkCenterBean {
	
	String workCenterCode;
	String description;
	int noOfMachines;
	String opCode;
	String opDescription;
	int prodBurRate;
	int setupBurRate;
	int hoursPerMachine;
	
	public String getWorkCenterCode() {
		return workCenterCode;
	}
	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNoOfMachines() {
		return noOfMachines;
	}
	public void setNoOfMachines(int noOfMachines) {
		this.noOfMachines = noOfMachines;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public String getOpDescription() {
		return opDescription;
	}
	public void setOpDescription(String opDescription) {
		this.opDescription = opDescription;
	}
	public int getProdBurRate() {
		return prodBurRate;
	}
	public void setProdBurRate(int prodBurRate) {
		this.prodBurRate = prodBurRate;
	}
	public int getSetupBurRate() {
		return setupBurRate;
	}
	public void setSetupBurRate(int setupBurRate) {
		this.setupBurRate = setupBurRate;
	}
	public int getHoursPerMachine() {
		return hoursPerMachine;
	}
	public void setHoursPerMachine(int hoursPerMachine) {
		this.hoursPerMachine = hoursPerMachine;
	}	
}