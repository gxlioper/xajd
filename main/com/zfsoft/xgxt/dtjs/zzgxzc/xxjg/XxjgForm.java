/**
 * @部门:学工产品事业部
 * @日期：2017-1-24 下午03:59:25 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设--信息结果模块
 * @类功能描述: 信息结果Form类
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月10日 下午7:16:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XxjgForm extends ActionForm {
	
	private static final long serialVersionUID = -4446660519981399069L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String xh;
	private String jgid;
	private String szdzb;//所在党支部
	private String szdzbmc;//所在党支部名称
	private String sfsn;
	private String jsdzz;
	private String sqdw;
	private String dfjzrq;
	private String sfkjhyzm;
	private String jsxbh;
	private String sjly;
	private String lclyid;
	private String sqr;
	private String sqsj;
	private String type;
	
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
	 * @return the szdzb
	 */
	public String getSzdzb() {
		return szdzb;
	}
	/**
	 * @param szdzb要设置的 szdzb
	 */
	public void setSzdzb(String szdzb) {
		this.szdzb = szdzb;
	}
	/**
	 * @return the szdzbmc
	 */
	public String getSzdzbmc() {
		return szdzbmc;
	}
	/**
	 * @param szdzbmc要设置的 szdzbmc
	 */
	public void setSzdzbmc(String szdzbmc) {
		this.szdzbmc = szdzbmc;
	}
	/**
	 * @return the sfsn
	 */
	public String getSfsn() {
		return sfsn;
	}
	/**
	 * @param sfsn要设置的 sfsn
	 */
	public void setSfsn(String sfsn) {
		this.sfsn = sfsn;
	}
	/**
	 * @return the jsdzz
	 */
	public String getJsdzz() {
		return jsdzz;
	}
	/**
	 * @param jsdzz要设置的 jsdzz
	 */
	public void setJsdzz(String jsdzz) {
		this.jsdzz = jsdzz;
	}
	/**
	 * @return the sqdw
	 */
	public String getSqdw() {
		return sqdw;
	}
	/**
	 * @param sqdw要设置的 sqdw
	 */
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	/**
	 * @return the dfjzrq
	 */
	public String getDfjzrq() {
		return dfjzrq;
	}
	/**
	 * @param dfjzrq要设置的 dfjzrq
	 */
	public void setDfjzrq(String dfjzrq) {
		this.dfjzrq = dfjzrq;
	}
	/**
	 * @return the sfkjhyzm
	 */
	public String getSfkjhyzm() {
		return sfkjhyzm;
	}
	/**
	 * @param sfkjhyzm要设置的 sfkjhyzm
	 */
	public void setSfkjhyzm(String sfkjhyzm) {
		this.sfkjhyzm = sfkjhyzm;
	}
	/**
	 * @return the jsxbh
	 */
	public String getJsxbh() {
		return jsxbh;
	}
	/**
	 * @param jsxbh要设置的 jsxbh
	 */
	public void setJsxbh(String jsxbh) {
		this.jsxbh = jsxbh;
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
	 * @return the lclyid
	 */
	public String getLclyid() {
		return lclyid;
	}
	/**
	 * @param lclyid要设置的 lclyid
	 */
	public void setLclyid(String lclyid) {
		this.lclyid = lclyid;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
