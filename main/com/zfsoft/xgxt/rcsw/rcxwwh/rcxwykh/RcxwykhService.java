package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwykh;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
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
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;

/**
 * �ճ���Ϊ�¿��� 
 */
public class RcxwykhService extends SuperServiceImpl<RcxwykhForm, RcxwykhDao> {
	private static final String[] YFDM_ARR = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] YFMC_ARR = new String[] { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
	private RcxwykhDao dao = new RcxwykhDao();

	public RcxwykhService() {
		super.setDao(dao);
	}
	/**
	 * �鿴�ճ���Ϊ�¿���
	 */
	public List<HashMap<String, String>> getPageListView(RcxwykhForm t, User user)
			throws Exception {
		return dao.getPageListView(t, user);
	}
	/**
	 * ̫ԭ����ְҵѧԺ����
	 */
	public void rcxwykhDc_13696(RcxwykhForm myForm,OutputStream os, User user)throws Exception{
		// ����,18,CENTRE,�Ӵ�
		WritableCellFormat s18CentreBOLDFormat = new WritableCellFormat();
		WritableFont s18CentreBOLDFont = new WritableFont(WritableFont.createFont("����"),18);
		s18CentreBOLDFont.setBoldStyle(WritableFont.BOLD);
		s18CentreBOLDFormat.setFont(s18CentreBOLDFont);
		s18CentreBOLDFormat.setAlignment(Alignment.CENTRE);
		s18CentreBOLDFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// ����,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		s12CentreFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// ����,12,LEFT
		WritableCellFormat s12LeftFormat = new WritableCellFormat();
		WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("����"),12);
		s12LeftFormat.setFont(s12LeftFont);
		s12LeftFormat.setAlignment(Alignment.LEFT);
		s12LeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// ����,12,RIGHT
		WritableCellFormat s12RightFormat = new WritableCellFormat();
		WritableFont s12RightFont = new WritableFont(WritableFont.createFont("����"),12);
		s12RightFormat.setFont(s12RightFont);
		s12RightFormat.setAlignment(Alignment.RIGHT);
		s12RightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// ����,12,�ұ߿�
		WritableCellFormat s12RightBorderFormat = new WritableCellFormat();
		WritableFont s12RightBorderFont = new WritableFont(WritableFont.createFont("����"),12);
		s12RightBorderFormat.setFont(s12RightBorderFont);
		s12RightBorderFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		s12RightBorderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s12RightBorderFormat.setWrap(true);
		// ����,12,�¡��ұ߿�
		WritableCellFormat s12RightBottomFormat = new WritableCellFormat();
		WritableFont s12RightBottomFont = new WritableFont(WritableFont.createFont("����"),12);
		s12RightBottomFormat.setFont(s12RightBottomFont);
		s12RightBottomFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		s12RightBottomFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		s12RightBottomFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s12RightBottomFormat.setWrap(true);
		// ����,11,RIGHT
		WritableCellFormat s11RightFormat = new WritableCellFormat();
		WritableFont s11RightFont = new WritableFont(WritableFont.createFont("����"),11);
		s11RightFormat.setFont(s11RightFont);
		s11RightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s11RightFormat.setAlignment(Alignment.RIGHT);
		// ����,12,CENTRE,�߿�
		WritableCellFormat s12CentreBorderFormat = new WritableCellFormat();
		WritableFont s12CentreBorderFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreBorderFormat.setFont(s12CentreBorderFont);
		s12CentreBorderFormat.setAlignment(Alignment.CENTRE);
		s12CentreBorderFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		s12CentreBorderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String nd = searchModel.getSearch_tj_nd()[0];
		String yf = searchModel.getSearch_tj_yf()[0];
		String bjdm = searchModel.getSearch_tj_bj()[0];
		// �༶��Ϣ
		HashMap<String,String> bjxxMap = dao.querBjxx(bjdm);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("�¿��˱�", 0);
		// ��ǰ��
		int col = 0;
		int colTemp  = 0;
		int row  = 2;
		// ��3��
		ws.setRowView(row, 450);
		ws.addCell(new Label(col, row, "����", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		//��Ϊ����
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwdlList = service.getXwdlList(null);
		for (int i = 0; i < xwdlList.size(); i++) {
			colTemp = col;
			HashMap<String,String> xwdlMap = xwdlList.get(i);
			String rcxwlbdldm = xwdlMap.get("rcxwlbdldm");
			String rcxwlbdlmc = xwdlMap.get("rcxwlbdlmc");
			ws.addCell(new Label(col, row, rcxwlbdlmc, s12CentreBorderFormat));
			// ��Ϊ���
			List<HashMap<String,String>> xwlbList = service.getXwlbList(rcxwlbdldm, null);
			for (int j = 0; j < xwlbList.size(); j++) {
				HashMap<String,String> xwlbMap = xwlbList.get(j);
				String rcxwlbmc = xwlbMap.get("rcxwlbmc");
				ws.setRowView(row + 1, 600);
				ws.addCell(new Label(col, row + 1, rcxwlbmc, s12CentreBorderFormat));
				col++;
			}
			ws.mergeCells(colTemp, row, col - 1, row);
//			System.out.println("colTemp:"+colTemp + " (col - 1):"+(col - 1));
		}
		ws.addCell(new Label(col, row, "�ܷ�", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		ws.addCell(new Label(col, row, "��С���ȼ�", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		// ������
		int colSum = col;
		ws.setColumnView(colSum - 1, 12);
		ws.setColumnView(colSum - 2, 10);
		// ��5��
		row++;
		List<HashMap<String,String>> resultList = dao.rcxwykhDc_13696(nd, yf, bjdm);
		// ========== ������ < =============
		int listSize = 18 - resultList.size();
		for (int i = 0 ; i < listSize ; i++){
			resultList.add(new HashMap<String, String>());
		}	
		// ========== ������ > =============
		for (HashMap<String, String> resultMap : resultList) {
			col = 0; // ����
			row++;
			ws.setRowView(row, 390);
			ws.addCell(new Label(col, row, resultMap.get("xm"), s12CentreBorderFormat));
			col++;
			//��Ϊ����
			for (int i = 0; i < xwdlList.size(); i++) {
				HashMap<String,String> xwdlMap = xwdlList.get(i);
				String rcxwlbdldm = xwdlMap.get("rcxwlbdldm");
				// ��Ϊ���
				List<HashMap<String,String>> xwlbList = service.getXwlbList(rcxwlbdldm, null);
				for (int j = 0; j < xwlbList.size(); j++) {
					ws.addCell(new Label(col, row, resultMap.get("fzsum"+i+"_"+j), s12CentreBorderFormat));
					col++;
				}
			}
			ws.addCell(new Label(col, row, resultMap.get("fzsum"), s12CentreBorderFormat));
			col++;
			ws.addCell(new Label(col, row, resultMap.get("ydjmc"), s12CentreBorderFormat));
			col++;
		}
		// ��1��
		ws.setRowView(0, 705);
		ws.addCell(new Label(0, 0, Base.xxmc + "���������¿��˱�", s18CentreBOLDFormat));
		ws.mergeCells(0, 0, colSum - 1, 0);
		// ��2��
		int yfIndex = Arrays.binarySearch(YFDM_ARR, yf);
		ws.setRowView(1, 390);
		ws.addCell(new Label(0, 1, "        "+bjxxMap.get("xymc")+" "+bjxxMap.get("bjmc"), s12LeftFormat));
		int mid = (int)(colSum - 1) / 2;
		ws.mergeCells(0, 1, mid, 1);
		ws.addCell(new Label(mid + 1, 1, "����ʱ�䣺"+nd+"��"+YFMC_ARR[yfIndex], s12RightFormat));
		ws.mergeCells(mid + 1, 1, colSum - 1, 1);
		// ҳβ
		String msg1 = "��������׼˵���������㣺�¸����ۻ���0-5�������ߣ������ã��¸����ۻ���6-10�������ߣ��ۼ����¸����ۻ���30�֣�����30�֣������ߣ��������¸����ۻ���30����30�֣����ϼ�����Υ���ܴ�����";
		row++;
		ws.setRowView(row, 720);
		ws.addCell(new Label(0, row, msg1, s12RightBorderFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		String msg2 = "�¸����ۻ�˵������45-54���ߣ����辯�洦�ִ�����55-64���ߣ��������ؾ��洦�ִ�����65-74���ߣ�����ǹ����ִ���75������������ڸ�����У�쿴�򿪳�ѧ�����ִ���";
		row++;
		ws.setRowView(row, 720);
		ws.addCell(new Label(0, row, msg2, s12RightBottomFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "������ǩ�֣�             ϵ������ǩ�֣�               ", s11RightFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
