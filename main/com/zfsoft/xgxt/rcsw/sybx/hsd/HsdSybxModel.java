/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午11:26:25 
 */  
package com.zfsoft.xgxt.rcsw.sybx.hsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 华师大-商业保险
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午11:26:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdSybxModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String bdh;
	private String bdyxq;
	private String be;
	private String gmsj;
	private String je;
	private String bxgs;
	
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the bdh
	 */
	public String getBdh() {
		return bdh;
	}
	/**
	 * @param bdh要设置的 bdh
	 */
	public void setBdh(String bdh) {
		this.bdh = bdh;
	}
	/**
	 * @return the bdyxq
	 */
	public String getBdyxq() {
		return bdyxq;
	}
	/**
	 * @param bdyxq要设置的 bdyxq
	 */
	public void setBdyxq(String bdyxq) {
		this.bdyxq = bdyxq;
	}
	/**
	 * @return the be
	 */
	public String getBe() {
		return be;
	}
	/**
	 * @param be要设置的 be
	 */
	public void setBe(String be) {
		this.be = be;
	}
	/**
	 * @return the gmsj
	 */
	public String getGmsj() {
		return gmsj;
	}
	/**
	 * @param gmsj要设置的 gmsj
	 */
	public void setGmsj(String gmsj) {
		this.gmsj = gmsj;
	}
	/**
	 * @return the je
	 */
	public String getJe() {
		return je;
	}
	/**
	 * @param je要设置的 je
	 */
	public void setJe(String je) {
		this.je = je;
	}
	/**
	 * @return the bxgs
	 */
	public String getBxgs() {
		return bxgs;
	}
	/**
	 * @param bxgs要设置的 bxgs
	 */
	public void setBxgs(String bxgs) {
		this.bxgs = bxgs;
	}
	
	
	
}
