/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:41:25 
 */
package com.zfsoft.xgxt.zxdk.xnwxdk.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:41:25
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XnwxdkJgDao extends SuperDAOImpl<XnwxdkJgModel> {
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkJgModel t)
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
	public List<HashMap<String, String>> getPageList(XnwxdkJgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx,");
//		sql.append(" decode(t.shzt,");
//		sql.append(" '0',");
//		sql.append(" 'δ�ύ',");
//		sql.append(" '1',");
//		sql.append(" 'ͨ��',");
//		sql.append(" '2',");
//		sql.append(" '��ͨ��',");
//		sql.append(" '3',");
//		sql.append(" '���˻�',");
//		sql.append(" '5',");
//		sql.append(" '�����',");
//		sql.append(" t.shzt) shztmc,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjk_jgb t");
		sql.append(" left join view_xsbfxx t1");
		sql
				.append(" on t.xh = t1.xh "
						+ " left join xqdzb t2 on t.xq = t2.xqdm  order by t1.xydm asc"
						+ ") t where 1= 1   ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XnwxdkJgModel.class);
		super.setKey("jgid");
		super.setTableName("xg_zdgxh_wxjk_jgb");
	}


	/*
	 * ����������ʱ���ȡѧ��������Ϣ�Լ�ס��������Ϣ
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql
				.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,");
		sql
				.append(" substr(t2.sqsjstart, 0, 4) || '��' || substr(t2.sqsjstart, 5, 2) || '��' ||substr(t2.sqsjstart, 7, 2) || '��' ||'��'||substr(t2.sqsjend, 0, 4) || '��' || substr(t2.sqsjend, 5, 2) || '��' ||substr(t2.sqsjend, 7, 2) || '��' as sqsj ");
		sql
				.append("from xg_gygl_xyzsgl t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm)");
		sql.append(" t where sqbh = ?  ");
		return dao.getMapNotOut(sql.toString(),
				new String[] { model.getSqbh() });
	}



	// ��ȡ�������
	public String getJesx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
	}
	
	//��������������ԭ��������е�����ʱ��ɾ��ԭ�������ݣ��ڽ��в���
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjk_jgb where xh = ? and xn = ? and xq = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh,xn,xq});
	}
	
	public boolean delDkjgByID(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjk_jgb where jgid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}

}
