package com.gemt.granite.service.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.gemt.granite.bean.mfg.JobOperationBean;
import com.gemt.granite.bean.mfg.OperationLaborBean;
import com.gemt.granite.dao.mfg.JobOperationDao;

public class JobOperationService {
	@Autowired
	JobOperationDao jobOperationDao;
	
	public List<JobOperationBean> getJobOperations(String wcCode, String opCode) throws Exception{
		return jobOperationDao.getJobOperations(wcCode, opCode);
	}
	
	public List<OperationLaborBean> getActiveOperations(String wcCode, String opCode) throws Exception{
		return jobOperationDao.getActiveOperationLabors(wcCode, opCode);
	}
		
}