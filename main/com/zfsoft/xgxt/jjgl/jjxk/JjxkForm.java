/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����02:35:20 
 */  
package com.zfsoft.xgxt.jjgl.jjxk;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @�๦������: �ҽ�ѧ��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����02:35:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjxkForm extends ActionForm {

	private static final long serialVersionUID = -4140952622007181224L;
	
	private String jjxkdm;
	private String jjxkmc;
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
	/**
	 * @return the jjxkdm
	 */
	public String getJjxkdm() {
		return jjxkdm;
	}
	/**
	 * @param jjxkdmҪ���õ� jjxkdm
	 */
	public void setJjxkdm(String jjxkdm) {
		this.jjxkdm = jjxkdm;
	}
	/**
	 * @return the jjxkmc
	 */
	public String getJjxkmc() {
		return jjxkmc;
	}
	/**
	 * @param jjxkmcҪ���õ� jjxkmc
	 */
	public void setJjxkmc(String jjxkmc) {
		this.jjxkmc = jjxkmc;
	}
	
	

}
