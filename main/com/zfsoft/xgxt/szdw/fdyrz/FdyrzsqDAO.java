/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����05:06:22 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

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
 * @�๦������: TODO ����Ա��ְ���� ��ְ����
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-6-4 ����04:56:01 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyrzsqDAO extends SuperDAOImpl<FdyrzsqForm>{

	/*
	 * ����: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: 
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select a.*,b.splc splcnew from (");
		sql.append(" select sqid, a.zgh, sqsj, sqly, decode(shzt,0,'δ�ύ',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����')shzt,shzt shztdm, sqdbgs, splc,b.zjz  from xg_szdw_fdyrzsqb a left join fdyxxb b on a.zgh = b.zgh where a.shzt <>9 and a.zgh='"+user.getUserName()+"'");
		
		sql.append(" order by a.sqsj desc ) a, xg_xtwh_shlcsqkzb b where 1=1 and b.key='szdw_fdyrzsq' ");//
		
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * ����: 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_szdw_fdyrzsqb");
		super.setKey("sqid");
	}

	/**
	 * @����:��ȡ����Ա������Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-5 ����10:30:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getFdyxx(String zgh){
		StringBuffer sb = new StringBuffer();
		sb.append(" select  b.*,a.mzmc,a.xb xbs,a.zzmmmc,a.bmmc from fdyxxb b join  view_fdyxx a on b.zgh = a.zgh  where b.zgh = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{zgh});
	}
	/**
	 * @����:�޸ĸ���Ա��Ϣ�� ר��ְ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-6 ����11:46:37
	 * @param zgh
	 * @param zjz
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int  updateFdyxxZjz(String zgh,String zjz) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("update fdyxxb b set b.zjz = ? where b.zgh = ?");
		
		return dao.update(sb.toString(), new String[]{zjz,zgh});
	}
	/**
	 * @����:�����������༶
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-6 ����02:07:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqbh
	 * @param bjList
	 * @param user
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 
	public int[]  insertFdysqbjb(String sqbh,List<String> bjList,User user) throws Exception{
		String sql = " insert into xg_szdw_fdyrzsqbjb values(?,? )";
		//String[] sqlArr = new String[bjList.size()];
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjList.size(); i++) {
			params.add(new String[]{sqbh,bjList.get(i)});
		//	sqlArr[i]="insert into xg_szdw_fdyrzsqbjb values('"+sqbh+"','"+bjList.get(i)+"')";
		}
		return dao.runBatch(sql, params);
	}*/
	
	/**
	 * @����:ȡ����ְ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-8 ����04:48:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] �������� 
	 * @throws
	 */
	public int[] updateFdyrzsq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @����:��ѯ��ǰ�û����������ְ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-31 ����10:18:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getFdyrzsqCount(String zgh) throws SQLException{
		String sql = " select count(1) s from xg_szdw_fdyrzsqb  b where b.zgh = '"+zgh+"' and b.shzt in(0,3,5)";
		return dao.getOneRsint(sql);
	}
	
	public int updateFdyrzsq(FdyrzsqForm model) throws Exception{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt=? ,splc = ? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
}
