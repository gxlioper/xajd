/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:21:25 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �Զ����ѯ��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-5-27 ����02:21:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpzForm extends ActionForm{

	
	private static final long serialVersionUID = 1L;

	private String gnbz;
	private String gnmc;
	
	private String guid;
	private String key;
	private String value;
	
	public String getGnbz() {
		return gnbz;
	}
	public void setGnbz(String gnbz) {
		this.gnbz = gnbz;
	}
	public String getGnmc() {
		return gnmc;
	}
	public void setGnmc(String gnmc) {
		this.gnmc = gnmc;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
