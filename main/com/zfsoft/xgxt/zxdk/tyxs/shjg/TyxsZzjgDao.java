/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-17 ����04:08:34 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-4-17 ����04:08:34
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzjgDao extends SuperDAOImpl<TyxsZzjg> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(TyxsZzjg.class);
		setKey("id");
		setTableName("xg_tyxsrx_xfzzjgb");

	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TyxsZzjg t)
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
	public List<HashMap<String, String>> getPageList(TyxsZzjg t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql
				.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.zybj,t2.zybjmc,x1.sydm,x1.symc,t2.nj,t2.xb,t2.sfzh,t2.lxdh, t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t2.mz, t3.csrq, t3.xxszd,t3.jtdz, t3.jtyb,t4.*");
		
		sql
				.append(" from xg_tyxsrx_xfzzjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql
		.append(" left join  xsxxb t3 on t1.xh =t3.xh  left join zzmmdmb t4 on t3.zzmm = t4.zzmmdm ");

		sql.append(") t where 1=1 ");
		
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		logger.info("sql=" + sql.toString());

		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:����ѧ�ź�ѧ���ж�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-23 ����05:03:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzjg t) {
		StringBuilder sql = new StringBuilder(
				"select count(1) c from xg_tyxsrx_xfzzjgb where xh = ? and xn = ?");
		String[] params = null;

		if (!StringUtil.isNull(t.getId())) {
			sql.append(" and id <> ?");
			params = new String[] { t.getXh(), t.getXn(), t.getId() };
		} else {
			params = new String[] { t.getXh(), t.getXn() };
		}

		return dao.getOneRs(sql.toString(), params, "c");
	}

	/**
	 * 
	 * @����:���ݣ��鿴�������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-23 ����05:04:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZzjgById(String id) {
		String sql = "select * from xg_tyxsrx_xfzzjgb where id = ? ";
		return dao.getMapNotOut(sql, new String[] { id });
	}
	
	
	public HashMap<String, String> getDjbById(String id) {
		String sql = "select * from xg_tyxsrx_xfzzjgb t1 left join xg_tyxsrx_xfzzsqb t2 on t1.xh = t2.xh and t1.xn = t2.xn where t1.id = ? ";
		return dao.getMapNotOut(sql, new String[] { id });
	}
	

}
