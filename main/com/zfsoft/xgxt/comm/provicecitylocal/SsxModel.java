/**
 * @部门:学工产品事业部
 * @日期：2015-12-12 下午03:38:25 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-12-12 下午03:38:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SsxModel extends ActionForm {
	private String provicedm;//省代码
	private String citydm;//市代码
	private String localdm;//县区代码
	/**
	 * @return the localdm
	 */
	public String getLocaldm() {
		return localdm;
	}
	/**
	 * @param localdm要设置的 localdm
	 */
	public void setLocaldm(String localdm) {
		this.localdm = localdm;
	}
	/**
	 * @return the provicedm
	 */
	public String getProvicedm() {
		return provicedm;
	}
	/**
	 * @param provicedm要设置的 provicedm
	 */
	public void setProvicedm(String provicedm) {
		this.provicedm = provicedm;
	}
	/**
	 * @return the ctiydm
	 */
	public String getCitydm() {
		return citydm;
	}
	/**
	 * @param ctiydm要设置的 ctiydm
	 */
	public void setCitydm(String citydm) {
		this.citydm = citydm;
	}
	
}
