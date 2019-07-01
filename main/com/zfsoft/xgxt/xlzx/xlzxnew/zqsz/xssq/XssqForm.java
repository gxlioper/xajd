package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XssqForm extends ActionForm {
	private String xh;
	private String rzksrq;
	private String rzjsrq;
	private String sfxypssb;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getRzksrq() {
		return rzksrq;
	}
	public void setRzksrq(String rzksrq) {
		this.rzksrq = rzksrq;
	}
	public String getRzjsrq() {
		return rzjsrq;
	}
	public void setRzjsrq(String rzjsrq) {
		this.rzjsrq = rzjsrq;
	}
	public String getSfxypssb() {
		return sfxypssb;
	}
	public void setSfxypssb(String sfxypssb) {
		this.sfxypssb = sfxypssb;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	private Pages pages = new Pages();
}
