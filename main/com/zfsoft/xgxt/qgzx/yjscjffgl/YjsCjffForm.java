package com.zfsoft.xgxt.qgzx.yjscjffgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 经费酬金管理  数据维护Form
 * @作者： zhangjw 
 * @时间：2013-04-15 上午09:46:37 
 * @版本： V5.1.75
 * @修改记录: 
 */
public class YjsCjffForm  extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String	guid	;	//	唯一编号
	private String	xh	;	//	学号
	private String xm;
	private String gwmc;
	private String	xn	;	//	学年
	private String	yrbm	;	//	用人部门
	private String	gs	;	//	工时
	private String	je	;	//	金额
	private String	gznr	;	//	工作内容
	private String ffny;//发放年月
	private String	bmdm; //部门代码
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYrbm() {
		return yrbm;
	}
	public void setYrbm(String yrbm) {
		this.yrbm = yrbm;
	}
	public String getGs() {
		return gs;
	}
	public void setGs(String gs) {
		this.gs = gs;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getGznr() {
		return gznr;
	}
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	public String getFfny() {
		return ffny;
	}
	public void setFfny(String ffny) {
		this.ffny = ffny;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	
	
}
