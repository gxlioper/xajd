/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-19 ����06:53:37 
 */  
package xgxt.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-1-19 ����06:53:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
