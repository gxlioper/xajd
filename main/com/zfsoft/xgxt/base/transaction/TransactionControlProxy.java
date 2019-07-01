/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-20 ����04:49:23 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �������ش���
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-1-20 ����04:49:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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

	              logger.info(method+"��ʼ�������");
	              o = method.invoke(obj, args);
	              transcationManager.commit();
	              logger.info(method+"�ύ����");
	           }catch(Exception e){
	        	   transcationManager.rollback();
	        	   Throwable t = e.getCause();
	        	   e.printStackTrace();
	        	   String message = t.getLocalizedMessage();
	        	   logger.debug("������Ϣ:"+t.getLocalizedMessage());
	        	   logger.info(method+"����ع�");
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


