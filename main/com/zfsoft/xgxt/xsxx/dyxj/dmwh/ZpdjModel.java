/**
 * @部门:学工产品事业部
 * @日期：2015-6-18 上午10:23:15 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 自评等级维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-18 上午10:23:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpdjModel extends ActionForm {

	private static final long serialVersionUID = 531837854488218723L;
	
	private String djdm;
	private String djmc;
	private String xmsm;
	
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
	/**
	 * @return the djdm
	 */
	public String getDjdm() {
		return djdm;
	}
	/**
	 * @param djdm要设置的 djdm
	 */
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	/**
	 * @return the djmc
	 */
	public String getDjmc() {
		return djmc;
	}
	/**
	 * @param djmc要设置的 djmc
	 */
	public void setDjmc(String djmc) {
		this.djmc = djmc;
	}
	/**
	 * @return the xmsm
	 */
	public String getXmsm() {
		return xmsm;
	}
	/**
	 * @param xmsm要设置的 xmsm
	 */
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}
	
	

}
