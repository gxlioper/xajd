
package xgxt.dtjs.utils;

import java.lang.reflect.InvocationTargetException;

import xgxt.base.DealString;

/**
* <p>Title: 学工管理系统</p>
* <p>Description: 学生信息管理思政队伍-model和string[]之间转换用类</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author 鲁宁
* @version 1.0
*/
public class ModelToStrings {
	public static String [] modelToStrings(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	//    转换model里的数据到输出数组里
		String [] inputList = new String [colList.length];
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			inputList[i] 	     = DealString.toGBK((String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null));
		}
		return inputList;	
	}
}
