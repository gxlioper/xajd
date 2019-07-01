package xsgzgl.customForm.demo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_DEMO_Service��
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

public class DemoFormService extends CommService {

	DemoFormDAO dao = new DemoFormDAO();

	// ===============��ԃ�Y�� begin=====================

	/**
	 * �@�ñ��^���Զ��x��Ρ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCustomFormTop(DemoFormModel model,
			User user) {

		DAO dao = DAO.getInstance();
		String[] en = new String[] { "pk", "ssmk", "bdmc", "sjb", "cz" };
		String[] cn = new String[] { "", "����ģ�K", "������Q", "������", "����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ԃ�������Զ��x��Ρ�
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> ggetCustomFormList(DemoFormForm myForm,
			DemoFormModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] value = new String[] { "C4A7E0884B916956E040007F010068C3",
				"�W����Ϣ", "��У���S�o", "xg_xsxx_xsxxb" };
		list.add(value);

		value = new String[] { "002", "�u���u��", "���W����Ո",

		"XG_PJPY_PJXMSQB" };
		list.add(value);

		return list;
	}

	/**
	 * ����HTML���Զ��x��Ρ�
	 * 
	 * @author ΰ�����
	 */
	public String createCustomFormHTML(SearchRsModel rsModel,
			DemoFormModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String form_id = rs[0];// FormID
				String ssb = rs[3];// ���ٱ�

				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" ");
				html.append("name=\"primarykey_checkVal\" ");
				html.append("value=\"" + form_id + "\"/>");
				html.append("</td>");

				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					html.append("style=\"\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}

				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				html.append("style=\"\" ");
				html.append(">");
				html.append("<a href=\"#\" ");
				html.append("onclick=\"xszdSetup('" + form_id + "','" + ssb
						+ "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("�ֶ��O��");
				html.append("</font>");
				html.append("</a>");

				html.append("  ");

				html.append("<a href=\"#\" ");
				html.append("onclick=\"jgcxSetup('" + form_id
						+ "');return false;\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append("��ԃ�O��");
				html.append("</font>");
				html.append("</a>");

				html.append("</td>");

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	// ===============��ԃ�Y�� end=====================

	// ===============��β��� begin=====================

	/**
	 * �����ֶ��б��a���У�
	 * 
	 * @param ssb
	 *            ���ٱ�
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZdList(String ssb) {

		// ����Д�
		int max_rows = 20;
		// ̎����ɺ���б�
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,

		String>>();
		// ��ԃ�Զ��x���ñ�������ֶ�
		List<HashMap<String, String>> zdList = getTableZdList(ssb);

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				list.add(zdList.get(i));
			}

			// ���M����Д����a����
			if (zdList.size() < max_rows) {
				for (int i = 0; i < max_rows - zdList.size(); i++) {
					list.add(new HashMap<String, String>());
				}
			}
		}

		return list;
	}

	/**
	 * ��ԃ�Զ��x���ñ�������ֶ�
	 * 
	 * @param ssb
	 *            ���ٱ�
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableZdList(String ssb) {

		// ���в�ԃ����
		List<HashMap<String, String>> list = dao.getTableZdList(ssb);

		return list;
	}

	/**
	 * ���������ӵı��
	 * 
	 * @author ΰ�����
	 */
	public boolean saveTable(DemoFormModel model, User user) {

		Random random = new Random();
		boolean flag = false;

		String table_id = String.valueOf(random.nextLong());
		String form_id = model.getForm_id();// ���ID
		String title = model.getTitle();// ���}
		String row_num = model.getRow_num();// �Д�
		String xssx = model.getXssx();// �@ʾ���

		model.setTable_id(table_id);

		try {
			flag = dao.insertCustomTable(table_id, form_id, title, row_num,
					xssx, user);

			if (flag) {
				saveContent(model, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����΃���
	 * 
	 * @author ΰ�����
	 */
	public boolean saveContent(DemoFormModel model, User user) {

		boolean flag = false;

		String table_id = model.getTable_id();// ��ID

		int num = Integer.parseInt(model.getRow_num()) * 4;
		String[] row = new String[num];
		String[] column = new String[num];

		String row_num = "1";
		String column_num = "1";

		for (int i = 0; i < num; i++) {
			row[i] = row_num;
			column[i] = column_num;

			column_num = String.valueOf(Integer.parseInt(column_num) + 1);

			if ((i + 1) % 4 == 0) {
				row_num = String.valueOf(Integer.parseInt(row_num) + 1);
				column_num = "1";
			}
		}

		try {
			flag = dao.insertCustomContent(table_id, row, column, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �@ʾ�Զ��x���
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void showCustomForm(DemoFormModel model, User user,
			HttpServletResponse response) throws IOException {

		// FormID
		String form_id = model.getForm_id();
		// TABLE List
		List<HashMap<String, String>> tableList = dao.getTableList(form_id);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		if (tableList != null && tableList.size() > 0) {
			for (int i = 0; i < tableList.size(); i++) {
				HashMap<String, String> tabMap = tableList.get(i);
				// TableID
				String table_id = tabMap.get("table_id");
				// ���}
				String title = tabMap.get("title");
				// �Д�
				int row_num = Integer.parseInt(tabMap.get("row_num"));
				
				html.append("<table width=\"100%\" class=\"formlist\">");
				//---------------���^ begin-------------------
				html.append("<thead onclick=\"\" style=\"\">");
				html.append("<tr>");
				html.append("<td colspan=\"4\">");
				html.append("<span>");
				html.append(title);
				html.append("</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				//---------------���^ end----------------------
				
				// ���� List
				List<HashMap<String, String>> contentList = dao
						.getContentList(table_id);
				
				// ---------------���� begin-------------------
				html.append("<tbody>");
				for (int j = 1; j <= row_num; j++) {
					html.append("<tr>");
					
					// ---------------��Ԫ�� begin-------------------
					if (contentList != null && contentList.size() > 0) {
						for (int k = 0; k < contentList.size(); k++) {
							HashMap<String, String> conMap = contentList.get(k);
							// ��
							String row = conMap.get("row_num");
							// ��
							String column = conMap.get("column_num");
							
							if (row.equalsIgnoreCase(String.valueOf(j))) {
								
								//TdID
								String td_id = table_id + "_" + row + "_"
										+ column;
								html.append("<td name=\"td_content\" ");
								html.append("id=\"" + td_id + "\"");
								html.append("style=\"width:25%;height:25px;cursor:hand\" ");
								html.append("onclick=\"clickTd(this)\" ");
								html.append(">");
								html.append("&nbsp;");
								html.append("</td>");
							}
						}
					}
					// ---------------��Ԫ�� end-------------------
					
					html.append("</tr>");
				}

				html.append("</tbody>");
				// ---------------���� begin-------------------
				
				html.append("</table>");
			}
		}
		

		response.getWriter().print(html.toString());
	}

	/**
	 * �����x�еČ���
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void createClickedObj(DemoFormModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		//TdID
		String[] td_id = model.getTd_id();
		
		if (td_id != null && td_id.length > 0) {
			for (int i = 0; i < td_id.length; i++) {
				
				// ��ID
				String table_id = td_id[i].split("_")[0];
				// ��
				String row = td_id[i].split("_")[1];
				// ��
				String column = td_id[i].split("_")[2];

				html.append("<input type=\"text\" ");
				html.append("name=\"table_obj_name\" ");
				html.append("id=\"table_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + table_id + "\" ");
				html.append("/>");

				html.append("<input type=\"text\" ");
				html.append("name=\"row_obj_name\" ");
				html.append("id=\"row_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + row + "\" ");
				html.append("/>");

				html.append("<input type=\"text\" ");
				html.append("name=\"column_obj_name\" ");
				html.append("id=\"column_obj_name_" + td_id[i] + "\" ");
				html.append("value=\"" + column + "\" ");
				html.append("/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ����ϲ���Ԫ��
	 * 
	 * @author ΰ�����
	 */
	public boolean saveCoalition(DemoFormModel model, User user) {

		boolean flag = false;

		try {
			//flag = dao.updateCustomContent(table_id, row, column, rowspan, colspan, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	// ===============��β��� end=====================

}
