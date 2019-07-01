package xgxt.action;

import java.util.Properties;

public class JndiBase {
	private static String Jndi;
	private static String webapplication; //tomcat,weblogic,websphere
	private static Properties props = new Properties();
	
	
	public static String getJndi() {
		return Jndi;
	}

	public static void setJndi(String jndi) {
		Jndi = jndi;
	}

	public static String getWebapplication() {
		return webapplication;
	}

	public static void setWebapplication(String webapplication) {
		JndiBase.webapplication = webapplication;
	}

	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		JndiBase.props = props;
	}
	
	
}
