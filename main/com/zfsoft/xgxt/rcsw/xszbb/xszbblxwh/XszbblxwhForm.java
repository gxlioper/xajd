/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:30:23 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import com.zfsoft.xgxt.rcsw.xszbb.comm.XszbbForm;





/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办类型维护) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午05:16:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbblxwhForm extends XszbbForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -8998807497101093040L;
	private String type;
	private String xszbblxdm;//行为大类代码
	private String xszbblxmc;//行为大类名称
	private String zjlxbs;   //证件类型标识
	
	private String shlc;
	
	
	
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the xszbblxdm
	 */
	public String getXszbblxdm() {
		return xszbblxdm;
	}
	/**
	 * @param xszbblxdm要设置的 xszbblxdm
	 */
	public void setXszbblxdm(String xszbblxdm) {
		this.xszbblxdm = xszbblxdm;
	}
	/**
	 * @return the xszbblxmc
	 */
	public String getXszbblxmc() {
		return xszbblxmc;
	}
	/**
	 * @param xszbblxmc要设置的 xszbblxmc
	 */
	public void setXszbblxmc(String xszbblxmc) {
		this.xszbblxmc = xszbblxmc;
	}
	public String getZjlxbs() {
		return zjlxbs;
	}
	public void setZjlxbs(String zjlxbs) {
		this.zjlxbs = zjlxbs;
	}


	
}
