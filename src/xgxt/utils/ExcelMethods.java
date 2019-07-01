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
	 * ����ģ�涨��ĸ�ʽ�������Ӧ��excel�ļ�
	 * @param inFilePath ���е�excelģ��
	 * @param outFilePath Ҫ���ɵ����ս���ļ�
	 * @return ���Ա༭��excel������
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
	 * ����ģ�涨��ĸ�ʽ�������Ӧ��excel�ļ�
	 * @param inFilePath ���е�excelģ��
	 * @param os ����outputstream��ʽ��excel�����
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
	 * ÿ�β�����excel��Ҫ�����ύ���ܰ�����д����Ӧ���ļ�
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
	 * ����ָ��excel�ļ�����
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
	 * ���ָ����excel����һ������
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
	 * ����ĳ��cell��ֵ
	 * @param sheet
	 * @param col
	 * @param row
	 * @return
	 */
	public static String getOneCellContent(Sheet sheet,int col,int row){
		return (sheet.getCell(col, row).getContents());
	} 
	
	/**
	 * ����һ��
	 * @param dest   Ŀ��sheet
	 * @param sour   ԭsheet
	 * @param row    �ڼ��� ��0��ʼ
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
	 * ����һ��
	 * @param dest   Ŀ��sheet
	 * @param sour   ԭsheet
	 * @param row    �ڼ��� ��0��ʼ
	 * @param startIndex �ӵڼ��п�ʼ ����0
	 */
	public static void copyARow(WritableSheet dest,Sheet sour,int row,int startIndex){
		Cell[] aRow = sour.getRow(row);
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			dest.addCell(new Label(0,rcount,"���"));
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
	 * ����һ��
	 * @param dest   Ŀ��sheet
	 * @param sour   ԭsheet
	 * @param row    �ڼ��� ��0��ʼ
	 * @param startIndex �ӵڼ��п�ʼ ����0
	 */
	public static void copyErrorRow(WritableSheet dest,Sheet sour,int row,int startIndex){
		Cell[] aRow = sour.getRow(row);
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			dest.addCell(new Label(0,rcount,"������Ϣ"));
			dest.addCell(new Label(1,rcount,"���"));
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
	 * �´���һ��excel�ĵ��������������뵽outputstream
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
	 * ��һ����д��sheet�е�ָ��λ��д������
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
			ExcelMethods.copyErrorRow(destSheet, sourceSheet, 0,0);//��ȡ������ͷ
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
			ExcelMethods.copyErrorRow(destSheet, sourceSheet, 0,0);//��ȡ������ͷ
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
	 * @param wf �����ʽ����
	 * @param fontSize �����С
	 * @param xStyle ��Ԫ�����ݺ�����ʾ
	 * @param yStyle ��Ԫ������������ʾ
	 * @param newLine �Ƿ��Զ�����
	 * @param borderShow �߿����Ƿ���ʾ
	 * @return ��Ԫ���ʽ����
	 * @throws Exception 
	 */
	public static WritableCellFormat getWcf(FontName name,int fontSize,boolean bold,Alignment xStyle,VerticalAlignment yStyle,boolean newLine,Border bordery) throws Exception{
		WritableFont wf = new WritableFont(name); // ���������ʽ
		WritableCellFormat wcf = new WritableCellFormat();		
		wf.setPointSize(fontSize);
		wcf.setFont(wf);
		wcf.setAlignment(xStyle); //ָ����ʽ�����ַ�������ʾλ��(�磺���Ҿ���)
		wcf.setVerticalAlignment(yStyle); //ָ����ʽ�����ַ�������ʾλ�ã��磺���¾��У�				
		wcf.setWrap(newLine);
		if(bordery!=Border.NONE){
			wcf.setBorder(bordery, BorderLineStyle.THIN); //���õ�Ԫ�������						
		}
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD); //���ô���
		}
		return wcf;
	}
	
	public static WritableCellFormat getWcf(WritableCellFormat wcf,FontName name,int fontSize,boolean bold,Alignment xStyle,VerticalAlignment yStyle,boolean newLine,Border bordery) throws Exception{
		WritableFont wf = new WritableFont(name); // ���������ʽ
		wf.setPointSize(fontSize);
		wcf.setFont(wf);
		wcf.setAlignment(xStyle); //ָ����ʽ�����ַ�������ʾλ��(�磺���Ҿ���)
		wcf.setVerticalAlignment(yStyle); //ָ����ʽ�����ַ�������ʾλ�ã��磺���¾��У�				
		wcf.setWrap(newLine);
		if(bordery!=Border.NONE){
			wcf.setBorder(bordery, BorderLineStyle.THIN); //���õ�Ԫ�������						
		}
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD); //���ô���
		}
		return wcf;
	}
	
	/**
	 * ��ȡһ��EXCEL������
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


