/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-23 ����05:02:03 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������(У���)
 * @�๦������: �������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-23 ����05:02:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XwhjCsszForm extends ActionForm{
	
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
