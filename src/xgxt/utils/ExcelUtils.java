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
		// TODO �Զ����ɷ������
		String filePath = "D:\\Program Files\\Apache Software Foundation\\Tomcat 5.5\\webapps\\xgxt\\upload\\zf0120070917113353.xls";
		System.out.println(ExcelUtils.getErrorExcel(filePath));
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe ��EXCEL���е�һ�� ���� ������һ��EXCEL��
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
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe ��������Ĺ����ǽ�EXCEL�е���ת��Ϊ����
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
	 * @describe ���ص�������ʱ������������Ҫ���浽��EXCEL�ļ���·����
	 * @param filePath
	 * @return
	 */
	public static String getErrorExcel(String filePath) {
		String path = filePath.substring(0,filePath.lastIndexOf(".xls"))+"_error.xls";
		return path;
	}
}
