package xsgzgl.customForm.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DEMO_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */
public class DemoFormDAO extends CommDAO {

	// ==================执行查询操作 begin==============================
	/**
	 * 根據所屬表查詢表字段
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTableZdList(String ssb) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.zd,a.zdm ");
		sql.append("from xg_custom_zdb a");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm" });

		return list;
	}

	/**
	 * 根據Form_id查詢Table
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTableList(String form_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.table_id,a.title,a.row_num ");
		sql.append("from xg_custom_table a ");
		sql.append("where form_id=? ");
		sql.append("order by to_number(a.xssx) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { form_id }, new String[] { "table_id", "title",
						"row_num" });

		return list;
	}
	
	/**
	 * 根據Table_id查詢Table
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getContentList(String table_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.row_num,a.column_num ");
		sql.append("from xg_custom_content a ");
		sql.append("where table_id=? ");
		sql.append("order by to_number(a.row_num),to_number(a.column_num) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { table_id }, new String[] { "row_num",
						"column_num" });

		return list;
	}
	// ==================执行查询操作 end==============================

	// ==================执行增加操作 begin==============================
	/**
	 * 新增数据（xg_custom_table:自定義表單TABLE表）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertCustomTable(String table_id, String form_id,
			String title, String row_num, String xssx, User user)
			throws Exception {

		// 表名
		String tableName = "xg_custom_table";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_table ");
		sql.append("(");
		sql.append("table_id,form_id,title,");
		sql.append("row_num,xssx");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { table_id, form_id, title, row_num, xssx };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 新增数据（xg_custom_content:自定義表單內容表）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertCustomContent(String table_id, String[] row,
			String[] column, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_content ");
		sql.append("(");
		sql.append("table_id,row_num,column_num");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < column.length; i++) {
			String[] value = new String[] { table_id, row[i], column[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行增加操作 end==============================
	
	// ==================执行修改操作 begin==============================
	/**
	 * 新增数据（xg_custom_content:自定義表單內容表）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateCustomContent(String[] table_id, String[] row,
			String[] column, String[] rowspan, String[] colspan, User user)
			throws Exception {

		// 表名
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_content ");
		sql.append("(");
		sql.append("table_id,row_num,column_num");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < column.length; i++) {
			String[] value = new String[] { table_id[i], row[i], column[i] };
			params.add(value);
		}

		boolean flag = true;// saveArrDate(sql.toString(), params, tableName,
							// user);

		return flag;
	}
	// ==================执行修改操作 begin==============================
}
