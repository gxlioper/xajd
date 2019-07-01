/**
 * @部门:学工产品事业部
 * @日期：2013-7-5 下午02:37:53 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训项目维护
 * @作者： zhangjw
 * @时间： 2013-7-5 下午02:35:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmwhForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String xmdm ;//项目代码
	private String xmmc ;//项目名称
	private String pxdd ;//培训地点
	private String pxsj ;//培训结束时间
	private String sqkssj ;//申请开始时间
	private String sqjssj ;//申请结束时间
	private String sqkg ;//申请开关
	private String pxjj ;//培训简介
	private String fbsj ;//发布时间
	private String fbr ;//发布人
	private String bmdm ;//组织部门
	private String pxxs ; //培训学时

	public String getPxxs() {
		return pxxs;
	}
	public void setPxxs(String pxxs) {
		this.pxxs = pxxs;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getPxdd() {
		return pxdd;
	}
	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}
	public String getPxsj() {
		return pxsj;
	}
	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getPxjj() {
		return pxjj;
	}
	public void setPxjj(String pxjj) {
		this.pxjj = pxjj;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	
}
