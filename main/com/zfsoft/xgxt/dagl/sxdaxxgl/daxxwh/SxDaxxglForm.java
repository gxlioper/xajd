/**
 * @部门:学工产品事业部
 * @日期：2016-8-17 下午02:06:36 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @作者：caopei[工号:1352]
 * @时间： 2016-8-17 下午02:06:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SxDaxxglForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	 private String bjid;
	 private String bjdm;
	private String daxxid;
	private String xh;
	private String kddh;
	private String yjdz;
	private String yjr;
	private String sj;
	private String bz;
	private String pj;
	
	 private String bjdms;
	 private String flag;
	 private String flag1;
	 private String xn;
	 private String xq;
	 
	 
	 private String bjrs;
	 private String whrs;
	 private String ywh;
	 
	/**
	 * @return the ywh
	 */
	public String getYwh() {
		return ywh;
	}
	/**
	 * @param ywh要设置的 ywh
	 */
	public void setYwh(String ywh) {
		this.ywh = ywh;
	}
	/**
	 * @return the bjid
	 */
	public String getBjid() {
		return bjid;
	}
	/**
	 * @param bjid要设置的 bjid
	 */
	public void setBjid(String bjid) {
		this.bjid = bjid;
	}
	/**
	 * @return the flag1
	 */
	public String getFlag1() {
		return flag1;
	}
	/**
	 * @param flag1要设置的 flag1
	 */
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	/**
	 * @return the flag
	 */
	 
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag要设置的 flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
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
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the xymc
	 */
	
	public String getBjrs() {
		return bjrs;
	}
	/**
	 * @param bjrs要设置的 bjrs
	 */
	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}
	/**
	 * @return the whrs
	 */
	public String getWhrs() {
		return whrs;
	}
	/**
	 * @param whrs要设置的 whrs
	 */
	public void setWhrs(String whrs) {
		this.whrs = whrs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
