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
import com.gemt.granite.bean.mfg.JobOperationBean;
import com.gemt.granite.bean.mfg.OperationLaborBean;
import com.gemt.granite.service.mfg.JobOperationService;

/**
 * Returns information on job operation.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/job")
public class JobOperation {
	@Autowired  
	JobOperationService jobOperationService;

	@RequestMapping(value="/ops/{wccode}/{opcode}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<JobOperationBean> getJobOperations(@PathVariable(value = "wccode") String wcCode, @PathVariable(value = "opcode") String opCode) throws Exception {
		return jobOperationService.getJobOperations(wcCode, opCode);
	}
	
	@RequestMapping(value="/activeops/{wccode}/{opcode}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OperationLaborBean> getActiveOpeartions(@PathVariable(value = "wccode") String wcCode, @PathVariable(value = "opcode") String opCode) throws Exception {
		return jobOperationService.getActiveOperations(wcCode, opCode);
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
