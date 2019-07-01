/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 上午09:35:38 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 考勤类型代码维护
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 上午09:35:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqlxForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String kqlxdm; //考勤类型代码
	private String kqlxmc; //考勤类型名称
	
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
	public String getKqlxdm() {
		return kqlxdm;
	}
	public void setKqlxdm(String kqlxdm) {
		this.kqlxdm = kqlxdm;
	}
	public String getKqlxmc() {
		return kqlxmc;
	}
	public void setKqlxmc(String kqlxmc) {
		this.kqlxmc = kqlxmc;
	}
	
}
