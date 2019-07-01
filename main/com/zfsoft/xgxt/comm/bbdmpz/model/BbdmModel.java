/**
 * @部门:学工产品事业部
 * @日期：2013-12-26 上午10:51:13 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.model;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-26 上午10:51:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbdmModel extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String guid;  //主键
	
	private String mkdm; //模块代码
	
	private String bbdm;	//报表代码
	
	private String bbmc;   //报表名称
	
	private String mblj;	//模板路径
	
	private String mbmc;	//模板名称
	
	private String tplj; //图片路径
	
	private String dyym; //对应页码

	private String thlj; //退回路径
	
	private String qqlj; //请求路径
	
	private String dybb; //对应报表代码id
	
	private String ywid; //业务主键
	/*****************参数列表 可以自己添加*********************/
	
	private String h_title;
	
	private String h_xn;
	
	private String h_xqmc;
	
	private String h_zd1;
	
	private String h_zd2;
	
	private String h_zd3;
	
	private String h_zd4;
	
	private String h_zd5;
	
	private String h_zd6;
	
	private String h_zd7;
	
	private String h_zd8;
	
	private String h_zd9;
	
	private String h_zd10;
	/*****************参数列表 可以自己添加*********************/
	
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
	 * @return the mkdm
	 */
	public String getMkdm() {
		return mkdm;
	}

	/**
	 * @param mkdm要设置的 mkdm
	 */
	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	/**
	 * @return the bbdm
	 */
	public String getBbdm() {
		return bbdm;
	}

	/**
	 * @param bbdm要设置的 bbdm
	 */
	public void setBbdm(String bbdm) {
		this.bbdm = bbdm;
	}

	/**
	 * @return the bbmc
	 */
	public String getBbmc() {
		return bbmc;
	}

	/**
	 * @param bbmc要设置的 bbmc
	 */
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	/**
	 * @return the mblj
	 */
	public String getMblj() {
		return mblj;
	}

	/**
	 * @param mblj要设置的 mblj
	 */
	public void setMblj(String mblj) {
		this.mblj = mblj;
	}

	/**
	 * @return the mbmc
	 */
	public String getMbmc() {
		return mbmc;
	}

	/**
	 * @param mbmc要设置的 mbmc
	 */
	public void setMbmc(String mbmc) {
		this.mbmc = mbmc;
	}

	/**
	 * @return the tplj
	 */
	public String getTplj() {
		return tplj;
	}

	/**
	 * @param tplj要设置的 tplj
	 */
	public void setTplj(String tplj) {
		this.tplj = tplj;
	}

	/**
	 * @return the dyym
	 */
	public String getDyym() {
		return dyym;
	}

	/**
	 * @param dyym要设置的 dyym
	 */
	public void setDyym(String dyym) {
		this.dyym = dyym;
	}

	/**
	 * @return the thlj
	 */
	public String getThlj() {
		return thlj;
	}

	/**
	 * @param thlj要设置的 thlj
	 */
	public void setThlj(String thlj) {
		this.thlj = thlj;
	}

	/**
	 * @return the qqlj
	 */
	public String getQqlj() {
		return qqlj;
	}

	/**
	 * @param qqlj要设置的 qqlj
	 */
	public void setQqlj(String qqlj) {
		this.qqlj = qqlj;
	}

	/**
	 * @return the h_xn
	 */
	public String getH_xn() {
		return h_xn;
	}

	/**
	 * @param hXn要设置的 h_xn
	 */
	public void setH_xn(String hXn) {
		h_xn = hXn;
	}

	/**
	 * @return the h_xqmc
	 */
	public String getH_xqmc() {
		return h_xqmc;
	}

	/**
	 * @param hXqmc要设置的 h_xqmc
	 */
	public void setH_xqmc(String hXqmc) {
		h_xqmc = hXqmc;
	}

	/**
	 * @return the h_zd1
	 */
	public String getH_zd1() {
		return h_zd1;
	}

	/**
	 * @param hZd1要设置的 h_zd1
	 */
	public void setH_zd1(String hZd1) {
		h_zd1 = hZd1;
	}

	/**
	 * @return the h_zd2
	 */
	public String getH_zd2() {
		return h_zd2;
	}

	/**
	 * @param hZd2要设置的 h_zd2
	 */
	public void setH_zd2(String hZd2) {
		h_zd2 = hZd2;
	}

	/**
	 * @return the h_zd3
	 */
	public String getH_zd3() {
		return h_zd3;
	}

	/**
	 * @param hZd3要设置的 h_zd3
	 */
	public void setH_zd3(String hZd3) {
		h_zd3 = hZd3;
	}

	/**
	 * @return the h_zd4
	 */
	public String getH_zd4() {
		return h_zd4;
	}

	/**
	 * @param hZd4要设置的 h_zd4
	 */
	public void setH_zd4(String hZd4) {
		h_zd4 = hZd4;
	}

	/**
	 * @return the h_zd5
	 */
	public String getH_zd5() {
		return h_zd5;
	}

	/**
	 * @param hZd5要设置的 h_zd5
	 */
	public void setH_zd5(String hZd5) {
		h_zd5 = hZd5;
	}

	/**
	 * @return the h_zd6
	 */
	public String getH_zd6() {
		return h_zd6;
	}

	/**
	 * @param hZd6要设置的 h_zd6
	 */
	public void setH_zd6(String hZd6) {
		h_zd6 = hZd6;
	}

	/**
	 * @return the h_zd7
	 */
	public String getH_zd7() {
		return h_zd7;
	}

	/**
	 * @param hZd7要设置的 h_zd7
	 */
	public void setH_zd7(String hZd7) {
		h_zd7 = hZd7;
	}

	/**
	 * @return the h_zd8
	 */
	public String getH_zd8() {
		return h_zd8;
	}

	/**
	 * @param hZd8要设置的 h_zd8
	 */
	public void setH_zd8(String hZd8) {
		h_zd8 = hZd8;
	}

	/**
	 * @return the h_zd9
	 */
	public String getH_zd9() {
		return h_zd9;
	}

	/**
	 * @param hZd9要设置的 h_zd9
	 */
	public void setH_zd9(String hZd9) {
		h_zd9 = hZd9;
	}

	/**
	 * @return the h_zd10
	 */
	public String getH_zd10() {
		return h_zd10;
	}

	/**
	 * @param hZd10要设置的 h_zd10
	 */
	public void setH_zd10(String hZd10) {
		h_zd10 = hZd10;
	}

	/**
	 * @return the dybb
	 */
	public String getDybb() {
		return dybb;
	}

	/**
	 * @param dybb要设置的 dybb
	 */
	public void setDybb(String dybb) {
		this.dybb = dybb;
	}

	/**
	 * @return the h_title
	 */
	public String getH_title() {
		return h_title;
	}

	/**
	 * @param hTitle要设置的 h_title
	 */
	public void setH_title(String hTitle) {
		h_title = hTitle;
	}

	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}

	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	
	
	
}
