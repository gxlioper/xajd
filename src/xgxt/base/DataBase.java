
/*
 * �������� 2006-5-13
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * ���ļ�Ϊ���ݿ�Ļ�������������
 */
public class DataBase extends Object {
	Connection conn = null;

	Statement stm = null;

	PreparedStatement pstm = null;

	ResultSet rs = null;

	boolean connected = false;

	/** ���캯�� */
	public DataBase() {
	}

	/** �����ҵ����ӳ� */
	public boolean getMyConnPool() {
		conn = Configuration.connMgr
		.getConnection(Configuration.ConnectionPoolName);
		if (conn == null) {
			return false;
		}
		return true;
	}

	/** �ͷ��ҵ����ӳ� */
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

	/** �ͷ����ݿ����� */
	public void releaseConn() {
		try {
			if (!connected) {
				// throw new SQLException("���ݿ�δ���ӣ�");
			}
			if (conn != null) {
				conn.close();
				connected = false;
			}
		} catch (SQLException e) {
			System.out.println("�ر����ݿ�����ʱ����;\r\n����Ϊ:" + e);
		}
	}

	/** ��ѯ��¼ */
	public ResultSet QuerySQL(String sql) {
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		} catch (SQLException sqle) {
			System.out.println("ִ��DataBase::QuerySQL(String)����SQL��� " + sql
					+ " ʱ����;\r\n����Ϊ:" + sqle);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					System.out
					.println("ִ��DataBase::QuerySQL(String)��ͼ�رմ��������ʱ����;\r\n����Ϊ��"
							+ e);
				}
			}
		}
		return rs;
	}

	/** ��ѯһ����¼ */
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
			System.out.println("ִ��DataBase::QuerySQL(String)����SQL��� " + sql
					+ " ʱ����;\r\n����Ϊ:" + sqle);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					System.out
					.println("ִ��DataBase::QuerySQL(String)��ͼ�رմ��������ʱ����;\r\n����Ϊ��"
							+ e);
				}
			}
		}
		return rsstr;
	}

	/** ִ����ɾ�ĵ���� */
	public int ExecuteSQL(String sql) {
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			conn.commit();
		} catch (SQLException sqle) {
			// System.out.println("ִ��DataBase::ExecuteSQL(String)����SQL���
			// "+sql+"
			// ʱ����;\r\n����Ϊ:"+sqle);
			return sqle.getErrorCode();
		} finally {
			try {
				pstm.close();
			} catch (SQLException sqle) {
				System.out.println("ִ��DataBase::ExecuteSQL(String)����SQL��� "
						+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
			}
		}
		return 0;
	}

	/** ����Ψһ��� */
	public int makeID(String table, String field1, String field2,
			String value1, boolean type1) {
		int out = -1;
		String sql = "";
		try {
			// ֻ��Ψһ����field1
			sql = "select " + field1 + " as ID from " + table + " order by "
			+ field1;
			// ����������field1��field2
			if (!value1.equals("")) {// ����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
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
				// �м�¼
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while (rs.next()) {
					// ��ֹһ����¼
					bool = true;
					t2 = rs.getInt("ID");
					if ((t2 - t1) > 1) {
						break; // ���t2��t1������1,������ȥ,�±��Ϊt1++��������**��
					}
					t1 = t2; // ����t2����t1
				}
				if (!bool) {
					// ���ֻ��һ����¼
					if (t1 > 1) {
						t1 = 1; // ������м�¼��ID�Ŵ���1�����±����Ϊ1
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
			.println("ִ��DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
		}
		return out;
	}

	/** ����Ψһ��� */
	public int makeID_Add1(String table, String field1, String field2,
			String value1, boolean type1) {
		int out = -1;
		String sql = "";
		try {
			// ֻ��Ψһ����field1
			sql = "select max(" + field1 + ")+1 as ID from " + table
			+ " order by " + field1;
			// ����������field1��field2
			if (!value1.equals("")) {
				// ����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select (" + field2 + ")+1 as ID from " + table
				+ " where " + field1 + "=" + value1 + " order by "
				+ field2;
				if (!type1) {
					sql = "select (" + field2 + ")+1 as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
					// ���ַ���ʱ ��type1��Ϊfalse
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) { // �м�¼
				out = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			System.out
			.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String value1,boolean type1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
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
			// ֻ��Ψһ����field1
			sql = "select " + field1 + " as ID from " + table + " order by "
			+ field1;
			// ����������field1��field2
			if (!value1.equals("")) {// ����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select " + field2 + " as ID from " + table + " where "
				+ field1 + "=" + value1 + " order by " + field2;
				if (!type1) {
					sql = "select " + field2 + " as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
				}
			}
			if (!value2.equals("")) {// ���ڶ����ֶβ���ʱ����Ϊ������ѯ�������ֶ�
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
			if (rs.next()) { // �м�¼
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while (rs.next()) { // ��ֹһ����¼
					bool = true;
					t2 = rs.getInt("ID");
					if ((t2 - t1) > 1) {
						break; // ���t2��t1������1,������ȥ,�±��Ϊt1++��������**��
					}
					t1 = t2; // ����t2����t1
				}
				if (!bool) {// ���ֻ��һ����¼
					if (t1 > 1) {
						t1 = 1; // ������м�¼��ID�Ŵ���1�����±����Ϊ1
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
			.println("ִ��DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
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
			// ֻ��Ψһ����field1
			sql = "select max(" + field1 + ") as ID from " + table
			+ " order by " + field1;
			// ����������field1��field2
			if (!value1.equals("")) {// ����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select max(" + field2 + ") as ID from " + table
				+ " where " + field1 + "=" + value1 + " order by "
				+ field2;
				if (!type1) {
					sql = "select max(" + field2 + ") as ID from " + table
					+ " where " + field1 + "='" + value1
					+ "' order by " + field2;
				}
			}
			if (!value2.equals("")) {// ���ڶ����ֶβ���ʱ����Ϊ������ѯ�������ֶ�
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
			if (rs.next()) { // �м�¼
				out = rs.getInt("ID");
			}
		} catch (SQLException sqle) {
			System.out
			.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
		}
		return out;
	}

	/** ������ת��Ϊ��� */
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
			.println("ִ��DataBase::toID(String table,String field1,String field2,String value1)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::toID(String table,String field1,String field2,String value1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::toID(String table,String field1,String field2,String value1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
		}
		return out;
	}

	/** �����ת��Ϊ���� */
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
			.println("ִ��DataBase::toName(String table,String field1,String field2,String value1)����SQL��� "
					+ sql + " ʱ����;\r\n����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::toName(String table,String field1,String field2,String value1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::toName(String table,String field1,String field2,String value1)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
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
		 * ��һ��Ϊ��ҳ�� �ڶ�...��ΪHashtable
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

			// �Ƶ���ǰ��
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


			// ��ѯ��ǰҳ
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
			System.out.println("ִ��SQL��� " + sql + " ��ҳ���� " + page
					+ " ҳʱ����;����Ϊ:" + sqle);
		} finally {
			closeRs();
			closePstm();
		}
		return vect;
	}

	/** ��ҳʱȡ��һҳ�������� */
	public Vector<Object> getOnePage1(String sql, int page, int records,
			boolean useDic) {
		// ��һ��Ϊ��ҳ��*/
		// �ڶ�...��ΪHashtable*/
		Vector<Object> vect = new Vector<Object>();
		int zdrecords = records;
		try {
			if (useDic) {
				String strsql = "select XMMC from CODE_ZDB where trim(ZDMC)='ÿҳ��ʾ��¼����'";
				pstm = conn.prepareStatement(strsql);
				rs = pstm.executeQuery();
				if (rs.next()) {
					zdrecords = Integer.parseInt(rs.getString("XMMC"));
				}
				rs.close();
				pstm.close();
			}
			// ��ѯ��ҳ��
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

			// �Ƶ���ǰ��
			// pstm.clearBatch();
			pstm = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			rows = (page - 1) * zdrecords;
			rs.absolute(rows + 1);
			rs.previous();


			// ��ѯ��ǰҳ
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
			System.out.println("DataBase::getOnePage(String,int,int)ִ��SQL��� "
					+ sql + " ��ҳ���� " + page + " ҳʱ����;����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("DataBase::getOnePage(String,int,int)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("DataBase::getOnePage(String,int,int)����SQL��� "
							+ sql + " ʱ����;\r\n����Ϊ:" + e);
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
			System.out.println("ִ��DataBase::getData(String)ִ��SQL��� " + sql
					+ " ʱ����;����Ϊ:" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::getData(String)��ͼ�ͷ�rsʱ����;\r\n����Ϊ:"
							+ e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("ִ��DataBase::getData(String)��ͼ�ͷ�pstmʱ����;\r\n����Ϊ:"
							+ e);
				}
			}
		}
		return vect;
	}

	/** Ϊĳһ���ֶν����������� */
	public int setSort(String table, String field1, String field2,
			String wherestr, String orderstr, boolean b) {
		// д�����к�,field2ΪΨһ�ֶ�*/
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
				if (b) {// Ϊfield2����
					sql = "update " + table + " set " + field1 + "=" + i
					+ " where " + field2 + "=" + rs.getString(1);
				} else {// Ϊfield2�ַ���
					sql = "update " + table + " set " + field1 + "=" + i
					+ " where " + field2 + "='" + rs.getString(1) + "'";
				}
				pstm_t = conn.prepareStatement(sql);
				pstm_t.executeUpdate();
				i++;
			}

			pstm_t.close();
		} catch (SQLException sqle) {
			System.out.println("����MyDataBase.setSort()��������:\r\n" + sqle);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out
					.println("����MyDataBase.setSort()��ͼ�ͷ�rsʱ����;\r\n����Ϊ:"
							+ e);
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out
					.println("����MyDataBase.setSort()��ͼ�ͷ�pstmʱ����;\r\n����Ϊ:"
							+ e);
				}
			}
		}
		return 0;
	}

	/** ȡ�����ݼ����� */
	public Vector<Hashtable<String, String>> getResultSetData(ResultSet rs) {
		Vector<Hashtable<String, String>> vect = new Vector<Hashtable<String, String>>();
		try {
			// ȡ������������
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
			System.out.println("����DataBase.getResultSetData()��������:\r\n" + sqle);
		}
		return vect;
	}

	/** ȡ�����ݲ�������������ת��Ϊ�ַ��� */
	public String getObject(String field, String sqlType) {
		try {
			if (rs == null) {
				return "";
			}
			if (sqlType.equals("BINARY") || sqlType.equals("VARBINARY")) {// �ֽ���
				byte b[] = rs.getBytes(field);
				return new String(b);
			} else if (sqlType.equals("LONGVARBINARY")
					|| sqlType.equals("BLOB")) {// δ������ֽ���
				InputStream is = rs.getBinaryStream(field);
				return (new DealFile()).readCHStr(is);
			} else if (sqlType.equals("LONGVARCHAR") || sqlType.equals("CLOB")) {// ������ֽ���
				InputStream is = rs.getAsciiStream(field);
				return (new DealFile()).readCHStr(is);
			} else {// �ַ�����
				return rs.getString(field);
			}
		} catch (Exception sqle) {
			System.out.println("����DataBase.getObject()��������:\r\n" + sqle);
		}
		return "";
	}

	/** �ر��������� */
	public void closePstm() {
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException sqle) {
				System.out.println("����DataBase.closePstm()��������:\r\n" + sqle);
			}
		}
	}

	/** �ر��α� */
	public void closeRs() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				System.out.println("����DataBase.closeRs()��������:\r\n" + sqle);
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

	public void closeStm() {// ר��ʹ��
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
			}
		}
	}

}