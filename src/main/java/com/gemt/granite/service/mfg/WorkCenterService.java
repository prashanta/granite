package com.gemt.granite.service.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemt.granite.bean.mfg.WorkCenterBean;
import com.gemt.granite.dao.mfg.WorkCenterDao;

public class WorkCenterService {
	@Autowired
	WorkCenterDao workCenterDao;
	
	public List<WorkCenterBean> getWorkCenters() throws Exception{
		return workCenterDao.getWorkCenters();
	}

	public WorkCenterBean getWorkCenter(String workCenterCode) throws Exception {
		return workCenterDao.getWorkCenter(workCenterCode);		
	}	
}