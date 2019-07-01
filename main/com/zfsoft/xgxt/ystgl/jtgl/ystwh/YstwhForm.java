/**
 * @部门:学工产品事业部
 * @日期：2015-8-4 上午11:38:01 
 */  
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstwhForm extends ActionForm{
	
	private String ystid;
	private String id;
	private String xn;
	private String xq;
	private String xqmc;
	private String ystxmmc;
	private String ystlbdm;
	private String xmlbdm;
	private String gkdwdm;//挂靠单位
	private String kssj;
	private String jssj;
	private String fzrlb;//负责人类别
	private String fzr;//社团负责人
	private String zdls;//指导老师
	private String sqsj;
	private String lxdh;
	private String jtr;//建团人
	private String stsm;//社团说明
	private String sjly;
	private String shzt;
	private String sqkssj;
	private String sqjssj;
	private String sqkg;
	private String splc;
	private String zdlszc;
	private String zdlslxfs;
	private String ssbm;
	private String ystclsj;
	private String ysthjqk;
	private String ystjj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the zdlszc
	 */
	public String getZdlszc() {
		return zdlszc;
	}
	/**
	 * @param zdlszc要设置的 zdlszc
	 */
	public void setZdlszc(String zdlszc) {
		this.zdlszc = zdlszc;
	}
	/**
	 * @return the zdlslxfs
	 */
	public String getZdlslxfs() {
		return zdlslxfs;
	}
	/**
	 * @param zdlslxfs要设置的 zdlslxfs
	 */
	public void setZdlslxfs(String zdlslxfs) {
		this.zdlslxfs = zdlslxfs;
	}
	/**
	 * @return the ssbm
	 */
	public String getSsbm() {
		return ssbm;
	}
	/**
	 * @param ssbm要设置的 ssbm
	 */
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}
	/**
	 * @return the stclsj
	 */
	public String getYstclsj() {
		return ystclsj;
	}
	/**
	 * @param stclsj要设置的 stclsj
	 */
	public void setYstclsj(String ystclsj) {
		this.ystclsj = ystclsj;
	}
	/**
	 * @return the sthjqk
	 */
	public String getYsthjqk() {
		return ysthjqk;
	}
	/**
	 * @param sthjqk要设置的 sthjqk
	 */
	public void setYsthjqk(String ysthjqk) {
		this.ysthjqk = ysthjqk;
	}

	/**
	 * @return the ystid
	 */
	public String getYstid() {
		return ystid;
	}
	/**
	 * @param ystid要设置的 ystid
	 */
	public void setYstid(String ystid) {
		this.ystid = ystid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	
	public String getystlbdm() {
		return ystlbdm;
	}
	public void setystlbdm(String ystlbdm) {
		this.ystlbdm = ystlbdm;
	}
	public String getXmlbdm() {
		return xmlbdm;
	}
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getFzrlb() {
		return fzrlb;
	}
	public void setFzrlb(String fzrlb) {
		this.fzrlb = fzrlb;
	}
	
	public String getZdls() {
		return zdls;
	}
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getJtr() {
		return jtr;
	}
	public void setJtr(String jtr) {
		this.jtr = jtr;
	}
	public String getStsm() {
		return stsm;
	}
	public void setStsm(String stsm) {
		this.stsm = stsm;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the ystxmmc
	 */
	public String getYstxmmc() {
		return ystxmmc;
	}
	/**
	 * @param ystxmmc要设置的 ystxmmc
	 */
	public void setYstxmmc(String ystxmmc) {
		this.ystxmmc = ystxmmc;
	}
	/**
	 * @return the ystlbdm
	 */
	public String getYstlbdm() {
		return ystlbdm;
	}
	/**
	 * @param ystlbdm要设置的 ystlbdm
	 */
	public void setYstlbdm(String ystlbdm) {
		this.ystlbdm = ystlbdm;
	}
	/**
	 * @return the gkdwdm
	 */
	public String getGkdwdm() {
		return gkdwdm;
	}
	/**
	 * @param gkdwdm要设置的 gkdwdm
	 */
	public void setGkdwdm(String gkdwdm) {
		this.gkdwdm = gkdwdm;
	}
	/**
	 * @return the fzr
	 */
	public String getFzr() {
		return fzr;
	}
	/**
	 * @param fzr要设置的 fzr
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	/**
	 * @return the ystjj
	 */
	public String getYstjj() {
		return ystjj;
	}
	/**
	 * @param ystjj要设置的 ystjj
	 */
	public void setYstjj(String ystjj) {
		this.ystjj = ystjj;
	}
	
	
	

}
