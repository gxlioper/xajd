/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 上午09:11:04 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 项目维护(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfXmmkwhForm extends ActionForm{
	
	private static final long serialVersionUID = 1023643161542977601L;
	
	private String xmmkdm;
	private String xmmkmc;
	private String xf;
	private String cxzd;
	private String hgf;
	
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdm要设置的 xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the xmmkmc
	 */
	public String getXmmkmc() {
		return xmmkmc;
	}
	/**
	 * @param xmmkmc要设置的 xmmkmc
	 */
	public void setXmmkmc(String xmmkmc) {
		this.xmmkmc = xmmkmc;
	}
	/**
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xf要设置的 xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
	}
	/**
	 * @return the jfxmdm
	 */
	
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
	 * @return the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @param cxzd要设置的 cxzd
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @return the hgf
	 */
	public String getHgf() {
		return hgf;
	}
	/**
	 * @param hgf要设置的 hgf
	 */
	public void setHgf(String hgf) {
		this.hgf = hgf;
	}
	
	
	
	
}
