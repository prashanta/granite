package com.gemt.granite.service.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemt.granite.bean.erp.PurchaseOrderBean;
import com.gemt.granite.dao.erp.PurchaseOrderDao;

@Service
public class PurchaseOrderService {
	@Autowired
	PurchaseOrderDao poDao;
	
	public List<PurchaseOrderBean> getListOfPOs(String partNum) throws Exception{
		return poDao.getListOfPOs(partNum);
	}
}
