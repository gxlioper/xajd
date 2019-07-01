package xsgzgl.customForm.gnmk;

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
import xgxt.jygl.njjs.NjjsJyglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.table.CustomTableDAO;
import xsgzgl.customForm.table.CustomTableForm;
import xsgzgl.customForm.table.CustomTableService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.rcsw.qjgl.RcswQjglForm;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.grxx.XsxxGrxxForm;

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

public class CustomGnmkService extends CommService {

	CustomGnmkDAO dao = new CustomGnmkDAO();

	// ===================查询================================
	
	/**
	 * 获得结果集(自定义功能模块)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getGnmkManageList(CustomGnmkForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 查询内容列表
		List<HashMap<String, String>> searchContentList = model
				.getSearchContentList();
		// 查询列表
		List<HashMap<String, String>> list = dao.getGnmkManageList(model, user);

		ArrayList<String[]> reslutList = null;
		
		if (list != null && list.size() > 0) {

			int column_num = list.size() / searchContentList.size();

			for (int i = 0; i < column_num; i++) {

				String[] value = new String[searchContentList.size()];

				// 计数器
				int count = 0;

				for (int j = (i * searchContentList.size()); j < list.size(); j++) {

					HashMap<String, String> rsMap = list.get(j);
					// 字段值
					String zdz = rsMap.get("zdz");

					value[count] = zdz;
					count++;
				}
				
				reslutList.add(value);
			}
		}

		return reslutList;
	}
	
	/**
	 * 自定义功能管理Html
	 * 
	 * @author 伟大的骆
	 */
	public String getGnmkManageHtml(SearchRsModel rsModel,
			CustomGnmkForm model, ArrayList<String[]> rsArrList, User user) {

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
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	// ===================查询 end================================

	// ===================详细================================
	
	/**
	 * 获得功能模块详细Html
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createGnmkDetail(CustomGnmkForm model, 
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 功能模块代码
		String gnmkdm = model.getGnmkdm();
		// 功能模块ID
		String gnmk_id = getOneValue("xg_custom_gnmkb", "id", "gnmkdm", gnmkdm);

		CustomTableDAO tableDAO = new CustomTableDAO();
		
		List<HashMap<String, String>> tableList = tableDAO
				.getTableList(gnmk_id);
		List<HashMap<String, String>> tableContentList = tableDAO
				.getTableContentList(gnmk_id);
		
		StringBuilder html = new StringBuilder();
		
		//系统时间
		String nowTime = getNowTime("YYYY年MM月DD日");
		String nowTimeMc = getNowTime("YYYYMMDD");
		
		if(tableList!=null && tableList.size()>0){
			
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
				html.append("<table id=\"table_"+table_num+"\" class=\"formlist\" width=\"100%\">");
				html.append("<thead>");
				html.append("<tr>");
				html.append("<td colspan=\"4\">");
				html.append("<span>");
				html.append(title);	
				html.append("</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				
				// ===============内容================
				html.append("<tbody>");

				for (int j = 1; j <= Integer.parseInt(row); j++) {

					html.append("<tr>");

					if (tableContentList != null && tableContentList.size() > 0) {

						for (int k = 0; k < tableContentList.size(); k++) {

							HashMap<String, String> contentMap = tableContentList
									.get(k);

							// 内容ID
							String id = contentMap.get("id");
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
							String textarea_rows = contentMap
									.get("textarea_rows");
							// 后缀
							String postfix = contentMap.get("postfix");
							// 数据源表
							String source_table = contentMap
									.get("source_table");
							// 表代码
							String select_dm = contentMap.get("select_dm");
							// 表名称
							String select_mc = contentMap.get("select_mc");
							// 合并单元格
							String colspan = contentMap.get("colspan");
							// 验证
							String checked = contentMap.get("checked");

							StringBuilder pHtml = new StringBuilder();
							
							// 位置
							String position = column_num.replace("_", "_" + i
									+ "_" + j + "_");
							
							if("xh".equalsIgnoreCase(content)){					
								pHtml.append("<input type=\"text\" id=\"input_xh\" style=\"width:100px\"/>");
								pHtml.append("<button type=\"button\" class=\"btn_01\" onclick=\"showChooseDiv();return false;\">选择</button>");
							}else if("zgh".equalsIgnoreCase(content)){
								pHtml.append("<input type=\"text\" id=\"zgh\" style=\"width:80px\"/>");
								pHtml.append("<button type=\"button\"  onclick=\"alertInfo('点击后将可选取老师');return false;\" class=\"btn_01\">选择</button>");
							}else if("xm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xm\"></span>");
							}else if("xb".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xb\"></span>");
							}else if("nj".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_nj\"></span>");
							}else if("xydm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xydm\"></span>");
							}else if("zydm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_zydm\"></span>");
							}else if("bjdm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_bjdm\"></span>");
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
								} else {

									CustomTableService tableService = new CustomTableService();

									List<HashMap<String, String>> optionList = tableService
											.getListBySource(source_table,
													select_dm, select_mc, null);

									if (optionList != null
											&& optionList.size() > 0) {

										for (int m = 0; m < optionList.size(); m++) {
											String dm = optionList.get(m).get(
													"dm");
											String mc = optionList.get(m).get(
													"mc");

											pHtml.append("<option value=\""
													+ dm + "\">" + mc
													+ "</option>");
										}
									}
								}
								
								pHtml.append("</select>");
							}else if("nowTime".equalsIgnoreCase(content)){//系统时间
								String nowTime_id = "nowTime_"+position;
								pHtml.append(nowTime);
								pHtml.append("<input type=\"hidden\" id=\""+nowTime_id+"\" style=\"\" value=\""+nowTimeMc+"\"/>");
							}else if("calendar".equalsIgnoreCase(content)){//日期格式
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
									&& row_num.equalsIgnoreCase(String
											.valueOf(j))) {

								boolean noColspan = "colspan".equalsIgnoreCase(content) ? false : true;
								
								if("1".equalsIgnoreCase(column_num) && noColspan){
									//左一
									html.append("<th id=\"th_"+table_num+"_"+j+"_left\" style=\"width:20%;height:25px;\">");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</th>");
								}else if("2".equalsIgnoreCase(column_num) && noColspan){	
									String td_width = Base.isNull(colspan) ? "30%" : "80%";	
									// 左二
									html.append("<td id=\"td_"+table_num+"_"+j+"_left\" style=\"width:"+td_width+";height:25px;\" colspan=\""+colspan+"\">");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</td>");
								}else if("3".equalsIgnoreCase(column_num) && noColspan){
									// 右一
									html.append("<th id=\"th_"+table_num+"_"+j+"_right\" style=\"width:20%;height:25px;\" >");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</th>");
								}else if("4".equalsIgnoreCase(column_num) && noColspan){
									// 右二
									html.append("<td id=\"td_"+table_num+"_"+j+"_right\" style=\"width:30%;height:25px;\" >");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</td>");
								}
							}
						}
					}

					html.append("</tr>");

				}

				html.append("</tbody>");
				// ===============内容 end============
				
				html.append("</table>");
			}
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 获得学生信息
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void getXsInfo(CustomGnmkForm model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> xsList = null;

		try {
			xsList = dao.getXsInfoList(model, user);
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		StringBuilder html = new StringBuilder();

		// 表头
		html.append("<table class=\"formlist\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		html.append("<span>");
		html.append("学生信息");
		html.append("</span>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");

		// ===============内容================
		html.append("<tbody>");
		
		if (xsList != null && xsList.size() > 0) {
			
			html.append("<tr>");
			html.append("<td style=\"width:10%\">序号</td>");
			html.append("<td style=\"width:45%\">学号</td>");
			html.append("<td style=\"width:45%\">姓名</td>");
			html.append("</tr>");
			
			for (int i = 0; i < xsList.size(); i++) {

				HashMap<String, String> xsInfo = xsList.get(i);

				String num = xsInfo.get("num");// 序号
				String xh = xsInfo.get("xh");// 学号
				String xm = xsInfo.get("xm");// 姓名
				String xb = xsInfo.get("xb");// 性别
				String nj = xsInfo.get("nj");// 年级
				String xymc = xsInfo.get("xymc");// 学院
				String zymc = xsInfo.get("zymc");// 专业
				String bjmc = xsInfo.get("bjmc");// 班级

				StringBuilder title = new StringBuilder();
				title.append("性别：");
				title.append(xb);
				title.append("	年级：");
				title.append(nj);
				title.append("	"+Base.YXPZXY_KEY+"：");
				title.append(xymc);
				title.append("	专业：");
				title.append(zymc);
				title.append("	班级：");
				title.append(bjmc);
				
				html.append("<tr ");
				html.append("title=\"" + title + "\" ");
				html.append("onclick=\"rowOnClick(this);\" "); 
				html.append("ondblclick=\"chooseXs('"+xh+"')\" ");
				html.append("style=\"cursor:hand\"");
				html.append(">");
				
				html.append("<td>");
				html.append(num);
				html.append("</td>");

				html.append("<td>");
				html.append(xh);
				html.append("</td>");

				html.append("<td>");
				html.append(xm);
				html.append("</td>");

				html.append("</tr>");
			}
		}
		
		html.append("</tbody>");
		// ===============内容 end============

		// ===============按钮================
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		html.append("<div class=\"btn\">");
		
		Pages page = model.getPages();
		int maxPage = page.getMaxPage();
		int currentPage = page.getCurrentPage();
		
		html.append("<input type=\"hidden\" id=\"max_xs_num\" value=\""+maxPage+"\">");
		
		if (currentPage != 1) {
			html.append("<button type=\"button\"  onclick=\"prePage();return false;\">");
			html.append("上一页");
			html.append("</button>");
		}

		if (currentPage != maxPage) {
			html.append("<button type=\"button\"  onclick=\"nextPage();return false;\">");
			html.append("下一页");
			html.append("</button>");
		}
		
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ===============按钮 end============
		
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存功能模块
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public boolean saveGnmk(CustomGnmkForm model, User user) {

		// 初始化功能模块Model
		initGnmkModel(model, user);
		// 功能模块代码
		String gnmkdm = model.getGnmkdm();

		// 操作项目表
		String tableName = model.getXmb();
		// 主键
		String pk = "gnmkdm||pk";
		// 主键值
		String[] pkValue = new String[] { gnmkdm + model.getPk() };
		// 批量字段
		String[] arrzd = new String[] { "zd", "zdz" };
		// 单一字段
		String[] onezd = new String[] { "gnmkdm", "pk" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.getZdz().hashCode();
		
		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 初始化功能模块Model
	 * 
	 * @author 伟大的骆
	 * 
	 */
	private void initGnmkModel(CustomGnmkForm model, User user) {

		// 主键列表
		List<HashMap<String, String>> pkList = dao.getGnmkPkList(model, user);

		// 字段
		String[] zd = model.getZd();
		// 字段值
		String[] zdz = model.getZdz();

		if (zdz != null && zdz.length > 0) {
			List<String> valueList = new ArrayList<String>();
			for (int i = 0; i < zdz.length; i++) {
				valueList.add(unicode2Gbk(zdz[i]).trim());
			}
			model.setZdz(valueList.toArray(new String[] {}));
		}
		
		// 主键
		String pk = "";

		if (pkList != null && pkList.size() > 0) {
			for (int i = 0; i < pkList.size(); i++) {
				HashMap<String, String> map = pkList.get(i);
				// 主键ID
				String pk_id = map.get("pk_id");

				for (int j = 0; j < zd.length; j++) {

					if (pk_id.equalsIgnoreCase(zd[j])) {
						pk += zd[j] + zdz[j];
						;
					}
				}
			}
		}
		
		model.setPk(pk);
	}

	// ===================详细 end=============================
}