package xgxt.pjpy.czxx.dycj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DyjcfDAO {
	
	//查询德育成绩各分所占比例
	final StringBuffer QUERY_DYCJBL = new StringBuffer("select nvl(dyjcfbl,0) ")
	          .append("dyjcfbl,nvl(dyssgffbl,0) dyssgffbl,nvl(dyfjfbl,0) dyfj")
	          .append("fbl,nvl(zykskmcjbl,0) zykskmcjbl,nvl(zykckmcjbl,0) zykc")
	          .append("kmcjbl,nvl(zyfjfbl,0) zyfjfbl,nvl(tycjbl,0) tyc,nvl(tyk")
	          .append("wtydlbl,0) tykwtydlbj,nvl(tyfjfbl,100) tyfjfbl from czxx_zhszcpfblszb");
	
	//查询德育成绩各分及总分信息
	
	public final StringBuffer QUERY_DYCJXX = new StringBuffer("select xh,xn,xq,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,cj,fjf,gff,nvl(case when instr(to_char(dyzf),'.',1,1)=1 then '0'||to_char(dyzf) else to_char(dyzf) end, '0') dyzf,bjdm,bjmc,xm,rownum r ")
							.append("from (select a.*,")
						    .append("nvl(case when instr(to_char(b.cj),'.',1,1)=1 then '0'||to_char(b.cj) else to_char(b.cj) end, '0') cj,nvl(case when instr(to_char(c.fjf),'.',1,1)=1 then '0'||to_char(c.fjf) else to_char(c.fjf) end, '0') fjf,nvl(case when instr(to_char(d.gff),'.',1,1)=1 then '0'||to_char(d.gff) else to_char(d.gff) end, '0') gff,((nvl(b.cj,0)*?/100) + ")
						    .append("(nvl(c.fjf,0)*?/100) + (nvl(d.gff,0)*?/100")
						    .append(")) dyzf from (select xh,xm,xydm,zydm,bjdm")
						    .append(",nj,xb,bjmc,? xn,? xq from ")
						    .append("view_xsjbxx a) a left join czxx_dyjcfb b ")
						    .append("on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ")
						    .append("left join (select xh,xn,xq,sum(nvl(lb,'')")
						    .append("||nvl(fs,0)) fjf from czxx_dyfjfb group ")
						    .append("by xh,xn,xq) c on a.xh=c.xh and a.xn=c.xn")
						    .append(" and a.xq=c.xq left join ssshgff d on ")
						    .append("a.xh=d.xh and a.xn=d.xn and a.xq=d.xq) a");
	
	
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 查询德育基础分信息 
	 * @param model
	 * @param fdyQuerySql 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyjcfResult(DyjcfModel model, String sql,
			String fdyQuerySql) throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		return CommonQueryDAO.commonQuery(sql, aMakeQuery
				.getQueryString()
				+ fdyQuerySql, aMakeQuery.getInputList(), new String[] { "pk",
				"r", "xn", "xqmc", "xh", "xm", "bjmc", "cj" }, model);
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
	 * 查询德育附加分数据
	 * @param model
	 * @param sql
	 * @param fdyQuerySql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyfjfResult(DyjcfModel model, String sql,
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
	 * 查询德育成绩各分所占比例
	 * @return
	 */
	public HashMap<String, String> queryDycjBl() {
		return dao.getMapNotOut(QUERY_DYCJBL.toString(), new String[]{});
	}
	
	/**
	 * 查询德育成绩信息
	 * @param model
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDycjResult(DyjcfModel model,
			HashMap<String, String> rs, String isFdyStr) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(aMakeQuery.baseQueryArr,
				aMakeQuery.baseListQueryArr, model);
		String[] inputList = makeInputArr(rs, aMakeQuery, model);
		
		return CommonQueryDAO.commonQuery(QUERY_DYCJXX.toString(), aMakeQuery
				.getQueryString() + isFdyStr, inputList, new String[] { "r", "xn"
			    , "xqmc","xh", "xm", "bjmc", "cj", "gff", "fjf", "dyzf" }, model);
	}

	private String[] makeInputArr(HashMap<String, String> rs,
			MakeQuery aMakeQuery, DyjcfModel model) {
		String[] makeInputList = aMakeQuery.getInputList();
		String[] result = makeInputList == null || makeInputList.length == 0 
			    ? new String[5]
				: new String[5 + makeInputList.length];
		result[0] = rs.get("dyjcfbl");
		result[2] = rs.get("dyssgffbl");
		result[1] = rs.get("dyfjfbl");
		result[3] = model.getXn();
		result[4] = model.getXq();
		if (makeInputList != null && makeInputList.length > 0) {
			for (int i = 0; i < makeInputList.length; i++) {
				result[i + 5] = makeInputList[i];
			}
		}
		return result;
	}
}
