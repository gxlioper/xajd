/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:37:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-������������
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-23 ����03:37:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwtlxwhForm extends ActionForm{

	private static final long serialVersionUID = 2019126384952581785L;

	private Pages pages = new Pages();
	
	/**
	 * ���ʹ���
	 */
	private String lxdm;
	
	/**
	 * ��������
	 */
	private String lxmc;

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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the lxmc
	 */
	public String getLxmc() {
		return lxmc;
	}

	/**
	 * @param lxmcҪ���õ� lxmc
	 */
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	
}
