/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午08:59:34 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息-大队维护
 * @类功能描述: 浙江警察学院个性化大队维护功能 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-12 上午08:59:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DdwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dddm;// 大队代码

	private String ddmc;// 大队名称

	private String qds;// 区队数(班级)
	
	private String qddm;

	private Pages pages = new Pages();

	private SearchModel searchModel = new SearchModel();
    
	private String type;
	
	private String flag;
	
	private String[] qdAll;
	
	public String[] getQdAll() {
		return qdAll;
	}

	public String getQddm() {
		return qddm;
	}


	public void setQddm(String qddm) {
		this.qddm = qddm;
	}


	public void setQdAll(String[] qdAll) {
		this.qdAll = qdAll;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDddm() {
		return dddm;
	}

	public void setDddm(String dddm) {
		this.dddm = dddm;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getQds() {
		return qds;
	}

	public void setQds(String qds) {
		this.qds = qds;
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
   
   
}
