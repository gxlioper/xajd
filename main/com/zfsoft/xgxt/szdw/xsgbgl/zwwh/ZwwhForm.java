/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:29:04 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务actionForm
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:29:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwwhForm extends ZwlxForm{

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String zwid ;//职务id
	private String zwmc ;//职务名称
	private String lxdm ;//职务类型代码
	private String zwzz ;//职责
	private String bz ;//备注
	public String getZwid() {
		return zwid;
	}
	public void setZwid(String zwid) {
		this.zwid = zwid;
	}
	public String getZwmc() {
		return zwmc;
	}
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	public String getZwzz() {
		return zwzz;
	}
	public void setZwzz(String zwzz) {
		this.zwzz = zwzz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	
}
