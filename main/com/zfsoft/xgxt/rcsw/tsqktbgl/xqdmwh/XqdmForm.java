/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����02:18:19 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.xqdmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-15 ����02:18:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqdmForm extends ActionForm{
	private String xqdm;
	private String xqmc;
	private String type;
	private Pages pages = new Pages();
	/**
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	
	
	
}
