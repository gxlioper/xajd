/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午09:57:43 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生授权管理 
 * @类功能描述: 学生授权管理 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午09:57:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XssqForm extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String xh;
	
	private String lx;
	
	private String rzksrq;
	
	private String rzjsrq;
	
	private String sfxypssb;

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
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the rzksrq
	 */
	public String getRzksrq() {
		return rzksrq;
	}

	/**
	 * @param rzksrq要设置的 rzksrq
	 */
	public void setRzksrq(String rzksrq) {
		this.rzksrq = rzksrq;
	}

	/**
	 * @return the rzjsrq
	 */
	public String getRzjsrq() {
		return rzjsrq;
	}

	/**
	 * @param rzjsrq要设置的 rzjsrq
	 */
	public void setRzjsrq(String rzjsrq) {
		this.rzjsrq = rzjsrq;
	}

	/**
	 * @return the sfxypssb
	 */
	public String getSfxypssb() {
		return sfxypssb;
	}

	/**
	 * @param sfxypssb要设置的 sfxypssb
	 */
	public void setSfxypssb(String sfxypssb) {
		this.sfxypssb = sfxypssb;
	}

}
