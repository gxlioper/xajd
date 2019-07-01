/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjlxForm extends ActionForm {
	private String qjlxid;
	private String qjlxmc;
	private String open;//开关
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	/**
	 * @return the qjlxid
	 */
	public String getQjlxid() {
		return qjlxid;
	}

	/**
	 * @param qjlxid要设置的
	 *            qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjlxmc
	 */
	public String getQjlxmc() {
		return qjlxmc;
	}

	/**
	 * @param qjlxmc要设置的
	 *            qjlxmc
	 */
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
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
