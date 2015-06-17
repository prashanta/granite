package com.gemt.granite.dev.service.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gemt.granite.dev.bean.erp.MaterialInfoBean;

/**
 * Returns information on parts.
 * 
 * qtyCount counts the total quantity of parts required to build the material
 * 
 * compaerMap(parentMap,childMap) updates the parentMap, if the part in childMap
 * is found in parentMap, update the quantity and add to parentMap.
 * 
 * setFlatBomBean(MaterialDetailBean mdb,FlatBOMBean flatBomBean) sets id's of
 * FlatBOMBean
 * 
 * @throws Exception
 * @author Yemini.B
 */

public class BOMService {

	public Map<String, MaterialInfoBean> qtyCount(List<MaterialInfoBean> mtlList,
			float n, TestMaterialService ms) throws Exception {

		Map<String, MaterialInfoBean> mtlMap = new HashMap<String, MaterialInfoBean>();
		Map<String, MaterialInfoBean> childMap = new HashMap<String, MaterialInfoBean>();

		String partNo;
		BOMService bomService = new BOMService();

		for (MaterialInfoBean mtl : mtlList) {
			MaterialInfoBean currentBean = new MaterialInfoBean();
			if (mtl.isPullAsAsm() && mtl.isViewAsAsm()
					&& mtl.getMtlPartNum() != null) { // condition if child exists
				
				MaterialInfoBean childBean = new MaterialInfoBean();
				partNo = mtl.getMtlPartNum();
				childBean.setQtyPer(mtl.getQtyPer());
				mtlMap.put(partNo, setFlatBomBean(mtl, childBean));
				List<MaterialInfoBean> children = null;
				try{
				 children = ms
						.getMaterialInfo(partNo); // retrieve child parts of
				// a child
				}
				catch(Exception e)
				{
					System.out.println("NO CHILd... in " + partNo);
				}
				finally{
					if ( children != null && children.size() > 0) {
						childMap = bomService.qtyCount(children,
								mtl.getQtyPer(), ms); // recursive call
						compareMap(mtlMap, childMap);
					}
				}
				
				
			} else { // condition if no child

				if (mtlMap.containsKey(mtl.getMtlPartNum())) { // if part exists
																// in map update
																// the part

					MaterialInfoBean tempBeam = mtlMap.get(mtl.getMtlPartNum());
					float tmpCount = tempBeam.getQtyPer();
					tempBeam.setQtyPer(tmpCount + (mtl.getQtyPer() * n));
					mtlMap.put(mtl.getMtlPartNum(), tempBeam);

				} else { // if part does not exist in map just add it into the
							// parent map

					currentBean.setQtyPer(mtl.getQtyPer() * n);
					mtlMap.put(mtl.getMtlPartNum(),
							setFlatBomBean(mtl, currentBean));

				}
			}
		}
		// if failed
		return mtlMap;
	}

	private static void compareMap(Map<String, MaterialInfoBean> parentMap,
			Map<String, MaterialInfoBean> childMap) {

		for (String key : childMap.keySet()) {
			if (parentMap.containsKey(key)) { // if part exists in map
				MaterialInfoBean Parent_BOM = parentMap.get(key);
				MaterialInfoBean child_BOM = childMap.get(key);
				Parent_BOM.setQtyPer(Parent_BOM.getQtyPer()
						+ child_BOM.getQtyPer());
				parentMap.put(key, Parent_BOM);

			} else { // if part does not exist in map
				parentMap.put(key, childMap.get(key));

			}

		}
	}

	private static MaterialInfoBean setFlatBomBean(MaterialInfoBean mdb,
			MaterialInfoBean flatBomBean) {

		flatBomBean.setPartClass(mdb.getPartClass());
		flatBomBean.setPartDescription(mdb.getPartDescription());
		flatBomBean.setPartNum(mdb.getMtlPartNum());
		flatBomBean.setInvUM(mdb.getInvUM());
		flatBomBean.setPartType(mdb.getPartType());

		return flatBomBean;
	}
}
