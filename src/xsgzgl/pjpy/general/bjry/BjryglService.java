package xsgzgl.pjpy.general.bjry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

public class BjryglService extends BasicService{

	/**
	 * ��ȡ�༶����ͷ��
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "pjzq", "nj", "xy", "zy", "bj", "hjry", "hjsj" };
		String[] cn = new String[] { "", "��������","�꼶","Ժϵ","רҵ","�༶","�������","���ʱ��" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * �����༶������ҳ����Ϣ
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * ��ȡ�༶�����������
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getBjryglCx(BjryglForm myForm) throws Exception {
		BjryglDAO dao = new BjryglDAO();
		return dao.getBjryglCx(myForm);
	}

	/**
	 * ��ȡ�������������
	 * @param request
	 * @return
	 */
	public Object getHdryList(HttpServletRequest request) {
		BjryglDAO dao = new BjryglDAO();
		return dao.getHdryList(request);
	}

	/**
	 * ��ȡ�༶��Ϣͷ��
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr2() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "","nj", "xy", "zy", "bj" };
		String[] cn = new String[] { "","�꼶","Ժϵ","רҵ","�༶" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ȡ�༶����
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjmc(BjryglForm myForm) throws Exception {
		BjryglDAO dao = new BjryglDAO();
		return dao.getBjmc(myForm);
	}

	/**
	 * ��ȡ�༶��Ϣҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * �鿴�༶������Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getBjryglMap(BjryglForm myForm) {
		BjryglDAO dao = new BjryglDAO();
		return dao.getBjryglMap(myForm);
	}

	/**
	 * �༶�����޸ı���
	 * @param myForm
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglXgBc(BjryglForm myForm, String username) throws Exception {
		BjryglDAO dao = new BjryglDAO();
		return dao.bjryglXgBc(myForm);
	}

	/**
	 * �༶����ɾ��
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglSc(BjryglForm myForm, String[] valArr, String username) throws Exception {
		BjryglDAO dao = new BjryglDAO();
		return dao.bjryglSc(myForm, valArr, username);
	}

	/**
	 * ��ȡ�༶��Ϣ
	 * @param myForm
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjxx(BjryglForm myForm, String bjdm) {
		BjryglDAO dao = new BjryglDAO();
		return dao.getBjxx(myForm, bjdm);
	}

	/**
	 * �༶�������ӱ���
	 * @param myForm
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglZjBc(BjryglForm myForm, String username) throws Exception {
		BjryglDAO dao = new BjryglDAO();
		return dao.bjryglZjBc(myForm);
	}
}
