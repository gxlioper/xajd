package xsgzgl.xtwh.general.power;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.CommService;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ��_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XtwhPowerService extends CommService {

	XtwhPowerDAO dao = new XtwhPowerDAO();

	/**
	 * ������ϵͳһ���˵��б�Div
	 * 
	 * @author ΰ�����
	 */
	public void createFirstGnmkDiv(XtwhPowerModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		String zdm = model.getZdm();

		List<HashMap<String, String>> list = dao.getFirstGnmkList(zdm, user);

		StringBuilder html = new StringBuilder();

		html.append("<label>��ϵͳ</label>");
		html.append("<select name=\"gnmkmc\"");
		html.append("onchange=\"createSecondGnmkDiv();\" id=\"powerTop\">");
		if (list != null && list.size() > 0) {
			html.append("<option value=\"\">");
			html.append("----ȫ��----");
			html.append("</option>");
			for (int i = 0; i < list.size(); i++) {
				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				html.append("<option ");
				html.append("value=\"" + dm + "\" ");
				html.append(">");
				html.append(dm + " | " + mc);
				html.append("</option>");
			}
		}
		html.append("</select>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ������ϵͳ�����˵��б�Div
	 * 
	 * @author ΰ�����
	 */
	public void createSecondGnmkDiv(XtwhPowerModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		// ����ģ�����
		String gnmkdm = model.getGnmkdm();
		// �����
		String zdm = model.getZdm();
		// �û���
		String yhm = model.getYhm();
		
		List<HashMap<String, String>> list = dao.getSecondGnmkList(gnmkdm, zdm,
				yhm, user);

		StringBuilder html = new StringBuilder();

		html.append("<select ");
		html.append("onmouseover=\"null\" ");
		html.append("name=\"gnmkdm\"  ");
		html.append("style=\"width:100%\" ");
		html.append("size=\"23\"  ");
		html.append("name=\"powerSub\" ");
		html.append("id=\"powerSub\"> ");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				html.append("<option ");
				html.append("value=\"" + dm + "\" ");
				html.append(">");
				if (dm.length() == 3) {
					html.append(dm + "____ | " + mc);
				} else if (dm.length() == 5) {
					html.append(dm + "__ | " + mc);
				} else {
					html.append(dm + " | " + mc);
				}

				html.append("</option>");
			}
		}
		html.append("</select> ");

		response.getWriter().print(html.toString());
	}

	/**
	 * �����û���˵��б�Div
	 * 
	 * @author ΰ�����
	 */
	public void createYhzGnmkDiv(XtwhPowerModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		// �����
		String zdm = model.getZdm();

		List<HashMap<String, String>> list = dao.getYhzGnmkList(zdm, user);

		StringBuilder html = new StringBuilder();

		html.append("<select name=\"zdm\" ");
		html.append("size=\"20\" ");
		html.append("id=\"groupPower\" ");
		html.append("class=\"selectlist\">");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");
				String dxq = list.get(i).get("dxq");
				String dxqmc = list.get(i).get("dxqmc");
				
				html.append("<option ");
				html.append("value=\"" + dxq + dm + "\" ");
				html.append(">");

				if (dm.length() == 3) {
					html.append(dm + "____ | " + dxqmc + " | " + mc);
				} else if (dm.length() == 5) {
					html.append(dm + "__ | " + dxqmc + " | " + mc);
				} else {
					html.append(dm + " | " + dxqmc + " | " + mc);
				}

				html.append("</option>");
			}
		}
		html.append("</select>");
		response.getWriter().print(html.toString());
	}
	
	/**
	 * �����û��˵��б�Div
	 * 
	 * @author ΰ�����
	 */
	public void createYhGnmkDiv(XtwhPowerModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		// �����
		String yhm = model.getYhm();

		List<HashMap<String, String>> list = dao.getYhGnmkList(yhm, user);

		StringBuilder html = new StringBuilder();

		html.append("<select name=\"userPower\" ");
		html.append("class=\"selectlist\" ");
		html.append("size=\"10\" id=\"groupPower\"> ");
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");
				String dxq = list.get(i).get("dxq");
				String dxqmc = list.get(i).get("dxqmc");
				
				html.append("<option ");
				html.append("value=\"" + dxq + dm + "\" ");
				html.append(">");

				if (dm.length() == 3) {
					html.append(dm + "____ | " + dxqmc + " | " + mc);
				} else if (dm.length() == 5) {
					html.append(dm + "__ | " + dxqmc + " | " + mc);
				} else {
					html.append(dm + " | " + dxqmc + " | " + mc);
				}

				html.append("</option>");
			}
		}
		html.append("</select>");
		response.getWriter().print(html.toString());
	}
	
	/**
	 * �����û�Div
	 * 
	 * @author ΰ�����
	 */
	public void createUserDiv(XtwhPowerModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		// �û���
		String yhm = model.getYhm();
		// ����
		String xm = model.getXm();
		// �����
		String zdm = model.getZdm();
		// ���ڲ���
		String szbm = model.getSzbm();
		
		List<HashMap<String, String>> list = dao.getUserList(yhm, xm, zdm, szbm, user);

		StringBuilder html = new StringBuilder();

		html.append("<select name=\"userName\" onchange=\"createYhGnmkDiv()\" ");
		html.append("size=\"10\" id=\"topGroup\" class=\"selectlist\"> ");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				html.append("<option value=\"" + list.get(i).get("yhm") + "\">");
				html.append(list.get(i).get("xm"));
				html.append("</option>");
			}
		}
		html.append("</select>");

		response.getWriter().print(html.toString());
	}
}