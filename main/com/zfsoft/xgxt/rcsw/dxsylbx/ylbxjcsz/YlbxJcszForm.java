package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;


import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办模块
 * @类功能描述: TODO(学生证补办基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:54:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxJcszForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 3047822587143156629L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//当前时间是否开启
	
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
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopen要设置的 isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
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





}
