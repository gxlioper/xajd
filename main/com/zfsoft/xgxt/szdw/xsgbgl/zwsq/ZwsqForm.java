/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:17:36 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务申请
 * @作者： zhangjw
 * @时间： 2013-8-8 下午2:30:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZwsqForm extends ZwwhForm {

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String sqid ;//申请id
	private String zwid ;//职务id
	private String xh ;//学号
	private String sqr ;//申请人
	private String shzt ;//审核状态
	private String splc ;//审批流程
	private String sqsj ;//申请时间
	private String sqly ;//申请理由
	
	private String xymc ;//学院名称
	private String zymc ;//专业
	private String bjmc ;//班级
	private String xm ;//姓名
	private ExportModel exportModel = new ExportModel();

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getZwid() {
		return zwid;
	}
	public void setZwid(String zwid) {
		this.zwid = zwid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	
	
	


}
