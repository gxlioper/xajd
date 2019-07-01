/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-27 ����09:06:18 
 */
package com.zfsoft.xgxt.xtwh.bjdl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �༶��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-27 ����09:06:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BjdlDao extends SuperDAOImpl<BjdlModel> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(BjdlModel.class);
		super.setKey("bjdm");
		super.setTableName("xg_xtwh_bjlbb");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjdlModel t)
			throws Exception {
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjdlModel t, User user)
			throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (");
		sql.append(" select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.nj,");
		sql
				.append(" t1.bjmc,t1.bjdm,nvl(t3.lbdm,'0') lbdm,nvl(t3.lbmc,'δ����') lbmc from view_njxyzybj_bzr t1");
		sql.append(" left join xg_xtwh_bjlbb t2 on t1.bjdm=t2.bjdm");
		sql.append(" left join xg_xtwh_bjdlb t3 on t2.lbdm=t3.lbdm");
		sql.append(") where 1=1 ");
		sql.append(searchTj);

		return super.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * 
	 * @����: ���ð༶����
	 * @���ߣ�����ԡ����ţ�445��
	 * @���ڣ�2013-8-27 ����02:45:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @param bjdm
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean szBjdl(String lbdm, String[] bjdm) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append("update xg_xtwh_bjlbb set lbdm=? where ");

		for (int i = 0, j = bjdm.length; i < j; i++) {
			sql.append("bjdm=?");

			if (i != j - 1) {
				sql.append(" or ");
			}
		}

		return dao.runUpdate(sql.toString(), StringUtils.joinStrArr(
				new String[] { lbdm }, bjdm));
	}

	/**
	 * 
	 * @����: ��ʼ���༶
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����05:48:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean initBjdl() throws Exception {
		StringBuilder sql = new StringBuilder();

		sql
				.append(" insert into xg_xtwh_bjlbb(bjdm) select bjdm from view_njxyzybj t1 ");
		sql
				.append(" where not exists (select 1 from xg_xtwh_bjlbb t2 where t1.bjdm=t2.bjdm)");

		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: ����༶����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����02:56:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @param lbmc
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean insertBjdl(String lbdm, String lbmc) throws Exception {

		String sql = "insert into xg_xtwh_bjdlb(lbdm,lbmc) values (?,?)";

		return dao.runUpdate(sql, new String[] { lbdm, lbmc });
	}

	/**
	 * 
	 * @����: ��ѯ�༶�����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����02:59:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBjdlList() {

		StringBuilder sql = new StringBuilder();

		sql.append(" select lbdm dm,lbmc mc from xg_xtwh_bjdlb t1 ");
		sql
				.append(" where exists (select 1 from xg_xtwh_bjlbb t2 where t1.lbdm=t2.lbdm)");

		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @����: ��ѯ�༶��Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����03:02:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBjxxList(String[] bjdm) {

		StringBuilder sql = new StringBuilder();

		sql.append("select bjdm ,bjmc from view_njxyzybj where ");

		for (int i = 0, j = bjdm.length; i < j; i++) {
			sql.append("bjdm=?");

			if (i != j - 1) {
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), bjdm);
	}

	/**
	 * 
	 * @����:ȡ���༶����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-19 ����10:13:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @param bjdm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean qxBjdl(String[] bjdm, BjdlModel model) throws Exception {

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append("update xg_xtwh_bjlbb set lbdm = '' ");
		sql.append("where bjdm in ( select bjdm from ( ");
		sql
				.append("select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.nj,t1.bjmc,t1.bjdm,nvl(t2.lbdm,'0') lbdm from view_njxyzybj t1 ");
		sql
				.append("left join xg_xtwh_bjlbb t2 on t1.bjdm=t2.bjdm ) where 1=1  ");

		sql.append(searchTj);

		if (null != bjdm && bjdm.length != 0) {

			for (int i = 0, j = bjdm.length; i < j; i++) {
				if (i == 0) {
					sql.append(" and bjdm in ( ? ");
				} else {
					sql.append(", ? ");
				}
			}
			sql.append(")");
		} else {
			bjdm = new String[] {};
		}

		sql.append(" )");

		bjdm = (String[]) ArrayUtils.addAll(inputV, bjdm);
		return dao.runUpdate2(sql.toString(), bjdm);
	}

}
