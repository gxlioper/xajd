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
 * @�๦������: ��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class FlszDao extends SuperDAOImpl<FlszModel> {

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
	 * @����:���ݹ��ܴ����ȡ�����б�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:07:46
	 * @�޸ļ�¼: 
	 * @param gndm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndm(String gndm)
			throws Exception {
		String sql = "select a.*,b.bhmk,b.gnlx,b.yzsz from xg_zdybd_flszb a,xg_zdybd_gnszb b  where a.gndm=b.gndm and a.gndm=? ";
		sql += " and a.sfqy='1' ";
		sql += " order by a.flflszid,to_number(a.xsxx) ";
		String[] input = { gndm };
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	protected void setTableInfo() {
		super.setTableName("xg_zdybd_flszb");
		super.setKey("flszid");
	}
	
	/**
	 * 
	 * @����: ���flflszidΪ�յķ����б��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-17 ����05:35:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gndm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmDx(String gndm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is null");
		sql.append(" order by to_number(xsxx)) a,");
		sql.append(" xg_zdybd_gnszb b");
		sql.append(" where a.gndm = b.gndm");
		return dao.getListNotOut(sql.toString(), new String[]{gndm});
	}
	
	/**
	 * 
	 * @����: ���flflszidΪ�յķ����б�С������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-17 ����05:35:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gndm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmXx(String gndm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = 'xsxx_query'");
		sql.append(" and flflszid is not null");
		sql.append(" order by flflszid,to_number(xsxx)) a,");
		sql.append(" xg_zdybd_gnszb b");
		sql.append(" where a.gndm = b.gndm");
		return dao.getListNotOut(sql.toString(), new String[]{gndm});
	}

}
