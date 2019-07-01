/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午03:10:43 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: 考核结果
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 下午03:10:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JgcxForm extends ActionForm {
	
	private static final long serialVersionUID = 1584857913791763482L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	
	private String xmid;
	private String khlx;
	private String xmmc;
	private String khdxid;
	private String kssj;
	private String jssj;
	private String xmms;
	private String khdxr;
	private String bmdm;
	private String pflx;
	private String pfzt;
	private String xmszid;

	
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
	 * @return the khlx
	 */
	public String getKhlx() {
		return khlx;
	}
	/**
	 * @param khlx要设置的 khlx
	 */
	public void setKhlx(String khlx) {
		this.khlx = khlx;
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
	 * @return the khdxid
	 */
	public String getKhdxid() {
		return khdxid;
	}
	/**
	 * @param khdxid要设置的 khdxid
	 */
	public void setKhdxid(String khdxid) {
		this.khdxid = khdxid;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssj要设置的 kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmms要设置的 xmms
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
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
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	/**
	 * @return the pflx
	 */
	public String getPflx() {
		return pflx;
	}
	/**
	 * @param pflx要设置的 pflx
	 */
	public void setPflx(String pflx) {
		this.pflx = pflx;
	}
	/**
	 * @return the pfzt
	 */
	public String getPfzt() {
		return pfzt;
	}
	/**
	 * @param pfzt要设置的 pfzt
	 */
	public void setPfzt(String pfzt) {
		this.pfzt = pfzt;
	}
	/**
	 * @return the xmszid
	 */
	public String getXmszid() {
		return xmszid;
	}
	/**
	 * @param xmszid要设置的 xmszid
	 */
	public void setXmszid(String xmszid) {
		this.xmszid = xmszid;
	}

}
