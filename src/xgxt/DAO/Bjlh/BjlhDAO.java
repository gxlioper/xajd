
package xgxt.DAO.Bjlh;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import common.Globals;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

import xgxt.DAO.DBPool;
import xgxt.base.BaseProperties;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.PropertesFileParser;

public class BjlhDAO  {
	private Connection conn = null;

	private PreparedStatement stmt = null;

	private Statement stat = null;

	private CallableStatement cstmt = null;
	
//	private PreparedStatement pstmt = null;

	private ResultSet rs = null;
	
	private DataSource db = null;
	
	public BjlhDAO(){
		db = DBPool.getPool();
	}
	
	public void closeStmt() {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				this.rs.close();
				this.rs = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				this.stmt.close();
				this.stmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stat != null) {
				this.stat.close();
				this.stat = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (cstmt != null) {
				this.cstmt.close();
				this.cstmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				this.conn.close();
				this.conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据提供的表名，获得在相应配置文件中指定的列信息
	 * @param tableName
	 * @return
	 */
	public Column[] getTableColumnInfo(String tableName) {
		ArrayList<Column> column = new ArrayList<Column>();//用于取出所有的列信息
		ArrayList<Column> subColumn = new ArrayList<Column>();//仅获得配置文件中指定的字段信息
		try {

			String sql = "select a.column_name,a.data_type,a.data_length,b.comments from user_tab_columns a,user_col_comments b where a.table_name=upper(?) and a.TABLE_NAME=b.table_name(+) and a.COLUMN_NAME=b.column_name(+)  order by a.column_id";
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tableName);
			rs = stmt.executeQuery();
			PropertesFileParser parser = new PropertesFileParser(BaseProperties.getTomcatPath()+"/servicesProperties/localTableColumns");
			String[] outputColumns =  parser.getLocalTableValue(tableName) != null ? parser.getLocalTableValue(tableName).trim().split(",") : null;
			//获得全部列信息
			while (rs.next()) {
				Column column1 = new Column();
				column1.setColName(rs.getString("column_name"));
				column1.setColComments(rs.getString("comments"));
				column1.setColType(rs.getString("data_type"));
				column1.setColLength(Integer.parseInt(rs.getString("data_length")));
				column.add(column1);
			}
			//获得限定列信息
			for(String temp : outputColumns){
				for(Column tempCol : column){
					if(temp != null && tempCol.getColName().equalsIgnoreCase(temp.trim())){
						subColumn.add(tempCol);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStmt();
		}
		return (Column[]) subColumn.toArray(new Column[subColumn.size()]);
	}
	
//	public Columns getTableColumnInfo2(String tableName) {
//		// TODO 自动生成方法存根
//		Columns r = new Columns();
//		ArrayList<Column> column = new ArrayList<Column>();
//		try {
//
//			String sql = "select a.column_name,a.data_type,a.data_length,b.comments from user_tab_columns a,user_col_comments b where a.table_name=upper(?) and a.TABLE_NAME=b.table_name(+) and a.COLUMN_NAME=b.column_name(+)  order by a.column_id";
//			this.conn = db.getConnection();
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, tableName);
//			rs = stmt.executeQuery();
//			while (rs.next()) {
//				Column column1 = new Column();
//				column1.setColName(rs.getString("column_name"));
//				column1.setColComments(rs.getString("comments"));
//				column1.setColType(rs.getString("data_type"));
//				column1.setColLength(rs.getString("data_length"));
//				column.add(column1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeStmt();
//		}
////		return (Column[]) column.toArray(new Column[column.size()]);
//		r.setColumns((Column[]) column.toArray(new Column[column.size()]));
//		return r;
//	}
	
	public int getTableRecordCount(String tableName) {
		// TODO 自动生成方法存根
		int RecordCount = 0;
		try {
			String sql = "select count(*) as rc from " + tableName;

			this.conn = db.getConnection();

			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				RecordCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStmt();
		}

		return RecordCount;
	}

	public String[][] getTableData(String tableName, int startLineNum,
			int pagesize) {
		// TODO 自动生成方法存根
		ArrayList<ArrayList> rowdata = new ArrayList<ArrayList>();
		int i_col, i_row;
		i_col = 0;
		PropertesFileParser parser = new PropertesFileParser(BaseProperties.getTomcatPath()+"/servicesProperties/localTableColumns");
		String xxdm = StandardOperation.getXxdm();
		try {
			String sql ="";
			sql = "select * from (select a.*,rownum hh from "
					+ tableName + " a ) where hh>=? and hh<=? ";
			//System.out.println(tableName);
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				startLineNum+=1;
			}
			stmt.setInt(1, startLineNum);
			stmt.setInt(2, (startLineNum + pagesize - 1));
			rs = stmt.executeQuery();
			while (rs.next()) {
//				ResultSetMetaData rsm = rs.getMetaData();
				String[] outputColumns =  parser.getLocalTableValue(tableName) != null ? parser.getLocalTableValue(tableName).trim().split(",") : null;
				//当获得了列时
				if(outputColumns != null){
					ArrayList<String> coldata = new ArrayList<String>();
					for (String temp : outputColumns) {
						coldata.add(rs.getString(temp.toLowerCase()));
					}
					i_col = coldata.size();
					rowdata.add(coldata);
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStmt();
		}
		i_row = rowdata.size();
		String[][] getstr = new String[i_row][i_col];
		for (int i = 0; i < i_row; i++) {
			for (int j = 0; j < i_col; j++) {
				getstr[i][j] = (String) rowdata.get(i).get(j);
			}
		}
		return getstr;
	}

	/**
	 * 用于其他客户webservice调用查数据的方法
	 * @param userName
	 * @param password
	 * @param dataServerId 指定要查数据的表名
	 * @param params
	 * @param start
	 * @param count
	 * @return
	 */
//	public String[][] getData(String userName,String password,String dataServerId,Param[] params,int start,int count){
//		String[][] result = null;
//		String sql = "select * from (select a.*,rownum r from "+dataServerId+" a ) where r>=? and r<=?";
//		try{
//			conn = db.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, count);
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//								
//			}
//		}catch(Exception e){
//			closeStmt();
//		}finally{
//			closeStmt();
//		}
//		return result;
//	} 
	
	/**
	 * 作者信息：zhj9705
	 * 说明：从CLOB转化到BASE64 String
	 * Created	on	2007-8-16
	 * @param sql
	 * @param inputValue
	 * @param colName
	 * @return
	 */
	public String getOneClobtoString(String sql, String[] inputValue,
			String colName) {
		CLOB clob = getOneClob(sql, inputValue, colName);
		String clobstring = new String();
		byte[] bytes = new byte[clob.getBytes().length];
		bytes = clob.getBytes();
		clobstring = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法
		return clobstring;
	}

	/**
	 * 作者信息：zhj9705
	 * 说明：从BASE64 String转化到CLOB,
	 * Created	on	2007-8-16
	 * @param clobstring
	 * @return
	 */
	public CLOB getOneStringtoClob(String clobstring) {
		CLOB clob = null;
		try {
			byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(clobstring);
			clob.setBytes(bytes);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return clob;
	}

	/**
	 * 作者信息：zhj9705
	 * 说明：取CLOB类型的字段值
	 * Created	on	2007-8-16
	 * @param sql
	 * @param inputValue
	 * @param colName
	 * @return
	 */
	public CLOB getOneClob(String sql, String[] inputValue, String colName) {
		CLOB clob = null;
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			rs.next();
			clob = (CLOB) rs.getClob(colName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStmt();
		}
		return clob;
	}

	/**
	 * 作者信息：zhj9705
	 * 说明：从BLOB转化到BASE64 String
	 * Created	on	2007-8-16
	 * @param sql
	 * @param inputValue
	 * @param colName
	 * @return
	 */
	public String getOneBlobtoString(String sql, String[] inputValue,
			String colName) {
		BLOB blob = getOneBlob(sql, inputValue, colName);

		String blobstring = new String();
		byte[] bytes = new byte[blob.getBytes().length];
		bytes = blob.getBytes();
		blobstring = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法		
		return blobstring;
	}

	/**
	 * 作者信息：zhj9705
	 * 说明：从BASE64 String转化到BLOB,
	 * Created	on	2007-8-16
	 * @param blobstring
	 * @return
	 */
	public BLOB getOneStringtoBlob(String blobstring) {
		BLOB blob = null;

		try {
			byte[] bytes = new sun.misc.BASE64Decoder()
					.decodeBuffer(blobstring);
			blob.setBytes(bytes);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return blob;
	}



	/**
	 * 作者信息：zhj9705
	 * 说明：取BLOB类型的字段值
	 * Created	on	2007-8-16
	 * @param sql
	 * @param inputValue
	 * @param colName
	 * @return
	 */
	public BLOB getOneBlob(String sql, String[] inputValue, String colName) {
		BLOB blob = null;
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			rs.next();
			blob = (BLOB) rs.getBlob(colName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStmt();
		}
		return blob;
	}
}
