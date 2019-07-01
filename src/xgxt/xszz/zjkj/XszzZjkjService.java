package xgxt.xszz.zjkj;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.comm.XszzCommTjbbService;

public class XszzZjkjService extends XszzCommService implements XszzCommTjbbService{
	
	private XszzZjkjDAO dao = new XszzZjkjDAO();
	
	/**
	 * ������ͳ�Ʊ���
	 * @param form
	 * @param os
	 * */
	public void printKnstjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_KNS;//������
		List<String[]> data = dao.getKnsData(xmdm, model);//��ѯ����������

		String title = "��ͥ��������ѧ����Ϣ���ܱ�";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ͥ��������ѧ����Ϣ���ܱ�", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			//�������
			ws.addCell(new Label(0, 1, "���", wcfTytle));
			ws.addCell(new Label(1, 1, "����ѧԺ", wcfTytle));
			ws.addCell(new Label(2, 1, "�༶", wcfTytle));
			ws.addCell(new Label(3, 1, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 1, "����", wcfTytle));
			ws.addCell(new Label(5, 1, "������ϵ�绰", wcfTytle));
			ws.addCell(new Label(6, 1, "��ͥ�˿�����", wcfTytle));
			ws.addCell(new Label(7, 1, "��ͥ�괿����", wcfTytle));
			ws.addCell(new Label(8, 1, "��ͥ��ϸסַ", wcfTytle));
			ws.addCell(new Label(9, 1, "��ͥ��ϵ�绰", wcfTytle));
			ws.addCell(new Label(10, 1, "��ͥ�����������", wcfTytle));
			ws.addCell(new Label(11, 1, "��ѧ��ѧϰ�ɼ����ϸ�����", wcfTytle));
			ws.addCell(new Label(12, 1, "���ѵȼ�", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 2 + i, data.get(i)[j], wcfTytle));
				}
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
		String xxdm = Base.xxdm;
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			printZgddBb(form, os);
		} else {
			if (XszzXmdm.XSZZ_TJBB_KNS.equalsIgnoreCase(form.getTjbbdm())) {// ������ͳ��
				printKnstjbb(form, os);
			}
		}
	}	
	
	/**
	 * �й��ش󱨱��ӡ
	 * 
	 * @param form
	 * @param os
	 */
	public void printZgddBb(XszzTyForm model, OutputStream os) {

		XszzZjkjDAO dao = new XszzZjkjDAO();
		
		String xmdm = model.getTjbbdm();// ��Ŀ����
		// ��Ŀ�����Ϣ
		model.setXmdm(xmdm);
		model.setPkValue(xmdm);
		HashMap<String, String> map = getXmxgInfo(model);
		// ��Ŀ��
		String xmb = map.get("xmb");
		// �����ֶ�
		String[] outValue = null;
		try {
			outValue = getOutValue(getTableZd(xmb));
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		
		List<String[]> data = dao.getZgddZzList(xmdm, map, outValue, model);//��ѯ����������

		String title = getOneValue("xszz_zzxmb", "xmmc", "xmdm", xmdm)
				+ "��Ϣ���ܱ�";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title.toString(), 16, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			//�������
			//ws.addCell(new Label(0, 1, "���뽱��", wcfTytle));
			ws.addCell(new Label(0, 1, "���", wcfTytle));
			ws.addCell(new Label(1, 1, "ѧ��", wcfTytle));
			ws.addCell(new Label(2, 1, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(3, 1, "����", wcfTytle));
			ws.addCell(new Label(4, 1, "�Ա�", wcfTytle));
			ws.addCell(new Label(5, 1, "�����ˮƽ", wcfTytle));
			ws.addCell(new Label(6, 1, "����ˮƽ", wcfTytle));
			ws.addCell(new Label(7, 1, "�ϰ�ѧ��ѧ�ּ���", wcfTytle));
			ws.addCell(new Label(8, 1, "�°�ѧ��ѧ�ּ���", wcfTytle));
			ws.addCell(new Label(9, 1, "�ۺ�����", wcfTytle));
			ws.addCell(new Label(10, 1, "��ͥ���", wcfTytle));
			ws.addCell(new Label(11, 1, "��������", wcfTytle));
			ws.addCell(new Label(12, 1, "���һ�굥�����ְ�񼰲μ�������", wcfTytle));
			ws.addCell(new Label(13, 1, "���֤��", wcfTytle));
			ws.addCell(new Label(14, 1, "������Ŀ����������", wcfTytle));
			ws.addCell(new Label(15, 1, "��ע", wcfTytle));


			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 2 + i, data.get(i)[j], wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
