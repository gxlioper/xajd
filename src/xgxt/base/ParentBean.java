/*
 * 创建日期 2006-5-13
 * 
 *  要更改此生成的文件的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class ParentBean extends Object {
	// 数据库处理对象*/
	//protected DataBase db = new DataBase();
	protected DataBase db = new DataBase();
	// 字符串处理时使用对象*/
	protected static DealString ds = new DealString();

	public static int count = 0;

	/** 当前连接的数据库类型 */
	public static String DBType = "NULL";

	public static String DBName = "";

	// 构造函数,初始化连接*/
	public ParentBean() {
		//db.getMyConnPool();

		DBType = "Oracle";
		DBName = "zfconnpool";
	}

	/** 返回一个数据库连接 */
	public Connection getConn() {
		DBType = "Oracle";
		DBName = "zfconnpool";
		return db.conn;
	}

	/** 释放连接 */
	public void closeConn() {
		DBType = "NULL";
		db.releaseMyConnPool();

	}

	/** 查询记录 */
	public ResultSet selectRecord(String sql) {
		return db.QuerySQL(sql);
	}

	/** 查询一条记录 */
	public String selectOneRecord(String sql, String[] strs) {
		// select password from username where userid=?
		// db.QueryOneSQL(sql,str1,str2,str3);
		return db.QueryOneSQL(sql, strs);
	}

	/** 新增记录 */
	protected int insertRecord(Vector vect) {
		/**
		 * Vector:第1项 表名(String) // 第2项
		 * 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
		 */

		// 临时变量
		String sqlField = "";// 形如(F1,F2)
		String sqlValue = "";// 形如(V1,V2)
		String field = "";
		String value = "";
		String type = "";

		for (int i = 1; i < vect.size(); i++) {
			// 对某一个字段
			Vector v_t = (Vector) vect.get(i);
			field = (String) v_t.get(0);
			value = (String) v_t.get(1);
			if (value.indexOf("'") != -1) {
				value = value.replaceAll("'", "''");
			}
			type = (String) v_t.get(2);

			// 组合字段SQL
			if (sqlField.equals("")) {
				sqlField = "(";
			} else {
				sqlField = sqlField + ",";
			}
			sqlField = sqlField + field;

			// 组合值SQL
			if (sqlValue.equals("")) {
				sqlValue = "(";
			} else {
				sqlValue = sqlValue + ",";
			}
			if (value.equals("")) {// 为空时
				sqlValue = sqlValue + "null";
			} else if (type.equals("CHAR")) {// 字符串
				sqlValue = sqlValue + "'" + value + "'";
			} else if (type.equals("NUM")) {// 数值
				sqlValue = sqlValue + value;
			} else if (type.equals("TIME")) {// 日期
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value
				+ "')";
			} else if (type.equals("CLOB")) {// clob类型
				sqlValue = sqlValue + "empty_clob()";
			} else if (type.equals("BLOB")) {// blob类型
				sqlValue = sqlValue + "empty_blob()";
			}
		}

		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String) vect.get(0) + sqlField
		+ " values" + sqlValue;
		return db.ExecuteSQL(sql);
	}

	/** 修改记录 */
	protected int updateRecord(Vector vect) {
		/**
		 * Vector:第1项 表名(String) // 第2项
		 * 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")]) //
		 * 第3项 条件(String sql)
		 */

		// 临时变量
		String sqlSet = "";// 形如(Name='name',ID=9)
		String field = "";
		String value = "";
		String type = "";

		int i = 1;
		int n = vect.size();
		for (; i < (n - 1); i++) {
			// 对某一个字段
			Vector v_t = (Vector) vect.get(i);
			field = (String) v_t.get(0);
			value = (String) v_t.get(1);
			if (value.indexOf("'") != -1) {
				value = value.replaceAll("'", "''");
			}
			type = (String) v_t.get(2);

			// 组合字段SQL
			if (sqlSet.equals("")) {
				sqlSet = " ";
			} else {
				sqlSet = sqlSet + ",";
			}
			sqlSet = sqlSet + field + "=";
			if (value.equals("") && type.equals("NUM")) {// 为空时
				sqlSet = sqlSet + "null";
			}
			if (type.equals("CHAR")) {// 字符串
				sqlSet = sqlSet + "'" + value + "'";
			} else if (type.equals("NUM")) {// 数值
				sqlSet = sqlSet + value;
			} else if (type.equals("TIME")) {// 日期
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value
				+ "')";
			} else if (type.equals("CLOB")) {// clob类型
			} else if (type.equals("BLOB")) {// blob类型
			}
		}

		String sql = "update " + (String) vect.get(0) + " set " + sqlSet;
		String sqlWhere = (String) vect.get(vect.size() - 1);
		if (!sqlWhere.equals("")) {
			sql = sql + " where " + sqlWhere;
		}
		return db.ExecuteSQL(sql);
	}

	/** 删除记录 */
	protected int deleteRecord(String sql) {
		return db.ExecuteSQL(sql);
	}

	/** 执行语句 */
	protected int executeUpdate(String sql) {
		return db.ExecuteSQL(sql);
	}

	/** 产生唯一编号 */
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

	/** 将名称转换为编号 */
	public int toID(String table, String field1, String field2, String value1) {
		return db.toID(table, field1, field2, value1);
	}

	/** 将编号转换为名称 */
	public String toName(String table, String field1, String field2,
			String value1) {
		return db.toName(table, field1, field2, value1);
	}

	/** 写数据库时某一个字段的存储类型 */
	protected Vector<String> addVector(String field, String value, String type) {
		Vector<String> vect = new Vector<String>();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}

	/** 分页时取得一页的数据量 */
	public Vector getOnePage(String sql, int page, int records) {
		return db.getOnePage(sql, page, records);
	}

	/** 为某一个字段进行重新排序 */
	public int setSort(String table, String field1, String field2,
			String wherestr, String orderstr, boolean b) {
		return db.setSort(table, field1, field2, wherestr, orderstr, b);
	}

	/** 取得数据集内容 */
	public Vector getResultSetData(ResultSet rs) {
		return db.getResultSetData(rs);
	}

	/** 取得数据并根据数据类型转化为字符串 */
	public String getObject(String field, String sqlType) {
		return db.getObject(field, sqlType);
	}

	/** 关闭申明对象 */
	public void closePstm() {
		db.closePstm();
	}

	/** 关闭游标 */
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