package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.dataFormat.IField;

public class PatternField implements IField {

	private String pattern;
	
	private String errorMessage;

	public PatternField() {
		super();
	}

	public PatternField(String pattern) {
		super();
		if(StringUtils.contains(pattern, "#")){
			this.pattern = StringUtils.split(pattern, "#")[0];
			this.errorMessage = StringUtils.split(pattern, "#")[1];
		}else{
			this.pattern = pattern;
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean check(String value) {
		try {
			Pattern p = null;
			Matcher m = null;
			boolean b = false;
			p = Pattern.compile(pattern);
			m = p.matcher(value);
			b = m.matches();
			return b;
		} catch (Exception e) {
			throw new RuntimeException("Field:Pattern-->" + pattern
					+ " is illegality");
		}
	}

	public String getErrorMessage(String value) {
		return "格式不正确！";
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
