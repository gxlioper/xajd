package xsgzgl.customForm.demo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DEMO_Service类
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

public class DemoFormService extends CommService {

	DemoFormDAO dao = new DemoFormDAO();

	// ===============查詢結果 begin=====================

	/**
	 * 獲得表頭【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCustomFormTop(DemoFormModel model,
			User user) {

		DAO dao = DAO.getInstance();
		String[] en = new String[] { "pk", "ssmk", "bdmc", "sjb", "cz" };
		String[] cn = new String[] { "", "所屬模塊", "表單名稱", "數據表", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 查詢數據【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> ggetCustomFormList(DemoFormForm myForm,
			DemoFormModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] value = new String[] { "C4A7E0884B916956E040007F010068C3",
				"學生信息", "在校生維護", "xg_xsxx_xsxxb" };
		list.add(value);

		value = new String[] { "002", "評獎評優", "獎學金申請",

		"XG_PJPY_PJXMSQB" };
		list.add(value);

		return list;
	}

	/**
	 * 構建HTML【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public String createCustomFormHTML(SearchRsModel rsModel,
			DemoFormModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String form_id = rs[0];// FormID
				String ssb = rs[3];// 所屬表

				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" ");
				html.append("name=\"primarykey_checkVal\" ");
				html.append("value=\"" + form_id + "\"/>");
				html.append("</td>");

				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					html.append("style=\"\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}

				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				html.append("style=\"\" ");
				html.append(">");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"xszdSetup('" + form_id + "','" + ssb
						+ "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("字段設置");
				html.append("</font>");
				html.append("</a>");

				html.append("  ");

				html.append("<a href=\"#\" ");
				html.append("onclick=\"jgcxSetup('" + form_id
						+ "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("查詢設置");
				html.append("</font>");
				html.append("</a>");

				html.append("</td>");

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	// ===============查詢結果 end=====================

	// ===============表單操作 begin=====================

	/**
	 * 構造字段列表（補空行）
	 * 
	 * @param ssb
	 *            所屬表
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZdList(String ssb) {

		// 最大行數
		int max_rows = 20;
		// 處理完成后的列表
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,

		String>>();
		// 查詢自定義配置表的配置字段
		List<HashMap<String, String>> zdList = getTableZdList(ssb);

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				list.add(zdList.get(i));
			}

			// 不滿最大行數，補空行
			if (zdList.size() < max_rows) {
				for (int i = 0; i < max_rows - zdList.size(); i++) {
					list.add(new HashMap<String, String>());
				}
			}
		}

		return list;
	}

	/**
	 * 查詢自定義配置表的配置字段
	 * 
	 * @param ssb
	 *            所屬表
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTableZdList(String ssb) {

		// 執行查詢操作
		List<HashMap<String, String>> list = dao.getTableZdList(ssb);

		return list;
	}

	/**
	 * 保存新增加的表單
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveTable(DemoFormModel model, User user) {

		Random random = new Random();
		boolean flag = false;

		String table_id = String.valueOf(random.nextLong());
		String form_id = model.getForm_id();// 表單ID
		String title = model.getTitle();// 標題
		String row_num = model.getRow_num();// 行數
		String xssx = model.getXssx();// 顯示順序

		model.setTable_id(table_id);

		try {
			flag = dao.insertCustomTable(table_id, form_id, title, row_num,
					xssx, user);

			if (flag) {
				saveContent(model, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存表單內容
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveContent(DemoFormModel model, User user) {

		boolean flag = false;

		String table_id = model.getTable_id();// 表ID

		int num = Integer.parseInt(model.getRow_num()) * 4;
		String[] row = new String[num];
		String[] column = new String[num];

		String row_num = "1";
		String column_num = "1";

		for (int i = 0; i < num; i++) {
			row[i] = row_num;
			column[i] = column_num;

			column_num = String.valueOf(Integer.parseInt(column_num) + 1);

			if ((i + 1) % 4 == 0) {
				row_num = String.valueOf(Integer.parseInt(row_num) + 1);
				column_num = "1";
			}
		}

		try {
			flag = dao.insertCustomContent(table_id, row, column, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 顯示自定義表單
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void showCustomForm(DemoFormModel model, User user,
			HttpServletResponse response) throws IOException {

		// FormID
		String form_id = model.getForm_id();
		// TABLE List
		List<HashMap<String, String>> tableList = dao.getTableList(form_id);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		if (tableList != null && tableList.size() > 0) {
			for (int i = 0; i < tableList.size(); i++) {
				HashMap<String, String> tabMap = tableList.get(i);
				// TableID
				String table_id = tabMap.get("table_id");
				// 標題
				String title = tabMap.get("title");
				// 行數
				int row_num = Integer.parseInt(tabMap.get("row_num"));
				
				html.append("<table width=\"100%\" class=\"formlist\">");
				//---------------表頭 begin-------------------
				html.append("<thead onclick=\"\" style=\"\">");
				html.append("<tr>");
				html.append("<td colspan=\"4\">");
				html.append("<span>");
				html.append(title);
				html.append("</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				//---------------表頭 end----------------------
				
				// 內容 List
				List<HashMap<String, String>> contentList = dao
						.getContentList(table_id);
				
				// ---------------內容 begin-------------------
				html.append("<tbody>");
				for (int j = 1; j <= row_num; j++) {
					html.append("<tr>");
					
					// ---------------單元格 begin-------------------
					if (contentList != null && contentList.size() > 0) {
						for (int k = 0; k < contentList.size(); k++) {
							HashMap<String, String> conMap = contentList.get(k);
							// 行
							String row = conMap.get("row_num");
							// 列
							String column = conMap.get("column_num");
							
							if (row.equalsIgnoreCase(String.valueOf(j))) {
								
								//TdID
								String td_id = table_id + "_" + row + "_"
										+ column;
								html.append("<td name=\"td_content\" ");
								html.append("id=\"" + td_id + "\"");
								html.append("style=\"width:25%;height:25px;cursor:hand\" ");
								html.append("onclick=\"clickTd(this)\" ");
								html.append(">");
								html.append("&nbsp;");
								html.append("</td>");
							}
						}
					}
					// ---------------單元格 end-------------------
					
					html.append("</tr>");
				}

				html.append("</tbody>");
				// ---------------內容 begin-------------------
				
				html.append("</table>");
			}
		}
		

		response.getWriter().print(html.toString());
	}

	/**
	 * 創建選中的對象
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createClickedObj(DemoFormModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		//TdID
		String[] td_id = model.getTd_id();
		
		if (td_id != null && td_id.length > 0) {
			for (int i = 0; i < td_id.length; i++) {
				
				// 表ID
				String table_id = td_id[i].split("_")[0];
				// 行
				String row = td_id[i].split("_")[1];
				// 列
				String column = td_id[i].split("_")[2];

				html.append("<input type=\"text\" ");
				html.append("name=\"table_obj_name\" ");
				html.append("id=\"table_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + table_id + "\" ");
				html.append("/>");

				html.append("<input type=\"text\" ");
				html.append("name=\"row_obj_name\" ");
				html.append("id=\"row_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + row + "\" ");
				html.append("/>");

				html.append("<input type=\"text\" ");
				html.append("name=\"column_obj_name\" ");
				html.append("id=\"column_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + column + "\" ");
				html.append("/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存合并单元格
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveCoalition(DemoFormModel model, User user) {

		boolean flag = false;

		try {
			//flag = dao.updateCustomContent(table_id, row, column, rowspan, colspan, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	// ===============表單操作 end=====================

}
