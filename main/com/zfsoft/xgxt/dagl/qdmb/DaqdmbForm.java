/**
 * @部门:学工产品事业部
 * @日期：2014-2-13 下午05:25:23 
 */  
package com.zfsoft.xgxt.dagl.qdmb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-13 下午05:25:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdmbForm extends ActionForm {
	
	private String daqdmb_id;
	private String daqdmb_mc;
	private String qyzt;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the daqdmb_id
	 */
	public String getDaqdmb_id() {
		return daqdmb_id;
	}
	/**
	 * @param daqdmb_id要设置的 daqdmb_id
	 */
	public void setDaqdmb_id(String daqdmb_id) {
		this.daqdmb_id = daqdmb_id;
	}
	/**
	 * @return the daqdmb_mc
	 */
	public String getDaqdmb_mc() {
		return daqdmb_mc;
	}
	/**
	 * @param daqdmb_mc要设置的 daqdmb_mc
	 */
	public void setDaqdmb_mc(String daqdmb_mc) {
		this.daqdmb_mc = daqdmb_mc;
	}
	
	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyzt要设置的 qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
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

}
