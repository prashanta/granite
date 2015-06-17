package com.gemt.granite.dev.rest.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.dev.service.erp.TestMaterialService;

@RestController
@RequestMapping(value="/testMaterialInfo")
public class TestMaterial {
	@Autowired  
	TestMaterialService testMaterialService;
	
	@RequestMapping(value="/{partnum:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> getMaterialInfo(@PathVariable(value = "partnum") String partNum) throws Exception {
		System.out.println(">> in dev.rest.erp.material");
		return new ResponseEntity<String>("Welcome to dev.rest.erp.material",
				HttpStatus.OK);
	}
}
