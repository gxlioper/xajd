/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:12:04 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金（浙江大学个性化！）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:12:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhForm extends ActionForm {
	private static final long serialVersionUID = 3768375193652880594L;
	private String type;
	private String zjlydm;
	private String zjlymc;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	
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
	 * @return the zjlydm
	 */
	public String getZjlydm() {
		return zjlydm;
	}
	/**
	 * @param zjlydm要设置的 zjlydm
	 */
	public void setZjlydm(String zjlydm) {
		this.zjlydm = zjlydm;
	}
	/**
	 * @return the zjlymc
	 */
	public String getZjlymc() {
		return zjlymc;
	}
	/**
	 * @param zjlymc要设置的 zjlymc
	 */
	public void setZjlymc(String zjlymc) {
		this.zjlymc = zjlymc;
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
