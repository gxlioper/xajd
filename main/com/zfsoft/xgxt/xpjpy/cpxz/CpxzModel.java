/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:57:54 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013-参评小组 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:57:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpxzModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String bjdm; //班级代码
	private String pmz;	//排名组

	//参评组分配管理员用字段
	private String[] zghs;
	private String[] cpzdmArry;
	private String cpzdms;
	private String sffp;
	//参评组分配管理员用字段

	public String[] getZghs() {
		return zghs;
	}

	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}

	public String[] getCpzdmArry() {
		return cpzdmArry;
	}

	public void setCpzdmArry(String[] cpzdmArry) {
		this.cpzdmArry = cpzdmArry;
	}

	public String getCpzdms() {
		return cpzdms;
	}

	public void setCpzdms(String cpzdms) {
		this.cpzdms = cpzdms;
	}

	public String getSffp() {
		return sffp;
	}

	public void setSffp(String sffp) {
		this.sffp = sffp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public SearchModel getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getPmz() {
		return pmz;
	}

	public void setPmz(String pmz) {
		this.pmz = pmz;
	}
	
	
}
