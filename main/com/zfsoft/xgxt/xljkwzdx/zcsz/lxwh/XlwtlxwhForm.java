/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:37:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-心理问题类型
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-23 下午03:37:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwtlxwhForm extends ActionForm{

	private static final long serialVersionUID = 2019126384952581785L;

	private Pages pages = new Pages();
	
	/**
	 * 类型代码
	 */
	private String lxdm;
	
	/**
	 * 类型名称
	 */
	private String lxmc;

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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the lxmc
	 */
	public String getLxmc() {
		return lxmc;
	}

	/**
	 * @param lxmc要设置的 lxmc
	 */
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	
}
