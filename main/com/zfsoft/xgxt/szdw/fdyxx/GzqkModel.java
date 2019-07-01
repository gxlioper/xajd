/**
 * @部门:学工产品事业部
 * @日期：2018-1-29 上午10:54:25 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： JZ[工号:1529]
 * @时间： 2018-1-29 上午10:54:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzqkModel implements Serializable{
	
	private static final long serialVersionUID = -8692278407785586702L;
	
	private String gzqkid;
	private String dwmc;
	private String qssj;
	private String jssj;
	private String zw;
	/**
	 * @return the gzqkid
	 */
	public String getGzqkid() {
		return gzqkid;
	}
	/**
	 * @param gzqkid要设置的 gzqkid
	 */
	public void setGzqkid(String gzqkid) {
		this.gzqkid = gzqkid;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmc要设置的 dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the qssj
	 */
	public String getQssj() {
		return qssj;
	}
	/**
	 * @param qssj要设置的 qssj
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the zw
	 */
	public String getZw() {
		return zw;
	}
	/**
	 * @param zw要设置的 zw
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
