/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午04:55:04 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行评语管理) 
 * @作者： CMJ [工号：913]
 * @时间： 2013-7-24 下午04:55:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpyForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String pk;
	private String xn;
	private String xq;
	private String xqmc;
	private String cxdjdm;
	private String cxdj;
	private String cxpy;
	private String xhs;
	private String xh;
	private String xm;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String cxdjmc;
	private String bzr;
	private String sjly;
	private String sqsj;
	
	
	
	
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the cxdj
	 */
	public String getCxdj() {
		return cxdj;
	}
	/**
	 * @param cxdj要设置的 cxdj
	 */
	public void setCxdj(String cxdj) {
		this.cxdj = cxdj;
	}
	/**
	 * @return the cxdjmc
	 */
	public String getCxdjmc() {
		return cxdjmc;
	}
	/**
	 * @param cxdjmc要设置的 cxdjmc
	 */
	public void setCxdjmc(String cxdjmc) {
		this.cxdjmc = cxdjmc;
	}
	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}
	/**
	 * @param pk要设置的 pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
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
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
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
	 * @return the cxdjdm
	 */
	public String getCxdjdm() {
		return cxdjdm;
	}
	/**
	 * @param cxdjdm要设置的 cxdjdm
	 */
	public void setCxdjdm(String cxdjdm) {
		this.cxdjdm = cxdjdm;
	}
	/**
	 * @return the cxpy
	 */
	public String getCxpy() {
		return cxpy;
	}
	/**
	 * @param cxpy要设置的 cxpy
	 */
	public void setCxpy(String cxpy) {
		this.cxpy = cxpy;
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
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzr() {
		return bzr;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
}
