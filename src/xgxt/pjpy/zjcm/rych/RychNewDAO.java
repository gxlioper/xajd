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
 * 浙江传媒荣誉称号审核DAO
 * @desc 主要处理荣誉称号数据与数据库的交互
 * @author lt
 * @time 2010-4-20 15:21
 * @version 1.0
 */
public class RychNewDAO {

	DAO dao = DAO.getInstance();
	
	//查询单个学院的荣誉称号参评范围
	private final StringBuilder QUERY_XYRYCHCPFW_SQL = new StringBuilder("select distinct bmlb from xyjxjrs")
	                                .append(" where (jxjbl is not null and xn=? and xqdm=? and nd=? and jxjdm=? and key='rych')")
	                                .append(" and (bmdm = ? or bmdm in (select distinct zydm from view_njxyzybj where xydm=?) or bmdm in (select distinct bjdm from view_njxyzybj where xydm=?))");
	
	//辅导员用户查询荣誉称号审核数据SQL
	private final StringBuffer QUERY_RYCHSHSJBYFDY_SQL = new StringBuffer("select pk,xh")
	                   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
	                   .append(".xqdm) xqmc,xq,rownum r,fdysh sh,(case when xysh='通过' or xxs")
	                   .append("h='通过' then 'disabled' else '' end) dis,dypm,zypm,typm,")
	                   .append("nlpm,zhpm,xysh,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//学院用户查询荣誉称号审核数据SQL
	private final StringBuffer QUERY_RYCHSHSJBYXY_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xysh sh,(case when xxsh='通过' then ")
					   .append("'disabled' else '' end) dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,xxsh,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//学校用户查询荣誉称号审核数据SQL
	private final StringBuffer QUERY_RYCHSHSJBYXX_SQL = new StringBuffer("select pk,xh")
					   .append(",xm,rychmc,bjmc,xn,(select xqmc from xqdzb b where a.xq=b")
					   .append(".xqdm) xqmc,xq,rownum r,xxsh sh,'' dis,dypm,zypm,typm,")
					   .append("nlpm,zhpm,(case when cfcs >0 then 'red' else '' end) color from view_pjpy_zjcm_xsrychb a");
	
	//查询荣誉称号获奖人数
	private final StringBuilder QUERY_RYCHHJRS_SQL = new StringBuilder("select sum(nvl(jxjrs,0)) jxjrs from xyjxjrs")
	                   .append(" where xn=? and xqdm=? and nd=? and jxjdm=? and key='rych' and bmdm=? and bmlb=?");
	
	//查询部门荣誉称号通过人数
	private final StringBuilder QUERY_RYCHTGRS_SQL = new StringBuilder("select count(*) rs from zjcm_rychsqb a where xn=? and xq=? and rychdm=? ");
	
	//查询辅导员单个审核奖学金信息
	private final StringBuilder QUERY_RYCHDGSHXXBYFDY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh sh,xysh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj yj,xyyj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	//查询学院单个审核奖学金信息
	private final StringBuilder QUERY_RYCHDGSHXXBYXY_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh,xysh sh,xxsh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj yj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm ,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	//查询学院单个审核奖学金信息
	private final StringBuilder QUERY_RYCHDGSHXXBYXX_SQL = new StringBuilder("select xh,")
	                   .append("xn,xq,rychmc,fdysh,xysh,xxsh sh,fdyshsj,xyshsj,xxshsj,")
	                   .append("fdyyj,xyyj,xxyj yj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
	                   .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
	                   .append("view_pjpy_zjcm_xsrychb a where xh||rychdm||xn||xq=?");
	
	/**
	 * 查询单个学院单个荣誉称号的参评范围
	 * @param model
	 * @return 如果返回多条数据,则作特殊标志 2为返回多条数据
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
	 * 查询奖学金审核结果
	 * @param model
	 * @param userType 辅导员，学院，学校三种类型用户
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
	 * 通过用户类型判断取不同的查询SQL，
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
	
	//输出列表
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
	
//	通过不同的用户类型返回SQL拼接语句
	public StringBuilder appendQuerySql(StringBuilder sql, String fdyFlag,
			String userName, String userType, String sh) {
		if (UserTypePd.isXy(userType)) {//如果用户类别是学院的
			if (UserTypePd.isFdy(fdyFlag)) {//辅导员用户要拼接的SQL
				sql.append(" and exists (select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='")
				   .append(userName)
				   .append("')");
				if (StringUtils.isNotNull(sh)) {
					sql.append(" and fdysh='");
					sql.append(sh);
					sql.append("'");
				}
			} else {//学院用户要拼接的SQL
				sql.append(" and fdysh='通过'");
				if (StringUtils.isNotNull(sh)) {
					sql.append(" and xysh='");
					sql.append(sh);
					sql.append("'");
				}
			}
		} else {
			sql.append(" and xysh='通过'");
			if (StringUtils.isNotNull(sh)) {
				sql.append(" and xxsh='");
				sql.append(sh);
				sql.append("'");
			}
		}
		return sql;
	}
	
	/**
	 * 查询荣誉称号获奖人数
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
	 * 查询部门荣誉称号通过人数
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
	 * 修改奖学金审核结果
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @return
	 * @throws SQLException
	 */
	public boolean updateRychResult(String[] pkValue, String jg,
			String userType) throws SQLException {
		jg = "yes".equalsIgnoreCase(jg) ? "通过"
				: ("no".equalsIgnoreCase(jg) ? "不通过" : "未审核");
		String[] sql = new String[pkValue.length];
		appendUpdateSql(pkValue, jg, userType, sql);
		return dao.checkBatch(dao.runBatch(sql));
	}

	//拼接修改荣誉称号审核SQL
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
	 * 查询单个荣誉称号详细信息
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
	 * 修改奖学金单个审核结果
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
