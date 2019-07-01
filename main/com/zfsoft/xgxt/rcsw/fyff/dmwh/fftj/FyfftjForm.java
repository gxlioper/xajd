/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 上午09:39:43 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放途径
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 上午09:39:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyfftjForm extends ActionForm {

	
	private static final long serialVersionUID = -2599078032310583584L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String fftjdm;	//发放途径代码
	private String fftj;	//发放途径
	private String ffzh;	//发放账号(0:需填写; 1:无需填写)
	
	
	
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
	public String getFftjdm() {
		return fftjdm;
	}
	public void setFftjdm(String fftjdm) {
		this.fftjdm = fftjdm;
	}
	public String getFftj() {
		return fftj;
	}
	public void setFftj(String fftj) {
		this.fftj = fftj;
	}
	public String getFfzh() {
		return ffzh;
	}
	public void setFfzh(String ffzh) {
		this.ffzh = ffzh;
	}
	
	

}
