/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:37:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-��������
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-23 ����03:37:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdlxwhForm extends ActionForm{

	private static final long serialVersionUID = -8558605391497554002L;

	private Pages pages = new Pages();
	
	/**
	 * �������ʹ���
	 */
	private String fdlxdm;
	
	/**
	 * ������������
	 */
	private String fdlxmc;

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
	 * @return the fdlxdm
	 */
	public String getFdlxdm() {
		return fdlxdm;
	}

	/**
	 * @param fdlxdmҪ���õ� fdlxdm
	 */
	public void setFdlxdm(String fdlxdm) {
		this.fdlxdm = fdlxdm;
	}

	/**
	 * @return the fdlxmc
	 */
	public String getFdlxmc() {
		return fdlxmc;
	}

	/**
	 * @param fdlxmcҪ���õ� fdlxmc
	 */
	public void setFdlxmc(String fdlxmc) {
		this.fdlxmc = fdlxmc;
	}
}
