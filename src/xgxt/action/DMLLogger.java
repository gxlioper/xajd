package xgxt.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

public class DMLLogger implements Runnable{
		private final int LOG_THRESHHOLD = 20;
		DAO dao;
		String DMLSql = "";//��Ӧ�Ĵ������
		String tableName;//Ҫ����ı�
		String primaryKey = "";//�����ֶ�
		String value;//������ֵ
		String[] newVals;//��ֵ����
		//HttpServletRequest request;
		String[] colList; //��
		String[] ovalues; //ԭֵ
		String cz = "";//�û����������룬�޸ģ�ɾ��
		String ip="";
		String clienttime="";
		String host="";
		String mac="";
		String yhm="";
	
		final static List<LogAttrabutes> logAttStack = new ArrayList<LogAttrabutes>(); 
		
		/**
		 * <font color=red>
		 * @param myDao
		 * @param DMLSql //��Ӧ�Ĵ������
		 * @param tableName //Ҫ����ı�
		 * @param newVals //Ҫ�޸��ֶε�ֵ
		 * @param request 
		 * </font>
		 */
		public DMLLogger(){
			
		}
		
		public void insertLog( String sql, 
		         		       String[] values,
		                       HttpServletRequest request){
			dao = DAO.getInstance();
			LogAttrabutes log = new LogAttrabutes();
			String ip = request.getRemoteAddr();
			log.setIp(ip);
			log.setClienttime(DateUtils.getTime());
			log.setHost(request.getRemoteHost());
			//log.setMac(dao.getMacAddressIP(ip));
			log.setMac((String)request.getSession().getAttribute("userMac"));
			log.setYhm((String)request.getSession().getAttribute("userName"));	
			log.setDMLSql(sql.toString());
			log.setNewVals(values);
			log.setCz(cz);
			log.setOvalues(ovalues);
			synchronized(this){
				logAttStack.add(log);
				if(DMLLogger.logAttStack.size() >= LOG_THRESHHOLD){
					new Thread(this).run();
				}
			}
		}
		
		public void insertLog( String sql, 
       		                   String[] values,
                               User user){
			dao = DAO.getInstance();
			LogAttrabutes log = new LogAttrabutes();
//			String ip = user.getIp();
			
			log.setIp(user.getIp());
			log.setClienttime(DateUtils.getTime());
			log.setHost(user.getHost());
			log.setMac(user.getUserMac());
			log.setYhm(user.getUserName());	
			log.setDMLSql(sql.toString());
			log.setNewVals(values);
			log.setCz(cz);
			log.setOvalues(ovalues);
			synchronized(this){
				logAttStack.add(log);
				if(DMLLogger.logAttStack.size() >= LOG_THRESHHOLD){
					new Thread(this).run();
				}
			}
		}

		public void run() {			
			logger();
		}
		
		/**
		 * <font color=red>
		 *  ��Ҫ������delete �� update
		 * @param tableName Ҫ����ı�
		 * @param primaryKey ���������Ϣ
		 * @param value ����ֵ
		 * @param cz �û��Ĳ���
		 * </font>
		 */
		public void setOldData(String tableName,String primaryKey,String value,String cz){
			dao = DAO.getInstance();
			this.tableName=tableName;
			this.primaryKey=primaryKey;
			this.value=value;
			try {
				this.cz = (cz+tableName+dao.getCNtableName(tableName));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			colList = dao.getColumnName("select * from " + tableName); 
			ovalues = dao.getOneRs("select * from " + tableName + " where " + primaryKey + "='" + value + "'", new String[]{}, colList);
		}
		
		
		
		/**
		 * <font>
		 * ��Ҫ������delete �� update
		 * @param sql ��ѯdml�������ͬ�����µ�ԭ���ļ�¼��sql����������ȡ�������ݣ�
		 *            ���sql����һ�������ľ��ӣ��У�����ռλ��
		 * @param tableName Ҫ����ı�
		 * @param cz �û��Ĳ���
		 * </font>
		 */
		public void setOldData(String sql,String tableName,String[] values,String cz){
			dao = DAO.getInstance();
			this.tableName=tableName;
			try {
				this.cz = (cz+tableName+dao.getCNtableName(tableName));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
//			this.primaryKey=primaryKey;
//			this.value=value;
			String whereClause = sql.substring(sql.indexOf("where"));
			colList = dao.getColumnName("select * from " + tableName);
			ovalues = dao.getOneRs("select * from " + tableName +" "+ whereClause, values, colList);
		}
		
			/**
		 * <font>
		 * ��Ҫ������delete �� update
		 * @param sql ��ѯdml�������ͬ�����µ�ԭ���ļ�¼��sql����������ȡ�������ݣ�
		 *            ���sql��һ�������ľ��ӣ�û�У�����ռλ��
		 * @param tableName Ҫ����ı�
		 * @param cz �û��Ĳ���
		 * </font>
		 */
		public void setOldData(String sql,String tableName,String cz){
			dao = DAO.getInstance();
			this.tableName=tableName;
			try {
				this.cz = (cz+tableName+dao.getCNtableName(tableName));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			String whereClause = sql.substring(sql.indexOf("where"));
			colList = dao.getColumnName("select * from " + tableName); 
			ovalues = dao.getOneRs("select * from " + tableName +" "+ whereClause,new String[]{}, colList);
		}
		
		/**
		 * <font color=red>
		 * ��Ҫ����insert���
		 * @param tableName
		 * @param cz
		 * </font>
		 */
		public void setOldData(String tableName,String cz){
			dao = DAO.getInstance();
			this.tableName=tableName;
			try {
				this.cz = (cz+tableName+dao.getCNtableName(tableName));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		/**
		 * @param myDao
		 * @param DMLSql
		 * @param tableName
		 * @param newVals
		 * @param cz
		 * @param request
		 */
		public void logger(){
			dao = DAO.getInstance();
			try{
				for(LogAttrabutes model : logAttStack){
					dao.writeLog(model.getDMLSql(), 
							     model.getNewVals(), 
							     model.getOvalues(),
							     model.getCz(),
							     model.getIp(),
							     model.getClienttime(),
							     model.getHost(), 
							     model.getMac(), 
							     model.getYhm());
				}
				logAttStack.clear();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public static final class LogAttrabutes {
			String ip;
			String clienttime;
			String host;
			String mac;
			String yhm;
			String tableName;
			String cz;
			String DMLSql;
			String[] newVals;
			String[] ovalues;
			
			public String getIp() {
				return ip;
			}
			public void setIp(String ip) {
				this.ip = ip;
			}
			public String getClienttime() {
				return clienttime;
			}
			public void setClienttime(String clienttime) {
				this.clienttime = clienttime;
			}
			public String getHost() {
				return host;
			}
			public void setHost(String host) {
				this.host = host;
			}
			public String getMac() {
				return mac;
			}
			public void setMac(String mac) {
				this.mac = mac;
			}
			public String getYhm() {
				return yhm;
			}
			public void setYhm(String yhm) {
				this.yhm = yhm;
			}
			public String[] getNewVals() {
				return newVals;
			}
			public void setNewVals(String[] newVals) {
				this.newVals = newVals;
			}
			public String[] getOvalues() {
				return ovalues;
			}
			public void setOvalues(String[] ovalues) {
				this.ovalues = ovalues;
			}
			public String getTableName() {
				return tableName;
			}
			public void setTableName(String tableName) {
				this.tableName = tableName;
			}
			public String getCz() {
				return cz;
			}
			public void setCz(String cz) {
				this.cz = cz;
			}
			public String getDMLSql() {
				return DMLSql;
			}
			public void setDMLSql(String sql) {
				DMLSql = sql;
			}
		}
}

