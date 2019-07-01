package xgxt.xljk.xlkhxs.util;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.New_Random_ID;
import xgxt.utils.form.FormUtils;
import xgxt.DAO.*;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.xljk.xlkhxs.form.*;
import xgxt.utils.sql.SQL_Util;

public class xljk_xlkhxs_util {
	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util commen_util = new lrh_commen_util();

	HttpServletRequest request;

	private static final String[] nullArray = {};

	public void xljk_xlkhxs_util1(HttpServletRequest request) {
		this.request = request;
	}

	public String xlkhxs_add(xljk_xlkhxs_form myform) throws SQLException {
		String tableName = "XLJK_XLKHXSXXB";
		if (FormUtils.haveOneRecord(tableName, "xh", myform.getXh())) {
			return "xh_exits";
		} else {
			myform.setXn_id(newId.new_xnid("xlkhxs"));
			String[] columns = { "xn_id", "xh", "zdgcdxdm" };
			boolean flag = mydao.insert(SQL_Util.getInsertSqlUseParam(
					tableName, columns), FormUtils.getProperties(columns,
					myform));
			return true == flag ? "insert_success" : "insert_fail";
		}
	}

	public List xlkhxs_search(xljk_xlkhxs_form myform) {
		String tableName = "VIEW_XLJK_XLKHXSXXB";
		String[] eqCols = new String[] { "xydm", "bjdm", "zdgcdxdm" };
		String[] lkCols = new String[] { "xm", "xh" };
		String[] outputValues = new String[] { "xn_id", "xh", "zdgcdxdm",
				"dmmc", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc" };
		String sql = SQL_Util.getQueryEqLkSqlUseValue(tableName, eqCols,
				FormUtils.getProperties(eqCols, myform), lkCols, FormUtils
						.getProperties(lkCols, myform), outputValues);
		return mydao.getList(sql, nullArray, outputValues);
	}

	public xljk_xlkhxs_form xlkhxs_view(xljk_xlkhxs_form myform) {
		String xn_id = myform.getXljk_xlkhxs_xnid();
		String tableName = "VIEW_XLJK_XLKHXSXXB";
		String[] outputValues = new String[] { "xn_id", "xh", "zdgcdxdm",
				"dmmc", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc" };
		String[] values = mydao.getOneRs(SQL_Util.getQuerySqlByPKParam(
				tableName, "xn_id"), new String[] { xn_id }, outputValues);
		FormUtils.setProperties(outputValues, values, myform);
		return myform;
	}

	public String xlkhxs_modi(xljk_xlkhxs_form myform) throws Exception {
		String xn_id = myform.getXljk_xlkhxs_xnid();
		String zdgcdxdm = myform.getZdgcdxdm();
		String tableName = "XLJK_XLKHXSXXB";
		boolean flag = false;
		String[] columns = { "ZDGCDXDM" };
		String[] values = { zdgcdxdm };
		String primaryKey = "XN_ID";
		String pkValue = xn_id;
		flag = StandardOperation.update(tableName, columns, values, primaryKey,
				pkValue, this.request);
		return false == flag ? "update_fail" : "update_success";
	}

	public String xlkhxs_del(xljk_xlkhxs_form myform) throws Exception {
		String xn_id = myform.getXljk_xlkhxs_xnid();
		String tableName = "XLJK_XLKHXSXXB";
		boolean flag = false;
		String primaryKey = "xn_id";
		String value = xn_id;
		flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return false == flag ? "del_fail" : "del_success";
	}
}
