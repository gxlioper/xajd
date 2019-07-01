package xgxt.sjdrdc;

import java.io.File;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import jxl.Sheet;
import jxl.Workbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.Dom4jForXmlHandle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.interfaces.IimportDataService;
import xgxt.sjdrdc.check.IimportCheck;
import xgxt.sjdrdc.check.NotNullAndLengthCheck;
import xgxt.utils.Arrays2;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.newp.ArrayUtil;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݵ���Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class ImportDataService extends IimportDataService {
	/**
	 * ��ȡҪ��������ݿ�����
	 * @param tabName
	 * @return List
	 * @throws SQLException 
	 * */
	public List getTableColumnList(String tabName){
		List list = new ArrayList();
		String xxdm = StandardOperation.getXxdm();
		String[] column = null;
		ImportDataDAO dao = new ImportDataDAO();
		//TODO ��ͬѧУ��ͬһ����ȡ��ͬ���ֶ�ʱ��drb�в������ڴ�ѧУ���ֶ�
		try {
			column = dao.getDiffentColumn(tabName,xxdm);//��ȡ��ͬѧУ��ͬһ��ȡ���ֶ���Ϣ
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}		
		
		//��������ֶ�����BZ����ֶ�,���BZ�ŵ����
		try {
			column = ArrayUtil.changeBzAfter(column);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		list = dao.getTableColumnList(tabName,column);//��ȡ�������ֶ���Ϣ
		return list;
	}
	
	/**
	 * ��ȡ��������ֶ�
	 * @param tableName
	 * @return String[]
	 * */
	public String[] getTablePk(String tableName){
		ImportDataDAO dao = new ImportDataDAO();
		String[] values = null;
		try {
			values = dao.getPkOfTable(tableName);
			values = dao.getPkNameCN(tableName,values);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return values;
	}
	
	/**
	 * ����excel�ļ�����
	 * @param filePath
	 * @return ArrayList<HashMap<String, String>>
	 */
	public ArrayList<HashMap<String, String>> getExcelCols(String filePath){
		return ExcelMethods.getExcelColumns(filePath);
	}
	
	/**
	 * ���Execl���е����������ݿ���Ҫ�����ʽ�Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean
	 * @aurthor sjf 
	 **/
	public List<String[]> checkExcelDataComm(ExportAndImportModel model) throws Exception {				
		// ���ص����д�����Ϣ 
		List<String[]> errorList = new ArrayList<String[]>();
		
		// ����ǿռ����
		IimportCheck  notNullCheck = new NotNullAndLengthCheck();
		
		// ���Ƚ��������еķǿռ��ͳ�����֤
		errorList =notNullCheck.checkExcelData(model);
		
		// ��ʼ���������Map,�����Զ�����,�ڸ÷���������Լ��ļ����ʵ��IimportCheck�Ľӿ�
		Map<String,IimportCheck> jclxMap = initializeJclx();
		
		// ��ȡ���б������ú���Ҫ�����ֶΣ�keyΪ�����ֶΣ�valueΪ��Ҫ�������list
		Map<String, List<String>> jczd = getJczd(model.getRealTable());
		
		// excel����ֶε�һһӳ��
		String[][] splitMappingOne = model.getSplitMapping();
		
		// ���������ֶΣ���ȡ�����ֶεļ�����ͣ��������м�����ͻ�ȡʵ�����࣬���м�ⷽ�����Ѵ������ݷ���errorList��
		loop:
		for(int i=0; i<splitMappingOne.length; i++){
			// ��Ӧ��excel�к�
			String index = splitMappingOne[i][0];
			model.setIndex(Integer.parseInt(index));
			
			// �������ֶ�
			String zd = splitMappingOne[i][1].toLowerCase();
			// �����ֶ���Ҫ���ļ������
			List<String> jclxList = jczd.get(zd);
			
			if(jclxList != null && jclxList.size()>0){
				for(String jclx : jclxList){
					IimportCheck checkObj = jclxMap.get(jclx);
					if(checkObj != null){
						errorList.addAll(checkObj.checkExcelData(model));
						if(errorList != null && errorList.size()>0){
							break loop;
						}
					}
				}
			}
		}
		
		return errorList;//���ؾ�����������������
	}
	
	/**
	 * ��ʼ���������map,��importData.xml������
	 * @return
	 * @author sjf
	 */
	@SuppressWarnings("unchecked")
	private Map<String,IimportCheck> initializeJclx(){
		// ͨ��dom4j��ȡxml�ĵ�����
		Document doc = Dom4jForXmlHandle.parserXml("importData.xml");
		
		// ��ż������
		Map<String,IimportCheck> jclxMap = new HashMap<String, IimportCheck>();
		
		// ��ȡ���м������
		List<Element> checkList = doc.selectNodes("//checks/check");
	
		for(int i=0; i<checkList.size(); i++){
			Element check = checkList.get(i);
			String type = check.valueOf("@type");
			String className = check.valueOf("@class");
			try{
				if("relationCheck".equalsIgnoreCase(type)){    		// ������֤��
					Constructor<IimportCheck> constructor = (Constructor<IimportCheck>) Class.forName(className).
																						getConstructor(String.class, String.class, String.class);
					
					for(Iterator<Element> iterator = check.elementIterator(); iterator.hasNext(); ){
						Element entity = iterator.next();
						String data_mapping = entity.element("data-mapping").getText();
						// ��������
						String param_tableName = entity.element("param-tableName").getText();
						// �����ֶ�
						String param_tableField = entity.element("param-tableField").getText();
						// ����������ʾ
						String param_comment = entity.element("param-comment").getText();
						
						jclxMap.put(data_mapping, constructor.newInstance(param_tableName, param_tableField, param_comment));
					}
				}else if("patternCheck".equalsIgnoreCase(type)){    // ������ʽ��֤��
					Constructor<IimportCheck> constructor = (Constructor<IimportCheck>) Class.forName(className).
																						getConstructor(String.class, String.class);

					for(Iterator<Element> iterator = check.elementIterator(); iterator.hasNext(); ){
						Element entity = iterator.next();
						String data_mapping = entity.element("data-mapping").getText();
						// ������ʽ
						String param_pattern = entity.element("param-pattern").getText();
						// ����������ʾ
						String param_comment = entity.element("param-comment").getText();
						
						jclxMap.put(data_mapping, constructor.newInstance(param_pattern, param_comment));
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return jclxMap;
	}
	
	/**
	 * ��ü���ֶ��Լ��������
	 * ���ص�map���棬keyΪ�ֶ�
	 * map��valueΪ�������List
	 * @param tableName
	 * @return
	 */
	private Map<String, List<String>> getJczd(String tableName){
		List<HashMap<String, String>> zdList = CommonQueryDAO.commonQueryforList("xg_xtwh_drzdjcb", " where bm=?", 
																		new String[]{tableName}, new String[]{"zd","jclx"}, "");
		Map<String, List<String>> jczd = new HashMap<String, List<String>>();
		
		for(HashMap<String, String> map : zdList){
			if(!jczd.containsKey(map.get("zd"))){
				List<String> lxList = new ArrayList<String>();
				lxList.add(map.get("jclx"));
				jczd.put(map.get("zd").toLowerCase(), lxList);
			}else{
				(jczd.get(map.get("zd").toLowerCase())).add(map.get("jclx"));
			}
		}
		
		return jczd;
	}
	
	public List<String[]> checkExcelData(HashMap<String, String> model, 
            String[] yzArr) throws Exception {		
		String xxdm = model.get("xxdm");
		String filePath = model.get("filePath");
		String realTable = model.get("realTable");
		String drms = model.get("drms");
		String userType = model.get("userType");
		String mappingItems = model.get("mappingItems");
		
		ArrayList<String[]> list = super.checkExcelCommon(filePath, realTable, mappingItems);//�ǿպ��ֶγ��ȵļ��		
		//if(list.size()<1){//���û�зǷ������ݾͽ����ж� 
		if(realTable!=null && (realTable.equals("xsxxb") || realTable.equalsIgnoreCase("bks_xsjbxx"))&& drms.equals("1")){//ѧ����Ϣ����������
		list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
		
		}else if("xszsxxb".equalsIgnoreCase(realTable)){//����ѧ��ס����Ϣ������������Ƿ����,2010.11.30 edit by luojw		
		list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));	
		list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
		list.addAll(super.checkExcelSsbhExists(filePath, realTable, mappingItems,drms));	
		}else if("szdw_xysf_fdypxb".equalsIgnoreCase(realTable)){
		list.addAll(super.checkExcelZghExists(filePath, realTable, mappingItems));
		}else{
		ImportDataDAO dao = new ImportDataDAO();
		boolean xhCheck =dao.checkXhComments(realTable);
		//2009-9-21 luning 
		if(!"newstusinfo".equalsIgnoreCase(realTable)||xhCheck){//������Ϣ���ü��
		list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems, yzArr));//ѧ���Ƿ���ں�ѧ���������Ƿ�ƥ��ļ��
		} 
		if(realTable!=null 
		&& (realTable.equalsIgnoreCase("xsjxjb") 
		|| realTable.equalsIgnoreCase("xsrychb"))){
		//�������� || �����ƺ�
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
		list.addAll(super.checkConditionOfPrise(filePath, realTable, mappingItems,userType));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս�����ҵ��ѧ
		list.addAll(super.ahjgCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)){//�Ϻ�����ӡˢ�ߵ�ר��ѧУ							 
		list.addAll(super.shcbysgdzkCheckOfPrise(filePath, realTable, mappingItems, userType));
		}
		}
		}
		//}
		return list;//���ؾ�����������������
	}
	
	/**
	 * ���Execl���е����������ݿ���Ҫ�����ʽ�Ƿ�һ��
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelData(String filePath, String realTable, String mappingItems,String drms,String userType) throws Exception {		
		String xxdm = StandardOperation.getXxdm();
		ArrayList<String[]> list = super.checkExcelCommon(filePath, realTable, mappingItems);//�ǿպ��ֶγ��ȵļ��		
//		if(list.size()<1){//���û�зǷ������ݾͽ����ж� 
			if(realTable!=null && (realTable.equals("xsxxb") || realTable.equalsIgnoreCase("bks_xsjbxx"))&& drms.equals("1")){//ѧ����Ϣ����������
				list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
			}else if("xszsxxb".equalsIgnoreCase(realTable)){//����ѧ��ס����Ϣ������������Ƿ����,2010.11.30 edit by luojw
				list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));	
				list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
				list.addAll(super.checkExcelSsbhExists(filePath, realTable, mappingItems,drms));	
			}else{
				ImportDataDAO dao = new ImportDataDAO();
				boolean xhCheck =dao.checkXhComments(realTable);
				//2009-9-21 luning 
				if(!"newstusinfo".equalsIgnoreCase(realTable)||xhCheck){//������Ϣ���ü��
					list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));//ѧ���Ƿ���ں�ѧ���������Ƿ�ƥ��ļ��
				} 
				if(realTable!=null 
							&& (realTable.equalsIgnoreCase("xsjxjb") 
							|| realTable.equalsIgnoreCase("xsrychb"))){
					 //�������� || �����ƺ�
						 if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
							 list.addAll(super.checkConditionOfPrise(filePath, realTable, mappingItems,userType));
						 }
//						 else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����
//							 list.addAll(super.zjjdCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
//						 }
						 else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս�����ҵ��ѧ
							 list.addAll(super.ahjgCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
						 }else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)){//�Ϻ�����ӡˢ�ߵ�ר��ѧУ							 
							 list.addAll(super.shcbysgdzkCheckOfPrise(filePath, realTable, mappingItems, userType));
						 }
					}
			}
//		}
		return list;//���ؾ�����������������
	}
	
	/**
	 * ��excel�����ݱ��浽���ݿ�,�����Υ��Ψһ��Լ�������������excel��ʽ����
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response         
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> saveExcel2Db(ExportAndImportModel model, HttpServletResponse response, String[] yzArr){		
		String xxdm = Base.xxdm;
		String path = model.getFilePath();
		String tableName = model.getRealTable();
		String[][] splitMappingOne = model.getSplitMapping();
		
		ImportDataDAO dao = new ImportDataDAO();

		List<String[]> list = null;
		try{			
			list = checkExcelDataComm(model);//���ݼ��
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//���ڱ����excel��õ�����
			
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[1])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//���Ҫ�����¼   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				if(NotNullAndLengthCheck.checkNoValue(row)){
					continue;
				}
				
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);		
				//ȥ�ո�
				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				tmpRow = trim(tmpRow);
				excelData.add(tmpRow);
                //���Ҫ�����¼   end
				}
			}
			ArrayList<String[]> violateOnlyOne = null;				
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && tableName.equalsIgnoreCase("zhszcp")){
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
					&& ("xsjxjb".equalsIgnoreCase(tableName) || "xsrychb"
							.equalsIgnoreCase(tableName))) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) && "pjpy_xjbjandwmsqb".equalsIgnoreCase(tableName)) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if ("zhszcp".equalsIgnoreCase(tableName)) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if ("zjlg_dywsf".equalsIgnoreCase(tableName)) {//�㽭�������ֵ���
				violateOnlyOne =  dao.saveWsfData(excelData, splitMappingOne,tableName);
			} else if("zdlskhb".equalsIgnoreCase(tableName)){
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,model);
			}else{
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName);
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//�����Υ��Ψһ��Լ�������ݾͼ��뵽�����������б���
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * �����°�ѧ����Ϣ����
	 * @param model
	 * @param response
	 * @param yzArr
	 * @return
	 * @author sjf
	 */
	public List<String[]> saveExcel2Xsxx(ExportAndImportModel model, HttpServletResponse response, String[] yzArr){		

		DAO dao = DAO.getInstance();
		
		// ��ȡѧ����Ϣ�Լ���ͥ��Ϣ��������
		String[] xsxxCols = dao.getColumnName("select * from xsxxb where 1=2");
		String[] fzxxCols = dao.getColumnName("select * from xsfzxxb where 1=2");
		
		String path = model.getFilePath();
		String[][] splitMappingOne = model.getSplitMapping();

		ArrayList<String[]> xsxxData = new ArrayList<String[]>();
		ArrayList<String[]> fzxxData = new ArrayList<String[]>();
		
		// ����execl��ÿһ����Ԫ�񣬲��жϸ�������ѧ����Ϣ���ݻ��Ǽ�ͥ��Ϣ���ݣ��ֿ���������List����
		try {
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			
			for(int sourceRow=1; sourceRow<sourceRowCount; sourceRow++){
				String[] xsxxValue = new String[xsxxCols.length];
				String[] fzxxValue = new String[fzxxCols.length];
				for(int sourceCol=0; sourceCol<splitMappingOne.length; sourceCol++){
					String zd = splitMappingOne[sourceCol][1];
					int index = contain(xsxxCols, zd);
		
					if(index > -1){
						xsxxValue[index] = ExcelMethods.getOneCellContent(sourceSheet, sourceCol, sourceRow);
					}
					
					index = contain(fzxxCols, zd);
					
					if(index > -1){
						fzxxValue[index] = ExcelMethods.getOneCellContent(sourceSheet, sourceCol, sourceRow);
					}
				}
				xsxxData.add(tran(xsxxValue));
				fzxxData.add(tran(fzxxValue));
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		//
		
		boolean[] flag = saveData("xsxxb", xsxxCols, xsxxData);
		
		for(int i=0; i<flag.length; i++){
			if(!flag[i]){
				fzxxData.remove(i);
			}
		}
		
		saveData("xsfzxxb", fzxxCols, fzxxData);
	
		return null;
	}
	
	public boolean[] saveData(String tableName, String[] cols, List<String[]> valueList){
		DAO dao = DAO.getInstance();
		
		boolean[] flag = new boolean[valueList.size()];
		
		for(int i=0; i<valueList.size(); i++){
			String[] value = valueList.get(i);
			try {
				flag[i] = dao.runInsert(tableName, cols, tran(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/**
	 * ��excel�����ݱ��浽���ݿ�,�����Υ��Ψһ��Լ�������������excel��ʽ����
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response         
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> saveExcel2Db(String path,String tableName,String mappingItems,String userType,HttpServletResponse response){		
		String xxdm = StandardOperation.getXxdm();
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		List list = null;
		try{			
			list = checkExcelData(path, tableName, mappingItems, "1", userType);//���ݼ��
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[1])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//���Ҫ�����¼   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				excelData.add(tmpRow);
                //���Ҫ�����¼   end
				}
			}
			ArrayList<String[]> violateOnlyOne = null;				
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && tableName.equalsIgnoreCase("zhszcp")){
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
					&& ("xsjxjb".equalsIgnoreCase(tableName) || "xsrychb"
							.equalsIgnoreCase(tableName))) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) && "pjpy_xjbjandwmsqb".equalsIgnoreCase(tableName)) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else if ("zhszcp".equalsIgnoreCase(tableName)) {
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName,xxdm);
			} else{
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName);
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//�����Υ��Ψһ��Լ�������ݾͼ��뵽�����������б���
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��excel�����ݸ��µ����ݿ�,�����Υ��Ψһ��Լ�������������excel��ʽ����
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response
	 * @param os         
	 * @return           �Ƿ���ɹ����ɹ�Ϊ��true ����false
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> updateExcel2Db(ExportAndImportModel model,HttpServletResponse response, String[] yzArr){
		//boolean result = true;//����ɹ�
		String xxdm = Base.xxdm;
		String path = model.getFilePath();
		String tableName = model.getRealTable();
		
		ImportDataDAO dao = new ImportDataDAO();
		String[][] splitMappingOne = model.getSplitMapping();
		
		List<String[]> list = null;
		try{
			list = checkExcelDataComm(model);//���ݼ��
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				excelData.add(StringUtils.joinStrArr(new String[]{rownum+""},row));
                //���Ҫ�����¼   end
			}
			ArrayList<String[]> violateOnlyOne = null;
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//������
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				//���������ۺ����ʲ���
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if ("zjlg_dywsf".equalsIgnoreCase(tableName)) {//�㽭��ƽʱ�ֵ����޸�
				violateOnlyOne =  dao.updateWsfData(excelData, splitMappingOne,tableName);
			} else{
				if(tableName != null && "xsxxb".equalsIgnoreCase(tableName)){
					dao.synchXsxx();
				}
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName);				
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//��������ݿ��в����ڵ����ݾ����
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��excel�����ݸ��µ����ݿ�,�����Υ��Ψһ��Լ�������������excel��ʽ����
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response
	 * @param os         
	 * @return           �Ƿ���ɹ����ɹ�Ϊ��true ����false
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> updateExcel2Db(String path,String tableName,String mappingItems,String userType,HttpServletResponse response){
		//boolean result = true;//����ɹ�
		String xxdm = StandardOperation.getXxdm();
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}	
		List list = null;
		try{
			list = checkExcelData(path, tableName, mappingItems, "0", userType);//���ݼ��
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				excelData.add(StringUtils.joinStrArr(new String[]{rownum+""},row));
                //���Ҫ�����¼   end
			}
			ArrayList<String[]> violateOnlyOne = null;
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//������
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				//���������ۺ����ʲ���
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else{
				if(tableName != null && "xsxxb".equalsIgnoreCase(tableName)){
					dao.synchXsxx();
				}
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName);				
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//��������ݿ��в����ڵ����ݾ����
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<HashMap<String, String>> getZdyDdTableColumnList(String tabName, String dzyDdTab) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		ImportDataDAO dao = new ImportDataDAO();
		if("py_bdszb".equalsIgnoreCase(dzyDdTab)){
			String viewName = "view_" + tabName;
			list = dao.getColColumn(tabName,viewName,dzyDdTab);//��ȡ��ͬѧУ��ͬһ��ȡ���ֶ���Ϣ
		}else{
			list = dao.getColColumn(tabName,dzyDdTab);//��ȡ��ͬѧУ��ͬһ��ȡ���ֶ���Ϣ
		}
		return list;
	}
	
	/**
	 * ��ȡ�Զ����ֶα���ʵ��
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSaveTable(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ty_bdsz", "ty_bdsz_bcnr");//ͨ��
		map.put("py_bdszb", "py_bdsz_bcnr");//����ģ��		
		return map;
	}
	
	public List<String[]> saveExcel2DbForZdy(String path, String tableName, String mappingItems, String userType, HttpServletResponse response, String dzyDdTab) throws SQLException {
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}
		
		//�Զ����ֶ�
		String [] zdids = dao.getZdyZdString(dzyDdTab,tableName);
		//�Զ����ֶα���ʵ��
		String saveTable = "";
		if(dzyDdTab.equalsIgnoreCase("ty_bdsz")){
			saveTable="ty_bdsz_bcnr"; 
		}
		List list = null;
		try{			
			list = checkExcelData(path, tableName, mappingItems, "1", userType);//���ݼ��
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[0])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//���Ҫ�����¼   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				excelData.add(tmpRow);
                //���Ҫ�����¼   end
				}
			}
			ArrayList<String[]> violateOnlyOne = null;
			violateOnlyOne =  dao.saveZdyData(excelData, splitMappingOne,tableName,saveTable,zdids);
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//�����Υ��Ψһ��Լ�������ݾͼ��뵽�����������б���
				list.addAll(violateOnlyOne);
			}
 		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ȥ�ո�
	 * */
	public String[] trim(String[] args){
		for(int i=0; i<args.length; i++){
			args[i] = StringUtils.isNotNull(args[i]) ? args[i].trim() : args[i];
		}
		return args;
	}
	
	/**
	 * �ж�str�Ƿ�������src�У����ڷ�������λ�ã����ڷ���-1
	 * @param src
	 * @param str
	 * @return
	 * @author sjf
	 */
	public int contain(String[] src, String str){
		int index = -1;
		if(src != null && str != null){
			for(int i=0; i<src.length; i++){
				if(src[i].equalsIgnoreCase(str)){
					index = i;
					break;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * ��������null��Ϊ""
	 * @param str
	 * @return
	 */
	public String[] tran(String[] str){
		for(int i=0; i<str.length; i++){
			if(StringUtils.isNull(str[i])){
				str[i] = "";
			}
		}
		return str;
	}
}
