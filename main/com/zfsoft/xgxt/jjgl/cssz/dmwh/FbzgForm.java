/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:22:05 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:22:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FbzgForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String fbzgdm;
	
	private String fbzgmc;
	
	private String fbzgms;
	
	private String fy;

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
	 * @return the fbzgdm
	 */
	public String getFbzgdm() {
		return fbzgdm;
	}

	/**
	 * @param fbzgdmҪ���õ� fbzgdm
	 */
	public void setFbzgdm(String fbzgdm) {
		this.fbzgdm = fbzgdm;
	}

	/**
	 * @return the fbzgmc
	 */
	public String getFbzgmc() {
		return fbzgmc;
	}

	/**
	 * @param fbzgmcҪ���õ� fbzgmc
	 */
	public void setFbzgmc(String fbzgmc) {
		this.fbzgmc = fbzgmc;
	}

	/**
	 * @return the fbzgms
	 */
	public String getFbzgms() {
		return fbzgms;
	}

	/**
	 * @param fbzgmsҪ���õ� fbzgms
	 */
	public void setFbzgms(String fbzgms) {
		this.fbzgms = fbzgms;
	}

	/**
	 * @return the fy
	 */
	public String getFy() {
		return fy;
	}

	/**
	 * @param fyҪ���õ� fy
	 */
	public void setFy(String fy) {
		this.fy = fy;
	}
	
	
}
