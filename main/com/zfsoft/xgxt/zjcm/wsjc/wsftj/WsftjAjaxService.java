/**
 * @部门:学工产品事业部
 * @日期：2016-3-14 上午11:34:02 
 */
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-14 上午11:34:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WsftjAjaxService extends BasicService {
	private WsftjDao dao = new WsftjDao();
	public void createHTML(WsftjForm myForm, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		String nd = myForm.getSearchModel().getSearch_tj_nd()[0];
		String yf = myForm.getSearchModel().getSearch_tj_yf()[0];
		// 卫生分统计
		List<HashMap<String, String>> tjList = dao.getPageList(myForm, user);

		StringBuilder html = new StringBuilder();
		html.append("<div style=\"width:100%;height:100%;overflow-x:auto;overflow-y:auto;\">");
		html.append("<h1 style=\"text-align:center;font-size:16px;margin-bottom:20px;margin-top:20px\">");
		html.append("浙江传媒学院寝室月评公布表("+nd+"年"+yf+"月)");
		html.append("</h1>");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<tbody>");
		html.append("<thead>");
		// =================第一行===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("学院");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("查寝室个数");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("优秀寝室个数");
		html.append("</td>");
		html.append("<td width=\"15%\">");
		html.append("百分比");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("不合格寝室个数");
		html.append("</td>");
		html.append("<td width=\"15%\">");
		html.append("百分比");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		if (tjList != null && tjList.size() > 0) {
			for (HashMap<String, String> zcxx:tjList) {
				html.append("<tr>");
				html.append("<td>" + zcxx.get("bmmc") + "</td>");
				html.append("<td>" + zcxx.get("totalnum") + "</td>");
				html.append("<td>" + zcxx.get("good") + "</td>");
				html.append("<td>" + zcxx.get("hao") + "</td>");
				html.append("<td>" + zcxx.get("bad") + "</td>");
				html.append("<td>" + zcxx.get("huai") + "</td>");
				html.append("</tr>");
			}
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		response.getWriter().print(html.toString());
	}
}
