/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:30:23 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 奖惩项目维护
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午04:12:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxwJcxmwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private String dm;//
	private String mc;//
	private String fz;//分值
	private String lx;//类型
	private String bz;//备注
	private Pages pages = new Pages();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getFz() {
		return fz;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	
}
