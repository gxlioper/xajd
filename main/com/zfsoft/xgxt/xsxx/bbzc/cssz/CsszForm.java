/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-23 ����10:55:47 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-23 ����10:55:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszForm extends ActionForm{
	
	private String id;
	private String zckg;
	private String zckssj;
	private String zcjssj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZckg() {
		return zckg;
	}
	public void setZckg(String zckg) {
		this.zckg = zckg;
	}
	public String getZckssj() {
		return zckssj;
	}
	public void setZckssj(String zckssj) {
		this.zckssj = zckssj;
	}
	public String getZcjssj() {
		return zcjssj;
	}
	public void setZcjssj(String zcjssj) {
		this.zcjssj = zcjssj;
	}
	
	

}
