/**
 * @部门:学工产品事业部
 * @日期：2017年2月14日 下午3:34:39 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出-改派日志模块
 * @类功能描述: Form
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月14日 下午3:34:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GprzForm extends ActionForm{
	
	private String type;
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	
	
	private String id;		//日志id
	private String xgsj;	//修改时间
	private String xgr;		//修改人
	private String xh;	//学号
	private String xgqjl;	//修改前记录
	private String xghjl;	//修改后记录
	private String gpyy;	//改派原因
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
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}
	/**
	 * @param xgsj要设置的 xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	/**
	 * @return the xgr
	 */
	public String getXgr() {
		return xgr;
	}
	/**
	 * @param xgr要设置的 xgr
	 */
	public void setXgr(String xgr) {
		this.xgr = xgr;
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
	 * @return the xgqjl
	 */
	public String getXgqjl() {
		return xgqjl;
	}
	/**
	 * @param xgqjl要设置的 xgqjl
	 */
	public void setXgqjl(String xgqjl) {
		this.xgqjl = xgqjl;
	}
	/**
	 * @return the xghjl
	 */
	public String getXghjl() {
		return xghjl;
	}
	/**
	 * @param xghjl要设置的 xghjl
	 */
	public void setXghjl(String xghjl) {
		this.xghjl = xghjl;
	}
	/**
	 * @return the gpyy
	 */
	public String getGpyy() {
		return gpyy;
	}
	/**
	 * @param gpyy要设置的 gpyy
	 */
	public void setGpyy(String gpyy) {
		this.gpyy = gpyy;
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
	
	
}
