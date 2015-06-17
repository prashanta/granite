package com.gemt.granite.dev.service.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.dev.dao.erp.TestMaterialDao;
import com.gemt.granite.dao.erp.PartDao;
import com.gemt.granite.dev.bean.erp.MaterialInfoBean;

public class TestMaterialService {
	@Autowired
	TestMaterialDao testMaterialDao;
	
	@Autowired
	PartDao testPartDao;
	
	public List<MaterialInfoBean> getMaterialInfo(String partNum) throws Exception{
		System.out.println(">> in dev.materialService.");
		PartRevBean partRev = testPartDao.getApprovedRevision(partNum);
		return testMaterialDao.getMaterialInfo(partNum, partRev.getRevisionNum());
	}
}
