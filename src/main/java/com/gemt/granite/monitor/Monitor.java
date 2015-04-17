package com.gemt.granite.monitor;

import java.io.DataOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Monitor implements  HandlerInterceptor{
	
	static Logger log = Logger.getLogger(Monitor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		   ipAddress = request.getRemoteAddr();  
		}
		MDC.put("ip", ipAddress);
		log.info("REQ: [" + request.getRequestURI() + "]");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)throws Exception {
		long startTime = (Long)request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long exeTime = endTime - startTime;
		String strExeTime = exeTime > 1000? (exeTime/1000 + " secs") : (exeTime + " msec");
		log.info("RES: [" + request.getRequestURI() + "] - Exe time: " + strExeTime);				
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception arg3) throws Exception {
		// Let RestExceptionProcessor handle response with exception
	}
}
