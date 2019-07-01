package xsgzgl.pjpy.szgyyq.mypj;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyMypjForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/* 通用 */
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();

	List<HashMap<String, String>> xmList;// 项目列表

	private String rows;// 行数

	private String yhlx;// 用户类型

	private String czxm;// 操作项目
	
	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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

	public List<HashMap<String, String>> getXmList() {
		return xmList;
	}

	public void setXmList(List<HashMap<String, String>> xmList) {
		this.xmList = xmList;
	}
}
