package com.bigcallcenter.icall.domain;

import java.util.Date;

public class Goods {

	private String id;
	private Date createTime;
	private Date modifyTime;
	private String goodsName;
	private String status;
	private float defaultPrice;
	private String category1Id;
	private String category2id;
	private String category3Id;
	private String brandId;
	private String introduction;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(float defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	public String getCategory1Id() {
		return category1Id;
	}
	public void setCategory1Id(String category1Id) {
		this.category1Id = category1Id;
	}
	public String getCategory2id() {
		return category2id;
	}
	public void setCategory2id(String category2id) {
		this.category2id = category2id;
	}
	public String getCategory3Id() {
		return category3Id;
	}
	public void setCategory3Id(String category3Id) {
		this.category3Id = category3Id;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
