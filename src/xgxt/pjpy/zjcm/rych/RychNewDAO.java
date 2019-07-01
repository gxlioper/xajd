package xgxt.pjpy.zjcm.rych;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.zjcm.xnjxj.XnjxjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

/**
 * �㽭��ý�����ƺ����DAO
 * @desc ��Ҫ���������ƺ����������ݿ�Ľ���
 * @author lt
 * @time 2010-4-20 15:21
 * @version 1.0
 */
public class RychNewDAO {

	DAO dao = DAO.getInstance();
	
	//��ѯ����ѧԺ�������ƺŲ�����Χ
	private final StringBuilder QUERY_XYRYCHCPFW_SQL = new StringBuilder("select distinct bmlb from xyjxjrs")
	                                .append(" where (jxjbl is not null and xn=? and xqdm=? and nd=? and jxjdm=? and key='rych')")
	                                .append(" and (bmdm = ? or bmdm in (select distinct zydm from view_njxyzybj where xydm=?) or bmdm in (select distinct bjdm from view_njxyzybj where xydm=?))");
	
	//����Ա�û���ѯ�����ƺ��������SQL
	private final StringBuffer QUERY_RYCHSHSJBYFDY_SQL = new StringBuffer("select pk,xh")
	                   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
	                   .append(".xqdm) xqmc,xq,rownum r,fdysh sh,(case when xysh='ͨ��' or xxs")
	                   .append("h='ͨ��' then 'disabled' else '' end) dis,dypm,zypm,typm,")
	                   .append("nlpm,zhpm,xysh,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//ѧԺ�û���ѯ�����ƺ��������SQL
	private final StringBuffer QUERY_RYCHSHSJBYXY_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xysh sh,(case when xxsh='ͨ��' then ")
					   .append("'disabled' else '' end) dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//ѧУ�û���ѯ�����ƺ��������SQL
	private final StringBuffer QUERY_RYCHSHSJBYXX_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xxsh sh,'' dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//��ѯ�����ƺŻ�����
	private final StringBuilder QUERY_RYCHHJRS_SQL = new StringBuilder("select sum(nvl(jxjrs,0)) jxjrs from xyjxjrs")
	                   .append(" where xn=? and xqdm=? and nd=? and jxjdm=? and key='rych' and bmdm=? and bmlb=?");
	
	//��ѯ���������ƺ�ͨ������
	private final StringBuilder QUERY_RYCHTGRS_SQL = new StringBuilder("select count(*) rs from zjcm_rychsqb a where xn=? and xq=? and rychdm=? ");
	
	//��ѯ����Ա������˽�ѧ����Ϣ
	private final StringBuilder QUERY_RYCHDGSHXXBYFDY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh sh,xysh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj yj,xyyj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	//��ѯѧԺ������˽�ѧ����Ϣ
	private final StringBuilder QUERY_RYCHDGSHXXBYXY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh,xysh sh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj yj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm ,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	//��ѯѧԺ������˽�ѧ����Ϣ
	private final StringBuilder QUERY_RYCHDGSHXXBYXX_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh,xysh,xxsh sh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj,xxyj yj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	/**
	 * ��ѯ����ѧԺ���������ƺŵĲ�����Χ
	 * @param model
	 * @return ������ض�������,���������־ 2Ϊ���ض�������
	 * @throws SQLException
	 */
	public String queryRychCpfwByXydm(RychModel model) throws SQLException{
		String[] rs = dao.getArray(getQUERY_XYRYCHCPFW_SQL(),
				                   new String[] {model.getXn(), model.getXqdm(), model.getNd(),
									             model.getRychdm(), model.getXydm(), model.getXydm(),
									             model.getXydm() },
				                   "bmlb");
		return rs != null && rs.length > 0 ? (rs.length == 1 ? rs[0] : "2") : ""; 
	}

	/**
	 * ��ѯ��ѧ����˽��
	 * @param model
	 * @param userType ����Ա��ѧԺ��ѧУ���������û�
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychshResult(RychModel model, String userType,
			String fdyFlag, String userName) throws Exception {
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"xq", "rychdm" };
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
				return getQUERY_RYCHSHSJBYFDY_SQL();
			} else {
				return getQUERY_RYCHSHSJBYXY_SQL();
			}
		} else {
			return getQUERY_RYCHSHSJBYXX_SQL();
		}
	}
	
	//����б�
	public String[] getQueryColListByUserType(String userType, String isFdy) {
		String[] colList = new String[]{ "pk","color",  "dis","r", "xh", "xm", "bjmc", "xn",
				"xqmc", "rychmc", "dypm", "zypm", "typm", "zhpm", "sh"};
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				colList = new String[]{ "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn",
						"xqmc", "rychmc", "dypm", "zypm", "typm", "zhpm", "sh","xysh","xxsh" };
			} else {
				colList = new String[]{ "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn",
						"xqmc", "rychmc", "dypm", "zypm", "typm", "zhpm", "sh","xxsh" };
			}
		} 
		return colList;
	}
	
//	ͨ����ͬ���û����ͷ���SQLƴ�����
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
	 * ��ѯ�����ƺŻ�����
	 * @param model
	 * @param bmdm
	 * @param bmlb
	 * @return
	 */
	public String queryRychHjrs(RychModel model, String bmdm, String bmlb) {
		return dao.getOneRs(getQUERY_RYCHHJRS_SQL(),
                            new String[] {model.getXn(), model.getXq(), model.getNd(),
				                          model.getRychdm(), bmdm, bmlb },
				            "jxjrs");
	}
	
	/**
	 * ��ѯ���������ƺ�ͨ������
	 * @param model
	 * @param appendSql
	 * @return
	 */
	public String queryRychTgrs(RychModel model, String appendSql, String bmdm) {
		return dao.getOneRs(getQUERY_RYCHTGRS_SQL() + appendSql, 
				            new String[]{model.getXn(),model.getXq(),model.getRychdm(), bmdm},
				            "rs");
	}
	
	/**
	 * �޸Ľ�ѧ����˽��
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @return
	 * @throws SQLException
	 */
	public boolean updateRychResult(String[] pkValue, String jg,
			String userType) throws SQLException {
		jg = "yes".equalsIgnoreCase(jg) ? "ͨ��"
				: ("no".equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");
		String[] sql = new String[pkValue.length];
		appendUpdateSql(pkValue, jg, userType, sql);
		return dao.checkBatch(dao.runBatch(sql));
	}

	//ƴ���޸������ƺ����SQL
	private void appendUpdateSql(String[] pkValue, String jg, String userType,
			String[] sql) {
		for (int i=0;i<pkValue.length;i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("update zjcm_rychsqb set ");
			sb.append(userType);
			sb.append("='");
			sb.append(jg);
			sb.append("' where pjxh||rychdm||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
	}
	
	/**
	 * ��ѯ���������ƺ���ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryRychDetails(String pkValue,
			String userType, String fdyFlag) {
		String sql = getSqlByUserType(userType, fdyFlag);
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	public String getSqlByUserType(String userType, String fdyFlag) {
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(fdyFlag)) {
				return getQUERY_RYCHDGSHXXBYFDY_SQL();
			} else {
				return getQUERY_RYCHDGSHXXBYXY_SQL();
			}
		} else {
			return getQUERY_RYCHDGSHXXBYXX_SQL();
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
	public boolean updateRychshResult(String shzd, String shyj,String shsj,
			RychModel model, String pkValue) throws Exception {
		return dao.runUpdate("update zjcm_rychsqb set " + shzd + "=?," + shyj
				+ "=?,"+shsj+"=(select to_char(sysdate,'yyyymmdd') from dual) where pjxh||rychdm||xn||xq = ?",
				new String[] {
				model.getSh(), model.getYj(), pkValue });
	}
	
	public String getQUERY_XYRYCHCPFW_SQL() {
		return QUERY_XYRYCHCPFW_SQL.toString();
	}
	
	public String getQUERY_RYCHSHSJBYFDY_SQL() {
		return QUERY_RYCHSHSJBYFDY_SQL.toString();
	}

	public String getQUERY_RYCHSHSJBYXX_SQL() {
		return QUERY_RYCHSHSJBYXX_SQL.toString();
	}
	
	public String getQUERY_RYCHSHSJBYXY_SQL() {
		return QUERY_RYCHSHSJBYXY_SQL.toString();
	}

	public String getQUERY_RYCHHJRS_SQL() {
		return QUERY_RYCHHJRS_SQL.toString();
	}

	public String getQUERY_RYCHTGRS_SQL() {
		return QUERY_RYCHTGRS_SQL.toString();
	}

	public String getQUERY_RYCHDGSHXXBYFDY_SQL() {
		return QUERY_RYCHDGSHXXBYFDY_SQL.toString();
	}

	public String getQUERY_RYCHDGSHXXBYXX_SQL() {
		return QUERY_RYCHDGSHXXBYXX_SQL.toString();
	}

	public String getQUERY_RYCHDGSHXXBYXY_SQL() {
		return QUERY_RYCHDGSHXXBYXY_SQL.toString();
	}
	
	
}
