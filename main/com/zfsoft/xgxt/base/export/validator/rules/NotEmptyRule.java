package com.zfsoft.xgxt.base.export.validator.rules;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * 不能为空验证规则。 <li>针对对象、String、List等</li> <li>格式验证</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class NotEmptyRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "不能为空！";

	public NotEmptyRule() {

	}

	public NotEmptyRule(String message) {
		validateInfo = message;
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if (value.getClass().isArray()) {
			return Array.getLength(value) > 0;
		} else if (value instanceof Collection<?>) {
			return ((Collection<?>) value).size() > 0;
		} else if (value instanceof Map<?, ?>) {
			return ((Map<?, ?>) value).size() > 0;
		} else if (value instanceof Number) {
			return ((Number) value) != null;
		} else if (value instanceof String) {
			return ((String) value).length() > 0;
		} else {
			// 以上都不是，则认为非空。因为空的情况在一开始就判断过了！
			return true;
		}
	}

	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}
}
