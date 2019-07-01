package xgxt.base;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;

public class LogUtil {
	
	private static int            LOGTHRESHOLD = 10;//��־������
	private static List<String[]> logInfo = new ArrayList<String[]>();//��־�����
	
	
	
	public static void maniLogs(String[] info,DAO dao,String sql){
		
		//�����־��Ϣ
		logInfo.add(info);
		//�ﵽ��־����ֵ��������������־�����ݿ�
		if(logInfo.size() > LOGTHRESHOLD){
			//---------edit by ³��--2011.5.4---begin----------
			//new LogManiSlave(logInfo,dao,sql).run();
			new Thread(new LogManiSlave(logInfo,dao,sql)).start();
			//---------------end---------------------
			//�����Ѿ����������־
			logInfo.clear();
		}
		
	}
	
	
	private static class LogManiSlave implements Runnable{
		
		private List<String[]> subLogInfo = new ArrayList<String[]>();
		private DAO dao ;
		private String sql;
		
		public LogManiSlave(List<String[]> subLogInfo,DAO dao,String sql){
			this.subLogInfo = subLogInfo;
			this.dao        = dao;
			this.sql        = sql;
		} 
		
		public void run() {
			for(String[] oneLog : subLogInfo){
				try {
					this.dao.runUpdate(sql, oneLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
