/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:39:17 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:39:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyJcszForm extends ActionForm{
	
	private String sqkg ;//申请开关 
	private String kssj ;//开始时间 
	private String jssj ;//结束时间 
	private String szid ;
	private String xmdm ;
	private String xmmc ;
	private String xn ;
	private String xq ;
	private String ffys ;
	private String ffksyf ;
	private String ffjsyf ;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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
	 * @return the szid
	 */
	public String getSzid() {
		return szid;
	}
	/**
	 * @param szid要设置的 szid
	 */
	public void setSzid(String szid) {
		this.szid = szid;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the ffys
	 */
	public String getFfys() {
		return ffys;
	}
	/**
	 * @param ffys要设置的 ffys
	 */
	public void setFfys(String ffys) {
		this.ffys = ffys;
	}
	/**
	 * @return the ffksyf
	 */
	public String getFfksyf() {
		return ffksyf;
	}
	/**
	 * @param ffksyf要设置的 ffksyf
	 */
	public void setFfksyf(String ffksyf) {
		this.ffksyf = ffksyf;
	}
	/**
	 * @return the ffjsyf
	 */
	public String getFfjsyf() {
		return ffjsyf;
	}
	/**
	 * @param ffjsyf要设置的 ffjsyf
	 */
	public void setFfjsyf(String ffjsyf) {
		this.ffjsyf = ffjsyf;
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
	
	

}
