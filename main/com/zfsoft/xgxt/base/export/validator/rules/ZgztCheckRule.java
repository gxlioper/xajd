/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-25 ����10:09:01 
 */  
package com.zfsoft.xgxt.base.export.validator.rules;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڸ�״̬��֤
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2014-10-20 ����11:09:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZgztCheckRule implements IValidateRule{
	private final String _ZG="zg";
	private final String _TG="tg";
	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if(_ZG.equals(value.toString())||_TG.equals(value.toString())){
			return true;
		}
		return false;
	}
	public String getValidateInfo() {
		return " ����д��ȷ���ڸ�״̬[zg/tg]��";
	}
}
