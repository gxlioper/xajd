
package xgxt.szdw.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import xgxt.base.DealString;

/**
* <p>Title: ѧ������ϵͳ</p>
* <p>Description: ѧ����Ϣ����˼������-model��string[]֮��ת������</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author ³��
* @version 1.0
*/

public class ModelToStrings {
	public static String [] modelToStrings(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	//    ת��model������ݵ����������
		String [] inputList = new String [colList.length];
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			inputList[i] 	     = DealString.toGBK((String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null));
		}
		return inputList;	
	}
	
	//��ת��
	public static String [] modelToStrings2(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//    ת��model������ݵ����������
	String [] inputList = new String [colList.length];
	Class myClass = model.getClass();
	for(int i = 0 ;i<colList.length;i++){
		String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
		inputList[i] 	     = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
	}
	return inputList;	
	}
	
	public static Class formToGBK(Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	//   ��form����model������String���͵�ֵת��һ��
	Class myClass = model.getClass();
	Method[] methods=myClass.getMethods();
	for(int i = 0 ;i<methods.length;i++){
		String methodOne = methods[i].getName();
		String methodType = methods[i].getReturnType().getName();
		if(methodOne.length()>3&&methodOne.substring(0, 3).equalsIgnoreCase("get")&&methodType.equalsIgnoreCase("java.lang.String")){
			String setMethod    = "set"+methodOne.substring(3);
			String newValue = DealString.toGBK((String) myClass.getMethod(methodOne,(Class[])null).invoke(model,(Object[]) null));
			myClass.getMethod(setMethod, new Class[]{String.class}).invoke(model, newValue);
		}
	}
	return null;	
	}
}
