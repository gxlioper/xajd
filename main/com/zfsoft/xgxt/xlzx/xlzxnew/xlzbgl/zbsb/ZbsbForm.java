package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import org.apache.struts.action.ActionForm;
import org.mortbay.html.Page;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZbsbForm extends ActionForm {
	private String sbsqid;
	private String xh;
	private String sbsj;
	private String sbzbid;
	private String ztqk;
	private String xlxsxxqk;
	private String bz;
	private String splcid;
	private String shzt;
	private String xn;
	private String xm;
	private String xq;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String[] xhArray;
	private String[] zbwtmsArray;
	private String saveFlag;
	private String bjdm;
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String[] getXhArray() {
		return xhArray;
	}
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	public String[] getZbwtmsArray() {
		return zbwtmsArray;
	}
	public void setZbwtmsArray(String[] zbwtmsArray) {
		this.zbwtmsArray = zbwtmsArray;
	}
	public String getSbsqid() {
		return sbsqid;
	}
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSbzbid() {
		return sbzbid;
	}
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}
	public String getZtqk() {
		return ztqk;
	}
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
}
