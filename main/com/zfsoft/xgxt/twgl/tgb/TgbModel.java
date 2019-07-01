/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tgb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	： TgbModel
 * @description	： TODO(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-15 下午03:23:01
 * @version 	V1.0 
 */

public class TgbModel extends ActionForm{
	/*xn varchar2(10) not null,
	zwdm varchar2(8) not null,
	rzzz varchar2(40) not null,
	rzsj varchar2(20) not null,
	bz varchar2(2000)*/
	private String jgid;
	private String xh;
	private String xn;
	private String zwdm;
	private String rzzz;
	private String rzsj;
	private String bz;
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	public String getZwdm() {
		return zwdm;
	}
	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}
	public String getRzzz() {
		return rzzz;
	}
	public void setRzzz(String rzzz) {
		this.rzzz = rzzz;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
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
