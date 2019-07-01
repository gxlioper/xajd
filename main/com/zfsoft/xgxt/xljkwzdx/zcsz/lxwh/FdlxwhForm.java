/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:37:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-辅导类型
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-23 下午03:37:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdlxwhForm extends ActionForm{

	private static final long serialVersionUID = -8558605391497554002L;

	private Pages pages = new Pages();
	
	/**
	 * 辅导类型代码
	 */
	private String fdlxdm;
	
	/**
	 * 辅导类型名称
	 */
	private String fdlxmc;

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
	 * @return the fdlxdm
	 */
	public String getFdlxdm() {
		return fdlxdm;
	}

	/**
	 * @param fdlxdm要设置的 fdlxdm
	 */
	public void setFdlxdm(String fdlxdm) {
		this.fdlxdm = fdlxdm;
	}

	/**
	 * @return the fdlxmc
	 */
	public String getFdlxmc() {
		return fdlxmc;
	}

	/**
	 * @param fdlxmc要设置的 fdlxmc
	 */
	public void setFdlxmc(String fdlxmc) {
		this.fdlxmc = fdlxmc;
	}
}
