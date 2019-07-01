/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:35:20 
 */  
package com.zfsoft.xgxt.jjgl.jjxk;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @类功能描述: 家教学科
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 下午02:35:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjxkForm extends ActionForm {

	private static final long serialVersionUID = -4140952622007181224L;
	
	private String jjxkdm;
	private String jjxkmc;
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
	 * @return the jjxkdm
	 */
	public String getJjxkdm() {
		return jjxkdm;
	}
	/**
	 * @param jjxkdm要设置的 jjxkdm
	 */
	public void setJjxkdm(String jjxkdm) {
		this.jjxkdm = jjxkdm;
	}
	/**
	 * @return the jjxkmc
	 */
	public String getJjxkmc() {
		return jjxkmc;
	}
	/**
	 * @param jjxkmc要设置的 jjxkmc
	 */
	public void setJjxkmc(String jjxkmc) {
		this.jjxkmc = jjxkmc;
	}
	
	

}
