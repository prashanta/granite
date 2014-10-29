package com.gemt.granite.rest.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.bean.mfg.WorkCenterBean;
import com.gemt.granite.service.mfg.WorkCenterService;

/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/wc")
public class WorkCenter {
	@Autowired  
	WorkCenterService workCenterService;

	@RequestMapping(value="/info/{workcentercode}", produces=MediaType.APPLICATION_JSON_VALUE)
	public WorkCenterBean getWorkCenter(@PathVariable(value = "workcentercode") String workCenterCode) throws Exception {
		return workCenterService.getWorkCenter(workCenterCode);
	}		
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<WorkCenterBean> getWorkCenters() throws Exception {
		return workCenterService.getWorkCenters();
	}
	
	@RequestMapping(value="*", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage fallbackMethod(){
		ErrorMessage msg = new ErrorMessage();
		msg.setCode(0);
		msg.setMessage("No end point found.");
		return msg;
	}
}
