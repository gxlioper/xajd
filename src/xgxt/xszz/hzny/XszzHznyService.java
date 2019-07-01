package xgxt.xszz.hzny;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.xmlg.wmbj.WmbjService;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzHznyService extends XszzService implements XszzCommTjbbService{
	XszzHznyDAO dao = new XszzHznyDAO();

	
	public void printXylstdTjbb(XszzTyForm model, OutputStream os){
		
		List<HashMap<String,String>> data = dao.getXylstdData();//����ѧԺͳ����ɫͨ����Ϣ
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("ѧԺ��ɫͨ��ͳ�Ʊ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧԺ��ɫͨ��ͳ��", 0);

		try {
			excel.printTitle(ws, title.toString(), 7, 400);// ����
		
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ		
			
			//�������
			ws.addCell(new Label(0, 1, "ѧԺ", wcfTytle));
			ws.addCell(new Label(1, 1, "������������", wcfTytle));
			ws.addCell(new Label(2, 1, "������ɫͨ��ѧ������", wcfTytle));
			ws.addCell(new Label(3, 1, "����", wcfTytle));
			ws.addCell(new Label(4, 1, "��������Ԫ��", wcfTytle));
			ws.addCell(new Label(5, 1, "δ���κη�������", wcfTytle));
			ws.addCell(new Label(6, 1, "�����ӷ�", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				
				HashMap<String,String>map=data.get(i);
				ws.addCell(new Label(0, 2 + i,map.get("xymc"), wcfTytle));
				ws.addCell(new Label(1, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(2, 2 + i,map.get("blstdrs"), wcfTytle));
				ws.addCell(new Label(3, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(4, 2 + i,map.get("hjje"), wcfTytle));
				ws.addCell(new Label(5, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(6, 2 + i,map.get("hjzf"), wcfTytle));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printSydlstdTjbb(XszzTyForm model, OutputStream os){
		
		List<HashMap<String,String>> data = dao.getSydlstdData();//��ȡ��Դ��ʡ��ɫͨ��ͳ����Ϣ
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("��Դ����ɫͨ��ͳ�Ʊ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Դ����ɫͨ��ͳ��", 0);

		try {
			excel.printTitle(ws, title.toString(), 5, 400);// ����
		
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ		
			
			//�������
			ws.addCell(new Label(0, 1, "��Դ��", wcfTytle));
			ws.addCell(new Label(1, 1, "¼ȡ����", wcfTytle));
			ws.addCell(new Label(2, 1, "��������", wcfTytle));
			ws.addCell(new Label(3, 1, "ͨ����ɫͨ������", wcfTytle));
			ws.addCell(new Label(4, 1, "����", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				
				HashMap<String,String>map=data.get(i);
				ws.addCell(new Label(0, 2 + i,map.get("syds"), wcfTytle));
				ws.addCell(new Label(1, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(2, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(3, 2 + i,map.get("tgrs"), wcfTytle));
				ws.addCell(new Label(4, 2 + i,map.get(""), wcfTytle));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/***
	 * ��ӡ�������е�ͳ�Ʊ���
	 * @param form
	 * @param os
	 * */
	
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if(XszzXmdm.XSZZ_TJBB_HZNY_XYLSTDTJB.equalsIgnoreCase(form.getTjbbdm())){//ѧԺ��ɫͨ��ͳ�Ʊ�
			printXylstdTjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_HZNY_SYDLSTDTJB.equalsIgnoreCase(form.getTjbbdm())){//��Դ����ɫͨ��ͳ�Ʊ�
			printSydlstdTjbb(form,os);
		}
	}
	
}
