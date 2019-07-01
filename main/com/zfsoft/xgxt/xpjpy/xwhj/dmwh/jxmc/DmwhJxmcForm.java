/**
 * @部门:学工产品事业部
 * @日期：2016-7-20 下午04:00:54 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 代码维护-奖项名次
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-20 下午04:00:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxmcForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String jxmcdm;
	private String jxmcmc;
	private String jxdjdm;
	private String jxdjmc;
	private String jsfs;
	private String jxlbdm;
	private String jxlbmc;
	private String je;
	
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
	public String getJxmcdm() {
		return jxmcdm;
	}
	public void setJxmcdm(String jxmcdm) {
		this.jxmcdm = jxmcdm;
	}
	public String getJxmcmc() {
		return jxmcmc;
	}
	public void setJxmcmc(String jxmcmc) {
		this.jxmcmc = jxmcmc;
	}
	public String getJxdjdm() {
		return jxdjdm;
	}
	public void setJxdjdm(String jxdjdm) {
		this.jxdjdm = jxdjdm;
	}
	public String getJxdjmc() {
		return jxdjmc;
	}
	public void setJxdjmc(String jxdjmc) {
		this.jxdjmc = jxdjmc;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getJxlbdm() {
		return jxlbdm;
	}
	public void setJxlbdm(String jxlbdm) {
		this.jxlbdm = jxlbdm;
	}
	public String getJxlbmc() {
		return jxlbmc;
	}
	public void setJxlbmc(String jxlbmc) {
		this.jxlbmc = jxlbmc;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
}
