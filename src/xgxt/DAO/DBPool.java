package xgxt.DAO;
import javax.sql.DataSource;

public class DBPool {
	/***  WebLogic J N D I   ***/
//	private static final String DEFAULT_WLS_URL = "t3://127.0.0.1:7001";
//	private static final String DEFAULT_JNDI_FACTORY =
//		"weblogic.jndi.WLInitialContextFactory";
//	private static final String DEFAULT_USERNAME = "admin";
//	private static final String DEFAULT_PASSWORD = "12345678";
//	private static final String CATALOG_HOME = "java:comp/env";
//	private static DataSource pool;
//	static {
//		Context ctx = null;
//		try {
//			String sysConfig = JndiBase.getWebapplication(); 
//			//除了WebLogic之外的都用默认配置
//			if(!"weblogic".equalsIgnoreCase(sysConfig)){//sysConfig == null || "tomcat".equalsIgnoreCase(sysConfig)
//				ctx = (Context) new InitialContext().lookup(CATALOG_HOME);
//				pool = (DataSource)ctx.lookup(JndiBase.getJndi());
//				if(pool==null) 
//					System.err.println("'DBPool' is an unknown DataSource");
//			} else {
//				ctx = (Context)(getWebLogicJNDIContext());
//				pool = (DataSource)ctx.lookup(JndiBase.getJndi());
//				if(pool==null) 
//					System.err.println("'DBPool' is an unknown DataSource");
//			}
//			
//		} catch(NamingException ne) {
//			ne.printStackTrace();
//		}
//	}

	public static DataSource getPool() {    	    	
		return com.zfsoft.xgxt.base.db.DBPool.getPool();
	}
	
//	public static Context getWebLogicJNDIContext(){
//		Context ctx = null;
//		Properties   specprops = JndiBase.getProps();//读取配置文件中的信息
//		Properties   envproperties   =   null;   
//		try{   
//			envproperties = new Properties();   
//			envproperties.put(Context.INITIAL_CONTEXT_FACTORY,   
//					specprops.getProperty("DEFAULT_JNDI_FACTORY")!=null 
//					    ? specprops.getProperty("DEFAULT_JNDI_FACTORY")
//					    :DEFAULT_JNDI_FACTORY);   
//			envproperties.put(Context.PROVIDER_URL,              
//					specprops.getProperty("DEFAULT_WLS_URL")!=null 
//				    	? specprops.getProperty("DEFAULT_WLS_URL")
//				    			:DEFAULT_WLS_URL);   
//			envproperties.put(Context.SECURITY_PRINCIPAL,        
//					specprops.getProperty("DEFAULT_USERNAME")!=null 
//				        ? specprops.getProperty("DEFAULT_USERNAME")
//				        		:DEFAULT_USERNAME);   
//			envproperties.put(Context.SECURITY_CREDENTIALS,      
//					specprops.getProperty("DEFAULT_PASSWORD")!=null 
//				    	? specprops.getProperty("DEFAULT_PASSWORD")
//				    			:DEFAULT_PASSWORD);
//			ctx = new InitialContext(envproperties);   
//		}
//		catch(Exception   e)   {
//			System.out.println(e);
//		}
//		return ctx;
//	}
}