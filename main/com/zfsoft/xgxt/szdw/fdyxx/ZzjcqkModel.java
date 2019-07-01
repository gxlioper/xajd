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
 * @类功能描述: 辅导员信息-著作、教材情况
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-12-16 上午10:45:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZzjcqkModel implements Serializable{

	private static final long serialVersionUID = -8692278407785586702L;
	
	private String zzjcqkid;
	private String zzjcmc;
	private String cbsj;
	private String cbs;
	private String jclb;
	private String cbqk;
	private String zd1;
	private String zd2;
	private String zd3;
	private String zd4;
	
	
	
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4要设置的 zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZzjcqkid() {
		return zzjcqkid;
	}
	public void setZzjcqkid(String zzjcqkid) {
		this.zzjcqkid = zzjcqkid;
	}
	public String getZzjcmc() {
		return zzjcmc;
	}
	public void setZzjcmc(String zzjcmc) {
		this.zzjcmc = zzjcmc;
	}
	public String getCbsj() {
		return cbsj;
	}
	public void setCbsj(String cbsj) {
		this.cbsj = cbsj;
	}
	public String getCbs() {
		return cbs;
	}
	public void setCbs(String cbs) {
		this.cbs = cbs;
	}
	public String getJclb() {
		return jclb;
	}
	public void setJclb(String jclb) {
		this.jclb = jclb;
	}
	public String getCbqk() {
		return cbqk;
	}
	public void setCbqk(String cbqk) {
		this.cbqk = cbqk;
	}
	

}
