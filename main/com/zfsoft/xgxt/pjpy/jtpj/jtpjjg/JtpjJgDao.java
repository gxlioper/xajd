/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����10:34:53 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-5 ����10:34:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjJgDao extends SuperDAOImplExtend<JtpjJgForm> {
	@Override
	protected void setTableInfo() {
		setKey("jgid");
		setTableName("xg_pjpy_jtpj_jtpjjgb");
		setClass(JtpjJgForm.class);
	}

	/**
	 * 
	 * @����: ��ȡ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����10:50:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return HashMap<String,String> ��������
	 */
	public HashMap<String, String> getJgModel(String jgid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_pjpy_jtpj_jtpjjgb where jgid=?");
		return dao.getMapNotOut(sb.toString(), new String[] { jgid });
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JtpjJgForm t)
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
	public List<HashMap<String, String>> getPageList(JtpjJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select t1.*,t2.sqsj from (");
		sql.append(" select * from (");
		sql.append(" SELECT D.JGID,");
		sql.append(" d.xydm,d.zydm,d.bjdm,");
		sql.append(" D.JXID,");
		sql.append(" D.SQXN,");
		sql.append(" D.SQXQ,");
		sql.append(" D.PDXN,");
		sql.append(" D.PDXQ,");
		sql.append(" D.JTPJDW,");
		sql.append(" D.PJJTID,");
		sql.append(" D.PJJTMC,");
		sql.append(" D.JTJJ,");
		sql.append(" nvl(D.RS,D.ZRS) RS,");
		sql.append(" D.SQRYLX,");
		sql.append(" D.SQR,");
		sql.append(" D.SQRXM,");
		sql.append(" D.SJLY,");
		sql.append(" D.SQID,");
		sql.append(" D.SQLY,");
		if("10704".equals(Base.xxdm)){//�����Ƽ���ѧ���Ի�
			sql.append(" D.RDFS,");
		}
		sql.append(" N.JXMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = N.PDXQ) PDXQMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = D.SQXQ) SQXQMC");
		sql.append(" from (");
		sql.append(" (select a.*, b.xydm, b.zydm, b.bjdm, m.zrs");
		sql.append("  from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("  left join view_njxyzybj_all b");
		sql.append("    on a.pjjtid = b.bjdm");
		sql.append("  left join (select count(xh) zrs,bjdm from view_xsbfxx a group by bjdm) m");
		sql.append("    on a.pjjtid = m.bjdm");
		sql.append("   where  a.jtpjdw = 'bj' ");

		// �û����
		String userStatus = user.getUserStatus();

		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �����û�ΪѧԺ
		if ("xy".equalsIgnoreCase(userStatus)) {
				sql.append(" and b.xydm = '" + userDep + "' ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from fdybjb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// �����û�Ϊ����Ա�������
			sql.append(" and (exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");
			
			sql.append(" or exists (select 1 from fdybjb z where ");
			sql.append(" b.bjdm =  z.bjdm ");
			sql.append(" and z.zgh = '" + userName + "')) ");

		}
		
		sql.append(" ) ");		
		
		sql.append(" union all");
		sql.append(" (select a.*,c.xydm, '' zydm, '' bjdm, m.zrs");
		sql.append("   from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("   left join (select distinct xydm from view_njxyzybj_all) c");
		sql.append("     on a.pjjtid = c.xydm");
		sql.append("  left join (select count(xh) zrs,xydm from view_xsbfxx a group by xydm) m");
		sql.append("    on a.pjjtid = m.xydm");
		sql.append("    where a.jtpjdw = 'xy' ");

		// �����û�ΪѧԺ/������/����Ա/����
		if ("xy".equalsIgnoreCase(userStatus) || "bzr".equalsIgnoreCase(userStatus) 
				|| "fdy".equalsIgnoreCase(userStatus) || "jd".equalsIgnoreCase(userStatus)) {
			sql.append(" and c.xydm = '" + userDep + "' ");
		} 
		
		sql.append(" ) ");		

		sql.append(") D");
		sql.append(" LEFT JOIN XG_PJPY_JTPJ_JTJXSZ N");
		sql.append(" ON D.JXID = N.JXID");
		sql.append(")a where 1 = 1");
		sql.append(" union all ");
		sql.append(getQtSql(t, user));
		sql.append(") t1 left join xg_pjpy_jtpj_jtjxsqb t2 on t1.sqid=t2.sqid) t1 where 1=1");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����: ��ȡ����sql��ѯ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-23 ����09:21:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	private String getQtSql(JtpjJgForm t, User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" SELECT D.JGID,");
		sql.append(" '' xydm,'' zydm,'' bjdm,");
		sql.append(" D.JXID,");
		sql.append(" D.SQXN,");
		sql.append(" D.SQXQ,");
		sql.append(" D.PDXN,");
		sql.append(" D.PDXQ,");
		sql.append(" D.JTPJDW,");
		sql.append(" D.PJJTID,");
		sql.append(" D.PJJTMC,");
		sql.append(" D.JTJJ,");
		sql.append(" D.RS,");
		sql.append(" D.SQRYLX,");
		sql.append(" D.SQR,");
		sql.append(" D.SQRXM,");
		sql.append(" D.SJLY,");
		sql.append(" D.SQID,");
		sql.append(" D.SQLY,");
		if("10704".equals(Base.xxdm)){//�����Ƽ���ѧ���Ի�
			sql.append(" D.RDFS,");
		}
		sql.append(" N.JXMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = N.PDXQ) PDXQMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = D.SQXQ) SQXQMC");
		sql.append(" from (");
		sql.append(" (select a.*, '' xydm, '' zydm, '' bjdm");
		sql.append("   from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("    where a.jtpjdw = 'qt' or a.jtpjdw = 'qs')");
		sql.append(") D");
		sql.append(" LEFT JOIN XG_PJPY_JTPJ_JTJXSZ N");
		sql.append(" ON D.JXID = N.JXID");
		sql.append(")a");
		return sql.toString();
	}

	/**
	 * 
	 * @����: ��������idɾ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����03:15:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean deleteJgForSqid(String sqid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_pjpy_jtpj_jtpjjgb where sqid=?");
		return dao.runDelete(sb.toString(), new String[] { sqid }) >= 0;
	}
	
	/**
	 * @����: ��ӡWord�ǼǱ��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.jgid,a.sqxn, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc ,a.rs, b.zymc from xg_pjpy_jtpj_jtpjjgb a ");		
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}
	
	public HashMap<String, String> getDjb(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc, c.mbmc from XG_PJPY_JTPJ_JTPJJGB a ");
		sql.append(" left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}

	public HashMap<String, String> gethbgydxDjb(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append("	select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc,(select count(*) from view_xsxxb y where y.BJDM = a.pjjtid)as bjrs , ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc ");
		sql.append(" ,( select x.xm from VIEW_NEW_DC_SZDW_XSDWWH x where x.BJDM= a.pjjtid and x.ZWMC='�೤')as bzmc, c.mbmc,decode(a.jtpjdw,'bj',c.xm,'') bzrxm, t3.xm as fdyxm from XG_PJPY_JTPJ_JTPJJGB a  ");
		sql.append("	left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid  ");
		sql.append("	left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm  ");
		sql.append("	left join (select t1.*,t2.xm from  fdybjb t1  left join fdyxxb t2 on t1.zgh = t2.zgh) t3 on a.pjjtid = t3.bjdm  ");
		sql.append("	left join (  ");
		sql.append("			select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm   ");
		sql.append("	 ) c on a.pjjtid=c.bjdm  ");
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}


	//ɽ��������ҽ�Ƚ��༯���Ƽ����ð༶��Ϣlist
	public List<HashMap<String, String>> getBzrxxlist(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t.nj,t.xydm,t.xymc,t.zydm,t.zymc,t.bjdm,t.bjmc," +
				"(select rs from xg_pjpy_jtpj_jtpjjgb where pjjtid = t.bjdm) rs," +
				"(select xm from BZRTXLB a where a.bjdm = t.bjdm) xm  from view_njxyzybj_all t " +
				"where t.bjdm in(");
		sql.append("select pjjtid from xg_pjpy_jtpj_jtpjjgb where jgid in ( ");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//ɽ��������ҽ�Ƚ��༯�����������ѧ����
	public HashMap<String, String> getRxrq(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum, rxrq from xsxxb where bjdm = (select pjjtid from xg_pjpy_jtpj_jtpjjgb where jgid = ?) and rownum = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}
	
	/**
	 * @��������ȡ�༶��Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��7�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String, String> getBjxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select m.*,n.bzrxms from VIEW_NJXYZYBJ m left join (select a.bjdm, WM_CONCAT(b.xm) bzrxms from bzrbbb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm )n on m.bjdm=n.bjdm where m.bjdm= ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @��������ȡѧԺ��Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��7�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String, String> getXyxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xymc,xydm from VIEW_NJXYZYBJ where xydm= ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	public boolean isBjjx(String jxid){
		String sql = "select jtpjdw from xg_pjpy_jtpj_jtjxsz where jxid = ?";
		return dao.getOneRs(sql, new String[]{jxid}, "jtpjdw").equalsIgnoreCase("bj")?true:false;
	}
	/**
	 * @��������ȡ����Ա��Ϣ
	 * @���ߣ�����[����:1529]
	 * @���ڣ�2017��11��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String, String> getfdyxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("  select b.xm as fdyxm, b.lxdh as lxdh from fdyxxb b left join (select a.zgh, c.bjdm from bzrbbb a " +
				" left join VIEW_NJXYZYBJ c  on a.bjdm = c.bjdm) n on b.zgh = n.zgh where n.bjdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
}
