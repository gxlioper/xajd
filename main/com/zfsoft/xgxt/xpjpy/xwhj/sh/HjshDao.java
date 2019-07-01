/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-26 ����05:54:59 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-26 ����05:54:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjshDao extends SuperDAOImpl<HjshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, t2.xm, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, ");
		sql.append(" t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, (case t1.jsfs when '1' then '����' when '2' then '����' else t1.jsfs end) jsfsmc, ");
		sql.append(" t6.guid shid, t6.shzt shztx, t6.gwid, t6.shr, t6.shyj, ");
		sql.append(" t9.mc || '[' || decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����') || ']' shztmc, ");
		sql.append(" t9.gwz, row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
		sql.append(" from xg_hjxxgl_sqb t1 ");
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh ");
		sql.append(" left join xg_hjxxgl_jxlb t3 ");
		sql.append(" on t1.jxlbdm = t3.jxlbdm ");
		sql.append(" left join xg_hjxxgl_jxdj t4 ");
		sql.append(" on t1.jxdjdm = t4.jxdjdm ");
		sql.append(" left join xg_hjxxgl_jxmc t5 ");
		sql.append(" on t1.jxmcdm = t5.jxmcdm ");
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
        sql.append(" ) t where 1 = 1 ");
		sql.append(" and rn = 1 ");
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
		super.setClass(HjshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hjxxgl_sqb");
	}
	
	/**
	 * 
	 * @����: ״̬����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-27 ����03:14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_hjxxgl_sqb set shzt=?  where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}
	
}
