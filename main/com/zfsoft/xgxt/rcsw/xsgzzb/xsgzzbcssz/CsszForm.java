/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-10 ����04:55:36 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-10 ����04:55:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszForm extends ActionForm {
	private String wjlxdm;
	private String wjlxmc;
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
	/**
	 * @return the wjlxdm
	 */
	public String getWjlxdm() {
		return wjlxdm;
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
	/**
	 * @param wjlxdmҪ���õ� wjlxdm
	 */
	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}
	/**
	 * @return the wjlxmc
	 */
	public String getWjlxmc() {
		return wjlxmc;
	}
	/**
	 * @param wjlxmcҪ���õ� wjlxmc
	 */
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}
}
