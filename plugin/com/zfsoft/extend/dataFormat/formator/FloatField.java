package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Pattern;

import com.zfsoft.extend.dataFormat.IField;

public class FloatField implements IField {

	public boolean check(String value) {
		
		return isFloat(value);
	}

	public String getErrorMessage(String value) {
		return "请输入浮点类型！";
	}

	private boolean isFloat(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
	
}
