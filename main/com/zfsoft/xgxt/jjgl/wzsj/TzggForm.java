/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 上午11:15:23 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 上午11:15:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TzggForm extends ActionForm {

	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	//编号
	private String sid;
	//标题
	private String title;
	//内容
	private String contents;
	//发布时间
	private String publishdate;
	//优先级
	private String priority;
	//是否发布
	private String sffb;
	//发布人
	private String zgh;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents要设置的 contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the publishdate
	 */
	public String getPublishdate() {
		return publishdate;
	}
	/**
	 * @param publishdate要设置的 publishdate
	 */
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority要设置的 priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
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
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	
	
	
}
