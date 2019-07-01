package xgxt.pjpy.czxx.tycj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.czxx.dycj.DyjcfModel;
import xgxt.pjpy.czxx.zycj.ZycjModel;
import xgxt.pjpy.zjjj.model.DycjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class TycjDAO {

	DAO dao = DAO.getInstance();
	
	//查询过渡版体育课成绩信息
	final StringBuffer QUERY_GDTJKCJXX = new StringBuffer("select a.*,rownum r ")
	                       .append("from (select a.xh,a.xn,a.xq,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.kcmc,a.kcxz")
	                       .append(",a.cj,b.xm,b.xb,b.xydm,b.nj,b.zydm,b.bjdm")
	                       .append(",b.xymc,b.zymc,b.bjmc from cjb a,view_xsjbxx")
	                       .append(" b where a.xh=b.xh and exists (select 1 from tykjxrwb c where a.xkkh=c.xkkh and rownum=1)) a");
	
	//查询学分制版体育课成绩信息
	final StringBuilder QUERY_XFTYJCJXX = new StringBuilder("select a.*,rownum r ")
					    .append("from (select a.xh,a.xn,a.xq,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.kcmc,a.kcxz")
					    .append(",a.cj,b.xm,b.xb,b.xydm,b.nj,b.zydm,b.bjdm")
					    .append(",b.xymc,b.zymc,b.bjmc from cjb a,view_xsjbxx")
					    .append(" b where a.xh=b.xh and exists (select 1 from tykjxrwb c where a.kcdm=c.xkkh and rownum=1)) a");
	
	//查询过渡体育成绩信息
	final StringBuffer QUERY_GDTYCJXX = new StringBuffer("select xh||xn||xq pk,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,round(((nvl(tycj,0)*?/100) + (nvl(dlf,0)*?/100) + (nvl(fjf,0)*?/100)),2) zf,rownum r from (")
	                    .append("select a.xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,")
	                    .append("(select avg(cj) from view_zhhcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and exists (select 1 from tykjxrwb d where b.xkkh=d.xkkh and rownum=1)) tycj,")
	                    .append("(select fs from czxx_tydlcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) dlf,")
	                    .append("(select sum(lb||fs) from czxx_tyfjfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) fjf")
	                    .append(" from (select xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,? xn,? xq from view_xsjbxx) a) a");
	//查询学分体育成绩信息
	final StringBuffer QUERY_XFTYCJXX = new StringBuffer("select xh||xn||xq pk,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,round(((nvl(tycj,0)*?/100) + (nvl(dlf,0)*?/100) + (nvl(fjf,0)*?/100)),2) zf,rownum r from (")
	                    .append("select a.xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,")
	                    .append("(select avg(cj) from view_zhhcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and exists (select 1 from tykjxrwb d where b.kcdm=d.xkkh and rownum=1)) tycj,")
	                    .append("(select fs from czxx_tydlcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) dlf,")
	                    .append("(select sum(lb||fs) from czxx_tyfjfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) fjf")
	                    .append(" from (select xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,? xn,? xq from view_xsjbxx) a) a");
	
	//查询综合总分信息
	final StringBuilder QUERY_ZHCJXX = new StringBuilder("select xh||xn||xq pk,rownum r,xn,xq,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,xh,xm,bjmc,dcj,zcj,tcj,zxf,zfpm from view_czxx_zhszcp a");
	
	/**
	 * 查询体育课成绩
	 * @param model
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTykcjResult(TycjModel model, String fdyQuerySql)
			throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		String sql = GlobalsVariable.XTDM_JWFLAG_GD.equalsIgnoreCase(Base
				.getJwflag()) ? getQUERY_GDTJKCJXX() : getQUERY_XFTYJCJXX();
		return CommonQueryDAO.commonQuery(sql, aMakeQuery
				.getQueryString()
				+ fdyQuerySql, aMakeQuery.getInputList(), new String[] { "r",
				"xn", "xqmc", "xh", "xm", "bjmc", "kcmc", "kcxz", "cj" }, model);
	}
	
	/**
	 * 查询体育锻炼附加分结果信息
	 * @param model
	 * @param sql
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTydlfjfResult(TycjModel model, String sql,
			String fdyQuerySql) throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		return CommonQueryDAO.commonQuery(sql, aMakeQuery.getQueryString()
				+ fdyQuerySql, aMakeQuery.getInputList(), new String[] { "pk",
				"r", "xn", "xqmc", "xh", "xm", "bjmc", "dlf", "fjf","zf" }, model);
	}
	
	/**
	 * 查询体育附加分数据
	 * @param model
	 * @param sql
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTyfjfResult(DyjcfModel model, String sql,
			String fdyQuerySql) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		return CommonQueryDAO.commonQuery(sql, aMakeQuery
				.getQueryString()
				+ fdyQuerySql, aMakeQuery.getInputList(), new String[] { "pk",
				"r", "xn", "xqmc", "xh", "xm", "bjmc", "zf" }, model);
	}
	
	/**
	 * 传入SQL数组,返回执行结果
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public int[] executeSql(String[] sqlArr) throws SQLException{		
		return dao.runBatch(sqlArr);
	}
	
	/**
	 * 对批处理执行结果进行检测
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean excuteSqlResult(String[] sqlArr) throws SQLException{
		return dao.checkBatch(executeSql(sqlArr));
	}
	
	/**
	 * 查询体育成绩结果
	 * @param model
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTycjResult(TycjModel model, String fdyQuerySql, HashMap<String, String> rs)
			throws Exception {
		String sql = GlobalsVariable.XTDM_JWFLAG_GD.equalsIgnoreCase(Base
				.getJwflag()) ? getQUERY_GDTYCJXX() : getQUERY_XFTYCJXX();
	
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(makeQuery.baseQueryArr,
				makeQuery.baseListQueryArr, model);
		String[] values = makeInputArr(rs, makeQuery, model);
		
		return CommonQueryDAO.commonQuery(sql, makeQuery
				.getQueryString()
				+ fdyQuerySql, values, new String[] { "pk",
				"r", "xn", "xqmc", "xh", "xm", "bjmc", "tycj", "dlf", "fjf","zf" }, model);
	}
	
	private String[] makeInputArr(HashMap<String, String> rs,
			MakeQuery aMakeQuery, TycjModel model) {
		String[] makeInputList = aMakeQuery.getInputList();
		String[] result = makeInputList == null || makeInputList.length == 0 ? new String[5]
				: new String[5 + makeInputList.length];
		result[0] = rs.get("tyc");
		result[1] = rs.get("tykwtydlbj");
		result[2] = StringUtils.isNull(rs.get("tyfjfbl")) || "0".equalsIgnoreCase(rs.get("tyfjfbl")) ? "100" : rs.get("tyfjfbl");
		result[3] = model.getXn();
		result[4] = model.getXq();
		if (makeInputList != null && makeInputList.length > 0) {
			for (int i = 0; i < makeInputList.length; i++) {
				result[i + 5] = makeInputList[i];
			}
		}
		return result;
	}
	
	/**
	 * 查询综合素质测评总成绩信息
	 * @param model
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhcpResult(TycjModel model, String fdyQuerySql)
			throws Exception {
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(makeQuery.baseQueryArr, makeQuery.baseListQueryArr,
				model);

		return CommonQueryDAO.commonQuery(getQUERY_ZHCJXX(), makeQuery
				.getQueryString()
				+ fdyQuerySql, makeQuery.getInputList(), new String[] { "pk",
				"r", "xn", "xqmc", "xh", "xm", "bjmc", "dcj", "zcj", "tcj",
				"zxf", "zfpm" }, model);
	}
	
	/**
	 * 自动计算总分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzf(TycjModel model) throws Exception{
		return dao
				.runProcuder("{call PJPY_CZXX_ZHSZCPCOUNT(?,?,?,?,?)}",
						new String[] { model.getXn(), model.getXq(),Base.getJxjsqnd(),
								Base.getJwflag(),model.getXydm() });
	}

	public String getQUERY_GDTJKCJXX() {
		return QUERY_GDTJKCJXX.toString();
	}

	public String getQUERY_XFTYJCJXX() {
		return QUERY_XFTYJCJXX.toString();
	}

	public String getQUERY_GDTYCJXX() {
		return QUERY_GDTYCJXX.toString();
	}

	public String getQUERY_XFTYCJXX() {
		return QUERY_XFTYCJXX.toString();
	}

	public String getQUERY_ZHCJXX() {
		return QUERY_ZHCJXX.toString();
	}
}
