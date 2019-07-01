/**
 * @部门:学工产品事业部
 * @日期：2016-1-27 下午04:16:06 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-27 下午04:16:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnjxjgForm extends ActionForm{
	String jgid;
	String xmdm;
	String xmjbdm;
	String sskmdm;
	String xmmc;
	String xn;
	String xq;
	String xh;
	String sqsj;
	String sqly;
	String sjly;
	String lylcywid;
	String ylzd1;
	String ylzd2;
	String ylzd3;
	String ylzd4;
	String ylzd5;
	String xhs;
	String ylzd1s;
	String ylzd2s;
	String sjlys;
	private String type;
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String sjly1;
	private String jxid;
	private String xmdms [];
	private String xqs [];
	private String xns [];
	private String xhss [];
	private String sfqq;
	private String lylcywid1;
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
	 * @return the xmjbdm
	 */
	public String getXmjbdm() {
		return xmjbdm;
	}
	/**
	 * @param xmjbdm要设置的 xmjbdm
	 */
	public void setXmjbdm(String xmjbdm) {
		this.xmjbdm = xmjbdm;
	}
	/**
	 * @return the sskmdm
	 */
	public String getSskmdm() {
		return sskmdm;
	}
	/**
	 * @param sskmdm要设置的 sskmdm
	 */
	public void setSskmdm(String sskmdm) {
		this.sskmdm = sskmdm;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
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
	 * @return the ylzd1s
	 */
	public String getYlzd1s() {
		return ylzd1s;
	}
	/**
	 * @param ylzd1s要设置的 ylzd1s
	 */
	public void setYlzd1s(String ylzd1s) {
		this.ylzd1s = ylzd1s;
	}
	/**
	 * @return the ylzd2s
	 */
	public String getYlzd2s() {
		return ylzd2s;
	}
	/**
	 * @param ylzd2s要设置的 ylzd2s
	 */
	public void setYlzd2s(String ylzd2s) {
		this.ylzd2s = ylzd2s;
	}
	/**
	 * @return the sjlys
	 */
	public String getSjlys() {
		return sjlys;
	}
	/**
	 * @param sjlys要设置的 sjlys
	 */
	public void setSjlys(String sjlys) {
		this.sjlys = sjlys;
	}
	/**
	 * @return the sjly1
	 */
	public String getSjly1() {
		return sjly1;
	}
	/**
	 * @param sjly1要设置的 sjly1
	 */
	public void setSjly1(String sjly1) {
		this.sjly1 = sjly1;
	}
	/**
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}
	/**
	 * @param jxid要设置的 jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	/**
	 * @return the xmdms
	 */
	public String[] getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdms要设置的 xmdms
	 */
	public void setXmdms(String[] xmdms) {
		this.xmdms = xmdms;
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
	 * @return the xhss
	 */
	public String[] getXhss() {
		return xhss;
	}
	/**
	 * @param xhss要设置的 xhss
	 */
	public void setXhss(String[] xhss) {
		this.xhss = xhss;
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
	 * @return the lylcywid1
	 */
	public String getLylcywid1() {
		return lylcywid1;
	}
	/**
	 * @param lylcywid1要设置的 lylcywid1
	 */
	public void setLylcywid1(String lylcywid1) {
		this.lylcywid1 = lylcywid1;
	}
	
	
	
}
