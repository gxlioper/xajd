package com.zfsoft.xgxt.base.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/******ϵͳ���ܶ�дȨ��******/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemAuth {
	
	public enum ReadWrite{ WRITEABLE,READONLY};
	
	
	/***url������ģ�����õ�·��***/
	public String [] url();
	
	
	/***��дȨ�ޣ�Ĭ��Ϊֻ��***/
	public ReadWrite rewritable() default ReadWrite.READONLY;
	
	
}
