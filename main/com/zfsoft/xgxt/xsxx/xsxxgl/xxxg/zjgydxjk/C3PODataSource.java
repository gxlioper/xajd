/**    
 * �ļ�����C3PODataSource.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�Dec 15, 2010    
 * Copyright ������� Corporation 2010     
 * ��Ȩ����    
 *    
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.zjgydxjk;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import xgxt.base.DBEncrypt;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class C3PODataSource {

	public static ComboPooledDataSource comboPooledDataSource;
	private static C3PODataSource c3PODataSource;
	private static Logger logger = Logger.getLogger(C3PODataSource.class);
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config/dxfs");
	
	public static C3PODataSource getInstance() {
		if(c3PODataSource == null){
			c3PODataSource = new C3PODataSource();
		}
		return c3PODataSource;
    }
	
	private C3PODataSource(){
		logger.info("��ȡ�����ݿ����ӣ�");
		comboPooledDataSource = new ComboPooledDataSource();
		try {
			DBEncrypt encrypt = new DBEncrypt();
			comboPooledDataSource.setDriverClass(resourceBundle.getString("database.driverClass"));
			comboPooledDataSource.setJdbcUrl(encrypt.dCode(resourceBundle.getString("database.jdbcUrl").getBytes()));
			comboPooledDataSource.setUser(encrypt.dCode(resourceBundle.getString("database.user").getBytes()));
			logger.info(encrypt.dCode(resourceBundle.getString("database.user").getBytes()));
			comboPooledDataSource.setPassword(encrypt.dCode(resourceBundle.getString("database.password").getBytes()));
			comboPooledDataSource.setMinPoolSize(Integer.parseInt(resourceBundle
					.getString("database.minPoolSize")));
			comboPooledDataSource.setMaxPoolSize(Integer.parseInt(resourceBundle
					.getString("database.maxPoolSize")));
			comboPooledDataSource.setInitialPoolSize(Integer.parseInt(resourceBundle
					.getString("database.initialPoolSize")));
			comboPooledDataSource.setMaxIdleTime(Integer.parseInt(resourceBundle
					.getString("database.maxIdleTime")));
			comboPooledDataSource.setAcquireIncrement(Integer.parseInt(resourceBundle
					.getString("database.acquireIncrement")));
			comboPooledDataSource.setAcquireRetryAttempts(Integer.parseInt(resourceBundle
					.getString("database.acquireRetryAttempts")));
			comboPooledDataSource.setAcquireRetryDelay(Integer.parseInt(resourceBundle
					.getString("database.acquireRetryDelay")));
			comboPooledDataSource.setBreakAfterAcquireFailure(Boolean.getBoolean(resourceBundle
					.getString("database.breakAfterAcquireFailure")));
			comboPooledDataSource.setIdleConnectionTestPeriod(Integer.parseInt(resourceBundle
					.getString("database.idleConnectionTestPeriod")));
			comboPooledDataSource.setTestConnectionOnCheckout(Boolean.getBoolean(resourceBundle
					.getString("database.testConnectionOnCheckout")));
			logger.info("��ȡ�����ݿ����ӳɹ���");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			logger.error("���ӳ��������쳣",e);
		}		
	}
	
	public static void setC3PODataSource() throws PropertyVetoException {
		if (comboPooledDataSource == null) {
			comboPooledDataSource = new ComboPooledDataSource();
			DBEncrypt encrypt = new DBEncrypt();
			comboPooledDataSource.setDriverClass(resourceBundle.getString("database.driverClass"));
			comboPooledDataSource.setJdbcUrl(encrypt.dCode(resourceBundle.getString("database.jdbcUrl").getBytes()));
			comboPooledDataSource.setUser(encrypt.dCode(resourceBundle.getString("database.user").getBytes()));
			logger.info(encrypt.dCode(resourceBundle.getString("database.user").getBytes()));
			comboPooledDataSource.setPassword(encrypt.dCode(resourceBundle.getString("database.password").getBytes()));
			comboPooledDataSource.setMinPoolSize(Integer.parseInt(resourceBundle
					.getString("database.minPoolSize")));
			comboPooledDataSource.setMaxPoolSize(Integer.parseInt(resourceBundle
					.getString("database.maxPoolSize")));
			comboPooledDataSource.setInitialPoolSize(Integer.parseInt(resourceBundle
					.getString("database.initialPoolSize")));
			comboPooledDataSource.setMaxIdleTime(Integer.parseInt(resourceBundle
					.getString("database.maxIdleTime")));
			comboPooledDataSource.setAcquireIncrement(Integer.parseInt(resourceBundle
					.getString("database.acquireIncrement")));
			comboPooledDataSource.setAcquireRetryAttempts(Integer.parseInt(resourceBundle
					.getString("database.acquireRetryAttempts")));
			comboPooledDataSource.setAcquireRetryDelay(Integer.parseInt(resourceBundle
					.getString("database.acquireRetryDelay")));
			comboPooledDataSource.setBreakAfterAcquireFailure(Boolean.getBoolean(resourceBundle
					.getString("database.breakAfterAcquireFailure")));
			comboPooledDataSource.setIdleConnectionTestPeriod(Integer.parseInt(resourceBundle
					.getString("database.idleConnectionTestPeriod")));
			comboPooledDataSource.setTestConnectionOnCheckout(Boolean.getBoolean(resourceBundle
					.getString("database.testConnectionOnCheckout")));
		}
	}
	
	public Connection getConnection() {  
		try {
	        return comboPooledDataSource.getConnection();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	logger.error("���ӳ����Ӵ��쳣",e);
	    }
	    return null;
	}
	    
    protected void finalize() throws Throwable {
          DataSources.destroy(comboPooledDataSource);
          super.finalize();
    }
}
