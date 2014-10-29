package com.gemt.granite.exception.erp;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.RestException;

public class PartRevNotFoundException extends RestException{
	
	private static final long serialVersionUID = 1L;
	
	public PartRevNotFoundException(){
		super(RestError.PART_REVISION_NOT_FOUND, null);		
	}
}