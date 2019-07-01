/**
 * @部门:学工产品事业部
 * @日期：2014-6-20 上午11:22:55 
 */  
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2014-6-20 上午11:22:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdDmwhForm extends ActionForm {
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	
	private String lbdm ;//类别代码
	private String lbmc ;//类别名称
	private String lbsm ;//类别说明
	
	//活动规格代码表维护form字段
	private String hdggdm ;
	private String hdggmc ;
	public String getType() {
		return type;
	}
	/**
	 * @return the hdggdm
	 */
	public String getHdggdm() {
		return hdggdm;
	}
	/**
	 * @param hdggdm要设置的 hdggdm
	 */
	public void setHdggdm(String hdggdm) {
		this.hdggdm = hdggdm;
	}
	/**
	 * @return the hdggmc
	 */
	public String getHdggmc() {
		return hdggmc;
	}
	/**
	 * @param hdggmc要设置的 hdggmc
	 */
	public void setHdggmc(String hdggmc) {
		this.hdggmc = hdggmc;
	}
	public void setType(String type) {
		this.type = type;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getLbmc() {
		return lbmc;
	}
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public String getLbsm() {
		return lbsm;
	}
	public void setLbsm(String lbsm) {
		this.lbsm = lbsm;
	}

	
	
	
	

}
