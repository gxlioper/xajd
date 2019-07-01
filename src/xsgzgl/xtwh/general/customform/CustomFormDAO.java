package xsgzgl.xtwh.general.customform;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DAO类
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
public class CustomFormDAO extends CommDAO {

	// ===============表单管理 begin=====================
	/**
	 * 查詢數據【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getCustomFormList(CustomFormForm myForm,
			CustomFormModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.form_id pk,a.form_id, a.form_name,a.ssmk, ");
		tableSql
				.append("a.source_table,a.detail_view,a.search_view,a.create_time ");
		tableSql.append("from xg_custom_form a ");
		tableSql.append(") a where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "form_id", "form_name", "ssmk",
				"source_table", "detail_view", "search_view", "create_time" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 保存自定義表單
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveCustomForm(String form_id, String form_name,
			String ssmk, String source_table, String source_table_pk,
			String assistant_table_one, String assistant_table_one_zd,
			String assistant_table_one_relate, String assistant_table_two,
			String assistant_table_two_zd, String assistant_table_two_relate,
			String detail_view, String search_view, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_form";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_form ");
		sql.append("(");
		sql.append("form_id,form_name,ssmk");
		sql.append(",source_table,source_table_pk");
		sql
				.append(",assistant_table_one,assistant_table_one_zd,assistant_table_one_relate");
		sql
				.append(",assistant_table_two,assistant_table_two_zd,assistant_table_two_relate");
		sql.append(",detail_view,search_view");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { form_id, form_name, ssmk, source_table,
				source_table_pk, assistant_table_one, assistant_table_one_zd,
				assistant_table_one_relate, assistant_table_two,
				assistant_table_two_zd, assistant_table_two_relate,
				detail_view, search_view };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 删除自定義表單
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean deleteCustomForm(String[] pkValue, User user)
			throws Exception {

		// 表名
		String tableName = "xg_custom_form";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_custom_form ");
		sql.append("where form_id=? ");

		List<String[]> params = new ArrayList<String[]>();

		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				String[] value = new String[] { pkValue[i] };
				params.add(value);
			}
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ===============表单管理 end=====================

	// ===============表单参数begin=====================
	/**
	 * 根據Form_id查詢表单信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getCustomFormInfo(String form_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select form_id,form_name,ssmk ");
		sql.append(",source_table,source_table_pk");
		sql
				.append(",assistant_table_one,assistant_table_one_zd,assistant_table_one_relate");
		sql
				.append(",assistant_table_two,assistant_table_two_zd,assistant_table_two_relate");
		sql.append(",detail_view,search_view ");
		sql.append("from xg_custom_form a ");
		sql.append("where form_id=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { form_id });

		return map;
	}

	// ===============表单参数 end=====================

	// ===============表单查詢 begin=====================
	/**
	 * 根據配置表查詢表字段
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSearchConfigureList(String form_id,
			String ssb) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select * ");
		sql.append("from (select a.column_name dm, ");
		sql.append("nvl(b.comments, a.column_name) mc, ");
		sql.append("a.nullable, ");
		sql.append("a.data_length len ");
		sql.append("from (select a.table_name, a.nullable, a.column_name, a.data_length ");
		sql.append("from user_tab_cols a ");
		sql.append("where a.table_name in ");
		sql.append("(upper(?), upper(?))) a ");
		sql.append("left join USER_COL_COMMENTS b ");
		sql.append("on a.table_name = b.table_name ");
		sql.append("and a.column_name = b.column_name) a ");
		sql.append("where not exists ");
		sql.append("(select 1 from xg_custom_search b ");
		sql.append("where upper(a.dm) = upper(b.zd) ");
		sql.append("and b.form_id=? ");
		sql.append(")) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { ssb, ssb, form_id }, new String[] { "dm", "mc",
						"len", "nullable" });

		return list;
	}
	/**

	 * 查詢自定義查詢列
	 * 
	 * @author 伟大的骆
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getSearchColumn(String form_id)
			throws SQLException {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.zd dm,a.zdm mc,a.xssx ");
		sql.append(" from xg_custom_search a ");
		sql.append(" where form_id=? ");
		sql.append(" order by to_number(xssx) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { form_id }, new String[] { "dm", "mc","xssx" });

		return list;
	}
	
	/**
	 * 根據查詢視圖查詢結果
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSearchResultList(
			String search_view, String other_query, String[] output) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ");
		sql.append(search_view);
		sql.append(" where 1=1 ");
		sql.append(Base.isNull(other_query) ? "" : other_query);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, output);

		return list;
	}
	
	/**
	 * 保存查詢字段
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveSearchZd(String form_id, String zd, String zdm,
			String xssx, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_search";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_search ");
		sql.append("(form_id,zd,zdm,xssx)");
		sql.append("values");
		sql.append("(?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { form_id, zd, zdm, xssx };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 刪除查詢字段
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean deleteSearchZd(String form_id, String zd, User user)
			throws Exception {

		// 表名
		String tableName = "xg_custom_search";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_custom_search ");
		sql.append("where 1=1 ");
		sql.append("and form_id=? ");
		sql.append("and zd=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { form_id, zd };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 獲取前一字段顯示順序
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, String> getPreviousZdXssx(String form_id, String xssx) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select zd,xssx from  ");
		sql.append("(select rownum r, a.* ");
		sql.append("from (select a.* ");
		sql.append("from xg_custom_search a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.form_id = ? ");
		sql.append("order by to_number(a.xssx)) a) a ");
		sql.append("where a.r = (select r ");
		sql.append("from (select rownum r, a.* ");
		sql.append("from (select a.* ");
		sql.append("from xg_custom_search a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.form_id = ? ");
		sql.append("order by to_number(a.xssx)) a) ");
		sql.append("where xssx = ?) - 1 ");

		HashMap<String, String> previous = dao.getMap(sql.toString(),
				new String[] { form_id, form_id, xssx }, new String[] { "zd",
						"xssx" });

		return previous;
	}

	/**
	 * 獲取后一字段顯示順序
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, String> getNextZdXssx(String form_id, String xssx) {
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select zd,xssx from  ");
		sql.append("(select rownum r, a.* ");
		sql.append("from (select a.* ");
		sql.append("from xg_custom_search a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.form_id = ? ");
		sql.append("order by to_number(a.xssx)) a) a ");
		sql.append("where a.r = (select r ");
		sql.append("from (select rownum r, a.* ");
		sql.append("from (select a.* ");
		sql.append("from xg_custom_search a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.form_id = ? ");
		sql.append("order by to_number(a.xssx)) a) ");
		sql.append("where xssx = ?) + 1 ");

		HashMap<String, String> next = dao.getMap(sql.toString(),
				new String[] { form_id, form_id, xssx }, new String[] { "zd",
						"xssx" });

		return next;
	}
	
	/**
	 * 保存查詢字段顯示順序
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveSearchZdXssx(String form_id, String[] xssx, String[] zd,
			User user) throws Exception {

		// 表名
		String tableName = "xg_custom_search";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_custom_search ");
		sql.append("set xssx = ? ");
		sql.append("where form_id=? ");
		sql.append("and zd=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < zd.length; i++) {
			String[] value = new String[] { xssx[i], form_id, zd[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 保存查詢字段顯示順序
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void saveSearchZdXssx(String form_id, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_custom_search a ");
		sql.append("set xssx = ");
		sql.append("(select r ");
		sql.append("from (select rownum r, a.* ");
		sql.append("from (select a.* ");
		sql.append("from xg_custom_search a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.form_id = ? ");
		sql.append("order by to_number(a.xssx)) a) b where a.zd = b.zd) ");
		sql.append("where a.form_id = ? ");

		dao.runUpdate(sql.toString(), new String[] { form_id, form_id });
	}
	// ===============表单查詢 end=====================
	
	// ===============表单设置 begin=====================
	/**
	 * 获得某自定义表单的最大显示顺序
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String getMaxXssx(String form_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(xssx)) xssx from ");
		sql.append("xg_custom_table where form_id = ? ");

		String xssx = dao.getOneRs(sql.toString(), new String[] { form_id },
				"xssx");

		return xssx;
	}

	/**
	 * 新增自定义表单Table
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean insertCustomTable(String table_id, String form_id,
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
	 * 新增自定義表單內容
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean insertCustomContent(String table_id, String[] row,
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
		sql.append("select a.table_id,a.row_num,a.column_num, ");
		sql.append("a.rowspan,a.colspan ");
		sql.append(",zd,zdm,zdlx,ssb ");
		sql.append(",textarea_rows,input_width,input_postfix ");
		sql.append(",source_table,option_dm,option_mc,checked ");
		sql.append(",isnull,checked ");
		sql.append("from xg_custom_content a ");
		sql.append("where table_id=? ");
		sql.append("order by to_number(a.row_num),to_number(a.column_num) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { table_id }, new String[] { "table_id",
						"row_num", "column_num", "rowspan", "colspan", "zd",
						"zdm", "zdlx", "ssb", "textarea_rows", "input_width",
						"input_postfix", "source_table", "option_dm",
						"option_mc", "isnull", "checked" });

		return list;
	}

	/**
	 * 保存合并列
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveCoalitionCol(String[] table_id, String[] row_id,
			String[] column_id, String[] col_span, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_custom_content ");
		sql.append("set colspan = ? ");
		sql.append("where table_id=? ");
		sql.append("and row_num=? ");
		sql.append("and column_num=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < table_id.length; i++) {
			String[] value = new String[] { col_span[i], table_id[i],
					row_id[i], column_id[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 保存合并行
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveCoalitionRow(String[] table_id, String[] row_id,
			String[] column_id, String[] row_span, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_custom_content ");
		sql.append("set rowspan = ? ");
		sql.append("where table_id=? ");
		sql.append("and row_num=? ");
		sql.append("and column_num=? ");
		if (!Base.isNull(row_span[0])) {
			sql.append("and (rowspan = 'no' or rowspan is null) ");
		}

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < table_id.length; i++) {
			String[] value = new String[] { row_span[i], table_id[i],
					row_id[i], column_id[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 初始化合并行
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initRowspan() throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append("update xg_custom_content a ");
		sql.append("set a.rowspan = '' ");
		sql.append("where exists (select 1 ");
		sql.append("from (select table_id, row_num, count(1) num ");
		sql.append("from xg_custom_content a ");
		sql.append("where (rowspan = 'no' or rowspan is null) ");
		sql.append("group by table_id, row_num) b ");
		sql.append("where a.table_id = b.table_id ");
		sql.append("and a.row_num = b.row_num ");
		sql.append("and b.num = '4') ");

		DAO dao = DAO.getInstance();
		dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * 获得合并信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getCoalitionInfo(String table_id,
			String row_num, String column_num) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select rowspan,colspan ");
		sql.append("from xg_custom_content ");
		sql.append("where table_id=? ");
		sql.append("and row_num=? ");
		sql.append("and column_num=? ");

		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				table_id, row_num, column_num }, new String[] { "rowspan",
				"colspan" });

		return map;
	}

	/**
	 * 根據配置表查詢表字段
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTableConfigureList(String form_id,
			String ssb) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.zd dm,a.zdm mc,input_width len,ssb ");
		sql.append(",zdlx,textarea_rows,input_width,input_postfix ");
		sql.append(",source_table,option_dm,option_mc ");
		sql.append(",checked,isnull,onlyone,edit ");
		sql.append("from xg_custom_zdb a ");
		sql.append("where ssb = ? ");
		sql.append("and not exists (select 1 ");
		sql.append("from xg_custom_content b, xg_custom_table c ");
		sql.append("where a.zd = b.zd ");
		sql.append("and a.ssb = b.ssb ");
		sql.append("and b.table_id = c.table_id ");
		sql.append("and c.form_id = ?) ");

		sql.append("order by to_number(xssx) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { ssb, form_id }, new String[] { "dm", "mc",
						"len", "ssb", "zdlx", "textarea_rows", "input_width",
						"input_postfix", "source_table", "option_dm",
						"option_mc", "checked", "isnull", "onlyone", "edit" });

		return list;
	}

	/**
	 * 获得指定表的字段
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableZdList(String form_id,
			String ssb) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select a.column_name dm, nvl(b.comments,a.column_name) mc, a.nullable, a.data_length len,e.zd ");
		sql.append("from (select a.table_name, a.nullable, a.column_name, a.data_length ");
		sql.append("from user_tab_cols a ");
		sql.append("where a.table_name in (upper(?), upper(?))) a ");
		sql.append("left join USER_COL_COMMENTS b ");
		sql.append("on a.table_name = b.table_name ");
		sql.append("and a.column_name = b.column_name ");
		sql.append("left join (select c.zd ");
		sql.append("from xg_custom_content c, xg_custom_table d ");
		sql.append("where c.table_id = d.table_id ");
		sql.append("and d.form_id = ?) e ");
		sql.append("on a.column_name = upper(e.zd) ");
		sql.append(") where zd is null order by nullable ");


		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { ssb, ssb, form_id }, new String[] { "dm", "mc",
						"len", "nullable" });

		return list;
	}
	
	/**
	 * 修改自定义表单内容
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean updateCustomContent(String table_id, String[] row_num,
			String[] column_num, String[] zd, String[] zdm, String[] zdlx,
			String[] ssb, String[] input_width, String[] textarea_rows,
			String[] input_postfix, String[] source_table, String[] option_dm,
			String[] option_mc, String[] isnull, String[] onlyone,
			String[] edit, String[] checked, User user) throws Exception {

		// 表名
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_custom_content ");
		sql.append("set zd = ? ");
		sql.append(", zdm = ? ");
		sql.append(", zdlx = ? ");
		sql.append(", ssb = ? ");
		sql.append(", input_width = ? ");
		sql.append(", textarea_rows = ? ");
		sql.append(", input_postfix = ? ");
		sql.append(", source_table = ? ");
		sql.append(", option_dm = ? ");
		sql.append(", option_mc = ? ");
		sql.append(", isnull = ? ");
		sql.append(", onlyone = ? ");
		sql.append(", edit = ? ");
		sql.append(", checked = ? ");
		sql.append("where table_id=? ");
		sql.append("and row_num=? ");
		sql.append("and column_num=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < row_num.length; i++) {
			String[] value = new String[] { zd[i], zdm[i], zdlx[i], ssb[i],
					input_width[i], textarea_rows[i], input_postfix[i],
					source_table[i], option_dm[i], option_mc[i], isnull[i],
					onlyone[i], edit[i], checked[i], table_id, row_num[i],
					column_num[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 获得数据源list
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getListBySource(String tablename,
			String dm, String mc) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(dm + " dm, ");
		sql.append(mc + " mc ");
		sql.append(" from ");
		sql.append(tablename);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	/**
	 * 获得国家地区列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getAreaList(String area_dm,
			String area_jb) {

		String dm = "";
		String query = "";

		if ("sheng".equalsIgnoreCase(area_jb)) {
			dm = "__0000";
		} else if ("shi".equalsIgnoreCase(area_jb)) {
			dm = area_dm.subSequence(0, 2) + "__00";
			query = " and qxdm <> '" + area_dm.subSequence(0, 2) + "0000' ";
		} else if ("xian".equalsIgnoreCase(area_jb)) {
			dm = area_dm.subSequence(0, 4) + "__";
			query = " and qxdm <> '" + area_dm.subSequence(0, 4) + "00' ";
		}

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select qxdm dm,qxmc mc, ");
		sql.append("substr(F_PINYIN(qxmc),0,1) py ");
		sql.append("from dmk_qx where qxdm like ? ");
		sql.append(query);
		sql.append("order by py ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { dm }, new String[] { "dm", "mc", "py" });

		return list;
	}

	/**
	 * 获得字段配置信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getConfigureInfo(String table_id,
			String row_num, String column_num) {

		StringBuilder sql = new StringBuilder();
		sql.append("select c.zd,a.zdm,c.ssb,c.zdlx,c.checked, ");
		sql.append("c.source_table,c.option_dm,c.option_mc, ");
		sql.append("c.input_width,c.input_postfix,c.textarea_rows, ");
		sql.append("c.onlyone,c.isnull,c.edit ");
		sql.append("from xg_custom_content a ");
		sql.append("left join (select b.* ");
		sql.append("from xg_custom_content b ");
		sql.append("where b.table_id = ? ");
		sql.append("and b.row_num = ? ");
		sql.append("and b.column_num = ?) c ");
		sql.append("on a.table_id = c.table_id ");
		sql.append("and a.row_num = c.row_num ");
		sql.append("where a.table_id = ? ");
		sql.append("and a.row_num = ? ");
		sql.append("and a.column_num = ? ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { table_id, row_num,
						String.valueOf(Integer.parseInt(column_num) + 1),
						table_id, row_num, column_num });

		return map;
	}
	// ===============表单设置 end=====================
}
