package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * 
 * ��֤�Ƿ�Ϊ��������Ĺ��� <li>���ڸ�ʽ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class SingleEmailRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "�����ʽ����ȷ��";

	public SingleEmailRule() {

	}

	public SingleEmailRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String) {
			// String check = "^(\\w+[-_.]?)+@((\\w+[-_]?)+\\.)+[a-zA-Z]{2,}$";
			String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher((String) value);
			return matcher.matches();
		} else {
			return false;
		}
	}

}
