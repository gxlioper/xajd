/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 上午09:11:20 
 */  
package com.zfsoft.xgxt.khgl.jgcx.pfxq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: form
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 上午09:11:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfxqForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String xmid;
	private String khbid;
	private String khdxr;
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
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the khbid
	 */
	public String getKhbid() {
		return khbid;
	}
	/**
	 * @param khbid要设置的 khbid
	 */
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	/**
	 * @return the khdxr
	 */
	public String getKhdxr() {
		return khdxr;
	}
	/**
	 * @param khdxr要设置的 khdxr
	 */
	public void setKhdxr(String khdxr) {
		this.khdxr = khdxr;
	}
	
	
}
