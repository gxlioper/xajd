package xgxt.xszz.zjyd;

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

public class XszzZjydService extends XszzService implements XszzCommTjbbService{
	XszzZjydDAO dao = new XszzZjydDAO();
	
	/**
	 * ���ҽ�ѧ��ѧ������������
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//���ҽ�ѧ��
		List<String[]> data = dao.getGjjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("ѧ����ͨ�ߵ�ѧУ���ҽ�ѧ���ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ҽ�ѧ���ѧ������������", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 400);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 8, 1);
			ws.addCell(new Label(0, 1, "ѧУ���ƣ��㽭�ʵ�ְҵ����ѧԺ", btomTytle));
			ws.addCell(new Label(3,1,"�����£�", btomTytle));
			ws.addCell(new Label(6,1,"   ������ڣ�		��		��		��", btomTytle));
			
			
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "�������֤����", wcfTytle));
			ws.addCell(new Label(3, 2, "Ժϵ", wcfTytle));
			ws.addCell(new Label(4, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(5, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(6, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(8, 2, "��ѧ����", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "������:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "��ϵ�绰:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "����:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "��������:", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	public void printGjlzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//���ҽ�ѧ��
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("ѧ����ͨ�ߵ�ѧУ������־��ѧ���ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������־��ѧ���ѧ������������", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 400);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 8, 1);
			ws.addCell(new Label(0, 1, "ѧУ���ƣ��㽭�ʵ�ְҵ����ѧԺ", btomTytle));
			ws.addCell(new Label(3,1,"�����£�", btomTytle));
			ws.addCell(new Label(6,1,"   ������ڣ�		��		��		��", btomTytle));
			
			
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "�������֤����", wcfTytle));
			ws.addCell(new Label(3, 2, "Ժϵ", wcfTytle));
			ws.addCell(new Label(4, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(5, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(6, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(8, 2, "��ѧ����", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "������:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "��ϵ�绰:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "����:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "��������:", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//���ҽ�ѧ��
		List<String[]> data = dao.getGjzxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("ѧ����ͨ�ߵ�ѧУ������ѧ����������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������ѧ����������������", 0);

		try {
			excel.printTitle(ws, title.toString(), 10, 400);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 9, 1);
			ws.addCell(new Label(0, 1, "ѧУ���ƣ��㽭�ʵ�ְҵ����ѧԺ", btomTytle));
			ws.addCell(new Label(3,1,"�����£�", btomTytle));
			ws.addCell(new Label(6,1,"   ������ڣ�		��		��		��", btomTytle));
			
			
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "�������֤����", wcfTytle));
			ws.addCell(new Label(3, 2, "Ժϵ", wcfTytle));
			ws.addCell(new Label(4, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(5, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(6, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(8, 2, "��ѧ����", wcfTytle));
			ws.addCell(new Label(9, 2, "��������", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 9, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "������:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "��ϵ�绰:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "����:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "��������:", btomTytle));
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
		if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//���ҽ�ѧ��
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//������־��ѧ��
			printGjlzjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//������ѧ��
			printGjzxjtjbb(form,os);
		}
	}
	
}
