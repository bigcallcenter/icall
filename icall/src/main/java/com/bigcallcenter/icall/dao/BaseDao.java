package com.bigcallcenter.icall.dao;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.bigcallcenter.icall.domain.Pagination;
import com.sohu.idcenter.IdWorker;

import net.sf.jsqlparser.JSQLParserException;
@Repository
public class BaseDao<T> extends JdbcDaoSupport{
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
	
	/**
	 * 分页查询  
	 * @param currentPage  当前页 
	 * @param numPerPage   每页记录数
	 * @return 
	 */
	public Pagination queryByPage(Integer currentPage,Integer numOfPage,String sql) {  
		Pagination page=null;
		try {
			page = new Pagination(sql, currentPage, numOfPage,  getJdbcTemplate());
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;    
	}
	
	/**
	 * 分页查询  
	 * @param currentPage  当前页 
	 * @param numPerPage   每页记录数
	 * @return 
	 */
	public Pagination<T> queryByPage(Integer currentPage,Integer numOfPage,String sql,Class<T> mappedClass) {  
		Pagination<T> page=null;
		try {
			page = new Pagination<T>(sql, currentPage, numOfPage,  getJdbcTemplate(),mappedClass);
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;    
	}
	
}
