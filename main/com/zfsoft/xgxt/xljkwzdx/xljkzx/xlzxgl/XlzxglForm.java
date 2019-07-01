/**
 * @部门:学工产品事业部
 * @日期：2014-5-7 下午01:54:50 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-5-7 下午01:54:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxglForm extends ActionForm{

	private static final long serialVersionUID = 8283786647718192612L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private ExportModel exportModel = new ExportModel();

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
	
}
