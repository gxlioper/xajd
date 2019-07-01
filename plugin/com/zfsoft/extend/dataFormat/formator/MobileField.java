package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.extend.dataFormat.IField;

public class MobileField implements IField {

	public boolean check(String value) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(value);
		b = m.matches();
		return b;
	}

	public String getErrorMessage(String value) {
		return "不是有效手机号码！";
	}
}
