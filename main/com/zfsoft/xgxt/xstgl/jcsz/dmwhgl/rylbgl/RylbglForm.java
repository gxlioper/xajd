/**
 * @部门:学工产品事业部
 * @日期：2015-9-9 下午04:37:36 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-9-9 下午04:37:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RylbglForm extends ActionForm {
	private String rylbdm; // 代码
	private String rylbmc; // 名称
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the rylbdm
	 */
	public String getRylbdm() {
		return rylbdm;
	}
	/**
	 * @param rylbdm要设置的 rylbdm
	 */
	public void setRylbdm(String rylbdm) {
		this.rylbdm = rylbdm;
	}
	/**
	 * @return the rylbmc
	 */
	public String getRylbmc() {
		return rylbmc;
	}
	/**
	 * @param rylbmc要设置的 rylbmc
	 */
	public void setRylbmc(String rylbmc) {
		this.rylbmc = rylbmc;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
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
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
