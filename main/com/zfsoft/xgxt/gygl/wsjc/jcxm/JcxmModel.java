/**
 * @部门:学工产品事业部
 * @日期：2015-5-28 下午04:11:01 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @类功能描述: 卫生检查项目
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-5-28 下午04:11:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcxmModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String xmdm;
	private String xmmc;
	private String xmnr;
	private String jcdx;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
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
	 * @return the xmnr
	 */
	public String getXmnr() {
		return xmnr;
	}
	/**
	 * @param xmnr要设置的 xmnr
	 */
	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
	}
	/**
	 * @return the jcdx
	 */
	public String getJcdx() {
		return jcdx;
	}
	/**
	 * @param jcdx要设置的 jcdx
	 */
	public void setJcdx(String jcdx) {
		this.jcdx = jcdx;
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
