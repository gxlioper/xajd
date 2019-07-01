package xgxt.pjpy.czxx.zycj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.dycj.DyjcfModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZycjDAO {

	DAO dao = DAO.getInstance();
	
	//智育成绩查询
	final StringBuffer QUERY_ZYCJXX = new StringBuffer("select xh,xm,xb,nj,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,")
	                       .append("xydm,zydm,bjdm,xymc,zymc,bjmc,xn,xq,")
	                       .append("nvl(case when instr(to_char(kscj),'.',1,1")
	                       .append(")=1 then '0'||to_char(kscj) else to_char(")
	                       .append("kscj) end, '0') kscj,nvl(case when instr")
	                       .append("(to_char(kccj),'.',1,1)=1 then '0'||to_char")
	                       .append("(kccj) else to_char(kccj) end, '0') kccj,")
	                       .append("nvl(case when instr(to_char(zyfjf),'.',1,")
	                       .append("1)=1 then '0'||to_char(zyfjf) else to_char")
	                       .append("(zyfjf) end, '0') zyfjf,nvl(case when ")
	                       .append("instr(to_char(cj),'.',1,1)=1 then '0'||")
	                       .append("to_char(cj) else to_char(cj) end, '0') cj")
	                       .append(",rownum r from (select a.*,round((nvl(kscj")
	                       .append(",0)*?/100 + nvl(kccj,0)*?/100 + nvl(zyfjf")
	                       .append(",0)*?/100),2) cj from (select a.*,(select ")
	                       .append("round(avg(cj),2) from view_zhhcjb b where khfs like")
	                       .append(" '考试%' and kcmc not like '%体育%' and ")
	                       .append("a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,")
	                       .append("(select round(avg(cj),2) from view_zhhcjb b where ")
	                       .append("khfs like '考查%' and kcmc not like '%体育%'")
	                       .append(" and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)")
	                       .append(" kccj,(select sum(nvl(lb,'')||nvl(fs,'0'))")
	                       .append(" zf from czxx_zyfjfb b where a.xh=b.xh and")
	                       .append(" a.xn=b.xn and a.xq=b.xq) zyfjf from (select")
	                       .append(" xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc")
	                       .append(",? xn,? xq from view_xsjbxx) a) a) a");
	
	/**
	 * 查询德育附加分数据
	 * @param model
	 * @param sql
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZyfjfResult(DyjcfModel model, String sql,
			String fdyQuerySql) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		return CommonQueryDAO.commonQuery(sql, aMakeQuery
				.getQueryString()
				+ fdyQuerySql, aMakeQuery.getInputList(), new String[] { "pk",
				"r", "xn", "xq", "xh", "xm", "bjmc", "zf" }, model);
	}
	
	/**
	 * 查询智育成绩结果
	 * @param model
	 * @param rs
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZycjResult(ZycjModel model,
			HashMap<String, String> rs, String fdyQuerySql) throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		String[] inputList = makeInputArr(rs, aMakeQuery, model);

		return CommonQueryDAO.commonQuery(getQUERY_ZYCJXX(), aMakeQuery
				.getQueryString()
				+ fdyQuerySql, inputList, new String[] { "r", "xn", "xqmc", "xh",
				"xm", "bjmc", "kscj", "kccj", "zyfjf", "cj" }, model);
	}

	private String[] makeInputArr(HashMap<String, String> rs,
			MakeQuery aMakeQuery, ZycjModel model) {
		String[] makeInputList = aMakeQuery.getInputList();
		String[] result = makeInputList == null || makeInputList.length == 0 ? new String[5]
				: new String[5 + makeInputList.length];
		result[0] = rs.get("zykskmcjbl");
		result[1] = rs.get("zykckmcjbl");
		result[2] = rs.get("zyfjfbl");
		result[3] = model.getXn();
		result[4] = model.getXq();
		if (makeInputList != null && makeInputList.length > 0) {
			for (int i = 0; i < makeInputList.length; i++) {
				result[i + 5] = makeInputList[i];
			}
		}
		return result;
	}

	public String getQUERY_ZYCJXX() {
		return QUERY_ZYCJXX.toString();
	}
}
