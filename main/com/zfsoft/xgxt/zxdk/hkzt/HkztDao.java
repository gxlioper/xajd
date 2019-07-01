/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-5 ����09:40:15 
 */  
package com.zfsoft.xgxt.zxdk.hkzt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����״̬
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-5 ����09:40:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HkztDao extends SuperDAOImpl<HkztModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_hkztb");
		super.setClass(HkztModel.class);
		super.setKey("dm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkztModel t)
			throws Exception {
		
		StringBuilder sql = new StringBuilder("select * from xg_zxdk_hkztb");
		List<String> params = new ArrayList<String>();
		
		if (!StringUtil.isNull(t.getMc())){
			sql.append(" where mc like '%'||?||'%'");
			params.add(t.getMc());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkztModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_zxdk_hkztb t1 ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("dm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") and not exists (select 1 from xg_xtwh_shztb t2 where t1.dm=t2.zd2)");
		
		return dao.runDelete(sql.toString(), values);
	}

	
}
