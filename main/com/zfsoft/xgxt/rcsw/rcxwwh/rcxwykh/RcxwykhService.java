package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwykh;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;

/**
 * 日常行为月考核 
 */
public class RcxwykhService extends SuperServiceImpl<RcxwykhForm, RcxwykhDao> {
	private static final String[] YFDM_ARR = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] YFMC_ARR = new String[] { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" };
	private RcxwykhDao dao = new RcxwykhDao();

	public RcxwykhService() {
		super.setDao(dao);
	}
	/**
	 * 查看日常行为月考核
	 */
	public List<HashMap<String, String>> getPageListView(RcxwykhForm t, User user)
			throws Exception {
		return dao.getPageListView(t, user);
	}
	/**
	 * 太原旅游职业学院导出
	 */
	public void rcxwykhDc_13696(RcxwykhForm myForm,OutputStream os, User user)throws Exception{
		// 宋体,18,CENTRE,加粗
		WritableCellFormat s18CentreBOLDFormat = new WritableCellFormat();
		WritableFont s18CentreBOLDFont = new WritableFont(WritableFont.createFont("宋体"),18);
		s18CentreBOLDFont.setBoldStyle(WritableFont.BOLD);
		s18CentreBOLDFormat.setFont(s18CentreBOLDFont);
		s18CentreBOLDFormat.setAlignment(Alignment.CENTRE);
		s18CentreBOLDFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 宋体,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		s12CentreFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 宋体,12,LEFT
		WritableCellFormat s12LeftFormat = new WritableCellFormat();
		WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12LeftFormat.setFont(s12LeftFont);
		s12LeftFormat.setAlignment(Alignment.LEFT);
		s12LeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 宋体,12,RIGHT
		WritableCellFormat s12RightFormat = new WritableCellFormat();
		WritableFont s12RightFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12RightFormat.setFont(s12RightFont);
		s12RightFormat.setAlignment(Alignment.RIGHT);
		s12RightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 宋体,12,右边框
		WritableCellFormat s12RightBorderFormat = new WritableCellFormat();
		WritableFont s12RightBorderFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12RightBorderFormat.setFont(s12RightBorderFont);
		s12RightBorderFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		s12RightBorderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s12RightBorderFormat.setWrap(true);
		// 宋体,12,下、右边框
		WritableCellFormat s12RightBottomFormat = new WritableCellFormat();
		WritableFont s12RightBottomFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12RightBottomFormat.setFont(s12RightBottomFont);
		s12RightBottomFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		s12RightBottomFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		s12RightBottomFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s12RightBottomFormat.setWrap(true);
		// 宋体,11,RIGHT
		WritableCellFormat s11RightFormat = new WritableCellFormat();
		WritableFont s11RightFont = new WritableFont(WritableFont.createFont("宋体"),11);
		s11RightFormat.setFont(s11RightFont);
		s11RightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		s11RightFormat.setAlignment(Alignment.RIGHT);
		// 宋体,12,CENTRE,边框
		WritableCellFormat s12CentreBorderFormat = new WritableCellFormat();
		WritableFont s12CentreBorderFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreBorderFormat.setFont(s12CentreBorderFont);
		s12CentreBorderFormat.setAlignment(Alignment.CENTRE);
		s12CentreBorderFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		s12CentreBorderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String nd = searchModel.getSearch_tj_nd()[0];
		String yf = searchModel.getSearch_tj_yf()[0];
		String bjdm = searchModel.getSearch_tj_bj()[0];
		// 班级信息
		HashMap<String,String> bjxxMap = dao.querBjxx(bjdm);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("月考核表", 0);
		// 当前列
		int col = 0;
		int colTemp  = 0;
		int row  = 2;
		// 第3行
		ws.setRowView(row, 450);
		ws.addCell(new Label(col, row, "姓名", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		//行为大类
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwdlList = service.getXwdlList(null);
		for (int i = 0; i < xwdlList.size(); i++) {
			colTemp = col;
			HashMap<String,String> xwdlMap = xwdlList.get(i);
			String rcxwlbdldm = xwdlMap.get("rcxwlbdldm");
			String rcxwlbdlmc = xwdlMap.get("rcxwlbdlmc");
			ws.addCell(new Label(col, row, rcxwlbdlmc, s12CentreBorderFormat));
			// 行为类别
			List<HashMap<String,String>> xwlbList = service.getXwlbList(rcxwlbdldm, null);
			for (int j = 0; j < xwlbList.size(); j++) {
				HashMap<String,String> xwlbMap = xwlbList.get(j);
				String rcxwlbmc = xwlbMap.get("rcxwlbmc");
				ws.setRowView(row + 1, 600);
				ws.addCell(new Label(col, row + 1, rcxwlbmc, s12CentreBorderFormat));
				col++;
			}
			ws.mergeCells(colTemp, row, col - 1, row);
//			System.out.println("colTemp:"+colTemp + " (col - 1):"+(col - 1));
		}
		ws.addCell(new Label(col, row, "总分", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		ws.addCell(new Label(col, row, "月小评等级", s12CentreBorderFormat));
		ws.mergeCells(col, row, col, row + 1);
		col++;
		// 总列数
		int colSum = col;
		ws.setColumnView(colSum - 1, 12);
		ws.setColumnView(colSum - 2, 10);
		// 第5行
		row++;
		List<HashMap<String,String>> resultList = dao.rcxwykhDc_13696(nd, yf, bjdm);
		// ========== 补空行 < =============
		int listSize = 18 - resultList.size();
		for (int i = 0 ; i < listSize ; i++){
			resultList.add(new HashMap<String, String>());
		}	
		// ========== 补空行 > =============
		for (HashMap<String, String> resultMap : resultList) {
			col = 0; // 重置
			row++;
			ws.setRowView(row, 390);
			ws.addCell(new Label(col, row, resultMap.get("xm"), s12CentreBorderFormat));
			col++;
			//行为大类
			for (int i = 0; i < xwdlList.size(); i++) {
				HashMap<String,String> xwdlMap = xwdlList.get(i);
				String rcxwlbdldm = xwdlMap.get("rcxwlbdldm");
				// 行为类别
				List<HashMap<String,String>> xwlbList = service.getXwlbList(rcxwlbdldm, null);
				for (int j = 0; j < xwlbList.size(); j++) {
					ws.addCell(new Label(col, row, resultMap.get("fzsum"+i+"_"+j), s12CentreBorderFormat));
					col++;
				}
			}
			ws.addCell(new Label(col, row, resultMap.get("fzsum"), s12CentreBorderFormat));
			col++;
			ws.addCell(new Label(col, row, resultMap.get("ydjmc"), s12CentreBorderFormat));
			col++;
		}
		// 第1行
		ws.setRowView(0, 705);
		ws.addCell(new Label(0, 0, Base.xxmc + "德育量化月考核表", s18CentreBOLDFormat));
		ws.mergeCells(0, 0, colSum - 1, 0);
		// 第2行
		int yfIndex = Arrays.binarySearch(YFDM_ARR, yf);
		ws.setRowView(1, 390);
		ws.addCell(new Label(0, 1, "        "+bjxxMap.get("xymc")+" "+bjxxMap.get("bjmc"), s12LeftFormat));
		int mid = (int)(colSum - 1) / 2;
		ws.mergeCells(0, 1, mid, 1);
		ws.addCell(new Label(mid + 1, 1, "考核时间："+nd+"年"+YFMC_ARR[yfIndex], s12RightFormat));
		ws.mergeCells(mid + 1, 1, colSum - 1, 1);
		// 页尾
		String msg1 = "月评定标准说明：①优秀：月负分累积在0-5分以内者；②良好：月负分累积在6-10分以内者；③及格：月负分累积在30分（不含30分）以内者；不及格：月负分累积在30（含30分）以上及当日违纪受处分者";
		row++;
		ws.setRowView(row, 720);
		ws.addCell(new Label(0, row, msg1, s12RightBorderFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		String msg2 = "月负分累积说明：①45-54分者：给予警告处分处理；②55-64分者：给予严重警告处分处理；③65-74分者：给予记过处分处理；75分以上者视情节给予留校察看或开除学籍处分处理。";
		row++;
		ws.setRowView(row, 720);
		ws.addCell(new Label(0, row, msg2, s12RightBottomFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "班主任签字：             系部主任签字：               ", s11RightFormat));
		ws.mergeCells(0, row, colSum - 1, row);
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
