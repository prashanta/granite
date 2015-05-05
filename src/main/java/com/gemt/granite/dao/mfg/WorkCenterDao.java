package com.gemt.granite.dao.mfg;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

import com.gemt.granite.bean.mfg.WorkCenterBean;
import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.GraniteRestException;

/**
 * @author Prashanta.s
 *
 */
@Transactional(isolation=Isolation.READ_UNCOMMITTED)
@Repository
public class WorkCenterDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	static Logger log = Logger.getLogger(WorkCenterDao.class);
	
	String beanMapSQL = 
			"SELECT " + 
			"WrkCenter.WCCode as workCenterCode, " +
			"WrkCenter.Description as description, " +
			"WrkCenter.NumberOfMachines as numberOfMachines, " +
			"WrkCenter.OpCode as opCode, " +
			"OpMaster.OpDesc as opDescription, " +
			"WrkCenter.HoursPerMachine as hoursPerMachine, " +
			"WrkCenter.ProdBurRate as prodBurRate, " +
			"WrkCenter.SetupBurRate as setupBurRate " +
			"FROM pub.WrkCenter " +
			"LEFT JOIN pub.OpMaster ON OpMaster.OpCode = WrkCenter.OpCode ";		
	
	/**
	 * Return all work centers.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WorkCenterBean> getWorkCenters() throws Exception{
		String sql = beanMapSQL;

		try{
			RowMapper<WorkCenterBean> rm = BeanPropertyRowMapper.newInstance(WorkCenterBean.class);
			List<WorkCenterBean> workCenters = jdbcTemplate.query(sql, rm);
			if(workCenters.isEmpty())
				throw new GraniteRestException(RestError.WORK_CENTERS_NOT_FOUND);
			return workCenters;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}
	
	
	/**
	 * Get work center information by work center code.
	 * 
	 * @param workCenterCode
	 * @return
	 * @throws Exception
	 */
	public WorkCenterBean getWorkCenter(String workCenterCode) throws Exception{
		String sql =
				beanMapSQL +
				"WHERE WrkCenter.WcCode = ?";		
		try{
			RowMapper<WorkCenterBean> rm = BeanPropertyRowMapper.newInstance(WorkCenterBean.class);
			WorkCenterBean workCenter = jdbcTemplate.queryForObject(sql, new Object[]{workCenterCode}, rm);			
			return workCenter;
		}		
		catch(EmptyResultDataAccessException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.WORK_CENTER_NONEXISTENT, ex);
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
	 * Get next work center for given Job and Operation Sequence
	 * @param jobNum
	 * @param operSeq
	 * @return
	 * @throws GraniteRestException
	 */
	public String getNextWorkCell(String jobNum, int operSeq) throws GraniteRestException{
		String nextWC = null;
		String sql =	
						"SELECT " +
						"JobOper.WCCode as wcCode " + 
						"FROM pub.JobOper " + 
						"WHERE JobOper.JobNum = ? AND JobOper.OprSeq > ? " +
						"ORDER BY JobOper.OprSeq";
		try{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, jobNum);
			ps.setInt(2, operSeq);
			ResultSet rs = ps.executeQuery();			
			if(rs.next()){
				nextWC = rs.getString(1);				
			}
			rs.close();			
			con.close();
			return nextWC;
		}		
		catch(Exception ex){
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}
}
