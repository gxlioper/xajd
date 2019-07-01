package xsgzgl.pjpy.zjlgdx.wdpj;

import java.util.ArrayList;


import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

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
 * @author 程强
 * @version 1.0
 */
public class PjpyWdpjService  extends
		xsgzgl.pjpy.general.wdpj.PjpyWdpjService {

	PjpyWdpjDAO dao = new PjpyWdpjDAO();

	
	/**
	 * 构建结果集(评奖评优_我的评奖【学生】)
	 * 
	 * @author 程强
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
					html.append("/");
					html.append(" <a href=\"#\" onclick=\"printPj('"+rs[0]+"','"+user.getUserName()+"');return false;\"><font color=\"blue\">打印登记表</font></a> ");
					html.append("</td>");
					}
				// -----------------------当前审核状态 end---------------------------
				}
				html.append("</tr>");
			}
		}

		return html.toString();
	}

}
