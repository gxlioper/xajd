/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:37:31 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理管理模块
 * @类功能描述: 档案转出参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:37:31 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazccsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String sqkg;/*申请开关*/
	private String splc;/*审批流程*/
	private String sqkssj;/*申请开始时间*/
	private String sqjssj;/*申请结束时间*/
	private String isopen ;/*当前时间是否开启*/
	private String fjid;// 档案自带协议书id
	private String uploadid;// 非数据库字段，查找附件表
	
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	/**
	 * @return the uploadid
	 */
	public String getUploadid() {
		return uploadid;
	}
	/**
	 * @param uploadid要设置的 uploadid
	 */
	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopen要设置的 isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
}
