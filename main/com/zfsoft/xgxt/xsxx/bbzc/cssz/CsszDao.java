/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-23 ����10:55:58 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-23 ����10:55:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
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

	public CsszForm getModel() throws Exception{
		String sql = "select * from xg_xsxx_bdzc_csszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @����:����ע�Ὺ��״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����01:44:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsszParam(){
		StringBuilder sql = new StringBuilder();
		sql.append("select case when t.zckg = 1 and sysdate between to_date(nvl(t.zckssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.zcjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end zczt ");
		sql.append(" from xg_xsxx_bdzc_csszb t  where 1=1");
		return dao.getOneRs(sql.toString(), new String[]{}, "zczt");
		
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_bdzc_csszb");
		
	}

}
