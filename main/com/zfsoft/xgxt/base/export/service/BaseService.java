package com.zfsoft.xgxt.base.export.service;

import java.util.List;
import java.util.Map;

/**
 * �����ƣ�ͨ��Service�ӿ� ����ʱ��:2012-5-3 �޸��ˣ�ZhenFei.Cao �޸�ʱ�䣺2012-8-2
 */
public interface BaseService<T> {

	/**
	 * ���Ӽ�¼
	 * 
	 * @param t
	 * @return
	 */
	public boolean insert(T t);

	/**
	 * �޸ļ�¼
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t);

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
	public boolean batchDelete(Map<String, Object> map);

	/**
	 * ����ɾ��
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean batchDelete(List list);

	/**
	 * �����޸�
	 * 
	 * @param map
	 * @return
	 */
	public boolean batchUpdate(Map<String, Object> map);

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
	 * �޷�ҳ��ѯ<br>
	 * <p>
	 * MyBatis�ж����ط���֧��ȱ�ݣ�XML��ʹ�ø÷���ӳ����<br>
	 * �κ�һ��string�������޷�ӳ�䣬����XML�н�ӳ��˷���һ�Σ�<br>
	 * ������������ҵ�����Լ��ӿ��ж�������������<br>
	 * </p>
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
