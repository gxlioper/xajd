/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:47:37 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:47:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmJgForm extends ActionForm {
	   /** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String ttjgid;
	private String xmdm;
	private String sqr;
	private String sqsj;
	private String sqly;
	private String tdmc;
	private String dzxh;
	private String sjly;
	private String lylcywid;
	private String[] xhArr;
	private String  xhs;
	private String  ylzd1;
	private String  ylzd2;
	private String  ylzd3;
	private String  ylzd4;
	private String  ylzd5;
	private String  sjly1;
	
	private String bz1;
	private String bz2;
	private String bz3;
	private String bz4;
	private String bz5;
	
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
	private String  lylcywid1;
	private SearchModel searchModel = new SearchModel();
		 // private static final long serialVersionUID = 1L;
	private String type;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
   /**
 * @return the ttjgid
	 */
	public String getTtjgid() {
		return ttjgid;
	}
	/**
	 * @param ttjgid要设置的 ttjgid
	 */
	public void setTtjgid(String ttjgid) {
		this.ttjgid = ttjgid;
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
	 * @return the tdmc
	 */
	public String getTdmc() {
		return tdmc;
	}
	/**
	 * @param tdmc要设置的 tdmc
	 */
	public void setTdmc(String tdmc) {
		this.tdmc = tdmc;
	}
	/**
	 * @return the dzxh
	 */
	public String getDzxh() {
		return dzxh;
	}
	/**
	 * @param dzxh要设置的 dzxh
	 */
	public void setDzxh(String dzxh) {
		this.dzxh = dzxh;
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
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
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
