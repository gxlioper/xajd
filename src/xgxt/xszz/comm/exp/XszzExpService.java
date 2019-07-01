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
	 * 导出数据
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expInfo(XszzTyForm model, OutputStream os) throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = model.getXmdm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// 项目表
		String xmb = xmInfo.get("xmb");
		model.setXmb(xmb);
		// 项目名称
		String title = xmInfo.get("xmmc");

		List<HashMap<String, String>> topTr = dao.getTopTr(xxdm, xmdm);
		ArrayList<String[]> list = null;
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
			list = dao.getZgddExpList(model, xmInfo);
		} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//武汉商业服务
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {
				list = dao.getWhsyGjzxjExpList(model, xmInfo);
			} else {
				list = dao.getWhsyExpList(model, xmInfo);
			}
		}else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)) {//华中农业大学
			
			if (XszzXmdm.XSZZ_HZNY_LSTD.equalsIgnoreCase(xmdm)) {
				list = dao.getHznyLstdExpList(model, xmInfo);
			}
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//湖州师范学院
			//“陆侯燕英”帮困奖学金&“迎南树人”奖学金
			if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) {
				list = dao.getHzsfLhyyAndYnsrExpList(model, xmInfo);
			}else{
				list = dao.getXysfExpList(model, xmInfo);
			}
		} else {
			list = dao.getXysfExpList(model, xmInfo);
		}
		
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//湖州师范学院
			//“陆侯燕英”帮困奖学金&“迎南树人”奖学金
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
	 * <p>输出EXCEL</p>
	 * <p>湖州师范个性化</p>
	 * <p>“陆侯燕英”帮困奖学金&“迎南树人”奖学金</p>
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
		
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		ws.addCell(new Label(0, 0, title+"候选人基本情况一览表", wcf));
		ws.mergeCells(0, 0, 6, 0);
		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}

		// 填充内容
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