/**
 * @部门:学工产品事业部
 * @日期：2013-8-16 上午09:04:42 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优_代码维护（项目类型和性质）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-16 上午09:04:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjdmModel extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String	xmxzdm;
	private String	xmxzmc;
	private String	xmlxdm;
	private String	xmlxmc;
	private String xmxz;

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
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
	public String getXmxzdm() {
		return xmxzdm;
	}
	public void setXmxzdm(String xmxzdm) {
		this.xmxzdm = xmxzdm;
	}
	public String getXmxzmc() {
		return xmxzmc;
	}
	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}
	public String getXmlxdm() {
		return xmlxdm;
	}
	public void setXmlxdm(String xmlxdm) {
		this.xmlxdm = xmlxdm;
	}
	public String getXmlxmc() {
		return xmlxmc;
	}
	public void setXmlxmc(String xmlxmc) {
		this.xmlxmc = xmlxmc;
	}
	
	
	

}
