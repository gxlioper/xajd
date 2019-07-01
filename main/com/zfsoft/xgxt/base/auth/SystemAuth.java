package com.zfsoft.xgxt.base.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/******系统功能读写权限******/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemAuth {
	
	public enum ReadWrite{ WRITEABLE,READONLY};
	
	
	/***url，功能模块配置的路径***/
	public String [] url();
	
	
	/***读写权限，默认为只读***/
	public ReadWrite rewritable() default ReadWrite.READONLY;
	
	
}
