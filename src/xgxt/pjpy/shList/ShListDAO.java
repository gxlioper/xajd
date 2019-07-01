
package xgxt.pjpy.shList;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class ShListDAO {

	/**
	 * @param tableName 要查询的表名
	 * @param conditions 查询的条件，其中条件中包含了‘?’占位符
	 * @param values 对应condition中的占位符的输入值
	 * @param cols  要输出的列名
	 * @param userType 用户类型
	 * @param passType 审核状态
	 * @return
	 */
	public  ArrayList<String[]> searchRS(DAO utilDao,String sqlColumn,String tableName,String conditions,String[] values,String[] cols,String userType,String passType){
		 ArrayList<String[]> rs = new ArrayList<String[]>();
		StringBuffer sql = new StringBuffer("");
//		for(int i=0;i<cols.length;i++){
//			sql.append((i==cols.length-1)?cols[i]:cols[i]+",");
//		}
		sqlColumn = StringUtils.isNull(sqlColumn) ? "" : sqlColumn;
		sql.append(sqlColumn);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(conditions);
		//加入审核条件
		if(("xy".equals(userType))){
			sql.append(("通过".equals(passType))?" and xysh='通过'" : (("不通过".equals(passType))?" and xysh='不通过'":("未审核".equalsIgnoreCase(passType) ? "and xysh='未审核'" : "")));
		} else if(("xx".equals(userType))||("admin".equals(userType))){
			sql.append(" and xysh='通过' ");
			sql.append(("通过".equals(passType))?" and xxsh='通过'" : (("不通过".equals(passType))?" and xxsh='不通过'":("未审核".equalsIgnoreCase(passType) ? "and xxsh='未审核'" : "")));
		}
		rs = utilDao.rsToVator(sql.toString(), values, cols);
		return rs;
	}
}
