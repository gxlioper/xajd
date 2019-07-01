/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-27 ����03:48:16 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-27 ����03:48:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxshDao extends SuperDAOImpl<XnjxshForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnjxshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(XnjxshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		//String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw", "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t1.*,");
		sql.append(" t2.xm,");
		sql.append(" t2.xb,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t4.guid shid,");
		sql.append(" t4.gwid,");
		sql.append(" t4.shr,");
		sql.append(" t4.shyj,");
		sql.append(" t7.xqmc,");
		sql.append(" t8.jcxf,t8.sbbmdm,");
		sql.append(" t8.xmkssj,");
		sql.append(" t8.xmmc xmmc1,");
		sql.append(" t9.xmjbdm,");
		sql.append(" t9.xmjbmc,");
		sql.append(" t10.sskmdm,");
		sql.append(" t10.sskmmc,");
		sql.append(" t11.bmmc sbbmmc,");
		sql.append(" t6.mc || '[' ||");
		sql.append(" decode(t4.shzt,");
		sql.append("  '0',");
		sql.append(" 'δ���',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '�˻�',");
		sql.append(" '4',");
		sql.append(" '������',");
		sql.append(" '5',");
		sql.append(" '�����') || ']' shztmc,");
		sql.append(" t6.gwz,");
		sql.append(" row_number() over(partition by t1.id order by t4.shsj desc) rn,");
		sql.append(" t12.jxmc,to_char(to_number(t12.fjxf)+to_number(t8.jcxf)) zf");
		sql.append(" from XG_SZTZ_JXSQ t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xqdzb t7");
		sql.append(" on t1.xq = t7.xqdm");
		sql.append(" left join xg_sztz_xmjg t8");
		sql.append(" on t1.xmdm = t8.xmdm");
		sql.append(" left join xg_sztz_xmjb t9");
		sql.append(" on t8.xmjbdm = t9.xmjbdm");
		sql.append(" left join xg_sztz_sskm t10");
		sql.append(" on t8.sskmdm = t10.sskmdm");
		sql.append(" left join zxbz_xxbmdm t11");
		sql.append(" on t8.sbbmdm = t11.bmdm");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t1.id = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" left join xg_sztz_xm_jx t12");
		sql.append(" on t12.jgid = t1.jxid");
		sql.append(" where t5.spyh ='" + user.getUserName() + "' ");
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
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XnjxshForm.class);
		super.setKey("id");
		super.setTableName("XG_SZTZ_JXSQ");
		
	}
	
	public String getJxmc(String jxid){
		String sql = "select jxmc from xg_sztz_xm_jx where jgid = ?";
		return dao.getOneRs(sql, new String[]{jxid}, "jxmc");
	}
	
	public String getJxfs(String jxid){
		String sql = "select fjxf from xg_sztz_xm_jx where jgid = ?";
		return dao.getOneRs(sql, new String[]{jxid}, "fjxf");
	}
	
}
