/**
 * @部门:学工产品事业部
 * @日期：2014-11-21 下午05:29:51 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 绿色通道
 * @类功能描述: 基础设置
 * @作者： cq [工号:785]
 * @时间： 2014-11-21 下午05:29:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdJcszForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//当前时间是否开启
	
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
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
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

}
