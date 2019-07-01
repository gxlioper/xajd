/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:50:41 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约信息 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-10-10 下午02:50:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyxxModel extends ActionForm{

	
	private static final long serialVersionUID = 3740163187234420743L;
	private String htbh;
	private String wyxq;
	private String bz;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	/**
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbh要设置的 htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the wyxq
	 */
	public String getWyxq() {
		return wyxq;
	}
	/**
	 * @param wyxq要设置的 wyxq
	 */
	public void setWyxq(String wyxq) {
		this.wyxq = wyxq;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	
	
}
