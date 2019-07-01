/**
 * @部门:学工产品事业部
 * @日期： 2014-10-8 上午11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍-谈话记录维护-谈话类型
 * @类功能描述: 
 * @作者： 江水才[工号:1150]
 * @时间： 2014-10-8 上午11:40:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThlxForm extends ActionForm{

	private static final long serialVersionUID = -1742917087154356376L;

	private Pages pages = new Pages();
	private String type;

	/**
	 * 类型代码
	 */
	private String lxdm;

	/**
	 * 类型名称
	 */
	private String lxmc;

	private String ssthlx;//困惑和问题时，所属谈话类型

	private String sskhwt;//所属困惑和问题

	private String wttg;

	private String id;//id

	public String getWttg() {
		return wttg;
	}

	public void setWttg(String wttg) {
		this.wttg = wttg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getSskhwt() {
		return sskhwt;
	}

	public void setSskhwt(String sskhwt) {
		this.sskhwt = sskhwt;
	}

	public String getSsthlx() {
		return ssthlx;
	}

	public void setSsthlx(String ssthlx) {
		this.ssthlx = ssthlx;
	}




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
