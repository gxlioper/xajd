/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:48:25 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:48:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XslxwhForm  extends ActionForm{
	private String xslxdm;
	private String xslxmc;
	private String type;
	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	/**
	 * @return the xslxdm
	 */
	public String getXslxdm() {
		return xslxdm;
	}
	/**
	 * @param xslxdm要设置的 xslxdm
	 */
	public void setXslxdm(String xslxdm) {
		this.xslxdm = xslxdm;
	}
	/**
	 * @return the xslxmc
	 */
	public String getXslxmc() {
		return xslxmc;
	}
	/**
	 * @param xslxmc要设置的 xslxmc
	 */
	public void setXslxmc(String xslxmc) {
		this.xslxmc = xslxmc;
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
