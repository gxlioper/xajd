/**
 * @部门:学工产品事业部
 * @日期：2014-12-5 下午02:20:48 
 */  
package com.zfsoft.xgxt.axcs.wpsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-5 下午02:20:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WpsqForm extends ActionForm{
	
	private String sqid;
	private String xn;
	private String xh;
	private String xmlb;
	private String xmdm;
	private String xmmc;
	private String sqsj;
	private String sqly;
	private String shzt;
	private String splc;
	private String sqr;
	private String sqzt;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the xmlb
	 */
	public String getXmlb() {
		return xmlb;
	}
	/**
	 * @param xmlb要设置的 xmlb
	 */
	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}
	/**
	 * @return the sqzt
	 */
	public String getSqzt() {
		return sqzt;
	}
	/**
	 * @param sqzt要设置的 sqzt
	 */
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	
	

}
