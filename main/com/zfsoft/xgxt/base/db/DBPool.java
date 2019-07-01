/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-6 ����07:35:21 
 */  
package com.zfsoft.xgxt.base.db;

import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import javax.sql.DataSource;
import xgxt.base.NewComboPooledDataSource;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: ���ݿ����ӳ� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-6 ����07:35:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DBPool {
	
	private static NewComboPooledDataSource dataSource = null;
	private static String driverClass = "db.driverClass";
	private static String jdbcUrl = "db.jdbcUrl";
	private static String user = "db.user";
	private static String password = "db.password";
	private static String maxPoolSize = "db.maxPoolSize";
	private static String minPoolSize = "db.minPoolSize";
	private static String initialPoolSize = "db.initialPoolSize";
	private static String maxIdleTime = "db.maxIdleTime";
	private static String acquireIncrement = "db.acquireIncrement";
	private static String idleConnectionTestPeriod = "db.idleConnectionTestPeriod";
	private static String acquireRetryAttempts = "db.acquireRetryAttempts";
	
	
	static {
		
		ResourceBundle resource = ResourceBundle.getBundle("config/db");
		
		dataSource = new NewComboPooledDataSource();
		//PropertiesLoaderSupport
		try {
			dataSource.setDriverClass(resource.getString(driverClass));
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		dataSource.setJdbcUrl(resource.getString(jdbcUrl));
		dataSource.setUser(resource.getString(user));
		dataSource.setPassword(resource.getString(password));
		dataSource.setAutoCommitOnClose(false);
		
		//���ӳ��б����������������Default: 15
		dataSource.setMaxPoolSize(Integer.valueOf(resource.getString(maxPoolSize)));
		//���ӳ��б�������С��������
		dataSource.setMinPoolSize(Integer.valueOf(resource.getString(minPoolSize)));
		//��ʼ��ʱ��ȡ����������ȡֵӦ��minPoolSize��maxPoolSize֮�䡣Default: 3
		dataSource.setInitialPoolSize(Integer.valueOf(resource.getString(initialPoolSize)));
		//#������ʱ��,1800����δʹ�������ӱ���������Ϊ0������������Default: 0
		dataSource.setMaxIdleTime(Integer.valueOf(resource.getString(maxIdleTime)));
		//#�����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ����������Default: 3
		dataSource.setAcquireIncrement(Integer.valueOf(resource.getString(acquireIncrement)));
		//#ÿ1800�����������ӳ��еĿ������ӡ�Default: 0
		dataSource.setIdleConnectionTestPeriod(Integer.valueOf(resource.getString(idleConnectionTestPeriod)));
		//#�����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ���
		dataSource.setAcquireRetryAttempts(Integer.valueOf(resource.getString(acquireRetryAttempts)));
		dataSource.setBreakAfterAcquireFailure(true);
	}
	
	private DBPool(){
		
	}
	
	
	public static DataSource getPool(){
		
		return dataSource;
	}
	
}
