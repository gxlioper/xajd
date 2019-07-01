package xsgzgl.xtwh.general.customform;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_Service类
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

public class CustomFormService extends CommService {

	CustomFormDAO dao = new CustomFormDAO();

	// ===============表单管理 begin=====================
	/**
	 * 獲得表頭【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCustomFormTop(
			CustomFormModel model, User user) {

		DAO dao = DAO.getInstance();
		String[] en = new String[] { "pk", "form_id", "form_name", "ssmk",
				"bdmc", "sjb", "xxst", "cxst", "cz" };
		String[] cn = new String[] { "", "表单ID", "表单名称", "所属模块", "数据表", "详细视图",
				"查询视图", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 查詢數據【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getCustomFormList(CustomFormForm myForm,
			CustomFormModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getCustomFormList(myForm, model, user);
	}

	/**
	 * 構建HTML【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public String createCustomFormHTML(SearchRsModel rsModel,
			CustomFormModel model, ArrayList<String[]> rsArrList, User user) {

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String form_id = rs[1];// 表单ID
				String souce_table = rs[rs.length - 4];// 数据表
				String detail_view = rs[rs.length - 3];// 详细视图
				String search_view = rs[rs.length - 2];// 查询视图

				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" ");
				html.append("name=\"primarykey_checkVal\" ");
				html.append("value=\"" + form_id + "\"/>");
				html.append("</td>");

				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					html.append("style=\"\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}

				// =============操作 begin===================
				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				html.append("style=\"\" ");
				html.append(">");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"xszdSetup('" + form_id + "','"
						+ souce_table + "','" + detail_view
						+ "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("字段设置");
				html.append("</font>");
				html.append("</a>");

				html.append("  ");

				html.append("<a href=\"#\" ");
				html.append("onclick=\"jgcxSetup('" + form_id + "','"
						+ search_view + "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("查询设置");
				html.append("</font>");
				html.append("</a>");

				html.append("</td>");
				// =============操作 end===================

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 保存數據【自定義表單】
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveCustomForm(CustomFormModel model, User user) {

		boolean flag = false;

		String form_id = model.getForm_id();// '表单ID';
		String form_name = model.getForm_name();// '表单名称';
		String ssmk = model.getSsmk();// '所属模块';
		
		String source_table = model.getSource_table();// '数据表';
		String source_table_pk = model.getSource_table_pk();// '主键';
		String assistant_table_one = model.getAssistant_table_one();// '辅助表1';
		String assistant_table_one_zd = model.getAssistant_table_one_zd();// '辅助表1字段';
		String assistant_table_one_relate = model.getAssistant_table_one_relate();// '辅助表1关联';
		String assistant_table_two = model.getAssistant_table_two();// '辅助表2';
		String assistant_table_two_zd = model.getAssistant_table_two_zd();// '辅助表2字段';
		String assistant_table_two_relate = model.getAssistant_table_two_relate();// '辅助表2关联';
		String detail_view = model.getDetail_view();// '详细视图';
		String search_view = model.getSearch_view();// '查询视图';

		try {
			flag = dao.saveCustomForm(form_id, form_name, ssmk, source_table
					.toLowerCase(), source_table_pk.toLowerCase(),
					assistant_table_one.toLowerCase(), assistant_table_one_zd
							.toLowerCase(), assistant_table_one_relate
							.toLowerCase(), assistant_table_two.toLowerCase(),
					assistant_table_two_zd.toLowerCase(),
					assistant_table_two_relate.toLowerCase(), detail_view
							.toLowerCase(), search_view.toLowerCase(), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 刪除數據【自定義表單】
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean deleteCustomForm(CustomFormModel model, User user) {

		boolean flag = false;
		String[] pkValue = model.getPkValue();

		try {
			flag = dao.deleteCustomForm(pkValue, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	// ===============表单管理 end=====================

	// ===============表单参数 begin=====================
	/**
	 * 创建选择主键DIV
	 * 
	 * @author 伟大的骆
	 */
	public void createChoosePkDiv(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {
		
		// 数据表
		String source_table = model.getSource_table();
		// 主键
		String source_table_pk = model.getSource_table_pk();
		// 字段列表
		List<HashMap<String, String>> list = getTableZdList(source_table);
		// 行数
		int rownum = 0;
		// 列数
		int colnum = 4;
		if (list != null && list.size() > 0) {
			int space = list.size() % colnum;
			rownum = (space == 0) ? list.size() / colnum : list.size() / colnum
					+ 1;
		}

		rownum = rownum < 7 ? 7 : rownum;
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:250px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tbody>");
		
		int num = 0;
		
		String[] pk = null;
		if(!Base.isNull(source_table_pk)){
			pk = source_table_pk.split(",");
		}
		for (int i = 0; i < rownum; i++) {
			html.append("<tr>");
			for (int j = 0; j < colnum; j++) {
				
				html.append("<td width=\"25%\">");
				if (num < list.size()) {
					HashMap<String, String> map = list.get(num);
					String dm = map.get("dm").toLowerCase();
					String mc = map.get("mc");
					html.append("<input type=\"checkbox\" ");
					html.append("name=\"checkbox_pk\" ");
					html.append("id=\"checkbox_" + dm + "\" ");
					html.append("value=\"" + dm + "\" ");
					if (pk != null && pk.length > 0) {
						for (int k = 0; k < pk.length; k++) {
							if(dm.equalsIgnoreCase(pk[k])){
								html.append("checked=\"checked\" ");
								break;
							}
						}
					}
					html.append("/>");
					
					html.append("<span style=\"width: 60%;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;\">");	
					if(!Base.isNull(mc)){
						html.append(mc);
					}else{
						html.append("未维护");
					}
					html.append("</span>");
					
					num++;
				}else{
					html.append("&nbsp;");
				}
				html.append("</td>");
			}
			html.append("</tr>");
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");	
		html.append("<button type=\"button\"  onclick=\"saveChoosePk();return false;\">保 存</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建选择关联DIV
	 * 
	 * @author 伟大的骆
	 */
	public void createChooseRelateDiv(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {
		
		// 数据表
		String source_table = model.getSource_table();
		// 辅助表1
		String assistant_table_one = model.getAssistant_table_one();
		// 辅助表2
		String assistant_table_two = model.getAssistant_table_two();
		// 辅助表
		String assistant_table = Base.isNull(assistant_table_two) ? assistant_table_one
				: assistant_table_two;
		// 类型
		String lx = Base.isNull(assistant_table_two) ? "one" : "two";
		// 字段列表
		List<HashMap<String, String>> sourceList = getTableZdList(source_table);
		List<HashMap<String, String>> assisList = getTableZdList(assistant_table);
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:250px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<input type=\"hidden\" id=\"hidden_lx\" value=\"" + lx + "\"/>");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td width=\"35%\">"+source_table+"</td>");
		html.append("<td width=\"35%\">"+assistant_table+"</td>");
		html.append("<td>匹配字段</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<td>");
		html.append("<select size=\"12\" style=\"width:100%\" id=\"select_source\" ondblclick=\"confirmRelate()\">");
		for(int i=0;i<sourceList.size();i++){
			String dm = sourceList.get(i).get("dm");
			String mc = sourceList.get(i).get("mc");
			html.append("<option value=\"" + dm + "\">" + mc + "</option>");
		}
		html.append("</select>");
		html.append("</td>");
		html.append("<td>");
		html.append("<select size=\"12\" style=\"width:100%\" id=\"select_assis\" ondblclick=\"confirmRelate()\">");
		for(int i=0;i<assisList.size();i++){
			String dm = assisList.get(i).get("dm");
			String mc = assisList.get(i).get("mc");
			html.append("<option value=\"" + dm + "\">" + mc + "</option>");
		}
		html.append("</select>");
		html.append("</td>");
		html.append("<td>");
		html.append("<select size=\"12\" style=\"width:100%\" id=\"select_finally\" ondblclick=\"cancelRelate()\">");
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");	
		html.append("<button type=\"button\"  onclick=\"saveChooseRelate();return false;\">保 存</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 根據Form_id查詢表单信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getCustomFormInfo(String form_id) {
		HashMap<String, String> map = dao.getCustomFormInfo(form_id);
		return map;
	}
	// ===============表单参数 end=====================
	
	// ===============表单查詢 begin=====================
	/**
	 * 创建自定義查詢
	 * 
	 * @author 伟大的骆
	 * @throws SQLException
	 * @throws IOException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void createCustomSearch(CustomFormModel model, User user,
			HttpServletResponse response) throws SQLException, IOException {

		response.setContentType("text/html;charset=gbk");

		// FormID
		String form_id = model.getForm_id();
		
		// 查詢列
		List<HashMap<String, String>> topList = dao.getSearchColumn(form_id);
		List<HashMap<String, String>> resultList = getResultList(model, topList);
		
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" class=\"dateline\">");

		// ---------------內容 begin-------------------
		html.append("<thead>");
		html.append("<tr>");
		
		int count = 1;
		
		if (topList != null && topList.size() > 0) {

			for (int i = 0; i < topList.size(); i++) {

				// 代碼
				String dm = topList.get(i).get("dm");
				// 名稱
				String mc = topList.get(i).get("mc");
				// 顯示順序
				String xssx = topList.get(i).get("xssx");
				
				html.append("<td>");
				
				html.append("<div class=\"tab_szcd\" ");
				html.append("onmouseover=\"javascript:document.getElementById('downmenu"+count+"').style.display='block'\" ");
				html.append("onmouseout=\"javascript:document.getElementById('downmenu"+count+"').style.display='none'\" ");
				html.append("style=\"position:relative;\">");
				
				html.append("<a class=\"ico_sz\" ");
				html.append("onclick=\"showhid('downmenu"+count+"');\" ");
				html.append("href=\"#\">");
				html.append(mc);
				html.append("</a>");
				
				html.append("<div class=\"btn_list\" ");
				html.append("id=\"downmenu"+count+"\" ");
				html.append("style=\"display:none;\">");
				html.append("<ul>");

				//=============左移 begin=================
				html.append("<li>");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"leftMove('" + dm + "','" + xssx
						+ "')\" ");
				html.append("class=\"btn_zy\">");
				html.append("左移");
				html.append("</a>");
				html.append("</li>");
				// =============左移end=================

				// =============右移 begin=================
				html.append("<li>");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"rightMove('" + dm + "','" + xssx
						+ "')\" ");
				html.append("class=\"btn_yy\">");
				html.append("右移");
				html.append("</a>");
				html.append("</li>");
				// =============右移end=================
				
				//=============修改 begin=================
				html.append("<li>");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"showEditDiv('" + dm + "','" + mc
						+ "')\" ");
				html.append("class=\"btn_xg noborder\">");
				html.append("修改");
				html.append("</a>");
				html.append("</li>");
				//=============修改end=================
				
				//=============刪除 begin=================
				html.append("<li>");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"deleteSearchZd('" + dm + "')\" ");
				html.append("class=\"btn_sc noborder\" >");
				html.append("刪除");
				html.append("</a");
				html.append("</li>");
				//=============刪除end=================
				
				html.append("</ul>");
				html.append("</div>");
		       
				html.append("</div>");
				
				html.append("</td>");
				
				count = Integer.parseInt(xssx) + 1;
			}
		}
		
		html.append("<td>");
		
		html.append("<div class=\"tab_szcd\" ");
		html.append("onmouseover=\"javascript:document.getElementById('downmenu"+count+"').style.display='block'\" ");
		html.append("onmouseout=\"javascript:document.getElementById('downmenu"+count+"').style.display='none'\" ");
		html.append("style=\"position:relative;\">");
		
		html.append("<a class=\"ico_sz\" ");
		html.append("onclick=\"showhid('downmenu"+count+"');\" ");
		html.append("href=\"#\">");
		html.append("未知列");
		html.append("</a>");
		
		html.append("<div class=\"btn_list\" ");
		html.append("id=\"downmenu"+count+"\" ");
		html.append("style=\"display:none;\">");
		html.append("<input type=\"hidden\" id=\"hidden_xssx\" value=\""+count+"\"/>");
		html.append("<ul>");
//		html.append("<li><a href=\"#\" class=\"btn_zy\">左移</a></li>");
//		html.append("<li><a href=\"#\" class=\"btn_yy\">右移</a></li>");
//		html.append("<li><a href=\"#\" class=\"btn_sy\">上移</a></li>");
//		html.append("<li><a href=\"#\" class=\"btn_xy\">下移</a></li>");
//		html.append("<li><a href=\"#\" class=\"btn_xg noborder\">修改</a></li>");
//		html.append("<li><a href=\"#\" class=\"btn_sc noborder\">删除</a></li>");
		html.append("<li>");
		html.append("<a href=\"#\" ");
		html.append("onclick=\"saveSearchZd()\" ");
		html.append("class=\"btn_dc noborder\" >");
		html.append("插入字段");
		html.append("</a");
		html.append("</li>");
		html.append("</ul>");
		html.append("</div>");
       
		html.append("</div>");
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		// ---------------內容 begin-------------------

		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 詢視圖查詢結果
	 * 
	 * @author 伟大的骆
	 * @throws SQLException
	 */
	private List<HashMap<String, String>> getResultList(CustomFormModel model,
			List<HashMap<String, String>> topList) throws SQLException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 查詢視圖
		String search_view = model.getSearch_view();

		int maxRow = 11;

		if (topList != null && topList.size() > 0) {

		}

		for (int i = 0; i < maxRow; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "&nbsp;");
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 创建操作字段TABLE
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void createSearchCzzdTable(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {

		// FormID
		String form_id = model.getForm_id();
		// 查詢視圖
		String search_view = model.getSearch_view();
		// 最大行数
		int max_rows = 14;
		// 字段列表
		List<HashMap<String, String>> zdList = getTableZdList(form_id, search_view);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table class=\"formlist\" ");
		html.append("id=\"table_czzd\" ");
		html.append("style=\"width: 80%\">");
		html.append("<thead>");
		html.append("<tr><td>字段操作</td></tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);
				String dm = map.get("dm").toLowerCase();
				String mc = map.get("mc");

				html.append("<tr>");
				html.append("<td ");
				html.append("id=\"td_"+dm+"\" ");
				html.append("style=\"cursor:hand\" ");
				html.append("onclick=\"clickCzzdTd('"+dm+"')\" ");
				html.append(">");
				
				if (!Base.isNull(mc)) {
					if (mc.length() > 4) {
						html.append(mc.substring(0, 4));
					}else{
						html.append(mc);
					}
				} else {
					html.append(mc);
				}

				html.append("</td>");
				html.append("</tr>");
			}
			if (zdList.size() < max_rows) {
				for (int i = 0; i < (max_rows - zdList.size()); i++) {
					html.append("<tr>");
					html.append("<td>");
					html.append("&nbsp;");
					html.append("</td>");
					html.append("</tr>");
				}
			}
		}
		html.append("</tbody>");
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存查詢字段
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveSearchZd(CustomFormModel model, User user) {

		boolean flag = false;

		// FormID
		String form_id = model.getForm_id();
		// 字段
		String zd = model.getZd();
		// 字段名
		String zdm = model.getZdm();
		// 顯示順序
		String xssx = model.getXssx();

		try {
			flag = dao.saveSearchZd(form_id, zd, zdm, xssx, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 刪除查詢字段
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteSearchZd(CustomFormModel model, User user) {

		boolean flag = false;

		// FormID
		String form_id = model.getForm_id();
		// 字段
		String zd = model.getZd();

		try {
			flag = dao.deleteSearchZd(form_id, zd, user);
			
			if(flag){
				dao.saveSearchZdXssx(form_id, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存查詢字段
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveSearchZdXssx(CustomFormModel model, User user) {

		boolean flag = false;

		// FormID
		String form_id = model.getForm_id();
		// 类型
		String lx = model.getLx();
		// 字段
		String zd = model.getZd();
		// 显示顺序
		String xssx = model.getXssx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("left".equalsIgnoreCase(lx)) {//左移
			map = dao.getPreviousZdXssx(form_id, xssx);
		} else {//右移
			map = dao.getNextZdXssx(form_id, xssx);
		}
		
		String other_zd = map.get("zd");;
		String other_xssx = map.get("xssx");
		
		ArrayList<String> zd_array = new ArrayList<String>();
		ArrayList<String> xssx_array = new ArrayList<String>();
			
		zd_array.add(zd);
		zd_array.add(other_zd);
		
		xssx_array.add(other_xssx);
		xssx_array.add(xssx);
		
		try {
			flag = dao.saveSearchZdXssx(form_id, xssx_array
					.toArray(new String[] {}), zd_array
					.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	// ===============表单查詢 end=====================	
	
	// ===============表单设置 begin=====================
	/**
	 * 创建自定義表單
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createCustomForm(CustomFormModel model, User user,
			HttpServletResponse response) throws IOException {

		// FormID
		String form_id = model.getForm_id();
		// 工程路径
		String contextPath = model.getContextPath();

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
				// ---------------表頭 begin-------------------
				html.append("<thead onclick=\"\" style=\"\">");
				html.append("<tr>");
				html.append("<td colspan=\"4\">");
				html.append("<span>");
				html.append(title);
				html.append("</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				// ---------------表頭 end----------------------

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
							
							// 工程路径
							conMap.put("contextPath", contextPath);
							// 行
							String row = conMap.get("row_num");
							// 列
							String column = conMap.get("column_num");
							// 列合并
							String colspan = conMap.get("colspan");
							// 行合并
							String rowspan = conMap.get("rowspan");

							if (row.equalsIgnoreCase(String.valueOf(j))) {

								if (!"yes".equalsIgnoreCase(colspan)
										&& !"yes".equalsIgnoreCase(rowspan)) {

									// TdID
									String td_id = table_id + "!!luojw!!" + row
											+ "!!luojw!!" + column;
									if ("1".equalsIgnoreCase(column)
											|| "3".equalsIgnoreCase(column)) {
										html.append("<th ");
									} else {
										html.append("<td ");
									}
									html.append("name=\"td_content\" ");
									html.append("id=\"" + td_id + "\"");
									html.append("style=\"width:25%;height:25px;cursor:hand\" ");
									html.append("onclick=\"clickTd(this)\" ");
									html.append("colspan=\"" + colspan + "\" ");
									html.append("rowspan=\"" + rowspan + "\" ");
									html.append(">");
									html.append(createObjectHtml(conMap));
									html.append("&nbsp;");

									if ("1".equalsIgnoreCase(column)
											|| "3".equalsIgnoreCase(column)) {
										html.append("</th> ");
									} else {
										html.append("</td> ");
									}
								}
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
	// ===============表单设置 end=====================
	
	// ===============表单操作相关 begin=====================
	/**
	 * 获得某自定义表单的最大显示顺序
	 * 
	 * @author 伟大的骆
	 */
	public String getMaxXssx(String form_id) {
		String xssx = dao.getMaxXssx(form_id);
		xssx = Base.isNull(xssx) ? "1" : xssx;
		xssx = String.valueOf(Integer.parseInt(xssx) + 1);
		return xssx;
	}

	/**
	 * 保存表單
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveTable(CustomFormModel model, User user) {

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
				flag = saveContent(model, user);
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
	public boolean saveContent(CustomFormModel model, User user) {

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
	 * 創建選中的對象
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createClickedObj(CustomFormModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		// TdID
		String[] td_id = null;// model.getTd_id();

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
	// ===============表单操作相关 begin=====================
	
	// ===============表单合并相关 begin=====================
	/**
	 * 保存合并列
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveCoalitionCol(CustomFormModel model, User user) {

		boolean flag = false;

		// TableID
		String table_id = model.getTable_id();
		// 行数
		String row_num = model.getRow_num();

		ArrayList<String> tableId = new ArrayList<String>();
		ArrayList<String> rowId = new ArrayList<String>();
		ArrayList<String> columnId = new ArrayList<String>();
		ArrayList<String> colSpan = new ArrayList<String>();

		for (int i = 1; i <= 4; i++) {
			String span = "yes";
			span = (i == 1) ? "no" : span;
			span = (i == 2) ? "3" : span;
			tableId.add(table_id);
			rowId.add(row_num);
			columnId.add(String.valueOf(i));
			colSpan.add(span);
		}

		try {
			flag = dao.saveCoalitionCol(tableId.toArray(new String[] {}), rowId
					.toArray(new String[] {}), columnId
					.toArray(new String[] {}),
					colSpan.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 取消合并列
	 * 
	 * @author 伟大的骆
	 */
	public boolean cancelCoalitionCol(CustomFormModel model, User user) {

		boolean flag = false;

		// TableID
		String table_id = model.getTable_id();
		// 行数
		String row_num = model.getRow_num();

		ArrayList<String> tableId = new ArrayList<String>();
		ArrayList<String> rowId = new ArrayList<String>();
		ArrayList<String> columnId = new ArrayList<String>();
		ArrayList<String> colSpan = new ArrayList<String>();

		for (int i = 1; i <= 4; i++) {
			String span = "";
			tableId.add(table_id);
			rowId.add(row_num);
			columnId.add(String.valueOf(i));
			colSpan.add(span);
		}

		try {
			flag = dao.saveCoalitionCol(tableId.toArray(new String[] {}), rowId
					.toArray(new String[] {}), columnId
					.toArray(new String[] {}),
					colSpan.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存合并行
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveCoalitionRow(CustomFormModel model, User user) {

		boolean flag = false;

		// TableID
		String table_id = model.getTable_id();
		// 行数
		String row_num = model.getRow_num();
		// 列数
		String column_num = model.getColumn_num();
		// 合并行
		String rowspan = model.getRowspan();

		ArrayList<String> tableId = new ArrayList<String>();
		ArrayList<String> rowId = new ArrayList<String>();
		ArrayList<String> columnId = new ArrayList<String>();
		ArrayList<String> rowSpan = new ArrayList<String>();

		for (int i = Integer.parseInt(row_num); i < Integer.parseInt(row_num)
				+ Integer.parseInt(rowspan); i++) {
			for (int j = 1; j <= 4; j++) {
				String span = "no";
				if (column_num.equalsIgnoreCase(String.valueOf(j))) {
					span = (i == Integer.parseInt(row_num)) ? rowspan : "yes";
				}

				tableId.add(table_id);
				rowId.add(String.valueOf(i));
				columnId.add(String.valueOf(j));
				rowSpan.add(span);
			}
		}

		try {
			flag = dao.saveCoalitionRow(tableId.toArray(new String[] {}), rowId
					.toArray(new String[] {}), columnId
					.toArray(new String[] {}),
					rowSpan.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 取消合并行
	 * 
	 * @author 伟大的骆
	 */
	public boolean cancelCoalitionRow(CustomFormModel model, User user) {

		boolean flag = false;

		// TableID
		String table_id = model.getTable_id();
		// 行数
		String row_num = model.getRow_num();
		// 列数
		String column_num = model.getColumn_num();
		// 获得合并行
		String pk = "table_id||row_num||column_num";
		String pkValue = table_id + row_num + column_num;
		String rowspan = getOneValue("xg_custom_content", "rowspan", pk,
				pkValue);

		ArrayList<String> tableId = new ArrayList<String>();
		ArrayList<String> rowId = new ArrayList<String>();
		ArrayList<String> columnId = new ArrayList<String>();
		ArrayList<String> rowSpan = new ArrayList<String>();

		for (int i = Integer.parseInt(row_num); i < Integer.parseInt(row_num)
				+ Integer.parseInt(rowspan); i++) {
			// for (int j = 1; j <= 4; j++) {
			tableId.add(table_id);
			rowId.add(String.valueOf(i));
			columnId.add(column_num);
			rowSpan.add("");
			// }
		}

		try {
			flag = dao.saveCoalitionRow(tableId.toArray(new String[] {}), rowId
					.toArray(new String[] {}), columnId
					.toArray(new String[] {}),
					rowSpan.toArray(new String[] {}), user);

			// 初始化合并行
			dao.initRowspan();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;

	}

	/**
	 * 获得合并信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getCoalitionInfo(CustomFormModel model) {

		String table_id = model.getTable_id();// 'TableID';
		String row_num = model.getRow_num();// '行数';
		String column_num = model.getColumn_num();// '行数';

		HashMap<String, String> map = dao.getCoalitionInfo(table_id, row_num,
				column_num);

		return map;
	}
	// ===============表单合并相关 end=====================
	
	// ===============字段操作相关 begin=====================
	/**
	 * 创建操作字段TABLE
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void createCzzdTable(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {

		// 所属表
		String ssb = model.getSsb();
		// FormID
		String form_id = model.getForm_id();
		// 最大行数
		int max_rows = 14;
		// 字段列表
		List<HashMap<String, String>> zdList = getTableZdList(form_id, ssb);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table class=\"formlist\" style=\"width: 80%\">");
		html.append("<tbody>");
		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);
				String dm = map.get("dm").toLowerCase();
				String mc = map.get("mc");
				// 字段长度
				String len = map.get("len");
				len = Base.isNull(len) ? "no" : len;
				// 所属表
				String tableName = ssb;
				// 字段类型
				String zdlx = map.get("zdlx");
				zdlx = Base.isNull(zdlx) ? "text" : zdlx;
				// 宽度
				String input_width = map.get("input_width");
				input_width = Base.isNull(input_width) ? "" : input_width;
				// 文本框行数
				String textarea_rows = map.get("textarea_rows");
				textarea_rows = Base.isNull(textarea_rows) ? "" : textarea_rows;
				// 输入框后缀
				String input_postfix = map.get("input_postfix");
				input_postfix = Base.isNull(input_postfix) ? "" : input_postfix;
				// 表
				String source_table = map.get("source_table");
				source_table = Base.isNull(source_table) ? "" : source_table;
				// 表代码
				String option_dm = map.get("option_dm");
				option_dm = Base.isNull(option_dm) ? "" : option_dm;
				// 表名称
				String option_mc = map.get("option_mc");
				option_mc = Base.isNull(option_mc) ? "" : option_mc;
				// 允许为空
				String isnull = map.get("isnull");
				isnull = Base.isNull(isnull) ? "" : isnull;
				// 是否唯一
				String onlyone = map.get("onlyone");
				// 可否编辑
				String edit = map.get("edit");
				// 检测方法
				String checked = map.get("checked");
				checked = Base.isNull(checked) ? len : checked;

				html.append("<tr>");
				html.append("<td>");
				html.append("<span style=\"float:left\" title=\""+mc+"\">");
				if (!Base.isNull(mc)) {
					if (mc.length() > 4) {
						html.append(mc.substring(0, 4));
					}else{
						html.append(mc);
					}
				} else {
					html.append(mc);
				}
				
				html.append("<input type=\"hidden\" id=\"zdm_" + ssb + "_" + dm
						+ "\" value=\"" + mc + "\"/>");
				html.append("<input type=\"hidden\" id=\"ssb_" + ssb + "_" + dm
						+ "\" value=\"" + tableName + "\"/>");
				html.append("<input type=\"hidden\" id=\"zdlx_" + ssb + "_"
						+ dm + "\" value=\"" + zdlx + "\"/>");

				html.append("<input type=\"hidden\" id=\"input_width_" + ssb
						+ "_" + dm + "\" value=\"" + input_width + "\"/>");
				html.append("<input type=\"hidden\" id=\"textarea_rows_" + ssb
						+ "_" + dm + "\" value=\"" + textarea_rows + "\"/>");
				html.append("<input type=\"hidden\" id=\"input_postfix_" + ssb
						+ "_" + dm + "\" value=\"" + input_postfix + "\"/>");

				html.append("<input type=\"hidden\" id=\"source_table_" + ssb
						+ "_" + dm + "\" value=\"" + source_table + "\"/>");
				html.append("<input type=\"hidden\" id=\"option_dm_" + ssb
						+ "_" + dm + "\" value=\"" + option_dm + "\"/>");
				html.append("<input type=\"hidden\" id=\"option_mc_" + ssb
						+ "_" + dm + "\" value=\"" + option_mc + "\"/>");

				html.append("<input type=\"hidden\" id=\"isnull_" + ssb + "_"
						+ dm + "\" value=\"" + isnull + "\"/>");
				html.append("<input type=\"hidden\" id=\"onlyone_" + ssb + "_"
						+ dm + "\" value=\"" + onlyone + "\"/>");
				html.append("<input type=\"hidden\" id=\"edit_" + ssb + "_"
						+ dm + "\" value=\"" + edit + "\"/>");
				html.append("<input type=\"hidden\" id=\"checked_" + ssb + "_"
						+ dm + "\" value=\"" + checked + "\"/>");

				html.append("</span>");

				html.append("<span style=\"float:right\">");
				html.append("<a href=\"#\" id=\"a_table_" + dm
						+ "\" onclick=\"addZd('" + dm + "','" + ssb
						+ "');return false;\">");
				html.append("<font id=\"font_table_" + dm
						+ "\" color=\"blue\">");
				html.append("添加");
				html.append("</font>");
				html.append("</a>");
				html.append("</span>");

				html.append("</td>");
				html.append("</tr>");
			}
			if (zdList.size() < max_rows) {
				for (int i = 0; i < (max_rows - zdList.size()); i++) {
					html.append("<tr>");
					html.append("<td>");
					html.append("&nbsp;");
					html.append("</td>");
					html.append("</tr>");
				}
			}
		}
		html.append("</tbody>");
		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 所屬表查詢表字段
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getTableZdList(String form_id,
			String ssb) {

		List<HashMap<String, String>> list = dao.getSearchConfigureList(form_id,
				ssb);

		return list;
	}
	
	/**
	 * 保存字段
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveZd(CustomFormModel model, User user) {

		String table_id = model.getTable_id();
		
		ArrayList<String> row_num = new ArrayList<String>();
		ArrayList<String> column_num = new ArrayList<String>();
		
		ArrayList<String> zd = new ArrayList<String>();
		ArrayList<String> zdm = new ArrayList<String>();
		ArrayList<String> zdlx = new ArrayList<String>();
		ArrayList<String> ssb = new ArrayList<String>();
		
		ArrayList<String> input_width = new ArrayList<String>();
		ArrayList<String> textarea_rows = new ArrayList<String>();
		ArrayList<String> input_postfix = new ArrayList<String>();
		
		ArrayList<String> source_table = new ArrayList<String>();
		ArrayList<String> option_dm = new ArrayList<String>();
		ArrayList<String> option_mc = new ArrayList<String>();
		
		ArrayList<String> isnull = new ArrayList<String>();
		ArrayList<String> onlyone = new ArrayList<String>();
		ArrayList<String> edit = new ArrayList<String>();
		ArrayList<String> checked = new ArrayList<String>();

		for (int i = 0; i < 2; i++) {

			row_num.add(model.getRow_num());

			if (i == 0) {
				column_num.add(String.valueOf(Integer.parseInt(model
						.getColumn_num()) - 1));
				zd.add("");
				zdm.add(model.getZdm());
				zdlx.add("lable");
				ssb.add("");
				input_width.add("");
				textarea_rows.add("");
				input_postfix.add("");
				source_table.add("");
				option_dm.add("");
				option_mc.add("");
				isnull.add("");
				onlyone.add("");
				edit.add("");
				checked.add("");
			} else {
				column_num.add(model.getColumn_num());
				zd.add(model.getZd());
				zdm.add("");
				zdlx.add(model.getZdlx());
				ssb.add(model.getSsb());
				input_width.add(model.getInput_width());
				textarea_rows.add(model.getTextarea_rows());
				input_postfix.add(model.getInput_postfix());
				source_table.add(model.getSource_table());
				option_dm.add(model.getOption_dm());
				option_mc.add(model.getOption_mc());
				isnull.add(model.getIsnull());
				onlyone.add(model.getOnlyone());
				edit.add(model.getEdit());
				checked.add(model.getChecked());
			}
		}

		boolean flag = false;

		try {
			flag = dao.updateCustomContent(table_id, row_num
					.toArray(new String[] {}), column_num
					.toArray(new String[] {}), zd.toArray(new String[] {}), zdm
					.toArray(new String[] {}), zdlx.toArray(new String[] {}),
					ssb.toArray(new String[] {}), input_width
							.toArray(new String[] {}), textarea_rows
							.toArray(new String[] {}), input_postfix
							.toArray(new String[] {}), source_table
							.toArray(new String[] {}), option_dm
							.toArray(new String[] {}), option_mc
							.toArray(new String[] {}), isnull
							.toArray(new String[] {}), onlyone
							.toArray(new String[] {}), edit
							.toArray(new String[] {}), checked
							.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	// ===============字段操作相关 end=====================
	
	// ===============表单对象相关 begin=====================
	/**
	 * 创建对象HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private String createObjectHtml(HashMap<String, String> map) {

		String table_id = map.get("table_id");// 'TableID';
		String row_num = map.get("row_num");// '行';
		String column_num = map.get("column_num");// '列';
		String contextPath = map.get("contextPath");// 工程路径
		
		String zd = map.get("zd");// '字段';
		String zdm = map.get("zdm");// '字段名';
		String zdlx = map.get("zdlx");// '字段類型';
		String ssb = map.get("ssb");// '所屬表';
		String input_width = map.get("input_width");// '宽度';
		String input_postfix = map.get("input_postfix");// '后缀';
		String textarea_rows = map.get("textarea_rows");// '文本域行数';
		String source_table = map.get("source_table");// '下拉列表代码';
		String option_dm = map.get("option_dm");// '下拉列表代码';
		String option_mc = map.get("option_mc");// '下拉列表名称';
		String isnull = map.get("isnull");//允许为空
		String checked = map.get("checked");// '验证';
		String maxLength = "";// 最大长度
		if (!Base.isNull(checked) && checked.split("!!luojw!!").length > 0) {
			maxLength = checked.split("!!luojw!!")[0];
		}

		StringBuilder html = new StringBuilder();

		if ("lable".equalsIgnoreCase(zdlx)) {// 文字描述
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("onclick=\"createConfigureDiv('"+table_id+"','"+row_num+"','"+column_num+"')\"");
			html.append(">");
			if("no".equalsIgnoreCase(isnull)){
				html.append("<font color=\"red\">*</font> ");
			}
			html.append("<font color=\"blue\"> ");
			html.append(zdm);
			html.append("</font> ");
			html.append("</a>");
		} else if ("text".equalsIgnoreCase(zdlx)) {// 输入框
			html.append("<input type=\"text\" ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"text_" + zd + "\" ");
			// 宽度
			if (!Base.isNull(input_width)) {
				html.append("style=\"width:" + input_width + "px\" ");
			}
			// 最大长度
			if (!Base.isNull(maxLength)) {
				html.append("maxLength=\"" + maxLength + "\" ");
			}
			html.append("/>");
			// 后缀
			if (!Base.isNull(input_postfix)) {
				html.append(input_postfix);
			}
		} else if ("textarea".equalsIgnoreCase(zdlx)) {// 文本域
			html.append("<textarea ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"textarea_" + zd + "\" ");
			// 宽度
			if (!Base.isNull(input_width)) {
				html.append("style=\"width:" + input_width + "px\" ");
			}
			// 行数
			if (!Base.isNull(textarea_rows)) {
				html.append("rows=\"" + textarea_rows + "\" ");
			}
			html.append("/>");
		}else if ("calendar".equalsIgnoreCase(zdlx)) {// 日期控件
			
			html.append("<input type=\"text\" ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"calendar_" + zd + "\" ");
			html.append("onblur=\"dateFormatChg(this)\" ");
			html.append("style=\"cursor:hand;");
			// 宽度
			if (!Base.isNull(input_width)) {
				html.append("width:" + input_width + "px\"; ");
			}
			html.append("\" ");
			html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
			html.append("readOnly=\"true\"/> ");
			
		}else if ("select".equalsIgnoreCase(zdlx)) {// 下拉列表

			html.append("<select ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"select_" + zd + "\" ");
			// 宽度
			if (!Base.isNull(input_width)) {
				html.append("style=\"width:" + input_width + "px\" ");
			}
			html.append(">");
			html.append("<option value=\"\"></option>");

			if (Base.isNull(source_table)) {// 无数据源
				
				String[] dm = option_dm.split("!!luojw!!");
				String[] mc = option_mc.split("!!luojw!!");

				for (int i = 0; i < dm.length; i++) {
					html.append("<option value=\"" + dm[i] + "\">" + mc[i]
							+ "</option>");
				}
			} else {
				if (!Base.isNull(option_dm)) {

					List<HashMap<String, String>> optionList = dao
							.getListBySource(source_table, option_dm, option_mc);

					if (optionList != null && optionList.size() > 0) {

						for (int m = 0; m < optionList.size(); m++) {
							String dm = optionList.get(m).get("dm");
							String mc = optionList.get(m).get("mc");

							html.append("<option value=\"" + dm + "\">" + mc
									+ "</option>");
						}
					}
				}
			}

			html.append("</select>");
		}else if ("area".equalsIgnoreCase(zdlx)) {// 行政地区
			html.append("<input type=\"text\" ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"area_" + zd + "\" ");
			// 宽度
			if (!Base.isNull(input_width)) {
				html.append("style=\"width:" + input_width + "px\" ");
			}
			// 最大长度
			if (!Base.isNull(maxLength)) {
				html.append("readonly=\"readonly\" ");
			}
			html.append("/>");
			html.append("<button type=\"button\"  class=\"btn_01\" onclick=\"createAreaDiv('"+zd+"','sheng','','','');return false;\">");
			html.append("选择</button>");

			html.append("<input type=\"hidden\" ");
			html.append("name=\"str_" + zd + "\" ");
			html.append("id=\"area_dm_" + zd + "\" ");
			html.append("/>");
			
			html.append("<input type=\"hidden\" ");
			html.append("id=\"area_mc_" + zd + "\" ");
			html.append("/>");
		} else if ("stu_pic".equalsIgnoreCase(zdlx)) {// 学生照片
			html.append("<div id=\"stuImg\"> ");
			html.append("<img ");
			html.append("src=\"" + contextPath + "/stuPic.jsp\" ");
			html.append("width=\"96\" height=\"128\" /> ");
			html.append("</div> ");
			html.append("<br /> ");
			html.append("<button  type=\"button\" ");
			html.append("onclick=\"tipsWindown('系统提示','id:div_stuPic','380','130','true','','true','id');return false;\" ");
			html.append("style=\"width:100px\" class=\"btn_01\"> ");
			html.append("上传照片 ");
			html.append("</button> ");
		}

		return html.toString();
	}
		
	/**
	 * 创建国家地区DIV
	 * 
	 * @author 伟大的骆
	 */
	@SuppressWarnings("unchecked")
	public void createAreaDiv(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {
		
		// 国家地区列表
		List<HashMap<String, Object>> list = getAreaList(model);
		
		// 地区类型
		String area_lx = model.getArea_lx();
		// 地区级别
		String area_jb = model.getArea_jb();
		// 地区代码
		String area_dm = model.getArea_dm();
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:250px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tbody>");
		
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = list.get(i);
				String py = (String) map.get("py");
				List<HashMap<String, String>> pyList = (List<HashMap<String, String>>) map
						.get("pyList");
				if (pyList != null && pyList.size() > 0) {
					
					html.append("<tr>");
					
					html.append("<td width=\"10%\">");
					html.append("<font color=\"#C474A4\" size=\"3\">");
					html.append(py);
					html.append("</font>");	
					html.append("</td>");
					
					html.append("<td>");
					for(int j=0;j<pyList.size();j++){
						HashMap<String, String> areaMap = pyList.get(j);
						String dm = areaMap.get("dm");
						String mc = areaMap.get("mc");
						
						html.append("<a href=\"#\" ");
						html.append("onclick=\"createAreaDiv('"+area_lx+"','"+area_jb+"','"+dm+"','"+mc+"','next')\">");	
						html.append("<font color=\"blue\" size=\"2\" id=\"font_"+ dm + "\">");
						html.append(mc);
						html.append("</font>");	
						html.append("</a>");	
						
						html.append("&nbsp;");
						html.append("|");
						html.append("&nbsp;");
					}
					html.append("</td>");
					
					html.append("</tr>");
				}
			}
		}else{
			html.append("<tr>");
			html.append("<td>");
			html.append("<font color=\"blue\" size=\"2\">");
			html.append("无下属地区，请直接点击关闭，或返回后重新选择");
			html.append("</font>");	
			html.append("</td>");
			html.append("</tr>");
		}
	
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");	
		
		if(!"sheng".equalsIgnoreCase(area_jb)){
			html.append("<button type=\"button\"  onclick=\"createAreaDiv('"+area_lx+"','"+area_jb+"','"+area_dm+"','','precede');return false;\">返 回</button>");
		}
		
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		response.getWriter().print(html.toString());
	}
	
	//拼音首字母
	public static final String[] PY_BIG = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
	
	/**
	 * 获得国家地区列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, Object>> getAreaList(CustomFormModel model) {

		// 地区级别
		String area_jb = model.getArea_jb();
		// 地区代码
		String area_dm = model.getArea_dm();
		// 操作类型
		String lx = model.getLx();

		if ("next".equalsIgnoreCase(lx)) {// 下一级
			if ("sheng".equalsIgnoreCase(area_jb)) {
				area_jb = "shi";
			} else if ("shi".equalsIgnoreCase(area_jb)) {
				area_jb = "xian";
			}
		} else if ("precede".equalsIgnoreCase(lx)) { // 上一级
			if ("shi".equalsIgnoreCase(area_jb)) {
				area_jb = "sheng";
			} else if ("xian".equalsIgnoreCase(area_jb)) {
				area_jb = "shi";
			}
		}
		model.setArea_jb(area_jb);
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, String>> areaList = dao.getAreaList(area_dm, area_jb);

		if (areaList != null && areaList.size() > 0) {

			for (int i = 0; i < PY_BIG.length; i++) {

				String py = PY_BIG[i];
				List<HashMap<String, String>> pyList = new ArrayList<HashMap<String, String>>();

				for (int j = 0; j < areaList.size(); j++) {

					HashMap<String, String> map = areaList.get(j);

					String area_py = map.get("py");
					String dm = map.get("dm");
					String mc = map.get("mc");

					if (py.equalsIgnoreCase(area_py)) {

						HashMap<String, String> areaMap = new HashMap<String, String>();
						areaMap.put("dm", dm);
						areaMap.put("mc", mc);
						pyList.add(areaMap);

					}
				}

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("py", py);
				map.put("pyList", pyList);

				list.add(map);
			}
		}

		return list;
	}
	// ===============表单对象相关 end=====================
	
	// ===============字段配置相关 begin=====================
	/**
	 * 创建修改字段配置DIV
	 * 
	 * @author 伟大的骆
	 */
	public void createConfigureDiv(CustomFormModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		// 表ID
		String table_id = model.getTable_id();
		// 行
		String row_num = model.getRow_num();
		// 列
		String column_num = model.getColumn_num();
		// 配置内容
		HashMap<String, String> map = dao.getConfigureInfo(table_id, row_num,
				column_num);

		// 字段
		String zd = map.get("zd");
		// 字段名称
		String zdm = map.get("zdm");
		// 所属表
		String ssb = map.get("ssb");
		// 字段类型
		String zdlx = map.get("zdlx");
		// 可否修改
		String edit = map.get("edit");
		
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead onclick=\"\" style=\"\">");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<span>");
		html.append("配置内容");
		html.append("</span>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		
		// =========字段名begin==============
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("显示字段");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"zdm_" + ssb + "_" + zd + "\" ");
		html.append("value=\"" + zdm + "\" ");
		html.append("maxLength=\"20\" ");
		html.append(">");
		html.append("</td>");
		html.append("</tr>");
		// =========字段名 end==============
		
		// =========字段类型 begin==============
		html.append("<tr>");
		html.append("<th>");
		html.append("字段类型");
		html.append("</th>");
		html.append("<td>");
		//输入框
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"text\" ");
		if ("text".equalsIgnoreCase(zdlx)) {
			html.append("checked=\"checked\" ");
		} else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">输入框");
		//文本域
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"textarea\" ");
		if("textarea".equalsIgnoreCase(zdlx)){
			html.append("checked=\"checked\" ");
		}else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">文本域");
		
		//日期
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"calendar\" ");
		if("calendar".equalsIgnoreCase(zdlx)){
			html.append("checked=\"checked\" ");
		}else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">日期格式");
		//下拉列表
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"select\" ");
		if("select".equalsIgnoreCase(zdlx)){
			html.append("checked=\"checked\" ");
		}else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">下拉列表");
		
		html.append("<br/>");
		
		//国家地区
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"area\" ");
		if("area".equalsIgnoreCase(zdlx)){
			html.append("checked=\"checked\" ");
		}else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">国家地区");
		
		//学生照片
		html.append("<input type=\"radio\" ");
		html.append("name=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("onclick=\"clickZdlx(this.value)\" ");
		html.append("value=\"area\" ");
		if("stu_pic".equalsIgnoreCase(zdlx)){
			html.append("checked=\"checked\" ");
		}else if ("yes".equalsIgnoreCase(edit)) {
			html.append("disabled=\"disabled\" ");
		}
		html.append(">学生照片");
		
		//字段类型
		html.append("<input type=\"hidden\" ");
		html.append("id=\"zdlx_" + ssb + "_"+zd+"\" ");
		html.append("value=\"" + zdlx + "\" ");
		html.append(">");
		//可否修改
		html.append("<input type=\"hidden\" ");
		html.append("id=\"edit_" + ssb + "_"+zd+"\" ");
		html.append("value=\"" + edit + "\" ");
		html.append(">");
		
		html.append("</td>");
		html.append("</tr>");
		// =========字段类型 end==============
		html.append("</tbody>");
		html.append("</table>");

		// 配置具体内容
		createConfigureHtml(html, map);
		
		// =========按钮 begin==============
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"updateZd('" + zd + "','" + ssb + "');return false;\">保 存</button>");
		html.append("<button type=\"button\"  onclick=\"deleteZd('" + ssb + "');return false;\">删 除</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// =========按钮 end==============
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建配置对象HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createConfigureHtml(StringBuilder html,
			HashMap<String, String> map) {

		// 创建输入框HTML
		createTextHtml(html, map);
		// 创建文本域HTML
		createTextAreaHtml(html, map);
		// 创建下拉列表HTML
		createSelectHtml(html, map);
		// 创建日期控件HTML
		createCalendarHtml(html, map);
		// 创建国家地区HTML
		createAreaHtml(html, map);
	}
	
	/**
	 * 创建输入框HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createTextHtml(StringBuilder html,
			HashMap<String, String> map) {
		
		// 字段
		String zd = map.get("zd");
		// 字段
		String zdlx = map.get("zdlx");
		// 所属表
		String ssb = map.get("ssb");
		// 宽度
		String input_width = map.get("input_width");
		input_width = Base.isNull(input_width) ? "" : input_width;
		// 后缀
		String input_postfix = map.get("input_postfix");
		input_postfix = Base.isNull(input_postfix) ? "" : input_postfix;
		// 验证
		String checked = map.get("checked");
		// 最大长度
		String maxLength = "";
		if (checked != null && checked.split("!!luojw!!").length > 0) {
			maxLength = checked.split("!!luojw!!")[0];
			maxLength = "no".equalsIgnoreCase(maxLength) ? "" : maxLength;
		}
		
		// 唯一
		String onlyone = map.get("onlyone");
		//可否为空
		String isnull = map.get("isnull");
		
		String lx = "text";
		
		html.append("<table width=\"100%\" ");
		html.append("name=\"table_edit\" ");
		html.append("id=\"table_edit_" + lx + "\" ");
		html.append("border=\"0\" ");
		html.append("class=\"formlist\" ");
		if(!lx.equalsIgnoreCase(zdlx)){
			html.append("style=\"display:none\" ");
		}
		html.append(">");
		
		html.append("<tbody>");
		// 宽度
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("宽度");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_width_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"5\" ");
		html.append("value=\"" + input_width + "\" ");
		html.append("/>(px)");
		html.append("</td>");
		html.append("</tr>");
		// 后缀
		html.append("<tr>");
		html.append("<th>");
		html.append("后缀");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_postfix_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"10\" ");
		html.append("value=\"" + input_postfix + "\" ");
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		// 唯一
		html.append("<tr>");
		html.append("<th>");
		html.append("唯一");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"onlyone_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(onlyone)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"onlyone_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(onlyone)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		// 可否为空
		html.append("<tr>");
		html.append("<th>");
		html.append("可否为空");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		// 最大位数
		html.append("<tr>");
		html.append("<th>");
		html.append("最大位数");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_maxlength_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"10\" ");
		html.append("value=\"" + maxLength + "\" ");
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
	}
	
	/**
	 * 创建文本域HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createTextAreaHtml(StringBuilder html,
			HashMap<String, String> map) {
		
		// 字段
		String zd = map.get("zd");
		// 字段
		String zdlx = map.get("zdlx");
		// 所属表
		String ssb = map.get("ssb");
		// 宽度
		String input_width = map.get("input_width");
		input_width = Base.isNull(input_width) ? "" : input_width;
		// 后缀
		String textarea_rows = map.get("textarea_rows");
		textarea_rows = Base.isNull(textarea_rows) ? "" : textarea_rows;
		// 验证
		String checked = map.get("checked");
		// 最大长度
		String maxLength = "";
		if (checked != null && checked.split("!!luojw!!").length > 0) {
			maxLength = checked.split("!!luojw!!")[0];
			maxLength = "no".equalsIgnoreCase(maxLength) ? "" : maxLength;
		}
		//可否为空
		String isnull = map.get("isnull");
		
		String lx = "textarea";

		html.append("<table width=\"100%\" ");
		html.append("border=\"0\" ");
		html.append("class=\"formlist\" ");
		html.append("name=\"table_edit\" ");
		html.append("id=\"table_edit_" + lx + "\" ");
		if(!lx.equalsIgnoreCase(zdlx)){
			html.append("style=\"display:none\" ");
		}
		html.append(">");
		
		html.append("<tbody>");
		// 宽度
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("宽度");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_width_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"5\" ");
		html.append("value=\"" + input_width + "\" ");
		html.append("/>(px)");
		html.append("</td>");
		html.append("</tr>");
		// 所占行数
		html.append("<tr>");
		html.append("<th>");
		html.append("所占行数");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"textarea_rows_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"10\" ");
		html.append("value=\"" + textarea_rows + "\" ");
		html.append("/>(行)");
		html.append("</td>");
		html.append("</tr>");
		// 可否为空
		html.append("<tr>");
		html.append("<th>");
		html.append("可否为空");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		// 最大位数
		html.append("<tr>");
		html.append("<th>");
		html.append("最大位数");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_maxlength_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"10\" ");
		html.append("value=\"" + maxLength + "\" ");
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
	}
	
	/**
	 * 创建日期控件HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createCalendarHtml(StringBuilder html,
			HashMap<String, String> map) {
		
		// 字段
		String zd = map.get("zd");
		// 字段
		String zdlx = map.get("zdlx");
		// 所属表
		String ssb = map.get("ssb");
		// 宽度
		String input_width = map.get("input_width");
		input_width = Base.isNull(input_width) ? "" : input_width;
		//可否为空
		String isnull = map.get("isnull");
		
		String lx = "calendar";

		html.append("<table width=\"100%\" ");
		html.append("border=\"0\" ");
		html.append("class=\"formlist\" ");
		html.append("name=\"table_edit\" ");
		html.append("id=\"table_edit_" + lx + "\" ");
		if(!lx.equalsIgnoreCase(zdlx)){
			html.append("style=\"display:none\" ");
		}
		html.append(">");
		
		html.append("<tbody>");
		// 宽度
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("宽度");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_width_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"5\" ");
		html.append("value=\"" + input_width + "\" ");
		html.append("/>(px)");
		html.append("</td>");
		html.append("</tr>");
		// 可否为空
		html.append("<tr>");
		html.append("<th>");
		html.append("可否为空");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
	}
	
	/**
	 * 创建下拉列表HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createSelectHtml(StringBuilder html,
			HashMap<String, String> map) {
		
		// 字段
		String zd = map.get("zd");
		// 字段
		String zdlx = map.get("zdlx");
		// 所属表
		String ssb = map.get("ssb");
		// 宽度
		String input_width = map.get("input_width");
		input_width = Base.isNull(input_width) ? "" : input_width;
		// 数据表
		String source_table = map.get("source_table");
		source_table = Base.isNull(source_table) ? "" : source_table;
		// 代码字段
		String option_dm = map.get("option_dm");
		option_dm = Base.isNull(option_dm) ? "" : option_dm;
		// 名称字段
		String option_mc = map.get("option_mc");
		option_mc = Base.isNull(option_mc) ? "" : option_mc;
		//可否为空
		String isnull = map.get("isnull");
		
		String lx = "select";

		html.append("<table width=\"100%\" ");
		html.append("border=\"0\" ");
		html.append("class=\"formlist\" ");
		html.append("name=\"table_edit\" ");
		html.append("id=\"table_edit_" + lx + "\" ");
		if(!lx.equalsIgnoreCase(zdlx)){
			html.append("style=\"display:none\" ");
		}
		html.append(">");
		
		html.append("<tbody>");
		// 宽度
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("宽度");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_width_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"5\" ");
		html.append("value=\"" + input_width + "\" ");
		html.append("/>(px)");
		html.append("</td>");
		html.append("</tr>");
		//数据表
		html.append("<tr>");
		html.append("<th>");
		html.append("数据表");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"source_table_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"20\" ");
		html.append("value=\"" + source_table + "\" ");
		html.append("/>");
		html.append("(如无数据源，下面两字段请以<font color=\"red\">!!luojw!!</font>进行分割)");
		html.append("</td>");
		html.append("</tr>");
		//代码
		html.append("<tr>");
		html.append("<th>");
		html.append("代码字段");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"option_dm_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"20\" ");
		html.append("value=\"" + option_dm + "\" ");
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		//名称
		html.append("<tr>");
		html.append("<th>");
		html.append("名称字段");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"option_mc_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"20\" ");
		html.append("value=\"" + option_mc + "\" ");
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");	
		// 可否为空
		html.append("<tr>");
		html.append("<th>");
		html.append("可否为空");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
	}
	
	/**
	 * 创建国家地区HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private void createAreaHtml(StringBuilder html,
			HashMap<String, String> map) {
		
		// 字段
		String zd = map.get("zd");
		// 字段
		String zdlx = map.get("zdlx");
		// 所属表
		String ssb = map.get("ssb");
		// 宽度
		String input_width = map.get("input_width");
		input_width = Base.isNull(input_width) ? "" : input_width;
		//可否为空
		String isnull = map.get("isnull");
		
		String lx = "area";

		html.append("<table width=\"100%\" ");
		html.append("border=\"0\" ");
		html.append("class=\"formlist\" ");
		html.append("name=\"table_edit\" ");
		html.append("id=\"table_edit_" + lx + "\" ");
		if(!lx.equalsIgnoreCase(zdlx)){
			html.append("style=\"display:none\" ");
		}
		html.append(">");
		
		html.append("<tbody>");
		// 宽度
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("宽度");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" style=\"width:100px\" ");
		html.append("id=\"input_width_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("maxLength=\"5\" ");
		html.append("value=\"" + input_width + "\" ");
		html.append("/>(px)");
		html.append("</td>");
		html.append("</tr>");
		// 可否为空
		html.append("<tr>");
		html.append("<th>");
		html.append("可否为空");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"no\" ");
		if(!"yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>否");		
		html.append("<input type=\"radio\" ");
		html.append("name=\"isnull_" + ssb + "_" + zd + "_" + lx + "\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(isnull)){
			html.append("checked=\"checked\" ");
		}
		html.append("/>是");	
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
	}
	
	/**
	 * 修改字段
	 * 
	 * @author 伟大的骆
	 */
	public boolean updateZd(CustomFormModel model, User user) {

		// 表ID
		String table_id = model.getTable_id();
		
		ArrayList<String> row_num = new ArrayList<String>();
		ArrayList<String> column_num = new ArrayList<String>();
		
		ArrayList<String> zd = new ArrayList<String>();
		ArrayList<String> zdm = new ArrayList<String>();
		ArrayList<String> zdlx = new ArrayList<String>();
		ArrayList<String> ssb = new ArrayList<String>();
		
		ArrayList<String> input_width = new ArrayList<String>();
		ArrayList<String> textarea_rows = new ArrayList<String>();
		ArrayList<String> input_postfix = new ArrayList<String>();
		
		ArrayList<String> source_table = new ArrayList<String>();
		ArrayList<String> option_dm = new ArrayList<String>();
		ArrayList<String> option_mc = new ArrayList<String>();
		
		ArrayList<String> isnull = new ArrayList<String>();
		ArrayList<String> onlyone = new ArrayList<String>();
		ArrayList<String> edit = new ArrayList<String>();
		ArrayList<String> checked = new ArrayList<String>();

		for (int i = 0; i < 2; i++) {

			row_num.add(model.getRow_num());

			if (i == 0) {
				
				column_num.add(model.getColumn_num());
				zd.add("");
				zdm.add(Base.isNull(model.getZdm()) ? "" : model.getZdm());
				zdlx.add(Base.isNull(model.getZdlx()) ? "" : "lable");
				ssb.add("");
				input_width.add("");
				textarea_rows.add("");
				input_postfix.add("");
				source_table.add("");
				option_dm.add("");
				option_mc.add("");
				isnull.add("");
				onlyone.add("");
				edit.add("");
				checked.add("");
			} else {
				column_num.add(String.valueOf(Integer.parseInt(model
						.getColumn_num()) +1));
				zd.add(model.getZd());
				zdm.add("");
				zdlx.add(model.getZdlx());
				ssb.add(model.getSsb());
				input_width.add(model.getInput_width());
				textarea_rows.add(model.getTextarea_rows());
				input_postfix.add(model.getInput_postfix());
				source_table.add(model.getSource_table());
				option_dm.add(model.getOption_dm());
				option_mc.add(model.getOption_mc());
				isnull.add(model.getIsnull());
				onlyone.add(model.getOnlyone());
				edit.add(model.getEdit());
				checked.add(model.getChecked());
			}
		}

		boolean flag = false;

		try {
			flag = dao.updateCustomContent(table_id, row_num
					.toArray(new String[] {}), column_num
					.toArray(new String[] {}), zd.toArray(new String[] {}), zdm
					.toArray(new String[] {}), zdlx.toArray(new String[] {}),
					ssb.toArray(new String[] {}), input_width
							.toArray(new String[] {}), textarea_rows
							.toArray(new String[] {}), input_postfix
							.toArray(new String[] {}), source_table
							.toArray(new String[] {}), option_dm
							.toArray(new String[] {}), option_mc
							.toArray(new String[] {}), isnull
							.toArray(new String[] {}), onlyone
							.toArray(new String[] {}), edit
							.toArray(new String[] {}), checked
							.toArray(new String[] {}), user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	// ===============字段配置相关 end=====================
	
	// ===============其他方法 begin=====================
	/**
	 * 保存学生照片
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveStuPic(CustomFormForm myForm, User user) {

		PicDAO picDao = new PicDAO();

		// 学号
		String xh = myForm.getXh();
		// 文件
		FormFile file = myForm.getStuPic();

		try {
			picDao.savePic(file.getInputStream(), xh, "stu");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	// ===============其他方法 end=====================
}
