package xgxt.pjpy.czxx.rych;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.jxj.JxjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class RychDAO {

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
	
	//ѧԺ��ѯ�����ƺ������Ϣ
	final StringBuilder QUERY_XYJXJSH_SQL = new StringBuilder("select distinct xn||xq||bjdm||rychdm pk,")
			.append("(case when (select tjzt from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and b.dm=a.xydm and b.bm='xy' and b.tjxm='rych')='1'")
			.append(" or xxsh='ͨ��' then 'disabled' else ''  end) dis,xn,xqmc,xymc,bjmc,rychmc,(count(xh) over (partition by xn,xq,xydm,bjdm,rychdm)) bjrs,")
			.append("(select tjr from pjpy_bmshtjb b where b.bm='xy' and a.xydm=b.dm and b.tjxm='rych' and a.xn||a.xq=b.zjz) tjr,")
			.append("(select tjsj from pjpy_bmshtjb b where b.bm='xy' and a.xydm=b.dm and b.tjxm='rych' and a.xn||a.xq=b.zjz) tjsj,xysh sh,xxsh from (")
			.append("select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,rychmc,rychdm,xn,xq,xqmc,zxf,zfpm,fdysh,xysh,xxsh from view_czxx_xsrychb a ")
			.append("where fdysh='ͨ��' and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='rych' and tjzt='1')) a ");
	
	
	//ѧУ��ѯ�����ƺ������Ϣ
	final StringBuilder QUERY_XXJXJSH_SQL = new StringBuilder("select distinct xn||xq||xydm pk,'' dis,xn,xqmc,xymc,")
			.append("(count(xh) over (partition by xn,xq,xydm)) bjrs,xxsh from ")
			.append("(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,rychdm,rychmc,")
			.append("xn,xq,xqmc,zxf,zfpm,fdysh,xysh,xxsh from view_czxx_xsrychb a where fdysh='ͨ��' and xysh='ͨ��' ")
			.append(" and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='rych' and tjzt='1') ")
			.append("and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.xydm=b.dm and b.bm='xy' and b.tjxm='rych' and tjzt='1')) a ");
	
	/**
	 * �����ƺ���������ѯ
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
	 * ���ѧ�����������ƺ������Ƿ����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param jxjdm
	 * @return
	 */
	public String[] checkJxjSqtj(String xh, String xn, String xq, String jxjdm) {
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
					new String[] { GlobalsVariable.PJPY_JXJ, jxjdm }, "bfb");
			
			double szbl = StringUtils.isNull(bl) ? 0 : Double.parseDouble(bl);
			HashMap<String, String> rs = dao.getMapNotOut("select bjdm,zfpm,(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm) bjrs from view_czxx_zhszcp a where xh=? and xn=? and xq=?", new String[]{xh, xn, xq});
			/* ����༶�����ٷֱ� */
			if (!rs.isEmpty()) {
				double zfpm = StringUtils.isNull(rs.get("zfpm")) ? 0 : Double.parseDouble(rs.get("zfpm"));
				double bjrs = StringUtils.isNull(rs.get("bjrs")) ? 1 : Double.parseDouble(rs.get("bjrs"));
				double bjpm = zfpm / bjrs * 100;
				if (szbl != 0 && bjpm > szbl) {
					result[1] = "true";
					result[2] = "�����۲��ܳɼ��༶����ǰ" + bjpm + "%,�������ƺ���������Ϊ�۲��ܳɼ��༶����ǰ" + szbl + "%.";
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
	 * �����ƺŵ������
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
	 * ����Ա�ύ�����ƺ���˽��
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
	 * ѧԺ��ѯ�����ƺ���˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyjxjshjg(JxjModel model) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(new String[] { "xn", "nj", "xq", "xydm", "zydm",
				"bjdm", "rychdm","xysh" }, aMakeQuery.baseListQueryArr, model);
		
		String beforeSql = "select pk,dis,rownum r,xn,xqmc,xymc,bjmc,rychmc,bjrs,tjr,tjsj,sh,xxsh from (";
		String endSql = ") a ";
		return CommonQueryDAO.commonQuery(beforeSql + getQUERY_XYJXJSH_SQL(), aMakeQuery
				.getQueryString() + endSql, aMakeQuery.getInputList(), new String[] {
				"pk","dis", "r", "xn", "xqmc", "xymc", "bjmc", "rychmc", "bjrs",
				"tjr", "tjsj", "sh", "xxsh" }, model);
	}
	
	/**
	 * ѧУ��ѯ�����ƺ���˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXxjxjshjg(JxjModel model) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(new String[] { "xn", "nj", "xq", "xydm", "zydm",
				"bjdm", "rychdm","xxsh" }, aMakeQuery.baseListQueryArr, model);
		
		String beforeSql = "select pk,dis,rownum r,xn,xqmc,xymc,bjrs,xxsh from (";
		String endSql = ") a ";
		return CommonQueryDAO.commonQuery(beforeSql + getQUERY_XXJXJSH_SQL(), aMakeQuery
				.getQueryString() + endSql, aMakeQuery.getInputList(), new String[] {
				"pk","dis", "r", "xn", "xqmc", "xymc", "bjrs", "xxsh" }, model);
	}
	
	/**
	 * ѧԺ�����ƺ�����޸�
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
	
	public String getQUERY_XXJXJSH_SQL() {
		return QUERY_XXJXJSH_SQL.toString();
	}
}
