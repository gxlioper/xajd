/**
 * @部门:学工产品事业部
 * @日期：2015-7-24 上午09:13:10 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 辅导员信息-发明创造等情况
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-12-17 上午09:18:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FmczModel implements Serializable{

	private static final long serialVersionUID = -8692278407785586702L;
	
	private String fmczid;
	private String mc;
	private String sj;
	private String brpm;
	private String lx;
	private String bz;
	
	
	public String getFmczid() {
		return fmczid;
	}
	public void setFmczid(String fmczid) {
		this.fmczid = fmczid;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getBrpm() {
		return brpm;
	}
	public void setBrpm(String brpm) {
		this.brpm = brpm;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	

}
