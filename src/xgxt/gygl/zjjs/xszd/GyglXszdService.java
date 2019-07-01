package xgxt.gygl.zjjs.xszd;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.date.MoneyFormat;

/**
 * ����ְҵ���������Ž�ѧ��ά��Service
 */
public class GyglXszdService extends CommService {

	GyglXszdDao dao = new GyglXszdDao();

	/**
	 * �����߶�����
	 * 
	 * @author luojw
	 */
	public boolean saveXszdSq(GyglXszdForm model) {
		return dao.saveXszdSq(model);
	}

	/**
	 * ���ѧ���߶�������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszdSqInfo(GyglXszdForm model) {

		String pk = (Base.isNull(model.getPk())) ? model.getXh()
				+ model.getSqsj() : model.getPk();

		return dao.getXszdSqInfo(pk);
	}

	/**
	 * ���ѧ���߶�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdshList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getXszdshList(model, colList, user);
		int page_size = model.getPages().getPageSize();
		
		int num = 0;
		
		if (list != null && list.size() > 0) {
			num = (page_size >= list.size()) ? page_size - list.size() : num;
		} else {
			num = page_size;
		}
		
		for(int i=0;i<num;i++){
			String[] rows = new String[colList.length];
			list.add(rows);
		}
		
		return list;
	}

	/**
	 * ����ѧ���߶����״̬
	 * 
	 * @author luojw
	 */
	public boolean saveXszdShzt(GyglXszdForm model,User user) {
		return dao.saveXszdShzt(model,user);
	}
	
	/**
	 * ��������ѧ���߶���״̬
	 * 
	 * @author luojw
	 */
	public boolean savePlXszdShzt(GyglXszdForm model, User user) {
		return dao.savePlXszdShzt(model, user);
	}

	/**
	 * ���ѧ���߶�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdjgList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getXszdjgList(model, colList, user);
		int page_size = model.getPages().getPageSize();
		
		int num = 0;
		
		if (list != null && list.size() > 0) {
			num = (page_size >= list.size()) ? page_size - list.size() : num;
		} else {
			num = page_size;
		}
		
		for(int i=0;i<num;i++){
			String[] rows = new String[colList.length];
			list.add(rows);
		}
		
		return list;
	}
	
	/**
	 * ��ӡ��ѧ����ͳ�Ʊ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expXszdToExcle(GyglXszdForm model,User user , OutputStream os)
			throws Exception {
		
		String[] colList = new String[] { "xymc", "bjmc", "xh", "xm", "xb",
				"lxdh", "zsdd", "jtdz", "jtdh", "zdkssj", "zdjssj", "sqly" };

		List<String[]> list = dao.getXszdTjList(model, colList, user);
		
		// ���ñ���
		StringBuffer title =new StringBuffer();
		title.append("�߶�ѧ��ͳ��");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("�߶�ͳ��", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 11, 0);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ����ͷ
		ws.addCell(new Label(0, 1, "���", wcf2));
		ws.addCell(new Label(1, 1, "ϵ��", wcf2));
		ws.addCell(new Label(2, 1, "�༶", wcf2));
		ws.addCell(new Label(3, 1, "ѧ��", wcf2));
		ws.addCell(new Label(4, 1, "����", wcf2));
		ws.addCell(new Label(5, 1, "�Ա�", wcf2));
		ws.addCell(new Label(6, 1, "ѧ����ϵ�绰", wcf2));
		ws.addCell(new Label(7, 1, "ס�޵ص�", wcf2));
		ws.addCell(new Label(8, 1, "��ͥ��ס�ص�", wcf2));
		ws.addCell(new Label(9, 1, "��ͥ��ϵ�绰", wcf2));
		ws.addCell(new Label(10, 1, "����ʱ��", wcf2));
		ws.addCell(new Label(11, 1, "��������", wcf2));
		
		ws.setColumnView(1, 20);
		ws.setColumnView(2, 20);
		ws.setColumnView(3, 20);
		ws.setColumnView(4, 20);
		ws.setColumnView(5, 20);
		ws.setColumnView(6, 20);
		ws.setColumnView(7, 20);
		ws.setColumnView(8, 20);
		ws.setColumnView(9, 20);
		ws.setColumnView(10, 40);
		ws.setColumnView(11, 20);
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String[] value = list.get(i);
				ws.addCell(new Label(0, 2 + i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 2 + i, value[0], wcf2));
				ws.addCell(new Label(2, 2 + i, value[1], wcf2));
				ws.addCell(new Label(3, 2 + i, value[2], wcf2));
				ws.addCell(new Label(4, 2 + i, value[3], wcf2));
				ws.addCell(new Label(5, 2 + i, value[4], wcf2));
				ws.addCell(new Label(6, 2 + i, value[5], wcf2));

				ws.addCell(new Label(7, 2 + i, value[6], wcf2));
				ws.addCell(new Label(8, 2 + i, value[7], wcf2));
				ws.addCell(new Label(9, 2 + i, value[8], wcf2));
				String sqsj = "";
				sqsj += value[9].substring(0, 4)+"��"+value[9].substring(4, 6)+"��"+value[9].substring(6, 8)+"��";
				sqsj += "��";
				sqsj += value[10].substring(0, 4)+"��"+value[10].substring(4, 6)+"��"+value[10].substring(6, 8)+"��";
				ws.addCell(new Label(10, 2 + i, sqsj, wcf2));
				ws.addCell(new Label(11, 2 + i, value[11], wcf2));
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
