package xgxt.utils;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		String filePath = "D:\\Program Files\\Apache Software Foundation\\Tomcat 5.5\\webapps\\xgxt\\upload\\zf0120070917113353.xls";
		System.out.println(ExcelUtils.getErrorExcel(filePath));
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 将EXCEL表单中的一行 拷贝 到另外一个EXCEL中
	 * @param ws
	 * @param descRow
	 * @param sheet
	 * @param srcRow
	 */
	public static void rowCopy(WritableSheet ws, int descRow,  Sheet sheet, int srcRow) {
		for (int col=0; col<sheet.getColumns(); col++) {
			try {
				ws.addCell(new Label(col, descRow, sheet.getCell(col, srcRow).getContents()));
			} catch (RowsExceededException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 这个函数的功能是将EXCEL中的行转化为数组
	 * @param sheet
	 * @param srcRow
	 * @return
	 */
	public static String[] rowToArray(Sheet sheet, int srcRow) {
		String[] result = new String[sheet.getColumns()];
		for (int col=0; col<sheet.getColumns(); col++) {
			result[col] = sheet.getCell(col, srcRow).getContents();
		}
		return result;
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 返回导入数据时，将错误数据要保存到的EXCEL文件的路径名
	 * @param filePath
	 * @return
	 */
	public static String getErrorExcel(String filePath) {
		String path = filePath.substring(0,filePath.lastIndexOf(".xls"))+"_error.xls";
		return path;
	}
}
