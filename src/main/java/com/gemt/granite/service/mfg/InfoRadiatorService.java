package com.gemt.granite.service.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.gemt.granite.bean.mfg.MTLXCUTActiveOperationBean;
import com.gemt.granite.bean.mfg.MTLXCUTOperBean;
import com.gemt.granite.bean.mfg.OperationLaborBean;
import com.gemt.granite.dao.mfg.JobOperationDao;
import com.gemt.granite.dao.mfg.MTLXWorkCellDao;
import com.gemt.granite.dao.mfg.WorkCenterDao;

public class InfoRadiatorService {
	@Autowired
	MTLXWorkCellDao mtlxWorkCellDao;
	
	@Autowired
	JobOperationDao jobOperationDao;
	
	@Autowired
	WorkCenterDao workCenterDao;
	
	@Transactional(readOnly=true, isolation=Isolation.READ_UNCOMMITTED)
	public List<MTLXCUTOperBean> getMTLXCUTOper() throws Exception{
		return mtlxWorkCellDao.getMTLXCUTOper();
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_UNCOMMITTED)
	public List<MTLXCUTOperBean> getMTLXCUTStdOper() throws Exception{
		List<MTLXCUTOperBean> stdOperations = mtlxWorkCellDao.getMTLXCUTStdOperations();
		return stdOperations;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_UNCOMMITTED)
	public List<MTLXCUTOperBean> getMTLXCUTNonStdOper() throws Exception{
		List<MTLXCUTOperBean> nonStdOperations = mtlxWorkCellDao.getMTLXCUTNonStdOperations();
		return nonStdOperations;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_UNCOMMITTED)
	public MTLXCUTActiveOperationBean getMTLXCUTActiveOper() throws Exception{	
		MTLXCUTActiveOperationBean activeOperation = new MTLXCUTActiveOperationBean();
		
		List<OperationLaborBean> labors = jobOperationDao.getActiveOperationLabors("MTLX", "CUT");
		if(labors.size() > 0){
			activeOperation.setLabor(labors.get(0));		
			activeOperation.setOper(mtlxWorkCellDao.getMTLXCUTOperDetail(activeOperation.getLabor().getJobNum()));
			String nextWC = workCenterDao.getNextWorkCell(activeOperation.getOper().getJobNum(), activeOperation.getOper().getOperSeq());
			activeOperation.getOper().setNextWorkCenter(nextWC);			
		}
		else{
			OperationLaborBean labor = new OperationLaborBean();
			labor.setJobNum(null);
			activeOperation.setLabor(labor);
		}
		return activeOperation;
	}
}