package com.zfsoft.xgxt.comm.zdybd.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.zdybd.model.FlszModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: ͨ����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ZdybdBaseDao extends SuperDAOImpl<FlszModel> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * 
	 * @param t
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 * 
	 * @param t
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:ͨ��sql��ȡ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-28 ����05:02:05
	 * @�޸ļ�¼:
	 * @param sql
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListBySql(String sql)
			throws Exception {
		return dao.getListNotOut(sql, null);
	}


	/**
	 * 
	 * @����:���ʡ���б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-2 ����04:54:59
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getShList() throws Exception {
		String sql = "select distinct sydqdm dm,sydq mc from dmk_sydq";
		sql += "   order by sydqdm";
		String[] input = {};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}	
	
	/**
	 * 
	 * @����:��������б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-2 ����04:54:59
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQxList() throws Exception {
		String sql = "select distinct qxdm dm,qxmc mc from dmk_qx";
		sql += "   order by qxdm";
		String[] input = {};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}

}
