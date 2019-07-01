/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:42:24 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsh.SthdshForm;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�������Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:42:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwShDao extends SuperDAOImpl<ZyfwShForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		super.setClass(ZyfwShForm.class);
		super.setKey("fwid");
		super.setTableName("xg_xsxx_zyfwgl_sqb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwShForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwShForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		
		sql.append("select t1.*,t9.xqmc,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,");
		sql.append("t2.bjdm,t2.bjmc,t2.nj,t2.xb, t4.guid shid,t4.gwid,t4.shr,t4.shyj,");
		sql.append("t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,");
	    sql.append("t6.gwz,ssx.shengmc||ssx.shimc||ssx.xianmc || t1.fwdd fwddxxdz,");
		sql.append("row_number() over(partition by t1.fwid order by t4.shsj desc) rn ");
		sql.append("from xg_xsxx_zyfwgl_sqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xqdzb t9 on t1.xq=t9.xqdm ");
		sql.append("left join xg_xtwh_shztb t4 on t1.fwid = t4.ywid ");
		sql.append("left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw ");
		sql.append("left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		
		sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

}
