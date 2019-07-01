package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;

/**
 * 
 * ��֤�ַ����ǵ����ȸ����͵Ĺ��� <li>��ʽ��֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class FloatRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "ֻ������С������һλ���ֵ���ֵ��";

	public FloatRule() {

	}

	public FloatRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		String check = "[1-9]*[0-9](\\.)[0-9][0-9]*";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(String.valueOf(value));
		return matcher.matches();
	}
}
