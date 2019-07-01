
package xgxt.action.base;

import java.util.List;


import xgxt.DAO.DAO;

public class BaseDAO extends DAO{
	
	/**
	 * 检测记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkVlaue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + "=?";
		String result = getOneRs(sql, new String[]{pkValue} , "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}
	
	
	/**
	 * @param userName
	 * @param groupName
	 * @return boolean
	 * */
	public boolean checkUserToGroup(String userName,String groupName){
		boolean result = false;
		String sql = "select count(a.yhm) cont  from view_yhz_yhxxb a,yhzb b where a.zdm=b.zdm and a.yhm=? and b.zmc=? ";
		String rs = getOneRs(sql, new String[]{userName, groupName}, "cont");
		if(rs != null && Integer.parseInt(rs)>0){
			result = true;
		}
		return result;
	}
	
	/**
	 * 获取表的主键字段
	 * @param tName
	 * @return List
	 * */
	public List getPkOfTable(String tName){
		String sql = "select a.column_name pk from user_cons_columns a,user_constraints b where a.table_name=b.table_name and a.constraint_name=b.constraint_name and b.constraint_type='P' and a.table_name=?";
		return getList(sql, new String[]{tName.toUpperCase()}, new String[]{"pk"});		
	}
	
	/**
	 * 判断用户是否是增加该条记录的用户
	 * @param userName
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean userIsOperator(String userName,String pkValue){
		boolean flag = false;
		pkValue = pkValue == null ? "" : pkValue.trim();
		String sql = "select count(*)num from dmwhczrxxb a where a.xmdm||a.ssb||a.czr=?";
		String result = getOneRs(sql, new String[]{pkValue+userName},"num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		flag = Integer.parseInt(result)>0 ? true : false;
		
		//操作人为空
		sql = "select count(*)num from dmwhczrxxb a where a.xmdm||a.ssb=?";
		result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		flag = Integer.parseInt(result) <1 ? true : flag;
		return flag;		
	}
	
}
