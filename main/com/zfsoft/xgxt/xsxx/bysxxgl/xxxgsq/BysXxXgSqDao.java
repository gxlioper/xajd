/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����04:32:29 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����04:32:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxXgSqDao extends SuperDAOImpl<BysXxForm> {
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t)
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
	public List<HashMap<String, String>> getPageList(BysXxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:��ѯ�����¼
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����06:43:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	// public List<HashMap<String, String>> getXgSqPageList(BysXxForm model,
	// User user) throws Exception {
	//
	// StringBuilder sb = new StringBuilder();
	// sb.append("select * from (");
	// sb.append("select  c.*,a.sqid,a.xgsj,a.shjg ");
	// sb
	// .append(", b.guid spclid,b.lcid,b.ywid,row_number() over(partition by b.ywid order by b.shsj desc) as rn ");
	// sb.append(" from xg_bysxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx  c ");
	// sb.append(" where a.xh=c.xh  and a.sqid=b.ywid");
	// sb.append(") a where  rn=1 ");
	// return getPageList(model, sb.toString(), new String[] {});
	// }
	/**
	 * 
	 * @����:��ѯ�����¼
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����04:35:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageList(BysXxForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select  b.*,a.sqid,a.xgsj,a.shjg,decode(a.shjg,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') shjgmc,a.splc ");
		sql.append(" from xg_bysxx_xxxgsqb a,view_xsjbxx  b ");
		sql.append(" where a.xh=b.xh");
		sql.append(") c where  1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:ɾ��ԭ��δ���������������¼
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����04:29:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean deleteXgsq(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_bysxx_xxxgsqb ");
		sql.append(" where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("' ");
		String[] inputValue = { model.getXh() };
		return dao.runUpdate(sql.toString(), inputValue);

	}
	public boolean deleteShlc(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_shztb ");
		sql.append(" where ywid in(select sqid from xg_bysxx_xxxgsqb where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("') ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}

	public boolean sqxxDel(String sqid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_bysxx_xxxgsqb ");
		sql.append(" where sqid=? ");
		String[] inputValue = { sqid };
		return dao.runUpdate(sql.toString(), inputValue);

	}

	/**
	 * 
	 * @����:��ѯ�����¼(ѧ��)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����06:52:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageListByStu(BysXxForm model,
			User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select  b.*,a.sqid,a.xgsj,a.shjg,decode(a.shjg,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') shjgmc,a.splc ");
		sql.append(" from xg_bysxx_xxxgsqb a,view_xsjbxx  b ");
		sql.append(" where a.xh=b.xh and a.xh=?");
		sql.append(") c where  1=1 ");
		return getPageList(model, sql.toString(), new String[] { model.getXh() });
	}

	/**
	 * 
	 * @����:��ѯ��ҵ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����10:56:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBysXxList(BysXxForm model, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from ( ");
		sql.append("select b.*,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql
				.append(
						"a.bjmc,a.xydm,a.zydm,a.bjdm from XG_BYSXX_BYSXXB b join view_xsjbxx a ")
				.append(
						"on a.xh=b.xh where b.xh not in(select xh from xg_bysxx_xxxgsqb ) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and bynd = (select bynd from XG_BYSXX_XXXGCSSZB) ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:TODO�����޸��ֶμ�ֵ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����02:32:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean insertXgzd(List<BysXxXgZdForm> list, String sqid)
			throws Exception {
		int[] result = null;
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		sb.append(" values(?,?,?,?)");
		for (BysXxXgZdForm xgzdForm : list) {
			String[] param = { sqid, xgzdForm.getZd(), xgzdForm.getZdz(),
					xgzdForm.getXgqz() };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:TODO�����޸��ֶ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����08:49:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateXgzd(List<BysXxXgZdForm> list, String sqid)
			throws Exception {
		int[] result = null;
		List<String[]> paramList = new ArrayList<String[]>();
		List<String[]> yxgParamList = new ArrayList<String[]>();
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		insertSql.append(" values(?,?,?,?)");
		StringBuffer updateSql = new StringBuffer();
		updateSql.append("update xg_xsxx_xgzdb set zdz=? where sqid=? and zd=?");
		for (BysXxXgZdForm xgzdForm : list) {
			String[] param = { sqid, xgzdForm.getZd(), xgzdForm.getZdz(),
					xgzdForm.getXgqz() };
			
			if (Integer.parseInt(isYxgZd(param))>0) {
				String[] yxgParam = { param[2], param[0], param[1] };
				yxgParamList.add(yxgParam);
			} else {
				paramList.add(param);
			}

		}
		if(yxgParamList.size()>0){
			result = dao.runBatch(updateSql.toString(), yxgParamList);
			}
		if(paramList.size()>0){
			result = dao.runBatch(insertSql.toString(), paramList);
		}
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:TODO�ж��ֶ����޸Ĺ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����08:53:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xgzd
	 * @return boolean ��������
	 * @throws
	 */
	private String isYxgZd(String[] xgzd) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) count from xg_xsxx_xgzdb where sqid=? and zd=? ");
		return dao.getOneRs(sb.toString(), new String[] {xgzd[0], xgzd[1]},"count");
	}

	/**
	 * 
	 * @����:TODO�����ҵ����Ϣ�޸�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����02:36:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean insertXgsq(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_bysxx_xxxgsqb(sqid,xh,xgsj,shjg,splc) ");
		sql.append(" values(?,?,?,?,?) ");
		String[] inputValue = { model.getSqid(), model.getXh(),
				model.getXgsj(), model.getShjg(), model.getSplc() };
		return dao.runUpdate(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:�޸��ֶ�ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����09:05:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean xgZdDel(String[] values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xsxx_xgzdb where sqid=?");
		return dao.runUpdate(sql.toString(), values);
	}

	public boolean updateXgSq(BysXxXgSqForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_bysxx_xxxgsqb set shjg=? where sqid=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getShjg(),
				model.getSqid() });
	}

	/**
	 * 
	 * @����:TODO�����޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����08:44:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean sqXgUpdate(BysXxXgSqForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_bysxx_xxxgsqb set shjg=?,xgsj=? where sqid=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getShjg(),
				model.getXgsj(), model.getSqid() });
	}

	/**
	 * 
	 * @����:TODO��ȡ������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����04:44:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getSqXxByXh(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_bysxx_xxxgsqb");
		sb.append("  where  xh=?");
		String[] input = { xh };
		return dao.getMapNotOut(sb.toString(), input);
	}

	public HashMap<String, String> getXhBySqid(String sqid) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_bysxx_xxxgsqb");
		sb.append("  where  sqid=?");
		String[] input = { sqid };
		return dao.getMapNotOut(sb.toString(), input);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_bysxx_xxxgsqb");
	}

}
