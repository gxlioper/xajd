package xgxt.pjpy.zjcm.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class PjpyZjcmCsszDAO {

	DAO dao = DAO.getInstance();
	
	private final static String BZ_XY = "xy";//ѧԺ�������
	private final static String BZ_ZY = "zy";//רҵ�������
	private final static String BZ_BJ = "bj";//�༶�������
	
	private ArrayList<String> values = new ArrayList<String>(); 
	
	//��ѯ��ѧ�����SQL
	private final StringBuffer QUERY_JXJDM_SQL = new StringBuffer("select jxjdm")
	                                  .append(" dm,jxjmc mc from jxjdmb a where not exists (select 1 from jxjlbdmb b where jxjlbmc like 'У��%' and a.jxjlb=b.jxjlbdm) ");
	
	//��ѯ�����ƺŴ���SQL
	private final StringBuffer QUERY_RYCHDM_SQL = new StringBuffer("select rychdm dm,")
	                                  .append("rychmc mc from rychdmb order by rychdm");
	
	//��ѯ��ѧ��������SQL
	private final StringBuffer QUERY_JXJLB_SQL = new StringBuffer("select jxjlbdm dm,")
	                                  .append("jxjlbmc mc from jxjlbdmb where jxjlbmc not like 'У��%' order by jxjlbdm");
	
	//��ѯ��ѧ�������������
	private final StringBuffer QUERY_JXJRS_SQL = new StringBuffer("select pk,xn,xq,nvl")
	                      .append("(case when instr(to_char(jsrs),'.',1,1)=1 then '0'||")
	                      .append("to_char(jsrs) else to_char(jsrs) end, '0') jsrs")
						  .append(",xqmc,bmlb,nd,bmdm,bmmc,jxjdm,jxjlb,mc,jxjlbmc,cprs,")
						  .append("jxjbl,jxjrs,nj,rownum r,key from (select xn||bmdm||nd||")
						  .append("jxjdm||xqdm||key||nj pk,xn,xqdm xq,(select xqmc from")
						  .append(" xqdzb b where a.xqdm=b.xqdm) xqmc,bmlb")
						  .append(",round((to_number(cprs)*to_number(jxjbl)/100),2) jsrs")
	                      .append(",nd,bmdm,(case when bmlb='xydm' then (select xymc from ")
	                      .append("view_njxyzybj b where a.bmdm=b.xydm and rownum=1) ")
	                      .append("when bmlb='zydm' then (select zymc from view_njxyzybj")
	                      .append(" b where a.bmdm=b.zydm and rownum=1) when bmlb='bjdm'")
	                      .append(" then (select bjmc from view_njxyzybj b where a.bmdm")
	                      .append("=b.bjdm and rownum=1) else '' end) bmmc,jxjdm,(select")
	                      .append(" jxjlb from jxjdmb b where a.jxjdm=b.jxjdm) jxjlb,")
	                      .append("(select mc from jxjlbxxdmb where dm=(select")
	                      .append(" jxjlb from jxjdmb b where a.jxjdm=b.jxjdm)) jxjlbmc,")
	                      .append("(case when key='jxj' then (select jxjmc from jxjdmb b")
	                      .append(" where a.jxjdm=b.jxjdm) when key='rych' then (select")
	                      .append(" rychmc from rychdmb b where a.jxjdm=b.rychdm)")
	                      .append(" when key='zhcpjxj' then (select jxjmc from ")
	                      .append("zhcpjxjdmb b where a.jxjdm=b.jxjdm) else ''")
	                      .append(" end) mc,cprs,jxjbl,jxjrs,nj,key from xyjxjrs a) a");
	
	//�޸Ľ�ѧ���������SQL
	private final StringBuffer UPDATE_JXJRS_SQL = new StringBuffer("update xyjxjrs set ")
	                      .append("jxjbl=?,cpfw=?,jxjrs=(case when round(round((cprs*?/100),1")
	                      .append("))<1 then 1 else round(round((cprs*?/100),1)) end) ")
	                      .append("where key=? and xn=? and xqdm=? and nd=? and jxjdm=?")
	                      .append(" and bmlb=?||'dm'");
	
	//��ѯ��ѧ�𵥸�����������Ϣ
	private final StringBuffer QUERY_JXJDGRS_SQL = new StringBuffer("select a.*,(nvl(case")
	                      .append(" when instr(to_char(jsrs),'.',1,1)=1 then '0'||to_ch")
	                      .append("ar(jsrs) else to_char(jsrs) end, '0')) rs from (select")
	                      .append(" xn,nd,(round((to_number(cprs)*to_number(jxjbl)/100),")
	                      .append("2)) jsrs,(select xqmc from xqdzb b where a.xqdm=b.xq")
	                      .append("dm) xqmc,cprs,jxjbl,jxjrs,nj,cpfw,(case when bmlb='xy")
	                      .append("dm' then (select xymc from view_njxyzybj b where rown")
	                      .append("um=1 and a.bmdm=b.xydm) when bmlb='zydm' then (select")
	                      .append(" zymc from view_njxyzybj b where rownum=1 and a.bmdm=")
	                      .append("b.zydm) when bmlb='bjdm' then (select bjmc from view")
	                      .append("_njxyzybj b where rownum=1 and a.bmdm=b.bjdm) else ''")
	                      .append(" end) bmmc,(select jxjmc from jxjdmb b where a.jxj")
	                      .append("dm=b.jxjdm) jxjmc,jxjdm,(select jxjlb from jxjdmb b ")
	                      .append("where a.jxjdm=b.jxjdm) jxjlb from xyjxjrs a ")
	                      .append("where xn||bmdm||nd||jxjdm||xqdm||key||nj=?) a");
	
	//�޸Ľ�ѧ�𵥸�����������Ϣ
	private final StringBuffer UPDATE_JXJDGRS_SQL = new StringBuffer("update xyjxjrs set")
	                      .append(" jxjrs = ? where xn||bmdm||nd||jxjdm||xqdm||key||nj=?");
	
	//��ѯ��ѧ��,�����ƺű�����ͷ
	private final StringBuilder QUERY_JXJRYCHBL_SQL = new StringBuilder("select ")
	                      .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
	                      .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
	                      .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
	                      .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
	                      .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
	                      .append("xqdm=?) where mc like '%һ�Ƚ�%' and jxjbl is not null and rownum=1) bl1,")
						  .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
						  .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
						  .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
						  .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
						  .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
						  .append("xqdm=?) where mc like '%���Ƚ�%' and jxjbl is not null and rownum=1) bl2,")
						  .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
						  .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
						  .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
						  .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
						  .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
						  .append("xqdm=?) where mc like '%���Ƚ�%' and jxjbl is not null and rownum=1) bl3,")
						  .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
						  .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
						  .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
						  .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
						  .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
						  .append("xqdm=?) where mc like '%��Ṥ������%' and jxjbl is not null and rownum=1) bl4,")
						  .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
						  .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
						  .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
						  .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
						  .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
						  .append("xqdm=?) where mc like '%����ѧ��%' and jxjbl is not null and rownum=1) bl5,")
						  .append("(select jxjbl||'%' bl1 from (select distinct jxjdm,jx")
						  .append("jbl,(case when key='jxj' then (select jxjmc from jxj")
						  .append("dmb b where a.jxjdm=b.jxjdm) when key='rych' then (se")
						  .append("lect rychmc from rychdmb b where a.jxjdm=b.rychdm) el")
						  .append("se '' end) mc from xyjxjrs a where xn=? and nd=? and ")
						  .append("xqdm=?) where mc like '%����ѧ���ɲ�%' and jxjbl is not null and rownum=1) bl6")
						  .append(" from dual");
						
	//��ѯ��ѧ�������ƺű�������
	private StringBuilder QUERY_JXJRYCHBLRS_SQL = new StringBuilder("select distinct a.")
	                      .append("bmdm,b.bjmc,cprs,(select jxjrs from view_zjcm_xyjxjrs")
	                      .append(" c where mc like 'һ�Ƚ�%' and xn=? and nd=? and xqdm=?")
	                      .append(" and a.bmdm=c.bmdm  and bmlb='bjdm' and jxjrs is not ")
	                      .append("null and rownum=1) yd,")
	                      .append("(select jxjrs from view_zjcm_xyjxjrs c where mc like '")
	                      .append("���Ƚ�%' and xn=? and nd=? and xqdm=? and a.bmdm=c.bmdm")
	                      .append("  and bmlb='bjdm' and jxjrs is not null and rownum=1) ed,")
	                      .append("(select jxjrs from view_zjcm_xyjxjrs c where mc like '")
	                      .append("���Ƚ�%' and xn=? and nd=? and xqdm=? and a.bmdm=c.bmdm")
	                      .append("  and bmlb='bjdm' and jxjrs is not null and rownum=1) sd,")
	                      .append("(select jxjrs from view_zjcm_xyjxjrs c where mc like '")
	                      .append("��Ṥ������%' and xn=? and nd=? and xqdm=? and a.bmdm=c.bmdm")
	                      .append("  and bmlb='bjdm' and jxjrs is not null and rownum=1) shgz,")                      
	                      .append("(select jxjrs from view_zjcm_xyjxjrs c where mc like '")
	                      .append("����ѧ��%' and xn=? and nd=? and xqdm=? and a.bmdm=c.bmdm")
	                      .append("  and bmlb='bjdm' and jxjrs is not null and rownum=1) sh ")
						  .append("from xyjxjrs a,view_njxyzybj b where a.bmdm=b.bjdm")
						  .append(" and xn=? and nd=? and xqdm=? and bmlb='bjdm' and exists")
						  .append("(select 1 from view_njxyzybj d where xydm=? and a.bmdm=d.bjdm)");
		
	//��ѯ�Ÿɱ�������
	private StringBuilder QUERY_YGBLRS_SQL = new StringBuilder("select jxjbl from (select")
	                      .append(" jxjbl,(select rychmc from rychdmb b where a.jxjdm=b.")
	                      .append("rychdm) mc from xyjxjrs a where bmdm=? and bmlb='xydm'")
	                      .append(" and key='rych' and xn=? and nd=? and xqdm=? and jxjbl")
	                      .append(" is not null) where mc like '����ѧ���ɲ�%' and rownum=1");
	
	//��ѯ��У�⽱ѧ�����Ľ�ѧ�����
	//private StringBuilder QUERY_JXJDMNOTLIKE_SQL = new StringBuilder("select jxjdm dm,jxjmc mc from jxjdmb a where not exists (select 1 from jxjlbdmb b where jxjlbmc like ? and a.jxjlb=b.jxjlbdm)");
	
	//��Ϊ����ʹ��ͨ�õ�ƴ�����������������ڵ���ƴ��
	public StringBuffer appendWhereSql(PjpyZjcmCsszModel model) {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer(" where 1=1");
		if (StringUtils.isNotNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (StringUtils.isNotNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (StringUtils.isNotNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
		}
		if (StringUtils.isNotNull(model.getJxjlb())) {
			whereSql.append(" and jxjlb = ?");
			values.add(model.getJxjlb());
		}
		if (StringUtils.isNotNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		if (StringUtils.isNotNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (StringUtils.isNotNull(model.getKey())) {
			whereSql.append(" and key = ?");
			values.add(model.getKey());
		}
		
		//����Ҫ��ѧԺ,רҵ,�༶����������װ
		if (BZ_XY.equalsIgnoreCase(model.getFs())) {
			whereSql.append(" and bmlb ='xydm'");
			if (StringUtils.isNotNull(model.getXydm())) {
				whereSql.append(" and bmdm = ?");
				values.add(model.getXydm());
			}
		} else if (BZ_ZY.equalsIgnoreCase(model.getFs())) {
			whereSql.append(" and bmlb='zydm'");
			if (StringUtils.isNull(model.getZydm()) && StringUtils.isNotNull(model.getXydm())) {
				whereSql.append(" and bmdm in (select distinct zydm from view_njxyzybj where xydm=?)");
				values.add(model.getXydm());
			} else if (StringUtils.isNotNull(model.getZydm())) {
				whereSql.append(" and bmdm = ?");
				values.add(model.getZydm());
			}
		} else if (BZ_BJ.equalsIgnoreCase(model.getFs())) {
			whereSql.append(" and bmlb='bjdm'");
			if (StringUtils.isNull(model.getBjdm())
					&& StringUtils.isNull(model.getZydm())
					&& StringUtils.isNotNull(model.getXydm())) {
				whereSql
						.append(" and bmdm in (select distinct bjdm from view_njxyzybj where xydm = ?)");
				values.add(model.getXydm());
			} else if (StringUtils.isNull(model.getBjdm())
					&& StringUtils.isNotNull(model.getZydm())) {
				whereSql
						.append(" and bmdm in (select distinct bjdm from view_njxyzybj where zydm = ?)");
				values.add(model.getZydm());
			} else if (StringUtils.isNotNull(model.getBjdm())) {
				whereSql.append(" and bmdm = ?");
				values.add(model.getBjdm());
			}
		}
		
		return whereSql;
	}
	
	/**
	 * ��ѧ���б�
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		StringBuffer sql = new StringBuffer();
		sql.append(getQUERY_JXJDM_SQL());
		values = new ArrayList<String>();
		if (StringUtils.isNotNull(jxjlb)) {
			sql.append(" and jxjlb=?");
			values.add(jxjlb);
		}
		sql.append(" order by jxjdm");
		return dao.getList(sql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * �����ƺ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getRychList() {
		return dao.getList(getQUERY_RYCHDM_SQL(), new String[] {},
				new String[] { "dm", "mc" });
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList() {
		return dao.getList(getQUERY_JXJLB_SQL(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * ��ѯ��У�⽱ѧ��Ľ�ѧ�������Ϣ
	 * @param notListLb
	 * @return
	 */
//	public List<HashMap<String, String>> getJxjlbList(String notListLb) {
//		return dao.getList(getQUERY_JXJDMNOTLIKE_SQL(), new String[]{}, new String[]{"dm", "mc"});
//	}
	
	/**
	 * ��ѯ��ѧ��������ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjrsResult(PjpyZjcmCsszModel model, String[] colList)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(getQUERY_JXJRS_SQL());
		StringBuffer whereSql = appendWhereSql(model);

		
		return CommonQueryDAO.commonQuery(sql.toString(), whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, colList, model);
	}
	
	/**
	 * ��ʼ����������
	 * @param lb
	 * @return
	 * @throws Exception
	 */
	public boolean baseDataInit(String lb) throws Exception{
		return dao.runProcuder("{call ty_initBaseData(?,?)}", new String[]{"", lb});
	}
	
	/**
	 * ��ʼ����������
	 * @param lb
	 * @return
	 * @throws Exception
	 */
	public boolean baseZhcpjxjDataInit(String lb) throws Exception{
		return dao.runProcuder("{call ty_initZhcpjxjBaseData(?,?)}", new String[]{"", lb});
	}
	
	/**
	 * ��ѧ������������������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjrs(PjpyZjcmCsszModel model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(getUPDATE_JXJRS_SQL());
		appendUpdateWhereSql(model, sql);
		return dao.runUpdate(sql.toString(), new String[] { model.getJxjbl(),
				model.getCpfw(), model.getJxjbl(), model.getJxjbl(),
				model.getKey(), model.getXn(), model.getXq(),
				model.getNd(), model.getJxjdm(), model.getCpfw() });
	}

	private void appendUpdateWhereSql(PjpyZjcmCsszModel model, StringBuffer sql) {
		if (BZ_XY.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm = '");
				sql.append(model.getXydm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
		} else if (BZ_ZY.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getZydm())) {
				sql.append(" and bmdm ='");
				sql.append(model.getZydm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm in (select distinct zydm from view_njxyzybj where xydm='");
				sql.append(model.getXydm());
				sql.append("')");
			}
			
		} else if (BZ_BJ.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getBjdm())) {
				sql.append(" and bmdm='");
				sql.append(model.getBjdm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm in (select distinct bjdm from view_njxyzybj where xydm='");
				sql.append(model.getXydm());
				sql.append("')");
			}
			if (StringUtils.isNotNull(model.getZydm())) {
				sql.append(" and bmdm in (select distinct bjdm from view_njxyzybj where zydm='");
				sql.append(model.getZydm());
				sql.append("')");
			}
			
		}
	}
	
	/**
	 * ��ѯ������ѧ��������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryDgJxjrsxx(String pkValue) {
		return dao.getMapNotOut(getQUERY_JXJDGRS_SQL(), new String[]{pkValue});
	}
	
	/**
	 * �޸Ľ�ѧ�𵥸�����������Ϣ
	 * @param pkValue
	 * @param jxjrs
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjDgtzrs(String pkValue, String jxjrs) throws Exception{
		return dao.runUpdate(getUPDATE_JXJDGRS_SQL(), new String[]{jxjrs,pkValue});
	}

	/**
	 * ��ѯ��ѧ��,�����ƺű�����ͷ����
	 * @param model
	 * @return
	 */
	public String[] getJxjRychBlTitle(PjpyZjcmCsszModel model) {
		return dao.getOneRs(getQUERY_JXJRYCHBL_SQL(), new String[] {
				model.getXn(), model.getNd(), model.getXq(), 
				model.getXn(), model.getNd(), model.getXq(), 
				model.getXn(), model.getNd(), model.getXq(), 
				model.getXn(), model.getNd(), model.getXq(), 
				model.getXn(), model.getNd(), model.getXq(), 
				model.getXn(), model.getNd(), model.getXq(), }, new String[] {
				"bl1", "bl2", "bl3", "bl4", "bl5", "bl6" });
		
	}
	
	/**
	 * ��ѯ��ѧ�������ƺű�������
	 * @param model
	 * @return
	 */
	public List<String[]> queryJxjRychBlRs(PjpyZjcmCsszModel model) {
		return dao.rsToVator(getQUERY_JXJRYCHBLRS_SQL(), new String[] {
				model.getXn(), model.getNd(), model.getXq(), model.getXn(),
				model.getNd(), model.getXq(), model.getXn(), model.getNd(),
				model.getXq(), model.getXn(), model.getNd(), model.getXq(),
				model.getXn(), model.getNd(), model.getXq(), model.getXn(),
				model.getNd(), model.getXq(),model.getXydm() }, new String[] { "bjmc", "cprs",
				"yd", "ed", "sd", "shgz", "sh" });
	}
	
	/**
	 * ������ѯ�Ÿɵ�ѧԺ��������
	 * @param model
	 * @return
	 */
	public String queryYgblRs(PjpyZjcmCsszModel model) {
		return dao.getOneRs(getQUERY_YGBLRS_SQL(), new String[] {
				model.getXydm(), model.getXn(), model.getNd(), model.getXq() },
				"jxjbl");
	}
	
	//�Զ�����GET����
	
	public StringBuffer getQUERY_JXJDM_SQL() {
		return QUERY_JXJDM_SQL;
	}

	public String getQUERY_RYCHDM_SQL() {
		return QUERY_RYCHDM_SQL.toString();
	}

	public String getQUERY_JXJLB_SQL() {
		return QUERY_JXJLB_SQL.toString();
	}

	public StringBuffer getQUERY_JXJRS_SQL() {
		return QUERY_JXJRS_SQL;
	}

	public StringBuffer getUPDATE_JXJRS_SQL() {
		return UPDATE_JXJRS_SQL;
	}

	public String getQUERY_JXJDGRS_SQL() {
		return QUERY_JXJDGRS_SQL.toString();
	}

	public String getUPDATE_JXJDGRS_SQL() {
		return UPDATE_JXJDGRS_SQL.toString();
	}

	public String getQUERY_JXJRYCHBL_SQL() {
		return QUERY_JXJRYCHBL_SQL.toString();
	}

	public String getQUERY_JXJRYCHBLRS_SQL() {
		return QUERY_JXJRYCHBLRS_SQL.toString();
	}

	public String getQUERY_YGBLRS_SQL() {
		return QUERY_YGBLRS_SQL.toString();
	}

//	public String getQUERY_JXJDMNOTLIKE_SQL() {
//		return QUERY_JXJDMNOTLIKE_SQL.toString();
//	}
	
	
}
