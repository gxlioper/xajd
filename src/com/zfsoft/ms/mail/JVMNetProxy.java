package com.zfsoft.ms.mail;

public class JVMNetProxy {

    public static final String JVM_NET_PROXY_ENABLE = "proxySet";
    public static final String JVM_NET_PROXY_TYPE = "proxyType";
    public static final String JVM_NET_PROXY_USER = "proxyUser";
    public static final String JVM_NET_PROXY_PASSWORD = "proxyPassword";

    /**  һ�����
     *
     * proxySet �Ǹ�boolean���͵ģ���������Ϊtrue����false��true����ʹ�ô����������
     * proxyHost �Ǵ����������IP��ַ
     * proxyPort �Ǵ���������Ķ˿ڵ�ַ
     *
     * ��ʽһ��
     *
     * set "JAVA_OPTS = -DproxySet=true -DproxyHost=someProxyHost -DproxyPort=someProxyPort"
     *
     * ��ʽ����
     *
     * System.getProperties().setProperty("proxySet","true");
     * System.getProperties().setProperty("proxyHost","someProxyHost");
     * System.getProperties().setProperty("proxyPort","someProxyPort");
     * System.getProperties().setProperty("nonProxyHosts","nonProxyHosts");
     *
     */

    public static final String JVM_PROXY_HOST = "proxyHost";
    public static final String JVM_PROXY_NON_HOSTS = "nonProxyHosts";
    public static final String JVM_PROXY_PORT = "proxyPort";


    /**  Http����
     *
     * proxySet �Ǹ�boolean���͵ģ���������Ϊtrue����false��true����ʹ�ô����������
     * http.proxyHost �Ǵ����������IP��ַ
     * http.proxyPort �Ǵ���������Ķ˿ڵ�ַ
     * http.proxyUser �Ǵ�����������˻�
     * http.proxyPassword �Ǵ�����������˻�����
     * http.nonProxyHosts �ǲ���Ҫͨ��������������ʵ�����IP��ַ������ʹ��*ͨ����������ַ��|�ָ�
     *
     * ��ʽһ��
     *
     * set "JAVA_OPTS = -DproxySet=true -Dhttp.proxyHost=someProxyHost -Dhttp.proxyPort=someProxyPort"
     *
     * ��ʽ����
     *
     * System.getProperties().setProperty("proxySet","true");
     * System.getProperties().setProperty("http.proxyHost","someProxyHost");
     * System.getProperties().setProperty("http.proxyPort","someProxyPort");
     * System.getProperties().setProperty("http.nonProxyHosts","someNonProxyHosts");
     * System.getProperties().setProperty("http.proxyUser","someUserName");
     * System.getProperties().setProperty("http.proxyPassword","somePassword");
     *
     */

    public static final String JVM_HTTP_PROXY_HOST = "http.proxyHost";
    public static final String JVM_HTTP_PROXY_PORT = "http.proxyPort";
    public static final String JVM_HTTP_PROXY_USER = "http.proxyUser";
    public static final String JVM_HTTP_PROXY_PASSWORD = "http.proxyPassword";
    public static final String JVM_HTTP_PROXY_NON_HOSTS = "http.nonProxyHosts";

    /**
     *
     * Https���ð�ȫ����ʹ�õĴ����������ַ��˿ڣ���û��https.nonProxyHosts���ԣ�������http.nonProxyHosts �����õĹ�����ʣ�
     *
     * proxySet �Ǹ�boolean���͵ģ���������Ϊtrue����false��true����ʹ�ô����������
     * https.proxyHost �Ǵ����������IP��ַ
     * https.proxyPort �Ǵ���������Ķ˿ڵ�ַ
     *
     * ��ʽһ��
     *
     * set "JAVA_OPTS = -DproxySet=true -Dhttps.proxyHost=someProxyHost -Dhttps.proxyPort=someProxyPort"
     *
     * ��ʽ����
     *
     * System.getProperties().setProperty("proxySet","true");
     * System.getProperties().setProperty("https.proxyHost","someProxyHost");
     * System.getProperties().setProperty("https.proxyPort","someProxyPort");
     *
     */

    public static final String JVM_HTTPS_PROXY_PROXYHOST = "https.proxyHost";
    public static final String JVM_HTTPS_PROXY_PROXYPORT = "https.proxyPort";

    /**
     *
     * FTP������������������˿��Լ�����Ҫʹ��FTP���������������
     *
     * proxySet �Ǹ�boolean���͵ģ���������Ϊtrue����false��true����ʹ�ô����������
     * ftp.proxyHost �Ǵ����������IP��ַ
     * ftp.proxyPort �Ǵ���������Ķ˿ڵ�ַ
     * ftp.nonProxyHosts �ǲ���Ҫͨ��������������ʵ�����IP��ַ������ʹ��*ͨ����������ַ��|�ָ�
     *
     * ��ʽһ��
     *
     * set "JAVA_OPTS = -DproxySet=true -Dftp.proxyHost=someProxyHost -Dftp.proxyPort=someProxyPort -Dftp.nonProxyHosts=someNonProxyHosts"
     *
     * ��ʽ����
     *
     * System.getProperties().setProperty("proxySet","true");
     * System.getProperties().setProperty("ftp.proxyHost","someProxyHost");
     * System.getProperties().setProperty("ftp.proxyPort","someProxyPort");
     * System.getProperties().setProperty("ftp.nonProxyHosts","someNonProxyHosts");
     *
     */

    public static final String JVM_FTP_PROXY_PROXYHOST = "ftp.proxyHost";
    public static final String JVM_FTP_PROXY_PROXYPORT = "ftp.proxyPort";
    public static final String JVM_FTP_PROXY_NON_HOSTS = "ftp.nonProxyHosts";

    /**
     * Socks����������ĵ�ַ��˿�
     *
     * proxySet �Ǹ�boolean���͵ģ���������Ϊtrue����false��true����ʹ�ô����������
     * socksProxyHost �Ǵ����������IP��ַ
     * socksProxyPort �Ǵ���������Ķ˿ڵ�ַ
     *
     * ��ʽһ��
     *
     * set "JAVA_OPTS = -DproxySet=true -DsocksProxyHost=someProxyHost -DsocksProxyPort=someProxyPort"
     *
     * ��ʽ����
     *
     * System.getProperties().setProperty("proxySet","true");
     * System.getProperties().setProperty("socksProxyHost","someProxyHost");
     * System.getProperties().setProperty("socksProxyPort","someProxyPort");
     *
     */

    public static final String JVM_SOCKS_PROXY_HOST = "socksProxyHost";
    public static final String JVM_SOCKS_PROXY_PORT = "socksProxyPort";


}
