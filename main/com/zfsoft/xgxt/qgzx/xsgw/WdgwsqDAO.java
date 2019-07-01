/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-17 ����09:16:37 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ѧ����λ-�ҵĸ�λ����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-17 ����09:09:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WdgwsqDAO extends SuperDAOImpl<WdgwsqForm> {
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");

	/**
	 * @����:��ȡ��λ�б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����03:25:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGwPageList(WdgwsqForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append(" select t.gwdm,t.gwmc,t1.id yrdwdm,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,");
		sql.append(" case when t.cq = '1' then '������Ч' else t.zpjssj end zpjssj,t.xqrs,");
		sql.append(" nvl((select count(1) from XG_QGZX_XSGWSQB where gwdm = t.gwdm group by gwdm),'0') ysqrs,");
		sql.append(" nvl((select count(1) from xg_qgzx_new_xsgwxxb where gwdm = t.gwdm group by gwdm),'0') ylyrs");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm");
		sql.append(" where t.shzt = '1') a where ylyrs < xqrs ");
		// �����ѧ���û� ����Ĭ������
		/*if (user.getUserStatus().equalsIgnoreCase("stu")) {
			sql.append(" and xn = '" + Base.currXn + "'");
			if("xq".equals(QgCommUtilf.getQgzq())){
				sql.append(" and xq = '"+Base.currXq+"' ");
			}
		}*/
		if (StringUtils.isNotNull(model.getYrdwdm())) {// ��������˵�λ���룬����Ϊ�ǵ���ʹ�ã��Ӳ�ѯ���˵�λ�ĸ�λ
			sql.append(" and yrdwdm='");
			sql.append(model.getYrdwdm());
			sql.append("'");
		}
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	// ===============================================//

	/**
	 * ��ȡ����
	 */
	public List<HashMap<String, String>> getGwsqPageListStu(WdgwsqForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String table = "VIEW_NEW_DC_QGZX_WDGWSQ";
		if("10335".equals(Base.xxdm)){
			table= "view_new_dc_qgzx_wdgwsq_ZJDX";
		}
		if("10351".equals(Base.xxdm)){//���ݴ�ѧ���Ի�
			sql.append(" select *from(select a.* ,(case when to_number(a.xqrs)<=to_number(a.zgrs) then 'N' else 'Y' end)sfksq, b.shzt, b.sqbh,  b.splc , decode(b.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') shztmc");
			sql.append(" from (select b.*,c.xydm from VIEW_XG_QGZX_GWXXB b left join xg_qgzx_gwxxsqxyglb c on b.gwdm = c.gwdm where b.sfyxgw='��' and exists (select 1 from view_xsbfxx d where d.xydm = c.xydm and d.xh = '"+user.getUserName()+"')) a");
			sql.append(" left join (select * from "+table+" where xh = '");
			sql.append(user.getUserName());
			sql.append("') b");
			sql.append(" on a.gwdm = b.GWDM");
			sql.append(" where not exists (select 1");
			sql.append(" from "+table+" t2");
			sql.append(" where t2.GWDM = a.gwdm");
			sql.append(" and t2.xh = '"+user.getUserName()+"'");
			sql.append(" and t2.shzt not in ('0', '3'))");
			/*
			sql.append(" and a.xn = '"+Base.currXn+"'");*/
			sql.append(") where 1=1 ");
			sql.append(searchTj);
		}else{			
			sql.append("select *from(select a.* ,(case when to_number(a.xqrs)<=to_number(a.zgrs) then 'N' else 'Y' end)sfksq, b.shzt, b.sqbh,  b.splc , decode(b.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') shztmc, decode(w.bmlb, '1', 'У��', '5', 'Ժ��') bmlb  ")
			.append("  from (select * from VIEW_XG_QGZX_GWXXB where sfyxgw='��') a")
			.append("  left join (select * from "+table+" where xh = '")
			.append(user.getUserName())
			.append("') b")
			.append("    on a.gwdm = b.GWDM ")
			.append(" left join ZXBZ_XXBMDM w on w.bmdm=a.yrdwdm ")
			.append(" where not exists (select 1")
			.append("          from "+table+" t2")
			.append("         where t2.GWDM = a.gwdm")
			.append("           and t2.xh = '")
			.append(user.getUserName())
			.append("'")
			.append("           and t2.shzt not in ('0', '3'))");
			/*
			.append("   and a.xn = '")
			.append(Base.currXn)
			.append("' ");*/
			
			sql.append(searchTj);
			sql.append(")");
		}
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��������Ŀ
	 * @���ߣ���С��[���ţ�1036]
	 */
	public List<HashMap<String, String>> getGwsqPageListStuYsq(
			WdgwsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String table = "VIEW_NEW_DC_QGZX_WDGWSQ";
		if("10335".equals(Base.xxdm)){
			table= "view_new_dc_qgzx_wdgwsq_ZJDX";
		}
		sql.append("select a.*,decode(b.bmlb, '1', 'У��', '5', 'Ժ��') bmlb   from "+table+" a " +
				" left join ZXBZ_XXBMDM b on b.bmdm=a.yrdwdm   where xh = '")
			.append(user.getUserName())
			.append("'")
			.append(" and shzt not in ('3' , '0') ");

		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	// ==============================================//
	public List<HashMap<String,String>> getGwList(WdgwsqForm t,User user)throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.gwdm,t.gwmc,t1.id yrdwdm,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,t.fbsj,t1.dwlb ");
		sql.append(" ,case when t.cq = '1' then '������Ч' else t.zpjssj end zpjssj,t.xqrs ");
		sql.append(" ,case when t.gwxzdm = '0' then '��ʱ' when t.gwxzdm = '1' then '��ʽ' else t.gwxzdm end gzxzmc, ");
		sql.append(" nvl((select count(xh) from XG_QGZX_XSGWSQB where gwdm = t.gwdm and shzt != '2' group by gwdm),'0') ysqrs,");
		sql.append(" nvl((select count(xh) from xg_qgzx_new_xsgwxxb where gwdm = t.gwdm and zgzt='zg' group by gwdm),'0') ylyrs, ");
		sql.append(" case when t3.sqbh is null then 'δ����' when t3.shzt='1' then 'ͨ��' when t3.shzt='0' then 'δ�ύ' else '������' end sqzt,t3.sqbh ");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm");
		sql.append(" left join (select sqbh,shzt,gwdm from XG_QGZX_XSGWSQB where xh = "+user.getUserName()+") t3 on t.gwdm = t3.gwdm ");
		sql.append(" where t.shzt = '1') a where ylyrs < xqrs ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String,String>> getWdgwsqjlList(WdgwsqForm t,User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append(" select t.sqbh,t.gwdm,t1.gwmc,nvl(t2.yrdwmc,t3.bmmc) yrdwmc,t2.dwlb,t1.xqrs,t.sqsj,t.shzt,t.splc ");
		sql.append(" ,nvl((select count(xh) from xg_qgzx_new_xsgwxxb where gwdm = t.gwdm and zgzt='zg' group by gwdm),'0') ylyrs ");
		sql.append(" ,decode(t.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', t.shzt) shztmc,t4.fid ");
		sql.append(" from XG_QGZX_XSGWSQB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t1.yrdwid = t2.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm");
		sql.append(" left join xg_comm_fileupload_data t4 on t1.fjid=t4.gid ");
		sql.append(" where t.xh= '"+user.getUserName()+"'");
		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public HashMap<String,String> getGwxxByGwdm(String gwdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.gwxzmc,nvl(t2.yrdwmc,t3.bmmc) yrdwmc");
		sql.append(" ,nvl((select count(xh) from xg_qgzx_new_xsgwxxb where gwdm = t.gwdm and zgzt='zg' group by gwdm),'0') ylyrs ");
		sql.append(" ,nvl((select count(xh) from XG_QGZX_XSGWSQB where gwdm = t.gwdm and shzt != '2' group by gwdm),'0') ysqrs ");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_GWXZDMB t1 on t.gwlb = t1.gwxzdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t.yrdwid = t2.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm");
		sql.append(" where t.gwdm = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{gwdm});
	}

	public HashMap<String,String> getGwxxByXhAndGwdm(String gwdm,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t2.gwxzmc,nvl(t3.yrdwmc,t4.bmmc) yrdwmc ");
		sql.append(" ,t1.xn,t1.gwmc,t1.gwxzdm,t1.xqrs,t1.gwlx,t1.gwcjsx,t1.gssx,t1.zpkssj,t1.zpjssj,t1.cq,t1.gwryyq");
		sql.append(" from xg_qgzx_new_xsgwxxb t ");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t2 on t1.gwlb = t2.gwxzdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t1.yrdwid = t3.id ");
		sql.append(" left join ZXBZ_XXBMDM t4 on t3.xydm = t4.bmdm");
		sql.append(" where t.gwdm = ? and t.xh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{gwdm,xh});
	}

	public List<HashMap<String,String>> getGzmxList(String gwdm,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select substr(ffsj, 0, 4) nd,substr(ffsj, 6) yf,gs,je from XG_QGZX_CJFF where gwdm = ? and xh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{gwdm,xh});
	}

	public boolean insertLzxx(WdgwsqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_QGZX_XSLZSQB(sqbh,gwdm,xh,sqsj,sqly,shzt,splc)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String[] input = {t.getSqbh(),t.getGwdm(),t.getXh(),t.getSqsj(),t.getSqly(),t.getShzt(),t.getSplc()};
		return dao.insert(sql.toString(),input);
	}

	public List<HashMap<String,String>> getWdGwList(WdgwsqForm t,User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append(" select t2.xm,t2.xydm,t2.pycc,t2.zybjmc,t2.zybj,t2.bjmc,t2.bjdm,t2.sydm1 sydm,t2.symc1 symc");
		sql.append(" ,t1.gwmc,t1.gwdm,t1.xn,t4.id yrdwdm,nvl(t4.yrdwmc,t3.bmmc) yrdwmc,t4.dwlb,t.sgsj,t.zgzt,t.tgsj ");
		sql.append(" ,(select sqbh from XG_QGZX_XSLZSQB where gwdm = t.gwdm and xh = t.xh) sqbh,t5.sqsj");
		sql.append(" ,nvl((select sum(gs) from XG_QGZX_MRKH_JGB where gwdm = t.gwdm and xh = t.xh),'0') gzsc");
		sql.append(" ,nvl((select sum(je) from XG_QGZX_CJFF where gwdm = t.gwdm and xh = t.xh),'0') zcj");
		sql.append(" ,t1.gwxzdm");
		sql.append(" ,case when t6.sqbh is not null then case when t6.shzt = '5' then '��ְ�����'else '����ְ'end ");
		sql.append(" else case when t.zgzt = 'zg' then '�ڸ�'else '����ְ' end end zgztmc ");
		sql.append(" from XG_QGZX_NEW_XSGWXXB t");
		sql.append(" left join XG_QGZX_XSLZSQB t6 on t.xh = t6.xh and t.gwdm = t6.gwdm ");
		sql.append(" left join XG_QGZX_XSGWSQB t5 on t.sqbh = t5.sqbh");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t4 on t1.yrdwid = t4.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t4.xydm = t3.bmdm ");
		sql.append(" left join view_xsjbxx t2 on t.xh = t2.xh");
		if("stu".equals(user.getUserType())){
			sql.append(" where t.xh = '"+user.getUserName()+"'");
		} else {
			sql.append(" where t.xh = '"+t.getXh()+"'");
		}

		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��˸�λ���ͨ������˸�λ ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-9 ����03:19:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shsftgbzgw
	 * @return String ��������
	 */
	public Integer getGwShtgRs(String splc, String shgw, String sqgw) {
		// ��ȡ��ǰ��˸�λ�����
		String xh = getGwSpXh(splc, shgw);
		// Integer xhI=StringUtils.paseStringToInteger(xh)-1;
		// Ȩ�����ͨ��������
		StringBuffer sb = new StringBuffer();
		// sb.append("select count(*) rs  from XG_QGZX_XSGWSQB a  left join XG_XTWH_SHZTB b  on b.ywid = a.sqbh  where a.splc=? and b.gwid = ? and a.shgw=? and b.shzt=?");
		// sb.append("select count(1) rs from xg_qgzx_xsgwsqb where shgw =? and gwdm=?");
		// sb.append("select count(1) rs from (select a.*,b.xh lcxh from xg_qgzx_xsgwsqb a left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw where a.gwdm=? and a.splc=? and a.shzt='1')where lcxh>=?");
		sb.append(" select count(1) rs from ( ");
		sb.append("    select a.*,b.xh lcxh,nvl(d.zgzt,'zg') zgzt from xg_qgzx_xsgwsqb a ");
		sb.append("    left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw ");
		sb.append("    left join xg_qgzx_xsgwxxb d on a.xh = d.xh and a.gwdm = d.gwdm ");
		sb.append("    where a.gwdm=? and a.splc=? and a.shzt in ('5','1')) ");
		sb.append(" where lcxh>=? and zgzt = 'zg' ");
		String shtgrs = dao.getOneRs(sb.toString(), new String[] { sqgw, splc,
				xh }, "rs");
		Integer shtgrsI = StringUtils.paseStringToInteger(shtgrs);

		// ����ĸ�λ����������ǰ���Ѿ����ж�ѧ���Ƿ��Ѿ��ڸڣ����Բ��ù���ĳѧ����
		StringBuffer gwrs = new StringBuffer();
		gwrs.append("select count(1) rs from xg_qgzx_xsgwxxb where gwdm=? and zgzt = ? and sjly=?");
		String sqgwyyrs = dao.getOneRs(gwrs.toString(), new String[] { sqgw,
				"zg", "0" }, "rs");
		Integer sqgwyyrsI = StringUtils.paseStringToInteger(sqgwyyrs);
		return shtgrsI + sqgwyyrsI;
	}

	/**
	 * 
	 * @����: ��ȡѧ�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-27 ����02:57:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param xh
	 * @param shgw
	 * @return Integer ��������
	 */
	public Integer getTgRs(String splc, String xh, String shgw) {
		// ��ȡ��ǰ��˸�λ�����
		String gwxh = getGwSpXh(splc, shgw);
		// ���ѧ��ͨ��������
		StringBuffer sb = new StringBuffer();
		// sb.append("select count(1) rs from xg_qgzx_xsgwsqb where xh = ? and shgw =?");
		sb.append(" select count(1) rs from (");
		sb.append("    select a.*,b.xh lcxh,nvl(d.zgzt,'zg') zgzt from xg_qgzx_xsgwsqb a ");
		sb.append("    left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw ");
		sb.append("    left join xg_qgzx_gwxxb c on c.gwdm=a.gwdm  ");
		sb.append("    left join xg_qgzx_xsgwxxb d on a.xh = d.xh and a.gwdm = d.gwdm ");
		sb.append("    where c.sfsgwsqsxz='1' and a.xh=? and a.splc=? and a.shzt in ('5','1'))");
		sb.append(" where lcxh>=? and zgzt = 'zg' ");
		String shtgrs = dao.getOneRs(sb.toString(), new String[] { xh, splc,
				gwxh }, "rs");
		// ���ά��ѧ�����ӵ�����
		StringBuffer sbzg = new StringBuffer();
		sbzg.append("select count(1) rs from xg_qgzx_xsgwxxb c where sfsgwsqsxz='1' and xh = ? and zgzt = ? and sjly=?");
		String jgkZjrs = dao.getOneRs(sbzg.toString(), new String[] { xh, "zg",
				"0" }, "rs");
		Integer tgrsI = StringUtils.isNull(shtgrs) ? 0 : Integer
				.parseInt(shtgrs);
		Integer jgkZjrsI = StringUtils.isNull(jgkZjrs) ? 0 : Integer
				.parseInt(jgkZjrs);
		return tgrsI + jgkZjrsI;
	}

	/**
	 * 
	 * @����: ��ȡѧ���˸�λ���ڸڵ�����(��������̿���)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-27 ����03:02:45
	 * @deprecated ��ʱδ����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param kzgw
	 * @param xh
	 * @param gwdm
	 * @return Integer ��������
	 */
	public Integer getXstgxx(String splc, String kzgw, String xh, String gwdm) {
		String gwxh = getGwSpXh(splc, kzgw);
		StringBuffer sb = new StringBuffer();
		// sb.append("select count(1) rs from xg_qgzx_xsgwsqb where xh = ? and shgw =?");
		sb.append("select count(1) rs from (select a.*,b.xh lcxh from xg_qgzx_xsgwsqb a left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw  and a.xh=? and a.splc=? and a.gwdm=?)where lcxh>=?");
		String shtgrs = dao.getOneRs(sb.toString(), new String[] { xh, splc,
				gwxh }, "rs");
		StringBuffer sbzg = new StringBuffer();
		sbzg.append("select count(1) rs from xg_qgzx_xsgwxxb c where  xh = ? and zgzt = ? and gwdm=?");
		String jgkZjrs = dao.getOneRs(sbzg.toString(), new String[] { xh, "zg",
				gwdm }, "rs");
		return StringUtils.paseStringToInteger(shtgrs)
				+ StringUtils.paseStringToInteger(jgkZjrs);
	}

	/**
	 * 
	 * @����: ��ȡѧ���˸�λ���ڸڵ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-27 ����03:02:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param kzgw
	 * @param xh
	 * @param gwdm
	 * @return Integer ��������
	 */
	public Integer getXstgxx(String xh, String gwdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) rs from xg_qgzx_xsgwxxb c where  xh = ? and zgzt = ? and gwdm=?");
		String jgkZjrs = dao.getOneRs(sb.toString(), new String[] { xh, "zg",
				gwdm }, "rs");
		return StringUtils.paseStringToInteger(jgkZjrs);
	}

	/**
	 * 
	 * @����:������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-13 ����03:12:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shgw
	 *            �������Ʊ�־
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean resetRsKz(String shgw) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update XG_QGZX_XSGWSQB set shgw=?");
		return dao.update(sb.toString(), new String[] { shgw }) > 0 ? true
				: false;
	}

	/**
	 * 
	 * @����: ��ȡ��������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-13 ����10:17:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwid
	 * @return String ��������
	 */
	public String getZcSjts(String gwid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select rownum as rs from XG_QGZX_XSGWSQB where shgw=?");
		return dao.getOneRs(sb.toString(), new String[] { gwid }, "rs");
	}

	public String getShtgRs(String ywid, String gwid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(ywid) rs from (");
		sb.append("select t.*,row_number() over (partition by t.ywid order by t.ywid,t.shsj desc ) as hh from xg_xtwh_shztb t");
		sb.append(" where t.ywid=? and t.gwid=?");
		sb.append(") where hh=1  and shzt='1'");
		return dao.getOneRs(sb.toString(), new String[] { ywid, gwid }, "rs");
	}

	/**
	 * 
	 * @����:��ȡ������λ��˳��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-12 ����05:01:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param spgw
	 * @return String ��������
	 */
	public String getGwSpXh(String splc, String spgw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select xh from xg_xtwh_spbz where splc=? and spgw=?");
		return dao.getOneRs(sb.toString(), new String[] { splc, spgw }, "xh");
	}

	/*
	 * ����:�ҵĸ�λ���� ��ҳ��ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(WdgwsqForm model, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append(" select t.sqbh,t.xh,t2.xm,t2.xydm,t2.pycc,t2.zybjmc,t2.zybj,t2.bjmc,t2.bjdm,t2.sydm1 sydm,t2.symc1 symc");
		sql.append(" ,t5.pyccmc");
		sql.append(" ,t1.gwmc,t1.gwdm,t4.id yrdwdm,nvl(t4.yrdwmc,t3.bmmc) yrdwmc,t.sqsj,t.shzt,t.splc");
		sql.append(" ,decode(t.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', t.shzt) shztmc");
		sql.append(" from XG_QGZX_XSGWSQB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t4 on t1.yrdwid = t4.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t4.xydm = t3.bmdm ");
		sql.append(" left join view_xsjbxx t2 on t.xh = t2.xh");
		sql.append(" left join XG_XSXX_PYCCDMB t5 on t2.pycc = t5.pyccdm");
		sql.append(" ) a where 1=1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ʦ���ڹ���ѧ��λ�����ѯ�ڹ���ѧ�ʸ����ѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-29 ����03:48:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxStuList(WdgwsqForm model, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where a.xh in(");
		sql.append("select xh from xg_qgzx_qgzxxsb)");
		sql.append(" and 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * ����:
	 */
	protected void setTableInfo() {
		super.setTableName("xg_qgzx_xsgwsqb");
		super.setKey("sqbh");
		super.setClass(WdgwsqForm.class);
	}

	@Override
	public WdgwsqForm getModel(WdgwsqForm model) throws Exception {
		String sqly = null;
		String sqsj = null;
		String xh = null;
		String shzt = null;
		String sffcap = null;
		String sfzqscy = null;
		String sqbh=null;
		String gwdm = model.getGwdm();
		if (StringUtils.isNotNull(model.getSqbh())) {
			String[] sqbArr = dao.getOneRs(
					"select sqly,sqsj,sffcap,sfzqscy,xh,shzt,gwdm,sqbh from xg_qgzx_xsgwsqb where sqbh=?",
					new String[] { model.getSqbh() }, new String[] {"sqly", "sqsj", "sffcap", "sfzqscy", "xh", "shzt", "gwdm"});
			sqly = sqbArr[0];
			sqsj = sqbArr[1];
			sffcap = sqbArr[2];
			sfzqscy = sqbArr[3];
			xh = sqbArr[4];
			shzt = sqbArr[5];
			gwdm = sqbArr[6];
			sqbh=model.getSqbh();
			
		}
		StringBuffer sql = new StringBuffer();

		sql.append("select t.*,t3.fid uploadid,t1.gwxzmc,t2.yrdwmc from XG_QGZX_GWXXB t left join XG_QGZX_GWXZDMB t1 on t.gwlb = t1.gwxzdm ");
		sql.append(" left join xg_comm_fileupload_data t3 on t.fjid=t3.gid ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t.yrdwid = t2.id where t.gwdm = ?");

		model = super.getModel(sql.toString(), new String[] { gwdm });
		model.setSqly(sqly);
		model.setSqsj(sqsj);
		model.setXh(xh);
		model.setShzt(shzt);
		model.setSffcap(sffcap);
		model.setSfzqscy(sfzqscy);
		model.setGwdm(gwdm);
		model.setSqbh(sqbh);
//		model.setGwcjbz(gwcjbz);
//		model.setJfhb(jfhb);
//		model.setZc(zc);
		return model;
	}

	/**
	 * @����:TODO��ȡ��������ID��У����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-17 ����06:23:20
	 * @return String ��������
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xsgwsqsplc splc from xg_qgzx_csszb b ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	/**
	 * @����:TODO��ȡԺ����������ID
	 * @���ߣ�lgx(1553)
	 * @���ڣ�2018-5-2 ����01:54:20
	 * @return String ��������
	 */
	public String getYjShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xsyjgwsqsplc yjsplc from xg_qgzx_csszb b ");
		return dao.getOneRs(sql.toString(), new String[] {}, "yjsplc");
	}

	
	
	/**
	 * 
	 * @����:��ȡ�û���Ӧ��λ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-23 ����05:21:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @param gwdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGwdm(String userName, String gwdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select spgw from xg_xtwh_spgwyh where spyh=? and spgw=?");
		return dao
				.getListNotOut(sb.toString(), new String[] { userName, gwdm });
	}

	/**
	 * @����:TODO��ȡ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����03:20:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	public HashMap<String, String> getCsszb() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_qgzx_csszb b ");
		return dao.getMapNotOut(sql.toString(), new String[] {});
	}

	/**
	 * @����:TODO��ȡ�Ѿ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����03:52:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getSqCount(String xh) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) sl from xg_qgzx_xsgwsqb  c where c.shzt in(?,?) and c.xh = '"
						+ xh + "'");
		String sl = dao.getOneRs(sql.toString(), new String[] {
				Constants.YW_TG, Constants.YW_SHZ }, "sl");
		Integer slI = StringUtils.isNull(sl) ? 0 : Integer.parseInt(sl);
		return slI;
	}

	/**
	 * @����:TODO��ȡ�Ѿ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����03:52:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * @throws SQLException int ��������
	 * 
	 * 10��24�ո��£������ж������λ�������Ƿ�һ�¡�
	 * �����λ����ֻ���Ƶ�ǰ�����λ��ѧ���Ƿ��������룻
	 * 1.ͨ������ͨ��������� ���������������
	 * 2.δ�ύ���˻ض��������������
	 * 
	 */
	public int getSqCount2(String xh, String xn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) sl from xg_qgzx_xsgwsqb c left join xg_qgzx_gwxxb b on c.gwdm=b.gwdm where c.shzt in(?,?,?) and nvl(c.shgw,'0')!='-1' and b.xn = ? and c.xh = ? ");
		String sl=dao.getOneRs(sql.toString(), new String[]{Constants.YW_SHZ,Constants.YW_TG,Constants.YW_BTG,xn,xh}, "sl");
		Integer slI=StringUtils.isNull(sl)?0:Integer.parseInt(sl);
		return slI;
	}

	/**
	 * ��ȡ�ø�λѧ��������
	 * ͨ��������� ���������������
	 * @param gwdm
	 * @param xn
	 * @return
	 */
	public int getXsCount(String gwdm,String xn){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) sl from xg_qgzx_xsgwsqb c left join xg_qgzx_gwxxb b on c.gwdm=b.gwdm where c.shzt in (?,?) and nvl(c.shgw,'0')!='-1' and b.xn = ? and c.gwdm = ? ");
		String sl = dao.getOneRs(sql.toString(), new String[]{Constants.YW_SHZ,Constants.YW_TG,xn,gwdm}, "sl");
		Integer slI=StringUtils.isNull(sl)?0:Integer.parseInt(sl);
		return slI;
	}
	/**
	 * @����:��ȡѧ����λ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����11:13:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXszggwsl(String gwdm, String xh) throws SQLException {
		String sql = " select count(1)sl from xg_qgzx_xsgwxxb a where a.gwdm = '"
				+ gwdm + "' and xh='" + xh + "' and a.zgzt='zg' ";
		return dao.getOneRsint(sql);
	}

	/**
	 * @����:��ȡѧ���ڸ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����11:13:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXszgsl(String xh) throws SQLException {
		String sql = " select count(1) sl from xg_qgzx_new_xsgwxxb a where xh='"
				+ xh + "' and a.zgzt='zg' ";
		return dao.getOneRsint(sql);
	}

	//��ѯѧ���Ƿ���ҵ����
	public boolean isHmdXs(String xh,String gwdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_QGZX_NEW_HMDB t");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdw = t1.id ");
		sql.append(" left join XG_QGZX_GWXXB t2 on t2.yrdwid = t1.id ");
		sql.append(" where t.xh = ? and t2.gwdm = ?");
		String num = dao.getOneRs(sql.toString(),new String[]{xh,gwdm},"num");
		return !"0".equals(num);
	}
	/**
	 * �ж�ѧ���ڸڻ���������ĸ�λ�Ƿ�����ʽ��
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getZgGwxxz(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t1.gwxzdm from xg_qgzx_xsgwsqb t ");
		sql.append(" left join xg_qgzx_gwxxb t1 on t.gwdm = t1.gwdm");
		sql.append(" where t.xh =? and (t.shzt = '1' or t.shzt = '5')");
		sql.append(" order by t1.gwxzdm desc");
		return dao.getList(sql.toString(),new String[]{xh},new String[]{"gwxzdm"});
	}
	/**
	 * @����:ѧ���Ƿ��ڴ˸�λ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����03:18:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXssqsl(String gwdm, String xh) throws SQLException {
		String sql = " select count(1)sqsl from xg_qgzx_xsgwsqb a where a.gwdm = '"
				+ gwdm + "' and xh='" + xh + "'  and a.shzt =? ";
		String sl = dao.getOneRs(sql.toString(),
				new String[] { Constants.YW_SHZ }, "sqsl");
		Integer slI = StringUtils.isNull(sl) ? 0 : Integer.parseInt(sl);
		return slI;
	}

	/*
	 * ����:
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WdgwsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * @����:���ݸ�λ�����ȡ��λ���������������������ڸ����������ڸ���������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����10:43:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> ��������
	 */
	public HashMap<String, String> getGwxx(String gwdm) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.xqrs,c.sfsgwsqsxz,nvl(c.knsrs,0) knsrs,count(distinct a.xh)gwrs,count(distinct b.xh)zgknss  from xg_qgzx_gwxxb c  left join xg_qgzx_xsgwxxb a on c.gwdm = a.gwdm and a.zgzt ='zg' left join xg_xszz_new_knsjgb b on a.xh =b.xh and c.xn = b.xn where c.gwdm =?    group by c.xqrs,c.sfsgwsqsxz,c.knsrs ");
		return dao.getMapNotOut(sql.toString(), new String[] { gwdm });
	}

	/**
	 * @����:�Ƿ���������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����03:02:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws Exception 
	 * @throws SQLException int ��������
	 */
	public int isKns(String gwdm, String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		if("10335".equals(Base.xxdm)){
			sql.append(" select  count(1) num from view_knsjgb_fqxrd e where e.xh = ? ");
			params.add(xh);
		}else{
		sql.append(" select  count(1) num from xg_xszz_new_knsjgb e where e.xh = ? ");
		sql.append(" and e.xn = ? ");
		params.add(xh);
		params.add(Base.currXn);
		if ("xq".equals(SQZQ)) {
			sql.append(" and e.xq = ? ");
			params.add(Base.currXq);
			}
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		return Integer.parseInt(num);
//		String sql = "  select  count(1) sl from xg_xszz_new_knsjgb e where e.xh = '"
//				+ xh
//				+ "' and  xn =(select b.xn from xg_qgzx_gwxxb b where b.gwdm='"
//				+ gwdm + "')";
//		return dao.getOneRsint(sql);
	}

	/**
	 * @����: ѧ����λ����ɾ��
	 * @���ߣ�HongLin
	 * @���ڣ�2014-1-17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return int
	 * @throws SQLException int ��������
	 */
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0) {
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}

		StringBuilder sql = new StringBuilder();

		sql.append("delete from xg_qgzx_xsgwsqb");
		sql.append(" where (shzt='0' or shzt='3') and ");
		sql.append("(");

		for (int i = 0, n = values.length; i < n; i++) {
			sql.append("sqbh=?");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}

		sql.append(")");
		int delNum = dao.runDelete(sql.toString(), values);
		if (0 != delNum) {
			// ɾ����˼�¼
			SqshDao sqshDao = new SqshDao();
			sqshDao.delShzt(values);
		}
		return delNum;
	}

	/**
	 * 
	 * @����:TODO�ж�ѧ���Ƿ����ڹ���ѧѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-29 ����05:09:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public HashMap<String, String> isQgzxStu(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_qgzx_qgzxxsb where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});

	}
	/**
	 * 
	 * @����:��ѯѧ��������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-26 ����04:33:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQsxx(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xqmc,yqmc,lddm,ldmc,qsh,qsdh,cwh,ch from xg_view_gygl_new_xszsgl  where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});

	}
	/**
	 * 
	 * @����:��ȡƶ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-25 ����02:27:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPkxjb(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		if("10335".equals(Base.xxdm)){
			sql.append(" select t1.xn,t1.xq,t1.xqmc,t1.sqsj,t1.rddc,t1.dcmc,t1.guid ");
			sql.append(" from view_knsjgb_fqxrd t1 ");
		}else{
			sql.append(" select t1.xn,t1.xq,t2.xqmc,t1.sqsj,t1.rddc,t3.dcmc,t1.guid ");
			sql.append(" from xg_xszz_new_knsjgb t1 ");
			sql.append(" left join xqdzb t2 on t1.xq=t2.xqdm ");
			sql.append(" left join xg_xszz_new_kndcdmb t3 on t1.rddc=t3.dcdm ");
		}
		sql.append("where t1.xh=?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "dcmc");
	}
	
	/**
	 * 
	 * ���ݸ�λ�����ȡ��λ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-10-25 ����11:42:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getGwInfo(String gwdm){
		
		String sql = " select * from xg_qgzx_gwxxb where gwdm = ? "	;
		
		return dao.getMapNotOut(sql, new String []{gwdm});
	}
	
	/**
	 * 
	 * @����:ɾ���ڹ���ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����04:02:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delQgmx(String id) throws Exception{
		String sql = "delete from XG_QGZX_QGSQMXB where xh=?";
		return dao.runUpdate(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @����:�����ڹ���ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����04:08:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQgmx(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_qgzx_qgsqmxb(xh,qgrq,qgmx,px) values (?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @����:�ڹ���ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����04:40:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		String sql = "select * from XG_QGZX_QGSQMXB where xh=? order by to_number(px)";
		
		return dao.getListNotOut(sql, new String[]{id});
	}
	
	/** 
	 * �ڹ���ϸ(��ӡ��ר��)
	 */
	public HashMap<String,String> getQgmxByQgrqQgmx(String[] qgrqArr, String[] qgmxArr, String xh){
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlRs = new StringBuilder();
		sql.append(" select ");
		for (int i = 0; i < qgrqArr.length; i++) {
			String qgrq = qgrqArr[i];
			for (int j = 0; j < qgmxArr.length; j++) {
				String qgmx = qgmxArr[j];
				sql.append(" case when qgmxtemp"+i+""+j+" > 0 then '��' else '' end qgmx"+i+""+j+" ");
				sqlRs.append(" sum(case when instr(qgrq,'"+qgrq+"') > 0 then (case when instr(qgmx,'"+qgmx+"') > 0 then 1 else 0 end) else 0 end) qgmxtemp"+i+""+j+" ");
				if(j < qgmxArr.length - 1){
					sql.append(", ");
					sqlRs.append(", ");
				}
			}
			if(i < qgrqArr.length - 1){
				sql.append(", ");
				sqlRs.append(", ");
			}
		}
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(sqlRs);
		sql.append(" from XG_QGZX_QGSQMXB where xh=? ");
		sql.append(" ) a ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/** 
	 * @����:����ͬһʱ�����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-3 ����01:45:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean getExist(WdgwsqForm model) throws Exception {
		ArrayList<String> paraList = new ArrayList<String>();
		String sql = "select count(1) count from xg_qgzx_xsgwsqb where xh = ? and gwdm = ?";
		paraList.add(model.getXh());
		paraList.add(model.getGwdm());
		String sqbh = model.getSqbh();
		if(StringUtils.isNotNull(model.getSqbh())){
			sql += " and sqbh != ?";
			paraList.add(sqbh);
		}
		String count = dao.getOneRs(sql, paraList.toArray(new String[]{}), "count");
		
		return Integer.parseInt(count)>0 ? true:false;
	}

	
	/******copy from mobile*********/
	public List<HashMap<String, String>> getGwWsqPageList(WdgwsqForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.sqbh,b.xn,b.yrdwmc,b.gwmc,b.gwxzmc,b.xqrs,b.zgrs,b.knsrs knss,b.gwdm,b.sqrs from  (select * from VIEW_XG_QGZX_GWXXB where sfyxgw = '��') b left join (select * from xg_qgzx_xsgwsqb where xh='"+user.getUserName()+"') c on b.gwdm = c.gwdm where 1=1 ");
		// sql.append(searchTjByUser);
		// �����ѧ���û� ����Ĭ������
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			sql.append(" and b.xn = '" + Base.currXn + "'");
			sql.append(" and b.gwdm not in(select gwdm from xg_qgzx_xsgwsqb where xh='"+user.getUserName()+"' and shzt !='0')");
			sql.append(" ");
		}
		if(StringUtils.isNotNull(model.getYrdwdm())){//��������˵�λ���룬����Ϊ�ǵ���ʹ�ã��Ӳ�ѯ���˵�λ�ĸ�λ
			sql.append(" and b.yrdwdm='");
			sql.append(model.getYrdwdm());
			sql.append("'");
		}
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ��ѧ�������ӡѧ��������Ϣ����λ��Ϣ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����10:00:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBmbxx(String sqbh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.zymc,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xh,");
		sql.append(" t4.gwmc,");
		sql.append(" t.sffcap,");
		sql.append(" t.sfzqscy,");
		sql.append(" t1.zzmmmc,");
		sql.append(" t1.sjhm lxdh,");
		sql.append(" t5.ldmc || '-' || t5.qsh qsh,");
		sql.append(" case when t2.xh is null then '��'");
		sql.append(" else '��' end sfkns,");
		sql.append(" t3.dcmc,");
		sql.append(" t.yhtc,");
		sql.append(" t.jtqk,");
		sql.append(" t.qgzxrs");
		sql.append(" from xg_qgzx_xsgwsqb t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join xg_qgzx_gwxxb t4");
		sql.append(" on t.gwdm = t4.gwdm");
		sql.append(" left join xg_xszz_new_knsjgb t2");
		sql.append(" on (t1.xh = t2.xh and t4.xn = t2.xn)");
		sql.append(" left join xg_xszz_new_kndcdmb t3");
		sql.append(" on t2.rddc = t3.dcdm");
		sql.append(" left join VIEW_XG_GYGL_NEW_CWXX t5");
		sql.append(" on t.xh = t5.xh");
		sql.append(" where t.sqbh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqbh});
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ�ڹ��������ӡ-������ϸ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����11:47:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKxsjMx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select qgrq,");
		sql.append(" case when (t.qgmx like '%1%' or  t.qgmx like '%2%' or  t.qgmx like '%3%' or  t.qgmx like '%4%')");
		sql.append(" then '1'  else '0'  end sw,");
		sql.append(" case when (t.qgmx like '%5%' or  t.qgmx like '%6%' or  t.qgmx like '%7%' )");
		sql.append(" then '1'  else '0'  end xw,");
		sql.append(" case when  (t.qgmx like '%8%' or  t.qgmx like '%9%' )");
		sql.append(" then '1'  else '0'  end ws");
		sql.append(" from XG_QGZX_QGSQMXB t where xh = ? order by to_number(px)");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/** 
	 * @����:��ȡ����ѧԺ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-18 ����01:44:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXyms(WdgwsqForm form) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select replace(wm_concat(c.bmmc), ';', ',') mc,a.gwdm");
		sb.append(" from xg_qgzx_gwxxb a");
		sb.append(" left join xg_qgzx_gwxxsqxyglb b on a.gwdm = b.gwdm");
		sb.append(" left join zxbz_xxbmdm c on b.xydm = c.bmdm");
		sb.append(" where a.gwdm = ? group by a.gwdm");
		return dao.getOneRs(sb.toString(), new String[]{form.getGwdm()}, "mc");
	}
	
	/** 
	 * @����:�ж��Ƿ��ѧ���˸��Ƿ����һ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����11:14:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isTgInOneYear(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_qgzx_new_xsgwxxb where xh = ? and zgzt = 'tg'");
		sql.append(" and to_date(to_char(add_months(to_date(tgsj,'yyyy-MM-dd'),12),'yyyy-MM-dd'),'yyyy-MM-dd')");
		sql.append(" >");
		sql.append(" to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')");
		String num = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		return Integer.valueOf(num)>0;
	}
	//���ݸ�λ�����ȡ���˵�λ���
	public String getYrdwlb(String gwdm){
		String sql = "select a.dwlb dwlb from XG_QGZX_YRDWDMB a left join XG_QGZX_GWXXB b on b.yrdwid=a.id where b.gwdm=?";
		return dao.getOneRs(sql, new String[]{gwdm}, "dwlb");
	}

	public List<HashMap<String, String>> getQglsjl(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.gwmc,nvl(t3.yrdwmc,t4.bmmc) yrdwmc,t2.sqsj,t.sgsj,");
		sql.append(" case when t.zgzt='zg' then '�ڸ�' else '�˸�' end zgzt ");
		sql.append(" from XG_QGZX_NEW_XSGWXXB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t1.yrdwid = t3.id");
		sql.append(" left join ZXBZ_XXBMDM t4 on t3.xydm = t4.bmdm ");
		sql.append(" left join XG_QGZX_XSGWSQB t2 on t.sqbh = t2.sqbh ");
		sql.append(" where t.xh=? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
}
