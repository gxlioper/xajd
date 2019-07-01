/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:26 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.fjfgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


public class FjfglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String id;
	private String xn;
	private String xh;
	private String fwsj;
	private String fwxss;
	private String fz;
	private String bz;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	public String getFwsj() {
		return fwsj;
	}
	public void setFwsj(String fwsj) {
		this.fwsj = fwsj;
	}
	public String getFwxss() {
		return fwxss;
	}
	public void setFwxss(String fwxss) {
		this.fwxss = fwxss;
	}
	public String getFz() {
		return fz;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	

}
