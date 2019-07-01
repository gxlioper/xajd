/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-12 ����03:54:01 
 */  
package com.zfsoft.xgxt.xszy.qsppgl;

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
 * @ʱ�䣺 2015-2-12 ����03:54:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszyQsppDao extends SuperDAOImpl<XszyQsppForm>{


	
	@Override
	public List<HashMap<String, String>> getPageList(XszyQsppForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(XszyQsppForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchQxTjByUser = SearchService.getSearchQxTjByUser(user, "t",
				"ssyxdm", "true");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append(" select t1.lddm,t1.qsh,t1.qsxb,(case when t1.xydm is null then '��' else '��' end) xysfwk,t1.xydm,t1.bz,(case when t2.hhqs>1 then '��'else '��'end) sfhhqs,t4.id qsfpid,t2.nj,nvl(t2.rzrs,0)rzrs,t2.dldm,t2.dl,t7.id qsgxid,");
		sql.append("(case when t9.bmlb='1' then '��' else '��' end)sfjg,t2.bjmc, nvl(t4.ssyxdm,  (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxdm else t1.xydm end) ) ssyxdm,");
        sql.append(" nvl(t4.ssyxmc, (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxmc else t11.bmmc end)) ssyxmc,t4.fpczr,t4.czsj,t5.ldmc,t6.bmmc xymc,t8.zgh,t8.xm,");
		sql.append(" t8.xb,t8.zwzc,t8.dwdm,t8.lxdh,t8.dzyx,t8.dlyq,t8.bz xszybz,t8.kyxbj,t9.bmmc dwmc,t10.zzmmmc, (case when t8.zgh is null then '��'else '��' end) sfypp from  ");
		sql.append("(select t1.lddm,t1.qsh,t4.nj,count(t2.xh) rzrs,count(distinct(t4.xydm)) hhqs,wm_concat(distinct(t4.zydm)) dldm,");
		sql.append("wm_concat(distinct(t4.zymc)) dl,wm_concat(distinct(t4.bjmc)) bjmc");
		sql.append(" from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2");
		sql.append(" on t1.lddm = t2.lddm and t1.qsh = t2.qsh and t2.nj='"+t.getNj()+"' left join xsxxb t3 ");
		sql.append(" on t2.xh = t3.xh left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm group by t1.lddm,t1.qsh,t4.nj) t2 left join xg_gygl_new_qsxxb t1 on t1.lddm=t2.lddm and t1.qsh = t2.qsh");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm left join xg_xszy_xszyqsgxb t7 on t7.nj='"+t.getNj()+"'");
		sql.append(" and t1.lddm=t7.lddm and t1.qsh=t7.qsh ");
		sql.append(" left join XG_XSZY_XSZYJBXXB t8 on t7.nj=t8.nj and t7.zgh = t8.zgh left join zxbz_xxbmdm t9 on t8.dwdm=t9.bmdm ");
		sql.append("left join zzmmdmb t10 on t8.zzmmdm=t10.zzmmdm left join zxbz_xxbmdm t11 on t1.xydm=t11.bmdm) t where 1=1  and t.ssyxdm is not null and nj='"+t.getNj()+"'");
		sql.append(searchQxTjByUser);
		sql.append(searchTj);
		sql.append(" ");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getExportData(XszyQsppForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchQxTjByUser = SearchService.getSearchQxTjByUser(user, "t",
				"ssyxdm", "true");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append(" select t1.lddm,t1.qsh,t1.qsxb,t11.bmdm,t12.xssx,(case when t1.xydm is null then '��' else '��' end) xysfwk,t1.xydm,t1.bz,(case when t2.hhqs>1 then '��'else '��'end) sfhhqs,t4.id qsfpid,t2.nj,nvl(t2.rzrs,0)rzrs,t2.dldm,t2.dl,t7.id qsgxid,");
		sql.append("t2.bjmc, nvl(t4.ssyxdm,  (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxdm else t1.xydm end) ) ssyxdm,");
        sql.append(" nvl(t4.ssyxmc, (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxmc else t11.bmmc end)) ssyxmc,t4.fpczr,t4.czsj,t5.ldmc,t6.bmmc xymc,t8.zgh,t8.xm,");
        sql.append(" t8.xb,t8.zwzc,t8.dwdm,t8.lxdh,t8.dzyx,t8.dlyq,t8.bz xszybz,t8.kyxbj,t9.bmmc dwmc,t10.zzmmmc, (case when t8.zgh is null then '��'else '��' end) sfypp,(case when t9.bmlb = '1' then '��'else '��' end) sfjg from  ");
		sql.append("(select t1.lddm,t1.qsh,t4.nj,count(t2.xh) rzrs,count(distinct(t4.xydm)) hhqs,wm_concat(distinct(t4.zydm)) dldm,");
		sql.append("wm_concat(distinct(t4.zymc)) dl,wm_concat(distinct(t4.bjmc)) bjmc");
		sql.append(" from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2");
		sql.append(" on t1.lddm = t2.lddm and t1.qsh = t2.qsh and t2.nj='"+t.getNj()+"' left join xsxxb t3 ");
		sql.append(" on t2.xh = t3.xh left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm group by t1.lddm,t1.qsh,t4.nj) t2 left join xg_gygl_new_qsxxb t1 on t1.lddm=t2.lddm and t1.qsh = t2.qsh");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm left join xg_xszy_xszyqsgxb t7 on t7.nj='"+t.getNj()+"'");
		sql.append(" and t1.lddm=t7.lddm and t1.qsh=t7.qsh ");
		sql.append(" left join XG_XSZY_XSZYJBXXB t8 on t7.nj=t8.nj and t7.zgh = t8.zgh left join zxbz_xxbmdm t9 on t8.dwdm=t9.bmdm ");
		sql.append("left join zzmmdmb t10 on t8.zzmmdm=t10.zzmmdm left join zxbz_xxbmdm t11 on t1.xydm=t11.bmdm left join xg_xszy_bmsxb t12 on t11.bmdm=t12.bmdm order by t9.bmlb asc,t9.bmdm asc) t where 1=1  and t.ssyxdm is not null and nj='"+t.getNj()+"'");
		sql.append(searchQxTjByUser);
		sql.append(searchTj);
			sql.append("order by zgh desc nulls last,lddm asc, qsh desc,to_number(xssx) asc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getfwExportData(XszyQsppForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchQxTjByUser = SearchService.getSearchQxTjByUser(user, "t",
				"ssyxdm", "true");
		StringBuilder sql = new StringBuilder();
		sql.append("select wm_concat(fpqs) fpqs,dwdm,dwmc,zgh,xm,xssx from(select * from (");
		sql.append(" select t1.lddm,t1.qsh,t1.qsxb,t1.xydm,t5.ldmc||t1.qsh fpqs,(case when t1.xydm is null then '��' else '��' end) xysfwk,t1.bz,t4.id qsfpid,t2.nj,nvl(t2.rzrs,0)rzrs,(case when t2.hhqs>1 then '��'else '��'end) sfhhqs,t2.dldm,t2.dl,t7.id qsgxid,t4.fpczr,t4.czsj,t5.ldmc,t6.bmmc xymc,t8.zgh,t8.xm,");
		sql.append(" nvl(t4.ssyxdm,  (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxdm else t1.xydm end) ) ssyxdm,");
        sql.append(" nvl(t4.ssyxmc, (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxmc else t6.bmmc end)) ssyxmc,");
        sql.append(" t8.xb,t8.zwzc,t8.dwdm,t8.lxdh,t8.dzyx,t8.dlyq,t8.bz xszybz,t8.kyxbj,t9.bmmc dwmc,t10.zzmmmc,t11.xssx, (case when t8.zgh is null then '��'else '��' end) sfypp from  ");
		sql.append("(select t1.lddm,t1.qsh,t4.nj,count(t2.xh) rzrs,count(distinct(t4.xydm)) hhqs,wm_concat(distinct(t4.zydm)) dldm,");
		sql.append("wm_concat(distinct(t4.zymc)) dl,wm_concat(distinct(t4.bjmc)) bjmc");
		sql.append(" from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2");
		sql.append(" on t1.lddm = t2.lddm and t1.qsh = t2.qsh and t2.nj='"+t.getNj()+"' left join xsxxb t3 ");
		sql.append(" on t2.xh = t3.xh left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm group by t1.lddm,t1.qsh,t4.nj) t2 left join xg_gygl_new_qsxxb t1 on t1.lddm=t2.lddm and t1.qsh = t2.qsh");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm left join xg_xszy_xszyqsgxb t7 on t7.nj='"+t.getNj()+"'");
		sql.append(" and t1.lddm=t7.lddm and t1.qsh=t7.qsh ");
		sql.append(" left join XG_XSZY_XSZYJBXXB t8 on t7.nj=t8.nj and t7.zgh = t8.zgh left join zxbz_xxbmdm t9 on t8.dwdm=t9.bmdm ");
		sql.append("left join zzmmdmb t10 on t8.zzmmdm=t10.zzmmdm left join xg_xszy_bmsxb t11 on t8.dwdm=t11.bmdm) t where 1=1 and t.ssyxdm is not null and qsgxid is not null ");
		sql.append(searchQxTjByUser);
		sql.append(searchTj);
		sql.append(")  group by dwdm,dwmc,zgh,xm,xssx order by to_number(xssx) ");
		return getPageList(t, sql.toString(), inputV);
}
	/**
	 * 
	 * @����:��ȡ����֮��ѡ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-13 ����02:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSgppXszyList(XszyQsppForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,nvl(t1.bjyx,t2.bmdm) xydm,(case when t2.bmlb='1' then '��' else '��' end)sfjg,t2.bmmc,t3.zzmmmc,t4.bmmc bjyxmc,nvl(t5.ppqss,0) ppqss,nvl2(t5.ppqss,'��','��')sfypp,t5.ppqs from XG_XSZY_XSZYJBXXB t1 left join zxbz_xxbmdm t2 on t1.dwdm=t2.bmdm ");
		sql.append(" left join zzmmdmb t3 on t1.zzmmdm=t3.zzmmdm");
		sql.append(" left join(select distinct(xydm) bmdm,xymc bmmc from view_njxyzybj_all");
		sql.append(" union all ");
		sql.append(" select bmdm,bmmc from xg_xszy_tsbmb)t4 on t1.bjyx=t4.bmdm ");
		sql.append(" left join (select a.zgh,b.nj,count(1) ppqss,wm_concat(c.ldmc||b.qsh) ppqs from XG_XSZY_XSZYJBXXB a left join xg_xszy_xszyqsgxb b on");
		sql.append(" a.zgh=b.zgh and a.nj=b.nj");
		sql.append(" left join xg_gygl_new_ldxxb c on b.lddm=c.lddm group by a.zgh,b.nj) t5 on t1.zgh=t5.zgh and t1.nj=t5.nj");
		sql.append(" ) t where 1=1 and t.nj='"+t.getNj()+"' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
 		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:ɾ������֮�����ҹ�ϵ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-13 ����03:59:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delQsgx(XszyQsppForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xszy_xszyqsgxb where  nj =? and lddm = ? and qsh = ?");
		return dao.runUpdate(sql.toString(), new String[]{t.getNj(),t.getLddm(),t.getQsh()});
	}
	/**
	 * 
	 * @����:�����Զ�ƥ����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-1 ����11:31:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean qsppPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_xszy_xszyqsgxb(nj,lddm,qsh,zgh,czr,czsj) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����: ��ȡԺϵ������Ϣ������֮����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-15 ����11:35:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsxxAndXszy(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.ssyxdm,a.ssyxmc,nvl(a.qss,0) qss,nvl(a.man,0) man,nvl(a.woman,0) woman,nvl(b.xszys,0)xszys,nvl(b.man,0) xszy_man,nvl(b.woman,0) xszy_woman from(select ssyxdm,ssyxmc,nj,count(1) qss,");
		sql.append(" nvl(sum(man),0) man,nvl(sum(woman),0) woman from(select ssyxdm,ssyxmc,nj,");
		sql.append(" case when qsxb = '��' then  '1' end man, case when qsxb = 'Ů' then '1' end woman from (select a.*, b.qsxb ");
        sql.append(" from xg_xszy_qsfpb a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm ");
        sql.append(" and a.qsh = b.qsh where not exists(select 1 from xg_xszy_xszyqsgxb c where a.nj=c.nj and a.lddm=c.lddm and a.qsh=c.qsh))) where  nj=? and ssyxdm=? group by ssyxdm,ssyxmc,nj )a ");
        sql.append(" full join (select ssyx,nj,count(1) xszys, nvl(sum(man),0) man,nvl(sum(woman),0) woman");
        sql.append(" from (select case when a.xb = '��' then '1' end man,case when a.xb = 'Ů' then '1' end woman,");
        sql.append(" (case when a.bjyx is not null then a.bjyx else a.dwdm end)ssyx,a.*");
        sql.append(" from XG_XSZY_XSZYJBXXB a where not exists(select 1 from xg_xszy_xszyqsgxb c where a.nj=c.nj and a.zgh=c.zgh))");
        sql.append(" where  nj=? and ssyx=? group by ssyx,nj)b on a.ssyxdm=b.ssyx and a.nj=b.nj ");
        sql.append("  ");
        return dao.getMapNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep(),t.getNj(),user.getUserDep()});
	}
	/**
	 * 
	 * @����:��ȡԺϵ�Ա�Ϊ����δ���������֮��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-1 ����11:10:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXszyOfMan(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
        sql.append(" select * from(select (case when a.bjyx is not null then a.bjyx else a.dwdm end)ssyxdm,a.*");
        sql.append(" from XG_XSZY_XSZYJBXXB a) a");
        sql.append(" where  a.nj=? and a.ssyxdm=? and a.xb='��'");
        sql.append(" and not exists(select 1 from xg_xszy_xszyqsgxb c");
        sql.append(" where a.nj=c.nj and a.zgh=c.zgh)");
        return dao.getListNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep()});
	}
	
	/**
	 * 
	 * @����:��ȡԺϵ�Ա�ΪŮ������֮��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-1 ����11:10:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXszyOfWoman(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from(select (case when a.bjyx is not null then a.bjyx else a.dwdm end)ssyxdm,a.*");
        sql.append(" from XG_XSZY_XSZYJBXXB a) a");
        sql.append(" where  a.nj=? and a.ssyxdm=? and a.xb='Ů'");
        sql.append(" and not exists(select 1 from xg_xszy_xszyqsgxb c");
        sql.append(" where a.nj=c.nj and a.zgh=c.zgh)");
        return dao.getListNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep()});
	}
	/**
	 * ��ȡԺϵδƥ������֮���������Ա�Ϊ�е�����
	 */
	public List<HashMap<String,String>> getQsxxOfMan(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.qsxb ");
        sql.append(" from xg_xszy_qsfpb a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm ");
        sql.append(" and a.qsh = b.qsh");
        sql.append(" where  a.nj=? and a.ssyxdm=? and b.qsxb='��'");
        sql.append(" and not exists(select 1 from xg_xszy_xszyqsgxb c");
        sql.append(" where a.nj=c.nj and a.qsh=c.qsh and a.lddm=c.lddm)");
        return dao.getListNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep()});
	}
	/**
	 * ��ȡԺϵδƥ������֮���������Ա�ΪŮ������
	 */
	public List<HashMap<String,String>> getQsxxOfWoman(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.qsxb ");
        sql.append(" from xg_xszy_qsfpb a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm ");
        sql.append(" and a.qsh = b.qsh");
        sql.append(" where  a.nj=? and a.ssyxdm=? and b.qsxb='Ů'");
        sql.append(" and not exists(select 1 from xg_xszy_xszyqsgxb c");
        sql.append(" where a.nj=c.nj and a.qsh=c.qsh and a.lddm=c.lddm)");
        return dao.getListNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep()});
	}
	
	
	/**
	 * 
	 * @����:��ȡԺϵ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-15 ����04:54:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYxQsxx(XszyQsppForm t,User user){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,nvl(b.xszys,0)xszys,nvl(b.man,0) xszy_man,nvl(b.woman,0) xszy_woman from(select ssyxdm,ssyxmc,nj,count(1) qss,");
		sql.append(" nvl(sum(man),0) man,nvl(sum(woman),0) woman from(select ssyxdm,ssyxmc,nj,");
		sql.append(" case when qsxb = '��' then  '1' end man, case when qsxb = 'Ů' then '1' end woman from (select a.*, b.qsxb ");
        sql.append(" from xg_xszy_qsfpb a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm ");
        sql.append(" and a.qsh = b.qsh)) group by ssyxdm,ssyxmc,nj )a ");
        return dao.getListNotOut(sql.toString(), new String[]{t.getNj(),user.getUserDep()});
	}
	/**
	 * 
	 * @����:����ƥ���˻������˻���Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-19 ����04:03:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean qsPlth(List<String[]> params) throws SQLException {
		String sql = "update xg_gygl_new_qsxxb set fpzt=?, thr=?, thsj=?, thxy=? where lddm=? and qsh=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(XszyQsppForm.class);
		super.setKey("id");
		super.setTableName("xg_xszy_xszyqsgxb");
		
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-24 ����06:30:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportDataGroup(XszyQsppForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchQxTjByUser = SearchService.getSearchQxTjByUser(user, "t",
				"ssyxdm", "true");
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct dwdm,dwmc  from (");
		sql.append(" select t1.lddm,t1.qsh,t1.qsxb,t11.bmdm,t12.xssx,(case when t1.xydm is null then '��' else '��' end) xysfwk,t1.xydm,t1.bz,(case when t2.hhqs>1 then '��'else '��'end) sfhhqs,t4.id qsfpid,t2.nj,nvl(t2.rzrs,0)rzrs,t2.dldm,t2.dl,t7.id qsgxid,");
		sql.append("t2.bjmc, nvl(t4.ssyxdm,  (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxdm else t1.xydm end) ) ssyxdm,");
		sql.append(" nvl(t4.ssyxmc, (case when t1.xydm in(select xydm from xg_xszy_xyb where bz<>'1') then t4.ssyxmc else t11.bmmc end)) ssyxmc,t4.fpczr,t4.czsj,t5.ldmc,t6.bmmc xymc,t8.zgh,t8.xm,");
		sql.append(" t8.xb,t8.zwzc,t8.dwdm,t8.lxdh,t8.dzyx,t8.dlyq,t8.bz xszybz,t8.kyxbj,t9.bmmc dwmc,t10.zzmmmc, (case when t8.zgh is null then '��'else '��' end) sfypp from  ");
		sql.append("(select t1.lddm,t1.qsh,t4.nj,count(t2.xh) rzrs,count(distinct(t4.xydm)) hhqs,wm_concat(distinct(t4.zydm)) dldm,");
		sql.append("wm_concat(distinct(t4.zymc)) dl,wm_concat(distinct(t4.bjmc)) bjmc");
		sql.append(" from xg_gygl_new_qsxxb t1 left join XG_XSZY_XSZSXXB t2");
		sql.append(" on t1.lddm = t2.lddm and t1.qsh = t2.qsh and t2.nj='"+t.getNj()+"' left join xsxxb t3 ");
		sql.append(" on t2.xh = t3.xh left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm group by t1.lddm,t1.qsh,t4.nj) t2 left join xg_gygl_new_qsxxb t1 on t1.lddm=t2.lddm and t1.qsh = t2.qsh");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm left join xg_xszy_xszyqsgxb t7 on t7.nj='"+t.getNj()+"'");
		sql.append(" and t1.lddm=t7.lddm and t1.qsh=t7.qsh ");
		sql.append(" left join XG_XSZY_XSZYJBXXB t8 on t7.nj=t8.nj and t7.zgh = t8.zgh left join zxbz_xxbmdm t9 on t8.dwdm=t9.bmdm ");
		sql.append("left join zzmmdmb t10 on t8.zzmmdm=t10.zzmmdm left join zxbz_xxbmdm t11 on t1.xydm=t11.bmdm left join xg_xszy_bmsxb t12 on t11.bmdm=t12.bmdm order by t9.bmlb asc,t9.bmdm asc) t where 1=1  and t.ssyxdm is not null and nj='"+t.getNj()+"'");
		sql.append(searchQxTjByUser);
		sql.append(searchTj);
			sql.append("");
		return getPageList(t, sql.toString(), inputV);
}

}
