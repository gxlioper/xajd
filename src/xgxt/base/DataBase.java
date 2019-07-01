
/*
 * 创建日期 2006-5-13
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 此文件为数据库的基本操作处理类
 */
public class DataBase extends Object {
	Connection conn = null;

	Statement stm = null;

	PreparedStatement pstm = null;

	ResultSet rs = null;

	boolean connected = false;

	/** 构造函数 */
	public DataBase() {
	}

	/** 创建我的连接池 */
	public boolean getMyConnPool() {
		conn = Configuration.connMgr
		.getConnection(Configuration.ConnectionPoolName);
		if (conn == null) {
			return false;
		}
		return true;
	}

	/** 释放我的连接池 */
	public boolean releaseMyConnPool() {
		boolean b;
		if (conn != null) {
			b = true;
		} else {
			b = false;
		}
		Configuration.connMgr.freeConnection(Configuration.ConnectionPoolName,
				conn);
		conn = null;
		return b;
	}

	/** 释放数据库连接 */
	public void releaseConn() {
		try {
			if (!connected) {
				// throw new SQLException("数据库未连接！");
			}
			if (conn != null) {
				conn.close();
				connected = false;
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库连接时出错;\r\n错误为:" + e);
		}
	}

	/** 查询记录 */
	public ResultSet QuerySQL(String sql) {
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		} catch (SQLException sqle) {
			System.out.println("执行DataBase::QuerySQL(String)调用SQL语句 " + sql
					+ " 时出错;\r\n错误为:" + sqle);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					System.out
					.println("执行DataBase::QuerySQL(String)试图关闭错误的声明时出错;\r\n错误为："
							+ e);
				}
			}
		}
		return rs;
	}

	/** 查询一条记录 */
	public String QueryOneSQL(String sql, String[] insStr) {
		ResultSet rs = null;
		String rsstr = null;
		try {
			pstm = conn.prepareStatement(sql);

			for (int i = 0; i < insStr.length; i++) {
				pstm.setString(i + 1, insStr[i]);
			}
			rs = pstm.executeQuery();
			if (rs.next()) {
				rsstr = rs.getString(1);
			}
		} catch (SQLException sqle) {
			System.out.println("执行DataBase::QuerySQL(String)调用SQL语句 " + sql
					+ " 时出错;\r\n错误为:" + sqle);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					System.out
					.println("执行DataBase::QuerySQL(String)试图关闭错误的声明时出错;\r\n错误为："
							+ e);
				}
			}
		}
		return rsstr;
	}

	/** 执行增删改的语句 */
	public int ExecuteSQL(String sql) {
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			conn.commit();
		} catch (SQLException sqle) {
			// System.out.println("执行DataBase::ExecuteSQL(String)调用SQL语句
			// "+sql+"
			// 时出错;\r\n错误为:"+sqle);
			return sqle.getErrorCode();
		} finally {
			try {
				pstm.close();
			} catch (SQLException sqle) {
				System.out.println("执行DataBase::ExecuteSQL(String)调用SQL语句 "
						+ sql + " 时出错;\r\n错误为:" + sqle);
			}
		}
		return 0;
	}

	/** 产生唯一编号 */
	public int makeID(String table, String field1, String field2,
			String value1, boolean type1) {
		int out = -1;
		String sql = "";
		try {
			// 只有唯一主键field1
			sql = "select " + field1 + " as ID from " + table + " order by "
			+ field1;
			// 有两个主键field1、field2
			if (!value1.equals("")) {// 当第一个字段不空时，作为条件查询第二个字段
				sql = "select " + field2 + " as ID from " + table + " where "
				+ field1 + "=" + value1 + " order by " + field2;
				if (!type1) {
					sql = "select " + field2 + " as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int t1 = 1;
			int t2 = 2;
			if (rs.next()) {
				// 有记录
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while (rs.next()) {
					// 不止一条纪录
					bool = true;
					t2 = rs.getInt("ID");
					if ((t2 - t1) > 1) {
						break; // 如果t2与t1相差大于1,则跳出去,新编号为t1++（见后面**）
					}
					t1 = t2; // 否则将t2赋给t1
				}
				if (!bool) {
					// 如果只有一条纪录
					if (t1 > 1) {
						t1 = 1; // 如果已有纪录的ID号大于1，则新编号设为1
					} else {
						t1++;
					}
				} else {
					t1++; // **
				}
			}
			if (out > 1) {
				out = 1;
			} else {
				out = t1;
			}

		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	/** 产生唯一编号 */
	public int makeID_Add1(String table, String field1, String field2,
			String value1, boolean type1) {
		int out = -1;
		String sql = "";
		try {
			// 只有唯一主键field1
			sql = "select max(" + field1 + ")+1 as ID from " + table
			+ " order by " + field1;
			// 有两个主键field1、field2
			if (!value1.equals("")) {
				// 当第一个字段不空时，作为条件查询第二个字段
				sql = "select (" + field2 + ")+1 as ID from " + table
				+ " where " + field1 + "=" + value1 + " order by "
				+ field2;
				if (!type1) {
					sql = "select (" + field2 + ")+1 as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
					// 是字符串时 将type1设为false
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) { // 有记录
				out = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	public int makeID(String table, String field1, String field2,
			String field3, String value1, String value2, boolean type1,
			boolean type2) {
		int out = -1;
		String sql = "";
		try {
			// 只有唯一主键field1
			sql = "select " + field1 + " as ID from " + table + " order by "
			+ field1;
			// 有两个主键field1、field2
			if (!value1.equals("")) {// 当第一个字段不空时，作为条件查询第二个字段
				sql = "select " + field2 + " as ID from " + table + " where "
				+ field1 + "=" + value1 + " order by " + field2;
				if (!type1) {
					sql = "select " + field2 + " as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
				}
			}
			if (!value2.equals("")) {// 当第二个字段不空时，作为条件查询第三个字段
				sql = "select " + field3 + " as ID from " + table + " where "
				+ field1 + "=" + value1 + " and " + field2 + "="
				+ value2 + " order by " + field3;
				if (!type2) {
					sql = "select " + field3 + " as ID from " + table
					+ " where " + field1 + "='" + value1 + "' and "
					+ field2 + "='" + value2 + "' order by " + field3;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int t1 = 1;
			int t2 = 2;
			if (rs.next()) { // 有记录
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while (rs.next()) { // 不止一条纪录
					bool = true;
					t2 = rs.getInt("ID");
					if ((t2 - t1) > 1) {
						break; // 如果t2与t1相差大于1,则跳出去,新编号为t1++（见后面**）
					}
					t1 = t2; // 否则将t2赋给t1
				}
				if (!bool) {// 如果只有一条纪录
					if (t1 > 1) {
						t1 = 1; // 如果已有纪录的ID号大于1，则新编号设为1
					} else {
						t1++;
					}
				} else {
					t1++; // **
				}
			}
			if (out > 1) {
				out = 1;
			} else {
				out = t1;
			}
		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	public int makeID_Add1(String table, String field1, String field2,
			String field3, String value1, String value2, boolean type1,
			boolean type2) {
		int out = -1;
		String sql = "";
		try {
			// 只有唯一主键field1
			sql = "select max(" + field1 + ") as ID from " + table
			+ " order by " + field1;
			// 有两个主键field1、field2
			if (!value1.equals("")) {// 当第一个字段不空时，作为条件查询第二个字段
				sql = "select max(" + field2 + ") as ID from " + table
				+ " where " + field1 + "=" + value1 + " order by "
				+ field2;
				if (!type1) {
					sql = "select max(" + field2 + ") as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
				}
			}
			if (!value2.equals("")) {// 当第二个字段不空时，作为条件查询第三个字段
				sql = "select max(" + field3 + ") as ID from " + table
				+ " where " + field1 + "=" + value1 + " and " + field2
				+ "=" + value2 + " order by " + field3;
				if (!type2) {
					sql = "select max(" + field3 + ") as ID from " + table
					+ " where " + field1 + "='" + value1 + "' and "
					+ field2 + "='" + value2 + "' order by " + field3;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) { // 有记录
				out = rs.getInt("ID");
			}
		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	/** 将名称转换为编号 */
	public int toID(String table, String field1, String field2, String value1) {
		int out = -1;
		String sql = "";
		try {
			sql = "select " + field2 + " from " + table + " where " + field1
			+ "='" + value1 + "'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				out = rs.getInt(field2);
			}
		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::toID(String table,String field1,String field2,String value1)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::toID(String table,String field1,String field2,String value1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::toID(String table,String field1,String field2,String value1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	/** 将编号转换为名称 */
	public String toName(String table, String field1, String field2,
			String value1) {
		String out = "";
		String sql = "";
		try {
			sql = "select " + field2 + " from " + table + " where " + field1
			+ "='" + value1 + "'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				out = DealString.toString(rs.getString(field2));
			}
		} catch (SQLException sqle) {
			System.out
			.println("执行DataBase::toName(String table,String field1,String field2,String value1)调用SQL语句 "
					+ sql + " 时出错;\r\n错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::toName(String table,String field1,String field2,String value1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::toName(String table,String field1,String field2,String value1)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return out;
	}

	public Vector<Object> getOnePage(String sql, int page, int records) {
		return getOnePage(sql, page, records, true);
	}

	public Vector<Object> getOnePage(String sql, int page, int records,
			boolean b) {
		/**
		 * 第一个为总页数 第二...个为Hashtable
		 */
		Vector<Object> vect = new Vector<Object>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			int sum = rows / records;
			if ((rows % records != 0) || (rows == 0)) {
				sum++;
			}
			vect.add("" + rows);
			vect.add("" + sum);
			int temp = rows;

			// 移到当前行
			pstm.close();
			// rs.close();

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rows = (page - 1) * records;
			rows++;
			while (rows > 0) {
				rs.next();
				rows--;
			}


			// 查询当前页
			int j = 0;

			do {
				if ((rs == null) || (j == records) || (temp == 0)
						|| (page > sum)) {
					break;
				}
				j++;

				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();
				Hashtable<String, String> hash = new Hashtable<String, String>();
				for (int i = 1; i <= cols; i++) {
					String field = DealString.toString(rsmd.getColumnName(i));
					String value = DealString.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			} while (rs.next());
		} catch (SQLException sqle) {
			System.out.println("执行SQL语句 " + sql + " 分页至第 " + page
					+ " 页时出错;错误为:" + sqle);
		} finally {
			closeRs();
			closePstm();
		}
		return vect;
	}

	/** 分页时取得一页的数据量 */
	public Vector<Object> getOnePage1(String sql, int page, int records,
			boolean useDic) {
		// 第一个为总页数*/
		// 第二...个为Hashtable*/
		Vector<Object> vect = new Vector<Object>();
		int zdrecords = records;
		try {
			if (useDic) {
				String strsql = "select XMMC from CODE_ZDB where trim(ZDMC)='每页显示记录条数'";
				pstm = conn.prepareStatement(strsql);
				rs = pstm.executeQuery();
				if (rs.next()) {
					zdrecords = Integer.parseInt(rs.getString("XMMC"));
				}
				rs.close();
				pstm.close();
			}
			// 查询总页数
			// pstm.clearBatch();
			pstm = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			int sum = rows / zdrecords;
			if ((rows % zdrecords != 0) || (rows == 0)) {
				sum++;
			}
			vect.add("" + sum);

			rs.close();
			pstm.close();

			// 移到当前行
			// pstm.clearBatch();
			pstm = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			rows = (page - 1) * zdrecords;
			rs.absolute(rows + 1);
			rs.previous();


			// 查询当前页
			int j = 0;
			while (rs.next()) {
				if (j == zdrecords) {
					break;
				}
				j++;

				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();
				Hashtable<String, String> hash = new Hashtable<String, String>();
				for (int i = 1; i <= cols; i++) {
					String field = DealString.toString(rsmd.getColumnName(i));
					String value = DealString.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			}
		} catch (SQLException sqle) {
			System.out.println("DataBase::getOnePage(String,int,int)执行SQL语句 "
					+ sql + " 分页至第 " + page + " 页时出错;错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("DataBase::getOnePage(String,int,int)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("DataBase::getOnePage(String,int,int)调用SQL语句 "
							+ sql + " 时出错;\r\n错误为:" + e);
				}
			}
		}
		return vect;
	}

	public Vector<Hashtable<String, String>> getData(String sql) {
		Vector<Hashtable<String, String>> vect = new Vector<Hashtable<String, String>>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				Hashtable<String, String> hash = new Hashtable<String, String>();
				for (int i = 1; i <= cols; i++) {
					String field = DealString.toString(rsmd.getColumnName(i));
					String value = DealString.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			}
		} catch (SQLException sqle) {
			System.out.println("执行DataBase::getData(String)执行SQL语句 " + sql
					+ " 时出错;错误为:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::getData(String)试图释放rs时出错;\r\n错误为:"
							+ e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("执行DataBase::getData(String)试图释放pstm时出错;\r\n错误为:"
							+ e);
				}
			}
		}
		return vect;
	}

	/** 为某一个字段进行重新排序 */
	public int setSort(String table, String field1, String field2,
			String wherestr, String orderstr, boolean b) {
		// 写入序列号,field2为唯一字段*/
		try {
			String sql = "select " + field2 + " from " + table;
			if (!wherestr.equals("")) {
				sql += " where " + wherestr;
			}
			sql += " " + orderstr;

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			PreparedStatement pstm_t = null;
			int i = 1;
			while (rs.next()) {
				if (b) {// 为field2整型
					sql = "update " + table + " set " + field1 + "=" + i
					+ " where " + field2 + "=" + rs.getString(1);
				} else {// 为field2字符串
					sql = "update " + table + " set " + field1 + "=" + i
					+ " where " + field2 + "='" + rs.getString(1) + "'";
				}
				pstm_t = conn.prepareStatement(sql);
				pstm_t.executeUpdate();
				i++;
			}

			pstm_t.close();
		} catch (SQLException sqle) {
			System.out.println("调用MyDataBase.setSort()函数错误:\r\n" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("调用MyDataBase.setSort()试图释放rs时出错;\r\n错误为:"
							+ e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("调用MyDataBase.setSort()试图释放pstm时出错;\r\n错误为:"
							+ e);
				}
			}
		}
		return 0;
	}

	/** 取得数据集内容 */
	public Vector<Hashtable<String, String>> getResultSetData(ResultSet rs) {
		Vector<Hashtable<String, String>> vect = new Vector<Hashtable<String, String>>();
		try {
			// 取得列数和列名
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				Hashtable<String, String> hash = new Hashtable<String, String>();
				for (int i = 1; i <= cols; i++) {
					String field = DealString.toString(rsmd.getColumnName(i));
					String value = DealString.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			}
		} catch (SQLException sqle) {
			System.out.println("调用DataBase.getResultSetData()函数错误:\r\n" + sqle);
		}
		return vect;
	}

	/** 取得数据并根据数据类型转化为字符串 */
	public String getObject(String field, String sqlType) {
		try {
			if (rs == null) {
				return "";
			}
			if (sqlType.equals("BINARY") || sqlType.equals("VARBINARY")) {// 字节型
				byte b[] = rs.getBytes(field);
				return new String(b);
			} else if (sqlType.equals("LONGVARBINARY")
					|| sqlType.equals("BLOB")) {// 未编码大字节型
				InputStream is = rs.getBinaryStream(field);
				return (new DealFile()).readCHStr(is);
			} else if (sqlType.equals("LONGVARCHAR") || sqlType.equals("CLOB")) {// 编码大字节型
				InputStream is = rs.getAsciiStream(field);
				return (new DealFile()).readCHStr(is);
			} else {// 字符串型
				return rs.getString(field);
			}
		} catch (Exception sqle) {
			System.out.println("调用DataBase.getObject()函数错误:\r\n" + sqle);
		}
		return "";
	}

	/** 关闭申明对象 */
	public void closePstm() {
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException sqle) {
				System.out.println("调用DataBase.closePstm()函数错误:\r\n" + sqle);
			}
		}
	}

	/** 关闭游标 */
	public void closeRs() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				System.out.println("调用DataBase.closeRs()函数错误:\r\n" + sqle);
			}
		}
	}

	public boolean getAutoCommit() {
		try {
			return conn.getAutoCommit();
		} catch (SQLException e) {
		}
		return true;
	}

	public void closeStm() {// 专门使用
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
			}
		}
	}

}