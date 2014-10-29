package com.gemt.granite.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.exception.RestException;

@ControllerAdvice
public class RestExceptionProcessor {
     
    @ExceptionHandler(RestException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleXException(HttpServletRequest req, RestException ex) {         
        return new ErrorMessage(ex);
    }
 
}