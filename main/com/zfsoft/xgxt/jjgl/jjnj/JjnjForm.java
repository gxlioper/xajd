/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:32:51 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @类功能描述: 家教年级 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 下午02:32:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjnjForm extends ActionForm{

	private static final long serialVersionUID = 4169122978722692227L;
	
	private String jjnjdm;
	private String jjnjmc;
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
	 * @return the jjnjdm
	 */
	public String getJjnjdm() {
		return jjnjdm;
	}
	/**
	 * @param jjnjdm要设置的 jjnjdm
	 */
	public void setJjnjdm(String jjnjdm) {
		this.jjnjdm = jjnjdm;
	}
	/**
	 * @return the jjnjmc
	 */
	public String getJjnjmc() {
		return jjnjmc;
	}
	/**
	 * @param jjnjmc要设置的 jjnjmc
	 */
	public void setJjnjmc(String jjnjmc) {
		this.jjnjmc = jjnjmc;
	}
	
	
}
