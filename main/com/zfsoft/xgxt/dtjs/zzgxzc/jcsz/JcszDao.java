/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��1��25�� ����9:05:05 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgForm;

import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž�����֯��ϵת������ģ��
 * @�๦������: ��������Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��1��25�� ����9:05:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszDao extends SuperDAOImpl<JcszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setTableName("xg_dtjs_zzgxzc_csszb");
		this.setKey("id");
		this.setClass(JcszForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * @throws Exception  
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��25�� ����11:00:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * JcszForm �������� 
	 * @throws 
	 */
	public JcszForm getModel() throws Exception {
		String sql = "select * from xg_dtjs_zzgxzc_csszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ���뿪��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����11:37:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String getSqKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_dtjs_zzgxzc_csszb t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},"sqkg");
	}
}
