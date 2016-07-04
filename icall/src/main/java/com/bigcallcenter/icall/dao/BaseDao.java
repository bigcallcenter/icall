package com.bigcallcenter.icall.dao;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.sohu.idcenter.IdWorker;
@Repository
public class BaseDao extends JdbcDaoSupport{
	@Resource(name = "dataSource")
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	@Autowired
	private IdWorker idGenerater; 
	
	/**
	 * 返回一个id
	 * @return
	 */
	protected long getId()
	{
		return idGenerater.getId();
	}
	
	
	
	
	
}
