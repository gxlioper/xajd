package xgxt.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;

	public class CommonUpdata {
		/**
		 * ͨ��updata������ͨ������������ж�model���Ƿ���ֵ���ڣ����������¸��ֶΣ��������޸�
		 * ������������飬���ж�model����Щ��ֵ��������ֵ���ֶ�
		 * @param colList
		 * @param model
		 * @param tableName
		 * @param pk
		 * @param pkValue
		 * @param request
		 * @return
		 * @throws Exception
		 */
		public static boolean commonUpdata(String[] colList,Object model,String tableName,String pk,String pkValue, HttpServletRequest request) throws Exception{
			//    ת��model������ݵ����������
			ArrayList<String> inputListArray = new ArrayList<String>();
			ArrayList<String> colListArray = new ArrayList<String>();
			if(colList!=null){
				colListArray.addAll(java.util.Arrays.asList(colList));
				Class myClass = model.getClass();
				for(int i = 0 ;i<colList.length;i++){
					String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
					String colValue = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
					if(colValue!=null){
						inputListArray.add((String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null));
					}else{
						colListArray.remove(colList[i]);
					}
				}
			}else{
				Class myClass = model.getClass();
				Method[] methods =  myClass.getDeclaredMethods();
				for(int i = 0 ;i<methods.length;i++){
					String methodName    = methods[i].getName();
					Type returnType    = methods[i].getReturnType();
					if(methodName.substring(0,3).equalsIgnoreCase("get")&&returnType==String.class){
						String colValue = (String)methods[i].invoke(model,(Object[]) null);
						if(colValue!=null){
							inputListArray.add((String)methods[i].invoke(model,(Object[]) null));
							colListArray.add(methodName.substring(3));
							}
						}
					}
				}
			String[] columns = colListArray.toArray(new String[colListArray.size()]);
			String[] values = inputListArray.toArray(new String[inputListArray.size()]);
			
			if(!Base.isNull(pkValue)){	
				return StandardOperation.updateNolog(tableName, columns, values, pk, pkValue);
			}else{
				return StandardOperation.insertNoLog(tableName, columns, values);
			}
		}
}
