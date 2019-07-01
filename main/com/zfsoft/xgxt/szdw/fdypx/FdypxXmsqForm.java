/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:17:36 
 */  
package com.zfsoft.xgxt.szdw.fdypx;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训申请 Form
 * @作者： zhangjw
 * @时间： 2013-7-24 下午4:15:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class FdypxXmsqForm extends FdypxXmwhForm {

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String sqid ;//申请编号
	private String sqr ;//审核人
	private String sqsj ;//申请时间
	private String sqly ;//申请理由
	private String shzt ;//审核状态
	private String splc ;//审批流程
	private String xmdm ;//申请项目代码
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	
	


}
