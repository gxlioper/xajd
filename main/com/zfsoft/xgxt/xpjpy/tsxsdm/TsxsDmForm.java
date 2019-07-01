/**
 * @部门:学工产品事业部
 * @日期：2013-8-20 上午09:34:39 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-20 上午09:34:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsDmForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String lxdm;
	private String lxmc;
	private String lxsm;
	private String lxsx;
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
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getLxsm() {
		return lxsm;
	}
	public void setLxsm(String lxsm) {
		this.lxsm = lxsm;
	}
	public String getLxsx() {
		return lxsx;
	}
	public void setLxsx(String lxsx) {
		this.lxsx = lxsx;
	}
	
}
