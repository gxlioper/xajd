package com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 评奖班级评议结果查询
 */
public class JgcxForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;
	
	private String sqid;
	private String xn;
	private String xq;
	private String xmdm;
	private String sqr;
	private String bjpydb;
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private String ylzd4;
	private String ylzd5;
	private String ylzd6;
	private String ylzd7;
	private String ylzd8;
	private String ylzd9;
	private String ylzd10;
	private String shzt;
	private String pyhsj;
	private String pyhdd;
	private String pyyj;
	private String tjzt;
	private String tjsj;
	
	private String pyyxbl; // 评议有效比例
	private String bjpyxzrs; // 评议小组人数
	private String bjpyxzcyxms; // 评议小组人员
	private String bjpyxzdbxms; // 评议小组代表
	private String pyjg; // 评议结果
	
	private String shztbjpy; // 班级评议状态
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getBjpydb() {
		return bjpydb;
	}
	public void setBjpydb(String bjpydb) {
		this.bjpydb = bjpydb;
	}
	public String getYlzd1() {
		return ylzd1;
	}
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	public String getYlzd2() {
		return ylzd2;
	}
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	public String getYlzd3() {
		return ylzd3;
	}
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	public String getYlzd4() {
		return ylzd4;
	}
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	public String getYlzd5() {
		return ylzd5;
	}
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	public String getYlzd6() {
		return ylzd6;
	}
	public void setYlzd6(String ylzd6) {
		this.ylzd6 = ylzd6;
	}
	public String getYlzd7() {
		return ylzd7;
	}
	public void setYlzd7(String ylzd7) {
		this.ylzd7 = ylzd7;
	}
	public String getYlzd8() {
		return ylzd8;
	}
	public void setYlzd8(String ylzd8) {
		this.ylzd8 = ylzd8;
	}
	public String getYlzd9() {
		return ylzd9;
	}
	public void setYlzd9(String ylzd9) {
		this.ylzd9 = ylzd9;
	}
	public String getYlzd10() {
		return ylzd10;
	}
	public void setYlzd10(String ylzd10) {
		this.ylzd10 = ylzd10;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getPyhsj() {
		return pyhsj;
	}
	public void setPyhsj(String pyhsj) {
		this.pyhsj = pyhsj;
	}
	public String getPyhdd() {
		return pyhdd;
	}
	public void setPyhdd(String pyhdd) {
		this.pyhdd = pyhdd;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getPyyxbl() {
		return pyyxbl;
	}
	public void setPyyxbl(String pyyxbl) {
		this.pyyxbl = pyyxbl;
	}
	public String getBjpyxzrs() {
		return bjpyxzrs;
	}
	public void setBjpyxzrs(String bjpyxzrs) {
		this.bjpyxzrs = bjpyxzrs;
	}
	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}
	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}
	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}
	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}
	public String getPyyj() {
		return pyyj;
	}
	public void setPyyj(String pyyj) {
		this.pyyj = pyyj;
	}
	public String getPyjg() {
		return pyjg;
	}
	public void setPyjg(String pyjg) {
		this.pyjg = pyjg;
	}
	public String getShztbjpy() {
		return shztbjpy;
	}
	public void setShztbjpy(String shztbjpy) {
		this.shztbjpy = shztbjpy;
	}
	
}
