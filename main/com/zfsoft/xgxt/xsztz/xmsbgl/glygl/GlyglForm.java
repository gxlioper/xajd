/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����10:26:28 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import xgxt.comm.CommForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����10:26:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GlyglForm extends CommForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String yhm;//�û���
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
