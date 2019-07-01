package xgxt.xszz.nnzy;

import java.io.OutputStream;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzNnzyService extends XszzCommService implements XszzCommTjbbService{
	

	/**
	 * ��ӡ����ͳ�Ʊ���
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//���ҽ�ѧ��
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//������־��ѧ��
			printGjlzjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//������ѧ��
			printGjzxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_ZZQRMZFJXJ.equalsIgnoreCase(form.getTjbbdm())){//����������������ѧ��
			printZzqrmzftjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_XZJXJ.equalsIgnoreCase(form.getTjbbdm())){//У����ѧ��
			printXzjxjtjbb(form,os);
		}
	}
	
	/**
	 * ���ҽ�ѧ��
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//���ҽ�ѧ��
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("ѧ�����ͨ�ߵ�ѧУ���ҽ�ѧ���ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ҽ�ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "ѧУ���ƣ�               �����£�                                          ������ڣ�       ��       ��       ��", btomTytle));
			
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "������ݺ���", wcfTytle));
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
			ws.mergeCells(0, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�绰��                         ���棺                         �������䣺                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������־��ѧ��
	 * @param form
	 * @param os
	 * */
	public void printGjlzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJLZJXJ;//������־��ѧ��
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("ѧ�����ͨ�ߵ�ѧУ������־��ѧ���ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������־��ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "����ѧԺ���ƣ�               �����£�                                          ������ڣ�       ��       ��       ��", btomTytle));
			
//			�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "������ݺ���", wcfTytle));
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
			ws.mergeCells(0, data.size()+3,8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�绰��                         ���棺                         �������䣺                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������ѧ��
	 * @param form
	 * @param os
	 * */
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//������ѧ��
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjzxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("ѧ�����ͨ�ߵ�ѧУ������ѧ���ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "����ѧԺ���ƣ�               �����£�                                          ������ڣ�       ��       ��       ��", btomTytle));
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "������ݺ���", wcfTytle));
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
			ws.mergeCells(0, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�绰��                         ���棺                         �������䣺                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����������������ѧ��
	 * @param form
	 * @param os
	 * */
	public void printZzqrmzftjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//������ѧ��
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getZzqrmzfjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("����������������ѧ��ͳ�Ʊ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("����������������ѧ��", 0);

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 10, 1);
			ws.addCell(new Label(0, 1, "����ѧԺ���ƣ�               �����£�                 ���:         ��ϵ�绰:               ��������:        ", btomTytle));
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(3, 2, "����", wcfTytle));
			ws.addCell(new Label(4, 2, "��������", wcfTytle));
			ws.addCell(new Label(5, 2, "Ժϵ�����", wcfTytle));
			ws.addCell(new Label(6, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(7, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(8, 2, "��ͥ��ϸ��ַ", wcfTytle));
			ws.addCell(new Label(9, 2, "������ϵ�绰", wcfTytle));
			ws.addCell(new Label(10, 2, "���֤��", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * У����ѧ��
	 * @param form
	 * @param os
	 * */
	public void printXzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//������ѧ��
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getXzjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("У����ѧ��ͳ�Ʊ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("У����ѧ��ͳ�Ʊ�", 0);

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 10, 1);
			ws.addCell(new Label(0, 1, "����ѧԺ���ƣ�               �����£�                 ���:         ��ϵ�绰:               ��������:        ", btomTytle));
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧ������", wcfTytle));
			ws.addCell(new Label(2, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(3, 2, "����", wcfTytle));
			ws.addCell(new Label(4, 2, "��������", wcfTytle));
			ws.addCell(new Label(5, 2, "Ժϵ�����", wcfTytle));
			ws.addCell(new Label(6, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(7, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(8, 2, "��ͥ��ϸ��ַ", wcfTytle));
			ws.addCell(new Label(9, 2, "������ϵ�绰", wcfTytle));
			ws.addCell(new Label(10, 2, "���֤��", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
