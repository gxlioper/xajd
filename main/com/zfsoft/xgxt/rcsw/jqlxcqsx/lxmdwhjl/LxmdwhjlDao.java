/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��3��27�� ����1:50:43 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh.LxmdwhForm;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��3��27�� ����1:50:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhjlDao extends SuperDAOImpl<LxmdwhjlForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_cqsx_jqlx_mdwhczjlb");
		super.setKey("jlid");
		super.setClass(LxmdwhjlForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhjlForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhjlForm t, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (");
		sql.append("SELECT t1.jlid,t1.czsj,t1.czlx,t1.xh,t1.xmid,t1.XGQLXQKSM,t1.XGHLXQKSM,");
		sql.append("y.xm czr,decode(t1.czlx,'1','����','2','�޸�','3','ɾ��') czlxmc,t2.XM,t2.bjdm,t2.xydm,t2.zydm,t2.nj,t2.bjmc,t2.zymc,t2.xymc,t3.XMMC,");
		sql.append("CASE t1.CZLX WHEN '2' THEN '���޸ġ���У���˵����' || t1.xghlxqksm END czxq ");
		sql.append("FROM xg_cqsx_jqlx_mdwhczjlb t1 ");
		sql.append("LEFT JOIN view_xsbfxx t2 ON t1.XH = t2.XH ");
		sql.append("LEFT JOIN yhb y ON y.yhm = t1.czr ");
		sql.append("LEFT JOIN XG_CQSX_JQLX_XMSZ t3 ON t1.XMID = t3.XMID) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @����:����������У����ά����¼��������ͬ���������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����12:00:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveLxmdwhjlList(LxmdwhForm t,String[] xhs,String czr) throws SQLException {
		String sql = "INSERT INTO xg_cqsx_jqlx_mdwhczjlb(czr,czlx,xh,xmid,xghlxqksm) "
				   + "VALUES (?,'1',?,?,?)";
		boolean result = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (String xh:xhs) {
			paraList.add(new String[]{czr,xh,t.getXmid(),t.getLxqksm()});
		}
		int[] rs = dao.runBatch(sql, paraList);
		for (int r:rs) {
			if (r == Statement.EXECUTE_FAILED){
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * @throws SQLException  
	 * @����:����������У����ά����¼��������ͬ���������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����4:11:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxmdList
	 * @param czr
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveLxmdwhjlList(List<HashMap<String, String>> lxmdList, String czr) throws SQLException {
		String sql = "INSERT INTO xg_cqsx_jqlx_mdwhczjlb(czr,czlx,xh,xmid,xgqlxqksm) "
				   + "VALUES (?,'3',?,?,?)";
		boolean result = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (HashMap<String,String> map:lxmdList) {
			paraList.add(new String[]{czr,map.get("xh"),map.get("xmid"),map.get("lxqksm")});
		}
		int[] rs = dao.runBatch(sql, paraList);
		for (int r:rs) {
			if (r == Statement.EXECUTE_FAILED){
				result = false;
				break;
			}
		}
		return result;
	}

	/** 
	 * @����:����id��ѯ��У����ά����¼��Ϣ��������У������Ϣ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����5:12:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jlid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getLxmdwhjlById(String jlid) {
		StringBuilder sql = new StringBuilder("SELECT t1.*,decode(t1.czlx,'1','����','2','�޸�','3','ɾ��') czlxmc,t2.XM,t3.XMMC,");
		sql.append("t3.LXXMSM,t3.lxkssj || ' �� ' || t3.lxjssj qzsj,y.xm czrmc,");
		sql.append("CASE t1.CZLX WHEN '2' THEN '���޸ġ���У���˵����' || t1.xghlxqksm END czxq ");
		sql.append("FROM xg_cqsx_jqlx_mdwhczjlb t1 ");
		sql.append("LEFT JOIN XSXXB t2 ON t1.XH = t2.XH ");
		sql.append("LEFT JOIN yhb y ON y.yhm = t1.czr ");
		sql.append("LEFT JOIN XG_CQSX_JQLX_XMSZ t3 ON t1.XMID = t3.XMID ");
		sql.append("WHERE t1.jlid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{jlid});
	}


}
