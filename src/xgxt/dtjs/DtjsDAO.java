package xgxt.dtjs;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DtjsDAO extends CommonQueryDAO {

	/**
	 * 获得党员信息相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得党团建设相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDtjsList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "pxxmdm", "zzzt", "lx","zbdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得党团建设相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDtjsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDtjsList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		if (Base.isNull(msg)) {
			msg = "----请选择-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("yesorno".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "有", "无" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "有", "无" };
			mc = new String[] { "有", "无" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "优秀率", "良好率", "合格率", "不合格率" };
			mc = new String[] { "优秀率", "良好率", "合格率", "不合格率" };
		} else if ("zjlg_dylx".equalsIgnoreCase(lx)) {
			dm = new String[] { "预备党员", "党员" };
			mc = new String[] { "预备党员", "党员" };
		} else if ("xllx".equalsIgnoreCase(lx)) {// 学历类型
			dm = new String[] { "初中","高中", "专科","本科","硕士","博士" };
			mc = new String[] { "初中","高中", "专科","本科","硕士","博士" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 删除所上传文件
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = true;
		String sql = "select " + dzzd + " scdz from " + tableName + " where "
				+ pk + " = ?";
		String wjlj = dao.getOneRs(sql, new String[] { pkValue }, "scdz");
		if (wjlj != null) {
			File file = new File(wjlj);
			if (file.exists()) {
				file.delete();
				sql = "update " + tableName + " set " + dzzd + "='' where "
						+ pk + " = ?";
				flag = dao.runUpdate(sql, new String[] { pkValue });
			}
		}
		return flag;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		String sql = "";
		if ("1".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '年' || to_char(sysdate, 'MM') "
					+ "|| '月' || to_char(sysdate, 'DD') || '日 ' now from dual";
		} else if ("2".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') ||  to_char(sysdate, 'MM') "
					+ "||  to_char(sysdate, 'DD') now from dual";
		}
		String now = dao.getOneRs(sql, new String[] {}, "now");
		return now;
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(dm);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + "='" + pkValue + "'");
		sql.append(" and rownum = 1 ");

		String value = dao.getOneRs(sql.toString(), new String[] {}, dm);
		return value;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 获得党员信息人数
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public String getDyxxRs(String tableName,String pk,String pkValue,String query){
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) rs from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + " = '" + pkValue + "'");
		if(!Base.isNull(query)){
			sql.append(" and " + query);
		}
		String num = dao.getOneRs(sql.toString(), new String[] {}, "rs");
		return num;
	}
	
	/**
	 * 获得党员信息
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDyxx(String[] key,
			String[] colList, String tableName, String pk, String distinct,String orderBy) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		if (colList != null && colList.length > 0) {
			sql.append("select ");
			for (int i = 0; i < colList.length; i++) {
				if (i == 0) {
					if (!Base.isNull(distinct)) {
						sql.append("distinct(" + colList[i] + ")");
					} else {
						sql.append(colList[i]);
					}
				} else {
					sql.append("," + colList[i]);
				}
			}
			sql.append(" from " + tableName);
		}

		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j])) {
					key[j] = "";
				}
			}
		}

		StringBuffer query = new StringBuffer(" where ");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				query.append(pk + "='" + key[i] + "' ");
			} else {
				if (!Base.isNull(key[i])) {
					query.append(" or " + pk + "='" + key[i] + "' ");
				}
			}
		}
		query.append(" order by " + orderBy);
		
		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, colList);
		return list;
	}
	
	/**
	 * 获得党员人数
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDyxxRs(String[] key,
			String[] colList, String tableName, String pk, String distinct,String groupBy,String other_query) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num");
		if (colList != null && colList.length > 0) {
			for (int i = 0; i < colList.length; i++) {
				if (!Base.isNull(distinct)) {
					sql.append(",distinct(" + colList[i] + ")");
				} else {
					sql.append("," + colList[i]);
				}
			}
		}
		sql.append(" from " + tableName);

		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j])) {
					key[j] = "";
				}
			}
		}

		StringBuffer query = new StringBuffer(" where (");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				query.append(pk + "='" + key[i] + "' ");
			} else {
				if (!Base.isNull(key[i])) {
					query.append(" or " + pk + "='" + key[i] + "' ");
				}
			}
		}
		query.append(")");
		query.append(other_query);
		query.append(" group by "+groupBy);
		
		String[] col = new String[colList.length+1];
		for(int i = 0;i<colList.length;i++){
			col[i]=colList[i];
		}
		col[colList.length] = "num";
		
		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, col);
		return list;
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveOtherDylx(String[] tableName, String pk, String[] pkValue)
			throws Exception {
		boolean flag = true;
		int n = 0;

		if ((tableName != null && tableName.length > 0)
				&& (pkValue != null && pkValue.length > 0)) {
			String[] sql = new String[tableName.length * pkValue.length];
			for (int i = 0; i < tableName.length; i++) {
				for (int j = 0; j < pkValue.length; j++) {
					sql[n] = "update " + tableName[i] + " set zzzt='no' where "
							+ pk + "='" + pkValue[j] + "'";
					n++;
				}
			}
			flag = saveArrDate(sql);
		}
		return flag;
	}
	
	/**
	 * 获得党团建设转换等级列表
	 * 
	 * @param zhdj(入党申请,积极分子，发展对象，预备党员)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZhdjList(String zhdj) {

		DAO dao = DAO.getInstance();

		String[] inputValue = new String[] { zhdj };
		String[] outputValue = new String[] { "dm", "mc" };

		StringBuffer sql = new StringBuffer();

		sql.append("select jb dm, mc from dtjs_djdmb where jb >");
		sql.append("(select jb from dtjs_djdmb where mc = ?)");

		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 获得需要转移者的学号
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<String> getZyInfoList(String tableName, String pk,
			String[] pkValue) throws SQLException {

		List<String> list = null;

		if (pkValue != null && pkValue.length > 0) {

			StringBuffer sql = new StringBuffer();
			sql.append("select distinct(xh) from ");
			sql.append(tableName);
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append(" where " + pk + "=? ");
				} else {
					sql.append(" or " + pk + "=? ");
				}
			}
			DAO dao = DAO.getInstance();
			list = dao.getList(sql.toString(), pkValue, "xh");
		}
		return list;
	}
	
	/**
	 * 获得学生党员信息情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDjList() {

		DAO dao = DAO.getInstance();

		// 获得党团等级列表
		String[] inputValue = new String[] {};
		String[] colList = new String[] { "dm", "mc", "jb" ,"tablename"};
		StringBuffer sql = new StringBuffer();

		sql.append("select t.dm, t.mc, t.jb,case ");
		sql.append("when t.mc = '入党申请' then '");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append("'");
		sql.append("when t.mc = '积极分子' then '");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append("'");
		sql.append("when t.mc = '发展对象' then '");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append("'");
		sql.append("when t.mc = '预备党员' then '");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append("'");
		sql.append("when t.mc = '正式党员' then '");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append("'");
		sql.append("end tablename from dtjs_djdmb t order by t.jb ");

		List<HashMap<String, String>> djList = dao.getList(sql.toString(),
				inputValue, colList);

		return djList;

	}
	
	/**
	 * 获得学生党员信息情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsDyInfoList(String[] zyxh) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer("select * from (");
		
		//学生基本信息
		sql.append("select t.xh,t.xm,");
		sql.append("nvl(a." + GlobalsVariable.DTJS_RDSQB + ", 0) "+ GlobalsVariable.DTJS_RDSQB + ",");
		sql.append("nvl(b." + GlobalsVariable.DTJS_JJFZB + ", 0) "+ GlobalsVariable.DTJS_JJFZB + ",");
		sql.append("nvl(c." + GlobalsVariable.DTJS_FZDXB + ", 0) "+ GlobalsVariable.DTJS_FZDXB + ",");
		sql.append("nvl(d." + GlobalsVariable.DTJS_YBDYB + ", 0) "+ GlobalsVariable.DTJS_YBDYB + ",");
		sql.append("nvl(e." + GlobalsVariable.DTJS_DYXXB + ", 0) "+ GlobalsVariable.DTJS_DYXXB + " ");
		sql.append("from view_xsjbxx t ");
		
		//入党申请
		sql.append("left join (select a.xh, count(a.xh) ");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append(" a group by a.xh) a on t.xh = a.xh ");
		
		//积极分子
		sql.append("left join (select b.xh, count(b.xh) ");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append(" b where b.zzzt = 'yes' group by b.xh) b on t.xh = b.xh ");
		
		//发展对象
		sql.append("left join (select c.xh, count(c.xh) ");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append(" c where c.zzzt = 'yes' group by c.xh) c on t.xh = c.xh ");
		
		//预备党员
		sql.append("left join (select d.xh, count(d.xh) ");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append(" d where d.zzzt = 'yes' group by d.xh) d on t.xh = d.xh ");
		
		// 正式党员
		sql.append("left join (select e.xh, count(e.xh) ");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append(" e where e.zzzt = 'yes' group by e.xh) e on t.xh = e.xh ");
		
		// 需要查询学生
		sql.append(")");
		if (zyxh != null && zyxh.length > 0) {
			for (int i = 0; i < zyxh.length; i++) {
				if (i == 0) {
					sql.append(" where xh = ?");
				} else {
					sql.append(" or xh = ?");
				}
			}
		}
		
		//String[] inputValue = new String[] {};
		String[] colList = new String[] { "xh", "xm",
				GlobalsVariable.DTJS_RDSQB, GlobalsVariable.DTJS_JJFZB,
				GlobalsVariable.DTJS_FZDXB, GlobalsVariable.DTJS_YBDYB,
				GlobalsVariable.DTJS_DYXXB };
		
		List<HashMap<String, String>> xsdxInfoList = dao.getList(
				sql.toString(), zyxh, colList);
		
		return xsdxInfoList;
	}
	
	/**
	 * 获得学生基本信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public String[] getStuDefInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ?");
		
		String[] rs = dao.getOneRs(sql.toString(), new String[]{pkValue}, colList);
		
		return rs;
	}
	
	/**
	 * 获得班级团员人数
	 * 
	 * @author luojw
	 */
	public String getBjtyRs(DtjsForm myForm) {

		DAO dao = DAO.getInstance();

		// 班级代码
		String bjdm = myForm.getBjdm();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(xh) tyrs from tyxxb a ");
		sql.append("where exists(select 1 from view_xsjbxx b ");
		sql.append("where a.xh = b.xh and b.bjdm = ? ) ");

		String[] inputValue = new String[] { bjdm };
		String outputValue = "tyrs";
		String tyrs = dao.getOneRs(sql.toString(), inputValue, outputValue);
		return tyrs;
	}
	
	/**
	 * 获得班级党员人数(包括预备党员,入党积极分子)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjDyRs(DtjsForm myForm) {

		DAO dao = DAO.getInstance();

		// 班级代码
		String bjdm = myForm.getBjdm();
		// 学年
		String xn = myForm.getXn();

		StringBuffer sql = new StringBuffer();
		
		// 党员人数
		sql.append("select a.dyrs, b.ybdyrs,c.jjfzrs from ( ");
		sql.append("select count(a.xh) dyrs, ? bjdm ");
		sql.append("from view_dyxx a where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = a.xh ");
		sql.append("and d.bjdm = ?) and a.zzzt = 'yes' ");
		sql.append("and a.xn = ?) a ");
		// 预备党员人数
		sql.append("left join (select count(b.xh) ybdyrs, ? bjdm ");
		sql.append("from view_ybdyxx b where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = b.xh ");
		sql.append("and d.bjdm = ?) and b.zzzt = 'yes' ");
		sql.append("and b.xn = ?) b on a.bjdm = b.bjdm ");
		// 积极分子人数
		sql.append("left join (select count(c.xh) jjfzrs, ? bjdm ");
		sql.append("from view_rdjjfzxx c where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = c.xh ");
		sql.append("and d.bjdm = ?) and c.zzzt = 'yes' ");
		sql.append("and c.xn = ?) c on a.bjdm = c.bjdm ");
		
		String[] inputValue = new String[] { bjdm, bjdm, xn, bjdm, bjdm, xn,bjdm, bjdm, xn };
		String[] outputValue = new String[] { "dyrs", "ybdyrs","jjfzrs" };
		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue,
				outputValue);
		return map;
	}
	
	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public String getBjRs(DtjsForm myForm) {
		
		DAO dao = DAO.getInstance();
		
		// 班级代码
		String bjdm = myForm.getBjdm();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t.xh) bjrs from view_xsjbxx t where t.bjdm = ?");
		
		String[] inputValue = new String[] { bjdm };
		String outputValue = "bjrs";
		String bjrs = dao.getOneRs(sql.toString(), inputValue, outputValue);
		return bjrs;
	}
	
	/**
	 * 获得用户类型相关限制条件
	 * 
	 * @param map
	 * @author luojw
	 */
	public String getUserTypeQuery(HashMap<String, String> map) {

		StringBuilder query = new StringBuilder();

		String userName = map.get("userName");// 用户名
		boolean fdy = Boolean.parseBoolean(map.get("fdy"));// 辅导员
		boolean bzr = Boolean.parseBoolean(map.get("bzr"));// 班主任
		boolean zbfzr = Boolean.parseBoolean(map.get("zbfzr"));// 支部负责人

		query.append(" and (");

		// 是辅导员
		if (fdy) {
			query.append(" exists (select 1 from fdybjb b ");
			query.append(" where a.bjdm = b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}
		// 是班主任
		if (bzr) {
			if (fdy) {
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb b ");
			query.append(" where a.bjdm = b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}
		// 是支部负责人
		if (zbfzr) {
			if (fdy || bzr) {
				query.append(" or ");
			}
			query.append(" exists (select 1 from zjlg_zbmc b,zjlg_zbfp c ");
			query.append(" where a.bjdm = c.bjdm and b.zbdm = c.zbdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}

		if (!fdy && !bzr && !zbfzr) {
			query.append(" 1 = 1");
		}

		query.append(" )");

		return query.toString();
	}
}
