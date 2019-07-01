package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * 
 * ��֤�ַ�������ĸ�����ֵĹ��� <li>��ʽ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class CharAndNumberRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "ֻ��������ĸ�����֣�";

	public CharAndNumberRule() {

	}

	public CharAndNumberRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		String check = "[\\da-zA-Z]+";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(String.valueOf(value));
		return matcher.matches();
	}

}
