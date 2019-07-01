/**
 * @部门:学工产品事业部
 * @日期：2013-11-4 下午06:12:54 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import jxl.Workbook;
import jxl.format.*;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.date.MoneyFormat;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-11-4 下午06:12:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxService extends SuperServiceImpl<TjcxModel, TjcxDao> implements
	Constants {

	private final String sjbj = "十佳班级";
	private final String xjbjt = "先进班集体";
	private final String shxsbb = "三好学生标兵";
	private final String shxs = "三好学生";
	private final String yxxsgb = "优秀学生干部";
	private final String yxshgzz = "优秀社会工作者";

	private TjcxDao dao = new TjcxDao();
	public TjcxService(){
		super.setDao(dao);
	}
	
	/**
	 * @throws WriteException 
	 * @throws Exception 
	 * 
	 * @描述 导出获奖名单列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-2 下午01:55:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//项目类型
		String[] xmlx = model.getXmlx();
		//学院代码
		String[] xydm = model.getXydm();
		//项目性质
		String[] xmxz = model.getXmxz();
		//项目名称
		String[] xmmc = model.getXmmc();
		/*
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}*/
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("获奖名单", 0);
		
		// 学院列表
		List<String[]> xymcList = dao.getXyList(xn, xq, xmlx, xydm, xmxz, xmmc,user);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//项目类型
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//奖学金名称样式
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		//过滤评奖周期（学期评奖才要打印出学期，否则不用）
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// 输出第一行的表头
			ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", xqmc, "学期", lxmc, "获得者名单"), jxjFormat));
		}else {
			ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "获得者名单"), jxjFormat));
		}
		if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
			ws.mergeCells(0, 0,11, 0);
		}else{
			ws.mergeCells(0, 0,10, 0);
		}
		
		
		//xmList 获奖名单 
		//jxjdmList 项目获奖人数
		//xymcList 学院获奖人数
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (共" +jxjdmArr[1] +"人)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 3
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[2] + "人)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}

	/**
	 * @描述:根据代码获取学院名称（南京高等职业技术学校：评奖评优-统计管理-获奖名单统计：奖学金相关表导出）
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/16 9:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [search_tj_xy]
	 * @return: java.lang.String[]
	 */
	public String[] getXymcByDms(String[] xydms) throws SQLException {
		return dao.getXymcByDms(xydms);
	}


	private class JxjExportProperties{
		 int x_axis = 0;//X座标
		 int row = 1;//行标
		 int rowCellCount = 1;//每行的cell个数计数
		 int[] maxLength = {25,25,25,25,25,25,25};//默认的列宽
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// 开始写姓名行
		properties.x_axis = rowCellCount;// 开始新奖学金的姓名
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				String cellContent = xmList.get(index - 1);
				if("12865".equals(Base.xxdm)){
					if (properties.rowCellCount == 11) {
						properties.row++;// 满10个就换行
						properties.x_axis = 0;
						properties.rowCellCount = 1;// 重新每行计数
					}
				}else{
					if (properties.rowCellCount == 8) {
						properties.row++;// 满10个就换行
						properties.x_axis = 0;
						properties.rowCellCount = 1;// 重新每行计数
					}
				}
				
				// 判断姓名长度,然后根据长度控制合并单元格，及超出长度的情况下进行换行
//				if (cellContent.length() > 3) {
//					int xmLength = cellContent.length() / 3
//							+ (cellContent.length() % 3 == 0 ? 0 : 1);
//					int pre_x_axis = properties.x_axis;// 合并前的x轴值
//					if (pre_x_axis + xmLength > 10) {
//						properties.row++;// 姓名过长，换行
//						properties.x_axis = rowCellCount;
//						properties.rowCellCount = 1;// 重新每行计数
//						pre_x_axis = properties.x_axis;
//					}
//					Label cell = new Label(++properties.x_axis, properties.row,
//							cellContent);
//					ws.addCell(cell);
//					ws.setColumnView(properties.x_axis, 7);
//					pre_x_axis++;
//					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
//							+ xmLength - 1, properties.row);
//					properties.x_axis = pre_x_axis + xmLength - 1;
//					properties.rowCellCount++;
//				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
//				}
			}
		}
		properties.row += 1;// 新奖学金换行
	}
	
	/**
	 * @描述 导出获奖名单列表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-6 下午05:24:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj_11458(HjmdExportModel model , OutputStream os) throws Exception{
		// 宋体,12,LEFT
		WritableCellFormat s12LeftFormat = new WritableCellFormat();
		WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12LeftFormat.setFont(s12LeftFont);
		s12LeftFormat.setAlignment(Alignment.LEFT);
		// 宋体,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// 宋体,12,RIGHT
		WritableCellFormat s12RightFormat = new WritableCellFormat();
		WritableFont s12RightFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12RightFormat.setFont(s12RightFont);
		s12RightFormat.setAlignment(Alignment.RIGHT);
		// 宋体,12,BOLD,LEFT
		WritableCellFormat s12LeftBoldFormat = new WritableCellFormat();
		WritableFont s12LeftBoldFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12LeftBoldFont.setBoldStyle(WritableFont.BOLD);
		s12LeftBoldFormat.setFont(s12LeftBoldFont);
		s12LeftBoldFormat.setAlignment(Alignment.LEFT);
		
		// 先进集体过滤条件
		String jtpjTj = "先进集体";
		//学年
		String xn = model.getXn();
		//项目性质
		String[] xmxz = model.getXmxz();
		//所有获奖学院
		List<HashMap<String,String>> xyAllList = dao.getAllXyList_11458(xn, xmxz);
		//用于生成excel的获奖学院
		List<HashMap<String,String>> xyLoopList = new ArrayList<HashMap<String,String>>();
		//学院代码
		String[] xydmSearchArr = model.getXydm();
		if(xydmSearchArr == null){
			xyLoopList = xyAllList;
		}else{
			for (int i = 0; i < xydmSearchArr.length; i++) {
				for (int j = 0; j < xyAllList.size(); j++) {
					HashMap<String,String> temp = xyAllList.get(j);
					if(temp.get("xydm").equals(xydmSearchArr[i])){
						xyLoopList.add(temp);
					}
				}
			}
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		for (int m = 0; m < xyLoopList.size(); m++) {
			HashMap<String,String> xyMap = xyLoopList.get(m);
			String[] xydm = new String[] { xyMap.get("xydm") };
		
			WritableSheet ws = wwb.createSheet(xyMap.get("xymc") + "公示名单", m);
			
			// 学院列表
			List<String[]> xymcList = dao.getXyList_11458(xn, xmxz, xydm);
			// 获奖姓名
			List<String[]> xmList = dao.getHjmdList_11458(xn, xmxz, xydm);
			// 集体评奖学院数量
			List<String[]> jtpjList = dao.getJtpjList_11458(jtpjTj, xn, xydm);
			
			// 中间列数
			int midColumns  = xymcList.size() + 1;
			// 总列数
			int columns  = midColumns * 2;
			// 设置第一列宽度
			ws.setColumnView(0, 17); 
			// 第1行
			WritableCellFormat s18BoldCenterFormat = new WritableCellFormat();
			WritableFont s18BoldCenterFont = new WritableFont(WritableFont.createFont("宋体"),18);
			s18BoldCenterFont.setBoldStyle(WritableFont.BOLD);
			s18BoldCenterFormat.setFont(s18BoldCenterFont);
			s18BoldCenterFormat.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 0, xn + "学年先进集体、个人公示名单", s18BoldCenterFormat));
			ws.mergeCells(0, 0, (columns - 1), 0);
			// 第2行
			WritableCellFormat r2Format = new WritableCellFormat();
			WritableFont r2Font = new WritableFont(WritableFont.createFont("宋体"),18);
			r2Font.setBoldStyle(WritableFont.BOLD);
			r2Format.setFont(r2Font);
			r2Format.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 1, "", r2Format));
			// 第3行
			ws.addCell(new Label(0, 2, "学    院：", s12LeftFormat));
			ws.addCell(new Label(1, 2, xyMap.get("xymc"), s12LeftFormat));
			ws.mergeCells(1, 2, (columns - 1), 2);
			// 第4行
			ws.addCell(new Label(0, 3, "评定学年：", s12LeftFormat));
			ws.addCell(new Label(1, 3, xn + "学年", s12LeftFormat));
			ws.mergeCells(1, 3, (columns - 1), 3);
			// 第5行
			ws.addCell(new Label(0, 4, "", s12LeftFormat));
			// 第6行
			ws.addCell(new Label(0, 5, "二级学院负责人签字：", s12LeftFormat));
			ws.addCell(new Label(midColumns, 5, "二级学院盖章：", s12LeftFormat));
			// 第7行
			ws.addCell(new Label(0, 6, "", s12LeftFormat));
			// 第8行
			ws.addCell(new Label(0, 7, "", s12LeftFormat));
			// 第9行
			// 宋体,12,CENTRE,边框
			WritableCellFormat s12CentreBorderFormat = new WritableCellFormat();
			WritableFont s12CentreBorderFont = new WritableFont(WritableFont.createFont("宋体"),12);
			s12CentreBorderFormat.setFont(s12CentreBorderFont);
			s12CentreBorderFormat.setAlignment(Alignment.CENTRE);
			s12CentreBorderFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			ws.addCell(new Label(0, 8, xmxz[0], s12CentreBorderFormat));
			ws.addCell(new Label(1, 8, jtpjTj, s12CentreBorderFormat));
			for (int i = 0; i < xymcList.size(); i++) {
				int temp =  2 + i * 2;
				ws.addCell(new Label(temp, 8, xymcList.get(i)[0], s12CentreBorderFormat));
				ws.mergeCells(temp, 8, (temp + 1), 8);
			}
			// 第10行
			ws.addCell(new Label(0, 9, "人数", s12CentreBorderFormat));
			ws.addCell(new Label(1, 9, String.valueOf(jtpjList.size()), s12CentreBorderFormat));
			for (int i = 0; i < xymcList.size(); i++) {
				int temp =  2 + i * 2;
				ws.addCell(new Label(temp, 9, xymcList.get(i)[1], s12CentreBorderFormat));
				ws.mergeCells(temp, 9, (temp + 1), 9);
			}
			// =============== 先进集体 begin=======================
			// 标题
			ws.addCell(new Label(0, 11, jtpjTj + "：", s12LeftBoldFormat));
			// 内容
			int rowTemp = 12;
			int colTemp = 0;
			for (int i = 0; i < jtpjList.size(); i++) {
				ws.addCell(new Label(colTemp, rowTemp, jtpjList.get(i)[0], s12LeftFormat));
				colTemp++;
				if(colTemp == columns){
					rowTemp++;
					colTemp = 0;
				}
			}
			// =============== 先进集体 end=======================
			rowTemp += 2; // 空一行
			// =============== 项目性质 begin=======================
			boolean flag = false; // 判断：刚好等于换行临界值，但是下一行没有数据时
			for (int i = 0; i < xymcList.size(); i++) {
				// 标题
				String xmmc = xymcList.get(i)[0];
				ws.addCell(new Label(0, rowTemp, xmmc + "：", s12LeftBoldFormat));
				// 内容
				colTemp = 0;
				rowTemp++; // 下一行
				for (int j = 0; j < xmList.size(); j++) {
					String xmmcXs = xmList.get(j)[1];
					String xmXs = xmList.get(j)[2];
					String bjmcXs = xmList.get(j)[3];
					if(xmmc.equals(xmmcXs)){
						ws.addCell(new Label(colTemp, rowTemp, bjmcXs, s12RightFormat));
						colTemp++;
						ws.addCell(new Label(colTemp, rowTemp, xmXs, s12CentreFormat));
						colTemp++;
						if(colTemp == columns){
							rowTemp++;
							colTemp = 0;
							flag = true;
						}else{
							flag = false;
						}
					}
				}
				if(flag){
					rowTemp += 1; // 空一行
				}else{
					rowTemp += 2; // 空一行
				}
			}
			// =============== 项目性质 end=======================
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	
	
	/**
	 * 
	 * @描述:浙江大学奖学金名额分配一览表
	 * @作者：张昌路[工号：982]
	 * @日期：2015-3-10 下午04:13:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getJxjmefpList(TjcxModel model, User user) throws Exception{
		
		//查询需要统计的评奖项目
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		CsszService  csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");
		return dao.getJxjmefpList(model, user, pjxmList,rsjsfs);
	}

	
	/** 
	 * @描述:浙江大学获得，需统计评奖项目
	 * @作者：张昌路[工号：982]
	 * @日期：2015-3-10 下午04:39:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPjxmList(TjcxModel model) {
		return dao.getPjxmList(model);
	}
	
	/**
	 * 浙江大学——证书打印（分页）
	 */
	public List<HashMap<String, String>> viewZsdy(TjcxModel t, User user) throws Exception {
		return dao.viewZsdy(t, user);
	}
	/**
	 * 浙江大学——证书打印（不分页）
	 */
	public List<HashMap<String, String>> viewZsdyAll(TjcxModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.viewZsdy(t, user);
	}
	/**
	 * 下载导入模版
	 */
	public void exportZsNew(TjcxModel t, User user, HttpServletResponse response, String filePath) throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(1);
		List<HashMap<String, String>> dataList = viewZsdyAll(t, user);
		
		//修改打印模板显示姓名
		HSSFSheet firstSheet = workbook.getSheetAt(0);
		//replace 学生姓名
		HSSFRow row4 = firstSheet.getRow(3);// 获得工作薄的第4行
		HSSFCell cell44 = row4.getCell(3);// 获得第4行的第4个单元格
		cell44.setCellValue(dataList.get(0).get("xm"));// 给单元格赋值
		//replace 学号
		HSSFCell cell46 = row4.getCell(5);// 获得第4行的第6个单元格
		cell46.setCellValue(dataList.get(0).get("xh"));// 给单元格赋值
		//replace 学年
		HSSFRow row5 = firstSheet.getRow(4);// 获得工作薄的第5行
		HSSFCell cell53 = row5.getCell(2);// 获得第5行的第3个单元格
		cell53.setCellValue(" 在 "+dataList.get(0).get("xn")+" 学 年 中 表 现 优 秀，现 授 予");// 给单元格赋值
		//replace 奖项
		HSSFRow row6 = firstSheet.getRow(5);// 获得工作薄的第6行
		HSSFCell cell64 = row6.getCell(3);// 获得第6行的第4个单元格
		cell64.setCellValue(dataList.get(0).get("xmmc"));// 给单元格赋值
		//replace 姓名拼音
		HSSFRow row9 = firstSheet.getRow(8);// 获得工作薄的第9行
		HSSFCell cell94 = row9.getCell(3);// 获得第9行的第4个单元格
		cell94.setCellValue(dataList.get(0).get("xmpy"));// 给单元格赋值
		//replace 奖项英文名称
		HSSFRow row10 = firstSheet.getRow(9);// 获得工作薄的第10行
		HSSFCell cell104 = row10.getCell(3);// 获得第10行的第4个单元格
		cell104.setCellValue(dataList.get(0).get("xmywmc"));// 给单元格赋值
		//replace 学年
		HSSFRow row11 = firstSheet.getRow(10);// 获得工作薄的第11行
		HSSFCell cell114 = row11.getCell(3);// 获得第11行的第4个单元格
		cell114.setCellValue("   Awarded on   "+dataList.get(0).get("xn"));// 给单元格赋值
		//replace 当前时间
		HSSFRow row19 = firstSheet.getRow(18);// 获得工作薄的第19行
		HSSFCell cell197 = row19.getCell(7);// 获得第19行的第7个单元格	
		cell197.setCellValue(DateUtils.getYear()+"/"+DateUtils.getMonth());// 给单元格赋值

		
		// 数据写入
		int row = 1;
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmmc"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmpy"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmywmc"));
			row++;
		}
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}

	/** 
	 * @描述:统计导出（浙江大学
	 * @作者：张昌路[工号：982]
	 * @日期：2015-3-11 下午03:44:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getTjjgFile(TjcxModel model, User user) throws Exception{
		
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		CsszService  csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");
		
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bmmc", "部门名称");
		
		for (int i = 0 , j = pjxmList.size() ; i < j ; i++){
			map.put("jx"+i, pjxmList.get(i).get("xmmc"));
		}
		
		map.put("jje", "奖金额");
		map.put("bmtzje", "调整金额");
		map.put("zrs", "学生总数");
		map.put("ytjrs", "综测已提交");
		
		//导出数据
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getJxjmefpList(model, user, pjxmList,rsjsfs);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	
	/**
	 * 
	 * @描述:浙江大学名单导出
	 * @作者：cq [工号：785]
	 * @日期：2015-3-16 下午02:29:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj_10335(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//项目类型
		String[] xmlx = model.getXmlx();
		//学院代码
		String[] xydm = model.getXydm();
		//项目性质
		String[] xmxz = model.getXmxz();
		//项目名称
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "获奖名单", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		// 学院列表
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//项目类型名称
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//奖学金名称样式
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		//奖项+学院样式
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		//过滤评奖周期（学期评奖才要打印出学期，否则不用）
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// 输出第一行的表头
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", xqmc, "学期", lxmc, "发文名单"), titlFormat));
		}else {
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"), titlFormat));
		}
		ws.mergeCells(1, 0,3, 1);
		
		//xmList 获奖名单 
		//jxjdmList 项目获奖人数
		//xymcList 学院获奖人数
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //浙江大学开头空两行
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 3, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						xy+="("+xymcArr[2]+"人)";
						System.out.println("学院："+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 3, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	/**
	 * XX系奖学金汇总报批表（安徽商贸职业技术学院）
	 */
	public void exportHjmdtj_12072(HjmdExportModel model , OutputStream os, User user) throws Exception{
		// 宋体,14,CENTRE,边框
		WritableCellFormat s14CentreFormat = new WritableCellFormat();
		WritableFont s14CentreFont = new WritableFont(WritableFont.createFont("宋体"),14);
		s14CentreFormat.setFont(s14CentreFont);
		s14CentreFormat.setAlignment(Alignment.CENTRE);
		s14CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		// 宋体,12,CENTRE,边框
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		s12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		// 左边框
		WritableCellFormat leftBorderFormat = new WritableCellFormat();
		leftBorderFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		// 仿宋_GB2312,12,CENTRE,边框
		WritableCellFormat fs12CentreFormat = new WritableCellFormat();
		WritableFont fs12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		fs12CentreFormat.setFont(fs12CentreFont);
		fs12CentreFormat.setAlignment(Alignment.CENTRE);
		fs12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//项目类型
		String[] xmlx = model.getXmlx();
		//学院代码
		String[] xydm = model.getXydm();
		//项目性质
		String[] xmxz = model.getXmxz();
		//项目名称
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		String xnTemp = xn.replace("-", "/");
		WritableSheet ws = wwb.createSheet("奖学金汇总报批表", 0);
		// 获奖项目 
		List<HashMap<String,String>> hjxmList = dao.getHjxmList_12072(xn, xq, xmlx, xydm, xmxz, xmmc,user);
		List<HashMap<String,String>> bjList = dao.getBjList_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user);
		int col = hjxmList.size() * 2 + 2;
		// 第1行
		ws.addCell(new Label(0, 0, "系别：（盖章）                             "+xnTemp+"学年度"+xqmc, s14CentreFormat));
		ws.mergeCells(0, 0, col, 0);
		ws.setRowView(0, 450);
		// 第2行
		ws.addCell(new Label(0, 1, "序号", s12CentreFormat));
		ws.mergeCells(0, 1, 0, 2);
		ws.setColumnView(0, 8);
		ws.addCell(new Label(1, 1, "班级", s12CentreFormat));
		ws.mergeCells(1, 1, 1, 2);
		ws.setColumnView(1, 17);
		for (int i = 0; i < hjxmList.size(); i++) {
			int colTemp = i * 2 + 2;
			HashMap<String, String> hjxmMap = hjxmList.get(i);
			ws.addCell(new Label(colTemp, 1, hjxmMap.get("xmmc"), s12CentreFormat));
			ws.mergeCells(colTemp, 1, colTemp + 1, 1);
			ws.addCell(new Label(colTemp, 2, "人数", s12CentreFormat));
			ws.setColumnView(colTemp, 9);
			ws.addCell(new Label(colTemp + 1, 2, "金额", s12CentreFormat));
			ws.setColumnView(colTemp + 1, 18);
		}
		ws.addCell(new Label(col, 1, "班级小计", s12CentreFormat));
		ws.setColumnView(col, 28);
		ws.mergeCells(col, 1, 1, 2);
		ws.setRowView(1, 390);
		ws.setRowView(2, 390);
		// 左边框
		ws.addCell(new Label(col+1, 1, " ", leftBorderFormat));
		ws.addCell(new Label(col+1, 2, " ", leftBorderFormat));
		// 第4行
		int row = 2;
		BigDecimal bjSumJe = new BigDecimal("0");
		BigDecimal sumJe = new BigDecimal("0");
		for (int j = 0; j < bjList.size(); j++) {
			bjSumJe = new BigDecimal("0");
			HashMap<String, String> bjMap = bjList.get(j);
			String bjmc = bjMap.get("bjmc");
			row++;
			ws.setRowView(row, 390);
			ws.addCell(new Label(0, row, String.valueOf(j + 1), s12CentreFormat));
			ws.addCell(new Label(1, row, bjmc, fs12CentreFormat));
			for (int i = 0; i < hjxmList.size(); i++) {
				int colTemp = i * 2 + 2;
				String bjxmrs = bjMap.get("bjxmrs"+i);
				if(bjxmrs == null){
					bjxmrs = "0";
				}
				String bjxmje = bjMap.get("bjxmje"+i);
				if(bjxmje == null){
					bjxmje = "0";
				}
				bjSumJe = bjSumJe.add(new BigDecimal(bjxmje));
				ws.addCell(new Label(colTemp, row, bjxmrs, fs12CentreFormat));
				ws.addCell(new Label(colTemp + 1, row, new BigDecimal(bjxmje).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), fs12CentreFormat));
			}
			ws.addCell(new Label(col, row, bjSumJe.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), s12CentreFormat));
			// 左边框
			ws.addCell(new Label(col+1, row, " ", leftBorderFormat));
			sumJe = sumJe.add(bjSumJe);
		}
		
		HashMap<String,String> bjSum = dao.getBjSum_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user);
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "合计", s12CentreFormat));
		ws.mergeCells(0, row, 1, row);
		for (int i = 0; i < hjxmList.size(); i++) {
			int colTemp = i * 2 + 2;
			String bjxmrssum = bjSum.get("bjxmrssum"+i);
			if(bjxmrssum == null){
				bjxmrssum = "0";
			}
			String bjxmjesum = bjSum.get("bjxmjesum"+i);
			if(bjxmjesum == null){
				bjxmjesum = "0";
			}
			ws.addCell(new Label(colTemp, row, bjxmrssum, fs12CentreFormat));
			ws.addCell(new Label(colTemp + 1, row, new BigDecimal(bjxmjesum).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), fs12CentreFormat));
		}
		ws.addCell(new Label(col, row, sumJe.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), s12CentreFormat));
		// 左边框
		ws.addCell(new Label(col+1, row, " ", leftBorderFormat));
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "总计：人民币 "+MoneyFormat.format(sumJe.toString()), s14CentreFormat));
		ws.mergeCells(0, row, col, row);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	
	
	/**
	 * 
	 * @描述:浙江大学个性化
	 * @作者：cq [工号：785]
	 * @日期：2015-3-17 下午01:32:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param rowCellCount
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// 开始写姓名行
		properties.x_axis = rowCellCount;// 开始新奖学金的姓名
		//姓名样式
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				int nameLength = 4; //合并单元格字符串长度
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 4) {
					properties.row++;// 满3个就换行
					properties.x_axis = 0;
					properties.rowCellCount = 1;// 重新每行计数
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,
						cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 30);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		properties.row += 1;// 新奖学金换行
	}
	
	
	/**
	 * 
	 * @描述:发放汇总导出
	 * @作者：cq [工号：785]
	 * @日期：2015-4-29 下午03:15:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File exportFfhz(TjcxModel model, User user) throws Exception{
		
		//执行浙大存储过程
		dao.computeFfhz(model.getXn(), Base.currXn);
		
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("xymc", "院系");
		map.put("xh", "学号");
		map.put("xm", "姓名");
		map.put("bcffje", "本次发放金额");
		map.put("jjze", "总金额");
		map.put("bz", "备注");
		
		//导出数据
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getFfhzList(model, user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	public void exportGjjxjhz(TjcxModel model,OutputStream os, User user) throws Exception {
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(model.getXn());
		title.append("学年国家奖学金发放汇总");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		List<HashMap<String, String>> gjjxjList = dao.getGjjxjList(model);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.mergeCells(0, 0, 3, 1);
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学号", wcf2));
		ws.addCell(new Label(2, 2, "姓名", wcf2));
		ws.addCell(new Label(3, 2, "发放金额(元)", wcf2));
		if (gjjxjList != null && gjjxjList.size() > 0) {

			for (int i = 0; i < gjjxjList.size(); i++) {

				HashMap<String, String> map = gjjxjList.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 30);
				ws.setColumnView(2, 30);
				ws.setColumnView(3, 30);

				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xmje"), wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @throws Exception  
	 * @描述:浙大数据导出
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 下午05:35:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param lxmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getExport(TjcxModel model, User user,
			String lxmc) throws Exception {
		return dao.getExport(model,user,lxmc);
	}
	
	
	/**
	 * 
	 * @描述:浙大数据导出
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 下午07:50:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File exportData(TjcxModel model, User user, String lxmc) throws Exception{
		
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("rn", "序号");
		map.put("cpxymc", "学院");
		map.put("xm", "姓名");
		map.put("xh", "学号");
		map.put("xmmc", "奖项");
		map.put("xmpy", "姓名拼音");
		map.put("xmywmc", "奖项英文名称");
		
		//导出数据
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String, String>> dataList = getExport(model,user,lxmc);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	/**
	 * 上海电机学院个性化查询
	 */
	public List<HashMap<String, String>> getPageListShTyxy(TjcxModel t, User user)
	throws Exception{
		return dao.getPageListShTyxy(t, user);
	}
	
	
	/**
	 * 
	 * @描述:浙江大学年鉴导出
	 * @作者：张昌路[工号：982]
	 * @日期：2016-5-9 下午04:28:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj_10335_njdc(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//项目类型
		String[] xmlx = model.getXmlx();
		//学院代码
		String[] xydm = model.getXydm();
		//项目性质
		String[] xmxz = model.getXmxz();
		//项目名称
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "获奖名单", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		// 学院列表
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//项目类型名称
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//奖学金名称样式
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		//奖项+学院样式
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		//过滤评奖周期（学期评奖才要打印出学期，否则不用）
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// 输出第一行的表头
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", xqmc, "学期", lxmc, "发文名单"), titlFormat));
		}else {
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"), titlFormat));
		}
		ws.mergeCells(1, 0,9, 1);
		
		//xmList 获奖名单 
		//jxjdmList 项目获奖人数
		//xymcList 学院获奖人数
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //浙江大学开头空两行
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 9, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[4]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						xy+="("+xymcArr[2]+"人)";
						System.out.println("学院："+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 9, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel_10335_njdc(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	
	
	/**
	 * 
	 * @描述:浙江大学个性化年鉴导出
	 * @作者：cq [工号：785]
	 * @日期：2015-3-17 下午01:32:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param rowCellCount
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335_njdc(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// 开始写姓名行
		properties.x_axis = rowCellCount;// 开始新奖学金的姓名
		//姓名样式
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),13);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
 			for (int index = 1; index < xmList.size() + 1; index++) {
				int nameLength = 3; //合并单元格字符串长度
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 10) {
					properties.row++;// 满9个就换行
					properties.x_axis = 0;
					properties.rowCellCount = 1;// 重新每行计数
				}
				
				//两个字的姓名不在sql当中处理，在这里增加
				if(strLength>2){
					xmContent=xmContent.replace(" ", "").substring(0, 1)+" "+xmContent.replace(" ", "").substring(1, 2);
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,
						cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 9);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		properties.row += 1;// 新奖学金换行
	}
	
	
    /**
     * 
     * @描述: 浙大发文名单导出word
     * @作者：yxy[工号：1206]
     * @日期：2016-7-14 下午01:50:45
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param response
     * @param model
     * @param user
     * @param path
     * @throws Exception
     * void 返回类型 
     * @throws
     */
	public void createWord(HttpServletResponse response,HjmdExportModel model,User user,String path) throws Exception{
		  Document document = new Document(PageSize.A4);
	        //建立一个书写器，与document对象关联  
		  response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("fwmddc_zjdx.doc".getBytes(), "GBK") + "\"");
	        RtfWriter2.getInstance(document, response.getOutputStream());  
	        document.open();  
	        //设置中文字体  
	        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
	        //标题字体风格  
	        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
	        //正文字体风格  
	        Font contextFont = new Font(bfChinese,16,Font.NORMAL);  
	        Paragraph title = new Paragraph(); 
	      //学年
			String xn = model.getXn();
			//学期
			String xq = model.getXq();
			//学期名称
			List<HashMap<String,String>> xqList = Base.getXqList();
			String xqmc = null;
			for (HashMap<String,String> map : xqList){
				
				if (map.get("xqdm").equals(model.getXq())){
					xqmc = map.get("xqmc");
					break;
				}
				
			}
			//项目类型
			String[] xmlx = model.getXmlx();
			//学院代码
			String[] xydm = model.getXydm();
			//项目性质
			String[] xmxz = model.getXmxz();
			//项目名称
			String[] xmmc = model.getXmmc();
			
			// 学院列表
			List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
			// 各奖学金获奖人数
			List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
			// 获奖姓名
			List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
			
			//项目类型名称
			PjdmService pjdmService = new PjdmService();
			List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
			String lxmc = null;
			for (int i = 0; i < xmlxmc.size(); i++) {
				if(i==0){
					lxmc=xmlxmc.get(i);
				}else{
					lxmc=lxmc+","+xmlxmc.get(i);
				}
			}
			
			
			//过滤评奖周期（学期评奖才要打印出学期，否则不用）
			CsszService csszService = new CsszService();
			String pjzq = csszService.getCsz("pjzq");
			 Phrase phrase = null;
			if("1".equals(pjzq)){ 	
				// 输出第一行的表头
				phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", xqmc, "学期", lxmc, "发文名单"),titleFont);
			}else {
				phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"),titleFont);
			}
	         
	        title.add(phrase);
	        //设置标题格式对齐方式  
	        title.setAlignment(Element.ALIGN_CENTER);
	        title.setSpacingAfter(20);
	        title.setIndentationLeft(5);
	        title.setIndentationRight(5);
	        document.add(title);  
	    	if (!xmList.isEmpty()) {
	    			Paragraph paraitra1 = null;
	    			int propertiesrow =3;
	    			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
	    				
	    				 
	    			
	    				String[] jxjdmArr = jxjdmList.get(i);
	    				String jxjmc = jxjdmArr[0];
	    				paraitra1 = new Paragraph(jxjmc,titleFont);
	    				paraitra1.setAlignment(Element.ALIGN_LEFT);
	    				document.add(paraitra1);
	    				
	    		      
	    				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
	    				Paragraph paraitra2 = null;
	    				for (int index = 0; index < xymcList.size(); index++) {
	    					String[] xymcArr = xymcList.get(index);
	    					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
	    						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
	    						
	    						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
	    						List<String> xmByXyList = new ArrayList<String>();
	    						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
	    							if (xmArr != null && xmArr.length >= 4
	    									&& xy.equalsIgnoreCase(xmArr[0])
	    									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
	    								xmByXyList.add(xmArr[2]);
	    								xmXhByXyList.add(xmArr[3]);
	    							}
	    						}
	    						if (xmByXyList == null || xmByXyList.size() <= 0) {
	    							continue;
	    						}
	    						//输出学院名称
	    						++propertiesrow;
	    						xy+="("+xymcArr[2]+"人)";
	    						paraitra2 = new Paragraph(xy,titleFont);
	    						paraitra2.setIndentationLeft(10);
	    						//paraitra2.setSpacingAfter(10);
	    						document.add(paraitra2);
//	    					    run2.setFontSize(32);
//	    				        run2.setFtcAscii(11);
//	    				        run2.setBold(true);
//	    						System.out.println("学院："+xy);
//	    						Label xymcCell = new Label(1,properties.row,xy );
//	    						xymcCell.setCellFormat(jxxyFormat);
//	    						ws.addCell(xymcCell);
//	    						ws.mergeCells(1,properties.row, 3, properties.row);
	    						//输出第三级将学院下面的获奖名单输出
	    			//			writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
	    						int len = xmXhByXyList.size();
	    				//		int modlen = len % 3;
//	    						if(len >= 3){
	    							Paragraph para3 = null;
	    							Phrase phrase3 = null;
	    							//活动技术器变量用来循环
	    							int count = 3;
	    							//新加行标志
//	    							boolean flag = true;
	    							//三级循环，三个名字为一行
	    							for (int j = 0; j < len; j=j+count) {
	    								para3 = new Paragraph();
	    								//名单写入，姓名超过4个字符的单独为一行，如果第二个名字或者第三个名字超过4个字符，不写入，另起一行，再写入
	    								for(int x=j;x<j+3;x++){
	    									if(x<len){
	    										if(x == j){
	    		    								phrase3 = new Phrase(xmXhByXyList.get(x),contextFont);
	    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
	    												para3.add(phrase3);
	    												count=1;
	    												break;
	    											}else{
	    												count=3;
	    											}
	    											
	    										}else{
	    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
	    												count=x-j;
	    												break;
	    											}else{	
	    												phrase3 = new Phrase("   "+xmXhByXyList.get(x),contextFont);
	    												count=3;
	    											}
	    										}
	    										para3.add(phrase3);
		    									
		    								}
	    									
	    								}
	    								
//	    								if(j+1<xmXhByXyList.size()){
//	    									phrase3 = new Phrase("     "+xmXhByXyList.get(j+1),contextFont);
//		    								para3.add(phrase3);
//	    								}
//	    								if(j+2<xmXhByXyList.size()){
//	    									phrase3 = new Phrase("     "+xmXhByXyList.get(j+2),contextFont);
//		    								para3.add(phrase3);
//	    								}
	    								para3.setIndentationLeft(10);
	    								document.add(para3);
									}
	    							para3 = new Paragraph("  ");
	    							//para3.setSpacingAfter(10);
	    							document.add(para3);
//	    						}
//	    						if(modlen != 0){
//	    							for (int j = len-modlen; j < xmXhByXyList.size(); j++) {
//										
//									}
//	    						}
	    					}
	    				}//二级 结束---------------------------------------------------------
	    			}//一级结束始---------------------------------------------------------
	    		  
	    		}
	       
	        document.close();  
       
	}
	
	/**
	 * 
	 * @描述: 浙大年鉴导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-14 下午04:01:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param model
	 * @param user
	 * @param path
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWordNjdc(HttpServletResponse response,HjmdExportModel model,User user) throws Exception{
		Document document = new Document(PageSize.A4);  
        //建立一个书写器，与document对象关联  
		//相应头设置
	    response.setHeader("Content-Disposition", "attachment;filename=\""
               + new String("njdc_zjdx.doc".getBytes(), "GBK") + "\"");
        RtfWriter2.getInstance(document, response.getOutputStream());
        //打开文档
        document.open();  
        //设置中文字体  
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
        //标题字体风格  
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        //正文字体风格  
        Font contextFont = new Font(bfChinese,13,Font.NORMAL);  
        
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//项目类型
		String[] xmlx = model.getXmlx();
		//学院代码
		String[] xydm = model.getXydm();
		//项目性质
		String[] xmxz = model.getXmxz();
		//项目名称
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		// 学院列表
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//项目类型名称
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		//过滤评奖周期（学期评奖才要打印出学期，否则不用）
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		Paragraph title = null;
		if("1".equals(pjzq)){ 	
		// 输出第一行的表头
			title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", xqmc, "学期", lxmc, "发文名单"),titleFont); 
		}else {
			title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"),titleFont); 
		}
		title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		if (!xmList.isEmpty()) {
			Paragraph paraitra1 = null;
			int propertiesrow =3;
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				
				 
			
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				paraitra1 = new Paragraph(jxjmc,titleFont);
				paraitra1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraitra1);
				
		      
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				Paragraph paraitra2 = null;
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++propertiesrow;
						xy+="("+xymcArr[2]+"人)";
						paraitra2 = new Paragraph(xy,titleFont);
						paraitra2.setIndentationLeft(10);
						//paraitra2.setSpacingAfter(10);
						document.add(paraitra2);
//					    run2.setFontSize(32);
//				        run2.setFtcAscii(11);
//				        run2.setBold(true);
//						System.out.println("学院："+xy);
//						Label xymcCell = new Label(1,properties.row,xy );
//						xymcCell.setCellFormat(jxxyFormat);
//						ws.addCell(xymcCell);
//						ws.mergeCells(1,properties.row, 3, properties.row);
						//输出第三级将学院下面的获奖名单输出
			//			writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
						int len = xmXhByXyList.size();
				//		int modlen = len % 3;
//						if(len >= 3){
							Paragraph para3 = null;
							Phrase phrase3 = null;
							//活动技术器变量用来循环
							int count = 9;
							//新加行标志
//							boolean flag = true;
							//三级循环，五个名字为一行
							for (int j = 0; j < len; j=j+count) {
								para3 = new Paragraph();
								int strlen = 35;
								int realtotallen = 0;
								int realeverylen = 0;
								//名单写入，常规导出一行9个，如果出现姓名较长的按一行35个字符算，如果超出35个字符，就会另起一行进行填写。
								for(int x=j;x<j+9;x++){
									if(x<len){
										if(x == j){
		    								phrase3 = new Phrase(xmByXyList.get(x),contextFont);
		    								realeverylen = xmByXyList.get(x).length();
		    								realtotallen =realtotallen+realeverylen;
											count=9;
											
										}else{
											realeverylen = xmByXyList.get(x).length();
											realtotallen =realtotallen+realeverylen;
											if(realtotallen >strlen){
												count=x-j;
												break;
											}else{
												phrase3 = new Phrase(" "+xmByXyList.get(x),contextFont);
												count=9;
											}
										}
										para3.add(phrase3);
    									
    								}
									
								}
								
//								if(j+1<xmXhByXyList.size()){
//									phrase3 = new Phrase("     "+xmXhByXyList.get(j+1),contextFont);
//    								para3.add(phrase3);
//								}
//								if(j+2<xmXhByXyList.size()){
//									phrase3 = new Phrase("     "+xmXhByXyList.get(j+2),contextFont);
//    								para3.add(phrase3);
//								}
								para3.setIndentationLeft(10);
								document.add(para3);
							}
							para3 = new Paragraph("  ");
							//para3.setSpacingAfter(10);
							document.add(para3);
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		  
		}
		//关闭文档
		document.close();
	}

	/**
	 *  根据学年、学院数组查询生成名单汇总表文件数组.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 17:07
	 * @param model
	 * @return java.io.File[]
	 * @throw
	 */
	public File[] getPymdhzbFiles(TjcxModel model) {

		List<File> fileList = new ArrayList<File>();
		String xn = model.getXn();
		String [] xyArr = model.getXyArr()[0].split(",");

		for(String xydm:xyArr){
			Map<String,Object> data = new HashMap<String,Object>();

			//学院名称
			String xymc = dao.getXymcByXydm(xydm);
			//十佳班级
			List<HashMap<String,String>> sjbjList = dao.getBjpjList(xn,xydm,sjbj);
			//先进班集体
			List<HashMap<String,String>> xjbjtList = dao.getBjpjList(xn,xydm,xjbjt);
			//三好学生标兵
			List<HashMap<String,String>> shxsbbList = dao.getGrpjList(xn,xydm,shxsbb);
			data.put("shxsbbCount",shxsbbList.size());
			shxsbbList = addBlankMapByCols(shxsbbList,5);
			//三好学生
			List<HashMap<String,String>> shxsList = dao.getGrpjList(xn,xydm,shxs);
			data.put("shxsCount",shxsList.size());
			shxsList = addBlankMapByCols(shxsList,5);
			//优秀学生干部
			List<HashMap<String,String>> yxxsgbList = dao.getGrpjList(xn,xydm,yxxsgb);
			data.put("yxxsgbCount",yxxsgbList.size());
			yxxsgbList = addBlankMapByCols(yxxsgbList,5);
			//优秀社会工作者
			List<HashMap<String,String>> yxshgzzList = dao.getGrpjList(xn,xydm,yxshgzz);
			data.put("yxshgzzCount",yxshgzzList.size());
			yxshgzzList = addBlankMapByCols(yxshgzzList,5);

			data.put("xymc",xymc);
			data.put("sjbj",sjbjList);
			data.put("xjbjt",xjbjtList);
			data.put("shxsbb",shxsbbList);
			data.put("shxs",shxsList);
			data.put("yxxsgb",yxxsgbList);
			data.put("yxshgzz",yxshgzzList);

			String year = DateUtils.getYear();
			String month = DateUtils.getMonth();
			String day = DateUtils.getDayOfMonth();

			data.put("year",year);
			data.put("month",month);
			data.put("day",day);

			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "yxpyhzmdword_10530.xml", xymc+"获奖名单统计");

			if(wordFile != null){
				fileList.add(wordFile);
			}
		}

		return fileList.toArray(new File[]{});
	}

	/**
	 *  按要求填充空白map到list.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-20 15:47
	 * @param rList
	 * @param cols
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	private List<HashMap<String,String>> addBlankMapByCols(List<HashMap<String,String>> rList,int cols){

		int size = rList.size();
		if(size>0&&size%cols!=0){
			int len = cols - size%cols;
			for (int i=0;i<len;i++){
				rList.add(new HashMap<String, String>());
			}
		}
		return rList;
	}

	public File getTjExcelFile(List<HashMap<String, String>> resultList,HashMap<String, String> infomap)
			throws Exception {
		//画Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);


		if(!file.exists()){
			file.createNewFile();
		}

		//创建工作簿
		WritableWorkbook  wwb = Workbook.createWorkbook(file);

		//设置相关格式
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置标题字体
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		WritableFont zbFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置制表栏

		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat zb_cf = new WritableCellFormat(zbFont);//设置正文单元格字体
		zb_cf.setWrap(true);
		zb_cf.setAlignment(Alignment.LEFT);//设置正文单元格水平对齐
		zb_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//设置垂直对齐

		body_cf.setWrap(true);
		head_cf.setWrap(true);//自动换行
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头垂直对齐
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直对齐
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
				jxl.format.Colour.BLACK);//设置正文单元格边框
		String xymc = "";
		if(!StringUtils.isNull(infomap.get("xymc"))){
			xymc = infomap.get("xymc");
		}
		String title = xymc+infomap.get("xn")+"学年"+infomap.get("xqmc")+"期末奖学金统计";	//标题

		//创建工作表
		WritableSheet sheet = wwb.createSheet("sheet 1", 0);
		//设置各列列宽
		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 15);
		sheet.setColumnView(4, 15);

		//行高
		sheet.setRowView(0,800);
		sheet.setRowView(1,600);

		//合并单元格
		//第一个参数：要合并的单元格最左上角的列号，
		//第二个参数：要合并的单元格最左上角的行号，
		//第三个参数：要合并的单元格最右角的列号，
		//第四个参数：要合并的单元格最右下角的行
		sheet.mergeCells(0, 0, 4, 0);	//标题

		//创建标题及表头
		Label t_0_0 = new Label(0, 0,title,title_cf);
		Label h_0_1 = new Label(0,1,"奖项",head_cf);
		Label h_1_1 = new Label(1,1,"班级",head_cf);
		Label h_2_1 = new Label(2,1,"姓名",head_cf);
		Label h_3_1 = new Label(3,1,"金额",head_cf);
		Label h_4_1 = new Label(4,1,"合计",head_cf);

		sheet.addCell(t_0_0);
		sheet.addCell(h_0_1);
		sheet.addCell(h_1_1);
		sheet.addCell(h_2_1);
		sheet.addCell(h_3_1);
		sheet.addCell(h_4_1);
		/*一开始思路有问题，写的巨差，算了，不想改，谁愿意改谁改吧*/
		//遍历创建单元格
		int size = resultList.size();
		if(size>2){
			int zje = 0;
			int hjje = 0;
			int hjrs = 1;//获奖人数
			for(int j=0;j<size;j++){
				HashMap<String,String> map = resultList.get(j);
				Label bjmc = new Label(1, j+2, map.get("bjmc"), body_cf);		//班级
				Label xm = new Label(2, j+2, map.get("xm"), body_cf);		//姓名
				String xmje = map.get("xmje") == null?"":map.get("xmje");
				Label je = new Label(3, j+2,xmje , body_cf);		//金额
				int temp = 0;
				sheet.addCell(bjmc);
				sheet.addCell(xm);
				sheet.addCell(je);
				if(!StringUtils.isNull(xmje)){
					temp= Integer.valueOf(xmje);
					zje = zje+temp;
				}
				hjje = hjje+temp;
				if(j > 0){
					if(map.get("xmmc").equals(resultList.get(j-1).get("xmmc"))){

						hjrs++;
						if(j == (size-1)){
							sheet.mergeCells(0, j+3-hjrs, 0, j+2);//奖项名称列合并单元格
							sheet.mergeCells(4, j+3-hjrs, 4, j+2);//合计列合并单元格
							Label xmmc = new Label(0, j+3-hjrs,
									map.get("xmmc")+"（"+hjrs+"人）", body_cf);//项目名称
							Label hj = new Label(4, j+3-hjrs, String.valueOf(hjje), body_cf);//项目名称
							sheet.addCell(xmmc);
							sheet.addCell(hj);
						}
					}else{
						hjje = hjje - temp;
						sheet.mergeCells(0, j+2-hjrs, 0, j+1);//奖项名称列合并单元格
						sheet.mergeCells(4, j+2-hjrs, 4, j+1);//合计列合并单元格
						Label xmmc = new Label(0, j+2-hjrs,
								resultList.get(j-1).get("xmmc")+"（"+hjrs+"人）", body_cf);//项目名称
						Label hj = new Label(4, j+2-hjrs, String.valueOf(hjje), body_cf);//项目名称
						sheet.addCell(xmmc);
						sheet.addCell(hj);
						if(j == (size-1)) {
							Label xmmc_last = new Label(0, j+2,
									map.get("xmmc")+"（1人）", body_cf);//项目名称
							Label hj_last = new Label(4, j+2, xmje, body_cf);//项目名称
							sheet.addCell(xmmc_last);
							sheet.addCell(hj_last);
						}
						hjje=temp;
						hjrs=1;
					}

				}
			}
			sheet.setRowView(size+2,600);
			Label hj_label = new Label(3, size+2, "合计：", title_cf);
			Label hjje_label = new Label(4, size+2, String.valueOf(zje), title_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			sheet.mergeCells(0, size+3, 3, size+3);//制表行合并单元格
			String zb = "制表：" + infomap.get("userName")+
					"                      审核：                              领导:";
			Label zb_label = new Label(0, size+3, zb, zb_cf);
			sheet.addCell(zb_label);
		}
		if(size == 1){
			HashMap<String,String> map = resultList.get(0);
			Label bjmc = new Label(1, 2, map.get("bjmc"), body_cf);		//班级
			Label xm = new Label(2, 2, map.get("xm"), body_cf);		//姓名
			String xmje = map.get("xmje") == null?"":map.get("xmje");
			Label je = new Label(3, 2,xmje , body_cf);		//金额
			Label xmmc = new Label(0, 2,
					map.get("xmmc")+"（1人）", body_cf);//项目名称
			Label hj = new Label(4, 2, String.valueOf(xmje), body_cf);//合计
			sheet.addCell(xmmc);
			sheet.addCell(hj);
			sheet.addCell(bjmc);
			sheet.addCell(xm);
			sheet.addCell(je);
			sheet.setRowView(size+2,600);
			Label hj_label = new Label(3, size+2, "合计：", title_cf);
			Label hjje_label = new Label(4, size+2, String.valueOf(xmje), title_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			sheet.mergeCells(0, size+3, 3, size+3);//制表行合并单元格
			String zb = "制表：" + infomap.get("userName")+
					"                      审核：                              领导:";
			Label zb_label = new Label(0, size+3, zb, zb_cf);
			sheet.addCell(zb_label);
		}

		//如果数据为空
		if(resultList == null||resultList.size()==0){
			//创建工作表
			WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"暂无数据！");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}

	public File getLqExcelFile(List<HashMap<String, String>> resultList,HashMap<String, String> infomap)
			throws Exception {
		//画Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		//创建工作簿
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		//设置相关格式
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置标题字体
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		WritableFont zbFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置制表栏

		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat zb_cf = new WritableCellFormat(zbFont);//设置正文单元格字体
		zb_cf.setWrap(true);
		zb_cf.setAlignment(Alignment.LEFT);//设置正文单元格水平对齐
		zb_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//设置垂直对齐

		body_cf.setWrap(true);
		head_cf.setWrap(true);//自动换行
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头垂直对齐
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直对齐
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
				jxl.format.Colour.BLACK);//设置正文单元格边框
		String xymc = "";
		if(!StringUtils.isNull(infomap.get("xymc"))){
			xymc = "(" + infomap.get("xymc")+")";
		}
		String title = infomap.get("xn")+infomap.get("xqmc")+"学生奖学金领取表"+xymc;	//标题

		//创建工作表
		WritableSheet sheet = wwb.createSheet("sheet 1", 0);
		//设置各列列宽
		sheet.setColumnView(0, 10);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 15);
		sheet.setColumnView(4, 25);

		//行高
		sheet.setRowView(0,800);

		//合并单元格
		//第一个参数：要合并的单元格最左上角的列号，
		//第二个参数：要合并的单元格最左上角的行号，
		//第三个参数：要合并的单元格最右角的列号，
		//第四个参数：要合并的单元格最右下角的行
		sheet.mergeCells(0, 0, 4, 0);	//标题
		sheet.mergeCells(0, 1, 0, 2);	//表头
		sheet.mergeCells(1, 1, 1, 2);	//表头
		sheet.mergeCells(2, 1, 2, 2);	//表头
		sheet.mergeCells(4, 1, 4, 2);	//表头

		//创建标题及表头
		Label t_0_0 = new Label(0, 0,title,title_cf);
		Label h_0_1 = new Label(0,1,"序",head_cf);
		Label h_1_1 = new Label(1,1,"班级",head_cf);
		Label h_2_1 = new Label(2,1,"姓名",head_cf);
		Label h_3_1 = new Label(3,1,"奖励",head_cf);
		Label h_3_2 = new Label(3,2,"金额",head_cf);
		Label h_4_1 = new Label(4,1,"领取人签名",head_cf);

		sheet.addCell(t_0_0);
		sheet.addCell(h_0_1);
		sheet.addCell(h_1_1);
		sheet.addCell(h_2_1);
		sheet.addCell(h_3_1);
		sheet.addCell(h_3_2);
		sheet.addCell(h_4_1);
		//遍历创建单元格
		int size = resultList.size();
		if(size>0){
			int bjrs = 1;
			int hj = 0;
			for(int j=0;j<size;j++){
				HashMap<String,String> map = resultList.get(j);
				Label xh = new Label(0, j+3, String.valueOf(j+1), body_cf);		//序号
				Label xm = new Label(2, j+3, map.get("xm"), body_cf);		//姓名
				String xmje = map.get("xmje") == null?"":map.get("xmje");
				Label je = new Label(3, j+3,xmje , body_cf);		//金额
				sheet.addCell(xh);
				sheet.addCell(xm);
				sheet.addCell(je);
				if(!StringUtils.isNull(xmje))
					hj = hj+Integer.valueOf(xmje);
				if(j<size-1){
					if(map.get("bjdm").equals(resultList.get(j+1).get("bjdm"))){
						bjrs++;
						if((j+1) == (size-1)){
							sheet.mergeCells(1, j+5-bjrs, 1, j+4);
							sheet.mergeCells(4, j+5-bjrs, 4, j+4);
							Label bjmc = new Label(1, j+5-bjrs, resultList.get(j).get("bjmc"), body_cf);//班级
							Label lqrqm = new Label(4, j+5-bjrs, "", body_cf);//领取人签名
							sheet.addCell(bjmc);
							sheet.addCell(lqrqm);
						}
					}else{
						sheet.mergeCells(1, j+4-bjrs, 1, j+3);
						sheet.mergeCells(4, j+4-bjrs, 4, j+3);
						Label bjmc = new Label(1, j+4-bjrs, map.get("bjmc"), body_cf);//班级
						Label lqrqm = new Label(4, j+4-bjrs, "", body_cf);//领取人签名
						sheet.addCell(bjmc);
						sheet.addCell(lqrqm);
						if((j+1) == (size-1)){
							Label bjmc_last = new Label(1, size+2, resultList.get(j+1).get("bjmc"), body_cf);//班级
							Label lqrqm_last = new Label(4, size+2, "", body_cf);//领取人签名
							sheet.addCell(bjmc_last);
							sheet.addCell(lqrqm_last);
						}
						bjrs=1;
					}
				}

			}
			sheet.mergeCells(0, size+3, 2, size+3);//合计列合并单元格
			sheet.mergeCells(3, size+3, 4, size+3);//合计列合并单元格
			sheet.mergeCells(0, size+4, 3, size+4);//制表列合并单元格
			Label hj_label = new Label(0, size+3, "合计：", head_cf);
			Label hjje_label = new Label(3, size+3, String.valueOf(hj), head_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			String zb = "制表：" + infomap.get("userName")+
					"                                     审核：";
			Label zb_label = new Label(0, size+4, zb, zb_cf);
			Label sj_label = new Label(4, size+5, infomap.get("sj"), zb_cf);
			sheet.addCell(zb_label);
			sheet.addCell(sj_label);
		}

		//如果数据为空
		if(resultList == null||resultList.size()==0){
			//创建工作表
			WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"暂无数据！");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}
}
