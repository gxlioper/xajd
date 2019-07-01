package xgxt.xljk.xyxljkgl.util;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import xgxt.xljk.lrh_Util.util.*;
import xgxt.xljk.xyxljkgl.form.*;

public class xljk_xyxljkjyhd_util {

	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util commen_util = new lrh_commen_util();

	HttpServletRequest request;

	public void xljk_xljkfdy_util(HttpServletRequest request) {
		this.request = request;
	}

	public String xyxljkjyhd_add(xljk_xyxljkjyhd_from myform)
			throws SQLException {
		String tableName = "XLJK_XYHD";
		myform.setXn_id(newId.new_xnid(tableName));
		String xydm = myform.getXydm();
		myform.setXymc(commen_util.getxymc_byxydm(xydm));
		String hdxs = myform.getHdxs();
		if (hdxs.equals("013")) { // 表明是其他活动形式
			myform.setQthdxs("");
		}
		hdxs = commen_util.xljk_dmwhb_dmzh(hdxs);
		String[] columns = new String[] { "xn_id", "zt", "xydm", "xymc",
				"hdxs", "rq", "zcr", "xs", "cyxs", "rs", "hdjl", "hdxg", "dd",
				"kssj", "jssj" };
		boolean flag = mydao.insert(SQL_Util.getInsertSqlUseParam(tableName,
				columns), FormUtils.getProperties(columns, myform));
		return flag == true ? "insert_success" : "insert_fail";
	}
	
	/**
	 * 闽江学院
	 * 心理健康 学院活动
	 * 增加wjdz(文件存放地址)字段
	 * @param myform
	 * @return
	 * @throws SQLException
	 */
	public boolean xyxljkjyhdadd(xljk_xyxljkjyhd_from myform)
	throws SQLException {
		String tableName = "XLJK_XYHD";
		myform.setXn_id(newId.new_xnid(tableName));
		String xydm = myform.getXydm();
		myform.setXymc(commen_util.getxymc_byxydm(xydm));
		String hdxs = myform.getHdxs();
		if (hdxs.equals("013")) { // 表明是其他活动形式
			myform.setQthdxs("");
		}
		hdxs = commen_util.xljk_dmwhb_dmzh(hdxs);
		String[] columns = new String[] { "xn_id", "zt", "xydm", "xymc",
				"hdxs", "rq", "zcr", "xs", "cyxs", "rs", "hdjl", "hdxg", "dd",
				"kssj", "jssj","wjdz" };
		boolean flag = mydao.insert(SQL_Util.getInsertSqlUseParam(tableName,
				columns), FormUtils.getProperties(columns, myform));
		return flag;
	}


	public List xyxljkjyhd_search(xljk_xyxljkjyhd_from myform) {
		String tableName = "view_xljk_xyhd";
		String[] columns = new String[] { "rq", "xydm" };
		String[] outputValues = new String[] { "xn_id", "xydm", "dd", "rq",
				"xymc", "zt", "cyxs", "zcr", "xs", "rs","hdmc" };
		return mydao.getList(SQL_Util.getQuerySqlUseValue(tableName, columns,
				FormUtils.getProperties(columns, myform), outputValues),
				new String[] {}, outputValues);
	}

	public xljk_xyxljkjyhd_from xyxljkjyhd_view(xljk_xyxljkjyhd_from myform) {
		String xn_id = myform.getXlxhhd_xnid();
		String tableName = "XLJK_XYHD";
		String[] columns = { "xydm", "xymc", "dd", "rq", "kssj", "jssj", "zt",
				"cyxs", "rs", "hdxs", "hdjl", "hdxg", "zcr", "xs", "qthdxs","wjdz" };
		String[] otherKeyValues = { xn_id };
		String[] values = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(
				tableName, new String[]{ "xn_id" }, otherKeyValues, columns),
				new String[] {}, columns);
		FormUtils.setProperties(columns, values, myform);
		return myform;
	}

	public String xyxljkjyhd_modi(xljk_xyxljkjyhd_from myform) throws Exception {
		String tableName = "XLJK_XYHD";
		String primaryKey = "XN_ID";
		String pkValue = myform.getXlxhhd_xnid();
		String xymc = commen_util.getxymc_byxydm(myform.getXydm());
		myform.setXymc(xymc);
		String hdxs = myform.getHdxs();
		String qthdxs = hdxs.equals("013") ? myform.getQthdxs() : "";
		//修改时不应该存MC,应该存DM
//		hdxs = commen_util.xljk_dmwhb_dmzh(hdxs);
		myform.setHdxs(hdxs);
		myform.setQthdxs(qthdxs);
		String[] columns = new String[] { "zt", "xydm", "xymc", "hdxs",
				"qthdxs", "rq", "zcr", "xs", "cyxs", "rs", "hdjl", "hdxg",
				"dd", "kssj", "jssj"};
		boolean flag = mydao.runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName,
				primaryKey, pkValue, columns), FormUtils.getProperties(columns,
				myform));
		return flag == true ? "update_success" : "update_fail";
	}

	public String xyxljkjyhd_del(xljk_xyxljkjyhd_from myform) throws Exception {
		boolean flag = false;
		String xn_id = myform.getXlxhhd_xnid();
		String tableName = "XLJK_XYHD";
		String pk = "XN_ID";
		String pkVal = xn_id;
		flag = StandardOperation.delete(tableName, pk, pkVal, this.request);
		return flag == true ? "del_success" : "del_fail";
	}

}
