package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XlzxSbJcszForm extends ActionForm {
	private String id;
	private String splc;
	private String lx;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String[] splcs;
	private String[] lxs;
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String[] getLxs() {
		return lxs;
	}
	public void setLxs(String[] lxs) {
		this.lxs = lxs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
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
