/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午02:24:28 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:24:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StlbglForm extends ActionForm{
	private String stlbdm; // 代码
	private String stlbmc; // 名称
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getStlbdm() {
		return stlbdm;
	}
	public void setStlbdm(String stlbdm) {
		this.stlbdm = stlbdm;
	}
	public String getStlbmc() {
		return stlbmc;
	}
	public void setStlbmc(String stlbmc) {
		this.stlbmc = stlbmc;
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
