/*
 * �������� 2006-5-13
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

/**
 * @author bat_zzj
 * 
 *  Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Configuration {

	/**
	 * ���ӳ�����
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
