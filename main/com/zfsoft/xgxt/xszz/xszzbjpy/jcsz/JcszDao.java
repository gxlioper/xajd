package com.zfsoft.xgxt.xszz.xszzbjpy.jcsz;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


public class JcszDao extends SuperDAOImpl<JcszForm> {

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_xszz_xszzjbszb");
		super.setClass(JcszForm.class);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public JcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" case when bjpykg=1 and sysdate between to_date(nvl(bjpykssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append(" and to_date(nvl(bjpyjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end bjpyisopen ");
		sql.append(" from xg_xszz_new_xszz_xszzjbszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * ɾ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJcsz(JcszForm model) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_xszz_new_xszz_xszzjbszb";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
}
