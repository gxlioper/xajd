package com.zfsoft.xgxt.base.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;


/**
 * ��������ɾ���ġ���ӿ�
 * @author qph
 * ���� 2013-4-10
 */
public interface SuperDAO <T>{

	/**
	 * ��ͨ��ѯ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(T t) throws Exception;
	
	
	/**
	 * ���û�Ȩ�޲�ѯ
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(T t, User user) throws Exception;
	
	
	/**
	 * ����
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean runInsert(T t) throws Exception;
	
	public boolean runInsertNotCommit(T t) throws Exception;
	
	
	/**
	 * �޸ģ����޸�����ֵ ��
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean runUpdate(T t) throws Exception;
	
	public boolean runUpdateNotCommit(T t) throws Exception;
	
	
	/**
	 * �޸ģ����޸�����ֵ ��
	 * @param t
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean runUpdate(T t ,String keyValue) throws Exception;
	
	public boolean runUpdateNotCommit(T t ,String keyValue) throws Exception;
	
	
	/**
	 * ����ɾ��
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int runDelete(String[] values) throws Exception;
	
	
	/**
	 * ������ѯ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public T getModel(T t) throws Exception;
	
	
	
	/**
	 * 
	 * @����: ������ѯ������Ϊ����ֵ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-8 ����11:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * T �������� 
	 * @throws
	 */
	public T getModel(String keyValue) throws Exception;
	
	
	
	
	/**
	 * 
	 * @����: ��ѯȫ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����05:33:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllList(T t, User user) throws Exception;
	
	
	
	/**
	 * 
	 * @����: ��ѯȫ�����ݣ���ͨ��ѯģʽ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-28 ����09:54:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllList(T t)  throws Exception;
}
