/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:49:04 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sh.XnwxdkShModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:49:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmshDao extends SuperDAOImpl<XnwxdkjmshModel> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmshModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmshModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm",
				"bjdm");
		// String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw",
		// "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t2.guid shid,");
		sql.append(" t2.gwid,");
		sql.append(" t2.shr,");
		sql.append(" t2.shyj,");
		sql.append(" t4.mc || '[' ||");
		sql.append(" decode(t2.shzt,");
		sql.append(" '0',");
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
		sql.append(" t4.gwz,");
		sql.append(" row_number() over(partition by t.sqid order by t2.shsj desc) rn");
		sql.append(" from xg_zdgxh_wxjkjm_sqb t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join xg_xtwh_shztb t2");
		sql.append(" on t.sqid = t2.ywid");
		sql.append(" left join xg_xtwh_spgwyh t3");
		sql.append(" on t2.gwid = t3.spgw");
		sql.append(" left join xg_xtwh_spgw t4");
		sql.append(" on t2.gwid = t4.id");
		sql.append("  where t3.spyh = '"+user.getUserName()+"' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t2.shzt<>0 and  t2.shzt<>4)");
		} else {
			sql.append(" and (t2.shzt=0  or t2.shzt = 4 )");
		}
		sql.append(" ");
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		// sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XnwxdkjmshModel.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_wxjkjm_sqb");

	}

	public boolean updateSqjl(String ywid, String shzt) throws Exception {
		String sql = "update xg_zdgxh_wxjkjm_sqb set shzt=?  where sqid = ?";

		return dao.runUpdate(sql, new String[] { shzt, ywid });

	}

}
