/**
 * @部门:学工产品事业部
 * @日期：2016-8-19 下午05:01:04 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：linguoxia	[工号:1553]
 * @时间： 2016-8-19 下午05:01:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CqxxDaxxjgForm extends ActionForm{
	private String daxxid;	
	private String xn;
	private String xh;
	private String kddh;
	private String yjdz;
	private String yjr;
	private String sj;
	private String bz;
	private String pj;

	private String xjzt;//学籍状态
	private String dahh;//档案盒号
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the xjzt
	 */
	public String getXjzt() {
		return xjzt;
	}
	/**
	 * @param xjzt要设置的 xjzt
	 */
	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}
	/**
	 * @return the dahh
	 */
	public String getDahh() {
		return dahh;
	}
	/**
	 * @param dahh要设置的 dahh
	 */
	public void setDahh(String dahh) {
		this.dahh = dahh;
	}
	/**
	 * @return the daxxid
	 */
	public String getDaxxid() {
		return daxxid;
	}
	/**
	 * @param daxxid要设置的 daxxid
	 */
	public void setDaxxid(String daxxid) {
		this.daxxid = daxxid;
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
	 * @return the kddh
	 */
	public String getKddh() {
		return kddh;
	}
	/**
	 * @param kddh要设置的 kddh
	 */
	public void setKddh(String kddh) {
		this.kddh = kddh;
	}
	/**
	 * @return the yjdz
	 */
	public String getYjdz() {
		return yjdz;
	}
	/**
	 * @param yjdz要设置的 yjdz
	 */
	public void setYjdz(String yjdz) {
		this.yjdz = yjdz;
	}
	/**
	 * @return the yjr
	 */
	public String getYjr() {
		return yjr;
	}
	/**
	 * @param yjr要设置的 yjr
	 */
	public void setYjr(String yjr) {
		this.yjr = yjr;
	}
	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @param pj要设置的 pj
	 */
	public void setPj(String pj) {
		this.pj = pj;
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
	
}
