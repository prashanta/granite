package com.gemt.granite.bean.error;

import javax.xml.bind.annotation.XmlRootElement;

import com.gemt.granite.exception.GraniteRestException;

@XmlRootElement
public class ErrorMessage {
	
	private int code;
	private String message = "";
	private String displayMessage = "";
	private int value = 0;
	
	
	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(int code, String message, String displayMessage) {
		super();
		this.code = code;
		this.message = message;
		this.displayMessage = displayMessage;
	}
	public ErrorMessage(GraniteRestException ex) {
		super();
		this.code = ex.getErrorCode();
		this.message = ex.getMessage();
		this.displayMessage = ex.getDisplayMessage();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
