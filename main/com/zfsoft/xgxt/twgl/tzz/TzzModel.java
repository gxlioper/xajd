/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tzz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	： TzzModel
 * @description	： 团组织model(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-14 上午11:50:27
 * @version 	V1.0 
 */

public class TzzModel extends ActionForm{
	/**
	 * @fields ：serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = -7107035123010327358L;
	private String zzid;
	private String zzmc;
	private String zddw;
	private String zzdz;
	private String zzjj;
	private String znjs;
	private String fzr;
	private String xh;
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getZzid() {
		return zzid;
	}
	public void setZzid(String zzid) {
		this.zzid = zzid;
	}
	public String getZzmc() {
		return zzmc;
	}
	public void setZzmc(String zzmc) {
		this.zzmc = zzmc;
	}
	public String getZddw() {
		return zddw;
	}
	public void setZddw(String zddw) {
		this.zddw = zddw;
	}
	public String getZzdz() {
		return zzdz;
	}
	public void setZzdz(String zzdz) {
		this.zzdz = zzdz;
	}
	public String getZzjj() {
		return zzjj;
	}
	public void setZzjj(String zzjj) {
		this.zzjj = zzjj;
	}
	public String getZnjs() {
		return znjs;
	}
	public void setZnjs(String znjs) {
		this.znjs = znjs;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	
}
