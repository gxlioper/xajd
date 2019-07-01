package xgxt.xszz.zjkj;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.comm.XszzCommTjbbService;

public class XszzZjkjService extends XszzCommService implements XszzCommTjbbService{
	
	private XszzZjkjDAO dao = new XszzZjkjDAO();
	
	/**
	 * 困难生统计报表
	 * @param form
	 * @param os
	 * */
	public void printKnstjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_KNS;//困难生
		List<String[]> data = dao.getKnsData(xmdm, model);//查询导出的数据

		String title = "家庭经济困难学生信息汇总表";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("家庭经济困难学生信息汇总表", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			//数据输出
			ws.addCell(new Label(0, 1, "序号", wcfTytle));
			ws.addCell(new Label(1, 1, "二级学院", wcfTytle));
			ws.addCell(new Label(2, 1, "班级", wcfTytle));
			ws.addCell(new Label(3, 1, "学号", wcfTytle));
			ws.addCell(new Label(4, 1, "姓名", wcfTytle));
			ws.addCell(new Label(5, 1, "本人联系电话", wcfTytle));
			ws.addCell(new Label(6, 1, "家庭人口总数", wcfTytle));
			ws.addCell(new Label(7, 1, "家庭年纯收入", wcfTytle));
			ws.addCell(new Label(8, 1, "家庭详细住址", wcfTytle));
			ws.addCell(new Label(9, 1, "家庭联系电话", wcfTytle));
			ws.addCell(new Label(10, 1, "家庭经济困难情况", wcfTytle));
			ws.addCell(new Label(11, 1, "上学年学习成绩不合格门数", wcfTytle));
			ws.addCell(new Label(12, 1, "困难等级", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 2 + i, data.get(i)[j], wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/***
	 * 打印资助所有的统计报表
	 * @param form
	 * @param os
	 * */
	
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		String xxdm = Base.xxdm;
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			printZgddBb(form, os);
		} else {
			if (XszzXmdm.XSZZ_TJBB_KNS.equalsIgnoreCase(form.getTjbbdm())) {// 困难生统计
				printKnstjbb(form, os);
			}
		}
	}	
	
	/**
	 * 中国地大报表打印
	 * 
	 * @param form
	 * @param os
	 */
	public void printZgddBb(XszzTyForm model, OutputStream os) {

		XszzZjkjDAO dao = new XszzZjkjDAO();
		
		String xmdm = model.getTjbbdm();// 项目代码
		// 项目相关信息
		model.setXmdm(xmdm);
		model.setPkValue(xmdm);
		HashMap<String, String> map = getXmxgInfo(model);
		// 项目表
		String xmb = map.get("xmb");
		// 导出字段
		String[] outValue = null;
		try {
			outValue = getOutValue(getTableZd(xmb));
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		
		List<String[]> data = dao.getZgddZzList(xmdm, map, outValue, model);//查询导出的数据

		String title = getOneValue("xszz_zzxmb", "xmmc", "xmdm", xmdm)
				+ "信息汇总表";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title.toString(), 16, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			//数据输出
			//ws.addCell(new Label(0, 1, "申请奖项", wcfTytle));
			ws.addCell(new Label(0, 1, "序号", wcfTytle));
			ws.addCell(new Label(1, 1, "学号", wcfTytle));
			ws.addCell(new Label(2, 1, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(3, 1, "姓名", wcfTytle));
			ws.addCell(new Label(4, 1, "性别", wcfTytle));
			ws.addCell(new Label(5, 1, "计算机水平", wcfTytle));
			ws.addCell(new Label(6, 1, "外语水平", wcfTytle));
			ws.addCell(new Label(7, 1, "上半学期学分绩点", wcfTytle));
			ws.addCell(new Label(8, 1, "下半学期学分绩点", wcfTytle));
			ws.addCell(new Label(9, 1, "综合排名", wcfTytle));
			ws.addCell(new Label(10, 1, "家庭情况", wcfTytle));
			ws.addCell(new Label(11, 1, "申请理由", wcfTytle));
			ws.addCell(new Label(12, 1, "最近一年单人社会职务及参加社会活动情况", wcfTytle));
			ws.addCell(new Label(13, 1, "身份证号", wcfTytle));
			ws.addCell(new Label(14, 1, "科研项目及发表论文", wcfTytle));
			ws.addCell(new Label(15, 1, "备注", wcfTytle));


			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 2 + i, data.get(i)[j], wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
