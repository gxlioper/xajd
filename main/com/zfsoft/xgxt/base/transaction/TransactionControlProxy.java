/**
 * @部门:学工产品事业部
 * @日期：2017-1-20 下午04:49:23 
 */  
package com.zfsoft.xgxt.base.transaction;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.DAO.TranscationManager;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 事务拦截代理
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-1-20 下午04:49:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TransactionControlProxy implements MethodInterceptor{
	protected Log logger = LogFactory.getLog(TransactionControlProxy.class);
	protected TranscationManager transcationManager = TranscationManager.getInstance();
	private Object obj;
	 
	private TransactionControlProxy(Object obj){
		this.obj=obj;
	}
	

    public static <T>T newProxy(T t){

    	Enhancer en = new Enhancer();

        en.setSuperclass(t.getClass());

        en.setCallback(new TransactionControlProxy(t));

        Object o = en.create();

        return (T)o;

    }
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		Object o = null;

	       if(method.isAnnotationPresent(TransactionControl.class)){

	           try{

	              logger.info(method+"开始事务控制");
	              o = method.invoke(obj, args);
	              transcationManager.commit();
	              logger.info(method+"提交事务");
	           }catch(Exception e){
	        	   transcationManager.rollback();
	        	   Throwable t = e.getCause();
	        	   e.printStackTrace();
	        	   String message = t.getLocalizedMessage();
	        	   logger.debug("错误信息:"+t.getLocalizedMessage());
	        	   logger.info(method+"事务回滚");
	        	   if(StringUtils.isNotNull(message) && t instanceof SystemException){
	        		   throw t;
	        	   }else{
	        		   throw new SystemException(MessageKey.SYS_OPERATE_FAIL);
	        	   }
	           }finally{
	              transcationManager.closeConnection();
	           }

	       }else{

	           o = method.invoke(obj,args);

	       }

	       return o;

	    }

	}


