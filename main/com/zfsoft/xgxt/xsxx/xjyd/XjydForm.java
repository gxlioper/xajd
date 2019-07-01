package com.zfsoft.xgxt.xsxx.xjyd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学籍异动
 * @作者： Qilm[工号:964]
 * @时间： 2013-11-28 上午09:40:48 
 * @版本： V5.12.20
 */
public class XjydForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	/**
	 * 学籍类别代码【原】
	 */
	private String xjlbdmold;
	
	/**
	 * 学籍类别代码
	 */
	private String xjlbdm;

	/**
	 * 学籍类别名称
	 */
	private String xjlbmc;
	/**
	 * 是否有学籍
	 */
	private String sfyxj;
	/**
	 * 是否有学籍名称
	 */
	private String sfyxjmc;
	/**
	 * 是否在校
	 */
	private String sfzx;
	/**
	 * 是否在校名称
	 */
	private String sfzxmc;
	
	/**
	 * 审核流程
	 */
	private String shlcid;
	/**
	 * 审核流程
	 */
	private String shlcmc;
	
	/**
	 * 是否需要调整班级
	 */
	private String sftjbj;
	/**
	 * 是否需要录入起止时间
	 */
	private String lrqzsj;
	
	/**
	 * 是否可申请
	 */
	private String sfksq;
	/**
	 * 申请开始时间
	 */
	private String sqkssj;
	/**
	 * 申请结束时间
	 */
	private String sqjssj;
	
	private String xzsfkq; // 学制是否开启(华中师范)
	
	private String xxsfkq; // 来源去向学校是否开启(华中师范)
	
	public String getXzsfkq() {
		return xzsfkq;
	}

	public void setXzsfkq(String xzsfkq) {
		this.xzsfkq = xzsfkq;
	}

	public String getXxsfkq() {
		return xxsfkq;
	}

	public void setXxsfkq(String xxsfkq) {
		this.xxsfkq = xxsfkq;
	}

	public String getShlcmc() {
		return shlcmc;
	}

	public void setShlcmc(String shlcmc) {
		this.shlcmc = shlcmc;
	}

	public String getShlcid() {
		return shlcid;
	}

	public void setShlcid(String shlcid) {
		this.shlcid = shlcid;
	}

	public String getSftjbj() {
		return sftjbj;
	}

	public void setSftjbj(String sftjbj) {
		this.sftjbj = sftjbj;
	}

	public String getSfksq() {
		return sfksq;
	}

	public void setSfksq(String sfksq) {
		this.sfksq = sfksq;
	}


	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getXjlbdmold() {
		return xjlbdmold;
	}

	public void setXjlbdmold(String xjlbdmold) {
		this.xjlbdmold = xjlbdmold;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getXjlbdm() {
		return xjlbdm;
	}

	public void setXjlbdm(String xjlbdm) {
		this.xjlbdm = xjlbdm;
	}

	public String getXjlbmc() {
		return xjlbmc;
	}

	public void setXjlbmc(String xjlbmc) {
		this.xjlbmc = xjlbmc;
	}

	public String getSfyxj() {
		return sfyxj;
	}

	public void setSfyxj(String sfyxj) {
		this.sfyxj = sfyxj;
	}

	public String getSfyxjmc() {
		return sfyxjmc;
	}

	public void setSfyxjmc(String sfyxjmc) {
		this.sfyxjmc = sfyxjmc;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getSfzxmc() {
		return sfzxmc;
	}

	public void setSfzxmc(String sfzxmc) {
		this.sfzxmc = sfzxmc;
	}

	public String getLrqzsj() {
		return lrqzsj;
	}

	public void setLrqzsj(String lrqzsj) {
		this.lrqzsj = lrqzsj;
	}	
	
}
