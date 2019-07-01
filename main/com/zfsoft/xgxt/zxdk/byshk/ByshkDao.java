/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����01:44:43 
 */  
package com.zfsoft.xgxt.zxdk.byshk;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������ѯ
 * @�๦������: ��ҵ�������ѯ 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-5 ����01:44:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByshkDao extends SuperDAOImpl<ByshkForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByshkForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByshkForm t, User user)
			throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * ");
		sql.append(" from (select * ");
		sql.append(" from (select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '��ƽ��������' dkxm, t1.sqje, a.nj, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zdgxh_ypzl_jgb t1 ");
		sql.append(" on a.xh = t1.xh ");
		sql.append(" where exists (select 1 from xg_zdgxh_ypzl_jgb b where a.xh = b.xh) ");
		sql.append(" and not exists (select 1 from XG_ZDGXH_YPZD_JGB b where a.xh = b.xh) ");
		//У԰�ش����ҵδ�����ѧ��
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, 'У԰�ش���' dkxm, t1.dkje sqje, a.nj, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a  ");
		sql.append(" left join xg_zxdk_xydkjgb t1 on a.xh = t1.xh ");
		sql.append(" where exists ");
		sql.append(" (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) ");
		sql.append(" and not exists ");
		sql.append(" (select 1 from XG_ZDGXH_XYDDK_JGB b where a.xh = b.xh) ");
		//��Դ�ش����ҵδ�����ѧ��
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '��Դ�ش���' dkxm, t1.dkje sqje, a.nj, t1.lrsj sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zxdk_syddk t1 on a.xh = t1.xh ");
		sql.append(" where exists ");
		sql.append(" (select 1 from xg_zxdk_syddk b where a.xh = b.xh) ");
		sql.append(" and not exists ");
		sql.append(" (select 1 from XG_ZDGXH_SYDDK_JGB b where a.xh = b.xh) ");
		//У����Ϣ����ҵδ�����ѧ��
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, 'У����Ϣ���' dkxm, t1.sqje, a.NJ, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zdgxh_wxjk_jgb t1 ");
		sql.append(" on a.XH = t1.xh ");
		sql.append(" where exists (select 1 from xg_zdgxh_wxjk_jgb b where a.xh = b.xh) ");
		sql.append(" and not exists (select 1 from XG_ZDGXH_WXJKHK_JGB b where a.xh = b.xh)) t where ");
		sql.append(" substr('");		
		sql.append(Base.currXn);
		sql.append("',6,4) - nvl(t.nj, 0) >= nvl(t.xz, 0) ");
		sql.append(" order by t.xh) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

}
