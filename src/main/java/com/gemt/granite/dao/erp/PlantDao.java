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

import com.gemt.granite.bean.erp.MRPRunInfo;
import com.gemt.granite.bean.erp.PartBean;
import com.gemt.granite.bean.erp.PartBinBean;
import com.gemt.granite.bean.erp.PartPlantBean;
import com.gemt.granite.bean.erp.PartRevBean;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

@Repository
public class PlantDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	static Logger log = Logger.getLogger(PlantDao.class);
			
	/**
	 * Ger MRP run information
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public MRPRunInfo getPlantMRPRunInfo(String plant) throws Exception{
		
		String sql ="SELECT " +
					"Plant.MRPLastRunDate, " +
					"Plant.MRPLastRunTime " +
					"FROM pub.Plant WHERE Plant.Plant = ? ";

		try{
			RowMapper<MRPRunInfo> rm = BeanPropertyRowMapper.newInstance(MRPRunInfo.class);
			MRPRunInfo info = jdbcTemplate.queryForObject(sql, new Object[]{plant}, rm);
			return info;
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
}
