package xgxt.xszz.comm.exp;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;
import common.XszzXmdm;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;

public class XszzExpService extends XszzCommService {

	XszzExpDAO dao = new XszzExpDAO();
	
	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expInfo(XszzTyForm model, OutputStream os) throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		model.setXmb(xmb);
		// ��Ŀ����
		String title = xmInfo.get("xmmc");

		List<HashMap<String, String>> topTr = dao.getTopTr(xxdm, xmdm);
		ArrayList<String[]> list = null;
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
			list = dao.getZgddExpList(model, xmInfo);
		} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//�人��ҵ����
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {
				list = dao.getWhsyGjzxjExpList(model, xmInfo);
			} else {
				list = dao.getWhsyExpList(model, xmInfo);
			}
		}else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)) {//����ũҵ��ѧ
			
			if (XszzXmdm.XSZZ_HZNY_LSTD.equalsIgnoreCase(xmdm)) {
				list = dao.getHznyLstdExpList(model, xmInfo);
			}
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//����ʦ��ѧԺ
			//��½����Ӣ��������ѧ��&��ӭ�����ˡ���ѧ��
			if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) {
				list = dao.getHzsfLhyyAndYnsrExpList(model, xmInfo);
			}else{
				list = dao.getXysfExpList(model, xmInfo);
			}
		} else {
			list = dao.getXysfExpList(model, xmInfo);
		}
		
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//����ʦ��ѧԺ
			//��½����Ӣ��������ѧ��&��ӭ�����ˡ���ѧ��
			if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) {
				expToExcelByHzsf(title, topTr, list, os);
			}else{
				expToExcel(title, topTr, list, os);
			}
		}else{
			expToExcel(title, topTr, list, os);
		}
	}
	
	/**
	 * <p>���EXCEL</p>
	 * <p>����ʦ�����Ի�</p>
	 * <p>��½����Ӣ��������ѧ��&��ӭ�����ˡ���ѧ��</p>
	 * @param title
	 * @param topTr
	 * @param list
	 * @param os
	 * @throws Exception
	 * @author honglin
	 * @date 2013-04-11
	 */
	public void expToExcelByHzsf(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);
		
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		ws.addCell(new Label(0, 0, title+"��ѡ�˻������һ����", wcf));
		ws.mergeCells(0, 0, 6, 0);
		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}

		// �������
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}