/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.xgxt.base.export.util.DateUtils;

/**
 * @className	�� TimeRule
 * @description	�� ��֤ʱ���ʽΪ yyyy-MM-dd
 * @author 		��CP��1352��
 * @date		�� 2018-4-13 ����03:28:51
 * @version 	V1.0
 */
public class TimeRule implements IValidateRule{
	private String validateInfo;
	private final String defaultMessage = "����ʱ���ʽΪ��YYYY-MM-DD!";
	public TimeRule() {

	}
	
	public TimeRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
		}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		String check = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(String.valueOf(value));
		return matcher.matches();
	}

}
