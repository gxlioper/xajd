/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-25 ����10:09:01 
 */  
package com.zfsoft.xgxt.base.export.validator.rules;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Ա���֤
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-25 ����10:09:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XbCheckRule implements IValidateRule{
	private final String _MAN="��";
	private final String _WOMAN="Ů";
	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if(_MAN.equals(value.toString())||_WOMAN.equals(value.toString())){
			return true;
		}
		return false;
	}
	public String getValidateInfo() {
		return " ����д��ȷ���Ա�[��/Ů]��";
	}
}
