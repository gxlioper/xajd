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
	 * @describe 获得辅导员月评活动字段列表
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
	 * @describe 保存辅导员月评活动字段设置
	 * @author luo
	 */
	public boolean saveCssz(FdyypModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveCssz(myModel, request);
	}

	/**
	 * @describe 获得辅导员月评活动字段
	 * @author luo
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) {
		return dao.getCssz(tableName, colList, pk, pkValue);
	}

	/**
	 * @describe 删除辅导员月评活动字段
	 * @author luo
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delCssz(pk, request);
	}

	/**
	 * @describe 获得辅导员月评申报自定义字段
	 * @author luo
	 */
	public List<HashMap<String, String>> getZdyZd(String xn, String xq) {
		return dao.getFdyypZdyZd(xn, xq);
	}

	/**
	 * @describe 保存辅导员月评申报内容
	 * @author luo
	 */
	public boolean saveFdyypsb(FdyypModel myModel, String[] zdyZd,
			String[] zdyZdz, HttpServletRequest request) throws Exception {
		return dao.saveFdyypsb(myModel, zdyZd, zdyZdz, request);
	}

	/**
	 * @describe 获得申报学生基本信息
	 * @author luo
	 */
	public HashMap<String, String> getSbrXx(String xh) {
		return dao.getSbrXx(xh);
	}

	/**
	 * @describe 获得辅导员月评申报列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyypList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyypList(tableName, model, colList);
	}

	/**
	 * @describe 删除辅导员月评信息
	 * @author luo
	 */
	public boolean delFdyyp(String[] key) throws Exception {
		return dao.delFdyyp(key);
	}

	/**
	 * @describe 是否系分管书记
	 * @author luo
	 */
	public boolean isXfgsj(String zgh) {
		return dao.isXfgsj(zgh);
	}

	/**
	 * @describe 获得辅导员列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyList(tableName, model, colList);
	}

	/**
	 * @describe 获得辅导员信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyxx(String[] colList, String zgh) {
		return dao.getFdyxx(colList, zgh);
	}

	/**
	 * @describe 获得申报人信息
	 * @author luo
	 */
	public HashMap<String, String> getSbrxx(String zgh) {
		return dao.getSbrxx(zgh);
	}
	
	/**
	 * @describe 获得辅导员月评信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyypXx(String pk) {
		return dao.getFdyypXx(pk);
	}
	
	/**
	 * 辅导员月评汇总表打印
	 * @author luo
	 */
	public void fdyypHz(FdyypModel model, OutputStream os)
			throws Exception {

		String[] month = {"","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
		
		String nd = model.getHzxn();
		int kssj = Integer.parseInt(model.getKssj());
		int jssj = Integer.parseInt(model.getJssj());
		int num = jssj - kssj;
		String title = "辅导员月评汇总表";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// 填充内容
		List<HashMap<String, String>> list = dao.getFdyypHzList(nd, kssj, jssj);
		
		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4 + num, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// 表头
		ws.addCell(new Label(0, 2, "姓名", wcf2)); // 姓名
		for (int i = 0; i <= num; i++) {
			ws.addCell(new Label(i + 1, 2, nd + "." + (kssj + i), wcf2)); // 汇总月份
		}
		ws.addCell(new Label(num+2, 2, "平均分", wcf2)); // 总分
		ws.addCell(new Label(num+3, 2, "总分", wcf2)); // 平均分
		ws.addCell(new Label(num+4, 2, "名次", wcf2)); // 名次

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				ws.addCell(new Label(0, 3 + i, map.get("xm"), wcf2)); // 姓名
				for (int j = 0; j <= num; j++) {
					ws.addCell(new Label(1+j, 3+i, map.get(month[kssj + j]), wcf2)); // 汇总月份
				}
				ws.addCell(new Label(num+2, 3+i, map.get("avgfs"), wcf2)); // 总分
				ws.addCell(new Label(num+3, 3+i, map.get("sumfs"), wcf2)); // 平均分
				ws.addCell(new Label(num+4, 3+i, map.get("pm"), wcf2)); // 名次
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	
	/**
	 * @describe 获得辅导员过失列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyGsList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getFdyGsList(tableName, model, colList);
	}
	
	/**
	 * @describe 保存辅导员重大过失
	 * @author luo
	 */
	public boolean saveFdyGs(FdyypModel myModel, String userName,
			HttpServletRequest request) throws Exception {
		return dao.saveFdyGs(myModel, userName, request);
	}
	
	/**
	 * @describe 获得辅导员过失信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyGsInfo(String pk) {
		return dao.getFdyGsInfo(pk);
	}
}
