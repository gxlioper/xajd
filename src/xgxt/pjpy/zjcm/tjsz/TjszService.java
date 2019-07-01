package xgxt.pjpy.zjcm.tjsz;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;

public class TjszService {

	TjszDAO dao = new TjszDAO();

	/**
	 * @author luo
	 * @describe ��������ֶ��б�
	 */
	public List<HashMap<String, String>> getZdList(String szlx) {
		return dao.getZdList(szlx);
	}

	/**
	 * @describe ��ý�ѧ������б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getJxjList() {
		return dao.getJxjList();
	}

	/**
	 * @describe ��������ƺ�����б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getRychList() {
		return dao.getRychList();
	}
	
	/**
	 * @describe ������������
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveTjsz(TjszModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveTjsz(myModel, request);
	}

	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe �������
	 */
	public List<HashMap<String, String>> getTjList(TjszModel model,
			String[] colList) {
		return dao.getTjList(model, colList);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ɾ������
	 */
	public boolean delTj(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delTj(pk, request);
	}
	
	/**
	 * ѧԺ�ۺ����ʲ�����
	 */
	public void printXyzhExcel(TjszModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		String xqmc = dao.getXqmc(xq);
		String xymc = dao.getXymc(xydm);
		String bjmc = dao.getBjmc(bjdm);
		
		bjdm = Base.isNull(bjdm) ? "%" : bjdm;
		
		String title = xn + "ѧ��" + xqmc+ "ѧ��"+xymc+"ѧԺ�ۺ����ʲ��������ۺ�����˳���ű��";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list = dao.getXyzhList(bjdm, xn, xq, xqmc);
		HashMap<String, String> bjjxj = dao.getBjJxj(bjdm, xn, xq);
		
		WritableSheet ws = wwb.createSheet("ѧԺ�ۺ����ʲ�����", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 19, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		String bj = "�༶:"
				+ bjmc
				+ "                                                               "
				+ "�༶����:" + (bjjxj.get("num") == null ? "0" : bjjxj.get("num"))
				+ "��";
		ex.printToOneCell_multy(ws, bj, 0, 2, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(0, 2, 19, 2);

		String jxj = "һ�Ƚ�ѧ��:"
				+ (bjjxj.get("ydjxj") == null ? "0" : bjjxj.get("ydjxj"))
				+ "��                         " + "���Ƚ�ѧ��"
				+ (bjjxj.get("rdjxj") == null ? "0" : bjjxj.get("rdjxj"))
				+ "��                                      " + "���Ƚ�ѧ��"
				+ (bjjxj.get("sdjxj") == null ? "0" : bjjxj.get("sdjxj")) + "��";

		ex.printToOneCell_multy(ws, jxj, 0, 3, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(0, 3, 19, 3);

		String bzr = "������ǩ��:";
		ex.printToOneCell_multy(ws, bzr, 7, 4, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(7, 4, 8, 4);
		
		ws.addCell(new Label(0, 5, "ѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 5, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(8, 5, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(9, 5, "�ܷ�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(10, 5, "�ۺ�����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(11, 5, "��ѧ�𼰵��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(12, 5, "����ѧ�����Ÿ�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(13, 5, "����ҽ�ѧ�����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(14, 5, "ѧϰ�ɼ����޲�����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(15, 5, "Ӣ��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(16, 5, "������������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(17, 5, "������Ƿѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(18, 5, "�������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(19, 5, "�������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				
				String tqxf = map.get("tqxf");
				String bjgkms = map.get("bjgkms");
				String cfqk = map.get("num");
				String wjcs = map.get("wjcs");
				wjcs = Base.isNull(wjcs) ? "" : wjcs + "��";
				
				if("no".equalsIgnoreCase(tqxf)){
					tqxf = "��";
				}else if("yes".equalsIgnoreCase(map.get("tqxf"))){
					tqxf = "��";
				}else{
					tqxf = "";
				}
				if ("0".equalsIgnoreCase(bjgkms)) {
					bjgkms = "��";
				} else if (!Base.isNull(bjgkms)) {
					bjgkms = bjgkms + "��";
				} else {
					bjgkms = "";
				}
				
				if (!Base.isNull(cfqk) && !"0".equalsIgnoreCase(cfqk)) {
					cfqk = "��";
				}
				
				ws.addCell(new Label(0, 6+i, map.get("xh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 6+i, map.get("xm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 6+i, map.get("dyf"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 6+i, map.get("dypm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 6+i, map.get("zyf"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(5, 6+i, map.get("zypm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(6, 6+i, map.get("tyf"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(7, 6+i, map.get("nlf"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(8, 6+i, map.get("nlpm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(9, 6+i, map.get("zhf"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(10, 6+i, map.get("zhpm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(11, 6+i, map.get("xnmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(12, 6+i, map.get("rychmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(13, 6+i, map.get("xwmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(14, 6+i, bjgkms, wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(15, 6+i, map.get("yygjqk"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(16, 6+i, map.get("jsjgjqk"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(17, 6+i, tqxf, wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(18, 6+i, cfqk, wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(19, 6+i, wjcs, wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
//			
//			//excel�ϲ���Ԫ��
////			int m = 1;
//			
////			boolean a = false;
//			
////			for (int i = 0; i <= list.size(); i++) {
////				String a3 = "";
////				String a4 = "";
////
////				WritableCell a1 = ws.getWritableCell(0, 3 + i);
////
////				if (i > 0) {
////					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
////					a4 = a2.getContents();
////
////				}
////				a3 = a1.getContents();
////				
////				if (a3.equals(a4)) {
////					m++;
////					a = true;
////				}
////				
////				if ((!a3.equals(a4)) && a) {
////					ws.mergeCells(0, 3 + i - m, 0, 3 + i - 1);
////					ws.mergeCells(1, 3 + i - m, 1, 3 + i - 1);
////					ws.mergeCells(2, 3 + i - m, 2, 3 + i - 1);
////					ws.mergeCells(3, 3 + i - m, 3, 3 + i - 1);
////					ws.mergeCells(4, 3 + i - m, 4, 3 + i - 1);
////					ws.mergeCells(5, 3 + i - m, 5, 3 + i - 1);
////					ws.mergeCells(6, 3 + i - m, 6, 3 + i - 1);
////					ws.mergeCells(7, 3 + i - m, 7, 3 + i - 1);
////					ws.mergeCells(8, 3 + i - m, 8, 3 + i - 1);
////					ws.mergeCells(9, 3 + i - m, 9, 3 + i - 1);
////					ws.mergeCells(10, 3 + i - m, 10, 3 + i - 1);
////					ws.mergeCells(11, 3 + i - m, 11, 3 + i - 1);
////					ws.mergeCells(12, 3 + i - m, 12, 3 + i - 1);
////					//ws.mergeCells(13, 3 + i - m, 13, 3 + i - 1);
////					ws.mergeCells(14, 3 + i - m, 14, 3 + i - 1);
////					ws.mergeCells(15, 3 + i - m, 15, 3 + i - 1);
////					ws.mergeCells(16, 3 + i - m, 16, 3 + i - 1);
////					ws.mergeCells(17, 3 + i - m, 17, 3 + i - 1);
////					ws.mergeCells(18, 3 + i - m, 18, 3 + i - 1);
////					ws.mergeCells(19, 3 + i - m, 19, 3 + i - 1);
////					m = 1;
////					a=false;
////				}
////			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ѧԺ���������������ܱ�
	 */
	public void printXyPymdExcel(TjszModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		//String bjdm = model.getBjdm();
		String xqmc = dao.getXqmc(xq);
		String xymc = dao.getXymc(xydm);
		
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		
		String title = "�㽭��ýѧԺ" + xn + "ѧ��" + xqmc + "ѧ��" + xymc + "���������������ܱ�("+time+")";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list = dao.getXyPymdList(xydm, xn, xq);
		
		WritableSheet ws = wwb.createSheet("ѧԺ���������������ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);	

		ws.addCell(new Label(0, 2, "�༶", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 2, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 2, "Ժ�ڽ�ѧ������(һ����������)", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 2, "����ѧ�������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 2, "����ѧ���ɲ�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 2, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 2, "Ժ�⽱ѧ������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 2, "ѧ�����п���", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(8, 2, "��ע(���Ƚ��༶��)", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				
				ws.addCell(new Label(0, 3+i, map.get("bjmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 3+i, map.get("xm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 3+i, map.get("djjxj"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 3+i, map.get("shxs"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 3+i, map.get("yxgb"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(5, 3+i, map.get("dxjxj"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(6, 3+i, map.get("xwjxj"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(7, 3+i, map.get("kh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(8, 3+i, "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
			
			//excel�ϲ���Ԫ��
			int m = 1;
			
			boolean a = false;
			
			for (int i = 0; i <= list.size(); i++) {
				String a3 = "";
				String a4 = "";

				WritableCell a1 = ws.getWritableCell(0, 3 + i);

				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
					a4 = a2.getContents();

				}
				a3 = a1.getContents();
				
				if (a3.equals(a4)) {
					m++;
					a = true;
				}
				
				if ((!a3.equals(a4)) && a) {
					ws.mergeCells(0, 3 + i - m, 0, 3 + i - 1);
					m = 1;
					a=false;
				}
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
