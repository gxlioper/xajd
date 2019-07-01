/*
 * 创建日期 2006-5-13
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * 管理类DBConnectionManager支持对一个或多个由属性文件定义的数据库连接
 * 池的访问.客户程序可以调用getInstance()方法访问本类的唯一实例.
 */
public class DBConnectionManager {
	static private DBConnectionManager instance; // 唯一实例

	static private int clients;

	private Vector<Driver> drivers = new Vector<Driver>();

	public PrintWriter log;

	public static Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

	/**
	 * 返回唯一实例.如果是第一次调用此方法,则创建实例
	 * 
	 * @return DBConnectionManager 唯一实例
	 */
	static synchronized public DBConnectionManager getInstance() {
		// 为了能够在控制台和Tomcat中都能使用连接池，在配置文件中初始化了两次
		// 第一次初始化默认的配置参数，给控制台使用，
		// 第二次初始化Tomcat中的配置参数，
		// 为了在第二次能够重新进行参数读取，此处注掉
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
	 * 建构函数私有以防止其它对象创建本类实例
	 */
	public DBConnectionManager() {
		init();
	}

	/**
	 * 将连接对象返回给由名字指定的连接池
	 * 
	 * @param name
	 *                在属性文件中定义的连接池名字
	 * @param con
	 *                连接对象
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/**
	 * 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数 限制,则创建并返回新连接
	 * 
	 * @param name
	 *                在属性文件中定义的连接池名字
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制, 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	 * 
	 * @param name
	 *                连接池名字
	 * @param time
	 *                以毫秒计的等待时间
	 * @return Connection 可用连接或null
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
	 * 关闭所有连接,撤销驱动程序的注册
	 */
	public synchronized void release() {
		// 等待直到最后一个客户程序调用
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
				log("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
			} catch (SQLException e) {
				log(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * 根据指定属性创建连接池实例.
	 * 
	 * @param props
	 *                连接池属性
	 */
	private void createPools() {
		String poolName = Configuration.ConnectionPoolName;
		String url = Configuration.DB_URL;
		if (url == null) {
			log("没有为连接池" + poolName + "指定URL");
			return;
		}
		String user = Configuration.DB_USERNAME;
		String password = Configuration.DB_PASSWORD;
		String maxconn = Configuration.DB_MAXCONNNUM;
		int max;
		try {
			max = Integer.valueOf(maxconn).intValue();
		} catch (NumberFormatException e) {
			log("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
			max = 0;
		}
		DBConnectionPool pool = new DBConnectionPool(poolName, url, user,
				password, max);
		pools.put(poolName, pool);
		log("成功创建连接池" + poolName);

	}

	/**
	 * 读取属性完成初始化
	 */
	private void init() {
		String logFile = Configuration.DB_LOGFILE;
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("无法打开日志文件: " + logFile);
			log = new PrintWriter(System.err);
		}

		loadDrivers();
		createPools();
	}

	/**
	 * 装载和注册所有JDBC驱动程序
	 * 
	 * @param props
	 *                属性
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
				log("成功注册JDBC驱动程序" + driverClassName);
			} catch (Exception e) {
				log("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
				System.out.println("\r\n无法注册JDBC驱动程序: " + driverClassName
						+ ", 错误: " + e);
			}
		}
	}

	/**
	 * 将文本信息写入日志文件
	 */
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	/**
	 * 将文本信息与异常写入日志文件
	 */
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}

	/**
	 * 此内部类定义了一个连接池.它能够根据要求创建新连接,直到预定的最 大连接数为止.在返回连接给客户程序之前,它能够验证连接的有效性.
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
		 * 创建新的连接池
		 * 
		 * @param name
		 *                连接池名字
		 * @param URL
		 *                数据库的JDBC URL
		 * @param user
		 *                数据库帐号,或 null
		 * @param password
		 *                密码,或 null
		 * @param maxConn
		 *                此连接池允许建立的最大连接数
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
		 * 将不再使用的连接返回给连接池
		 * 
		 * @param con
		 *                客户程序释放的连接
		 */
		public synchronized void freeConnection(Connection con) {
			// testConn(con);
			if (con != null) {
				// 将指定连接加入到向量末尾
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
		 * 从连接池获得一个可用连接.如没有空闲的连接且当前连接数小于最大连接 数限制,则创建新连接.如原来登记为可用的连接不再有效,则从向量删除之,
		 * 然后递归调用自己以尝试新的可用连接.
		 */
		public synchronized Connection getConnection() {
			Connection con = null;
			// System.out.println(freeConnections.size());
			// System.out.println(checkedOut);
			// System.out.println(maxConn);
			if (freeConnections.size() > 0) {
				// 获取向量中第一个可用连接
				con = freeConnections.firstElement();
				freeConnections.removeElementAt(0);
				try {
					if (con.isClosed()) {
						log("从连接池" + name + "删除一个无效连接");
						// 递归调用自己,尝试再次获取可用连接
						con = getConnection();
					}
				} catch (SQLException e) {
					log("从连接池" + name + "删除一个无效连接");
					// 递归调用自己,尝试再次获取可用连接
					con = getConnection();
				}
			} else if ((maxConn == 0) || (checkedOut < maxConn)) {
				con = newConnection();
			} else {
				// System.out.println(" 连接池取空,等待回收!");
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
		 * 从连接池获取可用连接.可以指定客户程序能够等待的最长时间 参见前一个getConnection()方法.
		 * 
		 * @param timeout
		 *                以毫秒计的等待时间限制
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
					// wait()返回的原因是超时
					return null;
				}
			}
			return con;
		}

		/**
		 * 关闭所有连接
		 */
		public synchronized void release() {
			Enumeration<Connection> allConnections = freeConnections.elements();
			while (allConnections.hasMoreElements()) {
				Connection con = allConnections.nextElement();
				try {
					con.close();
					log("关闭连接池" + name + "中的一个连接");
				} catch (SQLException e) {
					log(e, "无法关闭连接池" + name + "中的连接");
				}
			}
			freeConnections.removeAllElements();
		}

		/**
		 * 创建新的连接
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
				// log("连接池" + name + "创建一个新的连接");
			} catch (SQLException e) {
				// 以下注释实在数据库连接调试正常的情况下，因为以下的情况概率最大，所以没有提示连接地址错误
				log(e, "Oracle来不及响应,无法创建下列URL的连接: " + URL);
				// System.out.println(" Oracle来不及响应!");
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
					System.out.println("连接池错误：当前连接有问题:该连接破坏！");
				}

				Statement stm = c.createStatement();
				ResultSet rs = stm.executeQuery("select count(*) from xtszb");
				rs.next();
				rs.close();
				stm.close();
				// System.out.println(i+"="+rs.getInt(1));
			} catch (SQLException s) {
				System.out.println("连接池错误：当前连接有问题:" + s);
			}
		}
	}
}
