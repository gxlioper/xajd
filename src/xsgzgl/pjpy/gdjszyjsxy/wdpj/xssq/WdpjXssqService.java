package xsgzgl.pjpy.gdjszyjsxy.wdpj.xssq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_学生申请_通用_Service类
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

public class WdpjXssqService extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService  {

	WdpjXssqDAO dao = new WdpjXssqDAO();
	
	
	/**
	 * 显示学生申请DIV
	 * 
	 * @author qlj
	 * @throws IOException 
	 * 
	 * @throws IOException
	 */
	public void showXssqDiv(String opera,String xmdm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		String xh=user.getUserName();
		HashMap<String,String>xssqMap=new HashMap<String,String>();
		if("modi".equalsIgnoreCase(opera)){
			xssqMap.putAll(dao.getXssqMap(xmdm, xh));
		}
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>学生申请</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("项目说明");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
		// 项目说明
		html.append(Base.isNull(pjxmModel.getXmsm())?"":pjxmModel.getXmsm());
		
		html.append("</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("申请理由");
		html.append("<br/><font color='blue'>(请填写申请理由和评审<br/>学年所获主要奖项，<br/>填写字数最多500字)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
		html.append("<textarea id=\"sqly\"  name=\"sqly\"  rows=\"8\" cols=\"\" onblur='chLeng(this,500);' style='word-break:break-all;width:96%'>");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("sqly"))) {
				html.append(xssqMap.get("sqly"));
			}
		}
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");

		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"save('"+opera+"','"+xmdm+"');return false;\">");
		html.append("保 存");
		html.append("</button>");

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
	
}
