package xgxt.other.zjgyzy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.xtwh.zpgl.XtwhZpglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class ZjgyzyXxhzService extends BasicService {
	
	ZjgyzyXxhzDAO dao=new ZjgyzyXxhzDAO();
	/**
	 * 学生人数一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsrsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXsrsList(myForm,model,lx);
	}
	
	/**
	 * 学生档案一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsdaList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXsdaList(myForm,model,lx);
	}
	
	/**
	 * 学生住宿一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXszsList(myForm,model,lx);
	}
	
	
	/**
	 * 违纪处分一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getWjcfList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getWjcfList(myForm,model,lx);
	}
	
	/**
	 * 聘用情况一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getPyqkList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getPyqkList(myForm,model,lx);
	}
	
	/**
	 * 辅导员信息一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getFdyList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getFdyList(myForm,model,lx);
	}
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				
				if("yes".equalsIgnoreCase(rsModel.getCheckBox())){
					html.append("<input type='checkbox' name='div_pkValue'  ");
					html.append("  id='pkValue_"+i+"' ");
					html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				}else{
					html.append(replaceHtml(rs[0]));
				}
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
	
					html.append(replaceHtml(rs[j]));

					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}

	public void printXsrs(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xsrsList=getXsrsList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("学生人数情况一览表", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0, 0,Base.currXn+"学年第"+Base.getDqxqmc()+"学期学生人数一览表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"分院",wcf));
			ws.addCell(new Label(2, 1,"班级",wcf));
			ws.addCell(new Label(3, 1,"人数",wcf));
			ws.addCell(new Label(4, 1,"男生",wcf));
			ws.addCell(new Label(5, 1,"女生",wcf));
			if(xsrsList!=null && xsrsList.size()>0){
				for (int i = 0; i < xsrsList.size(); i++) {
	
					String[]printArr=xsrsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------打印时间-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(4, 2+xsrsList.size(), 5, 2+xsrsList.size());
				ws.addCell(new Label(4,2+xsrsList.size(), "打印日期："+GetTime.getNowTime2(), wcf));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printXszs(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs","rznsrs","rznvsrs","wrznsrs","wrznsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xszsList=getXszsList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("学生住宿情况一览表", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 9, 0);
			ws.addCell(new Label(0, 0,"学生住宿情况一览表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"分院",wcf));
			ws.addCell(new Label(2, 1,"班级",wcf));
			ws.addCell(new Label(3, 1,"人数",wcf));
			ws.addCell(new Label(4, 1,"男生",wcf));
			ws.addCell(new Label(5, 1,"女生",wcf));
			ws.addCell(new Label(6, 1,"入住男生",wcf));
			ws.addCell(new Label(7, 1,"入住女生",wcf));
			ws.addCell(new Label(8, 1,"未入住男生",wcf));
			ws.addCell(new Label(9, 1,"未入住女生",wcf));
			
			if(xszsList!=null && xszsList.size()>0){
				for (int i = 0; i < xszsList.size(); i++) {
	
					String[]printArr=xszsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------打印时间-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(8, 2+xszsList.size(), 9, 2+xszsList.size());
				ws.addCell(new Label(8,2+xszsList.size(), "打印日期："+GetTime.getNowTime2(), wcf));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printXsda(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xsdaList=getXsdaList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("学生档案一览", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0, 0, "学生档案人数一览表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"分院",wcf));
			ws.addCell(new Label(2, 1,"班级",wcf));
			ws.addCell(new Label(3, 1,"人数",wcf));
			ws.addCell(new Label(4, 1,"男生",wcf));
			ws.addCell(new Label(5, 1,"女生",wcf));
			
			if(xsdaList!=null && xsdaList.size()>0){
				for (int i = 0; i < xsdaList.size(); i++) {
	
					String[]printArr=xsdaList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------打印时间-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(4, 2+xsdaList.size(), 5, 2+xsdaList.size());
				ws.addCell(new Label(4,2+xsdaList.size(), "打印日期："+GetTime.getNowTime2(), wcf));
			
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printWjcf(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","nd","xn","xqmc","xh","xm","cflbmc","cfsj","cfyymc","cfwh","bjmc"}; 
		
		model.setColList(colList);
	
		List<String[]>wjcfList=getWjcfList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("违纪处分一览", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 10, 0);
			ws.addCell(new Label(0, 0, "学生违纪处分情况一览表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"年度",wcf));
			ws.addCell(new Label(2, 1,"学年",wcf));
			ws.addCell(new Label(3, 1,"学期",wcf));
			ws.addCell(new Label(4, 1,"学号",wcf));
			ws.addCell(new Label(5, 1,"姓名",wcf));
			ws.addCell(new Label(6, 1,"处分名称",wcf));
			ws.addCell(new Label(7, 1,"处分日期",wcf));
			ws.addCell(new Label(8, 1,"处分原因",wcf));
			ws.addCell(new Label(9, 1,"处分文号",wcf));
			ws.addCell(new Label(10, 1,"班级",wcf));
			
			if(wjcfList!=null && wjcfList.size()>0){
				for (int i = 0; i < wjcfList.size(); i++) {
	
					String[]printArr=wjcfList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------打印时间-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(9, 2+wjcfList.size(), 10, 2+wjcfList.size());
				ws.addCell(new Label(9,2+wjcfList.size(), "打印日期："+GetTime.getNowTime2(), wcf));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printFdy(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xm","xb","jg","zzmm","kzzd3","csrq","xl","xw","sxzy",
				"byyx","zc","zwmc","cjgzrq","kzzd1","bmmc","zyzz","pxqk","fblw","grhjqk","kzzd2","bz"}; 
		
		model.setColList(colList);
	
		List<String[]> xsrsList=getFdyList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("辅导员信息一览", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 21, 0);
			ws.addCell(new Label(0, 0, "浙江工业职业技术学院"+Base.currNd+"年度辅导员信息登记表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"姓名",wcf));
			ws.addCell(new Label(2, 1,"性别",wcf));
			ws.addCell(new Label(3, 1,"籍贯",wcf));
			ws.addCell(new Label(4, 1,"政治面貌",wcf));
			ws.addCell(new Label(5, 1,"婚姻状况",wcf));
			ws.addCell(new Label(6, 1,"出生年月",wcf));
			ws.addCell(new Label(7, 1,"学历",wcf));
			ws.addCell(new Label(8, 1,"学位",wcf));
			ws.addCell(new Label(9, 1,"所学专业",wcf));
			ws.addCell(new Label(10, 1,"毕业院校",wcf));
			ws.addCell(new Label(11, 1,"职称",wcf));
			ws.addCell(new Label(12, 1,"职务",wcf));
			ws.addCell(new Label(13, 1,"参加工作时间",wcf));
			ws.addCell(new Label(14, 1,"从事辅导员工作时间",wcf));
			ws.addCell(new Label(15, 1,"所在分院",wcf));
			ws.addCell(new Label(16, 1,"负责的主要工作",wcf));
			ws.addCell(new Label(17, 1,"进修、参加会议及培训情况",wcf));
			ws.addCell(new Label(18, 1,"论文、科研情况",wcf));
			ws.addCell(new Label(19, 1,"获奖情况",wcf));
			ws.addCell(new Label(20, 1,"兼课情况",wcf));
			ws.addCell(new Label(21, 1,"备注",wcf));
		
			if(xsrsList!=null && xsrsList.size()>0){
				for (int i = 0; i < xsrsList.size(); i++) {
	
					String[]printArr=xsrsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printPrqk(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","nd","xn","xqmc","xymc","bjmc","fdy","bzr"}; 
		
		model.setColList(colList);
	
		List<String[]>prqkList=getPyqkList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("辅导员班主任聘任情况一览", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 表头信息
			ws.mergeCells(0, 0, 7, 0);
			ws.addCell(new Label(0, 0, "辅导员班主任聘任一览表", wcf));

			// -----------------------学籍异动信息 begin--------------------------------
			ws.addCell(new Label(0, 1,"序号",wcf));
			ws.addCell(new Label(1, 1,"年度",wcf));
			ws.addCell(new Label(2, 1,"学年",wcf));
			ws.addCell(new Label(3, 1,"学期",wcf));
			ws.addCell(new Label(4, 1,"所在分院",wcf));
			ws.addCell(new Label(5, 1,"班级",wcf));
			ws.addCell(new Label(6, 1,"辅导员",wcf));
			ws.addCell(new Label(7, 1,"班主任",wcf));
			
			if(prqkList!=null && prqkList.size()>0){
				
				for (int i = 0; i < prqkList.size(); i++) {
	
					String[]printArr=prqkList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------打印时间-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(6, 2+prqkList.size(), 7, 2+prqkList.size());
				ws.addCell(new Label(6,2+prqkList.size(), "打印日期："+GetTime.getNowTime2(), wcf));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
