package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.extend.dataFormat.IField;

public class TelField implements IField {

	public boolean check(String value) {
		return isTel(value);
	}

	public String getErrorMessage(String value) {
		return "格式不正确！";
	}

	
	private boolean isTel(String str){
		 String telregex = "(^[0-9]{3,4}-[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{7,15}$)";
	     return Match(telregex, str);
	}
	
	/**
     * 正则验证方法
     * 
     * @param regexstr
     * @param str
     * @return
     */
    private boolean Match(String regexstr, String str) {
        Pattern regex = Pattern.compile(regexstr,Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }
	
}
