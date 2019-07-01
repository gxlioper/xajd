/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:37:18 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ģ��
 * @�๦������: ����ת����������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:37:31 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazccsszDao extends SuperDAOImpl<DazccsszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazccsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazccsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_dazccsszb");
		super.setClass(DazccsszForm.class);
	}
	
	/**
	 * @����: ��ѯ����������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-27 ����06:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * DazccsszForm �������� 
	 * @throws
	 */
	public DazccsszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.fid uploadid,a.*, case when sqkg = 1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_xsxx_dagl_dazccsszb a ");
		sql.append("left join xg_comm_fileupload_data b on a.fjid = b.gid ");
		sql.append("");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: �������ñ��水ť����ɾ�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-7 ����04:11:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteTableName() throws Exception{
		String sql = "delete from xg_xsxx_dagl_dazccsszb";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * @����: ȡ����������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-8 ����11:35:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getSplc(){
		String sql = "select * from xg_xsxx_dagl_dazccsszb where rownum = 1";
		return dao.getMapNotOut(sql, new String[]{});
	}

}
