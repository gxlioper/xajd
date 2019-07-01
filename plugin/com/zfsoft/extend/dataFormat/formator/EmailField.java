package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.extend.dataFormat.IField;

public class EmailField implements IField {

	public boolean check(String value) {
		return isEmail(value);
	}

	public String getErrorMessage(String valu) {
		return "格式不正确！";
	}
	
	private boolean isEmail(String str) { 
		 Pattern p = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");         
	     Matcher m = p.matcher(str);     
		 return m.matches();
	}
	
}
