/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午06:13:18 
 */  
package com.zfsoft.xgxt.axcs.wpsz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午06:13:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WpszForm extends ActionForm{
	private FormFile xmtp;
	private String xmdm;//项目代码
	private String xmlb;//项目类别
	private String xmlbmc;
	private String xmmc;//项目名称
	private String xn;
	private String xmxxjs;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String shkg;
	private String shkssj;
	private String shjssj;
	private String splc;
	private String jbsz;
	private String tjsz;
	private User user;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
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
	 * @return the xmxxjs
	 */
	public String getXmxxjs() {
		return xmxxjs;
	}
	/**
	 * @param xmxxjs要设置的 xmxxjs
	 */
	public void setXmxxjs(String xmxxjs) {
		this.xmxxjs = xmxxjs;
	}
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
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
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
	 * @return the xmtp
	 */
	public FormFile getXmtp() {
		return xmtp;
	}
	/**
	 * @param xmtp要设置的 xmtp
	 */
	public void setXmtp(FormFile xmtp) {
		this.xmtp = xmtp;
	}
	/**
	 * @return the jbsz
	 */
	public String getJbsz() {
		return jbsz;
	}
	/**
	 * @param jbsz要设置的 jbsz
	 */
	public void setJbsz(String jbsz) {
		this.jbsz = jbsz;
	}
	/**
	 * @return the tjsz
	 */
	public String getTjsz() {
		return tjsz;
	}
	/**
	 * @param tjsz要设置的 tjsz
	 */
	public void setTjsz(String tjsz) {
		this.tjsz = tjsz;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the xmlbmc
	 */
	public String getXmlbmc() {
		return xmlbmc;
	}
	/**
	 * @param xmlbmc要设置的 xmlbmc
	 */
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	

}
