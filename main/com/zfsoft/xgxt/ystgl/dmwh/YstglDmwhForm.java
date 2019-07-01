/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午02:24:28 
 */  
package com.zfsoft.xgxt.ystgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:24:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstglDmwhForm extends ActionForm{
	private String ystlbdm; // 代码
	private String ystlbmc; // 名称
	private String xmlbdm;
	private String xmlbmc;
	private String gkdwdm;
	private String gkdwmc;
	private Pages pages = new Pages();
	private String type;
	/**
	 * @return the ystlbdm
	 */
	public String getYstlbdm() {
		return ystlbdm;
	}
	/**
	 * @param ystlbdm要设置的 ystlbdm
	 */
	public void setYstlbdm(String ystlbdm) {
		this.ystlbdm = ystlbdm;
	}
	/**
	 * @return the ystlbmc
	 */
	public String getYstlbmc() {
		return ystlbmc;
	}
	/**
	 * @param ystlbmc要设置的 ystlbmc
	 */
	public void setYstlbmc(String ystlbmc) {
		this.ystlbmc = ystlbmc;
	}
	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}
	/**
	 * @param xmlbdm要设置的 xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	/**
	 * @return the xmlbmc
	 */
	public String getXmlbmc() {
		return xmlbmc;
	}
	/**
	 * @param xmlbmc要设置的 xmlbmc
	 */
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	/**
	 * @return the gkdwdm
	 */
	public String getGkdwdm() {
		return gkdwdm;
	}
	/**
	 * @param gkdwdm要设置的 gkdwdm
	 */
	public void setGkdwdm(String gkdwdm) {
		this.gkdwdm = gkdwdm;
	}
	/**
	 * @return the gkdwmc
	 */
	public String getGkdwmc() {
		return gkdwmc;
	}
	/**
	 * @param gkdwmc要设置的 gkdwmc
	 */
	public void setGkdwmc(String gkdwmc) {
		this.gkdwmc = gkdwmc;
	}
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
	
	
	

}
