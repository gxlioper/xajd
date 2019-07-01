/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 下午01:44:20 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放项目
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 下午01:44:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffxmForm extends ActionForm {

	
	private static final long serialVersionUID = -3059773922137100902L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String ffxmdm;	//发放项目代码
	private String ffxmmc;	//发放项目名称
	private String mrffje;	//默认发放金额
	private String fffs;	//发放方式
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getFfxmdm() {
		return ffxmdm;
	}
	public void setFfxmdm(String ffxmdm) {
		this.ffxmdm = ffxmdm;
	}
	public String getFfxmmc() {
		return ffxmmc;
	}
	public void setFfxmmc(String ffxmmc) {
		this.ffxmmc = ffxmmc;
	}
	public String getMrffje() {
		return mrffje;
	}
	public void setMrffje(String mrffje) {
		this.mrffje = mrffje;
	}
	public String getFffs() {
		return fffs;
	}
	public void setFffs(String fffs) {
		this.fffs = fffs;
	}
	
	
	

}
