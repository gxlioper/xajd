package xgxt.xszz.zjyd;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.xmlg.wmbj.WmbjService;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzZjydService extends XszzService implements XszzCommTjbbService{
	XszzZjydDAO dao = new XszzZjydDAO();
	
	/**
	 * 国家奖学金学生名单备案表
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//国家奖学金
		List<String[]> data = dao.getGjjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("学年普通高等学校国家奖学金获奖学生初审名单表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家奖学金获奖学生初审名单表", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 400);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 8, 1);
			ws.addCell(new Label(0, 1, "学校名称：浙江邮电职业技术学院", btomTytle));
			ws.addCell(new Label(3,1,"（公章）", btomTytle));
			ws.addCell(new Label(6,1,"   填表日期：		年		月		日", btomTytle));
			
			
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份证号码", wcfTytle));
			ws.addCell(new Label(3, 2, "院系", wcfTytle));
			ws.addCell(new Label(4, 2, "专业", wcfTytle));
			ws.addCell(new Label(5, 2, "学号", wcfTytle));
			ws.addCell(new Label(6, 2, "性别", wcfTytle));
			ws.addCell(new Label(7, 2, "民族", wcfTytle));
			ws.addCell(new Label(8, 2, "入学年月", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "联系电话:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "传真:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "电子信箱:", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	public void printGjlzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//国家奖学金
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("学年普通高等学校国家励志奖学金获奖学生初审名单表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家励志奖学金获奖学生初审名单表", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 400);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 8, 1);
			ws.addCell(new Label(0, 1, "学校名称：浙江邮电职业技术学院", btomTytle));
			ws.addCell(new Label(3,1,"（公章）", btomTytle));
			ws.addCell(new Label(6,1,"   填表日期：		年		月		日", btomTytle));
			
			
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份证号码", wcfTytle));
			ws.addCell(new Label(3, 2, "院系", wcfTytle));
			ws.addCell(new Label(4, 2, "专业", wcfTytle));
			ws.addCell(new Label(5, 2, "学号", wcfTytle));
			ws.addCell(new Label(6, 2, "性别", wcfTytle));
			ws.addCell(new Label(7, 2, "民族", wcfTytle));
			ws.addCell(new Label(8, 2, "入学年月", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "联系电话:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "传真:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "电子信箱:", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//国家奖学金
		List<String[]> data = dao.getGjzxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("学年普通高等学校国家助学金获得者名单备案表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家助学金获得者名单备案表", 0);

		try {
			excel.printTitle(ws, title.toString(), 10, 400);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 2, 1);
			ws.mergeCells(6, 1, 9, 1);
			ws.addCell(new Label(0, 1, "学校名称：浙江邮电职业技术学院", btomTytle));
			ws.addCell(new Label(3,1,"（公章）", btomTytle));
			ws.addCell(new Label(6,1,"   填表日期：		年		月		日", btomTytle));
			
			
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份证号码", wcfTytle));
			ws.addCell(new Label(3, 2, "院系", wcfTytle));
			ws.addCell(new Label(4, 2, "专业", wcfTytle));
			ws.addCell(new Label(5, 2, "学号", wcfTytle));
			ws.addCell(new Label(6, 2, "性别", wcfTytle));
			ws.addCell(new Label(7, 2, "民族", wcfTytle));
			ws.addCell(new Label(8, 2, "入学年月", wcfTytle));
			ws.addCell(new Label(9, 2, "资助档次", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 1, data.size()+3);
			ws.mergeCells(2, data.size()+3, 3, data.size()+3);
			ws.mergeCells(4, data.size()+3, 5, data.size()+3);
			ws.mergeCells(6, data.size()+3, 9, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人:", btomTytle));
			ws.addCell(new Label(2, data.size()+3, "联系电话:", btomTytle));
			ws.addCell(new Label(4, data.size()+3, "传真:", btomTytle));
			ws.addCell(new Label(6, data.size()+3, "电子信箱:", btomTytle));
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
		if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家奖学金
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家励志奖学金
			printGjlzjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//国家助学金
			printGjzxjtjbb(form,os);
		}
	}
	
}
