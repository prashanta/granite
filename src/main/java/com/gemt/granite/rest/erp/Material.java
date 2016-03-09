package com.gemt.granite.rest.erp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.erp.MaterialBean;
import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.bean.erp.MaterialInfoBean;
import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.service.erp.BOMService;
import com.gemt.granite.service.erp.MaterialService;

/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/mtl")
public class Material {
	@Autowired  
	MaterialService materialService;	

	@RequestMapping(value="/{partnum}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialBean> getMaterials(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		return materialService.getMaterials(partNum, revNum);
	}
	
	@RequestMapping(value="/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialBean> getMaterials(@PathVariable(value = "partnum") String partNum) throws Exception {
		return materialService.getMaterials(partNum);
	}
	
	@RequestMapping(value="/detail/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialDetailBean> getMaterailDetails(@PathVariable(value = "partnum") String partNum) throws Exception {
		return materialService.getMaterialDetails(partNum);
	}
	
	@RequestMapping(value="/detail/{partnum:.+}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialDetailBean> getMaterailDetails(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		return materialService.getMaterialDetails(partNum, revNum);
	}	
	
	@RequestMapping(value="**", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage fallbackMethod(){
		ErrorMessage msg = new ErrorMessage();
		msg.setCode(0);
		msg.setMessage("No end point found.");
		return msg;
	}
	
	@RequestMapping(value = "/flatBOM/{partNum}", method = RequestMethod.GET)
	public ResponseEntity<List<MaterialInfoBean>> getFlatBOM(@PathVariable(value = "partNum") String partNum) throws Exception {

		BOMService bomService = new BOMService();
		List<MaterialInfoBean> beans = materialService.getMaterialInfo(partNum);
		Map<String, MaterialInfoBean> m = bomService.qtyCount(beans, 1,materialService);
		List<MaterialInfoBean> flatBOMs = new ArrayList<MaterialInfoBean>(m.values());
		return new ResponseEntity<List<MaterialInfoBean>>(flatBOMs, HttpStatus.OK);
	}
}
