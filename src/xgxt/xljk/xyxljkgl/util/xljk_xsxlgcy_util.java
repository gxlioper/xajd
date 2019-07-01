package xgxt.xljk.xyxljkgl.util;

import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.DAO.*;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.xyxljkgl.form.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;

public class xljk_xsxlgcy_util {

	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util com_util = new lrh_commen_util();

	HttpServletRequest request;

	public void xljk_xljkfdy_util(HttpServletRequest request) {
		this.request = request;
	}

	// 根据表中其他字段值查询结果，传入的参数是其他字段的字段名，字段值，表名和主键，返回的是集合
	public List<HashMap<String, String>> Find_By_OtherKey(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		strbuf.append(" from ").append(tableName).append(" a  where 1=1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			strbuf.append(" and ").append(otherKeys[i]).append(" =?");
		}
		return mydao.getList(strbuf.toString(), otherKeyValues, tabTitle);
	}

	public List Find_By_OtherKey3(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues, String[] like,
			String[] likeVal, String[] order) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (StringUtils.isNotNull(usersql)) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		strbuf.append(" from ").append(tableName).append(" a  where 1=1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			strbuf.append(" and ").append(otherKeys[i]).append(" =?");
		}
		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				strbuf.append(" and ").append(like[i]).append(" like ?");
				val.append(likeVal[i] + s1);
			} else {
				strbuf.append(" and ").append(like[i]).append(" like '")
						.append(likeVal[i]).append("'");
			}
		}
		String[] values = val.toString().split(s1);
		return mydao.getList(strbuf.toString(), values, tabTitle);
	}

	public List Find_By_OtherKey2(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		strbuf.append(" from " + tableName + " a  where 1 = 1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			strbuf.append(" and " + otherKeys[i] + " =?");
		}
		List<Vector<String>> lii = mydao.getVectorList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
	}

	// 取表头，根据传入一个表名，和表中字段数组，取出表头
	public List Get_Table_Title(String tableName, String[] zdm) {
		String sql = "select rownum 行号, a.";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		for (int i = 0; i < zdm.length; i++) {
			strbuf.append(zdm[i]);
			strbuf.append((i == zdm.length - 1 ? " " : ",a."));
		}
		strbuf.append(" from " + tableName + " a ");
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
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (StringUtils.isNotNull(userSql)) {
			String[] temp = userSql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		strbuf.append(" from " + tableName + " a  where 1 = 1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	public List get_xyList() {
		return mydao.getXyList();
	}

	public List get_zyList(String xydm) {
		return mydao.getZyList(xydm);
	}

	public List get_bjList(String xydm, String zydm) {
		return mydao.getBjList(xydm, zydm);
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

	public List get_dmwhb_dmList(String ss) {
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "SS" };
		String[] otherKeyValues = { ss };
		return this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
	}

	public String xsxlgcy_add(xljk_xyxljkjyry_from myform) {
		boolean flag = false;
		String tableName = "XLJK_XSXLGCYB";
		myform.setXn_id(newId.new_xnid(tableName));
		String usersql = "select a.XN_ID from";
		String[] otherKeys = { "XH" };
		String[] otherKeyValues = { myform.getXh() };
		List testLi = Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		if (testLi.size() != 0) {
			return "xh_exits";
		}
		usersql = "select a.XN_ID from";
		String[] otherKeys1 = { "GCYBH" };
		String[] otherKeyValues1 = { myform.getGcybh() };
		testLi = Find_By_OtherKey(tableName, usersql, otherKeys1,
				otherKeyValues1);
		if (testLi.size() != 0) {
			return "gcybh_exits";
		}
		DAO dao = DAO.getInstance();
		
		String xxdm = StandardOperation.getXxdm();
		if(Globals.XXDM_SHGC.equals(xxdm)){
			String now = dao.getOneRs("select to_char(sysdate,'yyyymmddHHMMmm') now from dual",new String[] {}, "now");
			myform.setGcybh(now);
		}
//		System.out.println(myform.getGcybh());
		String[] columns = new String[] { "xn_id", "xh", "gcybh", "qsdh",
				"sjhm", "csny", "bz","nj","xlkscj"};
		flag = StandardOperation.insert(tableName, columns, FormUtils
				.getProperties(columns, myform), this.request);
		return flag == true ? "insert_success" : "insert_fail";
	}

	public String xsxlgcy_modi(xljk_xyxljkjyry_from myform) throws Exception {
		boolean flag = false;
		String tableName = "xljk_xsxlgcyb";
		String primaryKey = "XN_ID";
		String pkValue = myform.getXljk_xsxlgcyb_xnid();
		String[] columns = new String[] { "csny", "sjhm", "qsdh", "bz","gcybh" };
		flag = StandardOperation.update(tableName, columns, FormUtils
				.getProperties(columns, myform), primaryKey, pkValue,
				this.request);
		return flag == true ? "update_success" : "update_fail";
	}

	public List xsxlgcy_search(xljk_xyxljkjyry_from myform) {
		String[] eqCols = new String[] { "gcybh", "xb", "xydm", "bjdm","nj"};
		String[] lkCols = new String[] { "xm", "xh" };
		String tableName = "VIEW_XLJK_XSXLGCYB";
		String[] outputValues = new String[] { "xn_id", "xh", "xm", "xb",
				"xymc", "zydm", "gcybh", "zymc", "bjmc", "csny" };
		String sql = SQL_Util.getQueryEqLkSqlUseValue(tableName, eqCols,
				FormUtils.getProperties(eqCols, myform), lkCols, FormUtils
						.getProperties(lkCols, myform), outputValues);
		return mydao.getList(sql, new String[] {}, outputValues);
	}

	public xljk_xyxljkjyry_from xsxlgcy_view(xljk_xyxljkjyry_from myform)
			throws Exception {
		String xn_id = myform.getXljk_xsxlgcyb_xnid();
		String tableName = "VIEW_XLJK_XSXLGCYB";
		String[] outputValues = new String[] { "gcybh", "xh", "xm", "xb",
				"xymc", "bjmc", "csny", "qsdh", "sjhm", "bz", "xydm", "bjdm","nj","xlkscj" };
		String sql = SQL_Util.getQuerySqlByPKParam(tableName, "xn_id");
		String[] otherKeyValues = { xn_id };
		String[] fdyInfo = mydao.getOneRs(sql, otherKeyValues, outputValues);
		FormUtils.setProperties(outputValues, fdyInfo, myform);
		return myform;
	}

	public String xsxlgcy_del(xljk_xyxljkjyry_from myform) throws Exception {
		String xn_id = myform.getXljk_xsxlgcyb_xnid();
		String tableName = "xljk_xsxlgcyb";
		String primaryKey = "XN_ID";
		boolean flag = StandardOperation.delete(tableName, primaryKey, xn_id,
				this.request);
		return flag == true ? "del_success" : "del_fail";
	}
}
