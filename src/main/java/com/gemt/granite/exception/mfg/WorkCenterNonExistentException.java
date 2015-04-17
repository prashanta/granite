package com.gemt.granite.exception.mfg;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.GraniteRestException;

public class WorkCenterNonExistentException extends GraniteRestException{
	
	private static final long serialVersionUID = 1L;
	
	public WorkCenterNonExistentException(Exception ex){
		super(RestError.PART_NONEXISTENT, ex.getMessage());		
	}
}