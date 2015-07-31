package com.gemt.granite.dao.mfg;


import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

import com.gemt.granite.bean.mfg.JobOperationBean;
import com.gemt.granite.bean.mfg.OperationLaborBean;
import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.GraniteRestException;

/**
 * @author Prashanta.s
 *
 */
@Transactional(isolation=Isolation.READ_UNCOMMITTED)
@Repository
public class JobOperationDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	static Logger log = Logger.getLogger(JobOperationDao.class);		
	
	/**
	 * Return all job operations for given work center and operation code.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<JobOperationBean> getJobOperations(String wcCode, String opCode) throws Exception{
		String sql =
				"SELECT " + 
				"JobOper.JobNum as jobNum, " +
				"JobOper.OprSeq as oprSeq, " +
				"JobOper.AssemblySeq as assemblySeq, " +
				"JobOper.WCCode as wcCode, " +
				"JobOper.OpCode as opCode, " +
				"JobOper.JobComplete as jobComplete, " +
				"JobOper.ProdComplete as prodComplete, " +
				"JobOper.RunQty as runQty, " +
				"JobOper.QtyCompleted as qtyComplete, " +
				"JobOper.StartDate as startDate, " +
				"JobOper.DueDate as dueDate " +
				"FROM pub.JobOper "+
				"WHERE " +
				"JobOper.WCCode = ? AND " + 
				"JobOper.OpCode = ? AND " +
				"JobOper.ProdComplete = 0 AND " + 
				"JobOper.JobComplete = 0 " + 
				"ORDER BY JobOper.StartDate ";
		try{
			RowMapper<JobOperationBean> rm = BeanPropertyRowMapper.newInstance(JobOperationBean.class);
			List<JobOperationBean> jobOperations = jdbcTemplate.query(sql, new Object[]{wcCode, opCode}, rm);		
			if(jobOperations.isEmpty())
				throw new GraniteRestException(RestError.JOB_OPS_NOT_FOUND);
			return jobOperations;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		}
	}
	
	/**
	 * Return active operation labor detail for given work center and operation code.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<OperationLaborBean> getActiveOperationLabors(String wcCode, String opCode) throws Exception{
		String sql =
				"SELECT " +				
				"LaborDtl.JobNum as jobNum, " +
				"LaborDtl.WCCode as wcCode, " +
				"LaborDtl.OpCode as opCode, " +
				"LaborDtl.ClockinTime as clockIntime, " +
				"LaborDtl.ClockInDate as clockInDate, " +
				"LaborDtl.ClockOutTime as clockOutTime, " +
				"LaborDtl.PayrollDate as clockOutDate, " +
				"LaborDtl.OpComplete as opComplete, " +
				"EmpBasic.Name as employeeName " +
				"FROM pub.LaborDtl " +
				"LEFT JOIN pub.EmpBasic ON EmpBasic.EmpID = LaborDtl.EmployeeNum " +
				"WHERE " +
				"LaborDtl.WCCode = ? AND " +
				"LaborDtl.OpCode = ? AND " +
				"LaborDtl.ActiveTrans = 1 ";		
		try{
			RowMapper<OperationLaborBean> rm = BeanPropertyRowMapper.newInstance(OperationLaborBean.class);
			List<OperationLaborBean> activeOperations = jdbcTemplate.query(sql, new Object[]{wcCode, opCode}, rm);	
			return activeOperations;
		}	
		catch(EmptyResultDataAccessException ex){
			ex.printStackTrace();
			throw new GraniteRestException(RestError.JOB_OPS_NOT_FOUND, ex);
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
