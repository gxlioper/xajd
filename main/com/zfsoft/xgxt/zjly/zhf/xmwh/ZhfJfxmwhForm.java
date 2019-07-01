/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 下午04:36:01 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 计分项目(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 下午04:36:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfJfxmwhForm extends ActionForm{
	
	private static final long serialVersionUID = -2024530567050197284L;
	
	private String jfxmdm;
	private String jfxmmc;
	private String khyd;
	private String fs;
	private String sfxf;
	private String xdfs;
	private String sfbt;
	private String xmmkdm;
	private String cxzd;
	private String cxmc;
	private String jdszContent;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdm要设置的 jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmc要设置的 jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	/**
	 * @return the khyd
	 */
	public String getKhyd() {
		return khyd;
	}
	/**
	 * @param khyd要设置的 khyd
	 */
	public void setKhyd(String khyd) {
		this.khyd = khyd;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the sfxf
	 */
	public String getSfxf() {
		return sfxf;
	}
	/**
	 * @param sfxf要设置的 sfxf
	 */
	public void setSfxf(String sfxf) {
		this.sfxf = sfxf;
	}
	/**
	 * @return the xdfs
	 */
	public String getXdfs() {
		return xdfs;
	}
	/**
	 * @param xdfs要设置的 xdfs
	 */
	public void setXdfs(String xdfs) {
		this.xdfs = xdfs;
	}
	/**
	 * @return the sfbt
	 */
	public String getSfbt() {
		return sfbt;
	}
	/**
	 * @param sfbt要设置的 sfbt
	 */
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
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
	 * @return the cxmc
	 */
	public String getCxmc() {
		return cxmc;
	}
	/**
	 * @param cxmc要设置的 cxmc
	 */
	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}
	/**
	 * @return the jdszContent
	 */
	public String getJdszContent() {
		return jdszContent;
	}
	/**
	 * @param jdszContent要设置的 jdszContent
	 */
	public void setJdszContent(String jdszContent) {
		this.jdszContent = jdszContent;
	}
	
	
}
