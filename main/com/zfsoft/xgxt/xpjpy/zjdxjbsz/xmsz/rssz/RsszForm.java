/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 上午09:26:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-1 上午09:26:42 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RsszForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid;// 
	private String xmdm;// 项目代码
	private String bmdm;// 部门代码
	private String nj;// 年级
	private String fpbl;// 比例
	private String zzme;// 最终人数
	private String czfs;//操作方式
	private String zd1;
	private String zd2;

	private String rsfpfs;// 人数控制范围
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
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//人数是否已设置
	private String cpzmc;//参评组名称
	//============查询条件============
	private String status;// 查询状态，0|未设置,1|已设置
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the fpbl
	 */
	public String getFpbl() {
		return fpbl;
	}
	/**
	 * @param fpbl要设置的 fpbl
	 */
	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}
	/**
	 * @return the zzme
	 */
	public String getZzme() {
		return zzme;
	}
	/**
	 * @param zzme要设置的 zzme
	 */
	public void setZzme(String zzme) {
		this.zzme = zzme;
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
}
