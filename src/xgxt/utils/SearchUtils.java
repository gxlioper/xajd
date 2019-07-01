package xgxt.utils;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class SearchUtils {

	public static String equalSql(String columnName, String columnValue) {
		if (columnValue==null || columnValue.equalsIgnoreCase("")) {
			return " AND 1=1 ";
		} else {
			return " AND "+columnName+"='"+columnValue+"' ";
		}
	}

	public static String likeSql(String columnName, String columnValue) {
		if (columnValue==null || columnValue.equalsIgnoreCase("")) {
			return " AND 1=1 ";
		} else {
			return " AND "+columnName+" LIKE '%"+columnValue+"%' ";
		}
	}

	public static String leftLikeSql(String columnName, String columnValue) {
		if (columnValue==null || columnValue.equalsIgnoreCase("")) {
			return " AND 1=1 ";
		} else {
			return " AND "+columnName+" LIKE '%"+columnValue+"' ";
		}
	}

	public static String rightLikeSql(String columnName, String columnValue) {
		if (columnValue==null || columnValue.equalsIgnoreCase("")) {
			return " AND 1=1 ";
		} else {
			return " AND "+columnName+" LIKE '"+columnValue+"%' ";
		}
	}
	
	public static String makeQueryCondition(String xydm,String zydm,String bjdm,String xb,String xh,String xm,String nj,String nd,String xq,String xn){
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
		}
		if(zydm != null && !("".equalsIgnoreCase(zydm.trim()))){
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("' ");
		}
		if(bjdm != null && !("".equalsIgnoreCase(bjdm.trim()))){
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("' ");
		}
		if(xb != null && !("".equalsIgnoreCase(xb.trim()))){
			sb.append(" and xb='");
			sb.append(xb);
			sb.append("' ");
		}
		if(xh != null && !("".equalsIgnoreCase(xh.trim()))){
			sb.append(" and xh like '%");
			sb.append(xh);
			sb.append("%' ");
		}
		if(xm!= null && !("".equalsIgnoreCase(xm.trim()))){
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%' ");
		}
		if(nj!= null && !("".equalsIgnoreCase(nj.trim()))){
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("' ");
		}
		if(nd!= null && !("".equalsIgnoreCase(nd.trim()))){
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("' ");
		}
		if(xq!= null && !("".equalsIgnoreCase(xq.trim()))){
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("' ");
		}
		if(xn!= null && !("".equalsIgnoreCase(xn.trim()))){
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("' ");
		}
		return sb.toString();
	}
	
	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public static List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList,String [] colListCN) {
		DAO dao = DAO.getInstance();
		if(colListCN==null){
			colListCN = dao.getColumnNameCN(colList, tableName);
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}
	
	/**
	 * 获取表
	 * @param String tableName
	 * @param String[] colList
	 * @return String[]
	 * */
	public static String[] getColumnNameCN(String tableName,String[] colList){
		DAO dao = DAO.getInstance();
		return dao.getColumnNameCN(colList, tableName);
	}
}
