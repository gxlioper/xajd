/**
 * @部门:学工产品事业部
 * @日期：2016-8-23 下午03:49:48 
 */  
package com.zfsoft.xgxt.xsxx.qxxscx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 全校学生查询
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-8-23 下午03:49:48 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QxxscxForm extends ActionForm{
	private Pages pages = new Pages();//分页
	private SearchModel searchModel = new SearchModel();//高级查询
	private String type;//类型
	
	
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
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
