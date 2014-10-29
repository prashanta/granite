package com.gemt.granite.exception;

public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int errorCode = -1;
	private String displayMessage = "";
	
	public RestException(RestError error, String message){
		super(message);
		this.errorCode = error.code();
		this.displayMessage = error.message();
	}
	
	public RestException(RestError error){
		super("");
		this.errorCode = error.code();
		this.displayMessage = error.message();
	}
	
	public int getErrorCode(){
		return this.errorCode;
	}
	
	public String getDisplayMessage(){
		return this.displayMessage;
	}
}
