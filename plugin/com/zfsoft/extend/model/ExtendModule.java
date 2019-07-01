/**
 * @部门:学工产品事业部
 * @日期：2015-6-2 下午02:55:43 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: ZFXG_EXTEND_MODULE
 * @作者：张小彬[工号:1036]
 * @时间： 2015-6-2 下午02:55:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendModule  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	////////////////////////FIELDS///////////////////
	private String id;    //主键
	
	private String mc;    //名称
	
	private String sm;    //说明信息
	 
	private String sfqy;  //是否启用：1启用，0不启用
	
	private String sfsh;  //是否需要审核
	
	private String splc;  //审批流程
	
	private String sjxz;  //是否有时间限制
	
	private String kssj;  //开始时间
	
	private String jssj;  //结束时间
	
	private String bz;    //备注
	
   ////////////////////////FIELDS///////////////////
	
   	
	
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
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @return the sm
	 */
	public String getSm() {
		return sm;
	}

	/**
	 * @param sm要设置的 sm
	 */
	public void setSm(String sm) {
		this.sm = sm;
	}

	/**
	 * @return the sfqy
	 */
	public String getSfqy() {
		return sfqy;
	}

	/**
	 * @param sfqy要设置的 sfqy
	 */
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	/**
	 * @return the sfsh
	 */
	public String getSfsh() {
		return sfsh;
	}

	/**
	 * @param sfsh要设置的 sfsh
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the sjxz
	 */
	public String getSjxz() {
		return sjxz;
	}

	/**
	 * @param sjxz要设置的 sjxz
	 */
	public void setSjxz(String sjxz) {
		this.sjxz = sjxz;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssj要设置的 kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
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
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
}
