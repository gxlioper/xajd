package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;

/**
 * 
 * 验证字符串是单精度浮点型的规则 <li>格式验证</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class FloatRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "只能输入小数点后带一位数字的数值！";

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
