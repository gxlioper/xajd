package xgxt.xljk.zjlg.util;

import java.lang.reflect.InvocationTargetException;

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class XljkUtil.
 */
public class XljkUtil {

	/**
	 * Update sql for model.
	 * 
	 * @param insertList the insert list
	 * @param model the model
	 * @param tableName the table name
	 * @param pk the pk
	 * @param pkValue the pk value
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public static String updateSqlForModel(String[] insertList, Object model,
			String tableName,String pk,String pkValue) throws Exception {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(tableName).append(" set ");
		Class<?> myClass = model.getClass();
		if (insertList != null && insertList.length > 0) {
			for (int i = 0; i < insertList.length; i++) {
				sql.append(insertList[i]).append("=");
				String methodName = new StringBuffer("get").append(
						insertList[i].substring(0, 1).toUpperCase()).append(
						insertList[i].substring(1)).toString();
				String sT = (String) myClass.getMethod(methodName,
						(Class[]) null).invoke(model, (Object[]) null);
				sql.append("'");
				sql.append(StringUtils.isNull(sT)?"":sT);
				sql.append("'");
				sql.append((insertList.length - 1) == i ? "" : ", ");
			}
			sql.append(" where ");
			sql.append(pk);
			sql.append("='");
			sql.append(pkValue);
			sql.append("'");
		}
//		 System.out.println(sql);
		return sql.toString();
	}
	
	/**
	 * Insert sql for model.
	 * 
	 * @param insertList the insert list
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public static String insertSqlForModel(String[] insertList, Object model,
			String tableName) throws Exception {
		StringBuffer sql = new StringBuffer("insert into ");
		StringBuffer sqlVal = new StringBuffer();
		sql.append(tableName).append("(");
		Class<?> myClass = model.getClass();
		if (insertList != null && insertList.length > 0) {
			for (int i = 0; i < insertList.length; i++) {
				sql.append(insertList[i]);
				sql.append((insertList.length - 1) == i ? ") values(" : ", ");
				String methodName = new StringBuffer("get").append(
						insertList[i].substring(0, 1).toUpperCase()).append(
						insertList[i].substring(1)).toString();
				String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model, (Object[]) null);
				sqlVal.append("'");
				sqlVal.append(StringUtils.isNull(sT)?"":sT);
				sqlVal.append("'");
				sqlVal.append((insertList.length - 1) == i ? ")" : ", ");
			}
		}
		sql.append(sqlVal.toString());
//		System.out.println(sql);
		return sql.toString();
	}	public static StringBuffer makeQuery(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    传入数组和model，根据传入信息返回查询条件语句
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		return sb;	
	}
}
