package com.bigcallcenter.icall.domain;

import java.io.Serializable;

public class MenuInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String parentMenu;
	
	private String menuName;
	
	private String menuType;
	
	private String url;
	
	public String getId() {
		return id;
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
