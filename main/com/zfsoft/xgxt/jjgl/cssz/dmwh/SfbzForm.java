/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午03:05:41 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 收费标准
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午03:05:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SfbzForm extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String id;
	
	private String jjxkdm;
	
	private String jjnjdm;
	
	private String sfbz;
	
	private String bz;

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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the sfbz
	 */
	public String getSfbz() {
		return sfbz;
	}

	/**
	 * @param sfbz要设置的 sfbz
	 */
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
