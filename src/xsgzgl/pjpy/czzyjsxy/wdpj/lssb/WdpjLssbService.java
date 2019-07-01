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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��ʦ�ϱ�_����ְҵ����ѧԺ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class WdpjLssbService extends
		xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService {

	WdpjLssbDAO dao = new WdpjLssbDAO();

	/**
	 * ��ñ�ͷ�ļ�(�ҵ�����_��ʦ�ϱ�)
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xh", "xm", "nj", "bjmc", "zd1",
				"zcfbjpm", "zyfbjpm", "cz" };
		String[] cn = new String[] { "ѧ��", "����", "�꼶", "�༶", "�۲��", "�۲�����",
				"��������", "����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ѯ����(�ҵ�����_��ʦ�ϱ�)
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ָ��������Ŀ�������༶ѧ�������
		ArrayList<String[]> list = dao.getWdpjLssbList(myForm, model, user);

		return list;
	}

	/**
	 * ����HTML(�ҵ�����_��ʦ�ϱ�)
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 */
	public String createWdpjLssbHTML(SearchRsModel rsModel,
			PjpyWdpjModel model, HashMap<String, String> qdrsMap,
			ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		// ---------------ָ����Ŀ �༶�����޶� begin------------------
		String str_qdrs = Base.isNull(qdrsMap.get("qdrs")) ? "0" : qdrsMap
				.get("qdrs");
		int qdrs = Integer.parseInt(str_qdrs);
		// ---------------ָ����Ŀ �༶�����޶� end------------------

		// ------------------ƴ��html begin -------------------------
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

				// ------------------ϵͳ�Ƽ���Ա begin---------------
				if (i < qdrs) {
					html.append("<font color=\"red\">��</font>");
				}
				// ------------------ϵͳ�Ƽ���Ա end---------------
				html.append("</td>");

				// -----------------�����ֶ���ʾ begin ----------------------
				for (int j = 1; j < rs.length - 2; j++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				// -----------------�����ֶ���ʾ end ----------------------

				// -----------------��˽�� begin ----------------------
				if ("wsq".equalsIgnoreCase(rs[len - 2])) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"
							+ rs[0] + "');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("�ϱ�");
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
					html.append("����");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				} else {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<a href=\"#\" onclick=\"return false;\">");
					html.append("<font color=\"#A8A7A7\">");
					html.append("����");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}
				// -----------------��˽�� end ----------------------
				html.append("</tr>");
			}
		}
		// ------------------ƴ��html end -------------------------
		return html.toString();
	}
}
