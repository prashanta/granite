package com.gemt.granite.dao.erp;


import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.gemt.granite.bean.erp.MaterialBean;
import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

@Repository
public class MaterialDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
//	@Autowired
//	@Qualifier("txManager1")
//	DataSourceTransactionManager txManager;

	static Logger log = Logger.getLogger(MaterialDao.class);
	
	String beanMapSQL = 
			"SELECT "+
			"PartMtl.PartNum as partNum, "+
			"PartMtl.RevisionNum as revisionNum, "+
			"PartMtl.MtlSeq as mtlSeq, "+
			"PartMtl.QtyPer as qtyPer, "+
			"PartMtl.MtlPartNum as mtlPartNum, "+
			"PartMtl.PullAsAsm as pullAsAsm, "+
			"PartMtl.ViewAsAsm as viewAsAsm ";
	/**
	 * Get part information. Retrieves information if part is active.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<MaterialBean> getMaterials(String partNum, String revNum) throws Exception{
		String sql =	
				beanMapSQL +
				"FROM pub.PartMtl where PartNum = ? AND RevisionNum = ? ";
		
		try{
			log.info("Retriving materials for part number: " + partNum);
			RowMapper<MaterialBean> rm = BeanPropertyRowMapper.newInstance(MaterialBean.class);
			List<MaterialBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);
			if(materials.isEmpty())
				throw new GraniteRestException(RestError.MATERIALS_NOT_FOUND);
			return materials;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
	}
	
//	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<MaterialDetailBean> getMaterialDetails(String partNum, String revNum) throws Exception{
		String sql =	
				  "SELECT "
				+ "PartMtl.PartNum as partNum, "		
				+ "PartMtl.MtlSeq as mtlSeq, "
				+ "PartMtl.QtyPer as qtyPer, "
				+ "PartMtl.MtlPartNum as mtlPartNum, "
				+ "PartMtl.BubbleNum as bubbleNum, "
				+ "PartMtl.PullAsAsm as pullAsAsm, "
				+ "PartMtl.ViewAsAsm as viewAsAsm, "
				
				+ "Part.PartDescription as partDescription, "
				+ "Part.Class as partClass, "
				+ "Part.IUM as invUM, "
				+ "Part.TypeCode as partType, "
				+ "Part.NonStock as nonStock, "
				+ "Part.UserChar1 as manufacturer, "
				+ "Part.UserChar2 as manufacturerNumber, "
				+ "Part.UserChar3 as project, "
				+ "Part.UserDecimal1 as estimatedCost, "
				+ "Part.MfgComment as mfgComment, "
				+ "Part.PurComment as purComment, "				
				
				+ "PartPlant.LeadTime as leadTime, "
				+ "PartPlant.ProcessMRP as processMRP, "
				+ "PartPlant.DaysOfSupply as daysOfSupply, "
				+ "PartPlant.GenerateSugg as genPOSuggestion, "
								
				+ "PartRev.RevisionNum as revisionNum, " 
				+ "PartRev.EffectiveDate as revisionEffectiveDate, "
				
				+ "Vendor.Name as approvedVendor, "
				
				+ "PlantWhse.PrimBin as primaryBin "
				
				+ "FROM pub.PartMtl "
				+ "LEFT JOIN pub.Part ON Part.PartNum = PartMtl.MtlPartNum "
				+ "LEFT JOIN pub.PartPlant ON PartPlant.PartNum = PartMtl.MtlPartNum "
				+ "LEFT JOIN pub.PartRev ON PartRev.PartNum = PartMtl.MtlPartNum AND PartRev.Approved = 1 "
				+ "LEFT JOIN pub.AprvVend ON AprvVend.PartNum = PartMtl.MtlPartNum " 
				+ "LEFT JOIN pub.Vendor ON AprvVend.VendorNum = Vendor.VendorNum "
				+ "LEFT JOIN pub.PlantWhse ON PlantWhse.PartNum = PartMtl.MtlPartNum "
				
				+ "WHERE PartMtl.PartNum = ? AND PartMtl.RevisionNum = ? "
				+ "ORDER BY PartMtl.MtlSeq ";
		
		// An attempt to change isolation level
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//	    def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);

//	    TransactionStatus status = txManager.getTransaction(def);	    
	    
		try{
			log.info("Retriving materials with detail info for part number: " + partNum);			
			
			/*System.out.println("Isolation level: " + jdbcTemplate.getDataSource().getConnection().getTransactionIsolation());
			System.out.println("TRANSACTION_NONE: " + java.sql.Connection.TRANSACTION_NONE);
			System.out.println("TRANSACTION_READ_UNCOMMITTED: " + java.sql.Connection.TRANSACTION_READ_UNCOMMITTED);
			System.out.println("TRANSACTION_READ_COMMITTED: " + java.sql.Connection.TRANSACTION_READ_COMMITTED);
			System.out.println("TRANSACTION_REPEATABLE_READ: " + java.sql.Connection.TRANSACTION_REPEATABLE_READ);
			System.out.println("TRANSACTION_SERIALIZABLE: " + java.sql.Connection.TRANSACTION_SERIALIZABLE);*/
			
			RowMapper<MaterialDetailBean> rm = BeanPropertyRowMapper.newInstance(MaterialDetailBean.class);
			List<MaterialDetailBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);			
			
			// Trying to remove duplicates
			/*Set<MaterialDetailBean> uniqueSet = new HashSet<MaterialDetailBean>(materials);
			materials.clear();
			materials.addAll(uniqueSet);
			
			Iterator<MaterialDetailBean> itr = materials.iterator();
			while(itr.hasNext()){				
				MaterialDetailBean mat = itr.next();
				System.out.println(mat.getMtlPartNum() + " -- " + mat.hashCode());
			}*/						
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
		}
	}	
}
