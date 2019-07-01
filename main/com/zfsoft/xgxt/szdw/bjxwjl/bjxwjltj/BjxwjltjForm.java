/**
 * @部门:学工产品事业部
 * @日期：2014-5-16 下午01:39:24 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 */

public class BjxwjltjForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	/** 
	 * @属性 pages : 分页模型 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @属性 searchModel : 高级查询模型 
	 */
	private SearchModel searchModel = new SearchModel();

	
	private String bjdm;
	
	
	
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}

	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	
	
}
