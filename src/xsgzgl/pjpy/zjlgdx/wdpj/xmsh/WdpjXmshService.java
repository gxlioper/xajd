package xsgzgl.pjpy.zjlgdx.wdpj.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_项目审核_浙江理工大学_Service类
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

public class WdpjXmshService extends
		xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService {

	WdpjXmshDAO dao = new WdpjXmshDAO();

	/**
	 * 获得项目审核表头
	 * 
	 * @date 2013-01-06
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getWdpjXmshTop(WdpjXmshModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "njzypm", "xh", "xm", "nj", "zymc",
				"bjmc", "sqsj", "shzt" };
		String[] cn = new String[] { "", "排名","综测总分","学号", "姓名","专业", "班级",
				"申请时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得项目审核结果集
	 * 
	 * @date 2013-01-06
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm myForm,
			WdpjXmshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getWdpjXmshList(myForm, model, user);

		return list;
	}

	/**
	 * 构建结果集
	 * 
	 * @date 2013-01-06
	 * @author 伟大的骆
	 */
	public String createWdpjXmshHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();
		// V4路径
		String stylePath = rsModel.getStylePath();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String disabled = rs[0];
				String pk = rs[1];

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");

				if (!Base.isNull(disabled)) {
					html.append(" disabled=\"true\"");
				}

				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				html.append("<td>");	
				html.append(rs[2]);
				html.append("</td>");
				
				//综测总分
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append(rs[9]);
				html.append("</td>");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"showXsxxDetail('" + rs[3] + "')\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append(rs[3]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
				

				//姓名
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append(rs[4]);
				html.append("</td>");
				
//				//年级
//				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
//				html.append(">");
//				html.append(rs[5]);
//				html.append("</td>");
				
				//专业
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" title=\""+rs[7]+"\" ");
				html.append(">");
				if(rs[7].length()>8){
					html.append(rs[7].substring(0,8)+"...");
				}else{
				html.append(rs[7]);
				}
				html.append("</td>");
				
				//班级
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" title=\""+rs[6]+"\" ");
				html.append(">");
				if(rs[6].length()>8){
					html.append(rs[6].substring(0,8)+"...");
				}else{
				html.append(rs[6]);
				}
				html.append("</td>");
				
				//申请时间
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				if(rs[8].length()>8){
					html.append(rs[8].substring(0,8)+"...");
				}else{
				html.append(rs[8]);
				}
				html.append("</td>");
				
				//审核状态
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				if ("未审核".equalsIgnoreCase(rs[rs.length - 1])) {
					html
							.append("<align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html
							.append("<p><img src=\""
									+ stylePath
									+ "images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				} else if ("通过".equalsIgnoreCase(rs[rs.length - 1])) {
					html
							.append("<align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html
							.append("<p><img src=\""
									+ stylePath
									+ "images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				} else if ("不通过".equalsIgnoreCase(rs[rs.length - 1])) {
					html
							.append("<align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html
							.append("<p><img src=\""
									+ stylePath
									+ "images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				} else if ("退回".equalsIgnoreCase(rs[rs.length - 1])) {
					html
							.append("<align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html
							.append("<p><img src=\""
									+ stylePath
									+ "images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				} else if ("需重审".equalsIgnoreCase(rs[rs.length - 1])) {
					html
							.append("<align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html
							.append("<p><img src=\""
									+ stylePath
									+ "images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}
				
			}
		}

		return html.toString();

	}
}
