/**
 * @部门:学工产品事业部
 * @日期：2014-1-23 下午05:29:36 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.Serializable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学历社会经历Model
 * @类功能描述:学历社会经历Model
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-1-23 下午05:29:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlshjlModel implements Serializable{

	/** 
	 * @变量 serialVersionUID : 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String jlid; //经历id
	
	private String xh; //学号
	
	private String qzrq;//起止日期
	
	private String gzdd;//在何单位学习或工作
	
	private String zmrjhc;//证明人、现在何处
	
	private String zw;//职务
	
	private String jzsj; // 截止时间
	
	private String fjid; // 截止时间

	
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
	 * @return the jzsj
	 */
	public String getJzsj() {
		return jzsj;
	}

	/**
	 * @param jzsj要设置的 jzsj
	 */
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}

	/**
	 * @return the jlid
	 */
	public String getJlid() {
		return jlid;
	}

	/**
	 * @param jlid要设置的 jlid
	 */
	public void setJlid(String jlid) {
		this.jlid = jlid;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the qzrq
	 */
	public String getQzrq() {
		return qzrq;
	}

	/**
	 * @param qzrq要设置的 qzrq
	 */
	public void setQzrq(String qzrq) {
		this.qzrq = qzrq;
	}

	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}

	/**
	 * @param gzdd要设置的 gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	/**
	 * @return the zmrjhc
	 */
	public String getZmrjhc() {
		return zmrjhc;
	}

	/**
	 * @param zmrjhc要设置的 zmrjhc
	 */
	public void setZmrjhc(String zmrjhc) {
		this.zmrjhc = zmrjhc;
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
	
	
}
