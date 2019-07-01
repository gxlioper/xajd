package xsgzgl.pjpy.general.tjcx.hjmdhz;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjmdhzInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_����������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class TjcxHjmdhzService extends CommService implements
		TjcxHjmdhzInterface {

	TjcxHjmdhzDAO dao = new TjcxHjmdhzDAO();

	/**
	 * ��ñ�ͷ�ļ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getHjmdhzTop(TjcxHjmdhzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pn", "xh", "xm", "nj", "bjmc", "xmlx",
				"xmmc", "hdsj" };
		String[] cn = new String[] { "ѧ��", "ѧ��", "����", "Ժϵ����", "��Ŀ����", "��Ŀ����",
				"���ʱ��" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getHjmdhzList(PjpyGeneralForm myForm,
			TjcxHjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getHjmdhzList(myForm, model, user);

		return list;
	}

	/**
	 * ���������
	 * 
	 * @author ΰ�����
	 */
	public String createHjmdhzHTML(SearchRsModel rsModel,
			TjcxHjmdhzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ��������������
	 * 
	 * @author ΰ�����
	 */
	public void expHjmdhz(TjcxHjmdhzModel model, OutputStream os)
			throws Exception {

		// ѧ��
		String xn = model.getXn();
		// ��Ŀ����
		String xmlx = model.getXmlx();
		// Ժϵ����
		String arr_xydm[] = model.getXydm();

		// ��Ŀ��������
		String xmlxmc = "01".equalsIgnoreCase(xmlx) ? "��ѧ��" : "�����ƺ�";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmlxmc + "������", 0);
		
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList(xn, xmlx, arr_xydm);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmrsList(xn, xmlx);
		// ������
		List<String[]> xmList = dao.getHjmdList(xn, xmlx, arr_xydm);
		
		//��ѧ��������ʽ
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		// �����һ�еı�ͷ
		ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation
				.getXxmc(), xn, "ѧ���", "��", "ѧ��", xmlxmc, "���������"), jxjFormat));
		ws.mergeCells(0, 0, 8, 0);
		
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[1];
				String jxjdm = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (��" +jxjdmArr[2] +"��)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//����ڶ�����ѧԺ����д��EXCEL��
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjdm.equalsIgnoreCase(xymcArr[2])) {						
						String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
						String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						for (String[] xmArr : xmList) {
							if (xmArr != null && xmArr.length >= 3
									&& xydm.equalsIgnoreCase(xmArr[1])
									&& jxjdm.equalsIgnoreCase(xmArr[0])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[3] + "��)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//�����������ѧԺ����Ļ��������
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//��������д�뵽EXCEL��
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	
	private class JxjExportProperties{
		 int x_axis = 0;//X����
		 int row = 1;//�б�
		 int rowCellCount = 1;//ÿ�е�cell��������
		 int[] maxLength = {7,7,7,7,7,7,7};//Ĭ�ϵ��п�
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// ��ʼд������
		properties.x_axis = rowCellCount;// ��ʼ�½�ѧ�������
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				String cellContent = xmList.get(index - 1);
				if (properties.rowCellCount == 8) {
					properties.row++;// ��10���ͻ���
					properties.x_axis = 0;
					properties.rowCellCount = 1;// ����ÿ�м���
				}
				// �ж���������,Ȼ����ݳ��ȿ��ƺϲ���Ԫ�񣬼��������ȵ�����½��л���
				if (cellContent.length() > 3) {
					int xmLength = cellContent.length() / 3
							+ (cellContent.length() % 3 == 0 ? 0 : 1);
					int pre_x_axis = properties.x_axis;// �ϲ�ǰ��x��ֵ
					if (pre_x_axis + xmLength > 10) {
						properties.row++;// ��������������
						properties.x_axis = rowCellCount;
						properties.rowCellCount = 1;// ����ÿ�м���
						pre_x_axis = properties.x_axis;
					}
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					pre_x_axis++;
					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
							+ xmLength - 1, properties.row);
					properties.x_axis = pre_x_axis + xmLength - 1;
				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
				}
			}
		}
		properties.row += 1;// �½�ѧ����
	}
}