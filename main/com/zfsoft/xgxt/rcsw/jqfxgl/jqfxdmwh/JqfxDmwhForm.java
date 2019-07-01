/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午03:08:35 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-假期返校管理模块（青岛滨海学院）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-4-20 下午03:08:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("serial")
public class JqfxDmwhForm extends ActionForm{
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String fxdm;//返校代码
	private String fxmc;//返校名称
	
	
	/**
	 * @return the fxdm
	 */
	public String getFxdm() {
		return fxdm;
	}
	/**
	 * @param fxdm要设置的 fxdm
	 */
	public void setFxdm(String fxdm) {
		this.fxdm = fxdm;
	}
	/**
	 * @return the fxmc
	 */
	public String getFxmc() {
		return fxmc;
	}
	/**
	 * @param fxmc要设置的 fxmc
	 */
	public void setFxmc(String fxmc) {
		this.fxmc = fxmc;
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