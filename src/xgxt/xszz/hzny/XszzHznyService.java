package xgxt.xszz.hzny;

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

public class XszzHznyService extends XszzService implements XszzCommTjbbService{
	XszzHznyDAO dao = new XszzHznyDAO();

	
	public void printXylstdTjbb(XszzTyForm model, OutputStream os){
		
		List<HashMap<String,String>> data = dao.getXylstdData();//根据学院统计绿色通道信息
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("学院绿色通道统计表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学院绿色通道统计", 0);

		try {
			excel.printTitle(ws, title.toString(), 7, 400);// 标题
		
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式		
			
			//数据输出
			ws.addCell(new Label(0, 1, "学院", wcfTytle));
			ws.addCell(new Label(1, 1, "新生报到人数", wcfTytle));
			ws.addCell(new Label(2, 1, "办理绿色通道学生人数", wcfTytle));
			ws.addCell(new Label(3, 1, "比例", wcfTytle));
			ws.addCell(new Label(4, 1, "缓交金额（万元）", wcfTytle));
			ws.addCell(new Label(5, 1, "未交任何费用人数", wcfTytle));
			ws.addCell(new Label(6, 1, "缓交杂费", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				
				HashMap<String,String>map=data.get(i);
				ws.addCell(new Label(0, 2 + i,map.get("xymc"), wcfTytle));
				ws.addCell(new Label(1, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(2, 2 + i,map.get("blstdrs"), wcfTytle));
				ws.addCell(new Label(3, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(4, 2 + i,map.get("hjje"), wcfTytle));
				ws.addCell(new Label(5, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(6, 2 + i,map.get("hjzf"), wcfTytle));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printSydlstdTjbb(XszzTyForm model, OutputStream os){
		
		List<HashMap<String,String>> data = dao.getSydlstdData();//获取生源地省绿色通道统计信息
		
		StringBuilder title = new StringBuilder();
		title.append(model.getXn());
		title.append("生源地绿色通道统计表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("生源地绿色通道统计", 0);

		try {
			excel.printTitle(ws, title.toString(), 5, 400);// 标题
		
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式		
			
			//数据输出
			ws.addCell(new Label(0, 1, "生源地", wcfTytle));
			ws.addCell(new Label(1, 1, "录取人数", wcfTytle));
			ws.addCell(new Label(2, 1, "报道人数", wcfTytle));
			ws.addCell(new Label(3, 1, "通过绿色通道人数", wcfTytle));
			ws.addCell(new Label(4, 1, "比例", wcfTytle));
			
			for (int i = 0; i < data.size(); i++) {
				
				HashMap<String,String>map=data.get(i);
				ws.addCell(new Label(0, 2 + i,map.get("syds"), wcfTytle));
				ws.addCell(new Label(1, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(2, 2 + i,map.get(""), wcfTytle));
				ws.addCell(new Label(3, 2 + i,map.get("tgrs"), wcfTytle));
				ws.addCell(new Label(4, 2 + i,map.get(""), wcfTytle));
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
		if(XszzXmdm.XSZZ_TJBB_HZNY_XYLSTDTJB.equalsIgnoreCase(form.getTjbbdm())){//学院绿色通道统计表
			printXylstdTjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_HZNY_SYDLSTDTJB.equalsIgnoreCase(form.getTjbbdm())){//生源地绿色通道统计表
			printSydlstdTjbb(form,os);
		}
	}
	
}
