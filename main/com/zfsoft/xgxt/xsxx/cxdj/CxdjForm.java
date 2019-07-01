/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午06:09:41 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行等级维护) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午06:09:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxdjForm extends ActionForm {
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String cxdjmc;
	private String cxdjdm;
	
	
	
	/**
	 * @return the cxdjmc
	 */
	public String getCxdjmc() {
		return cxdjmc;
	}
	/**
	 * @param cxdjmc要设置的 cxdjmc
	 */
	public void setCxdjmc(String cxdjmc) {
		this.cxdjmc = cxdjmc;
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
	public void setCxdjdm(String cxdjdm) {
		this.cxdjdm = cxdjdm;
	}
	public String getCxdjdm() {
		return cxdjdm;
	}
	
	
}
