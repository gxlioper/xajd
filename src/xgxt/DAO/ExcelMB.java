/**
 * 
 */
package xgxt.DAO;

import java.util.List;

import xgxt.utils.String.StringUtils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Administrator
 *
 */
public class ExcelMB {
	/**
	 * 在第一行打印标题
	 * @param ws     指定可写的工作薄
	 * @param title   标题
	 * @param cols    一共合并多少列
	 * @param cols    行高度
	 * @return
	 */
	public void printTitle(WritableSheet ws,String title,int cols,int hight) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(18);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(0, 0, cols-1, 0); 
		ws.setRowView(0, hight); // 设置指定行高
		ws.addCell(new Label(0,0,title,wcf));
	}
	
	/**
	 * @param ws       可写的sheet
	 * @param content  输入的内容
	 * @param cols     指定列数
	 * @param column   指定写入数据的列
	 * @param row      指定写入数据的行
	 * @param pontSize 字体大小
	 * @param bold     是否粗体
	 * @param align    单元格字位置（左、右、居中）
	 * @param br       是否自动换行
	 */
	public void printThead(WritableSheet ws,String content,int cols,int column,int row,int pontSize,boolean bold ,Alignment align,boolean br) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if(bold){
		wf.setBoldStyle(WritableFont.BOLD);
		}
		wf.setPointSize(pontSize);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(column, row, column+cols-1, row);
		ws.addCell(new Label(column,row,content));
		ws.addCell(new Label(column,row,content,wcf));
	}
	

	public void unitiveFormat(WritableSheet ws) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(14);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);
		wcf.setFont(wf);		
	}
	
	/**
	 * @param ws       可写的sheet
	 * @param content  输入的内容
	 * @param column   指定写入数据的列
	 * @param row      指定写入数据的行
	 * @param pontSize 字体大小
	 * @param bold     是否粗体
	 * @param align    单元格字位置（左、右、居中）
	 * @param br       是否自动换行
	 */

	public void printToOneCell(WritableSheet ws,String content,int column,int row,int pontSize,boolean bold ,Alignment align,boolean br) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if(bold){
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wf.setPointSize(pontSize);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);		
		wcf.setFont(wf);
		ws.addCell(new Label(column,row,content,wcf));
	}
	//数据录入
	public void printToOneCell_self(WritableSheet ws,String content,int column,int row,int fontsize,boolean warp,boolean border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setPointSize(fontsize);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(warp);
		wcf.setLocked(false);
		wcf.setFont(wf);
		if(border){
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		}
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		ws.addCell(new Label(column,row,content,wcf));
	}
	
	/**
	 * @param ws
	 *            可写的sheet
	 * @param content
	 *            输入的内容
	 * @param column
	 *            指定写入数据的列
	 * @param row
	 *            指定写入数据的行
	 * @param pontSize
	 *            字体大小
	 * @param bold
	 *            是否粗体
	 * @param align
	 *            单元格字位置（左、右、居中）
	 * @param valign
	 *            单元格字纵向位置（上、下、居中）
	 * @param wrap
	 *            是否自动换行
	 * @param cols
	 *            行高度
	 * @param border
	 *            边框
	 */

	public void printToOneCell_multy(WritableSheet ws, String content,
			int column, int row, int pontSize, boolean bold, Alignment align,
			VerticalAlignment valign,
			boolean wrap, int hight, Border border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wcf.setBorder(border, BorderLineStyle.THIN);
		wf.setPointSize(pontSize);
		wcf.setAlignment(align);
		wcf.setVerticalAlignment(valign);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.setRowView(0, hight); // 设置指定行高
		ws.addCell(new Label(column, row, content, wcf));
	}
	
	/**
	 * 传入相关参数返回单元写入样式
	 * @param pontSize  字体大小
	 * @param bold  是否加粗
	 * @param align 单元格左右对齐
	 * @param valign 单元格上下对齐
	 * @param border 是否显示边框
	 * @return
	 * @throws Exception
	 */
	public static WritableCellFormat getWritableCellFormat(int pontSize,
			boolean bold, Alignment align, VerticalAlignment valign,
			Border border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wcf.setBorder(border, BorderLineStyle.THIN);
		wf.setPointSize(pontSize);
		wcf.setAlignment(align);
		wcf.setVerticalAlignment(valign);
		wcf.setWrap(true);
		wcf.setFont(wf);
		
		return wcf;
	}
	
	/**
	 * 写入表头到EXCEL表中，只适用于第一级标题
	 * @param ws  要输出的EXCEL单元
	 * @param title 要输出的表头字符串
	 * @param x  要输出的X轴坐标位置
	 * @param y  要输出的Y轴坐标位置
	 * @param unionX 要合并的X轴列数
	 * @param unionY 要合并的Y轴列数
	 * @param format  输出数据样式
	 */
	public static void writeTitleToCell(WritableSheet ws, String title, int x,
			int y, int unionX, int unionY, WritableCellFormat format)
			throws Exception {
		if (StringUtils.isNotNull(title)) {
			ws.addCell(new Label(x,y,title,format));
			ws.mergeCells(0, 0, unionX, unionY);
		}
	}
	
	/**
	 * 写入表头到EXCEL表中,只适用于第二级表头  表头数据必须为数组
	 * @param ws  要输出的EXCEL单元
	 * @param title 要输出的表头字符串
	 * @param x  要输出的X轴坐标位置
	 * @param y  要输出的Y轴坐标位置
	 * @param format  输出数据样式
	 */
	public static void writeEjTitleToCell(WritableSheet ws, String[] title,
			int x, int y, WritableCellFormat format) {
		try {
			if (title != null && title.length > 0) {
				// 循环写入每一个单元格表格
				for (int i = 0; i < title.length; i++) {
					ws.addCell(new Label(x + i, y, title[i], format));
					ws.setColumnView(x + i, StringUtils.isNull(title[i]) ? 6
							: title[i].length() * 2);
				}
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 循环输出查询数据到EXCEL表中，只适于有规则的循环 X轴默认从0开始输出
	 * @param ws  要输出的EXCEL单元
	 * @param data  输出的数据，格式必须是 List<String[]>
	 * @param y  输出的行高
	 * @param format  输出数据样式
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = 0;
				if (oneRs != null && oneRs.length > 0) {
					for (String value : oneRs) {
						ws.addCell(new Label(k, i + y, value, format));
						//设置列宽
						ws.setColumnView(k, (StringUtils.isNull(value) ? 6
								: value.length()) * 3);
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * 循环输出查询数据到EXCEL表中，只适于有规则的循环
	 * @param ws  要输出的EXCEL单元
	 * @param data  输出的数据，格式必须是 List<String[]>
	 * @param x 输出的X起始位置
	 * @param y  输出的行高
	 * @param format  输出数据样式
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int x, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = x;
				if (oneRs != null && oneRs.length > 0) {
					for (String value : oneRs) {
						ws.addCell(new Label(k, i + y, value, format));
						//设置列宽
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * 循环输出查询数据到EXCEL表中，只适于有规则的循环
	 * @param ws  要输出的EXCEL单元
	 * @param data  输出的数据，格式必须是 List<String[]>
	 * @param dataLen 要输出每一条数据的长度
	 * @param x 输出的X起始位置
	 * @param y  输出的行高
	 * @param format  输出数据样式
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int dataLen,int x, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = x;
				if (oneRs != null && oneRs.length > 0 && dataLen < oneRs.length) {
					for (int l=0;l<dataLen;l++) {
						ws.addCell(new Label(k, i + y, oneRs[l], format));
						//设置列宽						
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * 合并批定的单元格数据
	 * @param rs  数据集
	 * @param ws  单元格
	 * @param col   列起点
	 * @param row   行起点
	 * @param format 样式
	 * @throws Exception
	 */
	public static void mergeCellsData(List<String[]> rs, WritableSheet ws, int col,
			int row, WritableCellFormat format) throws Exception{
		int hbhs = 1;//合并行数
		boolean bz = false;//合并标志
		if (rs != null && !rs.isEmpty()) {
			for (int i = 0; i <= rs.size(); i++) {
				String currCellStr = "";//当前单元格字符串
				String beforeCellStr = "";//上一单元格字符串
				WritableCell currCell = ws.getWritableCell(col, row + i);
				currCellStr = currCell.getContents();
				
				if (i > 0) {
					WritableCell beforeCell = ws.getWritableCell(col, row - 1 + i);
					beforeCellStr = beforeCell.getContents();
				}
				
				if (currCellStr.equals(beforeCellStr)) {
					hbhs++;
					bz = true;
				}
				//判断是否该合并
				if ((!currCellStr.equals(beforeCellStr)) && bz) {
					String data = (rs.get(i-1) != null && rs.get(i-1).length > col) ? rs.get(i-1)[col] : "";
					ws.addCell(new Label(col, row + i - hbhs, data, format));
					ws.mergeCells(col, row + i - hbhs, col, row + i - 1);
					// 单元格合并，重置参数
					hbhs = 1;
					bz = false;
				}
			}
		}
	}
	
	/**
	 * 合并单元格
	 * @param ws Excel模版
	 * @param size 需要合并的总行数
	 * @param col 第几个单元格
	 * @param row 第几行起
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void mergeCells(WritableSheet ws,int size,int col,int row) 
									throws RowsExceededException, WriteException {
		
		int count = 1;
		boolean isHb = false;
		
		for ( int i = 0 ; i < size ; i++) {
			WritableCell currCell = ws.getWritableCell(col, row+i );//当前单元格
			WritableCell beforeCell = ws.getWritableCell(col, row+i - 1);//上一单元格
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();
			
			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}
			
			//判断是否该合并
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);
				// 单元格合并，重置参数
				count = 1;
				isHb = false;
			}
		}
	}
}
