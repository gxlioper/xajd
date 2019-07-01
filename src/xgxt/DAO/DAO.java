/*
 * �������� 2006-8-24
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.DAO;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import oracle.sql.BLOB;
import oracle.sql.CLOB;
import xgxt.action.Base;
import xgxt.action.DMLLogger;
import xgxt.base.DealString;
import xgxt.base.LogUtil;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CustomException;
import xgxt.utils.Pages;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzService;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import common.Globals;

/**
 * @author bat_zzj
 * 
 * Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class DAO {
	protected TranscationManager transcationManager = TranscationManager.getInstance();
	private static DAO dao = new DAO();
	
	private DataSource db = null;

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

	public DAO() {
		
		 this.db = com.zfsoft.xgxt.base.db.DBPool.getPool();
	}
	
	public DAO(DataSource db){
		this.db = db;
	}

	public static DAO getInstance() {
		return dao;
		
	}


	public DAO(String Ip) {
		// ���캯��������һ������IP����ʼ��IP��MAC����д����־ʱʹ�á�
		this();
		this.ip = Ip;
		this.mac = getMacAddressIP(ip);
	}

	public String getMacAddressIP(String remotePcIP) {
		// ͨ��IP��ȡMAC
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

//	public void closeStmt() {
//		// ͨ��ģ�飬�ر���������
//		try {
//			if (rs != null) {
//				this.rs.close();
//			}
//			if (stmt != null) {
//				this.stmt.close();
//			}
//			if (stat != null) {
//				this.stat.close();
//			}
//			if (cstmt != null) {
//				this.cstmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void closeFromResultSet(ResultSet rs) {
		 Statement stat = null;
		 Connection conn = null;
		 
		try {
			stat = rs.getStatement();
			conn = stat.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * �رմ����ResultSet��PreparedStatement��Statement��CallableStatement
	 * 
	 * @param rs
	 * @param pstmt
	 * @param stat
	 * @param cstmt
	 */
	public void closeAllStmtAndRs(ResultSet rs, PreparedStatement pstmt,
			Statement stat, CallableStatement cstmt) {
		// ͨ��ģ�飬�ر���������
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
	

	/**
	 * ���ڹر����ݿ�����,���뱣֤�Ѿ�ʹ������ResultSet��Statement, ���򽫳���ʹ��closed ResultSet��closed
	 * Statement�쳣
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void closeConnection() {
//		try {
//			if (conn != null && !conn.isClosed()) {
//				//conn.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public String[] getColumnName(String sql){
		// ͨ��SQL����ȡ���ݱ�������������������
		String[] tit = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
		conn = db.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		rsmd = rs.getMetaData();
			tit = new String[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				tit[i] = rsmd.getColumnLabel(i + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			tit = new String[0];
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return tit;
	}

	public String[] getColumnNameCN(String[] cName, String tabName) {
		// �����ݱ�Ӣ������ת��Ϊ����������������������[��������]���飨����ΪӢ����������ͱ�����
		String[] tit = new String[cName.length];
		String sql = "select COMMENTS from user_col_comments where table_name=? and COLUMN_NAME=?";
		for (int i = 0; i < cName.length; i++) {
			String[] tmp = getOneRs(sql, new String[] { tabName.toUpperCase(),
					cName[i].toUpperCase() }, new String[] { "COMMENTS" });
			if (tmp == null) {
				tit[i] = cName[i];
			} else {
				tit[i] = tmp[0];
				if ((tit[i] == null)
						|| tit[i].toLowerCase().equalsIgnoreCase("null")
						|| tit[i].equalsIgnoreCase("")) {
					tit[i] = cName[i];
				}
				if (tit[i].indexOf('(') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('('));
				}
				if (tit[i].indexOf('��') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('��'));
				}
			}
		}
		return tit;
	}

	public String[] getColumnNameCNAndSm(String[] cName, String tabName) {
		// �����ݱ�Ӣ������ת��Ϊ��������������������������˵�������飬ģ��ר��
		String[] tit = new String[cName.length];
		String sql = "select b.COMMENTS zdsm from user_tab_cols a left join user_col_comments b on a.COLUMN_NAME = b.COLUMN_NAME and a.table_name =b.table_name where a.table_name=? and a.COLUMN_NAME=?";
		for (int i = 0; i < cName.length; i++) {
			String[] tmp = getOneRs(sql, new String[] { tabName.toUpperCase(),
					cName[i].toUpperCase() }, new String[] { "zdsm" });
			if (tmp == null) {
				tit[i] = cName[i];
			} else {
				tit[i] = tmp[0];
				if ((tit[i] == null)
						|| tit[i].toLowerCase().equalsIgnoreCase("null")
						|| tit[i].equalsIgnoreCase("")) {
					tit[i] = cName[i];
				}
			}
		}
		return tit;
	}

	public String[] getColumnNamePostil(String[] cName, String tabName) {
		// �����ݱ�Ӣ������ת��Ϊ��������������������������˵�������飬ģ��ר��
		String[] tit = new String[cName.length];
		String sql = "select '��󳤶�:'||a.DATA_LENGTH||',   �Ƿ����Ϊ��:'||decode(a.NULLABLE,'N','��','��')||',   �Ƿ���Ĭ��ֵ:'"
				+ "||(case when a.data_default is null then '��' else '��' end)||',    �������ͣ�'||a.DATA_TYPE postil from user_tab_cols a left join user_col_comments b "
				+ "on a.COLUMN_NAME = b.COLUMN_NAME and a.table_name =b.table_name where a.table_name=? and a.COLUMN_NAME=?";
		for (int i = 0; i < cName.length; i++) {
			String[] tmp = getOneRs(sql, new String[] { tabName.toUpperCase(),
					cName[i].toUpperCase() }, new String[] { "postil" });
			if (tmp == null) {
				tit[i] = cName[i];
			} else {
				tit[i] = tmp[0];
				if ((tit[i] == null)
						|| tit[i].toLowerCase().equalsIgnoreCase("null")
						|| tit[i].equalsIgnoreCase("")) {
					tit[i] = cName[i];
				}
			}
		}
		return tit;
	}

	public String[] getColumnNameCNLong(String[] cName, String tabName) {
		// �����ݱ�Ӣ������ת��Ϊ����������������������[������]���飨����ΪӢ����������ͱ�����
		String[] tit = new String[cName.length];
		String sql = "select COMMENTS from user_col_comments where table_name=? and COLUMN_NAME=?";
		for (int i = 0; i < cName.length; i++) {
			String[] tmp = getOneRs(sql, new String[] { tabName.toUpperCase(),
					cName[i].toUpperCase() }, new String[] { "COMMENTS" });
			if (tmp == null) {
				tit[i] = cName[i];
			} else {
				tit[i] = tmp[0];
				if ((tit[i] == null)
						|| tit[i].toLowerCase().equalsIgnoreCase("null")
						|| tit[i].equalsIgnoreCase("")) {
					tit[i] = cName[i];
				}
			}
		}
		return tit;
	}

	/**
	 * 
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public ResultSet getRS(String sql) throws Exception {
		// ��������α�������ƶ����������ݿ�仯ʱ����������䣬�����ý�����������ݿ��еı�
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		}
		// finally {
		// closeAllStmtAndRs(rs, null, stat, null);
		// closeConnection(conn);
		// }
		return rs;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet getWriteRS(String sql,String[] input) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return rs;
	}

	public List<HashMap<String, String>> getRS(String tabName, String query,
			String[] cName, int currPage) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String sql = "select count(*) num from " + tabName + " a where "
				+ query;
		int rowBegin = 0;
		int rowEnd = 0;
		try {
			conn = db.getConnection();
			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stat.executeQuery(sql);
			rs.next();
			int rowCount = rs.getInt("num");
			if (pageSize == -1) {
				pageSize = rowCount;
			}
			this.setRsCount(rowCount);
			int pageNum = (rowCount % pageSize) == 0 ? (rowCount / pageSize)
					: (rowCount / pageSize + 1);
			this.setPageCount(pageNum);
			if ((currPage > pageNum) || (currPage <= 1)) {
				currPage = 1;
				rowBegin = 1;
				rowEnd = pageSize;
			} else {
				rowBegin = ((currPage - 1) * pageSize + 1);
				rowEnd = currPage * pageSize;
			}
			if (rowEnd > rowCount) {
				rowEnd = rowCount;
			}
			sql = "select * from (select rownum tmp_row_set_BE,a.* from (select * from "
					+ tabName
					+ " a where "
					+ query
					+ ") a) where tmp_row_set_BE>="
					+ rowBegin
					+ " and tmp_row_set_BE<=" + rowEnd;
			this.setCurrentPage(currPage);
			rs = stat.executeQuery(sql);
			HashMap<String, String> map = new HashMap<String, String>();
			while (rs.next()) {
				map = new HashMap<String, String>();
				for (int i = 0; i < cName.length; i++) {
					map.put(cName[i], rs.getString(i + 2));
				}
				aList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			aList = new ArrayList<HashMap<String, String>>();
		} finally {
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		return aList;
	}

	public List<HashMap<String, String>> getRS(String tabName, String query,
			String columns, String[] cName, int currPage) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String sql = "select count(*) num from " + tabName + " a where "
				+ query;
		int rowBegin = 0;
		int rowEnd = 0;
		try {
			conn = db.getConnection();
			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stat.executeQuery(sql);
			rs.next();
			int rowCount = rs.getInt("num");
			if (pageSize == -1) {
				pageSize = rowCount;
			}
			this.setRsCount(rowCount);
			int pageNum = (rowCount % pageSize) == 0 ? (rowCount / pageSize)
					: (rowCount / pageSize + 1);
			this.setPageCount(pageNum);
			if ((currPage > pageNum) || (currPage <= 1)) {
				currPage = 1;
				rowBegin = 1;
				rowEnd = pageSize;
			} else {
				rowBegin = ((currPage - 1) * pageSize + 1);
				rowEnd = currPage * pageSize;
			}
			if (rowEnd > rowCount) {
				rowEnd = rowCount;
			}
			sql = "select * from (select rownum tmp_row_set_BE,a.* from (select "
					+ columns
					+ " from "
					+ tabName
					+ " a where "
					+ query
					+ ") a) where tmp_row_set_BE>="
					+ rowBegin
					+ " and tmp_row_set_BE<=" + rowEnd;
			this.setCurrentPage(currPage);
			rs = stat.executeQuery(sql);
			HashMap<String, String> map = new HashMap<String, String>();
			while (rs.next()) {
				map = new HashMap<String, String>();
				for (int i = 0; i < cName.length; i++) {
					map.put(cName[i], rs.getString(i + 2));
				}
				aList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			aList = new ArrayList<HashMap<String, String>>();
		} finally {
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		return aList;
	}

	public String getStringToSplit(String sql, String[] inputValue,
			String[] outputValue) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// ���ؿ����ڲ�ֳɶ�ά������ַ���
		String rsStr = "";
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				rsStr += "!!SplitSignOne!!";
				for (int i = 0; i < outputValue.length; i++) {
					if ((rs.getString(outputValue[i]) == null)
							|| rs.getString(outputValue[i]).equalsIgnoreCase(
									"null")) {
						rsStr += ("!!SplitSignTwo!!" + " ");
					} else {
						rsStr += ("!!SplitSignTwo!!" + rs
								.getString(outputValue[i]));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			rsStr = "Error";
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return rsStr;
	}

	public List<HashMap<String, String>> getList(String sql,
			String[] inputValue, String[] outputValue) {
		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		// 'yyyy-mm-dd hh24:mi:ss'
		String nowtime = (getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
						// ����ʱ��25
						// ȡ�����ݿ���ʱ��
						new String[] {}, new String[] { "sdate" }))[0];
		nowtime = nowtime.substring(0, 10);
		ResultSet rsa = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			
			rsa = stmt.executeQuery();
			while (rsa.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i], rsa.getString(outputValue[i]));
				}
				String pubtime = map.get("pubtime");// ����ʱ��
				if (null != pubtime && !"".equalsIgnoreCase(pubtime)
						&& (pubtime.length() == 19 || pubtime.length() == 10)) {
					String pubtime1 = pubtime.substring(0, 10);
					if (pubtime1.equalsIgnoreCase(nowtime)) {
						map.put("newmark", "new");
					}
				}
				if (null != pubtime && !"".equalsIgnoreCase(pubtime)
						&& pubtime.length() == 14) {

					pubtime = doForTime4(pubtime);
					map.put("pubtime", pubtime);
				}
				String wjffsj = map.get("wjffsj");// �ļ�����ʱ��--����ļ�����
				if (null != wjffsj && !"".equalsIgnoreCase(wjffsj)
						&& wjffsj.length() == 19) {
					wjffsj = wjffsj.substring(0, 10);
					if (wjffsj.equalsIgnoreCase(nowtime)) {
						map.put("newmark", "new");
					}
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rsa, stmt, null, null);
			closeConnection(conn);
			try {
				rsa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}
	/**
	 * author lyl 
	 * Title ѧ����ס���ͳ�Ʒ��� JDBC
	 * @param sql  
	 * @param inputValue  ��ѯ����
	 * @param outputValue  ���ƥ�����
	 * @return
	 */
	public List<HashMap<String, String>> getTjList(String sql,
			String[] inputValue, String[] outputValue) {
		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		// 'yyyy-mm-dd hh24:mi:ss'
		String nowtime = (getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
						// ����ʱ��25
						// ȡ�����ݿ���ʱ��
						new String[] {}, new String[] { "sdate" }))[0];
		nowtime = nowtime.substring(0, 10);
		ResultSet rsa = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
				stmt.setString(i + 2, inputValue[i]);
			}
			rsa = stmt.executeQuery();
			while (rsa.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i], rsa.getString(outputValue[i]));
				}
				String pubtime = map.get("pubtime");// ����ʱ��
				if (null != pubtime && !"".equalsIgnoreCase(pubtime)
						&& (pubtime.length() == 19 || pubtime.length() == 10)) {
					String pubtime1 = pubtime.substring(0, 10);
					if (pubtime1.equalsIgnoreCase(nowtime)) {
						map.put("newmark", "new");
					}
				}
				if (null != pubtime && !"".equalsIgnoreCase(pubtime)
						&& pubtime.length() == 14) {

					pubtime = doForTime4(pubtime);
					map.put("pubtime", pubtime);
				}
				String wjffsj = map.get("wjffsj");// �ļ�����ʱ��--����ļ�����
				if (null != wjffsj && !"".equalsIgnoreCase(wjffsj)
						&& wjffsj.length() == 19) {
					wjffsj = wjffsj.substring(0, 10);
					if (wjffsj.equalsIgnoreCase(nowtime)) {
						map.put("newmark", "new");
					}
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rsa, stmt, null, null);
			closeConnection(conn);
			try {
				rsa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}
	public ArrayList<HashMap<String, String>> getArrayList(String sql,
			String[] inputValue, String[] outputValue) {

		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
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
					map.put(outputValue[i], DealString.toGBK(rs.getString(outputValue[i])));
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

	public ArrayList<HashMap<String, String>> getArrayList2(String sql,
			String[] inputValue, String[] outputValue) {

		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
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
				}
				if (map.get("fbsj") != null && !"".equals(map.get("fbsj"))) {
					map.put("fbsj", doForTime2(map.get("fbsj")));
				}
				if (map.get("sqsj") != null && !"".equals(map.get("sqsj"))) {// ����Ƽ�ѧ��֤����-����ʱ��
					map.put("sqsj", doForTime2(map.get("sqsj")));
				}
				if (map.get("bbsj") != null && !"".equals(map.get("bbsj"))) {// ����Ƽ�ѧ��֤����-����ʱ��
					map.put("bbsj", doForTime2(map.get("bbsj")));
				}
				if (map.get("djsj") != null && !"".equals(map.get("djsj"))) {// ����ְҵ-ѧ����ҵ��Ϣ�Ǽ�ʱ��
					map.put("djsj", doForTime2(map.get("djsj")));
				}
				if (map.get("tdsj") != null && !"".equals(map.get("tdsj"))) {// ��ҵ��-��λ�鿴Ͷ�ݼ���
					map.put("tdsj", doForTime2(map.get("tdsj")));
				}
				if ("%".equalsIgnoreCase(map.get("qyl"))) { // ����ְҵ-ѧ����ҵͳ��
					map.put("qyl", "");
				}
				if ("%".equalsIgnoreCase(map.get("jyl"))) { // ����ְҵ-ѧ����ҵͳ��
					map.put("jyl", "");
				}
				if ("%".equalsIgnoreCase(map.get("dkl"))) { // ����ְҵ-ѧ����ҵͳ��
					map.put("dkl", "");
				}
				if ("%".equalsIgnoreCase(map.get("bl"))) { // ����ְҵ-ѧ����ҵͳ��
					map.put("bl", "");
				}
				if (map.get("rid") != null && map.get("btrowid") != null) {// ���������̳
					// ͳ�ƻ�����
					String[] tieziinfo = getOneRs(
							"select bt,bk from syltb where rowid=?",
							new String[] { map.get("rid") }, new String[] {
									"bt", "bk" });
					if (null != tieziinfo) {
						String hfs = getOneRs(
										"select count(*) from syltb where bt=? and bk=?",
										new String[] { tieziinfo[0],
												tieziinfo[1] }, "count(*)");
						int temhfs = Integer.parseInt(hfs);
						temhfs = temhfs - 1;
						hfs = String.valueOf(temhfs);
						map.put("hfs", hfs);
					}
				}
				// ��Գ�ɳ����(����ģ��Ҳ��ʹ��)
				if (map.get("fbr") != null && !"".equals(map.get("fbr"))) {
					String[] fbrinfo = getOneRs(
							"select xm,szbm from yhb where yhm=?",
							new String[] { map.get("fbr") }, new String[] {
									"xm", "szbm" });
					if (null != fbrinfo) {
						String bmmc = getOneRs(
								"select bmmc from zxbz_xxbmdm where bmdm=?",
								new String[] { fbrinfo[1] }, "bmmc");
						if (null != bmmc) {
							map.put("bmmc", bmmc);
						}
						map.put("fbrxm", fbrinfo[0]);
					}
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

	public ArrayList<HashMap<String, String>> getArrayList3(String sql,
			String[] inputValue, String[] outputValue) {

		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		String nowtime = (getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				// ����ʱ��25
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		dealDate dealdate = new dealDate();
		String sjyear = nowtime.substring(0, 4);
		String sjmonth = nowtime.substring(4, 6);
		String sjday = nowtime.substring(6, 8);
		nowtime = sjyear + "-" + sjmonth + "-" + sjday;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
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
				}
				map.put("allnewstitle", map.get("newstitle"));
				map.put("allwjbt", map.get("wjbt"));
				map.put("allgsmc", map.get("gsmc"));

				String pubtime = map.get("pubtime");// ����ʱ��
				
				if (null != pubtime
						&& !"".equalsIgnoreCase(pubtime)) {
					String pubtime1 = pubtime.replaceAll("-", "");
					pubtime1 = pubtime1.replaceAll(":", "");
					pubtime1 = pubtime1.replaceAll(" ", "");
					pubtime1 = pubtime1.substring(0, 8);
					String temptime = dealdate.getDate2(Integer.parseInt("-3"), nowtime);
					temptime = temptime.replaceAll("-", "");
					if(pubtime1.compareTo(temptime)>=0){
						map.put("newmark", "new");
					}
				}
				
				String fbsj = map.get("fbsj");// ����ʱ��
				
				if (null != fbsj
						&& !"".equalsIgnoreCase(fbsj)) {
					String fbsj1 = fbsj.replaceAll("-", "");
					fbsj1 = fbsj1.replaceAll(":", "");
					fbsj1 = fbsj1.replaceAll(" ", "");
					fbsj1 = fbsj1.substring(0, 8);
					String temptime = dealdate.getDate2(Integer.parseInt("-3"), nowtime);
					temptime = temptime.replaceAll("-", "");
					if(fbsj1.compareTo(temptime)>=0){
						map.put("newmark", "new");
					}
				}

				if(map.get("pubtime") != null
						&& !"".equals(map.get("pubtime"))&&map.get("pubtime").length()==14){
					map.put("pubtime", doForTime3(map.get("pubtime")));
				}else if (map.get("pubtime") != null
						&& !"".equals(map.get("pubtime"))) {
					map.put("pubtime", doForTime5(map.get("pubtime")));
				}
				if(map.get("fbsj") != null
						&& !"".equals(map.get("fbsj"))&&map.get("fbsj").length()==14){
					map.put("fbsj", doForTime3(map.get("fbsj")));
				}else if (map.get("fbsj") != null && !"".equals(map.get("fbsj"))) {
					map.put("fbsj", doForTime2(map.get("fbsj")));
				}
				if (map.get("newstitle") != null
						&& !"".equals(map.get("newstitle"))
						&& map.get("newstitle").length() > 18) {
					String newstitle = map.get("newstitle").substring(0, 18);
					newstitle += "��";
					map.put("newstitle", newstitle);

				}
				if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
						&& map.get("wjbt").length() > 16) {
					String wjbt = map.get("wjbt").substring(0, 16);
					wjbt += "��";
					map.put("wjbt", wjbt);
				}
				if (map.get("bt") != null && !"".equals(map.get("bt"))
						&& map.get("bt").length() > 6) {
					String bt = map.get("bt").substring(0, 6);
					bt += "��";
					map.put("bt", bt);
				}
				if (map.get("gsmc") != null && !"".equals(map.get("gsmc"))
						&& map.get("gsmc").length() > 18) {
					String gsmc = map.get("gsmc").substring(0, 18);
					gsmc += "��";
					map.put("gsmc", gsmc);
				}
				if (map.get("zpzw") != null && !"".equals(map.get("zpzw"))
						&& map.get("zpzw").length() > 8) {
					String zpzw = map.get("zpzw").substring(0, 8);
					zpzw += "��";
					map.put("zpzw", zpzw);
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

	public List<HashMap<String, String>> getList(String sql,
			String[] inputValue, String[] outputValue,
			ArrayList<HashMap<String, String>> arrayList) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// ����List�ṹ�Ľ����
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
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

	public List<String[]> getSrlyList() {

		// ����List�ṹ�Ľ����
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select dm srly_dm,mc srly_mc from bjlydx_knssrly order by dm";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("srly_dm"),
						rs.getString("srly_mc") };
				arrayList.add(st);
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

	public ArrayList<String[]> getCqkjLstdList(String sql) {

		// ����List�ṹ�Ľ����
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("xh"),
						rs.getString("xm"), rs.getString("sqsj"),
						rs.getString("hjyy"), rs.getString("xxspsj"),
						rs.getString("tyhjxf"), rs.getString("jzrq") };
				arrayList.add(st);
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

	public List<Vector<String>> getVectorList(String sql, String[] inputValue,
			String[] outputValue) {

		// ����List�ṹ�Ľ����
		ArrayList<Vector<String>> arrayList = new ArrayList<Vector<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();

			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				for (int i = 0; i < outputValue.length; i++) {
					vector.add(rs.getString(outputValue[i]));
				}
				arrayList.add(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}

	// ������������Ľ����
	// ������sql��䣬����string�������͵�ֵ�����string�������͵�ֵ��
	public String[] getOneRs(String sql, String[] inputValue,
			String[] outputValue) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String[] result = new String[outputValue.length];
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				for (int i = 0; i < outputValue.length; i++) {
					result[i] = rs.getString(outputValue[i]);
				}
			} else {
				result = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			result = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}

	// ���ͳ������
	public int getOneRsint(String sql) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			String b = "";
			if (rs.next()) {
				b = rs.getString(1);
			}
			result = Integer.valueOf(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}

		return result;
	}

	/**
	 * ֻ�����ڷ���һά������
	 */
	public String[] getRs(String sql, String[] input, String output)
			throws Exception {

		String[] result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			rs = stmt.executeQuery();
			rs.first();
			int size = 0;
			if (rs.last()) {
				size = rs.getRow();
			}
			result = new String[size];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				result[i] = rs.getString(output);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}

	/**
	 * ��ʱ����ַ���ת��,ʱ��ĸ�ʽ����Ϊ'yyyymmddhhmiss'
	 * 
	 */
	public String doForTime(String time) {
		String aftertime = time.substring(0, 4) + "��" + time.substring(4, 6)
				+ "��" + time.substring(6, 8) + "�� " + time.substring(8, 10)
				+ ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
		return aftertime;
	}

	// ��ʱ����ַ���ת��Ϊ���β��
	public String doForTime2(String time) {
		String aftertime = time.substring(0, 4) + "��" + time.substring(4, 6)
				+ "��" + time.substring(6, 8) + "�� ";
		return aftertime;
	}

	// ��ʱ����ַ���ת��Ϊxxxx-xx-xx
	public String doForTime3(String time) {
		String aftertime = time.substring(0, 4) + "-" + time.substring(4, 6)
				+ "-" + time.substring(6, 8);
		return aftertime;
	}
	


	// ��ʱ����ַ���ת��Ϊxxxx-xx-xx
	public String doForTime4(String time) {
		String aftertime = time.substring(0, 4) + "-" + time.substring(4, 6)
				+ "-" + time.substring(6, 8) + " " + time.substring(8, 10)
				+ ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
		return aftertime;
	}
	
	// ��ʱ����ַ���ת��Ϊxxxx-xx-xx
	public String doForTime5(String time) {
		String aftertime = time.substring(0, 10);
		return aftertime;
	}

	public String getOneRs(String sql, String[] inputValue, String outputValue) {
		String tmp[] = getOneRs(sql, inputValue, new String[] { outputValue });
		String res = (tmp == null ? "" : tmp[0]);
		return res;
	}

	public HashMap<String, String> getMap(String sql, String[] inputValue,
			String[] outputValue) {
		HashMap<String, String> map = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i], rs.getString(outputValue[i]));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			map = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return map;
	}

	public boolean runUpdateTab(String sql, String tableName, User user) throws Exception {

		// ִ���޸ı�ṹ�����
		boolean result = true;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		//д������־
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "modify :");
			dmlLogger.insertLog(sql, null, user);
		}
		return result;
	}
	
	public boolean runUpdateTab(String sql) throws Exception {

		// ִ���޸ı�ṹ�����
		boolean result = true;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		return result;
	}

	public synchronized boolean runUpdate(String sql, String[] input) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	public  boolean runUpdateNotCommit(String sql, String[] input) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = transcationManager.getConnection();
			//conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
		    stmt.executeUpdate();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			//throw e;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @����:���²���������Ӱ���¼��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����03:22:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public synchronized int update(String sql, String[] input) throws Exception {

		// ִ�и������
		int num = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			num = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		
		return num;
	}
	
	
	
	/**
	 * ɾ������������Ӱ���¼����   �쳣����ֵ�޸� 0->-1 20170613
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public synchronized int runDelete(String sql, String[] input) throws Exception {

		// ִ�и������
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return -1;
	}
	
	/**
	 * ɾ������������Ӱ���¼����   �쳣����ֵ�޸� 0->-1 20170613
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public  int runDeleteNotCommit(String sql, String[] input) throws Exception {

		// ִ�и������
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = transcationManager.getConnection();
			//conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			return stmt.executeUpdate();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
		}
		return -1;
	}
	
	public synchronized boolean runUpdate(String sql, 
			                              String[] input, 
			                              String tableName, 
			                              User user) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//д������־
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql, input, user);
		}
		return result;
	}

	public boolean runUpdate2(String sql, String[] input) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				if(input[i]!=null){
					stmt.setString(i + 1, input[i]);
				}
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
			throw e;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	
	public boolean runUpdate2(String sql, 
							  String[] input, 
							  String tableName, 
							  User user) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
			throw e;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//д������־
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql, input, user);
		}
		return result;
	}

	/**
	 * ִ���޸Ĳ����������޸ĳɹ�������
	 * @param sql
	 * @param input
	 * @param tableName
	 * @return int
	 */
	public int runUpdate(String sql, String[] input, String tableName)
			throws Exception {

		// ִ�и������
		int iResultNum = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			iResultNum = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			iResultNum = 0;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return iResultNum;
	}
	
	

	/**
	 * @param sql
	 * @param input
	 *            ��ʽ�����ǣ�asdf!@fadsaf!@..... (��Ч)
	 * @return
	 * @throws Exception
	 */
//	public boolean runUpdate(String sql, String input) throws Exception {
//
//		// ִ�и������
//		boolean result = true;
//		try {
//			conn = db.getConnection();
//			conn.setAutoCommit(false);
//			cstmt = conn.prepareCall("{call DMLOperation(?,?)}");
//			cstmt.setString(1, sql);
//			cstmt.setString(2, input);
//			cstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			CustomException.pringCustomExcInfo(sql);
//			conn.rollback();
//			result = false;
//		} finally {
//			conn.commit();
//			closeAllStmtAndRs(rs, null, null, cstmt);
//			closeConnection(conn);
//		}
//		return result;
//	}

	public boolean runUpdate(Vector v) throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			for (int i = 0; i < v.size(); i++) {
				HashMap map = new HashMap();
				map = (HashMap) v.get(i);
				String[] inVal = (String[]) map.get("sqlVal");
				stmt = conn.prepareStatement(map.get("sqlTxt").toString());
				for (int j = 0; j < inVal.length; j++) {
					stmt.setString(j + 1, inVal[j]);
				}
				stmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}

	public boolean runUpdate(String sql, ArrayList<String> input)
			throws Exception {

		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			int i = 0;
			for (Iterator<String> it = input.iterator(); it.hasNext(); i++) {
				String temp = it.next();
				stmt.setString(i + 1, temp);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input.toArray(new String[input.size()]));
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	
	public boolean runUpdate(String sql, 
							 ArrayList<String> input,
							 String tableName, 
							 User user) throws Exception {
		// ִ�и������
		boolean result = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			int i = 0;
			for (Iterator<String> it = input.iterator(); it.hasNext(); i++) {
				String temp = it.next();
				stmt.setString(i + 1, temp);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input.toArray(new String[input.size()]));
			conn.rollback();
			result = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//д������־
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql, input.toArray(new String[]{}), user);
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public boolean addNews(String sNewsTitle, String sPart, String sContent,
			String sPuber) throws Exception {
		// �������
		boolean res = true;
		String sql = "insert into newscontent(newsid,newstitle,newspart,newscont,puber) values(s_news_id.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newscont like 'N' for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			throw e;
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// ��ҵ���������¶�̬
	@SuppressWarnings("deprecation")
	public boolean addZxdt(String sNewsTitle, String sPart, String sContent,
			String sPuber) throws Exception {
		// �������
		boolean res = true;
		String sql = "insert into newscontent(newsid,newstitle,newspart,newscont,puber) values(s_news_id.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newscont like 'N' for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean addLy(String ip, String yhm, String ly, String fbsj)
			throws Exception {
		// ��Ӿ�ҵ�����԰�����
		boolean res = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into jygl_lyb(id,ip,yhm,ly,fbsj) values(jyweb_lyb_id.NEXTVAL,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ip);
			stmt.setString(2, yhm);
			stmt.setString(3, ly);
			stmt.setString(4, fbsj);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean addMessage(String wjbt, String wjlx, String wjnr,
			String fbr, String fbsj) throws Exception {
		// ��Ӿ�ҵ����Ŀ����
		boolean res = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into jygl_zcwjb(wjbt,wjlx,wjnr,fbr,fbsj) values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, wjbt);
			stmt.setString(2, wjlx);
			stmt.setString(3, wjnr);
			stmt.setString(4, fbr);
			stmt.setString(5, fbsj);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean addNews2(String sNewsTitle, String sPart, String sContent,
			String sPuber, String towho,String newsType,String fwfs,String bmdm) throws Exception {
		// �������
		boolean res = true;
		String sql = "insert into newscontent(newstitle,newspart,newscont,puber,towho,xwlx,fwfs,bmdm,newssign) values(?,?,?,?,?,?,?,?,s_news_id.nextval)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.setString(5, towho);
			stmt.setString(6, newsType);
			stmt.setString(7, fwfs);
			stmt.setString(8, bmdm);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newscont like 'N' ";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean updateNews(String sNewsTitle, String sPart, String sContent,
			String sPuber, String newsId) throws Exception {
		// ��������
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		boolean res = true;
		String sql = "update newscontent set newstitle=?,newspart=?,newscont=?,puber=? where newsId=?";
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.setString(5, newsId);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newsid=? for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newsId);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// ��ɳ���������޸�
	@SuppressWarnings("deprecation")
	public boolean updateNews(String sNewsTitle, String sPart, String sContent,
			String newsId) throws Exception {
		// ��������
		boolean res = true;
		String sql = "update newscontent set newstitle=?,newspart=?,newscont=? where newsId=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, newsId);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newsid=? for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newsId);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// �����޸ķ�ȫ������ʦ��ѧ���ɼ�
	@SuppressWarnings("deprecation")
	public boolean updateNews2(String sNewsTitle, String sPart,
			String sContent, String sPuber, String newsId, String towho,String newsType,String fwfs)
			throws Exception {
		// ��������
		boolean res = true;
		String sql = "update newscontent set newstitle=?,newspart=?,newscont=?,puber=?,towho=?,xwlx=?,fwfs=? where newsId=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.setString(5, towho);
			stmt.setString(6, newsType);
			stmt.setString(7, fwfs);
			stmt.setString(8, newsId);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newsid=? for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newsId);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean runProcuder(String sql, String[] input) throws Exception {
		// ִ�д洢����
		boolean result = true;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = db.getConnection();
			cstmt = conn.prepareCall(sql);
			for (int i = 0; i < input.length; i++) {
				cstmt.setString(i + 1, input[i]);
			}
			cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			result = false;
			throw e;
		} finally {
			closeAllStmtAndRs(null, cstmt, null, cstmt);
			closeConnection(conn);
		}
		return result;
	}

	/** ��ȡ�洢���̷���ֵ */
	public String[] getProVal(String sql, String[] input, int[] output)
			throws Exception {

		String[] fhbj = new String[output.length];
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = db.getConnection();
			cstmt = conn.prepareCall(sql);
			for (int i = 0; i < input.length; i++) {
				cstmt.setString(i + 1, input[i]);
			}
			for (int i = 0; i < output.length; i++) {
				cstmt.registerOutParameter(output[i], Types.VARCHAR);
			}
			cstmt.execute();
			for (int i = 0; i < output.length; i++) {
				fhbj[i] = cstmt.getString(output[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
		} finally {
			closeAllStmtAndRs(null, null, null, cstmt);
			closeConnection(conn);
		}
		return fhbj;
	}

	/**
	 * @param czsql
	 *            �ͻ���������sql���
	 * @param newVal
	 *            �ͻ���������������
	 * @param oldVal
	 *            �ͻ���������������
	 * @param yhcz
	 *            �ͻ������ľ����������
	 * @param request
	 * @throws Exception
	 */
	public void writeLog(String czsql, String[] newVals, String[] oldVals,
			String yhcz, HttpServletRequest request) throws Exception {
		// д�������־
		HttpSession session = request.getSession();
		String ip = request.getRemoteAddr();
		String clienttime = new Long(request.getDateHeader("Date")).toString();
		String host = request.getRemoteHost();
		String yhm = session.getAttribute("userName") != null ? session.getAttribute("userName").toString() : "";
		StringBuffer newVal = new StringBuffer();
		StringBuffer oldVal = new StringBuffer();
		if (newVals != null) {
			for (int i = 0; i < newVals.length; i++) {
				newVal.append(newVals[i]);
				newVal.append(",");
			}
		}
		if (oldVals != null) {
			for (int i = 0; i < oldVals.length; i++) {
				oldVal.append(oldVals[i]);
				oldVal.append(",");
			}
		}
		String[] colVal = { yhm, yhcz, ip, mac, host, clienttime, newVal.toString(),
				oldVal.toString(), czsql };
		String sql = "insert into czrzb(rzxh,yhm,yhcz,ip,mac,host,clienttime,newval,oldval,sql) values(lsh_log.nextval,?,?,?,?,?,?,?,?,?)";
		//TODO ��������ʱ��Ҫ
		LogUtil.maniLogs(colVal, dao, sql);
		//TODO ����ʱ��Ҫ
//		LogUtil.maniLogs(colVal, this, sql);
//		Connection conn = null;
//		PreparedStatement stmt = null;
//
//		try {
//			conn = db.getConnection();
//			conn.setAutoCommit(false);
//			stmt = conn.prepareStatement(sql);
//			for (int i = 1; i <= colVal.length; i++) {
//				stmt.setString(i, colVal[i - 1]);
//			}
//			stmt.executeUpdate();
//			closeStmt();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			CustomException.pringCustomExcInfo(sql);
//			conn.rollback();
//		} finally {
//			conn.commit();
//			closeAllStmtAndRs(rs, stmt, null, null);
//			closeConnection(conn);
//		}
	}

	/**
	 * @param czsql
	 *            �ͻ���������sql���
	 * @param newVal
	 *            �ͻ���������������
	 * @param oldVal
	 *            �ͻ���������������
	 * @param yhcz
	 *            �ͻ������ľ���������� �����ǰ���Ӧ�Ŀͻ��˵���Ϣֱ�Ӵ�����
	 * @param ip
	 * @param clienttime
	 * @param host
	 * @param mac
	 * @param yhm
	 * @throws Exception
	 */
	public void writeLog(String czsql, String[] newVals, String[] oldVals,
			String yhcz, String ip, String clienttime, String host, String mac,
			String yhm) throws Exception {
		// д�������־
		String newVal = "";
		String oldVal = "";
		if (newVals != null) {
			for (int i = 0; i < newVals.length; i++) {
				newVal += newVals[i];
				newVal += ",";
			}
		}
		if (oldVals != null) {
			for (int i = 0; i < oldVals.length; i++) {
				oldVal += oldVals[i];
				oldVal += ",";
			}
		}
		String[] colVal = { yhm, yhcz, ip, mac, host, clienttime, newVal,
				oldVal, czsql };
		String sql = "insert into czrzb(rzxh,yhm,yhcz,ip,mac,host,clienttime,newval,oldval,sql) values(lsh_log.nextval,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= colVal.length; i++) {
				stmt.setString(i, colVal[i - 1]);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
	}

	public List<HashMap<String, String>> arrayToList(String[] arr1, String[] arr2) {
		// ����������ϲ���һ��List�����������Сһ�£�ͨ��Ϊ��Ӣ�Ķ��ա�����Ҫ��Ӣ����ǰ�������ں�
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("en", arr1[i]);
			map.put("cn", arr2[i]);
			list.add(map);
		}
		return list;
	}

	public ArrayList<String[]> rsToVator(String sql, String[] inputValue,
			String[] outputValue) {
		// ���ؽ����(ArrayList:��ά����)
		ArrayList<String[]> vector = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {	
					String tempstr = rs.getString(outputValue[i]);
					tmp[i] = (((tempstr != null)
							&& (outputValue[i].contains("sj")) && (tempstr
							.length() == 14)) ? tempstr.substring(0, 4) + "��"
							+ tempstr.substring(4, 6) + "��"
							+ tempstr.substring(6, 8) + "�� "
							+ tempstr.substring(8, 10) + ":"
							+ tempstr.substring(10, 12) + ":"
							+ tempstr.substring(12, 14) : tempstr);
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// �޸Ŀ�ʼ
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// �޸Ľ���
				}

				vector.add(tmp);
				tmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			vector = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return vector;
	}

	public ArrayList<String[]> rsToVator3(String sql, String[] inputValue,
			String[] outputValue) {
		// ���ؽ����(ArrayList:��ά����)
		ArrayList<String[]> vector = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					tmp[i] = tempstr;
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// �޸Ŀ�ʼ
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// �޸Ľ���
				}

				vector.add(tmp);
				tmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			vector = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return vector;
	}

	// ���ص�ʱ�侫ȷ����
	public ArrayList<String[]> rsToVator2(String sql, String[] inputValue,
			String[] outputValue) {
		// ���ؽ����(Vector:��ά����)
		ArrayList<String[]> vector = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					tmp[i] = (((tempstr != null)
							&& (outputValue[i].contains("sj")) && (tempstr
							.length() == 14)) ? tempstr.substring(0, 4) + "��"
							+ tempstr.substring(4, 6) + "��"
							+ tempstr.substring(6, 8) + "�� " : tempstr);
					if (outputValue[i].contains("TIME")) {
						tmp[i] = tempstr.substring(0, 4) + "��"
								+ tempstr.substring(5, 7) + "��"
								+ tempstr.substring(8, 10) + "�� ";
					}
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// �޸Ŀ�ʼ
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// �޸Ľ���
				}

				vector.add(tmp);
				tmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			vector = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return vector;
	}
	/**�й����ʴ�ѧ���˵�λ����ѧ����λ������--���ز��޸�ʱ��*/
	public ArrayList<String[]> rsToVator4(String sql, String[] inputValue,
			String[] outputValue) {
		// ���ؽ����(ArrayList:��ά����)
		ArrayList<String[]> vector = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					tmp[i] = tempstr;
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// �޸Ŀ�ʼ
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// �޸Ľ���
				}

				vector.add(tmp);
				tmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			vector = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return vector;
	}
	
	public String listToString(List list, String[] cols) {
		String str = "";
		HashMap map = null;
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				str = str + "!!SplitSignOne!!";
			}
			map = (HashMap) list.get(i);
			for (int j = 0; j < cols.length; j++) {
				if (j != 0) {
					str = str + "!!SplitSignTwo!!";
				}
				str = str + (String) map.get(cols[j]);
			}
		}
		return str;
	}

	public String listToString2(List list, String[] cols) {
		String str = "";
		HashMap map = null;
		for (int i = 1; i < list.size(); i++) {
			str = str + "!!SplitSignOne!!!!SplitSignTwo!!";
			map = (HashMap) list.get(i);
			for (int j = 0; j < cols.length; j++) {
				if (j != 0) {
					str = str + "!!SplitSignTwo!!";
				}
				str = str + (String) map.get(cols[j]);
			}
		}
		return str;
	}

	public List<HashMap<String, String>> getXnndList() {
		// ���ز�ѯʱʹ�õ�ѧ�ꡢ����б�
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("nd", "");
		map.put("xn", "");
		aList.add(map);
		// int currentNd = (new Date()).getYear() + 1900;
		//int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
		//		4));
		//��ϵͳ��ǰ��ȸ�Ϊ����ĵ�ǰ��ȣ�������ȡ���ݿ⵱ǰʱ��
		int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)){
			for (int i = currentNd - 8; i < currentNd + 3; i++) {
				map = new HashMap<String, String>();
				nd = String.valueOf(i);
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("nd", nd);
				map.put("xn", xn);
				aList.add(map);
			}
		}else{
			for (int i = currentNd - 5; i < currentNd + 5; i++) {
				map = new HashMap<String, String>();
				nd = String.valueOf(i);
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("nd", nd);
				map.put("xn", xn);
				aList.add(map);
			}
		}
		return aList;
	}
	
	
	//ȥ��ѧ�����
	public List<HashMap<String, String>> getXnndList2() {
		// ���ز�ѯʱʹ�õ�ѧ�ꡢ����б�
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		// int currentNd = (new Date()).getYear() + 1900;
		//int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
		//		4));
		//��ϵͳ��ǰ��ȸ�Ϊ����ĵ�ǰ��ȣ�������ȡ���ݿ⵱ǰʱ��
		int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)){
			for (int i = currentNd - 8; i < currentNd + 3; i++) {
				map = new HashMap<String, String>();
				nd = String.valueOf(i);
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("nd", nd);
				map.put("xn", xn);
				aList.add(map);
			}
		}else{
			for (int i = currentNd - 5; i < currentNd + 5; i++) {
				map = new HashMap<String, String>();
				nd = String.valueOf(i);
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("nd", nd);
				map.put("xn", xn);
				aList.add(map);
			}
		}
		return aList;
	}
	
	public List<HashMap<String, String>> getQsList() {
		// ��ȡ���ұ���б�
		String sql = "select distinct ssbh from zxbz_xsssxx order by ssbh";
		return getList(sql, new String[] {}, new String[] { "ssbh" });
	}

	public List<HashMap<String, String>> getYdlbList() {
		// ��ȡ�춯����б�
		String sql = "select distinct ydlbm ydlbdm,ydlbmc from dm_ydlb order by ydlbdm";
		return getList(sql, new String[] {},
				new String[] { "ydlbdm", "ydlbmc" });
	}

	public List<HashMap<String, String>> getNjList() {
		// ��ȡ�꼶�б�
		String sql = "select distinct nj from view_njxyzybj order by nj";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}
	
	public List<HashMap<String, String>> getNjallList() {
		// ��ȡ�꼶�б�
		String sql = "select distinct nj from view_njxyzybj_all order by nj";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}

	public List<HashMap<String, String>> getSjList() {
		// ��ȡ�꼶�б�
		String sql = "select sj from xssjb order by sj";
		return getList(sql, new String[] {}, new String[] { "sj" });
	}

	public List<HashMap<String, String>> getXqList() {
		// ��ȡѧ���б�
		String sql = "select xqdm,xqmc from xqdzb";
		// ============ begin ���ΰ 2010/4/21 ===================
		sql += " order by to_number(xqjb)";
		// ============ end ���ΰ 2010/4/21 ===================
		return getList(sql, new String[] {}, new String[] { "xqdm", "xqmc" });
	}
	
	// ====================�꼶��ѧԺ��רҵ���༶START ��ʾ���С�ѧ����׷�ӡ�=================================
	/**
	 * 
	 * @����:�����꼶�б�(���ݰ༶�����)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-23 ����07:09:31
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getNjNewList() {
		// ��ȡ�꼶�б�
		String sql = "select distinct nj from BKS_BJDM order by nj ";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}
	
	/**
	 * 
	 * @����: ��ȡѧԺ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-23 ����07:18:56
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyNewList() {
		// ��ȡѧԺ�б�
		String sql = "select distinct bmdm xydm,bmmc xymc,substr(nvl(f_pinyin(bmmc),bmmc),0,1) pyszm from ZXBZ_XXBMDM where bmlb = '5'order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc" , "pyszm"});
	}
	
	/**
	 * 
	 * @����:��ȡרҵ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-23 ����07:18:40
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyNewList(String xydm) {
		// ��ȡרҵ�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all") || xydm.equalsIgnoreCase("%")) {
			
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from BKS_ZYDM order by pyszm";
		} else {
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from BKS_ZYDM where bmdm='"
					+ xydm + "' order by pyszm";
		}
		return getList(sql, new String[] {}, new String[] { "zydm", "zymc","pyszm" });
	}
	
	/**
	 * 
	 * @����:��ȡ�༶�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-23 ����07:19:32
	 * @param xydm
	 * @param zydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjNewList(String xydm, String zydm) {
		// ��ȡ�༶�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all")) {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from BKS_BJDM order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from BKS_BJDM where zydm='"
						+ zydm + "' order by bjdm";
			}
		} else {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from BKS_BJDM a left join BKS_ZYDM b on a.zydm = b.zydm and b.bmdm ='"
						+ xydm + "' order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from BKS_BJDM where zydm='"
						+ zydm + "' order by bjdm";
			}
		}
		return getList(sql, new String[] {}, new String[] { "bjdm", "bjmc" });
	}
	
	// ====================�꼶��ѧԺ��רҵ���༶END ��ʾ���С�ѧ����׷�ӡ�=================================
	
	/**
	 * 
	 * @����:����ѧ�ڴ����ȡѧ������
	 *       �ܶ�ط�ʹ�ã�ÿ��dao��д���ظ���̫��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����02:48:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public  String getXqmcForXqdm(String xqdm) {
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[] { xqdm }, "xqmc");
	}
	public List<HashMap<String, String>> getYfList() {
		// ��ȡ�·��б�
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String yf = "";
		for (int i = 1; i < 13; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			// ============ begin ���ΰ 2009/4/21 ===================
			if (i < 10) {
				String xx = StandardOperation.getXxdm();
				if (xx.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					yf = Integer.toString(i);
				} else {
					yf = "0" + Integer.toString(i);
				}
			} else {
				yf = Integer.toString(i);
			}
			// ============ begin ���ΰ 2009/4/21 ===================
			map.put("yf", yf);
			aList.add(map);
		}
		return aList;
	}

	public String getBmmcByDm(String dm, String defaultStr) {
		// ͨ�����Ŵ����ȡ�������ƣ�������Ϊ�գ�����defaultStr
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String bmmc = "";
		if ((dm == null) || dm.equalsIgnoreCase("")
				|| dm.equalsIgnoreCase("all")) {
			return defaultStr;
		}
		String sql = "select nvl(bmmc,'"
				+ defaultStr
				+ "') from ("
				+ "select distinct xydm bmdm,xymc bmmc from view_njxyzybj union all ("
				+ "select distinct zydm,zymc from view_njxyzybj union all("
				+ "select distinct bjdm,bjmc from view_njxyzybj))"
				+ ") where bmdm=? and rownum=1";
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dm);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bmmc = rs.getString(1);
			} else {
				bmmc = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			bmmc = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return bmmc;
	}

	public List<HashMap<String, String>> getXyList() {
		// ��ȡѧԺ�б�
		String sql = "select distinct xydm,xymc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc" , "pyszm"});
	}
	
	// ��ȡѧԺ�б�(ȡview_njxyzybj_all���ݣ�û����У��ʱҲ������ʾ)
	public List<HashMap<String, String>> getXyallList() {
		String sql = "select distinct xydm,xymc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj_all order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc","pyszm" });
	}

	public List<HashMap<String, String>> getSyallList() {
		String sql = "select sydm,symc,substr(nvl(f_pinyin(symc),symc),0,1) pyszm from XG_XTWH_SYDMB order by pyszm";
		return getList(sql, new String[] {}, new String[] { "sydm", "symc","pyszm" });
	}

	//��ȡ�����꼶��У���������༶
	public List<HashMap<String,String>> getNjBjForSy(){
		String sql = "select distinct a.nj,a.bjdm,a.bjmc,b.sydm from view_njxyzybj_fdy a left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm order by bjdm";
		return getList(sql,new String[]{},new String[]{"nj","bjdm","bjmc","sydm"});
	}
	public String getXxdm() {
		// ��ȡѧУ����
//		String sql = "select xxdm from xtszb";
//		String tmp[] = getOneRs(sql, new String[] {}, new String[] { "xxdm" });
//		String res = (tmp == null ? "" : tmp[0]);
//		return res;
		return Base.xxdm;
	}

	public List<HashMap<String, String>> getZyList(String xydm) {
		// ��ȡרҵ�б�
		String sql = "";
		//2013-1-21 qph ��רҵ���Ƶ�ƴ������ĸҲ��ѯ����
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all") || xydm.equalsIgnoreCase("%")) {
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from view_njxyzybj order by pyszm";
		} else {
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from view_njxyzybj where xydm='"
					+ xydm + "' order by pyszm";
		}
		return getList(sql, new String[] {}, new String[] { "zydm", "zymc","pyszm" });
	}
	
	public List<HashMap<String, String>> getZyallList(String xydm) {
		// ��ȡרҵ�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all") || xydm.equalsIgnoreCase("%")) {
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from view_njxyzybj_all order by pyszm";
		} else {
			sql = "select distinct zydm,zymc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from view_njxyzybj_all where xydm='"
					+ xydm + "' order by pyszm";
		}
		return getList(sql, new String[] {}, new String[] { "zydm", "zymc","pyszm" });
	}

	public List<HashMap<String, String>> getBjList(String xydm, String zydm) {
		// ��ȡ�༶�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all")) {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from view_njxyzybj order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
						+ zydm + "' order by bjdm";
			}
		} else {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from view_njxyzybj where xydm='"
						+ xydm + "' order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
						+ zydm + "' order by bjdm";
			}
		}
		return getList(sql, new String[] {}, new String[] { "bjdm", "bjmc" });
	}

	public List<HashMap<String, String>> getBjallList(String xydm, String zydm) {
		// ��ȡ�༶�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all")) {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from view_njxyzybj_all order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from view_njxyzybj_all where zydm='"
						+ zydm + "' order by bjdm";
			}
		} else {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				sql = "select distinct bjdm,bjmc from view_njxyzybj_all where xydm='"
						+ xydm + "' order by bjdm";
			} else {
				sql = "select distinct bjdm,bjmc from view_njxyzybj_all where zydm='"
						+ zydm + "' order by bjdm";
			}
		}
		return getList(sql, new String[] {}, new String[] { "bjdm", "bjmc" });
	}
	
	public List<HashMap<String, String>> getBjList(String xydm, String zydm,
			String nj) {
		// �����꼶��ȡ�༶�б�
		String sql = "";
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all")) {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where nj='"
							+ nj + "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj order by bjdm";
				}
			} else {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
							+ zydm + "' and nj='" + nj + "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
							+ zydm + "' order by bjdm";
				}
			}
		} else {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where xydm='"
							+ xydm + "' and nj='" + nj + "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where xydm='"
							+ xydm + "' order by bjdm";
				}

			} else {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
							+ zydm + "' and nj='" + nj + "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj where zydm='"
							+ zydm + "' order by bjdm";
				}

			}
		}
		return getList(sql, new String[] {}, new String[] { "bjdm", "bjmc" });
	}

	public List<HashMap<String, String>> getBjList(String xydm, String zydm,
			String nj, ArrayList<String> bjdm) {
		// �����꼶��ȡ�༶�б�
		String sql = "";
		String query = "where 1=1 ";
		if (bjdm.size() != 0) {
			query += " and bjdm in ( '###'";
			for (Iterator<String> it = bjdm.iterator(); it.hasNext();) {
				query += ", '" + it.next() + "'";
			}
			query += " ) ";
		}
		if ((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all")) {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and nj='" + nj + "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " order by bjdm";
				}
			} else {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and zydm='" + zydm + "' and nj='" + nj
							+ "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and zydm='" + zydm + "' order by bjdm";
				}

			}
		} else {
			if ((zydm == null) || zydm.equalsIgnoreCase("")
					|| zydm.equalsIgnoreCase("all")) {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and xydm='" + xydm + "' and nj='" + nj
							+ "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and xydm='" + xydm + "' order by bjdm";
				}

			} else {
				if (nj != null && !(nj.trim().length() < 1)) {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and zydm='" + zydm + "' and nj='" + nj
							+ "' order by bjdm";
				} else {
					sql = "select distinct bjdm,bjmc from view_njxyzybj "
							+ query + " and zydm='" + zydm + "' order by bjdm";
				}

			}
		}

		return getList(sql, new String[] {}, new String[] { "bjdm", "bjmc" });
	}

	public ArrayList<String> getUserBj(String userName) {
		// ��ѯ����Ա�İ༶����
		ArrayList<String> bjdm = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String xxdm = Base.xxdm;
			String sql = "";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				sql = "select distinct bjdm from ( select bjdm from fdybjb where ZGH='" + userName + "' union all select bjdm from bzrbbb where ZGH='" + userName + "')";
			} else {
				sql = "select bjdm from fdybjb where ZGH='" + userName + "'";
			}
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bjdm.add(rs.getString("bjdm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return bjdm;

	}
	
	
	public List<HashMap<String, String>> getTypeList(int type){
		//��ȡ����������typeΪ���ͣ�
		String[] typeListEn = null;
		String[] typeListCn = null;
		if(type == 1){
			typeListCn = new String[]{"�ڶ�֤�������ڣ���ͨ�汾","�ڶ�֤����������ѧ��������ҵ�汾","�ڶ�֤����������ѧ��ѧλ�汾"};
			typeListEn = typeListCn;
		}
		if(type == 2){
			typeListCn = new String[]{"֪ͨ����","��������","��ʾ","�������"};
			typeListEn = new String[]{"001","002","003","004"};
		}
		return arrayToList(typeListEn, typeListCn);
	}

	public List<HashMap<String, String>> getScore(int tag) {
		// ��ȡ����������tagΪ�ȼ�����
		String[] tmp = new String[] {};
		if (tag == 5) {
			tmp = new String[] { "����", "����", "�е�", "����", "������" };
		}else if (tag == 6){
			tmp = new String[] { "����", "����", "�ϸ�", "���ϸ�"  };
		}else if (tag == 7){
			tmp = new String[] { "A��", "B��", "C��"};		
		}else if (tag == 1){
			tmp = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12"}; 
		}else{
			tmp = new String[] { "�ϸ�", "���ϸ�" };
		}
		return arrayToList(tmp, tmp);
	}

	public List<HashMap<String, String>> getChkList(int type) {
		// ��˽��������
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "�����", "δ���" };
		} else if (type == 2) {
			chkList = new String[] { "��", "��" };
		} else if (type == 3) {
			chkList = new String[] { "δ���", "ͨ��", "��ͨ��" };
		} else if (type == 4) {
			chkList = new String[] { "�ɹ�", "ʧ��" };
		} else if (type == 5) {
			chkList = new String[] { "δ���", "����", "һ������", "��ͨ��" };
		} else if (type == 6) {
			chkList = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
					"9", "10", "11", "12" };
		} else if (type == 7) {
			chkList = new String[] { "����", "��̸", "����", "�ݽ�", "�ι�", "����" };
		} else if (type == 8) {
			chkList = new String[] { "����潻��", "����ת��", "�绰��ϵ", "�ź�", "�����ʼ�",
					"�ֻ�����" };
		} else if (type == 9) {
			chkList = new String[] { "����", "һ������" };
		} else if (type == 10) {
			chkList = new String[] { "δ���", "һ������", "�ر�����", "������" };
		} else if (type == 11) {
			chkList = new String[] { "δ���", "һ������", "�Ƚ�����", "�ر�����", "������" };
		} else if (type == 12) {
			chkList = new String[] { "δ���", "һ������", "����", "��������", "������" };
		} else if (type == 13) {
			chkList = new String[] { "ʦ������", "��ʦ����", "��ְ����", "��ְר��" };
		} else if (type == 14) {
			chkList = new String[] { "δ���", "һ������", "�ر�����", "����", "��ͨ��" };
		} else if (type == 15) {
			chkList = new String[] { "δ���", "ƶ����", "������", "��ͨ��" };
		} else if (type == 16) {
			chkList = new String[] { "ѧ��", "����Ա", "����" };
		} else if (type == 17) {
			chkList = new String[] { "���д�����", "�ѷŴ�", "�ѻ���" };
		} else if (type == 18) {
			chkList = new String[] { "δ���", "���ϸ�", "�����", "���д�����", "�ѷŴ�", "�ѻ���" };
		} else if (type == 19) {
			chkList = new String[] { "δ���", "����", "��������", "������" };
		} else if (type == 20) {
			chkList = new String[] { "δ���", "����", "ƶ��", "һ��", "������" };
		} else if (type == 21) {
			chkList = new String[] { "δ���", "A��", "B��", "C��", "������" };
		} else if (type == 22) {
			chkList = new String[] { "��ʵ", "����ʵ" };
		} else if (type == 23) {
			chkList = new String[] { "A��", "B��", "C��" };
		} else if (type == 24) {
			chkList = new String[] { "���Ա�ҵ", "���ɱ�ҵ" };
		} else if (type == 25) {
			chkList = new String[] { "δ���", "һ������", "��������", "��������", "������" };
		} else if (type == 26) {
			chkList = new String[] { "δ���", "����", "�ر�����", "������" };
		} else if (type == 27) {
			chkList = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�" };
		} else if (type == 28) {
			chkList = new String[] { "δע��","ע��" };
		} else if (type == 29) {
			chkList = new String[] { "ѧ��","��ʦ" };
		} else if (type == 30) {
			chkList = new String[] { "ѧУ",Base.YXPZXY_KEY,"רҵ","�༶" };
		} else if (type == 31) {
			chkList = new String[] { "ȫ������","���ֲ���","�ɲ�����"};
		} else if (type==32){
			chkList = new String[] { "ͨ��","�˻�","��ͨ��" };
		} else if (type==33){
			chkList = new String[] { "δ���","�����","ͨ��","��ͨ��","�˻�" };
		}
		
		return arrayToList(chkList, chkList);
	}
	public List<HashMap<String, String>> getzJxjTj(int type) {
		// ��������������
		String[] enList = null;
		String[] cnList = null;
		if (type == 1) {
			cnList = new String[] { "�ۺ����ʲ������������", "�³ɼ�","�ǳɼ�","��ɼ�","���ܼӷ�" };
			enList = new String[] {"cpzfpm", "dcj","zcj","tcj","jnjf"};
		}
		return arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> getJxjDj() {
		String sql = "select dm,mc from ynys_zzdjb order by dm";
		return getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	public String getUserType(String userDep) {
		// 1 ΪѧУ��2 ΪѧԺ
		String userType = "xy";
		String sql = "select count(*) num from view_njxyzybj where xydm=?";
		String[] tmp = getOneRs(sql, new String[] { userDep },
				new String[] { "num" });
		if (tmp[0].equalsIgnoreCase("0")) {
			userType = "xx";
		}
		return userType;
	}

	public String dateToStr(String date) {
		if (date == null) {
			return "";
		}
		date = date.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		return date;
	}

	public String getXymcById(String id) {
		String sql = "select xymc from view_njxyzybj where xydm=?";
		String[] res = getOneRs(sql, new String[] { id },
				new String[] { "xymc" });
		if (res == null) {
			res = new String[] { " " };
		}
		return res[0];
	}

	public List<HashMap<String, String>> getXiaoQuList() {
		// ��ȡУ���б�
		String sql = "select distinct dm,xqmc from dm_zju_xq order by dm";
		return getList(sql, new String[] {}, new String[] { "dm", "xqmc" });
	}

	public List<HashMap<String, String>> getSexList() {
		// ��ȡ�Ա��б�
		String[] xbdm = new String[] { "1", "2" };
		String[] xbmc = new String[] { "��", "Ů" };
		return arrayToList(xbdm, xbmc);
	}

	public List<HashMap<String, String>> getModelList() {
		String sql = "select distinct gnmkdm,gnmkmc from gnmkdmb where length(gnmkdm)=3 order by gnmkdm";
		return getList(sql, new String[] {},
				new String[] { "gnmkdm", "gnmkmc" });
	}

	public CLOB getOneClob(String sql, String[] inputValue, String colName) {
		CLOB clob = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while(rs.next()){
				if (rs.getClob(colName)!=null && rs.getClob(colName).getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob(colName).getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob(colName));
		 	    }else{
		 	    	clob=(CLOB)rs.getClob(colName);
		 	    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return clob;
	}
	
	public BLOB getOneLong(String sql, String[] inputValue, String colName) {
		BLOB clob = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				clob = (BLOB) rs.getBlob(colName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return clob;
	}
	
	@SuppressWarnings("deprecation")
	public JPEGImageEncoder getPic(String id,HttpServletRequest request, 
			HttpServletResponse res) throws IOException{
        Connection conn = null;
        Statement stmt = null;
        @SuppressWarnings("unused")
		PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedInputStream imageStream = null;
        BufferedImage image = null;
        JPEGImageEncoder encoder = null;
        ServletOutputStream sos =  res.getOutputStream();
   
        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            System.out.println("initialization succeed!!");
            String preCursor = "select zp from xszpb where xh='"+id+"'";            
            rs = stmt.executeQuery(preCursor);
            if(rs.next()){
	            imageStream = new BufferedInputStream(rs.getBinaryStream(1));
	            
	            //---------------edit by quph 2010-10-19 begin------------------------
            } else {
            	String filePath = request.getRealPath("/images/type_pic.gif");
            	File file = new File(filePath);
            	imageStream = new BufferedInputStream(new FileInputStream(file));
            }
            //---------------edit by quph 2010-10-19 end------------------------
            try {
                image = ImageIO.read(imageStream);
                encoder =  JPEGCodec.createJPEGEncoder(sos);
                encoder.encode(image);
                imageStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return encoder;
    }
	
	
	/**
	 * 
	 * @����: ��ѯ���ֶΣ���������ʽ����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-20 ����10:16:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sql
	 * @param input
	 * @return
	 * @throws SQLException
	 * InputStream �������� 
	 * @throws
	 */
	public InputStream getInputStream(String sql,String[] input) throws SQLException{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
	     try{
		     conn = db.getConnection();
	         stmt = conn.prepareStatement(sql);
	         
	         for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			 }
	         rs = stmt.executeQuery();
	         if(rs.next()){
		        return rs.getBinaryStream(1);
	         }
	     }catch(SQLException e){
	    	 
	     } finally {
	    	 rs.close();
             stmt.close();
             conn.close();
	     }
	     
	     return null;
	}
	
	
	
	public String read() {
		String rtn = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			String sql = "select newscont from newscontent where rownum=1";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				rtn = clob.getSubString(0, 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return rtn;
	}

	public String getConf(int tag) {
		// ��ȡ��ǰ����:0-currXn;1-currXq;2-jxjsqxn;3-jxjsqnd;4-currNd;5-sfkpj;6-jxjsqxq
		String sql = "select dqxn,dqxq,jxjsqxn,jxjsqnd,dqnd,nvl(sfkpj,'��') sfkpj,jxjsqxq from xtszb where rownum=1";
		String[] colList = getOneRs(sql, new String[] {}, new String[] {
				"dqxn", "dqxq", "jxjsqxn", "jxjsqnd", "dqnd", "sfkpj",
				"jxjsqxq" });
		if (colList == null || colList.length <= tag) {
			return " ";
		}
		return colList[tag];
	}

	public HashMap<String, String> getRSArray(String sql, String[] outputValue)
			throws SQLException {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i],
							(rs.getString(outputValue[i]) == null ? "��" : rs
									.getString(outputValue[i])));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}

		return map;
	}

	public List<HashMap<String, String>> getNxList() {
		// ��ȡ�����б�
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String nx = "";
		for (int i = 1; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			nx = Integer.toString(i);
			map.put("nx", nx);
			aList.add(map);
		}
		return aList;
	}

	/**
	 * �Ա����Ϊ��׼��ǰ6�꣬���1��
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNdList() throws Exception {//

		List<HashMap<String, String>> ndlist = new ArrayList<HashMap<String, String>>();
		String[] ndarr = new String[7];
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String dqnd = "";
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("select dqnd from xtszb");
			rs = stmt.executeQuery();
			if (rs.next())
				dqnd = rs.getString("dqnd");
			for (int i = 6; i > 0; i--) {
				ndarr[6 - i] = (new Integer(Integer.parseInt(dqnd) + i - 6))
						.toString();
			}
			ndarr[6] = new Integer(Integer.parseInt(dqnd) + 1).toString();
			Arrays.sort(ndarr);
			for (int i = 0; i < ndarr.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("nd", ndarr[i]);
				ndlist.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return ndlist;
	}

	// �Լ���ӵķ���
	public List getData(String sql, String[] getParameter) throws SQLException {
		ArrayList<HashMap> rslist = new ArrayList<HashMap>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < getParameter.length; i++) {
					map.put(getParameter[i], rs.getString(i + 1));
				}
				rslist.add(map);
			}
		} catch (SQLException e) {
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return rslist;
	}

	public boolean insert(String sql, String[] input) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeQuery();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			flag = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return flag;
	}
	public boolean insertNotCommit(String sql, String[] input) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = transcationManager.getConnection();
			//conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeQuery();
			flag = true;
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
		}
		return flag;
	}
	
	/**
	 * ���ݲ���������������ݵ�ͬʱ���������־
	 * @param sql
	 * @param input
	 * @param tableName
	 * @param user
	 * @return boolean
	 * */
	public boolean insert(String sql, 
			              String[] input,
			              String tableName, 
			              User user) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			stmt.executeQuery();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			flag = false;
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		if(flag){
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "insert :");
			dmlLogger.insertLog(sql, input, user);
		}
		return flag;
	}

	// ��ȡʱ��������б�
	public List<HashMap<String, String>> getSJDList() {

		String[] SJDBH = new String[] { "1", "2", "3", "4" };
		String[] SJDMC = new String[] { "08:00", "09:50", "13:30", "15:30" };
		return arrayToList(SJDBH, SJDMC);
	}

	public String[] getColumnNameCN2(String[] cName, String tabName) {
		// �����ݱ�Ӣ������ת��Ϊ����������������������[��������]���飨����ΪӢ����������ͱ�����
		String[] tit = new String[cName.length];
		String sql = "select COMMENTS from user_col_comments where table_name=upper(?)and COLUMN_NAME=?";
		for (int i = 0; i < cName.length; i++) {
			String[] tmp = getOneRs(sql, new String[] { tabName.toUpperCase(),
					cName[i].toUpperCase() }, new String[] { "COMMENTS" });
			if (tmp == null) {
				tit[i] = cName[i];
			} else {
				tit[i] = tmp[0];
				if ((tit[i] == null)
						|| tit[i].toLowerCase().equalsIgnoreCase("null")
						|| tit[i].equalsIgnoreCase("")) {
					tit[i] = cName[i];
				}
				if (tit[i].indexOf('(') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('('));
				}
				if (tit[i].indexOf('��') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('��'));
				}
			}
		}
		return tit;
	}

	/**
	 * to get the list object which include the grade of the history lab
	 * 
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getNjHistoryList() throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select distinct nj from view_his_njxyzybj";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();
			rs.last();
			int cap = rs.getRow();
			String[] njArr = new String[cap];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				njArr[i] = rs.getString("nj");
				i++;
			}
			Arrays.sort(njArr);
			for (i = 0; i < njArr.length; i++) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("nj", njArr[i]);// ����njArr[i]��Ϊnj�ſ���ҳ��ȡ��
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);

		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}

		return list;
	}

	/**
	 * to get the colleges from history lab
	 */
	public List<HashMap<String, String>> getXyHistoryList() throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select distinct xydm,xymc from view_his_njxyzybj order by xydm";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("xydm", rs.getString("xydm"));
				temmap.put("xymc", rs.getString("xymc"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	/**
	 * to get the specialities from history lab
	 */
	public List<HashMap<String, String>> getZyHistoryList(String xydm)
			throws SQLException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer(
				"select distinct zydm,zymc from view_his_njxyzybj where ");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			sql.append((xydm == null || xydm.trim().equals("")) ? " 1=1 "
					: " xydm=" + xydm);
			sql.append(" order by zydm");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("zydm", rs.getString("zydm"));
				temmap.put("zymc", rs.getString("zymc"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql.toString());
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	/**
	 * to get the classes of history lab
	 */
	public List<HashMap<String, String>> getBjHistoryList(String xydm,
			String zydm, String nj) throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer(
				"select distinct bjdm,bjmc from view_his_njxyzybj where ");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			sql.append((xydm == null || xydm.trim().equals("")) ? " 1=1 "
					: " xydm='" + xydm + "'");
			sql.append((zydm == null || zydm.trim().equals("")) ? " and 1=1 "
					: " and zydm='" + zydm + "'");
			sql.append((nj == null || nj.trim().equals("")) ? " and 1=1 "
					: " and nj='" + nj + "'");
			sql.append(" order by bjdm");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("bjdm", rs.getString("bjdm"));
				temmap.put("bjmc", rs.getString("bjmc"));
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql.toString());
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	// ////////////�жϲ�ѯ�Ƿ�Ϊ��,����tag//////////////////////////////////////////
	public String returntag(String sql, String[] setParameter)
			throws SQLException {
		String tag = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < setParameter.length; i++) {
				stmt.setString(i + 1, setParameter[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				tag = "notempty";
			} else {
				tag = "empty";
			}
		} catch (SQLException e) {
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return tag;
	}

	// ��ȡѧ��������Ϣ�б�
	public List getStuDormList(String query) {
		String sql = "select * from view_xsgygl_xsssfbqkb" + query;
		List dormList = getList(sql, new String[] {}, new String[] { "xh",
				"xm", "xymc", "zymc", "bjmc", "ssbh", "fdyxm", "sz", "cz",
				"lz", "cwh" });
		return dormList;
	}

	// ��ȡѧ�������ȼ��б�
	public List<HashMap<String, String>> getXszzDjList() throws SQLException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select zzdjdm,zzdjmc||'||'||zzdjje zzdjmc from zzsf_xszzdjdmb";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("zzdjdm", rs.getString("zzdjdm"));
				temmap.put("zzdjmc", rs.getString("zzdjmc"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	// ��ȡѧ������б�
	public List<HashMap<String, String>> getXszzXslbList() throws SQLException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select xslb from zzsf_xslbdmb";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("xslb", rs.getString("xslb"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public boolean saveClobs(String sql, String clobSql, String sContent,
			String clobField) throws Exception {
		// ͨ�����ݿ�CLOB ���ĵ�д�����ݿ�clob�ֶ�
		boolean res = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			// ================ begin ���ΰ 2009/3/23 =====================
			// ���ڱ��˲���ģ���п��ܴ���һ�յ�SQL,Ϊ��ֹ����,�������Ŀ�ǿ��ж�
			if (sql != null && !"".equals(sql)) {
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				stmt.clearBatch();
			}
			// ================ end ���ΰ 2009/3/23 =====================
			stmt = conn.prepareStatement(clobSql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				CLOB clob = (CLOB) rs.getClob(clobField);
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean updataClobs(String sql, String clobSql, String sContent,
			String clobField) throws Exception {
		// ͨ�����ݿ�CLOB �޸����ݿ�clob�ֶ�
		boolean res = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(clobSql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				CLOB clob = (CLOB) rs.getClob(clobField);
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// ��������ѧ���༶����õ�����ѧ���༶����
	public List<HashMap<String, String>> getBjmcHistoryList(String bjdm)
			throws SQLException {
		String sql = "select distinct bjmc from view_his_njxyzybj where bjdm='"
				+ bjdm + "'";
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("bjmc", rs.getString("bjmc"));
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	// ��������ѧ��רҵ����õ�����ѧ��רҵ����
	public List<HashMap<String, String>> getZymcHistoryList(String zydm)
			throws SQLException {

		String sql = "select distinct zymc from view_his_njxyzybj where zydm='"
				+ zydm + "'";
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("zymc", rs.getString("zymc"));
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	// ��������ѧ��ѧԺ����õ�����ѧ��ѧԺ����
	public List<HashMap<String, String>> getXymcHistoryList(String xydm)
			throws SQLException {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		String sql = "select distinct xymc from view_his_njxyzybj where xydm='"
				+ xydm + "'";
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("xymc", rs.getString("xymc"));
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	public List<HashMap<String, String>> getXhList() throws SQLException {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select distinct xh from bks_xsjbxx order by xh";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("xh", rs.getString("xh"));
				list.add(temmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	public String[] getArray(String sql, String[] input, String output)
			throws SQLException {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		String[] arr = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			rs = stmt.executeQuery();
			rs.last();
			arr = new String[rs.getRow()];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				arr[i] = rs.getString(output);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arr;
	}
	
	public List<String> getList(String sql, String[] input, String output)
			throws SQLException {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			rs = stmt.executeQuery();
			rs.last();
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				list.add(rs.getString(output));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}

	public String[] getViewComm(String viewName) throws SQLException {
		// �õ���ͼ���ֶ�ע�����
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||chr(39) com "
				+ "from user_col_comments where table_name=upper('"
				+ viewName
				+ "')";
		arr = this.getArray(sql, new String[] {}, "com");
		return arr;
	}

	public boolean creatView(String sql, String[] com) throws SQLException {
		// �õ���ͼ���ֶ�ע�����
		boolean result = false;
		try {
			result = this.runUpdateTab(sql);
			for (int i = 0; i < com.length; i++) {
				runUpdateTab(com[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		}
		return result;
	}

	public boolean creatNewView(String sql, String[] com, String tableName)
			throws SQLException {
		boolean result = false;
		try {
			result = runUpdateTab(sql);
			String[] columns = this.getColumnName("select * from " + tableName
					+ " where 1=2");
			for (int i = 0; i < com.length; i++) {
				sql = "comment on column " + tableName + "." + columns[i]
						+ " is '" + com[i] + "'";
				runUpdateTab(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		}
		return result;
	}

	public HashMap<String, String> getMapNotOut(String sql, String[] inputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if (inputValue != null) { // �ж�û�в��������
				for (int i = 0; i < inputValue.length; i++) {
					stmt.setString(i + 1, inputValue[i]);
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()) {
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					map.put(rsmd.getColumnName(i).toLowerCase(), rs
							.getString(rsmd.getColumnName(i)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			map = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return map;
	}

	public Vector<String[]> rsToVatorNotOut(String sql, String[] inputValue) {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector<String[]> vector = new Vector<String[]>();
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if (inputValue != null) {
				for (int i = 0; i < inputValue.length; i++) {
					stmt.setString(i + 1, inputValue[i]);
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				String[] tmp = new String[rsmd.getColumnCount()];
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					tmp[i] = rs.getString(rsmd.getColumnName(i + 1));
					if (tmp[i] == null || tmp[i].equalsIgnoreCase("null")) {
						tmp[i] = "";
					}
				}
				vector.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			vector = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return vector;
	}

	public Vector<String[]> rsToVatorNotOutCN(String sql, String[] inputValue,
			String tableName) {
		Vector<String[]> vector = new Vector<String[]>();
		Vector<String[]> dataVector = new Vector<String[]>();
		String[] columnName = getColumnName(sql);
		String[] columnNameCN = getColumnNameCN(columnName, tableName);
		dataVector = rsToVatorNotOut(sql, inputValue);
		vector.add(columnNameCN);
		if (dataVector != null) { // vector.addAll������������nullֵ
			vector.addAll(dataVector);
		}
		return vector;
	}

	public List<HashMap<String, String>> getListNotOut(String sql,
			String[] inputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if (inputValue != null) { // �ж�û�в��������
				for (int i = 0; i < inputValue.length; i++) {
					stmt.setString(i + 1, inputValue[i]);
				}
			}
			rs = stmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					map.put(rsmd.getColumnName(i).toLowerCase(), getValue(rs,rsmd.getColumnName(i)));
				}
				map.put("rownum", rs.getRow() + "");
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}
	/**
	 * 
	 * @����: ���getString��ȡС������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-24 ����01:46:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * String ��������
	 */
	private String getValue(ResultSet rs,String columnName) throws SQLException{
		Object V=rs.getObject(columnName);
		if(null!=V){
			//������������
			if(V instanceof CLOB){
				return rs.getString(columnName);
			}else if(V instanceof BLOB){
				return rs.getString(columnName);
			}
			return V.toString();
		}
		return null;
	}
	public List<HashMap<String, String>> getMzList() {
		String sql = "select distinct mzdm,mzmc from mzdmb order by mzdm";
		return getList(sql, new String[] {}, new String[] { "mzdm", "mzmc" });
	}

	public List<HashMap<String, String>> getBzList() {
		String sql = "select distinct bz,bz from bjjxbzb";
		return getList(sql, new String[] {}, new String[] { "bz", "bz" });
	}

	// ��ȡ������ò�б�
	public List<HashMap<String, String>> getZzmmList() {
		String sql = "select distinct zzmmdm,zzmmmc from zzmmdmb order by zzmmdm";
		return getList(sql, new String[] {},
				new String[] { "zzmmdm", "zzmmmc" });
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe ��� ѧ��״̬
	 */
	public String getXszt(String xh) {
		String tempSql = "select ydlb from XSYDLB where xh=?";
		String xszt = "";
		String[] xsztl = this.getOneRs(tempSql, new String[] { xh },
				new String[] { "ydlb" });
		if ((xsztl == null) || ("��ѧ".equalsIgnoreCase(xsztl[0]))) {
			tempSql = "select ydlb from bks_stu_info_xjydb where xh=?";
			xsztl = this.getOneRs(tempSql, new String[] { xh },
					new String[] { "ydlb" });
			if (xsztl == null) {
				xszt = "�ڶ�";
			} else {
				xszt = xsztl[0];
			}
		} else {
			xszt = xsztl[0];
		}
		return xszt;
	}

	/**
	 * @author ZhouMi
	 * @describe �ж��Ƿ������� ������������true
	 */
	public boolean isKns(String xh) {
		boolean tm = false;
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xxdm = StandardOperation.getXxdm();
		String num = "";
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// ������ҵ��ѧ
			String tempSql = "select count(*) num from view_bjlydx_knssqb where xh=? and nd=? and xxsh='ͨ��'";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// ���������ѧ
			String tempSql = "select count(*) num from xsjtqkdcb where xh=? and xxsh='ͨ��'";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SCJZZYJSXY)) {// �Ĵ�����ְҵ����ѧԺ
			String tempSql = "select count(*) num from xsjtqkdcb where xh=? and xxsh='ͨ��'";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// ������Ϣְҵ����ѧԺ
			String tempSql = "select count(*) num from jsxx_knsxx where xh=? and nd=? and xxsh in ('һ������', '�Ƚ�����','�ر�����')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// ����ְҵ����ѧԺ
			String tempSql = "select count(*) num from hzzy_knssqb where xh=? and nd=? and xxsh in ('����','��������')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// ��ְҵ����ѧԺ
			String tempSql = "select count(*) num from xszz_jhzy_kns where xh=? and nd=? and xxsh in ('����','��������')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// ����ʦ��ѧԺ
			String tempSql = "select count(*) num from zzsf_knsxx where xh=? and nd=? and xxsh in ('һ������','����','��������')";
			num = this
					.getOneRs(tempSql, new String[] { xh, nd }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// �㶫Ů��ְҵ����ѧԺ
			String tempSql = "select count(*) num from gdnzzyjsxy_knsxx where xh=? and xxsh in ('һ������','�ر�����')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {// �Ϻ����̼�����ѧ
			String tempSql = "select count(*) num from shgc_knsxx where xh=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365 and xxsh in ('һ������','�ر�����','����')";
			num = this.getOneRs(tempSql, new String[] { xh }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {// ��������Ժ
			String tempSql = "select count(*) num from shgc_knsxx where xh=? and nd=? and xxsh in ('һ������','�ر�����','����')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHNLZYJSXY)) {// �Ϻ�ũ��ְҵ����ѧԺ
			String tempSql = "select count(*) num from shnl_knsxx where xh=? and nd=? and shjg in ('����','�ر�����')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// ��ɳ����ְҵ����ѧԺ
			String tempSql = "select count(*) num from csmz_knsxx where xh=? and nd=? and xxsh in ('ƶ����','������')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// �㽭����ְҵ����ѧԺ
			String tempSql = "select count(*) num from zjjd_knsxx where xh=? and nd=? and xxsh in ('һ������','����','��������')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// �й���ҵ��ѧ
			String tempSql = "select count(*) num from ZGKYDX_KNSXX where xh=? and nd=? and XXSHJG in ('A��', 'B��', 'C��')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// �й����ʴ�ѧ
			String tempSql = "select count(*) num from ZGDZDX_KNS_PKSRD where xh=? and xn=? and XXSH in ('һ������','����','��������')";
			num = this.getOneRs(tempSql, new String[] { xh, xn }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// �������ϴ�ѧ
			String tempSql = "select count(*) num from view_bjlhdx_kns where xh=? and XXSH in ('һ������', '��������', '��������')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {// �й�����ѧԺ
			String tempSql = "select count(*) num from zgmsxy_knsxx where xh=? and xn=? and XXSH in ('����', '��������')";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {// ������
			String tempSql = "select count(*) num from NBLG_KNS where xh=? and xn=? and XXSH in ('����', '�ر�����')";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {//�γ�����ְҵ����ѧԺ
			String tempSql = "select count(*) num from ycws_knrdxx where xh=? and knrd in ('һ������', '����', '��������')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
			//  �������ж�
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)) {// ���ݴ�ѧ
			String tempSql = "select count(*) num from xszz_com_knsrdb3 where xh=? and xn=? and XXSH in ('����', '��������','һ������')";
			num = this.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WZDX)) {// ���ݴ�ѧ
			String tempSql = "select count(*) num from XG_XSZZ_NEW_KNSJGB where xn = (select dqxn from xtszb where rownum = 1) and xq = (select nvl(rdxq, 'on') from xg_xszz_new_knsjbszb where rownum = 1) and xh=?";
			num = this.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else {
			String tempSql = "select count(*) num from view_knsxx where xh=? and xn=? and xxsh='ͨ��'";
			num = this.getOneRs(tempSql, new String[] { xh, xn }, new String(
					"num"));
			if ("0".equalsIgnoreCase(num)) {
				tempSql = "select count(*) num from view_xszz_new_knsxx where xh=? and nd=? and xxsh='ͨ��'";
				num = this.getOneRs(tempSql, new String[] { xh, nd },
						new String("num"));
			}
		}
		
		String[] sT = this.getOneRs("select sfyx,nvl(knsxxb,'') knsxxb,knslx,knszj from XSZZ_N05_KNSBZB where rownum=1", new String[]{}, new String[]{"sfyx","knsxxb","knslx","knszj"});
		if (sT != null && "1".equalsIgnoreCase(sT[0]) && sT.length == 4) {
			String sWh = " where 1=1 ";
			
			String[] pkT = sT[3].split("!");
			
			for (int i = 0; i < pkT.length; i++) {
				if ("xh".equalsIgnoreCase(pkT[i])) {
					sWh += " and xh='"+xh+"'";
				} else if ("xn".equalsIgnoreCase(pkT[i])) {
					sWh += " and xn='"+xn+"'";
				} else if ("nd".equalsIgnoreCase(pkT[i])) {
					sWh += " and nd='"+nd+"'";
				}
			}
			
			String[] xxshL = sT[2].split("!");
			String s = "";
			for (int i = 0; i < xxshL.length; i++) {
				s += "'" + xxshL[i]+"',";
			}
			sWh += " and xxsh in ("+s.substring(0, s.length()-1)+")";
			
			String tempSql = "select count(*) num from " + sT[1] + sWh;
			num = this.getOneRs(tempSql, new String[] {}, new String("num"));
		}
		
		if (!"0".equalsIgnoreCase(num)) {
			tm = true;
		}

		String xszztyFlag = Base.getInitProperties().get("xszztyFlag");
		//ʹ��N32��ѧ������
		if ("true".equalsIgnoreCase(xszztyFlag)) {
		//if (Globals.isNewImburse(xxdm)) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xh", xh);

			XszzService xszzService = new XszzService();
			tm = xszzService.isKns(map);
		}
		
		return tm;
	}
	
	/**
	 * @author lr
	 * @describe ��ȡ���������� xxsh='ͨ��'��ѧ��Ϊ�����ѡ�  ,xxsh='��ͨ��' ���� xxsh='δ���' ���� xxsh is null��ѧ��Ϊ'������' ����ȡxxsh�ֶ���Ϣ
	 */
	public String getKnslx(String xh) {
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xxdm = StandardOperation.getXxdm();
		String num = "";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// ������ҵ��ѧ
			String tempSql = "select xxsh num from view_bjlydx_knssqb where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// ���������ѧ
			String tempSql = "select xxsh  num from xsjtqkdcb where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SCJZZYJSXY)) {// �Ĵ�����ְҵ����ѧԺ
			String tempSql = "select xxsh num from xsjtqkdcb where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// ������Ϣְҵ����ѧԺ
			String tempSql = "select xxsh num from jsxx_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// ����ְҵ����ѧԺ
			String tempSql = "select xxsh num from hzzy_knssqb where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// ��ְҵ����ѧԺ
			String tempSql = "select xxsh num from xszz_jhzy_kns where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// ����ʦ��ѧԺ
			String tempSql = "select xxsh num from zzsf_knsxx where xh=? and nd=?";
			num = this
					.getOneRs(tempSql, new String[] { xh, nd }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// �㶫Ů��ְҵ����ѧԺ
			String tempSql = "select xxsh num from gdnzzyjsxy_knsxx where xh=? ";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {// �Ϻ����̼�����ѧ
			String tempSql = "select xxsh num from shgc_knsxx where xh=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365";
			num = this.getOneRs(tempSql, new String[] { xh }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {// ��������Ժ
			String tempSql = "select xxsh num from shgc_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHNLZYJSXY)) {// �Ϻ�ũ��ְҵ����ѧԺ
			String tempSql = "select shjg num from shnl_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// ��ɳ����ְҵ����ѧԺ
			String tempSql = "select xxsh num from csmz_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// �㽭����ְҵ����ѧԺ
			String tempSql = "select xxsh num from zjjd_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// �й���ҵ��ѧ
			String tempSql = "select xxshjg num from zgkydx_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// �й����ʴ�ѧ
			String tempSql = "select xxsh num from zgdzdx_kns_pksrd where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// �������ϴ�ѧ
			String tempSql = "select xxsh num from view_bjlhdx_kns where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {// �й�����ѧԺ
			String tempSql = "select xxsh num from zgmsxy_knsxx where xh=? and xn=?";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {// ������
			String tempSql = "select xxsh num from NBLG_KNS where xh=? and xn=?";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {//�γ�����ְҵ����ѧԺ
			String tempSql = "select knrd num from ycws_knrdxx where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
			// TODO �������ж�
		} else {
			String tempSql = "select xxsh num from view_knsxx where xh=? and xn=?";
			num = this.getOneRs(tempSql, new String[] { xh, xn }, new String(
					"num"));
		}
		
		String[] sT = this.getOneRs("select sfyx,nvl(knsxxb,'') knsxxb,knslx,knszj from XSZZ_N05_KNSBZB where rownum=1", new String[]{}, new String[]{"sfyx","knsxxb","knslx","knszj"});
		if (sT != null && "1".equalsIgnoreCase(sT[0]) && sT.length == 4) {
			String sWh = " where 1=1 ";
			
			String[] pkT = sT[3].split("!");
			
			for (int i = 0; i < pkT.length; i++) {
				if ("xh".equalsIgnoreCase(pkT[i])) {
					sWh += " and xh='"+xh+"'";
				} else if ("xn".equalsIgnoreCase(pkT[i])) {
					sWh += " and xn='"+xn+"'";
				} else if ("nd".equalsIgnoreCase(pkT[i])) {
					sWh += " and nd='"+nd+"'";
				}
			}
			
			String tempSql = "select xxsh num from " + sT[1] + sWh;
			num = this.getOneRs(tempSql, new String[] {}, new String("num"));
		}
		if(StringUtils.isNull(num) || "δ���".equalsIgnoreCase(num) || "��ͨ��".equalsIgnoreCase(num)){
			num = "������";
		}else if("ͨ��".equalsIgnoreCase(num)){
			num = "����";
		}
		return num;
	}

	/**
	 * @author ZhouMi
	 * @describe ��һ���������Ľ��Ӹ�λ��ʼÿ��λ��һ�����Ÿ鿪��
	 */
	public String internationalMoney(String je) {
		String inJe = "";
		List<String> list = new ArrayList<String>();
		int num = je.length();
		for (int i = num, j = 1; i > 0; i--, j++) {
			list.add(je.substring(i - 1, i));
			if (((j % 3) == 0) && (i != 1)) {
				list.add(",");
			}
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			inJe += list.get(i);
		}
		return inJe;
	}

	/**
	 * @author ZhouMi
	 * @describe ��ý�����Ϣְҵ����ѧԺ������ѧ��ȼ��б�
	 */
	public List<HashMap<String, String>> getJsxxGjzxjDj() {
		// ��ȡѧԺ�б�
		String sql = "select distinct djdm,djmc||'||'||djje djmc from jsxx_gjzxjdjdmb";
		return getList(sql, new String[] {}, new String[] { "djdm", "djmc" });
	}

	/**
	 * @author ZhouMi
	 * @describe ��ý�����Ϣְҵ����ѧԺѧ�������ѻ���ѧ��Ͳ��������
	 */
	public ArrayList<String> getJsxxStuJzxj(String xh) {
		ArrayList<String> aList = new ArrayList<String>();
		String sql = "select nd,jzxjzwmc from view_xsjxjzxjsq where xh=? and xxsh='ͨ��' order by nd";
		List<Vector<String>> temp = this.getVectorList(sql,
				new String[] { xh }, new String[] { "nd", "jzxjzwmc" });
		String xx = "";
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "���  ��ã�" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd,bzmc from view_xswszxjsqxx where xh=? and xxsh='ͨ��' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "bzmc" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "���  ��ã�" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd,bzmc from view_xsbzxx where xh=? and xxsh='ͨ��' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "bzmc" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "���  ��ã�" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd from VIEW_JSXX_GJZXDK where xh=? and xxsh='ͨ��' order by nd";
		temp = this.getVectorList(sql, new String[] { xh },
				new String[] { "nd" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "���  ��ã�������ѧ����";
				aList.add(xx);
			}
		}
		sql = "select sbsj from VIEW_JSXX_SBKNBZSQB where xh=? and xxsh='ͨ��' order by sbsj";
		temp = this.getVectorList(sql, new String[] { xh },
				new String[] { "sbsj" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				String year = vec.get(0).substring(0, 4);
				String mon = vec.get(0).substring(5, 7);
				String day = vec.get(0).substring(8);
				xx = year + "��" + mon + "��" + day + "��  ��ã��ˡ�������";
				aList.add(xx);
			}
		}
		sql = "select nd,xxtyshje from view_jsxx_gjzxj where xh=? and xxsh='ͨ��' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "xxtyshje" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "���  ��ã�������ѧ��,���Ϊ: " + vec.get(1) + "Ԫ";
				aList.add(xx);
			}
		}
		return aList;
	}

	public List<HashMap<String, String>> getKcList(String realTable,
			String bjdm, String xn, String xqdm) {
		// ��ȡ�γ��б�
		String sql = "";
		String dqxn = getConf(0);
		String dqxq = getConf(1);
		// int i = 0;
		if ((bjdm == null) || bjdm.equalsIgnoreCase("")
				|| bjdm.equalsIgnoreCase("all")) {
			sql = "select distinct kcdm,kcmc from bks_kcdm";
		} else {
			if (((xn == null) || xn.equalsIgnoreCase("") || xn
					.equalsIgnoreCase("all"))
					&& (xqdm == null)
					|| xqdm.equalsIgnoreCase("")
					|| xqdm.equalsIgnoreCase("all")) {
				sql = "select distinct kcdm,kcmc from " + realTable + " where "
						+ "bjdm='" + bjdm + "' and xn='" + dqxn + "' and xq='"
						+ dqxq + "' order by kcdm";
			} else if ((xn != null) || !xn.equalsIgnoreCase("")
					|| !xn.equalsIgnoreCase("all")) {
				if ((xqdm == null) || xqdm.equalsIgnoreCase("")
						|| xqdm.equalsIgnoreCase("all")) {
					sql = "select distinct kcdm,kcmc from " + realTable
							+ " where " + "bjdm='" + bjdm + "' and xn='" + xn
							+ "'order by kcdm";
				} else {
					sql = "select distinct kcdm,kcmc from " + realTable
							+ " where " + "bjdm='" + bjdm + "' and xn='" + xn
							+ "'and xq='" + xqdm + "' order by kcdm";
				}

			}
		}
		return getList(sql, new String[] {}, new String[] { "kcdm", "kcmc" });
	}

	public String getCNtableName(String tableName) throws Exception {
		String sql = "";
		String CNtableName = "";
		sql = "select distinct comments from all_tab_comments where TABLE_NAME=UPPER('"
				+ tableName + "') AND COMMENTS IS NOT NULL";
		CNtableName = getOneRs(sql, new String[] {}, "comments");
		return CNtableName;
	}

	/**
	 * �����е�update��insert��������������ݿ�ִ�У��Ӷ����������ݿ�Ľ����������� ������������sql���ӣ�����ֵ��Ҫ��ϵ�������ȥ
	 * ����: new String[]{"insert into table(a,b) values ('a','b')"}
	 * @param sqlArr
	 *            ����˳����sql���
	 * @return ���ص�int[]����sqlArr���Ӧ��˳��Ľ������ʾÿ�����ӵ�ִ���Ƿ�ɹ�
	 */
	public int[] runBatch(String[] sqlArr) throws SQLException {

		// Connection conn = null;
		// Statement stat = null;
		ArrayList<String> sqlV = new ArrayList<String>();
		for (int i = 0; i < sqlArr.length; i++) {
			if (sqlArr[i] != null && !"".equalsIgnoreCase(sqlArr[i])) {
				sqlV.add(sqlArr[i]);
			}
		}
		String[] sqlVArr = sqlV.toArray(new String[0]);
		int[] result = new int[sqlVArr.length];
		Arrays.fill(result, -1);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			for (String sql : sqlVArr) {
				stat.addBatch(sql);
			}
			result = stat.executeBatch();
			stat.clearBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			stat.clearBatch();
			conn.rollback();
			stat.close();
			conn.close();
		} finally {
			closeAllStmtAndRs(null, null, stat, null);
			closeConnection(conn);
		}
		
		return result;
	}
	
	/**
	 * �����е�update��insert��������������ݿ�ִ�У��Ӷ����������ݿ�Ľ����������� ������������sql���ӣ�����ֵ��Ҫ��ϵ�������ȥ
	 * ����: new String[]{"insert into table(a,b) values ('a','b')"}
	 * @param sqlArr
	 *            ����˳����sql���
	 * @return ���ص�int[]����sqlArr���Ӧ��˳��Ľ������ʾÿ�����ӵ�ִ���Ƿ�ɹ�
	 */
	public int[] runBatch(String[] sqlArr,String tableName,User user) throws SQLException {

		// Connection conn = null;
		// Statement stat = null;
		ArrayList<String> sqlV = new ArrayList<String>();
		for (int i = 0; i < sqlArr.length; i++) {
			if (sqlArr[i] != null && !"".equalsIgnoreCase(sqlArr[i])) {
				sqlV.add(sqlArr[i]);
			}
		}
		String[] sqlVArr = sqlV.toArray(new String[0]);
		int[] result = new int[sqlVArr.length];
		Arrays.fill(result, -1);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stat = conn.createStatement();
			for (String sql : sqlVArr) {
				stat.addBatch(sql);
			}
			result = stat.executeBatch();
			stat.clearBatch();
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "runBatch :");
			dmlLogger.insertLog(sqlV.toString(), null, user);
		} catch (Exception e) {
			e.printStackTrace();
			stat.clearBatch();
			conn.rollback();
			stat.close();
			conn.close();
		} finally {
			closeAllStmtAndRs(null, null, stat, null);
			closeConnection(conn);
		}
		
		return result;
	}


	/**
	 * @param v_key
	 *            ����
	 * @param v_sql_in
	 *            ����ĺϳ�sql
	 * @param v_page_size
	 *            ҳ���¼��
	 * @param currentPage
	 *            ��ǰҳ����
	 * @param v_table_name
	 *            ������ͼ����
	 * @param v_order_field
	 *            �����������
	 * @param v_order_sequence
	 *            ����ʽ ASC OR DESC
	 * @param pages
	 *            pages����
	 * @return
	 */
	public ArrayList<String[]> makePagination(String v_key, String v_sql_in,
			int v_page_size, int currentPage, String v_table_name,
			String v_order_field, String v_order_sequence, String[] columns,
			Pages pages) {
		ArrayList<String[]> result = new ArrayList<String[]>();
//		String sql = "";
		int v_total_count = 0;
		int v_page_size_temp = 10;// Ĭ��ÿҳ��ʾ10��
		int currentPage_temp = 0;// ��ǰ��ʾ��ҳ��
		if (v_page_size != 0) {
			v_page_size_temp = v_page_size;
		}
		if (currentPage > 0) {
			currentPage_temp = currentPage;
		}

		// Connection conn = null;
		// CallableStatement cstmt = null;
		// ResultSet rs = null;

		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			cstmt = conn
					.prepareCall("{call zfPackage.pagination(?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.setString(1, v_key);
			cstmt.setString(2, v_sql_in);
			cstmt.setInt(3, v_page_size_temp);
			cstmt.setInt(4, currentPage_temp);
			cstmt.setString(5, v_order_field);
			cstmt.setString(6, "");
			cstmt.execute();

			v_total_count = cstmt.getInt(7);
			rs = (ResultSet) cstmt.getObject(8);
			pages.setMaxRecord(v_total_count);
			while (rs.next()) {
				//  �ѵ�����¼�ŵ�list��
				String[] temp = new String[columns.length];
				for (int i = 0; i < temp.length; i++) {
					temp[i] = rs.getString(columns[i]);
				}
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, null, null, cstmt);
			closeConnection(conn);
		}
		return result;
	}

	public List<HashMap<String, String>> getBmList() {
		// ��ò��Ŵ��룬�����б�
		String sql = "select bmdm,bmmc from zxbz_xxbmdm��where bmjb = '1' ";
		return getList(sql, new String[] {}, new String[] { "bmdm", "bmmc" });
	}
	
	public List<HashMap<String, String>> getBmListAll() {
		// ��ò��Ŵ��룬�����б�
		String sql = "select bmdm,bmmc from zxbz_xxbmdm order by bmdm ";
		return getList(sql, new String[] {}, new String[] { "bmdm", "bmmc" });
	}
	
	public List<HashMap<String, String>> getYjbmList() {
		// ��ò��Ŵ��룬�����б�
		StringBuilder sql = new StringBuilder();

		sql.append("select bmdm,bmpy||bmmc bmqc,bmpy||bmsx bmjc, bmmc from (");
		sql.append("select bmdm,bmmc,");
		sql.append("case when length(bmmc)>6 then substr(bmmc,0,6)||'..' ");
		sql.append("else bmmc end bmsx, ");
		sql.append("F_PINYIN(substr(bmmc,0,1)) bmpy  ");
		sql.append("from zxbz_xxbmdm ");
		sql.append("where bmjb = '1' ");
		sql.append(") order by bmmc ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {"bmdm", "bmmc", "bmqc", "bmjc"});
	}

	public HashMap<String, String[]> getHashMapList(String sql,
			String[] inputValue, String[] outputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// ����hashMap�ṹ�Ľ����,�Ե�һ���ֶε�ֵΪ����
		HashMap<String, String[]> mapX = new HashMap<String, String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					if ((tempstr == null) || tempstr.equalsIgnoreCase("")) {
						tmp[i] = "";
					} else {
						tmp[i] = tempstr;
					}
				}
				mapX.put(tmp[0], tmp);
				tmp = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			mapX = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return mapX;
	}

	/**
	 * ���tableName�����Ƿ����keysָ�������ݼ�, ����ѭ��������������
	 * 
	 * @param tableName
	 * @param pk
	 * @param keys
	 * @return
	 */
	public boolean[] checkExists(String tableName, String pk, String[] keys) {
		// Connection conn = null;
		// CallableStatement cstmt = null;
		boolean[] result = new boolean[keys.length];
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn .prepareStatement("select count(*)num from " + tableName + " where " + pk + "=?");

			for (int i = 0; i < keys.length; i++) {
				stmt.setString(1, keys[i]);
				rs = stmt.executeQuery();
				if(rs.next()){
					result[i] = Integer.parseInt(rs.getString(1)) > 0 ? true
							: false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}

	/**
	 * ����ά����ת��ListΪString
	 * 
	 * @param list
	 * @param cols
	 * @return
	 */
	public String listToSplit(List list, String[] cols) {
		String str = "";
		HashMap map = null;
		for (int i = 0; i < list.size(); i++) {
			str += "!!SplitSignOne!!";
			map = (HashMap) list.get(i);
			for (int j = 0; j < cols.length; j++) {
				str += "!!SplitSignTwo!!";
				str += (String) map.get(cols[j]);
				str += "!!SplitSignTwo!!";
				str += (String) map.get(cols[j]);
			}
		}
		return str;
	}

	/**
	 * ��ɳ������������
	 * 
	 * @param list
	 * @param cols
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean addNews3(String sNewsTitle, String sPart, String sContent,
			String sPuber, String towho, String bmdm) throws Exception {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// �������
		boolean res = true;
		String sql = "insert into newscontent(newstitle,newspart,newscont,puber,towho,bmdm,newssign) values(?,?,?,?,?,?,s_news_id.nextval)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sNewsTitle);
			stmt.setString(2, sPart);
			stmt.setString(3, "N");
			stmt.setString(4, sPuber);
			stmt.setString(5, towho);
			stmt.setString(6, bmdm);
			//System.out.println("sql:"+sql);
			//System.out.println("sNewsTitle:"+sNewsTitle);
			//System.out.println("sPart:"+sPart);
			//System.out.println("sPuber:"+sPuber);
			//System.out.println("towho:"+towho);
			//System.out.println("bmdm:"+bmdm);
			stmt.executeUpdate();
			sql = "select newscont from newscontent where newscont like 'N' for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				oracle.sql.CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				
				System.out.println("sss:"+rs.getClob("newscont").getClass().getName().startsWith("weblogic"));
				
				if (rs.getClob("newscont").getClass().getName().startsWith(
						"weblogic")) {
					
					//===========2011.6.20 edit by luojw==============================
					
					Writer outStream = null;
					Object obj = rs.getClob("newscont");

					Method method = obj.getClass().getMethod("getVendorObj",
							new Class[] {});
					clob = (CLOB) method.invoke(obj);
					outStream = clob.getCharacterOutputStream();
					char[] c = sContent.toCharArray();
					outStream.write(c, 0, c.length);

					if (outStream != null) {
						outStream.flush();
						outStream.close();
					}

					// Method method =
					// rs.getClob("newscont").getClass().getMethod("getVendorObj",new
					// Class[]{});
					// clob =
					// (oracle.sql.CLOB)method.invoke(rs.getClob("newscont"));
				}else{
		 	    	clob = (CLOB) rs.getClob("newscont");
					clob = (CLOB) rs.getClob("newscont");
					
					Writer outstream = clob.getCharacterOutputStream();		
					//FileCopyUtils.copy(sContent, outstream);		
					outstream.write(sContent);
					outstream.close();
					
		 	    }		
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}
	
	// �����������Ϊһ������
	public String[] unionArray(String[] array1, String[] array2) {
		if (array1 != null) {
			if (array2 != null) {
				String array[] = new String[array1.length + array2.length];
				copyArray(array1, array);
				for (int i = 0; i < array2.length; i++) {
					array[array1.length + i] = array2[i];
				}
				return array;
			} else {
				return array1;
			}
		} else {
			return array2;
		}
	}

	// ��һ������copy����һ������
	public String[] copyArray(String[] fromArray, String[] toArray2) {
		if (fromArray != null && toArray2 != null) {
			int min = fromArray.length <= toArray2.length ? fromArray.length
					: toArray2.length;
			for (int i = 0; i < min; i++) {
				toArray2[i] = fromArray[i];
			}
			return toArray2;
		} else {
			if (toArray2 == null) {
				return fromArray;
			} else {
				return toArray2;
			}
		}
	}

	// ���ز�����
	public boolean checkBatch(int[] result) {
		boolean flag = false;
		for (int j = 0; j < result.length; j++) {
			if (result[j] > -1) {
				flag = true;
			} else {
				return false;
			}
		}
		return flag;
	}

	public boolean createCwxx() {
		try {
			runProcuder("{call update_cwxxb()}", new String[] {});// ��֤��λ��Ϣ�Ƿ���ȷ
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * �����������Ϊһ������,ǰ���Ǳ�֤�������鳤�ȱ���һ��
	 * �ϲ�˳��:array1[0],array2[0],array1[1],array2[1],array1[2],array2[2]...
	 */ 
		
		public String[] unionArrayTo(String[] array1, String[] array2) {
			if (array1 != null) {
				if (array2 != null) {
					String array[] = new String[array1.length + array2.length];
					int j = 0;
					for (int i = 0; i<array.length; i+=2) {
						if (i==array.length-1){
							break;
						} 
						array[i] = array1[j];
						array[i+1] = array2[j];
						j++;
					}
					return array;
				} else {
					return array1;
				}
			} else {
				return array2;
			}
		}

	public DataSource getDb() {
		return db;
	}

	public void setDb(DataSource db) {
		this.db = db;
	}
	public ArrayList<HashMap<String, String>> getArrayList3(String sql,
			String[] inputValue, String[] outputValue,String parm) {

		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;

		// ����List�ṹ�Ľ����
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		String nowtime = (getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				// ����ʱ��25
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		dealDate dealdate = new dealDate();
		String sjyear = nowtime.substring(0, 4);
		String sjmonth = nowtime.substring(4, 6);
		String sjday = nowtime.substring(6, 8);
		nowtime = sjyear + "-" + sjmonth + "-" + sjday;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
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
				}
				map.put("allnewstitle", map.get("newstitle"));
				map.put("allwjbt", map.get("wjbt"));
				map.put("allgsmc", map.get("gsmc"));

				String pubtime = map.get("pubtime");// ����ʱ��
				
				if (null != pubtime
						&& !"".equalsIgnoreCase(pubtime)) {
					String pubtime1 = pubtime.replaceAll("-", "");
					pubtime1 = pubtime1.replaceAll(":", "");
					pubtime1 = pubtime1.replaceAll(" ", "");
					pubtime1 = pubtime1.substring(0, 8);
					String temptime = dealdate.getDate2(Integer.parseInt("-3"), nowtime);
					temptime = temptime.replaceAll("-", "");
					if(pubtime1.compareTo(temptime)>=0){
						map.put("newmark", "new");
					}
				}
				
				String fbsj = map.get("fbsj");// ����ʱ��
				
				if (null != fbsj
						&& !"".equalsIgnoreCase(fbsj)) {
					String fbsj1 = fbsj.replaceAll("-", "");
					fbsj1 = fbsj1.replaceAll(":", "");
					fbsj1 = fbsj1.replaceAll(" ", "");
					fbsj1 = fbsj1.substring(0, 8);
					String temptime = dealdate.getDate2(Integer.parseInt("-3"), nowtime);
					temptime = temptime.replaceAll("-", "");
					if(fbsj1.compareTo(temptime)>=0){
						map.put("newmark", "new");
					}
				}
//				String pubtime = map.get("pubtime");// ����ʱ��
//				if (null != pubtime
//						&& !"".equalsIgnoreCase(pubtime)
//						&& (pubtime.length() == 19 || pubtime.length() == 10 || pubtime
//								.length() == 14)) {
//					String pubtime1 = pubtime.substring(0, 8);
//					if (pubtime1.equalsIgnoreCase(nowtime)) {
//						map.put("newmark", "new");
//					}
//				}
//
//				String fbsj = map.get("fbsj");// ����ʱ��
//				if (null != fbsj
//						&& !"".equalsIgnoreCase(fbsj)
//						&& (fbsj.length() == 19 || fbsj.length() == 10 || fbsj
//								.length() == 14)) {
//					String fbsj1 = fbsj.substring(0, 8);
//					if (fbsj1.equalsIgnoreCase(nowtime)) {
//						map.put("newmark", "new");
//					}
//				}
				

				if(map.get("pubtime") != null
						&& !"".equals(map.get("pubtime"))&&map.get("pubtime").length()==14){
					map.put("pubtime", doForTime3(map.get("pubtime")));
				}else if (map.get("pubtime") != null
						&& !"".equals(map.get("pubtime"))) {
					map.put("pubtime", doForTime5(map.get("pubtime")));
				}
				if(map.get("fbsj") != null
						&& !"".equals(map.get("fbsj"))&&map.get("fbsj").length()==14){
					map.put("fbsj", doForTime3(map.get("fbsj")));
				}else if (map.get("fbsj") != null && !"".equals(map.get("fbsj"))) {
					map.put("fbsj", doForTime2(map.get("fbsj")));
				}
				if (map.get("newstitle") != null
						&& !"".equals(map.get("newstitle"))
						&& map.get("newstitle").length() > 14) {
					String newstitle = map.get("newstitle").substring(0, 14);
					newstitle += "��";
					map.put("newstitle", newstitle);

				}
				if("xyxjh".equals(parm)){
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 18) {
						String wjbt = map.get("wjbt").substring(0, 18);
						wjbt += "��";
						map.put("wjbt", wjbt);
					}
				}else if("jyzc".equals(parm)){
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 9) {
						map.put("wjbttitle", map.get("wjbt"));
						String wjbt = map.get("wjbt").substring(0, 9);
						wjbt += "��";
						map.put("wjbt", wjbt);
					}
				}else if("bystj".equals(parm)){
					if (map.get("qzyx") != null && !"".equals(map.get("qzyx"))
							&& map.get("qzyx").length() > 20) {
						String qzyx = map.get("qzyx").substring(0, 20);
						qzyx += "��";
						map.put("qzyx", qzyx);
					}
				}else if("qzyx".equals(parm)){
					if (map.get("qzyx") != null && !"".equals(map.get("qzyx"))
							&& map.get("qzyx").length() > 13) {
						String qzyx = map.get("qzyx").substring(0, 13);
						qzyx += "��";
						map.put("qzyx", qzyx);
					}
				}else{
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 12) {
						map.put("wjbttitle", map.get("wjbt"));
						String wjbt = map.get("wjbt").substring(0, 12);
						wjbt += "��";
						map.put("wjbt", wjbt);
					}
				}
				
				if (map.get("bt") != null && !"".equals(map.get("bt"))
						&& map.get("bt").length() > 6) {
					String bt = map.get("bt").substring(0, 6);
					bt += "��";
					map.put("bt", bt);
				}
				if("xnzp".equals(parm)){
					if (map.get("gsmc") != null && !"".equals(map.get("gsmc"))
							&& map.get("gsmc").length() > 15) {
						String gsmc = map.get("gsmc").substring(0, 15);
						gsmc += "��";
						map.put("gsmc", gsmc);
					}
				}else if("dwxxk".equals(parm)){
					if (map.get("dwmc") != null && !"".equals(map.get("dwmc"))
							&& map.get("dwmc").length() > 20) {
						String dwmc = map.get("dwmc").substring(0, 20);
						dwmc += "��";
						map.put("alldwmc", dwmc);
					}else{
						if(map.get("dwmc") != null){
							map.put("alldwmc", map.get("dwmc"));
						}
					}
					String zjrq = map.get("zjrq");// ����ʱ��
					
					if (null != zjrq
							&& !"".equalsIgnoreCase(zjrq)) {
						String fbsj1 = zjrq.replaceAll("-", "");
						fbsj1 = fbsj1.replaceAll(":", "");
						fbsj1 = fbsj1.replaceAll(" ", "");
						fbsj1 = fbsj1.substring(0, 8);
						String temptime = dealdate.getDate2(Integer.parseInt("-3"), nowtime);
						temptime = temptime.replaceAll("-", "");
						if(fbsj1.compareTo(temptime)>=0){
							map.put("newmark", "new");
						}
					}
					if(map.get("zjrq") != null
							&& !"".equals(map.get("zjrq"))&&map.get("zjrq").length()==14){
						map.put("zjrq", doForTime3(map.get("zjrq")));
					}
				}else{
					if (map.get("gsmc") != null && !"".equals(map.get("gsmc"))
							&& map.get("gsmc").length() > 20) {
						String gsmc = map.get("gsmc").substring(0, 20);
						gsmc += "��";
						map.put("gsmc", gsmc);
					}
				}
				
				if (map.get("zpzw") != null && !"".equals(map.get("zpzw"))
						&& map.get("zpzw").length() > 8) {
					String zpzw = map.get("zpzw").substring(0, 8);
					zpzw += "��";
					map.put("zpzw", zpzw);
				}
				arrayList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			arrayList = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return arrayList;
	}
	
	//����ʱ��һ�ֶε�ֵΪ��������2���ֶε�ֵΪֵ
	public HashMap<String, String> getHashMapList2(String sql,
			String[] inputValue, String[] outputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// ����hashMap�ṹ�Ľ����,�Ե�һ���ֶε�ֵΪ����
		HashMap<String, String> mapX = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] tmp = new String[outputValue.length];
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					if ((tempstr == null) || tempstr.equalsIgnoreCase("")) {
						tmp[i] = "";
					} else {
						tmp[i] = tempstr;
					}
				}
				mapX.put(tmp[0], tmp[1]);
				tmp= null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			mapX = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return mapX;
	}	
	/**
	 * ��ȡѧ�����͡��������й����ʴ�ѧ��
	 * @param type "lx","lb"
	 * @return
	 */	 
	public List<HashMap<String, String>>  getStuType(String type){
		List<HashMap<String, String>> list= null;
		if("lb".equalsIgnoreCase(type)){
			list = getList("select dm,mc from stu_lbdmb order by dm",new String[]{},new String[]{"dm","mc"});
		}else{
			list = getList("select dm,mc from stu_lxdmb order by dm",new String[]{},new String[]{"dm","mc"});
		}
		return list;
	}
	/**
	 * 
	 * @param list
	 * @param cols
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean addXxjs(String[] values) throws Exception {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// �������
		boolean res = true;
		String sql = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
//			sql = "delete from xxjsb";
//			stmt = conn.prepareStatement(sql);
//			stmt.executeUpdate();
			sql = "insert into xxjsb(jsbt,sfxs,xxjs) values (?,?,empty_clob())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, values[0]);
			stmt.setString(2, values[1]);
			stmt.executeUpdate();
			conn.commit();
			sql = "select xxjs from xxjsb where rownum < 2 for update";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()) {
				CLOB clob = (CLOB) rs.getClob("xxjs");
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(values[2]);
				outstream.flush();
				outstream.close();
				sql = "commit";
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}
	
	/**
	 * ���ذ����������༶ѧ���б�
	 * @param zgh ְ����
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrXsList(String zgh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh, a.xm, a.xb, a.xymc,a.zymc,a.bjmc from view_xsjbxx a where exists (select 1 ");
		sb.append("from fdybjb b where a.bjdm = b.bjdm and b.zgh = ?)");
		return getList(sb.toString(), new String[] { zgh }, new String[] {
				"xh", "xm", "xb", "xymc", "zymc", "bjmc" });
	}
	
	/**
	 * ��������������DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveData(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		String[] arrzd = form.getArrzd();// �����ֶ�
		String[] onezd = form.getOnezd();// ��һ�ֶ�
		int size = (arrzd == null) ? 0 : arrzd.length + ((onezd == null) ? 0 : onezd.length);
		String[] notnull = form.getNotnull();//�ǿ��ֶ�
		StringBuffer del = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		List<String[]> arrList = new ArrayList<String[]>();
		List<String> oneList = new ArrayList<String>();
		String[] arrnull = new String[size];
		String[] notinsert = null;
		String[] values = null;
		String[] insert = null;

		Class myClass = model.getClass();
		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				del.append("delete from " + tableName);
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						del.append(" where " + pk + "='" + pkValue[i] + "'");
					} else {
						del.append(" or " + pk + "='" + pkValue[i] + "'");
					}
				}

				if (arrzd != null && arrzd.length > 0) {
					sql.append("insert into " + tableName + "(");
					for (int i = 0; i < arrzd.length; i++) {
						if (notnull != null && notnull.length > 0) {
							for (int j = 0; j < notnull.length; j++) {
								if (notnull[j] == arrzd[i]) {
									arrnull[i] = arrzd[i];
								}
							}
						}
						String methodName = "get"
								+ arrzd[i].substring(0, 1).toUpperCase()
								+ arrzd[i].substring(1);
						String[] arr = (String[]) myClass.getMethod(methodName,
								(Class[]) null).invoke(model, (Object[]) null);
						arrList.add(arr);
						if (i != 0) {
							sql.append(", ");
						}
						sql.append(arrzd[i]);
					}
					
					if (onezd != null && onezd.length > 0) {
						for (int i = 0; i < onezd.length; i++) {
							if (notnull != null && notnull.length > 0) {
								for (int j = 0; j < notnull.length; j++) {
									if (notnull[j] == onezd[i]) {
										arrnull[i] = arrzd[i];
									}
								}
							}
							String methodName = "get"
									+ onezd[i].substring(0, 1).toUpperCase()
									+ onezd[i].substring(1);
							String one = (String) myClass.getMethod(methodName,
									(Class[]) null).invoke(model,
									(Object[]) null);
							oneList.add(one);
							sql.append(", ");
							sql.append(onezd[i]);
						}
					}
					sql.append(") ");
				}

				if (arrList != null && arrList.size() > 0) {
					for (int i = 0; i < arrList.size(); i++) {
						if (arrList.get(i) != null && arrList.get(i).length > 0) {
							for (int j = 0; j < arrList.get(i).length; j++) {
								if (i == 0 && j == 0) {
									values = new String[arrList.get(i).length];
									notinsert = new String[arrList.get(i).length];
								}
								if (!Base.isNull(arrnull[i])) {
									if (Base.isNull(arrList.get(i)[j])) {
										notinsert[j] = "no";
									}
								}
								values[j] += ",'" + arrList.get(i)[j] + "'";
							}
						}
					}
				}

				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						if (oneList != null && oneList.size() > 0) {
							for (int j = 0; j < oneList.size(); j++) {
								values[i] += ",'" + oneList.get(j) + "'";
							}
						}
					}
				}

				if (values != null && values.length > 0) {
					insert = new String[values.length];
					for (int i = 0; i < values.length; i++) {
						if (notinsert != null && "no".equalsIgnoreCase(notinsert[i])) {
							continue;
						} else {
							insert[i] = sql.toString() + " values("+ values[i].replace("null,", "") + ")";
						}
					}
				}

				flg = runUpdate(del.toString(), new String[] {});

				if (flg && insert != null && insert.length > 0) {
					int[] res = runBatch(insert);
					for (int i = 0; i < res.length; i++) {
						flg = (res[i] == Statement.EXECUTE_FAILED) ? false
								: true;
						if (!flg)
							break;
					}
				}
			}

		}

		return flg;
	}
	
	/**
	 * ��������������DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveData(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		String[] arrzd = form.getArrzd();// �����ֶ�
		String[] onezd = form.getOnezd();// ��һ�ֶ�
		int size = (arrzd == null) ? 0 : arrzd.length + ((onezd == null) ? 0 : onezd.length);
		String[] notnull = form.getNotnull();//�ǿ��ֶ�
		StringBuffer del = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		List<String[]> arrList = new ArrayList<String[]>();
		List<String> oneList = new ArrayList<String>();
		String[] arrnull = new String[size];
		String[] notinsert = null;
		String[] values = null;
		String[] insert = null;

		Class myClass = model.getClass();
		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				del.append("delete from " + tableName);
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						del.append(" where " + pk + "='" + pkValue[i] + "'");
					} else {
						del.append(" or " + pk + "='" + pkValue[i] + "'");
					}
				}

				if (arrzd != null && arrzd.length > 0) {
					sql.append("insert into " + tableName + "(");
					for (int i = 0; i < arrzd.length; i++) {
						if (notnull != null && notnull.length > 0) {
							for (int j = 0; j < notnull.length; j++) {
								if (notnull[j] == arrzd[i]) {
									arrnull[i] = arrzd[i];
								}
							}
						}
						String methodName = "get"
								+ arrzd[i].substring(0, 1).toUpperCase()
								+ arrzd[i].substring(1);
						String[] arr = (String[]) myClass.getMethod(methodName,
								(Class[]) null).invoke(model, (Object[]) null);
						arrList.add(arr);
						if (i != 0) {
							sql.append(", ");
						}
						sql.append(arrzd[i]);
					}
					
					if (onezd != null && onezd.length > 0) {
						for (int i = 0; i < onezd.length; i++) {
							if (notnull != null && notnull.length > 0) {
								for (int j = 0; j < notnull.length; j++) {
									if (notnull[j] == onezd[i]) {
										arrnull[i] = arrzd[i];
									}
								}
							}
							String methodName = "get"
									+ onezd[i].substring(0, 1).toUpperCase()
									+ onezd[i].substring(1);
							String one = (String) myClass.getMethod(methodName,
									(Class[]) null).invoke(model,
									(Object[]) null);
							oneList.add(one);
							sql.append(", ");
							sql.append(onezd[i]);
						}
					}
					sql.append(") ");
				}

				if (arrList != null && arrList.size() > 0) {
					for (int i = 0; i < arrList.size(); i++) {
						if (arrList.get(i) != null && arrList.get(i).length > 0) {
							for (int j = 0; j < arrList.get(i).length; j++) {
								if (i == 0 && j == 0) {
									values = new String[arrList.get(i).length];
									notinsert = new String[arrList.get(i).length];
								}
								if (!Base.isNull(arrnull[i])) {
									if (Base.isNull(arrList.get(i)[j])) {
										notinsert[j] = "no";
									}
								}
								values[j] += ",'" + arrList.get(i)[j] + "'";
							}
						}
					}
				}

				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						if (oneList != null && oneList.size() > 0) {
							for (int j = 0; j < oneList.size(); j++) {
								values[i] += ",'" + oneList.get(j) + "'";
							}
						}
					}
				}

				if (values != null && values.length > 0) {
					insert = new String[values.length];
					for (int i = 0; i < values.length; i++) {
						if (notinsert != null && "no".equalsIgnoreCase(notinsert[i])) {
							continue;
						} else {
							insert[i] = sql.toString() + " values("+ values[i].replace("null,", "") + ")";
						}
					}
				}

				flg = runUpdate(del.toString(), new String[] {});

				if (flg && insert != null && insert.length > 0) {
					int[] res = runBatch(insert);
					for (int i = 0; i < res.length; i++) {
						flg = (res[i] == Statement.EXECUTE_FAILED) ? false
								: true;
						if (!flg)
							break;
					}
				}
			}

		}
		if(flg){
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "runBatch :");
			dmlLogger.insertLog(del.toString(), null, user);
			dmlLogger.insertLog(Arrays.deepToString(insert), null, user);
		}
		return flg;
	}
	
	/**
	 * �����޸�DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean updateData(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		String[] onezd = form.getOnezd();// ��һ�ֶ�
		StringBuffer query = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		List<String> oneList = new ArrayList<String>();

		Class myClass = model.getClass();
		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						query.append(" where " + pk + "='" + pkValue[i] + "'");
					} else {
						query.append(" or " + pk + "='" + pkValue[i] + "'");
					}
				}

				if (onezd != null && onezd.length > 0) {
					sql.append("update " + tableName + " set");
					
					for (int i = 0; i < onezd.length; i++) {
						
						String methodName = "get"
								+ onezd[i].substring(0, 1).toUpperCase()
								+ onezd[i].substring(1);
						String one = (String) myClass.getMethod(methodName,
								(Class[]) null).invoke(model, (Object[]) null);
						oneList.add(one);
						if (i == 0) {
							sql.append(" ");
							sql.append(onezd[i] + "='" + one+"'");
						}else{
							sql.append(",");
							sql.append(onezd[i] + "='" + one+"'");
						}
						
					}
					
					flg = runUpdate(sql.toString() + query.toString(),
							new String[] {});
				}		
			}

		}

		return flg;
	}
	
	/**
	 * �����޸�DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean updateData(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		String[] onezd = form.getOnezd();// ��һ�ֶ�
		StringBuffer query = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		List<String> oneList = new ArrayList<String>();

		Class myClass = model.getClass();
		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						query.append(" where " + pk + "='" + pkValue[i] + "'");
					} else {
						query.append(" or " + pk + "='" + pkValue[i] + "'");
					}
				}

				if (onezd != null && onezd.length > 0) {
					sql.append("update " + tableName + " set");
					
					for (int i = 0; i < onezd.length; i++) {
						
						String methodName = "get"
								+ onezd[i].substring(0, 1).toUpperCase()
								+ onezd[i].substring(1);
						String one = (String) myClass.getMethod(methodName,
								(Class[]) null).invoke(model, (Object[]) null);
						oneList.add(one);
						
						if (null != one && !"null".equalsIgnoreCase(one)){
							if (i == 0) {
								sql.append(" ");
								sql.append(onezd[i] + "='" + one+"'");
							}else{
								sql.append(",");
								sql.append(onezd[i] + "='" + one+"'");
							}
						}
						
					}
					
					flg = runUpdate(sql.toString() + query.toString(),
							new String[] {});
				}		
			}

		}
		if(flg){
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql.toString()+ query.toString(), null, user);
		}
		return flg;
	}
	
	/**
	 * �ύ���ݣ�������DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean submitData(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		String[] onezd = form.getOnezd();// ��һ�ֶ�
		String[] key = null;

		Class myClass = model.getClass();
		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {
				if (onezd != null && onezd.length > 0) {
					flg = StandardOperation.delete(tableName, pk, pkValue[0],
							request);
					if (onezd != null && onezd.length > 0) {
						key = new String[onezd.length];
						for (int i = 0; i < onezd.length; i++) {
							String methodName = "get"
									+ onezd[i].substring(0, 1).toUpperCase()
									+ onezd[i].substring(1);
							String one = (String) myClass.getMethod(methodName,
									(Class[]) null).invoke(model,
									(Object[]) null);
							key[i] = one;
						}
					}

					if (flg) {
						flg = StandardOperation.insert(tableName, onezd, key,
								request);
					}
				}
			}
		}

		return flg;
	}
	
	/**
	 * ����ɾ��DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean delDate(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		StringBuffer del = new StringBuffer();

		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				del.append("delete from " + tableName);
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						del.append(" where " + pk + "= ? ");
					} else {
						del.append(" or " + pk + "= ? ");
					}
				}

				flg = runUpdate(del.toString(), pkValue);

			}

		}		
		return flg;
	}
	
	/**
	 * ����ɾ��DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean delDate(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// ����
		String pk = form.getPk();// ����
		String[] pkValue = form.getPkValue();// ����ֵ
		StringBuffer del = new StringBuffer();

		if (!Base.isNull(tableName)) {
			if (!Base.isNull(pk) && pkValue != null && pkValue.length > 0) {

				del.append("delete from " + tableName);
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						del.append(" where " + pk + "= ? ");
					} else {
						del.append(" or " + pk + "= ? ");
					}
				}

				flg = runUpdate(del.toString(), pkValue);

			}

		}
		if(flg){
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "delete :");
			dmlLogger.insertLog(del.toString(), null, user);
		}
		return flg;
	}
	
	/**
	 * ������һ��ѧ�ڣ������Ӧ��ѧ�꣬��ȣ��ʺ�����ѧ�ڵģ�
	 */
	public HashMap<String, String> getBeforeXqInfo(){
		String xq = "";
		String xn = "";
		String nd = "";
		String beforeXq = "";
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> xqList = getXqList();
		for(int i=0;i<xqList.size();i++){
			map = xqList.get(i);
			xq = map.get("xqdm");
			if(xq != null && !xq.equals(Base.currXq)){
				beforeXq = xq;
				break;
			}
		}
		if(Base.currXn!=null && Base.currNd!=null){
			if(Base.currNd.equals(Base.currXn.substring(0, 4))){
				xn = Base.beforXn;
				nd = Base.currNd;
			}else{
				xn = Base.currXn;
				nd = (Integer.parseInt(Base.currNd)-1)+"";
			}
		}
		map = new HashMap<String, String>();
		map.put("xn", xn);
		map.put("xq", beforeXq);
		map.put("nd", nd);
		return map;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		
		return flag;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql, User user)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		if(flag){
			//д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData("", "runBatch :");
			dmlLogger.insertLog(Arrays.deepToString(sql), null, user);
		}
		return flag;
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue) {

		if (Base.isNull(msg)) {
			msg = "----��ѡ��-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @pararm isnull���Ƿ���Ҫ��ʾ��ѡ��
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue, boolean isnull) {

		if (isnull) {
			msg = "----��ѡ��-----";
		}

		StringBuffer sql = new StringBuffer();
		if(isnull){
			sql.append("select '' dm, '" + msg + "'mc from dual union");
		}
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append("case when length("+mc+")>7 then substr("+mc+",0,7)||'...' else "+mc+" end mc ");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param bsf(��ʶ����
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk,String bsf, String pkValue) {

		if (Base.isNull(msg)) {
			msg = "----��ѡ��-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + " "+ bsf + " '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	/**
	 * ɾ�����ϴ��ļ�
	 * 
	 * @param tableName(����)
	 * @param dzzd(�ļ���ַ�ֶ�)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luo
	 * @throws Exception 
	 */
	public boolean fileDel(String tableName,String dzzd,String pk,String pkValue) throws Exception{
//		DAO dao = new DAO();
		
		boolean flag = true;
		//����ļ�����·��
		String sql = "select " + dzzd + " scdz from " + tableName + " where " + pk + " = ?";
		String wjlj = getOneRs(sql, new String[]{pkValue}, "scdz");
		if(wjlj!=null){
			File file = new File(wjlj);
	    	if (file.exists()) {
	    		//�ļ����ڣ�ɾ�����ļ�
				flag = file.delete();
				if (flag) {
					//ɾ���ɹ�����ոõ�ַ
					sql = "update " + tableName + " set " + dzzd + "='' where "
							+ pk + " = ?";
					flag = runUpdate(sql, new String[] { pkValue });
				}
			}
		}
		return flag;
	}
	
	/**
	 * ����ܴ��б�
	 * 
	 * @author luo
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getZcList() throws SQLException {
		String sql = "select xqzs from xtszb where rownum=1";
		String xqzs = getOneRs(sql, new String[] {}, "xqzs");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(xqzs)) {
			for (int i = 1; i <= Integer.parseInt(xqzs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				if (i < 10) {
					map.put("dm", "0" + String.valueOf(i));
					map.put("mc", "��0" + String.valueOf(i) + "��");
				} else {
					map.put("dm", String.valueOf(i));
					map.put("mc", "��" + String.valueOf(i) + "��");
				}
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		String sql = "";
		if ("YYYY��MM��DD��".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '��' || to_char(sysdate, 'MM') "
					+ "|| '��' || to_char(sysdate, 'DD') || '��' now from dual";
		} else if ("YYYYMMDD".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') ||  to_char(sysdate, 'MM') "
					+ "||  to_char(sysdate, 'DD') now from dual";
		} else if ("yyyy-mm-dd hh24:mi:ss".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') now from dual";
		}
		String now = getOneRs(sql, new String[] {}, "now");
		return now;
	}
	
	/**
	 * ���ָ��ѧ�ڵ���һѧ��
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBeforeXq(HashMap<String, String> map) {
		HashMap<String, String> befMap = new HashMap<String, String>();

		// ָ��ѧ��
		String xn = map.get("xn");
		// ָ��ѧ��
		String xq = map.get("xq");
		// ���ѧ��
		String[] arrXn = xn.split("-");
		// ǰһѧ��
		String before = (arrXn != null && arrXn.length > 1) ? String
				.valueOf(Integer.parseInt(arrXn[0]) - 1) : "";
		String after = (arrXn != null && arrXn.length > 1) ? String
				.valueOf(Integer.parseInt(arrXn[1]) - 1) : "";
		String befXn = (!Base.isNull(before) && !Base.isNull(after)) ? before
				+ "-" + after : "";
		// ǰһѧ��
		String befXq = "";

		List<HashMap<String, String>> xqList = getXqList();

		if (xqList != null && xqList.size() > 0) {
			for (int i = 0; i < xqList.size(); i++) {
				HashMap<String, String> xqMap = xqList.get(i);
				if (xq.equalsIgnoreCase(xqMap.get("xqdm"))) {
					if (i == 0) {
						befXq = xqList.get(xqList.size() - 1).get("xqdm");
						break;
					} else {
						befXq = xqList.get(i - 1).get("xqdm");
						befXn = xn;
						break;
					}
				}
			}
		}

		befMap.put("xn", befXn);
		befMap.put("xq", befXq);

		return befMap;
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(dm);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk +"='"+pkValue+"'");
		sql.append(" and rownum = 1 ");
		
		String value = getOneRs(sql.toString(), new String[] {}, dm);
		return value;
	}
	
	/**
	 * ³��
	 * 2010-5-27
	 * ���������ж�ȡ���ݣ�������blob��ʽ�����ݵ����ݿ�
	 * @param sql
	 * @param input
	 * @param is
	 * @return
	 */
	public void upBlob(String sql,String xh,int m,InputStream instream) throws Exception{
	       // OracleResultSet rs = null;
	    	
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try { 
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	        	stmt = conn.prepareStatement(sql);
	        	stmt.setString(2,xh);
	        	stmt.setBinaryStream(1,instream,m);
	            stmt.executeUpdate();
	    	} catch (SQLException e) {
				e.printStackTrace();
				CustomException.pringCustomExcInfo(sql);
			} finally {
				conn.commit();
				closeAllStmtAndRs(null, stmt, null, null);
				closeConnection(conn);
			}
	    }

	/**
	 * ��ȡ���������ѡ��
	 * @param sql
	 * @param inputValue
	 * @param outputValue
	 * @return
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> getOptionList(String sql,
			String[] inputValue, String[] outputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// ����hashMap�ṹ�Ľ����,�Ե�һ���ֶε�ֵΪ����
		HashMap<String, ArrayList<HashMap<String, String>>> mapX = new HashMap<String, ArrayList<HashMap<String, String>>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<HashMap<String, String>> arrayList = mapX.get(rs.getString(outputValue[0]));
				if(arrayList==null){
					arrayList = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> listtmp = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					if ((tempstr == null) || tempstr.equalsIgnoreCase("")) {
						listtmp.put(outputValue[i], "");
					} else {
						listtmp.put(outputValue[i], tempstr);
					}
				}
				arrayList.add(listtmp);
				mapX.put(rs.getString(outputValue[0]), arrayList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			mapX = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return mapX;
	}
	
	/**
	 * ��ȡ���ڵ��ֶμ���ע��,���ȣ��Ƿ�Ϊ�գ����������ֵ
	 * ����NULLABLEΪ�Ƿ�Ϊ�գ�YΪ����Ϊ�գ�NΪ������Ϊ��
	 * @param cName
	 * @param tabName
	 * @return
	 */
	public List<HashMap<String, String>> getColumnAttributeList(String tabName,String[] outPutString) {
		// �����ݱ�Ӣ������ת��Ϊ����������������������[��������]���飨����ΪӢ����������ͱ�����
		String xxdm = Base.xxdm;
		StringBuilder sqlBuilder = new StringBuilder("select comments cn,column_name en,(select DATA_LENGTH from user_tab_columns where ");
		sqlBuilder.append("TABLE_NAME = a.TABLE_NAME and a.COLUMN_NAME = COLUMN_NAME)/2 length,(select NULLABLE from ");
		sqlBuilder.append("user_tab_columns where TABLE_NAME = a.TABLE_NAME and a.COLUMN_NAME = COLUMN_NAME) NULLABLE,zdlx ");
		sqlBuilder.append("from user_col_comments a left join (select zdlx,zbzd ");
		sqlBuilder.append("from dmwhxmglb where whdmb = ? and xxdm = ? and zdlx like 'dis%' union all select zdlx,zbzd ");
		sqlBuilder.append("from dmwhxmglb where xxdm = 'all' and whdmb = ? and zdlx like 'dis%') b on column_name = upper(zbzd)");
		sqlBuilder.append(" where table_name=upper(?) ");
		if(outPutString!=null&&outPutString.length!=0){
			sqlBuilder.append("and COLUMN_NAME in ( ");
			for(int i=0;i<outPutString.length;i++){
				sqlBuilder.append("upper('");
				sqlBuilder.append(outPutString[i]);
				sqlBuilder.append("')");
				sqlBuilder.append(",");
			}
			sqlBuilder.append("'KFSC_XG' ");
			sqlBuilder.append(")");
		}
		sqlBuilder.append(" order by en ");
		//		 ��ѯ��List�ṹ�Ľ����
		List<HashMap<String, String>> rs = getList(sqlBuilder.toString(),new String[]{tabName,xxdm,tabName,tabName},new String[]{"en","cn","length","nullable","zdlx"});
		//		 ����List�ṹ�Ľ����
		if(outPutString!=null&&outPutString.length!=0){
			ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
			for(int j =0;j<outPutString.length;j++){
				for(int i = 0;i<rs.size();i++){
					if(rs.get(i).get("en").equalsIgnoreCase(outPutString[j])){
						HashMap<String, String> hrs =  rs.get(i);
						if(hrs.get("length")=="0.5"){
							hrs.put("length", "1");
						}
						arrayList.add(hrs);
					 	break;
					}
				}
			}
			return arrayList;
		}else {
			return rs;
		}
		
	}

	/**
	 * ����һ����¼ָ����ֵ
	 * @param tableName
	 * @param fields
	 * @param input
	 * @return 
	 * @throws Exception
	 */
	public boolean runInsert(String tableName,String[] fields,String[] input) throws Exception{
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(tableName)
		.append("(");
		
		for(int i=0;i<fields.length;i++){
			if(i == fields.length-1){
				sql.append(fields[i]+")");
			}else {
				sql.append(fields[i]+",");
			}
		}
		
		sql.append(" values(");
		
		for(int i=0;i<input.length;i++){
			if(i == fields.length-1){
				sql.append("'").append(input[i]).append("')");
			}else {
				sql.append("'").append(input[i]).append("',");
			}
		}

		Connection conn = null;
		Statement stat = null;
		
		try{
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			int rs = stat.executeUpdate(sql.toString());
			
			conn.commit();
			
			if(rs==1){
				flag = true;
			}
		}catch(Exception e){
			conn.rollback();
			throw e;
			
		}finally{
			closeAllStmtAndRs(null, null, stat, null);
			closeConnection(conn);
		}
		
		return flag;
	}
	
	
	/** ��ȡBlob�������� */
	public Blob getOneBlob(String sql, String[] inputValue, String colName) {
		Blob blob = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			if(rs.next()){
				//rs.next();
				blob = rs.getBlob(colName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return blob;
	}

	/**
	 * ��ȡָ���������е���
	 * @param tableName
	 * @return
	 * @author sjf
	 */
	public List<String> getColumnsName(String tableName){
		String sql = "select column_name as c from user_col_comments where table_name=upper('" + tableName + "')";
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("c"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		
		return list;
	}
	
	/**
	 * ����Ԥ������䣬���ݿ������������ɴ�����ִ���ٶ�
	 * @author sjf
	 * @param sql Ԥ����sql���
	 * @param params ����ֵ
	 * @return
	 * @throws SQLException 
	 */
	public int[] runBatch(String sql, List<String[]> params) throws SQLException{
		int[] rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for(String[] strs : params){
				for(int i=0; i<strs.length; i++){
					stmt.setString(i+1, strs[i]);
				}
				stmt.addBatch();
			}
			
			rs = stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		} 
		return rs;
	}
	
	/**
	 * �ϲ�����
	 * @param arrays
	 * @return
	 * @author sjf
	 */
	public String[] uniteArrays(String[]...arrays){
		int length = 0;
		for (int i=0; i<arrays.length; i++){
			length += arrays[i].length;
		}
		
		String[] strs = new String[length];
		
		int count = 0;
		for(String[] array : arrays){
			for(int j=0; j<array.length; j++){
				strs[count++] = array[j];
			}
		}
		
		return strs; 
	}
	
	
	public boolean checkBatchResult(int result[]){
		
		boolean flag = true;
		
		for (int i = 0; i < result.length; i++) {
			flag = (result[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		
		return flag;
	}
	
	public List<HashMap<String,String>> getAllBjList(){
		String sql = "select distinct nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_njxyzybj";
		return getListNotOut(sql, new String[]{});
	}
	public List<HashMap<String,String>> getAllZyNewList(){
		String sql = "select distinct nj,xydm,xymc,zydm,zymc from view_njxyzybj_all";
		return getListNotOut(sql, new String[]{});
	}
	public List<HashMap<String,String>> getBjAllList(){
		String sql = "select distinct nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_njxyzybj_all";
		return getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 2010-5-27
	 * ���������ж�ȡ���ݣ�������blob��ʽ�����ݵ����ݿ�
	 * @param sql
	 * @param inputList �����в�����BLOB����ֵ ��һλ�������󣬵ڶ�λ��ʼ��ֵ
	 * @param is
	 * @return
	 */
	public boolean updateBlob(String sql,String[] inputList,int m,InputStream instream) throws Exception{
	       // OracleResultSet rs = null;
		boolean flag = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try { 
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	        	stmt = conn.prepareStatement(sql);
	        	stmt.setBinaryStream(1,instream,m);
	        	for (int i = 0; i < inputList.length; i++) {
					stmt.setString(i + 2, inputList[i]);
				}
	            stmt.executeUpdate();
	    	} catch (SQLException e) {
	    		flag = false;
				e.printStackTrace();
				CustomException.pringCustomExcInfo(sql);
			} finally {
				conn.commit();
				closeAllStmtAndRs(null, stmt, null, null);
				closeConnection(conn);
			}
			return flag;
	    }
	
	/**
	 * 2012-10-22
	 * @param sql
	 * @param �����������ֶ�
	 * @param is
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> rsToListNotOut(String sql, String[] inputValue) {

		List<String[]> list = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if (inputValue != null) {
				for (int i = 0; i < inputValue.length; i++) {
					stmt.setString(i + 1, inputValue[i]);
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				String[] tmp = new String[rsmd.getColumnCount()];
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					tmp[i] = rs.getString(rsmd.getColumnName(i + 1));
					if (tmp[i] == null || tmp[i].equalsIgnoreCase("null")) {
						tmp[i] = "";
					}
				}
				list.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,inputValue);
			list = null;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return list;
	}
	
	/**
	 * ����Ԥ������䣬���ݿ������������ɴ�����ִ���ٶ�
	 * @author 1206[����Դ] ����runbatch ԭ�����۽���
	 * @param sql Ԥ����sql���
	 * @param params ����ֵ
	 * @return
	 * @throws SQLException 
	 */
	public boolean runBatchNotCommit(String sql, List<String[]> params) throws SQLException{
		int[] rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = transcationManager.getConnection();
			//conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for(String[] strs : params){
				for(int i=0; i<strs.length; i++){
					stmt.setString(i+1, strs[i]);
				}
				stmt.addBatch();
			}
			
			rs = stmt.executeBatch();
			if(rs == null || rs.length == 0){
				return false;
			}
			for (int i = 0; i < rs.length; i++) {
				if(rs[i] == Statement.EXECUTE_FAILED){
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally{
			closeAllStmtAndRs(null, stmt, null, null);
		} 
		return false;
	}
	
	/**
	 * 
	 * @����: ����������boolean
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����10:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * int[] �������� 
	 * @throws
	 */
	public boolean runBatchBoolean(String sql, List<String[]> params) throws SQLException{
		int[] rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for(String[] strs : params){
				for(int i=0; i<strs.length; i++){
					stmt.setString(i+1, strs[i]);
				}
				stmt.addBatch();
			}
			
			rs = stmt.executeBatch();
			if(rs == null || rs.length == 0){
				return false;
			}
			for (int i = 0; i < rs.length; i++) {
				if(rs[i] == Statement.EXECUTE_FAILED){
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		} 
	}
}