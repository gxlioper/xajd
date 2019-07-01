/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:39:31 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ϰ���ڹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwzxKqjgDao extends SuperDAOImpl<ZwzxKqjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t1.*,");
		sql.append(" (select xqmc from xqdzb b where t1.xq = b.xqdm) xqmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.lxmc cclxmc,");
		sql.append(" t4.xm jlrxm,");
		sql.append(" t5.bmmc jlrbmmc");
		if("2297".equals(Base.xxdm)){
			sql.append(" , nvl(t6.rs,0) ydrsszly");
		}
		/**
		 * �����Ƽ�
		 */
		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}
		sql.append(" from XG_RCSW_ZWZXKQ_JGB t1");
		sql.append(" left join VIEW_NJXYZYBJ_all t2");
		sql.append(" on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_RCSW_ZWZXKQ_CCLXB t3");
		sql.append(" on t1.cclxdm = t3.lxdm");
		sql.append(" left join fdyxxb t4");
		sql.append(" on t1.jlr = t4.zgh");
		sql.append(" left join zxbz_xxbmdm t5");
		sql.append(" on t5.bmdm = t4.bmdm");
		if("2297".equals(Base.xxdm)){
			sql.append(" left join (select count(1) rs, bjdm from view_xsxxb group by bjdm) t6");
			sql.append(" on t1.bjdm = t6.bjdm");
		}
		//�����Ƽ�
		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		}
		sql.append(" order by t1.ccrq desc) t");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ѯȱ��ѧ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����04:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where (t1.sjzt='1' or t1.sjzt is null) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡȱ��ѧ����Ϣ�б�����ҳ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����01:53:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllQqxsList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where (t1.sjzt='1' or t1.sjzt is null) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ���ڽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public ZwzxKqjgForm getKqjg(ZwzxKqjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.xm jlrxm,c.dbfdy ");
		sql.append(" from XG_RCSW_ZWZXKQ_JGB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join fdyxxb t4 on t1.jlr=t4.zgh ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.jgid=? ");
		return super.getModel(sql.toString(), new String[] { t.getJgid() });

	}

	/**
	 * 
	 * @����:��ȡȱ��ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:50:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,t1.*,(case when length(t1.ylzd1)>10 then substr(t1.ylzd1,0, 10)||'...' else t1.ylzd1 end)  ylzd1str, ");
		sql.append(" t2.xm,t3.qqlxmc from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join view_xsjbxx t2 on t1.xh=t2.xh");
		sql.append(" left join XG_RCSW_ZWZXKQ_QQLXB t3 on t1.qqlxdm = t3.qqlxdm");
		sql.append(" where t1.id=? ");
		return dao.getListNotOut(sql.toString(), new String[] { id });

	}

	/**
	 *��ѯȱ������
	 */
	public List<HashMap<String, String>> getQqlxList() {
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @����:ѧ��ȱ����Ϣ����鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����05:09:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getQqxsxx(ZwzxKqjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where t1.id=? and t1.xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { model.getSqid(), model.getXh() });
	}

	/**
	 *�жϰ༶���ڽ���Ƿ��Ѵ���
	 */
	public boolean isHaveKgjg(ZwzxKqjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_RCSW_ZWZXKQ_JGB where xn=? and xq=? and bjdm=? and ccrq=? and cclxdm=?");
		if (null != model.getJgid()) {
			sql.append(" and jgid<>'" + model.getJgid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(), model.getXq(), model.getBjdm(), model.getCcrq(), model.getCclxdm() }, "num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * 
	 * @����:��ȡ�༶��Ϣ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����09:32:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListNew(ZwzxKqjgForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();

		sql.append("select t.* from (");
		sql.append("select distinct a.nj,a.bjdm,a.bjmc,a.zydm,a.zymc,a.xydm,a.xymc,nvl(b.bjrs,0) bjrs,c.dbfdy from view_njxyzybj_all a");
		sql.append(" left join (select count(xh) bjrs,bjdm from view_xsjbxx group by bjdm)b on a.bjdm=b.bjdm ");
		sql.append("LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

	}

	/**
	 * 
	 * @����:��ȡѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����11:03:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(ZwzxKqjgForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.bjdm='" + model.getBjdm() + "'");
		if(xhs.length>0){
		sql.append(" and a.xh not in(");
		for (int i = 0; i < xhs.length; i++) {
			if(i!=0){
				sql.append(", ");
			}
			sql.append("'"+xhs[i]+"' ");
		}
		sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:ȱ��ѧ����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����03:33:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	public boolean qqxsPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_RCSW_ZWZXKQ_XSKQXXB(id,xh,xn,xq,lrsj,cclxdm,qqlxdm,kkjs,bjdm,ccrq,jlf,jlr,ylzd1,ylzd2,ylzd3,sjzt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:ɾ��ȱ��ѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����09:26:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	public boolean delQqxs(String jgid) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_XSKQXXB where id = ?";
		return dao.runUpdate(sql, new String[] { jgid });
	}

	/**
	 * 
	 * ɾ�����ڽ��
	 */
	public boolean delKqjgBySqid(String sqid) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_JGB where jgid=?";
		return dao.runUpdate(sql, new String[] { sqid });
	}
	/**
	 * 
	 * ��ȡѧ������
	 */
	public String getXqmc(String xq) throws Exception {
		String sql = "select xqmc from xqdzb  where xqdm=?";
		return dao.getOneRs(sql, new String[]{xq}, "xqmc");

	}

	/**
	 * 
	 * @����:����jgidɾ������ȱ��ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����11:39:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean delPlQqxs(List<String[]> params) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_XSKQXXB where id = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:����ѧ��ȱ����Ϣ����״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����11:03:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return boolean ��������
	 * @throws
	 */
	public boolean updateQqxs(String id, String sjzt) throws Exception {
		String sql = "update XG_RCSW_ZWZXKQ_XSKQXXB set sjzt=? where id = ?";
		return dao.runUpdate(sql, new String[] { sjzt, id });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ZwzxKqjgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_RCSW_ZWZXKQ_JGB");

	}

}
