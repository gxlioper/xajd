package xgxt.shgc.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import xgxt.DAO.DAO;
import xgxt.DAO.DBPool;
import xgxt.utils.CustomException;

public class HzjyServiceDao {

	private static DAO dao = DAO.getInstance();

	private DataSource db = null;

	private Connection conn = null;

	private PreparedStatement stmt = null;

	private Statement stat = null;

	private CallableStatement cstmt = null;

	private ResultSet rs = null;

//	private ResultSetMetaData rsmd = null;

	private String ip = null;

	private String mac = null;

	private int rsCount = 0;

	private int pageCount = 0;

	private int pageSize = 0;

	private int currentPage = 0;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRsCount() {
		return rsCount;
	}

	public void setRsCount(int rsCount) {
		this.rsCount = rsCount;
	}

	public HzjyServiceDao() {
		// this.db = TestCases.getDs();
		this.db = DBPool.getPool();
		// System.out.println("this.db="+this.db.hashCode());
	}

	public static DAO getInstance() {
		// if (dao == null) {
		dao = new DAO();
		// }
		return dao;
	}

	public HzjyServiceDao(Connection conn) {
		// 构造函数初始化数据连接。;
		this.conn = conn; // //////Configuration.connMgr;
	}

	public HzjyServiceDao(String Ip) {
		// 构造函数，带有一个参数IP，初始化IP和MAC。供写入日志时使用。
		this();
		this.ip = Ip;
		this.mac = getMacAddressIP(ip);
	}

		public  String getMacAddressIP(String remotePcIP) {
		// 通过IP获取MAC
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
					}
				}
			}
		} catch (IOException ex) {

		} finally {

		}
		return macAddress;
	}

	public  void closeStmt() {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				this.rs.close();
			}
			if (stmt != null) {
				this.stmt.close();
			}
			if (stat != null) {
				this.stat.close();
			}
			if (cstmt != null) {
				this.cstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeFromResultSet(ResultSet rs){
		//Statement stat = null;
		//Connection conn = null;
		try {
			 stat = rs.getStatement();
			 conn = stat.getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 关闭传入的ResultSet、PreparedStatement、Statement、CallableStatement
	 * @param rs
	 * @param pstmt
	 * @param stat
	 * @param cstmt
	 */
	public  void closeAllStmtAndRs(ResultSet rs,PreparedStatement pstmt,Statement stat,CallableStatement cstmt) {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {//PreparedStatement
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
	

	/**
	 * 用于关闭数据库连接,必须保证已经使用完了ResultSet和Statement, 否则将出现使用closed ResultSet和closed
	 * Statement异常
	 * 
	 * @param conn
	 */
	public  void closeConnection(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public ArrayList<HashMap<String, String>> getQuerryInfoArrayList(
			String sql, String[] inputValue, String[] outputValue,HashMap<String, String> querryinfomap) {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i], rs.getString(outputValue[i]));
					
					map.put("xh1",querryinfomap.get("xh1"));
					map.put("xm1",querryinfomap.get("xm1"));
					map.put("nj",querryinfomap.get("nj"));
					map.put("xydm",querryinfomap.get("xydm"));
					map.put("zydm",querryinfomap.get("zydm"));
					map.put("bjdm",querryinfomap.get("bjdm"));				
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

}
