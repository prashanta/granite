package com.gemt.granite.exception.mfg;

import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.GraniteRestException;

public class WorkCentersNotFoundException extends GraniteRestException{
	
	private static final long serialVersionUID = 1L;
	
	public WorkCentersNotFoundException(){
		super(RestError.WORK_CENTERS_NOT_FOUND);		
	}
}