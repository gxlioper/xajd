package com.zfsoft.xgxt.xszz.zzxmjg;

import org.apache.struts.action.ActionForm;

public class ZzxmjgExportModel extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String[] xmlb;//��Ŀ���
//	private String[] xydm; //ѧԺ����
	private String[] xmmc; //��Ŀ����
	
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
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
	 * @param xqҪ���õ� xq
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
	 * @param xmlbҪ���õ� xmlb
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
//	 * @param xydmҪ���õ� xydm
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
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	
	
	
}
