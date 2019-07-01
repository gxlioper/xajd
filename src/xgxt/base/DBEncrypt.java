package xgxt.base;

/*
×Ö·û´® DESede(3DES) ¼ÓÃÜ
*/
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;

public class DBEncrypt{

/*	private Properties properties;

	public Object getObject() throws Exception {
		return getProperties();
	}

	public Class getObjectType() {
		return java.util.Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties inProperties) {
		this.properties = inProperties;
		String originalUsername = properties.getProperty("user");
		String originalPassword = properties.getProperty("password");
		String originalJdbcUrl = properties.getProperty("jdbcUrl");
		if (originalUsername != null) {
			String newUsername = deEncryptUsername(originalUsername);
			properties.put("user", newUsername);
		}
		if (originalPassword != null) {
			String newPassword = deEncryptPassword(originalPassword);
			properties.put("password", newPassword);
		}
		if (originalJdbcUrl != null) {
			String newJdbcUrl = deEncryptJdbcUrl(originalJdbcUrl);
			properties.put("jdbcUrl", newJdbcUrl);
		}
	}

	private String deEncryptUsername(String originalUsername) {
		return dCode(originalUsername.getBytes());
	}
	
	private String deEncryptJdbcUrl(String originalJdbcUrl) {
		return dCode(originalJdbcUrl.getBytes());
	}

	private String deEncryptPassword(String originalPassword) {
		return dCode(originalPassword.getBytes());
	}*/

	public String eCode(String needEncrypt){
		byte result[] = null;
		try {
			Cipher enCipher = Cipher.getInstance("DES");
			javax.crypto.SecretKey key = Key.loadKey();
			enCipher.init(1, key);
			result = enCipher.doFinal(needEncrypt.getBytes());
			BASE64Encoder b = new BASE64Encoder();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			b.encode(result, bos);
			result = bos.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException("System doesn't support DES algorithm.");
		}
		return new String(result);
	}

	public String dCode(byte result[]){
		String s = null;
		try {
			Cipher deCipher = Cipher.getInstance("DES");
			deCipher.init(2, Key.loadKey());
			BASE64Decoder d = new BASE64Decoder();
			result = d.decodeBuffer(new String(result));
			byte strByte[] = deCipher.doFinal(result);
			s = new String(strByte);
		} catch (Exception e) {
			throw new IllegalStateException("System doesn't support DES algorithm.");
		}
		return s;
	}

	public static void main(String[] args){
//		String s = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.14.5.7)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.14.5.8)(PORT = 1521))"+
//    "(LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = zjlg) (FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 180) (DELAY = 5) ) ) )";
//		String x= "new_xgxt";
//    	String s = "jdbc:oracle:thin:@10.71.19.92:1521:orcl";
//    	String x = "lgxg";
//    	String y = "zfsoft";
		//String s ="jdbc:oracle:thin:@10.71.32.37:1521:devdb";
		String s ="jdbc:oracle:thin:@10.71.32.37:1521:test";
		String y = "usr_zf_xgxt";
		String x = "zfsoft_zjcm";
		//String s ="jdbc:oracle:thin:@10.71.32.174:1521:orcl";
		//String y = "xgxt_hjj";
		//String x = "test";
    	DBEncrypt p = new DBEncrypt();
    	String afterE = p.eCode(s);
    	System.out.println(afterE);
    	System.out.println(p.dCode("Kbs2u6NELkMD+i6RnR+aSdT76UUiSKIQWme1vaP9axmXIrc3lg7ezA==".getBytes()));
    	
		String a ="gnd8bFTenCxAHVzzzujcXQ==";
		String b = "Kbs2u6NELkMD+i6RnR+aSRYguMAm9SijvWC4YE2SLnIHkVsh0SdX85+Y60A4RnMl";
    	System.out.println(p.dCode(a.getBytes()));
    	System.out.println(p.dCode(b.getBytes()));
    }
}