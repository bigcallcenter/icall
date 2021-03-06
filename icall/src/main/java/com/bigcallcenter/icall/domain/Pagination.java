package com.bigcallcenter.icall.domain;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.github.pagehelper.parser.impl.MysqlParser;

public class Pagination<T> extends JdbcDaoSupport{
	
	public static final int NUMBERS_PER_PAGE = 10;
	/** The class we are mapping to */
	private Class<T> mappedClass;
	 //一页显示的记录数
	  private int numPerPage; 
	 //记录总数
	  private int totalRows; 
	 //总页数
	  private int totalPages; 
	 //当前页码
	  private int currentPage; 
	  //起始行数
	  private int startIndex;
	  //结束行数
	  private int lastIndex;
	  //结果集存放List
	  private List<T> resultList;
	//结果集存放List
	  private List<Map<String, Object>> resultList2;
	  
	  
	  public List<Map<String, Object>> getResultList2() {
		return resultList2;
	}

	public void setResultList2(List<Map<String, Object>> resultList2) {
		this.resultList2 = resultList2;
	}

	//JdbcTemplate jTemplate
	  private JdbcTemplate jTemplate;
	  
	  private  MysqlParser sqlParser = new MysqlParser();
	 
	  /**
	   * 每页显示10条记录的构造函数,使用该函数必须先给Pagination设置currentPage，jTemplate初值
	   * @param sql oracle语句
	 * @throws JSQLParserException 
	   */
	  public Pagination(String sql) throws JSQLParserException{
	    if(jTemplate == null){
	      throw new IllegalArgumentException("分页对象的jTemplate is null,please initial it first. ");
	    }else if(sql.equals("")){
	      throw new IllegalArgumentException("分页对象的sql is empty,please initial it first. ");
	    }
	    new Pagination(sql,currentPage,NUMBERS_PER_PAGE,jTemplate);
	  }
	  
	  /**分页构造函数
	   * @param sql 根据传入的sql语句得到一些基本分页信息
	   * @param currentPage 当前页
	   * @param numPerPage 每页记录数
	   * @param jTemplate JdbcTemplate实例
	 * @throws JSQLParserException 
	   */
	  public Pagination(String sql,int currentPage,int numPerPage,JdbcTemplate jTemplate) throws JSQLParserException{
	    if(jTemplate == null){
	      throw new IllegalArgumentException("com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
	    }else if(sql == null || sql.equals("")){
	      throw new IllegalArgumentException("com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
	    }
	    //设置每页显示记录数
	    setNumPerPage(numPerPage);
	    //设置要显示的页数
	    setCurrentPage(currentPage);
	    //计算总记录数
	    //给JdbcTemplate赋值
	    setJdbcTemplate(jTemplate);
	    //总记录数
	    setTotalRows(getJdbcTemplate().queryForObject(sqlParser.getCountSql(sql), Integer.class));
	    //计算总页数
	    setTotalPages();
	    //计算起始行数
	    setStartIndex();
	    //计算结束行数
	    setLastIndex();
	    System.out.println("lastIndex="+lastIndex);//////////////////
	    
	    //构造oracle数据库的分页语句
	   /** StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
	    paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
	    paginationSQL.append(sql);
	    paginationSQL.append(" ) temp where ROWNUM <= " + lastIndex);
	    paginationSQL.append(" ) WHERE num > " + startIndex); 
	     */ 
	    
	    
	    //装入结果集
	    setResultList2(getJdbcTemplate().queryForList(getMySQLPageSQL(sql,startIndex,numPerPage)));
	  }
	  
	  /**分页构造函数
	   * @param sql 根据传入的sql语句得到一些基本分页信息
	   * @param currentPage 当前页
	   * @param numPerPage 每页记录数
	   * @param jTemplate JdbcTemplate实例
	 * @throws JSQLParserException 
	   */
	  public Pagination(String sql,int currentPage,int numPerPage,JdbcTemplate jTemplate,Class<T> mappedClass) throws JSQLParserException{
		  this.mappedClass=mappedClass;
	    if(jTemplate == null){
	      throw new IllegalArgumentException("com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
	    }else if(sql == null || sql.equals("")){
	      throw new IllegalArgumentException("com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
	    }
	    //设置每页显示记录数
	    setNumPerPage(numPerPage);
	    //设置要显示的页数
	    setCurrentPage(currentPage);
	    //计算总记录数
	    //给JdbcTemplate赋值
	    setJdbcTemplate(jTemplate);
	    //总记录数
	    setTotalRows(getJdbcTemplate().queryForObject(sqlParser.getCountSql(sql), Integer.class));
	    //计算总页数
	    setTotalPages();
	    //计算起始行数
	    setStartIndex();
	    //计算结束行数
	    setLastIndex();
	    System.out.println("lastIndex="+lastIndex);//////////////////
	    
	    //构造oracle数据库的分页语句
	   /** StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
	    paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
	    paginationSQL.append(sql);
	    paginationSQL.append(" ) temp where ROWNUM <= " + lastIndex);
	    paginationSQL.append(" ) WHERE num > " + startIndex); 
	     */ 
	    
	    
	    //装入结果集
	    setResultList(getJdbcTemplate().query(getMySQLPageSQL(sql,startIndex,numPerPage),new BeanPropertyRowMapper(mappedClass)));
	  }
	  
	   
	  
	    /**
		 * 构造MySQL数据分页SQL 
		 * @param queryString
		 * @param startIndex
		 * @param pageSize
		 * @return
		 */
		public String getMySQLPageSQL(String queryString, Integer startIndex, Integer pageSize)
		{
			String result = "";
			if (null != startIndex && null != pageSize)
			{
				result = queryString + " limit " + startIndex + "," + pageSize;
			} else if (null != startIndex && null == pageSize)
			{
				result = queryString + " limit " + startIndex;
			} else
			{
				result = queryString;
			}
			return result;
		}
		
		
	 
	  public int getCurrentPage() {
	    return currentPage;
	  } 

	  public void setCurrentPage(int currentPage) {
	    this.currentPage = currentPage;
	  }

	  public int getNumPerPage() {
	    return numPerPage;
	  }

	  public void setNumPerPage(int numPerPage) {
	    this.numPerPage = numPerPage;
	  }

	  public List<T> getResultList() {
	    return resultList;
	  }

	  public void setResultList(List<T> resultList) {
	    this.resultList = resultList;
	  }
	  
	  
	  

	  public int getTotalPages() {
	    return totalPages;
	  }
	 //计算总页数
	  public void setTotalPages() {
	    if(totalRows % numPerPage == 0){
	      this.totalPages = totalRows / numPerPage;
	    }else{
	      this.totalPages = (totalRows / numPerPage) + 1;
	    }
	  }

	  public int getTotalRows() {
	    return totalRows;
	  }

	  public void setTotalRows(int totalRows) {
	    this.totalRows = totalRows;
	  }

	  public int getStartIndex() {
	    return startIndex;
	  }

	  public void setStartIndex() {
	    this.startIndex = (currentPage - 1) * numPerPage;
	  }

	  public int getLastIndex() {
	    return lastIndex;
	  }

	  public JdbcTemplate getJTemplate() {
	    return jTemplate;
	  }

	  public void setJTemplate(JdbcTemplate template) {
	    jTemplate = template;
	  }
	   
	 //计算结束时候的索引
	  public void setLastIndex() {
	    System.out.println("totalRows="+totalRows);///////////
	    System.out.println("numPerPage="+numPerPage);///////////
	    if( totalRows < numPerPage){
	      this.lastIndex = totalRows;
	    }else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){
	      this.lastIndex = currentPage * numPerPage;
	    }else if(totalRows % numPerPage != 0 && currentPage == totalPages){//最后一页
	      this.lastIndex = totalRows ;
	    }
	  }
}
