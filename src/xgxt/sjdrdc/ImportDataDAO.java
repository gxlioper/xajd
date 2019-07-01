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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݵ���DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class ImportDataDAO {
	DAO dao = DAO.getInstance();
	PjpyScjzDAO mydao = PjpyScjzDAO.getInstance();
	/**
	 * ������Ӧ�����Ӣ�Ķ����б�
	 * @param tableName
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getTableColumnList(String tableName,String[] columns){
		String sql = "";
		StringBuffer sb = new StringBuffer();	
		
		//����ֶ����� start
		for(int i=0; i<columns.length; i++){
			sb.append(columns[i] + ",");
		}
		sb.deleteCharAt(sb.length()-1);//������","��ȥ��
		//����ֶ����� end
		
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
	 * ����Ӣ��������ȡ��������
	 * */
	public String[] getPkNameCN(String tabName,String[] column){
		column = dao.getColumnNameCN(column, tabName);
		return column;
	}
	
	/**
	 * ��ȡ���ֶε���Ϣ
	 * @param realTable
	 * @return HashMap<String,  String[]> �ֶ���Ϊkey �ֶ���ϢΪvalue[DATA_TYPE,DATA_LENGTH,NULLABLE,PK]
	 * */
	public HashMap<String,  String[]> getTableColumnsAttributes(String realTable){
		HashMap<String,  String[]> map = new HashMap<String,  String[]>();
		HashMap<String,  String> tmpMap = new HashMap<String,  String>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] output = {"column_name","data_type","data_length","nullable","data_default"};
		String sql =  "SELECT a.column_name,a.data_type,a.data_length,a.nullable" +
		               ",data_default FROM user_tab_columns a WHERE a.table_name=upper(?)";	
		
		list = dao.getList(sql, new String[]{realTable},output);
		//����ȡ������ΪHashMap<String, String>��ת����ΪHashMap<String, String[]>����
		for(int i=0;i<list.size(); i++){
			tmpMap = (HashMap<String, String>)list.get(i);
			map.put(tmpMap.get("column_name"), new String[]{tmpMap.get("data_type"),tmpMap.get("data_length"),
			tmpMap.get("nullable"),tmpMap.get("pk"),tmpMap.get("data_default")});
		}
		return map;
	}
	
	/**
	 * ��ȡһ����������ֶ�
	 * @param tableName
	 * @return String[]
	 * @throws SQLException 
	 * */
	public String[] getPkOfTable(String tableName) throws SQLException{
		String[] pkList = null;
		//��ѯ�������
		String sql = "select a.column_name pk,'' pk2 from user_cons_columns a,user_constraints b where a.table_name=b.table_name and a.constraint_name=b.constraint_name and b.constraint_type='P' and a.table_name=?";	
		pkList = dao.getArray(sql, new String[]{tableName.toUpperCase()}, "pk");
		return pkList;
	}
	
	/**
	 * ���浼�������
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> ����Υ��Ψһ��Լ��������
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,String[][] compArr,String realTable,String xxdm){
		ArrayList<String[]> result = new ArrayList<String[]>();
		int xhInd = 10000;//ѧ��λ��
		int xnInd = 10000;//ѧ��λ��
		int ndInd = 10000;//���λ��
		int jxjdmInd = 10000;//��ѧ�����λ��
		int rychdmInd = 10000;//�����ƺŴ���λ��
		int bjdmInd = 10000;//�༶����λ��
		int dcjInd = 10000;
		int zcjInd = 10000;
		int tcjInd = 10000;
		String errors = "";
		try{
			//���sql���  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//���sql����ǰ�벿��
			StringBuffer suffSqlPart = new StringBuffer(" values(");//���sql���ĺ�벿��
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
			preSqlPart.append(suffSqlPart);//����������sql
			//���sql���  end
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
						updateZhszcpzf(values);// �����ۺ����ʲ����ܷ�
					}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && realTable.equalsIgnoreCase("ynys_zhszcpb")) {
						String[] values= {inputData[xhInd],inputData[xnInd]};
						updateZhszzfByYnys(values);// �����ۺ����ʲ����ܷ�
					} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
							&& ("xsjxjb".equalsIgnoreCase(realTable) || "xsrychb"
									.equalsIgnoreCase(realTable))) {
						String[] values = null;
						if ("xsrychb".equalsIgnoreCase(realTable)) {
							values = new String[]{inputData[xhInd],inputData[xnInd], inputData[rychdmInd]};
						} else {
							values = new String[]{inputData[xhInd],inputData[xnInd], inputData[jxjdmInd]};
						}
						updateJxjbzxx(values,realTable);//���㽱ѧ��ע��Ϣ
					} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) && "pjpy_xjbjandwmsqb".equalsIgnoreCase(realTable)) {
						String[] values = new String[]{inputData[xnInd],inputData[bjdmInd], inputData[rychdmInd]};
						updateXjbjbz(values);//�й������Ƚ��༶�޸ı�ע��Ϣ
					} else if ("zhszcp".equalsIgnoreCase(realTable)) {
						String[] values = new String[]{inputData[xhInd],inputData[xnInd],inputData[ndInd],inputData[dcjInd],inputData[zcjInd], inputData[tcjInd]};
						updateZhszcpZf(values);
					}
				}catch(SQLException e){
					e.printStackTrace();
					result.add(getSQLErrorMsg(e.getMessage(), tempRow));//������������ݷ���
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ���浼�������
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> ����Υ��Ψһ��Լ��������
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		String xxdm = StandardOperation.getXxdm();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String ksh = "";
		try{
			//���sql���  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//���sql����ǰ�벿��
			StringBuffer suffSqlPart = new StringBuffer(" values(");//���sql���ĺ�벿��
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//����������sql
			//���sql���  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//��������ѧ����Ϣwebservice�ַ���
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
					
					if(flag && xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){//��������ѧ����Ϣwebservice�ַ���
						xsxxSb.append(ksh.trim()+"!@");
					}
				}catch(Exception e){
					 e.printStackTrace();
					 //��װ��������
					 result.add(getSQLErrorMsg(e.getMessage(),tempRow));
				}
			}
//			������ѧ��������Ϣ��ʱ����
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY) && realTable.toLowerCase().equals("xsjbxxlsb") && xsxxSb.toString().trim().length()>0){
				xsxxSb.delete(xsxxSb.length()-2,xsxxSb.length());				
//				WebServiceClientForXmlgxy wsXmlgxy = new WebServiceClientForXmlgxy();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient wsClient = new SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap service = wsClient.getSinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap();				
				String returnStr = service.registerStu(xsxxSb.toString());
				System.out.println("����ֵ" + returnStr);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * �����ۺ����ʲ����ܷ�
	 * @param values
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean updateZhszcpzf(String[] values) throws Exception{
		boolean flag = false;
		String[] tempValue = null;
		String jqf =  "";
		String kcjqpfj = "0";//�γ̼�Ȩƽ����
		String ddcj = "0";
		String xwcj = "0";
		String shqcj = "0";
		String zhszcpzf = "0";
		String sql = "select trunc((sum(zxf) / sum(xf)),2) jqf from (select distinct a.xn,a.xh,a.kcmc," +
		"to_number(a.cj) cj,a.xf,trunc((cj * a.xf),2) zxf  from cjb a  " +
		"where a.bkcj is null and a.cxcj is null and (a.cxbj is null or a.cxbj = '0') " +
		"and a.kcxz <> 'У��ѡ�޿�'  and a.kcxz <> 'У��ѡ�޿�' and a.xh = ? and a.xn = ?) " +
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
	 * �������������ۺϲ����ܷ�
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
	 * �޸�����
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> �������ݿ���û�е�����
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		String errors = "";
		try{
			//���sql���  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//���sql���
			for(int num=0;num<compArr.length;num++){
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//ȥ�����һ��","��
			
			if(pk!=null && pk.length>0){//��������
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//���sql���  end
			String[] inputData = null;			
			for(int i=0; i<excelData.size();i++){
				String[] tempRow = excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length+pk.length];
					for(int num=0;num < compArr.length;num++){
						inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//��ȫ��ת���ɰ�Ǻ󱣴�
						for(int j=0;j<pk.length;j++){//��ȡ��������е��������
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//���ֶ��������ֶ����ĳ���ֶΣ���ֵ���뵽���������
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
							}	
						}
					}					
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(iExecuteRow<1){
						errors = "���ݿ��в����ڴ����ݣ������޸�";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//�����ݿ���û�е����ݷ�
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//������������ݷ���
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * �޸�����
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> �������ݿ���û�е�����
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateData(ArrayList<String[]> excelData,String[][] compArr,String realTable, String xxdm){
	ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		int xhInd = 10000;//ѧ��λ��
		int xnInd = 10000;//ѧ��λ��
		int ndInd = 10000;//���λ��
		int jxjdmInd = 10000;//��ѧ�����λ��
		int rychdmInd = 10000;//�����ƺŴ���λ��
		String errors = "";
		
		@SuppressWarnings("unused")
		String ksh = "";
		try{
			//���sql���  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//���sql���
			for(int num=0;num<compArr.length;num++){
				xhInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xh") ? num : xhInd;
				xnInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("xn") ? num : xnInd;
				ndInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("nd") ? num : ndInd;
				jxjdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("jxjdm") ? num : jxjdmInd;
				rychdmInd = compArr[num][1] != null && compArr[num][1].toLowerCase().equalsIgnoreCase("rychdm") ? num : rychdmInd;
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//ȥ�����һ��","��
			
			if(pk!=null && pk.length>0){//��������
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//���sql���  end
			String[] inputData = null;
			for(int i = 0; i <  excelData.size(); i++){
				String[] tempRow = excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length+pk.length];
					for(int num=0;num < compArr.length;num++){
						inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())]);//��ȫ��ת���ɰ�Ǻ󱣴�
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("cj")) {
							inputData[num] = chgZwtoNum(inputData[num]);
						}
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("ksh")) {
							ksh = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
						}
						for(int j=0;j<pk.length;j++){//��ȡ��������е��������
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//���ֶ��������ֶ����ĳ���ֶΣ���ֵ���뵽���������
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(StringUtils.isNull(compArr[num][0]) ? compArr[num][0] : compArr[num][0].trim())]);
								/*if (compArr[num][1].toLowerCase().equalsIgnoreCase("cj")) {
									inputData[compArr.length+j] = chgZwtoNum(inputData[compArr.length+j]);
								}*/
							}	
						}
					}
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && realTable.equalsIgnoreCase("zhszcp")){
						//������
						String[] values= {inputData[xhInd],inputData[xnInd],inputData[ndInd]};
						updateZhszcpzf(values);
					}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && realTable.equalsIgnoreCase("ynys_zhszcpb")) {
						String[] values= {inputData[xhInd],inputData[xnInd]};
						updateZhszzfByYnys(values);
					} 
					if(iExecuteRow<1){
						errors = "���ݿ��в����ڴ����ݣ������޸�";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//�����ݿ���û�е����ݷ���						
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//������������ݷ���
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��ȡ������ֶ�
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
		if(iCount>0){//��drb�д��ڱ��ֶ�
			sql = "select count(*) count from drb where zdssb=? and xxdm=?";
			iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{tableName,xxdm}, "count"));
			if(iCount>0){//���ݿ�����ѧУ�ļ�¼��ȡ���ڸ�ѧУ���ֶ�
				sql = "select zdmc from drb where zdssb=? and xxdm=?";
				values = dao.getArray(sql, new String[]{tableName,xxdm}, "zdmc");
			}else{//���ݿ���û�����ڴ�ѧУ��¼��ȡͨ�õ��ֶ�(xxdmΪ�յ�Ϊͨ�õ�)
				sql = "select count(*)count from drb where zdssb=? and xxdm is null";
				iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{tableName}, "count"));
				if(iCount>0){// ��ͨ�õ�
					sql = "select zdmc from drb where zdssb=? and xxdm is null";
					values = dao.getArray(sql, new String[]{tableName}, "zdmc");
				}else{//��ͨ�õ�ȡ���Ĭ���ֶ�
					sql = "select * from " + tableName;
					values = dao.getColumnName(sql);
				}
			}
		}else{//��drb��û�м�¼����ֱ��ȡ���ֶ�
			sql = "select * from " + tableName;
			values = dao.getColumnName(sql);		
		}*/
		sql = Excel2Oracle.getSqlColumn(xxdm,tableName);
		values = dao.getColumnName(sql);
		return values;
	}
	
	/**
	 * ѧ���Ƿ�����ж�
	 * @param xh   ��excel���ݻ�õ�ѧ��
	 * @param xm   ��excel���ݻ�õ�����
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
	 * ѧ����Ϣ����ʱѧ���Ƿ��ظ��ж�
	 * @param xh   ��excel���ݻ�õ�ѧ��
	 * @param xm   ��excel���ݻ�õ�����
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
	 * ѧ�Ÿ������Ķ��գ��Ƿ���������ĵ���ͬ.���������������ͬ���ͷ��ؿգ�����ͷ����������ĵ�ѧ������
	 * @param xh   ��excel���ݻ�õ�ѧ��
	 * @param xm   ��excel���ݻ�õ�����
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
				value = "��";
			}
		}
		
		if(result){
			value = null;
		}
		return value;
	}
	
	/**
	 * ���ɼ�ת�����Ӧ����
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
				if (StringUtils.isEqual(cj, "����")) {
					return "85";
				} else if (StringUtils.isEqual(cj, "�ϸ�")) {
					return "75";
				} else if (StringUtils.isEqual(cj, "���ϸ�")) {
					return "45";
				}else if (StringUtils.isEqual(cj, "����")) {
					return "60";
				} else if (StringUtils.isEqual(cj, "������")) {
					return "45";
				} else if (StringUtils.isEqual(cj, "��")) {
					return "80";
				} else if (StringUtils.isEqual(cj, "��")) {
					return "70";
				} else {
					return "0";
				}
			}
			return cj;
		}
	}
	
	/**
	 * ��bks_xsjbxx�д��ڶ�xsxxb�в����ڵ�������ӵ�xsxxb��
	 * */
	public boolean synchXsxx(){
		boolean flag = false;
		try {
			flag = dao.runUpdate("insert into xsxxb(xh,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,bz,xmpy,syd,csrq,sfzh,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb," +
					"rxfs,pyfs,pycc,xjlb,xjztm,xm,xb)(select xh,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,bz,xmpy,syd,csrq,sfzh,mz,zzmm,lxdh,dzyx," +
					"cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xjztm,xm,xb from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh) and xydm is not null and bjdm is not null and zydm is not null)", 
					new String[]{});
		} catch (Exception e) {
			// TODO �Զ����� catch ��			
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * �й�������������ʱ���㽱ѧ��ע��Ϣ
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
	 * �й�������ѧ�����뱸ע��Ϣ�����ӿ���,Υ��,�ɼ���Ϣ
	 * @param xh
	 * @param xn
	 * @throws Exception
	 */
	public void updateJxjbz(String xh, String xn, String jxjdm, String tableName, String zd) throws Exception {
		DAO dao = DAO.getInstance();
		String bz = "";
		HashMap<String, String> tmpBz = dao
		.getMapNotOut(
				"select (select count(*) wjrs from view_wjcf a where xn=? and xh=?) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and xh=?) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and xh=? and tydb like '%������%') tyrs,(select count(*) bkrs from cjb where xn=? and xh=? and (bkcj2 is not null or cxcj2 is not null)) bkrs from dual",
				new String[] { xn, xh, xn, xh, xn,
						xh,xn,xh });
		if (!StringUtils.isNull(tmpBz.get("wjrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("wjrs"))) {//�Ƿ���Υ��
			bz += xn + "ѧ������ܹ�����,\n" ;		
		} else {
			bz += xn + "ѧ�����δ�ܴ���,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("kkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("kkrs"))) {//�Ƿ��п���
			bz += xn + "ѧ������п��μ�¼,\n" ;		
		} else {
			bz += xn + "ѧ������޿��μ�¼,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("tyrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("tyrs"))) {//�Ƿ�������δ���
			bz += xn + "ѧ���������δ���,\n" ;		
		} else {
			bz += xn + "ѧ���������ȫ�����,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("bkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("bkrs"))) {//�Ƿ�������δ���
			bz += xn + "ѧ������ɼ��в�����¼:\n" ;	
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
			bz += xn + "ѧ������ɼ��޲�����¼.\n";
		}
		dao.runUpdate("update "+tableName+" set bz=? where xh=? and xn=? and "+zd+"=?", new String[]{bz, xh, xn, jxjdm});
	}
	
	public String getBzs(String[] bzList) throws Exception {
		String bz = "";
		if (bzList != null && bzList.length==7) {
			if (!StringUtils.isNull(bzList[0])) {
				bz += bzList[0] + "ѧ���";
			}
			if (!StringUtils.isNull(bzList[1])) {
				bz += bzList[1] + "ѧ�� ";
			}
			if (!StringUtils.isNull(bzList[2])) {
				bz += " �γ�("+bzList[2]+")";
			}
			if (!StringUtils.isNull(bzList[3])) {
				bz += " ����("+bzList[3]+")";
			}
			if (!StringUtils.isNull(bzList[4])) {
				bz += " �ɼ�("+bzList[4]+")";
			}
			if (!StringUtils.isNull(bzList[5])) {
				bz += " �����ɼ�("+bzList[5]+")";
			}
			if (!StringUtils.isNull(bzList[6])) {
				bz += " ���޳ɼ�("+bzList[6]+").";
			}
			bz += "\n";
		}
		return bz;
	}
	
	/**
	 * �й������޸��Ƚ��༶��ע��Ϣ
	 * @param values
	 * @throws Exception
	 */
	public void updateXjbjbz(String[] values) throws Exception {
		DAO dao = DAO.getInstance();
		if (values != null) {
			String xn = Base.getJxjsqxn();
			String bjdm = values[1];
			String bz = "";//�й��������ڼ�¼�༶Υ��,����,������Ϣ
			HashMap<String, String> tmp = dao.getMapNotOut(
							"select (select count(*) wjrs from view_wjcf a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and bjdm = ? and tydb like '%������%' and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm) group by xh) tyrs from dual",
							new String[] { xn, bjdm, xn, bjdm, xn,
									bjdm }); 
			if (!StringUtils.isNull(tmp.get("wjrs"))
					&& !"0".equalsIgnoreCase(tmp.get("wjrs"))) {//�Ƿ���Υ��
				bz += xn + "ѧ��༶ѧ���ܴ���:" + tmp.get("wjrs") + "��.\n" ;		
			} else {
				bz += xn + "ѧ��༶��ѧ���ܴ���,\n";
			}
			if (!StringUtils.isNull(tmp.get("kkrs"))
					&& !"0".equalsIgnoreCase(tmp.get("kkrs"))) {//�Ƿ��п���
				bz += xn + "ѧ��༶ѧ������:" + tmp.get("kkrs") + "��.\n" ;		
			} else {
				bz += xn + "ѧ��༶��ѧ������,\n";
			}
			if (!StringUtils.isNull(tmp.get("tyrs"))
					&& !"0".equalsIgnoreCase(tmp.get("tyrs"))) {//�Ƿ�������δ���
				bz += xn + "ѧ��༶ѧ������δ���:" + tmp.get("tyrs") + "��." ;		
			} else {
				bz += xn + "ѧ��༶ѧ������ȫ�����.";
			}
			dao.runUpdate("update pjpy_xjbjandwmsqb set bz = ? where bjdm = ? and xn=? and rychdm=?",
							new String[] { bz, bjdm, xn, values[2] });
		
		}
	}
	
	/**
	 * ͨ�õĵ����ۺ����ʷ�ʱ�Զ������ܷ�
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
	 * ѧ���ж�ѧ���Ƿ�ͨ���ڹ���ѧ���
	 * @param xh   ��excel���ݻ�õ�ѧ��
	 * @param xm   ��excel���ݻ�õ�����
	 * @return
	 */
	public String checkQgzx(String xh){
		String sql = "select xh from qgzxsqb where xh=? and xxsh='ͨ��'";
		String xmStr = dao.getOneRs(sql, new String[]{ xh }, "xh");
		boolean result = (xmStr != null) && xmStr.replace(" ", "").equalsIgnoreCase("") ? true : false;
		if(result) return null;
		return xmStr;
	}

	public boolean checkXhComments(String realTable) {
		// ���ѧ�������ݿ��е�ע���Ƿ�Ϊ'���'
		String sql = "select COMMENTS from user_col_comments where COLUMN_NAME='XH' and TABLE_NAME=upper(?)";
		String XhComment = dao.getOneRs(sql, new String[] { realTable },
				"COMMENTS");
		if (StringUtils.isNotNull(XhComment)
				&& XhComment.trim().equalsIgnoreCase("���")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ���������ֵ��������
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> ����Υ��Ψһ��Լ��������
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveWsfData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		String xxdm = StandardOperation.getXxdm();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String error = "";
		try{
			//���sql���  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//���sql����ǰ�벿��
			StringBuffer suffSqlPart = new StringBuffer(" values(");//���sql���ĺ�벿��
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//����������sql
			//���sql���  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//��������ѧ����Ϣwebservice�ַ���
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				try{
					inputData = new String[compArr.length];
					for(int num=0;num < inputData.length;num++){
						if (compArr[num][1].toLowerCase().equalsIgnoreCase("iszds")) {
							String iszds = oneRow[Integer.parseInt(compArr[num][0])];
							inputData[num] = "��".equalsIgnoreCase(iszds) ? "checked" : iszds;
						} else {
							inputData[num] = oneRow[Integer.parseInt(compArr[num][0])];
						}
					}
					flag = dao.runUpdate2(preSqlPart.toString(), inputData);
					
				}catch(Exception e){
					//��װ��������
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
	 * �޸�����������
	 * @param excelData       excel�и��е�����
	 * @param compArr         excel�������ݿ��б�Ķ�Ӧ��ϵ
	 * @return ArrayList<String[]> �������ݿ���û�е�����
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> updateWsfData(ArrayList<String[]> excelData,String[][] compArr,String realTable){
		ArrayList list = new ArrayList<String[]>();
		int iExecuteRow = 0;
		String errors = "";
		try{
			//���sql���  start
			String[] pk = getPkOfTable(realTable);
			StringBuffer preSqlPart = new StringBuffer("update " + realTable + " set ");//���sql���
			for(int num=0;num<compArr.length;num++){
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append("=?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);//ȥ�����һ��","��
			
			if(pk!=null && pk.length>0){//��������
				preSqlPart.append(" where 1=1 ");
				for(int i=0; i<pk.length; i++){
					 preSqlPart.append(" and " + pk[i] + "=? ");
				}
			}
			//���sql���  end
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
							inputData[num] = "��".equalsIgnoreCase(iszds) ? "checked" : iszds;
							//inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//��ȫ��ת���ɰ�Ǻ󱣴�
						} else {
							inputData[num] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);//��ȫ��ת���ɰ�Ǻ󱣴�
						}
						
						for(int j=0;j<pk.length;j++){//��ȡ��������е��������
							if(compArr[num][1]!=null && compArr[num][1].equalsIgnoreCase(pk[j].toUpperCase())){//���ֶ��������ֶ����ĳ���ֶΣ���ֵ���뵽���������
								inputData[compArr.length+j] = StringUtils.Q2BChange(oneRow[Integer.parseInt(compArr[num][0])]);
							}	
						}
					}					
					iExecuteRow = dao.runUpdate(preSqlPart.toString(), inputData, realTable);
					if(iExecuteRow<1){
						errors = "���ݿ��в����ڴ����ݣ������޸�";
						list.add(StringUtils.joinStrArr(new String[]{errors},tempRow));//�����ݿ���û�е����ݷ�
					}
				}catch(SQLException e){
					e.printStackTrace();
					list.add(getSQLErrorMsg(e.getMessage(), tempRow));//������������ݷ���
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public  ArrayList<HashMap<String, String>> getColColumn(String tabName, String dzyDdTab) {
		// TODO �Զ����ɷ������
		String sql = "select COLUMN_NAME column_name,upper(COMMENTS) comments from user_col_comments  where table_Name " +
				"= upper(?) UNION ALL select ZDID,ZDMC from "+dzyDdTab+" where tabname = ?" ;
		return dao.getArrayList(sql, new String[]{tabName,tabName}, new String[]{"comments","column_name"});
	}
	
	public  ArrayList<HashMap<String, String>> getColColumn(String tabName, String viewName, String dzyDdTab) {
		// TODO �Զ����ɷ������
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
	 * ѧ�Ÿ������Ķ��գ��Ƿ���������ĵ���ͬ.���������������ͬ���ͷ��ؿգ�����ͷ����������ĵ�ѧ������
	 * @param xh   ��excel���ݻ�õ�ѧ��
	 * @param xm   ��excel���ݻ�õ�����
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
		//�����ֶ�
		String [] pkCol = CommonQueryDAO.GetPkCol(realTable);
		//������Ӧ�ֶ��ڵ��������е����
		int [] pkColIndex = new int[pkCol.length];
		//ȡʵ�ʲ�����ֶμ�
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
			//���sql���  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(");//���sql����ǰ�벿��
			StringBuffer suffSqlPart = new StringBuffer(" values(");//���sql���ĺ�벿��
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
			preSqlPart.append(suffSqlPart);//����������sql
			//���sql���  end
			String[] inputData = null;
			for(int i=0;i<excelData.size(); i++){
				String[] tempRow = (String[]) excelData.get(i);
				String[] oneRow = new String[tempRow.length-1];
				
				Arrays2.copy(tempRow, oneRow, tempRow.length, 1, 0);
				//				������ֵ�������Զ��������������
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
									realTable = "view_" + realTable;//��ͼ����
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
					if(e.getMessage().contains("ORA-00001")){//�����Υ��Ψһ��Լ��
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "Υ��ΨһԼ��";
						result.add(tmpRow);//��Υ��ΨһԼ�������ݷ���
					}else if(e.getMessage().contains("ORA-01400")){//�޷��� NULL ����
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "�����ֶα�����д";
						result.add(tmpRow);//�����ݷ���
					}else if(e.getMessage().contains("ORA-00957")){//�ظ�������
						String[] tmpRow = new String[oneRow.length+2];
						Arrays2.copy(oneRow, tmpRow,oneRow.length, 0, 1);
						tmpRow[0]=String.valueOf(tempRow[0]);
						tmpRow[tmpRow.length-1] = "�������ظ�";
						result.add(tmpRow);//�����ݷ���
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
	 * ��ȡsql������Ϣ
	 * @param errorCode
	 * @param errorData
	 * @return String[]
	 * */
	public String[] getSQLErrorMsg(String errorCode, String[] errorData){
		String errorMsg = "";
		if(errorCode.contains("ORA-00001")){//�����Υ��Ψһ��Լ��
			errorMsg = "��¼�Ѿ�����";
		}else if(errorCode.contains("ORA-01400")){//�޷��� NULL ����
			errorMsg = "����Ϊ�յ���Ϣ������д";
		}else if(errorCode.contains("ORA-00957")){//�ظ�������
			errorMsg = "������Ϣ���ظ�";
		}else if(errorCode.contains("ORA-01722")){//������
			errorMsg = "������Ϣ������������";
		}
		return StringUtils.joinStrArr(new String[]{errorMsg},errorData);//�ѽ��������		
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����04:30:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelData
	 * @param splitMappingOne
	 * @param model
	 * @return
	 * ArrayList<String[]> �������� 
	 * @throws 
	 */
	public ArrayList<String[]> saveData(ArrayList<String[]> excelData,
			String[][] compArr, ExportAndImportModel model) {
		// TODO �Զ����ɷ������

		String xxdm = StandardOperation.getXxdm();
		String realTable=model.getRealTable();
		boolean flag = false;
		ArrayList<String[]> result = new ArrayList<String[]>();
		String ksh = "";
		try{
			//���sql���  start
			StringBuffer preSqlPart = new StringBuffer("insert into " + realTable + "(nf,yf,");//���sql����ǰ�벿��
			StringBuffer suffSqlPart = new StringBuffer(" values('"+model.getNf()+"','"+model.getYf()+"',");//���sql���ĺ�벿��
			for(int num=0;num<compArr.length;num++){				
				preSqlPart.append(compArr[num][1]);
				preSqlPart.append(",");
				suffSqlPart.append("?,");
			}
			preSqlPart.deleteCharAt(preSqlPart.length()-1);
			preSqlPart.append(")");
			suffSqlPart.deleteCharAt(suffSqlPart.length()-1);
			suffSqlPart.append(")");
			preSqlPart.append(suffSqlPart);//����������sql
			//���sql���  end
			String[] inputData = null;
			StringBuffer xsxxSb = new StringBuffer();//��������ѧ����Ϣwebservice�ַ���
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
					
					if(flag && xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){//��������ѧ����Ϣwebservice�ַ���
						xsxxSb.append(ksh.trim()+"!@");
					}
				}catch(Exception e){
					 e.printStackTrace();
					 //��װ��������
					 result.add(getSQLErrorMsg(e.getMessage(),tempRow));
				}
			}
//			������ѧ��������Ϣ��ʱ����
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY) && realTable.toLowerCase().equals("xsjbxxlsb") && xsxxSb.toString().trim().length()>0){
				xsxxSb.delete(xsxxSb.length()-2,xsxxSb.length());				
//				WebServiceClientForXmlgxy wsXmlgxy = new WebServiceClientForXmlgxy();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient wsClient = new SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInClient();
				SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap service = wsClient.getSinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentInSoap();				
				String returnStr = service.registerStu(xsxxSb.toString());
				System.out.println("����ֵ" + returnStr);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	
	}
}
