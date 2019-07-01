/**
 * @部门:学工产品事业部
 * @日期：2016-6-21 上午09:25:14 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-困难生认定
 * @类功能描述: 申请理由代码维护
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-21 上午09:25:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SqlyDmwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String sqlydm;
	private String sqlymc;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getSqlydm() {
		return sqlydm;
	}
	public void setSqlydm(String sqlydm) {
		this.sqlydm = sqlydm;
	}
	public String getSqlymc() {
		return sqlymc;
	}
	public void setSqlymc(String sqlymc) {
		this.sqlymc = sqlymc;
	}
	
}
