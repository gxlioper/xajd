/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.jdsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 项目维护-兼得设置
 * @类功能描述:
 * @作者： ligl
 * @时间： 2013-4-18 下午06:33:00
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhJdszForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String xmdm;// 项目代码
	private String kjddm;// 可兼得代码(多个用分隔符隔开)
	private String xmmc;// 

	public XmwhJdszForm() {
		super();
	}

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

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getKjddm() {
		return kjddm;
	}

	public void setKjddm(String kjddm) {
		this.kjddm = kjddm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}


}
