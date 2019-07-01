package xsgzgl.pjpy.general.tjcx.hjmdhz;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjmdhzInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖名单汇总_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class TjcxHjmdhzService extends CommService implements
		TjcxHjmdhzInterface {

	TjcxHjmdhzDAO dao = new TjcxHjmdhzDAO();

	/**
	 * 获得表头文件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getHjmdhzTop(TjcxHjmdhzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pn", "xh", "xm", "nj", "bjmc", "xmlx",
				"xmmc", "hdsj" };
		String[] cn = new String[] { "学年", "学号", "姓名", "院系名称", "项目类型", "项目名称",
				"获得时间" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getHjmdhzList(PjpyGeneralForm myForm,
			TjcxHjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getHjmdhzList(myForm, model, user);

		return list;
	}

	/**
	 * 构建结果集
	 * 
	 * @author 伟大的骆
	 */
	public String createHjmdhzHTML(SearchRsModel rsModel,
			TjcxHjmdhzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 导出获奖名单汇总
	 * 
	 * @author 伟大的骆
	 */
	public void expHjmdhz(TjcxHjmdhzModel model, OutputStream os)
			throws Exception {

		// 学年
		String xn = model.getXn();
		// 项目类型
		String xmlx = model.getXmlx();
		// 院系代码
		String arr_xydm[] = model.getXydm();

		// 项目类型名称
		String xmlxmc = "01".equalsIgnoreCase(xmlx) ? "奖学金" : "荣誉称号";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmlxmc + "获奖名单", 0);
		
		// 学院列表
		List<String[]> xymcList = dao.getXyList(xn, xmlx, arr_xydm);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmrsList(xn, xmlx);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList(xn, xmlx, arr_xydm);
		
		//奖学金名称样式
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		// 输出第一行的表头
		ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation
				.getXxmc(), xn, "学年第", "二", "学期", xmlxmc, "获得者名单"), jxjFormat));
		ws.mergeCells(0, 0, 8, 0);
		
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[1];
				String jxjdm = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (共" +jxjdmArr[2] +"人)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//输出第二级将学院名称写到EXCEL中
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjdm.equalsIgnoreCase(xymcArr[2])) {						
						String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
						String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						for (String[] xmArr : xmList) {
							if (xmArr != null && xmArr.length >= 3
									&& xydm.equalsIgnoreCase(xmArr[1])
									&& jxjdm.equalsIgnoreCase(xmArr[0])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[3] + "人)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//将获奖名单写入到EXCEL中
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	
	private class JxjExportProperties{
		 int x_axis = 0;//X座标
		 int row = 1;//行标
		 int rowCellCount = 1;//每行的cell个数计数
		 int[] maxLength = {7,7,7,7,7,7,7};//默认的列宽
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
				if (properties.rowCellCount == 8) {
					properties.row++;// 满10个就换行
					properties.x_axis = 0;
					properties.rowCellCount = 1;// 重新每行计数
				}
				// 判断姓名长度,然后根据长度控制合并单元格，及超出长度的情况下进行换行
				if (cellContent.length() > 3) {
					int xmLength = cellContent.length() / 3
							+ (cellContent.length() % 3 == 0 ? 0 : 1);
					int pre_x_axis = properties.x_axis;// 合并前的x轴值
					if (pre_x_axis + xmLength > 10) {
						properties.row++;// 姓名过长，换行
						properties.x_axis = rowCellCount;
						properties.rowCellCount = 1;// 重新每行计数
						pre_x_axis = properties.x_axis;
					}
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					pre_x_axis++;
					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
							+ xmLength - 1, properties.row);
					properties.x_axis = pre_x_axis + xmLength - 1;
				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
				}
			}
		}
		properties.row += 1;// 新奖学金换行
	}
}