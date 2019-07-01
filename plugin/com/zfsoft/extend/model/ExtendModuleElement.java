/**
 * @部门:学工产品事业部
 * @日期：2015-6-2 下午03:04:09 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-2 下午03:04:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendModuleElement  implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String SFSH_Y = "1";
	
	public static final String SFSH_N = "0";
	
	////////////////////////FIELDS///////////////////
	private String id;      //主键
	
	private String mc;      //名称
	
	private String sfsh;    //是否需要审核：1需审核，0不需要审核
	 
	private String shlc;    //审核流程
	
	private String sm;		//说明
	
	private String mid;     //所属模块ID
	
	private String xssx;    //显示顺序
   ////////////////////////FIELDS///////////////////

	private List<ExtendGroup> extendGroupList;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
 * @描述 ：TODO描述下当前构造方法
 * @param id
 * @param mc
 * @param sfsh
 * @param shlc
 * @param sm
 * @param mid
 * @param xssx
 */
public ExtendModuleElement(String id, String mc, String sfsh, String shlc,
		String sm, String mid, String xssx) {
	super();
	this.id = id;
	this.mc = mc;
	this.sfsh = sfsh;
	this.shlc = shlc;
	this.sm = sm;
	this.mid = mid;
	this.xssx = xssx;
}

	/**
 * @描述 ：TODO描述下当前构造方法
 */
public ExtendModuleElement() {
	super();
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
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid要设置的 mid
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}

	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	/**
	 * @return the extendGroupList
	 */
	public List<ExtendGroup> getExtendGroupList() {
		return extendGroupList;
	}

	/**
	 * @param extendGroupList要设置的 extendGroupList
	 */
	public void setExtendGroupList(List<ExtendGroup> extendGroupList) {
		this.extendGroupList = extendGroupList;
	}
}
