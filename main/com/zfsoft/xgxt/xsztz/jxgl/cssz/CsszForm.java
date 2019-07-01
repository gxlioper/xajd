/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:03 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.cssz;

import org.apache.struts.action.ActionForm;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-27 上午10:04:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class CsszForm extends ActionForm {
	private String id;
	private String splc;
	private String type;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
