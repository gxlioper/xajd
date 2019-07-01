package com.zfsoft.xgxt.base.export.validator.error;

import java.util.List;

/**
 * <li>������Ϣ����</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public interface IErrorBindingResult {
	/**
	 * ��ȡĿ���������ơ�ͨ��class��getName()������ȡ�ġ���ˣ����������������ơ�
	 * 
	 * @return
	 */
	public String getTargetName();

	/**
	 * ��ȡĿ�����
	 * 
	 * @return
	 */
	public Object getTarget();

	/**
	 * ��ȡ���еĴ�����Ϣʵ�塣���û�У��򷵻�һ������Ϊ���List����
	 * 
	 * @return
	 */
	public List<FieldError> getAllErrors();

	/**
	 * ��һ����������е����д�����Ϣ�󶨵���ǰ�Ĵ�������С�ע�⣺��������������еĶ�����ͬһ������󶨻�ʧ�ܣ� <br>
	 * ���resultΪ�ջ�û����֤��Ϣʱ���򲻲�����
	 * 
	 * @param result
	 */
	public void addErrors(IErrorBindingResult result);

	/**
	 * �����Ƿ�����֤��Ϣ��
	 * 
	 * @return true���У�false���ޡ�
	 */
	public boolean hasErrors();

	/**
	 * ��ȡָ�����Ե�������֤��Ϣ�����û�ж�Ӧ����Ϣ���򷵻س���Ϊ0��List���ϡ�<br>
	 * ���fieldΪ�գ����׳��쳣��
	 * 
	 * @param field
	 *            ָ��������
	 * @return ���ض�Ӧ�Ĵ�����Ϣ
	 */
	public List<FieldError> getFieldErrors(String field);

	/**
	 * ��ȡָ�����Ե�������֤��Ϣ����ͨ���ַ������ء�����������ж����֤��Ϣ����ÿ��֮�������ķֺŸ��������û�ж�Ӧ����֤��Ϣ���򷵻�""��<br>
	 * ���fieldΪ�գ����׳��쳣��
	 * 
	 * @param field
	 *            ָ������������
	 * @return ���ض�Ӧ�������е���֤��Ϣ
	 */
	public String getFieldErrorMessage(String field);

	/**
	 * ע��һ�����Ե���֤��Ϣ����������Զ�Ӧ��ͬ������Ϣ�Ѿ����ڣ��򲻱���ӽ�ȥ����ֹ�ظ�����֤��Ϣ�� ���ָ������ϢΪ�գ��򲻲�����
	 * 
	 * @param field
	 *            ��������
	 * @param errMessage
	 *            ��֤��Ϣ��
	 * 
	 */
	public void rejectValue(String field, String errMessage);
}
