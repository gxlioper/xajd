/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:06:50 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:06:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxjgForm extends ActionForm {
	private String jgid;
	private String lylcywid;
	private String sjly;
	private String xh;
	private String xn;
	private String xq;
	private String jhrxm;
	private String jhrlxfs;
	private String sflx;
	private String lxsj;
	private String lxjtgjdm;
	private String lxcchb;
	private String mddssx;
	private String fxsj;
	private String fxjtgjdm;
	private String fxcchb;
	private String bz;
	private String sqsj;
	private String type;
	private String lxlx;//离校类型
	private String sflxdm;//是否离校
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private static final long serialVersionUID = 1L;

	public String getLxlx() {
		return lxlx;
	}

	public void setLxlx(String lxlx) {
		this.lxlx = lxlx;
	}

	public String getSflxdm() {
		return sflxdm;
	}

	public void setSflxdm(String sflxdm) {
		this.sflxdm = sflxdm;
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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}
	/**
	 * @param jhrxm要设置的 jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}
	/**
	 * @param jhrlxfs要设置的 jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}
	/**
	 * @return the sflx
	 */
	public String getSflx() {
		return sflx;
	}
	/**
	 * @param sflx要设置的 sflx
	 */
	public void setSflx(String sflx) {
		this.sflx = sflx;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsj要设置的 lxsj
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the lxjtgjdm
	 */
	public String getLxjtgjdm() {
		return lxjtgjdm;
	}
	/**
	 * @param lxjtgjdm要设置的 lxjtgjdm
	 */
	public void setLxjtgjdm(String lxjtgjdm) {
		this.lxjtgjdm = lxjtgjdm;
	}
	/**
	 * @return the lxcchb
	 */
	public String getLxcchb() {
		return lxcchb;
	}
	/**
	 * @param lxcchb要设置的 lxcchb
	 */
	public void setLxcchb(String lxcchb) {
		this.lxcchb = lxcchb;
	}
	

	/**
	 * @return the mddssx
	 */
	public String getMddssx() {
		return mddssx;
	}
	/**
	 * @param mddssx要设置的 mddssx
	 */
	public void setMddssx(String mddssx) {
		this.mddssx = mddssx;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @param fxsj要设置的 fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjtgjdm
	 */
	public String getFxjtgjdm() {
		return fxjtgjdm;
	}
	/**
	 * @param fxjtgjdm要设置的 fxjtgjdm
	 */
	public void setFxjtgjdm(String fxjtgjdm) {
		this.fxjtgjdm = fxjtgjdm;
	}
	/**
	 * @return the fxcchb
	 */
	public String getFxcchb() {
		return fxcchb;
	}
	/**
	 * @param fxcchb要设置的 fxcchb
	 */
	public void setFxcchb(String fxcchb) {
		this.fxcchb = fxcchb;
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

}
