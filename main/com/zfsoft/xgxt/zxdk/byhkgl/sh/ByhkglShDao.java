/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-10 ����03:54:19 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-10 ����03:54:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglShDao extends SuperDAOImpl<ByhkglShForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglShForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, t2.xm, t2.xb, t2.xymc, t2.zymc, t2.bjmc, t2.xydm, t2.zydm, t2.bjdm, t2.sfzh, t2.SJHM, t2.ZZMMMC, t2.jtdzxx, t2.MZMC, t2.nj, t2.XZ, ");
        sql.append(" t3.zqyymc, t6.guid shid, t6.shzt shztx, t6.gwid, t6.shr, t6.shyj, ");
        sql.append(" t9.mc || '[' || decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����') || ']' shztmc, ");
        sql.append(" t9.gwz, row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
        sql.append(" from XG_ZXDK_HZSF_BYHKSQB t1 ");
        sql.append(" left join view_xsbfxx t2 ");
        sql.append(" on t1.xh = t2.xh ");
        sql.append(" left join XG_ZXDK_HZSF_ZQYYDMB t3 ");
        sql.append(" on t1.zqyy = t3.zqyydm ");
        sql.append(" left join xg_xtwh_shztb t6 ");
        sql.append(" on t1.sqid = t6.ywid ");
        sql.append(" left join xg_xtwh_spgwyh t7 ");
        sql.append(" on t6.gwid = t7.spgw ");
        sql.append(" left join xg_xtwh_spgw t9 ");
        sql.append(" on t6.gwid = t9.id ");
        sql.append(" where t7.spyh ='" + user.getUserName() + "' ");
        String shlx = t.getShzt();		
        if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt <> 0 and t6.shzt <> 4) ");
		} else {
			sql.append(" and (t6.shzt = 0  or t6.shzt = 4 ) ");
		}
        sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(ByhkglShForm.class);
		super.setKey("sqid");
		super.setTableName("XG_ZXDK_HZSF_BYHKSQB");		
	}
	
	/**
	 * 
	 * @����: ����״̬
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����01:58:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update XG_ZXDK_HZSF_BYHKSQB set shzt=?  where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}
	

}
