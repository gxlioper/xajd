/**
 * @部门:学工产品事业部
 * @日期：2016年10月14日 下午4:37:28 
 */  
package com.zfsoft.xgxt.comm.task;

import java.util.concurrent.Callable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 带返回任务进度的Callable
 * @作者： 屈朋辉[工号:445]
 * @时间： 2016年10月14日 下午4:37:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public interface ProgressCallable<V> extends Callable<V> {

	
	/**
	 * 
	 * @描述: 获取进度百分比
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午4:38:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getProgress();
}
