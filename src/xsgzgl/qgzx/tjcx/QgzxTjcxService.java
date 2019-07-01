package xsgzgl.qgzx.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

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
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxTjcxService extends BasicService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * ��ѯ�¶ȳ�𷢷�ͳ����ҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTrByYdcjfftjCx() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ�¶ȳ�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getYdcjfftjCx(QgzxTjcxForm myForm)
			throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getYdcjfftjCx(myForm);
	}

	/**
	 * ƴ���¶ȳ�𷢷�ͳ����ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByYdcjfftjCx(QgzxTjcxForm myForm, SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		String yf = "";
		if(myForm.getSearchModel().getSearch_tj_yf()==null){
			yf = "����";
		}else{
			String[] yfjh = myForm.getSearchModel().getSearch_tj_yf();
			for(String dqyf : yfjh){
				yf +=dqyf+" ";
			}
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+yf+"�·ݳ�𷢷�ͳ��</td></tr>");
			html.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��������</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�·�</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ڹ�����</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ܳ��<Ԫ></td></thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
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
	 * �����¶ȳ�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expYdcjfftj(ServletOutputStream os, QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		String yf = "";
		if(myForm.getSearchModel().getSearch_tj_yf()==null){
			yf = "����";
		}else{
			String[] yfjh = myForm.getSearchModel().getSearch_tj_yf();
			for(String dqyf : yfjh){
				yf +=dqyf+" ";
			}
		}
		String title = yf + "�·ݳ�𷢷�ͳ��";
		// �̶���
		String[] gdName = new String[] { "��������", "�·�", "�ڹ�����", "�ܳ��" };
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = dao.getYdcjfftjCx(myForm);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 4, 800);// ����
		
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

	/**
	 * ��ѯ��ȳ�𷢷�ͳ����ҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTrByNdcjfftjCx() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ��ȳ�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getNdcjfftjCx(QgzxTjcxForm myForm)
			throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getNdcjfftjCx(myForm);
	}

	/**
	 * ƴ����ȳ�𷢷�ͳ����ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByNdcjfftjCx(QgzxTjcxForm myForm, SearchRsModel rsModel, ArrayList<String[]> rsArrList, ArrayList<String[]> rsArrListAll, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		String nd = "";
		if(myForm.getSearchModel().getSearch_tj_nd()==null){
			nd = "����";
		}else{
			String[] ndjh = myForm.getSearchModel().getSearch_tj_nd();
			for(String dqnd : ndjh){
				nd +=dqnd+" ";
			}
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<td align=\"middle\" colspan=\"40\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+nd+"��ȸ����˵�λ���귢�Ż��ܱ�</td></tr>");
			html.append("<thead align=\"center\"><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˵�λ</td>");
			for (int i = 1; i < 13; i++) {
				html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">" + i + "��</td>");
			}
			html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td></tr>");
			for (int i = 0; i < 13; i++) {
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��λ��</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�˴�</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���Ž��<Ԫ></td>");
			}
			html.append("</thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
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
			html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td>");
			if (rsArrListAll != null && rsArrListAll.size() > 0) {
				for (int i = 0; i < rsArrListAll.size(); i++) {
					String[] rs = rsArrListAll.get(i);
					for (int j = 0; j < rs.length; j++) {
						html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">" + replaceHtml(rs[j]) + "</td>");
					}
				}
			}
		}
		return html.toString();
	}
	
	/**
	 * ������ȳ�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expNdcjfftj(ServletOutputStream os, QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		String nd = "";
		if(myForm.getSearchModel().getSearch_tj_nd()==null){
			nd = "����";
		}else{
			String[] ndjh = myForm.getSearchModel().getSearch_tj_nd();
			for(String dqnd : ndjh){
				nd +=dqnd+" ";
			}
		}
		String title = nd + "��ȸ����˵�λ���귢�Ż��ܱ�";
		ArrayList<String[]> gdlist = dao.getNdcjfftjCx(myForm);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 40, 800);// ����
		
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0,1,"���˵�λ",wcf2));
		for(int i=1;i<=13;i++){
			if(i==13){
				ws.mergeCells((i-1)*3+1,1,(i-1)*3+3,1);
				ws.addCell(new Label((i-1)*3+1,1,"�ϼ�",wcf2));
			}else{
				ws.mergeCells((i-1)*3+1,1,(i-1)*3+3,1);
				ws.addCell(new Label((i-1)*3+1,1,i+"��",wcf2));
			}
		}
		for(int i=1;i<=13;i++){
			ws.addCell(new Label((i-1)*3+1,2,"��λ��",wcf2));
			ws.addCell(new Label((i-1)*3+2,2,"�˴�",wcf2));
			ws.addCell(new Label((i-1)*3+3,2,"���Ž��",wcf2));
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 3, info[j], wcf2));
					}
				}
			}
			ws.addCell(new Label(0,gdlist.size()+3,"�ϼ�",wcf2));
			ArrayList<String[]> gdlistAll = dao.getNdcjfftjCxAll(myForm);
			String[] infoAll = gdlistAll.get(0);
			for(int i=1;i<=infoAll.length;i++){
				ws.addCell(new Label(i,gdlist.size()+3,infoAll[i-1],wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��ѯ���ų�𷢷�ͳ����ҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTrByBmcjfftjCx() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ���ų�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBmcjfftjCx(QgzxTjcxForm myForm)
			throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getBmcjfftjCx(myForm);
	}

	/**
	 * ��ѯ���ų�𷢷�ͳ����ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByBmcjfftjCx(QgzxTjcxForm myForm, SearchRsModel rsModel,ArrayList<String[]> rsArrList, User user) {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		String yf = "";
		String bm = "";
		if(myForm.getSearchModel().getSearch_tj_yf()==null){
			yf = "����";
		}else{
			String[] yfjh = myForm.getSearchModel().getSearch_tj_yf();
			for(String dqyf : yfjh){
				yf +=dqyf+" ";
			}
		}
		if(myForm.getSearchModel().getSearch_tj_bm()==null){
			bm = "����";
		}else{
			String[] bmjh = myForm.getSearchModel().getSearch_tj_bm();
			for(String dqbm : bmjh){
				dqbm = dao.getBmmc(dqbm).get("bmmc");
				bm +=dqbm+" ";
			}
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<td align=\"middle\" colspan=\"7\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+bm+"����"+yf+"�·ݳ�𷢷�ͳ��</td></tr>");
			html.append("<thead align=\"center\"><td align=\"left\" colspan=\"4\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�������ƣ�"+bm+"</td><td align=\"left\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�·ݣ�"+yf+"</td></tr>");
			//html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"></td></tr>");
			html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧ��</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�꼶</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+Base.YXPZXY_KEY+"</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">רҵ</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�༶</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���<Ԫ></td></thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
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
	 * �������ų�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expBmcjfftj(ServletOutputStream os, QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String yf = "";
		String bm = "";
		if(myForm.getSearchModel().getSearch_tj_yf()==null){
			yf = "����";
		}else{
			String[] yfjh = myForm.getSearchModel().getSearch_tj_yf();
			for(String dqyf : yfjh){
				yf +=dqyf+" ";
			}
		}
		if(myForm.getSearchModel().getSearch_tj_bm()==null){
			bm = "����";
		}else{
			String[] bmjh = myForm.getSearchModel().getSearch_tj_bm();
			for(String dqbm : bmjh){
				dqbm = dao.getBmmc(dqbm).get("bmmc");
				bm +=dqbm+" ";
			}
		}
		String title = bm + "����" +yf + "�·ݳ�𷢷�ͳ��";
		// �̶���
		String[] gdName = new String[] { "ѧ��", "����", "�꼶", "ѧԺ", "רҵ", "�༶", "���" };
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = dao.getBmcjfftjCx(myForm);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 7, 800);// ����
		
		wcf2 = new WritableCellFormat();
		ws.mergeCells(0, 1, 3, 1);//�ϲ�ͬһ�е��ĸ���Ԫ��
		ws.addCell(new Label(0,1,"����:"+bm,wcf2));
		
		ws.mergeCells(4, 1, 6, 1);//�ϲ�ͬһ�е�������Ԫ��
		ws.addCell(new Label(4,1,"�·�:"+yf,wcf2));
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 3, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length-2; j++) {
						ws.addCell(new Label(j, i + 4, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��ѯ���˳�𷢷�ͳ����ҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTrByGrcjfftjCx() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ���˳�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGrcjfftjCx(QgzxTjcxForm myForm)
			throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getGrcjfftjCx(myForm);
	}

	/**
	 * ��ѯ���˳�𷢷�ͳ����ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByGrcjfftjCx(QgzxTjcxForm myForm,SearchRsModel rsModel, ArrayList<String[]> rsArrList, ArrayList<String[]> rsArrListAll, User user) {
		StringBuilder html = new StringBuilder();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String ie = rsModel.getIe();
		String nd = "";
		if(myForm.getSearchModel().getSearch_tj_nd()==null){
			nd = "����";
		}else{
			String[] ndjh = myForm.getSearchModel().getSearch_tj_nd();
			for(String dqnd : ndjh){
				nd +=dqnd+" ";
			}
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<td align=\"middle\" colspan=\"16\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\"> "+nd+"���ѧ��ÿ�±��귢����ϸ����</td></tr>");
			html.append("<thead align=\"center\"><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧ��</td>");
			html.append("<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td>");
			html.append("<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+Base.YXPZXY_KEY+"</td>");
			html.append("<td align=\"middle\" colspan=\"12\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��ϸͳ��<Ԫ></td>");
			html.append("<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�<Ԫ></td></tr>");
			for (int i = 1; i < 13; i++) {
				html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">" + i + "��</td>");
			}
			html.append("</thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
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
			html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td>");
			if (rsArrListAll != null && rsArrListAll.size() > 0) {
				for (int i = 0; i < rsArrListAll.size(); i++) {
					String[] rs = rsArrListAll.get(i);
					for (int j = 0; j < rs.length; j++) {
						html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">" + replaceHtml(rs[j]) + "</td>");
					}
				}
			}
		}
		return html.toString();
	}

	/**
	 * �������˳�𷢷�ͳ����ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expGrcjfftj(ServletOutputStream os, QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		String nd = "";
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if(myForm.getSearchModel().getSearch_tj_nd()==null){
			nd = "����";
		}else{
			String[] ndjh = myForm.getSearchModel().getSearch_tj_nd();
			for(String dqnd : ndjh){
				nd +=dqnd+" ";
			}
		}
		String title = nd + "���ѧ��ÿ�±��귢����ϸ����";
		ArrayList<String[]> gdlist = dao.getGrcjfftjCx(myForm);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 16, 800);// ����
		
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0,1,"ѧ��",wcf2));
		ws.mergeCells(1, 1, 1, 2);
		ws.addCell(new Label(1,1,"����",wcf2));
		ws.mergeCells(2, 1, 2, 2);
		ws.addCell(new Label(2,1,Base.YXPZXY_KEY,wcf2));
		ws.mergeCells(3, 1, 14, 1);
		ws.addCell(new Label(3,1,"��ϸͳ��",wcf2));
		for(int i=1;i<13;i++){
			ws.addCell(new Label(i+2,2,i+"��",wcf2));
		}
		ws.mergeCells(15, 1, 15, 2);
		ws.addCell(new Label(15,1,"�ϼ�",wcf2));
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 3, info[j], wcf2));
					}
				}
			}
			ws.mergeCells(0, gdlist.size()+3, 2, gdlist.size()+3);
			ws.addCell(new Label(0,gdlist.size()+3,"�ϼ�",wcf2));
			ArrayList<String[]> gdlistAll = dao.getGrcjfftjCxAll(myForm);
			String[] infoAll = gdlistAll.get(0);
			for(int i=1;i<=13;i++){
				ws.addCell(new Label(i+2,gdlist.size()+3,infoAll[i-1],wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ѯ���˳�𷢷�ͳ����ҳ�����ݺϼ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGrcjfftjCxAll(QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getGrcjfftjCxAll(myForm);
	}

	/**
	 * ��ѯ��ȳ�𷢷�ͳ����ҳ�����ݺϼ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getNdcjfftjCxAll(QgzxTjcxForm myForm) throws Exception {
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getNdcjfftjCxAll(myForm);
	}
	
	public String getDate(){
		QgzxTjcxDAO dao = new QgzxTjcxDAO();
		return dao.getNowTime("YYYYMMDD");
	}
}