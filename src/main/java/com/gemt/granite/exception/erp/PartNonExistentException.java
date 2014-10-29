package com.gemt.granite.exception.erp;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.RestException;

public class PartNonExistentException extends RestException{
	
	private static final long serialVersionUID = 1L;
	
	public PartNonExistentException(Exception ex){
		super(RestError.PART_NONEXISTENT, ex.getMessage());		
	}
}