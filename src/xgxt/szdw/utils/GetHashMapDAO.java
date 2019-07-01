package xgxt.szdw.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.sql.DataSource;

import xgxt.DAO.DBPool;
import xgxt.utils.CustomException;

public class GetHashMapDAO {
	private Connection conn = null;

	private PreparedStatement stmt = null;
	
//	private PreparedStatement pstmt = null;

	private ResultSet rs = null;
	
	private DataSource db = null;
	
	public GetHashMapDAO(){
		db = DBPool.getPool();
	}
	
	public void closeAllStmtAndRs(ResultSet rs, PreparedStatement pstmt,
			Statement stat, CallableStatement cstmt) {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {// PreparedStatement
				pstmt.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
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
	public HashMap<String, String> getHashMap(String sql,String inputString, String keyCol,String keyValueCol) {
		HashMap<String, String> mapX = new HashMap<String, String>();
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, inputString);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String key = rs.getString(keyCol);
				String KeyValue = rs.getString(keyValueCol);
				mapX.put(key, KeyValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			mapX = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return mapX;
	}
	
}
