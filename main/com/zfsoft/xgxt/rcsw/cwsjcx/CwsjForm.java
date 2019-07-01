/**
 * @部门:学工产品事业部
 * @日期：2013-6-19 下午03:32:56 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(财务数据查询) 
 * @作者： cmj [工号：913]
 * @时间： 2013-6-19 下午03:32:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CwsjForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	
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
	private String id;
	private String xn;
	private String xq;
	private String xh;
	private String zd1;
	private String zd2;
	private String zd3;
	private String zd4;
	private String zd5;
	private String zd6;
	private String zd7;
	private String zd8;
	private String zd9;
	private String zd10;

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
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4要设置的 zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5要设置的 zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}
	/**
	 * @param zd6要设置的 zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}
	/**
	 * @param zd7要设置的 zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}
	/**
	 * @param zd8要设置的 zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}
	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}
	/**
	 * @param zd9要设置的 zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}
	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}
	/**
	 * @param zd10要设置的 zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
}
