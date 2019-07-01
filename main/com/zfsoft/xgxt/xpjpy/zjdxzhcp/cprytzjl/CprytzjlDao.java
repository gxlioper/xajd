/**
 * @����:ѧ����Ʒ��1����
 * @���ڣ�2017-7-7 ����09:49:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.cprytzjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ������Ա������¼ 
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-7 ����09:50:08 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CprytzjlDao extends SuperDAOImpl<CprytzjlForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CprytzjlForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CprytzjlForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from (select a.xh,b.xm, ");
		sql.append(" (case b.xb when '1' then '��' when '2' then 'Ů' else b.xb end) xb, ");
		sql.append(" c.nj,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc,");
		sql.append(" (select xm from yhb b where a.tzr=b.yhm)tzrxm,a.tzr,a.tzsj,a.tzbz ");
		sql.append(" from xg_pjpy_new_cpmdtzjlb a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" left join view_njxyzybj_all c on b.bjdm=c.bjdm ");
		sql.append(" where a.xn=(select xn from xg_zjdx_pjpy_csszb where rownum=1) ");
		sql.append(" ) t1 where tzr ='" + user.getUserName()+ "' or (1=1");
		sql.append(searchTjByUser);
		sql.append(" )) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}

}
