/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:44:02 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:44:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdsqDao extends SuperDAOImpl<LstdsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdsqForm t, User user)
			throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh, ");
		sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm  ) a, XG_RCSW_LSTD_JCSZ b where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(LstdsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_rcsw_lstd_sqb");

	}
	
	/**
	 * 
	 * @����:������Ϣ�鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:19:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneSqList(String sqId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.sqsj,t1.lxdm,t1.sqly,t1.shzt,t1.splc, ");
		sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm where t1.sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{sqId});
	}
	
	/**
	 * 
	 * @����:��ȡ������ID
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:28:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  splc from xg_rcsw_lstd_jcsz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getLstdsq(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from XG_RCSW_LSTD_SQB a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.sqid= ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}
	
	
	/**
	 * 
	 * @����:����ά������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:31:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxwhList() {
		String sql = "select lxdm,lxmc from XG_RCSW_LSTD_LXWHB order by lxdm ";
		return dao.getList(sql, new String[] {}, new String[] {"lxdm", "lxmc" });
	}
	
	
	public boolean isCanDel(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from XG_RCSW_LSTD_SQB where sqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{sqid});
		String sqzt=map.get("shzt");
		//���δ�ύ�ſ����ύ
		return null==sqzt||sqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:��������״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:34:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSq(LstdsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_RCSW_LSTD_SQB ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:�����鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:35:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getLstdsqInfo(LstdsqForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.xn,(select xqmc from xqdzb b where b.xqdm=t1.xq) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm   where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	
	/**
	 * 
	 * @����:����˵ļ�¼����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:39:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from XG_RCSW_LSTD_SQB where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @����:����ѧ���жϸ�ѧ����ѧ����ɫͨ�������Ƿ��Ѿ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����11:40:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(LstdsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from XG_RCSW_LSTD_SQB where xh=? and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}
	
	
}
