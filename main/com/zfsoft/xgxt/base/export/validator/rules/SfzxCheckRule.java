/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-25 ����10:32:18 
 */
package com.zfsoft.xgxt.base.export.validator.rules;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Ƿ���У��֤
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-25 ����10:32:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SfzxCheckRule implements IValidateRule {
	private final String _ZX="��У";
	private final String _BZX="����У";
	public String getValidateInfo() {
		return " ����д��ȷ����У��Ϣ [��У/����У]��";
	}
	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if (_ZX.equals(value.toString())|| _BZX.equals(value.toString())) {
			return true;
		}
		return false;
	}

}
