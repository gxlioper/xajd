package xgxt.studentInfo.zjgszyjsxy;

import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江工商职业技术学院学生信息DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-06</p>
 */
public class XsxxZjgszyjsxyDAO extends DAO {
	
	/**
	 * 获取代码列表
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName, String[] columns){
		String sql = "select distinct ";
		for(int i=0; i<columns.length ; i++){
			if(i==columns.length-1){
				sql += columns[i];
			}else{
				sql += columns[i] + ",";
			}
		}
		sql += " from " + tableName;
		return getList(sql, new String[]{}, columns);
	}
}
