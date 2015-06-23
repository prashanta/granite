package com.gemt.granite.service.mfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.gemt.granite.bean.mfg.CUTActiveOperBean;
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
	
	public List<MTLXCUTOperBean> getMTLXCutOperations() throws Exception{
		List<MTLXCUTOperBean> stdOperations = mtlxWorkCellDao.getMTLXCutOperations();
		return stdOperations;
	}
	
	public List<MTLXCUTOperBean> getMTLNCutOperations() throws Exception{
		List<MTLXCUTOperBean> nonStdOperations = mtlxWorkCellDao.getMTLNCutOperations();
		return nonStdOperations;
	}
	
	public List<CUTActiveOperBean> getActiveCutOperations() throws Exception{	
		List<CUTActiveOperBean> activeOperations = new ArrayList<CUTActiveOperBean>();
		
		// Get active CUT operations for MTLX work center 
		List<OperationLaborBean> stdLabors = jobOperationDao.getActiveOperationLabors("MTLX", "CUT");
		// Get active CUT operations for MTLN work center
		List<OperationLaborBean> nonStdLabors = jobOperationDao.getActiveOperationLabors("MTLN", "CUT");
		// Combine both lists
		stdLabors.addAll(nonStdLabors);
		
		Iterator<OperationLaborBean> itr = stdLabors.iterator();
		
		while(itr.hasNext()){
			CUTActiveOperBean activeOper = new CUTActiveOperBean();
			activeOper.setLabor(itr.next());
			MTLXCUTOperBean cutOper = mtlxWorkCellDao.getCutOperDetail(activeOper.getLabor().getJobNum()); 
			activeOper.setOper(cutOper);
			String nextWC = workCenterDao.getNextWorkCell(activeOper.getOper().getJobNum(), activeOper.getOper().getOperSeq());
			activeOper.getOper().setNextWorkCenter(nextWC);
			activeOperations.add(activeOper);
		}		
		return activeOperations;
	}
}