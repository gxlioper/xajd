/**
 * @部门:学工产品事业部
 * @日期：2013-7-22 下午02:44:23 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测项目
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-22 下午02:44:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcxmModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String xmdm ;//项目代码 
	private String xn ;//学年 
	private String xq ;//学期 
	private String xmmc ;//项目名称 
	private String fjdm ;//父级代码 
	private String xmlx ;//项目类型 
	private String qzbl ;//权重比例 
	private String mrfs ;//默认分数 
	private String jktb;//接口同步
	private String zdfs;//最大分数
	private String zxfs;//最小分数
	private String tbbl;//同步更新详细比例
	private String bmdm;//部门代码
	private String xssx;//显示顺序
	private String zcyf; //综测月份
	private String type;
	private Pages pages = new Pages();
	
	
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
	 * @return the tbbl
	 */
	public String getTbbl() {
		return tbbl;
	}
	/**
	 * @param tbbl要设置的 tbbl
	 */
	public void setTbbl(String tbbl) {
		this.tbbl = tbbl;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getFjdm() {
		return fjdm;
	}
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	public String getXmlx() {
		return xmlx;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	public String getQzbl() {
		return qzbl;
	}
	public void setQzbl(String qzbl) {
		this.qzbl = qzbl;
	}
	public String getMrfs() {
		return mrfs;
	}
	public void setMrfs(String mrfs) {
		this.mrfs = mrfs;
	}
	public String getJktb() {
		return jktb;
	}
	public void setJktb(String jktb) {
		this.jktb = jktb;
	}
	/**
	 * @return the zdfs
	 */
	public String getZdfs() {
		return zdfs;
	}
	/**
	 * @param zdfs要设置的 zdfs
	 */
	public void setZdfs(String zdfs) {
		this.zdfs = zdfs;
	}
	/**
	 * @return the zxfs
	 */
	public String getZxfs() {
		return zxfs;
	}
	/**
	 * @param zxfs要设置的 zxfs
	 */
	public void setZxfs(String zxfs) {
		this.zxfs = zxfs;
	}
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	public String getZcyf() {
		return zcyf;
	}
	public void setZcyf(String zcyf) {
		this.zcyf = zcyf;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
