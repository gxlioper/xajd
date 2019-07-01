/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����04:24:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: ѧ�����ڹ���
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����04:24:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqglDao extends SuperDAOImpl<KqglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ѧ�����ڵǼǽ����ѯ
	 */
	public List<HashMap<String, String>> getPageList(KqglForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_new_dc_rcsw_kqgl t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	  
	
	protected void setTableInfo() {
		super.setTableName("xg_kqgl_kqdj");
		super.setKey("kqdjid");
	}
	
	
	/**
	 * 
	 * @����:���������ظ����жϣ�ѧ�ţ�����ʱ�䣬�������ͣ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����05:32:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(KqglForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_kqgl_kqdj ");
		sql.append(" where xh = ? and kqsj = ? and kqlxdm = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getKqsj(), form.getKqlxdm()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @����:�޸ı����ظ����жϣ�ѧ�ţ�����ʱ�䣬�������ͣ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����05:34:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(KqglForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_kqgl_kqdj ");
		sql.append(" where xh = ? and kqsj = ? and kqlxdm = ? and kqdjid <> ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getKqsj(), form.getKqlxdm() ,form.getKqdjid()}, "num");
		
		return num;
		
	}
	
	
	/**
	 * 
	 * @����:���ڵǼǽ�������鿴
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����05:45:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kqdjid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKqdjList(String kqdjid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_new_dc_rcsw_kqgl where kqdjid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[] { kqdjid });
		
	}
	
}
