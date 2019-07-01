/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午04:48:10 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-8-18 下午04:48:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HdkhglForm extends ActionForm {
	 private String id;
	 private String hdjgid;
	 private String xh;
	 private String xmmc;
	 private String xn;
	 private String xq;
	 private String sfcj;
	 private String qqyy;
	 private SearchModel searchModel = new SearchModel();
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 private String type;
	 private String[] ids;
	 private String[] hdjgids;
	 private String[] xhs;
	 private String[] xmmcs;
	 private String[] xns;
	 private String[] xqs;
	 /**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the hdjgids
	 */
	public String[] getHdjgids() {
		return hdjgids;
	}
	/**
	 * @param hdjgids要设置的 hdjgids
	 */
	public void setHdjgids(String[] hdjgids) {
		this.hdjgids = hdjgids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the xmmcs
	 */
	public String[] getXmmcs() {
		return xmmcs;
	}
	/**
	 * @param xmmcs要设置的 xmmcs
	 */
	public void setXmmcs(String[] xmmcs) {
		this.xmmcs = xmmcs;
	}
	/**
	 * @return the xns
	 */
	public String[] getXns() {
		return xns;
	}
	/**
	 * @param xns要设置的 xns
	 */
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	/**
	 * @return the xqs
	 */
	public String[] getXqs() {
		return xqs;
	}
	/**
	 * @param xqs要设置的 xqs
	 */
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
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
	 * @return the hdjgid
	 */
	public String getHdjgid() {
		return hdjgid;
	}
	/**
	 * @param hdjgid要设置的 hdjgid
	 */
	public void setHdjgid(String hdjgid) {
		this.hdjgid = hdjgid;
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
	 * @return the sfcj
	 */
	public String getSfcj() {
		return sfcj;
	}
	/**
	 * @param sfcj要设置的 sfcj
	 */
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	/**
	 * @return the qqyy
	 */
	public String getQqyy() {
		return qqyy;
	}
	/**
	 * @param qqyy要设置的 qqyy
	 */
	public void setQqyy(String qqyy) {
		this.qqyy = qqyy;
	}
	/**
	 * @return the jlygx
	 */
	public String getJlygx() {
		return jlygx;
	}
	/**
	 * @param jlygx要设置的 jlygx
	 */
	public void setJlygx(String jlygx) {
		this.jlygx = jlygx;
	}
	private String jlygx;
}
