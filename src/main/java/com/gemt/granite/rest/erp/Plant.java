package com.gemt.granite.rest.erp;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.erp.MRPRunInfo;
import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.service.erp.PlantService;



/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/plant")
public class Plant {
	@Autowired  
	@Qualifier("plantService")
	PlantService plantService;

	@RequestMapping(value="/mrprun/{plant:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public MRPRunInfo getPlantMRPRunInfo(@PathVariable(value = "plant") String plant) throws Exception {
		return plantService.getPlantMRPRunInfo(plant);
	}
	
	@RequestMapping(value="/mrprun/test", produces=MediaType.APPLICATION_JSON_VALUE)
	public MRPRunInfo getPlantMRPRunInfoTest() throws Exception {
		MRPRunInfo m = new MRPRunInfo();
		m.setMrpLastRunDate(new Date(3012,2,2));
		m.setMrpLastRunTime(1221);
		return m;
	}

	
	@RequestMapping(value="**", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage fallbackMethod(){
		ErrorMessage msg = new ErrorMessage();
		msg.setCode(0);
		msg.setMessage("No end point found.");
		return msg;
	}
}
