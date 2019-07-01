/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-26 ����04:34:40 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl.qsdskh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: ���ҵ�ʦ����
 * @���ߣ� ��ˮ��[���ţ�1150]
 * @ʱ�䣺 2014-11-26 ����04:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdskhDao extends SuperDAOImpl<QsdskhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdskhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdskhForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t1.* from view_XG_GYGL_QSDSKH t1 where 1=1 ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * ��ȡ���ҵ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsdskh(QsdskhForm model){
		String sql = "select t1.* from view_XG_GYGL_QSDSKH t1 where zgh = ? and xn||xq = ?||? ";
		return dao.getMapNotOut(sql, new String[]{model.getZgh(), model.getXn(), model.getXq()});
	}
	
	/**
	 * �������ҵ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateQsdskh(QsdskhForm model) throws Exception{
		String sql = "update XG_GYGL_QSDSKH set cj = ? where zgh = ? and xn||xq = ?||? ";
		return dao.runUpdate(sql, new String[]{model.getCj() , model.getZgh(), model.getXn(), model.getXq()});
	}
	
	/**
	 * ���ҵ�ʦ���˱��棨��飩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qsdskhAddCheck(QsdskhForm model) throws Exception{
		String sql = "select count(1) num from XG_GYGL_QSDSKH where zgh = ? and xn||xq = ?||? ";
		String num = dao.getOneRs(sql, new String[]{ model.getZgh(), model.getXn(), model.getXq()} ,"num");
		return !"0".equals(num);
	}
	
	/**
	 * ɾ����Ϣ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_QSDSKH where zgh = ? and xn||xq = ?||?";
		return dao.runBatch(sql, pks);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_QSDSKH");
		setClass(QsdskhForm.class);
	}

}
