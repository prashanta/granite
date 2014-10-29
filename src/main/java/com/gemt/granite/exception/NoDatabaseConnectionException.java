package com.gemt.granite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gemt.granite.exception.RestException;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class NoDatabaseConnectionException extends RestException{
	
	private static final long serialVersionUID = 1L;
	
	public NoDatabaseConnectionException(RestError error, String message) {
		super(error, message);		
	}
	
	public NoDatabaseConnectionException(Exception ex) {		
		super(RestError.NO_DATABASE_CONNECTION, ex.getMessage());		
	}
}
