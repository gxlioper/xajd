/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 上午11:58:04 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 上午11:58:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JJgzForm extends ActionForm {
	
	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	//编号
	private String sid;
	//名称
	private String gzmc;
	//内容
	private String gznr;
	//时间
	private String 	jlsj;
	//是否发布
	private String sffb;
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid要设置的 sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}
	/**
	 * @param gzmc要设置的 gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznr要设置的 gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsj要设置的 jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	/**
	 * @return the sffb
	 */
	public String getSffb() {
		return sffb;
	}
	/**
	 * @param sffb要设置的 sffb
	 */
	public void setSffb(String sffb) {
		this.sffb = sffb;
	}

}
