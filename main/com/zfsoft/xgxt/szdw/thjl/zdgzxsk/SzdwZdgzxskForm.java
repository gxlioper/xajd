package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.szdw.thjl.SzdwThjlForm;

/** 
 * 重点关注学生库维护
 */
public class SzdwZdgzxskForm extends SzdwThjlForm {
	
	private static final long serialVersionUID = 1L;

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private ExportModel exportModel = new ExportModel();
	
	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

}
