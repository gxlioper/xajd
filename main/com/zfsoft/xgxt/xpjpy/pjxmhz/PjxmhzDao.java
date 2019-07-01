/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-22 ����05:09:42 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-ͳ�ƹ���-������Ŀ����
 * @�๦������: ͳ��ÿѧ�ꡢÿѧ�ڡ�ÿ����Ŀ�Ļ�����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-2-22 ����05:09:42 
 * @�汾��Ver 5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzDao  extends SuperDAOImpl<PjxmhzForm>{
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_pjjgb");
		super.setKey("id");
		super.setClass(PjxmhzForm.class);
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
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz where xmxzdm = t.xzdm) xmxzmc, ");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx where xmlxdm = t.lxdm) xmlxmc, ");
		sql.append(" t.xmmc, ");
		sql.append(" count(*) hjrs ");
		sql.append(" from (select * ");
		sql.append(" from (select t1.xn, t1.xmdm, t1.xmmc, t1.lxdm, t1.xzdm, t2.xydm ");
		sql.append(" from xg_pjpy_new_pjjgb t1 ");
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
	 * @���ڣ�2017-2-24 ����09:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm t, User user,Boolean fyFlag)
		throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = " and xn = ? and lxdm = ? and xzdm = ? and xmmc = ? ";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = {t.getXn(),t.getLxdm(),t.getXzdm(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t2.xh,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t1.id,t1.xn,t1.xmmc,t1.lxdm,t1.xzdm,t1.xmje,t3.xmlxmc xmlx,t4.xmxzmc xmxz ");
		sql.append(" from xg_pjpy_new_pjjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_pjpy_new_xmlx t3 on t3.xmlxdm = t1.lxdm ");
		sql.append(" left join xg_pjpy_new_xmxz t4 on t4.xmxzdm = t1.xzdm");
		sql.append("  ) a where 1 = 1  ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
}
