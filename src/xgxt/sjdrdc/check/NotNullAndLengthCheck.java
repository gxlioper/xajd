package xgxt.sjdrdc.check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;
import xgxt.sjdrdc.ExportAndImportModel;
import xgxt.sjdrdc.ImportDataDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * 非空和长度验证类
 */
public class NotNullAndLengthCheck implements IimportCheck {
	
	public List<String[]> checkExcelData(ExportAndImportModel model) {
		// 获取映射
		String[][] splitMappingOne = model.getSplitMapping();
		String filePath = model.getFilePath();
		String realTable = model.getRealTable();

		ImportDataDAO dao = new ImportDataDAO();

		HashMap<String, String[]> tableColumnAttributes = dao.getTableColumnsAttributes(realTable);
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数	
			List<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			//开始检测数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				// 如果是空行就跳出检测
				if(checkNoValue(row)){
					continue;
				}
				
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					int m = Integer.parseInt(splitMappingOne[selnum][0]);
					String oneCellContent = StringUtils.Q2BChange(row[m]);//获得excel中一行的某个选中列的值
									
					String[] colAttrs = tableColumnAttributes.get(splitMappingOne[selnum][1]);//获得这个列对应的数据库表中的字段
					if(colAttrs!=null){
						//对获得字段值进行合法性判断
						if(StringUtils.isNull(oneCellContent) && colAttrs[2].equals("N") && StringUtils.isNull(colAttrs[4])){ //非空判断
							oneRowResult = true;
							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" 字段为空，数据库要求此字段非空");
						}
						if(!StringUtils.isNull(oneCellContent) && (oneCellContent.getBytes().length>Integer.parseInt(colAttrs[1]))){//是否超过数据库表字段设定长度判断
							oneRowResult = true;
							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" 字段数据长度过大，数据库要求的长度为："+colAttrs[1]+"字符(1个汉字等于2个字符)");
						}	
					}
				}
				if(oneRowResult){//如果有错误发生就进行记录
					String[] errorRow = ExcelMethods.getOneRow(sourceSheet, rownum,columnNum.size());
					errorsArray.add(StringUtils.joinStrArr(new String[]{errors,rownum+""},errorRow));
				}				
			}	
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return errorsArray;
	}
	
	/**
	 * 检测数据是否是空行
	 * @return
	 */
	public static boolean checkNoValue(String[] row){
		boolean isNull = true;
		for(String rowValue : row){
			if(StringUtils.isNotNull(rowValue.trim())){
				isNull = false;
				break;
			}
		}
		
		return isNull;
	}
}
