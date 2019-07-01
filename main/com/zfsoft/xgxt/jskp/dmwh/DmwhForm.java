package com.zfsoft.xgxt.jskp.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DmwhForm extends ActionForm {
	private String xmlbdm;
	private String xmlbmc;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
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
	public String getXmlbdm() {
		return xmlbdm;
	}
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	public String getXmlbmc() {
		return xmlbmc;
	}
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	
}
