package xgxt.sjdrdc.check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;

import xgxt.DAO.DAO;
import xgxt.sjdrdc.ExportAndImportModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * �����֮�������֤
 * @author sjf
 */
public class RelationCheck implements IimportCheck{
	private String relationTable;
	private String relationField;
	private String relationTableComment; 
	
	public RelationCheck(String relationTable, String relationField, String relationTableComment){
		this.relationTable = relationTable;
		this.relationField = relationField;
		this.relationTableComment = relationTableComment;
	}
	
	public List<String[]> checkExcelData(ExportAndImportModel model) {
		String filePath = model.getFilePath();
		int index = model.getIndex();
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();

		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������	
			List<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			String[] keys = new String[sourceRowCount-1];
			
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				keys[rownum-1] = StringUtils.Q2BChange(row[index]);//���excel��һ�е�ĳ��ѡ���е�ֵ
			}
			
			/*
			 * ��ȡ�����Ƿ���ڵ�ֵ
			 */
			boolean[] rs = dao.checkExists(relationTable, relationField, keys);
			
			for(int i=0; i<rs.length; i++){
				boolean oneRowResult = false;
				String errors = "������¼�а����Ĵ���";
				
				if(!rs[i]){
					oneRowResult = true;
					errors += (ExcelMethods.getOneCellContent(sourceSheet, index, 0)  + relationTableComment);
				}
				
				if(oneRowResult){
					errorsArray.add(StringUtils.joinStrArr(new String[]{errors,String.valueOf(i+1)},
															ExcelMethods.getOneRow(sourceSheet,i+1,columnNum.size())));
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
