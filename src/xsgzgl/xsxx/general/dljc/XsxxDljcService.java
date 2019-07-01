package xsgzgl.xsxx.general.dljc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��¼���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class XsxxDljcService extends CommService implements XsxxDljcInterface {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	XsxxDljcDAO dao = new XsxxDljcDAO();

	// ===================��ѯҳ�� begin=============================

	/**
	 * ��õ�¼����ͷ
	 * 
	 * @date 2012-12-04
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsxxDljclTop(XsxxDljcModel model,
			User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "xxwsmc" };
		String[] cn = new String[] { "", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
				"�༶����", "��Ϣ����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��õ�¼�������
	 * 
	 * @date 2012-12-04
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXsxxDljcList(XsxxGeneralForm myForm,
			XsxxDljcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxDljcList(myForm, model, user);
	}

	/**
	 * �����¼���HTML
	 * 
	 * @date 2012-12-04
	 * @author ΰ�����
	 */
	public String createXsxxDljcHTML(SearchRsModel rsModel,
			XsxxDljcModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ���õ�¼���
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public boolean resetDljc(XsxxGeneralForm myForm, XsxxDljcModel model,
			User user) {

		// ����
		String[] pkValue = model.getPkValue();
		// ��ѡ���
		String checked = model.getChecked();

		// �꼶
		String[] nj = model.getNj();
		if (nj != null && nj.length > 0 && Base.isNull(nj[0])) {
			nj = null;
		}

		// ѧԺ
		String[] xy = model.getXy();
		if (xy != null && xy.length > 0 && Base.isNull(xy[0])) {
			xy = null;
		}

		// רҵ
		String[] zy = model.getZy();
		if (zy != null && zy.length > 0 && Base.isNull(zy[0])) {
			zy = null;
		}

		// �༶
		String[] bj = model.getBj();
		if (bj != null && bj.length > 0 && Base.isNull(bj[0])) {
			bj = null;
		}

		boolean flag = false;

		try {
			if ("yes".equalsIgnoreCase(checked)) {// ����ѡ��
				flag = dao.deleteDljcb(pkValue, user);
				if (flag) {
					flag = dao.insertDljcb(pkValue, user);
				}
			} else if ("no".equalsIgnoreCase(checked)) {// ��δ��ѡ��
				flag = dao.deleteDljcb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertDljcb(nj, xy, zy, bj, user);
				}
			}

			if (flag) {
				dao.initXsfzxxb(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// ===================��ѯҳ�� end=============================

	// ===================����ҳ�� begin=============================

	/**
	 * �����ֶ�����Div
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public void createZdszDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		// ��������
		List<HashMap<String, String>> sslxList = dao.getSslxList();
		// �ֶ�����
		List<HashMap<String, String>> zdList = dao.getZdszList();

		for (int i = 0; i < sslxList.size(); i++) {

			HashMap<String, String> map = sslxList.get(i);
			String sslx = map.get("sslx");

			List<HashMap<String, String>> zdInfoList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			zdInfo.put("zd", "all_" + i);
			zdInfo.put("zdm", "ȫѡ");
			zdInfo.put("zdlx", "all");

			zdInfoList.add(zdInfo);

			for (int j = 0; j < zdList.size(); j++) {

				zdInfo = zdList.get(j);

				if (sslx.equalsIgnoreCase(zdInfo.get("sslx"))) {
					zdInfoList.add(zdInfo);
				}
			}

			int space = 5 - zdInfoList.size() % 5;
			if (space != 0) {
				for (int j = 0; j < space; j++) {
					zdInfo = new HashMap<String, String>();
					zdInfo.put("zd", "");
					zdInfo.put("zdm", "");

					zdInfoList.add(zdInfo);
				}
			}

			// ������
			int num = 0;
			boolean br = true;

			html.append("<table class=\"formlist\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<th colspan=\"5\">");
			html.append("<span>" + sslx + "</span>");
			html.append("</th>");
			html.append("</tr>");

			for (int j = 0; j < zdInfoList.size(); j++) {

				zdInfo = zdInfoList.get(j);

				// �ֶ�
				String zd = zdInfo.get("zd");
				// �ֶ���
				String zdm = zdInfo.get("zdm");
				// �ֶ�����
				String zdlx = zdInfo.get("zdlx");
				// ��־λ
				String flag = zdInfo.get("flag");

				// �Ƿ�ѡ��
				String checked = "";

				if ("yes".equalsIgnoreCase(flag)) {
					checked = "checked";
				}

				if (br) {
					html.append("<tr>");
					br = false;
				}

				html.append("<td style=\"width:15%\">");

				if ("all".equalsIgnoreCase(zdlx)) {
					html.append("<input type=\"checkbox\" id=\"cb_all_" + i
							+ "\" value=\"all\" onclick=\"checkAll('" + i
							+ "')\"/>");
				} else if (Base.isNull(zdlx)) {

				} else if ("disabled".equalsIgnoreCase(zdlx)) {
					html.append("<input type=\"checkbox\" value=\"" + zd
							+ "\" disabled=\"disabled\"/>");
				} else {
					html.append("<input type=\"checkbox\" name=\"cb_" + i
							+ "\" value=\"" + zd + "\" " + checked + "/>");
				}
				html.append(zdm);
				html.append("</td>");

				num++;

				if (num >= 5) {
					br = true;
					num = 0;
				}

				if (br) {
					html.append("</tr>");
				}

			}

			html.append("</thead>");

			html.append("</table>");
		}

		response.getWriter().print(html.toString());
	}

	/**
	 * �����ֶ�����
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public boolean saveZdsz(XsxxDljcModel model, User user) {

		String[] zd = model.getZd();

		boolean flag = false;

		try {
			flag = dao.deleteDljcszb(zd, user);

			if (flag) {
				flag = dao.insertDljcszb(zd, user);
			}

			if (flag) {

				flag = dao.deleteDljcb(null, null, null, null, user);

				if (flag) {
					flag = dao.insertDljcb(null, null, null, null, user);
				}
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	// ===================����ҳ�� end=============================

	// ===================��Ϣ���� begin=============================

	/**
	 * �Ƿ�����ѧ����Ϣ
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public boolean isXxws(String xh) {

		String xxws = dao.getXxws(xh);

		boolean flag = true;

		if ("no".equalsIgnoreCase(xxws)) {
			flag = false;
		}

		return flag;
	}

	/**
	 * ������Ϣ����Div
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public void createXxwsDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		String xh = user.getUserName();

		// �ֶ�������Ϣ
		List<HashMap<String, String>> list = getWszdList(user);

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span><font color=\"red\">��׼ȷ��д������Ϣ����������Ϣ����Ϊ�գ�</font></span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		if (list != null && list.size() > 0) {

			int row_num = list.size() / 2;

			html.append("<tbody>");

			for (int i = 0; i < row_num; i++) {
				html.append("<tr>");

				HashMap<String, String> left = list.get(i * 2);

				html.append("<th width=\"20%\">");
				if (!Base.isNull(left.get("zdm"))) {
					html.append("<span ");
					html.append("id=\"display_" + left.get("zd") + "\" >");
					html.append(left.get("zdm"));
					html.append("</span>");
				}
				html.append("</th>");
				html.append("<td width=\"30%\">");
				if (!Base.isNull(left.get("zdm"))) {
					html.append(createObjectHtml(left));
				}
				html.append("</td>");

				HashMap<String, String> right = list.get(i * 2 + 1);

				html.append("<th width=\"20%\">");
				if (!Base.isNull(right.get("zdm"))) {
					html.append("<span ");
					html.append("id=\"display_" + right.get("zd") + "\" >");
					html.append(right.get("zdm"));
					html.append("</span>");
				}
				html.append("</th>");
				html.append("<td>");
				if (!Base.isNull(right.get("zdm"))) {
					html.append(createObjectHtml(right));
				}
				html.append("</td>");
				html.append("</tr>");
			}

			html.append("</tbody>");

			html.append("<tfoot>");
			html.append("<tr>");
			html.append("<td colspan=\"4\">");
			html.append("<div class=\"btn\">");
			html
					.append("<button type=\"button\"  id=\"btn_save\"  onclick=\"checksSaveXxws();\">");
			html.append("�� ��</button>");
			html.append("</div>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tfoot>");

		} else {
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td colspan=\"4\">");
			html.append("����Ա��δ���ú���Ӧ��Ϣ");
			html.append("<br/>");
			html.append("����ϵ����Ա");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
		}
		html.append("</table>");
		
		html.append("<font color=\"red\">");
		html.append("&nbsp;&nbsp;&nbsp;&nbsp;������Ϣ��ѧУ��ϵ�����Ҫ;����");
		html.append("�Ժ�ͨ����ϵ�绰�������š���������Ҫ�����Ҫ��Ϣ��");
		html.append("Ϊ������ĸ���Ȩ�棬��ȷ��������Ϣ׼ȷ�����б䶯����ʱ���¡�");
		html.append("<font>");
		
		response.getWriter().print(html.toString());

	}

	/**
	 * ���������Ϣ�б�
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWszdList(User user) {

		// �ֶ�������Ϣ
		List<HashMap<String, String>> zdList = dao.getWszdList();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			ArrayList<String> xsxxb_column = new ArrayList<String>();
			ArrayList<String> xsfzxxb_column = new ArrayList<String>();

			for (int i = 0; i < zdList.size(); i++) {

				if ("xsxxb".equalsIgnoreCase(zdList.get(i).get("ssb"))) {
					xsxxb_column.add(zdList.get(i).get("zd"));
				} else if ("xsfzxxb".equalsIgnoreCase(zdList.get(i).get("ssb"))) {
					xsfzxxb_column.add(zdList.get(i).get("zd"));
				}
			}

			HashMap<String, String> map = new HashMap<String, String>();

			HashMap<String, String> xsxxb = new HashMap<String, String>();
			if (xsxxb_column != null && xsxxb_column.size() > 0) {

				xsxxb = getRsInfo("xsxxb", "xh", user.getUserName(),
						xsxxb_column.toArray(new String[] {}));
			}

			HashMap<String, String> xsfzxxb = new HashMap<String, String>();
			if (xsfzxxb_column != null && xsfzxxb_column.size() > 0) {
				xsfzxxb = getRsInfo("xsfzxxb", "xh", user.getUserName(),
						xsfzxxb_column.toArray(new String[] {}));
			}

			map.putAll(xsxxb);
			map.putAll(xsfzxxb);

			for (int i = 0; i < zdList.size(); i++) {
				HashMap<String, String> zdInfo = zdList.get(i);

				String content = map.get(zdInfo.get("zd"));

				if (Base.isNull(content)) {
					content = "";
				}
				zdInfo.put("content", content);
				list.add(zdInfo);
			}
		}

		if (list != null && list.size() > 0 && list.size() % 2 == 1) {
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			list.add(zdInfo);
		}

		return list;
	}

	/**
	 * ��������HTML
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	private String createObjectHtml(HashMap<String, String> map) {

		StringBuilder html = new StringBuilder();

		// �ֶ�����
		String zdlx = map.get("zdlx");

		if ("text".equalsIgnoreCase(zdlx)) {// �����(TEXT)
			html.append(createTextHtml(map));
		} else if ("textarea".equalsIgnoreCase(zdlx)) {// �ı���(TEXTAREA)
			// html.append(createTextAreaHtml(map));
		} else if ("calendar".equalsIgnoreCase(zdlx)) {// ���ڿؼ�(CALENDAR)
			html.append(createCalendarHtml(map));
		} else if ("select".equalsIgnoreCase(zdlx)) {// �����б�(SELECT)
			html.append(createSelectHtml(map));
		} else if ("area".equalsIgnoreCase(zdlx)) {// ��������(AREA)
			// html.append(createAreaHtml(map));
		} else if ("display".equalsIgnoreCase(zdlx)) {// ��ʾ(DISPLAY)
			// html.append(createDisplayHtml(map));
		} else if ("department".equalsIgnoreCase(zdlx)) {// ����(DEPARTMENT)
			// html.append(createStuPicHtml(map));
		} else if ("class".equalsIgnoreCase(zdlx)) {// �༶(CLASS)
			// html.append(createClassHtml(map));
		} else if ("stu_pic".equalsIgnoreCase(zdlx)) {// ѧ����Ƭ(STUPIC)
			// html.append(createStuPicHtml(map));
		} else if ("stu_choose".equalsIgnoreCase(zdlx)) {// ѧ��ѡ��(STUCHOOSE)
			// html.append(createStuChooseHtml(map));
		}

		return html.toString();
	}

	/**
	 * ���dText����
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	private String createTextHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '�ֶ�';
		String checked = map.get("checked");// '��֤';
		String maxLength = "";// ��󳤶�

		if (!Base.isNull(checked) && checked.split("!!luojw!!").length > 0) {
			maxLength = checked.split("!!luojw!!")[0];
		}

		String content = map.get("content");// '����';

		StringBuilder html = new StringBuilder();

		// �Ƿ�������L��
		boolean bool_maxlength = (!Base.isNull(maxLength)) ? true : false;

		String width = "150px";
		String maxlength = "";

		if (bool_maxlength) {
			maxlength = "maxLength=\"" + maxLength + "\" ";
		}

		html.append("<input type=\"text\" ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"text_" + zd + "\" ");
		html.append("style=\"" + width + "\" ");
		html.append(maxlength);
		html.append(" value=\"" + content + "\" ");

		if (!Base.isNull(checked) && checked.split("!!luojw!!").length > 0) {
			for (int i = 0; i < checked.split("!!luojw!!").length; i++) {
				if ("sfzh".equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// ���֤��
					html.append("onblur=\"checkSfzh(this)\" ");
				} else if ("num"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// ������֤
					html.append("onkeydown=\"return onlyNum(this,"
							+ checked.split("!!luojw!!")[0] + ")\" ");
					html.append("onmousedown=\"return onlyNum(this,"
							+ checked.split("!!luojw!!")[0] + ")\" ");
					html.append("style=\"ime-mode:disabled\" ");
				} else if ("dzyx"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// ��������
					html
							.append("onblur=\"if(isEmail(this.value)){}else{this.value='';alertInfo('���������ʽ����ȷ����ȷ��')}\" ");
				} else if ("money"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// �����֤
					html.append("onkeyup=\"checkInputNum(this)\"");
					html.append("onblur=\"checkInputNum(this)\"");
					html.append("style=\"ime-mode:disabled;\"");
				}
			}
		}

		html.append("/>");

		return html.toString();
	}

	/**
	 * ���dCalendar����
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	private String createCalendarHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '�ֶ�';
		String input_width = "150px";// '���';
		String content = map.get("content");// '����';

		StringBuilder html = new StringBuilder();

		// �Ƿ��Ќ���
		boolean bool_width = (!Base.isNull(input_width)) ? true : false;

		String width = "";

		if (bool_width) {
			width = "width:" + input_width + "px";
		}

		html.append("<input type=\"text\" ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"calendar_" + zd + "\" ");
		html.append("onblur=\"dateFormatChg(this)\" ");
		html.append("style=\"cursor:hand;");
		html.append(width);
		html.append("\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append(" value=\"" + content + "\" ");
		html.append("readOnly=\"true\"/> ");

		return html.toString();
	}

	/**
	 * ���dSelect����
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	private String createSelectHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '�ֶ�';
		String input_width = "";// '���';
		String input_postfix = "";// '��׺';
		String content = map.get("content");// '����';

		String source_table = map.get("source_table");// '�����б����';
		String option_dm = map.get("option_dm");// '�����б����';
		String option_mc = map.get("option_mc");// '�����б�����';

		StringBuilder html = new StringBuilder();

		// �Ƿ�����Y
		boolean bool_postfix = (!Base.isNull(input_postfix)) ? true : false;
		// �Ƿ��Д���Դ
		boolean bool_table = (!Base.isNull(source_table)) ? true : false;

		String postfix = "";

		if (bool_postfix) {
			postfix = input_postfix;
		}

		html.append("<select ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"select_" + zd + "\" ");
		html.append("style=\"width: 150px\" ");
		html.append(">");
		html.append("<option value=\"\"></option>");

		if (!bool_table) {// ������Դ

			String[] dm = option_dm.split("!!luojw!!");
			String[] mc = option_mc.split("!!luojw!!");

			for (int i = 0; i < dm.length; i++) {
				html.append("<option ");
				if (dm[i].equalsIgnoreCase(content)) {
					html.append(" selected=\"selected\" ");
				}
				html.append("value=\"" + dm[i] + "\">");
				html.append(mc[i]);
				html.append("</option>");
			}
		} else {
			if (!Base.isNull(option_dm)) {

				List<HashMap<String, String>> optionList = dao.getListBySource(
						source_table, option_dm, option_mc);

				if (optionList != null && optionList.size() > 0) {

					for (int m = 0; m < optionList.size(); m++) {
						String dm = optionList.get(m).get("dm");
						String mc = optionList.get(m).get("mc");

						html.append("<option ");
						if (dm.equalsIgnoreCase(content)) {
							html.append(" selected=\"selected\" ");
						}
						html.append("value=\"" + dm + "\">");
						html.append(mc);
						html.append("</option>");
					}
				}
			}
		}

		html.append("</select>");
		html.append(postfix);

		return html.toString();
	}

	/**
	 * ������Ϣ����
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public boolean saveXxws(User user, HttpServletRequest request) {

		// �ֶ�������Ϣ
		List<HashMap<String, String>> zdList = dao.getWszdList();

		ArrayList<String> xsxxb_column = new ArrayList<String>();
		ArrayList<String> xsxxb_value = new ArrayList<String>();
		ArrayList<String> xsfzxxb_column = new ArrayList<String>();
		ArrayList<String> xsfzxxb_value = new ArrayList<String>();

		for (int i = 0; i < zdList.size(); i++) {
			HashMap<String, String> zdInfo = zdList.get(i);
			String zd = zdInfo.get("zd");
			String ssb = zdInfo.get("ssb");
			String id = "str_" + zd;
			String value = unicode2Gbk(request.getParameter(id));

			if ("xsxxb".equalsIgnoreCase(ssb)) {
				xsxxb_column.add(zd);
				xsxxb_value.add(value);
			} else if ("xsfzxxb".equalsIgnoreCase(ssb)) {
				xsfzxxb_column.add(zd);
				xsfzxxb_value.add(value);
			}
		}

		boolean flag = false;

		try {
			flag = dao.updateXsxxb(xsxxb_column.toArray(new String[] {}),
					xsxxb_value.toArray(new String[] {}), user.getUserName(),
					user);

			if (flag && xsfzxxb_column != null && xsfzxxb_column.size() > 0) {
				flag = dao.updateXsfzxxb(xsfzxxb_column
						.toArray(new String[] {}), xsfzxxb_value
						.toArray(new String[] {}), user.getUserName(), user);
			}

			if (flag) {

				boolean isExists = isExists("xg_xsxx_dljcb", "xh", user
						.getUserName());

				if (isExists) {
					dao.updateDljcb(user.getUserName(), user);
				} else {
					dao.insertDljcb(new String[] { user.getUserName() }, user);
					dao.updateDljcb(user.getUserName(), user);
				}
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	// ===================��Ϣ���� end=============================

}
