/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����08:48:02 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��ͥ������� ��������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����08:48:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtqkdcJcszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String sqkg ;//���뿪�� 
	private String sqkssj ;//���뿪ʼʱ�� 
	private String sqjssj ;//�������ʱ�� 
	private String isOpen ;
	
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
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	
}
