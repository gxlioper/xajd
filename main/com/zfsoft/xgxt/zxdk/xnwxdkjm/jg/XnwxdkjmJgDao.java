/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:53:31 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:53:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmJgDao extends SuperDAOImpl<XnwxdkjmJgModel> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmJgModel t)
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
	public List<HashMap<String, String>> getPageList(XnwxdkjmJgModel t, User user)
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
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjkjm_jgb t");
		sql.append(" left join view_xsbfxx t1");
		sql
				.append(" on t.xh = t1.xh "
						+ " left join xqdzb t2 on t.xq = t2.xqdm "
						+ ") t where 1= 1  ");
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
		super.setClass(XnwxdkjmJgModel.class);
		super.setKey("jgid");
		super.setTableName("XG_ZDGXH_WXJKJM_JGB");
	}

	
	// ��ȡ������
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_wxjkjm_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

//	// ��ȡ������
//	public String getJesx() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
//		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
//	}

	// ��ȡspid
	public String getSqbh(XnwxdkjmsqModel model) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select sqid from xg_zdgxh_wxjkjm_sqb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[] { model.getXh()}, "sqid");
	}
	
	//��������������ԭ��������е�����ʱ��ɾ��ԭ�������ݣ��ڽ��в���
	public boolean delDkjg(String xh)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjkjm_jgb where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}
	
	public boolean delDkjgByID(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjkjm_jgb where jgid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
}
