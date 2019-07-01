/**
 * @部门:学工产品事业部
 * @日期：2014年6月12日 上午10:42:09 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 批量宿舍异动 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月12日 上午10:42:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PlydModel extends ActionForm {

	private static final long serialVersionUID = -6392320533049746745L;
	
	private String xh;
	private String yld;
	private String yss;
	private String ycw;
	private String xld;
	private String xss;
	private String xcw;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
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
	 * @return the yld
	 */
	public String getYld() {
		return yld;
	}
	/**
	 * @param yld要设置的 yld
	 */
	public void setYld(String yld) {
		this.yld = yld;
	}
	/**
	 * @return the yss
	 */
	public String getYss() {
		return yss;
	}
	/**
	 * @param yss要设置的 yss
	 */
	public void setYss(String yss) {
		this.yss = yss;
	}
	/**
	 * @return the ycw
	 */
	public String getYcw() {
		return ycw;
	}
	/**
	 * @param ycw要设置的 ycw
	 */
	public void setYcw(String ycw) {
		this.ycw = ycw;
	}
	/**
	 * @return the xld
	 */
	public String getXld() {
		return xld;
	}
	/**
	 * @param xld要设置的 xld
	 */
	public void setXld(String xld) {
		this.xld = xld;
	}
	/**
	 * @return the xss
	 */
	public String getXss() {
		return xss;
	}
	/**
	 * @param xss要设置的 xss
	 */
	public void setXss(String xss) {
		this.xss = xss;
	}
	/**
	 * @return the xcw
	 */
	public String getXcw() {
		return xcw;
	}
	/**
	 * @param xcw要设置的 xcw
	 */
	public void setXcw(String xcw) {
		this.xcw = xcw;
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
