
package xgxt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class DataManModel {
	//用于存放重复使用的数据
	//这个类作为一个form类型的类来使用
	//只要是要执行查询之类的操作，就将这里面的结果放到session里面去
	//
	private ArrayList<String> tables = new ArrayList<String>();
	private HashMap<String, String[]> colListCN = new HashMap<String, String[]>();
	private HashMap<String, List<HashMap<String, String>>> topTr = new HashMap<String, List<HashMap<String,String>>>();
	public String[] getColListCN(String tableName) {
		return colListCN.get(tableName);
	}
	public void setColListCN(DAO dao,String tableName,String[] colList) {
		(this.colListCN).put(tableName, dao.getColumnNameCN(colList, tableName));
	}
	public List<HashMap<String, String>> getTopTr(String tableName) {
		return topTr.get(tableName);
	}
	public void setTopTr(DAO dao,String tableName,String[] colList) {
		(this.topTr).put(tableName, dao.arrayToList(colList, this.colListCN.get(tableName)));
	}
	public boolean containTable(String tableName) {
		return tables.contains(tableName);
	}
	public void addTable(String tableName) {
		this.tables.add(tableName);
	}
	
}
