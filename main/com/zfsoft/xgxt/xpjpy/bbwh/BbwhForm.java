/**
 * @部门:学工产品事业部
 * @日期：2013-11-26 下午05:31:24 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-11-26 下午05:31:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String bbdm;// 报表代码
	private String bbmc;// 报表名称
	private String xmdm;// 项目代码
	private String bblx;//报表类型
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
	/**
	 * @return the bbdm
	 */
	public String getBbdm() {
		return bbdm;
	}
	/**
	 * @param bbdm要设置的 bbdm
	 */
	public void setBbdm(String bbdm) {
		this.bbdm = bbdm;
	}
	/**
	 * @return the bbmc
	 */
	public String getBbmc() {
		return bbmc;
	}
	/**
	 * @param bbmc要设置的 bbmc
	 */
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
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
	 * @return the bblx
	 */
	public String getBblx() {
		return bblx;
	}
	/**
	 * @param bblx要设置的 bblx
	 */
	public void setBblx(String bblx) {
		this.bblx = bblx;
	}
	
	

}
