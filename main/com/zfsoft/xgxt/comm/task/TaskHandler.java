/**
 * @部门:学工产品事业部
 * @日期：2016年10月14日 下午1:28:03 
 */  
package com.zfsoft.xgxt.comm.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公共模块
 * @类功能描述: 多线程任务处理器
 * @作者： 屈朋辉[工号:445]
 * @时间： 2016年10月14日 下午1:28:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TaskHandler {
	
	private Log logger = LogFactory.getLog(TaskHandler.class);
	
	private TaskHandler(){
		if (logger.isDebugEnabled()){
			logger.debug("task handler is created !");
		}
	}
	
	private Map<String,Map<String,Future<String>>> taskMap = new ConcurrentHashMap<String,Map<String,Future<String>>>();
	private Map<String,ExecutorService> cutorMap = new ConcurrentHashMap<String, ExecutorService>();
	
	
	private static class HandlerFactory{
		private static final TaskHandler handler = new TaskHandler();
	}
	
	/**
	 * 
	 * @描述: 获取 TaskHandler 实例对象 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午5:07:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * TaskHandler 返回类型 
	 */
	public static TaskHandler getInstance(){
		return HandlerFactory.handler;
	}
	

	/**
	 * 
	 * @描述:启动任务处理器
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午1:29:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @return 
	 * @throws
	 */
	public void startup(String type){
		
		if (cutorMap.containsKey(type)){
			
			if (logger.isDebugEnabled()){
				logger.debug(String.format("$s:task handler is running !", type));
			}
			return ;
		}
		
		ExecutorService exeService = Executors.newCachedThreadPool();
		
		cutorMap.put(type, exeService);
		
		if (logger.isDebugEnabled()){
			logger.debug(String.format("$s:task handler is start !", type));
		}
	}
	
	
	/**
	 * 
	 * @描述:关闭任务处理器
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午1:30:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void shutdown(String type){
		
		if (cutorMap.containsKey(type)){
			ExecutorService exeService = cutorMap.get(type);
			exeService.shutdown();
			taskMap.get(type).clear();
		}
		
		if (logger.isDebugEnabled()){
			logger.debug("task handler is shutdown !");
		}
	}
	
	
	/**
	 * 
	 * @描述: 添加任务
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午1:33:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id 任务ID
	 * @param runnable
	 * void 返回类型 
	 * @throws
	 */
	public void addTask(String type,String id,ProgressCallable<String> callable){
		
		if (!cutorMap.containsKey(type)){
			throw new NullPointerException("task handler is not be created !");
		}
		
		Future<String> future  = new ProgressFutureTask<String>(callable);
		
		ExecutorService exeService = cutorMap.get(type);
		Map<String,Future<String>> futureType = taskMap.get(type);
		
		if (futureType == null){
			futureType = new ConcurrentHashMap<String, Future<String>>();
		}
		
		exeService.submit((Runnable) future);
		futureType.put(id, future);
		taskMap.put(type, futureType);
		
		if (logger.isDebugEnabled()){
			logger.debug(String.format("task handler add a task,id=%s", id));
		}
		
	}
	
	
	/**
	 * 
	 * @描述: 检测任务是否完成
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午2:08:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id 任务ID
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean isComplate(String type,String id) throws Exception{
		Future<String> future = getTaskById(type,id);
		return future.isDone();
	}
	
	
	//根据ID获取任务
	private ProgressFutureTask<String> getTaskById(String type ,String id) throws Exception{
		
		if (!taskMap.containsKey(type) || !taskMap.get(type).containsKey(id)){
			throw new Exception(String.format("Can`t find task for id:%s", id));
		}
		
		Future<String> future = taskMap.get(type).get(id);
		
		return (ProgressFutureTask<String>) future;
	}
	
	
	/**
	 * 
	 * @描述: 查询任务进度
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午1:35:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * String 返回类型 
	 * @throws Exception 
	 */
	public String getTaskProgress(String type ,String id) throws Exception{
		ProgressFutureTask<String> future = getTaskById(type,id);
		String progress = future.getProgress();
		
		if (logger.isDebugEnabled()){
			logger.debug(String.format("progress info : %s-%s %s", type,id,progress));
		}
		return progress;
	}
	
	
}
