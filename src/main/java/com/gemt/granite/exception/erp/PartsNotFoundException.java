package com.gemt.granite.exception.erp;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.RestException;

public class PartsNotFoundException extends RestException{
	
	private static final long serialVersionUID = 1L;
	
	public PartsNotFoundException(){
		super(RestError.PARTS_NOT_FOUND, null);		
	}
}