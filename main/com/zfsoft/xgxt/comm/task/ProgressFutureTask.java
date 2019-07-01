/**
 * @部门:学工产品事业部
 * @日期：2016年10月14日 下午2:54:50 
 */  
package com.zfsoft.xgxt.comm.task;

import java.util.concurrent.FutureTask;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 带返回任务执行进度的Callable
 * @作者： 屈朋辉[工号:445]
 * @时间： 2016年10月14日 下午2:55:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 * @param <V>
 */

public class ProgressFutureTask<V> extends FutureTask<V> {

	private ProgressCallable<V> callable;
	
	public ProgressFutureTask(ProgressCallable<V> callable) {
		super(callable);
		this.callable=(ProgressCallable<V>) callable;
	}

	/**
	 * 
	 * @描述: 返回任务进度百分比
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午5:07:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 */
	public String getProgress(){
		return callable.getProgress();
	}
}
