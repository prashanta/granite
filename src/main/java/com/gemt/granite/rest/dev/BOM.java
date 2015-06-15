package com.gemt.granite.rest.dev;

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

import com.gemt.granite.BOM.bean.erp.FlatBOMBean;
import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.service.erp.BOMService;
import com.gemt.granite.service.erp.MaterialService;

;

/**
 * Returns information on parts.
 * 
 * @author Yemini.B
 */

@RestController
@RequestMapping(value = "/BOM")
public class BOM {
	// final String jsonFilePath = "C:\\Users\\Yemini\\Desktop\\FlatBOM.json";

	@Autowired
	MaterialService materialService;

	@RequestMapping(value = "/test/{partNum}", method = RequestMethod.GET)
	public ResponseEntity<List<FlatBOMBean>> getMaterials(
			@PathVariable(value = "partNum") String partNum) throws Exception {


		BOMService bomService = new BOMService();
		List<MaterialDetailBean> beans = materialService
				.getMaterialDetails(partNum);
		Map<String, FlatBOMBean> m = bomService.qtyCount(beans, 1,
				materialService);

		List<FlatBOMBean> flatBOMs = new ArrayList<FlatBOMBean>(m.values());

		return new ResponseEntity<List<FlatBOMBean>>(flatBOMs, HttpStatus.OK);

	}
}
