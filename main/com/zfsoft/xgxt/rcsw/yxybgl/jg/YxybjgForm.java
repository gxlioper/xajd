/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 下午05:12:50 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-24 下午05:12:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybjgForm extends ActionForm{
	private String jgid;
	private String xn;
	private String xq;
	private String yf;
	private String bygzkzqk;
	private String xsgzrd;
	private String xssxdt;
	private String xstsjgzjy;
	private String txsj;
	private String txr;
	private String xydm;
	private String sjly;
	private String lylcywid;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yf要设置的 yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	/**
	 * @return the bygzkzqk
	 */
	public String getBygzkzqk() {
		return bygzkzqk;
	}
	/**
	 * @param bygzkzqk要设置的 bygzkzqk
	 */
	public void setBygzkzqk(String bygzkzqk) {
		this.bygzkzqk = bygzkzqk;
	}
	/**
	 * @return the xsgzrd
	 */
	public String getXsgzrd() {
		return xsgzrd;
	}
	/**
	 * @param xsgzrd要设置的 xsgzrd
	 */
	public void setXsgzrd(String xsgzrd) {
		this.xsgzrd = xsgzrd;
	}
	/**
	 * @return the xssxdt
	 */
	public String getXssxdt() {
		return xssxdt;
	}
	/**
	 * @param xssxdt要设置的 xssxdt
	 */
	public void setXssxdt(String xssxdt) {
		this.xssxdt = xssxdt;
	}
	/**
	 * @return the xstsjgzjy
	 */
	public String getXstsjgzjy() {
		return xstsjgzjy;
	}
	/**
	 * @param xstsjgzjy要设置的 xstsjgzjy
	 */
	public void setXstsjgzjy(String xstsjgzjy) {
		this.xstsjgzjy = xstsjgzjy;
	}
	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}
	/**
	 * @param txsj要设置的 txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}
	/**
	 * @return the txr
	 */
	public String getTxr() {
		return txr;
	}
	/**
	 * @param txr要设置的 txr
	 */
	public void setTxr(String txr) {
		this.txr = txr;
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
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	
	
}
