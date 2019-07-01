package xgxt.sjdrdc.check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;

import xgxt.sjdrdc.ExportAndImportModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * @descprition 正则表达式验证实体类
 * @author sjf
 * @copyRight 1.0
 */
public class PatternCheck implements IimportCheck {
	private String pattern = "";
	private String comment = "";
	
	public PatternCheck(String pattern, String comment){
		this.pattern = pattern;
		this.comment = comment;
	}
	
	public List<String[]> checkExcelData(ExportAndImportModel model) {
		// 获取映射
		String filePath = model.getFilePath();
		int index = model.getIndex();

		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数	
			List<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			
			// 正则表达式验证
			Pattern p = Pattern.compile(pattern);
			
			//开始检测数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
					
				String oneCellContent = StringUtils.Q2BChange(row[index]);//获得excel中一行的某个选中列的值
		
				//对获得字段值进行合法性判断
				if(StringUtils.isNotNull(oneCellContent) && !p.matcher(oneCellContent).matches()){
					oneRowResult = true;
					errors += (ExcelMethods.getOneCellContent(sourceSheet, index, 0)+ comment);
				}
			
				if(oneRowResult){//如果有错误发生就进行记录
					errorsArray.add(StringUtils.joinStrArr(new String[]{errors,rownum+""},row));
				}				
			}	
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return errorsArray;
	}

}
