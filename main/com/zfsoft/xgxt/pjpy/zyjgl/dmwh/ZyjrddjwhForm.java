/**
 * @部门:学工产品事业部
 * @日期：2015-12-21 下午01:16:25 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-专业奖管理-代码维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2015-12-21 下午01:16:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyjrddjwhForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type; //操作类型
	private String rddjdm; //认定等级代码
	private String rddjmc; //认定等级名称
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
	 * @return the rddjdm
	 */
	public String getRddjdm() {
		return rddjdm;
	}
	/**
	 * @param rddjdm要设置的 rddjdm
	 */
	public void setRddjdm(String rddjdm) {
		this.rddjdm = rddjdm;
	}
	/**
	 * @return the rddjmc
	 */
	public String getRddjmc() {
		return rddjmc;
	}
	/**
	 * @param rddjmc要设置的 rddjmc
	 */
	public void setRddjmc(String rddjmc) {
		this.rddjmc = rddjmc;
	}
	
	
	
	
}
