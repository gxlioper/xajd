package com.zfsoft.xgxt.gygl.gypynew.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CsszDao extends SuperDAOImpl<CsszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(CsszForm.class);
		this.setKey("id");
		this.setTableName("xg_gygl_new_xjqscsszb");
	}
	
	/**
	 * 
	 * @����:��ȡ�������ñ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����04:34:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplc(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_new_xjqscsszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}

}
