/**
 * @部门:学工产品1部
 * @日期：2017-4-7 上午10:59:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优_项目设置_项目维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-7 上午10:57:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String xmdm;// 项目代码
	private String xn;// 学年
	private String xzdm;// 性质代码
	private String lxdm;// 类型代码
	private String xmmc;// 项目名称
	private String ywmc;// 英文名称
	private String xmje;// 项目金额
	private String xssx;// 显示顺序
	private String shlc;// 审核流程
	private String sqkg;// 申请开关
	private String sqkssj;// 申请开始时间
	private String sqjssj;// 申请结束时间
	private String shkg;// 审核开关
	private String shkssj;// 审核开始时间
	private String shjssj;// 审核结束时间
	private String kgbz;// 开关备注
	private String djb;// 登记表
	private String sbb;// 上报表
	private String xmsm;// 项目说明
	private String czfs; //操作方式
	private String rskzjb;// 人数控制级别
	private String jdkzjb;// 兼得控制级别
	private String rsfpfs;// 人数分配方式
	private String rsfpz;// 人数分配名额或比例
	private String rsfpnj;// 人数分配年级
	private String zcfpm;// 综测分排名方式
	private String xmdyb;// 用于增加一个项目时，新增一个空表格，在申请时方便学生下载打印【项目打印表】
	private String sfrssz;//用于区别项目是否要被设置人数，因为有的项目是不需要人数设置的【是否人数设置，0：不需要设置、1：需要设置】
	
	
	
	/**
	 * @return the jdkzjb
	 */
	public String getJdkzjb() {
		return jdkzjb;
	}
	/**
	 * @param jdkzjb要设置的 jdkzjb
	 */
	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}
	/**
	 * @return the rsfpfs
	 */
	public String getRsfpfs() {
		return rsfpfs;
	}
	/**
	 * @param rsfpfs要设置的 rsfpfs
	 */
	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}
	/**
	 * @return the rsfpz
	 */
	public String getRsfpz() {
		return rsfpz;
	}
	/**
	 * @param rsfpz要设置的 rsfpz
	 */
	public void setRsfpz(String rsfpz) {
		this.rsfpz = rsfpz;
	}
	/**
	 * @return the rsfpnj
	 */
	public String getRsfpnj() {
		return rsfpnj;
	}
	/**
	 * @param rsfpnj要设置的 rsfpnj
	 */
	public void setRsfpnj(String rsfpnj) {
		this.rsfpnj = rsfpnj;
	}
	/**
	 * @return the zcfpm
	 */
	public String getZcfpm() {
		return zcfpm;
	}
	/**
	 * @param zcfpm要设置的 zcfpm
	 */
	public void setZcfpm(String zcfpm) {
		this.zcfpm = zcfpm;
	}
	/**
	 * @return the czfs
	 */
	public String getCzfs() {
		return czfs;
	}
	/**
	 * @param czfs要设置的 czfs
	 */
	public void setCzfs(String czfs) {
		this.czfs = czfs;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjb要设置的 rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the xmsm
	 */
	public String getXmsm() {
		return xmsm;
	}
	/**
	 * @param xmsm要设置的 xmsm
	 */
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
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
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdm要设置的 xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
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
	 * @return the ywmc
	 */
	public String getYwmc() {
		return ywmc;
	}
	/**
	 * @param ywmc要设置的 ywmc
	 */
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmje要设置的 xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}
	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}
	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
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
	 * @return the kgbz
	 */
	public String getKgbz() {
		return kgbz;
	}
	/**
	 * @param kgbz要设置的 kgbz
	 */
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	/**
	 * @return the djb
	 */
	public String getDjb() {
		return djb;
	}
	/**
	 * @param djb要设置的 djb
	 */
	public void setDjb(String djb) {
		this.djb = djb;
	}
	/**
	 * @return the sbb
	 */
	public String getSbb() {
		return sbb;
	}
	/**
	 * @param sbb要设置的 sbb
	 */
	public void setSbb(String sbb) {
		this.sbb = sbb;
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
	/**
	 * @return the xmdyb
	 */
	public String getXmdyb() {
		return xmdyb;
	}
	/**
	 * @param xmdyb要设置的 xmdyb
	 */
	public void setXmdyb(String xmdyb) {
		this.xmdyb = xmdyb;
	}
	/**
	 * @return the sfrssz
	 */
	public String getSfrssz() {
		return sfrssz;
	}
	/**
	 * @param sfrssz要设置的 sfrssz
	 */
	public void setSfrssz(String sfrssz) {
		this.sfrssz = sfrssz;
	}
}
