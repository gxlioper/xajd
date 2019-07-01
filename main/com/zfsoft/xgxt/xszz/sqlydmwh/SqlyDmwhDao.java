/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-21 ����09:33:58 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-�������϶�
 * @�๦������: �������ɴ���ά�� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-21 ����09:33:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqlyDmwhDao extends SuperDAOImpl<SqlyDmwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SqlyDmwhForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.sqlydm,a.sqlymc from xg_xszz_xbmz_dmwh a where 1=1 ");
		
		if (!StringUtil.isNull(t.getSqlymc())){
			params.add(t.getSqlymc());
			sql.append(" and sqlymc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(sqlydm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SqlyDmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(SqlyDmwhForm.class);
		super.setTableName("xg_xszz_xbmz_dmwh");
		super.setKey("sqlydm");
	}
	
	/**
	 * 
	 * @����: �ظ�����֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����01:37:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String sqlywhCheckExist(SqlyDmwhForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_xszz_xbmz_dmwh where sqlymc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getSqlymc()}, "num");
		
		return num;
		
	}
	

}
