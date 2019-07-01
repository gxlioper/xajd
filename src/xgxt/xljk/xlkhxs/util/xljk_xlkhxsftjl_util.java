package xgxt.xljk.xlkhxs.util;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.New_Random_ID;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.xlkhxs.form.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

import javax.servlet.http.HttpServletRequest;

public class xljk_xlkhxsftjl_util {
	DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util commen_util = new lrh_commen_util();

	HttpServletRequest request;

	public void xljk_xlkhxsftjl_util1(HttpServletRequest request) {
		this.request = request;
	}

	private static final String[] nullArray = {};

	public String xlkhxsftjl_add(xljk_xlkhxs_form myform) throws SQLException {
		String tableName = "XLJK_XLKHXSFTB";
		String xn_id = newId.new_xnid("XLKHXSFTB");
		myform.setXn_id(xn_id);
		String[] columns = new String[] { "xn_id", "xh", "ftsj", "ftr", "ftdd",
				"ftjl", "bz","sjhm" };
		boolean flag = mydao.insert(SQL_Util.getInsertSqlUseValue(tableName,
				columns, FormUtils.getProperties(columns, myform)), nullArray);
		return flag == true ? "insert_success" : "insert_fail";
	}

	public List xlkhxsftjl_search(xljk_xlkhxs_form myform) throws Exception{
		String tableName = "VIEW_XLJK_XLKHXSFTB";
		String[] eqCols = new String[] { "nj", "xydm", "zydm", "bjdm"};
		String[] lkCols = new String[] { "xm", "xh" };
		String[] outputValues = new String[] { "xn_id", "XH", "XM", "XB", "XYMC", "BJMC", "FTR", "FTSJ",
				"FTDD"};
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(eqCols, lkCols, myform);
		String[] input = makeQuery.getInputList();
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotNull(myform.getFtsjks())){
			sb.append(" and to_number(substr(ftsj,0,4)||substr(ftsj,6,2)||substr(ftsj,9,2))>=?");
			input = StringUtils.joinStrArr(input, new String[]{myform.getFtsjks()});
			
		}
		if(StringUtils.isNotNull(myform.getFtsjjs())){
			sb.append(" and to_number(substr(ftsj,0,4)||substr(ftsj,6,2)||substr(ftsj,9,2))<=?");
			input = StringUtils.joinStrArr(input, new String[]{myform.getFtsjjs()});
		}
		String sql = makeQuery.getQueryString() + sb.toString();
		return CommonQueryDAO.commonQuery(tableName, 
				sql, input, outputValues, "",myform);	
	}

	public xljk_xlkhxs_form xlkhxsftjl_view(xljk_xlkhxs_form myform) {
		String xn_id = myform.getXljk_xlkhxsftjl_xnid();
		String tableName = "VIEW_XLJK_XLKHXSFTB";
		String[] outputValues = new String[] { "xn_id", "xh", "ftr", "ftsj",
				"ftdd", "ftjl", "bz", "xm", "xb", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc" ,"sjhm"};
		String[] values = mydao.getOneRs(SQL_Util.getQuerySqlByPKParam(
				tableName, "xn_id"), new String[] { xn_id }, outputValues);
		FormUtils.setProperties(outputValues, values, myform);
		return myform;
	}

	public String xlkhxsftjl_modi(xljk_xlkhxs_form myform) throws Exception {
		String xn_id = myform.getXljk_xlkhxsftjl_xnid();
		String tableName = "XLJK_XLKHXSFTB";
		myform.setXn_id(xn_id);
		String[] columns = new String[] { "xh", "ftsj", "ftr", "ftdd", "ftjl",
				"bz","sjhm" };
		boolean flag = mydao.runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName,
				"xn_id", xn_id, columns), FormUtils.getProperties(columns,
				myform));
		return flag == true ? "update_success" : "update_fail";
	}

	public String xlkhxsftjl_del(xljk_xlkhxs_form myform) throws Exception {
		String xn_id = myform.getXljk_xlkhxsftjl_xnid();
		String tableName = "XLJK_XLKHXSFTB";
		String primaryKey = "XN_ID";
		String value = xn_id;
		boolean flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == true ? "del_success" : "del_fail";
	}

}
