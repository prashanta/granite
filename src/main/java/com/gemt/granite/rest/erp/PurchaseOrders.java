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
import com.gemt.granite.bean.erp.PurchaseOrderBean;
import com.gemt.granite.bean.error.ErrorMessage;
import com.gemt.granite.service.erp.PartService;
import com.gemt.granite.service.erp.PurchaseOrderService;



/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/pos")
public class PurchaseOrders {
	@Autowired  
	PurchaseOrderService poService;

	// Get list of POs for given part number
	@RequestMapping(value="/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PurchaseOrderBean> getListOfPOs(@PathVariable(value = "partnum") String partNum) throws Exception {
		return poService.getListOfPOs(partNum);
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
