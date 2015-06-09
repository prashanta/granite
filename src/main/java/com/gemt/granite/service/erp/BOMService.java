

	
package com.gemt.granite.service.erp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemt.granite.bean.erp.MaterialDetailBean;

	;

	/**
	 * Returns information on parts.
	 * 
	 * @author Yemini.B
	 */

	
	public class BOMService {

		@Autowired
		MaterialService ms;
	

		/****
		 * qtyCount counts the total quantity of parts required to build the
		 * material
		 * 
		 * compaerMap(parentMap,childMap) updates the parentMap,
		 * if the part in childMap is found in parentMap, update the quantity and add to parentMap.  
		 * @throws Exception
		 *******/
		
		public Map<String, Integer> qtyCount(List<MaterialDetailBean> list, int n) throws Exception {
			Map<String,Integer> mtlMap = new HashMap<String,Integer>();
			Map<String, Integer> childMap=new HashMap<String,Integer>();
			
			int qty=0;
			String partNo;		
			
			BOMService recursive=new BOMService();
			
			for (MaterialDetailBean mtl : list) {
				System.out.println(">> " + mtl.getMtlPartNum());
				
				if (mtl.isPullAsAsm() && mtl.isViewAsAsm()) { // condition if child exists
					qty=mtl.getQtyPer();
				    partNo=mtl.getMtlPartNum();
					System.out.println(">> has child");

					mtlMap.put(partNo, qty);
					List<MaterialDetailBean> children =  ms.getMaterialDetails(mtl.getMtlPartNum());	// retrieve child parts of a child 
					childMap = recursive.qtyCount(children, mtl.getQtyPer());						// recursive call
					
					
				
					// test map print
					Map<String, Integer> m = childMap;
					for (Map.Entry<String, Integer> en : m.entrySet())
					{
						System.out.println(">> >> ChildMap:  " + en.getKey() + " : " + en.getValue());
					}
					compareMap(mtlMap, childMap);
					
				} else {  //condition if no child
					
					if (mtlMap.containsKey(mtl.getMtlPartNum())) { // if part exists
																	// in map
						int tmpCount = (int) mtlMap.get(mtl.getMtlPartNum());
						mtlMap.put(mtl.getMtlPartNum(), tmpCount + (mtl.getQtyPer() * n));
					} else { // if part does not exist in map
						mtlMap.put(mtl.getMtlPartNum(), mtl.getQtyPer() * n);
					}
				}
			}
			// if failed
			return mtlMap;
		}
		
		private static void compareMap( Map<String, Integer> parentMap, Map<String, Integer> childMap) 
		{
	
			for ( String key : childMap.keySet()) 
			{
				if (parentMap.containsKey(key)) { // if part exists in map
	
	
					parentMap.put(key, parentMap.get(key) + childMap.get(key));
				} else 
				{ // if part does not exist in map
					parentMap.put(key, childMap.get(key));
				}
			}
	
		}

	}



	/*
	 * @RequestMapping(value="/detail/{partnum:.+}",
	 * produces=MediaType.APPLICATION_JSON_VALUE) public List<MaterialDetailBean>
	 * getMaterailDetails(@PathVariable(value = "partnum") String partNum) throws
	 * Exception { return materialService.getMaterialDetails(partNum); }
	 */



