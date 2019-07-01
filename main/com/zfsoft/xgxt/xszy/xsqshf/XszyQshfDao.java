/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-6 ����04:39:12 
 */
package com.zfsoft.xgxt.xszy.xsqshf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-6 ����04:39:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszyQshfDao extends SuperDAOImpl<XszyQshfForm> {
	private static final String TSBM="90";//���ⲿ�ţ�����ѧԺ
	@Override
	public List<HashMap<String, String>> getPageList(XszyQshfForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XszyQshfForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.fpzt,t1.thr,t1.thxy,t1.thsj,t1.lddm,t1.qsh,t1.qsxb,(case when t1.xydm is null then '��' else '��' end) xysfwk,(case when t2.hhqs>1 then '��'else '��'end) sfhhqs,t2.nj,t2.rzrs,t2.dldm,");
		sql.append("t2.dl,t2.bjmc,t4.id,t4.ssyxdm,t4.ssyxmc,t4.fpczr,t4.czsj,t5.ldmc,t6.bmdm xydm,t6.bmmc xymc,t7.id qsgxid from xg_gygl_new_qsxxb t1 ");
		sql.append("left join(select t1.lddm,t1.qsh,t3.nj,count(t3.xh) rzrs,count(distinct(t3.xydm)) hhqs,wm_concat(distinct(t3.dldm)) dldm,");
		sql.append("wm_concat(distinct(t3.dl)) dl,wm_concat(distinct(t3.bjmc)) bjmc");
		sql.append(" from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2");
		sql.append(" on t1.lddm = t2.lddm and t1.qsh = t2.qsh  left join view_xszy_xsxx t3 ");
		sql.append(" on t2.xh = t3.xh and t2.nj=t3.nj group by t1.lddm,t1.qsh,t3.nj) t2 on t1.lddm=t2.lddm and t1.qsh = t2.qsh and t2.nj='"+t.getNj()+"'");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm left join xg_xszy_xszyqsgxb t7 on t1.lddm=t7.lddm and t1.qsh=t7.qsh and t7.nj='"+t.getNj()+"') t where 1=1 and nj='"+t.getNj()+"'");
		
		String fpzt = t.getFpzt();
		if (fpzt.equals("dfp")) {
			/*����ѧԺҪ����ʾ����δ��������ң���������2018-08-29���޸���:mengwei*/
			sql.append(" and ssyxdm is null and (fpzt is null or fpzt='0') and (1 = 1");
			if(!"xx".equals(user.getUserStatus())){
				sql.append(" and xydm in(select xydm from xg_xszy_xyb)");
			}
			sql.append(")");
		} else if(fpzt.equals("yth")){
			sql.append(" and fpzt='1' and qsgxid is null");
			//�������ѧԺֻ�鿴����ѧ԰�˻ؼ�¼
			if("xx".equals(user.getUserStatus())){
				//sql.append(" and (thxy='"+TSBM+"' or  sfhhqs ='��')");
				sql.append(" and thsj is not null");
			}else{
				sql.append(" and thsj is not null");
			}
		}else {
			sql.append("  and ssyxdm is not null ");
		}
		if("xx".equals(user.getUserStatus())){
			/*����ѧԺҪ����ʾ����δ��������ң��ǻ������ҲҪ��ʾ������������2018-08-29���޸���:mengwei*/
			/*sql.append(" and sfhhqs ='��' ");*/
		}else{
			sql.append(" and sfhhqs !='��' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append("order by dl desc,bjmc desc");
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:25:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getQsxx(XszyQshfForm t) throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append(" select t1.*,t2.ldmc,t3.bmmc xymc,t8.*  ");
		sql.append(" ,(case when t9.hhqs > 1 then '��' else '��' end) sfhhqs");
		sql.append(" from xg_gygl_new_qsxxb t1");
		sql.append(" left join xg_gygl_new_ldxxb t2 on t1.lddm=t2.lddm");
		sql.append(" left join zxbz_xxbmdm t3 on t1.xydm=t3.bmdm ");
		sql.append(" left join xg_xszy_xszyqsgxb t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh");
		sql.append("  left join (select t4.lddm,t4.qsh,t5.*,t6.bmmc dwmc,t7.zzmmmc from xg_xszy_xszyqsgxb t4 left join xg_xszy_xszyjbxxb t5");
		sql.append(" on t4.zgh = t5.zgh and t4.nj=t5.nj left join zxbz_xxbmdm t6 on t5.dwdm=t6.bmdm ");
		sql.append("left join zzmmdmb t7 on t5.zzmmdm=t7.zzmmdm where t4.nj=? ) t8 on t1.lddm=t8.lddm and t1.qsh=t8.qsh ");
		/*���ǵ�����֮����Ϣ��������ƥ�����ҡ�����undefind���������ֱ����SQL�����ѯ����,2017-09-13*/
		sql.append("left join (select t1.lddm,t1.qsh,t3.nj,count(t3.xh) rzrs, ");
		sql.append("count(distinct(t3.xydm)) hhqs,wm_concat(distinct(t3.dldm)) dldm,wm_concat(distinct(t3.dl)) dl,wm_concat(distinct(t3.bjmc)) bjmc ");
		sql.append("from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2 on t1.lddm = t2.lddm and t1.qsh = t2.qsh left join view_xszy_xsxx t3 on t2.xh = t3.xh ");
		sql.append("and t2.nj = t3.nj group by t1.lddm, t1.qsh, t3.nj) t9 ");
		sql.append("on t1.lddm = t9.lddm and t1.qsh = t9.qsh and t9.nj = ? ");
		sql.append(" where t1.lddm = ?  and t1.qsh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] {t.getNj(),t.getNj(),t.getLddm(), t.getQsh()});
	}

	/**
	 * 
	 * @����:��ȡȫУ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����10:44:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getQsxxAll(XszyQshfForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) qss,nvl(sum(man),0) man,nvl(sum(woman),0) woman from ( ");
		sql.append(" select case when qsxb = '��' then '1' end man, case when ");
		sql.append(" qsxb = 'Ů' then '1' end woman from  xg_gygl_new_qsxxb where lddm||qsh in (select lddm||qsh from(select distinct lddm,qsh from xg_xszy_xszsxxb where nj=?))) ");
		return dao.getMapNotOut(sql.toString(), new String[] {myForm.getNj()});
	}
	/**
	 * 
	 * @����:��ȡѧ԰������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-21 ����04:41:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXyQsxx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) qss,sum(man) man,nvl(sum(woman),0) woman from ( ");
		sql.append(" select case when qsxb = '��' then '1' end man, case when ");
		sql.append(" qsxb = 'Ů' then '1' end woman from  xg_gygl_new_qsxxb a) ");
		return dao.getMapNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����:�鿴��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����10:01:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getFpxq(XszyQshfForm t,User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ssyxdm,ssyxmc,nvl(sum(man),0) man,nvl(sum(woman),0) woman from (");
		sql.append("  select ssyxdm,ssyxmc,case when qsxb = '��' then '1' end man, case when");
		sql.append(" qsxb = 'Ů' then '1' end woman from (select a.*, b.qsxb");
		sql.append(" from (select * from xg_xszy_qsfpb where nj=?) a left join xg_gygl_new_qsxxb b ");
		sql.append(" on a.lddm = b.lddm and a.qsh = b.qsh where a.nj=?) ) ");
		sql.append(" group by ssyxdm,ssyxmc order by ssyxmc");
		
		return dao.getListNotOut(sql.toString(), new String[] { t.getNj(),t.getNj() });
	}
	/**
	 * 
	 * @����: ѧ԰����ͳ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-22 ����01:55:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFptj(XszyQshfForm t,User user)
	throws Exception {
	StringBuilder sql = new StringBuilder();
	sql.append("  select xydm,xymc,nj,count(qsh)qss,sum(ssyxdm) yhfqs,sum(zgh)yfpxszy");
	sql.append("  from (select distinct a.lddm,a.xydm,a.qsh, t.nj, (case when b.ssyxdm is null then '0' else '1' end) ssyxdm,c.bmmc xymc,(case when e.zgh");
	sql.append(" is null then '0' else '1' end)zgh  ");
	sql.append(" from xg_gygl_new_qsxxb a  left join (select * from XG_XSZY_XSZSXXB where nj=?) t on a.lddm=t.lddm and a.qsh=t.qsh left join xg_xszy_qsfpb b ");
	sql.append(" on a.lddm = b.lddm and a.qsh = b.qsh and b.nj=? left join zxbz_xxbmdm c ");
	sql.append(" on a.xydm=c.bmdm left join xg_xszy_xszyqsgxb e on a.lddm=e.lddm and a.qsh=e.qsh and e.nj=?");
	sql.append(" )  ");
	sql.append(" where nj=? and (xydm in (select xydm from xg_xszy_xyb) or xydm is null)group by xydm,xymc,nj order by xymc");
	return dao.getListNotOut(sql.toString(), new String[] { t.getNj(),t.getNj(),t.getNj(),t.getNj()  });
}

	/**
	 * 
	 * @����:��סѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:33:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getRzxsList(XszyQshfForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append(" select t1.*,t2.lddm,t2.qsh,t3.mzmc from view_xszy_xsxx t1 ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on t1.xh=t2.xh and t1.nj=t2.nj left join mzdmb t3 on t1.mzdm=t3.mzdm");
		sql.append(") t where t.lddm=? and t.qsh = ? and t.nj = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { t.getLddm(),
				t.getQsh(), t.getNj() });
	}
	
	public List<HashMap<String, String>> getRzxsListOfLxk(XszyQshfForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* ,case when lag(t.d)over( order by t.d) = d then '' else d end bzrxm,");
       sql.append("case when lag(t.e)over( order by t.d) = e then '' else e end dh from (");
		sql.append(" select t1.xm a,t1.lxdh b,t1.dzyx c,t1.bzrxm d,t1.bzrlxdh e,t1.nj,t2.lddm,t2.qsh,t3.mzmc from view_xszy_xsxx t1 ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on t1.xh=t2.xh and t1.nj=t2.nj left join mzdmb t3 on t1.mzdm=t3.mzdm");
		sql.append(") t where t.lddm=? and t.qsh = ? and t.nj = ? order by bzrxm nulls last");
		return dao.getListNotOut(sql.toString(), new String[] { t.getLddm(),
				t.getQsh(), t.getNj() });
	}
	
	public List<HashMap<String, String>> getXsBzrList(XszyQshfForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct(bzrxm) xm,bzrlxdh dh from (");
		sql.append(" select t1.xm,t1.lxdh,t1.bzrxm,t1.bzrlxdh,t1.nj,t2.lddm,t2.qsh,t3.mzmc from view_xszy_xsxx t1 ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on t1.xh=t2.xh and t1.nj=t2.nj  left join mzdmb t3 on t1.mzdm=t3.mzdm");
		sql.append(") t where t.lddm=? and t.qsh = ? and t.nj = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { t.getLddm(),
				t.getQsh(), t.getNj() });
	}
	
	public List<HashMap<String, String>> getXsFdyList(XszyQshfForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct(fdyxm) xm,fdylxdh dh from (");
		sql.append(" select t1.xm,t1.lxdh,t1.fdyxm,t1.fdylxdh,t1.nj,t2.lddm,t2.qsh,t3.mzmc from view_xszy_xsxx t1 ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on t1.xh=t2.xh and t1.nj=t2.nj left join mzdmb t3 on t1.mzdm=t3.mzdm");
		sql.append(") t where t.lddm=? and t.qsh = ? and t.nj = ?  and t.fdyxm is not null ");
		sql.append("  union all select ''xm,''dh from dual");
		return dao.getListNotOut(sql.toString(), new String[] { t.getLddm(),
				t.getQsh(), t.getNj() });
	}

	/**
	 * 
	 * @����:��ȡ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-9 ����03:16:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select distinct(bmdm)xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from zxbz_xxbmdm ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	public List<HashMap<String, String>> getXyList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select distinct(xydm)xydm,F_PINYIN(substr(xymc,0,1)) xypy,F_PINYIN(substr(xymc,0,1)) ||'-'||xymc xymc ");
		sql.append("from view_njxyzybj_all ");
		sql.append("union  ");
		sql.append("select bmdm xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from xg_xszy_tsbmb ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}

	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����08:52:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getBmmc(String bmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bmdm }, "bmmc");
	}

	/**
	 * 
	 * @����:���һ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����04:22:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	public boolean qshfPlbc(List<String[]> params,List<String[]> delparams) throws SQLException {
		String delSql = "delete from xg_xszy_qsfpb where lddm=? and qsh=? and nj=?";
		dao.runBatch(delSql,delparams);
		String sql = "insert into xg_xszy_qsfpb(lddm,qsh,nj,ssyxdm,ssyxmc,fpczr,czsj) values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�������ҷ���״̬
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-20 ����09:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateQsxxZt(XszyQshfForm model) throws Exception {
		String sql = "update xg_gygl_new_qsxxb set fpzt=?, thr=?, thsj=?, thxy=? where lddm=? and qsh=?";
		return dao.runUpdate(sql, new String[]{model.getFpzt(),model.getThr(),"",model.getThxy(),model.getLddm(),model.getQsh()});
	}
	
	public boolean delQsfp(XszyQshfForm model) throws Exception {
		String sql = "delete xg_xszy_qsfpb  where lddm=? and qsh=? and nj=?";
		return dao.runUpdate(sql, new String[]{model.getLddm(),model.getQsh(),model.getNj()});
	}


	@Override
	protected void setTableInfo() {
		super.setClass(XszyQshfForm.class);
		super.setKey("id");
		super.setTableName("xg_xszy_qsfpb");

	}

}
