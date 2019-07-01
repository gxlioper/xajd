package xsgzgl.wjcf.cfsjtj;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

public class WjcfCfsjtjService extends BasicService {
	private WjcfCfsjtjDao dao=new WjcfCfsjtjDao();
	private WjcfJcszServices wjcfjcszServices=new WjcfJcszServices();

	public List<String[]> xycftj(WjcfCfsjtjActionForm myForm ,List<HashMap<String, String>> list) throws Exception{
		// TODO �Զ����ɷ������
		return dao.getXycftj(myForm,list);
	}

	public void exportData(HttpServletResponse response, 
			List<String[]> tjList, List<HashMap<String, String>> cflbsList, String xn[], OutputStream os ) throws Exception{
		// TODO �Զ����ɷ������
		WritableWorkbook wwb =ExcelMethods.getWritableWorkbook(response.getOutputStream());
		int lbnum=cflbsList.size();
		try{
			WritableSheet ws=wwb.createSheet("11", 0);
			WritableCellFormat wcFormate=new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			//��ʽ
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			//����
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcFormate.setFont(wfTytle);
			ws.mergeCells(0, 0,5+lbnum, 3);
			String xns="";
			if(xn.length>0){
				for (String str : xn) {
					xns+=str+"��";
				}
				xns=xns.substring(0, xns.length()-1);
			}
			ws.addCell(new Label(0,0,Base.xxmc+xns+"ѧ��ѧ������ͳ�Ʊ�",wcFormate));
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcFormate.setFont(wfTytle);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setWrap(true);
//			 ���ñ��߿�
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcFormate.setFont(wfTytle);
			ws.mergeCells(0, 4, 0, 5);
			ws.addCell(new Label(0, 4, "���",wcFormate ));
			ws.mergeCells(1, 4, 1, 5);
			ws.addCell(new Label(1, 4, "Ժϵ",wcFormate ));
			ws.mergeCells(2, 4, 2, 5);
			ws.addCell(new Label(2, 4, "ѧ������",wcFormate ));
			ws.mergeCells(3, 4, 2+lbnum, 4);
			ws.addCell(new Label(3, 4, "Υ�ʹ���������˴Σ�",wcFormate ));
			ws.mergeCells(3+lbnum, 4,4+lbnum,4);
			ws.addCell(new Label(3+lbnum, 4, "�ܴ�������",wcFormate ));
			ws.mergeCells(5+lbnum, 4, 5+lbnum, 5);
			ws.addCell(new Label(5+lbnum, 4, "��ע",wcFormate ));
			int tmp=0;
			for (int i = 0; i < cflbsList.size(); i++) {
				ws.addCell(new Label(3+i, 5,cflbsList.get(i).get("mc"),wcFormate ));
				tmp=i;
			}
			ws.addCell(new Label(tmp+4, 5,"�˴�",wcFormate ));
			ws.addCell(new Label(tmp+5, 5,"����",wcFormate ));
			for (int i = 0; i <tjList.size() ; i++) {
				ws.addCell(new Label(0,6+i,new Integer(i+1).toString(),wcFormate));
				for (int j=0;j<tjList.get(i).length;j++) {
					ws.addCell(new Label(j+1,6+i,tjList.get(i)[j],wcFormate));
				}
				
			}
		}catch(Exception e){
			
		}finally{
			wwb.write();
			wwb.close();
		}
	}

	public List<String[]> yltj(WjcfCfsjtjActionForm myForm) throws Exception{
		// TODO �Զ����ɷ������
		return dao.getYltj(myForm);

	}

	public void exportData(HttpServletResponse response,
			List<String[]> tjList, String xn[], ServletOutputStream outputStream) throws Exception{
		// TODO �Զ����ɷ������
		WritableWorkbook wwb =ExcelMethods.getWritableWorkbook(response.getOutputStream());
		try{
			WritableSheet ws=wwb.createSheet("11", 0);
			WritableCellFormat wcFormate=new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			//��ʽ
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			//����
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcFormate.setFont(wfTytle);
			ws.mergeCells(0, 0, 10, 3);
			String xns="";
			if(xn.length>0){
				for (String str : xn) {
					xns+=str+"��";
				}
				xns=xns.substring(0, xns.length()-1);
			}
			ws.addCell(new Label(0,0,Base.xxmc+xns+"ѧ��ѧ���������һ����",wcFormate));
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcFormate.setFont(wfTytle);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setWrap(true);
//			 ���ñ��߿�
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcFormate.setFont(wfTytle);
			ws.addCell(new Label(0,4,"���",wcFormate));
			ws.addCell(new Label(1,4,"ѧ��",wcFormate));
			ws.addCell(new Label(2,4,"����",wcFormate));
			ws.addCell(new Label(3,4,"�Ա�",wcFormate));
			ws.addCell(new Label(4,4,"Ժϵ",wcFormate));
			ws.addCell(new Label(5,4,"�༶",wcFormate));
			ws.addCell(new Label(6,4,"Υ��ʱ��",wcFormate));
			ws.addCell(new Label(7,4,"���ĺ�",wcFormate));
			ws.addCell(new Label(8,4,"����ʱ��",wcFormate));
			ws.addCell(new Label(9,4,"��������",wcFormate));
			ws.addCell(new Label(10,4,"��������",wcFormate));
			for (int i = 0; i <tjList.size() ; i++) {
				ws.addCell(new Label(0,5+i,new Integer(i+1).toString(),wcFormate));
				for (int j=0;j<tjList.get(i).length;j++) {
					ws.addCell(new Label(j+1,5+i,tjList.get(i)[j],wcFormate));
				}
				
			}
		}catch(Exception e){
			
		}finally{
			wwb.write();
			wwb.close();	
		}
	}
	/**
	 * ����HTML(ѧԺͳ��)
	 * 
	 * @author 
	 * @throws Exception 
	 */
	public String createHTML(SearchRsModel rsModel,
			 ArrayList<String[]> rsArrList) throws Exception {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();
		html.append("<table width=\"99%\" id=\"rsTable\" class=\"dateline\">");
		html.append("<thead>");
		html.append("<tr align=\"center\" style=\"coursor: hand\">");
		html.append("<td rowspan=\"2\" width=\"4%\" align=\"center\">���</td>");
		html.append("<td rowspan=\"2\" width=\"8%\" align=\"center\">Ժϵ</td>");
		html.append("<td rowspan=\"2\" width=\"9%\" align=\"center\">ѧ������</td>");
		html.append("");
		List<HashMap<String, String>> cflbsList=wjcfjcszServices.cflbmcCx();
		int size=cflbsList.size();
		html.append("<td colspan="+size+" align=\"center\">Υ�ʹ���������˴Σ�</td>");
		html.append("<td colspan=\"2\" align=\"center\">�ܴ�������</td>");
		html.append("<td rowspan=\"2\" align=\"center\">��ע</td></tr>");
		html.append("<tr align=\"center\" style=\"coursor: hand\">");
		HashMap<String, String> map=new HashMap<String, String>();
		for (int i = 0; i < size; i++) {
			map=cflbsList.get(i);
			html.append("<td>"+map.get("mc")+"</td>");
		}
		html.append("<td>�˴�</td><td>����</td></tr>");
		html.append("</thead>");
		
		

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {
				

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr>");
				int temp=i+1;
				html.append("<td>"+temp+"</td>");
				
//				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
//				// IE8
//				if ("5.8".equalsIgnoreCase(ie)) {
//					html.append("height=\"28.5px\"");
//				} else {
//					html.append("height=\"29px\"");
//				}
			
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("<td></td></tr>");
			}
		}
		html.append("</table>");

		return html.toString();
	}
	
	/**
	 * ����HTML(ѧ��һ��)
	 * 
	 * @author 
	 * @throws Exception 
	 */
	public String createHTML2(SearchRsModel rsModel,
			 ArrayList<String[]> rsArrList) throws Exception {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();
		html.append("<table width=\"99%\" id=\"rsTable\" class=\"dateline\">");
		html.append("<thead>");
		html.append("<tr align=\"center\" style=\"coursor: hand\">");
		html.append("<td width=\"4%\" align=\"center\">���</td>");
		html.append("<td width=\"7%\" align=\"center\">ѧ��</td>");
		html.append("<td width=\"6%\" align=\"center\">����</td>");
		html.append("<td width=\"8%\" align=\"center\">�Ա�</td>");
		html.append("<td align=\"center\">Ժϵ</td>");
		html.append("<td width=\"8%\" align=\"center\">�༶</td>");
		html.append("<td width=\"8%\" align=\"center\">Υ��ʱ��</td>");
		html.append("<td width=\"8%\" align=\"center\">���ĺ�</td>");
		html.append("<td width=\"8%\" align=\"center\">����ʱ��</td>");
		html.append("<td width=\"8%\" align=\"center\">��������</td>");
		html.append("<td width=\"8%\" align=\"center\">��������</td>");
		html.append("</thead>");
		
		

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {
				

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr>");
				int temp=i+1;
				html.append("<td>"+temp+"</td>");
				
//				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
//				// IE8
//				if ("5.8".equalsIgnoreCase(ie)) {
//					html.append("height=\"28.5px\"");
//				} else {
//					html.append("height=\"29px\"");
//				}
			
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		html.append("</table>");

		return html.toString();
	}

}
