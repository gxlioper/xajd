package xsgzgl.pjpy.bjlhdx.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_北京联合大学_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyWdpjService extends xsgzgl.pjpy.general.wdpj.PjpyWdpjService {

	PjpyWdpjDAO dao = new PjpyWdpjDAO();

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

		String[] en = new String[] { "xmmc", "xmlx", "xshrs", "yshrs", "cz" };
		String[] cn = new String[] { "项目名称", "项目类型", "需审核人数", "已审核人数", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(评奖评优_我的评奖)
	 * 
	 * @date 2013.02.04
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getPjpyWdpjList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 用户类型
		String userType = user.getUserType();
		ArrayList<String[]> wdpjList = new ArrayList<String[]>();
		if ("stu".equalsIgnoreCase(userType)) {// 学生用户
			wdpjList = dao.getWdpjByStu(myForm, user);
		} else {
			//wdpjList = dao.getWdpjByTea(myForm, user);
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
			// 项目信息列表
			List<HashMap<String, String>> xmxxList = getXmxxList(myForm, user);
			// 审核岗位列表
			List<HashMap<String, String>> shgwList = dao.getShgwxxList(
					xmxxList, user);
			// 需审核信息列表
			List<HashMap<String, String>> xshxxList = dao.getXshxxList(
					xmxxList, user);
			// 已审核信息列表
			List<HashMap<String, String>> yshxxList = dao.getYshxxList(
					xmxxList, user);

			if (xmxxList != null && xmxxList.size() > 0) {
				for (int i = 0; i < xmxxList.size(); i++) {
					String[] value = new String[] {};
					rsArrList.add(value);
				}
			}
			
			html = createTeaHTML(rsModel, model, xmxxList, shgwList, xshxxList,
					yshxxList, user);
		}
		return html;
	}

	/**
	 * 获取项目信息列表
	 * 
	 * @date 2012.02.04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmxxList(PjpyGeneralForm myForm,
			User user) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		String zgh = user.getUserName();

		return dao.getXmxxList(myForm, pjxn, pjxq, pjnd, zgh);
	}

	/**
	 * 构建结果集(评奖评优_我的评奖)【非学生】
	 * 
	 * @date 2012.02.04
	 * @author 伟大的骆
	 */
	public String createTeaHTML(SearchRsModel rsModel, PjpyWdpjModel model,
			List<HashMap<String, String>> xmxxList,
			List<HashMap<String, String>> shgwList,
			List<HashMap<String, String>> xshxxList,
			List<HashMap<String, String>> yshxxList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (xmxxList != null && xmxxList.size() > 0) {

			for (int i = 0; i < xmxxList.size(); i++) {

				HashMap<String,String> map = xmxxList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				// 项目代码
				String xmdm = map.get("xmdm");
				// 项目名称
				String xmmc = map.get("xmmc");
				// 项目类型
				String xmlx = map.get("xmlx");
				
				// -----------------------项目名称 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				html.append("style=\"width:33%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(xmmc);
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

				html.append(xmlx);
				html.append("</td>");
				// -----------------------项目类型 end---------------------------

				// -----------------------需审核人数 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				boolean default_shgw = true;
				
				if (shgwList != null && shgwList.size() > 0) {
					for (int j = 0; j < shgwList.size(); j++) {
						if (xmdm.equalsIgnoreCase(shgwList.get(j).get("xmdm"))) {
							if(!default_shgw){
								html.append("-->");
							}
							html.append(shgwList.get(j).get("gwmc"));
							
							boolean default_xsh = true;
							
							if (xshxxList != null && xshxxList.size() > 0) {
								for (int k = 0; k < xshxxList.size(); k++) {
									if (xmdm.equalsIgnoreCase(xshxxList.get(k)
											.get("xmdm"))) {
										if (shgwList.get(j).get("gwdm")
												.equalsIgnoreCase(
														xshxxList.get(k).get(
																"gwdm"))) {
											html.append("(");
											html.append(xshxxList.get(k).get(
													"xshrs"));
											html.append(")");
											
											default_xsh = false;
										}
									}
								}
							}
							
							if(default_xsh){
								html.append("(0)");
							}
							
							default_shgw = false;
						}
					}
				}

				html.append("</td>");
				// -----------------------需审核人数 end---------------------------
				
				// -----------------------已审核人数 begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				default_shgw = true;
				
				if (shgwList != null && shgwList.size() > 0) {
					for (int j = 0; j < shgwList.size(); j++) {
						if (xmdm.equalsIgnoreCase(shgwList.get(j).get("xmdm"))) {
							if (!default_shgw) {
								html.append("-->");
							}
							html.append(shgwList.get(j).get("gwmc"));

							boolean default_ysh = true;

							if (yshxxList != null && yshxxList.size() > 0) {
								for (int k = 0; k < yshxxList.size(); k++) {
									if (xmdm.equalsIgnoreCase(yshxxList.get(k)
											.get("xmdm"))) {
										if (shgwList.get(j).get("gwdm")
												.equalsIgnoreCase(
														yshxxList.get(k).get(
																"gwdm"))) {
											html.append("(");
											html.append("<a href=\"#\" ");
											html.append("onclick=\"showWdshDiv('"+xmdm+"','"+shgwList.get(j).get("gwdm")+"');return false;\"");
											html.append(">");
											html.append("<font color=\"blue\">");
											html.append("<U>");
											html.append(yshxxList.get(k).get(
													"yshrs"));
											html.append("</U>");
											html.append("</font>");
											html.append("</a>");
											html.append(")");

											default_ysh = false;
										}
									}
								}
							}

							if (default_ysh) {
								html.append("(0)");
							}

							default_shgw = false;
						}
					}
				}

				html.append("</td>");
				// -----------------------已审核人数 end---------------------------
				
				// -----------------------操作begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(" <a href=\"#\" onclick=\"if(checkItsDis(this)){showSpgw('"
								+ xmdm
								+ "');};return false;\" class=\"a_lssh\" disabled=\"true\"><font color=\"blue\">审核</font></a>");

				html.append("</td>");
				// -----------------------操作end---------------------------

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 创建我的审核Div
	 * 
	 * @date 2012.02.04
	 * @author 伟大的骆
	 */
	public void createWdshDiv(PjpyWdpjModel model, User user,
			HttpServletResponse response) throws Exception {

		// 项目代码
		String xmdm = model.getXmdm();
		// 岗位代码
		String gwid = model.getGwid();
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		
		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"15%\"><div align='left'>学号</div></th>");	
		html.append("<th width=\"15%\"><div align='left'>姓名</div></th>");
		html.append("<th width=\"30%\"><div align='left'>班级</div></th>");
		html.append("<th width=\"20%\"><div align='left'>审核时间</div></th>");
		html.append("<th width=\"20%\"><div align='left'>审核状态</div></th>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");
		
		List<HashMap<String,String>> list = dao.getWdshList(xmdm, gwid, user);
		
		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table class=\"formlist\">");
		
		for(int i=0;i<list.size();i++){
			html.append("<tr>");
			html.append("<td width=\"15%\">" + list.get(i).get("xh") + "</td>");
			html.append("<td width=\"15%\">" + list.get(i).get("xm") + "</td>");
			html.append("<td width=\"30%\">" + list.get(i).get("bjmc") + "</td>");
			html.append("<td width=\"20%\">" + list.get(i).get("shsj") + "</td>");
			html.append("<td width=\"20%\">" + list.get(i).get("shzt") + "</td>");
			html.append("</tr>");
		}
		
		if(8-list.size()>0){
			for(int i=0;i<8-list.size();i++){
				html.append("<tr>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("</tr>");
			}
		}
		
		html.append("</table>");
		html.append("</div>");
		
		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		
		html.append("</div>");
		
		response.getWriter().print(html.toString());

	}
}
