package com.zfsoft.extend.dataFormat.formator;

import com.zfsoft.extend.dataFormat.IField;

public class IntField implements IField {

	public boolean check(String value) {
		 try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String getErrorMessage(String value) {
		return "请输入整数！";
	}

}
