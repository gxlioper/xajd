package com.zfsoft.xgxt.base.export.validator.rules;

/**
 * ��֤����Ľӿڡ����еľ�����֤����ʵ��������ӿڡ�
 * 
 * @author Jiangdong.Yi
 * 
 */
public interface IValidateRule {
	/**
	 * ���о���������֤�߼���
	 * 
	 * @param value
	 *            �������֤���ݡ�
	 * @return true����֤ͨ����false����֤��ͨ��
	 */
	public boolean validate(Object value);

	/**
	 * ��ȡ��֤��ͨ��ʱ�ı�����Ϣ������û�ָ������֤��ϢΪ�գ��򷵻�Ĭ�ϵı�����Ϣ��
	 * 
	 * @return ������֤�ı�����Ϣ
	 */
	public String getValidateInfo();
}
