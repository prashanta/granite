package com.gemt.granite.dev.dao.erp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.dev.bean.erp.MaterialInfoBean;
import com.gemt.granite.dev.dao.erp.TestMaterialDao;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

import org.apache.log4j.Logger;


@Repository
public class TestMaterialDao {
	
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	static Logger log = Logger.getLogger(TestMaterialDao.class);
	
	public List<MaterialInfoBean> getMaterialInfo(String partNum, String revNum) throws Exception{
		String sql =	
				  "SELECT "
				+ "PartMtl.PartNum as partNum, "		
				+ "PartMtl.QtyPer as qtyPer, "
				+ "PartMtl.MtlPartNum as mtlPartNum, "
				+ "PartMtl.PullAsAsm as pullAsAsm, "
				+ "PartMtl.ViewAsAsm as viewAsAsm, "
				+ "PartMtl.MtlPartNum as revisionNum, "
				
				+ "Part.PartDescription as partDescription, "
				+ "Part.Class as partClass, "
				+ "Part.IUM as invUM, "
				+ "Part.TypeCode as partType, "
								
				+ "FROM pub.PartMtl "
				+ "LEFT JOIN pub.Part ON Part.PartNum = PartMtl.MtlPartNum "
				
				+ "WHERE PartMtl.PartNum = ? AND PartMtl.RevisionNum = ? "
				+ "ORDER BY PartMtl.MtlSeq ";
		
		// An attempt to change isolation level
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//	    def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);

//	    TransactionStatus status = txManager.getTransaction(def);	    
	    
/*		try{
			log.info("Retriving materials with detail info for part number: " + partNum);			
			
			System.out.println("Isolation level: " + jdbcTemplate.getDataSource().getConnection().getTransactionIsolation());
			System.out.println("TRANSACTION_NONE: " + java.sql.Connection.TRANSACTION_NONE);
			System.out.println("TRANSACTION_READ_UNCOMMITTED: " + java.sql.Connection.TRANSACTION_READ_UNCOMMITTED);
			System.out.println("TRANSACTION_READ_COMMITTED: " + java.sql.Connection.TRANSACTION_READ_COMMITTED);
			System.out.println("TRANSACTION_REPEATABLE_READ: " + java.sql.Connection.TRANSACTION_REPEATABLE_READ);
			System.out.println("TRANSACTION_SERIALIZABLE: " + java.sql.Connection.TRANSACTION_SERIALIZABLE);
			
			RowMapper<MaterialDetailBean> rm = BeanPropertyRowMapper.newInstance(MaterialDetailBean.class);
			List<MaterialDetailBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);			
			
			// Trying to remove duplicates
			Set<MaterialDetailBean> uniqueSet = new HashSet<MaterialDetailBean>(materials);
			materials.clear();
			materials.addAll(uniqueSet);
			
			Iterator<MaterialDetailBean> itr = materials.iterator();
			while(itr.hasNext()){				
				MaterialDetailBean mat = itr.next();
				System.out.println(mat.getMtlPartNum() + " -- " + mat.hashCode());
			}						
			if(materials.isEmpty())
				throw new GraniteRestException(RestError.MATERIALS_NOT_FOUND);
			return materials;
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		}		
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}*/
		return null;
	}
	
	
}
