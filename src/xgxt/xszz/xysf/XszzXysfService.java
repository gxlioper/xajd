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
	 * 打印资助统计报表
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if (XszzXmdm.XSZZ_TJBB_XYSF_LSTD.equalsIgnoreCase(form.getTjbbdm())) {// 绿色通道
			printLstdtjbb(form, os);
		} else if (XszzXmdm.XSZZ_TJBB_XYSF_KNS.equalsIgnoreCase(form.getTjbbdm())) {// 家庭经济困难学生认定
			printKnstjbb(form, os);
		}
	}

	/**
	 * 绿色通道
	 * 
	 * @param form
	 * @param os
	 */
	public void printLstdtjbb(XszzTyForm model, OutputStream os) {

		DAO dao = DAO.getInstance();
		XszzXysfDAO tjDao = new XszzXysfDAO();
		 
		String xmdm = XszzXmdm.XSZZ_LSTD;// 绿色通道
		String xydm = model.getXydm();// 学院代码
		String xymc = dao.getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		
		List<String[]> data = tjDao.getLstdData(xmdm, model);// 查询要导出的数据

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);

		try {

			StringBuilder title = new StringBuilder();
			title.append("信阳师范学院通过“绿色通道”入学新生汇总表");

			WritableSheet ws = wwb.createSheet("“绿色通道”入学新生汇总表", 0);

			// 填充标题
			ExcelMB ex = new ExcelMB();
			ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
					Border.NONE);

			ws.mergeCells(0, 0, 7, 1);
			
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式

			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			
			StringBuilder nrSb = new StringBuilder();
			nrSb.append("学院：");
			nrSb.append(xymc);
			nrSb.append("        （公章）        ");
			nrSb.append("填表人：                填表日期：      ");
			nrSb.append("年        月        日");
			ws.addCell(new Label(0, 2, nrSb.toString(), wcf2));
			ws.mergeCells(0, 2, 7, 2);
			
			ws.addCell(new Label(0, 3, "序号", wcf2));
			ws.addCell(new Label(1, 3, "姓名", wcf2));
			ws.addCell(new Label(2, 3, "准考证号", wcf2));
			ws.addCell(new Label(3, 3, "家庭住址", wcf2));
			ws.addCell(new Label(4, 3, "专业", wcf2));
			ws.addCell(new Label(5, 3, "班级", wcf2));
			ws.addCell(new Label(6, 3, "应缴金额（元）", wcf2));
			ws.addCell(new Label(7, 3, "缓缴金额（元）", wcf2));
			
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

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 家庭经济困难学生认定
	 * 
	 * @param form
	 * @param os
	 */
	public void printKnstjbb(XszzTyForm model, OutputStream os) {

		XszzXysfDAO dao = new XszzXysfDAO();
		// 项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;// 困难生
		// 项目名称
		String title = "家庭经济困难学生认定";

		List<HashMap<String, String>> topTr = dao.getTopTr("kns");
		ArrayList<String[]> list = dao.getKnsData(xmdm, model);

		try {
			expToExcel(title, topTr, list, os);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
