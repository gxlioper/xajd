package xgxt.pjpy.zjcm.xnjxj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class XnjxjNewDAO {

	DAO dao = DAO.getInstance();
	
	//����Ա�û���ѯУ�ڽ�ѧ���������SQL
	private final StringBuffer QUERY_XNJXJSHSJBYFDY_SQL = new StringBuffer("select pk,xh")
	                   .append(",xm,jxjmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
	                   .append(".xqdm) xqmc,xq,rownum r,fdysh sh,(case when xysh='ͨ��' or xxs")
	                   .append("h='ͨ��' then 'disabled' else '' end) dis,dypm,zypm,typm,")
	                   .append("nlpm,zhpm,xysh,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsjxjb a");
	
	//ѧԺ�û���ѯУ�ڽ�ѧ���������SQL
	private final StringBuffer QUERY_XNJXJSHSJBYXY_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,jxjmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xysh sh,(case when xxsh='ͨ��' then ")
					   .append("'disabled' else '' end) dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsjxjb a");
	
	//ѧУ�û���ѯУ�ڽ�ѧ���������SQL
	private final StringBuffer QUERY_XNJXJSHSJBYXX_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,jxjmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xxsh sh,'' dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsjxjb a");
	
	//��ѯ�༶������
	private final StringBuffer QUERY_BJHJME_SQL = new StringBuffer("select nvl(jxjrs,0) jxjrs from ")
	                   .append("xyjxjrs where xn=? and nd=? and xqdm=? and bmdm=? and ")
	                   .append("bmlb='bjdm' and jxjdm=? and key=?");
	
	//��ѯ�༶ʵ�ʻ�ͨ������
	private final StringBuffer QUERY_BJHJRS_SQL = new StringBuffer("select count(*) num")
	                   .append(" from zjcm_jxjsq a where exists (select 1 from view_xsjbxx")
	                   .append(" b where a.pjxh=b.xh and b.bjdm=?) and xn=? and xq=?")
	                   .append(" and jxjdm=?");
	
	//��ѯ����Ա������˽�ѧ����Ϣ
	private final StringBuilder QUERY_JXJDGSHXXBYFDY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,jxjmc,fdysh sh,xysh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj yj,xyyj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsjxjb a where xh||jxjdm||xn||xq=?");
	
	//��ѯѧԺ������˽�ѧ����Ϣ
	private final StringBuilder QUERY_JXJDGSHXXBYXY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,jxjmc,fdysh,xysh sh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj yj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm ,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsjxjb a where xh||jxjdm||xn||xq=?");
	
	//��ѯѧԺ������˽�ѧ����Ϣ
	private final StringBuilder QUERY_JXJDGSHXXBYXX_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,jxjmc,fdysh,xysh,xxsh sh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj,xxyj yj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsjxjb a where xh||jxjdm||xn||xq=?");
	
	/**
	 * ��ѯ��ѧ����˽��
	 * @param model
	 * @param userType ����Ա��ѧԺ��ѧУ���������û�
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxhshResult(XnjxjModel model, String userType,
			String fdyFlag, String userName) throws Exception {
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"xq", "jxjdm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryObject.baseListQueryArr, model);

		StringBuilder whereSql = new StringBuilder();
		whereSql.append(queryObject.getQueryString());
		
		return CommonQueryDAO.commonQuery(getQuerySqlByUserType(userType,fdyFlag),
				                          appendQuerySql(whereSql, fdyFlag, userName, userType, model.getSh()).toString(),
				                          queryObject.getInputList(),
				                          getQueryColListByUserType(userType, fdyFlag),
				                          model);
	}
	
	/**
	 * ͨ���û������ж�ȡ��ͬ�Ĳ�ѯSQL��
	 * @param userType xy,xx,fdy
	 * @param isFdy true/false
	 * @return
	 */
	public String getQuerySqlByUserType(String userType, String fdyFlag) {
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(fdyFlag)) {
				return getQUERY_XNJXJSHSJBYFDY_SQL();
			} else {
				return getQUERY_XNJXJSHSJBYXY_SQL();
			}
		} else {
			return getQUERY_XNJXJSHSJBYXX_SQL();
		}
	}
	
	//����б�
	public String[] getQueryColListByUserType(String userType, String isFdy) {
		String[] colList = new String[]{ "pk","color",  "dis","r", "xh", "xm", "bjmc", "xn",
				"xqmc", "jxjmc", "dypm", "zypm", "typm", "zhpm", "sh"};
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				colList = new String[]{ "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn",
						"xqmc", "jxjmc", "dypm", "zypm", "typm", "zhpm", "sh","xysh","xxsh" };
			} else {
				colList = new String[]{ "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn",
						"xqmc", "jxjmc", "dypm", "zypm", "typm", "zhpm", "sh","xxsh" };
			}
		} 
		return colList;
	}
	
	//ͨ����ͬ���û����ͷ���SQLƴ�����
	public StringBuilder appendQuerySql(StringBuilder sql, String fdyFlag,
			String userName, String userType, String sh) {
		if (UserTypePd.isXy(userType)) {//����û������ѧԺ��
			if (UserTypePd.isFdy(fdyFlag)) {//����Ա�û�Ҫƴ�ӵ�SQL
				sql.append(" and exists (select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='")
				   .append(userName)
				   .append("')");
				if (StringUtils.isNotNull(sh)) {
					sql.append(" and fdysh='");
					sql.append(sh);
					sql.append("'");
				}
			} else {//ѧԺ�û�Ҫƴ�ӵ�SQL
				sql.append(" and fdysh='ͨ��'");
				if (StringUtils.isNotNull(sh)) {
					sql.append(" and xysh='");
					sql.append(sh);
					sql.append("'");
				}
			}
		} else {
			sql.append(" and xysh='ͨ��'");
			if (StringUtils.isNotNull(sh)) {
				sql.append(" and xxsh='");
				sql.append(sh);
				sql.append("'");
			}
		}
		return sql;
	}
	
	/**
	 * ��ѯ�༶������
	 * @param model
	 * @return
	 */
	public String queryBjhjme(XnjxjModel model) {
		return dao.getOneRs(getQUERY_BJHJME_SQL(), new String[] {
				model.getXn(), model.getNd(), model.getXq(), model.getBjdm(),
				model.getJxjdm(), model.getKey() }, "jxjrs");
	}
	
	/**
	 * ��ѯ�༶ʵ�ʻ�����
	 * @param model
	 * @return
	 */
	public String queryBjhjtgrs(XnjxjModel model, String appendSql) {
		return dao.getOneRs(getQUERY_BJHJRS_SQL() + appendSql,
				new String[] { model.getBjdm(), model.getXn(), model.getXq(),
						model.getJxjdm() }, "num"); 
	}
	
	/**
	 * �޸Ľ�ѧ����˽��
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJxjshResult(String[] pkValue, String jg,
			String userType) throws SQLException {
		jg = "yes".equalsIgnoreCase(jg) ? "ͨ��"
				: ("no".equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");
		String[] sql = new String[pkValue.length];
		appendUpdateSql(pkValue, jg, userType, sql);
		return dao.checkBatch(dao.runBatch(sql));
	}

	//ƴ���޸Ľ�ѧ�����SQL
	private void appendUpdateSql(String[] pkValue, String jg, String userType,
			String[] sql) {
		for (int i=0;i<pkValue.length;i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("update zjcm_jxjsq set ");
			sb.append(userType);
			sb.append("='");
			sb.append(jg);
			sb.append("' where pjxh||jxjdm||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
	}
	
	/**
	 * ��ѯ������ѧ����ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXnjxjDetails(String pkValue,
			String userType, String fdyFlag) {
		String sql = getSqlByUserType(userType, fdyFlag);
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	public String getSqlByUserType(String userType, String fdyFlag) {
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(fdyFlag)) {
				return getQUERY_JXJDGSHXXBYFDY_SQL();
			} else {
				return getQUERY_JXJDGSHXXBYXY_SQL();
			}
		} else {
			return getQUERY_JXJDGSHXXBYXX_SQL();
		}
	}
	
	/**
	 * �޸Ľ�ѧ�𵥸���˽��
	 * @param shzd fdysh,xysh,xxsh
	 * @param shyj fdyyj,xyyj,xxyj
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXnjxjshResult(String shzd, String shyj,String shsj,
			XnjxjModel model, String pkValue) throws Exception {
		return dao.runUpdate("update zjcm_jxjsq set " + shzd + "=?," + shyj
				+ "=?,"+shsj+"=(select to_char(sysdate,'yyyymmdd') from dual) where pjxh||jxjdm||xn||xq = ?",
				new String[] {
				model.getSh(), model.getYj(), pkValue });
	}
	
	/**
	 * ���ڽ�ѧ�𵥸���˽��
	 * @param pkValue
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public String checkJxjshjg(String pkValue, String userType, String isFdy) {
		HashMap<String, String> rs = dao
				.getMapNotOut(
						"select xh,xn,xq,jxjdm,bjdm,nj from view_pjpy_zjcm_xsjxjb where xh||jxjdm||xn||xq = ?",
						new String[] { pkValue });
		String zd = UserTypePd.isXy(userType) ? (UserTypePd.isFdy(isFdy) ? "fdysh"
				: "xysh")
				: "xxsh";
		String bjtgrs = dao
				.getOneRs(
						"select nvl(count(*),0) num from view_pjpy_zjcm_xsjxjb where xn=? and xq=? and bjdm=? and jxjdm=? and "
								+ zd + "='ͨ��'",
						new String[] { rs.get("xn"), rs.get("xq"),
								rs.get("bjdm"), rs.get("jxjdm") }, "num");
		String me = dao
				.getOneRs(
						"select sum(jxjrs) jxjrs from xyjxjrs where xn=? and xqdm=? and jxjdm=? and bmdm=? and key='jxj' and nj=?",
						new String[] { rs.get("xn"), rs.get("xq"),
								rs.get("jxjdm"), rs.get("bjdm"), rs.get("nj") },
						"jxjrs");
		int iMe = StringUtils.isNotNull(me) ? Integer.parseInt(me) : 0;
		int iBjtgrs = StringUtils.isNotNull(bjtgrs) ? Integer.parseInt(me) : 0;
		if (iMe > 0 && iMe > iBjtgrs) {
			return "true";
		}
		return "false";
	}
	
	
	
	public String getQUERY_XNJXJSHSJBYFDY_SQL() {
		return QUERY_XNJXJSHSJBYFDY_SQL.toString();
	}

	public String getQUERY_XNJXJSHSJBYXX_SQL() {
		return QUERY_XNJXJSHSJBYXX_SQL.toString();
	}

	public String getQUERY_XNJXJSHSJBYXY_SQL() {
		return QUERY_XNJXJSHSJBYXY_SQL.toString();
	}

	public String getQUERY_BJHJME_SQL() {
		return QUERY_BJHJME_SQL.toString();
	}

	public String getQUERY_BJHJRS_SQL() {
		return QUERY_BJHJRS_SQL.toString();
	}

	public String getQUERY_JXJDGSHXXBYFDY_SQL() {
		return QUERY_JXJDGSHXXBYFDY_SQL.toString();
	}

	public String getQUERY_JXJDGSHXXBYXX_SQL() {
		return QUERY_JXJDGSHXXBYXX_SQL.toString();
	}

	public String getQUERY_JXJDGSHXXBYXY_SQL() {
		return QUERY_JXJDGSHXXBYXY_SQL.toString();
	}

}
