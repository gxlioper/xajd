/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����11:16:42 
 */  
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
 * �ǿպͳ�����֤��
 */
public class QgzxFfjeCheck implements IimportCheck {
	
	public List<String[]> checkExcelData(ExportAndImportModel model) {
		// ��ȡӳ��
		String[][] splitMappingOne = model.getSplitMapping();
		String filePath = model.getFilePath();
		String realTable = model.getRealTable();

		ImportDataDAO dao = new ImportDataDAO();

		HashMap<String, String[]> tableColumnAttributes = dao.getTableColumnsAttributes(realTable);
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������	
			List<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				// ����ǿ��о��������
				if(checkNoValue(row)){
					continue;
				}
				
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					int m = Integer.parseInt(splitMappingOne[selnum][0]);
					String oneCellContent = StringUtils.Q2BChange(row[m]);//���excel��һ�е�ĳ��ѡ���е�ֵ
									
					String[] colAttrs = tableColumnAttributes.get(splitMappingOne[selnum][1]);//�������ж�Ӧ�����ݿ���е��ֶ�
					if(colAttrs!=null){
						//�Ի���ֶ�ֵ���кϷ����ж�
						if(StringUtils.isNull(oneCellContent) && colAttrs[2].equals("N") && StringUtils.isNull(colAttrs[4])){ //�ǿ��ж�
							oneRowResult = true;
							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" �ֶ�Ϊ�գ����ݿ�Ҫ����ֶηǿ�");
						}
						if(!StringUtils.isNull(oneCellContent) && (oneCellContent.getBytes().length>Integer.parseInt(colAttrs[1]))){//�Ƿ񳬹����ݿ���ֶ��趨�����ж�
							oneRowResult = true;
							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" �ֶ����ݳ��ȹ������ݿ�Ҫ��ĳ���Ϊ��"+colAttrs[1]+"�ַ�(1�����ֵ���2���ַ�)");
						}	
					}
				}
				if(oneRowResult){//����д������ͽ��м�¼
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
	 * ��������Ƿ��ǿ���
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
