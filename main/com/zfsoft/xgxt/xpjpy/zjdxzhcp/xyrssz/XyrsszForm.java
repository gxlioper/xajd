/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 上午09:30:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 学院人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-18 上午09:30:42 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyrsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private String xmdm;// 项目代码
	private String xn;// 学年
	private String xzdm;// 性质代码
	private String lxdm;// 类型代码
	private String xmmc;// 项目名称
	private String xmje;// 项目金额
	private String sqkg;// 申请开关
	private String rskzjb;// 人数控制级别
	private String jdkzjb;// 兼得控制级别
	private String rsfpfs;// 人数分配方式
	private String rsfpz;// 人数分配名额或比例
	private String rsfpnj;// 人数分配年级
	private String zcfpm;// 综测分排名方式
	private String sfysz;
	private String xydm;
	private String status;// 查询状态，0|未设置,1|已设置
	private String zd1;//操作人
	private String zd2;//操作时间
	
	private String[] grid_key;// 
	private String[] guids;// guid数组
	private String[] njs;// 年级数组
	private String[] xydms;//
	private String[] zydms;
	private String[] bjdms;
	private String[] cpzdms;
	private String[] zrss;
	private String[] zzmes;
	private String[] fpbls;
	private String[] jsrsHid;
	

	//============查询条件============
	private String zydm;
	private String bjdm;
	private String njq;
	private String cpzmc;//参评组名称
	
	
	/**
	 * @return the grid_key
	 */
	public String[] getGrid_key() {
		return grid_key;
	}
	/**
	 * @param gridKey要设置的 grid_key
	 */
	public void setGrid_key(String[] gridKey) {
		grid_key = gridKey;
	}
	/**
	 * @return the guids
	 */
	public String[] getGuids() {
		return guids;
	}
	/**
	 * @param guids要设置的 guids
	 */
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	/**
	 * @return the njs
	 */
	public String[] getNjs() {
		return njs;
	}
	/**
	 * @param njs要设置的 njs
	 */
	public void setNjs(String[] njs) {
		this.njs = njs;
	}
	/**
	 * @return the xydms
	 */
	public String[] getXydms() {
		return xydms;
	}
	/**
	 * @param xydms要设置的 xydms
	 */
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	/**
	 * @return the zydms
	 */
	public String[] getZydms() {
		return zydms;
	}
	/**
	 * @param zydms要设置的 zydms
	 */
	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}
	/**
	 * @return the bjdms
	 */
	public String[] getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}
	/**
	 * @return the cpzdms
	 */
	public String[] getCpzdms() {
		return cpzdms;
	}
	/**
	 * @param cpzdms要设置的 cpzdms
	 */
	public void setCpzdms(String[] cpzdms) {
		this.cpzdms = cpzdms;
	}
	/**
	 * @return the zrss
	 */
	public String[] getZrss() {
		return zrss;
	}
	/**
	 * @param zrss要设置的 zrss
	 */
	public void setZrss(String[] zrss) {
		this.zrss = zrss;
	}
	/**
	 * @return the zzmes
	 */
	public String[] getZzmes() {
		return zzmes;
	}
	/**
	 * @param zzmes要设置的 zzmes
	 */
	public void setZzmes(String[] zzmes) {
		this.zzmes = zzmes;
	}
	/**
	 * @return the fpbls
	 */
	public String[] getFpbls() {
		return fpbls;
	}
	/**
	 * @param fpbls要设置的 fpbls
	 */
	public void setFpbls(String[] fpbls) {
		this.fpbls = fpbls;
	}
	/**
	 * @return the jsrsHid
	 */
	public String[] getJsrsHid() {
		return jsrsHid;
	}
	/**
	 * @param jsrsHid要设置的 jsrsHid
	 */
	public void setJsrsHid(String[] jsrsHid) {
		this.jsrsHid = jsrsHid;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
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
	 * @return the njq
	 */
	public String getNjq() {
		return njq;
	}
	/**
	 * @param njq要设置的 njq
	 */
	public void setNjq(String njq) {
		this.njq = njq;
	}
	/**
	 * @return the cpzmc
	 */
	public String getCpzmc() {
		return cpzmc;
	}
	/**
	 * @param cpzmc要设置的 cpzmc
	 */
	public void setCpzmc(String cpzmc) {
		this.cpzmc = cpzmc;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the sfysz
	 */
	public String getSfysz() {
		return sfysz;
	}
	/**
	 * @param sfysz要设置的 sfysz
	 */
	public void setSfysz(String sfysz) {
		this.sfysz = sfysz;
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
}
