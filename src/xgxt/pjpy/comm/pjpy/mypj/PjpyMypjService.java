package xgxt.pjpy.comm.pjpy.mypj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsb.PjpyXmsbForm;
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
 * Description: 评奖评优_我的评奖_Service类
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

public class PjpyMypjService extends PjpyPjlcService {

	PjpyMypjDAO dao = new PjpyMypjDAO();

	/**
	 * 获得我的评奖Html(老师)
	 * 
	 * @author 伟大的骆
	 */
	public String getMypjTeaHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// 已申请项目统计列表
		List<HashMap<String, String>> tjList = dao.getMypjTeaList(model, user);
		// 分页
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);
		
		StringBuilder spHtml = new StringBuilder();
		
		if (tjList != null && tjList.size() > 0) {

			//行数
			model.setRows(String.valueOf(tjList.size()));
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				spHtml.append("/>");
				
				// 项目名称
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");
				// 上报人数
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append(rs.get("sbrs"));
				spHtml.append("</td>");
				// 审核级别
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:50%\"> ");
				spHtml.append(rs.get("lv"));
				spHtml.append("</td>");
				// 操作
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\"> ");
//				spHtml.append(rs.get("lv"));
//				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
			}
		}

		return spHtml.toString();
	}	
	
	/**
	 * 获得我的评奖Html(学生)
	 * 
	 * @author 伟大的骆
	 */
	public String getMypjStuHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// 已申请项目统计列表
		List<HashMap<String, String>> tjList = dao.getMypjStuList(model, user);
		
		// 分页
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);
		
		StringBuilder spHtml = new StringBuilder();
		
		if (tjList != null && tjList.size() > 0) {

			//行数
			model.setRows(String.valueOf(tjList.size()));
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				
				String color="";
				spHtml.append("/>");
				
				// 项目名称
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\">");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");
				// 上报人数
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:50%\"> ");
				spHtml.append(rs.get("shzt"));
				spHtml.append("</td>");
				// 审核级别
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append("<a href=\"#\" onclick=\"showXmsqDetail('"+rs.get("pk")+"','"+rs.get("xmdm")+"','"+rs.get("shjb")+"');return false;\">");
				spHtml.append("<font color=\"blue\">点击查看详细</font>");
				spHtml.append("</a>");
				spHtml.append("</td>");
				// 操作
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\"> ");
//				spHtml.append(rs.get("lv"));
//				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
			}
		}

		return spHtml.toString();
	}	
}