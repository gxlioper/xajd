/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-18 ����03:45:54 
 */  
package com.zfsoft.xgxt.xszz.xfbzmd;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-��������-ѧ�Ѳ���������ѯ
 * @�๦������: ��ʦ����ֱ����ϵͳ��鿴�����з���ѧ�Ѳ���������ͬѧ��������Ϣ������ѧ�š�������ѧԺ������Ļ�����ѧ԰����
 * 			       ͬʱ���ܿ���ÿ��ͬѧ����ѧ�Ѳ�����ԭ��ͬʱ֧�ֵ�����
 * 			       ������EXCLE�ֶ��� ��š�ѧ�š�ѧԺ���ơ���ע��ÿ��ѧ����������ѧ�Ѳ����������� 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-7-18 ����03:45:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XfbzmdDao  extends SuperDAOImpl<XfbzmdForm>{
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XfbzmdForm t)
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
	}
	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * ��ѯ
	 * ���ߣ�MengWei
	 * ʱ�䣺2016-07-18
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XfbzmdForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,xz, ");
		sql.append(" case when a.nj = e.dqnd then d.xymc else ''end zxzyqrqxy, ");
		sql.append(" b.sfdq || b.sfgc || b.lszn || b.ylzd1 || case when c.xh is not null then '����' else '' end || decode(jg, '520328', '������̶', '') || case when mzmc not like '����' then mzmc else '' end bz ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join (select xh,sfdq,decode(sfgc, '1', '�м�', '') sfgc,decode(lszn, '1', '��ʿ��Ů', '') lszn,decode(ylzd1, '1', '�Ÿ�������Ů', '') ylzd1 ");
		sql.append(" from xg_xszz_new_jtqkdcb where sfdq in ('�¶�') or sfgc = '1' or lszn = '1' or ylzd1 = '1') b on a.xh = b.xh ");
		sql.append(" left join (select distinct xh from view_knsjgb_fqxrd  where rddc = '2' and xh in (select xh from view_xsbfxx where mzmc not like '����' and sfzx = '��У')) c on a.xh = c.xh ");
		sql.append(" left join view_njxyzybj_all d on a.zxzyqrqbjdm = d.bjdm ");
		sql.append(" left join (select substr(dqxn, 6, 4) dqnd from xtszb) e on 1=1 ");
		sql.append(" where (a.jg = '520328' or b.xh is not null or c.xh is not null) and (to_number(a.nj) + to_number(a.xz)) > e.dqnd ");
		sql.append(" and a.xh in (select xh from view_knsjgb_fqxrd ) ");
		sql.append(" order by a.xymc, a.nj, a.xh ");
		sql.append(" ) a where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
}
