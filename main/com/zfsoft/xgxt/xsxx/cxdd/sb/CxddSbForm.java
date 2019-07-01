/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:19:30 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：yxy[工号:1206]
 * @时间： 2016-3-28 下午05:19:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddSbForm extends ActionForm {
	 private String bjid;
	 private String bjdm;
	 private String xn;
	 private String xq;
	 private String tjsj;
	 private String tjr;
	 private String shzt;
	 private String xsid;
	 private String xh;
	 private String pj;
	 private String py;
	 private String splc;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private String type;
	 private String flag;
	 private String bjdms;
	 private String flag1;
	
	/**
	 * @return the flag1
	 */
	public String getFlag1() {
		return flag1;
	}
	/**
	 * @param flag1要设置的 flag1
	 */
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}
	private Pages pages = new Pages(); 
	 /**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag要设置的 flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	
	 /**
	 * @return the bjid
	 */
	public String getBjid() {
		return bjid;
	}
	/**
	 * @param bjid要设置的 bjid
	 */
	public void setBjid(String bjid) {
		this.bjid = bjid;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsj要设置的 tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the tjr
	 */
	public String getTjr() {
		return tjr;
	}
	/**
	 * @param tjr要设置的 tjr
	 */
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the xsid
	 */
	public String getXsid() {
		return xsid;
	}
	/**
	 * @param xsid要设置的 xsid
	 */
	public void setXsid(String xsid) {
		this.xsid = xsid;
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
	 * @return the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @param pj要设置的 pj
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @return the py
	 */
	public String getPy() {
		return py;
	}
	/**
	 * @param py要设置的 py
	 */
	public void setPy(String py) {
		this.py = py;
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
