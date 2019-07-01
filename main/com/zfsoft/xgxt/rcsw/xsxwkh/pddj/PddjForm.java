/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:26 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.pddj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


public class PddjForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String jbfid;
	private String id;
	private String khxn;
	private String xn;
	private String xh;
	private String pddj;
	private String xf;
	private String[] xhs;
	private String[] xns;


	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getJbfid() {
		return jbfid;
	}
	public void setJbfid(String jbfid) {
		this.jbfid = jbfid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPddj() {
		return pddj;
	}
	public void setPddj(String pddj) {
		this.pddj = pddj;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
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
	public String getKhxn() {
		return khxn;
	}
	public void setKhxn(String khxn) {
		this.khxn = khxn;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String[] getXns() {
		return xns;
	}
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	
}
