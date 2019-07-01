package com.zfsoft.xgxt.base.export.validator.rules;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * �ַ�������֤���� <li>����String</li> <li>Ĭ����С����Ϊ0����󳤶�Ϊ20</li> <li>�������ݸ�ʽ��Χ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class StringLengthRule implements IValidateRule {
	private String validateInfo;
	private Integer min = 0;
	private Integer max = 20;
	private final String defaultMessage = "�ַ����Ȳ���" + min + "-" + max + "��Χ�ڣ�";

	public StringLengthRule(Integer min, Integer max) {
		this.min = min;
		this.max = max;
		this.validateInfo = "�ַ����Ȳ���" + min + "-" + max + "��Χ��!";
	}

	public StringLengthRule(Integer min, Integer max, String message) {
		this.min = min;
		this.max = max;
		this.validateInfo = message;
	}

	public StringLengthRule() {

	}

	public StringLengthRule(String message) {
		this.validateInfo = message;
	}
	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}
	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		if (value.toString().length() < min) {
			return false;
		} else if (value.toString().length() > max) {
			return false;
		}
		return true;

	}

	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * ��ȡ�ַ����ĳ��ȣ���������ģ���ÿ�������ַ���Ϊ2λ
	 * 
	 * @param value
	 *            ָ�����ַ���
	 * @return �ַ����ĳ���
	 */
	private int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* ��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1 */
		for (int i = 0; i < value.length(); i++) {
			/* ��ȡһ���ַ� */
			String temp = value.substring(i, i + 1);
			/* �ж��Ƿ�Ϊ�����ַ� */
			if (temp.matches(chinese)) {
				/* �����ַ�����Ϊ2 */
				valueLength += 2;
			} else {
				/* �����ַ�����Ϊ1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
}
