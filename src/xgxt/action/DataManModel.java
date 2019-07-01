
package xgxt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class DataManModel {
	//���ڴ���ظ�ʹ�õ�����
	//�������Ϊһ��form���͵�����ʹ��
	//ֻҪ��Ҫִ�в�ѯ֮��Ĳ������ͽ�������Ľ���ŵ�session����ȥ
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
