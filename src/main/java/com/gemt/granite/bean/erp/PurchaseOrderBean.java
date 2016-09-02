package com.gemt.granite.bean.erp;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Prashanta.S
 */
@XmlRootElement
public class PurchaseOrderBean {
	
	int poNum;
	int poLine;
	float orderQty;
	Date orderDate;
	int vendorNum;
	boolean openOrder;
	
	String vendorName;
	String vendorAddress;
	String vendorCity;
	String vendorCountry;
	
	public int getPoNum() {
		return poNum;
	}
	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}
	
	public int getPoLine() {
		return poLine;
	}
	public void setPoLine(int poLine) {
		this.poLine = poLine;
	}
	public float getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(float orderQty) {
		this.orderQty = orderQty;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getVendorNum() {
		return vendorNum;
	}
	public void setVendorNum(int vendorNum) {
		this.vendorNum = vendorNum;
	}
	public boolean isOpenOrder() {
		return openOrder;
	}
	public void setOpenOrder(boolean openOrder) {
		this.openOrder = openOrder;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getVendorCity() {
		return vendorCity;
	}
	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}
	public String getVendorCountry() {
		return vendorCountry;
	}
	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}
	
	
}