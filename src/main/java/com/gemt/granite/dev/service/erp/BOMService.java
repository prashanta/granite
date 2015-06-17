package com.gemt.granite.dev.service.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.dev.bean.erp.FlatBOMBean;
import com.gemt.granite.service.erp.MaterialService;

/**
 * Returns information on parts.
 * 
 * qtyCount counts the total quantity of parts required to build the material
 * 
 * compaerMap(parentMap,childMap) updates the parentMap, if the part in childMap
 * is found in parentMap, update the quantity and add to parentMap.
 * 
 * setFlatBomBean(MaterialDetailBean mdb,FlatBOMBean flatBomBean) sets id's of FlatBOMBean
 * 
 * @throws Exception
 * @author Yemini.B
 */

public class BOMService {

	public Map<String, FlatBOMBean> qtyCount(List<MaterialDetailBean> list,
			float n, MaterialService ms) throws Exception {

		Map<String, FlatBOMBean> mtlMap = new HashMap<String, FlatBOMBean>();
		Map<String, FlatBOMBean> childMap = new HashMap<String, FlatBOMBean>();

		String partNo;
		BOMService bomService = new BOMService();

		for (MaterialDetailBean mtl : list) {
			FlatBOMBean currentBOM = new FlatBOMBean();
			if (mtl.isPullAsAsm() && mtl.isViewAsAsm()) { // condition if child
															// exists
				FlatBOMBean childFlatBomBean = new FlatBOMBean();

				partNo = mtl.getMtlPartNum();
				childFlatBomBean.setQtyPer(mtl.getQtyPer());
				mtlMap.put(partNo, setFlatBomBean(mtl, childFlatBomBean));

				List<MaterialDetailBean> children = ms
						.getMaterialDetails(partNo); // retrieve child parts of
				// a child
				childMap = bomService.qtyCount(children, mtl.getQtyPer(), ms); // recursive
																				// call

				compareMap(mtlMap, childMap);

			} else { // condition if no child

				if (mtlMap.containsKey(mtl.getMtlPartNum())) { // if part exists
																// in map update
																// the part

					FlatBOMBean tempBeam = mtlMap.get(mtl.getMtlPartNum());
					float tmpCount = tempBeam.getQtyPer();
					tempBeam.setQtyPer(tmpCount + (mtl.getQtyPer() * n));
					mtlMap.put(mtl.getMtlPartNum(), tempBeam);

				} else { // if part does not exist in map just add it into the
							// parent map

					currentBOM.setQtyPer(mtl.getQtyPer() * n);
					mtlMap.put(mtl.getMtlPartNum(),
							setFlatBomBean(mtl, currentBOM));
					
				}
			}
		}
		// if failed
		return mtlMap;
	}

	private static void compareMap(Map<String, FlatBOMBean> parentMap,
			Map<String, FlatBOMBean> childMap) {

		for (String key : childMap.keySet()) {
			if (parentMap.containsKey(key)) { // if part exists in map
				FlatBOMBean Parent_BOM = parentMap.get(key);
				FlatBOMBean child_BOM = childMap.get(key);
				Parent_BOM.setQtyPer(Parent_BOM.getQtyPer()
						+ child_BOM.getQtyPer());
				parentMap.put(key, Parent_BOM);

			} else { // if part does not exist in map
				parentMap.put(key, childMap.get(key));

			}

		}
	}

	private static FlatBOMBean setFlatBomBean(MaterialDetailBean mdb,
			FlatBOMBean flatBomBean) {

		flatBomBean.setPartClass(mdb.getPartClass());
		flatBomBean.setDescription(mdb.getPartDescription());
		flatBomBean.setPartNum(mdb.getMtlPartNum());
		flatBomBean.setInvUM(mdb.getInvUM());
		flatBomBean.setPartType(mdb.getPartType());

		return flatBomBean;
	}
}
