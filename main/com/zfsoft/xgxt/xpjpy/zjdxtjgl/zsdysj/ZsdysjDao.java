/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-1-31 ����04:12:21 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ֤���ӡ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-31 ����04:12:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZsdysjDao extends SuperDAOImpl<ZsdysjForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsdysjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsdysjForm t, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.id,a.xh,b.xm,b.xb,b.xz,b.mz,b.mzmc,b.zzmm,b.zzmmmc,b.xmpy, ");
		sql.append("nvl(a.cpnj,b.nj) nj, ");
		sql.append("nvl(a.cpxydm,b.xydm) xydm, ");
		sql.append("nvl(a.cpxymc,b.xymc) xymc, ");
		sql.append("nvl(a.cpzydm,b.zydm) zydm, ");
		sql.append("nvl(a.cpzymc,b.zymc) zymc, ");
		sql.append("nvl(a.cpbjdm,b.bjdm) bjdm, ");
		sql.append("nvl(a.cpbjmc,b.bjmc) bjmc, ");
		sql.append("a.xn,c.xmdm,a.xmmc,a.xmje,a.sqsj,c.ywmc xmywmc,");
		sql.append("(select lxmc from xg_zjdx_pjpy_xmlx d where a.lxdm = d.lxdm) xmlxmc,a.lxdm, ");
		sql.append("(select xzmc from xg_zjdx_pjpy_xmxz f where a.xzdm = f.xzdm) xmxzmc,a.xzdm ");
		sql.append("from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join view_xsxxb b on a.xh = b.xh ");
		sql.append("left join xg_zjdx_pjpy_pjxmb c on a.xmmc = c.xmmc and a.xn = c.xn ");
		//sql.append(") t where 1 = 1 and xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ");
		sql.append(") t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t,sql.toString(),inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
	}

}
