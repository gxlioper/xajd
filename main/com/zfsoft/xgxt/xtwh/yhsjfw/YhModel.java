/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-26 ����09:32:21 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ϵͳ����
 * @�๦������:�û���
 * @���ߣ� ligl
 * @ʱ�䣺 2014-2-26 ����09:32:21
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */

public class YhModel extends ActionForm {

	private static final long serialVersionUID = 3371779777190121512L;
	private String type;
	private Pages pages = new Pages();
	private String yhm; // �û���
	private String xm; // ����
	private String kl; // ����
	private String zdm; // �����
	private String szbm; // ���ڲ���
	private String dwdm; // ��λ����
	private String qx; // ����
	private String sffz; // �Ƿ����
	private String sffdy; // �Ƿ񸨵�Ա
	private String sfbzr; // �Ƿ������

	/**
	 * @���� ��
	 */
	public YhModel() {
		super();
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getKl() {
		return kl;
	}

	public void setKl(String kl) {
		this.kl = kl;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	public String getSffz() {
		return sffz;
	}

	public void setSffz(String sffz) {
		this.sffz = sffz;
	}

	public String getSffdy() {
		return sffdy;
	}

	public void setSffdy(String sffdy) {
		this.sffdy = sffdy;
	}

	public String getSfbzr() {
		return sfbzr;
	}

	public void setSfbzr(String sfbzr) {
		this.sfbzr = sfbzr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

}
