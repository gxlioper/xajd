/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:29:04 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ��actionForm
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:29:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwwhForm extends ZwlxForm{

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String zwid ;//ְ��id
	private String zwmc ;//ְ������
	private String lxdm ;//ְ�����ʹ���
	private String zwzz ;//ְ��
	private String bz ;//��ע
	public String getZwid() {
		return zwid;
	}
	public void setZwid(String zwid) {
		this.zwid = zwid;
	}
	public String getZwmc() {
		return zwmc;
	}
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	public String getZwzz() {
		return zwzz;
	}
	public void setZwzz(String zwzz) {
		this.zwzz = zwzz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	
}
