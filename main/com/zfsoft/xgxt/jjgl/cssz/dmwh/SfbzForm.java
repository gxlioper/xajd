/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:05:41 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �շѱ�׼
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:05:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SfbzForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String id;
	
	private String jjxkdm;
	
	private String jjnjdm;
	
	private String sfbz;
	
	private String bz;

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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the sfbz
	 */
	public String getSfbz() {
		return sfbz;
	}

	/**
	 * @param sfbzҪ���õ� sfbz
	 */
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
