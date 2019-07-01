/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����10:03:20 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ȩ����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����10:03:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XssqDao extends SuperDAOImpl<XssqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t)
			throws Exception {
		
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String lx = t.getLx();
		String sblx = "";
		if("0".equals(lx)){
			sblx = "0";
		}else if("1".equals(lx)){
			sblx = "2";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (");
		sql.append("select t1.* , decode(t1.sbcount , '0' , '��' , '��') ywsbmc from (select a.xh||'@@'||a.lx as pk, a.lx , a.rzksrq , a.rzjsrq , a.sfxypssb , " +
				"decode(a.sfxypssb , '1' , '��' ,  '0' , '��') sfxypssbmc, " + 
				"b.* , (select count(1) from XG_XLJK_XLWYGL_XLSBJGB aa where aa.xh = a.xh and aa.sblx = '" + sblx + "') sbcount from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh where 1=1 ");
		
		if("0".equals(lx)){
			sql.append(" and a.lx = '0' ");
		}else if("1".equals(lx)){
			sql.append(" and a.lx = '1' ");
		}
		sql.append(" ) t1  ");
		
		sql.append(" ) t2 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh , String lx){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.xh||'@@'||a.lx as pk, a.lx , a.rzksrq , a.rzjsrq , a.sfxypssb , " +
				"decode(a.sfxypssb , '1' , '��' ,  '0' , '��') sfxypssbmc, " + 
				"b.* from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh");
		sql.append(" ) t1 where t1.xh = ? and t1.lx = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh , lx});
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����11:25:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateModel(XssqForm  model) throws Exception{
		String sql = "update XG_XLJK_XLWYGL_XSSQXXB set rzksrq = ? , rzjsrq = ? , sfxypssb = ? where xh = ? and lx = ? ";
		return dao.runUpdate(sql, new String[]{
				model.getRzksrq(), 
				model.getRzjsrq(), 
				model.getSfxypssb(),
				model.getXh(), 
				model.getLx()});
	}
	
	/**
	 * 1.�Ƿ�Ϊ�༶����ίԱ
	 * 2.¥��/�㳤
	 * 3.��Ȩѧ��
	 * @����:��ȡѧ����Ȩ���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-27 ����08:33:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> xssqCheck(String xh){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.*,t3.*,(select a.sfxypssb from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '1') sfxypssb from (select decode(count(1) , '1' , 'Y' , 'N') bjxlwy from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '0') t1")
		.append("                 ,(select decode(count(1),'1' , 'Y' , 'N') tsxs from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '1') t2")
		.append("                  ,(select decode(count(1),'1','Y','N') gygly from XG_GYGL_NEW_GYGLRYB a where a.xh = ? and a.rzzt = '����') t3");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh , xh,xh,xh});
		
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int[] del(List<String[]> pks) throws SQLException{
		String sql = "delete from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = ? ";
		return dao.runBatch(sql, pks);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:������ݿ��Ƿ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public int checkExist(String xh , String lx) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = ? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh , lx}, "rs"));
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XssqForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWYGL_XSSQXXB");
	}

}
