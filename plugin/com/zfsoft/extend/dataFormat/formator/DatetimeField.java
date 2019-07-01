package com.zfsoft.extend.dataFormat.formator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.dataFormat.IField;

public class DatetimeField implements IField {

	/**
	 * 日期格式
	 */
	private String pattern;
	
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public DatetimeField() {
		super();
	}

	public DatetimeField(String pattern) {
		super();
		this.pattern = pattern;
	}

	public boolean check(String value) {
		DateFormat dateFormat;
		if(StringUtils.isBlank(pattern)){
			dateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
		}else{
			dateFormat = new SimpleDateFormat(pattern);
		}
		try {
			dateFormat.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public String getErrorMessage(String value) {
		return "不符合[" + (StringUtils.isBlank(pattern) ? DEFAULT_PATTERN : pattern) + "]格式";
	}
	
}
