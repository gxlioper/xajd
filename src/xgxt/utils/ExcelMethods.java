package xgxt.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.WritableFont.FontName;
import jxl.write.biff.RowsExceededException;

public class ExcelMethods {
	/**
	 * 根据模版定义的格式，输出相应的excel文件
	 * @param inFilePath 已有的excel模版
	 * @param outFilePath 要生成的最终结果文件
	 * @return 可以编辑的excel工作薄
	 */
	public static WritableWorkbook getWritableWorkbook(File inFilePath,File outFilePath){
		WritableWorkbook wwb = null;
		try{
			Workbook tempWorkbook = Workbook.getWorkbook(inFilePath);
			wwb = Workbook.createWorkbook(outFilePath, tempWorkbook);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wwb;
	}
	
	/**
	 * 根据模版定义的格式，输出相应的excel文件
	 * @param inFilePath 已有的excel模版
	 * @param os 生成outputstream格式的excel输出流
	 * @return
	 */
	public static WritableWorkbook getWritableWorkbook(File inFilePath,OutputStream os){
		WritableWorkbook wwb = null;
		try{
			Workbook tempWorkbook = Workbook.getWorkbook(inFilePath);
			wwb = Workbook.createWorkbook(os, tempWorkbook);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wwb;
	}
	/**
	 * 每次操作完excel后，要进行提交才能把内容写入相应的文件
	 * @param wwb
	 * @throws IOException
	 * @throws WriteException
	 */
	public static void submitWritableWorkbookOperations(WritableWorkbook wwb) {
		try{
		 wwb.write();
		 wwb.close();
		}catch(Exception e){
			
		}
	}
	

	/**
	 * 生成指定excel文件的列
	 * 
	 * @param filePath
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getExcelColumns(
			String filePath) {
		ArrayList<HashMap<String, String>> cols = new ArrayList<HashMap<String, String>>();
		try {
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = wb.getSheet(0);
			Cell[] colRow = sheet.getRow(0);
			for (int cellnum = 0; cellnum < colRow.length; cellnum++) {
				HashMap<String, String> cellCol = new HashMap<String, String>();
				cellCol.put("col", colRow[cellnum].getContents());
				cols.add(cellCol);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cols;
	}
	
	/**
	 * 获得指定的excel表单的一行数据
	 * @param sheet
	 * @return
	 */
	public static String[] getOneRow(Sheet sheet,int rownum,int rowLength){
		Cell[] row = sheet.getRow(rownum);		
		String[] result = new String[Math.max(rowLength, row.length)];
		int length = Math.min(rowLength, row.length);
		for(int rnum=0;rnum<length;rnum++){
			result[rnum] = row[rnum].getContents();
		}
		for(int rowNum=0; rowNum<result.length;rowNum++){
			if(result[rowNum]==null){
				result[rowNum] = "";
			}
		}
		return result;
	}
	
	/**
	 * 返回某个cell的值
	 * @param sheet
	 * @param col
	 * @param row
	 * @return
	 */
	public static String getOneCellContent(Sheet sheet,int col,int row){
		return (sheet.getCell(col, row).getContents());
	} 
	
	/**
	 * 拷贝一行
	 * @param dest   目标sheet
	 * @param sour   原sheet
	 * @param row    第几行 从0开始
	 */
	public static void copyARow(WritableSheet dest,Sheet sour,int row){
		Cell[] aRow = sour.getRow(row);
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			for(int i=0;i<aRow.length;i++){
				dest.addCell(new Label(i,rcount,aRow[i].getContents()));
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拷贝一行
	 * @param dest   目标sheet
	 * @param sour   原sheet
	 * @param row    第几行 从0开始
	 * @param startIndex 从第几列开始 大于0
	 */
	public static void copyARow(WritableSheet dest,Sheet sour,int row,int startIndex){
		Cell[] aRow = sour.getRow(row);
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			dest.addCell(new Label(0,rcount,"序号"));
			for(int i=startIndex;i<aRow.length;i++){
				dest.addCell(new Label(i+1,rcount,aRow[i].getContents()));
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拷贝一行
	 * @param dest   目标sheet
	 * @param sour   原sheet
	 * @param row    第几行 从0开始
	 * @param startIndex 从第几列开始 大于0
	 */
	public static void copyErrorRow(WritableSheet dest,Sheet sour,int row,int startIndex){
		Cell[] aRow = sour.getRow(row);
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			dest.addCell(new Label(0,rcount,"错误信息"));
			dest.addCell(new Label(1,rcount,"序号"));
			for(int i=startIndex;i<aRow.length;i++){
				dest.addCell(new Label(i+2,rcount,aRow[i].getContents()));
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新创建一个excel文档，把数据流插入到outputstream
	 * @param os
	 * @return
	 */
	public static WritableWorkbook getWritableWorkbook(OutputStream os){
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wwb; 
	}
	
	/**
	 * 往一个可写的sheet中的指定位置写入内容
	 * @param contents
	 * @param sheet
	 * @param col
	 * @param row
	 */
	public static void writeACell(String contents,WritableSheet sheet,int col,int row){
		try {
			sheet.addCell(new Label(col,row,contents));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean write2Sheet(String path,OutputStream os,String sheetTitle,List<String[]> contents){
		boolean result = false ;
		WritableWorkbook destwwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet destSheet = destwwb.createSheet(sheetTitle, 0);
		Sheet sourceSheet;
		try {
			sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			ExcelMethods.copyErrorRow(destSheet, sourceSheet, 0,0);//获取表单的列头
			int rownum = 1;
			for(String[] oneRow : contents){
				for(int colnum=0;colnum<oneRow.length;colnum++){
					writeACell(oneRow[colnum], destSheet, colnum, rownum);
				}
				rownum++;
			}
			submitWritableWorkbookOperations(destwwb);
			result = true;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean write2SheetImport(String path,OutputStream os,String sheetTitle, List<String[]> contents){
		boolean result = false ;
		WritableWorkbook destwwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet destSheet = destwwb.createSheet(sheetTitle, 0);
		Sheet sourceSheet;
		try {
			sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			ExcelMethods.copyErrorRow(destSheet, sourceSheet, 0,0);//获取表单的列头
			int rownum = 1;
			for(String[] oneRow : contents){
				for(int colnum=0;colnum<oneRow.length;colnum++){
					writeACell(oneRow[colnum], destSheet, colnum, rownum);
				}
				rownum++;
			}
			submitWritableWorkbookOperations(destwwb);
			result = true;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 
	 * @param wf 字体格式对象
	 * @param fontSize 字体大小
	 * @param xStyle 单元格内容横向显示
	 * @param yStyle 单元格内容纵向显示
	 * @param newLine 是否自动换行
	 * @param borderShow 边框线是否显示
	 * @return 单元格格式对象
	 * @throws Exception 
	 */
	public static WritableCellFormat getWcf(FontName name,int fontSize,boolean bold,Alignment xStyle,VerticalAlignment yStyle,boolean newLine,Border bordery) throws Exception{
		WritableFont wf = new WritableFont(name); // 构造字体格式
		WritableCellFormat wcf = new WritableCellFormat();		
		wf.setPointSize(fontSize);
		wcf.setFont(wf);
		wcf.setAlignment(xStyle); //指定格式设置字符左右显示位置(如：左右居中)
		wcf.setVerticalAlignment(yStyle); //指定格式设置字符上下显示位置（如：上下居中）				
		wcf.setWrap(newLine);
		if(bordery!=Border.NONE){
			wcf.setBorder(bordery, BorderLineStyle.THIN); //设置单元格外边线						
		}
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD); //设置粗体
		}
		return wcf;
	}
	
	public static WritableCellFormat getWcf(WritableCellFormat wcf,FontName name,int fontSize,boolean bold,Alignment xStyle,VerticalAlignment yStyle,boolean newLine,Border bordery) throws Exception{
		WritableFont wf = new WritableFont(name); // 构造字体格式
		wf.setPointSize(fontSize);
		wcf.setFont(wf);
		wcf.setAlignment(xStyle); //指定格式设置字符左右显示位置(如：左右居中)
		wcf.setVerticalAlignment(yStyle); //指定格式设置字符上下显示位置（如：上下居中）				
		wcf.setWrap(newLine);
		if(bordery!=Border.NONE){
			wcf.setBorder(bordery, BorderLineStyle.THIN); //设置单元格外边线						
		}
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD); //设置粗体
		}
		return wcf;
	}
	
	/**
	 * 获取一个EXCEL工作表
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static WritableWorkbook getWritableWorkbook(
			HttpServletResponse response) throws Exception {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		return Workbook.createWorkbook(response.getOutputStream());
	}
}


