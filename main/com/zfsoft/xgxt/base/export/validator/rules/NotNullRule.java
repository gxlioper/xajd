package com.zfsoft.xgxt.base.export.validator.rules;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * ��Ϊ����֤�Ĺ��������ж϶����Ƿ�Ϊ�ա� <li>��ʽ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class NotNullRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "����Ϊ�գ�";

	public NotNullRule() {

	}

	public NotNullRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		return value != null;
	}
}
