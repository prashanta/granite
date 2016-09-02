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
import org.springframework.stereotype.Repository;

import com.gemt.granite.bean.erp.PurchaseOrderBean;
import com.gemt.granite.exception.GraniteRestException;
import com.gemt.granite.exception.RestError;

@Repository
public class PurchaseOrderDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	static Logger log = Logger.getLogger(PurchaseOrderDao.class);

	/**
	 * Get part information. Retrieves information if part is active.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseOrderBean> getListOfPOs(String partNum) throws Exception{
		String sql ="SELECT "+
					"PODetail.PONum, "+
					"PODetail.POLine, "+
					"PODetail.OrderQty, "+
					"POHeader.OrderDate, "+
					"POHeader.VendorNum, "+
					"Vendor.Name as vendorName, "+ 
					"(Vendor.Address1+' '+ Vendor.Address2 + ' ' + Vendor.Address3) As vendorAddress, " +
					"Vendor.City as vendorcity, " +
					"Vendor.Country as vendorCountry "+
					"FROM pub.PODetail "+
					"LEFT JOIN pub.POHeader ON POHeader.PONum = PODetail.PONum "+
					"LEFT JOIN pub.Vendor ON POHeader.VendorNum = Vendor.VendorNum "+
					"WHERE PODetail.partNum = ?";

		try{
			RowMapper<PurchaseOrderBean> rm = BeanPropertyRowMapper.newInstance(PurchaseOrderBean.class);
			List<PurchaseOrderBean> pos = jdbcTemplate.query(sql, new Object[]{partNum}, rm);
			if(pos.isEmpty())
				throw new GraniteRestException(RestError.PO_SUPPLY_NOT_FOUND);
			return pos;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new Exception("NO_DATABASE_CONNECTION" + ex.getMessage());
		}	
	}
}
