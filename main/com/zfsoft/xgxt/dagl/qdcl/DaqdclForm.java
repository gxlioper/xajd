/**
 * @部门:学工产品事业部
 * @日期：2014-2-10 下午05:25:23 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-10 下午05:25:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdclForm extends ActionForm {
	
	private String daqdcl_id;
	private String daqdcl_mc;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the daqdcl_id
	 */
	public String getDaqdcl_id() {
		return daqdcl_id;
	}
	/**
	 * @param daqdcl_id要设置的 daqdcl_id
	 */
	public void setDaqdcl_id(String daqdcl_id) {
		this.daqdcl_id = daqdcl_id;
	}
	/**
	 * @return the daqdcl_mc
	 */
	public String getDaqdcl_mc() {
		return daqdcl_mc;
	}
	/**
	 * @param daqdcl_mc要设置的 daqdcl_mc
	 */
	public void setDaqdcl_mc(String daqdcl_mc) {
		this.daqdcl_mc = daqdcl_mc;
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
