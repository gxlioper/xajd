/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-4 ����05:47:48 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rwdj.RwdjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-4 ����05:47:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjshDao extends SuperDAOImpl<RwdjshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select *");
		sql.append(" from (select (select mc");
		sql.append(" from dmk_rwtjb rwtjb");
		sql.append(" where rwtjb.dm = a.rwtj) rwtjmc,");
		sql.append(" x.xymc,");
		sql.append(" x.xydm,");
		sql.append(" x.bjmc,");
		sql.append(" x.bjdm,");
		sql.append(" x.zymc,");
		sql.append(" x.zydm,");
		sql.append(" x.xm,");
		sql.append(" x.xb,");
		sql.append(" x.nj,");
		sql.append(" a.*,");
        sql.append(" t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append(" decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz,");
		sql.append(" row_number() over(partition by a.rwdjid order by t4.shsj desc) rn");
		sql.append(" from xg_zbxx_sqb a");
		sql.append(" left join view_xsxxb x");
		sql.append(" on a.xh = x.xh");
		sql.append(" left join xg_xtwh_shztb t4 ");
		sql.append(" on a.rwdjid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" where t5.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append("  ) a");
		sql.append(" where 1 = 1");
		 sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		//sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(RwdjshForm.class);
		this.setTableName("xg_zbxx_sqb");
		this.setKey("rwdjid");
	}
	
	@Override
	public RwdjshForm getModel(RwdjshForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.mc rwtjmc from XG_ZBXX_sqb a ");
		sql.append(" left join dmk_rwtjb b on a.rwtj=b.dm ");
		sql.append(" where a.rwdjid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getRwdjid() });
	}

}
