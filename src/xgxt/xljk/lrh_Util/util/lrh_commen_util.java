package xgxt.xljk.lrh_Util.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CheckPower;
import xgxt.utils.New_Random_ID;

public class lrh_commen_util {
	New_Random_ID newId = new New_Random_ID();

	HttpServletRequest request;

	public lrh_commen_util(HttpServletRequest request) {
		this.request = request;
	}
	
	public lrh_commen_util() {}
	// 根据表中其他字段值查询结果，传入的参数是其他字段的字段名，字段值，表名和主键，返回的是集合
	public List<HashMap<String, String>> Find_By_OtherKey(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues) {
		DAO mydao = DAO.getInstance();
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " = ? ";
			strbuf.append(str);
		}
		List<HashMap<String, String>> lii = mydao.getList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
	}

	public List Find_By_OtherKey3(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues, String[] like,
			String[] likeVal, String[] order) {
		DAO mydao = DAO.getInstance();
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}

		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				str = " and " + like[i] + " like ? ";
				strbuf.append(str);
				val.append(likeVal[i] + s1);
			} else {
				str = " and " + like[i] + " like '" + likeVal[i] + "'";
				strbuf.append(str);
			}
		}
		String[] values = val.toString().split(s1);
		List lii = mydao.getList(strbuf.toString(), values, tabTitle);
		return lii;
	}

	public List Find_By_OtherKey2(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		DAO mydao = DAO.getInstance();
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " = ?  ";
			strbuf.append(str);
		}
		List<Vector<String>> lii = mydao.getVectorList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
	}

	public List<HashMap<String, String>> Find_By_OtherKey4(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues,
			String[] like, String[] likeVal, String[] order) {
		DAO mydao = DAO.getInstance();
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());

		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}

		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				str = " and " + like[i] + " like ? ";
				strbuf.append(str);
				val.append(likeVal[i] + s1);
			} else {
				str = " and " + like[i] + " like '" + likeVal[i] + "'";
				strbuf.append(str);
			}
		}

		List<HashMap<String, String>> lii = null;
		if (val.toString() != null && !val.toString().equalsIgnoreCase("")) {
			String[] values = val.toString().split(s1);
			lii = mydao.getList(strbuf.toString(), values, tabTitle);
		} else {
			String[] values = {};
			lii = mydao.getList(strbuf.toString(), values, tabTitle);
		}
		return lii;
	}

	public List Find_By_OtherKey5(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues, String[] like,
			String[] likeVal, String[] order, String[] and) {
		DAO mydao = DAO.getInstance();
		List li = null;
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		// int k=0;
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}

		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				str = " and " + like[i] + " like ? ";
				strbuf.append(str);
				val.append(likeVal[i] + s1);
			} else {
				str = " and " + like[i] + " like '" + likeVal[i] + "'";
				strbuf.append(str);
			}
		}
		for (int i = 0; i < and.length; i++) {

			strbuf.append(and[i]);
		}

		if (val.toString() != null && !val.toString().equalsIgnoreCase("")) {
			String[] values = val.toString().split(s1);
			li = mydao.getList(strbuf.toString(), values, tabTitle);
		} else {
			String[] values = {};
			li = mydao.getList(strbuf.toString(), values, tabTitle);
		}
		return li;
	}

	// 取表头，根据传入一个表名，和表中字段数组，取出表头
	public List Get_Table_Title(String tableName, String[] zdm) {
		DAO mydao = DAO.getInstance();
		String sql = "select rownum 行号, a.";
		String str = "";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		for (int i = 0; i < zdm.length; i++) {
			strbuf.append(zdm[i]);
			strbuf.append((i == zdm.length - 1 ? " " : ",a."));
		}
		str = " from " + tableName + " a ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, tableName);
		List titles = mydao.arrayToList(tabTitle, tabTitleCN);
		return titles;
	}

	/**
	 * @category 无条件查询,查询结果通过userSql定义,并且需要输入表名<font color=red>
	 *           userSql一定要按规范写,并且需要有关键字from </font>
	 * @return
	 */
	public List Find_All(String userSql, String tableName) {
		DAO mydao = DAO.getInstance();
		List li = null;
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (userSql != null && !userSql.equalsIgnoreCase("")) {
			String[] temp = userSql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		li = mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
		return li;
	}

	public List<HashMap<String, String>> get_xyList() {
		DAO mydao = DAO.getInstance();
		List<HashMap<String, String>> li = null;
		li = mydao.getXyList();
		return li;
	}

	public List get_zyList(String xydm) {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getZyList(xydm);
		return li;
	}

	public List get_bjList(String xydm, String zydm) {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getBjList(xydm, zydm);
		return li;
	}

	public List get_bjList2(String xydm, String zydm, String nj) {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getBjList(xydm, zydm, nj);
		return li;
	}

	public List get_xnndList() {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getXnndList();
		return li;
	}

	public List get_xqList() {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getXqList();
		return li;
	}

	public List get_njList() {
		DAO mydao = DAO.getInstance();
		List li = null;
		li = mydao.getNjList();
		return li;
	}

	public List<HashMap<String, String>> get_dmwhb_dmList(String ss) {
		List<HashMap<String, String>> li = null;
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "SS" };
		String[] otherKeyValues = { ss };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		return li;
	}

	public List<HashMap<String, String>> get_dmwhb_dmList2(String ss) {
		List<HashMap<String, String>> li = null;
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC,a.XN_ID,a.SS from";
		String[] otherKeys = { "SS" };
		String[] otherKeyValues = { ss };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		return li;
	}

	public String xljk_dmwhb_dmzh(String dmh) {
		List<HashMap<String, String>> li = null;
		String dmmc = "";
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "DMH" };
		String[] otherKeyValues = { dmh };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			dmmc = map.get("DMMC");
		}
		return dmmc;
	}

	public String xljk_dmwhb_dmzh2(String dmmc, String ss) {
		List<HashMap<String, String>> li = null;
		String dmh = "";
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "DMMC", "SS" };
		String[] otherKeyValues = { dmmc, ss };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			dmh = map.get("DMH");
		}
		return dmh;
	}

	public String getxymc_byxydm(String xydm) {
		DAO mydao = DAO.getInstance();
		String xymc = mydao.getXymcById(xydm);
		return xymc;
	}

	public List get_rcgl_getList(String ss) {
		List li = null;
		String tableName = "RCGL_DMWHB";
		String usersql = "select a.XN_ID,a.MKMC from";
		String[] otherKeys = { "MKSS" };
		String[] otherKeyValues = { ss };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		return li;
	}

	public String get_rcgl_getMKMC(String XN_ID) {
		List<HashMap<String, String>> li = null;
		String tableName = "RCGL_DMWHB";
		String usersql = "select a.XN_ID,a.MKMC from";
		String mkmc = "";
		String[] otherKeys = { "XN_ID" };
		String[] otherKeyValues = { XN_ID };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			mkmc = map.get("MKMC");
		}
		return mkmc;
	}

	public boolean checkUsrPower(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select a.dxq from yhqxb a where a.yhm=? ";
		String dxq = dao.getOneRs(sql, new String[] { userName }, "dxq");
		return ("1".equalsIgnoreCase(dxq) ? true : false);
	}

	public boolean checkUsrPower2(String userName, String dyym) {
		boolean flag = CheckPower.checkUsrPower(userName, dyym);
		return flag;
	}

	public boolean check_user_input(String input) {
		boolean flag = false;
		int index = input.indexOf("'");
		if (-1 != index) {
			return flag;
		} else {
			flag = true;
			return flag;
		}
	}

	public String xljk_dmwhb_add(String dmh, String nr, String ss) {
		String tableName = "xljk_dmwhb";
		String xn_id = newId.new_xnid("xljk_dmwhb");
		String[] columns = { "XN_ID", "DMH", "DMMC", "SS" };
		String[] values = { xn_id, dmh, nr, ss };
		String result = "";
		boolean flag = StandardOperation.insert(tableName, columns, values,
				this.request);
		if (false == flag) {
			result = "insert_fail";
		} else if (true == flag) {
			result = "insert_success";
		}
		return result;
	}

	public String get_userType(String userDep) {
		DAO mydao = DAO.getInstance();
		String userType = mydao.getUserType(userDep);
		return userType;
	}

	public String get_rychmc_bydm(String rychdm) {
		List<HashMap<String, String>> li = null;
		String rychmc = "";
		String tableName = "rychdmb";

		String usersql = "select a.RYCHDM,a.RYCHMC from";
		String[] otherKeys = { "RYCHDM" };
		String[] otherKeyValues = { rychdm };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			rychmc = map.get("RYCHMC");
		}
		return rychmc;
	}

	/**返回教育形式的信息
	 * @throws SQLException */
	public HashMap<String,String> doJyxsOneRs(String pk,String type,String dmmc){
		DAO dao = DAO.getInstance();
		String sql = "";
		String [] columns = new String[]{"dmh","dmmc"};
		boolean isOk = false;
		HashMap<String,String> map = new HashMap<String,String>();
		if("jyxs_view".equals(type)){       //查看
			sql = "select dmh,dmmc from xljk_dmwhb where dmh=?";
			map =  dao.getMap(sql, new String[]{pk},columns);
		}else if("jyxs_add".equals(type)){  //增加
			sql = "insert into xljk_dmwhb(xn_id,dmh,dmmc,ss) values(?,?,?,'xlxhhd_hdxs')";
			try {
				isOk = dao.insert(sql, new String[]{pk,pk,dmmc});
				map.put("result", String.valueOf(isOk));
			} catch (SQLException e) {	
				e.printStackTrace();
			}		
		}else if("jyxs_modi".equals(type)){ //修改
			sql = "update xljk_dmwhb set dmmc = ? where dmh = ?";
			try {
				isOk = dao.insert(sql, new String[]{dmmc,pk});
				map.put("result", String.valueOf(isOk));
			} catch (SQLException e) {		
				e.printStackTrace();
			}	
		}else if("jyxs_del".equals(type)){
			sql = "delete from xljk_dmwhb where dmh=?";
			try {
				isOk = dao.runUpdate(sql, new String[]{pk});
				map.put("result", String.valueOf(isOk));
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	public String select_sql_yuju(String tableName, String[] columes,
			String[] values) {
		StringBuffer sql = new StringBuffer();
		int size = columes.length;
		String sql_temp = "select * from " + tableName + " where 1=1 ";
		sql.append(sql_temp);
		for (int i = 0; i < size; i++) {
			if (null != values[i] && "" != values[i]) {
				sql_temp = " and " + columes[i] + " ='" + values[i] + "' ";
				sql.append(sql_temp);
			}
		}
		return sql.toString();
	}

	public String select_sql_yuju2(String tableName, String[] columes,
			String[] values, String[] like_com, String[] like_val) {
		StringBuffer sql = new StringBuffer();
		int size = columes.length;
		String sql_temp = "select * from " + tableName + " where 1=1 ";
		sql.append(sql_temp);
		for (int i = 0; i < size; i++) {
			if (null != values[i] && "" != values[i]) {
				sql_temp = " and " + columes[i] + " ='" + values[i] + "' ";
				sql.append(sql_temp);
			}
		}
		size = like_com.length;
		for (int i = 0; i < size; i++) {
			if (null != like_val[i] && "" != like_val[i]) {
				sql_temp = " and " + like_com[i] + " like'%" + like_val[i]
						+ "%' ";
				sql.append(sql_temp);
			}
		}
		return sql.toString();
	}

	public String select_sql_yuju3(String usersql, String tableName,
			String[] columes, String[] values, String[] like_com,
			String[] like_val) {
		String sql_temp = "";
		StringBuffer sql = new StringBuffer();
		int size = 0;
		if (null == usersql || "".equalsIgnoreCase(usersql)) {
			sql_temp = "select * from " + tableName + " where 1=1 ";
			size = columes.length;
			sql.append(sql_temp);
			for (int i = 0; i < size; i++) {
				if (null != values[i] && "" != values[i]) {
					sql_temp = " and " + columes[i] + " ='" + values[i] + "' ";
					sql.append(sql_temp);
				}
			}
			size = like_com.length;
			for (int i = 0; i < size; i++) {
				if (null != like_val[i] && "" != like_val[i]) {
					sql_temp = " and " + like_com[i] + " like'%" + like_val[i]
							+ "%' ";
					sql.append(sql_temp);
				}
			}
		} else {
			sql.append(usersql);
			sql_temp = " " + tableName + " a where 1=1";
			sql.append(sql_temp);
			size = columes.length;
			for (int i = 0; i < size; i++) {
				if (null != values[i] && "" != values[i]) {
					sql_temp = " and " + columes[i] + " ='" + values[i] + "' ";
					sql.append(sql_temp);
				}
			}
			size = like_com.length;
			for (int i = 0; i < size; i++) {
				if (null != like_val[i] && "" != like_val[i]) {
					sql_temp = " and " + like_com[i] + " like'%" + like_val[i]
							+ "%' ";
					sql.append(sql_temp);
				}
			}
		}
		return sql.toString();
	}
}
