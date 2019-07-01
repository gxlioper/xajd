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
	 * @describe 获得条件字段列表
	 */
	public List<HashMap<String, String>> getZdList(String szlx) {
		return dao.getZdList(szlx);
	}

	/**
	 * @describe 获得奖学金类别列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getJxjList() {
		return dao.getJxjList();
	}

	/**
	 * @describe 获得荣誉称号类别列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getRychList() {
		return dao.getRychList();
	}
	
	/**
	 * @describe 保存条件设置
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
	 * @describe 获得条件
	 */
	public List<HashMap<String, String>> getTjList(TjszModel model,
			String[] colList) {
		return dao.getTjList(model, colList);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 删除条件
	 */
	public boolean delTj(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delTj(pk, request);
	}
	
	/**
	 * 学院综合素质测评表
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
		
		String title = xn + "学年" + xqmc+ "学期"+xymc+"学院综合素质测评表（按综合排名顺序安排表格）";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// 填充内容
		List<HashMap<String, String>> list = dao.getXyzhList(bjdm, xn, xq, xqmc);
		HashMap<String, String> bjjxj = dao.getBjJxj(bjdm, xn, xq);
		
		WritableSheet ws = wwb.createSheet("学院综合素质测评表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 19, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		String bj = "班级:"
				+ bjmc
				+ "                                                               "
				+ "班级人数:" + (bjjxj.get("num") == null ? "0" : bjjxj.get("num"))
				+ "人";
		ex.printToOneCell_multy(ws, bj, 0, 2, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(0, 2, 19, 2);

		String jxj = "一等奖学金:"
				+ (bjjxj.get("ydjxj") == null ? "0" : bjjxj.get("ydjxj"))
				+ "人                         " + "二等奖学金"
				+ (bjjxj.get("rdjxj") == null ? "0" : bjjxj.get("rdjxj"))
				+ "人                                      " + "三等奖学金"
				+ (bjjxj.get("sdjxj") == null ? "0" : bjjxj.get("sdjxj")) + "人";

		ex.printToOneCell_multy(ws, jxj, 0, 3, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(0, 3, 19, 3);

		String bzr = "班主任签名:";
		ex.printToOneCell_multy(ws, bzr, 7, 4, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		ws.mergeCells(7, 4, 8, 4);
		
		ws.addCell(new Label(0, 5, "学号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 5, "姓名", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 5, "德育积分", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 5, "德育名次", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 5, "智育积分", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(5, 5, "智育名次", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(6, 5, "体育积分", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(7, 5, "能力积分", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(8, 5, "能力名次", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(9, 5, "总分", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(10, 5, "综合排名", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(11, 5, "奖学金及单项奖", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(12, 5, "三好学生及优干", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(13, 5, "获国家奖学金情况", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(14, 5, "学习成绩有无不及格", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(15, 5, "英语过级情况", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(16, 5, "计算机过级情况", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(17, 5, "有无拖欠学费", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(18, 5, "处分情况", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(19, 5, "旷课情况", wcf2)); // 添加有指定格式的单元格值
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				
				String tqxf = map.get("tqxf");
				String bjgkms = map.get("bjgkms");
				String cfqk = map.get("num");
				String wjcs = map.get("wjcs");
				wjcs = Base.isNull(wjcs) ? "" : wjcs + "次";
				
				if("no".equalsIgnoreCase(tqxf)){
					tqxf = "无";
				}else if("yes".equalsIgnoreCase(map.get("tqxf"))){
					tqxf = "有";
				}else{
					tqxf = "";
				}
				if ("0".equalsIgnoreCase(bjgkms)) {
					bjgkms = "无";
				} else if (!Base.isNull(bjgkms)) {
					bjgkms = bjgkms + "门";
				} else {
					bjgkms = "";
				}
				
				if (!Base.isNull(cfqk) && !"0".equalsIgnoreCase(cfqk)) {
					cfqk = "有";
				}
				
				ws.addCell(new Label(0, 6+i, map.get("xh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 6+i, map.get("xm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2, 6+i, map.get("dyf"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3, 6+i, map.get("dypm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4, 6+i, map.get("zyf"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(5, 6+i, map.get("zypm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(6, 6+i, map.get("tyf"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(7, 6+i, map.get("nlf"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(8, 6+i, map.get("nlpm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(9, 6+i, map.get("zhf"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(10, 6+i, map.get("zhpm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(11, 6+i, map.get("xnmc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(12, 6+i, map.get("rychmc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(13, 6+i, map.get("xwmc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(14, 6+i, bjgkms, wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(15, 6+i, map.get("yygjqk"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(16, 6+i, map.get("jsjgjqk"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(17, 6+i, tqxf, wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(18, 6+i, cfqk, wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(19, 6+i, wjcs, wcf2)); // 添加有指定格式的单元格值
			}
//			
//			//excel合并单元格
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
	 * 学院评奖评优名单汇总表
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
		
		String title = "浙江传媒学院" + xn + "学年" + xqmc + "学期" + xymc + "评奖评优名单汇总表("+time+")";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// 填充内容
		List<HashMap<String, String>> list = dao.getXyPymdList(xydm, xn, xq);
		
		WritableSheet ws = wwb.createSheet("学院评奖评优名单汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);	

		ws.addCell(new Label(0, 2, "班级", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 2, "姓名", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 2, "院内奖学金获得者(一、二、三等)", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 2, "三好学生获得者", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 2, "优秀学生干部", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(5, 2, "单项奖获得者", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(6, 2, "院外奖学金获得者", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(7, 2, "学生建行卡号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(8, 2, "备注(如先进班级等)", wcf2)); // 添加有指定格式的单元格值
	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				
				ws.addCell(new Label(0, 3+i, map.get("bjmc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 3+i, map.get("xm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2, 3+i, map.get("djjxj"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3, 3+i, map.get("shxs"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4, 3+i, map.get("yxgb"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(5, 3+i, map.get("dxjxj"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(6, 3+i, map.get("xwjxj"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(7, 3+i, map.get("kh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(8, 3+i, "", wcf2)); // 添加有指定格式的单元格值
			}
			
			//excel合并单元格
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
