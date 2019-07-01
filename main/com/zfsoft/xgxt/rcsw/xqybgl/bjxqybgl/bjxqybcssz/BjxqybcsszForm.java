/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 上午11:28:10 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_班级月报管理模块
 * @类功能描述: TODO(北京中医药_班级月报_参数设置) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-23 上午11:33:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxqybcsszForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//当前时间是否开启
	
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
	 

	
	

}
