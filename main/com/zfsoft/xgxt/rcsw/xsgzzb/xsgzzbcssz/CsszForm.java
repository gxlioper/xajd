/**
 * @部门:学工产品事业部
 * @日期：2015-11-10 下午04:55:36 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-10 下午04:55:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszForm extends ActionForm {
	private String wjlxdm;
	private String wjlxmc;
	private String type;
	private Pages pages = new Pages();
	
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
	 * @return the wjlxdm
	 */
	public String getWjlxdm() {
		return wjlxdm;
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
	 * @param wjlxdm要设置的 wjlxdm
	 */
	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}
	/**
	 * @return the wjlxmc
	 */
	public String getWjlxmc() {
		return wjlxmc;
	}
	/**
	 * @param wjlxmc要设置的 wjlxmc
	 */
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}
}
