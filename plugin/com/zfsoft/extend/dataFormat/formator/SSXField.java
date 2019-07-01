package com.zfsoft.extend.dataFormat.formator;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xgxt.DAO.DAO;

import com.zfsoft.extend.dataFormat.IField;

public class SSXField implements IField {

	private static String QUERY_SH = "select distinct sydqdm dm,sydq mc from dmk_sydq ORDER BY sydqdm";
	
	private static String QUERY_QX = "select distinct qxdm dm,qxmc mc from dmk_qx order by qxdm";
	
	private static DAO dao = DAO.getInstance();
	
	private static List<HashMap<String, String>> SH = dao.getListNotOut(QUERY_SH, new String[]{});
	
	private static List<HashMap<String, String>> QX = dao.getListNotOut(QUERY_QX, new String[]{});
	
	public boolean check(String value) {
		return isEmail(value);
	}

	public String getErrorMessage(String valu) {
		return "地址格式不正确！";
	}
	
	private boolean isEmail(String str) { 
		 Pattern p = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");         
	     Matcher m = p.matcher(str);     
		 return m.matches();
	}
	
	public String transToDM(String mc){
		return null;
	}
	
	public String transToMc(String dm){
		return null;
	}
	
}
