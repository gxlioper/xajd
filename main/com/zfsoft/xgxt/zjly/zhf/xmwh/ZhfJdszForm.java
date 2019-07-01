/**
 * @部门:学工产品事业部
 * @日期：2016-6-29 上午08:59:05 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 兼得设置(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-29 上午08:59:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfJdszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String jfxmdm;
	private String jfxmmc;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdm要设置的 jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmc要设置的 jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	
	
}
