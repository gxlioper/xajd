/*
 * �������� 2006-5-13
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * ������DBConnectionManager֧�ֶ�һ�������������ļ���������ݿ�����
 * �صķ���.�ͻ�������Ե���getInstance()�������ʱ����Ψһʵ��.
 */
public class DBConnectionManager {
	static private DBConnectionManager instance; // Ψһʵ��

	static private int clients;

	private Vector<Driver> drivers = new Vector<Driver>();

	public PrintWriter log;

	public static Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

	/**
	 * ����Ψһʵ��.����ǵ�һ�ε��ô˷���,�򴴽�ʵ��
	 * 
	 * @return DBConnectionManager Ψһʵ��
	 */
	static synchronized public DBConnectionManager getInstance() {
		// Ϊ���ܹ��ڿ���̨��Tomcat�ж���ʹ�����ӳأ��������ļ��г�ʼ��������
		// ��һ�γ�ʼ��Ĭ�ϵ����ò�����������̨ʹ�ã�
		// �ڶ��γ�ʼ��Tomcat�е����ò�����
		// Ϊ���ڵڶ����ܹ����½��в�����ȡ���˴�ע��
		instance = new DBConnectionManager();
		clients++;
		return instance;
	}

	public static int getCurConns(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getCurConns();
		}
		return 0;
	}

	public static int getSumConns(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getSumConns();
		}
		return 0;
	}

	public static int getMaxConns(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getMaxConns();
		}
		return 0;
	}

	public static int getNullPool(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getNullPool();
		}
		return 0;
	}

	public static int getOracleErr(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getOracleErr();
		}
		return 0;
	}

	/**
	 * ��������˽���Է�ֹ�������󴴽�����ʵ��
	 */
	public DBConnectionManager() {
		init();
	}

	/**
	 * �����Ӷ��󷵻ظ�������ָ�������ӳ�
	 * 
	 * @param name
	 *                �������ļ��ж�������ӳ�����
	 * @param con
	 *                ���Ӷ���
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/**
	 * ���һ�����õ�(���е�)����.���û�п�������,������������С����������� ����,�򴴽�������������
	 * 
	 * @param name
	 *                �������ļ��ж�������ӳ�����
	 * @return Connection �������ӻ�null
	 */
	public Connection getConnection(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * ���һ����������.��û�п�������,������������С���������������, �򴴽�������������.����,��ָ����ʱ���ڵȴ������߳��ͷ�����.
	 * 
	 * @param name
	 *                ���ӳ�����
	 * @param time
	 *                �Ժ���Ƶĵȴ�ʱ��
	 * @return Connection �������ӻ�null
	 */
	public Connection getConnection(String name, long time) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/*
	 * public static void test(String name) { DBConnectionPool pool =
	 * (DBConnectionPool)pools.get(name); pool.test(); }
	 */

	/**
	 * �ر���������,�������������ע��
	 */
	public synchronized void release() {
		// �ȴ�ֱ�����һ���ͻ��������
		if (--clients != 0) {
			return;
		}

		Enumeration<DBConnectionPool> allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = allPools.nextElement();
			pool.release();
		}
		Enumeration<Driver> allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				log("����JDBC�������� " + driver.getClass().getName() + "��ע��");
			} catch (SQLException e) {
				log(e, "�޷���������JDBC���������ע��: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * ����ָ�����Դ������ӳ�ʵ��.
	 * 
	 * @param props
	 *                ���ӳ�����
	 */
	private void createPools() {
		String poolName = Configuration.ConnectionPoolName;
		String url = Configuration.DB_URL;
		if (url == null) {
			log("û��Ϊ���ӳ�" + poolName + "ָ��URL");
			return;
		}
		String user = Configuration.DB_USERNAME;
		String password = Configuration.DB_PASSWORD;
		String maxconn = Configuration.DB_MAXCONNNUM;
		int max;
		try {
			max = Integer.valueOf(maxconn).intValue();
		} catch (NumberFormatException e) {
			log("������������������: " + maxconn + " .���ӳ�: " + poolName);
			max = 0;
		}
		DBConnectionPool pool = new DBConnectionPool(poolName, url, user,
				password, max);
		pools.put(poolName, pool);
		log("�ɹ��������ӳ�" + poolName);

	}

	/**
	 * ��ȡ������ɳ�ʼ��
	 */
	private void init() {
		String logFile = Configuration.DB_LOGFILE;
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("�޷�����־�ļ�: " + logFile);
			log = new PrintWriter(System.err);
		}

		loadDrivers();
		createPools();
	}

	/**
	 * װ�غ�ע������JDBC��������
	 * 
	 * @param props
	 *                ����
	 */
	private void loadDrivers() {
		String driverClasses = Configuration.DB_JDBCDRIVER;
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
				.newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				log("�ɹ�ע��JDBC��������" + driverClassName);
			} catch (Exception e) {
				log("�޷�ע��JDBC��������: " + driverClassName + ", ����: " + e);
				System.out.println("\r\n�޷�ע��JDBC��������: " + driverClassName
						+ ", ����: " + e);
			}
		}
	}

	/**
	 * ���ı���Ϣд����־�ļ�
	 */
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	/**
	 * ���ı���Ϣ���쳣д����־�ļ�
	 */
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}

	/**
	 * ���ڲ��ඨ����һ�����ӳ�.���ܹ�����Ҫ�󴴽�������,ֱ��Ԥ������ ��������Ϊֹ.�ڷ������Ӹ��ͻ�����֮ǰ,���ܹ���֤���ӵ���Ч��.
	 */
	class DBConnectionPool {
		private int sum;

		private int checkedOut;

		private Vector<Connection> freeConnections = new Vector<Connection>();

		private int maxConn;

		private int max;

		private String name;

		private String password;

		private String URL;

		private String user;

		private int isNullPool;

		private int isOracleErr;

		/**
		 * �����µ����ӳ�
		 * 
		 * @param name
		 *                ���ӳ�����
		 * @param URL
		 *                ���ݿ��JDBC URL
		 * @param user
		 *                ���ݿ��ʺ�,�� null
		 * @param password
		 *                ����,�� null
		 * @param maxConn
		 *                �����ӳ������������������
		 */
		public DBConnectionPool(String name, String URL, String user,
				String password, int maxConn) {
			this.name = name;
			this.URL = URL;
			this.user = user;
			this.password = password;
			this.maxConn = maxConn;
		}

		/**
		 * ������ʹ�õ����ӷ��ظ����ӳ�
		 * 
		 * @param con
		 *                �ͻ������ͷŵ�����
		 */
		public synchronized void freeConnection(Connection con) {
			// testConn(con);
			if (con != null) {
				// ��ָ�����Ӽ��뵽����ĩβ
				freeConnections.addElement(con);
				checkedOut--;
			}
			notifyAll();
		}

		protected void close(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
				rs = null;
			}
		}

		protected void close(PreparedStatement pstmt) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
				pstmt = null;
			}
		}

		/**
		 * �����ӳػ��һ����������.��û�п��е������ҵ�ǰ������С��������� ������,�򴴽�������.��ԭ���Ǽ�Ϊ���õ����Ӳ�����Ч,�������ɾ��֮,
		 * Ȼ��ݹ�����Լ��Գ����µĿ�������.
		 */
		public synchronized Connection getConnection() {
			Connection con = null;
			// System.out.println(freeConnections.size());
			// System.out.println(checkedOut);
			// System.out.println(maxConn);
			if (freeConnections.size() > 0) {
				// ��ȡ�����е�һ����������
				con = freeConnections.firstElement();
				freeConnections.removeElementAt(0);
				try {
					if (con.isClosed()) {
						log("�����ӳ�" + name + "ɾ��һ����Ч����");
						// �ݹ�����Լ�,�����ٴλ�ȡ��������
						con = getConnection();
					}
				} catch (SQLException e) {
					log("�����ӳ�" + name + "ɾ��һ����Ч����");
					// �ݹ�����Լ�,�����ٴλ�ȡ��������
					con = getConnection();
				}
			} else if ((maxConn == 0) || (checkedOut < maxConn)) {
				con = newConnection();
			} else {
				// System.out.println(" ���ӳ�ȡ��,�ȴ�����!");
				isNullPool++;
			}
			if (con != null) {
				checkedOut++;
				sum++;
			}
			// testConn(con);
			return con;
		}

		/**
		 * �����ӳػ�ȡ��������.����ָ���ͻ������ܹ��ȴ����ʱ�� �μ�ǰһ��getConnection()����.
		 * 
		 * @param timeout
		 *                �Ժ���Ƶĵȴ�ʱ������
		 */
		public synchronized Connection getConnection(long timeout) {
			long startTime = new Date().getTime();
			Connection con;
			while ((con = getConnection()) == null) {
				try {
					wait(timeout);
				} catch (InterruptedException e) {
				}
				if ((new Date().getTime() - startTime) >= timeout) {
					// wait()���ص�ԭ���ǳ�ʱ
					return null;
				}
			}
			return con;
		}

		/**
		 * �ر���������
		 */
		public synchronized void release() {
			Enumeration<Connection> allConnections = freeConnections.elements();
			while (allConnections.hasMoreElements()) {
				Connection con = allConnections.nextElement();
				try {
					con.close();
					log("�ر����ӳ�" + name + "�е�һ������");
				} catch (SQLException e) {
					log(e, "�޷��ر����ӳ�" + name + "�е�����");
				}
			}
			freeConnections.removeAllElements();
		}

		/**
		 * �����µ�����
		 */
		private Connection newConnection() {
			Connection con = null;
			try {
				if (user == null) {
					con = DriverManager.getConnection(URL);
				} else {
					con = DriverManager.getConnection(URL, user, password);
				}
				max++;
				// log("���ӳ�" + name + "����һ���µ�����");
			} catch (SQLException e) {
				// ����ע��ʵ�����ݿ����ӵ�������������£���Ϊ���µ���������������û����ʾ���ӵ�ַ����
				log(e, "Oracle��������Ӧ,�޷���������URL������: " + URL);
				// System.out.println(" Oracle��������Ӧ!");
				isOracleErr++;
				return null;
			}
			return con;
		}

		public int getCurConns() {
			return checkedOut;
		}

		public int getSumConns() {
			return sum;
		}

		public int getMaxConns() {
			return max;
		}

		public int getNullPool() {
			return isNullPool;
		}

		public int getOracleErr() {
			return isOracleErr;
		}

		public void test() {
			System.out.println("\r\n\r\n--------------------vect.size="
					+ freeConnections.size() + "\r\n\r\n");
			for (int i = 0; i < freeConnections.size(); i++) {
				Connection conn = freeConnections.get(i);
				testConn(conn);
			}
		}

		public void testConn(Connection c) {
			try {
				if (c.isClosed()) {
					System.out.println("���ӳش��󣺵�ǰ����������:�������ƻ���");
				}

				Statement stm = c.createStatement();
				ResultSet rs = stm.executeQuery("select count(*) from xtszb");
				rs.next();
				rs.close();
				stm.close();
				// System.out.println(i+"="+rs.getInt(1));
			} catch (SQLException s) {
				System.out.println("���ӳش��󣺵�ǰ����������:" + s);
			}
		}
	}
}
