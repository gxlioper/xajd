package com.zfsoft.xgxt.base.export.dao;

import java.util.List;
import java.util.Map;

/**
 * �����ƣ� ͨ��DAO�ӿ� �����ˣ�Penghui.Qu ����ʱ�䣺2012-5-3 �޸��ˣ�Zhenfei.Cao �޸�ʱ�䣺2012-8-2
 */
public interface BaseDao<T> {

	/**
	 * ���Ӽ�¼
	 * 
	 * @param t
	 * @return
	 */
	public int insert(T t);

	/**
	 * �޸ļ�¼
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * ��ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	public T getModel(String id);

	/**
	 * ��ѯ��������
	 * 
	 * @param t
	 * @return
	 */
	public T getModel(T t);

	/**
	 * ����ɾ��
	 * 
	 * @param map
	 * @return
	 */
	public int batchDelete(Map<String, Object> map);

	/**
	 * ����ɾ��
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int batchDelete(List list);

	/**
	 * �����޸�
	 * 
	 * @param map
	 * @return
	 */
	public int batchUpdate(Map<String, Object> map);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getPagedList(T t);

	/**
	 * �޷�ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getModelList(T t);

	/**
	 * �޷�ҳ��ѯ
	 * 
	 * @param str
	 * @return
	 */
	public List<T> getModelList(String... str);

	/**
	 * ͳ�Ƽ�¼��
	 * 
	 * @param t
	 * @return
	 */
	public int getCount(T t);

	/**
	 * �����ݷ�Χ��ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getPagedByScope(T t);

	/**
	 * �����ݷ�Χ�޷�ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getModelListByScope(T t);
}
