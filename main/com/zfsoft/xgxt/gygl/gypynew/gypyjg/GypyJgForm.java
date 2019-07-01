package com.zfsoft.xgxt.gygl.gypynew.gypyjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class GypyJgForm extends ActionForm {
	private String jgid;
	private String lddm;
	private String qsh;
	private String sqxj;
	private String sfzcgx;
	private String gxsj;
	private String sqsj;
	private String sqly;
	private String cxzt;
	private String cxyy;
	private String cxsj;
	private String ch;
	private String sjly;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	public String getCxsj() {
		return cxsj;
	}
	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getSqxj() {
		return sqxj;
	}
	public void setSqxj(String sqxj) {
		this.sqxj = sqxj;
	}
	public String getSfzcgx() {
		return sfzcgx;
	}
	public void setSfzcgx(String sfzcgx) {
		this.sfzcgx = sfzcgx;
	}
	public String getGxsj() {
		return gxsj;
	}
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getCxzt() {
		return cxzt;
	}
	public void setCxzt(String cxzt) {
		this.cxzt = cxzt;
	}
	public String getCxyy() {
		return cxyy;
	}
	public void setCxyy(String cxyy) {
		this.cxyy = cxyy;
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
