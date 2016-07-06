package com.bigcallcenter.icall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcallcenter.icall.dao.ProductDao;
import com.bigcallcenter.icall.domain.Goods;
import com.bigcallcenter.icall.domain.Pagination;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	/**
	 * 按照商品名称、状态、品牌查询goods
	 * @param brandId
	 * @param status
	 * @param goodsName
	 * @return
	 */
	public Pagination<Goods> queryGoods(String brandId,String status,String goodsName,int currentPage)
	{
		boolean hasWhere=false;
		StringBuilder sb = new StringBuilder("select * from goods ");
		if(!(brandId == null || "".equals(brandId))) {  
			hasWhere=appendWhereIfNeed(sb,hasWhere);
			sb.append("brand_id = '").append(brandId).append(" ");  
        } 
		if(!(status == null || "".equals(status))) {  
			hasWhere=appendWhereIfNeed(sb,hasWhere);
			sb.append("status = '").append(status).append(" ");  
        } 
		
		if(!(goodsName == null || "".equals(goodsName))) {  
			hasWhere=appendWhereIfNeed(sb,hasWhere);
			sb.append("goods_name = '").append(goodsName).append(" ");  
        } 
		return productDao.queryByPage(currentPage, 10, sb.toString(),Goods.class);
	}
	
	/**
	 * 拼装sql的方法
	 * @param sql
	 * @param hasWhere
	 * @return
	 */
	 private Boolean appendWhereIfNeed(StringBuilder sql,Boolean hasWhere){  
        if(hasWhere == false){  
            sql.append("where ");  
        }else{  
            sql.append("and");  
        }  
       return true;
    } 
}
