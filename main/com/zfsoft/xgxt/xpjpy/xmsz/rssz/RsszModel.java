/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述:项目维护-人数设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class RsszModel extends ActionForm {

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
	private String sfyxgb;//是否优秀学生干部

	//============查询条件============
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//人数是否已设置
	private String cpzmc;//参评组名称
	//============查询条件============
	private String status;// 查询状态，0|未设置,1|已设置
	private String pycc;//培养层次

	
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

	public RsszModel() {
		super();
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

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String[] getCpzdms() {
		return cpzdms;
	}

	public void setCpzdms(String[] cpzdms) {
		this.cpzdms = cpzdms;
	}

	public String getFpbl() {
		return fpbl;
	}

	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}

	public String getZzme() {
		return zzme;
	}

	public void setZzme(String zzme) {
		this.zzme = zzme;
	}

	public String getRsfpfs() {
		return rsfpfs;
	}

	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}

	public String[] getGrid_key() {
		return grid_key;
	}

	public void setGrid_key(String[] gridKey) {
		grid_key = gridKey;
	}

	public String[] getNjs() {
		return njs;
	}

	public void setNjs(String[] njs) {
		this.njs = njs;
	}

	public String[] getXydms() {
		return xydms;
	}

	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}

	public String[] getZydms() {
		return zydms;
	}

	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}

	public String[] getBjdms() {
		return bjdms;
	}

	public String[] getGuids() {
		return guids;
	}

	public void setGuids(String[] guids) {
		this.guids = guids;
	}

	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}

	public String[] getZrss() {
		return zrss;
	}

	public void setZrss(String[] zrss) {
		this.zrss = zrss;
	}

	public String[] getZzmes() {
		return zzmes;
	}

	public void setZzmes(String[] zzmes) {
		this.zzmes = zzmes;
	}

	public String[] getFpbls() {
		return fpbls;
	}

	public void setFpbls(String[] fpbls) {
		this.fpbls = fpbls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setSfysz(String sfysz) {
		this.sfysz = sfysz;
	}

	public String getSfysz() {
		return sfysz;
	}

	public void setNjq(String njq) {
		this.njq = njq;
	}

	public String getNjq() {
		return njq;
	}

	public String getCpzmc() {
		return cpzmc;
	}

	public void setCpzmc(String cpzmc) {
		this.cpzmc = cpzmc;
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

	public String[] getJsrsHid() {
		return jsrsHid;
	}

	public void setJsrsHid(String[] jsrsHid) {
		this.jsrsHid = jsrsHid;
	}
	
	public String getSfyxgb() {
		return sfyxgb;
	}

	public void setSfyxgb(String sfyxgb) {
		this.sfyxgb = sfyxgb;
	}

	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}

	/**
	 * @param pycc要设置的 pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	
}
