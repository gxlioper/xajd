/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:29:04 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务类型actionForm
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:29:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwlxForm extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String lxdm ;//职务类型
	private String lxmc ;//类型名称
	private String bz ;//备注
	private String splc;//审批流程   新增字段 2016-10-25
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the splc 新增字段 审批流程
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc 新增字段 审批流程
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
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
