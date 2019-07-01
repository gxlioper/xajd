/**
 * @部门:学工产品事业部
 * @日期：2013-4-27 下午04:14:46 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 公寓纪律信息DAO
 * @作者： zhangjw
 * @时间： 2013-4-27 下午04:09:18 
 * @版本： V5.9.17
 * @修改记录:   
 */

public class GyjltjForm   extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
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
	
	

}
