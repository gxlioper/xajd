/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 上午11:57:29 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 上午11:57:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JJalForm extends ActionForm {
	private Pages pages = new Pages();

	private static final long serialVersionUID = 1L;
	
	private String type;
	
	//编号
	private String sid;
	//对象
	private String jjdx;
	//时间
	private String jjsj;
	//学科
	private String fdxk;
	//描述
	private String jjms;
	//记录日期
	private String jlrq;
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
	 * @return the jjdx
	 */
	public String getJjdx() {
		return jjdx;
	}
	/**
	 * @param jjdx要设置的 jjdx
	 */
	public void setJjdx(String jjdx) {
		this.jjdx = jjdx;
	}
	/**
	 * @return the jjsj
	 */
	public String getJjsj() {
		return jjsj;
	}
	/**
	 * @param jjsj要设置的 jjsj
	 */
	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}
	/**
	 * @return the fdxk
	 */
	public String getFdxk() {
		return fdxk;
	}
	/**
	 * @param fdxk要设置的 fdxk
	 */
	public void setFdxk(String fdxk) {
		this.fdxk = fdxk;
	}
	/**
	 * @return the jjms
	 */
	public String getJjms() {
		return jjms;
	}
	/**
	 * @param jjms要设置的 jjms
	 */
	public void setJjms(String jjms) {
		this.jjms = jjms;
	}
	/**
	 * @return the jlrq
	 */
	public String getJlrq() {
		return jlrq;
	}
	/**
	 * @param jlrq要设置的 jlrq
	 */
	public void setJlrq(String jlrq) {
		this.jlrq = jlrq;
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
