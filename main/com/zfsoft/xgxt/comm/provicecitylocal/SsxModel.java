/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-12 ����03:38:25 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-12-12 ����03:38:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SsxModel extends ActionForm {
	private String provicedm;//ʡ����
	private String citydm;//�д���
	private String localdm;//��������
	/**
	 * @return the localdm
	 */
	public String getLocaldm() {
		return localdm;
	}
	/**
	 * @param localdmҪ���õ� localdm
	 */
	public void setLocaldm(String localdm) {
		this.localdm = localdm;
	}
	/**
	 * @return the provicedm
	 */
	public String getProvicedm() {
		return provicedm;
	}
	/**
	 * @param provicedmҪ���õ� provicedm
	 */
	public void setProvicedm(String provicedm) {
		this.provicedm = provicedm;
	}
	/**
	 * @return the ctiydm
	 */
	public String getCitydm() {
		return citydm;
	}
	/**
	 * @param ctiydmҪ���õ� ctiydm
	 */
	public void setCitydm(String citydm) {
		this.citydm = citydm;
	}
	
}
