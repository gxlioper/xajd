package xsgzgl.pjpy.czzyjsxy.wdpj.lssb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_老师上报_池州职业技术学院_Service类
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

public class WdpjLssbService extends
		xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService {

	WdpjLssbDAO dao = new WdpjLssbDAO();

	/**
	 * 获得表头文件(我的评奖_老师上报)
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xh", "xm", "nj", "bjmc", "zd1",
				"zcfbjpm", "zyfbjpm", "cz" };
		String[] cn = new String[] { "学号", "姓名", "年级", "班级", "综测分", "综测排名",
				"智育排名", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 查询数据(我的评奖_老师上报)
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 指定评奖项目、评奖班级学生结果集
		ArrayList<String[]> list = dao.getWdpjLssbList(myForm, model, user);

		return list;
	}

	/**
	 * 构建HTML(我的评奖_老师上报)
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 */
	public String createWdpjLssbHTML(SearchRsModel rsModel,
			PjpyWdpjModel model, HashMap<String, String> qdrsMap,
			ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		// ---------------指定项目 班级人数限定 begin------------------
		String str_qdrs = Base.isNull(qdrsMap.get("qdrs")) ? "0" : qdrsMap
				.get("qdrs");
		int qdrs = Integer.parseInt(str_qdrs);
		// ---------------指定项目 班级人数限定 end------------------

		// ------------------拼接html begin -------------------------
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				int len = rs.length;

				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<a href=\"#\" onclick=\"showXsxxDiv('" + rs[0]
						+ "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[0]);
				html.append("</font>");
				html.append("</a>");

				// ------------------系统推荐人员 begin---------------
				if (i < qdrs) {
					html.append("<font color=\"red\">★</font>");
				}
				// ------------------系统推荐人员 end---------------
				html.append("</td>");

				// -----------------常规字段显示 begin ----------------------
				for (int j = 1; j < rs.length - 2; j++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				// -----------------常规字段显示 end ----------------------

				// -----------------审核结果 begin ----------------------
				if ("wsq".equalsIgnoreCase(rs[len - 2])) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"
							+ rs[0] + "');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("上报");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				} else if ("wsh".equalsIgnoreCase(rs[len - 2])) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"
							+ rs[len - 1] + "');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("撤销");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				} else {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<a href=\"#\" onclick=\"return false;\">");
					html.append("<font color=\"#A8A7A7\">");
					html.append("撤销");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}
				// -----------------审核结果 end ----------------------
				html.append("</tr>");
			}
		}
		// ------------------拼接html end -------------------------
		return html.toString();
	}
}
