/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-26 ����05:58:14 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-26 ����05:58:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqshForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String type;

	private String sqr;
	
	private String xqid;
	
	private String shzt;
	
	private String ztbz;
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

	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqrҪ���õ� sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @return the xqid
	 */
	public String getXqid() {
		return xqid;
	}

	/**
	 * @param xqidҪ���õ� xqid
	 */
	public void setXqid(String xqid) {
		this.xqid = xqid;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the ztbz
	 */
	public String getZtbz() {
		return ztbz;
	}

	/**
	 * @param ztbzҪ���õ� ztbz
	 */
	public void setZtbz(String ztbz) {
		this.ztbz = ztbz;
	}
	
	
	
}
