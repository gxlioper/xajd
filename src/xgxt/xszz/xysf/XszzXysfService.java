package xgxt.xszz.xysf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.Globals;
import common.XszzXmdm;

public class XszzXysfService extends XszzCommService implements
		XszzCommTjbbService {

	/**
	 * ��ӡ����ͳ�Ʊ���
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if (XszzXmdm.XSZZ_TJBB_XYSF_LSTD.equalsIgnoreCase(form.getTjbbdm())) {// ��ɫͨ��
			printLstdtjbb(form, os);
		} else if (XszzXmdm.XSZZ_TJBB_XYSF_KNS.equalsIgnoreCase(form.getTjbbdm())) {// ��ͥ��������ѧ���϶�
			printKnstjbb(form, os);
		}
	}

	/**
	 * ��ɫͨ��
	 * 
	 * @param form
	 * @param os
	 */
	public void printLstdtjbb(XszzTyForm model, OutputStream os) {

		DAO dao = DAO.getInstance();
		XszzXysfDAO tjDao = new XszzXysfDAO();
		 
		String xmdm = XszzXmdm.XSZZ_LSTD;// ��ɫͨ��
		String xydm = model.getXydm();// ѧԺ����
		String xymc = dao.getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		
		List<String[]> data = tjDao.getLstdData(xmdm, model);// ��ѯҪ����������

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);

		try {

			StringBuilder title = new StringBuilder();
			title.append("����ʦ��ѧԺͨ������ɫͨ������ѧ�������ܱ�");

			WritableSheet ws = wwb.createSheet("����ɫͨ������ѧ�������ܱ�", 0);

			// ������
			ExcelMB ex = new ExcelMB();
			ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
					Border.NONE);

			ws.mergeCells(0, 0, 7, 1);
			
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ

			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			
			StringBuilder nrSb = new StringBuilder();
			nrSb.append("ѧԺ��");
			nrSb.append(xymc);
			nrSb.append("        �����£�        ");
			nrSb.append("����ˣ�                ������ڣ�      ");
			nrSb.append("��        ��        ��");
			ws.addCell(new Label(0, 2, nrSb.toString(), wcf2));
			ws.mergeCells(0, 2, 7, 2);
			
			ws.addCell(new Label(0, 3, "���", wcf2));
			ws.addCell(new Label(1, 3, "����", wcf2));
			ws.addCell(new Label(2, 3, "׼��֤��", wcf2));
			ws.addCell(new Label(3, 3, "��ͥסַ", wcf2));
			ws.addCell(new Label(4, 3, "רҵ", wcf2));
			ws.addCell(new Label(5, 3, "�༶", wcf2));
			ws.addCell(new Label(6, 3, "Ӧ�ɽ�Ԫ��", wcf2));
			ws.addCell(new Label(7, 3, "���ɽ�Ԫ��", wcf2));
			
			if (data != null && data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					for (int j = 0; j < data.get(i).length; j++) {
						ws.addCell(new Label(j, 4 + i, data.get(i)[j], wcf2));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ͥ��������ѧ���϶�
	 * 
	 * @param form
	 * @param os
	 */
	public void printKnstjbb(XszzTyForm model, OutputStream os) {

		XszzXysfDAO dao = new XszzXysfDAO();
		// ��Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;// ������
		// ��Ŀ����
		String title = "��ͥ��������ѧ���϶�";

		List<HashMap<String, String>> topTr = dao.getTopTr("kns");
		ArrayList<String[]> list = dao.getKnsData(xmdm, model);

		try {
			expToExcel(title, topTr, list, os);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
