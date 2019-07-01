/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午05:19:10 
 */  
package com.zfsoft.extend.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午05:19:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class MethodQueryDescriptor extends DataSourceQuery{

	private Class targetClazz;
	
	private String methodName;
	
	private String params;

	/*
	    描述: @see com.zfsoft.extend.service.DataSourceQuery#getData()
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
	 * @描述 ：TODO描述下当前构造方法
	 */
	public MethodQueryDescriptor() {
		super();
	}

	/**
	 * @描述 ：TODO描述下当前构造方法
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
	 * @param targetClazz要设置的 targetClazz
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
	 * @param methodName要设置的 methodName
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
	 * @param params要设置的 params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	
	
}
