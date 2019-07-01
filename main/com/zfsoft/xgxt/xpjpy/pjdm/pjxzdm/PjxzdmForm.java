/**
 * @部门:学工产品事业部
 * @日期：2013-8-21 上午10:08:22 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-21 上午10:08:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxzdmForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String xmxzdm;	//项目性质代码
	private String xmxzmc;	//项目性质名称
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
	public String getXmxzdm() {
		return xmxzdm;
	}
	public void setXmxzdm(String xmxzdm) {
		this.xmxzdm = xmxzdm;
	}
	public String getXmxzmc() {
		return xmxzmc;
	}
	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}
	
	

}
