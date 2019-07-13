/*
 * 创建日期 2006-8-24
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
 * 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
		// 构造函数，带有一个参数IP，初始化IP和MAC。供写入日志时使用。
		this();
		this.ip = Ip;
		this.mac = getMacAddressIP(ip);
	}

	public String getMacAddressIP(String remotePcIP) {
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

//	public void closeStmt() {
//		// 通用模块，关闭所有事务
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
	 * 关闭传入的ResultSet、PreparedStatement、Statement、CallableStatement
	 * 
	 * @param rs
	 * @param pstmt
	 * @param stat
	 * @param cstmt
	 */
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
	

	/**
	 * 用于关闭数据库连接,必须保证已经使用完了ResultSet和Statement, 否则将出现使用closed ResultSet和closed
	 * Statement异常
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
		// 通过SQL语句获取数据表列名，返回列名数组
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
		// 将数据表英文列名转化为中文列名，返回中文列名[不含括号]数组（参数为英文列名数组和表名）
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
				if (tit[i].indexOf('（') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('（'));
				}
			}
		}
		return tit;
	}

	public String[] getColumnNameCNAndSm(String[] cName, String tabName) {
		// 将数据表英文列名转化为中文列名，返回中文列名包括说明的数组，模版专用
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
		// 将数据表英文列名转化为中文列名，返回中文列名包括说明的数组，模版专用
		String[] tit = new String[cName.length];
		String sql = "select '最大长度:'||a.DATA_LENGTH||',   是否可以为空:'||decode(a.NULLABLE,'N','否','是')||',   是否有默认值:'"
				+ "||(case when a.data_default is null then '否' else '是' end)||',    数据类型：'||a.DATA_TYPE postil from user_tab_cols a left join user_col_comments b "
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
		// 将数据表英文列名转化为中文列名，返回中文列名[含括号]数组（参数为英文列名数组和表名）
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
		// 结果集的游标可上下移动，但当数据库变化时，结果集不变，不能用结果集更新数据库中的表。
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
			// TODO 自动生成 catch 块
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
		
		// 返回可用于拆分成二维数组的字符串
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
		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		// 'yyyy-mm-dd hh24:mi:ss'
		String nowtime = (getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
						// 发布时间25
						// 取至数据库临时表
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
				String pubtime = map.get("pubtime");// 发布时间
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
				String wjffsj = map.get("wjffsj");// 文件发布时间--针对文件管理
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
		}
		return arrayList;
	}
	/**
	 * author lyl 
	 * Title 学生入住情况统计分析 JDBC
	 * @param sql  
	 * @param inputValue  查询参数
	 * @param outputValue  输出匹配参数
	 * @return
	 */
	public List<HashMap<String, String>> getTjList(String sql,
			String[] inputValue, String[] outputValue) {
		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		// 'yyyy-mm-dd hh24:mi:ss'
		String nowtime = (getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
						// 发布时间25
						// 取至数据库临时表
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
				String pubtime = map.get("pubtime");// 发布时间
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
				String wjffsj = map.get("wjffsj");// 文件发布时间--针对文件管理
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
		}
		return arrayList;
	}
	public ArrayList<HashMap<String, String>> getArrayList(String sql,
			String[] inputValue, String[] outputValue) {

		// 返回List结构的结果集
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

		// 返回List结构的结果集
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
				if (map.get("sqsj") != null && !"".equals(map.get("sqsj"))) {// 重庆科技学生证补办-申请时间
					map.put("sqsj", doForTime2(map.get("sqsj")));
				}
				if (map.get("bbsj") != null && !"".equals(map.get("bbsj"))) {// 重庆科技学生证补办-补办时间
					map.put("bbsj", doForTime2(map.get("bbsj")));
				}
				if (map.get("djsj") != null && !"".equals(map.get("djsj"))) {// 宁波职业-学生就业信息登记时间
					map.put("djsj", doForTime2(map.get("djsj")));
				}
				if (map.get("tdsj") != null && !"".equals(map.get("tdsj"))) {// 就业网-单位查看投递简历
					map.put("tdsj", doForTime2(map.get("tdsj")));
				}
				if ("%".equalsIgnoreCase(map.get("qyl"))) { // 宁波职业-学生就业统计
					map.put("qyl", "");
				}
				if ("%".equalsIgnoreCase(map.get("jyl"))) { // 宁波职业-学生就业统计
					map.put("jyl", "");
				}
				if ("%".equalsIgnoreCase(map.get("dkl"))) { // 宁波职业-学生就业统计
					map.put("dkl", "");
				}
				if ("%".equalsIgnoreCase(map.get("bl"))) { // 宁波职业-学生就业统计
					map.put("bl", "");
				}
				if (map.get("rid") != null && map.get("btrowid") != null) {// 针对生涯论坛
					// 统计回贴数
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
				// 针对长沙民政(其他模块也可使用)
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

		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		String nowtime = (getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				// 发布时间25
				// 取至数据库临时表
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

				String pubtime = map.get("pubtime");// 发布时间
				
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
				
				String fbsj = map.get("fbsj");// 发布时间
				
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
					newstitle += "…";
					map.put("newstitle", newstitle);

				}
				if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
						&& map.get("wjbt").length() > 16) {
					String wjbt = map.get("wjbt").substring(0, 16);
					wjbt += "…";
					map.put("wjbt", wjbt);
				}
				if (map.get("bt") != null && !"".equals(map.get("bt"))
						&& map.get("bt").length() > 6) {
					String bt = map.get("bt").substring(0, 6);
					bt += "…";
					map.put("bt", bt);
				}
				if (map.get("gsmc") != null && !"".equals(map.get("gsmc"))
						&& map.get("gsmc").length() > 18) {
					String gsmc = map.get("gsmc").substring(0, 18);
					gsmc += "…";
					map.put("gsmc", gsmc);
				}
				if (map.get("zpzw") != null && !"".equals(map.get("zpzw"))
						&& map.get("zpzw").length() > 8) {
					String zpzw = map.get("zpzw").substring(0, 8);
					zpzw += "…";
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
		
		// 返回List结构的结果集
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

		// 返回List结构的结果集
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

		// 返回List结构的结果集
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

		// 返回List结构的结果集
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

	// 返回数组机构的结果集
	// 参数（sql语句，输入string数组类型的值，输出string数组类型的值）
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

	// 针对统计总量
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
	 * 只能用于返回一维的数组
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
	 * 把时间的字符串转化,时间的格式必须为'yyyymmddhhmiss'
	 * 
	 */
	public String doForTime(String time) {
		String aftertime = time.substring(0, 4) + "年" + time.substring(4, 6)
				+ "月" + time.substring(6, 8) + "日 " + time.substring(8, 10)
				+ ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
		return aftertime;
	}

	// 把时间的字符串转化为天结尾的
	public String doForTime2(String time) {
		String aftertime = time.substring(0, 4) + "年" + time.substring(4, 6)
				+ "月" + time.substring(6, 8) + "日 ";
		return aftertime;
	}

	// 把时间的字符串转化为xxxx-xx-xx
	public String doForTime3(String time) {
		String aftertime = time.substring(0, 4) + "-" + time.substring(4, 6)
				+ "-" + time.substring(6, 8);
		return aftertime;
	}
	


	// 把时间的字符串转化为xxxx-xx-xx
	public String doForTime4(String time) {
		String aftertime = time.substring(0, 4) + "-" + time.substring(4, 6)
				+ "-" + time.substring(6, 8) + " " + time.substring(8, 10)
				+ ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
		return aftertime;
	}
	
	// 把时间的字符串转化为xxxx-xx-xx
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

		// 执行修改表结构的语句
		boolean result = true;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			stat.execute(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		//写操作日志
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "modify :");
			dmlLogger.insertLog(sql, null, user);
		}
		return result;
	}
	
	public boolean runUpdateTab(String sql) throws Exception {

		// 执行修改表结构的语句
		boolean result = true;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			stat.execute(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(rs, null, stat, null);
			closeConnection(conn);
		}
		return result;
	}

	public boolean runUpdate(String sql, String[] input) throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	public boolean runUpdateNotCommit(String sql, String[] input) throws Exception {

		// 执行更新语句
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
	 * @描述:更新操作，返回影响记录数
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午03:22:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int update(String sql, String[] input) throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		
		return num;
	}
	
	
	
	/**
	 * 删除操作，返回影响记录条数   异常返回值修改 0->-1 20170613
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int runDelete(String sql, String[] input) throws Exception {

		// 执行更新语句
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			int num = stmt.executeUpdate();
			conn.commit();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return -1;
	}
	
	/**
	 * 删除操作，返回影响记录条数   异常返回值修改 0->-1 20170613
	 * @param sql
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public  int runDeleteNotCommit(String sql, String[] input) throws Exception {

		// 执行更新语句
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
	
	public boolean runUpdate(String sql, 
                             String[] input, 
                             String tableName, 
                             User user) throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//写操作日志
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql, input, user);
		}
		return result;
	}

	public boolean runUpdate2(String sql, String[] input) throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
			throw e;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	
	public boolean runUpdate2(String sql, 
							  String[] input, 
							  String tableName, 
							  User user) throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			result = false;
			throw e;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//写操作日志
		if(result){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql, input, user);
		}
		return result;
	}

	/**
	 * 执行修改操作，返回修改成功的行数
	 * @param sql
	 * @param input
	 * @param tableName
	 * @return int
	 */
	public int runUpdate(String sql, String[] input, String tableName)
			throws Exception {

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input);
			conn.rollback();
			iResultNum = 0;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return iResultNum;
	}
	
	

	/**
	 * @param sql
	 * @param input
	 *            格式必须是：asdf!@fadsaf!@..... (无效)
	 * @return
	 * @throws Exception
	 */
//	public boolean runUpdate(String sql, String input) throws Exception {
//
//		// 执行更新语句
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

		// 执行更新语句
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

		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input.toArray(new String[input.size()]));
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}
	
	public boolean runUpdate(String sql, 
							 ArrayList<String> input,
							 String tableName, 
							 User user) throws Exception {
		// 执行更新语句
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
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql,input.toArray(new String[input.size()]));
			conn.rollback();
			result = false;
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		//写操作日志
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
		// 添加新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
			throw e;
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// 就业网发布最新动态
	@SuppressWarnings("deprecation")
	public boolean addZxdt(String sNewsTitle, String sPart, String sContent,
			String sPuber) throws Exception {
		// 添加新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean addLy(String ip, String yhm, String ly, String fbsj)
			throws Exception {
		// 添加就业网留言板内容
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
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean addMessage(String wjbt, String wjlx, String wjnr,
			String fbr, String fbsj) throws Exception {
		// 添加就业网栏目更新
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
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			closeAllStmtAndRs(null, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean addNews2(String sNewsTitle, String sPart, String sContent,
			String sPuber, String towho,String newsType,String fwfs,String bmdm) throws Exception {
		// 添加新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean updateNews(String sNewsTitle, String sPart, String sContent,
			String sPuber, String newsId) throws Exception {
		// 更新新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// 长沙民族新闻修改
	@SuppressWarnings("deprecation")
	public boolean updateNews(String sNewsTitle, String sPart, String sContent,
			String newsId) throws Exception {
		// 更新新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	// 新闻修改分全部、教师、学生可见
	@SuppressWarnings("deprecation")
	public boolean updateNews2(String sNewsTitle, String sPart,
			String sContent, String sPuber, String newsId, String towho,String newsType,String fwfs)
			throws Exception {
		// 更新新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob("newscont");
		 	    	clob=(CLOB)rs.getClob("newscont");
		 	    }
				Writer outstream = clob.getCharacterOutputStream();
				outstream.write(sContent);
				outstream.close();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return res;
	}

	public boolean runProcuder(String sql, String[] input) throws Exception {
		// 执行存储过程
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

	/** 获取存储过程返回值 */
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
	 *            客户机操作的sql语句
	 * @param newVal
	 *            客户机操作的新数据
	 * @param oldVal
	 *            客户机操作的老数据
	 * @param yhcz
	 *            客户操作的具体操作描述
	 * @param request
	 * @throws Exception
	 */
	public void writeLog(String czsql, String[] newVals, String[] oldVals,
			String yhcz, HttpServletRequest request) throws Exception {
		// 写入操作日志
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
		//TODO 程序运行时需要
		LogUtil.maniLogs(colVal, dao, sql);
		//TODO 测试时需要
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
	 *            客户机操作的sql语句
	 * @param newVal
	 *            客户机操作的新数据
	 * @param oldVal
	 *            客户机操作的老数据
	 * @param yhcz
	 *            客户操作的具体操作描述 以下是把相应的客户端的信息直接传进来
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
		// 写入操作日志
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
		// 将两个数组合并到一个List，两个数组大小一致，通常为中英文对照。参数要求英文在前，中文在后。
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
		// 返回结果集(ArrayList:二维数组)
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
							.length() == 14)) ? tempstr.substring(0, 4) + "年"
							+ tempstr.substring(4, 6) + "月"
							+ tempstr.substring(6, 8) + "日 "
							+ tempstr.substring(8, 10) + ":"
							+ tempstr.substring(10, 12) + ":"
							+ tempstr.substring(12, 14) : tempstr);
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// 修改开始
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// 修改结束
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
		// 返回结果集(ArrayList:二维数组)
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
					// 修改开始
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// 修改结束
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

	// 返回的时间精确到天
	public ArrayList<String[]> rsToVator2(String sql, String[] inputValue,
			String[] outputValue) {
		// 返回结果集(Vector:二维数组)
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
							.length() == 14)) ? tempstr.substring(0, 4) + "年"
							+ tempstr.substring(4, 6) + "月"
							+ tempstr.substring(6, 8) + "日 " : tempstr);
					if (outputValue[i].contains("TIME")) {
						tmp[i] = tempstr.substring(0, 4) + "年"
								+ tempstr.substring(5, 7) + "月"
								+ tempstr.substring(8, 10) + "日 ";
					}
					if ((tmp[i] == null) || tmp[i].equalsIgnoreCase("")) {
						tmp[i] = "";
					}
					// 修改开始
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// 修改结束
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
	/**中国地质大学用人单位更换学生岗位申请中--返回不修改时间*/
	public ArrayList<String[]> rsToVator4(String sql, String[] inputValue,
			String[] outputValue) {
		// 返回结果集(ArrayList:二维数组)
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
					// 修改开始
					if ((outputValue[0].equalsIgnoreCase("ly")) && (tempstr != null)) {
						String rowid = rs.getString(0);
						CLOB clob = getOneClob(sql, new String[] { rowid },
								"ly");
						String lynr = clob
								.getSubString(1L, (int) clob.length());
						tmp[i] = lynr;
					}
					// 修改结束
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
		// 返回查询时使用的学年、年代列表
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
		//将系统当前年度改为保存的当前年度，而不是取数据库当前时间
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
	
	
	//去除学年空行
	public List<HashMap<String, String>> getXnndList2() {
		// 返回查询时使用的学年、年代列表
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		// int currentNd = (new Date()).getYear() + 1900;
		//int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
		//		4));
		//将系统当前年度改为保存的当前年度，而不是取数据库当前时间
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
		// 获取寝室编号列表
		String sql = "select distinct ssbh from zxbz_xsssxx order by ssbh";
		return getList(sql, new String[] {}, new String[] { "ssbh" });
	}

	public List<HashMap<String, String>> getYdlbList() {
		// 获取异动类别列表
		String sql = "select distinct ydlbm ydlbdm,ydlbmc from dm_ydlb order by ydlbdm";
		return getList(sql, new String[] {},
				new String[] { "ydlbdm", "ydlbmc" });
	}

	public List<HashMap<String, String>> getNjList() {
		// 获取年级列表
		String sql = "select distinct nj from view_njxyzybj order by nj";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}
	
	public List<HashMap<String, String>> getNjallList() {
		// 获取年级列表
		String sql = "select distinct nj from view_njxyzybj_all order by nj";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}

	public List<HashMap<String, String>> getSjList() {
		// 获取年级列表
		String sql = "select sj from xssjb order by sj";
		return getList(sql, new String[] {}, new String[] { "sj" });
	}

	public List<HashMap<String, String>> getXqList() {
		// 获取学期列表
		String sql = "select xqdm,xqmc from xqdzb";
		// ============ begin 骆嘉伟 2010/4/21 ===================
		sql += " order by to_number(xqjb)";
		// ============ end 骆嘉伟 2010/4/21 ===================
		return getList(sql, new String[] {}, new String[] { "xqdm", "xqmc" });
	}
	
	// ====================年级，学院，专业，班级START 显示所有【学籍版追加】=================================
	/**
	 * 
	 * @描述:所有年级列表(根据班级代码表)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-23 下午07:09:31
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNjNewList() {
		// 获取年级列表
		String sql = "select distinct nj from BKS_BJDM order by nj ";
		return getList(sql, new String[] {}, new String[] { "nj" });
	}
	
	/**
	 * 
	 * @描述: 获取学院列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-23 下午07:18:56
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyNewList() {
		// 获取学院列表
		String sql = "select distinct bmdm xydm,bmmc xymc,substr(nvl(f_pinyin(bmmc),bmmc),0,1) pyszm from ZXBZ_XXBMDM where bmlb = '5'order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc" , "pyszm"});
	}
	
	/**
	 * 
	 * @描述:获取专业列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-23 下午07:18:40
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyNewList(String xydm) {
		// 获取专业列表
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
	 * @描述:获取班级列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-23 下午07:19:32
	 * @param xydm
	 * @param zydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjNewList(String xydm, String zydm) {
		// 获取班级列表
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
	
	// ====================年级，学院，专业，班级END 显示所有【学籍版追加】=================================
	
	/**
	 * 
	 * @描述:根据学期代码获取学期名称
	 *       很多地方使用，每个dao都写次重复量太大
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午02:48:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public  String getXqmcForXqdm(String xqdm) {
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[] { xqdm }, "xqmc");
	}
	public List<HashMap<String, String>> getYfList() {
		// 获取月份列表
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String yf = "";
		for (int i = 1; i < 13; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			// ============ begin 骆嘉伟 2009/4/21 ===================
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
			// ============ begin 骆嘉伟 2009/4/21 ===================
			map.put("yf", yf);
			aList.add(map);
		}
		return aList;
	}

	public String getBmmcByDm(String dm, String defaultStr) {
		// 通过部门代码获取部门名称，若代码为空，返回defaultStr
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
		// 获取学院列表
		String sql = "select distinct xydm,xymc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc" , "pyszm"});
	}
	
	// 获取学院列表(取view_njxyzybj_all数据，没有在校生时也可以显示)
	public List<HashMap<String, String>> getXyallList() {
		String sql = "select distinct xydm,xymc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj_all order by pyszm";
		return getList(sql, new String[] {}, new String[] { "xydm", "xymc","pyszm" });
	}

	public String getXxdm() {
		// 获取学校代码
//		String sql = "select xxdm from xtszb";
//		String tmp[] = getOneRs(sql, new String[] {}, new String[] { "xxdm" });
//		String res = (tmp == null ? "" : tmp[0]);
//		return res;
		return Base.xxdm;
	}

	public List<HashMap<String, String>> getZyList(String xydm) {
		// 获取专业列表
		String sql = "";
		//2013-1-21 qph 把专业名称的拼音首字母也查询出来
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
		// 获取专业列表
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
		// 获取班级列表
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
		// 获取班级列表
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
		// 根据年级获取班级列表
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
		// 根据年级获取班级列表
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
		// 查询辅导员的班级代码
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
		//获取基本下拉框（type为类型）
		String[] typeListEn = null;
		String[] typeListCn = null;
		if(type == 1){
			typeListCn = new String[]{"在读证明（国内）普通版本","在读证明（出国留学）正常毕业版本","在读证明（出国留学）学位版本"};
			typeListEn = typeListCn;
		}
		if(type == 2){
			typeListCn = new String[]{"通知发布","评优条例","公示","结果公告"};
			typeListEn = new String[]{"001","002","003","004"};
		}
		return arrayToList(typeListEn, typeListCn);
	}

	public List<HashMap<String, String>> getScore(int tag) {
		// 获取基本下拉框（tag为等级级别）
		String[] tmp = new String[] {};
		if (tag == 5) {
			tmp = new String[] { "优秀", "良好", "中等", "及格", "不及格" };
		}else if (tag == 6){
			tmp = new String[] { "优秀", "良好", "合格", "不合格"  };
		}else if (tag == 7){
			tmp = new String[] { "A级", "B级", "C级"};		
		}else if (tag == 1){
			tmp = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12"}; 
		}else{
			tmp = new String[] { "合格", "不合格" };
		}
		return arrayToList(tmp, tmp);
	}

	public List<HashMap<String, String>> getChkList(int type) {
		// 审核结果下拉框
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "已审核", "未审核" };
		} else if (type == 2) {
			chkList = new String[] { "是", "否" };
		} else if (type == 3) {
			chkList = new String[] { "未审核", "通过", "不通过" };
		} else if (type == 4) {
			chkList = new String[] { "成功", "失败" };
		} else if (type == 5) {
			chkList = new String[] { "未审核", "特困", "一般困难", "不通过" };
		} else if (type == 6) {
			chkList = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
					"9", "10", "11", "12" };
		} else if (type == 7) {
			chkList = new String[] { "讲座", "座谈", "征文", "演讲", "参观", "其他" };
		} else if (type == 8) {
			chkList = new String[] { "面对面交流", "他人转告", "电话联系", "信函", "电子邮件",
					"手机短信" };
		} else if (type == 9) {
			chkList = new String[] { "特困", "一般困难" };
		} else if (type == 10) {
			chkList = new String[] { "未审核", "一般困难", "特别困难", "不困难" };
		} else if (type == 11) {
			chkList = new String[] { "未审核", "一般困难", "比较困难", "特别困难", "不困难" };
		} else if (type == 12) {
			chkList = new String[] { "未审核", "一般困难", "困难", "特殊困难", "不困难" };
		} else if (type == 13) {
			chkList = new String[] { "师范本科", "非师本科", "高职本科", "高职专科" };
		} else if (type == 14) {
			chkList = new String[] { "未审核", "一般困难", "特别困难", "特殊", "不通过" };
		} else if (type == 15) {
			chkList = new String[] { "未审核", "贫困生", "特困生", "不通过" };
		} else if (type == 16) {
			chkList = new String[] { "学生", "辅导员", "干事" };
		} else if (type == 17) {
			chkList = new String[] { "银行待审批", "已放贷", "已还款" };
		} else if (type == 18) {
			chkList = new String[] { "未审核", "不合格", "审核中", "银行待审批", "已放贷", "已还款" };
		} else if (type == 19) {
			chkList = new String[] { "未审核", "困难", "特殊困难", "不困难" };
		} else if (type == 20) {
			chkList = new String[] { "未审核", "特困", "贫困", "一般", "不困难" };
		} else if (type == 21) {
			chkList = new String[] { "未审核", "A档", "B档", "C档", "不困难" };
		} else if (type == 22) {
			chkList = new String[] { "属实", "不属实" };
		} else if (type == 23) {
			chkList = new String[] { "A档", "B档", "C档" };
		} else if (type == 24) {
			chkList = new String[] { "可以毕业", "不可毕业" };
		} else if (type == 25) {
			chkList = new String[] { "未审核", "一般困难", "特殊困难", "其他困难", "不困难" };
		} else if (type == 26) {
			chkList = new String[] { "未审核", "困难", "特别困难", "不困难" };
		} else if (type == 27) {
			chkList = new String[] { "未审核", "通过", "不通过", "退回" };
		} else if (type == 28) {
			chkList = new String[] { "未注册","注册" };
		} else if (type == 29) {
			chkList = new String[] { "学生","老师" };
		} else if (type == 30) {
			chkList = new String[] { "学校",Base.YXPZXY_KEY,"专业","班级" };
		} else if (type == 31) {
			chkList = new String[] { "全部参与","部分参与","可不参与"};
		} else if (type==32){
			chkList = new String[] { "通过","退回","不通过" };
		} else if (type==33){
			chkList = new String[] { "未审核","审核中","通过","不通过","退回" };
		}
		
		return arrayToList(chkList, chkList);
	}
	public List<HashMap<String, String>> getzJxjTj(int type) {
		// 设置条件下拉框
		String[] enList = null;
		String[] cnList = null;
		if (type == 1) {
			cnList = new String[] { "综合素质测参评排名比例", "德成绩","智成绩","体成绩","技能加分" };
			enList = new String[] {"cpzfpm", "dcj","zcj","tcj","jnjf"};
		}
		return arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> getJxjDj() {
		String sql = "select dm,mc from ynys_zzdjb order by dm";
		return getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	public String getUserType(String userDep) {
		// 1 为学校，2 为学院
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
		// 获取校区列表
		String sql = "select distinct dm,xqmc from dm_zju_xq order by dm";
		return getList(sql, new String[] {}, new String[] { "dm", "xqmc" });
	}

	public List<HashMap<String, String>> getSexList() {
		// 获取性别列表
		String[] xbdm = new String[] { "1", "2" };
		String[] xbmc = new String[] { "男", "女" };
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
		    		clob = (CLOB)method.invoke(rs.getClob(colName));
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
			closeAllStmtAndRs(rs, null, stmt, null);
			closeConnection(conn);
        }
        return encoder;
    }
	
	
	/**
	 * 
	 * @描述: 查询大字段，以流的形式返回
	 * @作者：Penghui.Qu
	 * @日期：2013-5-20 上午10:16:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sql
	 * @param input
	 * @return
	 * @throws SQLException
	 * InputStream 返回类型 
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
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				if (rs.getClob("newscont").getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob("newscont").getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (CLOB)method.invoke(rs.getClob("newscont"));
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
		// 获取当前设置:0-currXn;1-currXq;2-jxjsqxn;3-jxjsqnd;4-currNd;5-sfkpj;6-jxjsqxq
		String sql = "select dqxn,dqxq,jxjsqxn,jxjsqnd,dqnd,nvl(sfkpj,'否') sfkpj,jxjsqxq from xtszb where rownum=1";
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
							(rs.getString(outputValue[i]) == null ? "无" : rs
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
		// 获取年限列表
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
	 * 以本年度为基准向前6年，向后1年
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

	// 自己添加的方法
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
	 * 数据插入操作，插入数据的同时插入操作日志
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
			//写操作日志
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "insert :");
			dmlLogger.insertLog(sql, input, user);
		}
		return flag;
	}

	// 获取时间段下拉列表
	public List<HashMap<String, String>> getSJDList() {

		String[] SJDBH = new String[] { "1", "2", "3", "4" };
		String[] SJDMC = new String[] { "08:00", "09:50", "13:30", "15:30" };
		return arrayToList(SJDBH, SJDMC);
	}

	public String[] getColumnNameCN2(String[] cName, String tabName) {
		// 将数据表英文列名转化为中文列名，返回中文列名[不含括号]数组（参数为英文列名数组和表名）
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
				if (tit[i].indexOf('（') >= 0) {
					tit[i] = tit[i].substring(0, tit[i].indexOf('（'));
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
				temmap.put("nj", njArr[i]);// 将键njArr[i]改为nj才可在页面取出
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

	// ////////////判断查询是否为空,返回tag//////////////////////////////////////////
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

	// 获取学生宿舍信息列表
	public List getStuDormList(String query) {
		String sql = "select * from view_xsgygl_xsssfbqkb" + query;
		List dormList = getList(sql, new String[] {}, new String[] { "xh",
				"xm", "xymc", "zymc", "bjmc", "ssbh", "fdyxm", "sz", "cz",
				"lz", "cwh" });
		return dormList;
	}

	// 获取学生资助等级列表
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

	// 获取学生类别列表
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
		// 通用数据库CLOB 把文档写入数据库clob字段
		boolean res = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			// ================ begin 骆嘉伟 2009/3/23 =====================
			// 由于本人操作模块有可能传入一空的SQL,为防止出错,加入该项目非空判断
			if (sql != null && !"".equals(sql)) {
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				stmt.clearBatch();
			}
			// ================ end 骆嘉伟 2009/3/23 =====================
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
		// 通用数据库CLOB 修改数据库clob字段
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

	// 根据历届学生班级代码得到历届学生班级名称
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

	// 根据历届学生专业代码得到历届学生专业名称
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

	// 根据历届学生学院代码得到历届学生学院名称
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
		// 得到视图的字段注释语句
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||chr(39) com "
				+ "from user_col_comments where table_name=upper('"
				+ viewName
				+ "')";
		arr = this.getArray(sql, new String[] {}, "com");
		return arr;
	}

	public boolean creatView(String sql, String[] com) throws SQLException {
		// 得到视图的字段注释语句
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
			if (inputValue != null) { // 判断没有参数的情况
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
		if (dataVector != null) { // vector.addAll方法不能增加null值
			vector.addAll(dataVector);
		}
		return vector;
	}

	public List<HashMap<String, String>> getListNotOut(String sql,
			String[] inputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			if (inputValue != null) { // 判断没有参数的情况
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
	 * @描述: 解决getString获取小数问题
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-24 下午01:46:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * String 返回类型
	 */
	private String getValue(ResultSet rs,String columnName) throws SQLException{
		Object V=rs.getObject(columnName);
		if(null!=V){
			//处理特殊类型
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

	// 获取政治面貌列表
	public List<HashMap<String, String>> getZzmmList() {
		String sql = "select distinct zzmmdm,zzmmmc from zzmmdmb order by zzmmdm";
		return getList(sql, new String[] {},
				new String[] { "zzmmdm", "zzmmmc" });
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 获得 学生状态
	 */
	public String getXszt(String xh) {
		String tempSql = "select ydlb from XSYDLB where xh=?";
		String xszt = "";
		String[] xsztl = this.getOneRs(tempSql, new String[] { xh },
				new String[] { "ydlb" });
		if ((xsztl == null) || ("复学".equalsIgnoreCase(xsztl[0]))) {
			tempSql = "select ydlb from bks_stu_info_xjydb where xh=?";
			xsztl = this.getOneRs(tempSql, new String[] { xh },
					new String[] { "ydlb" });
			if (xsztl == null) {
				xszt = "在读";
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
	 * @describe 判断是否困难生 是困难生返回true
	 */
	public boolean isKns(String xh) {
		boolean tm = false;
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xxdm = StandardOperation.getXxdm();
		String num = "";
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// 北京林业大学
			String tempSql = "select count(*) num from view_bjlydx_knssqb where xh=? and nd=? and xxsh='通过'";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// 西南民族大学
			String tempSql = "select count(*) num from xsjtqkdcb where xh=? and xxsh='通过'";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SCJZZYJSXY)) {// 四川建筑职业技术学院
			String tempSql = "select count(*) num from xsjtqkdcb where xh=? and xxsh='通过'";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// 江苏信息职业技术学院
			String tempSql = "select count(*) num from jsxx_knsxx where xh=? and nd=? and xxsh in ('一般困难', '比较困难','特别困难')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院
			String tempSql = "select count(*) num from hzzy_knssqb where xh=? and nd=? and xxsh in ('困难','特殊困难')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// 金华职业技术学院
			String tempSql = "select count(*) num from xszz_jhzy_kns where xh=? and nd=? and xxsh in ('困难','特殊困难')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// 漳州师范学院
			String tempSql = "select count(*) num from zzsf_knsxx where xh=? and nd=? and xxsh in ('一般困难','困难','特殊困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh, nd }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// 广东女子职业技术学院
			String tempSql = "select count(*) num from gdnzzyjsxy_knsxx where xh=? and xxsh in ('一般困难','特别困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {// 上海工程技术大学
			String tempSql = "select count(*) num from shgc_knsxx where xh=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365 and xxsh in ('一般困难','特别困难','特殊')";
			num = this.getOneRs(tempSql, new String[] { xh }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {// 西北二民院
			String tempSql = "select count(*) num from shgc_knsxx where xh=? and nd=? and xxsh in ('一般困难','特别困难','特殊')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHNLZYJSXY)) {// 上海农林职业技术学院
			String tempSql = "select count(*) num from shnl_knsxx where xh=? and nd=? and shjg in ('困难','特别困难')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// 长沙民政职业技术学院
			String tempSql = "select count(*) num from csmz_knsxx where xh=? and nd=? and xxsh in ('贫困生','特困生')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// 浙江机电职业技术学院
			String tempSql = "select count(*) num from zjjd_knsxx where xh=? and nd=? and xxsh in ('一般困难','困难','特殊困难')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// 中国矿业大学
			String tempSql = "select count(*) num from ZGKYDX_KNSXX where xh=? and nd=? and XXSHJG in ('A档', 'B档', 'C档')";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学
			String tempSql = "select count(*) num from ZGDZDX_KNS_PKSRD where xh=? and xn=? and XXSH in ('一般困难','困难','特殊困难')";
			num = this.getOneRs(tempSql, new String[] { xh, xn }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// 北京联合大学
			String tempSql = "select count(*) num from view_bjlhdx_kns where xh=? and XXSH in ('一般困难', '特殊困难', '其他困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {// 中国美术学院
			String tempSql = "select count(*) num from zgmsxy_knsxx where xh=? and xn=? and XXSH in ('困难', '特殊困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {// 宁波理工
			String tempSql = "select count(*) num from NBLG_KNS where xh=? and xn=? and XXSH in ('困难', '特别困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {//盐城卫生职业技术学院
			String tempSql = "select count(*) num from ycws_knrdxx where xh=? and knrd in ('一般困难', '困难', '特殊困难')";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
			//  困难生判断
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)) {// 广州大学
			String tempSql = "select count(*) num from xszz_com_knsrdb3 where xh=? and xn=? and XXSH in ('困难', '特殊困难','一般困难')";
			num = this.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WZDX) || xxdm.equalsIgnoreCase("10426")) {// 温州大学
			String tempSql = "select count(*) num from XG_XSZZ_NEW_KNSJGB where xn = (select dqxn from xtszb where rownum = 1) and xq = (select nvl(rdxq, 'on') from xg_xszz_new_knsjbszb where rownum = 1) and xh=?";
			num = this.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else {
			String tempSql = "select count(*) num from view_knsxx where xh=? and xn=? and xxsh='通过'";
			num = this.getOneRs(tempSql, new String[] { xh, xn }, new String(
					"num"));
			if ("0".equalsIgnoreCase(num)) {
				tempSql = "select count(*) num from view_xszz_new_knsxx where xh=? and nd=? and xxsh='通过'";
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
		//使用N32的学生资助
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
	 * @describe 获取困难生类型 xxsh='通过'的学生为‘困难’  ,xxsh='不通过' 或者 xxsh='未审核' 或者 xxsh is null的学生为'不困难' 否则取xxsh字段信息
	 */
	public String getKnslx(String xh) {
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xxdm = StandardOperation.getXxdm();
		String num = "";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// 北京林业大学
			String tempSql = "select xxsh num from view_bjlydx_knssqb where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// 西南民族大学
			String tempSql = "select xxsh  num from xsjtqkdcb where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SCJZZYJSXY)) {// 四川建筑职业技术学院
			String tempSql = "select xxsh num from xsjtqkdcb where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// 江苏信息职业技术学院
			String tempSql = "select xxsh num from jsxx_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院
			String tempSql = "select xxsh num from hzzy_knssqb where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// 金华职业技术学院
			String tempSql = "select xxsh num from xszz_jhzy_kns where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// 漳州师范学院
			String tempSql = "select xxsh num from zzsf_knsxx where xh=? and nd=?";
			num = this
					.getOneRs(tempSql, new String[] { xh, nd }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// 广东女子职业技术学院
			String tempSql = "select xxsh num from gdnzzyjsxy_knsxx where xh=? ";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {// 上海工程技术大学
			String tempSql = "select xxsh num from shgc_knsxx where xh=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365";
			num = this.getOneRs(tempSql, new String[] { xh }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {// 西北二民院
			String tempSql = "select xxsh num from shgc_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHNLZYJSXY)) {// 上海农林职业技术学院
			String tempSql = "select shjg num from shnl_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// 长沙民政职业技术学院
			String tempSql = "select xxsh num from csmz_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// 浙江机电职业技术学院
			String tempSql = "select xxsh num from zjjd_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// 中国矿业大学
			String tempSql = "select xxshjg num from zgkydx_knsxx where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学
			String tempSql = "select xxsh num from zgdzdx_kns_pksrd where xh=? and nd=?";
			num = this.getOneRs(tempSql, new String[] { xh, nd }, new String(
					"num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// 北京联合大学
			String tempSql = "select xxsh num from view_bjlhdx_kns where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {// 中国美术学院
			String tempSql = "select xxsh num from zgmsxy_knsxx where xh=? and xn=?";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {// 宁波理工
			String tempSql = "select xxsh num from NBLG_KNS where xh=? and xn=?";
			num = this
					.getOneRs(tempSql, new String[] { xh,xn }, new String("num"));
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {//盐城卫生职业技术学院
			String tempSql = "select knrd num from ycws_knrdxx where xh=?";
			num = this
					.getOneRs(tempSql, new String[] { xh }, new String("num"));
			// TODO 困难生判断
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
		if(StringUtils.isNull(num) || "未审核".equalsIgnoreCase(num) || "不通过".equalsIgnoreCase(num)){
			num = "不困难";
		}else if("通过".equalsIgnoreCase(num)){
			num = "困难";
		}
		return num;
	}

	/**
	 * @author ZhouMi
	 * @describe 将一个正整数的金额从个位开始每三位用一个逗号搁开。
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
	 * @describe 获得江苏信息职业技术学院国家助学金等级列表。
	 */
	public List<HashMap<String, String>> getJsxxGjzxjDj() {
		// 获取学院列表
		String sql = "select distinct djdm,djmc||'||'||djje djmc from jsxx_gjzxjdjdmb";
		return getList(sql, new String[] {}, new String[] { "djdm", "djmc" });
	}

	/**
	 * @author ZhouMi
	 * @describe 获得江苏信息职业技术学院学生历年已获奖助学金和补助情况。
	 */
	public ArrayList<String> getJsxxStuJzxj(String xh) {
		ArrayList<String> aList = new ArrayList<String>();
		String sql = "select nd,jzxjzwmc from view_xsjxjzxjsq where xh=? and xxsh='通过' order by nd";
		List<Vector<String>> temp = this.getVectorList(sql,
				new String[] { xh }, new String[] { "nd", "jzxjzwmc" });
		String xx = "";
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "年度  获得：" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd,bzmc from view_xswszxjsqxx where xh=? and xxsh='通过' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "bzmc" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "年度  获得：" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd,bzmc from view_xsbzxx where xh=? and xxsh='通过' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "bzmc" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "年度  获得：" + vec.get(1);
				aList.add(xx);
			}
		}
		sql = "select nd from VIEW_JSXX_GJZXDK where xh=? and xxsh='通过' order by nd";
		temp = this.getVectorList(sql, new String[] { xh },
				new String[] { "nd" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "年度  获得：国家助学贷款";
				aList.add(xx);
			}
		}
		sql = "select sbsj from VIEW_JSXX_SBKNBZSQB where xh=? and xxsh='通过' order by sbsj";
		temp = this.getVectorList(sql, new String[] { xh },
				new String[] { "sbsj" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				String year = vec.get(0).substring(0, 4);
				String mon = vec.get(0).substring(5, 7);
				String day = vec.get(0).substring(8);
				xx = year + "年" + mon + "月" + day + "日  获得：伤、病补助";
				aList.add(xx);
			}
		}
		sql = "select nd,xxtyshje from view_jsxx_gjzxj where xh=? and xxsh='通过' order by nd";
		temp = this.getVectorList(sql, new String[] { xh }, new String[] {
				"nd", "xxtyshje" });
		if (temp.size() != 0) {
			for (Iterator<Vector<String>> it = temp.iterator(); it.hasNext();) {
				Vector<String> vec = it.next();
				xx = vec.get(0) + "年度  获得：国家助学金,金额为: " + vec.get(1) + "元";
				aList.add(xx);
			}
		}
		return aList;
	}

	public List<HashMap<String, String>> getKcList(String realTable,
			String bjdm, String xn, String xqdm) {
		// 获取课程列表
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
	 * 把所有的update和insert语句正批发到数据库执行（从而减少与数据库的交互次数）， 必须是完整的sql句子，各个值都要组合到句子中去
	 * 例如: new String[]{"insert into table(a,b) values ('a','b')"}
	 * @param sqlArr
	 *            按照顺序存放sql语句
	 * @return 返回的int[]是与sqlArr相对应的顺序的结果，表示每个句子的执行是否成功
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
	 * 把所有的update和insert语句正批发到数据库执行（从而减少与数据库的交互次数）， 必须是完整的sql句子，各个值都要组合到句子中去
	 * 例如: new String[]{"insert into table(a,b) values ('a','b')"}
	 * @param sqlArr
	 *            按照顺序存放sql语句
	 * @return 返回的int[]是与sqlArr相对应的顺序的结果，表示每个句子的执行是否成功
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
			//写操作日志
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
	 *            主键
	 * @param v_sql_in
	 *            输入的合成sql
	 * @param v_page_size
	 *            页面记录数
	 * @param currentPage
	 *            当前页面数
	 * @param v_table_name
	 *            表（或视图）名
	 * @param v_order_field
	 *            用于排序的列
	 * @param v_order_sequence
	 *            排序方式 ASC OR DESC
	 * @param pages
	 *            pages对象
	 * @return
	 */
	public ArrayList<String[]> makePagination(String v_key, String v_sql_in,
			int v_page_size, int currentPage, String v_table_name,
			String v_order_field, String v_order_sequence, String[] columns,
			Pages pages) {
		ArrayList<String[]> result = new ArrayList<String[]>();
//		String sql = "";
		int v_total_count = 0;
		int v_page_size_temp = 10;// 默认每页显示10行
		int currentPage_temp = 0;// 当前显示的页面
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
				//  把单个记录放到list中
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
		// 获得部门代码，名称列表
		String sql = "select bmdm,bmmc from zxbz_xxbmdm　where bmjb = '1' ";
		return getList(sql, new String[] {}, new String[] { "bmdm", "bmmc" });
	}
	
	public List<HashMap<String, String>> getBmListAll() {
		// 获得部门代码，名称列表
		String sql = "select bmdm,bmmc from zxbz_xxbmdm order by bmdm ";
		return getList(sql, new String[] {}, new String[] { "bmdm", "bmmc" });
	}
	
	public List<HashMap<String, String>> getYjbmList() {
		// 获得部门代码，名称列表
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
		// 返回hashMap结构的结果集,以第一个字段的值为索引
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
	 * 检测tableName表中是否存在keys指定的数据集, 避免循环遍历检测存在性
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
	 * 代码维护中转换List为String
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
	 * 长沙民政新闻增加
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
		// 添加新闻
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
				CLOB clob =null;// (CLOB) rs.getClob("tpmold");
				
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
	
	// 将两个数组和为一个数组
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

	// 将一个数组copy到另一数组中
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

	// 返回布尔型
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
			runProcuder("{call update_cwxxb()}", new String[] {});// 验证床位信息是否正确
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 将两个数组和为一个数组,前提是保证二个数组长度必须一致
	 * 合并顺序:array1[0],array2[0],array1[1],array2[1],array1[2],array2[2]...
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

		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		String nowtime = (getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				// 发布时间25
				// 取至数据库临时表
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

				String pubtime = map.get("pubtime");// 发布时间
				
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
				
				String fbsj = map.get("fbsj");// 发布时间
				
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
//				String pubtime = map.get("pubtime");// 发布时间
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
//				String fbsj = map.get("fbsj");// 发布时间
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
					newstitle += "…";
					map.put("newstitle", newstitle);

				}
				if("xyxjh".equals(parm)){
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 18) {
						String wjbt = map.get("wjbt").substring(0, 18);
						wjbt += "…";
						map.put("wjbt", wjbt);
					}
				}else if("jyzc".equals(parm)){
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 9) {
						map.put("wjbttitle", map.get("wjbt"));
						String wjbt = map.get("wjbt").substring(0, 9);
						wjbt += "…";
						map.put("wjbt", wjbt);
					}
				}else if("bystj".equals(parm)){
					if (map.get("qzyx") != null && !"".equals(map.get("qzyx"))
							&& map.get("qzyx").length() > 20) {
						String qzyx = map.get("qzyx").substring(0, 20);
						qzyx += "…";
						map.put("qzyx", qzyx);
					}
				}else if("qzyx".equals(parm)){
					if (map.get("qzyx") != null && !"".equals(map.get("qzyx"))
							&& map.get("qzyx").length() > 13) {
						String qzyx = map.get("qzyx").substring(0, 13);
						qzyx += "…";
						map.put("qzyx", qzyx);
					}
				}else{
					if (map.get("wjbt") != null && !"".equals(map.get("wjbt"))
							&& map.get("wjbt").length() > 12) {
						map.put("wjbttitle", map.get("wjbt"));
						String wjbt = map.get("wjbt").substring(0, 12);
						wjbt += "…";
						map.put("wjbt", wjbt);
					}
				}
				
				if (map.get("bt") != null && !"".equals(map.get("bt"))
						&& map.get("bt").length() > 6) {
					String bt = map.get("bt").substring(0, 6);
					bt += "…";
					map.put("bt", bt);
				}
				if("xnzp".equals(parm)){
					if (map.get("gsmc") != null && !"".equals(map.get("gsmc"))
							&& map.get("gsmc").length() > 15) {
						String gsmc = map.get("gsmc").substring(0, 15);
						gsmc += "…";
						map.put("gsmc", gsmc);
					}
				}else if("dwxxk".equals(parm)){
					if (map.get("dwmc") != null && !"".equals(map.get("dwmc"))
							&& map.get("dwmc").length() > 20) {
						String dwmc = map.get("dwmc").substring(0, 20);
						dwmc += "…";
						map.put("alldwmc", dwmc);
					}else{
						if(map.get("dwmc") != null){
							map.put("alldwmc", map.get("dwmc"));
						}
					}
					String zjrq = map.get("zjrq");// 发布时间
					
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
						gsmc += "…";
						map.put("gsmc", gsmc);
					}
				}
				
				if (map.get("zpzw") != null && !"".equals(map.get("zpzw"))
						&& map.get("zpzw").length() > 8) {
					String zpzw = map.get("zpzw").substring(0, 8);
					zpzw += "…";
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
	
	//返回时第一字段的值为主键，第2个字段的值为值
	public HashMap<String, String> getHashMapList2(String sql,
			String[] inputValue, String[] outputValue) {
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// 返回hashMap结构的结果集,以第一个字段的值为索引
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
	 * 获取学生类型、类别类别（中国地质大学）
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
		// 添加新闻
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
	 * 返回班主任所属班级学生列表
	 * @param zgh 职工号
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
	 * 批量保存数据入DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveData(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
		String[] arrzd = form.getArrzd();// 批量字段
		String[] onezd = form.getOnezd();// 单一字段
		int size = (arrzd == null) ? 0 : arrzd.length + ((onezd == null) ? 0 : onezd.length);
		String[] notnull = form.getNotnull();//非空字段
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
	 * 批量保存数据入DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveData(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
		String[] arrzd = form.getArrzd();// 批量字段
		String[] onezd = form.getOnezd();// 单一字段
		int size = (arrzd == null) ? 0 : arrzd.length + ((onezd == null) ? 0 : onezd.length);
		String[] notnull = form.getNotnull();//非空字段
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
			//写操作日志
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "runBatch :");
			dmlLogger.insertLog(del.toString(), null, user);
			dmlLogger.insertLog(Arrays.deepToString(insert), null, user);
		}
		return flg;
	}
	
	/**
	 * 批量修改DB中的数据
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean updateData(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
		String[] onezd = form.getOnezd();// 单一字段
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
	 * 批量修改DB中的数据
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean updateData(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
		String[] onezd = form.getOnezd();// 单一字段
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
			//写操作日志
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql.toString()+ query.toString(), null, user);
		}
		return flg;
	}
	
	/**
	 * 提交数据，保存入DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean submitData(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
		String[] onezd = form.getOnezd();// 单一字段
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
	 * 批量删除DB中的数据
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean delDate(SaveForm form, Object model) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
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
	 * 批量删除DB中的数据
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean delDate(SaveForm form, Object model, User user) throws Exception {

		boolean flg = false;

		String tableName = form.getTableName();// 表名
		String pk = form.getPk();// 主键
		String[] pkValue = form.getPkValue();// 主键值
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
			//写操作日志
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "delete :");
			dmlLogger.insertLog(del.toString(), null, user);
		}
		return flg;
	}
	
	/**
	 * 返回上一个学期，及其对应的学年，年度（适合两个学期的）
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
	 * 批量提交
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
	 * 批量提交
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
			//写操作日志
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData("", "runBatch :");
			dmlLogger.insertLog(Arrays.deepToString(sql), null, user);
		}
		return flag;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue) {

		if (Base.isNull(msg)) {
			msg = "----请选择-----";
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
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @pararm isnull（是否需要显示请选择）
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue, boolean isnull) {

		if (isnull) {
			msg = "----请选择-----";
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
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param bsf(标识符）
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk,String bsf, String pkValue) {

		if (Base.isNull(msg)) {
			msg = "----请选择-----";
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
	 * 删除所上传文件
	 * 
	 * @param tableName(表名)
	 * @param dzzd(文件地址字段)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luo
	 * @throws Exception 
	 */
	public boolean fileDel(String tableName,String dzzd,String pk,String pkValue) throws Exception{
//		DAO dao = new DAO();
		
		boolean flag = true;
		//获得文件保存路径
		String sql = "select " + dzzd + " scdz from " + tableName + " where " + pk + " = ?";
		String wjlj = getOneRs(sql, new String[]{pkValue}, "scdz");
		if(wjlj!=null){
			File file = new File(wjlj);
	    	if (file.exists()) {
	    		//文件存在，删除该文件
				flag = file.delete();
				if (flag) {
					//删除成功，清空该地址
					sql = "update " + tableName + " set " + dzzd + "='' where "
							+ pk + " = ?";
					flag = runUpdate(sql, new String[] { pkValue });
				}
			}
		}
		return flag;
	}
	
	/**
	 * 获得周次列表
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
					map.put("mc", "第0" + String.valueOf(i) + "周");
				} else {
					map.put("dm", String.valueOf(i));
					map.put("mc", "第" + String.valueOf(i) + "周");
				}
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		String sql = "";
		if ("YYYY年MM月DD日".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '年' || to_char(sysdate, 'MM') "
					+ "|| '月' || to_char(sysdate, 'DD') || '日' now from dual";
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
	 * 获得指定学期的上一学期
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBeforeXq(HashMap<String, String> map) {
		HashMap<String, String> befMap = new HashMap<String, String>();

		// 指定学年
		String xn = map.get("xn");
		// 指定学期
		String xq = map.get("xq");
		// 拆分学年
		String[] arrXn = xn.split("-");
		// 前一学年
		String before = (arrXn != null && arrXn.length > 1) ? String
				.valueOf(Integer.parseInt(arrXn[0]) - 1) : "";
		String after = (arrXn != null && arrXn.length > 1) ? String
				.valueOf(Integer.parseInt(arrXn[1]) - 1) : "";
		String befXn = (!Base.isNull(before) && !Base.isNull(after)) ? before
				+ "-" + after : "";
		// 前一学期
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
	 * 获得指定表的指定字段
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
	 * 鲁宁
	 * 2010-5-27
	 * 从数据流中读取数据，并保存blob形式的数据到数据库
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
	 * 获取多个下拉框选项
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
		// 返回hashMap结构的结果集,以第一个字段的值为索引
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
	 * 获取表内的字段及其注释,长度，是否为空，并返回相关值
	 * 其中NULLABLE为是否为空，Y为可以为空，N为不可以为空
	 * @param cName
	 * @param tabName
	 * @return
	 */
	public List<HashMap<String, String>> getColumnAttributeList(String tabName,String[] outPutString) {
		// 将数据表英文列名转化为中文列名，返回中文列名[不含括号]数组（参数为英文列名数组和表名）
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
		//		 查询出List结构的结果集
		List<HashMap<String, String>> rs = getList(sqlBuilder.toString(),new String[]{tabName,xxdm,tabName,tabName},new String[]{"en","cn","length","nullable","zdlx"});
		//		 返回List结构的结果集
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
	 * 插入一条记录指定的值
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
	
	
	/** 获取Blob类型数据 */
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
	 * 获取指定表中所有的列
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
	 * 采用预编译语句，数据库批量操作，可大大提高执行速度
	 * @author sjf
	 * @param sql 预编译sql语句
	 * @param params 变量值
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
	 * 合并数组
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
	 * 从数据流中读取数据，并保存blob形式的数据到数据库
	 * @param sql
	 * @param inputList 参数中不包括BLOB参数值 第一位是流对象，第二位开始是值
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
	 * @param 无需带入输出字段
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
	 * 采用预编译语句，数据库批量操作，可大大提高执行速度
	 * @author 1206[喻鑫源] 改自runbatch 原作者邵剑锋
	 * @param sql 预编译sql语句
	 * @param params 变量值
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
	 * @描述: 批处理，返回boolean
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 上午10:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * int[] 返回类型 
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