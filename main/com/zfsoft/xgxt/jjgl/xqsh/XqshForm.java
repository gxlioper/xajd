/**
 * @部门:学工产品事业部
 * @日期：2014-8-26 下午05:58:14 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-26 下午05:58:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqshForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String type;

	private String sqr;
	
	private String xqid;
	
	private String shzt;
	
	private String ztbz;
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
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @return the xqid
	 */
	public String getXqid() {
		return xqid;
	}

	/**
	 * @param xqid要设置的 xqid
	 */
	public void setXqid(String xqid) {
		this.xqid = xqid;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the ztbz
	 */
	public String getZtbz() {
		return ztbz;
	}

	/**
	 * @param ztbz要设置的 ztbz
	 */
	public void setZtbz(String ztbz) {
		this.ztbz = ztbz;
	}
	
	
	
}
