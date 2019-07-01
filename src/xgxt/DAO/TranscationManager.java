/**
 * @部门:学工产品事业部
 * @日期：2017-1-19 下午06:53:37 
 */  
package xgxt.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-1-19 下午06:53:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TranscationManager {
	
	/**
	 * 
	 */
	protected static final ThreadLocal<Connection> thradlocalConnection = new ThreadLocal<Connection>();
	
	protected DataSource dataSource;
	private static TranscationManager transcationManager = new TranscationManager();
	public TranscationManager(){
		this.dataSource=DBPool.getPool();
	}
	public static TranscationManager getInstance() {
		return transcationManager;
		
	}
	public Connection getConnection() throws SQLException {
		Connection connection = thradlocalConnection.get();
		if(connection == null||connection.isClosed()){
			System.out.print("test<====>connection=");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			thradlocalConnection.set(connection);
		}
		return connection;
	}
	
	public void commit(){
		try {
			if (thradlocalConnection.get() != null && !thradlocalConnection.get().isClosed()) {
				thradlocalConnection.get().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void rollback(){
		try {
			if (thradlocalConnection.get() != null && !thradlocalConnection.get().isClosed()) {
				thradlocalConnection.get().rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		System.out.print("close<===>connection");
		try {
			if (thradlocalConnection.get() != null && !thradlocalConnection.get().isClosed()) {
				thradlocalConnection.get().rollback();
				thradlocalConnection.get().setAutoCommit(true);
				thradlocalConnection.get().close();
				thradlocalConnection.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
