/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-10-8 ����11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������-̸����¼ά��-̸������
 * @�๦������: 
 * @���ߣ� ��ˮ��[����:1150]
 * @ʱ�䣺 2014-10-8 ����11:40:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThlxForm extends ActionForm{

	private static final long serialVersionUID = -1742917087154356376L;

	private Pages pages = new Pages();
	private String type;

	/**
	 * ���ʹ���
	 */
	private String lxdm;

	/**
	 * ��������
	 */
	private String lxmc;

	private String ssthlx;//���������ʱ������̸������

	private String sskhwt;//�������������

	private String wttg;

	private String id;//id

	public String getWttg() {
		return wttg;
	}

	public void setWttg(String wttg) {
		this.wttg = wttg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getSskhwt() {
		return sskhwt;
	}

	public void setSskhwt(String sskhwt) {
		this.sskhwt = sskhwt;
	}

	public String getSsthlx() {
		return ssthlx;
	}

	public void setSsthlx(String ssthlx) {
		this.ssthlx = ssthlx;
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
