/**
 * @部门:学工产品事业部
 * @日期：2016-11-17 下午07:14:28 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-11-17 下午07:14:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyzgkModel extends ActionForm{
	
	private static final long serialVersionUID = -1313339430724700820L;
	
	private String zydm;
	private String pjxn;
	private String kcmc;
	private String[] zgks;
	private String xydm;
	
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the pjxn
	 */
	public String getPjxn() {
		return pjxn;
	}
	/**
	 * @param pjxn要设置的 pjxn
	 */
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}
	/**
	 * @return the kcmc
	 */
	public String getKcmc() {
		return kcmc;
	}
	/**
	 * @param kcmc要设置的 kcmc
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	/**
	 * @return the zgks
	 */
	public String[] getZgks() {
		return zgks;
	}
	/**
	 * @param zgks要设置的 zgks
	 */
	public void setZgks(String[] zgks) {
		this.zgks = zgks;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	
	
}
