/**
 * @部门:学工产品事业部
 * @日期：2016-6-8 下午02:37:19 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-6-8 下午02:37:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XshdglForm extends ActionForm {
	 private String sqid;
	 private String hdmc;
	 private String hdsj;
	 private String hddd;
	 private String zbdwdm;
	 private String xbdwdm;
	 private String cbdwdm;
	 private String hdfzr;
	 private String cyry;
	 private String hdjj;
	 private String filepath;
	 private String type;
	 private String hdlx;
	 private SearchModel searchModel = new SearchModel();
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	 * @return the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @param hdmc要设置的 hdmc
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @return the hdsj
	 */
	public String getHdsj() {
		return hdsj;
	}
	/**
	 * @param hdsj要设置的 hdsj
	 */
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hddd要设置的 hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @return the zbdwdm
	 */
	public String getZbdwdm() {
		return zbdwdm;
	}
	/**
	 * @param zbdwdm要设置的 zbdwdm
	 */
	public void setZbdwdm(String zbdwdm) {
		this.zbdwdm = zbdwdm;
	}
	/**
	 * @return the xbdwdm
	 */
	public String getXbdwdm() {
		return xbdwdm;
	}
	/**
	 * @param xbdwdm要设置的 xbdwdm
	 */
	public void setXbdwdm(String xbdwdm) {
		this.xbdwdm = xbdwdm;
	}
	/**
	 * @return the cbdwdm
	 */
	public String getCbdwdm() {
		return cbdwdm;
	}
	/**
	 * @param cbdwdm要设置的 cbdwdm
	 */
	public void setCbdwdm(String cbdwdm) {
		this.cbdwdm = cbdwdm;
	}
	/**
	 * @return the hdfzr
	 */
	public String getHdfzr() {
		return hdfzr;
	}
	/**
	 * @param hdfzr要设置的 hdfzr
	 */
	public void setHdfzr(String hdfzr) {
		this.hdfzr = hdfzr;
	}
	/**
	 * @return the cyry
	 */
	public String getCyry() {
		return cyry;
	}
	/**
	 * @param cyry要设置的 cyry
	 */
	public void setCyry(String cyry) {
		this.cyry = cyry;
	}
	/**
	 * @return the hdjj
	 */
	public String getHdjj() {
		return hdjj;
	}
	/**
	 * @param hdjj要设置的 hdjj
	 */
	public void setHdjj(String hdjj) {
		this.hdjj = hdjj;
	}
	/**
	 * @return the filePath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filePath要设置的 filePath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the hdlx
	 */
	public String getHdlx() {
		return hdlx;
	}
	/**
	 * @param hdlx要设置的 hdlx
	 */
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
}
