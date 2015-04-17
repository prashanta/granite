package com.gemt.granite.exception;

public class GraniteRestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int errorCode = -1;
	private String displayMessage = "";
	
	public GraniteRestException(RestError error, String message){
		super(message);
		this.errorCode = error.code();
		this.displayMessage = error.message();
	}
	
	public GraniteRestException(RestError error, Exception ex){
		super(ex.getLocalizedMessage());
		this.errorCode = error.code();
		this.displayMessage = error.message();
	}
	
	public GraniteRestException(RestError error){
		super(error.message());
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
