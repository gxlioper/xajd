package xgxt.daoActionLogic;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.DMLLogger;
import xgxt.base.reqestuser.UserInfo;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * @author tao this class is made for standard operation including
 *         addtion,delete and modification+
 *         
 *         
 */

public class StandardOperation {
	
	private static  UserInfo user;
	
	public static UserInfo getUser() {
		return user;
	}

	public synchronized static void setUser(UserInfo user) {
		StandardOperation.user = user;
	}

	/**
	 * @param tableName
	 *            is the table that is going to be inserted
	 * @param columns
	 *            are the target columns
	 * @param values
	 *            are the values coresponding to the columns respectively
	 */

	public synchronized static boolean insert(String tableName,String[] columns,String[] values, HttpServletRequest request){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName);
		sql.append("( ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "" : ",");
		}
		sql.append(" ) values( ");
		for (int i = 0; i < values.length; i++) {
			if (i == (values.length - 1))
				sql.append("?");
			else
				sql.append("?,");
		}
		sql.append(" )");
		boolean rs = false;
		try {
			rs = dao.runUpdate(sql.toString(), values);
			if(rs){
				DMLLogger dmlLogger = new DMLLogger();
				dmlLogger.setOldData(tableName, "insert :");
				dmlLogger.insertLog(sql.toString(), values, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 与insert方法的生成sql的功能相同，主要就是用insert方法的生成sql来返回生成的sql
	 * */
	public static synchronized String insertSql(String tableName,String[] columns,String[] values, HttpServletRequest request){
		boolean[] rs = Check_Input_Value.checkArrVal(values);
		for(int i=0;i<rs.length;i++){
			if(!rs[i]){
				return null;
			}
		}
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName);
		sql.append("( ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "" : ",");
		}
		sql.append(" ) values( ");
		for (int i = 0; i < values.length; i++) {
			sql.append((i == (values.length - 1))? "'"+values[i]+"'" : "'"+values[i]+"',");
		}
		sql.append(" )");
		try {
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "insert :");
			dmlLogger.insertLog(sql.toString(), values, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql.toString();
	}
	
	/**
	 * @param tableName
	 *            is the table that is going to be inserted
	 * @param columns
	 *            are the target columns
	 * @param values
	 *            are the values coresponding to the columns respectively
	 */
	public synchronized static boolean insert2(String tableName, String[] columns,
			String[] values, String zdhs_zd, String zdhz_value,HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName);
		sql.append("( ");
		if (zdhs_zd != null && !zdhs_zd.equalsIgnoreCase("")) {
			sql.append(zdhs_zd + ",");
		}
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "" : ",");
		}
		sql.append(" ) values( ");
		if (zdhz_value != null && !zdhz_value.equalsIgnoreCase("")) {
			sql.append(zdhz_value + ",");
		}
		for (int i = 0; i < values.length; i++) {
			if (i == (values.length - 1))
				sql.append("?");
			else
				sql.append("?,");
		}
		sql.append(" )");

		boolean rs = false;
		try {
			rs = dao.runUpdate(sql.toString(), values);
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "insert :");
			dmlLogger.insertLog(sql.toString(), values, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @param tableName
	 *            操作的表
	 * @param dgcolumns
	 *            表字段列表
	 * @param dgvalues
	 *            表字段值数据列表
	 * @return true if it works well other false
	 * @throws Exception
	 *             <srong>you must be careful when you use this method,unless
	 *             you truely want to delete the whole table ,you must give the
	 *             primaryKey and the value to designate the records</strong>
	 */
	public synchronized  static boolean delete(String tableName, String[] dgcolumns,
			String[] dgvalues, HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		boolean del = false;
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		if (dgcolumns != null || dgcolumns.length != 0) {
			for (int i = 0; i < dgcolumns.length; i++) {
				sql.append(dgcolumns[i]);
				sql.append("='");
				if (Check_Input_Value.check(dgvalues[i]) == 1) {
					sql.append(dgvalues[i]);
				}
				sql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
			}
		}
//		取老数据
		dao.getColumnName("select * from " + tableName); 
		StringBuffer oldsql = new StringBuffer();
		oldsql.append("select * from ");
		oldsql.append(tableName);
		oldsql.append(" where ");
		if (dgcolumns != null || dgcolumns.length != 0) {
			for (int i = 0; i < dgcolumns.length; i++) {
				oldsql.append(dgcolumns[i]);
				oldsql.append("='");
				if (Check_Input_Value.check(dgvalues[i]) == 1) {
					oldsql.append(dgvalues[i]);
				}
				oldsql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
			}
		}
		String []newVals=null;		
		del = dao.runUpdate(sql.toString(), new String[] {});
		
		if(del){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(oldsql.toString(),tableName,"delete :");
			dmlLogger.insertLog(sql.toString(), newVals, request);
		}
		return del;
	}

	
	
	
	/**
	 * @param tableName
	 *            it's the table where you want to delete records
	 * @param primaryKey
	 *            is the primary key of the table given by the name tableName
	 * @param value
	 *            is the primary key value to identify the records to be deleted
	 *            <br>
	 *            <font color='red'>你必须提供primarykey and
	 *            value以确定要删除的记录,否则你将删除整个表的数据</font>
	 * @return true if it works well other false
	 * @throws Exception
	 *             <srong>you must be careful when you use this method,unless
	 *             you truely want to delete the whole table ,you must give the
	 *             primaryKey and the value to designate the records</strong>
	 * 
	 */
	public synchronized static boolean delete(String tableName, String primaryKey,
			String value, HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		boolean del = false;
		sql.append("delete from ");
		sql.append(tableName);
		if (primaryKey != null || primaryKey.trim().length() > 0) {
			sql.append(" where ");
			sql.append(primaryKey);
			sql.append("='");
			sql.append(value);
			sql.append("'");
		}
//		取老数据
		String []newVals=null;
		del = dao.runUpdate(sql.toString(), new String[] {});
		if(del){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, primaryKey, value,"delete :");
			dmlLogger.insertLog(sql.toString(), newVals, request);
			//request.setAttribute("msg", "删除成功！");
		}
		return del;
	}

	
	/**
	 * @param tableName 操作的表
	 * @param primaryKey  操作表的主键
	 * @param value			操作表的主键值
	 * @param notInCol		操作表中no in 的字段
	 * @param notInVal		操作表中no in 的字段值
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public synchronized static boolean delete(String tableName, String primaryKey,
			String value, String notInCol, String[] notInVal, HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		boolean del = false;
		sql.append("delete from ");
		sql.append(tableName);
		if (primaryKey != null || primaryKey.trim().length() > 0) {
			sql.append(" where ");
			sql.append(primaryKey);
			sql.append("='");
			sql.append(value);
			sql.append("'");
		}
		if((null != notInCol) && (!"".equalsIgnoreCase(notInCol.trim()))){
			sql.append(" and ");
			sql.append(notInCol);
			sql.append(" not in (");
			if (notInVal.length > 0) {
				int i;
				for (i = 0; i < notInVal.length - 1; i++) {
					sql.append("'");
					sql.append(notInVal[i]);
					sql.append("',");
				}
				sql.append("'");
				sql.append(notInVal[i]);
				sql.append("'");
			}
			sql.append(")");
		}
//		取老数据
		String []newVals=null;
		del = dao.runUpdate(sql.toString(), new String[] {});
		if(del){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, primaryKey, value,"delete :");
			dmlLogger.insertLog(sql.toString(), newVals, request);
		}
		return del;
	}
	
	/**
	 * @param tableName 操作的表
	 * @param sql		操作sql
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public synchronized static boolean delete(String sql,String tableName, HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		boolean del = false;
		String []newVals=null;
		del = dao.runUpdate(sql, new String[] {});
		if(del){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(sql, tableName,"delete :");
			dmlLogger.insertLog(sql.toString(), newVals, request);
		}
		return del;
	}

	public  synchronized static boolean delete2(String tableName, String pk,
			String[] otherKey, String[] otherKayValue, HttpServletRequest request) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("delete ");
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		for (int i = 0; i < otherKey.length; i++) {
			sql.append(" and ");
			sql.append(otherKey[i]);
			sql.append("= ? ");
		}
		return flag;
	}

	/**
	 * 
	 * 尽量少用这个方法，这个方法的操作容易产生sql注入（尽管加入了输入检测）
	 * @param tableName
	 * @param otherKey
	 * @param otherKayValue
	 * @param like
	 * @param likeVal
	 * @return
	 * @throws Exception
	 */
	public synchronized static boolean delete3(String tableName,String[] otherKey, String[] otherKayValue,String []like,String []likeVal,HttpServletRequest request) throws Exception {
		
//		DAO dao = DAO.getInstance();
		boolean[] res = Check_Input_Value.checkArrVal(likeVal);
		for(int i=0;i<res.length;i++){
			if(!res[i]) return false;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		for (int i = 0; i < otherKey.length; i++) {
			sql.append(" and ");
			sql.append(otherKey[i]);
			sql.append("="+otherKayValue[i]+"");
		}		
		for(int i=0;i<like.length;i++){
			sql.append(" and ");
			sql.append(like[i]);
			sql.append(" like '"+likeVal[i]+"' ");
		}
		return delete(sql.toString(), tableName, request);
	}
	
	public synchronized  static String deleteSql(String tableName, String[] dgcolumns,
			String[] dgvalues, HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		boolean del = false;
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		if (dgcolumns != null || dgcolumns.length != 0) {
			for (int i = 0; i < dgcolumns.length; i++) {
				sql.append(dgcolumns[i]);
				sql.append("='");
				if (Check_Input_Value.check(dgvalues[i]) == 1) {
					sql.append(dgvalues[i]);
				}
				sql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
			}
		}
//		取老数据
		dao.getColumnName("select * from " + tableName); 
//		String []colList = dao.getColumnName("select * from " + tableName); 
		StringBuffer oldsql = new StringBuffer();
		oldsql.append("select * from ");
		oldsql.append(tableName);
		oldsql.append(" where ");
		if (dgcolumns != null || dgcolumns.length != 0) {
			for (int i = 0; i < dgcolumns.length; i++) {
				oldsql.append(dgcolumns[i]);
				oldsql.append("='");
				if (Check_Input_Value.check(dgvalues[i]) == 1) {
					oldsql.append(dgvalues[i]);
				}
				oldsql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
			}
		}
		String []newVals=null;
		if(del){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(oldsql.toString(),tableName,"delete :");
			dmlLogger.insertLog(sql.toString(), newVals, request);
		}
		return sql.toString();
	}
	

	/**
	 * @param tableName
	 *            tableName it's the table where you want to update records
	 * @param columns
	 *            are the ones you want to update
	 * @param values
	 *            are the ones you want to give them to the columns you
	 *            designate
	 * @param primaryKey
	 *            for designating the columns
	 * @param pkValue
	 *            <br>
	 *            <font color=red>要提供primaryKey and value,否则就可能更新所有记录</font>
	 * @return
	 * @throws Exception
	 * 
	 */
	public synchronized static boolean update(String tableName, String[] columns,
			String[] values, String primaryKey, String pkValue, HttpServletRequest request)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableName);
		sql.append(" set ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "=?" : "=?,");
		}
		if ((primaryKey != null || primaryKey.trim().length() > 0)
				&& (pkValue != null) || pkValue.trim().length() > 0) {
			sql.append(" where ");
			sql.append(primaryKey);
			sql.append("=?");
		}
		String[] input = new String[values.length + 1];
		Arrays2.copy(values, input, values.length, 0, 0);
		input[values.length] = pkValue;
		//取老数据
		boolean up = dao.runUpdate(sql.toString(), input);
		if(up){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName,primaryKey, pkValue,"update :");
			dmlLogger.insertLog(sql.toString(),input,request);
		}
		return up;
	}
	 
	public synchronized static boolean update(String tableName, String[] columns,
			String[] values, String primaryKey, String pkValue, User user)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableName);
		sql.append(" set ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "=?" : "=?,");
		}
		if ((primaryKey != null || primaryKey.trim().length() > 0)
				&& (pkValue != null) || pkValue.trim().length() > 0) {
			sql.append(" where ");
			sql.append(primaryKey);
			sql.append("=?");
		}
		String[] input = new String[values.length + 1];
		Arrays2.copy(values, input, values.length, 0, 0);
		input[values.length] = pkValue;
		//取老数据
		boolean up = dao.runUpdate(sql.toString(), input, tableName, user);
//		if(up){
//			DMLLogger dmlLogger = new DMLLogger();
//			dmlLogger.setOldData(tableName,primaryKey, pkValue,"update :");
//			dmlLogger.insertLog(sql.toString(),input,request);
//		}
		return up;
	}
	
	/**
	 * @category 通过dgcolumns和dgValues来确定where语句，由columns指定要修改的列，values是要改成的值。
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param dgcolumns
	 * @param dgValues
	 *            <br>
	 *            <font color=red>即：update tableName set columns=values where
	 *            dgcolumns=dgValues;</font>
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public synchronized static boolean update(String tableName, String[] columns,
			String[] values, String[] dgcolumns, String[] dgValues, HttpServletRequest request)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableName);
		sql.append(" set ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "=?" : "=?,");
		}
		sql.append(" where ");
		if (dgcolumns != null || dgcolumns.length != 0) {
			for (int i = 0; i < dgcolumns.length; i++) {
				sql.append(dgcolumns[i]);
				sql.append("='");
				if (Check_Input_Value.check(dgValues[i]) == 1) {
					sql.append(dgValues[i]);
				}
				sql.append((i == (dgcolumns.length - 1)) ? "'" : "' and ");
			}
		}
		
		boolean up = dao.runUpdate(sql.toString(), values);
		if(up){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(sql.toString(), tableName, "update :");
			dmlLogger.insertLog(sql.toString(),values,request);
			}
		return up;
	}


	/**
	 * @param tableName 操作的表
	 * @param sql		操作sql
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public synchronized static boolean update(String tableName, String sql, HttpServletRequest request)
	throws Exception {
		DAO dao = DAO.getInstance();
		boolean up = dao.runUpdate(sql, new String[]{});
		if(up){
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "update :");
			dmlLogger.insertLog(sql.toString(),new String[]{},request);
		}
		return up;
	}
	
	
	
//	/**
//	 * @param tableName
//	 *            is the table that is going to be inserted
//	 * @param columns
//	 *            are the target columns
//	 * @param values
//	 *            are the values coresponding to the columns respectively
//	 */
//
//	public synchronized static boolean insert(String tableName,String[] columns,String[] values){
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer("insert into ");
//		sql.append(tableName);
//		sql.append("( ");
//		for (int i = 0; i < columns.length; i++) {
//			sql.append(columns[i]);
//			sql.append((i == columns.length - 1) ? "" : ",");
//		}
//		sql.append(" ) values( ");
//		for (int i = 0; i < values.length; i++) {
//			if (i == (values.length - 1))
//				sql.append("?");
//			else
//				sql.append("?,");
//		}
//		sql.append(" )");
//
//		boolean rs = false;
//		try {
//			rs = dao.runUpdate(sql.toString(), values);
//			if(rs){
//				DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),values,user);
//				dmlLogger.setOldData(tableName, "insert :");
//				new Thread(dmlLogger).start();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
//	
//	/**
//	 * 与insert方法的生成sql的功能相同，主要就是用insert方法的生成sql来返回生成的sql
//	 * */
//	public static synchronized String insertSql(String tableName,String[] columns,String[] values){
//		DAO dao = DAO.getInstance();
//		boolean[] rs = Check_Input_Value.checkArrVal(values);
//		for(int i=0;i<rs.length;i++){
//			if(!rs[i]){
//				return null;
//			}
//		}
//		StringBuffer sql = new StringBuffer("insert into ");
//		sql.append(tableName);
//		sql.append("( ");
//		for (int i = 0; i < columns.length; i++) {
//			sql.append(columns[i]);
//			sql.append((i == columns.length - 1) ? "" : ",");
//		}
//		sql.append(" ) values( ");
//		for (int i = 0; i < values.length; i++) {
//			sql.append((i == (values.length - 1))? "'"+values[i]+"'" : "'"+values[i]+"',");
//		}
//		sql.append(" )");
//		try {
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),values,user);
//			dmlLogger.setOldData(tableName, "insert :");
//			new Thread(dmlLogger).start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sql.toString();
//	}
//	
//	/**
//	 * @param tableName
//	 *            is the table that is going to be inserted
//	 * @param columns
//	 *            are the target columns
//	 * @param values
//	 *            are the values coresponding to the columns respectively
//	 */
//	public synchronized static boolean insert2(String tableName, String[] columns,
//			String[] values, String zdhs_zd, String zdhz_value) {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer("insert into ");
//		sql.append(tableName);
//		sql.append("( ");
//		if (zdhs_zd != null && !zdhs_zd.equalsIgnoreCase("")) {
//			sql.append(zdhs_zd + ",");
//		}
//		for (int i = 0; i < columns.length; i++) {
//			sql.append(columns[i]);
//			sql.append((i == columns.length - 1) ? "" : ",");
//		}
//		sql.append(" ) values( ");
//		if (zdhz_value != null && !zdhz_value.equalsIgnoreCase("")) {
//			sql.append(zdhz_value + ",");
//		}
//		for (int i = 0; i < values.length; i++) {
//			if (i == (values.length - 1))
//				sql.append("?");
//			else
//				sql.append("?,");
//		}
//		sql.append(" )");
//
//		boolean rs = false;
//		try {
//			rs = dao.runUpdate(sql.toString(), values);
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),values,user);
//			dmlLogger.setOldData(tableName, "insert :");
//			new Thread(dmlLogger).start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
//
//	/**
//	 * @param tableName
//	 *            操作的表
//	 * @param dgcolumns
//	 *            表字段列表
//	 * @param dgvalues
//	 *            表字段值数据列表
//	 * @return true if it works well other false
//	 * @throws Exception
//	 *             <srong>you must be careful when you use this method,unless
//	 *             you truely want to delete the whole table ,you must give the
//	 *             primaryKey and the value to designate the records</strong>
//	 */
//	public synchronized  static boolean delete(String tableName, String[] dgcolumns,
//			String[] dgvalues) throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		boolean del = false;
//		sql.append("delete from ");
//		sql.append(tableName);
//		sql.append(" where ");
//		if (dgcolumns != null || dgcolumns.length != 0) {
//			for (int i = 0; i < dgcolumns.length; i++) {
//				sql.append(dgcolumns[i]);
//				sql.append("='");
//				if (Check_Input_Value.check(dgvalues[i]) == 1) {
//					sql.append(dgvalues[i]);
//				}
//				sql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
//			}
//		}
////		取老数据
//		String []colList = dao.getColumnName("select * from " + tableName); 
//		StringBuffer oldsql = new StringBuffer();
//		oldsql.append("select * from ");
//		oldsql.append(tableName);
//		oldsql.append(" where ");
//		if (dgcolumns != null || dgcolumns.length != 0) {
//			for (int i = 0; i < dgcolumns.length; i++) {
//				oldsql.append(dgcolumns[i]);
//				oldsql.append("='");
//				if (Check_Input_Value.check(dgvalues[i]) == 1) {
//					oldsql.append(dgvalues[i]);
//				}
//				oldsql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
//			}
//		}
//		String []newVals=null;
//		DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),newVals,user);
//		dmlLogger.setOldData(oldsql.toString(),tableName,"delete :");
//		
//		del = dao.runUpdate(sql.toString(), new String[] {});
//		if(del){
//			new Thread(dmlLogger).start();
//		}
//		return del;
//	}
//
//	
//	
//	
//	/**
//	 * @param tableName
//	 *            it's the table where you want to delete records
//	 * @param primaryKey
//	 *            is the primary key of the table given by the name tableName
//	 * @param value
//	 *            is the primary key value to identify the records to be deleted
//	 *            <br>
//	 *            <font color='red'>你必须提供primarykey and
//	 *            value以确定要删除的记录,否则你将删除整个表的数据</font>
//	 * @return true if it works well other false
//	 * @throws Exception
//	 *             <srong>you must be careful when you use this method,unless
//	 *             you truely want to delete the whole table ,you must give the
//	 *             primaryKey and the value to designate the records</strong>
//	 * 
//	 */
//	public synchronized static boolean delete(String tableName, String primaryKey,
//			String value) throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		boolean del = false;
//		sql.append("delete from ");
//		sql.append(tableName);
//		if (primaryKey != null || primaryKey.trim().length() > 0) {
//			sql.append(" where ");
//			sql.append(primaryKey);
//			sql.append("='");
//			sql.append(value);
//			sql.append("'");
//		}
////		取老数据
////		String []colList = dao.getColumnName("select * from " + tableName); 
////		String []values = dao.getOneRs("select * from " + tableName + " where " + primaryKey + "='" + value + "'", new String[]{}, colList);
//		String []newVals=null;
//		DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),newVals,user);
//		dmlLogger.setOldData(tableName, primaryKey, value,"delete :");
//		del = dao.runUpdate(sql.toString(), new String[] {});
//		if(del){
////			String CNtableName = dao.getCNtableName(tableName);
////			String yhcz = "删除记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql.toString(), newVals, values, yhcz, request);
//			new Thread(dmlLogger).start();
//		}
//		return del;
//	}
//
//	
//	/**
//	 * @param tableName 操作的表
//	 * @param primaryKey  操作表的主键
//	 * @param value			操作表的主键值
//	 * @param notInCol		操作表中no in 的字段
//	 * @param notInVal		操作表中no in 的字段值
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	public synchronized static boolean delete(String tableName, String primaryKey,
//			String value, String notInCol, String[] notInVal) throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		boolean del = false;
//		sql.append("delete from ");
//		sql.append(tableName);
//		if (primaryKey != null || primaryKey.trim().length() > 0) {
//			sql.append(" where ");
//			sql.append(primaryKey);
//			sql.append("='");
//			sql.append(value);
//			sql.append("'");
//		}
//		if((null != notInCol) && (!"".equalsIgnoreCase(notInCol.trim()))){
//			sql.append(" and ");
//			sql.append(notInCol);
//			sql.append(" not in (");
//			if (notInVal.length > 0) {
//				int i;
//				for (i = 0; i < notInVal.length - 1; i++) {
//					sql.append("'");
//					sql.append(notInVal[i]);
//					sql.append("',");
//				}
//				sql.append("'");
//				sql.append(notInVal[i]);
//				sql.append("'");
//			}
//			sql.append(")");
//		}
////		取老数据
////		String []colList = dao.getColumnName("select * from " + tableName); 
////		String []values  = dao.getOneRs("select * from " + tableName + " where " + primaryKey + "='" + value + "'", new String[]{}, colList);
//		String []newVals=null;
//		DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),newVals,user);
//		dmlLogger.setOldData(tableName, primaryKey, value,"delete :");
//		del = dao.runUpdate(sql.toString(), new String[] {});
//		if(del){
//			new Thread(dmlLogger).start();
//		}
//		return del;
//	}
//	
//	/**
//	 * @param tableName 操作的表
//	 * @param sql		操作sql
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	public synchronized static boolean delete(String sql,String tableName) throws Exception {
//		DAO dao = DAO.getInstance();
//		boolean del = false;
//		String []newVals=null;
//		DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),newVals,user);
//		dmlLogger.setOldData(sql, tableName,"delete :");
//		del = dao.runUpdate(sql, new String[] {});
//		if(del){
////			String []newVals=null;
////			String CNtableName = dao.getCNtableName(tableName);
////			String yhcz = "删除记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql.toString(), newVals, null, yhcz, request);
//			new Thread(dmlLogger).start();
//		}
//		System.out.println("delete sql");
//		return del;
//	}
//
//	public  synchronized static boolean delete2(String tableName, String pk,
//			String[] otherKey, String[] otherKayValue) throws Exception {
//		DAO dao = DAO.getInstance();
//		boolean flag = false;
//		StringBuffer sql = new StringBuffer();
//		String pkValue = "";
//		sql.append("select ");
//		sql.append(pk);
//		sql.append(" from ");
//		sql.append(tableName);
//		sql.append(" where 1=1 ");
//		for (int i = 0; i < otherKey.length; i++) {
//			sql.append(" and ");
//			sql.append(otherKey[i]);
//			sql.append("= ? ");
//		}
//		List<HashMap<String, String>> li = dao.getList(sql.toString(), otherKayValue,
//				new String[] { pk });
//		HashMap<String, String> map = new HashMap<String, String>();
//		int tempLen = li.size();
//		for (int i = 0; i < tempLen; i++) {
//			map = li.get(i);
//			pkValue = pkValue + map.get(pk) + (i == tempLen - 1 ? "" : ",");
//		}
//		String[] pkV = pkValue.split(",");
//
//		for (int i = 0; i < pkV.length; i++) {
//			flag = delete(tableName, pk, pkV[i]);
//		}
//		return flag;
//	}
//
//	/**
//	 * 
//	 * 尽量少用这个方法，这个方法的操作容易产生sql注入（尽管加入了输入检测）
//	 * @param tableName
//	 * @param otherKey
//	 * @param otherKayValue
//	 * @param like
//	 * @param likeVal
//	 * @return
//	 * @throws Exception
//	 */
//	public synchronized static boolean delete3(String tableName,String[] otherKey, String[] otherKayValue,String []like,String []likeVal) throws Exception {
//		
//		DAO dao = DAO.getInstance();
//		boolean[] res = Check_Input_Value.checkArrVal(likeVal);
//		for(int i=0;i<res.length;i++){
//			if(!res[i]) return false;
//		}
//		StringBuffer sql = new StringBuffer();
//		sql.append("delete from ");
//		sql.append(tableName);
//		sql.append(" where 1=1 ");
//		for (int i = 0; i < otherKey.length; i++) {
//			sql.append(" and ");
//			sql.append(otherKey[i]);
//			sql.append("="+otherKayValue[i]+"");
//		}		
//		for(int i=0;i<like.length;i++){
//			sql.append(" and ");
//			sql.append(like[i]);
//			sql.append(" like '"+likeVal[i]+"' ");
//		}
//		return delete(sql.toString(), tableName);
//	}
//	
//	public synchronized  static String deleteSql(String tableName, String[] dgcolumns,
//			String[] dgvalues) throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		boolean del = false;
//		sql.append("delete from ");
//		sql.append(tableName);
//		sql.append(" where ");
//		if (dgcolumns != null || dgcolumns.length != 0) {
//			for (int i = 0; i < dgcolumns.length; i++) {
//				sql.append(dgcolumns[i]);
//				sql.append("='");
//				if (Check_Input_Value.check(dgvalues[i]) == 1) {
//					sql.append(dgvalues[i]);
//				}
//				sql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
//			}
//		}
////		取老数据
//		String []colList = dao.getColumnName("select * from " + tableName); 
//		StringBuffer oldsql = new StringBuffer();
//		oldsql.append("select * from ");
//		oldsql.append(tableName);
//		oldsql.append(" where ");
//		if (dgcolumns != null || dgcolumns.length != 0) {
//			for (int i = 0; i < dgcolumns.length; i++) {
//				oldsql.append(dgcolumns[i]);
//				oldsql.append("='");
//				if (Check_Input_Value.check(dgvalues[i]) == 1) {
//					oldsql.append(dgvalues[i]);
//				}
//				oldsql.append((i == (dgcolumns.length - 1)) ? "' " : "' and ");
//			}
//		}
////		String []values = dao.getOneRs(oldsql.toString(), new String[]{}, colList);
//		//del = dao.runUpdate(sql.toString(), new String[] {});
//		String []newVals=null;
//		DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),newVals,user);
//		dmlLogger.setOldData(oldsql.toString(),tableName,"delete :");
//		if(del){
////			String []newVals=null;
////			String CNtableName = dao.getCNtableName(tableName);
////			String yhcz = "删除记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql.toString(), newVals, values, yhcz, request);
//			new Thread(dmlLogger).start();
//		}
//		return sql.toString();
//	}
//	
//
//	/**
//	 * @param tableName
//	 *            tableName it's the table where you want to update records
//	 * @param columns
//	 *            are the ones you want to update
//	 * @param values
//	 *            are the ones you want to give them to the columns you
//	 *            designate
//	 * @param primaryKey
//	 *            for designating the columns
//	 * @param pkValue
//	 *            <br>
//	 *            <font color=red>要提供primaryKey and value,否则就可能更新所有记录</font>
//	 * @return
//	 * @throws Exception
//	 * 
//	 */
//	public synchronized static boolean update(String tableName, String[] columns,
//			String[] values, String primaryKey, String pkValue)
//	throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		sql.append("update ");
//		sql.append(tableName);
//		sql.append(" set ");
//		for (int i = 0; i < columns.length; i++) {
//			sql.append(columns[i]);
//			sql.append((i == columns.length - 1) ? "=?" : "=?,");
//		}
//		if ((primaryKey != null || primaryKey.trim().length() > 0)
//				&& (pkValue != null) || pkValue.trim().length() > 0) {
//			sql.append(" where ");
//			sql.append(primaryKey);
//			sql.append("=?");
//		}
//		String[] input = new String[values.length + 1];
//		Arrays2.copy(values, 0, values.length, input, 0);
//		input[values.length] = pkValue;
//		//取老数据
////		String []oldvalues = dao.getOneRs("select * from " + tableName + " where " + primaryKey + "='" + pkValue + "'", new String[]{}, columns);
//		
//		boolean up = dao.runUpdate(sql.toString(), input);
//		if(up){
////			String CNtableName = dao.getCNtableName(tableName);
////			String yhcz = "修改记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql.toString(), values, oldvalues, yhcz, request);
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),input,user);
//			dmlLogger.setOldData(tableName,primaryKey, pkValue,"update :");
//			new Thread(dmlLogger).start();
//				}
//		return up;
//	}
//	 
//	/**
//	 * @category 通过dgcolumns和dgValues来确定where语句，由columns指定要修改的列，values是要改成的值。
//	 * 
//	 * @param tableName
//	 * @param columns
//	 * @param values
//	 * @param dgcolumns
//	 * @param dgValues
//	 *            <br>
//	 *            <font color=red>即：update tableName set columns=values where
//	 *            dgcolumns=dgValues;</font>
//	 * @return
//	 * @throws Exception
//	 * 
//	 * 
//	 */
//	public synchronized static boolean update(String tableName, String[] columns,
//			String[] values, String[] dgcolumns, String[] dgValues)
//	throws Exception {
//		DAO dao = DAO.getInstance();
//		StringBuffer sql = new StringBuffer();
//		sql.append("update ");
//		sql.append(tableName);
//		sql.append(" set ");
//		for (int i = 0; i < columns.length; i++) {
//			sql.append(columns[i]);
//			sql.append((i == columns.length - 1) ? "=?" : "=?,");
//		}
//		sql.append(" where ");
//		if (dgcolumns != null || dgcolumns.length != 0) {
//			for (int i = 0; i < dgcolumns.length; i++) {
//				sql.append(dgcolumns[i]);
//				sql.append("='");
//				if (Check_Input_Value.check(dgValues[i]) == 1) {
//					sql.append(dgValues[i]);
//				}
//				sql.append((i == (dgcolumns.length - 1)) ? "'" : "' and ");
//			}
//		}
////		取老数据
////		StringBuffer oldsql = new StringBuffer();
////		oldsql.append("select * from ");
////		oldsql.append(tableName);
////		oldsql.append(" where ");
////		if (dgcolumns != null || dgcolumns.length != 0) {
////			for (int i = 0; i < dgcolumns.length; i++) {
////				oldsql.append(dgcolumns[i]);
////				oldsql.append("='");
////				if (Check_Input_Value.check(dgValues[i]) == 1) {
////					oldsql.append(dgValues[i]);
////				}
////				oldsql.append((i == (dgcolumns.length - 1)) ? "'" : "' and ");
////			}
////		}
////		String []oldvalues = dao.getOneRs(oldsql.toString(), new String[]{}, columns);
//		
//		boolean up = dao.runUpdate(sql.toString(), values);
//		if(up){
////			String CNtableName = dao.getCNtableName(tableName);
////			String KeyCol = "";
////			String KeyColVal = "";
////			for(int c=0; c<dgcolumns.length; c++){
////				KeyCol += dgcolumns[c];
////				KeyCol += ",";
////			}
////			for(int v=0; v<dgValues.length; v++){
////				KeyColVal += dgValues[v];
////				KeyColVal += ",";
////			}
////			String yhcz = "修改记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql.toString(), values, oldvalues, yhcz, request);
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),values,user);
//			dmlLogger.setOldData(sql.toString(), tableName, "update :");
//			new Thread(dmlLogger).start();
//			}
//		return up;
//	}
//
//
//	/**
//	 * @param tableName 操作的表
//	 * @param sql		操作sql
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	public synchronized static boolean update(String tableName, String sql)
//	throws Exception {
//		DAO dao = DAO.getInstance();
//		boolean up = dao.runUpdate(sql, new String[]{});
//		if(up){
////			String CNtableName = dao.getCNtableName(tableName);
////			String yhcz = "修改记录：" + tableName + "-" + CNtableName;
////			dao.writeLog(sql, null, null, yhcz, request);
//			DMLLogger dmlLogger = new DMLLogger(dao,sql,new String[]{},user);
//			dmlLogger.setOldData(tableName, "update :");
//			new Thread(dmlLogger).start();
//		}
//		return up;
//	}
	
	
	
	/**
	 * 
	 * @return 返回系统设置表中的当前学年、学期数组，第一个为学年，第二个为学期
	 */
	public static String[] getXnXq() {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select dqxn,dqxq from xtszb ", new String[] {},
				new String[] { "dqxn", "dqxq" });
	}

	/**
	 * @param tableName
	 *            要检测的表
	 * @param primaryKey
	 *            查询条件
	 * @param pkValue
	 *            查询条件值
	 * @return 存在就返回 true,否则为false
	 * @category 此方法用于检测表中是否已经存在指定记录
	 */
	public static boolean testRecordExist(String tableName, String primaryKey,
			String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) cont from " + tableName + " where "
		+ primaryKey + "=?";
		String cont = dao.getOneRs(sql, new String[] { pkValue }, "cont");
		return (cont != null && !cont.equals("0")) ? true : false;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param from
	 * @param xh
	 * @param variable
	 *            要通过request返回的变量,但是不能传回变量值，只能传回用于<logic:present/>
	 * @return
	 * @throws Exception
	 */
	public static ActionForward getStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String from, String xh,
			String variable) throws Exception {
		DAO dao = DAO.getInstance();
		String[] outCols = { "xh", "xm", "xymc", "zymc", "bjmc", "xb", "nj","rxny", "xz", "ssbh", "qsdh", "sjhm", "mzmc","syd" };
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.nj,a.rxrq rxny,a.xz,a.ssbh,a.qsdh,a.sjhm,a.mzmc,a.syd from view_xsxxb a where xh=?";
		String[] outVals = dao.getOneRs(sql, new String[] { xh }, outCols);
		HashMap<String, String> map = new HashMap<String, String>();
		if(outVals!=null&&outVals.length>0){
			for(int i=0;i<outCols.length;i++){
				if(outVals[i]!=null && outVals[i].equals("男")){
					map.put("xb", "1");
				}else if(outVals[i]!=null && outVals[i].equals("女")){
					map.put("xb", "2");
				}else
					map.put(outCols[i], outVals[i]);
			}
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},"xxmc");
		String xxdm = StandardOperation.getXxdm();

		map.put("xxmc", xxmc);
		request.setAttribute(variable, variable);
		request.setAttribute("bzList", dao.getBzList());
		request.setAttribute("rs", map);
		String jxjdm = Base.chgNull(request.getParameter("jxjdm"), "", 1);
		if (jxjdm!=null && !jxjdm.equals("")){	//主要用于处理上海工程中的评奖评优	奖学金代码
			request.setAttribute("jxjdm", jxjdm);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && (jxjdm.equals("0000000010") || jxjdm.equals("0000000012"))) {	//处理上海工程技术大学	上联奖学金、交运奖学金	将学生成绩查询出来
			List<HashMap<String,String>> cjList = dao.getListNotOut("select kcmc, cj, bkcj, cxcj from view_cjb where kcxz = '必修课' and xh=?", new String[]{xh});
			int mod = cjList.size()%3;
			if (mod==1) {
				cjList.add(new HashMap<String, String>());
				cjList.add(new HashMap<String, String>());
			} else if (mod==2) {
				cjList.add(new HashMap<String, String>());
			}
			request.setAttribute("cjList", cjList);
			request.setAttribute("cjRow", cjList.size()/3+1);
			request.setAttribute("cjIter", new String[cjList.size()/3]);
		}
		return new ActionForward(from, false);
	}


	public static ActionForward getStudetailArchives(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,
			String from,String xh,String variable)
	throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		DAO	dao = DAO.getInstance();
		String sql="";
		String[] colList={"xh","xm","sfzh","nj","xymc","zymc","bjmc","zddwmc","zddwdz","zysj"};
		String[] outValues=new String[]{xh};
		String tableName="view_jygl_archives";//
		sql="select  * from "+tableName+" where xh=?";
		map=dao.getMap(sql,outValues, colList);
		request.setAttribute("rs", map);
		return new ActionForward(from, false);
	}
	public static ActionForward getStudetailInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,
			String from,String xh,String variable)
	throws Exception{
		DAO	dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql="";
		String[] outCols=null;
		String[] outValues=null;
		String result="";
		sql="select * from view_xsjtxx where xh=?";
		if("xsxx".equalsIgnoreCase(variable)){			
			String modistu=dao.getOneRs("select modistuinfo from xtszb", new String[]{}, "modistuinfo");
			sql="select * from view_xsjbxx where xh=?";
			outCols=new String[]{"xh","xm","nj","sfzh","xydm","xymc","zydm","zymc","bjdm","bjmc","xz","lxdh","xb"};
			outValues=dao.getOneRs(sql, new String[]{xh}, outCols);					
			if(null!=outValues){
				for(int i=0;i<outValues.length;i++){
					map.put(outCols[i],outValues[i]);
				}
			}
			map.putAll(dao.getMap("select mzmc mz,mzmc,csrq,zzmmmc,mz mzdm from view_xsxxb where xh=?", new String[]{xh}, new String[]{"mzmc", "mz", "mzdm", "csrq","zzmmmc"}));
//			if(modistu.equalsIgnoreCase("1")){	
//				map.putAll(dao.getMap("select mzmc,csrq,zzmmmc,mz mzdm from view_xsxxb where xh=?", new String[]{xh}, new String[]{"mzmc", "mzdm", "csrq","zzmmmc"}));
//				mzmc=dao.getOneRs("select mzmc from view_xsxxb where xh=?", new String[]{xh}, "mzmc");
//				csrq=dao.getOneRs("select csrq from view_xsxxb where xh=?", new String[]{xh}, "csrq");
//				zzmmmc = 
//			}else{
//				mzdm=dao.getOneRs("select mzdm from bks_xsqtxx where xh=?", new String[]{xh}, "mzdm");
//				mzmc=dao.getOneRs("select mzmc from mzdmb where mzdm=?", new String[]{mzdm}, "mzmc");
//				csrq=dao.getOneRs("select csrq from bks_xsqtxx where xh=?", new String[]{xh}, "csrq");
//			}
//			map.put("mz", mzmc);
//			map.put("csrq", csrq);
		}else{
			result=dao.getOneRs(sql, new String[]{xh}, "xh");
			if(result.equals("")){
				sql="select * from xsxxb where xh=?";
				outCols=new String[]{"XH","XM", "XB","NJ", "XYDM","XY","XZ","ZYDM","ZYMC","BJDM","BJMC","XY","SYD","LXDH","BZ"};
				outValues=dao.getOneRs(sql, new String[]{xh}, outCols);
				if(outValues!=null&&outValues.length>0){
					for(int i=0;i<outValues.length;i++){
						map.put(outCols[i],outValues[i]);
					}
					map.put("xymc", outValues[5]);
				}
			}else{
				sql="select * from view_xsjtxx where xh=?";
				outCols=new String[]{"xh","xb","xm","nj","zymc","zydm","xymc","xydm","bjmc","bjdm","jtszd","jtcy1_xm","jtcy1_nl","jtcy1_gx"   
						,"jtcy1_gzdz","jtcy1_zy","jtcy1_zw","jtcy1_zzmm","jtcy1_yzbm","jtcy1_mz","jtcy1_sfzh","jtcy1_lxdh1"
						,"jtcy1_lxdh2","jtcy1_lxdh3","jtcy2_xm","jtcy2_nl","jtcy2_gx"   
						,"jtcy2_gzdz","jtcy2_zy","jtcy2_zw","jtcy2_zzmm","jtcy2_yzbm","jtcy2_mz","jtcy2_sfzh","jtcy2_lxdh1"
						,"jtcy2_lxdh2","jtcy2_lxdh3","jtcy3_xm","jtcy3_nl","jtcy3_gx"   
						,"jtcy3_gzdz","jtcy3_zy","jtcy3_zw","jtcy3_zzmm","jtcy3_yzbm","jtcy3_mz","jtcy3_sfzh","jtcy3_lxdh1"
						,"jtcy3_lxdh2","jtcy3_lxdh3","jjzk","jtyb","sfdb","srly","jtzsr","jtrjsr","jtdh","jtcy1_jtdz","jtcy2_jtdz","jtcy3_jtdz"};
				outValues=dao.getOneRs(sql, new String[]{xh}, outCols);
				for(int i=0;i<outValues.length;i++){
					map.put(outCols[i],outValues[i]);
				}
			}
		}
		request.setAttribute("rs", map);
		return  new ActionForward(from, false);
	}

	public static ActionForward getXjydStuInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,
			String from,String xh,String variable)
	throws Exception{
		DAO	dao = DAO.getInstance();
		String sql="";
		String[] outCols=null;
		HashMap<String, String> map = new HashMap<String, String>();
		
		//基本信息从学生基本信息中查找		
		outCols =new String[] {"xh","xm","xymc","xydm","zymc","zydm","bjmc","bjdm","nj","xz","xjztm","xm"};
		sql= "select xh,xm,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjztm,xm from view_xsjbxx where xh=?";

		String[] outVals = dao.getOneRs(sql, new String[]{xh}, outCols);
		
		if(null!=outVals){
			//异动前信息
			map.put("ydqnj", outVals[8]);
			map.put("ydqxymc", outVals[2]);
			map.put("ydqzymc", outVals[4]);
			map.put("ydqbjmc", outVals[6]);
			map.put("ydqxz", outVals[9]);				
			map.put("ydhxymc", outVals[2]);
			map.put("ydqxjztm", outVals[10]);
			
			//异动后信息
			map.put("ydhnj", outVals[8]);
			map.put("ydhxydm", outVals[3]);
			map.put("ydhxymc",outVals[2]);
			map.put("ydhzydm", outVals[5]);
			map.put("ydhzymc", outVals[4]);
			map.put("ydhbjdm", outVals[7]);			
			map.put("ydhbjmc", outVals[6]);
			map.put("ydhxz", outVals[9]);	
			map.put("ydhxjztm", outVals[10]);
			
			//基本信息
			map.put("xh", outVals[0]);
			map.put("xm", outVals[1]);
		}
			request.setAttribute("rs", map);	
			//request.setAttribute("xjztList", dao.getList("select zxdm,zxdmmc from dm_zju_xjzt order by zxdm", new String[]{}, new String[]{"zxdm","zxdmmc"}));
			return  new ActionForward(from, false);

	}

	public static ActionForward delAllData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String codeType = request.getParameter("codeType");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nf = request.getParameter("nf");
		String jxjdm = request.getParameter("jxjdm");
		String rychdm = request.getParameter("rychdm");
		String yf = request.getParameter("yf");
		String pk = request.getParameter("pk");
		String []pkV = null;
		String sql = "";
		String querry = "";
		StringBuffer querry1 = new  StringBuffer();
		querry1.append(" where 1=1");
		if(realTable.equalsIgnoreCase("gdnz_lpxxb_bx")){
			realTable="gdnz_lpxxb";;
			querry1.append(" and sfxfzrx='0'");
		}
		if(realTable.equalsIgnoreCase("gdnz_lpxxb_sh")){
			realTable="gdnz_lpxxb";
			querry1.append(" and sfxfzrx='1'");
		}
		boolean del = false;
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}
		if("".equalsIgnoreCase(pk) || pk == null){
			querry1.append(" and 1=2");
		} else if ("delcode".equalsIgnoreCase(pk)){
			querry1.append(" and 1=1");
		}else{
			pkV = pk.split("\\|\\|");
			for(int i =0; i<pkV.length; i++){
				if(pkV[i].indexOf("datetimetostr")==0) {
				querry1.append(" and nvl(datetimetostr(a.");
				querry1.append(pkV[i].substring(14));
				querry1.append(",0)=nvl(datetimetostr(b.");
				querry1.append(pkV[i].substring(14));
				querry1.append(",0)");
				}else if (pkV[i].indexOf("strtodatetime")==0){
					querry1.append(" and nvl(strtodatetime(a.");
					querry1.append(pkV[i].substring(14));
					querry1.append(",0)=nvl(strtodatetime(b.");
					querry1.append(pkV[i].substring(14));
					querry1.append(",0)");
				}else {
					querry1.append(" and nvl(a.");
					querry1.append(pkV[i]);
					querry1.append(",0)=nvl(b.");
					querry1.append(pkV[i]);
					querry1.append(",0)");
					
				}
			}
		}
		querry = getdelquerry(xn, xq, nj, xydm, zydm, bjdm, nf, jxjdm, rychdm, yf);
		
		if(tableName.indexOf("rcgl_dmwhb")!=-1){//&&xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){				
			String tTem = tableName.replace("rcgl_dmwhb!!", "");
			tableName = "rcgl_dmwhb";
			realTable = "rcgl_dmwhb";
			querry += " and MKSS='"+tTem+"'";
		}
		
		sql = "delete " + realTable + " a where exists (select * from " + "(select * from " + tableName + querry + ")" + " b" + querry1.toString() + ")";
		del = dao.runUpdate(sql, new String[]{});
		if(del){
			request.setAttribute("result", "ok");
		}else{
			request.setAttribute("result", "no");
		}
		if("delcode".equalsIgnoreCase(pk)){
			return new ActionForward("/code_man.do?act=" + codeType, false);
		}else if("yt_id".equalsIgnoreCase(pk)){
			return mapping.findForward("yt_delete");
		}else{
			return mapping.findForward("success");
		}
	}

	/**
	 * @param xn  学年
	 * @param xq  学期
	 * @param nj  年级
	 * @param xydm 学院代码
	 * @param zydm 专业代码
	 * @param bjdm 班级代码
	 * @param nf  年份
	 * @param jxjdm  奖学金代码
	 * @param rychdm 荣誉称号代码
	 * @param yf   月份
	 * @return 返回查询条件
	 */
	private static String getdelquerry(String xn, String xq, String nj, String xydm, String zydm, String bjdm, String nf, String jxjdm, String rychdm, String yf) {
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		if("".equalsIgnoreCase(nj) || nj == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(nj)){
				querry.append(" and nj='");
				querry.append(nj);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(xydm) || xydm == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(xydm)){
				querry.append(" and xydm='");
				querry.append(xydm);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(zydm) || zydm == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(zydm)){
				querry.append(" and zydm='");
				querry.append(zydm);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(bjdm) || bjdm == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(bjdm)){
				querry.append(" and bjdm='");
				querry.append(bjdm);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(nf) || nf == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(nf)){
				querry.append(" and nf='");
				querry.append(nf);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(jxjdm) || jxjdm == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(jxjdm)){
				querry.append(" and jxjdm='");
				querry.append(jxjdm);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(rychdm) || rychdm == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(rychdm)){
				querry.append(" and rychdm='");
				querry.append(rychdm);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(yf) || yf == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(yf)){
				querry.append(" and yf='");
				querry.append(yf);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(xn) || xn == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(xn)){
				querry.append(" and xn='");
				querry.append(xn);
				querry.append("'");
			}
		}
		if("".equalsIgnoreCase(xq) || xq == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(xq)){
				querry.append(" and xq='");
				querry.append(xq);
				querry.append("'");
			}
		}
		return querry.toString();
	}

	/**
	 * @return 返回数据库当前时间
	 */
	public static String getRightTime(){
		DAO	dao = DAO.getInstance();
		return dao.getOneRs("select to_char(sysdate,'yyyymmddhh24miss') dqsj from dual", new String[]{}, "dqsj");
	}

	/**
	 * @param xh
	 *            相应的学号
	 * @return 返回固定的学生信息，<font colour="red">包括：
	 *         "xh","xm","xymc","zymc","bjmc","xb","nj","rxny","ssbh","qsdh","sjhm","mzmc"</font>
	 */
	public static String[] getFixupStuInfo(String xh) {
		DAO dao = DAO.getInstance();
		String[] outCols = { "xh", "xm", "xymc", "zymc", "bjmc", "xb", "nj",
				"rxny", "xz", "ssbh", "qsdh", "sjhm", "mzmc" };
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.nj,b.rxny,b.xz,a.ssbh,a.qsdh,a.sjhm,c.mzmc"
			+ " from view_xsjbxx a,bks_xsjbxx b,(select a.xh,b.mzmc from bks_xsqtxx a,mzdmb b where a.mzdm=b.mzdm) c "
			+ " where a.xh=b.xh and b.xh=c.xh and a.xh=?";
		return dao.getOneRs(sql, new String[] { xh }, outCols);

	}


	/**
	 * @return  学校名称
	 */
	public static String getXxmc(){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xxmc from xtszb", new String[] {}, "xxmc");
	}

	/**
	 * @return  学校代码
	 */
	public static String getXxdm(){
	    //DAO dao = DAO.getInstance();
	    //return dao.getOneRs("select xxdm from xtszb", new String[] {}, "xxdm");
		
		return Base.xxdm;
	}

	/**
	 * 判断表是否存在,存在就返回true,否则false
	 * @param tableName
	 * @return boolean
	 */ 
	public static boolean tableExist(String tableName) {
		DAO dao = DAO.getInstance();
		String sql = "select decode(table_name,'','no','yes') result from user_tables where table_name=upper('"
			+ tableName + "')";
		String result = dao.getOneRs(sql, new String[] {}, "result");
		return result.equalsIgnoreCase("yes") ? true : false;
	}
	
	/**
	 * @return  返回学校的奖学金评比周期方式 xn 或 xq
	 */
	public static String getCollegePriseCycle(){
		DAO dao = DAO.getInstance();
		String cycle = dao.getOneRs("select (case jxjpbzq when 0 then 'xn' else 'xq' end) fs from xtszb", new String[]{}, "fs");
		return cycle;
	}

	/**
	 * @author ZhouMi
	 * @return  返回学校当前周次(北京联合大学)
	 */
	public static String getCurrZc(){
		DAO dao = DAO.getInstance();
		String[] temp = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') now,kxdyt,xqzs from xtszb", new String[]{}, new String[]{"now","kxdyt","xqzs"});
		String dqzc = "放假";
		int dqzcNum = 0;
		String dayNum = dao.getOneRs("select to_date('" + temp[0]
				+ "','yyyy-mm-dd')-to_date('" + temp[1]
				+ "','yyyy-mm-dd') dNum from dual", new String[] {},
				"dNum");
		for(int i = Integer.parseInt(dayNum); i >= 0; i -= 7){
			dqzcNum++;
		}
		temp[2] = temp[2] != null && !"".equalsIgnoreCase(temp[2]) ? temp[2] : "0";
		if(dqzcNum < Integer.parseInt(temp[2])){
			dqzc = String.valueOf(dqzcNum);
		}
		return dqzc;
	}
	
	/**
	 * 将数组arr1和arr2一一对应合到一个容器中，两个数组的长度必须相等，否则返回null
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static Vector<HashMap<String, String>> getVector(String[] arr1,String[] arr2){
		Vector<HashMap<String, String>> result = new Vector<HashMap<String,String>>();
		if(arr1.length != arr2.length){
			return null;
		}
		for(int i=0;i<arr1.length;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", arr1[i]);
			map.put("cn", arr2[i]!=null?arr2[i]:"");
			result.add(map);
		}
		return result;
	}

	public static String getAutoGenZhcp(){
		DAO dao = DAO.getInstance();
		String sql = "select autogenzhcp from xtszb";
		return dao.getOneRs(sql, new String[]{}, "autogenzhcp");
	}
	
	/**
	 * @return 违纪处分处理文号
	 */
	public static String getWjcfClwh(String xxdm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select cfwhmc from wjcf_cfwhdmb where xxdm=?", new String[] {xxdm}, "cfwhmc");
	}
	
	/**
	 * 根据提供的主键和键值来查看某个表中是否有指定的记录
	 * @param tableName
	 * @param key
	 * @param keyVal
	 * @return 有：true；没有：false
	 */
	public static boolean existsRecord(String tableName,String key,String keyVal){
		boolean result = false;
		DAO dao = DAO.getInstance();
		String sql = "select count(1) cont from "+tableName+" where "+key+"='"+keyVal.toLowerCase()+"'";
		if(Integer.parseInt(dao.getOneRs(sql, new String[]{}, "cont"))>0){
			result = true;
		}
		return result;
	} 
	
	/**
	 * @author LiRong
	 * @param String xh
	 * @return boolean 
	 * */
	public static boolean isBys(String xh){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		boolean flag = false;
		int num = 0;
		String dqn = "";
		String sql = "";
		String rxrq = "";
		String rxy = "";
		
		if (StringUtils.isNull(xh)) {
			return false;
		}
		
		dqn = dao.getOneRs("select (to_number(nj)+to_number(xz)) byn from view_xsjbxx where xh=?", new String[]{xh}, "byn");
		sql = "select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) year,to_number(substr(to_char(sysdate,'yyyy-mm-dd'),6,2)) month from dual";
		map = dao.getMap(sql, new String[]{}, new String[]{"year","month"});
		num = Integer.parseInt(map.get("year")) - Integer.parseInt(dqn);
		if(num==0){
			rxrq = dao.getOneRs("select rxrq from view_xsxxb where xh=?", new String[]{xh}, "rxrq");			
			if(rxrq!=null && rxrq.length()==10 && rxrq.indexOf("-")>0){
				rxy = dao.getOneRs("select to_number(substr(rxrq,6,2)) rxy from view_xsxxb where xh=?", new String[]{xh}, "rxy");
			}else if(rxrq!=null && rxrq.length()>5 && rxrq.indexOf("-")<1){
				rxy = dao.getOneRs("select to_number(substr(rxrq,5,2)) rxy from view_xsxxb where xh=?", new String[]{xh}, "rxy");
			}else{
				flag = false;
			}
			rxy = rxy == null || "".equalsIgnoreCase(rxy) ? map.get("month") : rxy;
			num = Integer.parseInt(map.get("month")) - Integer.parseInt(rxy);
			if(num>=0){
				flag = true;
			}
		}
		return flag;
	}
	
	public synchronized static boolean insertNoLog(String tableName,String[] columns,String[] values){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName);
		sql.append("( ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "" : ",");
		}
		sql.append(" ) values( ");
		for (int i = 0; i < values.length; i++) {
			if (i == (values.length - 1))
				sql.append("?");
			else
				sql.append("?,");
		}
		sql.append(" )");
		boolean rs = false;
		try {
			rs = dao.runUpdate(sql.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * @param tableName 操作的表
	 * @param sql		操作sql
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public synchronized static boolean updateNoLog(String sql)
	throws Exception {
		DAO dao = DAO.getInstance();
		boolean up = dao.runUpdate(sql, new String[]{});
		return up;
	}
	
	/**中国地质大学直接分配岗位后，该学生其他岗位信息设置为审核不通过
	 * @throws Exception */
	public static boolean updateXsgwxx(String[] tmp) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update xsgwxxb set xyyj='不通过',xxyj='不通过' where xh=? and xn=? and nd=? and xq=? and xyyj<>'通过' and xxyj<>'通过' ";
		
		return dao.runUpdate(sql, tmp);
	}
	
	
	
	/**
	 * 通用更新，无日志
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param request
	 * @return
	 */
	public synchronized static boolean updateNolog(String tableName, String[] columns,
			String[] values, String primaryKey, String pkValue)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableName);
		sql.append(" set ");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i]);
			sql.append((i == columns.length - 1) ? "=?" : "=?,");
		}
		if ((primaryKey != null || primaryKey.trim().length() > 0)
				&& (pkValue != null) || pkValue.trim().length() > 0) {
			sql.append(" where ");
			sql.append(primaryKey);
			sql.append("=?");
		}
		String[] input = new String[values.length + 1];
		Arrays2.copy(values, input, values.length, 0, 0);
		input[values.length] = pkValue;
		//取老数据
		boolean up = dao.runUpdate(sql.toString(), input);
		return up;
	}
}
