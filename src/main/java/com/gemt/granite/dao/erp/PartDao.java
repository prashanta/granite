package com.gemt.granite.dao.erp;


import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gemt.granite.bean.erp.PartBean;
import com.gemt.granite.bean.erp.PartBinBean;
import com.gemt.granite.bean.erp.PartPlantBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

@Repository
public class PartDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	static Logger log = Logger.getLogger(PartDao.class);
	
	String beanMapSQL = "Part.PartNum as partNum, "
					+ 	"Part.PartDescription as partDescription, "
					+ 	"Part.Class as partClass, "
					+ 	"Part.IUM as invUM, "
					+ 	"Part.TypeCode as partType, "
					+ 	"Part.NonStock as nonStock, "
					+ 	"Part.InActive as inactive ";
		
	/**
	 * Get part information. Retrieves information if part is active.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public PartBean getPartInfo(String partNum) throws Exception{
		String sql =	"SELECT "
					+ 	beanMapSQL
					+ 	"FROM pub.Part WHERE Part.InActive = 0 AND Part.PartNum = ? ";

		try{
			RowMapper<PartBean> rm = BeanPropertyRowMapper.newInstance(PartBean.class);
			PartBean part = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, rm);
			return part;
		}
		catch(EmptyResultDataAccessException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.PART_NONEXISTENT, ex);
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}
	
	/**
	 * Search for parts by part number and if active.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<PartBean> searchPart(String partNum) throws Exception{
		String sql =	"SELECT "
				+ 	beanMapSQL
				+ 	"FROM pub.Part WHERE Part.InActive = 0 AND Part.PartNum LIKE ?";
		
		try{
			RowMapper<PartBean> rm = BeanPropertyRowMapper.newInstance(PartBean.class);
			List<PartBean> parts = jdbcTemplate.query(sql, new Object[]{"%"+partNum+"%"}, rm);
			if(parts.isEmpty())
				throw new GraniteRestException(RestError.PARTS_NOT_FOUND);
			return parts;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
	/**
	 * Search for parts by part description and if active. 
	 * 
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public List<PartBean> searchPartByDescription(String description) throws Exception{
		String sql =	"SELECT "
				+ 	beanMapSQL
				+ 	"FROM pub.Part WHERE Part.InActive = 0 AND Part.partDescription LIKE ?";		
		try{
			RowMapper<PartBean> rm = BeanPropertyRowMapper.newInstance(PartBean.class);
			List<PartBean> parts = jdbcTemplate.query(sql, new Object[]{"%"+description+"%"}, rm);
			if(parts.isEmpty())
				throw new GraniteRestException(RestError.PARTS_NOT_FOUND);
			return parts;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
	/**
	 * Get parts by part class and if active. 
	 * 
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public List<PartBean> getPartsByClass(String partClass) throws Exception{
		String sql =	"SELECT "
				+ 	beanMapSQL
				+ 	"FROM pub.Part WHERE Part.InActive = 0 AND Part.Class = ?";
		
		try{			
			RowMapper<PartBean> rm = BeanPropertyRowMapper.newInstance(PartBean.class);
			List<PartBean> parts = jdbcTemplate.query(sql, new Object[]{partClass}, rm);
			if(parts.isEmpty())
				throw new GraniteRestException(RestError.PARTS_NOT_FOUND);
			return parts;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
	/**
	 * Get Part revision information for given part number.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<PartRevBean> getPartRevisions(String partNum) throws Exception{
		log.info("Getting Part revision for :[" + partNum+ "] ");
		String sql = 	"SELECT "				
				+ 		"PartRev.RevisionNum as revisionNum, "
				+ 		"PartRev.Approved as approved, "
				+ 		"PartRev.ApprovedDate as approvedDate, "
				+ 		"PartRev.ApprovedBy as approvedBy, "
				+ 		"PartRev.EffectiveDate as effectiveDate "
				+ 		"FROM pub.PartRev WHERE PartRev.PartNum = ? ";
		try{			
			RowMapper<PartRevBean> rm = BeanPropertyRowMapper.newInstance(PartRevBean.class);
			List<PartRevBean> parts = jdbcTemplate.query(sql, new Object[]{partNum}, rm);
			if(parts.isEmpty())
				throw new GraniteRestException(RestError.PARTS_NOT_FOUND);				
			return parts;
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
	/**
	 * Returns approved revision.
	 *  
	 * @param partNum
	 * @return
	 * @throws Exception
	 * 
	 */
	public PartRevBean getApprovedRevision(String partNum) throws Exception{		
		String sql = 	"SELECT "				
				+ 		"PartRev.RevisionNum as revisionNum, "
				+ 		"PartRev.Approved as approved, "
				+ 		"PartRev.ApprovedDate as approvedDate, "
				+ 		"PartRev.ApprovedBy as approvedBy, "
				+ 		"PartRev.EffectiveDate as effectiveDate "
				+ 		"FROM pub.PartRev WHERE PartRev.PartNum = ? AND PartRev.Approved = 1";
		try{
			RowMapper<PartRevBean> rm = BeanPropertyRowMapper.newInstance(PartRevBean.class);
			PartRevBean rev = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, rm);
			log.info("Approved revision found for part number: " + partNum + " -- Revison: " + rev.getRevisionNum());
			return rev;
		}
		catch(EmptyResultDataAccessException ex){			
			ex.printStackTrace();
			throw new GraniteRestException(RestError.PART_REVISION_NOT_FOUND, ex);
		}
		catch(IncorrectResultSizeDataAccessException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.PART_REVISION_APPROVED_EXCESS, ex);
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		}		
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}
	
	/**
	 * Get part bins for given part number.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<PartBinBean> getPartBin(String partNum) throws Exception{
		String sql = 	"SELECT "				
				+ 		"PartBin.BinNum as binNum, "
				+ 		"PartBin.OnhandQty as onhandQty "
				+ 		"FROM pub.PartBin WHERE PartBin.PartNum = ? ";
		try{
			RowMapper<PartBinBean> rm = BeanPropertyRowMapper.newInstance(PartBinBean.class);
			List<PartBinBean> parts = jdbcTemplate.query(sql, new Object[]{partNum}, rm);
			if(parts.isEmpty())
				throw new GraniteRestException(RestError.PART_BIN_NOT_FOUND);
			return parts;
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(GraniteRestException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
	public PartPlantBean getPartPlantInfo(String partNum) throws Exception{
		String sql = 	"SELECT "				
				+ 		"PartPlant.PartNum as partNum, "
				+ 		"PartPlant.LeadTime as leadTime, "
				+ 		"PartPlant.ProcessMRP as processMRP, "
				+ 		"PartPlant.DaysOfSupply as daysOfSupply "
				+ 		"FROM pub.PartPlant WHERE PartPlant.PartNum = ? ";
		try{			
			RowMapper<PartPlantBean> rm = BeanPropertyRowMapper.newInstance(PartPlantBean.class);
			PartPlantBean part = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, rm);
			return part;
		}
		catch(EmptyResultDataAccessException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.PART_PLANT_INFO_NOT_FOUND, ex.getMessage());
		}
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex.getMessage());
		}
	}
	
}
