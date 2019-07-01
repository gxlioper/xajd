/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:38:01 
 */  
package com.zfsoft.xgxt.khgl.khxmgl;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KhxmglForm extends ActionForm{
	private String xmid; 
	private String xmmc; 
	private String khdxid; 
	private String khdxmc;
	private String kssj;
	private String jssj;
	private String xmms;
	private String sfysy;
	private String xmszid;           
	private String   pfzid ;           
	private String   pffw  ; 
	private String sjfwdm;
	private String   khbid ;            
	private String   szqz ;          
	private String   yxfbl;            
	private String   jsfs ;            
	private String   jsfsbfb  ;         
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getKhdxid() {
		return khdxid;
	}
	public void setKhdxid(String khdxid) {
		this.khdxid = khdxid;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXmms() {
		return xmms;
	}
	public void setXmms(String xmms) {
		this.xmms = xmms;
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
	public String getXmszid() {
		return xmszid;
	}
	public void setXmszid(String xmszid) {
		this.xmszid = xmszid;
	}
	public String getPfzid() {
		return pfzid;
	}
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	public String getPffw() {
		return pffw;
	}
	public void setPffw(String pffw) {
		this.pffw = pffw;
	}
	public String getKhbid() {
		return khbid;
	}
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	public String getSzqz() {
		return szqz;
	}
	public void setSzqz(String szqz) {
		this.szqz = szqz;
	}
	public String getYxfbl() {
		return yxfbl;
	}
	public void setYxfbl(String yxfbl) {
		this.yxfbl = yxfbl;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getJsfsbfb() {
		return jsfsbfb;
	}
	public void setJsfsbfb(String jsfsbfb) {
		this.jsfsbfb = jsfsbfb;
	}
	public String getKhdxmc() {
		return khdxmc;
	}
	public void setKhdxmc(String khdxmc) {
		this.khdxmc = khdxmc;
	}
	public String getSfysy() {
		return sfysy;
	}
	public void setSfysy(String sfysy) {
		this.sfysy = sfysy;
	}
	public String getSjfwdm() {
		return sjfwdm;
	}
	public void setSjfwdm(String sjfwdm) {
		this.sjfwdm = sjfwdm;
	}
	

}
