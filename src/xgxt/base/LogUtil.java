package xgxt.base;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;

public class LogUtil {
	
	private static int            LOGTHRESHOLD = 10;//日志处理阀门
	private static List<String[]> logInfo = new ArrayList<String[]>();//日志缓存池
	
	
	
	public static void maniLogs(String[] info,DAO dao,String sql){
		
		//添加日志信息
		logInfo.add(info);
		//达到日志处理阀值，就批量处理日志到数据库
		if(logInfo.size() > LOGTHRESHOLD){
			//---------edit by 鲁大--2011.5.4---begin----------
			//new LogManiSlave(logInfo,dao,sql).run();
			new Thread(new LogManiSlave(logInfo,dao,sql)).start();
			//---------------end---------------------
			//清理已经处理过的日志
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
