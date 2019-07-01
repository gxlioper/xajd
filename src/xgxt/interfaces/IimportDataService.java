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
//	循环列
//	列数组 colArr
//		   valArr
//	for(int i=0; i<列数组长度; i++){
//		colArr[i]
//		String[] att = tableColumnAttributes.get(colArr[i]);
//		att[0] >= valArr[i].length;
//	}
	
	/**
	 * 检测Execl表中的数据与数据库中要求的形式是否一致
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
//			//开始检测数据
//			for(int rownum=1;rownum<valArr.size();rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
//				//start 开始遍历一行的各个字段
//				String[] row = valArr.get(rownum);
//				
//				String errors = "这条记录中包含的错误：";
//				boolean oneRowResult = false;
//				for(int selnum=0;selnum<colArr.length;selnum++){//对选中的列进行检测
//					String colName = colArr[selnum];
//		
//					String[] colAttrs = tableColumnAttributes.get(colName);//获得这个列对应的数据库表中的字段
//					if(colAttrs!=null){
//						//对获得字段值进行合法性判断
//						if(StringUtils.isNull(row[selnum]) && colAttrs[2].equals("N") && StringUtils.isNull(colAttrs[4])){ //非空判断
//							oneRowResult = true;
//							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" 字段为空，数据库要求此字段非空；");
//						}
//						if(!StringUtils.isNull(oneCellContent) && (oneCellContent.getBytes().length>Integer.parseInt(colAttrs[1]))){//是否超过数据库表字段设定长度判断
//							oneRowResult = true;
//							errors += (ExcelMethods.getOneCellContent(sourceSheet, Integer.parseInt(splitMappingOne[selnum][0]), 0)+" 字段数据长度过大，数据库要求的长度为："+colAttrs[1]+"字符(即中文长度折半)；");
//						}	
//					}
//					//TODO 其它特殊判断可在此处加入
//				}
//				if(oneRowResult){//如果有错误发生就进行记录
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
	 * 检测Execl表中的数据与数据库中要求的形式是否一致
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}
		HashMap<String, String[]> tableColumnAttributes = dao.getTableColumnsAttributes(realTable);
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数	
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			//开始检测数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
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
					//TODO 其它特殊判断可在此处加入
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
	 * 检测Execl表中的数据与数据库中学号和姓名是否一致
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		boolean xmFlag = false;//是否验证姓名标志
		boolean sfzhFlag = false;//是否验证身份证号标志
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
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
			String tempXm = "";//从excel中获得姓名信息
			String tempSfzh = "";//从excel中获得身份证号信息
			
			int xhNum = -1;//记录学号所在列的序号，从0开始
			int xmNum = -1;//记录姓名所在列的序号，从0开始
			int sfzhNum = -1;//记录身份证号所在列的序号，从0开始
			for(int colNum=0;colNum<columnNum.size();colNum++){//从excel文件中获取列，检查列中是否含有学号和姓名
				HashMap<String, String> col = columnNum.get(colNum);
				if("学号".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xhNum = colNum;
				}				
				if(xmFlag && "姓名".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xm".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xmNum = colNum;
				}
				if(sfzhFlag && "身份证号".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "sfzh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					sfzhNum = colNum;
				}
			}
			//开始检测数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					tempXm = "";
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if(xmFlag && "xm".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取姓名
						tempXm = oneCellContent;
					}		
					if(sfzhFlag && "sfzh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取身份证号
						tempSfzh = oneCellContent;
					}
				}
				
				if((tempXh == null) && (xhNum > -1)){//字段对应中没有学号字段，但是excel中有学号列
					tempXh = StringUtils.Q2BChange(row[xhNum]);
				}

				if(xmFlag 
						&& (tempXm == null || "".equalsIgnoreCase(tempXm)) 
						&& (xmNum > -1)){//字段对应中没有姓名字段，但是excel中有姓名列
					tempXm = StringUtils.Q2BChange(row[xmNum]);
				}
				if(sfzhFlag 
						&& (tempSfzh == null || "".equalsIgnoreCase(tempSfzh)) 
						&& (sfzhNum > -1)){//字段对应中没有身份证号字段，但是excel中有身份证号列
					tempSfzh = StringUtils.Q2BChange(row[sfzhNum]);
				}
				
				String realXh = dao.checkXh(tempXh);	
				
				//-------edit by qph---begin-----
				//检测学号是否存在，如果字段为xh、对应comment为空或“学号”时才检测-------------
				String[] xhCn = dao.getPkNameCN(realTable, new String[] {"xh"});
				
				if(StringUtils.isNull(realXh) && ("学号".equals(xhCn[0]) || StringUtils.isNull(xhCn[0]))){//学号不存在
				
				//-------edit by qph-----end---------------------------
					if(realTable != null &&!"bjlh_xszsxxb".equalsIgnoreCase(realTable) && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "学号为"+tempXh+"的学生不存在";
						}
					}
				}else{
					if(xmFlag && xhNum>-1 && xmNum>-1){//excel表中存在学号和姓名列
						HashMap<String, String> xmMap = new HashMap<String, String>();
						xmMap.put("xm", tempXm);
						
						String realXm = dao.checkXh2Other(tempXh, xmMap);//学号和姓名是否匹配
						if(realXm!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += StringUtils.joinStr("学号与姓名不匹配，学号", 
									                      tempXh, 
									                      "对应的姓名是", 
									                      realXm, 
									                      ";");
						}
					}
					if(sfzhFlag && xhNum>-1 && sfzhNum>-1){//excel表中存在学号和身份证号列
						HashMap<String, String> sfzhMap = new HashMap<String, String>();
						sfzhMap.put("sfzh", tempSfzh);
						String realSfzh = dao.checkXh2Other(tempXh, sfzhMap);//学号和身份证号是否匹配
						if(realSfzh!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += StringUtils.joinStr("学号与身份证号不匹配，学号",
									                      tempXh,
									                      "对应的身份证号是",
									                      realSfzh,
									                      ";");
						}
					}
				}		
				
				//中国地质大学勤工助学酬金发放导入时检测学生勤工助学申请不通过的学生，在导入时不导入
				String xxdm = StandardOperation.getXxdm();
				if("xscjffb".equalsIgnoreCase(realTable)&&xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					String qgzxxh = dao.checkQgzx(tempXh);
					if(qgzxxh==null||qgzxxh.equals("")){//学号不存在，学生没有通过勤工助学审核，不属于勤工助学学生
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "学号为"+tempXh+"的学生不是勤工助学学生无法填报酬金";
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
	 * 检测Execl表中的数据与数据库中学号是否重复
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}				
				}
				boolean result = dao.checkXhRepeat(tempXh,realTable);//判断学号是否重复
				if((result)){//导入新数据
					oneRowResult = true;
					errors += "学号为："+tempXh+"的学生已经存在";
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
	 * 检测Execl表中的数据与数据库中学号是否重复
	 * @param firlPath
	 * @param realTable
	 * @param mappingItems
	 * @param response
	 * @return boolean 
	 * */
	public ArrayList<String[]> checkConditionOfPrise(String filePath, String realTable, String mappingItems,String userType){
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
			String tempSh = "";//从excel中获得审核信息			 
			String xmdm = "";//从excel中获得项目代码 
			String xn = "";
			String nd = "";
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//奖学金代码
						xmdm = oneCellContent;
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//荣誉称号代码 
						xmdm = oneCellContent;
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//学年
						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//年度 
						nd = oneCellContent;
					}
					if(userType!=null && userType.equalsIgnoreCase("fdy")){
						if("fdysh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取辅导员审核
							tempSh = oneCellContent;
						}
					}else if(userType!=null && userType.equalsIgnoreCase("xy")){
						if("xysh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取辅导员审核
							tempSh = oneCellContent;
						}
					}else{
						if("xxsh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取辅导员审核
							tempSh = oneCellContent;
						}
					}
				}
				PjpyWhlgDAO whlgDao = new PjpyWhlgDAO();
				if(tempSh!=null && tempSh.equalsIgnoreCase("通过")){					
					if(realTable!=null && realTable.equalsIgnoreCase("xsjxjb")){//奖学金
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
							errors = "审核通过人数已达到或超过限制人数!超过人数：" + String.valueOf(tgrs).substring(1, String.valueOf(tgrs).length());
						}
					}else{
						realTable =  "rychdmb";
					}
					String mes = whlgDao.checkDemand(tempXh, xmdm, realTable);
					if(mes!=null && !mes.equalsIgnoreCase(" ") && !mes.equalsIgnoreCase("")){//学生是否符合条件
						oneRowResult = true;
						errors = mes + "不符合条件,不能审核通过";
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
	 * 浙江机电奖学金，荣誉称号数据导入检测
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
//			String xq = "";//从excel中获得学期			 
			String xmdm = "";//从excel中获得项目代码
			String xmlx = "";//项目类型奖学金，荣誉称号
//			String xn = "";
//			String nd = "";
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//奖学金代码
						xmdm = oneCellContent;
						xmlx = "jxj";
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//荣誉称号代码 
						xmdm = oneCellContent;
						xmlx = "rych";
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//学年
//						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//年度 
//						nd = oneCellContent;
					}
					if ("xq".equalsIgnoreCase(splitMappingOne[selnum][1])) {//学期
//						xq = oneCellContent;
					}	
				}
				boolean tjFlag = ApplyAction.pdStuTjFlag(tempXh, xmdm, xmlx);//对学生申请信息进行条件检测
				if (!tjFlag) {
					oneRowResult = true;
					errors += "该生不符合此奖项的申请条件！";
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
	 * 安徽建工奖学金，荣誉称号数据导入检测
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
				new String[] { "jxjsqxn", "jxjsqnd", "jxjsqxq" });//获取奖学金申请学年，年度，学期
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
//			String xq = "";//从excel中获得学期			 
			String xmdm = "";//从excel中获得项目代码
			String xmlx = "";//项目类型奖学金，荣誉称号
//			String xn = "";
//			String nd = "";
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//奖学金代码
						xmdm = oneCellContent;
						xmlx = "jxj";
					}
					if("rychdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//荣誉称号代码 
						xmdm = oneCellContent;
						xmlx = "rych";
					}
					if("xn".equalsIgnoreCase(splitMappingOne[selnum][1])){//学年
//						xn = oneCellContent;
					}
					if("nd".equalsIgnoreCase(splitMappingOne[selnum][1])){//年度 
//						nd = oneCellContent;
					}
					if ("xq".equalsIgnoreCase(splitMappingOne[selnum][1])) {//学期
//						xq = oneCellContent;
					}	
				}
				boolean tjFlag =ZzsfPjpyAction.chkJxjsqFlags(tempXh, xmdm, xmlx, jxjsqxnnd);//对学生申请信息进行条件检测
				
				if (!tjFlag) {
					oneRowResult = true;
					errors += "该生不符合此奖项的申请条件！";
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
	
	public ArrayList<String[]> shcbysgdzkCheckOfPrise(String filePath,String realTable, String mappingItems, String userType) throws Exception {
		JxjService service = new JxjService();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
			String xmdm = "";//从excel中获得项目代码
			String jd = "";//从excel中获得绩点
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if("jxjdm".equalsIgnoreCase(splitMappingOne[selnum][1])){//奖学金代码
						xmdm = oneCellContent;
					}					
					if("jd".equalsIgnoreCase(splitMappingOne[selnum][1])){//绩点
						jd = oneCellContent;
					}
				}
				String tjFlag = service.chkJxjsqtj(tempXh, jd, xmdm);//条件检测
					
				if (tjFlag != null && "wcj".equalsIgnoreCase(tjFlag)) {//无成绩
					oneRowResult = true;
					errors += "该生没有成绩信息!";
				}
				if (tjFlag != null && "cjbhg".equalsIgnoreCase(tjFlag)) {//成绩不合格
					oneRowResult = true;
					errors += "该生存在不及格成绩信息！";
				}
				if (tjFlag != null && "kqbhg".equalsIgnoreCase(tjFlag)) {//考勤不合格
					oneRowResult = true;
					errors += "该生考勤不合格！";
				}
				if (tjFlag != null && "cjhkbhg".equalsIgnoreCase(tjFlag)) {//存在缓考成绩
					oneRowResult = true;
					errors += "该生有课程缓考！";
				}
				if (tjFlag != null && "jdbhg".equalsIgnoreCase(tjFlag)) {//绩点不合格
					oneRowResult = true;
					errors += "该生绩点不符合评奖标准！";
				}
				if(tjFlag != null && "".equalsIgnoreCase(tjFlag)){//符合条件
					oneRowResult = false;
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
	 * 检测Execl表中的数据与数据库中学号和姓名是否一致
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList<HashMap<String, String>> columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempXh = "";//从excel中获得学号信息
			String tempXm = "";//从excel中获得姓名信息
			
			int xhNum = -1;//记录学号所在列的序号，从0开始
			int xmNum = -1;//记录姓名所在列的序号，从0开始
			for(int colNum=0;colNum<columnNum.size();colNum++){//从excel文件中获取列，检查列中是否含有学号和姓名
				HashMap<String, String> col = columnNum.get(colNum);
				if("学号".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xh".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xhNum = colNum;
				}
				if("姓名".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col"))) || "xm".equalsIgnoreCase(StringUtils.Q2BChange(col.get("col")))){
					xmNum = colNum;
				}
			}
			//开始检测数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "这条记录中包含的错误：";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					tempXm = "";
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("xh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取学号
						tempXh = oneCellContent;
					}
					if("xm".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取姓名
						tempXm = oneCellContent;
					}					
				}
				
				if((tempXh == null) && (xhNum > -1)){//字段对应中没有学号字段，但是excel中有学号列
					tempXh = StringUtils.Q2BChange(row[xhNum]);
				}

				if((tempXm == null || "".equalsIgnoreCase(tempXm)) && (xmNum > -1)){//字段对应中没有姓名字段，但是excel中有姓名列
					tempXm = StringUtils.Q2BChange(row[xmNum]);
				}
				
				String realXh = dao.checkXh(tempXh);				
				if(realXh==null||realXh.equals("")){//学号不存在
					if(realTable != null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "学号为"+tempXh+"的学生不存在";
						}
					}
				}else{
					if(xhNum>-1 && xmNum>-1){//excel表中存在学号和姓名列
						String realXm = dao.checkXh2Xm(tempXh, tempXm);//学号和姓名是否匹配
						if(realXm!=null && !"xsxxb".equalsIgnoreCase(realTable) && !"bks_xsjbxx".equalsIgnoreCase(realTable)){
							oneRowResult = true;
							errors += "学号与姓名不匹配，学号"+tempXh+"对应的姓名是"+realXm;
						}
					}
				}		
				
				//中国地质大学勤工助学酬金发放导入时检测学生勤工助学申请不通过的学生，在导入时不导入
				String xxdm = StandardOperation.getXxdm();
				if("xscjffb".equalsIgnoreCase(realTable)&&xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					String qgzxxh = dao.checkQgzx(tempXh);
					if(qgzxxh==null||qgzxxh.equals("")){//学号不存在，学生没有通过勤工助学审核，不属于勤工助学学生
						if(tempXh != null && !"".equalsIgnoreCase(tempXh)){
							oneRowResult = true;
							errors += "学号为"+tempXh+"的学生不是勤工助学学生无法填报酬金";
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
	 * 检测Execl表中的数据的宿舍编号是否在数据库中存在
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
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempSsbh = "";//从excel中获得宿舍编号信息
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("ssbh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取宿舍编号
						tempSsbh = oneCellContent;
					}				
				}
				String pk = dao.getOneValue("view_ssxx", "ssbh", "ssbh",
						tempSsbh);
				if (Base.isNull(pk)) {
					oneRowResult = true;
					errors += "宿舍编号为：" + tempSsbh + "的寝室不存在存在";
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
	
	public ArrayList<String[]> checkExcelZghExists(String filePath, String realTable, String mappingItems){
		DAO dao = DAO.getInstance();
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}		
		ArrayList<String[]> errorsArray = new ArrayList<String[]>();
		try {
			Sheet sourceSheet = WorkbookParser.getWorkbook(new File(filePath)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(filePath);
			String tempZgh = "";//从excel中获得职工号信息
			//开始检测数据 
			for(int rownum=1;rownum<sourceRowCount;rownum++){//从excel数据的第一行开始(去掉列名行)，对每行数据进行检测
				
				//start 开始遍历一行的各个字段
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				String errors = "";
				boolean oneRowResult = false;
				for(int selnum=0;selnum<splitMappingOne.length;selnum++){//对选中的列进行检测
					//将全角转换成半角
					String oneCellContent = StringUtils.Q2BChange(row[Integer.parseInt(splitMappingOne[selnum][0])]);//获得excel中一行的某个选中列的值
					
					if("zgh".equalsIgnoreCase(splitMappingOne[selnum][1])){//获取职工号
						tempZgh = oneCellContent;
					}				
				}
				String pk = dao.getOneValue("fdyxxb", "zgh", "zgh",
						tempZgh);
				if (Base.isNull(pk)) {
					oneRowResult = true;
					errors += "职工号为'" + tempZgh + "'的辅导员不存在";
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
}
