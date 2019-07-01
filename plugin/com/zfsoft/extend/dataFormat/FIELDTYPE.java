package com.zfsoft.extend.dataFormat;

import java.util.HashMap;
import java.util.Map;

public enum FIELDTYPE {
	
	STRING, NUMBER, EMAIL, FLOAT, DATE, DATETIME, IDCARD, TEL, ZIP, MOBILE, INT,PATTERN;
	
	private static final Map<String, FIELDTYPE> stringToEnum = new HashMap<String, FIELDTYPE>();
	
	static {
		for (FIELDTYPE blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static FIELDTYPE fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
