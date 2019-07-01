/**
 * @部门:学工产品事业部
 * @日期：2016-3-29 下午04:06:38 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设
 * @类功能描述: 统计查询功能
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-3-29 下午04:06:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DtxxTjForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String xydm;  // 学院
	private String xh;    // 学号
	private String xm;    // 姓名
	private String nd;    // 年度
	
	// 高级查询
	SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	
	
	
}
