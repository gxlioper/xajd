/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午03:30:59 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 学生献血管理actionForm 
 * @作者： zhangjw 
 * @时间： 2013-4-18 下午03:26:39 
 * @版本： V5.1.75
 */
public class XsxxglForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String 	xxgldm	;//	主键pk_xg_rcsw_xsxxgl
	private String 	xh	;//	学号
	private String 	xn	;//	学年
	private String 	xxsj	;//	献血时间
	private String 	bz	;//	备注
	public String getXxgldm() {
		return xxgldm;
	}
	public void setXxgldm(String xxgldm) {
		this.xxgldm = xxgldm;
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
	public String getXxsj() {
		return xxsj;
	}
	public void setXxsj(String xxsj) {
		this.xxsj = xxsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}


}
