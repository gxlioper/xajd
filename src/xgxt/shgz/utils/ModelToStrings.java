
package xgxt.shgz.utils;

import java.lang.reflect.InvocationTargetException;

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
}
