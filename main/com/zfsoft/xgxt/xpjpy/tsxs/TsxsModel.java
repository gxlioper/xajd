/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午10:13:48 
 */  
package com.zfsoft.xgxt.xpjpy.tsxs;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 特殊学生维护 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-2 上午10:13:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsModel extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private Pages pages = new Pages();
	private String tsxsxh;		//特殊学生学号
	private String lxdm;		//特殊学生代码
	private String lxmc;		//特殊学生名称
	private String lsmc;		//特殊学生名称
	private String pjzq;		//评奖周期
	private String xhs;			//学号组	
	private String xq;			//学期
	private String xn; 			//学年
	private String mklx;		//模块类型
	
	public String getXhs() {
		return xhs;
	}
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getTsxsxh() {
		return tsxsxh;
	}
	public void setTsxsxh(String tsxsxh) {
		this.tsxsxh = tsxsxh;
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
	public String getLsmc() {
		return lsmc;
	}
	public void setLsmc(String lsmc) {
		this.lsmc = lsmc;
	}
	public String getPjzq() {
		return pjzq;
	}
	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getMklx() {
		return mklx;
	}
	public void setMklx(String mklx) {
		this.mklx = mklx;
	}
	
	
}
