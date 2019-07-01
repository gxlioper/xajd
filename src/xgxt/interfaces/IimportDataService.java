package xgxt.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import common.Globals;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.DAO.DAO;
import xgxt.pjpy.shcbys.jxj.JxjService;
import xgxt.pjpy.whlgdx.PjpyWhlgDAO;
import xgxt.pjpy.whlgdx.WhlgJxjModel;
import xgxt.pjpy.zzsf.ZzsfPjpyAction;
import xgxt.sjdrdc.ImportDataDAO;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public abstract class IimportDataService {
//	ѭ����
//	������ colArr
//		   valArr
//	for(int i=0; i<�����鳤��; i++){
//		colArr[i]
//		String[] att = tableColumnAttributes.get(colArr[i]);
//		att[0] >= valArr[i].length;
//	}
	
	/**
	 * ���Execl���е����������ݿ���Ҫ�����ʽ�Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
//	public ArrayList<String[]> checkExcelCommon(String[] colArr, List<String[]> valArr, String realTable, Sheet sourceSheet){
//		ImportDataDAO dao = new ImportDataDAO();
//		
//		HashMap<String, String[]> tableColumnAttributes = dao.getTableColumnsAttributes(realTable);
//		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
//		try {
//			//��ʼ�������
//			for(int rownum=1;rownum<valArr.size();rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
//				//start ��ʼ����һ�еĸ����ֶ�
//				String[] row = valArr.get(rownum);
//				
//				String errors = "������¼�а����Ĵ���";
//				boolean oneRowResult = false;
//				for(int selnum=0;selnum<colArr.length;selnum++){//��ѡ�е��н��м��
//					String colName = colArr[selnum];
//		
//					String[] colAttrs = tableColumnAttributes.get(colName);//�������ж�Ӧ�����ݿ���е��ֶ�
//					if(colAttrs!=null){
//						//�Ի���ֶ�ֵ���кϷ����ж�
//						if(StringUtils.isNull(row[selnum]) && colAttrs[2].equals("N") && StringUtils.isNull(colAttrs[4])){ //�ǿ��ж�
//							oneRowResult = true;
//							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" �ֶ�Ϊ�գ����ݿ�Ҫ����ֶηǿգ�");
//						}
//						if(!StringUtils.isNull(oneCellContent) && (oneCellContent.getBytes().length>Integer.parseInt(colAttrs[1]))){//�Ƿ񳬹����ݿ���ֶ��趨�����ж�
//							oneRowResult = true;
//							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" �ֶ����ݳ��ȹ������ݿ�Ҫ��ĳ���Ϊ��"+colAttrs[1]+"�ַ�(�����ĳ����۰�)��");
//						}	
//					}
//					//TODO ���������жϿ��ڴ˴�����
//				}
//				if(oneRowResult){//����д������ͽ��м�¼
//					String[] errorRow = ExcelMethods.getOneRow(sourceSheet, rownum,columnNum.size());
//					errorsArray.add(StringUtils.joinStrArr(new String[]{errors,rownum+""},errorRow));
//				}				
//			}	
//		} catch (BiffException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return errorsArray;
//	}
//	
	/**
	 * ���Execl���е����������ݿ���Ҫ�����ʽ�Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelCommon(String filePath, String realTable, String mappingItems){
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}
		HashMap<String, String[]> tableColumnAttributes = dao.getTableColumnsAttributes(realTable);
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������	
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
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
					//TODO ���������жϿ��ڴ˴�����
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
	 * ���Execl���е����������ݿ���ѧ�ź������Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param String[] yzArr
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> checkExcelXh2Xm(String filePath, 
			                                   String realTable, 
			                                   String mappingItems,
			                                   String[] yzArr){
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		boolean xmFlag = false;//�Ƿ���֤������־
		boolean sfzhFlag = false;//�Ƿ���֤���֤�ű�־
		if(yzArr == null){
			yzArr = new String[]{};
		}
		for(String key : yzArr){
			if("xm".equalsIgnoreCase(key.toLowerCase())){
				xmFlag = true;
			}
			if("sfzh".equalsIgnoreCase(key.toLowerCase())){
				sfzhFlag = true;
			}
		}
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
			String tempXm = "";//��excel�л��������Ϣ
			String tempSfzh = "";//��excel�л�����֤����Ϣ
			
			int xhNum = -1;//��¼ѧ�������е���ţ���0��ʼ
			int xmNum = -1;//��¼���������е���ţ���0��ʼ
			int sfzhNum = -1;//��¼���֤�������е���ţ���0��ʼ
			for(int colNum=0;colNum<columnNum.size();colNum++){//��excel�ļ��л�ȡ�У���������Ƿ���ѧ�ź�����
				HashMap<String, String> col = columnNum.get(colNum);
				if("ѧ��".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xhNum = colNum;
				}				
				if(xmFlag && "����".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xm".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xmNum = colNum;
				}
				if(sfzhFlag && "���֤��".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "sfzh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					sfzhNum = colNum;
				}
			}
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					tempXm = "";
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if(xmFlag && "xm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ����
						tempXm = oneCellContent;
					}		
					if(sfzhFlag && "sfzh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ���֤��
						tempSfzh = oneCellContent;
					}
				}
				
				if((tempXh == null) && (xhNum > -1)){//�ֶζ�Ӧ��û��ѧ���ֶΣ�����excel����ѧ����
					tempXh = StringUtils.Q2BChange(row[xhNum]);
				}

				if(xmFlag 
						&& (tempXm == null || "".equalsIgnoreCase(tempXm)) 
						&& (xmNum > -1)){//�ֶζ�Ӧ��û�������ֶΣ�����excel����������
					tempXm = StringUtils.Q2BChange(row[xmNum]);
				}
				if(sfzhFlag 
						&& (tempSfzh == null || "".equalsIgnoreCase(tempSfzh)) 
						&& (sfzhNum > -1)){//�ֶζ�Ӧ��û�����֤���ֶΣ�����excel�������֤����
					tempSfzh = StringUtils.Q2BChange(row[sfzhNum]);
				}
				
				String realXh = dao.checkXh(tempXh);	
				
				//-------edit by qph---begin-----
				//���ѧ���Ƿ���ڣ�����ֶ�Ϊxh����ӦcommentΪ�ջ�ѧ�š�ʱ�ż��-------------
				String[] xhCn = dao.getPkNameCN(realTable, new String[] {"xh"});
				
				if(StringUtils.isNull(realXh) && ("ѧ��".equals(xhCn[0]) || StringUtils.isNull(xhCn[0]))){//ѧ�Ų�����
				
				//-------edit by qph-----end---------------------------
					if(realTable != null &&!"bjlh_xszsxxb".equalsIgnoreCase(realTable) && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "ѧ��Ϊ"+tempXh+"��ѧ��������";
						}
					}
				}else{
					if(xmFlag && xhNum>-1 && xmNum>-1){//excel���д���ѧ�ź�������
						HashMap<String, String> xmMap = new HashMap<String, String>();
						xmMap.put("xm", tempXm);
						
						String realXm = dao.checkXh2Other(tempXh, xmMap);//ѧ�ź������Ƿ�ƥ��
						if(realXm!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += StringUtils.joinStr("ѧ����������ƥ�䣬ѧ��", 
									                      tempXh, 
									                      "��Ӧ��������", 
									                      realXm, 
									                      ";");
						}
					}
					if(sfzhFlag && xhNum>-1 && sfzhNum>-1){//excel���д���ѧ�ź����֤����
						HashMap<String, String> sfzhMap = new HashMap<String, String>();
						sfzhMap.put("sfzh", tempSfzh);
						String realSfzh = dao.checkXh2Other(tempXh, sfzhMap);//ѧ�ź����֤���Ƿ�ƥ��
						if(realSfzh!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += StringUtils.joinStr("ѧ�������֤�Ų�ƥ�䣬ѧ��",
									                      tempXh,
									                      "��Ӧ�����֤����",
									                      realSfzh,
									                      ";");
						}
					}
				}		
				
				//�й����ʴ�ѧ�ڹ���ѧ��𷢷ŵ���ʱ���ѧ���ڹ���ѧ���벻ͨ����ѧ�����ڵ���ʱ������
				String xxdm = StandardOperation.getXxdm();
				if("xscjffb".equalsIgnoreCase(realTable)&&xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					String qgzxxh = dao.checkQgzx(tempXh);
					if(qgzxxh==null||qgzxxh.equals("")){//ѧ�Ų����ڣ�ѧ��û��ͨ���ڹ���ѧ��ˣ��������ڹ���ѧѧ��
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "ѧ��Ϊ"+tempXh+"��ѧ�������ڹ���ѧѧ���޷�����";
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
	 * ���Execl���е����������ݿ���ѧ���Ƿ��ظ�
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelXhRepeat(String filePath, String realTable, String mappingItems,String drms){
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}				
				}
				boolean result = dao.checkXhRepeat(tempXh,realTable);//�ж�ѧ���Ƿ��ظ�
				if((result)){//����������
					oneRowResult = true;
					errors += "ѧ��Ϊ��"+tempXh+"��ѧ���Ѿ�����";
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
	 * ���Execl���е����������ݿ���ѧ���Ƿ��ظ�
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkConditionOfPrise(String filePath, String realTable, String mappingItems,String userType){
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
			String tempSh = "";//��excel�л�������Ϣ			 
			String xmdm = "";//��excel�л����Ŀ���� 
			String xn = "";
			String nd = "";
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ѧ�����
						xmdm = oneCellContent;
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//�����ƺŴ��� 
						xmdm = oneCellContent;
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//ѧ��
						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//��� 
						nd = oneCellContent;
					}
					if(userType!=null && userType.equalsIgnoreCase("fdy")){
						if("fdysh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ����Ա���
							tempSh = oneCellContent;
						}
					}else if(userType!=null && userType.equalsIgnoreCase("xy")){
						if("xysh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ����Ա���
							tempSh = oneCellContent;
						}
					}else{
						if("xxsh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ����Ա���
							tempSh = oneCellContent;
						}
					}
				}
				PjpyWhlgDAO whlgDao = new PjpyWhlgDAO();
				if(tempSh!=null && tempSh.equalsIgnoreCase("ͨ��")){					
					if(realTable!=null && realTable.equalsIgnoreCase("xsjxjb")){//��ѧ��
						realTable = "jxjdmb";
						WhlgJxjModel model = new WhlgJxjModel();
						model.setXn(xn);
						model.setNd(nd);
						model.setXh(tempXh);
						model.setUserType(userType);
						model.setJxjdm(xmdm);	
						int tgrs = whlgDao.checkPersonNumber(model);
						if(tgrs<0){
							oneRowResult = true;
							errors = "���ͨ�������Ѵﵽ�򳬹���������!����������" + String.valueOf(tgrs).substring(1, String.valueOf(tgrs).length());
						}
					}else{
						realTable =  "rychdmb";
					}
					String mes = whlgDao.checkDemand(tempXh, xmdm, realTable);
					if(mes!=null && !mes.equalsIgnoreCase(" ") && !mes.equalsIgnoreCase("")){//ѧ���Ƿ��������
						oneRowResult = true;
						errors = mes + "����������,�������ͨ��";
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
	 * �㽭���罱ѧ�������ƺ����ݵ�����
	 * @param filePath
	 * @param realTable
	 * @param mappingItems
	 * @param userType
	 * @return
	 */
	public ArrayList<String[]> zjjdCheckConditionOfPrise(String filePath,
			String realTable, String mappingItems, String userType) throws Exception {
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
//			String xq = "";//��excel�л��ѧ��			 
			String xmdm = "";//��excel�л����Ŀ����
			String xmlx = "";//��Ŀ���ͽ�ѧ�������ƺ�
//			String xn = "";
//			String nd = "";
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ѧ�����
						xmdm = oneCellContent;
						xmlx = "jxj";
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//�����ƺŴ��� 
						xmdm = oneCellContent;
						xmlx = "rych";
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//ѧ��
//						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//��� 
//						nd = oneCellContent;
					}
					if ("xq".equalsIgnoreCase(splitMappingOne[selnum][1])) {//ѧ��
//						xq = oneCellContent;
					}	
				}
				boolean tjFlag = ApplyAction.pdStuTjFlag(tempXh, xmdm, xmlx);//��ѧ��������Ϣ�����������
				if (!tjFlag) {
					oneRowResult = true;
					errors += "���������ϴ˽��������������";
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
	 * ���ս�����ѧ�������ƺ����ݵ�����
	 * @param filePath
	 * @param realTable
	 * @param mappingItems
	 * @param userType
	 * @return
	 */
	public ArrayList<String[]> ahjgCheckConditionOfPrise(String filePath,
			String realTable, String mappingItems, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String[] jxjsqxnnd = dao.getOneRs(
				"select jxjsqxn,jxjsqnd,jxjsqxq from xtszb", new String[] {},
				new String[] { "jxjsqxn", "jxjsqnd", "jxjsqxq" });//��ȡ��ѧ������ѧ�꣬��ȣ�ѧ��
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
//			String xq = "";//��excel�л��ѧ��			 
			String xmdm = "";//��excel�л����Ŀ����
			String xmlx = "";//��Ŀ���ͽ�ѧ�������ƺ�
//			String xn = "";
//			String nd = "";
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ѧ�����
						xmdm = oneCellContent;
						xmlx = "jxj";
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//�����ƺŴ��� 
						xmdm = oneCellContent;
						xmlx = "rych";
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//ѧ��
//						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//��� 
//						nd = oneCellContent;
					}
					if ("xq".equalsIgnoreCase(splitMappingOne[selnum][1])) {//ѧ��
//						xq = oneCellContent;
					}	
				}
				boolean tjFlag =ZzsfPjpyAction.chkJxjsqFlags(tempXh, xmdm, xmlx, jxjsqxnnd);//��ѧ��������Ϣ�����������
				
				if (!tjFlag) {
					oneRowResult = true;
					errors += "���������ϴ˽��������������";
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
	
	public ArrayList<String[]> shcbysgdzkCheckOfPrise(String filePath,String realTable, String mappingItems, String userType) throws Exception {
		JxjService service = new JxjService();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
			String xmdm = "";//��excel�л����Ŀ����
			String jd = "";//��excel�л�ü���
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ѧ�����
						xmdm = oneCellContent;
					}					
					if("jd".equalsIgnoreCase(splitMappingOne[selnum][1])){//����
						jd = oneCellContent;
					}
				}
				String tjFlag = service.chkJxjsqtj(tempXh, jd, xmdm);//�������
					
				if (tjFlag != null && "wcj".equalsIgnoreCase(tjFlag)) {//�޳ɼ�
					oneRowResult = true;
					errors += "����û�гɼ���Ϣ!";
				}
				if (tjFlag != null && "cjbhg".equalsIgnoreCase(tjFlag)) {//�ɼ����ϸ�
					oneRowResult = true;
					errors += "�������ڲ�����ɼ���Ϣ��";
				}
				if (tjFlag != null && "kqbhg".equalsIgnoreCase(tjFlag)) {//���ڲ��ϸ�
					oneRowResult = true;
					errors += "�������ڲ��ϸ�";
				}
				if (tjFlag != null && "cjhkbhg".equalsIgnoreCase(tjFlag)) {//���ڻ����ɼ�
					oneRowResult = true;
					errors += "�����пγ̻�����";
				}
				if (tjFlag != null && "jdbhg".equalsIgnoreCase(tjFlag)) {//���㲻�ϸ�
					oneRowResult = true;
					errors += "�������㲻����������׼��";
				}
				if(tjFlag != null && "".equalsIgnoreCase(tjFlag)){//��������
					oneRowResult = false;
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
	 * ���Execl���е����������ݿ���ѧ�ź������Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelXh2Xm(String filePath, String realTable, String mappingItems){
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//��excel�л��ѧ����Ϣ
			String tempXm = "";//��excel�л��������Ϣ
			
			int xhNum = -1;//��¼ѧ�������е���ţ���0��ʼ
			int xmNum = -1;//��¼���������е���ţ���0��ʼ
			for(int colNum=0;colNum<columnNum.size();colNum++){//��excel�ļ��л�ȡ�У���������Ƿ���ѧ�ź�����
				HashMap<String, String> col = columnNum.get(colNum);
				if("ѧ��".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xhNum = colNum;
				}
				if("����".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xm".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xmNum = colNum;
				}
			}
			//��ʼ�������
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "������¼�а����Ĵ���";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					tempXm = "";
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡѧ��
						tempXh = oneCellContent;
					}
					if("xm".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ����
						tempXm = oneCellContent;
					}					
				}
				
				if((tempXh == null) && (xhNum > -1)){//�ֶζ�Ӧ��û��ѧ���ֶΣ�����excel����ѧ����
					tempXh = StringUtils.Q2BChange(row[xhNum]);
				}

				if((tempXm == null || "".equalsIgnoreCase(tempXm)) && (xmNum > -1)){//�ֶζ�Ӧ��û�������ֶΣ�����excel����������
					tempXm = StringUtils.Q2BChange(row[xmNum]);
				}
				
				String realXh = dao.checkXh(tempXh);				
				if(realXh==null||realXh.equals("")){//ѧ�Ų�����
					if(realTable != null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "ѧ��Ϊ"+tempXh+"��ѧ��������";
						}
					}
				}else{
					if(xhNum>-1 && xmNum>-1){//excel���д���ѧ�ź�������
						String realXm = dao.checkXh2Xm(tempXh, tempXm);//ѧ�ź������Ƿ�ƥ��
						if(realXm!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += "ѧ����������ƥ�䣬ѧ��"+tempXh+"��Ӧ��������"+realXm;
						}
					}
				}		
				
				//�й����ʴ�ѧ�ڹ���ѧ��𷢷ŵ���ʱ���ѧ���ڹ���ѧ���벻ͨ����ѧ�����ڵ���ʱ������
				String xxdm = StandardOperation.getXxdm();
				if("xscjffb".equalsIgnoreCase(realTable)&&xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					String qgzxxh = dao.checkQgzx(tempXh);
					if(qgzxxh==null||qgzxxh.equals("")){//ѧ�Ų����ڣ�ѧ��û��ͨ���ڹ���ѧ��ˣ��������ڹ���ѧѧ��
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "ѧ��Ϊ"+tempXh+"��ѧ�������ڹ���ѧѧ���޷�����";
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
	 * ���Execl���е����ݵ��������Ƿ������ݿ��д���
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelSsbhExists(String filePath, String realTable, String mappingItems,String drms){
		DAO dao = DAO.getInstance();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempSsbh = "";//��excel�л����������Ϣ
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("ssbh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡ������
						tempSsbh = oneCellContent;
					}				
				}
				String pk = dao.getOneValue("view_ssxx", "ssbh", "ssbh",
						tempSsbh);
				if (Base.isNull(pk)) {
					oneRowResult = true;
					errors += "������Ϊ��" + tempSsbh + "�����Ҳ����ڴ���";
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
	
	public ArrayList<String[]> checkExcelZghExists(String filePath, String realTable, String mappingItems){
		DAO dao = DAO.getInstance();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempZgh = "";//��excel�л��ְ������Ϣ
			//��ʼ������� 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//��excel���ݵĵ�һ�п�ʼ(ȥ��������)����ÿ�����ݽ��м��
				
				//start ��ʼ����һ�еĸ����ֶ�
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//��ѡ�е��н��м��
					//��ȫ��ת���ɰ��
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//���excel��һ�е�ĳ��ѡ���е�ֵ
					
					if("zgh".equalsIgnoreCase(splitMappingOne[selnum][1])){//��ȡְ����
						tempZgh = oneCellContent;
					}				
				}
				String pk = dao.getOneValue("fdyxxb", "zgh", "zgh",
						tempZgh);
				if (Base.isNull(pk)) {
					oneRowResult = true;
					errors += "ְ����Ϊ'" + tempZgh + "'�ĸ���Ա������";
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
}
