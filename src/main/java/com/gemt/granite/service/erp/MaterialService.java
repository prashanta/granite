package com.gemt.granite.service.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemt.granite.bean.erp.MaterialBean;
import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.dao.erp.MaterialDao;
import com.gemt.granite.dao.erp.PartDao;

public class MaterialService {
	@Autowired
	MaterialDao materialDao;
	
	@Autowired
	PartDao partDao;
	
	public List<MaterialBean> getMaterials(String partNum, String revNum) throws Exception{
		return materialDao.getMaterials(partNum, revNum);
	}
	
	public List<MaterialBean> getMaterials(String partNum) throws Exception{
		PartRevBean partRev = partDao.getApprovedRevision(partNum);		
		return materialDao.getMaterials(partNum, partRev.getRevisionNum());
	}

	public List<MaterialDetailBean> getMaterialDetails(String partNum) throws Exception {
		PartRevBean partRev = partDao.getApprovedRevision(partNum);		
		return materialDao.getMaterialDetails(partNum, partRev.getRevisionNum());		
	}
}
