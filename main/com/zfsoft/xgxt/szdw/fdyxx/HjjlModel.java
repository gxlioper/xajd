/**
 * @部门:学工产品事业部
 * @日期：2015-7-24 上午09:20:04 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/** 
 * @类功能描述: 辅导员信息-获奖经历
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-7-24 上午09:20:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjlModel implements Serializable{

	
	private static final long serialVersionUID = 4560185390984895976L;

	private String hjid;
	private String hjjb;
	private String hjmc;
	private String bjdw;
	private String hjzrs;
	private String brpm;
	private String hjbz;
	private String hjsj; // 获奖时间 
	private String zd1;
	private String zd2;
	private String zd3;
	private String zd4;
	private String zd5;
	private String zd6;
	private String fjid;//附件
	
	
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
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5要设置的 zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}
	/**
	 * @param zd6要设置的 zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	/**
	 * @return the hjid
	 */
	public String getHjid() {
		return hjid;
	}
	/**
	 * @param hjid要设置的 hjid
	 */
	public void setHjid(String hjid) {
		this.hjid = hjid;
	}
	/**
	 * @return the hjjb
	 */
	public String getHjjb() {
		return hjjb;
	}
	/**
	 * @param hjjb要设置的 hjjb
	 */
	public void setHjjb(String hjjb) {
		this.hjjb = hjjb;
	}
	/**
	 * @return the hjmc
	 */
	public String getHjmc() {
		return hjmc;
	}
	/**
	 * @param hjmc要设置的 hjmc
	 */
	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}
	/**
	 * @return the bjdw
	 */
	public String getBjdw() {
		return bjdw;
	}
	/**
	 * @param bjdw要设置的 bjdw
	 */
	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}
	/**
	 * @return the hjzrs
	 */
	public String getHjzrs() {
		return hjzrs;
	}
	/**
	 * @param hjzrs要设置的 hjzrs
	 */
	public void setHjzrs(String hjzrs) {
		this.hjzrs = hjzrs;
	}
	/**
	 * @return the brpm
	 */
	public String getBrpm() {
		return brpm;
	}
	/**
	 * @param brpm要设置的 brpm
	 */
	public void setBrpm(String brpm) {
		this.brpm = brpm;
	}
	/**
	 * @return the hjbz
	 */
	public String getHjbz() {
		return hjbz;
	}
	/**
	 * @param hjbz要设置的 hjbz
	 */
	public void setHjbz(String hjbz) {
		this.hjbz = hjbz;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	
	
}
