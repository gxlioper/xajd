/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��10��14�� ����1:28:03 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: ���߳���������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2016��10��14�� ����1:28:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @����: ��ȡ TaskHandler ʵ������ 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����5:07:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * TaskHandler �������� 
	 */
	public static TaskHandler getInstance(){
		return HandlerFactory.handler;
	}
	

	/**
	 * 
	 * @����:������������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����1:29:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
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
	 * @����:�ر���������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����1:30:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
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
	 * @����: �������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����1:33:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id ����ID
	 * @param runnable
	 * void �������� 
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
	 * @����: ��������Ƿ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����2:08:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id ����ID
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean isComplate(String type,String id) throws Exception{
		Future<String> future = getTaskById(type,id);
		return future.isDone();
	}
	
	
	//����ID��ȡ����
	private ProgressFutureTask<String> getTaskById(String type ,String id) throws Exception{
		
		if (!taskMap.containsKey(type) || !taskMap.get(type).containsKey(id)){
			throw new Exception(String.format("Can`t find task for id:%s", id));
		}
		
		Future<String> future = taskMap.get(type).get(id);
		
		return (ProgressFutureTask<String>) future;
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����1:35:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * String �������� 
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
