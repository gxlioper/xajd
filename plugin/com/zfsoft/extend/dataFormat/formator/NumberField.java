package com.zfsoft.extend.dataFormat.formator;

import org.apache.commons.lang.math.NumberUtils;

import com.zfsoft.extend.dataFormat.IField;

public class NumberField implements IField {

	public boolean check(String value) {
		return NumberUtils.isNumber(value);
	}

	public String getErrorMessage(String value) {
		return "请输入数字！";
	}
}
