/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:21:25 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 自定义查询列
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-5-27 下午02:21:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpzForm extends ActionForm{

	
	private static final long serialVersionUID = 1L;

	private String gnbz;
	private String gnmc;
	
	private String guid;
	private String key;
	private String value;
	
	public String getGnbz() {
		return gnbz;
	}
	public void setGnbz(String gnbz) {
		this.gnbz = gnbz;
	}
	public String getGnmc() {
		return gnmc;
	}
	public void setGnmc(String gnmc) {
		this.gnmc = gnmc;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
