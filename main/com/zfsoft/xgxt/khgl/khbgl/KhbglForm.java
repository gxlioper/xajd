/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:38:01 
 */  
package com.zfsoft.xgxt.khgl.khbgl;


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

public class KhbglForm extends ActionForm{
	private String khbid; 
	private String khbmc; // 考核表名称
	private String cjsj; 
	private String xgsj;
	private String khdxid;
	private String khdxmc;
	private String sfysy;
	private String sfqy;//是否启用
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getKhbid() {
		return khbid;
	}
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	public String getKhbmc() {
		return khbmc;
	}
	public void setKhbmc(String khbmc) {
		this.khbmc = khbmc;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getXgsj() {
		return xgsj;
	}
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	public String getKhdxid() {
		return khdxid;
	}
	public void setKhdxid(String khdxid) {
		this.khdxid = khdxid;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
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
	
	

}
