/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午03:52:13 
 */  
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.base.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午03:52:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlLbglForm extends ActionForm {
	private String lbdm; // 代码
	private String lbmc; // 名称
	private String lbsm; //说明
	private String xssx;//显示顺序
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the lbmc
	 */
	public String getLbmc() {
		return lbmc;
	}
	/**
	 * @param lbmc要设置的 lbmc
	 */
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	/**
	 * @return the lbsm
	 */
	public String getLbsm() {
		return lbsm;
	}
	/**
	 * @param lbsm要设置的 lbsm
	 */
	public void setLbsm(String lbsm) {
		this.lbsm = lbsm;
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
	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}
	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	
	

}
