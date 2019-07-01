/**
 * @部门:学工产品事业部
 * @日期：2016-5-23 下午05:02:03 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优(校外获奖)
 * @类功能描述: 参数设置 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-23 下午05:02:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XwhjCsszForm extends ActionForm{
	
	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String splc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}

}
