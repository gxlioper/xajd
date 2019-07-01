/**
 * @部门:学工产品事业部
 * @日期：2016-5-6 上午09:38:38 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 参数设置 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-6 上午09:38:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglCsszForm extends ActionForm{
	
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
