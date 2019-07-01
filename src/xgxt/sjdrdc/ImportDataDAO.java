package xgxt.sjdrdc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import ws.SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient;
import ws.SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.scjz.PjpyScjzDAO;
import xgxt.utils.Arrays2;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 数据导入DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class ImportDataDAO {
	DAO dao = DAO.getInstance();
	PjpyScjzDAO mydao = PjpyScjzDAO.getInstance();
	/**
	 * 返回相应表的中英文对照列表
	 * @param tableName
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getTableColumnList(String tableName,String[] columns){
		String sql = "";
		StringBuffer sb = new StringBuffer();	
		
		//组合字段名称 start
		for(int i=0; i<columns.length; i++){
			sb.append(columns[i] + ",");
		}
		sb.deleteCharAt(sb.length()-1);//将最后的","号去除
		//组合字段名称 end
		
		sql = "select " + sb + " from " + tableName;
		String[] colName = dao.getColumnName(sql);
		String[] colNameCN = dao.getColumnNameCN(colName, tableName);
		
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colName.length; i++) {
			map = new HashMap<String, String>();
			map.put("comments", colNameCN[i]);
			map.put("column_name", colName[i]);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 根据英文列名获取中文列名
	 * */
	public String[] getPkNameCN(String tabName,String[] column){
		column = dao.getColumnNameCN(column, tabName);
		return column;
	}
	
	/**
	 * 获取表字段的信息
	 * @param realTable
	 * @return HashMap<String,  String[]> 字段名为key 字段信息为value[DATA_TYPE,DATA_LENGTH,NULLABLE,PK]
	 * */
	public HashMap<String,  String[]> getTableColumnsAttributes(String realTable){
		HashMap<String,  String[]> map = new HashMap<String,  String[]>();
		HashMap<String,  String> tmpMap = new HashMap<String,  String>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] output = {"column_name","data_type","data_length","nullable","data_default"};
		String sql =  "SELECT a.column_name,a.data_type,a.data_length,a.nullable" +
		               ",data_default FROM user_tab_columns a WHERE a.table_name=upper(?)";	
		
		list = dao.getList(sql, new String[]{realTable},output);
		//将获取的类型为HashMap<String, String>的转换成为HashMap<String, String[]>类型
		for(int i=0;i<list.size(); i++){
			tmpMap = (HashMap<String, String>)list.get(i);
			map.put(tmpMap.get("column_name"), new String[]{tmpMap.get("data_type"),tmpMap.get("data_length"),
			tmpMap.get("nullable"),tmpMap.get("pk"),tmpMap.get("data_default")});
		}
		return map;
	}
	
	/**
	 * 获取一个表的主键字段
	 * @param tableName
	 * @return String[]
	 * @throws SQLException 
	 * */
	public String[] getPkOfTable(String tableName) throws SQLException{
		String[] pkList = null;
		//查询表的主键
		String sql = "select a.column_name pk,'' pk2 from user_cons_columns a,user_constraints b where a.table_name=b.table_name and a.constraint_name=b.constraint_name and b.constraint_type='P' and a.table_name=?";	
		pkList = dao.getArray(sql, new String[]{tableName.toUpperCase()}, "pk");
		return pkList;
	}
	
	/**
	 * 保存导入的数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回违反唯一性约束的数据
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,String[][] compArr,String realTable,String xxdm){
		ArrayList<String[]> result = new ArrayList<String[]>();
		int xhInd = 10000;//学号位置
		int xnInd = 10000;//学年位置
		int ndInd = 10000;//年度位置
		int jxjdmInd = 10000;//奖学金代码位置
		int rychdmInd = 10000;//荣誉称号代码位置
		int bjdmInd = 10000;//班级代码位置
		int dcjInd = 10000;
		int zcjInd = 10000;
		int tcjInd = 10000;
		String errors = "";
		try{
			//组合sql语句  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//组合sql语句的前半部分
			StringBuffer suffSqlPart = new StringBuffer(" values(");//组合sql语句的后半部分
			for(int num=0;num<compArr.length;num++){
				xhInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xh") ? num : xhInd;
				xnInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xn") ? num : xnInd;
				ndInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("nd") ? num : ndInd;
				jxjdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("jxjdm") ? num : jxjdmInd;
				rychdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("rychdm") ? num : rychdmInd;
				bjdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("bjdm") ? num : bjdmInd;
				dcjInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("dcj") ? num : dcjInd;
				zcjInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("zcj") ? num : zcjInd;
				tcjInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("tcj") ? num : tcjInd;
				preSqlPart.append(compArr[num][1]);
				
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//整合两部分sql
			//组合sql语句  end
			String[] inputData = null;
			for(int i=0; i<excelData.size();i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copyShort(tempRow, oneRow, oneRow.length-1, 1, 0);
				
				try{
					inputData = new String[compArr.length];
					for(int num=0;num < inputData.length;num++){
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("cj")) {
							inputData[num] = chgZwtoNum(oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())]);
						} else {
							inputData[num] = oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())];
						}
						
					}
					dao.runUpdate2(preSqlPart.toString(), inputData);	
					if(realTable != null && realTable.equalsIgnoreCase("zhszcp") && xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
						String[] values= {inputData[xhInd],inputData[xnInd],inputData[ndInd]};
						updateZhszcpzf(values);// 计算综合素质测评总分
					}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && realTable.equalsIgnoreCase("ynys_zhszcpb")) {
						String[] values= {inputData[xhInd],inputData[xnInd]};
						updateZhszzfByYnys(values);// 计算综合素质测评总分
					} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
							&& ("xsjxjb".equalsIgnoreCase(realTable) || "xsrychb"
									.equalsIgnoreCase(realTable))) {
						String[] values = null;
						if ("xsrychb".equalsIgnoreCase(realTable)) {
							values = new String[]{inputData[xhInd],inputData[xnInd], inputData[rychdmInd]};
						} else {
							values = new String[]{inputData[xhInd],inputData[xnInd], inputData[jxjdmInd]};
						}
						updateJxjbzxx(values,realTable);//计算奖学金备注信息
					} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) && "pjpy_xjbjandwmsqb".equalsIgnoreCase(realTable)) {
						String[] values = new String[]{inputData[xnInd],inputData[bjdmInd], inputData[rychdmInd]};
						updateXjbjbz(values);//中国美术先进班级修改备注信息
					} else if ("zhszcp".equalsIgnoreCase(realTable)) {
						String[] values = new String[]{inputData[xhInd],inputData[xnInd],inputData[ndInd],inputData[dcjInd],inputData[zcjInd], inputData[tcjInd]};
						updateZhszcpZf(values);
					}
				}catch(SQLException e){
					e.printStackTrace();
					result.add(getSQLErrorMsg(e.getMessage(), tempRow));//把有问题的数据返回
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 保存导入的数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回违反唯一性约束的数据
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		String xxdm = StandardOperation.getXxdm();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String ksh = "";
		try{
			//组合sql语句  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//组合sql语句的前半部分
			StringBuffer suffSqlPart = new StringBuffer(" values(");//组合sql语句的后半部分
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//整合两部分sql
			//组合sql语句  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//厦门理工的学生信息webservice字符串
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length];
					for (int num = 0; num < inputData.length; num++) {
						if (compArr[num][1].toLowerCase()
								.equalsIgnoreCase("cj")) {
							inputData[num] = chgZwtoNum(oneRow[Integer
									.parseInt(compArr[num][0])]);
						} else {
							inputData[num] = oneRow[Integer
									.parseInt(compArr[num][0])];
							if (compArr[num][1] != null
									&& "ksh".equalsIgnoreCase(compArr[num][1]
											.toLowerCase())) {
								ksh = oneRow[Integer.parseInt(compArr[num][0])];
							}
						}
					}
					flag = dao.runUpdate2(preSqlPart.toString(), inputData);
					
					if(flag && xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){//厦门理工的学生信息webservice字符串
						xsxxSb.append(ksh.trim()+"!@");
					}
				}catch(Exception e){
					 e.printStackTrace();
					 //封装错误数据
					 result.add(getSQLErrorMsg(e.getMessage(),tempRow));
				}
			}
//			厦门理工学生基本信息临时表导入
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY) && realTable.toLowerCase().equals("xsjbxxlsb") && xsxxSb.toString().trim().length()>0){
				xsxxSb.delete(xsxxSb.length()-2,xsxxSb.length());				
//				WebServiceClientForXmlgxy wsXmlgxy = new WebServiceClientForXmlgxy();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient wsClient = new SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap service = wsClient.getSinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap();				
				String returnStr = service.registerStu(xsxxSb.toString());
				System.out.println("返回值" + returnStr);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 计算综合素质测评总分
	 * @param values
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean updateZhszcpzf(String[] values) throws Exception{
		boolean flag = false;
		String[] tempValue = null;
		String jqf =  "";
		String kcjqpfj = "0";//课程加权平均分
		String ddcj = "0";
		String xwcj = "0";
		String shqcj = "0";
		String zhszcpzf = "0";
		String sql = "select trunc((sum(zxf) / sum(xf)),2) jqf from (select distinct a.xn,a.xh,a.kcmc," +
		"to_number(a.cj) cj,a.xf,trunc((cj * a.xf),2) zxf  from cjb a  " +
		"where a.bkcj is null and a.cxcj is null and (a.cxbj is null or a.cxbj = '0') " +
		"and a.kcxz <> '校级选修课'  and a.kcxz <> '校际选修课' and a.xh = ? and a.xn = ?) " +
		"group by xh, xn";
		
		jqf = dao.getOneRs(sql, new String[]{values[0],values[1]}, "jqf");
		jqf = jqf == null || jqf.equalsIgnoreCase("") ? "0" : jqf;
		kcjqpfj = jqf;
		
		sql = "select ddcj,xwcj,shqcj from zhszcp where xh=? and xn=? and nd=?";
		
		tempValue = dao.getOneRs(sql, values, new String[]{"ddcj","xwcj","shqcj"});
		ddcj = tempValue[0] == null || tempValue.equals("") ? "0" : tempValue[0];
		xwcj = tempValue[1] == null || tempValue.equals("") ? "0" : tempValue[1];
		shqcj = tempValue[2] == null || tempValue.equals("") ? "0" : tempValue[2];
		
		zhszcpzf = (Float.parseFloat(ddcj) + Float.parseFloat(xwcj) + Float.parseFloat(shqcj) + Float.parseFloat(kcjqpfj)*75/100)+""; 
		zhszcpzf = dao.getOneRs("select trunc("+zhszcpzf+") zhszcpzf from dual", new String[]{}, "zhszcpzf");		
		sql = "update zhszcp set zhszcpzf ='" + zhszcpzf + "' , kcjqpfj='"+kcjqpfj+"' where xh=? and xn=? and nd=?";
		dao.runUpdate(sql, values);
		return flag;
	}
	
	/**
	 * 云南艺术计算综合测评总分
	 * @param values
	 * @throws Exception
	 */
	public void updateZhszzfByYnys(String[] values) throws Exception {
		String sql = "select sxzzddszf,kxwhszf,sxlxszf,sjlxcxf,zhszcpzf"
				+ " from ynys_zhszcpb where xh=? and xn=?";
		String[] tmpVal = dao.getOneRs(sql, values, new String[] { "sxzzddszf",
				"kxwhszf", "sxlxszf", "sjlxcxf", "zhszcpzf" });
		double sxzzf = 0.00;
		double kxwhf = 0.00;
		double sxlxf = 0.00;
		double sjlxf = 0.00;
		if (tmpVal != null && tmpVal.length == 5) {
			sxzzf = StringUtils.isNull(tmpVal[0]) ? 0.00 : Double.parseDouble(tmpVal[0]);
			kxwhf = StringUtils.isNull(tmpVal[1]) ? 0.00 : Double.parseDouble(tmpVal[1]);
			sxlxf = StringUtils.isNull(tmpVal[2]) ? 0.00 : Double.parseDouble(tmpVal[2]);
			sjlxf = StringUtils.isNull(tmpVal[3]) ? 0.00 : Double.parseDouble(tmpVal[3]);
		}
		String zf = "";
		zf = "" + (sxzzf * 20 / 100 + kxwhf * 50 / 100 + sxlxf * 10 / 100 + sjlxf * 20 / 100);
		zf = dao.getOneRs("select trunc("+zf+",2) zf from dual", new String[]{}, "zf");
		dao.runUpdate(
				"update ynys_zhszcpb set zhszcpzf = ? where xh= ? and xn = ?",
				new String[] { zf, values[0], values[1] });
	}
	
	/**
	 * 修改数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回数据库中没有的数据
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		String errors = "";
		try{
			//组合sql语句  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//组合sql语句
			for(int num=0;num<compArr.length;num++){
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//去掉最后一个","号
			
			if(pk!=null && pk.length>0){//加入条件
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//组合sql语句  end
			String[] inputData = null;			
			for(int i=0; i<excelData.size();i++){
				String[] tempRow = excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length+pk.length];
					for(int num=0;num < compArr.length;num++){
						inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//将全角转换成半角后保存
						for(int j=0;j<pk.length;j++){//获取条件语句中的输入参数
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//该字段是主键字段里的某个字段，将值加入到输入参数中
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
							}	
						}
					}					
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(iExecuteRow<1){
						errors = "数据库中不存在此数据，不可修改";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//把数据库中没有的数据返
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//把有问题的数据返回
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 修改数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回数据库中没有的数据
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateData(ArrayList<String[]> excelData,String[][] compArr,String realTable, String xxdm){
	ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		int xhInd = 10000;//学号位置
		int xnInd = 10000;//学年位置
		int ndInd = 10000;//年度位置
		int jxjdmInd = 10000;//奖学金代码位置
		int rychdmInd = 10000;//荣誉称号代码位置
		String errors = "";
		
		@SuppressWarnings("unused")
		String ksh = "";
		try{
			//组合sql语句  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//组合sql语句
			for(int num=0;num<compArr.length;num++){
				xhInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xh") ? num : xhInd;
				xnInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xn") ? num : xnInd;
				ndInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("nd") ? num : ndInd;
				jxjdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("jxjdm") ? num : jxjdmInd;
				rychdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("rychdm") ? num : rychdmInd;
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//去掉最后一个","号
			
			if(pk!=null && pk.length>0){//加入条件
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//组合sql语句  end
			String[] inputData = null;
			for(int i = 0; i <  excelData.size(); i++){
				String[] tempRow = excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length+pk.length];
					for(int num=0;num < compArr.length;num++){
						inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())]);//将全角转换成半角后保存
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("cj")) {
							inputData[num] = chgZwtoNum(inputData[num]);
						}
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("ksh")) {
							ksh = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
						}
						for(int j=0;j<pk.length;j++){//获取条件语句中的输入参数
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//该字段是主键字段里的某个字段，将值加入到输入参数中
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())]);
								/*if (compArr[num][1].toLowerCase().equalsIgnoreCase("cj")) {
									inputData[compArr.length+j] = chgZwtoNum(inputData[compArr.length+j]);
								}*/
							}	
						}
					}
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && realTable.equalsIgnoreCase("zhszcp")){
						//宁波理工
						String[] values= {inputData[xhInd],inputData[xnInd],inputData[ndInd]};
						updateZhszcpzf(values);
					}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && realTable.equalsIgnoreCase("ynys_zhszcpb")) {
						String[] values= {inputData[xhInd],inputData[xnInd]};
						updateZhszzfByYnys(values);
					} 
					if(iExecuteRow<1){
						errors = "数据库中不存在此数据，不可修改";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//把数据库中没有的数据返回						
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//把有问题的数据返回
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取导入的字段
	 * @param tableName
	 * @param xxdm
	 * @return String[]
	 * @throws SQLException 
	 * */
	public String[] getDiffentColumn(String tableName,String xxdm) throws SQLException{
		String[] values = null;
//		int iCount = 0;
		String sql = "";
		/**
		String sql = "select count(*) count from drb where zdssb=? and xxdm=?";
		iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{tableName,xxdm}, "count"));
		if(iCount>0){//在drb中存在表字段
			sql = "select count(*) count from drb where zdssb=? and xxdm=?";
			iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{tableName,xxdm}, "count"));
			if(iCount>0){//数据库中有学校的记录的取属于该学校的字段
				sql = "select zdmc from drb where zdssb=? and xxdm=?";
				values = dao.getArray(sql, new String[]{tableName,xxdm}, "zdmc");
			}else{//数据库中没有属于此学校记录的取通用的字段(xxdm为空的为通用的)
				sql = "select count(*)count from drb where zdssb=? and xxdm is null";
				iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{tableName}, "count"));
				if(iCount>0){// 有通用的
					sql = "select zdmc from drb where zdssb=? and xxdm is null";
					values = dao.getArray(sql, new String[]{tableName}, "zdmc");
				}else{//无通用的取表的默认字段
					sql = "select * from " + tableName;
					values = dao.getColumnName(sql);
				}
			}
		}else{//在drb中没有记录，就直接取表字段
			sql = "select * from " + tableName;
			values = dao.getColumnName(sql);		
		}*/
		sql = Excel2Oracle.getSqlColumn(xxdm,tableName);
		values = dao.getColumnName(sql);
		return values;
	}
	
	/**
	 * 学号是否存在判断
	 * @param xh   从excel数据获得的学号
	 * @param xm   从excel数据获得的姓名
	 * @return
	 */
	public String checkXh(String xh){
		String sql = "select xh from view_xsjbxx where xh=?";
		String xmStr = dao.getOneRs(sql, new String[]{ xh }, "xh");
		boolean result = (xmStr != null) && xmStr.replace(" ", "").equalsIgnoreCase("") ? true : false;
		if(result) return null;
		return xmStr;
	}
	
	
	/**
	 * 学生信息导入时学号是否重复判断
	 * @param xh   从excel数据获得的学号
	 * @param xm   从excel数据获得的姓名
	 * @return
	 */
	public boolean checkXhRepeat(String xh,String realTable){
		String sql = "select xh from " + realTable + " where xh=?";
		String xhStr = dao.getOneRs(sql, new String[]{ xh }, "xh");
		//xhStr = xhStr == null ? "" : xhStr;
		xhStr = StringUtils.Q2BChange(xhStr);
		boolean result = (xhStr != null) && xhStr.replace(" ", "").equalsIgnoreCase(xh) ? true : false;		
		return result;
	}
	
	/**
	 * 学号跟姓名的对照，是否跟数据中心的相同.如果两个姓名是相同，就返回空，否则就返回数据中心的学生姓名
	 * @param xh   从excel数据获得的学号
	 * @param xm   从excel数据获得的姓名
	 * @return
	 */
	public String checkXh2Other(String xh,HashMap<String, String> eMap){
		boolean result = false;
		String value = "";
		String sql = "select xm, sfzh from view_xsjbxx where xh=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{ xh }, new String[]{"xm", "sfzh"});
		for(String key : eMap.keySet()){
			if(StringUtils.isNotNull(map.get(key))){
				if(map.get(key).equalsIgnoreCase(eMap.get(key))){
					result = true;				
				}else{
					value = map.get(key);
				}
			} else {
				value = "空";
			}
		}
		
		if(result){
			value = null;
		}
		return value;
	}
	
	/**
	 * 将成绩转换其对应分数
	 * @param cj
	 * @return
	 * @throws Exception
	 */
	public static String chgZwtoNum(String cj) throws Exception {
		if (StringUtils.isNull(cj)) {
			return "0";
		} else {
			cj = cj.trim();
			try {
				Float.parseFloat(cj);
			} catch(Exception ex){
				if (StringUtils.isEqual(cj, "优秀")) {
					return "85";
				} else if (StringUtils.isEqual(cj, "合格")) {
					return "75";
				} else if (StringUtils.isEqual(cj, "不合格")) {
					return "45";
				}else if (StringUtils.isEqual(cj, "及格")) {
					return "60";
				} else if (StringUtils.isEqual(cj, "不及格")) {
					return "45";
				} else if (StringUtils.isEqual(cj, "良")) {
					return "80";
				} else if (StringUtils.isEqual(cj, "中")) {
					return "70";
				} else {
					return "0";
				}
			}
			return cj;
		}
	}
	
	/**
	 * 将bks_xsjbxx中存在而xsxxb中不存在的数据添加到xsxxb中
	 * */
	public boolean synchXsxx(){
		boolean flag = false;
		try {
			flag = dao.runUpdate("insert into xsxxb(xh,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,bz,xmpy,syd,csrq,sfzh,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb," +
					"rxfs,pyfs,pycc,xjlb,xjztm,xm,xb)(select xh,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,bz,xmpy,syd,csrq,sfzh,mz,zzmm,lxdh,dzyx," +
					"cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xjztm,xm,xb from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh) and xydm is not null and bjdm is not null and zydm is not null)", 
					new String[]{});
		} catch (Exception e) {
			// TODO 自动生成 catch 块			
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 中国美术导入数据时计算奖学金备注信息
	 * @throws Exception
	 */
	public void updateJxjbzxx(String[] values, String realTable) throws Exception {
		String xh = "";
		String xn = "";
		String jxjdm = "";
		if (values != null && values.length==3) {
			xh = values[0];
			xn = values[1];
			jxjdm = values[2];
		}
		if ("xsjxjb".equalsIgnoreCase(realTable)) {
			updateJxjbz(xh, xn, jxjdm, "xsjxjb", "jxjdm");
		} else {
			updateJxjbz(xh, xn, jxjdm, "xsrychb", "rychdm");
		}
	}
	
	/**
	 * 中国美术奖学金申请备注信息中增加旷课,违纪,成绩信息
	 * @param xh
	 * @param xn
	 * @throws Exception
	 */
	public void updateJxjbz(String xh, String xn, String jxjdm, String tableName, String zd) throws Exception {
		DAO dao = DAO.getInstance();
		String bz = "";
		HashMap<String, String> tmpBz = dao
		.getMapNotOut(
				"select (select count(*) wjrs from view_wjcf a where xn=? and xh=?) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and xh=?) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and xh=? and tydb like '%不及格%') tyrs,(select count(*) bkrs from cjb where xn=? and xh=? and (bkcj2 is not null or cxcj2 is not null)) bkrs from dual",
				new String[] { xn, xh, xn, xh, xn,
						xh,xn,xh });
		if (!StringUtils.isNull(tmpBz.get("wjrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("wjrs"))) {//是否有违纪
			bz += xn + "学年该生受过处分,\n" ;		
		} else {
			bz += xn + "学年该生未受处分,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("kkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("kkrs"))) {//是否有旷课
			bz += xn + "学年该生有旷课记录,\n" ;		
		} else {
			bz += xn + "学年该生无旷课记录,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("tyrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("tyrs"))) {//是否有体育未达标
			bz += xn + "学年该生体育未达标,\n" ;		
		} else {
			bz += xn + "学年该生体育全部达标,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("bkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("bkrs"))) {//是否有体育未达标
			bz += xn + "学年该生成绩有补考记录:\n" ;	
			List<String[]> bkcjList = dao
				.rsToVator(
						"select xn,xq,kcmc,kclx,zpcj2,bkcj2,cxcj2 from cjb where xh=? and xn=? and (bkcj2 is not null or cxcj2 is not null)",
						new String[] { xh, xn },
						new String[] { "xn", "xq", "kcmc",
								"kclx", "zpcj2", "bkcj2",
								"cxcj2" });
			
			for (int i=0;i<bkcjList.size();i++) {
				String[] tp = bkcjList.get(i);
				bz += getBzs(tp);
			}
		} else {
			bz += xn + "学年该生成绩无补考记录.\n";
		}
		dao.runUpdate("update "+tableName+" set bz=? where xh=? and xn=? and "+zd+"=?", new String[]{bz, xh, xn, jxjdm});
	}
	
	public String getBzs(String[] bzList) throws Exception {
		String bz = "";
		if (bzList != null && bzList.length==7) {
			if (!StringUtils.isNull(bzList[0])) {
				bz += bzList[0] + "学年第";
			}
			if (!StringUtils.isNull(bzList[1])) {
				bz += bzList[1] + "学期 ";
			}
			if (!StringUtils.isNull(bzList[2])) {
				bz += " 课程("+bzList[2]+")";
			}
			if (!StringUtils.isNull(bzList[3])) {
				bz += " 类型("+bzList[3]+")";
			}
			if (!StringUtils.isNull(bzList[4])) {
				bz += " 成绩("+bzList[4]+")";
			}
			if (!StringUtils.isNull(bzList[5])) {
				bz += " 补考成绩("+bzList[5]+")";
			}
			if (!StringUtils.isNull(bzList[6])) {
				bz += " 重修成绩("+bzList[6]+").";
			}
			bz += "\n";
		}
		return bz;
	}
	
	/**
	 * 中国美术修改先进班级备注信息
	 * @param values
	 * @throws Exception
	 */
	public void updateXjbjbz(String[] values) throws Exception {
		DAO dao = DAO.getInstance();
		if (values != null) {
			String xn = Base.getJxjsqxn();
			String bjdm = values[1];
			String bz = "";//中国美术用于记录班级违纪,旷课,体育信息
			HashMap<String, String> tmp = dao.getMapNotOut(
							"select (select count(*) wjrs from view_wjcf a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and bjdm = ? and tydb like '%不及格%' and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) tyrs from dual",
							new String[] { xn, bjdm, xn, bjdm, xn,
									bjdm }); 
			if (!StringUtils.isNull(tmp.get("wjrs"))
					&& !"0".equalsIgnoreCase(tmp.get("wjrs"))) {//是否有违纪
				bz += xn + "学年班级学生受处分:" + tmp.get("wjrs") + "人.\n" ;		
			} else {
				bz += xn + "学年班级无学生受处分,\n";
			}
			if (!StringUtils.isNull(tmp.get("kkrs"))
					&& !"0".equalsIgnoreCase(tmp.get("kkrs"))) {//是否有旷课
				bz += xn + "学年班级学生旷课:" + tmp.get("kkrs") + "人.\n" ;		
			} else {
				bz += xn + "学年班级无学生旷课,\n";
			}
			if (!StringUtils.isNull(tmp.get("tyrs"))
					&& !"0".equalsIgnoreCase(tmp.get("tyrs"))) {//是否有体育未达标
				bz += xn + "学年班级学生体育未达标:" + tmp.get("tyrs") + "人." ;		
			} else {
				bz += xn + "学年班级学生体育全部达标.";
			}
			dao.runUpdate("update pjpy_xjbjandwmsqb set bz = ? where bjdm = ? and xn=? and rychdm=?",
							new String[] { bz, bjdm, xn, values[2] });
		
		}
	}
	
	/**
	 * 通用的导入综合素质分时自动计算总分
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpZf(String[] values) throws Exception {
		boolean flag = false;
		String zf = "";
		if (values != null && values.length==6) {
			zf = mydao.countZf(values[3], values[4], values[5]);
			dao.runUpdate("update zhszcp set zpf=? where xh=? and xn=? and nd=?", new String[]{zf,values[0],values[1],values[2]});
		}
		return flag;
	}
	
	/**
	 * 学号判断学生是否通过勤工助学审核
	 * @param xh   从excel数据获得的学号
	 * @param xm   从excel数据获得的姓名
	 * @return
	 */
	public String checkQgzx(String xh){
		String sql = "select xh from qgzxsqb where xh=? and xxsh='通过'";
		String xmStr = dao.getOneRs(sql, new String[]{ xh }, "xh");
		boolean result = (xmStr != null) && xmStr.replace(" ", "").equalsIgnoreCase("") ? true : false;
		if(result) return null;
		return xmStr;
	}

	public boolean checkXhComments(String realTable) {
		// 检测学号在数据库中的注释是否为'序号'
		String sql = "select COMMENTS from user_col_comments where COLUMN_NAME='XH' and TABLE_NAME=upper(?)";
		String XhComment = dao.getOneRs(sql, new String[] { realTable },
				"COMMENTS");
		if (StringUtils.isNotNull(XhComment)
				&& XhComment.trim().equalsIgnoreCase("序号")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 保存卫生分导入的数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回违反唯一性约束的数据
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveWsfData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		String xxdm = StandardOperation.getXxdm();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String error = "";
		try{
			//组合sql语句  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//组合sql语句的前半部分
			StringBuffer suffSqlPart = new StringBuffer(" values(");//组合sql语句的后半部分
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//整合两部分sql
			//组合sql语句  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//厦门理工的学生信息webservice字符串
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length];
					for(int num=0;num < inputData.length;num++){
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("iszds")) {
							String iszds = oneRow[Integer.parseInt(compArr[num][0])];
							inputData[num] = "是".equalsIgnoreCase(iszds) ? "checked" : iszds;
						} else {
							inputData[num] = oneRow[Integer.parseInt(compArr[num][0])];
						}
					}
					flag = dao.runUpdate2(preSqlPart.toString(), inputData);
					
				}catch(Exception e){
					//封装错误数据
					e.printStackTrace();
					result.add(getSQLErrorMsg(e.getMessage(),tempRow));
				}
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改卫生分数据
	 * @param excelData       excel中各行的数据
	 * @param compArr         excel中与数据库中表的对应关系
	 * @return ArrayList<String[]> 返回数据库中没有的数据
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateWsfData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		String errors = "";
		try{
			//组合sql语句  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//组合sql语句
			for(int num=0;num<compArr.length;num++){
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//去掉最后一个","号
			
			if(pk!=null && pk.length>0){//加入条件
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//组合sql语句  end
			String[] inputData = null;			
			for(int i=0; i<excelData.size();i++){
				String[] tempRow = excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length+pk.length];
					for(int num=0;num < compArr.length;num++){
						if ("iszds".equalsIgnoreCase(compArr[num][1].toLowerCase())) {
							String iszds = oneRow[Integer.parseInt(compArr[num][0])];
							inputData[num] = "是".equalsIgnoreCase(iszds) ? "checked" : iszds;
							//inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//将全角转换成半角后保存
						} else {
							inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//将全角转换成半角后保存
						}
						
						for(int j=0;j<pk.length;j++){//获取条件语句中的输入参数
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//该字段是主键字段里的某个字段，将值加入到输入参数中
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
							}	
						}
					}					
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(iExecuteRow<1){
						errors = "数据库中不存在此数据，不可修改";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//把数据库中没有的数据返
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//把有问题的数据返回
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public  ArrayList<HashMap<String, String>> getColColumn(String tabName, String dzyDdTab) {
		// TODO 自动生成方法存根
		String sql = "select COLUMN_NAME column_name,upper(COMMENTS) comments from user_col_comments  where table_Name " +
				"= upper(?) UNION ALL select ZDID,ZDMC from "+dzyDdTab+" where tabname = ?" ;
		return dao.getArrayList(sql, new String[]{tabName,tabName}, new String[]{"comments","column_name"});
	}
	
	public  ArrayList<HashMap<String, String>> getColColumn(String tabName, String viewName, String dzyDdTab) {
		// TODO 自动生成方法存根
		String sql = StringUtils.joinStr("select distinct column_name,comments from(",
				"select COLUMN_NAME column_name,upper(COMMENTS) comments ",
				"from user_col_comments  where table_Name = upper(?) ",
				"UNION ALL ",
				"select upper(ZDID),ZDMC from ",
				dzyDdTab,
				" b where tabname = ? ",
				"and not exists(select 1 from user_col_comments a where a.column_name=upper(b.zdid) and a.table_Name = upper(?)))") ;
		return dao.getArrayList(sql, new String[]{tabName,viewName,tabName}, new String[]{"comments","column_name"});
	}
	
	/**
	 * 学号跟姓名的对照，是否跟数据中心的相同.如果两个姓名是相同，就返回空，否则就返回数据中心的学生姓名
	 * @param xh   从excel数据获得的学号
	 * @param xm   从excel数据获得的姓名
	 * @return
	 */
	public String checkXh2Xm(String xh,String xm){
		String sql = "select xm from view_xsjbxx where xh=?";
		String xmStr = dao.getOneRs(sql, new String[]{ xh }, "xm");
		xmStr = StringUtils.Q2BChange(xmStr);
//		return (xmStr != null) && xmStr.trim().equalsIgnoreCase(xm != null ? xm.trim() : "") ? true : false;
		boolean result = (xmStr != null) && xmStr.replace(" ", "").equalsIgnoreCase(xm) ? true : false;
		if(result) return null;
		return xmStr;
	}
	
	
	
	
	public ArrayList<String[]> saveZdyData(ArrayList<String[]> excelData, String[][] compArr, String realTable, String saveTable, String[] zdids) throws SQLException {
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		//主键字段
		String [] pkCol = CommonQueryDAO.GetPkCol(realTable);
		//主键对应字段在导入数据中的序号
		int [] pkColIndex = new int[pkCol.length];
		//取实际插入的字段集
		String[] insertColList = new String[compArr.length];
		int insertNum = 0;
		for(int i = 0;i<compArr.length;i++){
			for(int j = 0;j<pkCol.length;j++){
					if(compArr[i][1].equalsIgnoreCase(pkCol[j])){
						pkColIndex[j] = i;
					}
			}
			
			if(!StringUtils.stringExistArray(compArr[i][1], zdids)){
				insertColList[insertNum] = compArr[i][1];
				insertNum++;
			}
		}
		
		try{
			//组合sql语句  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//组合sql语句的前半部分
			StringBuffer suffSqlPart = new StringBuffer(" values(");//组合sql语句的后半部分
			for(int num=0;num<insertColList.length;num++){	
				if(insertColList[num]!=null){
				preSqlPart.append(insertColList[num]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
				}
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//整合两部分sql
			//组合sql语句  end
			String[] inputData = null;
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				//				主键的值，用于自定义表单的主键插入
				String pkValue= "";
				try{
					inputData = new String[compArr.length];
					ArrayList<String> inputList = new ArrayList<String>();
					String [] zdyDdsqlMapF = new String [zdids.length];
					String [] zdyDdsqlMapAll = new String [zdids.length];
					String [] zdyDdsqlMapT = new String [zdids.length];
					int sqlMapIndex = 0;
					for(int num=0;num < compArr.length;num++){
							
							if(StringUtils.stringExistArray(compArr[num][1], zdids)){
								if("py_bdsz_bcnr".equalsIgnoreCase(saveTable)){
									realTable = "view_" + realTable;//视图名称
								}
								zdyDdsqlMapF[sqlMapIndex]="insert into "+saveTable+ "(zdid,tabname,zbid,bcnr) values ('"+compArr[num][1]+"','"+realTable+"','";
								zdyDdsqlMapT[sqlMapIndex]="','"+oneRow[Integer.parseInt(compArr[num][0])]+"')";
								sqlMapIndex++;
							}else{
								inputData[num] = oneRow[Integer.parseInt(compArr[num][0])];
								if(oneRow[Integer.parseInt(compArr[num][0])] != null){
									inputList.add(oneRow[Integer.parseInt(compArr[num][0])]);
								}
							}
					}
					
					
					if ("rcsw_lpwh".equals(realTable) ||
						"xsh_hdxx".equals(realTable)||
						"xsh_xcxx".equals(realTable)||
						"rcsw_bxwh".equals(realTable)||
						"xsh_stglb".equals(realTable)) {
						for(int k=0;k<pkColIndex.length;k++){
							pkValue += inputData[pkColIndex[k]];
							if (k !=pkColIndex.length-1 )
								pkValue+="!!@!!";
						}
					} else {
						for(int k=0;k<pkColIndex.length;k++){
							pkValue += inputData[pkColIndex[k]];
						}
						
					}
					
					for(int t = 0;t<zdyDdsqlMapAll.length;t++){
						if(zdyDdsqlMapF[t]!=null){
							zdyDdsqlMapAll[t] = zdyDdsqlMapF[t]+pkValue+zdyDdsqlMapT[t];
						}
					}
					flag = dao.runUpdate2(preSqlPart.toString(), (String[])inputList.toArray(new String[0]));
					if(flag){
						dao.runBatch(zdyDdsqlMapAll);
					}
				}catch(Exception e){
					 e.printStackTrace();
					if(e.getMessage().contains("ORA-00001")){//如果是违反唯一性约束
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "违反唯一约束";
						result.add(tmpRow);//把违反唯一约束的数据返回
					}else if(e.getMessage().contains("ORA-01400")){//无法将 NULL 插入
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "必填字段必须填写";
						result.add(tmpRow);//把数据返回
					}else if(e.getMessage().contains("ORA-00957")){//重复的列名
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "列名有重复";
						result.add(tmpRow);//把数据返回
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String[] getZdyZdString(String dzyDdTab, String tableName) throws SQLException {
		String sql = "select zdid from "+dzyDdTab+" where tabname = ?";
		String [] zdids = dao.getArray(sql, new String[]{tableName}, "zdid");
		return zdids;
	}
	
	/**
	 * 获取sql出错信息
	 * @param errorCode
	 * @param errorData
	 * @return String[]
	 * */
	public String[] getSQLErrorMsg(String errorCode, String[] errorData){
		String errorMsg = "";
		if(errorCode.contains("ORA-00001")){//如果是违反唯一性约束
			errorMsg = "记录已经存在";
		}else if(errorCode.contains("ORA-01400")){//无法将 NULL 插入
			errorMsg = "不可为空的信息必需填写";
		}else if(errorCode.contains("ORA-00957")){//重复的列名
			errorMsg = "部分信息有重复";
		}else if(errorCode.contains("ORA-01722")){//非数字
			errorMsg = "部分信息必须输入数字";
		}
		return StringUtils.joinStrArr(new String[]{errorMsg},errorData);//把结果集返回		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-16 下午04:30:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelData
	 * @param splitMappingOne
	 * @param model
	 * @return
	 * ArrayList<String[]> 返回类型 
	 * @throws 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,
			String[][] compArr, ExportAndImportModel model) {
		// TODO 自动生成方法存根

		String xxdm = StandardOperation.getXxdm();
		String realTable=model.getRealTable();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String ksh = "";
		try{
			//组合sql语句  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(nf,yf,");//组合sql语句的前半部分
			StringBuffer suffSqlPart = new StringBuffer(" values('"+model.getNf()+"','"+model.getYf()+"',");//组合sql语句的后半部分
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//整合两部分sql
			//组合sql语句  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//厦门理工的学生信息webservice字符串
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length];
					for (int num = 0; num < inputData.length; num++) {
						if (compArr[num][1].toLowerCase()
								.equalsIgnoreCase("cj")) {
							inputData[num] = chgZwtoNum(oneRow[Integer
									.parseInt(compArr[num][0])]);
						} else {
							inputData[num] = oneRow[Integer
									.parseInt(compArr[num][0])];
							if (compArr[num][1] != null
									&& "ksh".equalsIgnoreCase(compArr[num][1]
											.toLowerCase())) {
								ksh = oneRow[Integer.parseInt(compArr[num][0])];
							}
						}
					}
					flag = dao.runUpdate2(preSqlPart.toString(), inputData);
					
					if(flag && xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){//厦门理工的学生信息webservice字符串
						xsxxSb.append(ksh.trim()+"!@");
					}
				}catch(Exception e){
					 e.printStackTrace();
					 //封装错误数据
					 result.add(getSQLErrorMsg(e.getMessage(),tempRow));
				}
			}
//			厦门理工学生基本信息临时表导入
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY) && realTable.toLowerCase().equals("xsjbxxlsb") && xsxxSb.toString().trim().length()>0){
				xsxxSb.delete(xsxxSb.length()-2,xsxxSb.length());				
//				WebServiceClientForXmlgxy wsXmlgxy = new WebServiceClientForXmlgxy();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient wsClient = new SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap service = wsClient.getSinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap();				
				String returnStr = service.registerStu(xsxxSb.toString());
				System.out.println("返回值" + returnStr);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	
	}
}
