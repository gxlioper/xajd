/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-21 ����05:29:51 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ɫͨ��
 * @�๦������: ��������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-21 ����05:29:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdJcszForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//��ǰʱ���Ƿ���
	
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
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
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

}
