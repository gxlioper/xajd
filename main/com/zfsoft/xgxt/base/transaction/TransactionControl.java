/**
 * @部门:学工产品事业部
 * @日期：2017-1-20 下午04:45:28 
 */  
package com.zfsoft.xgxt.base.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 事务控制注解
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-1-20 下午04:45:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransactionControl {


}
