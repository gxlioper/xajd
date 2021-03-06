/**
 * @部门:学工产品(1)部
 * @日期：2018-1-31 下午04:13:55 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 证书打印数据
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-31 下午04:13:55 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZsdysjForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	
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
