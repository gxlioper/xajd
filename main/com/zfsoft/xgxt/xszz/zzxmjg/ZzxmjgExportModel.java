package com.zfsoft.xgxt.xszz.zzxmjg;

import org.apache.struts.action.ActionForm;

public class ZzxmjgExportModel extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String xn;	//学年
	private String xq;	//学期
	private String[] xmlb;//项目类别
//	private String[] xydm; //学院代码
	private String[] xmmc; //项目名称
	
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xmlb
	 */
	public String[] getXmlb() {
		return xmlb;
	}
	/**
	 * @param xmlb要设置的 xmlb
	 */
	public void setXmlb(String[] xmlb) {
		this.xmlb = xmlb;
	}
//	/**
//	 * @return the xydm
//	 */
//	public String[] getXydm() {
//		return xydm;
//	}
//	/**
//	 * @param xydm要设置的 xydm
//	 */
//	public void setXydm(String[] xydm) {
//		this.xydm = xydm;
//	}
	/**
	 * @return the xmmc
	 */
	public String[] getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	
	
	
}
