/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:44:23 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.jsxm;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲���Ŀ
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-22 ����02:44:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsJsxmModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String xmdm ;//��Ŀ���� 
	private String xn ;//ѧ�� 
	private String xq ;//ѧ�� 
	private String xmmc ;//��Ŀ���� 
	private String fjdm ;//�������� 
	private String xmlx ;//��Ŀ���� 
	private String qzbl ;//Ȩ�ر��� 
	private String mrfs ;//Ĭ�Ϸ��� 
	private String zdfz;//������
	private String zxfz;//��С����
	private String bmdm;//���Ŵ���
	private String xssx;//��ʾ˳��
	private String tbbl;//ͬ��������ϸ����
	private String pfsm;
	private String type;
	private Pages pages = new Pages();
	
	
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdmҪ���õ� bmdm
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
	 * @param xssxҪ���õ� xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZdfz() {
		return zdfz;
	}
	public void setZdfz(String zdfz) {
		this.zdfz = zdfz;
	}
	public String getZxfz() {
		return zxfz;
	}
	public void setZxfz(String zxfz) {
		this.zxfz = zxfz;
	}
	public String getPfsm() {
		return pfsm;
	}
	public void setPfsm(String pfsm) {
		this.pfsm = pfsm;
	}
	public String getTbbl() {
		return tbbl;
	}
	public void setTbbl(String tbbl) {
		this.tbbl = tbbl;
	}
	
	
}
