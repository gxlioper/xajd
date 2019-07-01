/**
 * @部门:学工产品事业部
 * @日期：2013-6-6 下午07:35:21 
 */  
package com.zfsoft.xgxt.base.db;

import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import javax.sql.DataSource;
import xgxt.base.NewComboPooledDataSource;




/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 数据库连接池 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-6 下午07:35:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
		
		//连接池中保留的最大连接数。Default: 15
		dataSource.setMaxPoolSize(Integer.valueOf(resource.getString(maxPoolSize)));
		//连接池中保留的最小连接数。
		dataSource.setMinPoolSize(Integer.valueOf(resource.getString(minPoolSize)));
		//初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3
		dataSource.setInitialPoolSize(Integer.valueOf(resource.getString(initialPoolSize)));
		//#最大空闲时间,1800秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0
		dataSource.setMaxIdleTime(Integer.valueOf(resource.getString(maxIdleTime)));
		//#当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3
		dataSource.setAcquireIncrement(Integer.valueOf(resource.getString(acquireIncrement)));
		//#每1800秒检查所有连接池中的空闲连接。Default: 0
		dataSource.setIdleConnectionTestPeriod(Integer.valueOf(resource.getString(idleConnectionTestPeriod)));
		//#定义在从数据库获取新连接失败后重复尝试的次数
		dataSource.setAcquireRetryAttempts(Integer.valueOf(resource.getString(acquireRetryAttempts)));
		dataSource.setBreakAfterAcquireFailure(true);
	}
	
	private DBPool(){
		
	}
	
	
	public static DataSource getPool(){
		
		return dataSource;
	}
	
}
