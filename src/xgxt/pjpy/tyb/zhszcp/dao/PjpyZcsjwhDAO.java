package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyZcsjwhDAO {

	DAO dao = DAO.getInstance();
	
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 查询综测代码信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryZctjdmList(PjpyZctjszModel model) {
		MakeQuery queryObject = new MakeQuery();
		try {
			queryObject.makeQuery(new String[] { "fdm", "sfwh", "dmjb" }, null, model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return dao
				.getList(
						"select DM,MC,BL,XZF,LB,BM,ZD,FDM,TJ,SFPLZJ,SHJB,DMJB,MRF,ZJ,ZJZ,SFWH from pjpy_zctjszb "
								+ queryObject.getQueryString(), queryObject
								.getInputList(), new String[] { "DM", "MC",
								"BL", "XZF", "LB", "BM", "ZD", "FDM", "TJ",
								"SFPLZJ", "SHJB", "DMJB", "MRF", "SFWH" });
	}
	
	/**
	 * 查询综合素质测评成绩维护信息
	 * @param model
	 * @param titleList
	 * @param opList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZcsjwhResult(PjpyZhszcpModel model,
			List<HashMap<String, String>> titleList, String[] opList, boolean isPage)
			throws Exception {
		StringBuilder sql = new StringBuilder(
				"select a.*,rownum r from (select a.* from (select a.xh,a.xm,a.xydm,a.bjdm,a.zydm,a.xymc,a.nj,a.zymc,a.bjmc,b.*,'"
						+ model.getXn()
						+ "' xn,(select xqmc from xqdzb where xqdm='"
						+ model.getXq() + "') xqmc,'" + model.getNd() + "' nd from view_xsjbxx a left join (select pk,xh zcxh");
		String xmdm = "";
		String mrf = "";
		for (HashMap<String, String> xmMap : titleList) {
			mrf = StringUtils.isNull(xmMap.get("MRF")) || "0".equalsIgnoreCase(mrf) ? "" : xmMap.get("MRF");
			xmdm = xmMap.get("DM");
			sql
					.append(",(case when max((select fs from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) is null then '' else max((select fs from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) end) ");
			sql.append(xmMap.get("MC"));
		}
		sql.append(" from view_pjpy_zhszcpb a ");
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm",
					"xn", "xq", "nd" }, new String[] { "xh", "xm" }, model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		sql.append(makeQuery.getQueryString());
		sql.append(" and exists (select 1 from pjpy_zctjszb b where fdm='");
		sql.append(model.getZcfdm());
		sql.append("' and dmjb='");
		sql.append(model.getZcdmjb());
		sql.append("' and a.dm=b.dm)");
		sql.append(" group by pk,xh,xm,xymc,nj,zymc,bjmc,xn,xqmc,nd order by xymc,nj,bjmc,xh) b on a.xh=b.zcxh) a");
		
		MakeQuery query = new MakeQuery();
		try {
			query.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm" }, new String[] { "xh", "xm" }, model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		sql.append(query.getQueryString());
		sql.append(") a");
		
		String[] inputList = makeQuery.getInputList();
		inputList = dao.unionArray(inputList, query.getInputList());
		if (isPage) {
			return CommonQueryDAO.commonQuery(sql.toString(), "", inputList, opList, model);
		} else {
			sql.append(" where exists (select 1 from pjpy_zhszcpb b where a.xh=b.xh and b.xn='");
			sql.append(StringUtils.isNull(model.getXn()) ? "无" : model.getXn());
			sql.append("'");
			sql.append(" and b.nd='");
			sql.append(StringUtils.isNull(model.getNd()) ? "无" : model.getNd());
			sql.append("'");
			sql.append(" and b.xq='");
			sql.append(StringUtils.isNull(model.getXq()) ? "无" : model.getXq());
			sql.append("')");
			return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputList, opList, model);
		}
		
	}
	
	public boolean dellrf(String[] pkValues){
		boolean flag = false;
	
		String[] sqlArr = new String[pkValues.length];
		
		for(int i=0;i<pkValues.length;i++){
			sqlArr[i] = "delete from pjpy_zhszcpb where xh||xn||xq='"+pkValues[i]+"'";
		}
		
		try {
			dao.runBatch(sqlArr);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean initZcf(PjpyZhszcpModel model){
		boolean flag = false;
		String[] sqlArray = new String[2];
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from pjpy_zhszcpb a");
		sql.append(" where exists(select 1 from view_xsjbxx b where b.xydm='");
		sql.append(model.getXydm());
		sql.append("' and b.xh=a.xh)");
		sql.append(" and a.xn='");
		sql.append(model.getXn());
		sql.append("' and xq='");
		sql.append(model.getXq());
		sql.append("' and nd='");
		sql.append(model.getNd());
		sql.append("' and exists (select 1 from pjpy_zctjszb b where b.dmjb='");
		sql.append(model.getZcdmjb());
		sql.append("' and b.fdm='");
		sql.append(model.getZcfdm());
		sql.append("' and a.dm = b.dm)");
		
		sqlArray[0] = sql.toString();
		
		sql = new StringBuilder();
		
		sql.append("insert into pjpy_zhszcpb a");
		sql.append(" select xh,'");
		sql.append(model.getXn());
		sql.append("' xn,'");
		sql.append(model.getXq());
		sql.append("' xq,'");
		sql.append(model.getNd());
		sql.append("' nd,dm,dmjb,mrf fs,'' pm");
		sql.append(" from view_xsjbxx, pjpy_zctjszb");
		sql.append(" where xydm='");
		sql.append(model.getXydm());
		sql.append("' and dmjb='");
		sql.append(model.getZcdmjb());
		sql.append("' and fdm='");
		sql.append(model.getZcfdm());
		sql.append("'");
		
		sqlArray[1] = sql.toString();
		
		try {
			dao.runBatch(sqlArray);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
