/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-3 ����10:59:52 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-3 ����10:59:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QjXySzDao extends SuperDAOImpl<QjXySzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjXySzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjXySzForm t, User user)
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
		super.setClass(QjXySzForm.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_new_qjgl_qjxyszb");
	}
	
	/**
	 * 
	 * @����:���������ű�Ӧ��ֻ��һ����¼�����Բ��Ӳ�ѯ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-3 ����11:10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQjXySzDada() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,mc,content from xg_rcsw_new_qjgl_qjxyszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
//	//��ȡclob�ֶΣ�Э���������ݣ�
//	public String getContent_Clob() throws Exception{
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select content from xg_rcsw_new_qjgl_qjxyszb ");
//		return dao.getOneRs(sql, inputValue, outputValue);
//	}
	
	//��ɾһ����е�����
	public boolean del_Table_content() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_new_qjgl_qjxyszb");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
   
	/**
	 * 
	 * @����:��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����02:20:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(QjXySzForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_rcsw_new_qjgl_qjxyszb (mc,content,bjsj,bjr) values(?,?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getMc(),t.getContent(),t.getBjsj(),t.getBjr()});
	}
}
