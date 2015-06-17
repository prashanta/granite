package com.gemt.granite.dev.rest.erp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.dev.bean.erp.MaterialInfoBean;
import com.gemt.granite.dev.service.erp.BOMService;
import com.gemt.granite.dev.service.erp.TestMaterialService;


/**
 * Returns Flat BOM of parts.
 * 
 * @author Yemini.B
 */

@RestController
@RequestMapping(value = "/testFlatBOM")
public class BOM {
	// final String jsonFilePath = "C:\\Users\\Yemini\\Desktop\\FlatBOM.json";

	@Autowired
	TestMaterialService testMaterialService;

	@RequestMapping(value = "/{partNum}", method = RequestMethod.GET)
	public ResponseEntity<List<MaterialInfoBean>> getFlatBOM(
			@PathVariable(value = "partNum") String partNum) throws Exception {


		BOMService bomService = new BOMService();
		List<MaterialInfoBean> beans = testMaterialService
				.getMaterialInfo(partNum);
		Map<String, MaterialInfoBean> m = bomService.qtyCount(beans, 1,
				testMaterialService);

		List<MaterialInfoBean> flatBOMs = new ArrayList<MaterialInfoBean>(m.values());

		return new ResponseEntity<List<MaterialInfoBean>>(flatBOMs, HttpStatus.OK);

	}
}
