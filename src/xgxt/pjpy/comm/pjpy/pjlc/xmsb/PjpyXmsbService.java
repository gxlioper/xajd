package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsh.PjpyXmshService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszUtils;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目上报_Service类
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

public class PjpyXmsbService extends PjpyPjlcService {

	PjpyXmsbDAO dao = new PjpyXmsbDAO();

	/**
	 * 获得项目上报学生列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsList(PjpyXmsbForm model,
			User user) {
		return dao.getXsList(model, user);
	}
	
	/**
	 * 根据录入获取项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmOption(PjpyXmsbForm model) {
		return dao.getXmOption(model);
	}
	
	/**
	 * 根据项目条件初始化学生列表
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> defaultXsList(PjpyXmsbForm model,
			User user) {
		return null;
	}
	
	/**
	 * 获得项目设置人数
	 * 
	 * @author 伟大的骆
	 */
	public String getXmszrs(PjpyXmsbForm model) {
		return dao.getXmszrs(model);
	}
	
	/**
	 * 获得老师上报Html
	 * 
	 * @author 伟大的骆
	 */
	public String getLssbHtml(SearchRsModel rsModel, PjpyXmsbForm model, User user) {
				
		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();
		
		// 项目设置model初始化
		PjpyXmszModel xmszModel = model.getXmszModel();
		// 班级代码
		String bjdm = model.getBjdm();
		// 项目代码
		String xmdm = model.getXmdm();
		// 人数设置
		String rssz = xmszModel.getRssz();
		// 控制范围
		String kzfw = xmszModel.getKzfw();
		// 综测排名
		String zcpm = model.getZcpm();
		
		// 学生信息列表
		List<HashMap<String, String>> xsList = getXsList(model, user);
	
		// 无资格评奖的学号
		String[] noPjzgXh = new String[]{};
		
		// 是否过滤
		String search_condition = model.getSearch_condition();
		// 是否根据条件过滤
		if ("yes".equalsIgnoreCase(search_condition)) {
			// 上报班级学号
			String[] xh = getSbxh(xsList);
			// 上报项目的条件设置情况
			List<HashMap<String, String>> tjList = tjszUtils
					.getXmTj(bjdm, xmdm);
			if (tjList != null && tjList.size() > 0) {
				noPjzgXh = xh;
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> tjMap = tjList.get(i);
					noPjzgXh = tjszUtils.getNoPjzgXh(noPjzgXh, tjMap, null);
				}
			}
		}
		
		StringBuilder spHtml = new StringBuilder();

		boolean defaultRs = false;
		//确定人数
		String qdrs = "0";
		int count = 1;
		if ("是".equalsIgnoreCase(rssz) && "3".equalsIgnoreCase(zcpm)
				&& "bj".equalsIgnoreCase(kzfw)) {
			
			qdrs = xmszModel.getSzrs();
			qdrs = Base.isNull(qdrs) ? "0" : qdrs;
			defaultRs = true;
		}
		
		if (xsList != null && xsList.size() > 0) {

			for (int i = 0; i < xsList.size(); i++) {

				HashMap<String, String> rs = xsList.get(i);
				
				if ("yes".equalsIgnoreCase(search_condition)) {
					if (isExistInArr(noPjzgXh, rs.get("xh"))) {
						continue;
					}
				}
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				
				String color="";

				// 默认选中
				boolean checked = false;
				// 已申请
				boolean ysq = Base.isNull(rs.get("ysq")) ? false : true;
				
				if (defaultRs) {
					if (count <= Integer.parseInt(qdrs)) {
						//color="background-color: green";
						//checked = true;
					}
				}
				spHtml.append("/>");
				
				spHtml.append("<td align=\"center\" width=\"5px\" style=\"" + color + "\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + rs.get("xh") + "\"");
				
				// if (isExistInArr(noPjzgXh, rs.get("xh"))
				// || Base.isNull(rs.get("pm"))) {
				// spHtml.append("disabled=\"disabled\"");
				// } else if (ysq) {
				// spHtml.append("disabled=\"disabled\"");
				// }
				
				if (checked) {
					spHtml.append("checked=\"true\"");
				} else if (ysq) {
					spHtml.append("checked=\"true\" disabled=\"true\"");
				}
				
				spHtml.append("/>");
				spHtml.append("</td>");
				
				// 学号
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xh"));
				spHtml.append("</td>");
				// 姓名
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xm"));
				spHtml.append("</td>");
				// 性别
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xb"));
				spHtml.append("</td>");
				// 班级名称
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("pjbjmc"));
				spHtml.append("</td>");
				// 综合分
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zhf"))){
					spHtml.append("未计算分数");
				}else{
					spHtml.append(rs.get("zhf"));
				}
				spHtml.append("</td>");
				// 综合排名
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("pm"))){
					spHtml.append("未计算排名");
				}else{
					spHtml.append(rs.get("pm"));
				}
				spHtml.append("</td>");
				
				
//				 综合分
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zyf"))){
					spHtml.append("未计算分数");
				}else{
					spHtml.append(rs.get("zyf"));
				}
				spHtml.append("</td>");
				// 综合排名
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zyfpm"))){
					spHtml.append("未计算排名");
				}else{
					spHtml.append(rs.get("zyfpm"));
				}
				spHtml.append("</td>");
				
				// 操作
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append("<a href=\"#\" onclick=\"showCjInfo('"+rs.get("xh")+"');\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("查看成绩");
				spHtml.append("</font>");
				spHtml.append("</a>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
				count++;
			}
		}

		return spHtml.toString();
	}	
	
	/**
	 * 获得上报班级的学号数组
	 * 
	 * @author 伟大的骆
	 */
	private String[] getSbxh(List<HashMap<String, String>> xsList){
		
		List<String> xhList = new ArrayList<String>();
		
		if (xsList != null && xsList.size() > 0) {
			for (int i = 0; i < xsList.size(); i++) {
				HashMap<String, String> map = xsList.get(i);
				String xh = map.get("xh");
				xhList.add(xh);
			}
		}
		
		return xhList.toArray(new String[]{});
	}
	
	/**
	 * 保存项目上报
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmsb(PjpyXmsbForm model,
			User user) {
		boolean flag = saveXmsq(model, user);
		
		if(flag){
			flag = saveXmsh(model, user);
		}
		
		return flag;
	}
		
	/**
	 * 保存项目申请表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmsq(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 将学期
		String pjnd = jbszModel.getPjnd();// 评奖年度
		String xmdm = model.getXmdm();// 项目代码

		// 项目设置model初始化
		String pkValue = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
		
		model.setXmszModel(xmszModel);
		
		return dao.saveXmsqb(model, user);
	}
	
	/**
	 * 保存项目审核表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmsh(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 将学期
		String pjnd = jbszModel.getPjnd();// 评奖年度
		String xmdm = model.getXmdm();// 项目代码

		// 项目设置model初始化
		String pkValue = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
		
		model.setXmszModel(xmszModel);
		
		return dao.saveXmshb(model, user);
	}
	
	/**
	 * 判断上报资格
	 * 
	 * @author 伟大的骆
	 */
	public String judgeSbzg(PjpyXmsbForm model) {

		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();

		// 项目设置model初始化
		PjpyXmszModel xmszModel = model.getXmszModel();
		// 班级代码
		String bjdm = model.getBjdm();
		// 项目代码
		String xmdm = model.getXmdm();

		// 上报项目的条件设置情况
		List<HashMap<String, String>> tjList = tjszUtils.getXmTj(bjdm, xmdm);
		// 无资格评奖的学号
		String[] noPjzgXh = new String[] {};
		// 评奖学号
		String[] xh = model.getPjxh();
		String message = "";

		if (tjList != null && tjList.size() > 0) {
			noPjzgXh = xh;
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				message = tjszUtils.getNoPjzgXh(noPjzgXh, tjMap);
				if(!Base.isNull(message)){
					break;
				}
			}
		}

		return message;
	}
	
	/**
	 * 创建学生成绩HTML
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createXscjHTML(PjpyXmsbForm model, HttpServletResponse response)
			throws IOException {

		// 学号
		String xh = model.getXh();

		// 成绩列表
		List<HashMap<String, String>> cjList = dao.getCjList(model);
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		
		html.append("<table class=\"dateline\" width=\"100%\">");
		// =========================标题===========================
		html.append("<tr>");
		html.append("<td>课程名称</td>");
		html.append("<td>课程性质</td>");
		html.append("<td>成绩</td>");
		html.append("</tr>");
		// =========================标题 end========================
		
		// =========================内容===========================
		if (cjList != null && cjList.size() > 0) {
			for (int i = 0; i < cjList.size(); i++) {
				HashMap<String, String> map = cjList.get(i);
				html.append("<tr>");
				html.append("<td>" + map.get("kcmc") + "</td>");
				html.append("<td>" + map.get("kcxz") + "</td>");
				html.append("<td>" + map.get("cj") + "</td>");
				html.append("</tr>");
			}
		}else{
			html.append("<tr>");
			html.append("<td colspan=\"3\">");
			html.append("<font color=\"blue\">");
			html.append("查询不到该学生的成绩信息");
			html.append("</font>");
			html.append("</td>");
			html.append("</tr>");
		}
		// =========================内容 end========================
		
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
}