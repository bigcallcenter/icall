package com.bigcallcenter.icall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigcallcenter.icall.domain.Goods;
import com.bigcallcenter.icall.domain.Pagination;
import com.bigcallcenter.icall.service.ProductService;
import com.google.gson.Gson;

@Controller
public class ProductManageController {

	@Autowired
	private ProductService productService;
	/**
	 * 商品查询列表
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/product/queryGoods.do",produces = "text/html;charset=UTF-8")
	public String queryGoods(HttpServletRequest request,ModelMap modelMap)
	{
		String status=request.getParameter("status");
		String goodsName=request.getParameter("goodsName");
		String brandId=request.getParameter("brandId");
		String page = request.getParameter("currentPage");
		Integer currentPage=0;
		if(!(page==null || "".equals(page)))
		{
			currentPage=Integer.parseInt(page);
		}
 		Map<String, Object> map = new HashMap<String, Object>();
 		Pagination<Goods> pagination=productService.queryGoods(brandId, status, goodsName, currentPage);
		
		if(pagination==null)
			return "{\"msg\":\"error\"}";
		map.put("msg", "succ");  
		map.put("totalPage", pagination.getTotalPages());  
		map.put("currentPage", pagination.getCurrentPage());  
		map.put("totalRows", pagination.getTotalRows());  
		map.put("numPerPage", pagination.getNumPerPage());  
		map.put("businessList", pagination.getResultList());  
		Gson gson = new Gson();
		return gson.toJson(map);
	}
	
	/**
	 * 商品管理界面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/product/goodsManager.do")
	public String goodsManager(HttpServletRequest request,ModelMap modelMap)
	{
		return "/product/goodsManager";
	}
	
}
