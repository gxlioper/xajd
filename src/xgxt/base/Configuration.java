/*
 * 创建日期 2006-5-13
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

/**
 * @author bat_zzj
 * 
 *  要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Configuration {

	/**
	 * 连接池配置
	 */
	public static String ConnectionPoolFor = "";

	public static String ConnectionPoolName = "";

	public static String DB_URL = "";

	public static String DB_JDBCDRIVER = "";

	public static String DB_USERNAME = "";

	public static String DB_PASSWORD = "";

	public static String DB_MAXCONNNUM = "500";

	public static String DB_LOGFILE = "c:\\log.txt";

	public static String PRO_PATH = "";

	public static String FILE_UPLOAD_DIR = "";

	public static DBConnectionManager connMgr = DBConnectionManager
	.getInstance();

}
