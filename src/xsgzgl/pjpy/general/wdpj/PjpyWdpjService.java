package xsgzgl.pjpy.general.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyWdpjService extends CommService implements PjpyWdpjInterface {

	PjpyWdpjDAO dao = new PjpyWdpjDAO();

	/**
	 * 获取评奖项目性质列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmxzList() {

		List<HashMap<String, String>> list = getWhList("xg_pjpy_xmxzb", "xzdm",
				"xzmc", "", "", "", false);

		return list;
	}

	/**
	 * 获得表头文件(评奖评优_我的评奖)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjpyWdpjTop(PjpyWdpjModel model,
			User user) {

		// 用户类型
		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {// 学生用户
			return getTopByStu();
		} else {// 非学生用户
			return getTopByTea();
		}
	}

	/**
	 * 获得表头文件【学生】
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getTopByStu() {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xmmc", "sqsj", "shzt", "cz" };
		String[] cn = new String[] { "已申请项目", "申请时间", "目前审核状态", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得表头文件【非学生】
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getTopByTea() {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xmmc", "xmlx","ksbrs", "xshrs", "yshrs", "cz" };
		String[] cn = new String[] { "项目名称", "项目类型","可申报人数", "需审核人数", "已审核人数", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	public ArrayList<String[]> getPjpyWdpjList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 用户类型
		String userType = user.getUserType();
		ArrayList<String[]> wdpjList = new ArrayList<String[]>();
		if ("stu".equalsIgnoreCase(userType)) {// 学生用户
			
			wdpjList=dao.getWdpjByStu(myForm, user);
			
		} else {
			
			wdpjList=dao.getWdpjByTea(myForm, user);
			
		}
		
		return wdpjList;
	}

	/**
	 * 构建结果集(评奖评优_我的评奖)
	 * 
	 * @author 伟大的骆
	 */
	public String createPjpyWdpjHTML(SearchRsModel rsModel,
			PjpyGeneralForm myForm, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user) {

		// 用户类型
		String userType = user.getUserType();
		String html = "";
		if ("stu".equalsIgnoreCase(userType)) {// 学生用户
			html = createStuHTML(rsModel, model, rsArrList, user);
		} else {
			html = createTeaHTML(rsModel, model, rsArrList, user);
		}
		return html;
	}

	/**
	 * 构建结果集(评奖评优_我的评奖【学生】)
	 * 
	 * @author 伟大的骆
	 */
	public String createStuHTML(SearchRsModel rsModel, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user) {
		String userType = user.getUserType();

		// IE版本
		String ie = rsModel.getIe();

		String stylePath = rsModel.getStylePath();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xmdm=rs[rs.length-1];

				html.append("<tr onclick=\"rowOnClick(this);\" >");
				
				for (int j=0;j<rs.length-1;j++){
				// --------------------构建HTML扩展字段与分数除外------------------------

				// -----------------------项目名称 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[j]);
				html.append("</td>");
				// -----------------------项目名称 end---------------------------

				// -----------------------当前审核状态 end---------------------------

				// -----------------------当前审核状态
				// begin---------------------------
				if(j==2){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(" <a href=\"#\" onclick=\"showWdpjView('"+xmdm+"');return false;\" ><font color=\"blue\">查看详细信息</font></a>");
					html.append("</td>");
					}
				// -----------------------当前审核状态 end---------------------------
				}
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 构建结果集(评奖评优_我的评奖)【非学生】
	 * 
	 * @author 伟大的骆
	 */
	public String createTeaHTML(SearchRsModel rsModel, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user) {
		String userType = user.getUserType();

		// IE版本
		String ie = rsModel.getIe();

		String stylePath = rsModel.getStylePath();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				// --------------------构建HTML扩展字段与分数除外------------------------

				// -----------------------项目名称 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:33%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[0]);
				html.append("</td>");
				// -----------------------项目名称 end---------------------------

				// -----------------------项目类型 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[1]);
				html.append("</td>");
				// -----------------------项目类型 end---------------------------
				
				// -----------------------可申报人数 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[4]);
				html.append("</td>");
				// -----------------------可申报人数 end---------------------------

				// -----------------------需审核人数 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[2]);
				html.append("</td>");
				// -----------------------需审核人数 end---------------------------
				
				// -----------------------审核通过人数 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[3]);
				html.append("</td>");
				// -----------------------审核通过人数 end---------------------------
				
				// -----------------------操作begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(" <a href=\"#\" onclick=\"if(checkItsDis(this)){showSpgw('"+rs[5]+"');};return false;\" class=\"a_lssh\" disabled=\"true\"><font color=\"blue\">审核</font></a>");

				html.append("</td>");
				// -----------------------操作end---------------------------

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 显示学生申请DIV
	 * 
	 * @author qlj
	 * @throws IOException 
	 * 
	 * @throws IOException
	 */
	public void showWdpjView(SearchRsModel rsModel,String xmdm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		WdpjXssqDAO xssqDAO = new WdpjXssqDAO();
		
		String xh=user.getUserName();
		
		String stylePath = rsModel.getStylePath();
		
		HashMap<String,String>xssqInfo=xssqDAO.getXssqMap(xmdm, xh);
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		List<HashMap<String,String>>xssqDetail=dao.getWdpjDetailByStu(xmdm, xh);
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"width:100%;height:330px;overflow-x:hidden;overflow-y:auto;\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr align=center>");
		html.append("<th colspan=\"4\" >");
		html.append("<span>项目申请信息 </span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("项目名称");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' colspan=\"3\">");
		// 项目说明
		html.append(pjxmModel.getXmmc());
		
		html.append("</td>");
		html.append("</tr>");
		
		if(xssqDetail!=null && xssqDetail.size()>0){
			
			for(int i=0;i<xssqDetail.size();i++){
				
				HashMap<String,String>xssqMap=xssqDetail.get(i);

				html.append("<tr align=center>");
				html.append("<td colspan=\"4\" >");
				html.append(xssqMap.get("mc"));
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<th width=\"25%\">");
				html.append("审核状态");
				html.append("</th>");
				html.append("<td colspan=\"3\" align=\"left\" nowrap=\"nowrap\" >");
				
				if("wsh".equalsIgnoreCase(xssqMap.get("shzt"))){
						
					html.append("<p><img src=\""+stylePath+"images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					
				}else if("tg".equalsIgnoreCase(xssqMap.get("shzt"))){
				
					html.append("<p><img src=\""+stylePath+"images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					
				}else if("btg".equalsIgnoreCase(xssqMap.get("shzt"))){
					
					html.append("<p><img src=\""+stylePath+"images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					
				}else if("th".equalsIgnoreCase(xssqMap.get("shzt"))){
					
					html.append("<p><img src=\""+stylePath+"images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
				
				}else if("cx".equalsIgnoreCase(xssqMap.get("shzt"))){
					
					html.append("<p><img src=\""+stylePath+"images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
				
				}
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<th width=\"25%\">");
				html.append("审核人");
				html.append("</th>");
				html.append("<td width=\"25%\">");
				html.append(!Base.isNull(xssqMap.get("shrxm"))?xssqMap.get("shrxm"):"");
				html.append("</td>");
				html.append("<th width=\"25%\">");
				html.append("审核时间");
				html.append("</th>");
				html.append("<td width=\"25%\">");
				html.append(!Base.isNull(xssqMap.get("shsj"))?xssqMap.get("shsj"):"");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<th width=\"25%\">");
				html.append("审核意见");
				html.append("</th>");
				html.append("<td colspan=\"3\">");
				html.append(!Base.isNull(xssqMap.get("shyj"))?xssqMap.get("shyj"):"");
				html.append("</td>");
				html.append("</tr>");
			
			}
		}
		html.append("<tr align=center>");
		html.append("<th width=\"25%\">");
		html.append("项目说明");
		html.append("</th>");
		html.append("<td colspan=\"3\" style=\"word-break:break-all;\" >");
		html.append(!Base.isNull(pjxmModel.getXmsm())?pjxmModel.getXmsm():"");
		html.append("</td>");
		html.append("</tr>");
	
		html.append("<tr align=center>");
		html.append("<th width=\"25%\">");
		html.append("申请理由");
		html.append("</th>");
		html.append("<td colspan=\"3\" style=\"word-break:break-all;\" >");
		html.append(!Base.isNull(xssqInfo.get("sqly"))?xssqInfo.get("sqly"):"");
		html.append("</td>");
		html.append("</tr>");
		
		
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"4\">");
		html.append("<div class=\"btn\">");


		html.append("<button  type=\"button\" id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}
	

	/**
	 * 显示学生申请DIV
	 * 
	 * @author qlj
	 * @throws IOException 
	 * 
	 * @throws IOException
	 */
	public void showLnzcInfo(SearchRsModel rsModel,String pkValue, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		PjpyZhcpService zhcpService=new PjpyZhcpService();
		
		String[]pkArr=pkValue.split("!!@@!!");
	
		List<HashMap<String,Object>>zcList=zhcpService.getZclnInfo(pkArr[3], pkArr[0],pkArr[1],pkArr[2]);
		
	
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"width:100%;height:330px;overflow-x:hidden;overflow-y:auto;\">");

		html.append("<table id=\"table_rs\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr align=center>");
		html.append("<th colspan=\"4\" >");
		html.append("<span>项目申请信息 </span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		
		if(zcList!=null && zcList.size()>0){
			
			for(int i=0;i<zcList.size();i++){
				
				HashMap<String,Object>zcMap=zcList.get(i);
				
				HashMap<String,String>left=(HashMap<String,String>)zcMap.get("left");

				HashMap<String,String>right=(HashMap<String,String>)zcMap.get("right");
				html.append("<tr align=center>");
				html.append("<th width=\"25%\">");
				html.append(Base.isNull(left.get("cn"))?"":left.get("cn"));
				html.append("</th>");
				html.append("<td width=\"25%\">");
				html.append(Base.isNull(left.get("zcinfo"))?"":left.get("zcinfo"));
				html.append("</td>");
				html.append("<th width=\"25%\">");
				html.append(Base.isNull(right.get("cn"))?"":right.get("cn"));
				html.append("</th>");
				html.append("<td width=\"25%\">");
				html.append(Base.isNull(right.get("zcinfo"))?"":right.get("zcinfo"));
				html.append("</td>");
				html.append("</tr>");
			
			}
		}
		
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"4\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	public void showWdpjLssb(String xmdm,User user, String xh, HttpServletResponse response) throws IOException {
		// TODO 自动生成方法存根
		response.setContentType("text/html;charset=gbk");
		
		WdpjXssqDAO xssqDAO = new WdpjXssqDAO();
		
		HashMap<String,String>xssqInfo=xssqDAO.getXssqMap(xmdm, xh);
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		List<HashMap<String,String>>xssqDetail=dao.getWdpjDetailByStu(xmdm, xh);
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"width:100%;height:375px;overflow-x:hidden;overflow-y:auto;\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr align=center>");
		html.append("<th colspan=\"4\" >");
		html.append("<span>项目申请信息 </span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("项目名称");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' colspan=\"3\">");
		// 项目说明
		html.append(pjxmModel.getXmmc());
		
		html.append("</td>");
		html.append("</tr>");
		
		if(xssqDetail!=null && xssqDetail.size()>0){
			
			for(int i=0;i<xssqDetail.size();i++){
				
				HashMap<String,String>xssqMap=xssqDetail.get(i);

				html.append("<tr align=center>");
				html.append("<td colspan=\"4\" >");
				html.append(xssqMap.get("mc"));
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<td width=\"25%\">");
				html.append("审核状态");
				html.append("</td>");
				html.append("<td colspan=\"3\">");
				html.append(!Base.isNull(xssqMap.get("shzt"))?xssqMap.get("shzt"):"");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<td width=\"25%\">");
				html.append("审核人");
				html.append("</td>");
				html.append("<td width=\"25%\">");
				html.append(!Base.isNull(xssqMap.get("shrxm"))?xssqMap.get("shrxm"):"");
				html.append("</td>");
				html.append("<td width=\"25%\">");
				html.append("审核时间");
				html.append("</td>");
				html.append("<td width=\"25%\">");
				html.append(!Base.isNull(xssqMap.get("shsj"))?xssqMap.get("shsj"):"");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr align=center>");
				html.append("<td width=\"25%\">");
				html.append("审核意见");
				html.append("</td>");
				html.append("<td colspan=\"3\">");
				html.append(!Base.isNull(xssqMap.get("shyj"))?xssqMap.get("shyj"):"");
				html.append("</td>");
				html.append("</tr>");
			
			}
		}
		html.append("<tr align=center>");
		html.append("<td width=\"25%\">");
		html.append("项目说明");
		html.append("</td>");
		html.append("<td colspan=\"3\" style=\"word-break:break-all;\" >");
		html.append(!Base.isNull(pjxmModel.getXmsm())?pjxmModel.getXmsm():"");
		html.append("</td>");
		html.append("</tr>");
	
		html.append("<tr align=center>");
		html.append("<td width=\"25%\">");
		html.append("项目说明");
		html.append("</td>");
		html.append("<td colspan=\"3\" style=\"word-break:break-all;\" >");
		html.append(!Base.isNull(xssqInfo.get("sqly"))?xssqInfo.get("sqly"):"");
		html.append("</td>");
		html.append("</tr>");
		
		
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"4\">");
		html.append("<div class=\"btn\">");


		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 创建我的审核Div
	 * 
	 * @date 2012.02.04
	 * @author 伟大的骆
	 */
	public void createWdshDiv(PjpyWdpjModel model, User user,
			HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		
	}
}
