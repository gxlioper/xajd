package xsgzgl.gygl.hzsf;

import java.sql.SQLException;
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
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;
/**
 * ��Ԣ����-����ʦ��-¥�����˹���
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglService extends BasicService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	LdkhglDao ldkhglDao = new LdkhglDao();

	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "yf", "khzgf", "zgfld", "khzdf", "zdfld" };
		String[] cn = new String[] { "", "�����·�", "������߷�", "��߷�¥��", "������ͷ�", "��ͷ�¥��" };
		if("khcjwh".equalsIgnoreCase(type)){
			en = new String[] { "", "ldmc", "lh", "zz", "xc", "hqjc", "dxjc", "sjjc", "lzfk", "xyzg", "zf", "pm" };
			cn = new String[] { "", "¥��", "����", "��֯", "����", "���ڼ��","���ͼ��","������","¥������","ѧԺ����","�ܷ�","����"};
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ¥�����˹���
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> ldkhgl(LdkhglForm model) {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String xn = searchModel.getSearch_tj_xn()[0];
		String[] yf = scyfList(xn);
		List<HashMap<String,String>> ldkhcj = ldkhglDao.ldkhgl(model);
		ArrayList<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < yf.length; i++){
			String[] arr = new String[6];
			if(ldkhcj.size()==0){
				arr[0] = yf[i];
				arr[1] = yf[i];
				arr[2] = "";
				arr[3] = "";
				arr[4] = "";
				arr[5] = "";
			}
			for(int j = 0; j < ldkhcj.size();j++){
				HashMap<String,String> ldkh = ldkhcj.get(j);
				if(yf[i].equalsIgnoreCase(ldkh.get("yf"))){
					arr[0] = yf[i];
					arr[1] = yf[i];
					arr[2] = ldkh.get("khzgf");
					arr[3] = ldkh.get("zgfld");
					arr[4] = ldkh.get("khzdf");
					arr[5] = ldkh.get("zdfld");
					break;
				}else if(j ==ldkhcj.size()-1){
					arr[0] = yf[i];
					arr[1] = yf[i];
					arr[2] = "";
					arr[3] = "";
					arr[4] = "";
					arr[5] = "";
				}
			}
			list.add(arr);
		}
		return list;
	}

	/**
	 * �����·��б�
	 * @param xn
	 * @return
	 */
	private String[] scyfList(String xn) {
		String qxn = xn.substring(0, 4);
		String hxn = xn.substring(5, 9);
		String[] yf = new String[10];
		for(int i = 0; i < 4; i++){
			String ss = "0"+(i+9);
			yf[i] = qxn+"-"+ss.substring(ss.length()-2, ss.length());
		}
		for(int i = 0; i < 6; i++){
			String ss = "0"+(i+1);
			yf[i+4] = hxn+"-"+ss.substring(ss.length()-2, ss.length());;
		}
		return yf;
	}
	
	
	/**
	 * ���˳ɼ�ά��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> khcjwh(LdkhglForm model) throws Exception{
		return ldkhglDao.khcjwh(model);
	}
	

	/**
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
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
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
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
	 * ����ҳ�濼�˳ɼ�
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByKhcj(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			html.append("<thead align=\"center\"><tr><td align=\"center\" nowrap=\"nowrap\" rowspan=\"3\" height=\"28.5px\"></td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" rowspan=\"3\" height=\"28.5px\">¥��</td>" +
					"<td align=\"center\" nowrap=\"nowrap\" colspan=\"8\" height=\"28.5px\">��������</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" rowspan=\"3\" height=\"28.5px\">�ܷ�</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" rowspan=\"3\" height=\"28.5px\">����</td></tr>" +
					"<tr><td align=\"center\" nowrap=\"nowrap\" colspan=\"3\" height=\"28.5px\">�ճ�����(30��)</td>" +
					"<td align=\"center\" nowrap=\"nowrap\" colspan=\"3\" height=\"28.5px\">���Ҽ��(60��)</td>" +
					"<td align=\"center\" nowrap=\"nowrap\" colspan=\"2\" height=\"28.5px\">����(10��)</td></tr>" +
					"<tr><td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">����<br/>(10��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">��֯<br/>(10��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">����<br/>(10��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">���ڼ��<br/>(20��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">���ͼ��<br/>(20��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">������<br/>(20��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">¥�����<br/>(5��)</td>" +
					"<td width=\"9%\" align=\"center\" nowrap=\"nowrap\" height=\"28.5px\">"+Base.YXPZXY_KEY+"����<br/>(5��)</td></tr></thead>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='hidden' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append("><input id=\"text_"+i+"_"+j+"_"+rs.length+"\" type=\"text\" maxlength=\"5\" size=\"5\" onkeydown=\"if(event.keyCode==13) {event.keyCode=9;}\" onkeyup=\"skipNext(this)\" onblur=\"checkInputNum(this);checkIn(this,");
					if(j==2||j==3||j==4){
						html.append(10);
					}else if(j==5||j==6||j==7){
						html.append(20);
					}else{
						html.append(5);
					}
					html.append(")\" value=\"");
					html.append(replaceHtml(rs[j]));
					html.append("\"/></td>");
				}
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"40px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[rs.length-2]));
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"40px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[rs.length-1]));
				html.append("</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void exp(ServletOutputStream outputStream, LdkhglForm model) throws Exception {		
		String title =  model.getSearchModel().getSearch_tj_xn()[0]+"ѧ��¥�����˹�����";
		// ���������ͷ
		String[] topTr = new String[] { "�����·�", "������߷�", "��߷�¥��", "������ͷ�", "��ͷ�¥��" };
		
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = ldkhgl(model);

		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 5, 800);// ����
		for (int i = 0; i < topTr.length; i++) {
			ws.setColumnView(i, 25);
			ws.addCell(new Label(i, 1, topTr[i], wcf2));
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length-1; j++) {
						ws.addCell(new Label(j, i + 2, info[j+1], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expCj(ServletOutputStream outputStream, LdkhglForm model) throws Exception {	
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String title =  model.getPkValue()+"�·�¥�����˳ɼ���";
		// ���������ͷ
		String[] topTr = new String[] { "����(10��)", "��֯(10��)", "����(10��)", "���ڼ��(20��)","���ͼ��(20��)","������(20��)","¥������(5��)",Base.YXPZXY_KEY+"����(5��)"};
		
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = khcjwh(model);

		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 11, 800);// ����
		ws.addCell(new Label(0, 1, "¥��", wcf2));
		ws.mergeCells(0,1,0,3);
		ws.addCell(new Label(1, 1, "��������", wcf2));
		ws.mergeCells(1,1,8,1);
		ws.addCell(new Label(9, 1, "�ܷ�", wcf2));
		ws.mergeCells(9,1,9,3);
		ws.addCell(new Label(10, 1, "����", wcf2));
		ws.mergeCells(10,1,10,3);
		ws.addCell(new Label(1, 2, "�ճ�����(30��)", wcf2));
		ws.mergeCells(1,2,3,2);
		ws.addCell(new Label(4, 2, "���Ҽ��(60��)", wcf2));
		ws.mergeCells(4,2,6,2);
		ws.addCell(new Label(7, 2, "����(10��)", wcf2));
		ws.mergeCells(7,2,8,2);
		for (int i = 0; i < topTr.length; i++) {
			ws.setColumnView(i+1, 14);
			ws.addCell(new Label(i+1, 3, topTr[i], wcf2));
		}
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length-1; j++) {
						ws.addCell(new Label(j, i + 4, info[j+1], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	

	/**
	 * ���˳ɼ�����
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String khcjBc(LdkhglForm model) throws Exception {
		boolean flag = false;
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		flag = ldkhglDao.delKhcj(pkValue[0].split("!!@@!!")[0]);
		if(flag){
			flag = ldkhglDao.khcjBc(params);
		}
		return flag? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}



}
