/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����07:35:46 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����07:35:46
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxCsSzForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String bynd;
	private String kgzt;

	private String shlid;

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getShlid() {
		return shlid;
	}

	public void setShlid(String shlid) {
		this.shlid = shlid;
	}

	/**
	 * @return the bynd
	 */
	public String getBynd() {
		return bynd;
	}

	/**
	 * @param byndҪ���õ� bynd
	 */
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	

}
