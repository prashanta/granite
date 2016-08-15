package com.gemt.granite.dao.mfg;

import javax.sql.DataSource;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gemt.granite.bean.mfg.MTLXCUTOperBean;
import com.gemt.granite.exception.RestError;
import com.gemt.granite.exception.GraniteRestException;

/**
 * @author Prashanta.s
 *
 */
@Repository
public class MTLXWorkCellDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSource1")

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static Logger log = Logger.getLogger(MTLXWorkCellDao.class);

	/**
	 * Get CUT operation detail for given job number
	 * 
	 * @return
	 * @throws Exception
	 */
	public MTLXCUTOperBean getCutOperDetail(String jobNum)
			throws GraniteRestException {
		String sql = // Get Job details
		"SELECT "
				+ "JobOper.JobNum AS jobNum, "
				+ "JobOper.StartDate AS startDate, "
				+ "JobOper.DueDate AS dueDate, "
				+ "JobOper.RunQty as productionQty, "
				+ "JobOper.OprSeq as operSeq, "
				+ "JobOper.EstProdHours as estProdHours, "
				+
				// Get part number of first assembly sequence - 1
				"JobAsmbl.PartNum as subAsmPartNum, "
				+
				// Get material for the parent assembly part
				"PartMtl.MtlPartNum as subAsmMtlPartNum, "
				+ "PartMtl.QtyPer as subAsmMtlQty ,"
				+ "Part.IUM as subAsmMtlUOM, "
				+
				// Get first material - 10
				"JobMtl.PartNum as mtlPartNum, "
				+ "JobMtl.QtyPer as mtlQty, "
				+ "JobMtl.IUM as mtlUOM "
				+

				"FROM pub.JobOper "
				+ "LEFT JOIN pub.JobAsmbl ON JobAsmbl.JobNum = JobOper.JobNum AND JobAsmbl.AssemblySeq = 1 "
				+ "LEFT JOIN pub.PartMtl ON PartMtl.PartNum = JobAsmbl.PartNum AND PartMtl.MtlSeq = 10 "
				+ "LEFT JOIN pub.Part ON Part.PartNum = PartMtl.PartNum "
				+ "LEFT JOIN pub.JobMtl on JobMtl.JobNum = JobOper.JobNum AND JobMtl.AssemblySeq = 0 "
				+ "WHERE JobOper.OpCode = 'CUT' AND "
				+ "JobOper.ProdComplete = 0 AND JobOper.JobComplete = 0  AND JobOper.JobNum = ?"
				+ "Order by JobOper.StartDate ";
		try {
			log.info("Fetching CUT operations from MTLX workcell for job - "
					+ jobNum);
			RowMapper<MTLXCUTOperBean> rm = BeanPropertyRowMapper
					.newInstance(MTLXCUTOperBean.class);
			MTLXCUTOperBean operation = null;
			try {
				operation = jdbcTemplate.queryForObject(sql,
						new Object[] { jobNum }, rm);
			} catch (Exception e) {
				// e.printStackTrace();
				return null;
			}
			return operation;
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		} catch (BadSqlGrammarException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.BAD_SQL_ERROR, ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}

	/**
	 * Get details for all incomplete STANDARD CUT operation from MTLX work cell
	 * for all open Firm Jobs
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MTLXCUTOperBean> getMTLXCutOperations()
			throws GraniteRestException {
		String sql = // Get Job details
		"SELECT "
				+ "JobOper.JobNum AS jobNum, "
				+ "JobOper.StartDate AS startDate, "
				+ "JobOper.DueDate AS dueDate, "
				+ "JobOper.RunQty as productionQty, "
				+ "JobOper.OprSeq as operSeq, "
				+
				// Get part number of first assembly sequence - SUBASM 1
				"JobAsmbl.PartNum as subAsmPartNum, "
				+
				// Get material for the parent assembly part
				"JobMtl.PartNum as subAsmMtlPartNum "
				+ "FROM pub.JobOper "
				+ "JOIN pub.JobAsmbl ON JobAsmbl.AssemblySeq = 1 AND JobOper.JobNum = JobAsmbl.JobNum "
				+ "LEFT JOIN pub.JobMtl ON JobMtl.JobNum = JobOper.JobNum AND JobMtl.AssemblySeq = 1 AND  JobMtl.MtlSeq = 10 "
				+ "WHERE JobOper.WCCode = 'MTLX' AND JobOper.OpCode = 'CUT' AND JobOper.ProdComplete = 0 AND JobOper.JobComplete = 0 "
				+
				// Get FIRM JOBS only
				"AND JobOper.JobNum NOT LIKE 'amrp%' "
				+ "Order by JobOper.DueDate ";
		try {
			RowMapper<MTLXCUTOperBean> rm = BeanPropertyRowMapper
					.newInstance(MTLXCUTOperBean.class);
			List<MTLXCUTOperBean> operations = jdbcTemplate.query(sql, rm);
			log.info("MTLX CUT Standard Operations Fetched: "
					+ operations.size());
			return operations;
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		} catch (BadSqlGrammarException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.BAD_SQL_ERROR, ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}

	/**
	 * Get details for all incomplete NON-STANDARD CUT operation from MTLX work
	 * cell for all open FIRM JOBS
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MTLXCUTOperBean> getMTLNCutOperations()
			throws GraniteRestException {
		String sql = // Get Job details
		"SELECT "
				+ "JobOper.JobNum AS jobNum, "
				+ "JobOper.StartDate AS startDate, "
				+ "JobOper.DueDate AS dueDate, "
				+ "JobOper.RunQty as productionQty, "
				+ "JobOper.OprSeq as operSeq, "
				+
				// Get first material - 10
				"JobMtl.PartNum as mtlPartNum, "
				+ "JobMtl.QtyPer as mtlQty, "
				+ "JobMtl.IUM as mtlUOM "
				+

				"FROM pub.JobOper "
				+ "JOIN pub.JobMtl on JobMtl.JobNum = JobOper.JobNum AND JobMtl.AssemblySeq = 0 AND JobMtl.MtlSeq = 10 "
				+ "WHERE JobOper.WCCode = 'MTLN' AND JobOper.OpCode = 'CUT' AND JobOper.ProdComplete = 0 AND JobOper.JobComplete = 0 "
				+
				// Get FIRM JOBS only
				"AND JobOper.JobNum NOT LIKE 'amrp%' "
				+ "ORDER BY JobOper.DueDate ";
		try {
			RowMapper<MTLXCUTOperBean> rm = BeanPropertyRowMapper
					.newInstance(MTLXCUTOperBean.class);
			List<MTLXCUTOperBean> operations = jdbcTemplate.query(sql, rm);
			log.info("MTLX CUT Non-Standard Operations Fetched: "
					+ operations.size());
			return operations;
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		} catch (BadSqlGrammarException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.BAD_SQL_ERROR, ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}
}
