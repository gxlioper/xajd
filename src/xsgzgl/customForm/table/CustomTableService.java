package xsgzgl.customForm.table;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.model.TablePkContentModel;
import xsgzgl.customForm.model.TableSearchContentModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.rcsw.qjgl.RcswQjglForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class CustomTableService extends CommService {

	CustomTableDAO dao = new CustomTableDAO();

	/**
	 * 获得功能模块list
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getGnmkList() {
		return dao.getGnmkList();
	}
	
	/**
	 * 获得结果集(功能模块)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getCustomGnmkList(CustomTableForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// 功能模块代码
		String gnmkdm = model.getGnmkdm();
		model.setGnmkdm(gnmkdm.trim());

		// 功能模块名称
		String gnmkmc = unicode2Gbk(model.getGnmkmc());
		model.setGnmkmc(gnmkmc.trim());

		// 审核状态
		String shzt = unicode2Gbk(model.getShzt());
		model.setShzt(shzt.trim());

		return dao.getCustomGnmkList(model, user);
	}
	
	/**
	 * 获得表头文件(综合测评维护)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCustomTableTop(
			CustomTableForm model, User user) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		map = new HashMap<String, String>();
		map.put("en", "gnmkdm");
		map.put("cn", "功能模块代码");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "gnmkmc");
		map.put("cn", "功能模块名称");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "shzt");
		map.put("cn", "需要审核");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "time");
		map.put("cn", "创建时间");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "操作");
		topTr.add(map);
		
		return topTr;
	}
	
	/**
	 * 自定义功能模块Html
	 * 
	 * @author 伟大的骆
	 */
	public String getCustomGnmkHtml(SearchRsModel rsModel, CustomTableForm model,
			ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String id = rs[0];

				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 1; j < rs.length ; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\">");
				
//				spHtml.append("<a href=\"#\" onclick=\"showTableDetail('"+id+"');return false;\">");
//				spHtml.append("<font color=\"blue\">");
//				spHtml.append("查看");
//				spHtml.append("</font>");
//				spHtml.append("</a>");
//				
//				spHtml.append("&nbsp;&nbsp;");
				
				spHtml.append("<a href=\"#\" onclick=\"showTableUpdate('"+id+"');return false;\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("详细");
				spHtml.append("</font>");
				spHtml.append("</a>");
				
				spHtml.append("&nbsp;&nbsp;");
				
				spHtml.append("<a href=\"#\" onclick=\"showTableSetting('"+id+"');return false;\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("设置");
				spHtml.append("</font>");
				spHtml.append("</a>");
				
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}

	//===================查询================================
	
	/**
	 * 保存功能模块
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveGnmk(CustomTableForm model, User user,
			HttpServletRequest request) {

		String dyym = model.getDyym();
		
		if(Base.isNull(dyym)){
			StringBuilder path = new StringBuilder();
			path.append("customGnmk.do?method=customGnmkManage");
			path.append("&gnmkdm=");
			path.append(model.getGnmkdm());
			model.setDyym(path.toString());		
		}
			
		boolean flag = true;
		
		if (Base.isNull(dyym)) {
			
			flag = saveGnmkdmb(model, user, request);
			
			if (flag) {
				flag = saveYhqxb(model, user, request);
			}
		}

		if (flag) {
			flag = saveCustomGnmk(model, user, request);
		}

		return flag;
	}
	
	/**
	 * 保存自定义功能模块
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveCustomGnmk(CustomTableForm model, User user,
			HttpServletRequest request) {
		// 功能模块
		String tableName = "xg_custom_gnmkb";
		// 主键
		String pk = "id";
		// 主键值
		String[] pkValue = new String[] { model.getId() };
		// 单一字段
		String[] onezd = new String[] { "gnmkdm", "gnmkmc", "dyym", "shzt",
				"tablename" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存功能模块代码表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveGnmkdmb(CustomTableForm model, User user,
			HttpServletRequest request) {
		// 功能模块
		String tableName = "gnmkdmb";
		// 主键
		String pk = "gnmkdm";
		// 主键值
		String[] pkValue = new String[] { "luojw" };
		// 单一字段
		String[] onezd = new String[] { "gnmkdm", "gnmkmc", "dyym", "dxq" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存用户权限表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveYhqxb(CustomTableForm model, User user,
			HttpServletRequest request) {
		// 功能模块
		String tableName = "yhqxb";
		// 主键
		String pk = "yhm||gnmkdm";
		// 主键值
		String[] pkValue = new String[] { model.getYhm()+ model.getGnmkdm() };
		// 单一字段
		String[] onezd = new String[] { "gnmkdm", "yhm", "dxq" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获得功能模块信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getGnmkInfo(CustomTableForm model, User user) {

		String tableName = "xg_custom_gnmkb";
		String pk = "id";
		String pkValue = model.getId();

		String[] colList = new String[] { "id", "gnmkdm", "gnmkmc", "dyym",
				"shzt", "create_time" };

		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	// ===================详细================================
	
	/**
	 * 检测表和字段
	 * 
	 * @author 伟大的骆
	 */
	public Boolean checkTableName(String tablename, String dm, String mc,
			CustomTableForm model) {
		return dao.checkTableName(tablename, dm, mc, model);
	}

	/**
	 * 获得数据源list
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getListBySource(String tablename,
			String dm, String mc, CustomTableForm model) {
		return dao.getListBySource(tablename, dm, mc, model);
	}
	
	/**
	 * 保存表单
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveTable(CustomTableForm model, User user) {

		TableModel tableModel = model.getTableModel();

		// 自定义表单
		String tableName = "xg_custom_table";
		// 主键
		String pk = "id";
		// 主键值
		String[] pkValue = tableModel.getId();
		// 批量字段
		String[] arrzd = new String[] { "title", "row_all" };
		// 单一字段
		String[] onezd = new String[] { "gnmk_id" };
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		boolean flag = false;

		String[] title = tableModel.getTitle();
		
		if (title != null && title.length > 0) {
			
			String[] fin_title = new String[title.length];
			
			for (int i = 0; i < title.length; i++) {
				fin_title[i] = unicode2Gbk(title[i]);
			}
			
			tableModel.setTitle(fin_title);
		}
		
		try {
			
			flag = dao.deleteOldTable(model);
			
			if(flag){
				flag = saveInfoToDb(saveForm, tableModel, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获得表单Id list
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTableIdList() {
		return dao.getTableIdList();
	}
	
	/**
	 * 保存表单
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveTableContent(CustomTableForm model, User user) {

		TableContentModel tableContentModel = model.getTableContentModel();

		// 评奖项目审核表
		String tableName = "xg_custom_table_content";
		// 主键
		String pk = "table_id";
		// 主键值
		String[] pkValue = tableContentModel.getOld_id();
		// 批量字段
		String[] arrzd = new String[] { "table_id", "row_num", "column_num",
				"content", "width", "textarea_rows", "postfix", "source_table",
				"select_dm", "select_mc", "checked" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		boolean flag = false;

		String[] content = tableContentModel.getContent();
		String[] postfix = tableContentModel.getPostfix();
		String[] select_dm = tableContentModel.getSelect_dm();
		String[] select_mc = tableContentModel.getSelect_mc();

		if (content != null && content.length > 0) {

			String[] fin_content = new String[content.length];
			String[] fin_postfix = new String[content.length];
			String[] fin_select_dm = new String[content.length];
			String[] fin_select_mc = new String[content.length];

			for (int i = 0; i < content.length; i++) {
				fin_content[i] = unicode2Gbk(content[i]);
				fin_postfix[i] = unicode2Gbk(postfix[i]);
				fin_select_dm[i] = unicode2Gbk(select_dm[i]);
				fin_select_mc[i] = unicode2Gbk(select_mc[i]);
			}

			tableContentModel.setContent(fin_content);
			tableContentModel.setPostfix(fin_postfix);
			tableContentModel.setSelect_dm(fin_select_dm);
			tableContentModel.setSelect_mc(fin_select_mc);
		}

		try {
			flag = saveInfoToDb(saveForm, tableContentModel, user);
			
			if(flag){
				flag = dao.deleteOldTableContent(model);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建审核信息Html
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createTable(CustomTableForm model, String gnmk_id,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> tableList = dao.getTableList(gnmk_id);
		List<HashMap<String, String>> tableContentList = dao.getTableContentList(gnmk_id);
		
		StringBuilder html = new StringBuilder();
		
		if(tableList!=null && tableList.size()>0){
			
			html.append("<input type=\"hidden\" id=\"max_table_num\" value=\""+tableList.size()+"\">");
			
			for(int i=0;i<tableList.size();i++){
				
				HashMap<String, String> tableMap = tableList.get(i);
				
				//表格序号
				String table_num = String.valueOf(i);
				// 标题
				String title = tableMap.get("title");
				// 行数
				String row = tableMap.get("row_all");
				// 表单ID
				String table_id = tableMap.get("table_id");
				
				//表头
				html.append("<table id=\"table_"+table_num+"\" border=\"1\" bordercolor=\"#000000\" width=\"100%\">");
				html.append("<thead>");
				html.append("<tr>");
				html.append("<td colspan=\"4\" bgcolor=\"#CCFFCC\">");
				html.append("标题：<input type=\"text\" name=\"text_title\" id=\"text_title_"+table_num+"\" style=\"width:150px\" value=\""+title+"\"/>");		
				html.append("<input type=\"hidden\" name=\"text_id\" id=\"text_id_"+table_num+"\" style=\"width:150px\" value=\""+table_id+"\"/>");				
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				
				// ===============内容================
				html.append("<tr><td><div id=\"div_"+table_num+"\">");
				html.append("<input type=\"hidden\" name=\"text_row_all\" id=\"text_row_all_"+table_num+"\" value=\""+row+"\"/>");
				
				for (int j = 1; j <= Integer.parseInt(row); j++) {
					html.append("<div id=\"div_"+table_num+"_"+j+"\">");
					html.append("<table id=\"th_"+table_num+"_"+j+"\" border=\"1\" bordercolor=\"#000000\" width=\"100%\">");
					html.append("<tr>");
					
					if (tableContentList != null && tableContentList.size() > 0) {
						for (int k = 0; k < tableContentList.size(); k++) {
							HashMap<String, String> contentMap = tableContentList
									.get(k);
							
							// 表单ID
							String tableContent_id = contentMap.get("table_id");
							// 行数
							String row_num = contentMap.get("row_num");
							// 列数
							String column_num = contentMap.get("column_num");
							// 内容
							String content = contentMap.get("content");
							// 宽度
							String width = contentMap.get("width");
							// 文本域行数
							String textarea_rows = contentMap.get("textarea_rows");
							// 后缀
							String postfix = contentMap.get("postfix");
							// 数据源表
							String source_table = contentMap.get("source_table");
							// 表代码
							String select_dm = contentMap.get("select_dm");
							// 表名称
							String select_mc = contentMap.get("select_mc");
							// 合并单元格
							String colspan = contentMap.get("colspan");
							// 验证
							String checked = contentMap.get("checked");
							
							StringBuilder pHtml = new StringBuilder();
							
							//位置
							String position = column_num.replace("_", "_" + i + "_" + j + "_");
							
							if("xh".equalsIgnoreCase(content)){
								String xh_id = "xh_"+position;
								pHtml.append("<input type=\"text\" id=\""+xh_id+"\" style=\"width:80px\"/>");
								pHtml.append("<button type=\"button\"  onclick=\"alertInfo('点击后将可选取学生')\" class=\"btn_01\">选择</button>");	
							}else if("zgh".equalsIgnoreCase(content)){
								String zgh_id = "zgh_"+position;	
								pHtml.append("<input type=\"text\" id=\""+zgh_id+"\" style=\"width:80px\"/>");
								pHtml.append("<button type=\"button\"  onclick=\"alertInfo('点击后将可选取老师')\" class=\"btn_01\">选择</button>");
							}else if("xm".equalsIgnoreCase(content)){
								pHtml.append("学生姓名");
							}else if("xb".equalsIgnoreCase(content)){
								pHtml.append("学生性别");
							}else if("nj".equalsIgnoreCase(content)){
								pHtml.append("本人所在年级");
							}else if("xydm".equalsIgnoreCase(content)){
								pHtml.append("本人所在部门(院系)");
							}else if("zydm".equalsIgnoreCase(content)){
								pHtml.append("本人所在专业");
							}else if("bjdm".equalsIgnoreCase(content)){
								pHtml.append("本人所在班级");
							}else if("text".equalsIgnoreCase(content)){
								String input_id = "input_"+position;
								pHtml.append("<input type=\"text\" id=\""+input_id+"\" style=\"width:"+width+"\"/>");
								if(!Base.isNull(postfix)){
									pHtml.append(postfix);
								}
							}else if("select".equalsIgnoreCase(content)){
								
								String select_id = "select_"+position;
								
								pHtml.append("<select id=\""+select_id+"\" style=\"width:"+width+"px\">");
								pHtml.append("<option value=\"\"></option>");
								
								if(Base.isNull(source_table.trim())){
									String[] dm = select_dm.split("!!luojw!!");
									String[] mc = select_mc.split("!!luojw!!");
									
									for(int m=0;m<dm.length;m++){			
										pHtml.append("<option value=\""+dm[m]+"\">"+mc[m]+"</option>");
									}
								}else{
									
									List<HashMap<String, String>> optionList = getListBySource(source_table, select_dm, select_mc, model);
									
									if (optionList != null && optionList.size() > 0) {
										
										for(int m=0;m<optionList.size();m++){			
											String dm = optionList.get(m).get("dm");
											String mc = optionList.get(m).get("mc");
											
											pHtml.append("<option value=\""+dm+"\">"+mc+"</option>");
										}
									}
								}
								
								pHtml.append("</select>");
							}else if("nowTime".equalsIgnoreCase(content)){
								pHtml.append("系统当前时间<br/>格式：YYYY年MM月DD日");
							}else if("calendar".equalsIgnoreCase(content)){
								String time_id = "time_"+position;
								pHtml.append("<input type=\"text\" id=\""+time_id+"\" style=\"width:150px\"");
								pHtml.append(" onclick=\"return showCalendar(this.id,'ymmdd');\"/>");
							}else if("textarea".equalsIgnoreCase(content)){
								String textarea_id = "textarea_"+position;
								pHtml.append("<textarea type=\"text\" id=\""+textarea_id+"\" style=\"width:"+width+"\" rows=\""+textarea_rows+"\"></textarea>");
							}else{
								pHtml.append(content);
							}
							
							if (tableContent_id.equalsIgnoreCase(table_id)
									&& row_num.equalsIgnoreCase(String.valueOf(j))) {

								boolean noColspan = "colspan".equalsIgnoreCase(content) ? false : true;
								
								if("1".equalsIgnoreCase(column_num) && noColspan){
									//左一
									html.append("<th id=\"th_"+table_num+"_"+j+"_left\" style=\"width:25%;height:25px;cursor:hand\" onclick=\"clickTd(this)\">");
									html.append("<p id=\"p_th_"+table_num+"_"+j+"_left\" align=\"right\">&nbsp;"+pHtml+"</p>");
									/*------------------保存内容 begin -----------------*/
									html.append("<p id=\"p_th_nr_"+table_num+"_"+j+"_left\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_th_"+table_num+"_"+j+"_left\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_th_"+table_num+"_"+j+"_left\" value=\"1\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_th_"+table_num+"_"+j+"_left\" value=\""+content+"\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_th_"+table_num+"_"+j+"_left\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_th_"+table_num+"_"+j+"_left\" value=\""+postfix+"\"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_th_"+table_num+"_"+j+"_left\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_th_"+table_num+"_"+j+"_left\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_th_"+table_num+"_"+j+"_left\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_th_"+table_num+"_"+j+"_left\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_th_"+table_num+"_"+j+"_left\" value=\""+checked+"\"/>");//验证
									html.append("</p>");
									/*------------------保存内容 end-----------------*/
								}else if("2".equalsIgnoreCase(column_num) && noColspan){
									
									String td_width = Base.isNull(colspan) ? "25%" : "75%";
									
									// 左二
									html.append("<td id=\"td_"+table_num+"_"+j+"_left\" style=\"width:"+td_width+";height:25px;cursor:hand\" onclick=\"clickTd(this)\" colspan=\""+colspan+"\">");
									html.append("<p id=\"p_td_"+table_num+"_"+j+"_left\" align=\"left\">&nbsp;"+pHtml+"</p>");
									/*------------------保存内容 begin -----------------*/
									html.append("<p id=\"p_td_nr_"+table_num+"_"+j+"_left\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_td_"+table_num+"_"+j+"_left\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_td_"+table_num+"_"+j+"_left\" value=\"2\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_td_"+table_num+"_"+j+"_left\" value=\""+content+"\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_td_"+table_num+"_"+j+"_left\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_td_"+table_num+"_"+j+"_left\" value=\""+postfix+"\"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_td_"+table_num+"_"+j+"_left\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_td_"+table_num+"_"+j+"_left\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_td_"+table_num+"_"+j+"_left\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_td_"+table_num+"_"+j+"_left\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_td_"+table_num+"_"+j+"_left\" value=\""+checked+"\"/>");//验证
									html.append("</p>");
									/*------------------保存内容 end-----------------*/	
								}else if("3".equalsIgnoreCase(column_num) && noColspan){
									// 右一
									html.append("<th id=\"th_"+table_num+"_"+j+"_right\" style=\"width:25%;height:25px;cursor:hand\" onclick=\"clickTd(this)\">");
									html.append("<p id=\"p_th_"+table_num+"_"+j+"_right\" align=\"left\">&nbsp;"+pHtml+"</p>");
									/*------------------保存内容 begin -----------------*/
									html.append("<p id=\"p_th_nr_"+table_num+"_"+j+"_right\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_th_"+table_num+"_"+j+"_right\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_th_"+table_num+"_"+j+"_right\" value=\"3\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_th_"+table_num+"_"+j+"_right\" value=\""+content+"\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_th_"+table_num+"_"+j+"_right\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_th_"+table_num+"_"+j+"_right\" value=\""+postfix+"\"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_th_"+table_num+"_"+j+"_right\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_th_"+table_num+"_"+j+"_right\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_th_"+table_num+"_"+j+"_right\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_th_"+table_num+"_"+j+"_right\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_th_"+table_num+"_"+j+"_right\" value=\""+checked+"\"/>");//验证
									html.append("</p>");
									/*------------------保存内容 end-----------------*/	
								}else if("4".equalsIgnoreCase(column_num) && noColspan){
									// 右二
									html.append("<td id=\"td_"+table_num+"_"+j+"_right\" style=\"width:25%;height:25px;cursor:hand\" onclick=\"clickTd(this)\">");
									html.append("<p id=\"p_td_"+table_num+"_"+j+"_right\" align=\"left\">&nbsp;"+pHtml+"</p>");
									/*------------------保存内容 begin -----------------*/
									html.append("<p id=\"p_td_nr_"+table_num+"_"+j+"_right\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_td_"+table_num+"_"+j+"_right\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_td_"+table_num+"_"+j+"_right\" value=\"4\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_td_"+table_num+"_"+j+"_right\" value=\""+content+"\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_td_"+table_num+"_"+j+"_right\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_td_"+table_num+"_"+j+"_right\" value=\" \"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_td_"+table_num+"_"+j+"_right\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_td_"+table_num+"_"+j+"_right\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_td_"+table_num+"_"+j+"_right\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_td_"+table_num+"_"+j+"_right\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_td_"+table_num+"_"+j+"_right\" value=\""+checked+"\"/>");//验证
									html.append("</p>");
									/*------------------保存内容 end-----------------*/	
								}
								
								if(!noColspan){
									
									/*------------------合并单元格 begin -----------------*/
									html.append("<p id=\"p_th_nr_"+table_num+"_"+j+"_right\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_th_"+table_num+"_"+j+"_right\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_th_"+table_num+"_"+j+"_right\" value=\"3\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_th_"+table_num+"_"+j+"_right\" value=\"colspan\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_th_"+table_num+"_"+j+"_right\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_th_"+table_num+"_"+j+"_right\" value=\""+postfix+"\"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_th_"+table_num+"_"+j+"_right\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_th_"+table_num+"_"+j+"_right\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_th_"+table_num+"_"+j+"_right\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_th_"+table_num+"_"+j+"_right\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_th_"+table_num+"_"+j+"_right\" value=\""+checked+"\"/>");//验证
									html.append("</p>");

									html.append("<p id=\"p_td_nr_"+table_num+"_"+j+"_right\">");
									html.append("<input type=\"hidden\" name=\"nr_row_num\" id=\"nr_row_num_td_"+table_num+"_"+j+"_right\" value=\""+j+"\"/>");//行
									html.append("<input type=\"hidden\" name=\"nr_column_num\" id=\"nr_column_num_td_"+table_num+"_"+j+"_right\" value=\"4\"/>");//列
									html.append("<input type=\"hidden\" name=\"nr_content\" id=\"nr_content_td_"+table_num+"_"+j+"_right\" value=\"colspan\"/>");//内容
									html.append("<input type=\"hidden\" name=\"nr_width\" id=\"nr_width_td_"+table_num+"_"+j+"_right\" value=\""+width+"\"/>");//宽度
									html.append("<input type=\"hidden\" name=\"nr_postfix\" id=\"nr_postfix_td_"+table_num+"_"+j+"_right\" value=\" \"/>");//后缀
									html.append("<input type=\"hidden\" name=\"nr_textarea_rows\" id=\"nr_textarea_rows_td_"+table_num+"_"+j+"_right\" value=\""+textarea_rows+"\"/>");//文本域行数
									html.append("<input type=\"hidden\" name=\"nr_source_table\" id=\"nr_source_table_td_"+table_num+"_"+j+"_right\" value=\""+source_table+"\"/>");//数据源
									html.append("<input type=\"hidden\" name=\"nr_select_dm\" id=\"nr_select_dm_td_"+table_num+"_"+j+"_right\" value=\""+select_dm+"\"/>");//代码
									html.append("<input type=\"hidden\" name=\"nr_select_mc\" id=\"nr_select_mc_td_"+table_num+"_"+j+"_right\" value=\""+select_mc+"\"/>");//名称
									html.append("<input type=\"hidden\" name=\"nr_checked\" id=\"nr_checked_td_"+table_num+"_"+j+"_right\" value=\""+checked+"\"/>");//验证
									html.append("</p>");
									/*------------------保存内容 end-----------------*/	
								}
								
								if (("1".equalsIgnoreCase(column_num) || "3".equalsIgnoreCase(column_num)) && noColspan) {
									html.append("</th>");
								} else if (("2".equalsIgnoreCase(column_num) || "4".equalsIgnoreCase(column_num)) && noColspan) {
									html.append("</td>");
								}						
							}
						}
					}
					
					html.append("</tr>");
					html.append("</table>");
					html.append("</div>");
				}	
				
				html.append("</div></td></tr>");
				// =============内容 end===============
				
				html.append("</table>");
			}
		}
		

		response.getWriter().print(html.toString());
	}

	// ===================设置================================
	/**
	 * 获得功能模块内容 list
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getGnmkContentList(String gnmk_id) {
		return dao.getGnmkContentList(gnmk_id);
	}

	// =============查询显示列设置 end==================
	/**
	 * 显示字段设置Div
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void showZdxzDiv(CustomTableForm model,HttpServletResponse response)
			throws IOException {
		
		response.setContentType("text/html;charset=gbk");
		
		// 功能模块ID
		String gnmk_id = model.getId();
		// 所属类型
		List<HashMap<String, String>> sslxList = getWhList("xg_custom_table",
				"id", "title", "", "gnmk_id", gnmk_id, false);
		// 字段设置
		List<HashMap<String, String>> zdList = dao.getGnmkContentList(gnmk_id);
		
		StringBuilder html = new StringBuilder();
		
		for (int i = 0; i < sslxList.size(); i++) {

			HashMap<String, String> map = sslxList.get(i);
			// 表头
			String title = map.get("mc");
			// 表ID
			String table_id = map.get("dm");

			List<HashMap<String, String>> zdInfoList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			zdInfo.put("zd", "all_" + i);
			zdInfo.put("zdm", "全选");

			zdInfoList.add(zdInfo);

			for (int j = 0; j < zdList.size(); j++) {

				zdInfo = zdList.get(j);

				if (table_id.equalsIgnoreCase(zdInfo.get("table_id"))) {
					zdInfoList.add(zdInfo);
				}
			}
			
			int space = 5 - zdInfoList.size() % 5;
			if (space != 0) {
				for (int j = 0; j < space; j++) {
					zdInfo = new HashMap<String, String>();
					zdInfo.put("zd", "");
					zdInfo.put("zdm", "");

					zdInfoList.add(zdInfo);
				}
			}

			// 计数器
			int num = 0;
			boolean br = true;
			
			html.append("<table class=\"formlist\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<th colspan=\"5\">");
			html.append("<span>" + title + "</span>");
			html.append("</th>");
			html.append("</tr>");
			html.append("</thead>");
			
			html.append("<tbody>");
			
			for (int j = 0; j < zdInfoList.size(); j++) {

				zdInfo = zdInfoList.get(j);

				// 字段
				String zd = zdInfo.get("zd");
				// 字段名
				String zdm = zdInfo.get("zdm");
				// 显示顺序
				String xssx = zdInfo.get("xssx");
				
				String checked = (Base.isNull(xssx))?"":"checked=\"true\"";
				
				if (br) {
					html.append("<tr>");
					br = false;
				}
				
				html.append("<td style=\"width:15%\">");
				
				if (("all_" + i).equalsIgnoreCase(zd)) {
					html.append("<input type=\"checkbox\" id=\"cb_all_" + i
							+ "\" value=\"all\" onclick=\"checkAll('" + i
							+ "')\"/>");
				} else if (!Base.isNull(zdm.trim())) {
					html.append("<input type=\"checkbox\" title=\"" + zdm
							+ "\" name=\"cb_" + i + "\" value=\"" + zd + "\" "
							+ checked + "/>");
				}
				html.append(zdm);
				html.append("</td>");
				
				num++;

				if (num >= 5) {
					br = true;
					num=0;
				}
				
				if (br) {
					html.append("</tr>");	
				}
			}
			
			html.append("</tbody>");
			html.append("</table>");
		
		}
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存功能模块查询内容
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveTableSearchContent(CustomTableForm model, User user) {

		// TableSearchContentModel tableSearchContentModel = model
		// .getTableSearchContentModel();
		//
		// // 评奖项目审核表
		// String tableName = "xg_custom_search_content";
		// // 主键
		// String pk = "gnmk_id";
		// // 主键值
		// String[] pkValue = new String[] {
		// tableSearchContentModel.getGnmk_id() };
		// // 单一字段
		// String[] onezd = new String[] { "gnmk_id" };
		// // 批量字段
		// String[] arrzd = new String[] { "content_id", "xssx" };
		//
		// SaveForm saveForm = new SaveForm();
		// saveForm.setTableName(tableName);
		// saveForm.setPk(pk);
		// saveForm.setPkValue(pkValue);
		// saveForm.setArrzd(arrzd);
		// saveForm.setOnezd(onezd);
		//
		// boolean flag = false;
		//
		// try {
		// flag = saveInfoToDb(saveForm, tableSearchContentModel, user);
		// } catch (Exception e) {
		// // TODO 自动生成 catch 块
		// e.printStackTrace();
		// }

		boolean flag = false;

		try {
			// 删除查询显示列设置
			flag = dao.deleteTableSearchContent(model, user);

			// 保存查询显示列设置
			if (flag) {
				flag = dao.saveTableSearchContent(model, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	// =============查询显示列设置 =====================

	// =============主键设置=========================
	/**
	 * 显示主键确认Div
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void showZjqrDiv(CustomTableForm model,HttpServletResponse response)
			throws IOException {
		
		response.setContentType("text/html;charset=gbk");
		
		// 功能模块ID
		String gnmk_id = model.getId();
		// 所属类型
		List<HashMap<String, String>> sslxList = getWhList("xg_custom_table",
				"id", "title", "", "gnmk_id", gnmk_id, false);
		// 字段设置
		List<HashMap<String, String>> zdList = dao.getPkContentList(gnmk_id);
		
		StringBuilder html = new StringBuilder();
		
		for (int i = 0; i < sslxList.size(); i++) {

			HashMap<String, String> map = sslxList.get(i);
			// 表头
			String title = map.get("mc");
			// 表ID
			String table_id = map.get("dm");

			List<HashMap<String, String>> zdInfoList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			zdInfo.put("zd", "all_" + i);
			zdInfo.put("zdm", "全选");

			zdInfoList.add(zdInfo);

			for (int j = 0; j < zdList.size(); j++) {

				zdInfo = zdList.get(j);

				if (table_id.equalsIgnoreCase(zdInfo.get("table_id"))) {
					zdInfoList.add(zdInfo);
				}
			}
			
			int space = 5 - zdInfoList.size() % 5;
			if (space != 0) {
				for (int j = 0; j < space; j++) {
					zdInfo = new HashMap<String, String>();
					zdInfo.put("zd", "");
					zdInfo.put("zdm", "");

					zdInfoList.add(zdInfo);
				}
			}

			// 计数器
			int num = 0;
			boolean br = true;
			
			html.append("<table class=\"formlist\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<th colspan=\"5\">");
			html.append("<span>" + title + "</span>");
			html.append("</th>");
			html.append("</tr>");
			html.append("</thead>");
			
			html.append("<tbody>");
			
			for (int j = 0; j < zdInfoList.size(); j++) {

				zdInfo = zdInfoList.get(j);

				// 字段
				String zd = zdInfo.get("zd");
				// 字段名
				String zdm = zdInfo.get("zdm");
				// 显示顺序
				String xssx = zdInfo.get("xssx");
				
				String checked = (Base.isNull(xssx))?"":"checked=\"true\"";
				
				if (br) {
					html.append("<tr>");
					br = false;
				}
				
				html.append("<td style=\"width:15%\">");
				
				if (("all_" + i).equalsIgnoreCase(zd)) {
					html.append("<input type=\"checkbox\" id=\"cb_all_" + i
							+ "\" value=\"all\" onclick=\"checkAll('" + i
							+ "')\"/>");
				} else if (!Base.isNull(zdm.trim())) {
					html.append("<input type=\"checkbox\" title=\"" + zdm
							+ "\" name=\"cb_" + i + "\" value=\"" + zd + "\" "
							+ checked + "/>");
				}
				html.append(zdm);
				html.append("</td>");
				
				num++;

				if (num >= 5) {
					br = true;
					num=0;
				}
				
				if (br) {
					html.append("</tr>");	
				}
			}
			
			html.append("</tbody>");
			html.append("</table>");
		
		}
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存功能模块主键
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveTablePkContent(CustomTableForm model, User user) {

//		TablePkContentModel tablePkContentModel = model
//				.getTablePkContentModel();
//
//		// 评奖项目审核表
//		String tableName = "xg_custom_pk_content";
//		// 主键
//		String pk = "gnmk_id";
//		// 主键值
//		String[] pkValue = new String[] { tablePkContentModel.getGnmk_id() };
//		// 单一字段
//		String[] onezd = new String[] { "gnmk_id" };
//		// 批量字段
//		String[] arrzd = new String[] { "pk_id", "xssx" };
//
//		SaveForm saveForm = new SaveForm();
//		saveForm.setTableName(tableName);
//		saveForm.setPk(pk);
//		saveForm.setPkValue(pkValue);
//		saveForm.setArrzd(arrzd);
//		saveForm.setOnezd(onezd);
//
//		boolean flag = false;
//
//		try {
//			flag = saveInfoToDb(saveForm, tablePkContentModel, user);
//		} catch (Exception e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}

		boolean flag = false;

		try {
			// 删除主键设置
			flag = dao.deletePkSearchContent(model, user);

			// 保存主键设置
			if (flag) {
				flag = dao.saveTablePkContent(model, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	// =============主键设置 end=====================
}