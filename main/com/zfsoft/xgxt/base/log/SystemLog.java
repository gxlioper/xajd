/**
 * @部门:学工产品事业部
 * @日期：2013-5-17 上午09:06:06 
 */  
package com.zfsoft.xgxt.base.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类方法描述注解
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

	/**
	 * 描述
	 * @return
	 */
	public String description() default "无描述信息";
}
