package xgxt.base;

//��ȡExcel����
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import common.Globals;
import common.newp.ArrayUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.DBPool;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.utils.ExcelUtils;

public class Excel2Oracle {

	public static int getColumns(String filePath) {
		int columns = 0;
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			columns = sheet.getColumns();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return columns;
	}

	public static String getDataItem(String filePath) {
		String itemStr = "";
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			int columns = sheet.getColumns();
			for (int i = 0; i < columns; i++) {
				itemStr = itemStr + "!!SplitSignOne!!!!SplitSignTwo!!";
				itemStr = itemStr + i;
				itemStr = itemStr + "!!SplitSignTwo!!";
				Cell cell1 = sheet.getCell(i, 0);
				itemStr = itemStr + cell1.getContents();
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemStr;
	}

	public static HashMap<String, String> impXsxx(String[][] mappingItems,
			String filePath, String userName, String userType){		
		DataSource db = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
//		PreparedStatement pstmt_lrh = null;
		PreparedStatement pstmt_mm = null;
		String sql = "";
		String tableName = "xsxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		db = DBPool.getPool();

		try {
			conn = db.getConnection();
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			// ���浼����������Ϣ
			map.put("total", Integer.toString(rows - 1));

			String sqlTmp = "select colname from drsjjcgzb where tname=? and tag='0'";
			List gzList = dao.getList(sqlTmp, new String[] { tableName },
					new String[] { "colname" });
			String tag = ",";

			sql = "insert into " + tableName + " (";
			for (int m = 1; m < mappingItems.length; m++) {
				if (gzList != null
						&& gzList.toString().indexOf(
								"colname=" + mappingItems[m][2].toLowerCase()) > 0
								&& !userType.equalsIgnoreCase("admin")) {
					tag += String.valueOf(m) + ",";
				}

				sql = sql + mappingItems[m][2];

				if (m != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					sql = sql + ") values (";
				}				
			}
			for (int n = 1; n < mappingItems.length; n++) {
				sql = sql + "?";
				if (n != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					sql = sql + ")";
				}
			}		
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < rows; i++) {
				try {
					for (int j = 1; j < mappingItems.length; j++) {
						Cell cell1 = sheet.getCell(Integer.parseInt(mappingItems[j][1]), i);
						String itemContent = cell1.getContents();
						if (tag.indexOf("," + String.valueOf(j) + ",") >= 0) {
							String sqlTmp1 = "select defvalue from drsjjcgzb "
								+ "where tname=? and tag='0' and rownum=1 and colname=?";
							String sqV = dao.getOneRs(sqlTmp1,
									new String[] { tableName,
									mappingItems[j][2].toLowerCase() },
									new String[] { "defvalue" })[0];
							pstmt.setString(j, sqV);
						} else {
							pstmt.setString(j, itemContent);
						}
					}
					pstmt.executeUpdate();
					//��ssmmb�е�������					
					pstmt_mm=conn.prepareStatement(sql);
					//for(int m=1;m<rows;m++){
					Cell cell1 = sheet.getCell(Integer.parseInt(mappingItems[1][1]),i);						
					String itemContent = cell1.getContents();
					String sqlStr="insert into xsmmb(xh) values('"+itemContent+"')";
					pstmt_mm.executeUpdate(sqlStr);							
					//}	

					// System.out.println(i);
					// ���浼��ɹ��к���Ϣ
					map.put("succInfo", map.get("succInfo") + "-" + i);
				} catch (SQLException e) {
					// ���浼��ʧ���к���Ϣ
					System.out.println(e);
					map.put("failInfo", map.get("failInfo") + "-" + i);
					map.put("importInfo", "���������д��ڴ���!");
					// System.out.println("e:"+i);
				}
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("importInfo", "��������ʧ��!");
		}
		return map;
	}

	public static HashMap<String, String> importData(String mappingItems[][],
			String tableName, String filePath, String userName, String userType) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
//		int updateFlag = 0;
		conn = DBPool.getPool().getConnection();
		if (tableName.equalsIgnoreCase("xsxxb")) {
			return impXsxx(mappingItems, filePath, userName, userType);
		}
		try {

			// ����Ϊ�����ۺ����ʲ����������
			if (tableName.equalsIgnoreCase("zhszcp")) {
//				updateFlag = 1;
				tableName = "zhszcp";

			}
			// ����Ϊ�����ۺ����ʲ����������
			else {
				String tableNameTmp = tableName + "_tmp_" + userName;
				if(StandardOperation.tableExist(tableNameTmp)){
					sql = "drop table " + tableNameTmp;
					dao.runUpdate(sql, new String[] {});
				}
				sql = "create table " + tableNameTmp + " as select * from "
				+ tableName;
				dao.runUpdate(sql, new String[] {});
			}
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			// ���浼����������Ϣ
			map.put("total", Integer.toString(rows - 1));

			String sqlTmp = "select colname from drsjjcgzb where tname=? and tag='0'";
			List gzList = dao.getList(sqlTmp, new String[] { tableName },
					new String[] { "colname" });
			String tag = ",";
			if(tableName.equalsIgnoreCase("xsjxbxb")){
				sql="insert into "+ tableName + " (id, ";
			}else if(tableName.equalsIgnoreCase("xsjxbzb")){
				sql="insert into "+ tableName + " (bzh, ";
			}else if(tableName.equalsIgnoreCase("xsjxcfb")){
				sql="insert into "+ tableName + " (cfh, ";
			}else{			
				sql = "insert into " + tableName + " (";
			}
			for (int m = 1; m < mappingItems.length; m++) {
				if (gzList != null
						&& gzList.toString().indexOf(
								"colname=" + mappingItems[m][2].toLowerCase()) > 0
								&& !userType.equalsIgnoreCase("admin")) {
					tag += String.valueOf(m) + ",";
				}

				sql = sql + mappingItems[m][2];

				if (m != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					if(tableName.equalsIgnoreCase("xsjxbxb")){
						sql=sql+ ") values(seq_jxbx.nextval,";
					}else if(tableName.equalsIgnoreCase("xsjxbzb")){
						sql=sql+ ") values(seq_jxbz.nextval,";
					}else if(tableName.equalsIgnoreCase("xsjxcfb")){
						sql=sql+ ") values(seq_jxcf.nextval,";
					}
					else{
						sql = sql + ") values (";
					}
				}
			}
			for (int n = 1; n < mappingItems.length; n++) {
				sql = sql + "?";
				if (n != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					sql = sql + ")";
				}
			}
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < rows; i++) {
				try {
					for (int j = 1; j < mappingItems.length; j++) {
						Cell cell1 = sheet.getCell(Integer
								.parseInt(mappingItems[j][1]), i);
						String itemContent = cell1.getContents();
						if (tag.indexOf("," + String.valueOf(j) + ",") >= 0) {
							String sqlTmp1 = "select defvalue from drsjjcgzb "
								+ "where tname=? and tag='0' and rownum=1 and colname=?";
							String sqV = dao.getOneRs(sqlTmp1,
									new String[] { tableName,
									mappingItems[j][2].toLowerCase() },
									new String[] { "defvalue" })[0];
							pstmt.setString(j, sqV);
						} else {
							pstmt.setString(j, itemContent);
						}
					}
					pstmt.executeUpdate();
					// System.out.println(i);
					// ���浼��ɹ��к���Ϣ
					map.put("succInfo", map.get("succInfo") + "-" + i);
				} catch (SQLException e) {
					// ���浼��ʧ���к���Ϣ
					map.put("failInfo", map.get("failInfo") + "-" + i);
					map.put("importInfo", "���������д��ڴ���!");
					// System.out.println("e:"+i);
				}
			}

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("importInfo", "��������ʧ��!");
		}
		return map;
	}
	public static HashMap importData_error(String mappingItems[][],
			String tableName, String filePath, String userName, String userType) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		HashMap<String, Object> map = new HashMap<String, Object>();
		DAO dao = DAO.getInstance();
//		int updateFlag = 0;
		String xxdm = dao.getXxdm();
		conn = DBPool.getPool().getConnection();
		if (tableName.equalsIgnoreCase("xsxxb")) {
			return impXsxx(mappingItems, filePath, userName, userType);
		}
		try {

			// ����Ϊ�����ۺ����ʲ����������
			if (tableName.equalsIgnoreCase("zhszcp")) {
//				updateFlag = 1;
				tableName = "zhszcp";
				//������
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					tableName = "zhszcplsb";
				}
			}
			// ����Ϊ�����ۺ����ʲ����������
			else {
				String tableNameTmp = tableName + "_tmp_" + userName;
				if(StandardOperation.tableExist(tableNameTmp)){
					sql = "drop table " + tableNameTmp;
					dao.runUpdate(sql, new String[] {});
				}
				sql = "create table " + tableNameTmp + " as select * from "
				+ tableName;
				dao.runUpdate(sql, new String[] {});
			}
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			// ���浼����������Ϣ

//			��������Ϣ�ŵ�����һ��EXCEL�ļ���
			String error_file = ExcelUtils.getErrorExcel(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(new File(error_file));
			WritableSheet ws = wwb.createSheet("���ݵ���ɹ�", 0);
			int wsRow = 0;
			ExcelUtils.rowCopy(ws, 0, sheet, 0);
			List<String[]> errorList = new ArrayList<String[]>();	//����б��Ǵ�Ŵ�����кͱ�ͷ
			errorList.add(ExcelUtils.rowToArray(sheet, 0));


			map.put("total", Integer.toString(rows - 1));

			String sqlTmp = "select colname from drsjjcgzb where tname=? and tag='0'";
			List gzList = dao.getList(sqlTmp, new String[] { tableName },
					new String[] { "colname" });
			String tag = ",";
			if(tableName.equalsIgnoreCase("xsjxbxb")){
				sql="insert into "+ tableName + " (id, ";
			}else if(tableName.equalsIgnoreCase("xsjxbzb")){
				sql="insert into "+ tableName + " (bzh, ";
			}else if(tableName.equalsIgnoreCase("xsjxcfb")){
				sql="insert into "+ tableName + " (cfh, ";
			}else{			
				sql = "insert into " + tableName + " (";
			}
			for (int m = 1; m < mappingItems.length; m++) {
				if (gzList != null
						&& gzList.toString().indexOf(
								"colname=" + mappingItems[m][2].toLowerCase()) > 0
								&& !userType.equalsIgnoreCase("admin")) {
					tag += String.valueOf(m) + ",";
				}

				sql = sql + mappingItems[m][2];

				if (m != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					if(tableName.equalsIgnoreCase("xsjxbxb")){
						sql=sql+ ") values(seq_jxbx.nextval,";
					}else if(tableName.equalsIgnoreCase("xsjxbzb")){
						sql=sql+ ") values(seq_jxbz.nextval,";
					}else if(tableName.equalsIgnoreCase("xsjxcfb")){
						sql=sql+ ") values(seq_jxcf.nextval,";
					}
					else{
						sql = sql + ") values (";
					}
				}
			}
			for (int n = 1; n < mappingItems.length; n++) {
				sql = sql + "?";
				if (n != mappingItems.length - 1) {
					sql = sql + ",";
				} else {
					sql = sql + ")";
				}
			}
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < rows; i++) {
				try {
					for (int j = 1; j < mappingItems.length; j++) {
						Cell cell1 = sheet.getCell(Integer.parseInt(mappingItems[j][1]), i);
						String itemContent = cell1.getContents();
						if (tag.indexOf("," + String.valueOf(j) + ",") >= 0) {
							String sqlTmp1 = "select defvalue from drsjjcgzb "
								+ "where tname=? and tag='0' and rownum=1 and colname=?";
							String sqV = dao.getOneRs(sqlTmp1,
									new String[] { tableName,
									mappingItems[j][2].toLowerCase() },
									new String[] { "defvalue" })[0];
							pstmt.setString(j, sqV);
						} else {
							pstmt.setString(j, itemContent);
						}
					}
					pstmt.executeUpdate();
					// System.out.println(i);
					// ���浼��ɹ��к���Ϣ
					map.put("succInfo", map.get("succInfo") + "-" + i);
				} catch (SQLException e) {
					// ���浼��ʧ���к���Ϣ
					e.printStackTrace();
					wsRow += 1;
					errorList.add(ExcelUtils.rowToArray(sheet, i));
					ExcelUtils.rowCopy(ws, wsRow, sheet, i);

					map.put("failInfo", map.get("failInfo") + "-" + i);
					map.put("importInfo", "���������д��ڴ���!");
					// System.out.println("e:"+i);
				}
			}
			if (wsRow != 0) {
				map.put("error_file", error_file);
			}
			map.put("errorList", errorList);
			wwb.write();
			wwb.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("importInfo", "��������ʧ��!");
		}
		return map;
	}
	
	
	//Execl�������
	public static HashMap importData_modify(String mappingItems[][],
			String tableName, String filePath, String userName, String userType) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		HashMap<String, Object> map = new HashMap<String, Object>();
		conn = DBPool.getPool().getConnection();
		DAO dao=DAO.getInstance();
		try {			
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			// ���浼����������Ϣ
			//��������Ϣ�ŵ�����һ��EXCEL�ļ���
			String error_file = ExcelUtils.getErrorExcel(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(new File(error_file));
			WritableSheet ws = wwb.createSheet("���ݵ������", 0);
			int wsRow = 0;
			ExcelUtils.rowCopy(ws, 0, sheet, 0);
			List<String[]> errorList = new ArrayList<String[]>();	//����б��Ǵ�Ŵ�����кͱ�ͷ
			errorList.add(ExcelUtils.rowToArray(sheet, 0));
			
			map.put("total", Integer.toString(rows - 1));
			//��ѯ�������
			sql = "select a.column_name pk from user_cons_columns a,user_constraints b where a.table_name=b.table_name and a.constraint_name=b.constraint_name and b.constraint_type='P' and a.table_name=?";
			String[] pkList=dao.getOneRs(sql, new String[]{tableName.toUpperCase()}, new String[]{"pk"});	
			
			//����ѧ����Ϣ�������
			if("xsxxb".equalsIgnoreCase(tableName)){				
				for (int n = 1; n < rows; n++) {
					try{
					//��ѯѧ����Ϣ��xsxxb���Ƿ���ڣ�����˵��ѧ�����޸Ĺ���Ϣ��������˵��ѧ����û���޸Ĺ���Ϣ
					sql="select xh from xsxxb where ";
					String pkStr="";
					for(int j=0;j<pkList.length;j++){							
						//String[] pkT=pkList[j].split("SplitSignTwo");
						//String columnExcel=pkT[0].substring(0,pkT[0].indexOf("!"));
						String columnOracl=pkList[j];//pkT[1].substring(2,pkT[1].length());
						int index = 0;
						//[null, [, 0, XH], [, 5, XM]]
						 for(int i=0;i<mappingItems.length;i++){
							 String[] tempList = mappingItems[i];
							 if(tempList!=null && tempList.length>0){
								 for(int f=0;f<tempList.length; f++){
									 //���orcal���е��ֶκ��������ȡ��excel�е���Ӧֵ����
									 if(tempList[f]!=null && tempList[f].equalsIgnoreCase(pkList[j])){
										 index = i;
										 break;
									 }
								 }
							 }
							 if(index!=0){
								 break;
							 }
						 }
						index = Integer.parseInt(mappingItems[index][1]) ;
						//Cell cell1 = sheet.getCell(Integer.parseInt(columnExcel), n);
						 Cell cell1 = sheet.getCell(index, n);
						String itemContent = cell1.getContents();						
						pkStr = pkStr + columnOracl.toLowerCase()+"='"+itemContent+"'";
						if(j!=pkList.length-1){
							pkStr=pkStr+" and ";
						}
					}	
					
					sql=sql+pkStr;
					List rs=dao.getList(sql, new String[] {}, new String[] {"xh"});
					if(rs.size()<=0){						
						sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz) (select xh,xm,xb,xymc,xydm,zymc,zydm,bjmc,bjdm ,nj,xz from view_xsjbxx where ";
						sql = sql+pkStr+")";
						dao.runUpdate(sql,new String[]{});
						sql = "update xsxxb set sfzh=?,xmpy=?,cym=?,xjztm=?,bz=?,pyfs=?,rxfsm=?,jg=?";
						String[] val=dao.getOneRs("select bz,xmpy,sfzh,cym,rxfsm,pyfs,xjztm,jgm from bks_xsjbxx where "+pkStr, new String[] {},  new String[] {});
						if(val!=null && val.length>0){
							dao.runUpdate(sql,val);
						}
					}
					}catch(SQLException ex){
						//���浼��ʧ���к���Ϣ
						ex.printStackTrace();
						wsRow += 1;
						errorList.add(ExcelUtils.rowToArray(sheet, n));
						ExcelUtils.rowCopy(ws, wsRow, sheet, n);

						map.put("failInfo", map.get("failInfo") + "-" + n);
						map.put("importInfo", "���������д��ڴ���!");
						// System.out.println("e:"+i);
					}
				}		
				
			}
			
			sql = "update " + tableName + " set ";							
			for (int m = 1; m < mappingItems.length; m++) {
				sql = sql + mappingItems[m][2]+"=? ";
				if (m != mappingItems.length - 1) {
					sql = sql + ",";
				} else {					
						sql = sql + " where ";				
						//�������
						for(int i=0;i<pkList.length;i++){							
							//String[] pkT=pkList[i].split("SplitSignTwo");							
							String columnOracl=pkList[i];//pkT[1].substring(2,pkT[1].length());
							sql = sql + columnOracl+"=?";
							if(i!=pkList.length-1){
								sql=sql+" and ";
							}							
						}
				}
			}
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < rows; i++) {
				try {
					for (int j = 1; j < mappingItems.length; j++) {
						//�ֶ�ֵ
						Cell cell1 = sheet.getCell(Integer.parseInt(mappingItems[j][1]), i);
						String itemContent = cell1.getContents();						
						pstmt.setString(j, itemContent);						
					}
					for(int k = 0;k<pkList.length;k++){	
						//����ֵ
//						String[] pkT=pkList[k].split("SplitSignTwo");							
//						String columnExcel=pkT[0].substring(0,pkT[0].indexOf("!"));
//						Cell cell1 = sheet.getCell(Integer.parseInt(columnExcel), i);
//						String itemContent = cell1.getContents();		
						int index = 0;
						//[null, [, 0, XH], [, 5, XM]]
						 for(int m=0;m<mappingItems.length;m++){
							 String[] tempList = mappingItems[m];
							 if(tempList!=null && tempList.length>0){
								 for(int f=0;f<tempList.length; f++){
									 if(tempList[f]!=null && tempList[f].equalsIgnoreCase(pkList[k])){
										 index = m;
										 break;
									 }
								 }
							 }
							 if(index!=0){
								 break;
							 }
						 }
						index = Integer.parseInt(mappingItems[index][1]) ;
						Cell cell1 = sheet.getCell(index, i);
						String itemContent = cell1.getContents();
						pstmt.setString(mappingItems.length+k, itemContent);
					}
					
					if(pstmt.executeUpdate()<1)//����ʧ��
						throw new SQLException();
					// ���浼��ɹ��к���Ϣ
					map.put("succInfo", map.get("succInfo") + "-" + i);
				} catch (SQLException e) {
					// ���浼��ʧ���к���Ϣ
					e.printStackTrace();
					wsRow += 1;
					errorList.add(ExcelUtils.rowToArray(sheet, i));
					ExcelUtils.rowCopy(ws, wsRow, sheet, i);

					map.put("failInfo", map.get("failInfo") + "-" + i);
					map.put("importInfo", "���������д��ڴ���!");
					// System.out.println("e:"+i);
				}                               
			}
			if (wsRow != 0) {
				map.put("error_file", error_file);
			}
			map.put("errorList", errorList);
			wwb.write();
			wwb.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("importInfo", "��������ʧ��!");
		}
		return map;
	}
	
	public static void exportData(String sql, String tabName, OutputStream os)
	throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			
			//��������ֶ�����BZ����ֶ�,���BZ�ŵ����
			try {
				ColumnName = ArrayUtil.changeBzAfter(ColumnName);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public static void exportData(String sql, String tabName,String title, OutputStream os)
	throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			
			//��������ֶ�����BZ����ֶ�,���BZ�ŵ����
			try {
				ColumnName = ArrayUtil.changeBzAfter(ColumnName);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			excel.printTitle(ws, title, ColumnName.length, 400);
			
			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 1, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 2, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public static void exportData(List<String[]> vec,String[] ColumnName,String[] ColumnNameCN, OutputStream os)
	throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public static void exportData(String sql, String tabName, OutputStream os,String sheetTitle)
	throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(sheetTitle, 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public static void exportData(String sql,String[] colNameCN ,OutputStream os,String title)
	throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			//String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 0, colNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public static void exportData(String sql,String[] inputs,String[] outputs,String[] colNameCN,OutputStream os,String title)
	throws Exception {
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		try {
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 0, colNameCN[m]));
			}
			
			list = dao.rsToVator(sql, inputs, outputs);
			
			int k = outputs.length;
			for (int i = 0; i < list.size(); i++) {
				String[] tmp = list.get(i);
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public void exportData(String sql,String[] inputs,String[] outputs,String[] colNameCN,OutputStream os,String sheetTitle, String title)
	throws Exception {
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);; // ���쵥Ԫ���ʽ
		
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.addCell(new Label(0, 0, title, titleFormat)); 
		
		try {
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 1, colNameCN[m]));
			}
			
			list = dao.rsToVator(sql, inputs, outputs);
			
			int k = outputs.length;
			for (int i = 0; i < list.size(); i++) {
				String[] tmp = list.get(i);
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 2, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	/**
	 * ģ�嵼�����ڶ��ű�ģ��
	 * @param sql
	 * @param tabName
	 * @param os
	 * @author sjf
	 * 
	 **/
	public static void exportDataStencil(String[] sql, String[] tabName, OutputStream os) throws Exception{
		DAO dao = DAO.getInstance();
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("ģ������", 0);
		
		List<String> columnList = new ArrayList<String>();
		List<String> columnPostilList = new ArrayList<String>();
		
		try {
			for(int i=0; i<sql.length; i++){
				String ColumnName[] = dao.getColumnName(sql[i]);
				
				String[] columnCN = dao.getColumnNameCNAndSm(ColumnName, tabName[i].toUpperCase());
				String[] columnPostil = dao.getColumnNamePostil(ColumnName, tabName[i].toUpperCase());//��ע
				
				for(int j=0; j<columnCN.length; j++){
					columnList.add(columnCN[j]);
					columnPostilList.add(columnPostil[j]);
				}
			}
			
			for (int m = 0; m < columnList.size(); m++) {
				ws.addCell(new Label(m, 0, columnList.get(m)));
				WritableCellFeatures wcfeat = new WritableCellFeatures(); // ���쵥Ԫ������
				WritableCell wc = ws.getWritableCell(m, 0);//��ȡ��m�У�0�е�Ԫ�����	
				wcfeat.setComment(columnPostilList.get(m));
				wc.setCellFeatures(wcfeat);	//д����ע
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ģ������ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
	public static void exportDataStencil(String sql, String tabName, OutputStream os)
	throws Exception {
		DAO dao = DAO.getInstance();
//		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("ģ������", 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			
			//������Ƶ�����������ֶ������б�ע,��ô����ע���������
			if (ColumnName != null) {
				ColumnName = ArrayUtil.changeBzAfter(ColumnName);
			}
			
			String ColumnNameCN[] = dao.getColumnNameCNAndSm(ColumnName, tabName.toUpperCase());
			String ColumnNamePostil[] = dao.getColumnNamePostil(ColumnName, tabName.toUpperCase());//��ע
			
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBackground(jxl.format.Colour.ROSE); //������ɫ����ʽ  
			
			for (int m = 0; m < ColumnNameCN.length; m++) {
				
				// ========2011.3.24 edit by luojw ===========
				// ������Ϊ�յĵ����ֶα�ע��ɫ��õ��
				int flag = ColumnNamePostil[m].indexOf("�Ƿ����Ϊ��:��");
				
				if (flag == -1) {
					ws.addCell(new Label(m, 0, ColumnNameCN[m]));
				} else {
					ws.addCell(new Label(m, 0, ColumnNameCN[m], wcf));
				}
				
				WritableCellFeatures wcfeat = new WritableCellFeatures(); // ���쵥Ԫ������
				WritableCell wc = ws.getWritableCell(m, 0);//��ȡ��m�У�0�е�Ԫ�����	
				wcfeat.setComment(ColumnNamePostil[m]);
				wc.setCellFeatures(wcfeat);	//д����ע
			}
//			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
//			int k = ColumnName.length;
//			for (int i = 0; i < vec.size(); i++) {
//				String[] tmp = (String[]) vec.toArray()[i];
//				for (int j = 0; j < k; j++) {
//					ws.addCell(new Label(j, i + 1, tmp[j]));
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ģ������ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}

	//��Ҫѭ����ѯ������
	public static synchronized void exportDataFor(List<Object> vec, String ColumnNameCN[], OutputStream os)
	throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < tmp.length; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	/**
	 * ����ѧУ���롢������ȡ���������Ӧ�����ֶ�SQL
	 * @param xxdm
	 * @param tableName
	 * @return
	 */
	public static String getImpColumn(String xxdm , String tableName){
		DAO dao = DAO.getInstance();
		String zd = "";
		String retSql = "";
		String sql = "select zdmc from  drb where xxdm=? and zdssb=? order by to_number(xsxh) asc" ; 
		List<HashMap<String,String>> list = dao.getList(sql, new String[]{xxdm,tableName},new String[]{"zdmc"});
		if(list.size()>0){
			for(int i=0;i<list.size();i++){				
				if(i==list.size()-1){
					zd+=list.get(i).get("zdmc");
				}else{
					zd+=list.get(i).get("zdmc")+",";
				}
			}
		}
		retSql = "select " + zd + " from " + tableName;
		return retSql;
	}
	/**
	 * ����ѧУ���롢�����жϵ�����д��ڸ�У�ñ���ڵ����ֶ����
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return
	 */
	public  static boolean checkExists(String tableName, String pk, String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from " + tableName + " where " + pk + " = ?";
		String result = dao.getOneRs(sql, new String[]{pkValue}, "num");
		result = Base.isNull(result) ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}
	
	/**
	 * ��ͬѧУ��������ȡ�����ֶ��б���
	 * @param xxdm
	 * @param tableName
	 * @return
	 */
	public static String getSqlColumn(String xxdm,String tableName){
		String sql = "select * from "+tableName+" where 1=0 "; 
		if(checkExists("drb","xxdm||zdssb", xxdm+tableName)){
			sql = getImpColumn(xxdm,tableName);
		}else if("xsxxb".equalsIgnoreCase(tableName)){//ѧ����Ϣ
			xxdm = "public_xsxxb";//������в����ڸ�У��"xsxxb"�����ֶ�ʱ��ȡ��Ĭ�ϵĹ����ֶ�
			if(checkExists("drb","xxdm||zdssb",(xxdm+tableName).toLowerCase())){
				sql =  getImpColumn(xxdm,tableName.toLowerCase());
			}
		}else if("xsfzxxb".equalsIgnoreCase(tableName)){
			xxdm = "public_xsfzxxb";
			if(checkExists("drb","xxdm||zdssb",(xxdm+tableName).toLowerCase())){
				sql =  getImpColumn(xxdm,tableName.toLowerCase());
			}
		}else{
			xxdm = "public";//������в����ڸ�У�ֶ�ʱ��ȡ��Ĭ�ϵĹ����ֶ�
			if(checkExists("drb","xxdm||zdssb",(xxdm+tableName).toLowerCase())){
				sql =  getImpColumn(xxdm,tableName.toLowerCase());
			}
		}
		return  sql;
	}	
	
	
	
	public static void main(String...strings)throws Exception{
		String[] zdList = new String[]{"b", "c", "BZ", "a"};
		zdList = ArrayUtil.changeBzAfter(zdList);
		for (int i=0;i<zdList.length;i++) {
			System.out.print(zdList[i] + "   ");
		}
	}
	
	

	/**
	 * ����������
	 * @param sql
	 * @param tabName
	 * @param inputList
	 * @param os
	 * @param sheetTitle
	 * @throws Exception
	 */
	public static void exportDataIn(String sql, String tabName,String [] inputList,String [] ColumnNameCN,OutputStream os,String sheetTitle)
	throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(sheetTitle, 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, inputList, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	/**
	 * ������ҳǩҳ��
	 * @param sheetName ҳǩ���б�
	 * @param vec ���ݼ��б�
	 * @param ColumnName ����д���
	 * @param ColumnNameCN ���������
	 * @param os �����
	 * @throws Exception
	 */
	public static void exportDataMoreSheet(List<String> sheetName, List<List<String[]>> vec,List<Integer> ColumnNum,
			List<String[]> ColumnNameCN,OutputStream os)
	throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		try {
			for(int index=0;index<sheetName.size();index++){
				WritableSheet ws = wwb.createSheet(sheetName.get(index), index);
				for (int m = 0; m < ColumnNameCN.get(index).length; m++) {
					ws.addCell(new Label(m, 0, ColumnNameCN.get(index)[m]));
				}
				int k =ColumnNum.get(index);
				for (int i = 0; i < vec.get(index).size(); i++) {
					String[] tmp = (String[]) vec.get(index).toArray()[i];
					for (int j = 0; j < k; j++) {
						ws.addCell(new Label(j, i + 1, tmp[j]));
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
}
