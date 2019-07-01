package xgxt.pjpy.czxx.jxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

import common.GlobalsVariable;

public class JxjDAO {

	DAO dao = DAO.getInstance();
	
	//����Ա ��ѧ���ѯ���
	final StringBuilder QUERY_JXJ_SQLFDY = new StringBuilder("select xh||jxjdm||xn||xq ")//xh||xn||xq||jxjdm
			.append("pk,(case when xysh='ͨ��' or xxsh='ͨ��' then 'disabled' else '' end) dis,")
			.append("rownum r,xn,xqmc,xh,xm,bjmc,jxjmc,jlje,zfpm,fdysh,xysh,xxsh from view_czxx_xsjxjb");
	
	//ѧԺ ��ѧ���ѯ���
	final StringBuilder QUERY_JXJ_SQLXY = new StringBuilder("select xh||jxjdm||xn||xq ")//xh||xn||xq||jxjdm
			.append("pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,")
			.append("rownum r,xn,xqmc,xh,xm,bjmc,jxjmc,jlje,zfpm,fdysh,xysh,xxsh from view_czxx_xsjxjb");
	
	//ѧУ ��ѧ���ѯ���
	final StringBuilder QUERY_JXJ_SQLXX = new StringBuilder("select xh||jxjdm||xn||xq ")//xh||xn||xq||jxjdm
			.append("pk,'' dis,")
			.append("rownum r,xn,xqmc,xh,xm,bjmc,jxjmc,jlje,zfpm,fdysh,xysh,xxsh from view_czxx_xsjxjb");
	
	
	final StringBuilder QUERY_STU_WJCFXX = new StringBuilder("select xn,xqmc,cflbmc,cfyymc,cfsj,cfwh from view_wjcf where xxsh='ͨ��' and xh=?");
	
	final StringBuilder QUERY_STU_KCCJXX = new StringBuilder("select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,kcmc,cj,kclx,bkcj,cxcj from cjb a where xh=?");
	
	final StringBuilder DELETE_FDYTJ_SQL = new StringBuilder("delete from pjpy_bmshtjb where dm=? and bm=? and zjz=? and tjxm=?");
	
	final StringBuilder INSERT_FDYTJ_SQL = new StringBuilder("insert into pjpy_bmshtjb(dm,bm,zj,zjz,tjsj,tjzt,tjr,tjxm) values (?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?,?)");
	
	final StringBuilder QUERY_TJZT_SQL = new StringBuilder("select (case tjzt when '1' then '�ύ' when '2' then '����' else 'δ�ύ' end) tjzt from pjpy_bmshtjb where dm=? and bm=? and zjz=? and tjxm=?");
	
	//ѧԺ��ѯ��ѧ�������Ϣ
	final StringBuilder QUERY_XYJXJSH_SQL = new StringBuilder("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,")
			.append(" (select tjr from pjpy_bmshtjb b where b.bm='xy' and a.xydm=b.dm and b.tjxm='jxj' and a.xn||a.xq=b.zjz) tjr,")
			.append(" (select tjsj from pjpy_bmshtjb b where b.bm='xy' and a.xydm=b.dm and b.tjxm='jxj' and a.xn||a.xq=b.zjz) tjsj,")
			.append(" (case when (select tjzt from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and b.dm=a.xydm and b.bm='xy' and b.tjxm='jxj')='1' then 'disabled' else ''  end) dis from (select pk,xn,xq,jxjdm,bjdm,xydm,bjrs,(select bjmc from view_njxyzybj b where a.bjdm=b.bjdm and rownum<2) bjmc,")
			.append(" (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum<2) xymc,")
			.append("  (select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,(select jlje from jxjdmb b where a.jxjdm=b.jxjdm) jlje,")
			.append("  (select xysh from view_czxx_xsjxjb b where a.xn=b.xn and a.xq=b.xq and a.jxjdm=b.jxjdm and a.bjdm=b.bjdm and fdysh='ͨ��'")
			.append("  and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1') and rownum<2) xysh,")
			.append("  (select xxsh from view_czxx_xsjxjb b where a.xn=b.xn and a.xq=b.xq and a.jxjdm=b.jxjdm and a.bjdm=b.bjdm and fdysh='ͨ��' ")
			.append("  and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1')")
			.append("  and xysh='ͨ��' and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.xydm=b.dm and b.bm='xy' and b.tjxm='jxj' and tjzt='1')")
			.append("   and rownum<2) xxsh,(select zydm from view_czxx_xsjxjb b where a.bjdm=b.bjdm and rownum<2) zydm,")
			.append("   (select nj from view_czxx_xsjxjb b where a.bjdm=b.bjdm and rownum<2) nj from")
			.append("(select distinct xn||xq||bjdm||jxjdm pk,xn,xq,bjdm,jxjdm,xydm,(count(xh)) bjrs")
			.append(" from (select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jxjdm,jxjmc,xn,xq,xqmc,zxf,zfpm,fdysh,xysh,xxsh from view_czxx_xsjxjb a")
			.append(" where fdysh='ͨ��' and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1')) a  ")
			.append(" group by xn,xq,xydm,bjdm,jxjdm) a) a ");
	
	final StringBuilder QUERY_XYJXJSHVIEW_SQL = new StringBuilder("select * from view_czxx_xsjxjb where fdysh='ͨ��' and xn||xq||bjdm||jxjdm=?");
	
	//ѧУ��ѯ��ѧ�������Ϣ
	final StringBuilder QUERY_XXJXJSH_SQL = new StringBuilder("select pk,dis,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,")
			.append("(select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum<2) xymc,bjrs,je,")
			.append("(select xxsh from view_czxx_xsjxjb b where a.xn=b.xn and a.xq=b.xq and a.xydm=b.xydm and rownum<2) xxsh from ")
			.append("(select distinct xn||xq||xydm pk,'' dis,xn,xq,xydm,(sum(jlje)) je,(count(xh)) bjrs from ")
			.append("(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jxjdm,jxjmc,jlje,xn,xq,xqmc,zxf,zfpm,fdysh,xysh,xxsh from view_czxx_xsjxjb a ")
			.append("where fdysh='ͨ��' and xysh='ͨ��'  and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1') ")
			.append("and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.xydm=b.dm and b.bm='xy' and b.tjxm='jxj' and tjzt='1')) a group by xn,xq,xydm) a");
	
	//��ѯ�����ƺ������������
	final StringBuilder QUERY_RYCHSQTJ_SQL = new StringBuilder("select dm,cj,(select jxjmc from jxjdmb b where a.dm=b.jxjdm) jxjmc from czxx_rychtjszb a");
	
	//��ѯѧ�������ɼ�
	final StringBuilder QUERY_STUDCJ_SQL = new StringBuilder("select dcj from view_czxx_zhszcp where xh=? and xn=? and xq=?");
	
	final StringBuilder QUERY_JXJPRINT_SQL = new StringBuilder("select * from view_czxx_xsjxjb where xh||jxjdm||xn||xq = ? ");
	final StringBuilder QUERY_RYCHPRINT_SQL = new StringBuilder("select a.*,xyyj xyshyj,xxyj xxshyj from view_czxx_xsrychb a where xh||xn||rychdm||xq = ? ");
	
	final StringBuilder QUERY_STU_ZCCJ = new StringBuilder("select dcj,zcj,tcj,zxf,dpm,zpm,tpm,zfpm from view_czxx_zhszcp where xh=? and xn=? and xq=?");
	
	/**
	 * ��ѧ����������ѯ
	 * @param model
	 * @param userType
	 * @param fdySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjResult(JxjModel model, String userType,
			String fdySql) throws Exception {
		String sql = UserTypePd.isXy(userType) ? getQUERY_JXJ_SQLXY()
				: (UserTypePd.isXx(userType) ? getQUERY_JXJ_SQLXX()
						: getQUERY_JXJ_SQLFDY());
		sql = StringUtils.isNotNull(fdySql) ? getQUERY_JXJ_SQLFDY() : sql;

		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(new String[] { "xn", "nj", "xq", "xydm", "zydm",
				"bjdm", "jxjdm" }, aMakeQuery.baseListQueryArr, model);

		return CommonQueryDAO.commonQuery(sql, aMakeQuery.getQueryString()
				+ fdySql, aMakeQuery.getInputList(), new String[] { "pk",
				"dis", "r", "xn", "xqmc", "xh", "xm", "bjmc", "jxjmc", "jlje","zfpm",
				"fdysh", "xysh", "xxsh" }, model);
	}
	
	/**
	 * ���ѧ�����뽱ѧ�������Ƿ����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param jxjdm
	 * @return
	 */
	public String[] checkJxjSqtj(String xh, String xn, String xq, String jxjdm, String lb) {
		String[] result = new String[]{"false", "false", "false"};
		
		/* ��ѯ�Ƿ��д��ּ�¼ */
		String cfcount = dao.getOneRs("select count(*) cnt from wjcfb where xh=? and xn=? " +
				"and xq=? and xxsh='ͨ��' and cfsj is not null and cfwh is not null",
						new String[] { xh, xn, xq }, "cnt");
		
		if (StringUtils.isNotNull(cfcount) && !"0".equalsIgnoreCase(cfcount)) {
			result[0] = "true";
		} else {
			String bl = dao.getOneRs(
					"select bfb from zhszpmbfbb where lb=? and dm=?",
					new String[] { lb, jxjdm }, "bfb");
			
			double szbl = StringUtils.isNull(bl) ? 0 : Double.parseDouble(bl);
			HashMap<String, String> rs = dao.getMapNotOut("select bjdm,zfpm,(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm) bjrs from view_czxx_zhszcp a where xh=? and xn=? and xq=?", new String[]{xh, xn, xq});
			/* ����༶�����ٷֱ� */
			if (!rs.isEmpty()) {
				double zfpm = StringUtils.isNull(rs.get("zfpm")) ? 0 : Double.parseDouble(rs.get("zfpm"));
				double bjrs = StringUtils.isNull(rs.get("bjrs")) ? 1 : Double.parseDouble(rs.get("bjrs"));
				double bjpm = zfpm / bjrs * 100;
				
				java.text.DecimalFormat f = new java.text.DecimalFormat(
				"0.00");
				String bjpmStr = f.format(bjpm);
				
				if (szbl != 0 && bjpm > szbl) {
					result[1] = "true";
					result[2] = "�����۲��ܳɼ��༶����" + bjpmStr + "%,�˽�����������Ϊ�۲��ܳɼ��༶����ǰ" + szbl + "%.";
				}
			}
		}
		return result;
	}
	
	/**
	 * ���ѧ�����뽱ѧ�������Ƿ����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param jxjdm
	 * @return
	 */
	public String[] checkRychSqtj(String xh, String xn, String xq, String jxjdm, String lb) throws Exception{
		String[] result = new String[]{"false", "false", "", "false", "", "false", ""};
		
		/* ��ѯ�Ƿ��д��ּ�¼ */
		String cfcount = dao.getOneRs("select count(*) cnt from wjcfb where xh=? and xn=? " +
				"and xq=? and xxsh='ͨ��' and cfsj is not null and cfwh is not null",
						new String[] { xh, xn, xq }, "cnt");
		
		if (StringUtils.isNotNull(cfcount) && !"0".equalsIgnoreCase(cfcount)) {
			result[0] = "true";
		} else {
			String bl = dao.getOneRs(
					"select bfb from zhszpmbfbb where lb=? and dm=?",
					new String[] { "rych", jxjdm }, "bfb");
			
			double szbl = StringUtils.isNull(bl) ? 0 : Double.parseDouble(bl);
			HashMap<String, String> rs = dao.getMapNotOut("select bjdm,zfpm,(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm) bjrs from view_czxx_zhszcp a where xh=? and xn=? and xq=?", new String[]{xh, xn, xq});
			/* ����༶�����ٷֱ� */
			if (!rs.isEmpty()) {
				double zfpm = StringUtils.isNull(rs.get("zfpm")) ? 0 : Double.parseDouble(rs.get("zfpm"));
				double bjrs = StringUtils.isNull(rs.get("bjrs")) ? 1 : Double.parseDouble(rs.get("bjrs"));
				double bjpm = zfpm / bjrs * 100;
				
				java.text.DecimalFormat f = new java.text.DecimalFormat(
				"0.00");
				String bjpmStr = f.format(bjpm);
				
				if (szbl != 0 && bjpm > szbl) {
					result[1] = "true";
					result[2] = "�����۲��ܳɼ��༶����ǰ" + bjpmStr + "%,�˽�����������Ϊ�۲��ܳɼ��༶����ǰ" + szbl + "%.";
					return result;
				}
			}
			
			/* ��������ƺ����� */
			List<String[]> pmcjList = dao.rsToVator(getQUERY_RYCHSQTJ_SQL(),
					new String[] {}, new String[] { "dm", "cj","jxjmc" });
			String dcjStr = dao.getOneRs(getQUERY_STUDCJ_SQL(), new String[] {
					xh, xn, xq }, "dcj");
			double dcj = StringUtils.isNull(dcjStr) ? 0 : Double
					.parseDouble(dcjStr);
			if (!pmcjList.isEmpty()) {
				String dcjTjStr = pmcjList.get(0) != null
						&& pmcjList.get(0).length == 3 ? pmcjList.get(0)[1]
						: "";
				double dcjTj = StringUtils.isNull(dcjTjStr) ? 0 : Double
						.parseDouble(dcjTjStr);
				
				/* �������ɼ��Ƿ�������� */
				if (dcjTj != 0 && dcj < dcjTj) {
					result[3] = "true";
					result[4] = "���������ɼ������������������˽�����������ɼ�����Ϊ��" + dcjTj + "������.";
					return result;
				}
				
				StringBuilder sql = new StringBuilder(
						"select b.jxjmc from xsjxjb a,jxjdmb b where a.jxjdm=b.jxjdm and a.xh=? and a.xn=? and a.xq=? and a.xxsh='ͨ��' and b.jxjdm in ('");
				int bz = 0;
				for (String[] s : pmcjList) {
					if (s != null
							&& StringUtils.isNotNull(s[0])) {
						sql.append(s[0]);
						sql.append("','");
						bz++;
					}

				}
				if (bz != 0) {
					sql.delete(sql.length() - 2, sql.length());
				} else {
					sql.append("'");
					
				}
				
				sql.append(")");

				String[] cont = dao.getArray(sql.toString(), new String[] { xh,
						xn, xq }, "cnt");
				
				//����Ƿ��л�ѧ��ǰ������
				if ((cont == null || cont.length <= 0) && bz != 0) {
					result[5] = "true";
					result[6] = "����������ƺŵ�ǰ�������Ǳ��������½�ѧ��\n";
					for (int i=0;i<pmcjList.size();i++) {
						String[] s = pmcjList.get(i);
						if (s != null && s.length == 3) {
						result[6] += "    "+ (i+1) + ". " + s[2];
						result[6] += ",\n";
						}
					}
					return result;
				}
			}
		}
		return result;
	}
	
	/**
	 * ��ѯѧ��Υ�ʹ�����Ϣ  ������ѧ�Ų���Ϊ��
	 * @param paramMap
	 * @return
	 */
	public List<String[]> queryStuWjcfxx(HashMap<String, String> paramMap) {
		if (StringUtils.isNull(paramMap.get("xh"))) {
			System.out.println("ѧ�Ų���Ϊ�գ�");
			return new ArrayList<String[]>();
		}
		
		StringBuilder whereSql = new StringBuilder();
		List<String> values = new ArrayList<String>();
		values.add(paramMap.get("xh"));
		
		if (StringUtils.isNotNull(paramMap.get("xn"))) {
			whereSql.append(" and xn = ?");
			values.add(paramMap.get("xn"));
		}
		if (StringUtils.isNotNull(paramMap.get("xq"))) {
			whereSql.append(" and xq = ?");
			values.add(paramMap.get("xq"));
		}
		
		return dao.rsToVator(getQUERY_STU_WJCFXX() + whereSql.toString(),
				values.isEmpty() ? new String[] {} : values
						.toArray(new String[0]), new String[] { "xn", "xqmc",
						"cflbmc", "cfyymc", "cfsj", "cfwh" });
	}

	/**
	 * ��ѯѧ���γ̳ɼ���Ϣ  ������ѧ�Ų���Ϊ��
	 * @param paramMap
	 * @return
	 */
	public List<String[]> queryStuKccjxx(HashMap<String, String> paramMap) {
		if (StringUtils.isNull(paramMap.get("xh"))) {
			System.out.println("ѧ�Ų���Ϊ�գ�");
			return new ArrayList<String[]>();
		}
		
		StringBuilder whereSql = new StringBuilder();
		List<String> values = new ArrayList<String>();
		values.add(paramMap.get("xh"));
		
		if (StringUtils.isNotNull(paramMap.get("xn"))) {
			whereSql.append(" and xn = ?");
			values.add(paramMap.get("xn"));
		}
		if (StringUtils.isNotNull(paramMap.get("xq"))) {
			whereSql.append(" and xq = ?");
			values.add(paramMap.get("xq"));
		}
		
		return dao.rsToVator(getQUERY_STU_KCCJXX() + whereSql.toString(),
				values.isEmpty() ? new String[] {} : values
						.toArray(new String[0]), new String[] { "xn", "xqmc",
						"kcmc", "cj", "kclx", "bkcj", "cxcj" });
	}
	
	/**
	 * ��ѧ�𵥸����
	 * @param pkValue
	 * @param sql
	 * @param sh
	 * @param yj
	 * @return
	 * @throws Exception
	 */
	public boolean jxjDgshjg(String pkValue, String sql, String sh, String yj) throws Exception{
		return dao.runUpdate(sql, new String[]{sh, yj, pkValue});
	}
	
	/**
	 * ����Ա�ύ��ѧ����˽��
	 * @param model
	 * @param userName
	 * @param xm
	 * @param zt
	 * @param bmlb
	 * @return
	 * @throws Exception
	 */
	public boolean fdyTjJxjjg(String bmdm, JxjModel model, String userName, String xm,
			String zt, String bmlb) throws Exception {
		boolean result = dao.runUpdate(getDELETE_FDYTJ_SQL(), new String[] {
				bmdm, bmlb,model.getQueryequals_xn()+model.getQueryequals_xq(), xm });
		if (result) {
			result = dao.runUpdate(getINSERT_FDYTJ_SQL(), new String[] {
					bmdm, bmlb, "xn||xq",
					model.getQueryequals_xn()+model.getQueryequals_xq(), zt, userName, xm });
		}
		return result;
	}
	
	/**
	 * ��ѯ�ύ״̬
	 * @param dm
	 * @param model
	 * @param lb
	 * @return
	 */
	public String queryTjzt(String dm, JxjModel model, String lb, String xmlb) {
		return dao.getOneRs(getQUERY_TJZT_SQL(), new String[]{dm, lb, model.getQueryequals_xn()+model.getQueryequals_xq(), xmlb}, "tjzt");
	}
	
	/**
	 * ѧԺ��ѯ��ѧ����˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyjxjshjg(JxjModel model) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(new String[] { "xn", "nj", "xq", "xydm", "zydm",
				"bjdm", "jxjdm","xysh" }, aMakeQuery.baseListQueryArr, model);
		
		String beforeSql = "select pk,dis,rownum r,xn,xqmc,xymc,bjmc,jxjmc,bjrs,(nvl(a.bjrs,0)*a.jlje) je,tjr,tjsj,xysh,xxsh from (";
		String endSql = ") a ";
		System.out.println(beforeSql + getQUERY_XYJXJSH_SQL() + aMakeQuery
				.getQueryString() + endSql);
		System.out.println(model.getXn());
		System.out.println(model.getXq());
		System.out.println(model.getXydm());
		return CommonQueryDAO.commonQuery(beforeSql + getQUERY_XYJXJSH_SQL(), aMakeQuery
				.getQueryString() + endSql, aMakeQuery.getInputList(), new String[] {
				"pk","dis", "r", "xn", "xqmc", "xymc", "bjmc", "jxjmc", "bjrs", "je",
				"tjr", "tjsj", "xysh", "xxsh" }, model);
	}
	
	/**
	 * ѧУ��ѯ��ѧ����˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXxjxjshjg(JxjModel model) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		
		String xxshSql = "";
		if (StringUtils.isNotNull(model.getXxsh())) {
			xxshSql = " where xxsh='";
			xxshSql += model.getXxsh();
			xxshSql += "'";
		}
		
		aMakeQuery.makeQuery(new String[] { "xn", "nj", "xq", "xydm", "zydm",
				"bjdm", "jxjdm" }, aMakeQuery.baseListQueryArr, model);
		
		String beforeSql = "select pk,dis,rownum r,xn,xqmc,xymc,bjrs,je,xxsh from (select pk,dis,xn,xqmc,xymc,bjrs,je,xxsh from (";
		String endSql = " ) a";
		System.out.println(beforeSql + getQUERY_XXJXJSH_SQL()+ aMakeQuery
				.getQueryString() + endSql + xxshSql + endSql);
		System.out.println(model.getXn());
		System.out.println(model.getXq());
		System.out.println(model.getXydm());
		return CommonQueryDAO.commonQuery(beforeSql + getQUERY_XXJXJSH_SQL(), aMakeQuery
				.getQueryString() + endSql + xxshSql + endSql, aMakeQuery.getInputList(), new String[] {
				"pk","dis", "r", "xn", "xqmc", "xymc", "bjrs", "je", "xxsh" }, model);
	}
	
	/**
	 * ѧԺ��ѧ������޸�
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean updateXyjxjplshjg(String sql ) throws Exception{
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * ��ⷵ��������������Ƿ������ͨ��������
	 * @param pkValue
	 * @return
	 */
	public boolean queryShjg(String pkValue, String userType) {
		String[] pkArr = StringUtils.isNotNull(pkValue) ? pkValue.split("!@") : new String[]{};
		String count = "0";
		String sql = "";
		if (UserTypePd.isXy(userType)) {
			sql = "select count(*) cnt from view_czxx_xsjxjb where fdysh='ͨ��' and xysh='ͨ��' and xn||xq||bjdm||jxjdm in ('";
		} else {
			sql = "select count(*) cnt from view_czxx_xsjxjb where fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' and xn||xq||xydm in ('";
		}
		if (pkArr != null) {
			for (String s : pkArr) {
				sql += s;
				sql += "','";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ")";
			count = dao.getOneRs(sql, new String[]{}, "cnt"); 
		}
		return "0".equalsIgnoreCase(count) || StringUtils.isNull(count) ? false : true;
	}
	
	/**
	 * ��ⷵ��������������Ƿ������ͨ��������
	 * @param pkValue
	 * @return
	 */
	public boolean queryShjgByrych(String pkValue, String userType) {
		String[] pkArr = StringUtils.isNotNull(pkValue) ? pkValue.split("!@") : new String[]{};
		String count = "0";
		String sql = "";
		if (UserTypePd.isXy(userType)) {
			sql = "select count(*) cnt from view_czxx_xsrychb where fdysh='ͨ��' and xysh='ͨ��' and xn||xq||bjdm||rychdm in ('";
		} else {
			sql = "select count(*) cnt from view_czxx_xsrychb where fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' and xn||xq||xydm in ('";
		}
		if (pkArr != null) {
			for (String s : pkArr) {
				sql += s;
				sql += "','";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ")";
			count = dao.getOneRs(sql, new String[]{}, "cnt"); 
		}
		return "0".equalsIgnoreCase(count) || StringUtils.isNull(count) ? false : true;
	}
	
	//��ӡ����
	public HashMap<String, String> getPrintMap(String pkValue, String table) {
		return "xsjxjb".equalsIgnoreCase(table) ? dao.getMapNotOut(
				getQUERY_JXJPRINT_SQL(), new String[] { pkValue }) : dao
				.getMapNotOut(getQUERY_RYCHPRINT_SQL(),
						new String[] { pkValue });
	}
	
	public HashMap<String, String> queryStuZhcjMap(HashMap<String, String> map) {
		return dao.getMapNotOut(getQUERY_STU_ZCCJ(), new String[]{map.get("xh"),map.get("xn"),map.get("xq")});
	}
	
	public String getQUERY_JXJ_SQLFDY() {
		return QUERY_JXJ_SQLFDY.toString();
	}

	public String getQUERY_JXJ_SQLXX() {
		return QUERY_JXJ_SQLXX.toString();
	}

	public String getQUERY_JXJ_SQLXY() {
		return QUERY_JXJ_SQLXY.toString();
	}

	public String getQUERY_STU_KCCJXX() {
		return QUERY_STU_KCCJXX.toString();
	}

	public String getQUERY_STU_WJCFXX() {
		return QUERY_STU_WJCFXX.toString();
	}

	public String getDELETE_FDYTJ_SQL() {
		return DELETE_FDYTJ_SQL.toString();
	}

	public String getINSERT_FDYTJ_SQL() {
		return INSERT_FDYTJ_SQL.toString();
	}

	public String getQUERY_TJZT_SQL() {
		return QUERY_TJZT_SQL.toString();
	}

	public String getQUERY_XYJXJSH_SQL() {
		return QUERY_XYJXJSH_SQL.toString();
	}

	public String getQUERY_XYJXJSHVIEW_SQL() {
		return QUERY_XYJXJSHVIEW_SQL.toString();
	}

	public String getQUERY_XXJXJSH_SQL() {
		return QUERY_XXJXJSH_SQL.toString();
	}

	public String getQUERY_RYCHSQTJ_SQL() {
		return QUERY_RYCHSQTJ_SQL.toString();
	}

	public String getQUERY_STUDCJ_SQL() {
		return QUERY_STUDCJ_SQL.toString();
	}

	public String getQUERY_JXJPRINT_SQL() {
		return QUERY_JXJPRINT_SQL.toString();
	}

	public String getQUERY_RYCHPRINT_SQL() {
		return QUERY_RYCHPRINT_SQL.toString();
	}

	public String getQUERY_STU_ZCCJ() {
		return QUERY_STU_ZCCJ.toString();
	}
}
