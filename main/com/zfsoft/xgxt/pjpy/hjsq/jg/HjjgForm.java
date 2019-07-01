package com.zfsoft.xgxt.pjpy.hjsq.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class HjjgForm extends ActionForm {
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String hjmc;
	private String hjsj;
	private String fjdw;
	private String fj;
	private String sjly;
	private String sfysq;
	private SearchModel searchModel = new SearchModel();
    private String type;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	public String getSfysq() {
		return sfysq;
	}
	public void setSfysq(String sfysq) {
		this.sfysq = sfysq;
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
	public String getHjmc() {
		return hjmc;
	}
	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getFjdw() {
		return fjdw;
	}
	public void setFjdw(String fjdw) {
		this.fjdw = fjdw;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
