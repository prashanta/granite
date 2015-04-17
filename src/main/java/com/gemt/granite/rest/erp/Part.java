package com.gemt.granite.rest.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.erp.PartBean;
import com.gemt.granite.bean.erp.PartBinBean;
import com.gemt.granite.bean.erp.PartDetailBean;
import com.gemt.granite.bean.erp.PartPlantBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.service.erp.PartService;



/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/part")
public class Part {
	@Autowired  
	PartService partService;

	@RequestMapping(value="/info/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartBean getPartInfo(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartInfo(partNum);
	}
	
	@RequestMapping(value="/detail/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartDetailBean getPartDetail(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartDetail(partNum);
	}
	
	@RequestMapping(value="/rev/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartRevBean> getPartRevision(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartRevision(partNum);
	}
	
	@RequestMapping(value="/approvedrev/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartRevBean getPartApprovedRevision(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartApprovedRevision(partNum);
	}
	
	@RequestMapping(value="/bin/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartBinBean> getPartBin(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartBin(partNum);
	}

	@RequestMapping(value="/search/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartBean> searchPart(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.searchPart(partNum);
	}
	
	@RequestMapping(value="/searchbydesc/{description}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartBean> searchPartByDescription(@PathVariable(value = "description") String description) throws Exception {
		return partService.searchPartByDescription(description);
	}
	
	@RequestMapping(value="/class/{class}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartBean> getPartsByClass(@PathVariable(value = "class") String partClass) throws Exception {
		return partService.getPartsByClass(partClass);
	}
	
	@RequestMapping(value="/plant/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartPlantBean getPartPlantInfo(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partService.getPartPlantInfo(partNum);
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
