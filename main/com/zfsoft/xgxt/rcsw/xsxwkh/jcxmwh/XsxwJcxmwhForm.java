/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:30:23 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������Ŀά��
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����04:12:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxwJcxmwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private String dm;//
	private String mc;//
	private String fz;//��ֵ
	private String lx;//����
	private String bz;//��ע
	private Pages pages = new Pages();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getFz() {
		return fz;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	
}
