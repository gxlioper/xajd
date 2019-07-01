package xgxt.xsh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XshDAO {
	DAO dao = DAO.getInstance();

	/**
	 * 获取自定义表单字段
	 * 
	 * @param realTable
	 * @return
	 */
	public List<HashMap<String, String>> getBdZd(String realTable) {

		String sql = "select zdid,zdmc,zdlx,zdcd from ty_bdsz where tabname = ?  order by zdlx,zdpx";
		return dao.getList(sql, new String[] { realTable }, new String[] {
				"zdid", "zdmc", "zdlx", "zdcd" });
	}

	/**
	 * 获得自定义字段选项列
	 * 
	 * @param tableName
	 * @param xn
	 * @return
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> getOps(
			String realTable) {
		String sql = "select b.zdid,a.opid,a.opmc from ty_bdszxxb a "
				+ "left join ty_bdsz b on a.szbzj= b.zdid||b.tabname "
				+ "where b.tabname = ?  order by b.zdpx";
		return dao.getOptionList(sql, new String[] { realTable }, new String[] {
				"zdid", "opid", "opmc" });
	}

	/**
	 * 查询自定义字段数据
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> zdyColList(String tableName)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select zdid,zdmc from ty_bdsz where "
				+ " tabname = ? and cxxs = '显示' order by cxxspx";
		return dao.getList(sql, new String[] { tableName }, new String[] {
				"zdid", "zdmc" });
	}

	/**
	 * 查询含自定义字段数据
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getZdyQueryList(String tableName,
			XshModel myModel, String[] colList, String[] zdyCol,
			String realTable, String[] pkKey) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] {};
		String[] queryLikeList = new String[] { "hddd", "hdzt", "fqr", "xczt",
				"xcdd", "xckh", "stmc", "stcsr", "stxz" };
		String[] inputList = new String[] {};
		String query = "";
		if (queryList != null) {
			MakeQuery makeQuery = new MakeQuery();
			makeQuery.makeQuery(queryList, queryLikeList, myModel);
			inputList = makeQuery.getInputList();
			query = makeQuery.getQueryString();

			if ("xsh_stglb".equals(tableName)) {
				query += " and stmc||stxz||bmdm<>'学生会学校无'";
			}
		}
		int size = colList.length - 1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for (int i = 0; i < (size); i++) {
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(" rownum r,a.");
		sqlBuffer.append(colList[size]);

		for (int i = 0; i < zdyCol.length; i++) {
			sqlBuffer
					.append(",(select bcnr from ty_bdsz_bcnr where tabname = '");
			sqlBuffer.append(realTable);
			sqlBuffer.append("' and zdid = '");
			sqlBuffer.append(zdyCol[i]);
			sqlBuffer.append("' and zbid = ");
			for (int j = 0; j < pkKey.length; j++) {
				sqlBuffer.append("a.");
				sqlBuffer.append(pkKey[j]);
				if (j != pkKey.length - 1) {
					sqlBuffer.append("||");
				}
			}
			sqlBuffer.append(") ");
			sqlBuffer.append(zdyCol[i]);
		}
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" a ");
		String[] zcolList = new String[colList.length + zdyCol.length];
		for (int i = 0; i < colList.length; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colList.length + i] = zdyCol[i];
		}
		return CommonQueryDAO.commonQuery(sqlBuffer.toString(), query,
				inputList, zcolList, myModel);
	}

	/**
	 * 批量删除含自定义字段数据
	 * 
	 * @param tableName
	 * @param pkV
	 * @param primaryKey
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String tableName, String[] pkV, String primaryKey)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] sqlMap = new String[pkV.length];
		String[] sqlZdyMap = new String[pkV.length];

		for (int i = 0; i < pkV.length; i++) {
			sqlMap[i] = "delete from " + tableName + " where " + primaryKey
					+ " = '" + pkV[i] + "'";
			sqlZdyMap[i] = "delete from ty_bdsz_bcnr where zbid = '" + pkV[i]
					+ "' and tabname='" + tableName + "'";
		}

		int[] result = dao.runBatch(sqlMap);

		boolean update = dao.checkBatch(result);

		if (update) {
			result = dao.runBatch(sqlZdyMap);
			update = dao.checkBatch(result);
		} else {
			update = false;
		}

		return update;
	}

	/**
	 * 返回单条自定义数据
	 * 
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String[] colList, String[] zdyzdList,
			String pkCol, String pk) {
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName,
				colList, pkCol, pk);
		ArrayList<String[]> zdysjj = CommonQueryDAO.commonQueryNotFy(
				"ty_bdsz_bcnr", " where tabname = ? and zbid = ?",
				new String[] { realTable, pk },
				new String[] { "zdid", "bcnr" }, null);
		for (int i = 0; i < zdysjj.size(); i++) {
			String[] zdyTmp = zdysjj.get(i);
			rs.put(zdyTmp[0], zdyTmp[1]);
		}
		return rs;
	}

	/**
	 * 获取教师列表
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> getJs(XshModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] {};
		String[] queryLikeList = new String[] { "yhm", "xm", "zmc" };
		String[] inputList = new String[] {};

		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(queryList, queryLikeList, model);
		inputList = makeQuery.getInputList();
		String query = makeQuery.getQueryString();

		String sql = "select rownum r,a.yhm,a.xm,b.zmc from yhb a left join yhzb b on a.zdm=b.zdm";
		return CommonQueryDAO.commonQuery(sql, query, inputList, new String[] {
				"yhm", "xm", "zmc" }, model);
	}

	/**
	 * 获得干部列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGbList(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// 干部类型
		String gblx = map.get("gblx");
		// 是否社团
		String isst = map.get("isst");
		// 社团名称
		String stmc = map.get("stmc");

		StringBuilder sql = new StringBuilder();

		sql
				.append("select t.dm || t.gblx || t.stmc pk, t.* from (select a.dm, ");
		sql
				.append("a.gblx,(select b.mc from bjxh_xsgblxb b where a.gblx = b.dm) lxmc, ");
		sql
				.append("a.gbmc,case  when gblx = '002' then 'ty' else 'no' end stmc, ");
		sql.append("case when gblx = '002' then 'yes' else 'no' end isst ");
		sql.append("from bjxh_sttygbb a ");
		sql.append("union all select a.dm,'002' gblx, ");
		sql
				.append("(select b.mc from bjxh_xsgblxb b where b.dm = '002') lxmc, ");
		sql.append("a.gbmc,a.stmc,'yes' isst from bjxh_stdygbb a) t ");
		sql.append("where 1 = 1 ");
		sql.append(Base.isNull(gblx) ? "" : " and t.gblx = '" + gblx + "'");
		sql.append(Base.isNull(isst) ? "" : " and t.isst = '" + isst + "'");

		if ("no".equalsIgnoreCase(stmc)) {
			sql.append(" and t.stmc = 'no'");
		} else if ("ty".equalsIgnoreCase(stmc)) {
			sql.append(" and t.stmc = 'ty'");
		} else {
			sql.append(Base.isNull(stmc) ? "" : " and (stmc = 'ty' or stmc = '"
					+ stmc + "')");
		}

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "pk", "gbmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * 社团列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getStList() {
		String sql = "select stmc||stxz||bmdm pk, stmc from xsh_stglb ";
		// "where stmc||stxz||bmdm<>'学生会学校无'";
		return dao.getList(sql, new String[] {}, new String[] { "pk", "stmc" });
	}

	/**
	 * 批量将学生加入社团
	 * 
	 * @param xh
	 * @param stmc
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc) throws SQLException {
		String[] sqlArr = new String[xh.length * 2];
		int n = 0;
		for (int i = 0; i < xh.length; i++) {
			sqlArr[n] = "delete from xsh_cyglb where xh='" + xh[i]
					+ "' and stv='" + stmc + "'";
			n++;
			sqlArr[n] = "insert into xsh_cyglb (xh,stv) values ('" + xh[i]
					+ "','" + stmc + "')";
			n++;
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	/**
	 * 批量将学生加入社团
	 * 
	 * @param xh
	 * @param stmc
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc,String nd) throws SQLException {
		String[] sqlArr = new String[xh.length * 2];
		int n = 0;
		for (int i = 0; i < xh.length; i++) {
			sqlArr[n] = "delete from xsh_cyglb where xh='" + xh[i]
					+ "' and stv='" + stmc + "'";
			n++;
			sqlArr[n] = "insert into xsh_cyglb (xh,stv,zcnd) values ('" + xh[i]
					+ "','" + stmc + "','"+nd+"')";
			n++;
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	/**
	 * 修改职务
	 * 
	 * @param pkValue
	 * @param zwdm
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZw(String[] pkValue, String zwdm) throws SQLException {
		String[] sqlArr = new String[pkValue.length];

		for (int i = 0; i < pkValue.length; i++) {
			sqlArr[i] = "update xsh_cyglb set zwdm='"+zwdm+"'";
			if(Base.isNull(zwdm)){
				sqlArr[i] +=" ,sfyxstgb='否' ";
			}
			sqlArr[i] +=" where xh||stv='" + pkValue[i] + "'";
		}
	
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	/**
	 * 设置是否优秀社团干部
	 * 
	 * @param pkValue
	 * @param sfyxstgb
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSfyxgb(String[] pkValue, String sfyxstgb)
			throws SQLException {

		String[] sqlArr = new String[pkValue.length];

		for (int i = 0; i < pkValue.length; i++) {
			sqlArr[i] = "update xsh_cyglb set sfyxstgb='" + sfyxstgb
					+ "' where xh||stv='" + pkValue[i] + "'";
		}

		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	/**
	 * 根据用户名取所在社团信息
	 * 
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getStInfo(String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xsh_stglb where (stmc||stxz||bmdm in ");
		sql.append("(select stv from xsh_cyglb where xh=?) or fzr=?)");
		sql.append(" and stmc||stxz||bmdm<>'学生会学校无'");

		return dao.getListNotOut(sql.toString(), new String[] { userName,
				userName });
	}

	/**
	 * DWR调用，获取社团详细信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> getstInfo(String pkValue) {
		String sql = "select * from xsh_stglb where stmc||stxz||bmdm=?";
		return dao.getMap(sql, new String[] { pkValue }, new String[] { "stmc",
				"stxz", "bmdm" });
	}

	/**
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.zzmmmc,to_number(sysd)-to_number(nl) nl from( ");
		sql.append(" select xh,xm,xb,xymc,nj,zymc,bjmc,zzmmmc, ");
		sql.append(" case when csrq is not null then to_char(to_date(csrq, 'yyyymmdd'), 'yyyy') ");
		sql.append(" else  ''  end nl,  to_char(sysdate, 'yyyy')sysd ");
		sql.append(" from view_xsxxb where xh = ?)a ");
		return dao.getMap(sql.toString(), new String[] { xh }, new String[] {"xh","xm","xymc","nj",
			"zymc","bjmc","zzmmmc","nl","xb"});
	}
	
	/**
	 * 获取社团干部所管理的部门
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getStgbst(String xh){
		StringBuilder sql=new StringBuilder();
		DAO dao=DAO.getInstance();
		sql.append(" select * from ( ");
		sql.append(" select stmc||stxz||bmdm pk, stmc from XSH_STGLB a ,(  ");
		sql.append(" select stv from xsh_cyglb a,bjxh_sttygbb b  ");
		sql.append(" where  a.zwdm=b.dm||b.gblx||'ty' and b.ywglqx='yes' and a.xh=? ");
		sql.append("  )b where a.stmc||stxz||bmdm=b.stv  ");
		
		sql.append("  union select stmc||stxz||bmdm pk, stmc from XSH_STGLB a ,(  ");
		sql.append(" select stv from xsh_cyglb a,bjxh_stdygbb b  ");
		sql.append(" where a.stv = b.stmc  and b.ywglqx='yes' and a.xh=? )b  ");
		sql.append(" where a.stmc||stxz||bmdm=b.stv) ");
		return dao.getList(sql.toString(), new String[]{xh,xh}, new String[]{"pk","stmc"});
	}
}
