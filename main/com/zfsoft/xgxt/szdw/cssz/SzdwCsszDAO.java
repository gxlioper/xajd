/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-2 ����06:53:42 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:�������� DAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-2 ����06:53:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwCsszDAO extends SuperDAOImpl<SzdwCsszForm>{

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwCsszForm t)
			throws Exception {
		return null;
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwCsszForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����:
	 */
	
	@Override
	public SzdwCsszForm getModel(String keyValue) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when kg=1 and sysdate between to_date(nvl(kssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(jssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_xtwh_shlcsqkzb a ");
		sql.append("where a.key = ? ");
		return super.getModel(sql.toString(), new String[] { keyValue });
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xtwh_shlcsqkzb");
		super.setKey("key");
		super.setClass(SzdwCsszForm.class);
	}

}
