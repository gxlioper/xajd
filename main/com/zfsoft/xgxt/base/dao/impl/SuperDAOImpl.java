package com.zfsoft.xgxt.base.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.SuperDAO;


/**
 * 
 * ͨ������ɾ���顢�Ļ�����
 * @author qph
 * ���ڣ�2013-4-10
 */
public abstract class SuperDAOImpl<T> implements SuperDAO<T>{
	

	protected Log logger = LogFactory.getLog(SuperDAOImpl.class);
	protected DAO dao = DAO.getInstance();
	private String tableName;
	private String key;
	private Class<T> c ;;
	
	protected SuperDAOImpl() {
		setTableInfo();
	}
	
	
	/**
	 * ��������DAO����Ӧ�ı�
	 */
	protected abstract void setTableInfo(); 
	
	

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}


	protected void setKey(String key) {
		this.key = key;
	}


	protected void setClass(Class<T> c){
		this.c = c;
	}
	

	/**
	 * @return the tableName
	 */
	protected String getTableName() {
		return tableName;
	}


	/**
	 * @return the key
	 */
	protected String getKey() {
		return key;
	}


	/**
	 * ��ҳ��ѯ
	 * @param t
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	protected List<HashMap<String,String>> getPageList(T t,String sql,String[] input) throws Exception{
		
		List<HashMap<String,String>> resultList = null;
		
		try {
			Pages page = (Pages) t.getClass().getMethod("getPages", new Class[]{}).invoke(t);
			
			if (StringUtil.isNull(sql)){
				throw new NullPointerException("sql is null !");
			}
			
			//��ҳSQL
			String pageSql = getPageSql1(page,sql);//�����µĲ�ѯ��¼sql�����������¼��ѯ�ֿ�
			
			logger.debug(pageSql);
			logger.debug(Arrays.toString(input));
			//�����
			resultList = dao.getListNotOut(pageSql, input);
			int total = getTotal(page,sql,input);//��ѯ��¼����
			if(resultList != null){//��ԭ�й��򣬽���¼�����ӵ��������
				for (HashMap<String, String> hashMap : resultList) {
					if(hashMap != null){
						hashMap.put("total", total + "");
					}
				}
			}
		} catch (NoSuchMethodException e) {
			logger.error("Can't find method named 'getPages' in model.");
			throw e;
		}
		
		
		return resultList;
	}
	
	
	/*
	 * ���ɷ�ҳ��ѯSQL
	 */
	private String getPageSql(Pages page , String sql){
		
		StringBuilder pageSql = new StringBuilder();
		
		pageSql.append("select *  from (");
		pageSql.append("select k.*,count(*) over() as total,");
		
		String sortName = page.getSortName();
		String sortOrder = page.getSortOrder();
		
		if (!StringUtil.isNull(sortName)){
			pageSql.append("row_number() over(order by ");
			pageSql.append(sortName);
			pageSql.append(" ");
			pageSql.append(sortOrder);
			pageSql.append(")");
		} else {
			pageSql.append("rownum ");
		}
		
		pageSql.append(" as rowindex from (");
		pageSql.append(sql);
		pageSql.append(") k ) z where z.rowindex <= ");
		pageSql.append(page.getStart() + page.getPageSize());
		pageSql.append(" and z.rowindex > ");
		pageSql.append(page.getStart());
		
		return pageSql.toString();
	}
	
	/*
	 * ��ѯ��¼sql�����������¼��ѯ�ֿ�
	 */
	private String getPageSql1(Pages page , String sql){		
		StringBuilder pageSql = new StringBuilder();		
		pageSql.append("select *  from (");
		pageSql.append("select k.*,rownum rowindex from (");		
		pageSql.append("select * from (");
		pageSql.append(sql);		
		pageSql.append(" ) ");
		String sortName = page.getSortName();
		String sortOrder = page.getSortOrder();		
		if (!StringUtil.isNull(sortName)){
			pageSql.append("order by ");
			pageSql.append(sortName);
			pageSql.append(" ");
			pageSql.append(sortOrder);
		}		
		pageSql.append(") k) ");
		pageSql.append(" where rowindex between ");
		pageSql.append(page.getStart() + 1);		
		pageSql.append(" and ");
		pageSql.append(page.getStart() + page.getPageSize());
		return pageSql.toString();
	}
	
	/*
	 * ��ȡ��¼����
	 */
	private int getTotal(Pages page , String sql,String[] input){
		int count = 0;
		StringBuilder pageSql = new StringBuilder();		
		pageSql.append("select count(*) count from (");
		pageSql.append(sql);
		pageSql.append(")");
		HashMap<String, String> map = dao.getMapNotOut(pageSql.toString(), input);
		if (map != null && map.size() > 0) {
			try {
				count = Integer.parseInt(map.get("count"));
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	/*
	 * �����߼�����
	 */
	private boolean runInsert(T t , String tableName) throws Exception{
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				if (!StringUtil.isNull(value)){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		String sql = getInsertSql(keys,tableName);
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.insert(sql, values.toArray(new String[]{}));
	}
	private boolean runInsertNotCommit(T t , String tableName) throws Exception{
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				if (!StringUtil.isNull(value)){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		String sql = getInsertSql(keys,tableName);
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.insertNotCommit(sql, values.toArray(new String[]{}));
	}
	
	
	
	/*
	 * ���� insert ���
	 */
	private String getInsertSql(List<String> keys ,String tableName){
		
		if (keys.isEmpty()){
			logger.error("����insert sql ����");
		}
		
		StringBuilder insertSql = new StringBuilder();
		
		insertSql.append("insert into ");
		insertSql.append(tableName);
		insertSql.append(" ( ");
		
		insertSql.append(keys.toString().replace("[", "").replace("]", ""));
		insertSql.append(") values (");
		
		for (int i = 0, n = keys.size() ; i < n ; i++){
			insertSql.append("?");
			if (i != n-1){
				insertSql.append(",");
			}
		}
		insertSql.append(") ");
		return insertSql.toString();
	}


	
	
	/*
	 * �޸��߼�����
	 */
	private boolean runUpdate(T t ,String tableName, String key ,String oldKeyValue) throws Exception{
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				//������ַ���
				if (value != null){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		if (StringUtil.isNull(oldKeyValue) || StringUtil.isNull(oldKeyValue)){
			logger.error("����������ֵ����Ϊ��!");
			throw new NullPointerException();
		}
		
		values.add(oldKeyValue);
		String sql = getUpdateSql(keys,tableName,key);
		
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.runUpdate(sql, values.toArray(new String[]{}));
	}
	
	private boolean runUpdateNotCommit(T t ,String tableName, String key ,String oldKeyValue) throws Exception{
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				//������ַ���
				if (value != null){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		if (StringUtil.isNull(oldKeyValue) || StringUtil.isNull(oldKeyValue)){
			logger.error("����������ֵ����Ϊ��!");
			throw new NullPointerException();
		}
		
		values.add(oldKeyValue);
		String sql = getUpdateSql(keys,tableName,key);
		
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.runUpdateNotCommit(sql, values.toArray(new String[]{}));
	}
	
	


	/*
	 * �޸Ĳ���
	 */
	private boolean runUpdate(T t , String tableName, String key) throws Exception{
		
		if (StringUtil.isNull(key) ){
			logger.error("��������Ϊ��!");
			throw new NullPointerException();
		}
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String keyValue = null;
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				if (fieldName.equalsIgnoreCase(key)){
					
					if (StringUtil.isNull(value)){
						logger.error("����ֵ����Ϊ��");
						throw new NullPointerException();
					} else {
						keyValue = value;
					}
				}
				
				//�������ֶΡ�������ַ���
				if (!fieldName.equalsIgnoreCase(key) && value != null){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		values.add(keyValue);
		String sql = getUpdateSql(keys,tableName,key);
		
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.runUpdate(sql, values.toArray(new String[]{}));
	}
	
	private boolean runUpdateNotCommit(T t , String tableName, String key) throws Exception{
		
		if (StringUtil.isNull(key) ){
			logger.error("��������Ϊ��!");
			throw new NullPointerException();
		}
		
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(tableName);
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String keyValue = null;
		
		for (Field f : fields){
			
			String fieldName = f.getName();
			
			if (columns.contains(fieldName.toUpperCase())){
				
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				String value = null;
				try {
					value = (String) t.getClass().getMethod(methodName, new Class[]{}).invoke(t);
				} catch (Exception e) {
					logger.error("����get��������,methodName:"+methodName);
					throw e;
				} 
				
				if (fieldName.equalsIgnoreCase(key)){
					
					if (StringUtil.isNull(value)){
						logger.error("����ֵ����Ϊ��");
						throw new NullPointerException();
					} else {
						keyValue = value;
					}
				}
				
				//�������ֶΡ�������ַ���
				if (!fieldName.equalsIgnoreCase(key) && value != null){
					keys.add(fieldName);
					values.add(value);
				}
			}
		}
		
		values.add(keyValue);
		String sql = getUpdateSql(keys,tableName,key);
		
		logger.debug(sql);
		logger.debug(Arrays.toString(values.toArray(new String[]{})));
		return dao.runUpdateNotCommit(sql, values.toArray(new String[]{}));
	}


	
	/*
	 * ����update���
	 */
	private String getUpdateSql(List<String> keys ,String tableName ,String key ){
		
		
		if (keys.isEmpty()){
			logger.error("����insert sql ����");
		}
		
		StringBuilder updateSql = new StringBuilder();
		
		updateSql.append("update ");
		updateSql.append(tableName);
		updateSql.append(" set ");
		
		for (int i = 0, n = keys.size() ; i < n ; i++){
			
			updateSql.append(keys.get(i));
			updateSql.append("=? ");
			if (i != n-1){
				updateSql.append(",");
			}
		}
		updateSql.append("where ");
		updateSql.append(key);
		updateSql.append("=?");
		return updateSql.toString();
		
	}

	
	/*
	 * ������ѯ����
	 */
	@SuppressWarnings("unchecked")
	private T getModel(T t , String tableName, String key) throws Exception{
		
		if (StringUtil.isNull(key) || StringUtil.isNull(tableName)){
			logger.error("the key and tableName can't null !");
			throw new NullPointerException();
		}
		
		try {
			String keyValue = (String) t.getClass().getMethod("get"+key.substring(0, 1).toUpperCase()+key.substring(1)).invoke(t);
			StringBuilder sql = new StringBuilder();
			
			sql.append("select * from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(key);
			sql.append("=?");
			
			logger.debug(sql);
			logger.debug("keyValue:"+keyValue);
			
			Map<String,String> values = dao.getMapNotOut(sql.toString(), new String[]{keyValue});
			
			if (!values.isEmpty()){
			
				T model = (T) t.getClass().getConstructor().newInstance();
				
				for(Map.Entry<String,String> entry : values.entrySet()){
					String property = entry.getKey();
					String value = entry.getValue();
					
					try {
						Method setMethod = model.getClass().getMethod("set"+property.substring(0, 1).toUpperCase()+property.substring(1),String.class);
						setMethod.invoke(model, value);
					} catch(NoSuchMethodException e){
						e.printStackTrace();
						continue;
					}
				}
				
				return model;
			} 
			
			return null;
		} catch (Exception e) {
			logger.error("����key �� set ��������");
			throw e;
		} 
		
	}
	
	
	/**
	 * ������ѯ���Լ�дSQL
	 * @param t
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected T getModel(T t ,String sql,String[] input) throws Exception{
		
		logger.debug(sql);
		logger.debug(Arrays.toString(input));
		
		Map<String,String> values = dao.getMapNotOut(sql.toString(), input);
		
		if (!values.isEmpty()){
			
			T model = (T) t.getClass().getConstructor().newInstance();
			
			for(Map.Entry<String,String> entry : values.entrySet()){
				String property = entry.getKey();
				String value = entry.getValue();
				String methodName = "set"+property.substring(0, 1).toUpperCase()+property.substring(1);
				
				try {
					Method setMethod = model.getClass().getMethod(methodName,String.class);
					setMethod.invoke(model, value);
				} catch (Exception e) {
					logger.error("����"+model.getClass().getName()+"��"+methodName+"����");
				} 			
			}
			return model;
		}
		
		return null;
	}
	
	
	
	/*
	 * 
	 * ����: @see com.zfsoft.xgxt.base.dao.SuperDAO#getModel(java.lang.String, java.lang.String[])
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.SuperDAO#getModel(java.lang.String, java.lang.String[])
	 */
	protected T getModel(String sql,String[] input) throws Exception{
		
		T model = (T) c.getConstructor().newInstance();
		
		return getModel(model, sql, input);
	}
	
	
	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#getPageList(java.lang.Object)
	 */
	public abstract List<HashMap<String, String>> getPageList(T t) throws Exception;
	

	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public abstract List<HashMap<String, String>> getPageList(T t, User user) throws Exception;

	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runInsert(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runInsert(T t) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runInsert(o, tableName);
	};
	
	public boolean runInsertNotCommit(T t) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runInsertNotCommit(o, tableName);
	};
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runInsert(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runInsertTrim(T t) throws Exception{
		T o = (T) StringUtils.formatBeanTrim(t);
		return runInsert(o, tableName);
	};
	

	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runUpdate(T t) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runUpdate(o, tableName, key);
	};
	@SuppressWarnings("unchecked")
	public boolean runUpdateNotCommit(T t) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runUpdateNotCommit(o, tableName, key);
	};
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean runUpdateTrim(T t) throws Exception{
		T o = (T) StringUtils.formatBeanTrim(t);
		return runUpdate(o, tableName, key);
	};
	
	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean runUpdate(T t ,String keyValue) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runUpdate(o, tableName, key , keyValue);
	};
	@SuppressWarnings("unchecked")
	public boolean runUpdateNotCommit(T t ,String keyValue) throws Exception{
		T o = (T) StringUtils.formatBean(t);
		return runUpdateNotCommit(o, tableName, key , keyValue);
	};


	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#getModel(java.lang.Object)
	 */
	public T getModel(T t) throws Exception{
		return getModel(t, tableName, key);
	};

	
	/*
	 * 
	 * ����: @see com.zfsoft.xgxt.base.dao.SuperDAO#getModel(java.lang.String)
	 * @param keyValue
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.SuperDAO#getModel(java.lang.String)
	 */
	public T getModel(String keyValue) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(key);
		sql.append("=?");
		logger.debug(sql);
		logger.debug("keyValue:"+keyValue);
		return getModel(sql.toString(), new String[]{keyValue});
	}
	
	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runDelete(java.lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append(key);
			sql.append("=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
	}

	
	
	/*
	 * 
	      ����: @see com.zfsoft.xgxt.base.dao.SuperDAO#getAllList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String,String>> getAllList(T t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getPageList(t, user);
	}




	/*
	 * 
	      ����: @see com.zfsoft.xgxt.base.dao.SuperDAO#getAllList(java.lang.Object)
	 */
	public List<HashMap<String,String>> getAllList(T t)  throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getPageList(t);
	}





}
