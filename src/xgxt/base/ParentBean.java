/*
 * �������� 2006-5-13
 * 
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class ParentBean extends Object {
	// ���ݿ⴦�����*/
	//protected DataBase db = new DataBase();
	protected DataBase db = new DataBase();
	// �ַ�������ʱʹ�ö���*/
	protected static DealString ds = new DealString();

	public static int count = 0;

	/** ��ǰ���ӵ����ݿ����� */
	public static String DBType = "NULL";

	public static String DBName = "";

	// ���캯��,��ʼ������*/
	public ParentBean() {
		//db.getMyConnPool();

		DBType = "Oracle";
		DBName = "zfconnpool";
	}

	/** ����һ�����ݿ����� */
	public Connection getConn() {
		DBType = "Oracle";
		DBName = "zfconnpool";
		return db.conn;
	}

	/** �ͷ����� */
	public void closeConn() {
		DBType = "NULL";
		db.releaseMyConnPool();

	}

	/** ��ѯ��¼ */
	public ResultSet selectRecord(String sql) {
		return db.QuerySQL(sql);
	}

	/** ��ѯһ����¼ */
	public String selectOneRecord(String sql, String[] strs) {
		// select password from username where userid=?
		// db.QueryOneSQL(sql,str1,str2,str3);
		return db.QueryOneSQL(sql, strs);
	}

	/** ������¼ */
	protected int insertRecord(Vector vect) {
		/**
		 * Vector:��1�� ����(String) // ��2��
		 * ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
		 */

		// ��ʱ����
		String sqlField = "";// ����(F1,F2)
		String sqlValue = "";// ����(V1,V2)
		String field = "";
		String value = "";
		String type = "";

		for (int i = 1; i < vect.size(); i++) {
			// ��ĳһ���ֶ�
			Vector v_t = (Vector) vect.get(i);
			field = (String) v_t.get(0);
			value = (String) v_t.get(1);
			if (value.indexOf("'") != -1) {
				value = value.replaceAll("'", "''");
			}
			type = (String) v_t.get(2);

			// ����ֶ�SQL
			if (sqlField.equals("")) {
				sqlField = "(";
			} else {
				sqlField = sqlField + ",";
			}
			sqlField = sqlField + field;

			// ���ֵSQL
			if (sqlValue.equals("")) {
				sqlValue = "(";
			} else {
				sqlValue = sqlValue + ",";
			}
			if (value.equals("")) {// Ϊ��ʱ
				sqlValue = sqlValue + "null";
			} else if (type.equals("CHAR")) {// �ַ���
				sqlValue = sqlValue + "'" + value + "'";
			} else if (type.equals("NUM")) {// ��ֵ
				sqlValue = sqlValue + value;
			} else if (type.equals("TIME")) {// ����
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value
				+ "')";
			} else if (type.equals("CLOB")) {// clob����
				sqlValue = sqlValue + "empty_clob()";
			} else if (type.equals("BLOB")) {// blob����
				sqlValue = sqlValue + "empty_blob()";
			}
		}

		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String) vect.get(0) + sqlField
		+ " values" + sqlValue;
		return db.ExecuteSQL(sql);
	}

	/** �޸ļ�¼ */
	protected int updateRecord(Vector vect) {
		/**
		 * Vector:��1�� ����(String) // ��2��
		 * ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")]) //
		 * ��3�� ����(String sql)
		 */

		// ��ʱ����
		String sqlSet = "";// ����(Name='name',ID=9)
		String field = "";
		String value = "";
		String type = "";

		int i = 1;
		int n = vect.size();
		for (; i < (n - 1); i++) {
			// ��ĳһ���ֶ�
			Vector v_t = (Vector) vect.get(i);
			field = (String) v_t.get(0);
			value = (String) v_t.get(1);
			if (value.indexOf("'") != -1) {
				value = value.replaceAll("'", "''");
			}
			type = (String) v_t.get(2);

			// ����ֶ�SQL
			if (sqlSet.equals("")) {
				sqlSet = " ";
			} else {
				sqlSet = sqlSet + ",";
			}
			sqlSet = sqlSet + field + "=";
			if (value.equals("") && type.equals("NUM")) {// Ϊ��ʱ
				sqlSet = sqlSet + "null";
			}
			if (type.equals("CHAR")) {// �ַ���
				sqlSet = sqlSet + "'" + value + "'";
			} else if (type.equals("NUM")) {// ��ֵ
				sqlSet = sqlSet + value;
			} else if (type.equals("TIME")) {// ����
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value
				+ "')";
			} else if (type.equals("CLOB")) {// clob����
			} else if (type.equals("BLOB")) {// blob����
			}
		}

		String sql = "update " + (String) vect.get(0) + " set " + sqlSet;
		String sqlWhere = (String) vect.get(vect.size() - 1);
		if (!sqlWhere.equals("")) {
			sql = sql + " where " + sqlWhere;
		}
		return db.ExecuteSQL(sql);
	}

	/** ɾ����¼ */
	protected int deleteRecord(String sql) {
		return db.ExecuteSQL(sql);
	}

	/** ִ����� */
	protected int executeUpdate(String sql) {
		return db.ExecuteSQL(sql);
	}

	/** ����Ψһ��� */
	public int makeID(String table, String field1, String field2,
			String value1, boolean type1) {
		return db.makeID(table, field1, field2, value1, type1);
	}

	public int makeID(String table, String field1, String field2,
			String field3, String value1, String value2, boolean type1,
			boolean type2) {
		return db.makeID(table, field1, field2, field3, value1, value2, type1,
				type2);
	}

	public int makeID_Add1(String table, String field1, String field2,
			String value1, boolean type1) {
		return db.makeID(table, field1, field2, value1, type1);
	}

	public int makeID_Add1(String table, String field1, String field2,
			String field3, String value1, String value2, boolean type1,
			boolean type2) {
		return db.makeID(table, field1, field2, field3, value1, value2, type1,
				type2);
	}

	/** ������ת��Ϊ��� */
	public int toID(String table, String field1, String field2, String value1) {
		return db.toID(table, field1, field2, value1);
	}

	/** �����ת��Ϊ���� */
	public String toName(String table, String field1, String field2,
			String value1) {
		return db.toName(table, field1, field2, value1);
	}

	/** д���ݿ�ʱĳһ���ֶεĴ洢���� */
	protected Vector<String> addVector(String field, String value, String type) {
		Vector<String> vect = new Vector<String>();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}

	/** ��ҳʱȡ��һҳ�������� */
	public Vector getOnePage(String sql, int page, int records) {
		return db.getOnePage(sql, page, records);
	}

	/** Ϊĳһ���ֶν����������� */
	public int setSort(String table, String field1, String field2,
			String wherestr, String orderstr, boolean b) {
		return db.setSort(table, field1, field2, wherestr, orderstr, b);
	}

	/** ȡ�����ݼ����� */
	public Vector getResultSetData(ResultSet rs) {
		return db.getResultSetData(rs);
	}

	/** ȡ�����ݲ�������������ת��Ϊ�ַ��� */
	public String getObject(String field, String sqlType) {
		return db.getObject(field, sqlType);
	}

	/** �ر��������� */
	public void closePstm() {
		db.closePstm();
	}

	/** �ر��α� */
	public void closeRs() {
		db.closeRs();
	}

	public Vector getDataBySql(String sql) {
		return db.getData(sql);
	}

	public boolean getAutoCommit() {
		return db.getAutoCommit();
	}

	public void closeStm() {
		db.closeStm();
	}

}