package com.gemt.granite.dao.erp;


import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

import com.gemt.granite.bean.erp.MaterialBean;
import com.gemt.granite.bean.erp.MaterialDetailBean;
import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.RestException;

@Transactional(isolation=Isolation.READ_UNCOMMITTED)
@Repository
public class MaterialDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
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
				throw new RestException(RestError.MATERIALS_NOT_FOUND);
			return materials;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new RestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
	}
	
	public List<MaterialDetailBean> getMaterialDetails(String partNum, String revNum) throws Exception{
		String sql =	
				  "SELECT "
				+ "PartMtl.PartNum as partNum, "
				+ "PartMtl.RevisionNum as revisionNum, "
				+ "PartMtl.MtlSeq as mtlSeq, "
				+ "PartMtl.QtyPer as qtyPer, "
				+ "PartMtl.MtlPartNum as mtlPartNum, "
				+ "PartMtl.PullAsAsm as pullAsAsm, "
				+ "PartMtl.ViewAsAsm as viewAsAsm, "
				
				+ "Part.PartDescription as partDescription, "
				+ "Part.Class as partClass, "
				+ "Part.IUM as invUM, "
				+ "Part.TypeCode as partType, "
				+ "Part.NonStock as nonStock, "
				
				+ "PartPlant.LeadTime as leadTime, "
				+ "PartPlant.ProcessMRP as processMRP, "
				+ "PartPlant.DaysOfSupply as daysOfSupply "
				
				+ "FROM pub.PartMtl "
				+ "LEFT JOIN pub.Part ON Part.PartNum = PartMtl.MtlPartNum "
				+ "LEFT JOIN pub.PartPlant ON PartPlant.PartNum = PartMtl.MtlPartNum "
				+ "WHERE PartMtl.PartNum = ? AND PartMtl.RevisionNum = ? ";
								
		try{
			log.info("Retriving materials with detail info for part number: " + partNum);
			RowMapper<MaterialDetailBean> rm = BeanPropertyRowMapper.newInstance(MaterialDetailBean.class);
			List<MaterialDetailBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);
			if(materials.isEmpty())
				throw new RestException(RestError.MATERIALS_NOT_FOUND);
			return materials;
		}	
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new RestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}		
		catch(RestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new RestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}	
}
