package com.gemt.granite.dev.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * Returns information on parts.
 * 
 * @author ChiragRajk
 */

@RestController
@RequestMapping(value = "/devDB")
public class Second {

	@Autowired
	TestDAO t = new TestDAO();
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<String> test() throws Exception {
		String ret = "Hallo!! Testing DB!!";
		System.out.println(ret);
		
		t.insert();
		
		return new ResponseEntity<String>(ret,
				HttpStatus.OK);
	}

//	@RequestMapping(value = "/test/{partNum}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public void getMaterials(@PathVariable(value = "partNum") String partNum)
//			throws Exception {
//		BOMService test=new BOMService();
//		Map<String, Integer> m = test.qtyCount(
//				materialService.getMaterialDetails(partNum), 1);
//		for (Entry<String, Integer> en : m.entrySet())  // test print for hash map
//		{ 
//																			
//			System.out.println(">> key:  " + en.getKey() + " value: "
//					+ en.getValue());
//		}
//	}

}	
	




