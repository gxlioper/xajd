/**
 * @部门:学工产品事业部
 * @日期：2016-4-18 下午01:57:33 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-18 下午01:57:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LkxxForm extends ActionForm{
	private String id;
	private String xm;
	private String sfzh;
	private String xb;
	private String hkszd;
	private String rzld;
	private String rzfj;
	private String rzcw;
	private String rzyj;
	private String rzsj;
	private String tssj;
	private String bz;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the hkszd
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * @param hkszd要设置的 hkszd
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * @return the rzld
	 */
	public String getRzld() {
		return rzld;
	}
	/**
	 * @param rzld要设置的 rzld
	 */
	public void setRzld(String rzld) {
		this.rzld = rzld;
	}
	/**
	 * @return the rzfj
	 */
	public String getRzfj() {
		return rzfj;
	}
	/**
	 * @param rzfj要设置的 rzfj
	 */
	public void setRzfj(String rzfj) {
		this.rzfj = rzfj;
	}
	/**
	 * @return the rzcw
	 */
	public String getRzcw() {
		return rzcw;
	}
	/**
	 * @param rzcw要设置的 rzcw
	 */
	public void setRzcw(String rzcw) {
		this.rzcw = rzcw;
	}
	/**
	 * @return the rzyj
	 */
	public String getRzyj() {
		return rzyj;
	}
	/**
	 * @param rzyj要设置的 rzyj
	 */
	public void setRzyj(String rzyj) {
		this.rzyj = rzyj;
	}
	/**
	 * @return the rzsj
	 */
	public String getRzsj() {
		return rzsj;
	}
	/**
	 * @param rzsj要设置的 rzsj
	 */
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	/**
	 * @return the tssj
	 */
	public String getTssj() {
		return tssj;
	}
	/**
	 * @param tssj要设置的 tssj
	 */
	public void setTssj(String tssj) {
		this.tssj = tssj;
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
	
	
	
}
