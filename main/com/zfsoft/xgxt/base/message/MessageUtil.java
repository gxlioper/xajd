package com.zfsoft.xgxt.base.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 资源文件工具
 * @author Penghui.Qu
 * 日期：2013-4-16
 */
public class MessageUtil {

	private static ResourceBundle messageBundle = null;
	
	static {
		/*
		 * 加载message.properties
		 */
		messageBundle = ResourceBundle.getBundle("config/message");
	}
	
	/**
	 * 获取message.properties中的消息
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getText(String key,Object[] params){
		String message = null;
		try{
			message = messageBundle.getString(key);
		} catch(MissingResourceException e){
			e.printStackTrace();
		}
		message = setParams(params, message);
		return message;
	}
	

	
	/**
	 * 为资源文件中的参数设值
	 * @param params
	 * @param message
	 * @return
	 */
	private static String setParams(Object[] params, String message) {
		if (null != params && params.length > 0){
			for (int i = 0 ; i < params.length ; i++){
				message = message.replace("{"+i+"}", String.valueOf(params[i]));
			}
		}
		return message;
	}
	
	/**
	 * 获取message.properties中的消息
	 * @param key
	 * @return
	 */
	public static String getText(String key){
		return getText(key,null);
	}
	
	
	/**
	 * 从message.properties中取消息，带单个参数
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getText(String key,Object params){
		return getText(key,new Object[]{params});
	}
	
	
}
