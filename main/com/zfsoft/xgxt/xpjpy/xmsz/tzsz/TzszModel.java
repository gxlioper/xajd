/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述:项目维护-审核调整奖项设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class TzszModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String xmdm;// 项目代码
	private String tzjxdm;// 调整奖项代码
	private String xmmc;// 

	public TzszModel() {
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

	public String getTzjxdm() {
		return tzjxdm;
	}

	public void setTzjxdm(String tzjxdm) {
		this.tzjxdm = tzjxdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

}
