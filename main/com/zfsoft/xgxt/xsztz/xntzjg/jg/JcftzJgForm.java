/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 上午11:13:12 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-31 上午11:13:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzJgForm extends ActionForm{
	private String jgid;
	private String xmdm;
	private String xh;
	private String tzhjcf;
	private String jxdm;
	private String sfqq;
	private String lylcywid;
	private String sjly;
	private String type;
	private String xfrdjgzt;
	private String[] xhs;
	private String[] jxdms;
	private String[] sfqqs;
	private String[] tzhjcfs;
	private String[] jgids;
	private String csms;
	private String[] csmss;
	private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
	
    private String bz1;
    private String bz2;
    private String bz3;
    private String bz4;
    private String bz5;
    
    private String[] bz1s;
    private String[] bz2s;
    private String[] bz3s;
    private String[] bz4s;
    private String[] bz5s;
    
    
	/**
	 * @return the bz1s
	 */
	public String[] getBz1s() {
		return bz1s;
	}
	/**
	 * @param bz1s要设置的 bz1s
	 */
	public void setBz1s(String[] bz1s) {
		this.bz1s = bz1s;
	}
	/**
	 * @return the bz2s
	 */
	public String[] getBz2s() {
		return bz2s;
	}
	/**
	 * @param bz2s要设置的 bz2s
	 */
	public void setBz2s(String[] bz2s) {
		this.bz2s = bz2s;
	}
	/**
	 * @return the bz3s
	 */
	public String[] getBz3s() {
		return bz3s;
	}
	/**
	 * @param bz3s要设置的 bz3s
	 */
	public void setBz3s(String[] bz3s) {
		this.bz3s = bz3s;
	}
	/**
	 * @return the bz4s
	 */
	public String[] getBz4s() {
		return bz4s;
	}
	/**
	 * @param bz4s要设置的 bz4s
	 */
	public void setBz4s(String[] bz4s) {
		this.bz4s = bz4s;
	}
	/**
	 * @return the bz5s
	 */
	public String[] getBz5s() {
		return bz5s;
	}
	/**
	 * @param bz5s要设置的 bz5s
	 */
	public void setBz5s(String[] bz5s) {
		this.bz5s = bz5s;
	}
	/**
	 * @return the bz1
	 */
	public String getBz1() {
		return bz1;
	}
	/**
	 * @param bz1要设置的 bz1
	 */
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	/**
	 * @return the bz2
	 */
	public String getBz2() {
		return bz2;
	}
	/**
	 * @param bz2要设置的 bz2
	 */
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	/**
	 * @return the bz3
	 */
	public String getBz3() {
		return bz3;
	}
	/**
	 * @param bz3要设置的 bz3
	 */
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	/**
	 * @return the bz4
	 */
	public String getBz4() {
		return bz4;
	}
	/**
	 * @param bz4要设置的 bz4
	 */
	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}
	/**
	 * @return the bz5
	 */
	public String getBz5() {
		return bz5;
	}
	/**
	 * @param bz5要设置的 bz5
	 */
	public void setBz5(String bz5) {
		this.bz5 = bz5;
	}
	/**
	 * @return the csms
	 */
	public String getCsms() {
		return csms;
	}
	/**
	 * @param csms要设置的 csms
	 */
	public void setCsms(String csms) {
		this.csms = csms;
	}
	/**
	 * @return the csmss
	 */
	public String[] getCsmss() {
		return csmss;
	}
	/**
	 * @param csmss要设置的 csmss
	 */
	public void setCsmss(String[] csmss) {
		this.csmss = csmss;
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the tzhjcf
	 */
	public String getTzhjcf() {
		return tzhjcf;
	}
	/**
	 * @param tzhjcf要设置的 tzhjcf
	 */
	public void setTzhjcf(String tzhjcf) {
		this.tzhjcf = tzhjcf;
	}
	/**
	 * @return the jxdm
	 */
	public String getJxdm() {
		return jxdm;
	}
	/**
	 * @param jxdm要设置的 jxdm
	 */
	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	/**
	 * @return the sfqq
	 */
	public String getSfqq() {
		return sfqq;
	}
	/**
	 * @param sfqq要设置的 sfqq
	 */
	public void setSfqq(String sfqq) {
		this.sfqq = sfqq;
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
	 * @return the xfrdjgzt
	 */
	public String getXfrdjgzt() {
		return xfrdjgzt;
	}
	/**
	 * @param xfrdjgzt要设置的 xfrdjgzt
	 */
	public void setXfrdjgzt(String xfrdjgzt) {
		this.xfrdjgzt = xfrdjgzt;
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
	 * @return the jxdms
	 */
	public String[] getJxdms() {
		return jxdms;
	}
	/**
	 * @param jxdms要设置的 jxdms
	 */
	public void setJxdms(String[] jxdms) {
		this.jxdms = jxdms;
	}
	/**
	 * @return the sfqqs
	 */
	public String[] getSfqqs() {
		return sfqqs;
	}
	/**
	 * @param sfqqs要设置的 sfqqs
	 */
	public void setSfqqs(String[] sfqqs) {
		this.sfqqs = sfqqs;
	}
	/**
	 * @return the tzhjcfs
	 */
	public String[] getTzhjcfs() {
		return tzhjcfs;
	}
	/**
	 * @param tzhjcfs要设置的 tzhjcfs
	 */
	public void setTzhjcfs(String[] tzhjcfs) {
		this.tzhjcfs = tzhjcfs;
	}
	/**
	 * @return the jgids
	 */
	public String[] getJgids() {
		return jgids;
	}
	/**
	 * @param jgids要设置的 jgids
	 */
	public void setJgids(String[] jgids) {
		this.jgids = jgids;
	}
	
	
    
	
}
