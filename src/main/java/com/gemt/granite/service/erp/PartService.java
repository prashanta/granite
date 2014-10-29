package com.gemt.granite.service.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemt.granite.bean.erp.PartBean;
import com.gemt.granite.bean.erp.PartBinBean;
import com.gemt.granite.bean.erp.PartDetailBean;
import com.gemt.granite.bean.erp.PartPlantBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.dao.erp.PartDao;

public class PartService {
	@Autowired
	PartDao partDao;
	
	public PartBean getPartInfo(String partNum) throws Exception{
		return partDao.getPartInfo(partNum);
	}

	public List<PartBean> searchPart(String partNum) throws Exception {
		return partDao.searchPart(partNum);		
	}

	public List<PartBean> searchPartByDescription(String description) throws Exception {
		return partDao.searchPartByDescription(description);
	}

	public List<PartBean> getPartsByClass(String partClass) throws Exception {
		return partDao.getPartsByClass(partClass);
	}
	
	public List<PartRevBean> getPartRevision(String partNum) throws Exception {
		return partDao.getPartRevisions(partNum);
	}
	
	public List<PartBinBean> getPartBin(String partNum) throws Exception {
		return partDao.getPartBin(partNum);
	}
		
	public PartDetailBean getPartDetail(String partNum) throws Exception {
		PartDetailBean partDetail = new PartDetailBean();
		PartBean part = partDao.getPartInfo(partNum);
		List<PartRevBean> rev = null;
		List<PartBinBean> bin = null;
		try{
			rev = partDao.getPartRevisions(partNum);
			bin = partDao.getPartBin(partNum);
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		partDetail.setPart(part);
		partDetail.setRevList(rev);
		partDetail.setBinList(bin);
		return partDetail;
	}

	public PartPlantBean getPartPlantInfo(String partNum) throws Exception {
		return partDao.getPartPlantInfo(partNum);
	}
}
