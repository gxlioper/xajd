/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 下午06:00:19 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stzhwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-14 下午06:00:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StzhwhForm extends ActionForm {
	
	 private String xh;
	 private String stid ;
	 private String id ;
	 private String sjly;
	 private String xq;
	 private String xn;
	 private String cjpd;
	 private String type;
	 private String rtid;
	//杭州职业学院个性化
	private String stxj;//社团星级

	public String getStxj() {
		return stxj;
	}

	public void setStxj(String stxj) {
		this.stxj = stxj;
	}

	/**
	 * @return the rtid
	 */
	public String getRtid() {
		return rtid;
	}
	/**
	 * @param rtid要设置的 rtid
	 */
	public void setRtid(String rtid) {
		this.rtid = rtid;
	}
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
	 * @return the stid
	 */
	public String getStid() {
		return stid;
	}
	/**
	 * @param stid要设置的 stid
	 */
	public void setStid(String stid) {
		this.stid = stid;
	}
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
	 * @return the cjpd
	 */
	public String getCjpd() {
		return cjpd;
	}
	/**
	 * @param cjpd要设置的 cjpd
	 */
	public void setCjpd(String cjpd) {
		this.cjpd = cjpd;
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
}
