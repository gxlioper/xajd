package xgxt.xljk.common;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.DAO.DAO;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

public class XljkUtil {

	private static final DAO dao = new DAO();

	private static XljkUtil service = new XljkUtil();

	public static XljkUtil getInstance() {
		return service;
	}

	private XljkUtil() {
	}

	private static final String[] nullArray = {};

	/**
	 * 返回一条记录的所有字段信息
	 * 
	 * @param pkValue
	 * @param tableName
	 * @param pk
	 * @param myForm
	 * @throws Exception
	 */
	public void getCommonAllInfo(String pkValue, String tableName, String pk,
			ActionForm myForm) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(dao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] rs = dao.getOneRs(
				SQL_Util.getQuerySqlByPKParam(tableName, pk),
				new String[] { pkValue }, columnNames);
		FormUtils.setProperties(columnNames, rs, myForm);
	}

	/**
	 * 将整条记录保存到数据库中
	 * 
	 * @param myForm
	 * @param realTable
	 * @param pk
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveCommonInfo(ActionForm myForm, String realTable,
			String pk, String pkValue) throws Exception {
		boolean flag = true;
		String[] columns = FormUtils.chgArrayElem2LowerCase(dao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		columns = FormUtils.removeArrayElem(columns, FormUtils
				.getDefaultColumns(realTable));
		if (FormUtils.haveOneRecord(realTable, pk, pkValue)) {
			flag = dao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { pkValue });
		}
		if (flag) {
			flag = dao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}

	/**
	 * 返回 List<String[]>
	 * 
	 * @param myForm
	 * @param tableName
	 * @param userType
	 * @param columns
	 * @param outputValues
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCommonVector(CommonForm myForm, String tableName,
			String userType, String[] columns, String[] outputValues)
			throws Exception {
		myForm.setColumnCN(dao.arrayToList(outputValues, dao.getColumnNameCN(
				outputValues, tableName)));
		return dao.rsToVator3(SQL_Util.getQuerySqlUseValue(tableName, columns,
				FormUtils.getProperties(columns, myForm), outputValues),
				nullArray, outputValues);
	}

	/** 批量删除 !!splitOne!! */
	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split的方式 来自于jsp页面
		return dao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/**
	 * 判断是否是更改或查看
	 * 
	 * @param doType
	 * @return
	 */
	public boolean viewOrModi(String doType) {
		return "view".equals(doType) || "modi".equals(doType);
	}

	/** set Result */
	public String setResult(boolean result) {
		return (result == true) ? "ok" : "no";
	}
}
