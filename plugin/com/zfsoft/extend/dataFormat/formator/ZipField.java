package com.zfsoft.extend.dataFormat.formator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfsoft.extend.dataFormat.IField;

public class ZipField implements IField {

	public boolean check(String value) {
		return isZipCode(value);
	}

	public String getErrorMessage(String value) {
		return "格式不正确！";
	}

	private boolean isZipCode(String str){
		 Pattern p = Pattern.compile("^[0-9]{6}$");         
	     Matcher m = p.matcher(str);     
		 return m.matches();
	}
	
}
