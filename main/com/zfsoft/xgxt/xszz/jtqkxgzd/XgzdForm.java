/**
 * @部门:学工产品事业部
 * @日期：2016-7-4 上午10:33:33 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-4 上午10:33:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XgzdForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] zddms;
	private String[] guids;
	private String[] sfbts;
	private String guid;
	private String zddm;
	private String sfbt;
	private String type;
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
	 * @return the zddms
	 */
	public String[] getZddms() {
		return zddms;
	}
	/**
	 * @param zddms要设置的 zddms
	 */
	public void setZddms(String[] zddms) {
		this.zddms = zddms;
	}
	/**
	 * @return the guids
	 */
	public String[] getGuids() {
		return guids;
	}
	/**
	 * @param guids要设置的 guids
	 */
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	/**
	 * @return the sfbts
	 */
	public String[] getSfbts() {
		return sfbts;
	}
	/**
	 * @param sfbts要设置的 sfbts
	 */
	public void setSfbts(String[] sfbts) {
		this.sfbts = sfbts;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the zddm
	 */
	public String getZddm() {
		return zddm;
	}
	/**
	 * @param zddm要设置的 zddm
	 */
	public void setZddm(String zddm) {
		this.zddm = zddm;
	}
	/**
	 * @return the sfbt
	 */
	public String getSfbt() {
		return sfbt;
	}
	/**
	 * @param sfbt要设置的 sfbt
	 */
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
	
}
