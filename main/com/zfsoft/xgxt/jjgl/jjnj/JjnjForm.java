/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����02:32:51 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @�๦������: �ҽ��꼶 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����02:32:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjnjForm extends ActionForm{

	private static final long serialVersionUID = 4169122978722692227L;
	
	private String jjnjdm;
	private String jjnjmc;
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
	 * @return the jjnjdm
	 */
	public String getJjnjdm() {
		return jjnjdm;
	}
	/**
	 * @param jjnjdmҪ���õ� jjnjdm
	 */
	public void setJjnjdm(String jjnjdm) {
		this.jjnjdm = jjnjdm;
	}
	/**
	 * @return the jjnjmc
	 */
	public String getJjnjmc() {
		return jjnjmc;
	}
	/**
	 * @param jjnjmcҪ���õ� jjnjmc
	 */
	public void setJjnjmc(String jjnjmc) {
		this.jjnjmc = jjnjmc;
	}
	
	
}
