/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����02:24:28 
 */  
package com.zfsoft.xgxt.ystgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-07-31 ����02:24:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YstglDmwhForm extends ActionForm{
	private String ystlbdm; // ����
	private String ystlbmc; // ����
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
	 * @param ystlbdmҪ���õ� ystlbdm
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
	 * @param ystlbmcҪ���õ� ystlbmc
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
	 * @param xmlbdmҪ���õ� xmlbdm
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
	 * @param xmlbmcҪ���õ� xmlbmc
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
	 * @param gkdwdmҪ���õ� gkdwdm
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
	 * @param gkdwmcҪ���õ� gkdwmc
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
	 * @param pagesҪ���õ� pages
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
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
