/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:21:31 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 留校项目设置 xg_cqsx_jqlx_xmsz
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:21:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxxmszForm extends ActionForm {
	  private String xmid;
	  private String xmmc;
	  private String lxkssj;
	  private String lxjssj;
	  private static final long serialVersionUID = 1L;
	  private String lxxmsm;
	  private String type;
	  private SearchModel searchModel = new SearchModel();
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the lxkssj
	 */
	public String getLxkssj() {
		return lxkssj;
	}
	/**
	 * @param lxkssj要设置的 lxkssj
	 */
	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}
	/**
	 * @return the lxjssj
	 */
	public String getLxjssj() {
		return lxjssj;
	}
	/**
	 * @param lxjssj要设置的 lxjssj
	 */
	public void setLxjssj(String lxjssj) {
		this.lxjssj = lxjssj;
	}
	/**
	 * @return the lxxmsm
	 */
	public String getLxxmsm() {
		return lxxmsm;
	}
	/**
	 * @param lxxmsm要设置的 lxxmsm
	 */
	public void setLxxmsm(String lxxmsm) {
		this.lxxmsm = lxxmsm;
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
