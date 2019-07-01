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
 * @descprition ������ʽ��֤ʵ����
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
		// ��ȡӳ��
		String filePath = model.getFilePath();
		int index = model.getIndex();

		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������	
			List<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			
			// ������ʽ��֤
			Pattern p = Pattern.compile(pattern);
			
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
					
				String oneCellContent = StringUtils.Q2BChange(row[index]);//���excel��һ�е�ĳ��ѡ���е�ֵ
		
				//�Ի���ֶ�ֵ���кϷ����ж�
				if(StringUtils.isNotNull(oneCellContent) && !p.matcher(oneCellContent).matches()){
					oneRowResult = true;
					errors += (ExcelMethods.getOneCellContent(sourceSheet, index, 0)+ comment);
				}
			
				if(oneRowResult){//����д������ͽ��м�¼
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
