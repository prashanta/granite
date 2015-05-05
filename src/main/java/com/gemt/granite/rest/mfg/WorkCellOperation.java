package com.gemt.granite.rest.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.mfg.CUTActiveOperBean;
import com.gemt.granite.bean.mfg.MTLXCUTOperBean;
import com.gemt.granite.service.mfg.InfoRadiatorService;

/**
 * Returns information on job operation.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/ir")
public class WorkCellOperation {
	@Autowired  
	InfoRadiatorService infoRadiatorService;
		
	@RequestMapping(value="/mtlxcutoperations", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MTLXCUTOperBean> getMTLXCutOperations() throws Exception {
		return infoRadiatorService.getMTLXCutOperations();
	}
	
	@RequestMapping(value="/mtlncutoperations", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MTLXCUTOperBean> getMTLNCutOperations() throws Exception {
		return infoRadiatorService.getMTLNCutOperations();
	}
	
	@RequestMapping(value="/activecutoperations", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CUTActiveOperBean> getActiveCutOperations() throws Exception {
		return infoRadiatorService.getActiveCutOperations();
	}		
}
