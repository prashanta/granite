package com.gemt.granite.exception.erp;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.RestException;

public class PartBinNotFoundException extends RestException{
	
	private static final long serialVersionUID = 1L;
	
	public PartBinNotFoundException(){
		super(RestError.PART_BIN_NOT_FOUND, null);		
	}
}