/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����05:19:10 
 */  
package com.zfsoft.extend.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����05:19:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class MethodQueryDescriptor extends DataSourceQuery{

	private Class targetClazz;
	
	private String methodName;
	
	private String params;

	/*
	    ����: @see com.zfsoft.extend.service.DataSourceQuery#getData()
	*/
	
	@Override
	public List<HashMap<String, String>> getData() {
		return invokeMethodOnObject();
	}

	private List<HashMap<String,String>> invokeMethodOnObject(){
		if(targetClazz == null){
			return null;
		}
		
		if(StringUtils.isBlank(methodName)){
			return null;
		}
		try {
			Object newInstance = targetClazz.newInstance();
			Method method = targetClazz.getMethod(methodName, String.class);
			Object invoke = method.invoke(newInstance, params);
			if(invoke instanceof List<?>){
				return (List)invoke;
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public MethodQueryDescriptor() {
		super();
	}

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param targetClazz
	 * @param methodName
	 * @param params
	 */
	public MethodQueryDescriptor(Class targetClazz, String methodName,
			String params) {
		super();
		this.targetClazz = targetClazz;
		this.methodName = methodName;
		this.params = params;
	}

	/**
	 * @return the targetClazz
	 */
	public Class getTargetClazz() {
		return targetClazz;
	}

	/**
	 * @param targetClazzҪ���õ� targetClazz
	 */
	public void setTargetClazz(Class targetClazz) {
		this.targetClazz = targetClazz;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodNameҪ���õ� methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param paramsҪ���õ� params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	
	
}
