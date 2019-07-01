/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:41:01 
 */  
package com.zfsoft.xgxt.rcsw.sztz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


public class SzxfModel extends ActionForm {

	
	private static final long serialVersionUID = 7426339785751241000L;
	
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	
	private String id;
	private String xh ;
	private String xn;
	private String xq;
	private String xqmc;
	private String tzxm ;
	private String tzjb;
	private String xf;
	private String mc;
	private String qrr;
	private String qrsj;
	private String shr;
	private String lrsj;
	
	
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
	 * @return the tzxm
	 */
	public String getTzxm() {
		return tzxm;
	}
	/**
	 * @param tzxm要设置的 tzxm
	 */
	public void setTzxm(String tzxm) {
		this.tzxm = tzxm;
	}
	/**
	 * @return the tzjb
	 */
	public String getTzjb() {
		return tzjb;
	}
	/**
	 * @param tzjb要设置的 tzjb
	 */
	public void setTzjb(String tzjb) {
		this.tzjb = tzjb;
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
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @return the qrr
	 */
	public String getQrr() {
		return qrr;
	}
	/**
	 * @param qrr要设置的 qrr
	 */
	public void setQrr(String qrr) {
		this.qrr = qrr;
	}
	/**
	 * @return the qrsj
	 */
	public String getQrsj() {
		return qrsj;
	}
	/**
	 * @param qrsj要设置的 qrsj
	 */
	public void setQrsj(String qrsj) {
		this.qrsj = qrsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	

}
