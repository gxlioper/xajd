/**
 * @部门:学工产品事业部
 * @日期：2013-6-8 下午02:15:49 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： huj
 * @时间： 2013-6-8 下午02:15:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JzygForm extends ActionForm {

	private static final long serialVersionUID = -60909231L;

	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String zgh;
	private String zglx;
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
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZglx() {
		return zglx;
	}
	public void setZglx(String zglx) {
		this.zglx = zglx;
	}
	
	

}
