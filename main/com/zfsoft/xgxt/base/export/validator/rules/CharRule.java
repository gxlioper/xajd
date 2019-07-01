package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;



/**
 * 
 * ��֤�ַ�������ĸ�Ĺ��� <li>��ʽ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class CharRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "ֻ��������ĸ��";

	public CharRule() {

	}

	public CharRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		String check = "[a-zA-Z]+";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher((String) value);
		return matcher.matches();
	}

}
