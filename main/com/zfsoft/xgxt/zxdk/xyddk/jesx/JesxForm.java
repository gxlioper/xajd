/**
 * @部门:学工产品事业部
 * @日期：2016-11-7 下午03:12:49 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-7 下午03:12:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JesxForm extends ActionForm {
	private String xlccdm;
	private String xlccmc;
	private String jesx;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	/**
	 * @return the xlccmc
	 */
	public String getXlccmc() {
		return xlccmc;
	}
	/**
	 * @param xlccmc要设置的 xlccmc
	 */
	public void setXlccmc(String xlccmc) {
		this.xlccmc = xlccmc;
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
	 * @return the xlccdm
	 */
	public String getXlccdm() {
		return xlccdm;
	}
	/**
	 * @param xlccdm要设置的 xlccdm
	 */
	public void setXlccdm(String xlccdm) {
		this.xlccdm = xlccdm;
	}
	/**
	 * @return the jesx
	 */
	public String getJesx() {
		return jesx;
	}
	/**
	 * @param jesx要设置的 jesx
	 */
	public void setJesx(String jesx) {
		this.jesx = jesx;
	}
	
}
