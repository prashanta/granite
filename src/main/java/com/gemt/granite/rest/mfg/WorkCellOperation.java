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

	@RequestMapping(value="/mtlxcut", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MTLXCUTOperBean> getMTLXCUTOper() throws Exception {
		return infoRadiatorService.getMTLXCUTOper();
	}
	
	@RequestMapping(value="/mtlxcutstd", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MTLXCUTOperBean> getMTLXCUTStdOper() throws Exception {
		return infoRadiatorService.getMTLXCUTStdOper();
	}
	
	@RequestMapping(value="/mtlxcutnonstd", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MTLXCUTOperBean> getMTLXCUTNonStdOper() throws Exception {
		return infoRadiatorService.getMTLXCUTNonStdOper();
	}
	
	@RequestMapping(value="/mtlxcutactive", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CUTActiveOperBean> getMTLXCUTActiveOper() throws Exception {
		return infoRadiatorService.getMTLXCUTActiveOper();
	}		
}
