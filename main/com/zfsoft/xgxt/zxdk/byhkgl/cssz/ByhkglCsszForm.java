/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����09:38:38 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: �������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����09:38:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglCsszForm extends ActionForm{
	
	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String splc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}

}
