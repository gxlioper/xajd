/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:18:18 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ���� DAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-24 ����4:15:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmsqDAO extends SuperDAOImpl<FdypxXmsqForm> {

	/*
	      ����:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_fdypxsq");
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����:��ѯ��ѵ��Ŀ�����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select sqid, sqsj, sqly,  b.*, decode(shzt,0,'δ�ύ',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����')shzt, shzt shztdm, splc  ");
		sql.append("from xg_szdw_fdypxsq a left join xg_szdw_fdypxxm b on a.xmdm = b.xmdm" );
		sql.append(" where a.shzt <>9 and a.sqr='"+user.getUserName()+"' ");
		
		sql.append(" order by a.sqsj desc) where 1=1");
		sql.append(searchTj); 
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @����:��ѯ�Ѿ����������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����2:16:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCount(String zgh,String xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select  count(1)a from xg_szdw_fdypxsq  b where b.sqr = '"+zgh+"' and xmdm='"+xmdm+"' and b.shzt not in(9,2)");
		return dao.getOneRsint(sql.toString());
	}
	/**
	 * @����:ȡ������Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����3:13:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] ��������
	 */
	public int[] updateFdypxsq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_fdypxsq b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @����:������Ŀ��Ų�ѯ����Ŀ�Ƿ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-5 ����3:04:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCountByPxxm(String xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from xg_szdw_fdypxsq b where b.shzt<>9 and  b.xmdm in( ");
		sql.append(xmdm);
		sql.append(" )");
		return dao.getOneRsint(sql.toString());
	}
	
	/**
	 * 
	 * @����:TODO(����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����03:01:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int updateFdypxsq(FdypxXmsqForm model) throws Exception{
		String sql = " update xg_szdw_fdypxsq b set b.shzt=? , b.splc = ? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
	
	/**
	 * 
	 * @����: �ж���Ŀ�Ƿ��Ƿ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-29 ����06:22:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXmkg(String xmdm){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) num from xg_szdw_fdypxxm b where xmdm = ? and sysdate >= to_date(b.sqkssj, 'yyyy-mm-dd')");
		sb.append(" and sysdate <= to_date(b.sqjssj, 'yyyy-mm-dd') + 1 and b.sqkg = 1 ");
		
		return dao.getOneRs(sb.toString(), new String[]{xmdm}, "num");
	}
	
}
