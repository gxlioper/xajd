/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:23:43 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:23:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhForm extends ActionForm {
	  private String id;
	  private String xh;
	  private String xmid;
	  private static final long serialVersionUID = 1L;
	  private String lxqksm;
	  private String type;
	  private SearchModel searchModel = new SearchModel();
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
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
	 * @return the lxqksm
	 */
	public String getLxqksm() {
		return lxqksm;
	}
	/**
	 * @param lxqksm要设置的 lxqksm
	 */
	public void setLxqksm(String lxqksm) {
		this.lxqksm = lxqksm;
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
}
