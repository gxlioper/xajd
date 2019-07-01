/**
 * @部门:学工产品事业部
 * @日期：2015-6-1 上午09:01:43 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @类功能描述: 检查日程
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-1 上午09:01:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcrcModel extends ActionForm {

	
	private static final long serialVersionUID = 1430900341644686761L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xn;
	private String xq;
	private String rcmc;
	private String kssj;
	private String jssj;
	private String fslx;
	private String bz;
	private String tjzt;
	
	private String[] xmdm;
	
	
	/**
	 * @return the xmdm
	 */
	public String[] getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
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
	 * @return the rcmc
	 */
	public String getRcmc() {
		return rcmc;
	}
	/**
	 * @param rcmc要设置的 rcmc
	 */
	public void setRcmc(String rcmc) {
		this.rcmc = rcmc;
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
	 * @return the fslx
	 */
	public String getFslx() {
		return fslx;
	}
	/**
	 * @param fslx要设置的 fslx
	 */
	public void setFslx(String fslx) {
		this.fslx = fslx;
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
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}
	/**
	 * @param tjzt要设置的 tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

	
}
