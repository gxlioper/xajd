package xgxt.szdw.xmlg.fdyyp;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.utils.ExcelMethods;

public class FdyypService {

	FdyypDAO dao = new FdyypDAO();

	/**
	 * @describe ��ø���Ա������ֶ��б�
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @author luo
	 */
	public ArrayList<String[]> getCsszList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getCsszList(tableName, model, colList);
	}

	/**
	 * @describe ���渨��Ա������ֶ�����
	 * @author luo
	 */
	public boolean saveCssz(FdyypModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveCssz(myModel, request);
	}

	/**
	 * @describe ��ø���Ա������ֶ�
	 * @author luo
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) {
		return dao.getCssz(tableName, colList, pk, pkValue);
	}

	/**
	 * @describe ɾ������Ա������ֶ�
	 * @author luo
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delCssz(pk, request);
	}

	/**
	 * @describe ��ø���Ա�����걨�Զ����ֶ�
	 * @author luo
	 */
	public List<HashMap<String, String>> getZdyZd(String xn, String xq) {
		return dao.getFdyypZdyZd(xn, xq);
	}

	/**
	 * @describe ���渨��Ա�����걨����
	 * @author luo
	 */
	public boolean saveFdyypsb(FdyypModel myModel, String[] zdyZd,
			String[] zdyZdz, HttpServletRequest request) throws Exception {
		return dao.saveFdyypsb(myModel, zdyZd, zdyZdz, request);
	}

	/**
	 * @describe ����걨ѧ��������Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getSbrXx(String xh) {
		return dao.getSbrXx(xh);
	}

	/**
	 * @describe ��ø���Ա�����걨�б�
	 * @author luo
	 */
	public ArrayList<String[]> getFdyypList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyypList(tableName, model, colList);
	}

	/**
	 * @describe ɾ������Ա������Ϣ
	 * @author luo
	 */
	public boolean delFdyyp(String[] key) throws Exception {
		return dao.delFdyyp(key);
	}

	/**
	 * @describe �Ƿ�ϵ�ֹ����
	 * @author luo
	 */
	public boolean isXfgsj(String zgh) {
		return dao.isXfgsj(zgh);
	}

	/**
	 * @describe ��ø���Ա�б�
	 * @author luo
	 */
	public ArrayList<String[]> getFdyList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyList(tableName, model, colList);
	}

	/**
	 * @describe ��ø���Ա��Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getFdyxx(String[] colList, String zgh) {
		return dao.getFdyxx(colList, zgh);
	}

	/**
	 * @describe ����걨����Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getSbrxx(String zgh) {
		return dao.getSbrxx(zgh);
	}
	
	/**
	 * @describe ��ø���Ա������Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getFdyypXx(String pk) {
		return dao.getFdyypXx(pk);
	}
	
	/**
	 * ����Ա�������ܱ��ӡ
	 * @author luo
	 */
	public void fdyypHz(FdyypModel model, OutputStream os)
			throws Exception {

		String[] month = {"","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
		
		String nd = model.getHzxn();
		int kssj = Integer.parseInt(model.getKssj());
		int jssj = Integer.parseInt(model.getJssj());
		int num = jssj - kssj;
		String title = "����Ա�������ܱ�";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list = dao.getFdyypHzList(nd, kssj, jssj);
		
		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4 + num, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ��ͷ
		ws.addCell(new Label(0, 2, "����", wcf2)); // ����
		for (int i = 0; i <= num; i++) {
			ws.addCell(new Label(i + 1, 2, nd + "." + (kssj + i), wcf2)); // �����·�
		}
		ws.addCell(new Label(num+2, 2, "ƽ����", wcf2)); // �ܷ�
		ws.addCell(new Label(num+3, 2, "�ܷ�", wcf2)); // ƽ����
		ws.addCell(new Label(num+4, 2, "����", wcf2)); // ����

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				ws.addCell(new Label(0, 3 + i, map.get("xm"), wcf2)); // ����
				for (int j = 0; j <= num; j++) {
					ws.addCell(new Label(1+j, 3+i, map.get(month[kssj + j]), wcf2)); // �����·�
				}
				ws.addCell(new Label(num+2, 3+i, map.get("avgfs"), wcf2)); // �ܷ�
				ws.addCell(new Label(num+3, 3+i, map.get("sumfs"), wcf2)); // ƽ����
				ws.addCell(new Label(num+4, 3+i, map.get("pm"), wcf2)); // ����
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	
	/**
	 * @describe ��ø���Ա��ʧ�б�
	 * @author luo
	 */
	public ArrayList<String[]> getFdyGsList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyGsList(tableName, model, colList);
	}
	
	/**
	 * @describe ���渨��Ա�ش��ʧ
	 * @author luo
	 */
	public boolean saveFdyGs(FdyypModel myModel, String userName,
			HttpServletRequest request) throws Exception {
		return dao.saveFdyGs(myModel, userName, request);
	}
	
	/**
	 * @describe ��ø���Ա��ʧ��Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getFdyGsInfo(String pk) {
		return dao.getFdyGsInfo(pk);
	}
}
