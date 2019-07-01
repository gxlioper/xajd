package xgxt.xszz.nnzy;

import java.io.OutputStream;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzNnzyService extends XszzCommService implements XszzCommTjbbService{
	

	/**
	 * 打印资助统计报表
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家奖学金
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家励志奖学金
			printGjlzjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//国家助学金
			printGjzxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_ZZQRMZFJXJ.equalsIgnoreCase(form.getTjbbdm())){//自治区人民政府奖学金
			printZzqrmzftjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_XZJXJ.equalsIgnoreCase(form.getTjbbdm())){//校长奖学金
			printXzjxjtjbb(form,os);
		}
	}
	
	/**
	 * 国家奖学金
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//国家奖学金
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("学年度普通高等学校国家奖学金获奖学生初审名单表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家奖学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "学校名称：               （公章）                                          填表日期：       年       月       日", btomTytle));
			
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份号码", wcfTytle));
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
			ws.mergeCells(0, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人：                        联系电话：                         传真：                         电子信箱：                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 国家励志奖学金
	 * @param form
	 * @param os
	 * */
	public void printGjlzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJLZJXJ;//国家励志奖学金
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("学年度普通高等学校国家励志奖学金获奖学生初审名单表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家励志奖学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "二级学院名称：               （公章）                                          填表日期：       年       月       日", btomTytle));
			
//			数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份号码", wcfTytle));
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
			ws.mergeCells(0, data.size()+3,8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人：                        联系电话：                         传真：                         电子信箱：                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 国家助学金
	 * @param form
	 * @param os
	 * */
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//国家助学金
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getGjzxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("学年度普通高等学校国家助学金获奖学生初审名单表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家助学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 9, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 8, 1);
			ws.addCell(new Label(0, 1, "二级学院名称：               （公章）                                          填表日期：       年       月       日", btomTytle));
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "公民身份号码", wcfTytle));
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
			ws.mergeCells(0, data.size()+3, 8, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "经办人：                        联系电话：                         传真：                         电子信箱：                  ", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 自治区人民政府奖学金
	 * @param form
	 * @param os
	 * */
	public void printZzqrmzftjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//国家助学金
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getZzqrmzfjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("自治区人民政府奖学金统计表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("自治区人民政府奖学金", 0);

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 10, 1);
			ws.addCell(new Label(0, 1, "二级学院名称：               （公章）                 填报人:         联系电话:               报送日期:        ", btomTytle));
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "性别", wcfTytle));
			ws.addCell(new Label(3, 2, "民族", wcfTytle));
			ws.addCell(new Label(4, 2, "出生年月", wcfTytle));
			ws.addCell(new Label(5, 2, "院系、班别", wcfTytle));
			ws.addCell(new Label(6, 2, "学号", wcfTytle));
			ws.addCell(new Label(7, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(8, 2, "家庭详细地址", wcfTytle));
			ws.addCell(new Label(9, 2, "本人联系电话", wcfTytle));
			ws.addCell(new Label(10, 2, "身份证号", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 校长奖学金
	 * @param form
	 * @param os
	 * */
	public void printXzjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//国家助学金
		XszzNnzyDao dao=new XszzNnzyDao();
		List<String[]> data = dao.getXzjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(Base.currXn);
		title.append("校长奖学金统计表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("校长奖学金统计表", 0);

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 10, 1);
			ws.addCell(new Label(0, 1, "二级学院名称：               （公章）                 填报人:         联系电话:               报送日期:        ", btomTytle));
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学生姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "性别", wcfTytle));
			ws.addCell(new Label(3, 2, "民族", wcfTytle));
			ws.addCell(new Label(4, 2, "出生年月", wcfTytle));
			ws.addCell(new Label(5, 2, "院系、班别", wcfTytle));
			ws.addCell(new Label(6, 2, "学号", wcfTytle));
			ws.addCell(new Label(7, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(8, 2, "家庭详细地址", wcfTytle));
			ws.addCell(new Label(9, 2, "本人联系电话", wcfTytle));
			ws.addCell(new Label(10, 2, "身份证号", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
