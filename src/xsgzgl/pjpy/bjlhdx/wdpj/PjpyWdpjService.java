package xsgzgl.pjpy.bjlhdx.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_�������ϴ�ѧ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyWdpjService extends xsgzgl.pjpy.general.wdpj.PjpyWdpjService {

	PjpyWdpjDAO dao = new PjpyWdpjDAO();

	/**
	 * ��ñ�ͷ�ļ�(��������_�ҵ�����)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjpyWdpjTop(PjpyWdpjModel model,
			User user) {

		// �û�����
		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�
			return getTopByStu();
		} else {// ��ѧ���û�
			return getTopByTea();
		}
	}

	/**
	 * ��ñ�ͷ�ļ���ѧ����
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getTopByStu() {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xmmc", "sqsj", "shzt", "cz" };
		String[] cn = new String[] { "��������Ŀ", "����ʱ��", "Ŀǰ���״̬", "����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ñ�ͷ�ļ�����ѧ����
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getTopByTea() {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xmmc", "xmlx", "xshrs", "yshrs", "cz" };
		String[] cn = new String[] { "��Ŀ����", "��Ŀ����", "���������", "���������", "����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��������_�ҵ�����)
	 * 
	 * @date 2013.02.04
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getPjpyWdpjList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// �û�����
		String userType = user.getUserType();
		ArrayList<String[]> wdpjList = new ArrayList<String[]>();
		if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�
			wdpjList = dao.getWdpjByStu(myForm, user);
		} else {
			//wdpjList = dao.getWdpjByTea(myForm, user);
		}

		return wdpjList;
	}

	/**
	 * ���������(��������_�ҵ�����)
	 * 
	 * @author ΰ�����
	 */
	public String createPjpyWdpjHTML(SearchRsModel rsModel,
			PjpyGeneralForm myForm, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user) {

		// �û�����
		String userType = user.getUserType();
		String html = "";
		if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�
			html = createStuHTML(rsModel, model, rsArrList, user);
		} else {
			// ��Ŀ��Ϣ�б�
			List<HashMap<String, String>> xmxxList = getXmxxList(myForm, user);
			// ��˸�λ�б�
			List<HashMap<String, String>> shgwList = dao.getShgwxxList(
					xmxxList, user);
			// �������Ϣ�б�
			List<HashMap<String, String>> xshxxList = dao.getXshxxList(
					xmxxList, user);
			// �������Ϣ�б�
			List<HashMap<String, String>> yshxxList = dao.getYshxxList(
					xmxxList, user);

			if (xmxxList != null && xmxxList.size() > 0) {
				for (int i = 0; i < xmxxList.size(); i++) {
					String[] value = new String[] {};
					rsArrList.add(value);
				}
			}
			
			html = createTeaHTML(rsModel, model, xmxxList, shgwList, xshxxList,
					yshxxList, user);
		}
		return html;
	}

	/**
	 * ��ȡ��Ŀ��Ϣ�б�
	 * 
	 * @date 2012.02.04
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmxxList(PjpyGeneralForm myForm,
			User user) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		String zgh = user.getUserName();

		return dao.getXmxxList(myForm, pjxn, pjxq, pjnd, zgh);
	}

	/**
	 * ���������(��������_�ҵ�����)����ѧ����
	 * 
	 * @date 2012.02.04
	 * @author ΰ�����
	 */
	public String createTeaHTML(SearchRsModel rsModel, PjpyWdpjModel model,
			List<HashMap<String, String>> xmxxList,
			List<HashMap<String, String>> shgwList,
			List<HashMap<String, String>> xshxxList,
			List<HashMap<String, String>> yshxxList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (xmxxList != null && xmxxList.size() > 0) {

			for (int i = 0; i < xmxxList.size(); i++) {

				HashMap<String,String> map = xmxxList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				// ��Ŀ����
				String xmdm = map.get("xmdm");
				// ��Ŀ����
				String xmmc = map.get("xmmc");
				// ��Ŀ����
				String xmlx = map.get("xmlx");
				
				// -----------------------��Ŀ���� begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				html.append("style=\"width:33%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(xmmc);
				html.append("</td>");
				// -----------------------��Ŀ���� end---------------------------

				// -----------------------��Ŀ���� begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(xmlx);
				html.append("</td>");
				// -----------------------��Ŀ���� end---------------------------

				// -----------------------��������� begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				boolean default_shgw = true;
				
				if (shgwList != null && shgwList.size() > 0) {
					for (int j = 0; j < shgwList.size(); j++) {
						if (xmdm.equalsIgnoreCase(shgwList.get(j).get("xmdm"))) {
							if(!default_shgw){
								html.append("-->");
							}
							html.append(shgwList.get(j).get("gwmc"));
							
							boolean default_xsh = true;
							
							if (xshxxList != null && xshxxList.size() > 0) {
								for (int k = 0; k < xshxxList.size(); k++) {
									if (xmdm.equalsIgnoreCase(xshxxList.get(k)
											.get("xmdm"))) {
										if (shgwList.get(j).get("gwdm")
												.equalsIgnoreCase(
														xshxxList.get(k).get(
																"gwdm"))) {
											html.append("(");
											html.append(xshxxList.get(k).get(
													"xshrs"));
											html.append(")");
											
											default_xsh = false;
										}
									}
								}
							}
							
							if(default_xsh){
								html.append("(0)");
							}
							
							default_shgw = false;
						}
					}
				}

				html.append("</td>");
				// -----------------------��������� end---------------------------
				
				// -----------------------��������� begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				default_shgw = true;
				
				if (shgwList != null && shgwList.size() > 0) {
					for (int j = 0; j < shgwList.size(); j++) {
						if (xmdm.equalsIgnoreCase(shgwList.get(j).get("xmdm"))) {
							if (!default_shgw) {
								html.append("-->");
							}
							html.append(shgwList.get(j).get("gwmc"));

							boolean default_ysh = true;

							if (yshxxList != null && yshxxList.size() > 0) {
								for (int k = 0; k < yshxxList.size(); k++) {
									if (xmdm.equalsIgnoreCase(yshxxList.get(k)
											.get("xmdm"))) {
										if (shgwList.get(j).get("gwdm")
												.equalsIgnoreCase(
														yshxxList.get(k).get(
																"gwdm"))) {
											html.append("(");
											html.append("<a href=\"#\" ");
											html.append("onclick=\"showWdshDiv('"+xmdm+"','"+shgwList.get(j).get("gwdm")+"');return false;\"");
											html.append(">");
											html.append("<font color=\"blue\">");
											html.append("<U>");
											html.append(yshxxList.get(k).get(
													"yshrs"));
											html.append("</U>");
											html.append("</font>");
											html.append("</a>");
											html.append(")");

											default_ysh = false;
										}
									}
								}
							}

							if (default_ysh) {
								html.append("(0)");
							}

							default_shgw = false;
						}
					}
				}

				html.append("</td>");
				// -----------------------��������� end---------------------------
				
				// -----------------------����begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(" <a href=\"#\" onclick=\"if(checkItsDis(this)){showSpgw('"
								+ xmdm
								+ "');};return false;\" class=\"a_lssh\" disabled=\"true\"><font color=\"blue\">���</font></a>");

				html.append("</td>");
				// -----------------------����end---------------------------

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * �����ҵ����Div
	 * 
	 * @date 2012.02.04
	 * @author ΰ�����
	 */
	public void createWdshDiv(PjpyWdpjModel model, User user,
			HttpServletResponse response) throws Exception {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��λ����
		String gwid = model.getGwid();
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		
		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"15%\"><div align='left'>ѧ��</div></th>");	
		html.append("<th width=\"15%\"><div align='left'>����</div></th>");
		html.append("<th width=\"30%\"><div align='left'>�༶</div></th>");
		html.append("<th width=\"20%\"><div align='left'>���ʱ��</div></th>");
		html.append("<th width=\"20%\"><div align='left'>���״̬</div></th>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");
		
		List<HashMap<String,String>> list = dao.getWdshList(xmdm, gwid, user);
		
		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table class=\"formlist\">");
		
		for(int i=0;i<list.size();i++){
			html.append("<tr>");
			html.append("<td width=\"15%\">" + list.get(i).get("xh") + "</td>");
			html.append("<td width=\"15%\">" + list.get(i).get("xm") + "</td>");
			html.append("<td width=\"30%\">" + list.get(i).get("bjmc") + "</td>");
			html.append("<td width=\"20%\">" + list.get(i).get("shsj") + "</td>");
			html.append("<td width=\"20%\">" + list.get(i).get("shzt") + "</td>");
			html.append("</tr>");
		}
		
		if(8-list.size()>0){
			for(int i=0;i<8-list.size();i++){
				html.append("<tr>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("<td width=\"\">&nbsp;</td>");
				html.append("</tr>");
			}
		}
		
		html.append("</table>");
		html.append("</div>");
		
		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		
		html.append("</div>");
		
		response.getWriter().print(html.toString());

	}
}
