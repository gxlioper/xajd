package com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 评奖班级评议结果录入
 */
public class JglrForm extends ActionForm {

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
	private String bjpyr;
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
	private String tjzt;
	private String tjsj;
	
	private String[] xns;
	private String[] xqs;
	private String[] xmdms;
	private String[] sqrs;
	private String[] ylzd1s;
	private String[] ylzd2s;
	private String[] ylzd3s;
	
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
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getBjpyr() {
		return bjpyr;
	}
	public void setBjpyr(String bjpyr) {
		this.bjpyr = bjpyr;
	}
	public String getYlzd1() {
		return ylzd1;
	}
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	public String[] getYlzd1s() {
		return ylzd1s;
	}
	public void setYlzd1s(String[] ylzd1s) {
		this.ylzd1s = ylzd1s;
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
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String[] getXns() {
		return xns;
	}
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	public String[] getXqs() {
		return xqs;
	}
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
	}
	public String[] getSqrs() {
		return sqrs;
	}
	public void setSqrs(String[] sqrs) {
		this.sqrs = sqrs;
	}
	public String[] getYlzd2s() {
		return ylzd2s;
	}
	public void setYlzd2s(String[] ylzd2s) {
		this.ylzd2s = ylzd2s;
	}
	public String[] getYlzd3s() {
		return ylzd3s;
	}
	public void setYlzd3s(String[] ylzd3s) {
		this.ylzd3s = ylzd3s;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String[] getXmdms() {
		return xmdms;
	}
	public void setXmdms(String[] xmdms) {
		this.xmdms = xmdms;
	}
	
}
