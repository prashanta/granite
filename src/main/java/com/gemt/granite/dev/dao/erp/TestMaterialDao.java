package com.gemt.granite.dev.dao.erp;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gemt.granite.dev.bean.erp.MaterialInfoBean;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

@Repository
public class TestMaterialDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSource1")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static Logger log = Logger.getLogger(TestMaterialDao.class);

	public List<MaterialInfoBean> getMaterialInfo(String partNum, String revNum)
			throws Exception {

		String sql = "SELECT "
				+ "PartMtl.PartNum as partNum, "
				+ "PartMtl.MtlSeq as mtlSeq, "
				+ "PartMtl.QtyPer as qtyPer, "
				+ "PartMtl.MtlPartNum as mtlPartNum, "
				+ "PartMtl.PullAsAsm as pullAsAsm, "
				+ "PartMtl.ViewAsAsm as viewAsAsm, "

				+ "Part.PartDescription as partDescription, "
				+ "Part.Class as partClass, "
				+ "Part.IUM as invUM, "
				+ "Part.TypeCode as partType, "

				+ "PartRev.RevisionNum as revisionNum, "
				+ "PartRev.EffectiveDate as revisionEffectiveDate "

				+ "FROM pub.PartMtl "
				+ "LEFT JOIN pub.Part ON Part.PartNum = PartMtl.MtlPartNum "
				+ "LEFT JOIN pub.PartRev ON PartRev.PartNum = PartMtl.MtlPartNum AND PartRev.Approved = 1 "
			
				+ "WHERE PartMtl.PartNum = ? AND PartMtl.RevisionNum = ? "
				+ "ORDER BY PartMtl.MtlSeq ";

		try {
			log.info("Retriving materials info for part number: "
					+ partNum);

			RowMapper<MaterialInfoBean> rm = BeanPropertyRowMapper
					.newInstance(MaterialInfoBean.class);
			List<MaterialInfoBean> materials = jdbcTemplate.query(sql,
					new Object[] { partNum, revNum }, rm);

			if (materials.isEmpty())
				throw new GraniteRestException(RestError.MATERIALS_NOT_FOUND);
			return materials;
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
			throw new GraniteRestException(RestError.NO_DATABASE_CONNECTION, ex);
		} catch (GraniteRestException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GraniteRestException(RestError.APP_SERVER_ERROR, ex);
		}
	}

}
