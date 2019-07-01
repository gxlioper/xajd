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
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 数据导入Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class ImportDataService extends IimportDataService {
	/**
	 * 获取要导入的数据库表的列
	 * @param tabName
	 * @return List
	 * @throws SQLException 
	 * */
	public List getTableColumnList(String tabName){
		List list = new ArrayList();
		String xxdm = StandardOperation.getXxdm();
		String[] column = null;
		ImportDataDAO dao = new ImportDataDAO();
		//TODO 不同学校对同一个表取不同的字段时在drb中插入属于此学校的字段
		try {
			column = dao.getDiffentColumn(tabName,xxdm);//获取不同学校对同一表取的字段信息
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}		
		
		//调整如果字段中有BZ这个字段,则把BZ放到最后
		try {
			column = ArrayUtil.changeBzAfter(column);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		list = dao.getTableColumnList(tabName,column);//获取导入表的字段信息
		return list;
	}
	
	/**
	 * 获取表的主键字段
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
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return values;
	}
	
	/**
	 * 返回excel文件的列
	 * @param filePath
	 * @return ArrayList<HashMap<String, String>>
	 */
	public ArrayList<HashMap<String, String>> getExcelCols(String filePath){
		return ExcelMethods.getExcelColumns(filePath);
	}
	
	/**
	 * 检测Execl表中的数据与数据库中要求的形式是否一致
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean
	 * @aurthor sjf 
	 **/
	public List<String[]> checkExcelDataComm(ExportAndImportModel model) throws Exception {				
		// 返回的所有错误信息 
		List<String[]> errorList = new ArrayList<String[]>();
		
		// 导入非空检测类
		IimportCheck  notNullCheck = new NotNullAndLengthCheck();
		
		// 有先进行所有列的非空检测和长度验证
		errorList =notNullCheck.checkExcelData(model);
		
		// 初始化检测类型Map,若有自定义检测,在该方法中添加自己的检测类实现IimportCheck的接口
		Map<String,IimportCheck> jclxMap = initializeJclx();
		
		// 获取所有表中配置好需要检测的字段，key为表中字段，value为需要检测类型list
		Map<String, List<String>> jczd = getJczd(model.getRealTable());
		
		// excel与表字段的一一映射
		String[][] splitMappingOne = model.getSplitMapping();
		
		// 遍历导入字段，获取导入字段的检测类型，根据所有检测类型获取实体检测类，运行检测方法，把错误数据放入errorList中
		loop:
		for(int i=0; i<splitMappingOne.length; i++){
			// 对应的excel列号
			String index = splitMappingOne[i][0];
			model.setIndex(Integer.parseInt(index));
			
			// 导入表的字段
			String zd = splitMappingOne[i][1].toLowerCase();
			// 导入字段需要做的检测类型
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
		
		return errorList;//返回经过检测有问题的数据
	}
	
	/**
	 * 初始化检测类型map,在importData.xml中配置
	 * @return
	 * @author sjf
	 */
	@SuppressWarnings("unchecked")
	private Map<String,IimportCheck> initializeJclx(){
		// 通过dom4j获取xml文档对象
		Document doc = Dom4jForXmlHandle.parserXml("importData.xml");
		
		// 存放检测类型
		Map<String,IimportCheck> jclxMap = new HashMap<String, IimportCheck>();
		
		// 获取所有检测类型
		List<Element> checkList = doc.selectNodes("//checks/check");
	
		for(int i=0; i<checkList.size(); i++){
			Element check = checkList.get(i);
			String type = check.valueOf("@type");
			String className = check.valueOf("@class");
			try{
				if("relationCheck".equalsIgnoreCase(type)){    		// 关联验证类
					Constructor<IimportCheck> constructor = (Constructor<IimportCheck>) Class.forName(className).
																						getConstructor(String.class, String.class, String.class);
					
					for(Iterator<Element> iterator = check.elementIterator(); iterator.hasNext(); ){
						Element entity = iterator.next();
						String data_mapping = entity.element("data-mapping").getText();
						// 参数表名
						String param_tableName = entity.element("param-tableName").getText();
						// 参数字段
						String param_tableField = entity.element("param-tableField").getText();
						// 参数错误提示
						String param_comment = entity.element("param-comment").getText();
						
						jclxMap.put(data_mapping, constructor.newInstance(param_tableName, param_tableField, param_comment));
					}
				}else if("patternCheck".equalsIgnoreCase(type)){    // 正则表达式验证类
					Constructor<IimportCheck> constructor = (Constructor<IimportCheck>) Class.forName(className).
																						getConstructor(String.class, String.class);

					for(Iterator<Element> iterator = check.elementIterator(); iterator.hasNext(); ){
						Element entity = iterator.next();
						String data_mapping = entity.element("data-mapping").getText();
						// 正则表达式
						String param_pattern = entity.element("param-pattern").getText();
						// 参数错误提示
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
	 * 获得检测字段以及检测类型
	 * 返回的map里面，key为字段
	 * map的value为检测类型List
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
		
		ArrayList<String[]> list = super.checkExcelCommon(filePath, realTable, mappingItems);//非空和字段长度的检测		
		//if(list.size()<1){//如果没有非法的数据就进行判断 
		if(realTable!=null && (realTable.equals("xsxxb") || realTable.equalsIgnoreCase("bks_xsjbxx"))&& drms.equals("1")){//学生信息导入新数据
		list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
		
		}else if("xszsxxb".equalsIgnoreCase(realTable)){//导入学生住宿信息，检测宿舍编号是否存在,2010.11.30 edit by luojw		
		list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));	
		list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
		list.addAll(super.checkExcelSsbhExists(filePath, realTable, mappingItems,drms));	
		}else if("szdw_xysf_fdypxb".equalsIgnoreCase(realTable)){
		list.addAll(super.checkExcelZghExists(filePath, realTable, mappingItems));
		}else{
		ImportDataDAO dao = new ImportDataDAO();
		boolean xhCheck =dao.checkXhComments(realTable);
		//2009-9-21 luning 
		if(!"newstusinfo".equalsIgnoreCase(realTable)||xhCheck){//新生信息表不用检测
		list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems, yzArr));//学生是否存在和学号与姓名是否匹配的检测
		} 
		if(realTable!=null 
		&& (realTable.equalsIgnoreCase("xsjxjb") 
		|| realTable.equalsIgnoreCase("xsrychb"))){
		//评奖评优 || 荣誉称号
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//武汉理工大学
		list.addAll(super.checkConditionOfPrise(filePath, realTable, mappingItems,userType));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//安徽建筑工业大学
		list.addAll(super.ahjgCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)){//上海出版印刷高等专科学校							 
		list.addAll(super.shcbysgdzkCheckOfPrise(filePath, realTable, mappingItems, userType));
		}
		}
		}
		//}
		return list;//返回经过检测有问题的数据
	}
	
	/**
	 * 检测Execl表中的数据与数据库中要求的形式是否一致
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkExcelData(String filePath, String realTable, String mappingItems,String drms,String userType) throws Exception {		
		String xxdm = StandardOperation.getXxdm();
		ArrayList<String[]> list = super.checkExcelCommon(filePath, realTable, mappingItems);//非空和字段长度的检测		
//		if(list.size()<1){//如果没有非法的数据就进行判断 
			if(realTable!=null && (realTable.equals("xsxxb") || realTable.equalsIgnoreCase("bks_xsjbxx"))&& drms.equals("1")){//学生信息导入新数据
				list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
			}else if("xszsxxb".equalsIgnoreCase(realTable)){//导入学生住宿信息，检测宿舍编号是否存在,2010.11.30 edit by luojw
				list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));	
				list.addAll(super.checkExcelXhRepeat(filePath, realTable, mappingItems,drms));	
				list.addAll(super.checkExcelSsbhExists(filePath, realTable, mappingItems,drms));	
			}else{
				ImportDataDAO dao = new ImportDataDAO();
				boolean xhCheck =dao.checkXhComments(realTable);
				//2009-9-21 luning 
				if(!"newstusinfo".equalsIgnoreCase(realTable)||xhCheck){//新生信息表不用检测
					list.addAll(super.checkExcelXh2Xm(filePath, realTable, mappingItems));//学生是否存在和学号与姓名是否匹配的检测
				} 
				if(realTable!=null 
							&& (realTable.equalsIgnoreCase("xsjxjb") 
							|| realTable.equalsIgnoreCase("xsrychb"))){
					 //评奖评优 || 荣誉称号
						 if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//武汉理工大学
							 list.addAll(super.checkConditionOfPrise(filePath, realTable, mappingItems,userType));
						 }
//						 else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//浙江机电
//							 list.addAll(super.zjjdCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
//						 }
						 else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//安徽建筑工业大学
							 list.addAll(super.ahjgCheckConditionOfPrise(filePath, realTable, mappingItems, userType));
						 }else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)){//上海出版印刷高等专科学校							 
							 list.addAll(super.shcbysgdzkCheckOfPrise(filePath, realTable, mappingItems, userType));
						 }
					}
			}
//		}
		return list;//返回经过检测有问题的数据
	}
	
	/**
	 * 把excel的数据保存到数据库,如果有违反唯一性约束的情况，就以excel格式返回
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
			list = checkExcelDataComm(model);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[1])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				if(NotNullAndLengthCheck.checkNoValue(row)){
					continue;
				}
				
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);		
				//去空格
				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				tmpRow = trim(tmpRow);
				excelData.add(tmpRow);
                //添加要保存记录   end
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
			} else if ("zjlg_dywsf".equalsIgnoreCase(tableName)) {//浙江理工卫生分导入
				violateOnlyOne =  dao.saveWsfData(excelData, splitMappingOne,tableName);
			} else if("zdlskhb".equalsIgnoreCase(tableName)){
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,model);
			}else{
				violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName);
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有违反唯一性约束的数据就加入到有问题数据列表中
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 保存新版学生信息导入
	 * @param model
	 * @param response
	 * @param yzArr
	 * @return
	 * @author sjf
	 */
	public List<String[]> saveExcel2Xsxx(ExportAndImportModel model, HttpServletResponse response, String[] yzArr){		

		DAO dao = DAO.getInstance();
		
		// 获取学生信息以及家庭信息的所有列
		String[] xsxxCols = dao.getColumnName("select * from xsxxb where 1=2");
		String[] fzxxCols = dao.getColumnName("select * from xsfzxxb where 1=2");
		
		String path = model.getFilePath();
		String[][] splitMappingOne = model.getSplitMapping();

		ArrayList<String[]> xsxxData = new ArrayList<String[]>();
		ArrayList<String[]> fzxxData = new ArrayList<String[]>();
		
		// 遍历execl中每一个单元格，并判断改数据是学生信息数据还是家庭信息数据，分开放在两个List当中
		try {
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			
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
	 * 把excel的数据保存到数据库,如果有违反唯一性约束的情况，就以excel格式返回
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		List list = null;
		try{			
			list = checkExcelData(path, tableName, mappingItems, "1", userType);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[1])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				excelData.add(tmpRow);
                //添加要保存记录   end
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
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有违反唯一性约束的数据就加入到有问题数据列表中
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 把excel的数据更新到数据库,如果有违反唯一性约束的情况，就以excel格式返回
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response
	 * @param os         
	 * @return           是否导入成功，成功为：true 否则：false
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> updateExcel2Db(ExportAndImportModel model,HttpServletResponse response, String[] yzArr){
		//boolean result = true;//导入成功
		String xxdm = Base.xxdm;
		String path = model.getFilePath();
		String tableName = model.getRealTable();
		
		ImportDataDAO dao = new ImportDataDAO();
		String[][] splitMappingOne = model.getSplitMapping();
		
		List<String[]> list = null;
		try{
			list = checkExcelDataComm(model);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				excelData.add(StringUtils.joinStrArr(new String[]{rownum+""},row));
                //添加要保存记录   end
			}
			ArrayList<String[]> violateOnlyOne = null;
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//宁波理工
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				//云南艺术综合素质测评
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if ("zjlg_dywsf".equalsIgnoreCase(tableName)) {//浙江理工平时分导入修改
				violateOnlyOne =  dao.updateWsfData(excelData, splitMappingOne,tableName);
			} else{
				if(tableName != null && "xsxxb".equalsIgnoreCase(tableName)){
					dao.synchXsxx();
				}
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName);				
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有数据库中不存在的数据就输出
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 把excel的数据更新到数据库,如果有违反唯一性约束的情况，就以excel格式返回
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response
	 * @param os         
	 * @return           是否导入成功，成功为：true 否则：false
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> updateExcel2Db(String path,String tableName,String mappingItems,String userType,HttpServletResponse response){
		//boolean result = true;//导入成功
		String xxdm = StandardOperation.getXxdm();
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}	
		List list = null;
		try{
			list = checkExcelData(path, tableName, mappingItems, "0", userType);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				excelData.add(StringUtils.joinStrArr(new String[]{rownum+""},row));
                //添加要保存记录   end
			}
			ArrayList<String[]> violateOnlyOne = null;
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//宁波理工
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && tableName.equalsIgnoreCase("ynys_zhszcpb")) {
				//云南艺术综合素质测评
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName,xxdm);
			} else{
				if(tableName != null && "xsxxb".equalsIgnoreCase(tableName)){
					dao.synchXsxx();
				}
				violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName);				
			}
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有数据库中不存在的数据就输出
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
			list = dao.getColColumn(tabName,viewName,dzyDdTab);//获取不同学校对同一表取的字段信息
		}else{
			list = dao.getColColumn(tabName,dzyDdTab);//获取不同学校对同一表取的字段信息
		}
		return list;
	}
	
	/**
	 * 获取自定义字段保存实表
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSaveTable(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ty_bdsz", "ty_bdsz_bcnr");//通用
		map.put("py_bdszb", "py_bdsz_bcnr");//评奖模块		
		return map;
	}
	
	public List<String[]> saveExcel2DbForZdy(String path, String tableName, String mappingItems, String userType, HttpServletResponse response, String dzyDdTab) throws SQLException {
		ImportDataDAO dao = new ImportDataDAO();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}
		
		//自定义字段
		String [] zdids = dao.getZdyZdString(dzyDdTab,tableName);
		//自定义字段保存实表
		String saveTable = "";
		if(dzyDdTab.equalsIgnoreCase("ty_bdsz")){
			saveTable="ty_bdsz_bcnr"; 
		}
		List list = null;
		try{			
			list = checkExcelData(path, tableName, mappingItems, "1", userType);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[0])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				excelData.add(tmpRow);
                //添加要保存记录   end
				}
			}
			ArrayList<String[]> violateOnlyOne = null;
			violateOnlyOne =  dao.saveZdyData(excelData, splitMappingOne,tableName,saveTable,zdids);
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有违反唯一性约束的数据就加入到有问题数据列表中
				list.addAll(violateOnlyOne);
			}
 		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 去空格
	 * */
	public String[] trim(String[] args){
		for(int i=0; i<args.length; i++){
			args[i] = StringUtils.isNotNull(args[i]) ? args[i].trim() : args[i];
		}
		return args;
	}
	
	/**
	 * 判断str是否在数组src中，若在返回所在位置，不在返回-1
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
	 * 把数组中null都为""
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
