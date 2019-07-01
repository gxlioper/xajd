/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 上午08:48:02 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--家庭情况调查 基础设置
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 上午08:48:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtqkdcJcszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String sqkg ;//申请开关 
	private String sqkssj ;//申请开始时间 
	private String sqjssj ;//申请结束时间 
	private String isOpen ;
	
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	
}
