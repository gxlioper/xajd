package com.zfsoft.xgxt.xsxx.tjcx.zmxszxqktj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZxqktjForm extends ActionForm {
	private String kxrq;
	private String kkrq;
	private String jfjzrq;
	private String zd4;
	private String zd5;
	private String zd6;
	private String type;
	private SearchModel searchModel = new SearchModel();
	ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
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
	public String getKxrq() {
		return kxrq;
	}
	public void setKxrq(String kxrq) {
		this.kxrq = kxrq;
	}
	public String getKkrq() {
		return kkrq;
	}
	public void setKkrq(String kkrq) {
		this.kkrq = kkrq;
	}
	public String getJfjzrq() {
		return jfjzrq;
	}
	public void setJfjzrq(String jfjzrq) {
		this.jfjzrq = jfjzrq;
	}
	public String getZd4() {
		return zd4;
	}
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZd5() {
		return zd5;
	}
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	public String getZd6() {
		return zd6;
	}
	public void setZd6(String zd6) {
		this.zd6 = zd6;
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
