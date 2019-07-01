/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:30:11 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班风竞赛管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-4-1 上午08:55:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BfjsCsszModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String sqkg ;//申请开关 
	private String kssj ;//开始时间 
	private String jssj ;//结束时间 
	private String xn ;//学年 
	private String xq ;//学期 

	private String jsfs ;//竞赛方式 	
	
	private String pdzq;//评定周期
	private String zqmc;//周期名称
	private String jsxmjb;//竞赛项目级别，0 全体 、 1 年级 、2 学院
	
	
	private String kgzt;
	
	private String rownum;

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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

	public String getJsfs() {
		return jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public String getPdzq() {
		return pdzq;
	}

	public void setPdzq(String pdzq) {
		this.pdzq = pdzq;
	}

	public String getZqmc() {
		return zqmc;
	}

	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getJsxmjb() {
		return jsxmjb;
	}

	public void setJsxmjb(String jsxmjb) {
		this.jsxmjb = jsxmjb;
	}
	
	
	
	

}
