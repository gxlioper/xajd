package com.zfsoft.xgxt.rcsw.gjgl.qjsdxz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class QjsdxzDao extends SuperDAOImpl<QjsdxzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(QjsdxzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjsdxzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(QjsdxzForm.class);
		this.setKey("id");
		this.setTableName("XG_RCSW_QJSDCSSZB");
	}
	
	/**
	 * @throws Exception 
	 *
	 * @����: getModel��д
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-11 ����02:47:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * QjsdxzForm �������� 
	 * @throws
	 */
	@Override
	public QjsdxzForm getModel(QjsdxzForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_RCSW_QJSDCSSZB ");
		return getModel(sql.toString(), new String[]{});
		
	}
	
	/**
	 * 
	 * @����: ɾ�����¼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-11 ����02:58:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteRow() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_RCSW_QJSDCSSZB ");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{});
	}

}
