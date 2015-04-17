package com.gemt.granite.exception;

import javax.servlet.http.HttpServletRequest; 

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gemt.granite.bean.error.ErrorMessage;

@ControllerAdvice
public class RestExceptionProcessor {
     
	static Logger log = Logger.getLogger(RestExceptionProcessor.class);
	
    @ExceptionHandler(GraniteRestException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleXException(HttpServletRequest req, GraniteRestException ex) {        
    	long startTime = (Long)req.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long exeTime = endTime - startTime;
		String strExeTime = exeTime > 1000? (exeTime/1000 + " secs") : (exeTime + " msec");
		log.error("RES: [" + req.getRequestURI() + "] - " + ex.getMessage() + " - Exe time: " + strExeTime);		
        return new ErrorMessage(ex);
    }
}