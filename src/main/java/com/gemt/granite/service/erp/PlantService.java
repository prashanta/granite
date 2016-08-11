package com.gemt.granite.service.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemt.granite.bean.erp.MRPRunInfo;
import com.gemt.granite.bean.erp.PartBean;
import com.gemt.granite.bean.erp.PartBinBean;
import com.gemt.granite.bean.erp.PartDetailBean;
import com.gemt.granite.bean.erp.PartPlantBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.dao.erp.PartDao;
import com.gemt.granite.dao.erp.PlantDao;

@Service("plantService")
public class PlantService {
	@Autowired
	PlantDao plantDao;
	
	public MRPRunInfo getPlantMRPRunInfo(String plant) throws Exception{
		return plantDao.getPlantMRPRunInfo(plant);
	}
}
