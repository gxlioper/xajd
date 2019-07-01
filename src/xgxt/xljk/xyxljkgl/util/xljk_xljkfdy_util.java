package xgxt.xljk.xyxljkgl.util;

import java.util.*;
import xgxt.utils.New_Random_ID;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.DAO.*;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.xyxljkgl.form.*;
import xgxt.xljk.lrh_Util.util.*;
import javax.servlet.http.HttpServletRequest;

public class xljk_xljkfdy_util {
	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util commen_util = new lrh_commen_util();

	HttpServletRequest request;

	public void xljk_xljkfdy_util1(HttpServletRequest request) {
		this.request = request;
	}

	public String fdy_add(xljk_xyxljkjyry_from myform) throws Exception {
		boolean flag = false;
		String message = "";
		String tableName = "xljk_fdyb";
		String xn_id = newId.new_xnid(tableName);
		myform.setXn_id(xn_id);
		String fdybh = myform.getFdybh();
		String usersql = "select a.XN_ID from";
		String[] otherKeys1 = { "FDYBH" };
		String[] otherKeyValues1 = { fdybh };
		List testLi = commen_util.Find_By_OtherKey(tableName, usersql,
				otherKeys1, otherKeyValues1);
		if (testLi.size() != 0) {
			message = "fdybh_exits";
			return message;
		}
		myform.setXb(commen_util.xljk_dmwhb_dmzh(myform.getXb()));
		myform.setXymc(commen_util.getxymc_byxydm(myform.getXydm()));
		String[] columns = new String[] { "xn_id", "fdybh", "xm", "xb", "xydm",
				"xymc", "rzsj", "xl", "zy", "sjhm", "lxdh", "byyx", "gzjl",
				"pxqk", "lwcg", "csny", "zw", "zc", "bz" };
		String[] values = FormUtils.getProperties(columns, myform);
		flag = StandardOperation.insert(tableName, columns, values,
				this.request);
		return flag == true ? "insert_success" : "insert_fail";
	}

	public List fdy_search(xljk_xyxljkjyry_from myform) {
		String fdybh = myform.getFdybh();
		String xm = myform.getXm();
		String xb = myform.getXb();
		String xydm = myform.getXydm();
		String tableName = "xljk_fdyb";
		xb = commen_util.xljk_dmwhb_dmzh(xb); // 将性别代码转换为中文的性别
		String[] eqCols = new String[] { "fdybh", "xb", "xydm" };
		String[] eqVals = new String[] { fdybh, xb, xydm };
		String[] lkCols = new String[] { "xm" };
		String[] lkVals = new String[] { xm };
		String[] outputValues = new String[] { "xn_id", "fdybh", "xm", "xb",
				"xydm", "xymc", "rzsj", "xl", "zy", "sjhm", "lxdh", "byyx",
				"gzjl", "pxqk", "lwcg", "csny", "zw", "zc", "bz" };
		String sql = SQL_Util.getQueryEqLkSqlUseValue(tableName, eqCols,
				eqVals, lkCols, lkVals, outputValues);
		return mydao.getList(sql, new String[] {}, outputValues);
	}

	public xljk_xyxljkjyry_from fdy_view(xljk_xyxljkjyry_from myform)
			throws Exception {
		String xn_id = myform.getXljk_fdyb_xnid();
		String tableName = "xljk_fdyb";
		String[] outputValues = new String[] { "fdybh", "xydm", "xymc", "xm",
				"xb", "csny", "zw", "rzsj", "zc", "xl", "zy", "byyx", "lxdh",
				"sjhm", "gzjl", "pxqk", "lwcg", "bz" };
		String sql = SQL_Util.getQuerySqlByPKParam(tableName, "xn_id");
		String[] otherKeyValues = { xn_id };
		String[] fdyInfo = mydao.getOneRs(sql, otherKeyValues, outputValues);
		FormUtils.setProperties(outputValues, fdyInfo, myform);
		return myform;
	}

	public String fdy_modi(xljk_xyxljkjyry_from myform) throws Exception {
		String xn_id = myform.getXljk_fdyb_xnid();
		boolean flag = false;
		String tableName = "xljk_fdyb";
		String xymc = commen_util.getxymc_byxydm(myform.getXydm());
		myform.setXb(commen_util.xljk_dmwhb_dmzh(myform.getXb()));
		myform.setXymc(xymc);
		String[] columns = new String[] { "xm", "xb", "xydm", "xymc", "rzsj",
				"xl", "zy", "sjhm", "lxdh", "byyx", "gzjl", "pxqk", "lwcg",
				"csny", "zw", "zc", "bz" };
		String[] values = FormUtils.getProperties(columns, myform);
		String primaryKey = "XN_ID";
		String pkValue = xn_id;
		flag = StandardOperation.update(tableName, columns, values, primaryKey,
				pkValue, this.request);
		return flag == true ? "update_success" : "update_fail";
	}

	public String fdy_del(xljk_xyxljkjyry_from myform) throws Exception {
		String xn_id = myform.getXljk_fdyb_xnid();
		boolean flag = false;
		String tableName = "xljk_fdyb";
		String primaryKey = "XN_ID";
		String value = xn_id;
		flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == true ? "del_success" : "del_fail";
	}

}
