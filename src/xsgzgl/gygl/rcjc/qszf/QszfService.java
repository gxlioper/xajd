package xsgzgl.gygl.rcjc.qszf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-9 ����14:19:22
 * </p>
 */

public class QszfService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * �����߷���ҳ����Ϣͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xy", "bzrxm", "bj", "xsszld", "xsszqs", "xqsj" };
		String[] cn = new String[] { "", Base.YXPZXY_KEY, "����������", "�༶", "ѧ������¥��", "ѧ����������", "�߷�ʱ��" };
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ�߷���ʦҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr2() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "zgh", "xm" };
		String[] cn = new String[] { "ְ����", "�߷���ʦ����" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * �����߷���Ϣ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getQszfCx(QszfForm myForm) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.getQszfCx(myForm);
	}

	/**
	 * ��ȡ���������߷���ʦ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getZfls(QszfForm myForm) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.getZfls(myForm);
	}

	/**
	 * ���������߷���ҳ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
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
	 * ����������ʦ��ѯҳ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML2(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"sendLsgh();\" style=\"cursor:hand\">");
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append("<input type=\"hidden\" value=\""+rs[j]+"\"/>");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * ������ѯ-���߷���ʦְ���Ż�ȡ����༶
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFzbjForZflsgh(String zflsgh) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { zflsgh };
		String[] outputValue = new String[] { "bjmc", "bjdm" };
		return dao.getFzbjForZflsgh(inputValue, outputValue);
	}

	/**
	 * ������ѯ-���߷���ʦְ���Ż�ȡ�߷���ʦ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZflsxmForZflsgh(QszfForm qszfForm, String zflsgh) {
		QszfDAO dao = new QszfDAO();
		return dao.getZflsxmForZflsgh(qszfForm, zflsgh);
	}

	/**
	 * ������ѯ-�ɸ���༶��ȡ¥��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLdForFzbj(String fzbj) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { fzbj };
		String[] outputValue = new String[] { "ldmc", "lddm" };
		return dao.getLdForFzbj(inputValue, outputValue);
	}

	/**
	 * ������ѯ-��¥���Ż�ȡ���Һ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQshForLd(String fzbj, String xsszldh) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { fzbj, xsszldh };
		String[] outputValue = new String[] { "xsszqsh" };
		return dao.getQshForLd(inputValue, outputValue);
	}

	/**
	 * �����߷���Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfBc(QszfForm myForm, String username) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.qszfBc(myForm, username);
	}

	/**
	 * �����߷���Ϣɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfSc(QszfForm myForm, String[] valArr, String username) throws SQLException {
		QszfDAO dao = new QszfDAO();
		return dao.qszfSc(myForm, valArr, username);
	}

	/**
	 * ��ȡѧԺ�����򼯺�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getXyList(HttpServletRequest request) {
		QszfDAO dao = new QszfDAO();
		return dao.getXyList(request);
	}

	/**
	 * �鿴��ȡ�����߷���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQszfMap(QszfForm qszfForm) {
		QszfDAO dao = new QszfDAO();
		return dao.getQszfMap(qszfForm);
	}

	/**
	 * �����߷���Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfXg(QszfForm myForm, String username) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.qszfXg(myForm, username);
	}

	/**
	 * �߷ô���ͳ�Ƶ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expXqtjb(QszfForm model, ServletOutputStream os, String xn, String yf, String xymc) throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		QszfDAO dao = new QszfDAO();
		String title = "ѧ�����쵼���������߷ô���ͳ��(" + xn + "��" + yf + "��)";
		// �̶���
		String[] gdName = new String[] { Base.YXPZXY_KEY, "������", "�༶", "ѧ����������", "���޵Ĵ���" };
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = dao.expXqtjbList(model, xn, yf, xymc);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 5, 800);// ����
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}