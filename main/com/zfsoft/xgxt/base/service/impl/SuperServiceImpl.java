package com.zfsoft.xgxt.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.service.SuperService;

/**
 * 通用增、删、改、查基础service
 * @author qph
 * 日期2013-4-10
 */
public abstract class SuperServiceImpl<T , E extends SuperDAOImpl<T>> implements SuperService<T> {

	public E dao ;
	public Log logger = LogFactory.getLog(SuperServiceImpl.class);
	
	@Deprecated
	public void setDao(E e){
		dao = e;
	}
	
	
	@SuppressWarnings("unchecked")
	public SuperServiceImpl(){
		Type genType = getClass().getGenericSuperclass(); 
		
		if (genType instanceof ParameterizedType){
		
	        Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
			try {
				this.dao = (E) ((Class<?>) params[1]).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*
	 * 
	 * 描述: @see com.zfsoft.xgxt.base.service.SuperService#getModel(java.lang.String)
	 * @param keyValue
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.service.SuperService#getModel(java.lang.String)
	 */
	public T getModel(String keyValue) throws Exception {
		return dao.getModel(keyValue);
	}


	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#getPageList(java.lang.Object)
	 */
	public List<HashMap<String,String>> getPageList(T t) throws Exception {
		return dao.getPageList(t);
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String,String>> getPageList(T t, User user) throws Exception {
		return dao.getPageList(t, user);
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#runInsert(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runInsert(T t) throws Exception {
		
		T o = (T) StringUtils.formatBean(t);
		
		return dao.runInsert(o);
	}
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#runInsert(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runInsertTrim(T t) throws Exception {
		
		T o = (T) StringUtils.formatBeanTrim(t);
		
		return dao.runInsertTrim(o);
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#runUpdate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runUpdate(T t) throws Exception {
		
		T o = (T) StringUtils.formatBean(t);
		
		return dao.runUpdate(o);
	}
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#runUpdate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runUpdateTrim(T t) throws Exception {
		
		T o = (T) StringUtils.formatBeanTrim(t);
		
		return dao.runUpdateTrim(o);
	}


	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#getModel(java.lang.Object)
	 */
	public T getModel(T t) throws Exception {
		return dao.getModel(t);
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.SuperService#runDelete(java.lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {
		return dao.runDelete(values);
	}



	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperService#getAllList(java.lang.Object, xgxt.form.User)
	 */
	
	public List<HashMap<String, String>> getAllList(T t, User user)
			throws Exception {
		
		return dao.getAllList(t, user);
	}



	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperService#getAllList(java.lang.Object)
	 */
	
	public List<HashMap<String, String>> getAllList(T t) throws Exception {
		return dao.getAllList(t);
	}

}
