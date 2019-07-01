/**
 * @部门:学工产品事业部
 * @日期：2016-3-18 下午02:11:24 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-18 下午02:11:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqkjgForm extends ActionForm{
	private String jgid;
	private String xn;
	private String xq;
	private String xh;
	private String xqdm1;
	private String xqdm2;
	private String tbsj;
	private String tsxq;
	private String tsxqgyqk;
	private String txsj;
	private String txr;
	private String sjly;
	private String lylcywid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String clcj;
	
	private String wtjjcd;//北京中医药问题紧急程度
	
	
	/**
	 * @return the wtjjcd
	 */
	public String getWtjjcd() {
		return wtjjcd;
	}
	/**
	 * @param wtjjcd要设置的 wtjjcd
	 */
	public void setWtjjcd(String wtjjcd) {
		this.wtjjcd = wtjjcd;
	}
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
	 * @return the xqdm1
	 */
	public String getXqdm1() {
		return xqdm1;
	}
	/**
	 * @param xqdm1要设置的 xqdm1
	 */
	public void setXqdm1(String xqdm1) {
		this.xqdm1 = xqdm1;
	}
	/**
	 * @return the xqdm2
	 */
	public String getXqdm2() {
		return xqdm2;
	}
	/**
	 * @param xqdm2要设置的 xqdm2
	 */
	public void setXqdm2(String xqdm2) {
		this.xqdm2 = xqdm2;
	}
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}
	/**
	 * @param tbsj要设置的 tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
	}
	/**
	 * @return the tsxq
	 */
	public String getTsxq() {
		return tsxq;
	}
	/**
	 * @param tsxq要设置的 tsxq
	 */
	public void setTsxq(String tsxq) {
		this.tsxq = tsxq;
	}
	/**
	 * @return the tsxqgyqk
	 */
	public String getTsxqgyqk() {
		return tsxqgyqk;
	}
	/**
	 * @param tsxqgyqk要设置的 tsxqgyqk
	 */
	public void setTsxqgyqk(String tsxqgyqk) {
		this.tsxqgyqk = tsxqgyqk;
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
	/**
	 * @return the clcj
	 */
	public String getClcj() {
		return clcj;
	}
	/**
	 * @param clcj要设置的 clcj
	 */
	public void setClcj(String clcj) {
		this.clcj = clcj;
	}
	
	
	
}
