package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XsxlpcForm extends ActionForm {
	private String id;
	private String xh;
	private String pcjg;
	private String fjpath;
	private String bz;
	private String sfgz;
	private String jlr;
	private String jlsj;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String type;
	//µ¼³ö
	private ExportModel exportModel = new ExportModel();
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPcjg() {
		return pcjg;
	}
	public void setPcjg(String pcjg) {
		this.pcjg = pcjg;
	}
	public String getFjpath() {
		return fjpath;
	}
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSfgz() {
		return sfgz;
	}
	public void setSfgz(String sfgz) {
		this.sfgz = sfgz;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String getJlsj() {
		return jlsj;
	}
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
