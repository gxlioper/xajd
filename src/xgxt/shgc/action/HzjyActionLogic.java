package xgxt.shgc.action;

import java.util.ArrayList;

import xgxt.DAO.*;
public class HzjyActionLogic {
	/**
	 * @param sql is the statement to get the column,ended with where 1=2
	 * */
	private static DAO dao = DAO.getInstance();
	public static String[] getColumnEn(String tableOrView){
		
		String[] cols = null;
		if(tableOrView.equalsIgnoreCase("view_hzjyazb")){
			cols = dao.getColumnName("select * from view_hzjyazb where 1=2");
		}
		return cols;
	}
	public static String[] getColumnCn(String[] columns,String tableName){
		String[] columnCn = dao.getColumnNameCN(columns, tableName);
		return columnCn;
	}
	
	public static ArrayList<String[]> getIteratorRs(String tableName,String[] input,String[] output,
													String xh,String xm,String xydm,String zydm,String bjdm,String nj){
		StringBuffer sql = new StringBuffer();
		if(tableName.equalsIgnoreCase("view_hzjyazb")){
			sql.append("select rownum bh,a.* from view_hzjyazb a where 1=1 "); 
			sql.append((xh == null) || xh.trim().equalsIgnoreCase("") ? "" : " and xh='"+xh+"'");
			sql.append((xm == null) || xm.trim().equalsIgnoreCase("") ? "" : " and xm like '%"+xm+"%");
			sql.append((xydm == null) || xydm.trim().equalsIgnoreCase("") ? "" : " and xydm='"+xydm+"'");
			sql.append((zydm == null) || zydm.trim().equalsIgnoreCase("") ? "" : " and zydm='"+zydm+"'");
			sql.append((bjdm == null) || bjdm.trim().equalsIgnoreCase("") ? "" : "and bjdm='"+bjdm+"'");
			sql.append(" order by a.xh");
		}
		return dao.rsToVator(sql.toString(), input, output);
	}
}
