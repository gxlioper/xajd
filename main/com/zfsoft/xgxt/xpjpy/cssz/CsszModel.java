/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:30:11 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参数设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:30:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String pjkg ;//评奖开关 
	private String kssj ;//开始时间 
	private String jssj ;//结束时间 
	private String xn ;//学年 
	private String xq ;//学期 
	private String cpzcsh ;//参评组初始化方式（1：年级+专业、2：班级） 
	private String zcflrfs ;//综测分录入方式（1：无页签，0：有页签） 
	private String pjfs ;//1:学期综测 学期评奖 2： 学年综测 学年评奖 3：学期综测 学年评奖 	
	
	private String pjzq;//评奖周期
	private String zqmc;//周期名称
	private String zcmrpm;//综测默认排名 
	private String zcxmjb;//综测项目级别，0 全体 、 1 年级 、2 学院
	
	private String kgzt;
	
	private String rownum;
	
	/**
	 * @return the zcxmjb
	 */
	public String getZcxmjb() {
		return zcxmjb;
	}
	/**
	 * @param zcxmjb要设置的 zcxmjb
	 */
	public void setZcxmjb(String zcxmjb) {
		this.zcxmjb = zcxmjb;
	}
	public String getPjkg() {
		return pjkg;
	}
	public void setPjkg(String pjkg) {
		this.pjkg = pjkg;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getCpzcsh() {
		return cpzcsh;
	}
	public void setCpzcsh(String cpzcsh) {
		this.cpzcsh = cpzcsh;
	}
	public String getPjfs() {
		return pjfs;
	}
	public void setPjfs(String pjfs) {
		this.pjfs = pjfs;
	}
	public String getZcflrfs() {
		return zcflrfs;
	}
	public void setZcflrfs(String zcflrfs) {
		this.zcflrfs = zcflrfs;
	}
	public String getPjzq() {
		return pjzq;
	}
	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getZqmc() {
		return zqmc;
	}
	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}
	public String getZcmrpm() {
		return zcmrpm;
	}
	public void setZcmrpm(String zcmrpm) {
		this.zcmrpm = zcmrpm;
	}
	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}
	/**
	 * @param kgzt要设置的 kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}
	
	

}
