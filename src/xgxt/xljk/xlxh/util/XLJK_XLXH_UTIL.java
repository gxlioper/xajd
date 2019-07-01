package xgxt.xljk.xlxh.util;

import xgxt.DAO.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.xlxh.Form.AssociationForm;
import xgxt.xljk.xlxh.Form.xljk_xlxhhd_from;
import xgxt.xljk.xlxh.Form.xljk_xlxhhy_from;
import xgxt.xljk.xlxh.model.XLJK_XLXH_MODEL;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.utils.New_Random_ID;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

import xgxt.xljk.lrh_Util.util.*;

public class XLJK_XLXH_UTIL {

	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util com_util = new lrh_commen_util();

	HttpServletRequest request;

	public void XLJK_XLXH_UTIL1(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @category 根据用户选择查询条件进行查询,返回的是list<font color=red>
	 *           返回类型List(Vector(String)) </font>
	 * @return
	 */
	public List Find_By_SqlComment(AssociationForm form) {
		String tableName = "VIEW_XLXHHYXX";
		String[] inputvalue = {};
		String[] outputValues = new String[] { "member_id", "xh", "xm", "xb",
				"bjmc", "xymc", "stand_a", "stand_c", "csrq", "stand_b",
				"stabd_d" };
		String[] columns = new String[] { "member_id", "xh", "xb", "xydm",
				"bjdm" };
		// set xb
		form.setXb(this.getXbById(form.getXb()));
		String sql = SQL_Util.getQuerySqlUseValue(tableName, columns, FormUtils
				.getProperties(columns, form), outputValues);
		String[] tabTitle = mydao.getColumnName(sql);
		return mydao.getVectorList(sql, inputvalue, tabTitle);
	}

	/**
	 * @category 取表头,返回的是list<font color=red> 取的表是xljk_xlxh </font>
	 * @return
	 */
	public List Get_xljk_xlxh_Title() {
		String[] columns = new String[] { "member_id", "xh", "xm", "xb",
				"bjmc", "xymc", "stand_a", "stand_c", "csrq", "stand_b",
				"stabd_d" };
		String[] tabTitleCN = mydao.getColumnNameCN(columns, "VIEW_XLXHHYXX");
		List titles = mydao.arrayToList(columns, tabTitleCN);
		return titles;
	}

	/** 心理协会会员 */
	public List xljk_xlxhhy_findbysql(xljk_xlxhhy_from myform) {
		String tableName = "VIEW_XLXHHY";
		myform.setXb(this.getXbById(myform.getXb()));
		String[] eqCols = new String[] { "hybh", "xb", "xydm", "bjdm" };
		String[] lkCols = new String[] { "hyxh", "xm" };
		String[] outputValues = new String[] { "xn_id", "hybh", "xm", "xb",
				"hyxh", "csrq", "xymc", "bjmc", "sjhm", "qsdh", "zw", "bz" };
		return mydao.getList(SQL_Util.getQueryEqLkSqlUseValue(tableName,
				eqCols, FormUtils.getProperties(eqCols, myform), lkCols,
				FormUtils.getProperties(lkCols, myform), outputValues),
				new String[] {}, outputValues);
	}

	/** 根据代码获得性别 */
	private String getXbById(String id) {
		for (HashMap<String, String> map : mydao.getSexList()) {
			if (map.get("en").equals(id)) {
				return map.get("cn");
			}
		}
		return "";
	}

	public XLJK_XLXH_MODEL xljk_xlxhhy_detail(String xn_id) {
		String tableName = "view_xlxhhy";
		XLJK_XLXH_MODEL myForm = new XLJK_XLXH_MODEL();
		String[] outputValue = new String[] { "hybh", "hyxh", "sjhm", "qsdh",
				"zw", "bz", "csrq", "xm", "xb", "xymc", "bjmc" };
		String[] res = mydao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName,
				"xn_id"), new String[] { xn_id }, outputValue);
		FormUtils.setProperties(outputValue, res, myForm);
		return myForm;
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
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}
		List<HashMap<String, String>> lii = mydao.getList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
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
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}
		List<Vector<String>> lii = mydao.getVectorList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
	}

	public XLJK_XLXH_MODEL stu_info_fill(stu_info_model stu_mod) {
		XLJK_XLXH_MODEL mymodel = new XLJK_XLXH_MODEL();
		mymodel.setHyxh(stu_mod.getXH());
		mymodel.setXm(stu_mod.getXM());
		mymodel.setSex(stu_mod.getXB());
		mymodel.setXymc(stu_mod.getXYMC());
		mymodel.setBjmc(stu_mod.getBJMC());
		mymodel.setHyxm(stu_mod.getXM());
		return mymodel;
	}

	public xljk_xlxhhy_from stu_info_fill2(stu_info_model stu_mod) {
		xljk_xlxhhy_from myform = new xljk_xlxhhy_from();
		myform.setXh(stu_mod.getXH());
		myform.setHyxh(stu_mod.getXH());
		myform.setXm(stu_mod.getXM());
		myform.setXb(stu_mod.getXB());
		myform.setXymc(stu_mod.getXYMC());
		myform.setBjmc(stu_mod.getBJMC());
		myform.setHyxm(stu_mod.getXM());
		return myform;
	}

	// 取表头，根据传入一个表名，和表中字段数组，取出表头
	public List Get_Table_Title(String tableName, String[] zdm) {
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

	public String xljk_xlxh_add(xljk_xlxhhy_from myform) throws SQLException {
		New_Random_ID newid = new New_Random_ID();
		String tableName = "XLJK_XLXHHYB";
		String hyxh = myform.getHyxh();
		String hybh = myform.getHybh();
		String usersql = "select a.XN_ID from ";
		String[] otherKeys = { "HYBH" };
		String[] otherKeyValues = { hybh };
		List test1 = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		if (test1.size() != 0) {
			return "bh exits";
		}
		String[] otherKeys2 = { "HYXH" };
		String[] otherKeyValues2 = { hyxh };
		test1 = this.Find_By_OtherKey(tableName, usersql, otherKeys2,
				otherKeyValues2);
		if (test1.size() != 0) {
			return "stu exits";
		}
		String[] columns = new String[] { "xn_id", "hybh", "hyxh", "qsdh",
				"csrq", "sjhm", "zw", "bz" };
		myform.setXn_id(newid.new_xnid("xljk_xlxhhyb"));
		boolean flag = mydao.insert(SQL_Util.getInsertSqlUseValue(tableName,
				columns, FormUtils.getProperties(columns, myform)),
				new String[] {});
		return flag == false ? "insert fail" : "insert success";
	}

	public String xljk_xlxh_del(xljk_xlxhhy_from myform) throws Exception {
		String xlshhy_xnid = myform.getXlxhhyb_xnid();
		String tableName = "xljk_xlxhhyb";
		String primaryKey = "XN_ID";
		String value = xlshhy_xnid;
		boolean flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == false ? "xlxhhy_del_fail" : "xlxhhy_del_success";
	}

	public List xlxhhd_gethdxsList() {
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from ";
		String[] otherKeys = { "SS" };
		String[] otherKeyValues = { "xlxhhd_hdxs" };
		return this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
	}

	public String xlxhhd_add_hd(xljk_xlxhhd_from myform) throws SQLException {
		String tableName = "xljk_xlxhhd";
		myform.setXn_id(newId.new_xnid(tableName));
		String hdxs = myform.getHdxs();
		if (!hdxs.equals("013")) {
			myform.setQthdxs("");
		}
		myform.setHdxs(this.xljk_dmwhb_dmzh(hdxs));
		String[] columns = new String[] { "zt", "xn_id", "rq", "zcr", "xs",
				"hdxs", "qthdxs", "cyxs", "rs", "hdjl", "hdxg", "dd", "kssj",
				"jssj" };
		boolean flag = mydao.insert(SQL_Util.getInsertSqlUseValue(tableName,
				columns, FormUtils.getProperties(columns, myform)),
				new String[] {});
		return flag == false ? "insert_fail" : "insert_success";
	}

	public List xlxhhd_search_hd(xljk_xlxhhd_from myform) {
		String tableName = "xljk_xlxhhd";
		String[] values = new String[] { myform.getRq() };
		String[] outputValues = new String[] { "xn_id", "dd", "rq", "kssj",
				"jssj", "zt", "cyxs", "rs", "hdxs", "hdjl", "hdxg", "zcr",
				"xs", "qthdxs" };
		return mydao.getList(SQL_Util.getQuerySqlUseValue(tableName,
				new String[] { "rq" }, values, outputValues), new String[] {},
				outputValues);
	}

	public xljk_xlxhhd_from xlxhhd_view_hd(xljk_xlxhhd_from myform) {
		String[] outputValues = new String[] { "xn_id", "dd", "rq", "kssj",
				"jssj", "zt", "cyxs", "rs", "hdxs", "hdjl", "hdxg", "zcr",
				"xs", "qthdxs" };
		String tableName = "xljk_xlxhhd";
		String[] columns = new String[] { "xn_id" };
		String[] values = new String[] { myform.getXlxhhd_xnid() };
		String[] rs = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(tableName,
				columns, values, outputValues), new String[] {}, outputValues);
		FormUtils.setProperties(outputValues, rs, myform);
		return myform;
	}

	public String xlxhhd_modi_hd(xljk_xlxhhd_from myform) throws Exception {
		String tableName = "xljk_xlxhhd";
		String hdxs = myform.getHdxs();
		if (!hdxs.equals("013")) {
			myform.setQthdxs("");
		}
		myform.setHdxs(this.xljk_dmwhb_dmzh(hdxs));
		String[] columns = new String[] { "zt", "hdxs", "rq", "zcr", "xs",
				"cyxs", "rs", "hdjl", "hdxg", "dd", "kssj", "jssj" };
		String zt = myform.getZt(); 
		String hdxs1 = myform.getHdxs(); 
		String rq = myform.getRq(); 
		String zcr = myform.getZcr(); 
		String xs = myform.getXs();
		String cyxs = myform.getCyxs(); 
		String rs = myform.getRs(); 
		String hdjl = myform.getHdjl(); 
		String hdxg = myform.getHdxg(); 
		String dd = myform.getDd(); 
		String kssj = myform.getKssj(); 
		String jssj = myform.getJssj();
		@SuppressWarnings("unused")
		String s = SQL_Util.getUpdateSqlPKParam(tableName,"xn_id", columns);
		boolean flag = mydao.runUpdate(SQL_Util.getUpdateSqlPKParam(tableName,
				"xn_id", columns), new String[] { zt,hdxs1,rq,zcr,xs,cyxs,rs,hdjl,hdxg,dd,kssj,jssj,myform.getXlxhhd_xnid() });
		return flag == true ? "update_success" : "update_fail";
	}

	public String xlxhhd_del_hd(xljk_xlxhhd_from myform) throws Exception {
		String xlxhhd_xnid = myform.getXlxhhd_xnid();
		String tableName = "xljk_xlxhhd";
		boolean flag = StandardOperation.delete(tableName, "XN_ID",
				xlxhhd_xnid, this.request);
		return flag == true ? "del_success" : "del_fail";
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

	// update
	public String xjlk_xlxhhy_modi(String xn_id, xljk_xlxhhy_from myform)
			throws Exception {
		String tableName = "xljk_xlxhhyb";
		String[] columns = new String[] { "csrq", "sjhm", "qsdh", "zw", "bz" };
		boolean flag = mydao.runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName,
				"xn_id", xn_id, columns), FormUtils.getProperties(columns,
				myform));
		return flag == true ? "update_success" : "update_fail";
	}
}
