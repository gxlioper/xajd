/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-14 ����11:34:02 
 */
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-14 ����11:34:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WsftjAjaxService extends BasicService {
	private WsftjDao dao = new WsftjDao();
	public void createHTML(WsftjForm myForm, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		String nd = myForm.getSearchModel().getSearch_tj_nd()[0];
		String yf = myForm.getSearchModel().getSearch_tj_yf()[0];
		// ������ͳ��
		List<HashMap<String, String>> tjList = dao.getPageList(myForm, user);

		StringBuilder html = new StringBuilder();
		html.append("<div style=\"width:100%;height:100%;overflow-x:auto;overflow-y:auto;\">");
		html.append("<h1 style=\"text-align:center;font-size:16px;margin-bottom:20px;margin-top:20px\">");
		html.append("�㽭��ýѧԺ��������������("+nd+"��"+yf+"��)");
		html.append("</h1>");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<tbody>");
		html.append("<thead>");
		// =================��һ��===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("ѧԺ");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("�����Ҹ���");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("�������Ҹ���");
		html.append("</td>");
		html.append("<td width=\"15%\">");
		html.append("�ٷֱ�");
		html.append("</td>");
		html.append("<td width=\"10%\">");
		html.append("���ϸ����Ҹ���");
		html.append("</td>");
		html.append("<td width=\"15%\">");
		html.append("�ٷֱ�");
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
