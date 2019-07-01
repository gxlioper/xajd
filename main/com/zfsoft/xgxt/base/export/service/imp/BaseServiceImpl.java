package com.zfsoft.xgxt.base.export.service.imp;

import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.export.business.BusinessFactory;
import com.zfsoft.xgxt.base.export.business.IBaseBusiness;
import com.zfsoft.xgxt.base.export.dao.BaseDao;
import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.base.export.service.BaseService;

/**
 * ͨ��Serviceʵ��<br>
 * ����ʱ�䣺2012-5-3<br>
 * ˵����daoBaseע�뷽ʽ �޸��ˣ�ZhenFei.Cao �޸�ʱ�䣺2012-08-02
 * 
 * @param <T>
 *            model
 * @param <E>
 *            dao
 * 
 */
public class BaseServiceImpl<T, E extends BaseDao<T>> implements BaseService<T> {

	protected E dao;

	// protected ICacheClient cache;

	/**
	 * ����springע��
	 * 
	 * @param dao
	 */
	public void setDao(E dao) {
		this.dao = dao;
	}

	/**
	 * ���Ӽ�¼
	 * 
	 * @param t
	 * @return
	 */
	public boolean insert(T t) {
		int result = dao.insert(t);
		return result > 0 ? true : false;
	}

	/**
	 * �޸ļ�¼
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t) {
		int result = dao.update(t);
		return result > 0 ? true : false;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	public T getModel(String id) {
		return dao.getModel(id);
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param t
	 * @return
	 */
	public T getModel(T t) {
		return dao.getModel(t);
	}

	/**
	 * ����ɾ��
	 * 
	 * @param map
	 * @return
	 */
	public boolean batchDelete(Map<String, Object> map) {
		int result = dao.batchDelete(map);
		return result > 0 ? true : false;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param list
	 * @return
	 */
	public boolean batchDelete(List list) {
		int result = dao.batchDelete(list);
		return result > 0 ? true : false;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param map
	 * @return
	 */
	public boolean batchUpdate(Map<String, Object> map) {
		int result = dao.batchUpdate(map);
		return result > 0 ? true : false;
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getPagedList(T t) {
		return dao.getPagedList(t);
	}

	/**
	 * �޷�ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getModelList(T t) {

		return dao.getModelList(t);
	}

	/**
	 * �޷�ҳ��ѯ
	 * 
	 * @param str
	 * @return
	 */
	public List<T> getModelList(String... str) {

		return dao.getModelList(str);
	}

	/**
	 * ͳ�Ƽ�¼��
	 * 
	 * @param t
	 * @return
	 */
	public int getCount(T t) {
		return dao.getCount(t);
	}

	/**
	 * �����ݷ�Χ��ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 */
	public List<T> getModelListByScope(T t) {

		return dao.getModelListByScope(t);
	}

	/**
	 * �����ݷ�Χ�޷�ҳ��ѯ
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<T> getPagedByScope(T t) {

		return dao.getPagedByScope(t);

	}

	public IBaseBusiness getBaseBusiness(ImportModel im) {
		return BusinessFactory.getInstance(im);
	}
	/*
	 * public ICacheClient getCache() { return cache; }
	 * 
	 * @Autowired(required = false)
	 * 
	 * @Qualifier("newMemcachedClient") public void setCache(ICacheClient cache)
	 * { this.cache = cache; }
	 */
}
