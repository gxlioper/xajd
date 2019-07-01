/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-5 ����11:22:18 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������
 * @�๦������:  �㽭��ѧ����������-ͳ�Ʋ�ѯ-������Ŀ����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-5 ����10:26:12 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzDao extends SuperDAOImpl<PjxmhzForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t)
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
	
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ( select t.xn,t.lxdm,t.xzdm, ");
		sql.append(" (select xzmc from xg_zjdx_pjpy_xmxz where xzdm = t.xzdm) xmxzmc, ");
		sql.append(" (select lxmc from xg_zjdx_pjpy_xmlx where lxdm = t.lxdm) xmlxmc, ");
		sql.append(" t.xmmc, ");
		sql.append(" count(*) hjrs ");
		sql.append(" from (select * ");
		sql.append(" from (select t1.xn, t1.xmdm, t1.xmmc, t1.lxdm, t1.xzdm, t2.xydm ");
		sql.append(" from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ) t ");
		sql.append(" group by t.xmmc, t.xzdm, t.lxdm, t.xn ");
		sql.append(" order by xn desc ) t3");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @����: ����������ĳ����ӣ���ѯ����Ŀ���е�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-9 ����10:23:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm t, User user,Boolean fyFlag)
		throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = " and xn = ? and lxdm = ? and xzdm = ? and xmmc = ? ";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = {t.getXn(),t.getLxdm(),t.getXzdm(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.xn,t1.xh,t5.xm,t5.xb,t1.xmmc,t1.lxdm,t1.xzdm,t1.xmje,t6.lxmc xmlx,t7.xzmc xmxz, ");
		sql.append("nvl(t3.nj, t5.nj) nj, ");
		sql.append("nvl(t3.xydm, t5.xydm) xydm, ");
		sql.append("nvl(t3.xymc, t5.xymc) xymc, ");
		sql.append("nvl(t3.zydm, t5.zydm) zydm, ");
		sql.append("nvl(t3.zymc, t5.zymc) zymc, ");
		sql.append("nvl(t3.bjdm, t5.bjdm) bjdm, ");
		sql.append("nvl(t3.bjmc, t5.bjmc) bjmc  ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm ");
		sql.append("left join view_xsbfxx t5 on t1.xh = t5.xh ");
		sql.append("left join xg_zjdx_pjpy_xmlx t6 on t4.lxdm = t6.lxdm ");
		sql.append("left join xg_zjdx_pjpy_xmxz t7 on t1.xzdm = t7.xzdm ");
		sql.append(") a where 1 = 1  ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
}
