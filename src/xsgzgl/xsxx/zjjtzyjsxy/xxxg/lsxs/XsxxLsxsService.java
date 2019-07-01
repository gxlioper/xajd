package xsgzgl.xsxx.zjjtzyjsxy.xxxg.lsxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxLsxsInterface;
import xsgzgl.xsxx.general.lsxs.XsxxLsxsModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��ʷѧ��_�㽭��ְͨҵ����ѧԺ_Service��
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

public class XsxxLsxsService extends CommService implements XsxxLsxsInterface {

	XsxxLsxsDAO dao = new XsxxLsxsDAO();

	/**
	 * ��ñ�ͷ�ļ�(��ʷѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsxxLsxsTop(XsxxLsxsModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"byny" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "�༶", "��ҵʱ��" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * ��ý����(��ʷѧ��)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxLsxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getXsxxLsxsList(myForm, user);

		return list;
	}
	
	/**
	 * ���������(��ʷѧ��)
	 * 
	 * @author ΰ�����
	 */
	public String createXsxxLsxsHTML(SearchRsModel rsModel,
			XsxxLsxsModel model, ArrayList<String[]> rsArrList, User user) {

		BasicService basicService = new BasicService();
		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];

				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("style=\"cursor:hand\" ");
				html.append("ondblclick=\"showXsxxDetail('" + pk + "')\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html
						.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");

				for (int j = 1; j < rs.length; j++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();

	}

	public HashMap<String, String> getLsxsInfo(XsxxZxxsModel model, User user) {
		// TODO �Զ����ɷ������
		return null;
	}
}