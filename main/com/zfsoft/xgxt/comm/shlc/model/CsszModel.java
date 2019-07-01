/**
 * @部门:学工产品事业部
 * @日期：2014年6月9日 下午1:50:12 
 */  
package com.zfsoft.xgxt.comm.shlc.model;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 申请审核-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月9日 下午1:50:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszModel extends ActionForm{

	private static final long serialVersionUID = 1L;

	private String id ;//ID（功能标识） 
	private String sqkg ;//申请开关 
	private String sqkssj ;//申请开始时间 
	private String sqjssj ;//申请结束时间 
	private String shkg ;//审核开关 
	private String shkssj ;//审核开始时间 
	private String shjssj ;//审核结束时间 
	private String shlc ;//审核流程 
	private String ssmk ;//所属模块 
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
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
	 * @return the ssmk
	 */
	public String getSsmk() {
		return ssmk;
	}
	/**
	 * @param ssmk要设置的 ssmk
	 */
	public void setSsmk(String ssmk) {
		this.ssmk = ssmk;
	}

	
}
