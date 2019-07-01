/**
 * @部门:学工产品事业部
 * @日期：2013-8-12 上午10:05:50 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import com.zfsoft.xgxt.szdw.xsgbgl.zwsh.ZwshForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理 队伍维护
 * @作者： zhangjw
 * @时间： 2013-8-12 上午10:05:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DwwhForm extends ZwshForm{

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String dwid ;//队伍ID
	private String zwid ;//职务id
	private String xh ;//学号
	private String zzzt ;//在职状态
	private String rzsj ;//任职时间
	private String lzsj ;//离职时间
	private String lrsj ;//加入时间
	private String sjly ;//数据来源
	private String zwsqid ;//申请id
	private String bjdm;

	private String[] zwids;
	private String[] xhs;

	@Override
	public String[] getZwids() {
		return zwids;
	}

	@Override
	public void setZwids(String[] zwids) {
		this.zwids = zwids;
	}

	@Override
	public String[] getXhs() {
		return xhs;
	}

	@Override
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
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
	public String getZzzt() {
		return zzzt;
	}
	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getLzsj() {
		return lzsj;
	}
	public void setLzsj(String lzsj) {
		this.lzsj = lzsj;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the zwsqid
	 */
	public String getZwsqid() {
		return zwsqid;
	}
	/**
	 * @param zwsqid要设置的 zwsqid
	 */
	public void setZwsqid(String zwsqid) {
		this.zwsqid = zwsqid;
	}

	

}
