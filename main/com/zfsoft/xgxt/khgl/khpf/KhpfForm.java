/**
 * @部门:学工产品事业部
 * @日期：2015-8-12 下午02:35:57 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核评分
 * @类功能描述: Form
 * @作者：cq [工号:785]
 * @时间： 2015-8-12 下午02:35:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KhpfForm extends ActionForm {
	
	private static final long serialVersionUID = -4285619768768178920L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String pfid;
	private String xmid;
	private String xmmc;
	private String khbid;
	private String khbmc;
	private String pfr;
	private String sftj;
	private String sfyx;
	private String khdxr;
	private String khlx;
	private String zbmxid;
	private String fs;
	private String xmszid;
	private String yjjy;
	private String bz;
	private String shzt;
	private String xn;
	private String pflx;

	private String [] zbmxidArr;
	private String [] fsArr;
	
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
	 * @return the pfid
	 */
	public String getPfid() {
		return pfid;
	}
	/**
	 * @param pfid要设置的 pfid
	 */
	public void setPfid(String pfid) {
		this.pfid = pfid;
	}
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the khbid
	 */
	public String getKhbid() {
		return khbid;
	}
	/**
	 * @param khbid要设置的 khbid
	 */
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	/**
	 * @return the pfr
	 */
	public String getPfr() {
		return pfr;
	}
	/**
	 * @param pfr要设置的 pfr
	 */
	public void setPfr(String pfr) {
		this.pfr = pfr;
	}
	/**
	 * @return the sftj
	 */
	public String getSftj() {
		return sftj;
	}
	/**
	 * @param sftj要设置的 sftj
	 */
	public void setSftj(String sftj) {
		this.sftj = sftj;
	}
	/**
	 * @return the sfyx
	 */
	public String getSfyx() {
		return sfyx;
	}
	/**
	 * @param sfyx要设置的 sfyx
	 */
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	/**
	 * @return the khdxr
	 */
	public String getKhdxr() {
		return khdxr;
	}
	/**
	 * @param khdxr要设置的 khdxr
	 */
	public void setKhdxr(String khdxr) {
		this.khdxr = khdxr;
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
	 * @return the khlx
	 */
	public String getKhlx() {
		return khlx;
	}
	/**
	 * @param khlx要设置的 khlx
	 */
	public void setKhlx(String khlx) {
		this.khlx = khlx;
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
	 * @return the khbmc
	 */
	public String getKhbmc() {
		return khbmc;
	}
	/**
	 * @param khbmc要设置的 khbmc
	 */
	public void setKhbmc(String khbmc) {
		this.khbmc = khbmc;
	}
	/**
	 * @return the zbmxid
	 */
	public String getZbmxid() {
		return zbmxid;
	}
	/**
	 * @param zbmxid要设置的 zbmxid
	 */
	public void setZbmxid(String zbmxid) {
		this.zbmxid = zbmxid;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the xmszid
	 */
	public String getXmszid() {
		return xmszid;
	}
	/**
	 * @param xmszid要设置的 xmszid
	 */
	public void setXmszid(String xmszid) {
		this.xmszid = xmszid;
	}
	/**
	 * @return the yjjy
	 */
	public String getYjjy() {
		return yjjy;
	}
	/**
	 * @param yjjy要设置的 yjjy
	 */
	public void setYjjy(String yjjy) {
		this.yjjy = yjjy;
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
	 * @return the pflx
	 */
	public String getPflx() {
		return pflx;
	}
	/**
	 * @param pflx要设置的 pflx
	 */
	public void setPflx(String pflx) {
		this.pflx = pflx;
	}

	public String[] getZbmxidArr() {
		return zbmxidArr;
	}

	public void setZbmxidArr(String[] zbmxidArr) {
		this.zbmxidArr = zbmxidArr;
	}

	public String[] getFsArr() {
		return fsArr;
	}

	public void setFsArr(String[] fsArr) {
		this.fsArr = fsArr;
	}
}
