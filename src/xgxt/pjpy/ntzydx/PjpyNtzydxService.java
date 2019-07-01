package xgxt.pjpy.ntzydx;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhszcpDAO;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhszcpService;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyNtzydxService {
	
	/**
	 * 奖学金条件判断
	 * @param jxjpdModel
	 * @param lb
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> pdStuTjFlag(JxjpdxxModel model, String lb){
		String msg = "";//操作提示信息
		boolean result = true;//操作结果
		HashMap<String, String> tjjcMap = new HashMap<String, String>();
		//申请一等奖学金必须本学年每学期都获得了一等综合测评奖学金，同理二等、三等也如此
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		//同等级综测奖项数
		int tdjzcjxs = dao.getXnzhcpJxjs(model);
		if(tdjzcjxs<2){
			result = false;
			msg = "学年内必须两次获得同等级以上综合测评奖学金才可以申请该奖学金！";
		}
		
		tjjcMap.put("message", msg);
		tjjcMap.put("result", String.valueOf(result));		
		return tjjcMap;
	}
	
	/**
	 * 综合测评统计报表打印
	 * @param model
	 * @param type
	 * @param os
	 * @throws Exception 
	 * */
	public void printZhcptjbb(PjpyZhszcpwhActionForm model, String type,OutputStream os) throws Exception{
		if("1".equalsIgnoreCase(type)){
			//平均学分绩点统计
			printPjxfjdbb(model,os);
		}else if("2".equalsIgnoreCase(type)){
			//综合测评各项目统计
			printZhcpxmfbb(model,os);
		}
	}
	
	/**
	 * 平均学分绩点统计表打印
	 * @param model
	 * @param os
	 * */
	public void printPjxfjdbb(PjpyZhszcpwhActionForm model, OutputStream os){
		String title = "综合测评统计表";
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		//学生成绩和综测分
		List<HashMap<String, String>> data = dao.queryXscjxx(model);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("综合测评统计表", 0);

		try {
			excel.printTitle(ws, title.toString(), 7, 800);// 标题	
			//数据单元格样式
			WritableCellFormat dataWcf = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式	
								

			//写表头
			ws.addCell(new Label(0, 1, "序号", dataWcf));
			ws.addCell(new Label(1, 1, "系  科", dataWcf));
			ws.addCell(new Label(2, 1, "班  级", dataWcf));
			ws.addCell(new Label(3, 1, "学  号", dataWcf));
			ws.addCell(new Label(4, 1, "姓  名", dataWcf));
			ws.addCell(new Label(5, 1, "平均成绩学分绩点", dataWcf));
			ws.addCell(new Label(6, 1, "综合测评成绩", dataWcf));
			
			
			
			//数据输出
			for (int i = 0; i < data.size(); i++) {
				ws.addCell(new Label(0, 2 + i, (i+1)+"", dataWcf));
				ws.addCell(new Label(1, 2 + i, data.get(i).get("xymc"), dataWcf));
				ws.addCell(new Label(2, 2 + i, data.get(i).get("bjmc"), dataWcf));
				ws.addCell(new Label(3, 2 + i, data.get(i).get("xh"), dataWcf));
				ws.addCell(new Label(4, 2 + i, data.get(i).get("xm"), dataWcf));
				ws.addCell(new Label(5, 2 + i, data.get(i).get("pjxfjd"), dataWcf));
				ws.addCell(new Label(6, 2 + i, data.get(i).get("zf"), dataWcf));			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 综合测评统计表打印
	 * @param model
	 * @param os
	 * @throws Exception 
	 * */
	public void printZhcpxmfbb(PjpyZhszcpwhActionForm model, OutputStream os) throws Exception{
		String title = "南通职业大学学生综合素质测评成绩统计表";
		String zczq = "xq";
		String jb = "4";
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyZhszcpService service = new PjpyZhszcpService();
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		
		model.setQueryequals_xn(StringUtils.isNull(model.getQueryequals_xn()) ? Base.getJxjsqxn() : model.getQueryequals_xn());
		model.setQueryequals_xq(StringUtils.isNull(model.getQueryequals_xq()) ? Base.getJxjsqxq() : model.getQueryequals_xq());
		
		HashMap<String, String> titleMap = dao.getZhcpxmfTitle(model);
		//获取查询的字段信息
		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(zczq,
																		jb, 
																		map, 
																		true, 
																		model);
		//查询综合测评分数据
		List<String[]> data = dao.queryZhszcpzfForExp(zczq, 
				                                          jb, 
				                                          map, 
				                                          colMap.get("en"), 
				                                          model, 
				                                          false);
		
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("综合素质测评成绩统计表", 0);

		try {
			excel.printTitle(ws, title.toString(), 20, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			// 副标题左单元格样式
			WritableCellFormat fbtLWcf = ExcelMB.getWritableCellFormat(10,
					true, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);
			// 副标题右单元格样式
			WritableCellFormat fbtRWcf = ExcelMB.getWritableCellFormat(10,
					true, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.NONE);
			//数据单元格样式
			WritableCellFormat dataWcf = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.addCell(new Label(0, 1, titleMap.get("xymc")+titleMap.get("bjmc")+"（系部盖章）", fbtLWcf));
			ws.mergeCells(0, 1, 16, 1);
			ws.addCell(new Label(17, 1, model.getQueryequals_xn()+"学年第" + titleMap.get("xqmc")+ "学期", fbtRWcf));
			ws.mergeCells(17, 1, 20, 1);
			
			//写表头
			ws.addCell(new Label(0, 2, "", wcfTytle));
			ws.mergeCells(0, 2, 0, 4);
			ws.addCell(new Label(1, 2, "德  育  素  质  30分", wcfTytle));
			ws.mergeCells(1, 2, 8, 2);
			ws.addCell(new Label(9, 2, "智  育  素  质  60分", wcfTytle));
			ws.mergeCells(9, 2, 13, 2);
			ws.addCell(new Label(14, 2, "体  育  素  质  10分", wcfTytle));
			ws.mergeCells(14, 2, 18, 2);
			ws.addCell(new Label(19, 2, "综合测评总分", wcfTytle));
			ws.mergeCells(19, 2, 19, 4);
			ws.addCell(new Label(20, 2, "名次", wcfTytle));
			ws.mergeCells(20, 2, 20, 4);
			
			ws.addCell(new Label(1, 3, "思想	品德6分", wcfTytle));
			ws.mergeCells(1, 3, 1, 4);
			ws.addCell(new Label(2, 3, "组织纪律5分", wcfTytle));
			ws.mergeCells(2, 3, 2, 4);
			ws.addCell(new Label(3, 3, "学习态度4分", wcfTytle));
			ws.mergeCells(3, 3, 3, 4);
			ws.addCell(new Label(4, 3, "集体观念5分", wcfTytle));
			ws.mergeCells(4, 3, 4, 4);
			ws.addCell(new Label(5, 3, "日常行为", wcfTytle));
			ws.mergeCells(5, 3, 7, 3);
			ws.addCell(new Label(5, 4, "基本分", wcfTytle));
			ws.addCell(new Label(6, 4, "加分", wcfTytle));
			ws.addCell(new Label(7, 4, "减分", wcfTytle));
			ws.addCell(new Label(8, 3, "小计", wcfTytle));
			ws.mergeCells(8, 3, 8, 4);
			ws.addCell(new Label(9, 3, "学习成绩", wcfTytle));
			ws.mergeCells(9, 3, 9, 4);
			ws.addCell(new Label(10, 3, "奖惩项", wcfTytle));
			ws.mergeCells(9, 3, 11, 3);
			ws.addCell(new Label(10, 4, "基本分", wcfTytle));
			ws.addCell(new Label(11, 4, "加分", wcfTytle));
			ws.addCell(new Label(12, 4, "减分", wcfTytle));
			ws.addCell(new Label(13, 3, "小计", wcfTytle));
			ws.mergeCells(13, 3, 13, 4);
			ws.addCell(new Label(14, 3, "体育课成绩4分", wcfTytle));
			ws.mergeCells(14, 3, 14, 4);
			ws.addCell(new Label(15, 3, "课外锻炼2分", wcfTytle));
			ws.mergeCells(15, 3, 15, 4);
			ws.addCell(new Label(16, 3, "体质状况2分", wcfTytle));
			ws.mergeCells(16, 3, 16, 4);
			ws.addCell(new Label(17, 3, "竞技能力2分", wcfTytle));
			ws.mergeCells(17, 3, 17, 4);
			ws.addCell(new Label(18, 3, "小计", wcfTytle));
			ws.mergeCells(18, 3, 18, 4);
			
			//数据输出
			for (int i = 0; i < data.size(); i++) {
				float xj = 0;
				
				ws.addCell(new Label(0, 5 + i, data.get(i)[2], dataWcf));
				ws.addCell(new Label(1, 5 + i, data.get(i)[6], dataWcf));
				ws.addCell(new Label(2, 5 + i, data.get(i)[8], dataWcf));
				ws.addCell(new Label(3, 5 + i, data.get(i)[10], dataWcf));
				ws.addCell(new Label(4, 5 + i, data.get(i)[12], dataWcf));
				ws.addCell(new Label(5, 5 + i, "7分", dataWcf));
				ws.addCell(new Label(6, 5 + i, Integer.parseInt(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14]) >=7 ? Integer.parseInt(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14])-7+"" : "0", dataWcf));
				ws.addCell(new Label(7, 5 + i, data.get(i)[16], dataWcf));
				//小计
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[6]) ? "0" : data.get(i)[6]) 
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[8]) ? "0" : data.get(i)[8])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[10]) ? "0" : data.get(i)[10])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[12]) ? "0" : data.get(i)[12])
				     //+ 7
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14])
					 - Float.parseFloat(StringUtils.isNull(data.get(i)[16]) ? "0" : data.get(i)[16]);
				     
				ws.addCell(new Label(8, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				//学习成绩
				ws.addCell(new Label(9, 5 + i, StringUtils.isNull(data.get(i)[18]) ? "0" : data.get(i)[18], dataWcf));
				ws.addCell(new Label(10, 5 + i, "5分", dataWcf));
				ws.addCell(new Label(11, 5 + i, Integer.parseInt(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20]) >=5 ? Integer.parseInt(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20])-5+"" : "0", dataWcf));
				ws.addCell(new Label(12, 5 + i, data.get(i)[22], dataWcf));
				//小计
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[18]) ? "0" : data.get(i)[18])
				     //+ 5
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20])
				     - Float.parseFloat(StringUtils.isNull(data.get(i)[22]) ? "0" : data.get(i)[22]);
				ws.addCell(new Label(13, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				//体育成绩
				ws.addCell(new Label(14, 5 + i, data.get(i)[24], dataWcf));
				ws.addCell(new Label(15, 5 + i, data.get(i)[26], dataWcf));
				ws.addCell(new Label(16, 5 + i, data.get(i)[28], dataWcf));
				ws.addCell(new Label(17, 5 + i, data.get(i)[30], dataWcf));
				//小计
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[24]) ? "0" : data.get(i)[24])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[26]) ? "0" : data.get(i)[26])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[28]) ? "0" : data.get(i)[28])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[30]) ? "0" : data.get(i)[30]);
				ws.addCell(new Label(18, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				ws.addCell(new Label(19, 5 + i, data.get(i)[32], dataWcf));
				ws.addCell(new Label(20, 5 + i, data.get(i)[33], dataWcf));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
