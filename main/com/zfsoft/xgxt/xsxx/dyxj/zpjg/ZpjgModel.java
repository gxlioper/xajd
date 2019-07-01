/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 下午01:59:15 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 自评结果
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 下午01:59:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpjgModel extends ActionForm{

	private static final long serialVersionUID = -4427242453796115424L;

	
	private String id ;
	private String xh ;
	private String xn ;
	private String xq ;
	private String djdm ;
	private String zpnr ;
	private String pysj ;
	private String sjlyywid ;
	private String sjly ;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xqmc;
	private String djmc;
	private String bjdm;
	private String xydm;
	private String xymc;
	
	
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the djmc
	 */
	public String getDjmc() {
		return djmc;
	}
	/**
	 * @param djmc要设置的 djmc
	 */
	public void setDjmc(String djmc) {
		this.djmc = djmc;
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
	 * @return the djdm
	 */
	public String getDjdm() {
		return djdm;
	}
	/**
	 * @param djdm要设置的 djdm
	 */
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	/**
	 * @return the zpnr
	 */
	public String getZpnr() {
		return zpnr;
	}
	/**
	 * @param zpnr要设置的 zpnr
	 */
	public void setZpnr(String zpnr) {
		this.zpnr = zpnr;
	}
	/**
	 * @return the pysj
	 */
	public String getPysj() {
		return pysj;
	}
	/**
	 * @param pysj要设置的 pysj
	 */
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	/**
	 * @return the sjlyywid
	 */
	public String getSjlyywid() {
		return sjlyywid;
	}
	/**
	 * @param sjlyywid要设置的 sjlyywid
	 */
	public void setSjlyywid(String sjlyywid) {
		this.sjlyywid = sjlyywid;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
