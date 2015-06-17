package com.gemt.granite.dev.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gemt.granite.bean.erp.MaterialBean;

@Component
public class TestDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("dataSource2")
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }
	
	public void insert(){
		String sql = "INSERT INTO persons (lastname, firstname) VALUES ('first', 'last');";
		
		jdbcTemplate.update(sql);
		System.out.println("sucess!!!!");
	}

}
