/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:43:20 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 代码维护_项目性质
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:43:20 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmxzForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private String type;    //类型
	private SearchModel searchModel = new SearchModel();
	private String xzdm;	//项目性质代码
	private String xzmc;	//项目性质名称
	
	
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
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdm要设置的 xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the xzmc
	 */
	public String getXzmc() {
		return xzmc;
	}
	/**
	 * @param xzmc要设置的 xzmc
	 */
	public void setXzmc(String xzmc) {
		this.xzmc = xzmc;
	}
}
