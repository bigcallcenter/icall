package com.bigcallcenter.icall.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;

public class Pagination extends JdbcDaoSupport{
	
	public static final int NUMBERS_PER_PAGE = 10;
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
	  private List resultList;
	  //JdbcTemplate jTemplate
	  private JdbcTemplate jTemplate;
	 
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
	    StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
	    totalSQL.append(this.removeOrderBy(sql));
	    totalSQL.append(" ) totalTable ");
	    //给JdbcTemplate赋值
	    setJdbcTemplate(jTemplate);
	    //总记录数
	    setTotalRows(getJdbcTemplate().queryForObject(totalSQL.toString(), Integer.class));
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
	    setResultList(getJdbcTemplate().queryForList(getMySQLPageSQL(sql,startIndex,numPerPage)));
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

	  public List getResultList() {
	    return resultList;
	  }

	  public void setResultList(List resultList) {
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

	  private String removeOrderBy(String sql) throws JSQLParserException {
		    Statement stmt = CCJSqlParserUtil.parse(sql);
		    Select select = (Select) stmt;
		    SelectBody selectBody = select.getSelectBody();
		    processSelectBody(selectBody);
		    return select.toString();
		}

		private void processSelectBody(SelectBody selectBody) {
		    if (selectBody instanceof PlainSelect) {
		        processPlainSelect((PlainSelect) selectBody);
		    } else if (selectBody instanceof WithItem) {
		        WithItem withItem = (WithItem) selectBody;
		        if (withItem.getSelectBody() != null) {
		            processSelectBody(withItem.getSelectBody());
		        }
		    } else {
		        SetOperationList operationList = (SetOperationList) selectBody;
		        if (operationList.getPlainSelects() != null && operationList.getPlainSelects().size() > 0) {
		            List<PlainSelect> plainSelects = operationList.getPlainSelects();
		            for (PlainSelect plainSelect : plainSelects) {
		                processPlainSelect(plainSelect);
		            }
		        }
		        if (!orderByHashParameters(operationList.getOrderByElements())) {
		            operationList.setOrderByElements(null);
		        }
		    }
		}

		private void processPlainSelect(PlainSelect plainSelect) {
		    if (!orderByHashParameters(plainSelect.getOrderByElements())) {
		        plainSelect.setOrderByElements(null);
		    }
		    if (plainSelect.getFromItem() != null) {
		        processFromItem(plainSelect.getFromItem());
		    }
		    if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {
		        List<Join> joins = plainSelect.getJoins();
		        for (Join join : joins) {
		            if (join.getRightItem() != null) {
		                processFromItem(join.getRightItem());
		            }
		        }
		    }
		}

		private void processFromItem(FromItem fromItem) {
		    if (fromItem instanceof SubJoin) {
		        SubJoin subJoin = (SubJoin) fromItem;
		        if (subJoin.getJoin() != null) {
		            if (subJoin.getJoin().getRightItem() != null) {
		                processFromItem(subJoin.getJoin().getRightItem());
		            }
		        }
		        if (subJoin.getLeft() != null) {
		            processFromItem(subJoin.getLeft());
		        }
		    } else if (fromItem instanceof SubSelect) {
		        SubSelect subSelect = (SubSelect) fromItem;
		        if (subSelect.getSelectBody() != null) {
		            processSelectBody(subSelect.getSelectBody());
		        }
		    } else if (fromItem instanceof ValuesList) {

		    } else if (fromItem instanceof LateralSubSelect) {
		        LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
		        if (lateralSubSelect.getSubSelect() != null) {
		            SubSelect subSelect = (SubSelect) (lateralSubSelect.getSubSelect());
		            if (subSelect.getSelectBody() != null) {
		                processSelectBody(subSelect.getSelectBody());
		            }
		        }
		    }
		    //Table时不用处理
		}

		private boolean orderByHashParameters(List<OrderByElement> orderByElements) {
		    if (orderByElements == null) {
		        return false;
		    }
		    for (OrderByElement orderByElement : orderByElements) {
		        if (orderByElement.toString().toUpperCase().contains("?")) {
		            return true;
		        }
		    }
		    return false;
		}
}
