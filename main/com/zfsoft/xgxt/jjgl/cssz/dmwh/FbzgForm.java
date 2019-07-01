/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午03:22:05 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午03:22:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FbzgForm extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String fbzgdm;
	
	private String fbzgmc;
	
	private String fbzgms;
	
	private String fy;

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
	 * @return the fbzgdm
	 */
	public String getFbzgdm() {
		return fbzgdm;
	}

	/**
	 * @param fbzgdm要设置的 fbzgdm
	 */
	public void setFbzgdm(String fbzgdm) {
		this.fbzgdm = fbzgdm;
	}

	/**
	 * @return the fbzgmc
	 */
	public String getFbzgmc() {
		return fbzgmc;
	}

	/**
	 * @param fbzgmc要设置的 fbzgmc
	 */
	public void setFbzgmc(String fbzgmc) {
		this.fbzgmc = fbzgmc;
	}

	/**
	 * @return the fbzgms
	 */
	public String getFbzgms() {
		return fbzgms;
	}

	/**
	 * @param fbzgms要设置的 fbzgms
	 */
	public void setFbzgms(String fbzgms) {
		this.fbzgms = fbzgms;
	}

	/**
	 * @return the fy
	 */
	public String getFy() {
		return fy;
	}

	/**
	 * @param fy要设置的 fy
	 */
	public void setFy(String fy) {
		this.fy = fy;
	}
	
	
}
