/**
 * @部门:学工产品事业部
 * @日期：2015-3-23 上午10:55:47 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-23 上午10:55:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszForm extends ActionForm{
	
	private String id;
	private String zckg;
	private String zckssj;
	private String zcjssj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZckg() {
		return zckg;
	}
	public void setZckg(String zckg) {
		this.zckg = zckg;
	}
	public String getZckssj() {
		return zckssj;
	}
	public void setZckssj(String zckssj) {
		this.zckssj = zckssj;
	}
	public String getZcjssj() {
		return zcjssj;
	}
	public void setZcjssj(String zcjssj) {
		this.zcjssj = zcjssj;
	}
	
	

}
