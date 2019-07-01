package xgxt.utils.util.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class DBHandle {

	public final static String connURL = "jdbc:oracle:thin:@10.128.32.222:1521:orcl";

	public final static String userName = "jsxx";

	public final static String password = "123";

	public final static String connDriver = "oracle.jdbc.driver.OracleDriver";

	public final static String dbtype = "oracle";

	public static Connection getConnection() throws Exception {
		Connection connection = null;
		Class.forName(connDriver);
		connection = DriverManager.getConnection(connURL, userName, password);
		return connection;
	}

	public static void closeConnection(Connection connection,
			PreparedStatement ps, ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static BeanTableStruct[] getTableStruct(String tableName)
			throws Exception {

		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from "
				+ tableName);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsm = rs.getMetaData();
		int count = rsm.getColumnCount();
		BeanTableStruct[] beans = new BeanTableStruct[count];
		for (int i = 0; i < count; i++) {
			String cName = rsm.getColumnName(i + 1);
			String cLable = rsm.getColumnLabel(i + 1);
			String cTypeName = rsm.getColumnTypeName(i + 1);
			// String cClassName = rsm.getColumnClassName(i+1);
			int cDisplaySize = rsm.getColumnDisplaySize(i + 1);
			String cType = rsm.getColumnTypeName(i + 1);

			BeanTableStruct beanTableStruct = new BeanTableStruct();
			// beanTableStruct.setCClassName(cClassName);
			beanTableStruct.setCDisplaySize(cDisplaySize);
			beanTableStruct.setCLable(cLable);
			beanTableStruct.setCName(cName.toLowerCase());
			beanTableStruct.setCType(cType);
			beanTableStruct.setCTypeName(cTypeName);
			beans[i] = beanTableStruct;
		}

		closeConnection(connection, ps, rs);

		return beans;
	}

	public static void main(String[] args) throws Exception {
		BeanTableStruct[] beans = getTableStruct("t_product");
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			System.out.println(beanTableStruct.getCName() + "---"
					+ beanTableStruct.getCType());
		}
	}
}
